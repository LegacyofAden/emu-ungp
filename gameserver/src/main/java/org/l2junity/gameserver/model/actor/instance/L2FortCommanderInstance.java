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

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.l2junity.commons.util.concurrent.ThreadPool;
import org.l2junity.gameserver.ai.CtrlIntention;
import org.l2junity.gameserver.config.GeneralConfig;
import org.l2junity.gameserver.enums.ChatType;
import org.l2junity.gameserver.enums.InstanceType;
import org.l2junity.gameserver.instancemanager.FortSiegeManager;
import org.l2junity.gameserver.model.FortSiegeSpawn;
import org.l2junity.gameserver.model.L2Spawn;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.Summon;
import org.l2junity.gameserver.model.actor.templates.L2NpcTemplate;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.client.send.string.NpcStringId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class L2FortCommanderInstance extends L2DefenderInstance
{
	private static final Logger LOGGER = LoggerFactory.getLogger(L2FortCommanderInstance.class);
	
	private boolean _canTalk;
	
	public L2FortCommanderInstance(L2NpcTemplate template)
	{
		super(template);
		setInstanceType(InstanceType.L2FortCommanderInstance);
		_canTalk = true;
	}
	
	/**
	 * Return True if a siege is in progress and the L2Character attacker isn't a Defender.
	 * @param attacker The L2Character that the L2CommanderInstance try to attack
	 */
	@Override
	public boolean isAutoAttackable(Creature attacker)
	{
		if ((attacker == null) || !(attacker instanceof PlayerInstance))
		{
			return false;
		}
		
		// Attackable during siege by all except defenders
		return ((getFort() != null) && (getFort().getResidenceId() > 0) && getFort().getSiege().isInProgress() && !getFort().getSiege().checkIsDefender(attacker.getClan()));
	}
	
	@Override
	public void addDamageHate(Creature attacker, int damage, int aggro)
	{
		if (attacker == null)
		{
			return;
		}
		
		if (!(attacker instanceof L2FortCommanderInstance))
		{
			super.addDamageHate(attacker, damage, aggro);
		}
	}
	
	@Override
	public boolean doDie(Creature killer)
	{
		if (!super.doDie(killer))
		{
			return false;
		}
		
		if (getFort().getSiege().isInProgress())
		{
			getFort().getSiege().killedCommander(this);
			
		}
		
		return true;
	}
	
	/**
	 * This method forces guard to return to home location previously set
	 */
	@Override
	public void returnHome()
	{
		if (!isInRadius2d(getSpawn(), 200))
		{
			if (GeneralConfig.DEBUG)
			{
				LOGGER.info(getObjectId() + ": moving home");
			}
			setisReturningToSpawnPoint(true);
			clearAggroList();
			
			if (hasAI())
			{
				getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, getSpawn());
			}
		}
	}
	
	@Override
	public final void addDamage(Creature attacker, int damage, Skill skill)
	{
		L2Spawn spawn = getSpawn();
		if ((spawn != null) && canTalk())
		{
			List<FortSiegeSpawn> commanders = FortSiegeManager.getInstance().getCommanderSpawnList(getFort().getResidenceId());
			for (FortSiegeSpawn spawn2 : commanders)
			{
				if (spawn2.getId() == spawn.getId())
				{
					NpcStringId npcString = null;
					switch (spawn2.getMessageId())
					{
						case 1:
							npcString = NpcStringId.ATTACKING_THE_ENEMY_S_REINFORCEMENTS_IS_NECESSARY_TIME_TO_DIE;
							break;
						case 2:
							if (attacker.isSummon())
							{
								attacker = ((Summon) attacker).getOwner();
							}
							npcString = NpcStringId.EVERYONE_CONCENTRATE_YOUR_ATTACKS_ON_S1_SHOW_THE_ENEMY_YOUR_RESOLVE;
							break;
						case 3:
							npcString = NpcStringId.FIRE_SPIRIT_UNLEASH_YOUR_POWER_BURN_THE_ENEMY;
							break;
					}
					if (npcString != null)
					{
						broadcastSay(ChatType.NPC_SHOUT, npcString, npcString.getParamCount() == 1 ? attacker.getName() : null);
						setCanTalk(false);
						ThreadPool.schedule(new ScheduleTalkTask(), 10000, TimeUnit.MILLISECONDS);
					}
				}
			}
		}
		super.addDamage(attacker, damage, skill);
	}
	
	private class ScheduleTalkTask implements Runnable
	{
		
		public ScheduleTalkTask()
		{
		}
		
		@Override
		public void run()
		{
			setCanTalk(true);
		}
	}
	
	void setCanTalk(boolean val)
	{
		_canTalk = val;
	}
	
	private boolean canTalk()
	{
		return _canTalk;
	}
	
	@Override
	public boolean hasRandomAnimation()
	{
		return false;
	}
}
