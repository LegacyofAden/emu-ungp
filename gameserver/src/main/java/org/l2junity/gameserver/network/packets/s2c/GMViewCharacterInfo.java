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
import org.l2junity.gameserver.model.VariationInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

public class GMViewCharacterInfo extends GameServerPacket {
	private final Player _activeChar;
	private final int _runSpd, _walkSpd;
	private final int _swimRunSpd, _swimWalkSpd;
	private final int _flyRunSpd, _flyWalkSpd;
	private final double _moveMultiplier;

	public GMViewCharacterInfo(Player cha) {
		_activeChar = cha;
		_moveMultiplier = cha.getMovementSpeedMultiplier();
		_runSpd = (int) Math.round(cha.getRunSpeed() / _moveMultiplier);
		_walkSpd = (int) Math.round(cha.getWalkSpeed() / _moveMultiplier);
		_swimRunSpd = (int) Math.round(cha.getSwimRunSpeed() / _moveMultiplier);
		_swimWalkSpd = (int) Math.round(cha.getSwimWalkSpeed() / _moveMultiplier);
		_flyRunSpd = cha.isFlying() ? _runSpd : 0;
		_flyWalkSpd = cha.isFlying() ? _walkSpd : 0;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.GM_VIEW_CHARACTER_INFO.writeId(body);

		body.writeD((int) _activeChar.getX());
		body.writeD((int) _activeChar.getY());
		body.writeD((int) _activeChar.getZ());
		body.writeD(_activeChar.getHeading());
		body.writeD(_activeChar.getObjectId());
		body.writeS(_activeChar.getName());
		body.writeD(_activeChar.getRace().ordinal());
		body.writeD(_activeChar.getAppearance().getSex() ? 1 : 0);
		body.writeD(_activeChar.getClassId().getId());
		body.writeD(_activeChar.getLevel());
		body.writeQ(_activeChar.getExp());
		body.writeF((float) (_activeChar.getExp() - ExperienceData.getInstance().getExpForLevel(_activeChar.getLevel())) / (ExperienceData.getInstance().getExpForLevel(_activeChar.getLevel() + 1) - ExperienceData.getInstance().getExpForLevel(_activeChar.getLevel()))); // High Five exp %
		body.writeD(_activeChar.getSTR());
		body.writeD(_activeChar.getDEX());
		body.writeD(_activeChar.getCON());
		body.writeD(_activeChar.getINT());
		body.writeD(_activeChar.getWIT());
		body.writeD(_activeChar.getMEN());
		body.writeD(_activeChar.getLUC());
		body.writeD(_activeChar.getCHA());
		body.writeD(_activeChar.getMaxHp());
		body.writeD((int) _activeChar.getCurrentHp());
		body.writeD(_activeChar.getMaxMp());
		body.writeD((int) _activeChar.getCurrentMp());
		body.writeQ(_activeChar.getSp());
		body.writeD(_activeChar.getCurrentLoad());
		body.writeD(_activeChar.getMaxLoad());
		body.writeD(_activeChar.getPkKills());

		for (int slot : getPaperdollOrder()) {
			body.writeD(_activeChar.getInventory().getPaperdollObjectId(slot));
		}

		for (int slot : getPaperdollOrder()) {
			body.writeD(_activeChar.getInventory().getPaperdollItemDisplayId(slot));
		}

		for (int slot : getPaperdollOrder()) {
			final VariationInstance augment = _activeChar.getInventory().getPaperdollAugmentation(slot);
			body.writeD(augment != null ? augment.getOption1Id() : 0); // Confirmed
			body.writeD(augment != null ? augment.getOption2Id() : 0); // Confirmed
		}

		body.writeC(_activeChar.getInventory().getTalismanSlots()); // CT2.3
		body.writeC(_activeChar.getInventory().canEquipCloak() ? 1 : 0); // CT2.3
		body.writeD(0x00);
		body.writeH(0x00);
		body.writeD(_activeChar.getPAtk());
		body.writeD(_activeChar.getPAtkSpd());
		body.writeD(_activeChar.getPDef());
		body.writeD(_activeChar.getEvasionRate());
		body.writeD(_activeChar.getAccuracy());
		body.writeD(_activeChar.getCriticalHit());
		body.writeD(_activeChar.getMAtk());

		body.writeD(_activeChar.getMAtkSpd());
		body.writeD(_activeChar.getPAtkSpd());

		body.writeD(_activeChar.getMDef());
		body.writeD(_activeChar.getMagicEvasionRate());
		body.writeD(_activeChar.getMagicAccuracy());
		body.writeD(_activeChar.getMCriticalHit());

		body.writeD(_activeChar.getPvpFlag()); // 0-non-pvp 1-pvp = violett name
		body.writeD(_activeChar.getReputation());

		body.writeD(_runSpd);
		body.writeD(_walkSpd);
		body.writeD(_swimRunSpd);
		body.writeD(_swimWalkSpd);
		body.writeD(_flyRunSpd);
		body.writeD(_flyWalkSpd);
		body.writeD(_flyRunSpd);
		body.writeD(_flyWalkSpd);
		body.writeF(_moveMultiplier);
		body.writeF(_activeChar.getAttackSpeedMultiplier()); // 2.9);//
		body.writeF(_activeChar.getCollisionRadius()); // scale
		body.writeF(_activeChar.getCollisionHeight()); // y offset ??!? fem dwarf 4033
		body.writeD(_activeChar.getAppearance().getHairStyle());
		body.writeD(_activeChar.getAppearance().getHairColor());
		body.writeD(_activeChar.getAppearance().getFace());
		body.writeD(_activeChar.isGM() ? 0x01 : 0x00); // builder level

		body.writeS(_activeChar.getTitle());
		body.writeD(_activeChar.getClanId()); // pledge id
		body.writeD(_activeChar.getClanCrestId()); // pledge crest id
		body.writeD(_activeChar.getAllyId()); // ally id
		body.writeC(_activeChar.getMountType().ordinal()); // mount type
		body.writeC(_activeChar.getPrivateStoreType().getId());
		body.writeC(_activeChar.getCreateItemLevel() > 0 ? 1 : 0);
		body.writeD(_activeChar.getPkKills());
		body.writeD(_activeChar.getPvpKills());

		body.writeH(_activeChar.getRecomLeft());
		body.writeH(_activeChar.getRecomHave()); // Blue value for name (0 = white, 255 = pure blue)
		body.writeD(_activeChar.getClassId().getId());
		body.writeD(0x00); // special effects? circles around player...
		body.writeD(_activeChar.getMaxCp());
		body.writeD((int) _activeChar.getCurrentCp());

		body.writeC(_activeChar.isRunning() ? 0x01 : 0x00); // changes the Speed display on Status Window

		body.writeC(321);

		body.writeD(_activeChar.getPledgeClass()); // changes the text above CP on Status Window

		body.writeC(_activeChar.getNobleStatus().getClientId());
		body.writeC(_activeChar.isHero() ? 0x01 : 0x00);

		body.writeD(_activeChar.getAppearance().getNameColor());
		body.writeD(_activeChar.getAppearance().getTitleColor());

		final AttributeType attackAttribute = _activeChar.getAttackElement();
		body.writeH(attackAttribute.getClientId());
		body.writeH(_activeChar.getAttackElementValue(attackAttribute));
		for (AttributeType type : AttributeType.ATTRIBUTE_TYPES) {
			body.writeH(_activeChar.getDefenseElementValue(type));
		}
		body.writeD(_activeChar.getFame());
		body.writeD(_activeChar.getVitalityPoints());
		body.writeD(0x00);
		body.writeD(0x00);
	}
}