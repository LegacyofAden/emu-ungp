package org.l2junity.tools.parsers.ai.class_model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitrij on 15.06.15.
 */
public class MethodParameter {
	private final int eventId;
	public String realName;
	public String eventName;
	public Class type;

	public MethodParameter(String realName, Class<?> type) {
		this(-1, realName, realName, type);
	}

	public MethodParameter(int eventId, String eventName, String realName, Class<?> type) {
		this.eventId = eventId;
		this.eventName = eventName;
		this.realName = realName;
		this.type = type;
	}

	public List<ClassInfo> process() {
		List<ClassInfo> classInfos = new ArrayList<>();

		ClassInfo classInfo = new ClassInfo(type);

		classInfos.add(classInfo);
		classInfos.addAll(classInfo.process());

		return classInfos;
	}
}
