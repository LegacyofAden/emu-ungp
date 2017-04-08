package org.l2junity.tools.parsers.ai.class_model;

/**
 * Created by dmitrij on 15.06.15.
 */
public class ClassField {
	private final String eventName;
	private final Class type;

	public ClassField(String eventName, Class type) {
		this.eventName = eventName;
		this.type = type;
	}

	public String getEventName() {
		return eventName;
	}

	public Class getType() {
		return type;
	}
}
