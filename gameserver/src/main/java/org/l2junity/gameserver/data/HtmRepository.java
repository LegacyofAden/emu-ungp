package org.l2junity.gameserver.data;

import com.googlecode.htmlcompressor.compressor.HtmlCompressor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.l2junity.commons.util.ZipUtils;
import org.l2junity.core.configs.ScriptsConfig;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.model.Language;
import org.l2junity.gameserver.model.actor.instance.Player;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author ANZO
 * @since 04.01.2016
 */
@Slf4j
@StartupComponent("Data")
public class HtmRepository {
	@Getter(lazy = true)
	private static final HtmRepository instance = new HtmRepository();

	private final HtmlCompressor htmlCompressor;

	private static final Map<Language, Map<String, String>> caches = new HashMap<>(Language.values().length);

	private HtmRepository() {
		htmlCompressor = new HtmlCompressor();
		htmlCompressor.setEnabled(true);                   //if false all compression is off (default is true)
		htmlCompressor.setRemoveComments(true);            //if false keeps HTML comments (default is true)
		htmlCompressor.setRemoveMultiSpaces(true);         //if false keeps multiple whitespace characters (default is true)
		htmlCompressor.setRemoveIntertagSpaces(true);      //removes iter-tag whitespace characters
		htmlCompressor.setRemoveQuotes(true);              //removes unnecessary tag attribute quotes
		htmlCompressor.setSimpleDoctype(false);             //simplify existing doctype
		htmlCompressor.setRemoveScriptAttributes(false);    //remove optional attributes from script tags
		htmlCompressor.setRemoveStyleAttributes(false);     //remove optional attributes from style tags
		htmlCompressor.setRemoveLinkAttributes(true);      //remove optional attributes from link tags
		htmlCompressor.setRemoveFormAttributes(true);      //remove optional attributes from form tags
		htmlCompressor.setRemoveInputAttributes(true);     //remove optional attributes from input tags
		htmlCompressor.setSimpleBooleanAttributes(true);   //remove values from boolean tag attributes
		htmlCompressor.setRemoveJavaScriptProtocol(false);  //remove "javascript:" from inline event handlers
		htmlCompressor.setRemoveHttpProtocol(false);        //replace "http://" with "//" inside tag attributes
		htmlCompressor.setRemoveHttpsProtocol(false);       //replace "https://" with "//" inside tag attributes
		htmlCompressor.setPreserveLineBreaks(false);        //preserves original line breaks
		htmlCompressor.setRemoveSurroundingSpaces("all"); //remove spaces around provided tags

		htmlCompressor.setCompressCss(false);               //compress inline css
		htmlCompressor.setCompressJavaScript(false);        //compress inline javascript

		htmlCompressor.setGenerateStatistics(true);

		loadHtmls();

		for (Map.Entry<Language, Map<String, String>> entry : caches.entrySet()) {
			log.info("Loaded {} html's for {} language.", entry.getValue().size(), entry.getKey());
		}
	}

	private void loadHtmls() {
		for (Language language : Language.values()) {
			caches.put(language, new HashMap<>());
		}

		for (Language language : Language.values()) {
			try {
				Path htmPath = Paths.get(ScriptsConfig.DATA_ROOT.toString(), "html", language.getShortName());

				if (!Files.exists(htmPath)) {
					log.error("Can't find directory {}", htmPath);
					continue;
				}

				try (Stream<Path> stream = Files.walk(htmPath)) {
					stream.filter(path -> !Files.isDirectory(path))
							.forEach(path -> readFile(language, path));
				}
			} catch (IOException e) {
				throw new IllegalArgumentException(e);
			}
		}
	}

	public void reload() {
		caches.clear();
		loadHtmls();
	}

	public String getHtm(Player player, String htmPath) {
		return getHtm(player.getLang(), htmPath);
	}

	public String getHtm(Language language, String htmPath) {
		String htm = caches.get(language).get(htmPath);
		if (htm != null && !htm.isEmpty()) {
			return htm;
		}
		log.error("Can't find html=[{}] for language=[{}]", htmPath, language);
		return null;
	}

	public String getCustomHtm(Language language, String htmPath) {
		return getHtm(language, "custom/" + htmPath);
	}

	public String getCustomHtm(Player player, String htmPath) {
		return getHtm(player.getLang(), "custom/" + htmPath);
	}

	public String getCustomHtm(String htmPath) {
		return getHtm(Language.ENGLISH, "custom/" + htmPath);
	}

	private void readFile(Language language, Path filePath) {
		boolean isZip = FilenameUtils.getExtension(filePath.toString()).equals("zip");
		if (isZip) {
			try (FileSystem zipFileSystem = ZipUtils.createZipFileSystem(filePath.toString(), false)) {
				Path root = zipFileSystem.getPath("/");
				Files.walk(root).filter(item -> FilenameUtils.getExtension(item.toString()).equals("htm")).forEach(path -> {
					try (InputStream is = Files.newInputStream(path)) {
						addHtm(language, path, IOUtils.toString(is, UTF_8));
					} catch (Exception e) {
						log.error("Error while loading html=[{}] from archive=[{}]", path, filePath);
					}
				});
			} catch (IOException e) {
				log.error("Error while reading HTML files from archive", e);
			}
		} else {
			try {
				Path relativePath = Paths.get(ScriptsConfig.DATA_ROOT.toString(), "html", language.getShortName()).relativize(filePath);
				addHtm(language, relativePath, new String(Files.readAllBytes(filePath), UTF_8));
			} catch (IOException e) {
				log.error("Error while reading HTML file [{}]", filePath.toString(), e);
			}
		}
	}

	private void addHtm(Language language, Path htmPath, String htm) {
		if (ScriptsConfig.HTML_COMPRESSION_ENABLE) {
			htm = htmlCompressor.compress(htm);
		}
		caches.get(language).putIfAbsent(htmPath.toString().replaceFirst("/*$", ""), htm);
	}
}
