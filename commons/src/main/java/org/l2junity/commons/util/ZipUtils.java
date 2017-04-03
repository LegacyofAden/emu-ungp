package org.l2junity.commons.util;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ANZO
 * @since 10.03.2017
 */
public class ZipUtils {
	/**
	 * @param zipFilename to construct the file system from
	 * @param create      true if the zip file should be created
	 * @return a zip file system
	 */
	public static FileSystem createZipFileSystem(String zipFilename, boolean create) throws IOException {
		final Path path = Paths.get(zipFilename);
		final URI uri = URI.create("jar:file:" + path.toUri().getPath());

		final Map<String, String> env = new HashMap<>();
		if (create) {
			env.put("create", "true");
		}
		return FileSystems.newFileSystem(uri, env);
	}
}
