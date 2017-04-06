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
import org.l2junity.gameserver.enums.NpcInfoType;
import org.l2junity.gameserver.enums.Team;
import org.l2junity.gameserver.model.actor.Summon;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.skills.AbnormalVisualEffect;
import org.l2junity.gameserver.model.zone.ZoneId;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.Set;

/**
 * @author Sdw
 */
public class ExPetInfo extends AbstractMaskPacket<NpcInfoType> {
	private final Summon _summon;
	private final Player _attacker;
	private final int _val;
	private final byte[] _masks = new byte[]
			{
					(byte) 0x00,
					(byte) 0x0C,
					(byte) 0x0C,
					(byte) 0x00,
					(byte) 0x00
			};

	private int _initSize = 0;
	private int _blockSize = 0;

	private int _clanCrest = 0;
	private int _clanLargeCrest = 0;
	private int _allyCrest = 0;
	private int _allyId = 0;
	private int _clanId = 0;
	private int _statusMask = 0;
	private final String _title;
	private final Set<AbnormalVisualEffect> _abnormalVisualEffects;

	public ExPetInfo(Summon summon, Player attacker, int val) {
		_summon = summon;
		_attacker = attacker;
		_title = (summon.getOwner() != null) && summon.getOwner().isOnline() ? summon.getOwner().getName() : "";
		_val = val;
		_abnormalVisualEffects = summon.getEffectList().getCurrentAbnormalVisualEffects();

		if (summon.getTemplate().getDisplayId() != summon.getTemplate().getId()) {
			_masks[2] |= 0x10;
			addComponentType(NpcInfoType.NAME);
		}

		addComponentType(NpcInfoType.ATTACKABLE, NpcInfoType.UNKNOWN1, NpcInfoType.TITLE, NpcInfoType.ID, NpcInfoType.POSITION, NpcInfoType.ALIVE, NpcInfoType.RUNNING);

		if (summon.getHeading() > 0) {
			addComponentType(NpcInfoType.HEADING);
		}

		if ((summon.getStat().getPAtkSpd() > 0) || (summon.getStat().getMAtkSpd() > 0)) {
			addComponentType(NpcInfoType.ATK_CAST_SPEED);
		}

		if (summon.getRunSpeed() > 0) {
			addComponentType(NpcInfoType.SPEED_MULTIPLIER);
		}

		if ((summon.getWeapon() > 0) || (summon.getArmor() > 0)) {
			addComponentType(NpcInfoType.EQUIPPED);
		}

		if (summon.getTeam() != Team.NONE) {
			addComponentType(NpcInfoType.TEAM);
		}

		if (summon.isInsideZone(ZoneId.WATER) || summon.isFlying()) {
			addComponentType(NpcInfoType.SWIM_OR_FLY);
		}

		if (summon.isFlying()) {
			addComponentType(NpcInfoType.FLYING);
		}

		if (summon.getMaxHp() > 0) {
			addComponentType(NpcInfoType.MAX_HP);
		}

		if (summon.getMaxMp() > 0) {
			addComponentType(NpcInfoType.MAX_MP);
		}

		if (summon.getCurrentHp() <= summon.getMaxHp()) {
			addComponentType(NpcInfoType.CURRENT_HP);
		}

		if (summon.getCurrentMp() <= summon.getMaxMp()) {
			addComponentType(NpcInfoType.CURRENT_MP);
		}

		if (!_abnormalVisualEffects.isEmpty()) {
			addComponentType(NpcInfoType.ABNORMALS);
		}

		if (summon.getTemplate().getWeaponEnchant() > 0) {
			addComponentType(NpcInfoType.ENCHANT);
		}

		if (summon.getTransformationDisplayId() > 0) {
			addComponentType(NpcInfoType.TRANSFORMATION);
		}

		if (summon.isShowSummonAnimation()) {
			addComponentType(NpcInfoType.SUMMONED);
		}

		if (summon.getReputation() != 0) {
			addComponentType(NpcInfoType.REPUTATION);
		}

		if (summon.getOwner().getClan() != null) {
			_clanId = summon.getOwner().getAppearance().getVisibleClanId();
			_clanCrest = summon.getOwner().getAppearance().getVisibleClanCrestId();
			_clanLargeCrest = summon.getOwner().getAppearance().getVisibleClanLargeCrestId();
			_allyCrest = summon.getOwner().getAppearance().getVisibleAllyId();
			_allyId = summon.getOwner().getAppearance().getVisibleAllyCrestId();

			addComponentType(NpcInfoType.CLAN);
		}

		addComponentType(NpcInfoType.UNKNOWN8);

		// TODO: Confirm me
		if (summon.isInCombat()) {
			_statusMask |= 0x01;
		}
		if (summon.isDead()) {
			_statusMask |= 0x02;
		}
		if (summon.isTargetable()) {
			_statusMask |= 0x04;
		}

		_statusMask |= 0x08;

		if (_statusMask != 0) {
			addComponentType(NpcInfoType.VISUAL_STATE);
		}
	}

	@Override
	protected byte[] getMasks() {
		return _masks;
	}

	@Override
	protected void onNewMaskAdded(NpcInfoType component) {
		calcBlockSize(_summon, component);
	}

	private void calcBlockSize(Summon summon, NpcInfoType type) {
		switch (type) {
			case ATTACKABLE:
			case UNKNOWN1: {
				_initSize += type.getBlockLength();
				break;
			}
			case TITLE: {
				_initSize += type.getBlockLength() + (_title.length() * 2);
				break;
			}
			case NAME: {
				_blockSize += type.getBlockLength() + (summon.getName().length() * 2);
				break;
			}
			default: {
				_blockSize += type.getBlockLength();
				break;
			}
		}
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_PET_INFO.writeId(body);

		body.writeD(_summon.getObjectId());
		body.writeC(_val); // // 0=teleported 1=default 2=summoned
		body.writeH(37); // mask_bits_37
		body.writeB(_masks);

		// Block 1
		body.writeC(_initSize);

		if (containsMask(NpcInfoType.ATTACKABLE)) {
			body.writeC(_summon.isAutoAttackable(_attacker) ? 0x01 : 0x00);
		}
		if (containsMask(NpcInfoType.UNKNOWN1)) {
			body.writeD(0x00); // unknown
		}
		if (containsMask(NpcInfoType.TITLE)) {
			body.writeS(_title);
		}

		// Block 2
		body.writeH(_blockSize);
		if (containsMask(NpcInfoType.ID)) {
			body.writeD(_summon.getTemplate().getDisplayId() + 1000000);
		}
		if (containsMask(NpcInfoType.POSITION)) {
			body.writeD((int) _summon.getX());
			body.writeD((int) _summon.getY());
			body.writeD((int) _summon.getZ());
		}
		if (containsMask(NpcInfoType.HEADING)) {
			body.writeD(_summon.getHeading());
		}
		if (containsMask(NpcInfoType.UNKNOWN2)) {
			body.writeD(0x00); // Unknown
		}
		if (containsMask(NpcInfoType.ATK_CAST_SPEED)) {
			body.writeD(_summon.getPAtkSpd());
			body.writeD(_summon.getMAtkSpd());
		}
		if (containsMask(NpcInfoType.SPEED_MULTIPLIER)) {
			body.writeE((float) _summon.getStat().getMovementSpeedMultiplier());
			body.writeE((float) _summon.getStat().getAttackSpeedMultiplier());
		}
		if (containsMask(NpcInfoType.EQUIPPED)) {
			body.writeD(_summon.getWeapon());
			body.writeD(_summon.getArmor()); // Armor id?
			body.writeD(0x00);
		}
		if (containsMask(NpcInfoType.ALIVE)) {
			body.writeC(_summon.isDead() ? 0x00 : 0x01);
		}
		if (containsMask(NpcInfoType.RUNNING)) {
			body.writeC(_summon.isRunning() ? 0x01 : 0x00);
		}
		if (containsMask(NpcInfoType.SWIM_OR_FLY)) {
			body.writeC(_summon.isInsideZone(ZoneId.WATER) ? 0x01 : _summon.isFlying() ? 0x02 : 0x00);
		}
		if (containsMask(NpcInfoType.TEAM)) {
			body.writeC(_summon.getTeam().getId());
		}
		if (containsMask(NpcInfoType.ENCHANT)) {
			body.writeD(_summon.getTemplate().getWeaponEnchant());
		}
		if (containsMask(NpcInfoType.FLYING)) {
			body.writeD(_summon.isFlying() ? 0x01 : 00);
		}
		if (containsMask(NpcInfoType.CLONE)) {
			body.writeD(0x00); // Player ObjectId with Decoy
		}
		if (containsMask(NpcInfoType.UNKNOWN8)) {
			// No visual effect
			body.writeD(0x00); // Unknown
		}
		if (containsMask(NpcInfoType.DISPLAY_EFFECT)) {
			body.writeD(0x00);
		}
		if (containsMask(NpcInfoType.TRANSFORMATION)) {
			body.writeD(_summon.getTransformationDisplayId()); // Transformation ID
		}
		if (containsMask(NpcInfoType.CURRENT_HP)) {
			body.writeD((int) _summon.getCurrentHp());
		}
		if (containsMask(NpcInfoType.CURRENT_MP)) {
			body.writeD((int) _summon.getCurrentMp());
		}
		if (containsMask(NpcInfoType.MAX_HP)) {
			body.writeD(_summon.getMaxHp());
		}
		if (containsMask(NpcInfoType.MAX_MP)) {
			body.writeD(_summon.getMaxMp());
		}
		if (containsMask(NpcInfoType.SUMMONED)) {
			body.writeC(_summon.isShowSummonAnimation() ? 0x02 : 0x00); // 2 - do some animation on spawn
		}
		if (containsMask(NpcInfoType.UNKNOWN12)) {
			body.writeD(0x00);
			body.writeD(0x00);
		}
		if (containsMask(NpcInfoType.NAME)) {
			body.writeS(_summon.getName());
		}
		if (containsMask(NpcInfoType.NAME_NPCSTRINGID)) {
			body.writeD(-1); // NPCStringId for name
		}
		if (containsMask(NpcInfoType.TITLE_NPCSTRINGID)) {
			body.writeD(-1); // NPCStringId for title
		}
		if (containsMask(NpcInfoType.PVP_FLAG)) {
			body.writeC(_summon.getPvpFlag()); // PVP flag
		}
		if (containsMask(NpcInfoType.REPUTATION)) {
			body.writeD(_summon.getReputation()); // Name color
		}
		if (containsMask(NpcInfoType.CLAN)) {
			body.writeD(_clanId);
			body.writeD(_clanCrest);
			body.writeD(_clanLargeCrest);
			body.writeD(_allyId);
			body.writeD(_allyCrest);
		}

		if (containsMask(NpcInfoType.VISUAL_STATE)) {
			body.writeC(_statusMask);
		}

		if (containsMask(NpcInfoType.ABNORMALS)) {
			body.writeH(_abnormalVisualEffects.size());
			for (AbnormalVisualEffect abnormalVisualEffect : _abnormalVisualEffects) {
				body.writeH(abnormalVisualEffect.getClientId());
			}
		}
	}
}