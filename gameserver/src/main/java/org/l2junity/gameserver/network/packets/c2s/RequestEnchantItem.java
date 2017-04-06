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

import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.gameserver.enums.ItemSkillType;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.actor.request.EnchantItemRequest;
import org.l2junity.gameserver.model.holders.ItemSkillHolder;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.skills.SkillConditionScope;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.EnchantResult;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.gameserver.util.Util;
import org.l2junity.network.PacketReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public final class RequestEnchantItem extends GameClientPacket {
	private int _objectId;
	private int _supportId;

	@Override
	public void readImpl() {
		_objectId = readD();
		_supportId = readD();
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

		request.setEnchantingItem(_objectId);
		request.setProcessing(true);

		if (!activeChar.isOnline() || getClient().isDetached()) {
			activeChar.removeRequest(request.getClass());
			return;
		}

		if (activeChar.isProcessingTransaction() || activeChar.isInStoreMode()) {
			getClient().sendPacket(SystemMessageId.YOU_CANNOT_ENCHANT_WHILE_OPERATING_A_PRIVATE_STORE_OR_PRIVATE_WORKSHOP);
			activeChar.removeRequest(request.getClass());
			return;
		}

		final ItemInstance item = request.getEnchantingItem();
		final ItemInstance scroll = request.getEnchantingScroll();
		final ItemInstance support = request.getSupportItem();
		if ((item == null) || (scroll == null)) {
			activeChar.removeRequest(request.getClass());
			return;
		}

		final List<ItemSkillHolder> scrollSkills = scroll.getItem().getSkills(ItemSkillType.NORMAL);

		if (!scrollSkills.stream().allMatch(skill -> skill.getSkill().checkConditions(SkillConditionScope.GENERAL, activeChar, item))) {
			activeChar.sendPacket(SystemMessageId.INAPPROPRIATE_ENCHANT_CONDITIONS);
			activeChar.removeRequest(request.getClass());
			getClient().sendPacket(new EnchantResult(EnchantResult.ERROR, 0, 0));
			return;
		}

		// fast auto-enchant cheat check
		if ((request.getTimestamp() == 0) || ((System.currentTimeMillis() - request.getTimestamp()) < 2000)) {
			Util.handleIllegalPlayerAction(activeChar, "Player " + activeChar.getName() + " use autoenchant program ", GeneralConfig.DEFAULT_PUNISH);
			activeChar.removeRequest(request.getClass());
			getClient().sendPacket(new EnchantResult(EnchantResult.ERROR, 0, 0));
			return;
		}

		// template for support item, if exist
		if (support != null) {
			if (support.getObjectId() != _supportId) {
				activeChar.removeRequest(request.getClass());
				return;
			}

			final List<ItemSkillHolder> supportSkills = support.getItem().getSkills(ItemSkillType.NORMAL);

			if (!supportSkills.stream().allMatch(skill -> skill.getSkill().checkConditions(SkillConditionScope.GENERAL, activeChar, item))) {
				activeChar.sendPacket(SystemMessageId.INAPPROPRIATE_ENCHANT_CONDITIONS);
				activeChar.removeRequest(request.getClass());
				getClient().sendPacket(new EnchantResult(EnchantResult.ERROR, 0, 0));
				return;
			}

			supportSkills.forEach(skill -> skill.getSkill().applyEffects(activeChar, activeChar, support));
		}

		scrollSkills.forEach(skill -> skill.getSkill().applyEffects(activeChar, activeChar, scroll));
	}
}
