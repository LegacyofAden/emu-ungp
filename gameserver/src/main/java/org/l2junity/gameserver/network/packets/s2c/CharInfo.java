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
import org.l2junity.core.configs.AdminConfig;
import org.l2junity.gameserver.instancemanager.CursedWeaponsManager;
import org.l2junity.gameserver.model.VariationInstance;
import org.l2junity.gameserver.model.actor.instance.DecoyInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.ceremonyofchaos.CeremonyOfChaosEvent;
import org.l2junity.gameserver.model.ceremonyofchaos.CeremonyOfChaosMember;
import org.l2junity.gameserver.model.interfaces.ILocational;
import org.l2junity.gameserver.model.itemcontainer.Inventory;
import org.l2junity.gameserver.model.skills.AbnormalVisualEffect;
import org.l2junity.gameserver.model.zone.ZoneId;
import org.l2junity.gameserver.network.packets.GameServerPacketType;
import org.l2junity.gameserver.network.packets.GameServerPacket;

import java.util.Set;

public class CharInfo extends GameServerPacket {
	private final Player _activeChar;
	private int _objId;
	private int _x, _y, _z, _heading;
	private final int _mAtkSpd, _pAtkSpd;

	private final int _runSpd, _walkSpd;
	private final int _swimRunSpd;
	private final int _swimWalkSpd;
	private final int _flyRunSpd;
	private final int _flyWalkSpd;
	private final double _moveMultiplier;
	private final float _attackSpeedMultiplier;
	private int _enchantLevel = 0;
	private int _armorEnchant = 0;
	private int _vehicleId = 0;
	private final boolean _gmSeeInvis;

	private static final int[] PAPERDOLL_ORDER = new int[]
			{
					Inventory.PAPERDOLL_UNDER,
					Inventory.PAPERDOLL_HEAD,
					Inventory.PAPERDOLL_RHAND,
					Inventory.PAPERDOLL_LHAND,
					Inventory.PAPERDOLL_GLOVES,
					Inventory.PAPERDOLL_CHEST,
					Inventory.PAPERDOLL_LEGS,
					Inventory.PAPERDOLL_FEET,
					Inventory.PAPERDOLL_CLOAK,
					Inventory.PAPERDOLL_RHAND,
					Inventory.PAPERDOLL_HAIR,
					Inventory.PAPERDOLL_HAIR2
			};

	public CharInfo(Player cha, boolean gmSeeInvis) {
		_activeChar = cha;
		_objId = cha.getObjectId();
		if ((_activeChar.getVehicle() != null) && (_activeChar.getInVehiclePosition() != null)) {
			_x = (int) _activeChar.getInVehiclePosition().getX();
			_y = (int) _activeChar.getInVehiclePosition().getY();
			_z = (int) _activeChar.getInVehiclePosition().getZ();
			_vehicleId = _activeChar.getVehicle().getObjectId();
		} else {
			_x = (int) _activeChar.getX();
			_y = (int) _activeChar.getY();
			_z = (int) _activeChar.getZ();
		}
		_heading = _activeChar.getHeading();
		_mAtkSpd = _activeChar.getMAtkSpd();
		_pAtkSpd = _activeChar.getPAtkSpd();
		_attackSpeedMultiplier = (float) _activeChar.getAttackSpeedMultiplier();
		_moveMultiplier = cha.getMovementSpeedMultiplier();
		_runSpd = (int) Math.round(cha.getRunSpeed() / _moveMultiplier);
		_walkSpd = (int) Math.round(cha.getWalkSpeed() / _moveMultiplier);
		_swimRunSpd = (int) Math.round(cha.getSwimRunSpeed() / _moveMultiplier);
		_swimWalkSpd = (int) Math.round(cha.getSwimWalkSpeed() / _moveMultiplier);
		_flyRunSpd = cha.isFlying() ? _runSpd : 0;
		_flyWalkSpd = cha.isFlying() ? _walkSpd : 0;
		_enchantLevel = cha.getInventory().getWeaponEnchant();
		_armorEnchant = cha.getInventory().getArmorMinEnchant();
		_gmSeeInvis = gmSeeInvis;
	}

	public CharInfo(DecoyInstance decoy, boolean gmSeeInvis) {
		this(decoy.getActingPlayer(), gmSeeInvis); // init
		_objId = decoy.getObjectId();
		_x = (int) decoy.getX();
		_y = (int) decoy.getY();
		_z = (int) decoy.getZ();
		_heading = decoy.getHeading();
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.CHAR_INFO.writeId(body);
		final CeremonyOfChaosEvent event = _activeChar.getEvent(CeremonyOfChaosEvent.class);
		final CeremonyOfChaosMember cocPlayer = event != null ? event.getMember(_activeChar.getObjectId()) : null;
		body.writeD(_x); // Confirmed
		body.writeD(_y); // Confirmed
		body.writeD(_z); // Confirmed
		body.writeD(_vehicleId); // Confirmed
		body.writeD(_objId); // Confirmed
		body.writeS(_activeChar.getAppearance().getVisibleName()); // Confirmed

		body.writeH(_activeChar.getRace().ordinal()); // Confirmed
		body.writeC(_activeChar.getAppearance().getSex() ? 0x01 : 0x00); // Confirmed
		body.writeD(_activeChar.getBaseClass()); // Confirmed

		for (int slot : getPaperdollOrder()) {
			body.writeD(_activeChar.getInventory().getPaperdollItemDisplayId(slot)); // Confirmed
		}

		for (int slot : getPaperdollOrderAugument()) {
			final VariationInstance augment = _activeChar.getInventory().getPaperdollAugmentation(slot);
			body.writeD(augment != null ? augment.getOption1Id() : 0); // Confirmed
			body.writeD(augment != null ? augment.getOption2Id() : 0); // Confirmed
		}

		body.writeC(_armorEnchant);

		for (int slot : getPaperdollOrderVisualId()) {
			body.writeD(_activeChar.getInventory().getPaperdollItemVisualId(slot));
		}

		body.writeC(_activeChar.getPvpFlag());
		body.writeD(_activeChar.getReputation());

		body.writeD(_mAtkSpd);
		body.writeD(_pAtkSpd);

		body.writeH(_runSpd);
		body.writeH(_walkSpd);
		body.writeH(_swimRunSpd);
		body.writeH(_swimWalkSpd);
		body.writeH(_flyRunSpd);
		body.writeH(_flyWalkSpd);
		body.writeH(_flyRunSpd);
		body.writeH(_flyWalkSpd);
		body.writeF(_moveMultiplier);
		body.writeF(_attackSpeedMultiplier);

		body.writeF(_activeChar.getCollisionRadius());
		body.writeF(_activeChar.getCollisionHeight());

		body.writeD(_activeChar.getVisualHair());
		body.writeD(_activeChar.getVisualHairColor());
		body.writeD(_activeChar.getVisualFace());

		body.writeS(_gmSeeInvis ? "Invisible" : _activeChar.getAppearance().getVisibleTitle());

		body.writeD(_activeChar.getAppearance().getVisibleClanId());
		body.writeD(_activeChar.getAppearance().getVisibleClanCrestId());
		body.writeD(_activeChar.getAppearance().getVisibleAllyId());
		body.writeD(_activeChar.getAppearance().getVisibleAllyCrestId());

		body.writeC(_activeChar.isSitting() ? 0x00 : 0x01); // Confirmed
		body.writeC(_activeChar.isRunning() ? 0x01 : 0x00); // Confirmed
		body.writeC(_activeChar.isInCombat() ? 0x01 : 0x00); // Confirmed

		body.writeC(!_activeChar.isInOlympiadMode() && _activeChar.isAlikeDead() ? 0x01 : 0x00); // Confirmed

		body.writeC(_activeChar.isInvisible() ? 0x01 : 0x00);

		body.writeC(_activeChar.getMountType().ordinal()); // 1-on Strider, 2-on Wyvern, 3-on Great Wolf, 0-no mount
		body.writeC(_activeChar.getPrivateStoreType().getId()); // Confirmed

		body.writeH(_activeChar.getCubics().size()); // Confirmed
		_activeChar.getCubics().keySet().forEach(body::writeH);

		body.writeC(_activeChar.isInMatchingRoom() ? 0x01 : 0x00); // Confirmed

		body.writeC(_activeChar.isInsideZone(ZoneId.WATER) ? 1 : _activeChar.isFlyingMounted() ? 2 : 0);
		body.writeH(_activeChar.getRecomHave()); // Confirmed
		body.writeD(_activeChar.getMountNpcId() == 0 ? 0 : _activeChar.getMountNpcId() + 1000000);

		body.writeD(_activeChar.getClassId().getId()); // Confirmed
		body.writeD(0x00); // TODO: Find me!
		body.writeC(_activeChar.isMounted() ? 0 : _enchantLevel); // Confirmed

		body.writeC(_activeChar.getTeam().getId()); // Confirmed

		body.writeD(_activeChar.getClanCrestLargeId());
		body.writeC(_activeChar.getNobleStatus().getClientId()); // Confirmed
		body.writeC(_activeChar.isHero() || (_activeChar.isGM() && AdminConfig.GM_HERO_AURA) ? 1 : 0); // Confirmed

		body.writeC(_activeChar.isFishing() ? 1 : 0); // Confirmed

		final ILocational baitLocation = _activeChar.getFishing().getBaitLocation();
		body.writeD((int) baitLocation.getX()); // Confirmed
		body.writeD((int) baitLocation.getY()); // Confirmed
		body.writeD((int) baitLocation.getZ()); // Confirmed

		body.writeD(_activeChar.getAppearance().getNameColor()); // Confirmed

		body.writeD(_heading); // Confirmed

		body.writeC(_activeChar.getPledgeClass());
		body.writeH(_activeChar.getPledgeType());

		body.writeD(_activeChar.getAppearance().getTitleColor()); // Confirmed

		body.writeC(_activeChar.isCursedWeaponEquipped() ? CursedWeaponsManager.getInstance().getLevel(_activeChar.getCursedWeaponEquippedId()) : 0);

		body.writeD(_activeChar.getAppearance().getVisibleClanId() > 0 ? _activeChar.getClan().getReputationScore() : 0);
		body.writeD(_activeChar.getTransformationDisplayId()); // Confirmed
		body.writeD(_activeChar.getAgathionId()); // Confirmed

		body.writeC(0x00); // TODO: Find me!

		body.writeD((int) Math.round(_activeChar.getCurrentCp())); // Confirmed
		body.writeD(_activeChar.getMaxHp()); // Confirmed
		body.writeD((int) Math.round(_activeChar.getCurrentHp())); // Confirmed
		body.writeD(_activeChar.getMaxMp()); // Confirmed
		body.writeD((int) Math.round(_activeChar.getCurrentMp())); // Confirmed

		body.writeC(0x00); // TODO: Find me!
		final Set<AbnormalVisualEffect> abnormalVisualEffects = _activeChar.getEffectList().getCurrentAbnormalVisualEffects();
		body.writeD(abnormalVisualEffects.size() + (_gmSeeInvis ? 1 : 0)); // Confirmed
		for (AbnormalVisualEffect abnormalVisualEffect : abnormalVisualEffects) {
			body.writeH(abnormalVisualEffect.getClientId()); // Confirmed
		}
		if (_gmSeeInvis) {
			body.writeH(AbnormalVisualEffect.STEALTH.getClientId());
		}
		body.writeC(cocPlayer != null ? cocPlayer.getPosition() : 0);
		body.writeC(_activeChar.isHairAccessoryEnabled() ? 0x01 : 0x00); // Hair accessory
		body.writeC(_activeChar.getAbilityPointsUsed()); // Used Ability Points
	}

	@Override
	public int[] getPaperdollOrder() {
		return PAPERDOLL_ORDER;
	}
}
