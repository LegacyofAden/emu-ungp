/*
 * Copyright © 2016 BDO-Emu authors. All rights reserved.
 * Viewing, editing, running and distribution of this software strongly prohibited.
 * Author: xTz, Anton Lasevich, Tibald
 */

package org.l2junity.core.startup;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.atteo.classindex.ClassIndex;
import org.l2junity.commons.util.ServerInfoUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author ANZO, PointerRage
 * @since 27.12.2016
 */
@Slf4j
public class StartupManager {
	@Getter(lazy = true)
	private final static StartupManager instance = new StartupManager();

	public <SL extends Enum<SL>> void startup(Class<SL> sl) {
		StartupInstance<SL> startup = new StartupInstance<>();

		for (Class<?> clazz : ClassIndex.getAnnotated(StartupComponent.class)) {
			StartupComponent startupAnnotation = clazz.getAnnotation(StartupComponent.class);

			try {
				SL key = Enum.valueOf(sl, startupAnnotation.value());
				final StartModule<SL> module = new StartModule<>(key, clazz);
				startup.put(key, module);
			} catch (Exception e) {
				log.error("Error while initializing StartupManager", e);
			}
		}

		for (Map.Entry<SL, List<StartModule<SL>>> entry : startup.getSorted()) {
			final List<StartModule<SL>> invalidModules = new ArrayList<>();
			final List<StartModule<SL>> modules = entry.getValue();
			for (StartModule<SL> module : modules) {
				final Class<?> clazz = module.getClazz();
				final Class<?>[] dependency = clazz.getAnnotation(StartupComponent.class).dependency();
				for (Class<?> dep : dependency) {
					final Optional<StartModule<SL>> dependencyModule = startup.getAll().stream()
							.filter(m -> m.getClazz().getCanonicalName().equals(dep.getCanonicalName()))
							.findAny();

					if (dependencyModule.isPresent()) {
						module.addDependency(dependencyModule.get());
					} else {
						invalidModules.add(module);
						log.warn("Not found dependency ({}) for {} on {} start level.", dep.getCanonicalName(), clazz.getCanonicalName(), module.getStartLevel().name());
					}
				}
			}

			modules.removeAll(invalidModules);
		}

		// Run registered components
		for (SL startupLevel : sl.getEnumConstants()) {
			ServerInfoUtils.printSection(startupLevel.name());
			((IStartupLevel) startupLevel).invokeDepends();
			startup.runLevel(startupLevel);
		}
	}
}
