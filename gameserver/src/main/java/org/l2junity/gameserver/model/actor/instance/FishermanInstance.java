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

import org.l2junity.gameserver.data.xml.impl.SkillTreesData;
import org.l2junity.gameserver.enums.InstanceType;
import org.l2junity.gameserver.model.SkillLearn;
import org.l2junity.gameserver.model.actor.templates.NpcTemplate;
import org.l2junity.gameserver.model.base.AcquireSkillType;
import org.l2junity.gameserver.network.packets.s2c.ExAcquirableSkillListByClass;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;

import java.util.List;

public final class FishermanInstance extends MerchantInstance {
	public FishermanInstance(NpcTemplate template) {
		super(template);
		setInstanceType(InstanceType.L2FishermanInstance);
	}

	@Override
	public String getHtmlPath(int npcId, int val) {
		String pom = "";

		if (val == 0) {
			pom = "" + npcId;
		} else {
			pom = npcId + "-" + val;
		}

		return "fisherman/" + pom + ".htm";
	}

	@Override
	public void onBypassFeedback(Player player, String command) {
		if (command.equalsIgnoreCase("FishSkillList")) {
			showFishSkillList(player);
		} else {
			super.onBypassFeedback(player, command);
		}
	}

	public static void showFishSkillList(Player player) {
		final List<SkillLearn> skills = SkillTreesData.getInstance().getAvailableFishingSkills(player);

		if (skills.isEmpty()) {
			final int minlLevel = SkillTreesData.getInstance().getMinLevelForNewSkill(player, SkillTreesData.getInstance().getFishingSkillTree());
			if (minlLevel > 0) {
				SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_DO_NOT_HAVE_ANY_FURTHER_SKILLS_TO_LEARN_COME_BACK_WHEN_YOU_HAVE_REACHED_LEVEL_S1);
				sm.addInt(minlLevel);
				player.sendPacket(sm);
			} else {
				player.sendPacket(SystemMessageId.THERE_ARE_NO_OTHER_SKILLS_TO_LEARN);
			}
		} else {
			player.sendPacket(new ExAcquirableSkillListByClass(skills, AcquireSkillType.FISHING));
		}
	}
}
