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
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.interfaces.ILocational;
import org.l2junity.gameserver.model.skills.SkillCastingType;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * MagicSkillUse server packet implementation.
 *
 * @author UnAfraid, NosBit
 */
public final class MagicSkillUse extends GameServerPacket {
	private final int _skillId;
	private final int _skillLevel;
	private final int _hitTime;
	private final int _reuseGroup;
	private final int _reuseDelay;
	private final int _actionId; // If skill is called from RequestActionUse, use that ID.
	private final SkillCastingType _castingType; // Defines which client bar is going to use.
	private final Creature _activeChar;
	private final WorldObject _target;
	private final List<Integer> _unknown = Collections.emptyList();
	private final List<ILocational> _groundLocations;

	public MagicSkillUse(Creature cha, WorldObject target, int skillId, int skillLevel, int hitTime, int reuseDelay, int reuseGroup, int actionId, SkillCastingType castingType) {
		_activeChar = cha;
		_target = target;
		_skillId = skillId;
		_skillLevel = skillLevel;
		_hitTime = hitTime;
		_reuseGroup = reuseGroup;
		_reuseDelay = reuseDelay;
		_actionId = actionId;
		_castingType = castingType;
		ILocational skillWorldPos = null;
		if (cha.isPlayer()) {
			final Player player = cha.getActingPlayer();
			if (player.getCurrentSkillWorldPosition() != null) {
				skillWorldPos = player.getCurrentSkillWorldPosition();
			}
		}
		_groundLocations = skillWorldPos != null ? Arrays.asList(skillWorldPos) : Collections.emptyList();
	}

	public MagicSkillUse(Creature cha, WorldObject target, int skillId, int skillLevel, int hitTime, int reuseDelay) {
		this(cha, target, skillId, skillLevel, hitTime, reuseDelay, -1, -1, SkillCastingType.NORMAL);
	}

	public MagicSkillUse(Creature cha, int skillId, int skillLevel, int hitTime, int reuseDelay) {
		this(cha, cha, skillId, skillLevel, hitTime, reuseDelay, -1, -1, SkillCastingType.NORMAL);
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.MAGIC_SKILL_USE.writeId(body);

		body.writeD(_castingType.getClientBarId()); // Casting bar type: 0 - default, 1 - default up, 2 - blue, 3 - green, 4 - red.
		body.writeD(_activeChar.getObjectId());
		body.writeD(_target.getObjectId());
		body.writeD(_skillId);
		body.writeD(_skillLevel);
		body.writeD(_hitTime);
		body.writeD(_reuseGroup);
		body.writeD(_reuseDelay);
		body.writeD((int) _activeChar.getX());
		body.writeD((int) _activeChar.getY());
		body.writeD((int) _activeChar.getZ());
		body.writeH(_unknown.size()); // TODO: Implement me!
		for (int unknown : _unknown) {
			body.writeH(unknown);
		}
		body.writeH(_groundLocations.size());
		for (ILocational target : _groundLocations) {
			body.writeD((int) target.getX());
			body.writeD((int) target.getY());
			body.writeD((int) target.getZ());
		}
		body.writeD((int) _target.getX());
		body.writeD((int) _target.getY());
		body.writeD((int) _target.getZ());
		body.writeD(_actionId >= 0 ? 0x01 : 0x00); // 1 when ID from RequestActionUse is used
		body.writeD(_actionId >= 0 ? _actionId : 0); // ID from RequestActionUse. Used to set cooldown on summon skills.
	}
}