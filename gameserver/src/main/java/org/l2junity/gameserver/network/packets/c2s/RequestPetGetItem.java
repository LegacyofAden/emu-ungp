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

import org.l2junity.gameserver.ai.CtrlIntention;
import org.l2junity.gameserver.instancemanager.CastleManager;
import org.l2junity.gameserver.instancemanager.FortSiegeManager;
import org.l2junity.gameserver.instancemanager.SiegeGuardManager;
import org.l2junity.gameserver.model.actor.instance.PetInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.entity.Castle;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ActionFailed;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.network.PacketReader;

public final class RequestPetGetItem extends GameClientPacket {
	private int _objectId;

	@Override
	public void readImpl() {
		_objectId = readD();
	}

	@Override
	public void runImpl() {
		final Player player = getClient().getActiveChar();
		if(player == null) {
			return;
		}
		
		ItemInstance item = (ItemInstance) player.getWorld().findObject(_objectId);
		if ((item == null) || !player.hasPet()) {
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		final Castle castle = CastleManager.getInstance().getCastle(item);
		if ((castle != null) && (SiegeGuardManager.getInstance().getSiegeGuardByItem(castle.getResidenceId(), item.getId()) != null)) {
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if (FortSiegeManager.getInstance().isCombat(item.getId())) {
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		final PetInstance pet = player.getPet();
		if (pet.isDead() || pet.isControlBlocked()) {
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if (pet.isUncontrollable()) {
			getClient().sendPacket(SystemMessageId.WHEN_YOUR_PET_S_HUNGER_GAUGE_IS_AT_0_YOU_CANNOT_USE_YOUR_PET);
			return;
		}

		pet.getAI().setIntention(CtrlIntention.AI_INTENTION_PICK_UP, item);
	}
}