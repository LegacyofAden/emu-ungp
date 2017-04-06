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

import lombok.extern.slf4j.Slf4j;
import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.gameserver.data.xml.impl.HennaData;
import org.l2junity.gameserver.model.PcCondOverride;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.items.Henna;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ActionFailed;
import org.l2junity.gameserver.network.packets.s2c.InventoryUpdate;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.gameserver.util.Util;


/**
 * @author Zoey76
 */
@Slf4j
public final class RequestHennaEquip extends GameClientPacket {
	private int _symbolId;

	@Override
	public void readImpl() {
		_symbolId = readD();
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		if (!getClient().getFloodProtectors().getTransaction().tryPerformAction("HennaEquip")) {
			return;
		}

		if (activeChar.getHennaEmptySlots() == 0) {
			activeChar.sendPacket(SystemMessageId.NO_SLOT_EXISTS_TO_DRAW_THE_SYMBOL);
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		final Henna henna = HennaData.getInstance().getHenna(_symbolId);
		if (henna == null) {
			log.warn("Invalid Henna Id: " + _symbolId + " from player " + activeChar);
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		final long _count = activeChar.getInventory().getInventoryItemCount(henna.getDyeItemId(), -1);
		if (henna.isAllowedClass(activeChar.getClassId()) && (_count >= henna.getWearCount()) && (activeChar.getAdena() >= henna.getWearFee()) && activeChar.addHenna(henna)) {
			activeChar.destroyItemByItemId("Henna", henna.getDyeItemId(), henna.getWearCount(), activeChar, true);
			activeChar.getInventory().reduceAdena("Henna", henna.getWearFee(), activeChar, activeChar.getLastFolkNPC());
			final InventoryUpdate iu = new InventoryUpdate();
			iu.addModifiedItem(activeChar.getInventory().getAdenaInstance());
			activeChar.sendInventoryUpdate(iu);
			activeChar.sendPacket(SystemMessageId.THE_SYMBOL_HAS_BEEN_ADDED);
		} else {
			activeChar.sendPacket(SystemMessageId.THE_SYMBOL_CANNOT_BE_DRAWN);
			if (!activeChar.canOverrideCond(PcCondOverride.ITEM_CONDITIONS) && !henna.isAllowedClass(activeChar.getClassId())) {
				Util.handleIllegalPlayerAction(activeChar, "Exploit attempt: Character " + activeChar.getName() + " of account " + activeChar.getAccountName() + " tryed to add a forbidden henna.", GeneralConfig.DEFAULT_PUNISH);
			}
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
		}
	}
}
