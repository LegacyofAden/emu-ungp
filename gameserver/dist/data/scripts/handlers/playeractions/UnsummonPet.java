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
package handlers.playeractions;

import org.l2junity.gameserver.handler.IPlayerActionHandler;
import org.l2junity.gameserver.handler.PlayerActionHandler;
import org.l2junity.gameserver.model.ActionDataHolder;
import org.l2junity.gameserver.model.actor.Summon;
import org.l2junity.gameserver.model.actor.instance.PetInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;

/**
 * Unsummon Pet player action handler.
 *
 * @author St3eT
 */
public final class UnsummonPet implements IPlayerActionHandler {
	@Override
	public void useAction(Player activeChar, ActionDataHolder data, boolean ctrlPressed, boolean shiftPressed) {
		final Summon pet = activeChar.getPet();

		if (pet == null) {
			activeChar.sendPacket(SystemMessageId.YOU_DO_NOT_HAVE_A_PET);
		} else if (((PetInstance) pet).isUncontrollable()) {
			activeChar.sendPacket(SystemMessageId.WHEN_YOUR_PET_S_HUNGER_GAUGE_IS_AT_0_YOU_CANNOT_USE_YOUR_PET);
		} else if (pet.isBetrayed()) {
			activeChar.sendPacket(SystemMessageId.WHEN_YOUR_PET_S_HUNGER_GAUGE_IS_AT_0_YOU_CANNOT_USE_YOUR_PET);
		} else if (pet.isDead()) {
			activeChar.sendPacket(SystemMessageId.DEAD_PETS_CANNOT_BE_RETURNED_TO_THEIR_SUMMONING_ITEM);
		} else if (pet.isAttackingNow() || pet.isInCombat() || pet.isMovementDisabled()) {
			activeChar.sendPacket(SystemMessageId.A_PET_CANNOT_BE_UNSUMMONED_DURING_BATTLE);
		} else if (pet.isHungry()) {
			activeChar.sendPacket(SystemMessageId.YOU_MAY_NOT_RESTORE_A_HUNGRY_PET);
		} else {
			pet.unSummon(activeChar);
		}
	}

	public static void main(String[] args) {
		PlayerActionHandler.getInstance().registerHandler(new UnsummonPet());
	}
}
