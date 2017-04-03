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
import org.l2junity.gameserver.instancemanager.FortSiegeManager;
import org.l2junity.gameserver.instancemanager.SiegeManager;
import org.l2junity.gameserver.model.L2Clan;
import org.l2junity.gameserver.model.SiegeClan;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.templates.L2NpcTemplate;
import org.l2junity.gameserver.model.entity.Siegable;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.client.send.ActionFailed;
import org.l2junity.gameserver.network.client.send.SystemMessage;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;

public class L2SiegeFlagInstance extends Npc {
	private final L2Clan _clan;
	private Siegable _siege;
	private long _nextTalkTime;

	public L2SiegeFlagInstance(Player player, L2NpcTemplate template, boolean advanced) {
		super(template);
		setInstanceType(InstanceType.L2SiegeFlagInstance);

		_clan = player.getClan();
		_siege = SiegeManager.getInstance().getSiege(player.getX(), player.getY(), player.getZ());
		if (_siege == null) {
			_siege = FortSiegeManager.getInstance().getSiege(player.getX(), player.getY(), player.getZ());
		}
		if ((_clan == null) || (_siege == null)) {
			throw new NullPointerException(getClass().getSimpleName() + ": Initialization failed.");
		}

		SiegeClan sc = _siege.getAttackerClan(_clan);
		if (sc == null) {
			throw new NullPointerException(getClass().getSimpleName() + ": Cannot find siege clan.");
		}

		sc.addFlag(this);
		getStatus();
		setIsInvul(false);
	}

	@Override
	public boolean canBeAttacked() {
		return !isInvul();
	}

	@Override
	public boolean isAutoAttackable(Creature attacker) {
		return !isInvul();
	}

	@Override
	public boolean doDie(Creature killer) {
		if (!super.doDie(killer)) {
			return false;
		}
		if ((_siege != null) && (_clan != null)) {
			SiegeClan sc = _siege.getAttackerClan(_clan);
			if (sc != null) {
				sc.removeFlag(this);
			}
		}
		return true;
	}

	@Override
	public void onForcedAttack(Player player) {
		onAction(player);
	}

	@Override
	public void onAction(Player player, boolean interact) {
		if ((player == null) || !canTarget(player)) {
			return;
		}

		// Check if the L2PcInstance already target the L2NpcInstance
		if (this != player.getTarget()) {
			// Set the target of the L2PcInstance player
			player.setTarget(this);
		} else if (interact) {
			if (isAutoAttackable(player) && (Math.abs(player.getZ() - getZ()) < 100)) {
				player.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, this);
			} else {
				// Send a Server->Client ActionFailed to the L2PcInstance in order to avoid that the client wait another packet
				player.sendPacket(ActionFailed.STATIC_PACKET);
			}
		}
	}

	@Override
	public void reduceCurrentHp(double value, Creature attacker, Skill skill, boolean isDOT, boolean directlyToHp, boolean critical, boolean reflect) {
		super.reduceCurrentHp(value, attacker, skill, isDOT, directlyToHp, critical, reflect);
		if (System.currentTimeMillis() > _nextTalkTime) {
			if (((getCastle() != null) && getCastle().getSiege().isInProgress()) || ((getFort() != null) && getFort().getSiege().isInProgress())) {
				if (_clan != null) {
					// send warning to owners of headquarters that theirs base is under attack
					_clan.broadcastToOnlineMembers(SystemMessage.getSystemMessage(SystemMessageId.YOUR_BASE_IS_BEING_ATTACKED));
					_nextTalkTime = System.currentTimeMillis() + 20000;
				}
			}
		}
	}
}
