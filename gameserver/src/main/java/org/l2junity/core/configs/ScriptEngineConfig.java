package org.l2junity.core.configs;

import org.l2junity.commons.config.annotation.ConfigComments;
import org.l2junity.commons.config.annotation.ConfigFile;
import org.l2junity.commons.config.annotation.ConfigProperty;

/**
 * @author ANZO
 * @since 02.04.2017
 */
@ConfigFile(name = "configs/scriptengine.properties")
public class ScriptEngineConfig {
	@ConfigProperty(name = "PreferedCompiler", value = "com.sun.tools.javac.api.JavacTool")
	@ConfigComments(comment = {
			"The prefered java compiler api to use.",
			"The value is a fully qualified name of a class which implements the javax.toold.JavaCompiler and has a zero argument constructor.",
			"When the prefered compiler is not set, the first found compiler is used.",
			"When the prefered compiler is not found, the last found compiler is used."
	})
	public static String[] PREFERED_COMPILER;

	@ConfigProperty(name = "ClassLoader", value = "System")
	@ConfigComments(comment = {
			"The parent class loader for isolated script class loaders.",
			"When this property is not specified, has an invalid value or is a class name which could not be found, the System classloader is used.",
			"Values: System, ThreadContext or a fully qualified java class name"
	})
	public static String CLASS_LOADER;

	@ConfigProperty(name = "SourceCompatibility", value = "1.8")
	@ConfigComments(comment = {
			"Source compatibility"
	})
	public static String SOURCE_COMPATIBILITY;

	@ConfigProperty(name = "SourcePath", value = "data/scripts")
	@ConfigComments(comment = {
			"The java sourcepath, when you have a different datapack root, you must change this too."
	})
	public static String SOURCE_PATH;

	@ConfigProperty(name = "DebugArgs", value = "source,lines,vars")
	@ConfigComments(comment = {
			"The debug informations to generate for compiled class files."
	})
	public static String DEBUG_ARGS;

	@ConfigProperty(name = "WarnArgs", value = "-enumSwitch")
	@ConfigComments(comment = {
			"Ignore missing enum switch for ecj."
	})
	public static String WARN_VARS;
}