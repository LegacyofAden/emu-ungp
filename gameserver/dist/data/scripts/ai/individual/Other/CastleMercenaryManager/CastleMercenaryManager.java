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
package ai.individual.Other.CastleMercenaryManager;

import ai.AbstractNpcAI;
import org.l2junity.gameserver.model.ClanPrivilege;
import org.l2junity.gameserver.model.PcCondOverride;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.MerchantInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.entity.Castle;
import org.l2junity.gameserver.network.packets.s2c.NpcHtmlMessage;

import java.util.StringTokenizer;

/**
 * Castle Mercenary Manager AI.
 *
 * @author malyelfik
 */
public final class CastleMercenaryManager extends AbstractNpcAI {
	// NPCs
	private static final int[] NPCS =
			{
					35102, // Greenspan
					35144, // Sanford
					35186, // Arvid
					35228, // Morrison
					35276, // Eldon
					35318, // Solinus
					35365, // Rowell
					35511, // Gompus
					35557, // Kendrew
			};

	private CastleMercenaryManager() {
		addStartNpc(NPCS);
		addTalkId(NPCS);
		addFirstTalkId(NPCS);
	}

	@Override
	public String onAdvEvent(String event, Npc npc, Player player) {
		String htmltext = null;
		final StringTokenizer st = new StringTokenizer(event, " ");
		switch (st.nextToken()) {
			case "limit": {
				final Castle castle = npc.getCastle();
				final NpcHtmlMessage html = new NpcHtmlMessage(npc.getObjectId());
				if (castle.getName().equalsIgnoreCase("Aden")) {
					html.setHtml(getHtm(player.getLang(), "mercmanager-aden-limit.html"));
				} else if (castle.getName().equalsIgnoreCase("Rune")) {
					html.setHtml(getHtm(player.getLang(), "mercmanager-rune-limit.html"));
				} else {
					html.setHtml(getHtm(player.getLang(), "mercmanager-limit.html"));
				}
				html.replace("%feud_name%", String.valueOf(1001000 + castle.getResidenceId()));
				player.sendPacket(html);
				break;
			}
			case "buy": {
				final int listId = Integer.parseInt(npc.getId() + st.nextToken());
				((MerchantInstance) npc).showBuyWindow(player, listId, false);
				break;
			}
			case "main": {
				htmltext = onFirstTalk(npc, player);
				break;
			}
			case "mercmanager-01.html": {
				htmltext = event;
				break;
			}
		}
		return htmltext;
	}

	@Override
	public String onFirstTalk(Npc npc, Player player) {
		final String htmltext;
		if (player.canOverrideCond(PcCondOverride.CASTLE_CONDITIONS) || ((player.getClanId() == npc.getCastle().getOwnerId()) && player.hasClanPrivilege(ClanPrivilege.CS_MERCENARIES))) {
			htmltext = npc.getCastle().getSiege().isInProgress() ? "mercmanager-siege.html" : "mercmanager.html";
		} else {
			htmltext = "mercmanager-no.html";
		}
		return htmltext;
	}

	public static void main(String[] args) {
		new CastleMercenaryManager();
	}
}
