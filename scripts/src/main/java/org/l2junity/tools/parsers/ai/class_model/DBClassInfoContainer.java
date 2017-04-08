package org.l2junity.tools.parsers.ai.class_model;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.tools.parsers.ai.model.DBClassInfo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ANZO
 * @since 21.03.2017
 */
@Slf4j
public class DBClassInfoContainer {
	@Getter(lazy = true)
	private final static DBClassInfoContainer instance = new DBClassInfoContainer();

	private Map<String, DBClassInfo> dbClassInfoMap = new ConcurrentHashMap<>();

	public void add(DBClassInfo classInfo) {
		dbClassInfoMap.put(classInfo.getName(), classInfo);
	}

	public DBClassInfo get(String className) {
		return dbClassInfoMap.get(className);
	}

	public boolean exists(String className) {
		return dbClassInfoMap.containsKey(className);
	}
}
