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
package org.l2junity.gameserver.network.packets.c2s;

import org.l2junity.gameserver.enums.PrivateStoreType;
import org.l2junity.gameserver.model.actor.Summon;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.items.type.ActionType;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ExAutoSoulShot;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;


/**
 * @author Unknown, UnAfraid
 */
public final class RequestAutoSoulShot extends GameClientPacket {
	private int _itemId;
	private boolean _enable;
	private int _type;

	@Override
	public void readImpl() {
		_itemId = readD();
		_enable = readD() == 1;
		_type = readD();
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		if ((activeChar.getPrivateStoreType() == PrivateStoreType.NONE) && (activeChar.getActiveRequester() == null) && !activeChar.isDead()) {
			final ItemInstance item = activeChar.getInventory().getItemByItemId(_itemId);
			if (item == null) {
				return;
			}

			if (_enable) {
				if (!activeChar.getInventory().canManipulateWithItemId(item.getId())) {
					activeChar.sendMessage("Cannot use this item.");
					return;
				}

				switch (item.getItem().getDefaultAction()) {
					case SUMMON_SPIRITSHOT:
					case SUMMON_SOULSHOT: {
						if (activeChar.hasSummon()) {
							final boolean isSoulshot = item.getEtcItem().getDefaultAction() == ActionType.SUMMON_SOULSHOT;
							final boolean isSpiritshot = item.getEtcItem().getDefaultAction() == ActionType.SUMMON_SPIRITSHOT;
							if (isSoulshot) {
								int soulshotCount = 0;
								final Summon pet = activeChar.getPet();
								if (pet != null) {
									soulshotCount += pet.getSoulShotsPerHit();
								}
								for (Summon servitor : activeChar.getServitors().values()) {
									soulshotCount += servitor.getSoulShotsPerHit();
								}
								if (soulshotCount > item.getCount()) {
									getClient().sendPacket(SystemMessageId.YOU_DON_T_HAVE_ENOUGH_SOULSHOTS_NEEDED_FOR_A_PET_SERVITOR);
									return;
								}
							} else if (isSpiritshot) {
								int spiritshotCount = 0;
								final Summon pet = activeChar.getPet();
								if (pet != null) {
									spiritshotCount += pet.getSpiritShotsPerHit();
								}
								for (Summon servitor : activeChar.getServitors().values()) {
									spiritshotCount += servitor.getSpiritShotsPerHit();
								}
								if (spiritshotCount > item.getCount()) {
									getClient().sendPacket(SystemMessageId.YOU_DON_T_HAVE_ENOUGH_SOULSHOTS_NEEDED_FOR_A_PET_SERVITOR);
									return;
								}
							}

							// Activate shots
							activeChar.addAutoSoulShot(_itemId);
							getClient().sendPacket(new ExAutoSoulShot(_itemId, _enable, _type));

							// Send message
							final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.THE_AUTOMATIC_USE_OF_S1_HAS_BEEN_ACTIVATED);
							sm.addItemName(item);
							getClient().sendPacket(sm);

							// Recharge summon's shots
							final Summon pet = activeChar.getPet();
							if (pet != null) {
								pet.rechargeShots(isSoulshot, isSpiritshot, false);
							}
							activeChar.getServitors().values().forEach(s ->
							{
								s.rechargeShots(isSoulshot, isSpiritshot, false);
							});
						} else {
							getClient().sendPacket(SystemMessageId.YOU_DO_NOT_HAVE_A_SERVITOR_OR_PET_AND_THEREFORE_CANNOT_USE_THE_AUTOMATIC_USE_FUNCTION);
						}
						break;
					}
					case SPIRITSHOT:
					case SOULSHOT:
					case FISHINGSHOT: {
						final boolean isSoulshot = item.getEtcItem().getDefaultAction() == ActionType.SOULSHOT;
						final boolean isSpiritshot = item.getEtcItem().getDefaultAction() == ActionType.SPIRITSHOT;
						final boolean isFishingshot = item.getEtcItem().getDefaultAction() == ActionType.FISHINGSHOT;
						if ((activeChar.getActiveWeaponItem() == activeChar.getFistsWeaponItem()) || (item.getItem().getCrystalType() != activeChar.getActiveWeaponItem().getCrystalTypePlus())) {
							getClient().sendPacket(isSoulshot ? SystemMessageId.THE_SOULSHOT_YOU_ARE_ATTEMPTING_TO_USE_DOES_NOT_MATCH_THE_GRADE_OF_YOUR_EQUIPPED_WEAPON : SystemMessageId.YOUR_SPIRITSHOT_DOES_NOT_MATCH_THE_WEAPON_S_GRADE);
							return;
						}

						// Activate shots
						activeChar.addAutoSoulShot(_itemId);
						getClient().sendPacket(new ExAutoSoulShot(_itemId, _enable, _type));

						// Send message
						final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.THE_AUTOMATIC_USE_OF_S1_HAS_BEEN_ACTIVATED);
						sm.addItemName(item);
						getClient().sendPacket(sm);

						// Recharge player's shots
						activeChar.rechargeShots(isSoulshot, isSpiritshot, isFishingshot);
						break;
					}
				}
			} else {
				// Cancel auto shots
				activeChar.removeAutoSoulShot(_itemId);
				getClient().sendPacket(new ExAutoSoulShot(_itemId, _enable, _type));

				// Send message
				final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.THE_AUTOMATIC_USE_OF_S1_HAS_BEEN_DEACTIVATED);
				sm.addItemName(item);
				getClient().sendPacket(sm);
			}
		}
	}
}
