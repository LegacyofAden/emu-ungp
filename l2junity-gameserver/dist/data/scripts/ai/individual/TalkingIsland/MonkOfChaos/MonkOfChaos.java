/*
 * Copyright (C) 2004-2016 L2J Unity
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
package ai.individual.TalkingIsland.MonkOfChaos;

import java.util.List;

import org.l2junity.gameserver.data.xml.impl.SkillData;
import org.l2junity.gameserver.data.xml.impl.SkillTreesData;
import org.l2junity.gameserver.enums.SubclassType;
import org.l2junity.gameserver.model.SkillLearn;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.base.AcquireSkillType;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.variables.PlayerVariables;
import org.l2junity.gameserver.network.client.send.ExAcquirableSkillListByClass;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;

import ai.AbstractNpcAI;

/**
 * Monk of Chaos AI.
 * @author Sdw
 */
public final class MonkOfChaos extends AbstractNpcAI
{
	private static final int MONK_OF_CHAOS = 33880;
	private static final int MIN_LEVEL = 85;
	private static final long CANCEL_FEE = 100000000;
	private static final int CHAOS_POMANDER = 37374;
	private static final int CHAOS_POMANDER_DUALCLASS = 37375;
	private static final String[] REVELATION_VAR_NAMES =
	{
		PlayerVariables.REVELATION_SKILL_1_MAIN_CLASS,
		PlayerVariables.REVELATION_SKILL_2_MAIN_CLASS,
	};
	
	private static final String[] DUALCLASS_REVELATION_VAR_NAMES =
	{
		PlayerVariables.REVELATION_SKILL_1_DUAL_CLASS,
		PlayerVariables.REVELATION_SKILL_2_DUAL_CLASS
	};
	
	private MonkOfChaos()
	{
		addStartNpc(MONK_OF_CHAOS);
		addTalkId(MONK_OF_CHAOS);
		addFirstTalkId(MONK_OF_CHAOS);
	}
	
	@Override
	public String onAdvEvent(String event, Npc npc, PlayerInstance player)
	{
		String htmltext = null;
		
		switch (event)
		{
			case "33880-1.html":
			case "33880-2.html":
			{
				htmltext = event;
				break;
			}
			case "LearnRevelationSkills":
			{
				if ((player.getLevel() < MIN_LEVEL) || !player.isAwakenedClass())
				{
					htmltext = "no-learn.html";
					break;
				}
				
				if (player.isSubClassActive() && !player.isDualClassActive())
				{
					htmltext = "no-subclass.html";
					break;
				}
				
				if (player.isDualClassActive())
				{
					final List<SkillLearn> skills = SkillTreesData.getInstance().getAvailableRevelationSkills(player, SubclassType.DUALCLASS);
					
					if (skills.size() > 0)
					{
						player.sendPacket(new ExAcquirableSkillListByClass(skills, AcquireSkillType.REVELATION_DUALCLASS));
					}
					else
					{
						player.sendPacket(SystemMessageId.THERE_ARE_NO_OTHER_SKILLS_TO_LEARN);
					}
				}
				else
				{
					final List<SkillLearn> skills = SkillTreesData.getInstance().getAvailableRevelationSkills(player, SubclassType.BASECLASS);
					
					if (skills.size() > 0)
					{
						player.sendPacket(new ExAcquirableSkillListByClass(skills, AcquireSkillType.REVELATION));
					}
					else
					{
						player.sendPacket(SystemMessageId.THERE_ARE_NO_OTHER_SKILLS_TO_LEARN);
					}
				}
				break;
			}
			case "CancelRevelationSkills":
			{
				if (player.isSubClassActive() && !player.isDualClassActive())
				{
					htmltext = "no-subclass.html";
					break;
				}
				
				int count = 0;
				
				final String[] varNames = player.isDualClassActive() ? DUALCLASS_REVELATION_VAR_NAMES : REVELATION_VAR_NAMES;
				final int chaosPomander = player.isDualClassActive() ? CHAOS_POMANDER_DUALCLASS : CHAOS_POMANDER;
				
				for (String varName : varNames)
				{
					if (player.getVariables().getInt(varName, 0) > 0)
					{
						count++;
					}
				}
				
				if ((player.getLevel() < MIN_LEVEL) || (count == 0) || !player.isAwakenedClass())
				{
					htmltext = "no-cancel.html";
					break;
				}
				
				if (player.getAdena() < CANCEL_FEE)
				{
					htmltext = "no-adena.html";
					break;
				}
				
				for (String varName : varNames)
				{
					final int skillId = player.getVariables().getInt(varName, 0);
					final Skill sk = SkillData.getInstance().getSkill(skillId, 1);
					if (sk != null)
					{
						player.removeSkill(sk);
						player.getVariables().remove(varName);
					}
				}
				giveItems(player, chaosPomander, count);
				htmltext = "canceled.html";
				break;
			}
		}
		return htmltext;
	}
	
	public static void main(String[] args)
	{
		new MonkOfChaos();
	}
}