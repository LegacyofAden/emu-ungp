package org.l2junity.commons.config.utils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.atteo.classindex.ClassIndex;
import org.l2junity.commons.config.annotation.ConfigAfterLoad;
import org.l2junity.commons.config.annotation.ConfigComments;
import org.l2junity.commons.config.annotation.ConfigFile;
import org.l2junity.commons.config.annotation.ConfigProperty;
import org.l2junity.commons.lang.management.ShutdownManager;
import org.l2junity.commons.lang.management.TerminationStatus;
import org.l2junity.core.startup.StartupComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * @author ANZO
 */
@Slf4j
@StartupComponent("Configure")
public final class ConfigLoader {
	@Getter(lazy = true)
	private static final ConfigLoader instance = new ConfigLoader();

	private static Set<String> VAR_NAMES_CACHE = new HashSet<>();

	private ConfigLoader() {
		loadConfigs();
	}

	public void loadConfigs() {
		try {
			for (Class<?> clazz : ClassIndex.getAnnotated(ConfigFile.class)) {
				boolean loadConfig = false;
				ConfigFile annotation = clazz.getAnnotation(ConfigFile.class);
				if (annotation.loadForPackages().length > 0) {
					for (String classPath : annotation.loadForPackages()) {
						if (!StringUtils.isEmpty(classPath)) {
							URL url = getClass().getClassLoader().getResource(classPath.replace(".", "/"));
							if (url != null) {
								loadConfig = true;
								break;
							}
						}
					}
				} else {
					loadConfig = true;
				}

				if (!loadConfig) {
					continue;
				}

				final File file = new File(annotation.name());

				if (!file.exists() && file.isDirectory()) {
					file.mkdirs();
				}

				if (!file.exists()) {
					buildConfig(clazz);
				} else {
					updateConfig(clazz);
				}

				loadConfig(clazz);
			}
		} catch (Exception e) {
			log.error("Error while loading configs. Halting system.", e);
			ShutdownManager.getInstance().halt(TerminationStatus.RUNTIME_UNCAUGHT_ERROR);
		}
	}

	private void updateConfig(Class<?> clazz) {
		Properties properties = new Properties();
		String fileName = clazz.getAnnotation(ConfigFile.class).name();
		try (InputStream input = new FileInputStream(fileName)) {
			properties.load(input);
		} catch (IOException ex) {
			log.error("Error while calling loadConfig", ex);
		}

		if (properties.size() == clazz.getDeclaredFields().length) {
			return;
		}

		StrBuilder out = new StrBuilder();
		for (Field field : clazz.getDeclaredFields()) {
			ConfigProperty annotation = field.getAnnotation(ConfigProperty.class);
			if (annotation != null) {
				final String propertyValue = properties.getProperty(configName(field));
				if (propertyValue == null) {
					out.appendln("").appendln(generateFieldConfig(field));
					log.info("Updated '{}' config with new field '{}'", fileName, configName(field));
				}
			}
		}
		if (out.length() > 0) {
			final Path path = Paths.get(fileName);
			try {
				Files.write(path, out.toString().getBytes(), StandardOpenOption.APPEND);
			} catch (Exception e) {
				log.error("Error while writing config update", e);
			}
		}
	}

	private void buildConfig(Class<?> clazz) {
		String fileName = clazz.getAnnotation(ConfigFile.class).name();
		Path path = Paths.get(fileName);
		log.info("Generated '{}'", fileName);
		try {
			Files.deleteIfExists(path);
			Files.createDirectories(path.getParent());
		} catch (IOException ex) {
			log.error("Error while buildConfig()", ex);
			return;
		}

		StrBuilder out = new StrBuilder();

		for (Field field : clazz.getDeclaredFields()) {
			String configFields = generateFieldConfig(field);
			if (configFields != null && !configFields.isEmpty()) {
				out.appendln(configFields);
			}
		}
		try {
			Files.write(path, out.toString().getBytes(), StandardOpenOption.CREATE);
		} catch (IOException ex) {
			log.error("Error while writing config file: " + path, ex);
		}
	}

	private String generateFieldConfig(Field field) {
		ConfigComments configComments = field.getAnnotation(ConfigComments.class);
		ConfigProperty configProperty = field.getAnnotation(ConfigProperty.class);
		String cfgName = configName(field);

		if (configProperty != null) {
			StrBuilder out = new StrBuilder();
			if (configComments != null && configComments.comment().length > 0) {
				for (String txt : configComments.comment()) {
					out.appendln("# " + txt);
				}
			} else if (configProperty.comment().length > 0) {
				for (String txt : configProperty.comment()) {
					out.appendln("# " + txt);
				}
			}

			if (VAR_NAMES_CACHE.contains(cfgName)) {
				log.warn("Config property name [{}] already defined!", cfgName);
			} else {
				VAR_NAMES_CACHE.add(cfgName);
			}

			out.appendln(cfgName + " = " + configProperty.value());
			return out.toString();
		}
		return null;
	}

	private void loadConfig(Class<?> clazz) {
		Properties properties = new Properties();

		String fileName = clazz.getAnnotation(ConfigFile.class).name();

		log.info("Loading config file: {}", fileName);
		try (InputStream input = new FileInputStream(fileName)) {
			properties.load(input);
		} catch (IOException ex) {
			log.error("Error while calling loadConfig", ex);
		}

		try {
			Object configObject = clazz.newInstance();

			for (Field field : clazz.getFields()) {
				ConfigProperty annotation = field.getAnnotation(ConfigProperty.class);
				if (annotation == null) {
					continue;
				}
				if (!Modifier.isStatic(field.getModifiers())
						|| Modifier.isFinal(field.getModifiers())) {
					log.warn("Invalid modifiers for {}", field);
					return;
				}

				setConfigValue(configObject, field, properties, annotation);
			}

			for (Method method : clazz.getDeclaredMethods()) {
				if (method.isAnnotationPresent(ConfigAfterLoad.class)) {
					boolean isMethodAccessible = method.isAccessible();
					method.setAccessible(true);
					try {
						method.invoke(configObject);
					} catch (Exception e) {
						log.error("Error while calling ConfigAfterLoad method", e);
					} finally {
						method.setAccessible(isMethodAccessible);
					}
				}
			}
		} catch (Exception e) {
			log.error("Error while initializing config object", e);
		}
	}

	@SuppressWarnings("unchecked")
	private void setConfigValue(Object object, Field field, Properties properties, ConfigProperty annotation) {
		final String propertyValue = properties.getProperty(configName(field), annotation.value());
		if (StringUtils.isEmpty(propertyValue)) {
			return;
		}
		try {
			if (field.getType().isArray()) {
				Class<?> baseType = field.getType().getComponentType();
				String[] values = propertyValue.split(annotation.splitter());

				Object array = Array.newInstance(baseType, values.length);
				field.set(null, array);

				int index = 0;
				for (String arrValue : values) {
					Array.set(array, index, ConfigTypeCaster.cast(baseType, arrValue.trim()));
					++index;
				}

				field.set(null, array);
			} else if (field.getType().isAssignableFrom(List.class)) {
				Class genericType = (Class) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
				String[] values = propertyValue.split(annotation.splitter());
				((List<Object>) field.get(object)).clear();
				for (String value : values) {
					if (!value.trim().isEmpty()) {
						((List<Object>) field.get(object)).add(ConfigTypeCaster.cast(genericType, value.trim()));
					}
				}
			} else if (field.getType().isAssignableFrom(Map.class)) {
				((Map<Object, Object>) field.get(object)).clear();

				Class keyType = (Class) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
				Class valueType = (Class) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[1];
				String[] values = propertyValue.split(annotation.splitter());
				for (String value : values) {
					String[] valueDatas = value.trim().split(":");
					Object keyObj = ConfigTypeCaster.cast(keyType, valueDatas[0].trim());
					Object valueObj = ConfigTypeCaster.cast(valueType, valueDatas[1].trim());
					((Map<Object, Object>) field.get(object)).put(keyObj, valueObj);
				}
			} else {
				ConfigTypeCaster.cast(object, field, propertyValue);
			}
		} catch (IllegalAccessException e) {
			log.error("Invalid modifiers for field {}", field);
		}
	}

	private String configName(Field field) {
		ConfigProperty anno = field.getAnnotation(ConfigProperty.class);
		return anno == null || anno.name().isEmpty() ? field.getName() : anno.name();
	}
}