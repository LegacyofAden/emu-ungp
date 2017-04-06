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

import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.gameserver.MonsterRace;
import org.l2junity.gameserver.handler.AdminCommandHandler;
import org.l2junity.gameserver.handler.IAdminCommandHandler;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.s2c.DeleteObject;
import org.l2junity.gameserver.network.packets.s2c.MonRaceInfo;
import org.l2junity.gameserver.network.packets.s2c.PlaySound;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;

import java.util.concurrent.TimeUnit;

/**
 * This class handles following admin commands: - invul = turns invulnerability on/off
 *
 * @version $Revision: 1.1.6.4 $ $Date: 2007/07/31 10:06:00 $
 */
public class AdminMonsterRace implements IAdminCommandHandler {
	private static final String[] ADMIN_COMMANDS =
			{
					"admin_mons"
			};

	protected static int state = -1;

	@Override
	public boolean useAdminCommand(String command, Player activeChar) {
		if (command.equalsIgnoreCase("admin_mons")) {
			handleSendPacket(activeChar);
		}
		return true;
	}

	@Override
	public String[] getAdminCommandList() {
		return ADMIN_COMMANDS;
	}

	private void handleSendPacket(Player activeChar) {
		/*
		 * -1 0 to initialize the race 0 15322 to start race 13765 -1 in middle of race -1 0 to end the race 8003 to 8027
		 */

		int[][] codes =
				{
						{
								-1,
								0
						},
						{
								0,
								15322
						},
						{
								13765,
								-1
						},
						{
								-1,
								0
						}
				};
		MonsterRace race = MonsterRace.getInstance();

		if (state == -1) {
			state++;
			race.newRace();
			race.newSpeeds();
			MonRaceInfo spk = new MonRaceInfo(codes[state][0], codes[state][1], race.getMonsters(), race.getSpeeds());
			activeChar.sendPacket(spk);
			activeChar.broadcastPacket(spk);
		} else if (state == 0) {
			state++;
			SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.THEY_RE_OFF);
			sm.addInt(0);
			activeChar.sendPacket(sm);
			PlaySound SRace = new PlaySound(1, "S_Race", 0, 0, 0, 0, 0);
			activeChar.sendPacket(SRace);
			activeChar.broadcastPacket(SRace);
			PlaySound SRace2 = new PlaySound(0, "ItemSound2.race_start", 1, 121209259, 12125, 182487, -3559);
			activeChar.sendPacket(SRace2);
			activeChar.broadcastPacket(SRace2);
			MonRaceInfo spk = new MonRaceInfo(codes[state][0], codes[state][1], race.getMonsters(), race.getSpeeds());
			activeChar.sendPacket(spk);
			activeChar.broadcastPacket(spk);

			ThreadPool.getInstance().scheduleGeneral(new RunRace(codes, activeChar), 5000, TimeUnit.MILLISECONDS);
		}

	}

	class RunRace implements Runnable {

		private final int[][] codes;
		private final Player activeChar;

		public RunRace(int[][] pCodes, Player pActiveChar) {
			codes = pCodes;
			activeChar = pActiveChar;
		}

		@Override
		public void run() {
			// int[][] speeds1 = MonsterRace.getInstance().getSpeeds();
			// MonsterRace.getInstance().newSpeeds();
			// int[][] speeds2 = MonsterRace.getInstance().getSpeeds();
			/*
			 * int[] speed = new int[8]; for (int i=0; i<8; i++) { for (int j=0; j<20; j++) { //LOGGER.info("Adding "+speeds1[i][j] +" and "+ speeds2[i][j]); speed[i] += (speeds1[i][j]*1);// + (speeds2[i][j]*1); } LOGGER.info("Total speed for "+(i+1)+" = "+speed[i]); }
			 */

			MonRaceInfo spk = new MonRaceInfo(codes[2][0], codes[2][1], MonsterRace.getInstance().getMonsters(), MonsterRace.getInstance().getSpeeds());
			activeChar.sendPacket(spk);
			activeChar.broadcastPacket(spk);
			ThreadPool.getInstance().scheduleGeneral(new RunEnd(activeChar), 30000, TimeUnit.MILLISECONDS);
		}
	}

	private static class RunEnd implements Runnable {
		private final Player activeChar;

		public RunEnd(Player pActiveChar) {
			activeChar = pActiveChar;
		}

		@Override
		public void run() {
			DeleteObject obj = null;
			for (int i = 0; i < 8; i++) {
				obj = new DeleteObject(MonsterRace.getInstance().getMonsters()[i]);
				activeChar.sendPacket(obj);
				activeChar.broadcastPacket(obj);
			}
			state = -1;
		}
	}

	public static void main(String[] args) {
		AdminCommandHandler.getInstance().registerHandler(new AdminMonsterRace());
	}
}