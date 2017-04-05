package org.l2junity.gameserver.model.variables;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.gameserver.model.Language;
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.holders.ItemHolder;
import org.l2junity.gameserver.model.holders.SkillHolder;
import org.l2junity.gameserver.model.holders.TrainingHolder;

import java.io.*;
import java.util.*;

/**
 * @author UnAfraid
 * @since 05.04.2017
 */
@Slf4j
public class ValidObjectInputStream extends ObjectInputStream {
	private static final List<Class<?>> VALID_CLASSES = new ArrayList<>();

	static {
		// Default classes
		VALID_CLASSES.add(String.class);
		VALID_CLASSES.add(Boolean.class);

		// Unity's default classes
		VALID_CLASSES.add(TrainingHolder.class);
		VALID_CLASSES.add(ItemHolder.class);
		VALID_CLASSES.add(SkillHolder.class);
		VALID_CLASSES.add(Location.class);
		VALID_CLASSES.add(Language.class);

		// Custom classes
		Arrays.stream(GeneralConfig.ACCOUNT_VARIABLES_CLASS_WHITELIST)
				.map(className ->
				{
					try {
						return Class.forName(className);
					} catch (Exception e) {
						log.warn("Missing class: {}", className);
						return null;
					}
				})
				.filter(Objects::nonNull)
				.forEach(VALID_CLASSES::add);
	}

	public ValidObjectInputStream(InputStream inputStream) throws IOException {
		super(inputStream);
	}

	@Override
	protected Class<?> resolveClass(ObjectStreamClass osc) throws IOException, ClassNotFoundException {
		final Class<?> clazz = super.resolveClass(osc);
		if (!clazz.isPrimitive() && !clazz.isArray() && !Number.class.isAssignableFrom(clazz) && !Collection.class.isAssignableFrom(clazz) && !Map.class.isAssignableFrom(clazz) && !Enum.class.isAssignableFrom(clazz) && !VALID_CLASSES.contains(clazz)) {
			throw new InvalidClassException("Unauthorized deserialization attempt", osc.getName());
		}
		return clazz;
	}
}