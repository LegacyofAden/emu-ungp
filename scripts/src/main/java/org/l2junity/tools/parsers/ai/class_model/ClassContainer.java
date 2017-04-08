package org.l2junity.tools.parsers.ai.class_model;

import lombok.Getter;
import org.l2junity.gameserver.retail.ai.MakerEventHandler;
import org.l2junity.gameserver.retail.ai.NpcEventHandler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dmitrij on 15.06.15.
 */
public class ClassContainer {
	@Getter(lazy = true)
	private final static ClassContainer instance = new ClassContainer();

	public static Set<Class> visitedClass = new HashSet<>();
	private Set<ClassInfo> classInfoList = new HashSet<>();

	public ClassContainer() {
		processClass(NpcEventHandler.class);
		processClass(MakerEventHandler.class);
	}

	private void processClass(Class clazz) {
		ClassInfo classInfo = new ClassInfo(clazz);
		classInfoList.addAll(classInfo.process());

		classInfoList.add(classInfo);
	}

	public ClassMethod findMethod(int eventId, Class clazz) {
		for (ClassInfo classInfo : classInfoList) {
			if (classInfo.getClazz() == clazz) {
				return classInfo.findMethod(eventId);
			}
		}

		return null;
	}

	public ClassField findField(int eventId, Class clazz) {
		for (ClassInfo classInfo : classInfoList) {
			if (classInfo.getClazz() == clazz) {
				return classInfo.findField(eventId);
			}
		}

		return null;
	}

	public ClassField findField(String varName, Class clazz) {
		for (ClassInfo classInfo : classInfoList) {
			if (classInfo.getClazz() == clazz) {
				return classInfo.findField(varName);
			}
		}
		return null;
	}

	public List<ClassField> findFields(int eventId) {
		List<ClassField> fields = new ArrayList<>();
		for (ClassInfo classInfo : classInfoList) {
			ClassField classField = classInfo.findField(eventId);
			if (classField != null) {
				fields.add(classField);
			}
		}
		return fields;
	}
}
