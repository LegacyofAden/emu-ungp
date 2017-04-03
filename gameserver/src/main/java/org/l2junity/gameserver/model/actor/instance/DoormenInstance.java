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
package org.l2junity.gameserver.model.actor.instance;

import org.l2junity.gameserver.data.xml.impl.DoorData;
import org.l2junity.gameserver.data.xml.impl.TeleportersData;
import org.l2junity.gameserver.enums.InstanceType;
import org.l2junity.gameserver.enums.TeleportType;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.templates.NpcTemplate;
import org.l2junity.gameserver.model.teleporter.TeleportHolder;
import org.l2junity.gameserver.network.client.send.ActionFailed;
import org.l2junity.gameserver.network.client.send.NpcHtmlMessage;

import java.util.StringTokenizer;

/**
 * This class ...
 *
 * @version $Revision$ $Date$
 */
public class DoormenInstance extends NpcInstance {
	public DoormenInstance(NpcTemplate template) {
		super(template);
		setInstanceType(InstanceType.L2DoormenInstance);
	}

	@Override
	public boolean isAutoAttackable(Creature attacker) {
		if (attacker.isMonster()) {
			return true;
		}

		return super.isAutoAttackable(attacker);
	}

	@Override
	public void onBypassFeedback(Player player, String command) {
		if (command.startsWith("Chat")) {
			showChatWindow(player);
			return;
		} else if (command.startsWith("open_doors")) {
			if (isOwnerClan(player)) {
				if (isUnderSiege()) {
					cannotManageDoors(player);
				} else {
					openDoors(player, command);
				}
			}
			return;
		} else if (command.startsWith("close_doors")) {
			if (isOwnerClan(player)) {
				if (isUnderSiege()) {
					cannotManageDoors(player);
				} else {
					closeDoors(player, command);
				}
			}
			return;
		} else if (command.startsWith("tele")) {
			if (isOwnerClan(player)) {
				final TeleportHolder holder = TeleportersData.getInstance().getHolder(getId(), TeleportType.OTHER.name());
				if (holder != null) {
					final int locId = Integer.parseInt(command.substring(5).trim());
					holder.doTeleport(player, this, locId);
				}
			}
			return;
		}
		super.onBypassFeedback(player, command);
	}

	@Override
	public void showChatWindow(Player player) {
		player.sendPacket(ActionFailed.STATIC_PACKET);

		final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());

		if (!isOwnerClan(player)) {
			html.setFile(player.getLang(), "doormen/" + getTemplate().getId() + "-no.htm");
		} else {
			html.setFile(player.getLang(), "doormen/" + getTemplate().getId() + ".htm");
		}

		html.replace("%objectId%", String.valueOf(getObjectId()));
		player.sendPacket(html);
	}

	protected void openDoors(Player player, String command) {
		StringTokenizer st = new StringTokenizer(command.substring(10), ", ");
		st.nextToken();

		while (st.hasMoreTokens()) {
			DoorData.getInstance().getDoor(Integer.parseInt(st.nextToken())).openMe();
		}
	}

	protected void closeDoors(Player player, String command) {
		StringTokenizer st = new StringTokenizer(command.substring(11), ", ");
		st.nextToken();

		while (st.hasMoreTokens()) {
			DoorData.getInstance().getDoor(Integer.parseInt(st.nextToken())).closeMe();
		}
	}

	protected void cannotManageDoors(Player player) {
		player.sendPacket(ActionFailed.STATIC_PACKET);

		final NpcHtmlMessage html = new NpcHtmlMessage(getObjectId());
		html.setFile(player.getLang(), "doormen/" + getTemplate().getId() + "-busy.htm");
		player.sendPacket(html);
	}

	protected boolean isOwnerClan(Player player) {
		return true;
	}

	protected boolean isUnderSiege() {
		return false;
	}
}
