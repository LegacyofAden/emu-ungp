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
import org.l2junity.gameserver.data.xml.impl.ExperienceData;
import org.l2junity.gameserver.enums.AttributeType;
import org.l2junity.gameserver.enums.ItemGrade;
import org.l2junity.gameserver.enums.UserInfoType;
import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.Party;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.base.ClassId;
import org.l2junity.gameserver.model.zone.ZoneId;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author Sdw, UnAfraid
 */
public class UserInfo extends AbstractMaskPacket<UserInfoType> {
	private final Player _activeChar;

	private final int _relation;
	private final int _runSpd;
	private final int _walkSpd;
	private final int _swimRunSpd;
	private final int _swimWalkSpd;
	private final int _flRunSpd = 0;
	private final int _flWalkSpd = 0;
	private final int _flyRunSpd;
	private final int _flyWalkSpd;
	private final double _moveMultiplier;
	private int _enchantLevel = 0;
	private int _armorEnchant = 0;
	private final String _title;

	private final byte[] _masks = new byte[]
			{
					(byte) 0x00,
					(byte) 0x00,
					(byte) 0x00
			};

	private int _initSize = 5;

	public UserInfo(Player cha) {
		this(cha, true);
	}

	public UserInfo(Player cha, boolean addAll) {
		_activeChar = cha;

		_relation = calculateRelation(cha);
		_moveMultiplier = cha.getMovementSpeedMultiplier();
		_runSpd = (int) Math.round(cha.getRunSpeed() / _moveMultiplier);
		_walkSpd = (int) Math.round(cha.getWalkSpeed() / _moveMultiplier);
		_swimRunSpd = (int) Math.round(cha.getSwimRunSpeed() / _moveMultiplier);
		_swimWalkSpd = (int) Math.round(cha.getSwimWalkSpeed() / _moveMultiplier);
		_flyRunSpd = cha.isFlying() ? _runSpd : 0;
		_flyWalkSpd = cha.isFlying() ? _walkSpd : 0;
		_enchantLevel = cha.getInventory().getWeaponEnchant();
		_armorEnchant = cha.getInventory().getArmorMinEnchant();
		_title = cha.isInvisible() ? "Invisible" : _activeChar.getAppearance().getVisibleTitle();

		if (addAll) {
			addComponentType(UserInfoType.values());
		}
	}

	@Override
	protected byte[] getMasks() {
		return _masks;
	}

	@Override
	protected void onNewMaskAdded(UserInfoType component) {
		calcBlockSize(component);
	}

	private void calcBlockSize(UserInfoType type) {
		switch (type) {
			case BASIC_INFO: {
				_initSize += type.getBlockLength() + (_activeChar.getAppearance().getVisibleName().length() * 2);
				break;
			}
			case CLAN: {
				_initSize += type.getBlockLength() + (_title.length() * 2);
				break;
			}
			default: {
				_initSize += type.getBlockLength();
				break;
			}
		}
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.USER_INFO.writeId(body);

		body.writeD(_activeChar.getObjectId());
		body.writeD(_initSize);
		body.writeH(23);
		body.writeB(_masks);

		if (containsMask(UserInfoType.RELATION)) {
			body.writeD(_relation);
		}

		if (containsMask(UserInfoType.BASIC_INFO)) {
			body.writeH(16 + (_activeChar.getAppearance().getVisibleName().length() * 2));
			body.writeString(_activeChar.getAppearance().getVisibleName());
			body.writeC(_activeChar.isGM() ? 0x01 : 0x00);
			body.writeC(_activeChar.getRace().ordinal());
			body.writeC(_activeChar.getAppearance().getSex() ? 0x01 : 0x00);
			body.writeD(ClassId.getRootClassId(_activeChar.getBaseClass()).getId());
			body.writeD(_activeChar.getClassId().getId());
			body.writeC(_activeChar.getLevel());
		}

		if (containsMask(UserInfoType.BASE_STATS)) {
			body.writeH(18);
			body.writeH(_activeChar.getSTR());
			body.writeH(_activeChar.getDEX());
			body.writeH(_activeChar.getCON());
			body.writeH(_activeChar.getINT());
			body.writeH(_activeChar.getWIT());
			body.writeH(_activeChar.getMEN());
			body.writeH(_activeChar.getLUC());
			body.writeH(_activeChar.getCHA());
		}

		if (containsMask(UserInfoType.MAX_HPCPMP)) {
			body.writeH(14);
			body.writeD(_activeChar.getMaxHp());
			body.writeD(_activeChar.getMaxMp());
			body.writeD(_activeChar.getMaxCp());
		}

		if (containsMask(UserInfoType.CURRENT_HPMPCP_EXP_SP)) {
			body.writeH(38);
			body.writeD((int) Math.round(_activeChar.getCurrentHp()));
			body.writeD((int) Math.round(_activeChar.getCurrentMp()));
			body.writeD((int) Math.round(_activeChar.getCurrentCp()));
			body.writeQ(_activeChar.getSp());
			body.writeQ(_activeChar.getExp());
			body.writeF((float) (_activeChar.getExp() - ExperienceData.getInstance().getExpForLevel(_activeChar.getLevel())) / (ExperienceData.getInstance().getExpForLevel(_activeChar.getLevel() + 1) - ExperienceData.getInstance().getExpForLevel(_activeChar.getLevel())));
		}

		if (containsMask(UserInfoType.ENCHANTLEVEL)) {
			body.writeH(4);
			body.writeC(_enchantLevel);
			body.writeC(_armorEnchant);
		}

		if (containsMask(UserInfoType.APPAREANCE)) {
			body.writeH(15);
			body.writeD(_activeChar.getVisualHair());
			body.writeD(_activeChar.getVisualHairColor());
			body.writeD(_activeChar.getVisualFace());
			body.writeC(_activeChar.isHairAccessoryEnabled() ? 0x01 : 0x00);
		}

		if (containsMask(UserInfoType.STATUS)) {
			body.writeH(6);
			body.writeC(_activeChar.getMountType().ordinal());
			body.writeC(_activeChar.getPrivateStoreType().getId());
			body.writeC(_activeChar.getCrystallizeGrade() != ItemGrade.NONE ? 1 : 0);
			body.writeC(_activeChar.getAbilityPointsUsed());
		}

		if (containsMask(UserInfoType.STATS)) {
			body.writeH(56);
			body.writeH(_activeChar.getActiveWeaponItem() != null ? 40 : 20);
			body.writeD(_activeChar.getPAtk());
			body.writeD(_activeChar.getPAtkSpd());
			body.writeD(_activeChar.getPDef());
			body.writeD(_activeChar.getEvasionRate());
			body.writeD(_activeChar.getAccuracy());
			body.writeD(_activeChar.getCriticalHit());
			body.writeD(_activeChar.getMAtk());
			body.writeD(_activeChar.getMAtkSpd());
			body.writeD(_activeChar.getPAtkSpd()); // Seems like atk speed - 1
			body.writeD(_activeChar.getMagicEvasionRate());
			body.writeD(_activeChar.getMDef());
			body.writeD(_activeChar.getMagicAccuracy());
			body.writeD(_activeChar.getMCriticalHit());
		}

		if (containsMask(UserInfoType.ELEMENTALS)) {
			body.writeH(14);
			body.writeH(_activeChar.getDefenseElementValue(AttributeType.FIRE));
			body.writeH(_activeChar.getDefenseElementValue(AttributeType.WATER));
			body.writeH(_activeChar.getDefenseElementValue(AttributeType.WIND));
			body.writeH(_activeChar.getDefenseElementValue(AttributeType.EARTH));
			body.writeH(_activeChar.getDefenseElementValue(AttributeType.HOLY));
			body.writeH(_activeChar.getDefenseElementValue(AttributeType.DARK));
		}

		if (containsMask(UserInfoType.POSITION)) {
			body.writeH(18);
			body.writeD((int) _activeChar.getX());
			body.writeD((int) _activeChar.getY());
			body.writeD((int) _activeChar.getZ());
			body.writeD(_activeChar.isInVehicle() ? _activeChar.getVehicle().getObjectId() : 0);
		}

		if (containsMask(UserInfoType.SPEED)) {
			body.writeH(18);
			body.writeH(_runSpd);
			body.writeH(_walkSpd);
			body.writeH(_swimRunSpd);
			body.writeH(_swimWalkSpd);
			body.writeH(_flRunSpd);
			body.writeH(_flWalkSpd);
			body.writeH(_flyRunSpd);
			body.writeH(_flyWalkSpd);
		}

		if (containsMask(UserInfoType.MULTIPLIER)) {
			body.writeH(18);
			body.writeF(_moveMultiplier);
			body.writeF(_activeChar.getAttackSpeedMultiplier());
		}

		if (containsMask(UserInfoType.COL_RADIUS_HEIGHT)) {
			body.writeH(18);
			body.writeF(_activeChar.getCollisionRadius());
			body.writeF(_activeChar.getCollisionHeight());
		}

		if (containsMask(UserInfoType.ATK_ELEMENTAL)) {
			body.writeH(5);
			final AttributeType attackAttribute = _activeChar.getAttackElement();
			body.writeC(attackAttribute.getClientId());
			body.writeH(_activeChar.getAttackElementValue(attackAttribute));
		}

		if (containsMask(UserInfoType.CLAN)) {
			body.writeH(32 + (_title.length() * 2));
			body.writeString(_title);
			body.writeH(_activeChar.getPledgeType());
			body.writeD(_activeChar.getClanId());
			body.writeD(_activeChar.getClanCrestLargeId());
			body.writeD(_activeChar.getClanCrestId());
			body.writeD(_activeChar.getClanPrivileges().getBitmask());
			body.writeC(_activeChar.isClanLeader() ? 0x01 : 0x00);
			body.writeD(_activeChar.getAllyId());
			body.writeD(_activeChar.getAllyCrestId());
			body.writeC(_activeChar.isInMatchingRoom() ? 0x01 : 0x00);
		}

		if (containsMask(UserInfoType.SOCIAL)) {
			body.writeH(22);
			body.writeC(_activeChar.getPvpFlag());
			body.writeD(_activeChar.getReputation()); // Reputation
			body.writeC(_activeChar.getNobleStatus().getClientId());
			body.writeC(_activeChar.isHero() ? 0x01 : 0x00);
			body.writeC(_activeChar.getPledgeClass());
			body.writeD(_activeChar.getPkKills());
			body.writeD(_activeChar.getPvpKills());
			body.writeH(_activeChar.getRecomLeft());
			body.writeH(_activeChar.getRecomHave());
		}

		if (containsMask(UserInfoType.VITA_FAME)) {
			body.writeH(15);
			body.writeD(_activeChar.getVitalityPoints());
			body.writeC(0x00); // Vita Bonus
			body.writeD(_activeChar.getFame());
			body.writeD(_activeChar.getRaidbossPoints());
		}

		if (containsMask(UserInfoType.SLOTS)) {
			body.writeH(9);
			body.writeC(_activeChar.getInventory().getTalismanSlots()); // Confirmed
			body.writeC(_activeChar.getInventory().getBroochJewelSlots()); // Confirmed
			body.writeC(_activeChar.getTeam().getId()); // Confirmed
			body.writeD(0x00); // Some kind of aura mask (1 = Red, 2 = White, 3 = White AND Red, there is higher values: 20, 50, 100 produces different aura) dotted / straight circle ring on the floor
		}

		if (containsMask(UserInfoType.MOVEMENTS)) {
			body.writeH(4);
			body.writeC(_activeChar.isInsideZone(ZoneId.WATER) ? 1 : _activeChar.isFlyingMounted() ? 2 : 0);
			body.writeC(_activeChar.isRunning() ? 0x01 : 0x00);
		}

		if (containsMask(UserInfoType.COLOR)) {
			body.writeH(10);
			body.writeD(_activeChar.getAppearance().getNameColor());
			body.writeD(_activeChar.getAppearance().getTitleColor());
		}

		if (containsMask(UserInfoType.INVENTORY_LIMIT)) {
			body.writeH(9);
			body.writeD(0x00);
			body.writeH(_activeChar.getInventoryLimit());
			body.writeC(0x00); // if greater than 1 show the attack cursor when interacting, CoC or Cursed Weapon level ?
		}

		if (containsMask(UserInfoType.UNK_3)) {
			body.writeH(9);
			body.writeC(0x00);
			body.writeD(0x00);
			body.writeC(0x00);
			body.writeC(_activeChar.isTrueHero() ? 100 : 0x00);
		}
	}

	private int calculateRelation(Player activeChar) {
		int relation = 0;
		final Party party = activeChar.getParty();
		final Clan clan = activeChar.getClan();

		if (party != null) {
			relation |= 0x08; // Party member
			if (party.getLeader() == _activeChar) {
				relation |= 0x10; // Party leader
			}
		}

		if (clan != null) {
			relation |= 0x20; // Clan member
			if (clan.getLeaderId() == activeChar.getObjectId()) {
				relation |= 0x40; // Clan leader
			}
		}

		if (activeChar.isInSiege()) {
			relation |= 0x80; // In siege
		}

		return relation;
	}
}
