/*
 * Copyright (C) 2004-2015 L2J Unity
 * 
 * This file is part of L2J Unity.
 * 
 * L2J Unity is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J Unity is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.l2junity.gameserver.model.actor.instance;

import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.commons.util.Rnd;
import org.l2junity.core.configs.NpcConfig;
import org.l2junity.gameserver.enums.InstanceType;
import org.l2junity.gameserver.model.L2Spawn;
import org.l2junity.gameserver.model.actor.templates.NpcTemplate;
import org.l2junity.gameserver.network.packets.s2c.PlaySound;

import java.util.concurrent.TimeUnit;

/**
 * This class manages all RaidBoss.<br>
 * In a group mob, there are one master called RaidBoss and several slaves called Minions.
 */
public class RaidBossInstance extends MonsterInstance {
	private static final int RAIDBOSS_MAINTENANCE_INTERVAL = 30000; // 30 sec

	private boolean _useRaidCurse = true;

	/**
	 * Constructor of RaidBossInstance (use L2Character and NpcInstance constructor).<br>
	 * <B><U>Actions</U>:</B>
	 * <ul>
	 * <li>Call the L2Character constructor to set the _template of the RaidBossInstance (copy skills from template to object and link _calculators to NPC_STD_CALCULATOR)</li>
	 * <li>Set the name of the RaidBossInstance</li>
	 * <li>Create a RandomAnimation Task that will be launched after the calculated delay if the server allow it</li>
	 * </ul>
	 *
	 * @param template to apply to the NPC
	 */
	public RaidBossInstance(NpcTemplate template) {
		super(template);
		setInstanceType(InstanceType.L2RaidBossInstance);
		setIsRaid(true);
		setLethalable(false);
	}

	@Override
	public void onSpawn() {
		super.onSpawn();
		setRandomWalking(false);
		broadcastPacket(new PlaySound(1, getParameters().getString("RaidSpawnMusic", "Rm01_A"), 0, 0, 0, 0, 0));
	}

	@Override
	protected int getMaintenanceInterval() {
		return RAIDBOSS_MAINTENANCE_INTERVAL;
	}

	/**
	 * Spawn all minions at a regular interval Also if boss is too far from home location at the time of this check, teleport it home.
	 */
	@Override
	protected void startMaintenanceTask() {
		_maintenanceTask = ThreadPool.getInstance().scheduleGeneralAtFixedRate(this::checkAndReturnToSpawn, 60000, getMaintenanceInterval() + Rnd.get(5000), TimeUnit.MILLISECONDS);
	}

	protected void checkAndReturnToSpawn() {
		if (isDead() || isMovementDisabled() || !canReturnToSpawnPoint()) {
			return;
		}

		final L2Spawn spawn = getSpawn();
		if (spawn == null) {
			return;
		}

		if (!isInCombat() && !isMovementDisabled()) {
			if (!isInRadius3d(spawn, Math.max(NpcConfig.MAX_DRIFT_RANGE, 200))) {
				teleToLocation(spawn);
			}
		}
	}

	@Override
	public int getVitalityPoints(int level, double exp, boolean isBoss) {
		return -super.getVitalityPoints(level, exp, isBoss);
	}

	@Override
	public boolean useVitalityRate() {
		return false;
	}

	public void setUseRaidCurse(boolean val) {
		_useRaidCurse = val;
	}

	@Override
	public boolean giveRaidCurse() {
		return _useRaidCurse;
	}
}
