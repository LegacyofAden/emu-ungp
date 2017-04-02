/*
 * Copyright © 2016 BDO-Emu authors. All rights reserved.
 * Viewing, editing, running and distribution of this software strongly prohibited.
 * Author: xTz, Anton Lasevich, Tibald
 */

package org.l2junity.core.startup;

import lombok.Getter;
import org.l2junity.commons.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PointerRage
 */
public class StartModule<SL extends Enum<SL>> {
	private final List<StartModule<SL>> dependency = new ArrayList<>(0);

	@Getter
	private final SL startLevel;
	@Getter
	private final Class<?> clazz;

	@Getter
	private Object instance;

	public StartModule(SL startLevel, Class<?> clazz) {
		this.startLevel = startLevel;
		this.clazz = clazz;
	}

	public void addDependency(StartModule<SL> module) {
		dependency.add(module);
	}

	public void init() {
		if (instance != null) { //cannot allow to secondary init
			return;
		}

		for (StartModule<SL> depend : dependency) {
			depend.init();
		}

		instance = ClassUtils.singletonInstance(clazz);
	}
}