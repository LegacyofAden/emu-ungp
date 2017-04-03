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

	public Language valueOfShort(String shortName) {
		for (Language language : Language.values()) {
			if (language.getShortName().equals(shortName)) {
				return language;
			}
		}
		return null;
	}
}