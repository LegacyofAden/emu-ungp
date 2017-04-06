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
package org.l2junity.gameserver.network.packets.s2c;

import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.skills.BuffInfo;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ExAbnormalStatusUpdateFromTarget extends GameServerPacket {
	private final Creature _character;
	private List<Effect> _effects = new ArrayList<>();

	private static class Effect {
		protected int _skillId;
		protected int _level;
		protected int _subLevel;
		protected int _abnormalType;
		protected int _duration;
		protected int _caster;

		public Effect(BuffInfo info) {
			final Skill skill = info.getSkill();
			final Creature caster = info.getEffector();
			int casterId = 0;
			if (caster != null) {
				casterId = caster.getObjectId();
			}

			_skillId = skill.getDisplayId();
			_level = skill.getDisplayLevel();
			_subLevel = skill.getSubLevel();
			_abnormalType = skill.getAbnormalType().getClientId();
			_duration = skill.isAura() ? -1 : info.getTime();
			_caster = casterId;
		}
	}

	public ExAbnormalStatusUpdateFromTarget(Creature character) {
		//@formatter:off
		_character = character;
		_effects = character.getEffectList().getEffects()
				.stream()
				.filter(Objects::nonNull)
				.filter(BuffInfo::isInUse)
				.filter(b -> !b.getSkill().isToggle())
				.map(Effect::new)
				.collect(Collectors.toList());
		//@formatter:on
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_ABNORMAL_STATUS_UPDATE_FROM_TARGET.writeId(body);

		body.writeD(_character.getObjectId());
		body.writeH(_effects.size());

		for (Effect info : _effects) {
			body.writeD(info._skillId);
			body.writeH(info._level);
			body.writeH(info._subLevel);
			body.writeH(info._abnormalType);
			body.writeOptionalD(info._duration);
			body.writeD(info._caster);
		}
	}
}
