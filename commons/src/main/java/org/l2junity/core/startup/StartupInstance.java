package org.l2junity.core.startup;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author n3k0nation, ANZO
 */
@Slf4j
public class StartupInstance<SL extends Enum<SL>> {
	private final HashMap<SL, List<StartModule<SL>>> startTable = new HashMap<>();

	void put(SL level, StartModule<SL> module) {
		List<StartModule<SL>> invokes = startTable.computeIfAbsent(level, k -> new ArrayList<>());
		invokes.add(module);
	}

	protected Collection<Map.Entry<SL, List<StartModule<SL>>>> getSorted() {
		return startTable.entrySet();
	}

	protected List<StartModule<SL>> getAll() {
		return startTable.values().stream().flatMap(List::stream).collect(Collectors.toList());
	}

	void runLevel(SL level) {
		List<StartModule<SL>> list = startTable.get(level);
		if (list == null) {
			return;
		}

		for (StartModule<SL> module : list) {
			module.init();
		}
	}
}