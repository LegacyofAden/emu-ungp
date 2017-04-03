package org.l2junity.commons.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ANZO
 * @since 12.09.2013 2:36 AM.
 */
@Slf4j
public class ClassUtils {
	public static Object singletonInstance(Class<?> clazz) {
		try {
			Method method = clazz.getDeclaredMethod("getInstance");
			return method.invoke(null);
		} catch (Exception e) {
			log.error("Error while calling singleton instance of " + clazz.getSimpleName(), e);
			return null;
		}
	}

	public static List<Method> getMethodsAnnotatedWith(final Class<?> type, final Class<? extends Annotation> annotation) {
		final List<Method> methods = new ArrayList<>();
		Class<?> clazz = type;
		while (clazz != Object.class) {
			final List<Method> allMethods = new ArrayList<>(Arrays.asList(clazz.getDeclaredMethods()));
			methods.addAll(allMethods.stream().filter(method -> method.isAnnotationPresent(annotation)).collect(Collectors.toList()));
			clazz = clazz.getSuperclass();
		}
		return methods;
	}
}
