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
package org.l2junity.gameserver.model.actor;

import org.l2junity.gameserver.ai.CtrlIntention;
import org.l2junity.gameserver.geodata.GeoData;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.actor.templates.NpcTemplate;
import org.l2junity.gameserver.network.packets.s2c.ActionFailed;

/**
 * This class is a super-class for ControlTowerInstance and FlameTowerInstance.
 *
 * @author Zoey76
 */
public abstract class Tower extends Npc {
	public Tower(NpcTemplate template) {
		super(template);
		setIsInvul(false);
	}

	@Override
	public boolean canBeAttacked() {
		// Attackable during siege by attacker only
		return ((getCastle() != null) && (getCastle().getResidenceId() > 0) && getCastle().getSiege().isInProgress());
	}

	@Override
	public boolean isAutoAttackable(Creature attacker) {
		// Attackable during siege by attacker only
		return ((attacker != null) && attacker.isPlayer() && (getCastle() != null) && (getCastle().getResidenceId() > 0) && getCastle().getSiege().isInProgress() && getCastle().getSiege().checkIsAttacker(attacker.getClan()));
	}

	@Override
	public void onAction(Player player, boolean interact) {
		if (!canTarget(player)) {
			return;
		}

		if (this != player.getTarget()) {
			// Set the target of the L2PcInstance player
			player.setTarget(this);
		} else if (interact) {
			if (isAutoAttackable(player) && (Math.abs(player.getZ() - getZ()) < 100) && GeoData.getInstance().canSeeTarget(player, this)) {
				// Notify the L2PcInstance AI with AI_INTENTION_INTERACT
				player.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, this);
			}
		}
		// Send a Server->Client ActionFailed to the L2PcInstance in order to avoid that the client wait another packet
		player.sendPacket(ActionFailed.STATIC_PACKET);
	}

	@Override
	public void onForcedAttack(Player player) {
		onAction(player);
	}
}
