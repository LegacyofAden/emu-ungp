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

import org.l2junity.gameserver.enums.ItemSkillType;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.actor.request.EnchantItemRequest;
import org.l2junity.gameserver.model.holders.ItemSkillHolder;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.skills.SkillConditionScope;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ExPutEnchantScrollItemResult;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;

import java.util.List;

/**
 * @author Sdw
 */
public class RequestExAddEnchantScrollItem extends GameClientPacket {
	private int _scrollObjectId;
	private int _enchantObjectId;

	@Override
	public void readImpl() {
		_scrollObjectId = readD();
		_enchantObjectId = readD();
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		final EnchantItemRequest request = activeChar.getRequest(EnchantItemRequest.class);
		if ((request == null) || request.isProcessing()) {
			return;
		}

		request.setEnchantingItem(_enchantObjectId);
		request.setEnchantingScroll(_scrollObjectId);

		final ItemInstance item = request.getEnchantingItem();
		final ItemInstance scroll = request.getEnchantingScroll();
		if ((item == null) || (scroll == null)) {
			// message may be custom
			activeChar.sendPacket(SystemMessageId.INAPPROPRIATE_ENCHANT_CONDITIONS);
			activeChar.sendPacket(new ExPutEnchantScrollItemResult(0));
			request.setEnchantingItem(Player.ID_NONE);
			request.setEnchantingScroll(Player.ID_NONE);
			return;
		}

		final List<ItemSkillHolder> skills = item.getItem().getSkills(ItemSkillType.NORMAL);

		if (!skills.stream().allMatch(skill -> skill.getSkill().checkConditions(SkillConditionScope.GENERAL, activeChar, item))) {
			activeChar.sendPacket(SystemMessageId.INAPPROPRIATE_ENCHANT_CONDITIONS);
			activeChar.sendPacket(new ExPutEnchantScrollItemResult(0));
			request.setEnchantingScroll(Player.ID_NONE);
			return;
		}

		request.setTimestamp(System.currentTimeMillis());
		activeChar.sendPacket(new ExPutEnchantScrollItemResult(_scrollObjectId));
	}
}
