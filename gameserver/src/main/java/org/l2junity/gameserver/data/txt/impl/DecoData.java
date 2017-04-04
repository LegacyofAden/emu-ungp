package org.l2junity.gameserver.data.txt.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.l2junity.core.configs.ScriptsConfig;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.data.txt.gen.*;
import org.l2junity.gameserver.data.txt.model.decodata.DecoDataTemplate;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ANZO
 * @since 04.04.2017
 */
@Slf4j
public class DecoData extends DecoDatasBaseListener {
	@Getter(lazy = true)
	private static final DecoData instance = new DecoData();

	private Map<Integer, DecoDataTemplate> templates = new HashMap<>();

	private DecoData() {
		log.info("Loading data file: decodata.txt");
		final Path filePath = Paths.get(ScriptsConfig.DATA_ROOT.toString(), "scripts", "decodata.txt");
		if (Files.exists(filePath)) {
			try (InputStream is = Files.newInputStream(filePath)) {
				ANTLRInputStream input = new ANTLRInputStream(is);
				DecoDatasLexer lexer = new DecoDatasLexer(input);
				CommonTokenStream tokens = new CommonTokenStream(lexer);
				DecoDatasParser parser = new DecoDatasParser(tokens);

				ParseTree tree = parser.file();
				ParseTreeWalker walker = new ParseTreeWalker();
				walker.walk(this, tree);
			} catch (Exception e) {
				log.error("Error while loading decodata.txt", e);
			}
			finally {
				log.info("Loaded {} DecoDataTemplate's", templates.size());
			}
		}
	}

	@Override
	public void exitDecoData(DecoDatasParser.DecoDataContext ctx) {
		DecoDataTemplate decoTemplate = new DecoDataTemplate(ctx);
		templates.put(decoTemplate.getId(), decoTemplate);
	}

	public DecoDataTemplate getTemplate(int id) {
		return templates.get(id);
	}

	public Map<Integer, DecoDataTemplate> getTemplates() {
		return templates;
	}
}
