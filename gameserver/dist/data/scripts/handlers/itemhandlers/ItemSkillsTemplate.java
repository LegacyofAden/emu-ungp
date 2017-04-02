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

import org.l2junity.gameserver.ai.CtrlIntention;
import org.l2junity.gameserver.enums.ItemSkillType;
import org.l2junity.gameserver.handler.IItemHandler;
import org.l2junity.gameserver.handler.ItemHandler;
import org.l2junity.gameserver.model.actor.Playable;
import org.l2junity.gameserver.model.holders.ItemSkillHolder;
import org.l2junity.gameserver.model.holders.SkillHolder;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.items.type.EtcItemType;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.skills.SkillCaster;
import org.l2junity.gameserver.network.client.send.SystemMessage;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;

import java.util.List;

/**
 * Template for item skills handler.
 *
 * @author Zoey76
 */
public class ItemSkillsTemplate implements IItemHandler {
	@Override
	public boolean useItem(Playable playable, ItemInstance item, boolean forceUse) {
		if (!playable.isPlayer() && !playable.isPet()) {
			return false;
		}

		// Pets can use items only when they are tradable.
		if (playable.isPet() && !item.isTradeable()) {
			playable.sendPacket(SystemMessageId.YOUR_PET_CANNOT_CARRY_THIS_ITEM);
			return false;
		}

		// Verify that item is not under reuse.
		if (!checkReuse(playable, null, item)) {
			return false;
		}

		final List<ItemSkillHolder> skills = item.getItem().getSkills(ItemSkillType.NORMAL);
		if (skills == null) {
			_log.info("Item " + item + " does not have registered any skill for handler.");
			return false;
		}

		boolean hasConsumeSkill = false;
		boolean successfulUse = false;

		for (SkillHolder skillInfo : skills) {
			if (skillInfo == null) {
				continue;
			}

			Skill itemSkill = skillInfo.getSkill();

			if (itemSkill != null) {
				if (itemSkill.getItemConsumeId() > 0) {
					hasConsumeSkill = true;
				}

				if (!itemSkill.checkCondition(playable, playable.getTarget())) {
					continue;
				}

				if (playable.isSkillDisabled(itemSkill)) {
					continue;
				}

				// Verify that skill is not under reuse.
				if (!checkReuse(playable, itemSkill, item)) {
					continue;
				}

				if (!item.isPotion() && !item.isElixir() && !item.isScroll() && playable.isCastingNow()) {
					continue;
				}

				// Send message to the master.
				if (playable.isPet()) {
					SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOUR_PET_USES_S1);
					sm.addSkillName(itemSkill);
					playable.sendPacket(sm);
				}

				if (itemSkill.isWithoutAction() || item.getItem().hasImmediateEffect() || item.getItem().hasExImmediateEffect()) {
					SkillCaster.triggerCast(playable, null, itemSkill, item, false);
					successfulUse = true;
				} else {
					playable.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
					if (playable.useMagic(itemSkill, playable.getTarget(), item, forceUse, false)) {
						successfulUse = true;
					}
				}

				if (itemSkill.getReuseDelay() > 0) {
					playable.addTimeStamp(itemSkill, itemSkill.getReuseDelay());
				}
			}
		}

		// Consume the item if its skills don't have item consume.
		if (!hasConsumeSkill && item.getItem().hasImmediateEffect()) {
			switch (item.getItem().getDefaultAction()) {
				case SKILL_REDUCE: {
					if (item.getItem().getItemType() == EtcItemType.ENCHT_ATTR) {
						break;
					}
				}
				case SKILL_REDUCE_ON_SKILL_SUCCESS:
				case CAPSULE: {
					// Capsules are consumed regardless of skill consuming or not.
					if (!playable.destroyItem("Consume", item.getObjectId(), 1, playable, false)) {
						playable.sendPacket(SystemMessageId.INCORRECT_ITEM_COUNT2);
						return false;
					}
				}
			}
		}

		return successfulUse;
	}

	/**
	 * @param playable the character using the item or skill
	 * @param skill    the skill being used, can be null
	 * @param item     the item being used
	 * @return {@code true} if the the item or skill to check is available, {@code false} otherwise
	 */
	private boolean checkReuse(Playable playable, Skill skill, ItemInstance item) {
		final long remainingTime = (skill != null) ? playable.getSkillRemainingReuseTime(skill.getReuseHashCode()) : playable.getItemRemainingReuseTime(item.getObjectId());
		final boolean isAvailable = remainingTime <= 0;
		if (playable.isPlayer()) {
			if (!isAvailable) {
				final int hours = (int) (remainingTime / 3600000L);
				final int minutes = (int) (remainingTime % 3600000L) / 60000;
				final int seconds = (int) ((remainingTime / 1000) % 60);
				SystemMessage sm = null;
				if (hours > 0) {
					sm = SystemMessage.getSystemMessage(SystemMessageId.THERE_ARE_S2_HOUR_S_S3_MINUTE_S_AND_S4_SECOND_S_REMAINING_IN_S1_S_RE_USE_TIME);
					if ((skill == null) || skill.isStatic()) {
						sm.addItemName(item);
					} else {
						sm.addSkillName(skill);
					}
					sm.addInt(hours);
					sm.addInt(minutes);
				} else if (minutes > 0) {
					sm = SystemMessage.getSystemMessage(SystemMessageId.THERE_ARE_S2_MINUTE_S_S3_SECOND_S_REMAINING_IN_S1_S_RE_USE_TIME);
					if ((skill == null) || skill.isStatic()) {
						sm.addItemName(item);
					} else {
						sm.addSkillName(skill);
					}
					sm.addInt(minutes);
				} else {
					sm = SystemMessage.getSystemMessage(SystemMessageId.THERE_ARE_S2_SECOND_S_REMAINING_IN_S1_S_RE_USE_TIME);
					if ((skill == null) || skill.isStatic()) {
						sm.addItemName(item);
					} else {
						sm.addSkillName(skill);
					}
				}
				sm.addInt(seconds);
				playable.sendPacket(sm);
			}
		}
		return isAvailable;
	}

	public static void main(String[] args) {
		ItemHandler.getInstance().registerHandler(new ItemSkillsTemplate());
	}
}