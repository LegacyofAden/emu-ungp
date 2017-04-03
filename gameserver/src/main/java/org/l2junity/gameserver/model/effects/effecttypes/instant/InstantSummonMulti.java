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

import org.l2junity.gameserver.data.xml.impl.ExperienceData;
import org.l2junity.gameserver.data.xml.impl.NpcData;
import org.l2junity.gameserver.enums.Race;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.L2ServitorInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.actor.templates.L2NpcTemplate;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.holders.ItemHolder;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.skills.Skill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SummonMulti effect implementation.
 *
 * @author UnAfraid
 */
public final class InstantSummonMulti extends AbstractEffect {
	private static final Logger LOGGER = LoggerFactory.getLogger(InstantSummonMulti.class);

	private final int _npcId;
	private final float _expMultiplier;
	private final ItemHolder _consumeItem;
	private final int _lifeTime;
	private final int _consumeItemInterval;
	private final int _summonPoints;

	public InstantSummonMulti(StatsSet params) {
		_npcId = params.getInt("npcId");
		_expMultiplier = params.getFloat("expMultiplier", 1);
		_consumeItem = new ItemHolder(params.getInt("consumeItemId", 0), params.getInt("consumeItemCount", 1));
		_consumeItemInterval = params.getInt("consumeItemInterval", 0);
		_lifeTime = params.getInt("lifeTime", 3600) > 0 ? params.getInt("lifeTime", 3600) * 1000 : -1;
		_summonPoints = params.getInt("summonPoints", 0);
	}

	@Override
	public void instant(Creature caster, WorldObject target, Skill skill, ItemInstance item) {
		final Player casterPlayer = caster.asPlayer();
		if (casterPlayer == null) {
			return;
		}

		if ((casterPlayer.getSummonPoints() + _summonPoints) > casterPlayer.getMaxSummonPoints()) {
			return;
		}
		final L2NpcTemplate template = NpcData.getInstance().getTemplate(_npcId);
		final L2ServitorInstance summon = new L2ServitorInstance(template, casterPlayer);
		final int consumeItemInterval = (_consumeItemInterval > 0 ? _consumeItemInterval : (template.getRace() != Race.SIEGE_WEAPON ? 240 : 60)) * 1000;

		summon.setName(template.getName());
		summon.setTitle(casterPlayer.getName());
		summon.setReferenceSkill(skill.getId());
		summon.setExpMultiplier(_expMultiplier);
		summon.setLifeTime(_lifeTime);
		summon.setItemConsume(_consumeItem);
		summon.setItemConsumeInterval(consumeItemInterval);

		if (summon.getLevel() >= ExperienceData.getInstance().getMaxPetLevel()) {
			summon.getStat().setExp(ExperienceData.getInstance().getExpForLevel(ExperienceData.getInstance().getMaxPetLevel() - 1));
			LOGGER.warn("({}) NpcID: {} has a level above {}. Please rectify.", summon.getName(), summon.getId(), ExperienceData.getInstance().getMaxPetLevel());
		} else {
			summon.getStat().setExp(ExperienceData.getInstance().getExpForLevel(summon.getLevel() % ExperienceData.getInstance().getMaxPetLevel()));
		}

		summon.setCurrentHp(summon.getMaxHp());
		summon.setCurrentMp(summon.getMaxMp());
		summon.setHeading(casterPlayer.getHeading());
		summon.setSummonPoints(_summonPoints);

		casterPlayer.addServitor(summon);
		casterPlayer.getEffectList().getBuffs().stream().filter(info -> info.getSkill().isSharedWithSummon()).forEach(info -> info.getSkill().applyEffects(casterPlayer, summon, false, info.getAbnormalTime()));

		summon.setShowSummonAnimation(true);
		summon.setRunning();
		summon.spawnMe();
	}
}
