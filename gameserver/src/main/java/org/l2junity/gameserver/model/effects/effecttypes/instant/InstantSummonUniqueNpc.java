/*
 * Copyright (C) 2004-2015 L2J DataPack
 * 
 * This file is part of L2J DataPack.
 * 
 * L2J DataPack is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J DataPack is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.l2junity.gameserver.model.effects.effecttypes.instant;

import org.l2junity.commons.util.Rnd;
import org.l2junity.gameserver.data.xml.impl.NpcData;
import org.l2junity.gameserver.model.L2Spawn;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.DecoyInstance;
import org.l2junity.gameserver.model.actor.instance.EffectPointInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.actor.templates.NpcTemplate;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.interfaces.ILocational;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.skills.TargetType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Summon Npc effect implementation.
 *
 * @author Zoey76
 */
public final class InstantSummonUniqueNpc extends AbstractEffect {
	private static final Logger LOGGER = LoggerFactory.getLogger(InstantSummonUniqueNpc.class);

	private int _despawnDelay;
	private final int _npcId;
	private final int _npcCount;
	private final boolean _randomOffset;
	private final boolean _isSummonSpawn;

	public InstantSummonUniqueNpc(StatsSet params) {
		_despawnDelay = params.getInt("despawnDelay", 20000);
		_npcId = params.getInt("npcId", 0);
		_npcCount = params.getInt("npcCount", 1);
		_randomOffset = params.getBoolean("randomOffset", false);
		_isSummonSpawn = params.getBoolean("isSummonSpawn", false);
	}

	@Override
	public void instant(Creature caster, WorldObject target, Skill skill, ItemInstance item) {
		final Player casterPlayer = caster.asPlayer();
		if (casterPlayer == null) {
			return;
		}

		if (casterPlayer.isAlikeDead() || casterPlayer.inObserverMode()) {
			return;
		}

		if ((_npcId <= 0) || (_npcCount <= 0)) {
			LOGGER.warn("Invalid NPC ID or count skill ID: {}", skill.getId());
			return;
		}

		if (casterPlayer.isMounted()) {
			return;
		}

		final NpcTemplate npcTemplate = NpcData.getInstance().getTemplate(_npcId);
		if (npcTemplate == null) {
			LOGGER.warn("Spawn of the nonexisting NPC ID: {}, skill ID: {}", _npcId, skill.getId());
			return;
		}

		double x = casterPlayer.getX();
		double y = casterPlayer.getY();
		double z = casterPlayer.getZ();
		if (skill.getTargetType() == TargetType.GROUND) {
			final ILocational wordPosition = casterPlayer.getCurrentSkillWorldPosition();
			if (wordPosition != null) {
				x = wordPosition.getX();
				y = wordPosition.getY();
				z = wordPosition.getZ();
			}
		}

		if (_randomOffset) {
			x += (Rnd.nextBoolean() ? Rnd.get(20, 50) : Rnd.get(-50, -20));
			y += (Rnd.nextBoolean() ? Rnd.get(20, 50) : Rnd.get(-50, -20));
		}

		switch (npcTemplate.getType()) {
			case "L2Decoy": {
				final DecoyInstance decoy = new DecoyInstance(npcTemplate, casterPlayer, _despawnDelay);
				decoy.setCurrentHp(decoy.getMaxHp());
				decoy.setCurrentMp(decoy.getMaxMp());
				decoy.setHeading(casterPlayer.getHeading());
				decoy.setInstance(casterPlayer.getInstanceWorld());
				decoy.setSummoner(casterPlayer);
				decoy.spawnMe(x, y, z);
				break;
			}
			case "L2EffectPoint": // TODO: Implement proper signet skills.
			{
				final EffectPointInstance effectPoint = new EffectPointInstance(npcTemplate, casterPlayer);
				effectPoint.setCurrentHp(effectPoint.getMaxHp());
				effectPoint.setCurrentMp(effectPoint.getMaxMp());
				effectPoint.setIsInvul(true);
				effectPoint.setSummoner(casterPlayer);
				effectPoint.setTitle(casterPlayer.getName());
				effectPoint.spawnMe(x, y, z);
				_despawnDelay = effectPoint.getParameters().getInt("despawn_time", 0) * 1000;
				if (_despawnDelay > 0) {
					effectPoint.scheduleDespawn(_despawnDelay);
				}
				break;
			}
			default: {
				L2Spawn spawn;
				try {
					spawn = new L2Spawn(npcTemplate);
				} catch (Exception e) {
					LOGGER.warn("Unable to create spawn", e);
					return;
				}

				spawn.setXYZ(x, y, z);
				spawn.setHeading(casterPlayer.getHeading());
				spawn.stopRespawn();

				// Delete any other identical npc
				casterPlayer.getSummonedNpcs().stream().filter(npc -> npc.getId() == _npcId).forEach(npc -> npc.deleteMe());

				final Npc npc = spawn.doSpawn(_isSummonSpawn);
				casterPlayer.addSummonedNpc(npc); // npc.setSummoner(player);
				npc.setName(npcTemplate.getName());
				npc.setTitle(npcTemplate.getName());
				if (_despawnDelay > 0) {
					npc.scheduleDespawn(_despawnDelay);
				}
				npc.setIsRunning(false); // TODO: Fix broadcast info.
			}
		}
	}
}
