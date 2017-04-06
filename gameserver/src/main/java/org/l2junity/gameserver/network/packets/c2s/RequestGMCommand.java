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

import org.l2junity.gameserver.data.sql.impl.ClanTable;
import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.GMHennaInfo;
import org.l2junity.gameserver.network.packets.s2c.GMViewCharacterInfo;
import org.l2junity.gameserver.network.packets.s2c.GMViewItemList;
import org.l2junity.gameserver.network.packets.s2c.GMViewPledgeInfo;
import org.l2junity.gameserver.network.packets.s2c.GMViewSkillInfo;
import org.l2junity.gameserver.network.packets.s2c.GMViewWarehouseWithdrawList;
import org.l2junity.gameserver.network.packets.s2c.GmViewQuestInfo;
import org.l2junity.network.PacketReader;

public final class RequestGMCommand extends GameClientPacket {
	private String _targetName;
	private int _command;

	@Override
	public void readImpl() {
		_targetName = readS();
		_command = readD();
		// _unknown = readD();
	}

	@Override
	public void runImpl() {
		// prevent non gm or low level GMs from vieweing player stuff
		if (!getClient().getActiveChar().isGM() || !getClient().getActiveChar().getAccessLevel().allowAltG()) {
			return;
		}

		Player player = WorldManager.getInstance().getPlayer(_targetName);

		Clan clan = ClanTable.getInstance().getClanByName(_targetName);

		// player name was incorrect?
		if ((player == null) && ((clan == null) || (_command != 6))) {
			return;
		}

		switch (_command) {
			case 1: // player status
			{
				getClient().sendPacket(new GMViewCharacterInfo(player));
				getClient().sendPacket(new GMHennaInfo(player));
				break;
			}
			case 2: // player clan
			{
				if ((player != null) && (player.getClan() != null)) {
					getClient().sendPacket(new GMViewPledgeInfo(player.getClan(), player));
				}
				break;
			}
			case 3: // player skills
			{
				getClient().sendPacket(new GMViewSkillInfo(player));
				break;
			}
			case 4: // player quests
			{
				getClient().sendPacket(new GmViewQuestInfo(player));
				break;
			}
			case 5: // player inventory
			{
				getClient().sendPacket(new GMViewItemList(player));
				getClient().sendPacket(new GMHennaInfo(player));
				break;
			}
			case 6: // player warehouse
			{
				// gm warehouse view to be implemented
				if (player != null) {
					getClient().sendPacket(new GMViewWarehouseWithdrawList(player));
					// clan warehouse
				} else {
					getClient().sendPacket(new GMViewWarehouseWithdrawList(clan));
				}
				break;
			}
		}
	}
}