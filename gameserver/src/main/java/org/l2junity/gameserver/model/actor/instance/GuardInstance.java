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

import org.l2junity.gameserver.ai.CtrlIntention;
import org.l2junity.gameserver.enums.InstanceType;
import org.l2junity.gameserver.model.World;
import org.l2junity.gameserver.model.WorldRegion;
import org.l2junity.gameserver.model.actor.Attackable;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.templates.NpcTemplate;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.EventType;
import org.l2junity.gameserver.model.events.impl.character.npc.OnNpcFirstTalk;
import org.l2junity.gameserver.network.client.send.ActionFailed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class manages all Guards in the world. It inherits all methods from L2Attackable and adds some more such as tracking PK and aggressive MonsterInstance.
 */
public class GuardInstance extends Attackable {
	private static Logger _log = LoggerFactory.getLogger(GuardInstance.class);

	/**
	 * Constructor of GuardInstance (use L2Character and NpcInstance constructor).<br>
	 * <B><U> Actions</U> :</B>
	 * <ul>
	 * <li>Call the L2Character constructor to set the _template of the GuardInstance (copy skills from template to object and link _calculators to NPC_STD_CALCULATOR)</li>
	 * <li>Set the name of the GuardInstance</li>
	 * <li>Create a RandomAnimation Task that will be launched after the calculated delay if the server allow it</li>
	 * </ul>
	 *
	 * @param template to apply to the NPC
	 */
	public GuardInstance(NpcTemplate template) {
		super(template);
		setInstanceType(InstanceType.L2GuardInstance);
	}

	@Override
	public boolean isAutoAttackable(Creature attacker) {
		if (attacker.isMonster()) {
			return true;
		}

		if (attacker.isPlayer()) {
			return attacker.getReputation() < 0;
		}

		return super.isAutoAttackable(attacker);
	}

	/**
	 * Set the home location of its GuardInstance.
	 */
	@Override
	public void onSpawn() {
		setRandomWalking(false);
		super.onSpawn();

		// check the region where this mob is, do not activate the AI if region is inactive.
		WorldRegion region = World.getInstance().getRegion(this);
		if ((region != null) && (!region.isActive())) {
			getAI().stopAITask();
		}
	}

	/**
	 * Return the pathfile of the selected HTML file in function of the GuardInstance Identifier and of the page number.<br>
	 * <B><U> Format of the pathfile </U> :</B>
	 * <ul>
	 * <li>if page number = 0 : <B>data/html/guard/12006.htm</B> (npcId-page number)</li>
	 * <li>if page number > 0 : <B>data/html/guard/12006-1.htm</B> (npcId-page number)</li>
	 * </ul>
	 *
	 * @param npcId The Identifier of the NpcInstance whose text must be display
	 * @param val   The number of the page to display
	 */
	@Override
	public String getHtmlPath(int npcId, int val) {
		String pom = "";
		if (val == 0) {
			pom = "" + npcId;
		} else {
			pom = npcId + "-" + val;
		}
		return "guard/" + pom + ".htm";
	}

	/**
	 * Manage actions when a player click on the GuardInstance.<br>
	 * <B><U> Actions on first click on the GuardInstance (Select it)</U> :</B>
	 * <ul>
	 * <li>Set the GuardInstance as target of the L2PcInstance player (if necessary)</li>
	 * <li>Send a Server->Client packet MyTargetSelected to the L2PcInstance player (display the select window)</li>
	 * <li>Set the L2PcInstance Intention to AI_INTENTION_IDLE</li>
	 * <li>Send a Server->Client packet ValidateLocation to correct the GuardInstance position and heading on the client</li>
	 * </ul>
	 * <B><U> Actions on second click on the GuardInstance (Attack it/Interact with it)</U> :</B>
	 * <ul>
	 * <li>If L2PcInstance is in the _aggroList of the GuardInstance, set the L2PcInstance Intention to AI_INTENTION_ATTACK</li>
	 * <li>If L2PcInstance is NOT in the _aggroList of the GuardInstance, set the L2PcInstance Intention to AI_INTENTION_INTERACT (after a distance verification) and show message</li>
	 * </ul>
	 * <B><U> Example of use </U> :</B>
	 * <ul>
	 * <li>Client packet : Action, AttackRequest</li>
	 * </ul>
	 *
	 * @param player The L2PcInstance that start an action on the GuardInstance
	 */
	@Override
	public void onAction(Player player, boolean interact) {
		if (!canTarget(player)) {
			return;
		}

		// Check if the L2PcInstance already target the GuardInstance
		if (getObjectId() != player.getTargetId()) {
			// Set the target of the L2PcInstance player
			player.setTarget(this);
		} else if (interact) {
			// Check if the L2PcInstance is in the _aggroList of the GuardInstance
			if (containsTarget(player)) {
				// Set the L2PcInstance Intention to AI_INTENTION_ATTACK
				player.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, this);
			} else {
				// Calculate the distance between the L2PcInstance and the NpcInstance
				if (!canInteract(player)) {
					// Set the L2PcInstance Intention to AI_INTENTION_INTERACT
					player.getAI().setIntention(CtrlIntention.AI_INTENTION_INTERACT, this);
				} else {
					player.setLastFolkNPC(this);

					// Open a chat window on client with the text of the GuardInstance
					if (hasListener(EventType.ON_NPC_QUEST_START)) {
						player.setLastQuestNpcObject(getObjectId());
					}

					if (hasListener(EventType.ON_NPC_FIRST_TALK)) {
						EventDispatcher.getInstance().notifyEventAsync(new OnNpcFirstTalk(this, player), this);
					} else {
						showChatWindow(player, 0);
					}
				}
			}
		}
		// Send a Server->Client ActionFailed to the L2PcInstance in order to avoid that the client wait another packet
		player.sendPacket(ActionFailed.STATIC_PACKET);
	}
}
