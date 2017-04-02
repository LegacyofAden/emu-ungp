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
import org.l2junity.gameserver.data.xml.impl.SkillData;
import org.l2junity.gameserver.handler.AdminCommandHandler;
import org.l2junity.gameserver.handler.IAdminCommandHandler;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.client.send.MagicSkillUse;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class AdminTest implements IAdminCommandHandler {
	private static final String[] ADMIN_COMMANDS =
			{
					"admin_stats",
					"admin_skill_test",
					"admin_known"
			};

	@Override
	public boolean useAdminCommand(String command, PlayerInstance activeChar) {
		if (command.equals("admin_stats")) {
			activeChar.sendMessage(ThreadPool.getInstance().getStats());
		} else if (command.startsWith("admin_skill_test")) {
			try {
				StringTokenizer st = new StringTokenizer(command);
				st.nextToken();
				int id = Integer.parseInt(st.nextToken());
				if (command.startsWith("admin_skill_test")) {
					adminTestSkill(activeChar, id, true);
				} else {
					adminTestSkill(activeChar, id, false);
				}
			} catch (NumberFormatException e) {
				activeChar.sendMessage("Command format is //skill_test <ID>");
			} catch (NoSuchElementException nsee) {
				activeChar.sendMessage("Command format is //skill_test <ID>");
			}
		}
		return true;
	}

	/**
	 * @param activeChar
	 * @param id
	 * @param msu
	 */
	private void adminTestSkill(PlayerInstance activeChar, int id, boolean msu) {
		Creature caster;
		WorldObject target = activeChar.getTarget();
		if (!(target instanceof Creature)) {
			caster = activeChar;
		} else {
			caster = (Creature) target;
		}

		Skill _skill = SkillData.getInstance().getSkill(id, 1);
		if (_skill != null) {
			caster.setTarget(activeChar);
			if (msu) {
				caster.broadcastPacket(new MagicSkillUse(caster, activeChar, id, 1, _skill.getHitTime(), _skill.getReuseDelay()));
			} else {
				caster.doCast(_skill);
			}
		}
	}

	@Override
	public String[] getAdminCommandList() {
		return ADMIN_COMMANDS;
	}

	public static void main(String[] args) {
		AdminCommandHandler.getInstance().registerHandler(new AdminTest());
	}
}