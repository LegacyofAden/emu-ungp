package org.l2junity.gameserver.model;

import java.io.Serializable;

public enum Language implements Serializable {
	ENGLISH("en"),
	RUSSIAN("ru");

	private final String shortName;

	Language(String shortName) {
		this.shortName = shortName;
	}

	public String getShortName() {
		return shortName;
	}
}