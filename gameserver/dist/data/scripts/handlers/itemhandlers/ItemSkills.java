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
package handlers.itemhandlers;

import org.l2junity.gameserver.handler.ItemHandler;
import org.l2junity.gameserver.model.actor.Playable;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;

/**
 * Item skills not allowed on Olympiad.
 */
public class ItemSkills extends ItemSkillsTemplate {
	@Override
	public boolean useItem(Playable playable, ItemInstance item, boolean forceUse) {
		final Player activeChar = playable.getActingPlayer();
		if ((activeChar != null) && activeChar.isInOlympiadMode()) {
			activeChar.sendPacket(SystemMessageId.YOU_CANNOT_USE_THAT_ITEM_IN_A_OLYMPIAD_MATCH);
			return false;
		}
		return super.useItem(playable, item, forceUse);
	}

	public static void main(String[] args) {
		ItemHandler.getInstance().registerHandler(new ItemSkills());
	}
}