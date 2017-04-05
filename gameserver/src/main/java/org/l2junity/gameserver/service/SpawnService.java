package org.l2junity.gameserver.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.data.xml.impl.SpawnsData;
import org.l2junity.gameserver.model.spawns.SpawnTemplate;

/**
 * @author ANZO
 * @since 02.04.2017
 */
@Slf4j
@StartupComponent("AfterStart")
public class SpawnService {
	@Getter(lazy = true)
	private static final SpawnService instance = new SpawnService();

	private SpawnService() {
		if (GeneralConfig.ALT_DEV_NO_SPAWNS) {
			return;
		}
		spawnAll();
	}

	public void spawnAll() {
		log.info("Initializing spawns...");
		SpawnsData.getInstance().getSpawns(SpawnTemplate::isSpawningByDefault).forEach(template ->
		{
			template.spawnAll(null);
			template.notifyActivate();
		});
		log.info("All spawns have been initialized!");
	}

	public void despawnAll() {
		log.info("Removing all spawns...");
		SpawnsData.getInstance().getSpawns().forEach(SpawnTemplate::despawnAll);
		log.info("All spawns have been removed!");
	}
}