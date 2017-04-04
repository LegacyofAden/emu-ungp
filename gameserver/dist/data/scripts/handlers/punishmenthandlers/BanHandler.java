/*
 * Copyright (C) 2004-2015 L2J DataPack
 * 
 * This file is part of L2J DataPack.
 * 
 * L2J DataPack is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J DataPack is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package handlers.punishmenthandlers;

import org.l2junity.gameserver.handler.IPunishmentHandler;
import org.l2junity.gameserver.handler.PunishmentHandler;
import org.l2junity.gameserver.model.World;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.punishment.PunishmentTask;
import org.l2junity.gameserver.model.punishment.PunishmentType;
import org.l2junity.gameserver.network.client.Disconnection;
import org.l2junity.gameserver.network.client.L2GameClient;
import org.l2junity.gameserver.service.GameServerRMI;

/**
 * This class handles ban punishment.
 *
 * @author UnAfraid
 */
public class BanHandler implements IPunishmentHandler {
	@Override
	public void onStart(PunishmentTask task) {
		switch (task.getAffect()) {
			case CHARACTER: {
				final int objectId = Integer.parseInt(String.valueOf(task.getKey()));
				final Player player = World.getInstance().getPlayer(objectId);
				if (player != null) {
					applyToPlayer(player);
				}
				break;
			}
			case ACCOUNT: {
				final String account = String.valueOf(task.getKey());
				final L2GameClient client = GameServerRMI.getInstance().getClient(account);
				if (client != null) {
					final Player player = client.getActiveChar();
					if (player != null) {
						applyToPlayer(player);
					} else {
						Disconnection.of(client).defaultSequence(false);
					}
				}
				break;
			}
			case IP: {
				final String ip = String.valueOf(task.getKey());
				for (Player player : World.getInstance().getPlayers()) {
					if (ip.equalsIgnoreCase(player.getIPAddress())) {
						applyToPlayer(player);
					}
				}
				break;
			}
			case HWID: {
				final String hwid = String.valueOf(task.getKey());
				for (Player player : World.getInstance().getPlayers()) {
					if (hwid.equalsIgnoreCase(player.getHWID())) {
						applyToPlayer(player);
					}
				}
				break;
			}
		}
	}

	@Override
	public void onEnd(PunishmentTask task) {

	}

	/**
	 * Applies all punishment effects from the player.
	 *
	 * @param player
	 */
	private static void applyToPlayer(Player player) {
		Disconnection.of(player).defaultSequence(false);
	}

	@Override
	public PunishmentType getType() {
		return PunishmentType.BAN;
	}

	public static void main(String[] args) {
		PunishmentHandler.getInstance().registerHandler(new BanHandler());
	}
}