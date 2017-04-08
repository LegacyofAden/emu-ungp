package org.l2junity.tools.parsers.ai.class_model;

import org.l2junity.gameserver.retail.AiEventId;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by dmitrij on 15.06.15.
 */
public class ClassInfo {
	private Class clazz;
	private Map<Integer, ClassField> classFieldMap = new HashMap<>();
	private Map<Integer, ClassMethod> classMethodMap = new HashMap<>();

	public ClassInfo(Class clazz) {
		this.clazz = clazz;
	}

	public Class getClazz() {
		return clazz;
	}

	public List<ClassInfo> process() {
		List<ClassInfo> classInfos = new ArrayList<>();

		if (!ClassContainer.visitedClass.contains(clazz)) {
			ClassContainer.visitedClass.add(clazz);

			classInfos.addAll(processFields());
			classInfos.addAll(processMethods());
		}

		return classInfos;
	}

	private List<ClassInfo> processFields() {
		List<ClassInfo> classInfos = new ArrayList<>();
		for (Field field : clazz.getDeclaredFields()) {
			AiEventId eventId = field.getAnnotation(AiEventId.class);
			if (eventId != null) {
				classFieldMap.put(eventId.value(), new ClassField(field.getName(), field.getType()));

				ClassInfo classInfo = new ClassInfo(field.getType());

				classInfos.add(classInfo);
				classInfos.addAll(classInfo.process());
			}
		}

		return classInfos;
	}

	private List<ClassInfo> processMethods() {
		List<ClassInfo> classInfos = new ArrayList<>();
		for (Method method : clazz.getDeclaredMethods()) {
			AiEventId eventId = method.getAnnotation(AiEventId.class);
			if (eventId != null) {
				ClassMethod classMethod = new ClassMethod(method);
				classMethodMap.put(eventId.value(), classMethod);

				classInfos.addAll(classMethod.process());
			}
		}
		return classInfos;
	}

	public Map<Integer, ClassField> getClassFieldMap() {
		return classFieldMap;
	}

	public Map<Integer, ClassMethod> getClassMethodMap() {
		return classMethodMap;
	}

	@Override
	public int hashCode() {
		return clazz.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		return o != null && o instanceof ClassInfo && ((ClassInfo) o).clazz.equals(clazz);
	}

	public ClassMethod findMethod(int eventId) {
		if (classMethodMap.containsKey(eventId))
			return classMethodMap.get(eventId);
		return null;
	}

	public ClassField findField(int eventId) {
		if (classFieldMap.containsKey(eventId))
			return classFieldMap.get(eventId);

		return null;
	}

	public ClassField findField(String varName) {
		for (Map.Entry<Integer, ClassField> entry : classFieldMap.entrySet()) {
			if (Objects.equals(entry.getValue().getEventName(), varName)) {
				return entry.getValue();
			}
		}
		return null;
	}
}
