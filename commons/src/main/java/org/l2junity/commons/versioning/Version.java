/*
 * Copyright © 2016 BDO-Emu authors. All rights reserved.
 * Viewing, editing, running and distribution of this software strongly prohibited.
 * Author: xTz, Anton Lasevich, Tibald
 */

package org.l2junity.commons.versioning;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.jar.Attributes;
import java.util.jar.JarFile;

/**
 * @author ANZO
 * @since 06.07.2016
 */

@Slf4j
public class Version {
	@Getter(lazy = true)
	private static final Version instance = new Version();

	private String versionRevision;
	private String buildDate = "";
	private String buildJdk = "";

	public void init(Class<?> c) {
		File jarName = null;
		try {
			jarName = Locator.getClassSource(c);

			// Only if we starting from Jar
			if (StringUtils.endsWith(jarName.getName(), ".jar")) {
				JarFile jarFile = new JarFile(jarName);

				Attributes attrs = jarFile.getManifest().getMainAttributes();
				setBuildJdk(attrs);
				setBuildDate(attrs);
				setVersionRevision(attrs);
			} else {
				versionRevision = "Dev";
				buildDate = "N/A";
				buildJdk = System.getProperty("java.version");
			}
		} catch (IOException e) {
			log.error("Unable to get soft information\nFile name '" + (jarName.getAbsolutePath()) + "' isn't a valid jar", e);
		} finally {
			info();
		}
	}

	/**
	 * @param attrs manifest attributes
	 */
	private void setVersionRevision(Attributes attrs) {
		String versionRevision = attrs.getValue("Implementation-Version");
		if (versionRevision != null && !versionRevision.isEmpty()) {
			this.versionRevision = versionRevision;
		} else {
			this.versionRevision = "N/A";
		}
	}

	/**
	 * @param attrs manifest attributes
	 */
	private void setBuildJdk(Attributes attrs) {
		String buildJdk = attrs.getValue("Built-JDK");
		if (buildJdk != null) {
			this.buildJdk = buildJdk;
		} else {
			buildJdk = attrs.getValue("Source-Compatibility");
			if (buildJdk != null) {
				this.buildJdk = buildJdk;
			} else {
				this.buildJdk = "-1";
			}
		}
	}

	/**
	 * @param attrs manifest attributes
	 */
	private void setBuildDate(Attributes attrs) {
		String buildDate = attrs.getValue("Built-Date");
		if (buildDate != null) {
			this.buildDate = buildDate;
		} else {
			this.buildDate = "-1";
		}
	}

	public String getVersionRevision() {
		return versionRevision;
	}

	public String getBuildDate() {
		return buildDate;
	}

	public String getBuildJdk() {
		return buildJdk;
	}

	private void info() {
		log.info("=================================================");
		log.info("Revision: ................ " + getVersionRevision());
		log.info("Build date: .............. " + getBuildDate());
		log.info("=================================================");
	}

	@Override
	public String toString() {
		return "Version " + versionRevision + " from " + buildDate;
	}
}
