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
package handlers.admincommandhandlers;

import org.l2junity.gameserver.handler.AdminCommandHandler;
import org.l2junity.gameserver.handler.IAdminCommandHandler;
import org.l2junity.gameserver.instancemanager.PetitionManager;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;

/**
 * This class handles commands for GMs to respond to petitions.
 *
 * @author Tempy
 */
public class AdminPetition implements IAdminCommandHandler {
	private static final String[] ADMIN_COMMANDS =
			{
					"admin_view_petitions",
					"admin_view_petition",
					"admin_accept_petition",
					"admin_reject_petition",
					"admin_reset_petitions",
					"admin_force_peti"
			};

	@Override
	public boolean useAdminCommand(String command, PlayerInstance activeChar) {
		int petitionId = -1;

		try {
			petitionId = Integer.parseInt(command.split(" ")[1]);
		} catch (Exception e) {
		}

		if (command.equals("admin_view_petitions")) {
			PetitionManager.getInstance().sendPendingPetitionList(activeChar);
		} else if (command.startsWith("admin_view_petition")) {
			PetitionManager.getInstance().viewPetition(activeChar, petitionId);
		} else if (command.startsWith("admin_accept_petition")) {
			if (PetitionManager.getInstance().isPlayerInConsultation(activeChar)) {
				activeChar.sendPacket(SystemMessageId.YOU_MAY_ONLY_SUBMIT_ONE_PETITION_ACTIVE_AT_A_TIME);
				return true;
			}

			if (PetitionManager.getInstance().isPetitionInProcess(petitionId)) {
				activeChar.sendPacket(SystemMessageId.YOUR_PETITION_IS_BEING_PROCESSED);
				return true;
			}

			if (!PetitionManager.getInstance().acceptPetition(activeChar, petitionId)) {
				activeChar.sendPacket(SystemMessageId.NOT_UNDER_PETITION_CONSULTATION);
			}
		} else if (command.startsWith("admin_reject_petition")) {
			if (!PetitionManager.getInstance().rejectPetition(activeChar, petitionId)) {
				activeChar.sendPacket(SystemMessageId.FAILED_TO_CANCEL_PETITION_PLEASE_TRY_AGAIN_LATER);
			}
			PetitionManager.getInstance().sendPendingPetitionList(activeChar);
		} else if (command.equals("admin_reset_petitions")) {
			if (PetitionManager.getInstance().isPetitionInProcess()) {
				activeChar.sendPacket(SystemMessageId.YOUR_PETITION_IS_BEING_PROCESSED);
				return false;
			}
			PetitionManager.getInstance().clearPendingPetitions();
			PetitionManager.getInstance().sendPendingPetitionList(activeChar);
		} else if (command.startsWith("admin_force_peti")) {
			try {
				WorldObject targetChar = activeChar.getTarget();
				if ((targetChar == null) || !(targetChar instanceof PlayerInstance)) {
					activeChar.sendPacket(SystemMessageId.THAT_IS_AN_INCORRECT_TARGET);
					return false;
				}
				PlayerInstance targetPlayer = (PlayerInstance) targetChar;

				String val = command.substring(15);

				petitionId = PetitionManager.getInstance().submitPetition(targetPlayer, val, 9);
				PetitionManager.getInstance().acceptPetition(activeChar, petitionId);
			} catch (StringIndexOutOfBoundsException e) {
				activeChar.sendMessage("Usage: //force_peti text");
				return false;
			}
		}
		return true;
	}

	@Override
	public String[] getAdminCommandList() {
		return ADMIN_COMMANDS;
	}

	public static void main(String[] args) {
		AdminCommandHandler.getInstance().registerHandler(new AdminPetition());
	}
}