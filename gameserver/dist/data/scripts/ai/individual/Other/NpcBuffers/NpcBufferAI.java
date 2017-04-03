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
package ai.individual.Other.NpcBuffers;

import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.gameserver.data.xml.impl.SkillData;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.skills.BuffInfo;
import org.l2junity.gameserver.model.skills.Skill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author UnAfraid
 */
public class NpcBufferAI implements Runnable {
	private static final Logger LOGGER = LoggerFactory.getLogger(NpcBufferAI.class);
	private final Npc _npc;
	private final NpcBufferSkillData _skillData;

	protected NpcBufferAI(Npc npc, NpcBufferSkillData skill) {
		_npc = npc;
		_skillData = skill;
	}

	private Skill getSkill(Player player) {
		if (_skillData.getScaleToLevel() < 1) {
			return _skillData.getSkill();
		}

		final BuffInfo currentBuff = player.getEffectList().getBuffInfoBySkillId(_skillData.getSkill().getId());
		if (currentBuff != null) {
			int level = currentBuff.getSkill().getLevel();
			if (_skillData.getScaleToLevel() > level) {
				level++;
			}

			final Skill skill = SkillData.getInstance().getSkill(_skillData.getSkill().getId(), level);
			if (skill == null) {
				LOGGER.warn("Requested non existing skill level: {} for id: {}", level, _skillData.getSkill().getId());
			}
			return skill;
		}

		return _skillData.getSkill();
	}

	@Override
	public void run() {
		if ((_npc == null) || !_npc.isSpawned() || _npc.isDecayed() || _npc.isDead() || (_skillData == null) || (_skillData.getSkill() == null)) {
			return;
		}

		if ((_npc.getSummoner() == null) || !_npc.getSummoner().isPlayer()) {
			return;
		}

		final Player player = _npc.getSummoner().getActingPlayer();

		final Skill skill = getSkill(player);
		if (skill == null) {
			return;
		}

		_npc.doCast(skill);

		ThreadPool.getInstance().scheduleGeneral(this, skill.getReuseDelay(), TimeUnit.MILLISECONDS);
	}
}