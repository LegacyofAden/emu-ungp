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
package org.l2junity.gameserver.model.olympiad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.l2junity.gameserver.config.OlympiadConfig;
import org.l2junity.gameserver.instancemanager.MultiboxManager;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.network.client.send.ActionFailed;
import org.l2junity.gameserver.network.client.send.SystemMessage;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;

/**
 * @author DS
 */
public class OlympiadManager
{
	private final Set<Integer> _nonClassBasedRegisters = ConcurrentHashMap.newKeySet();
	private final Map<Integer, Set<Integer>> _classBasedRegisters = new ConcurrentHashMap<>();
	
	protected OlympiadManager()
	{
	}
	
	public static OlympiadManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	public final Set<Integer> getRegisteredNonClassBased()
	{
		return _nonClassBasedRegisters;
	}
	
	public final Map<Integer, Set<Integer>> getRegisteredClassBased()
	{
		return _classBasedRegisters;
	}
	
	protected final List<Set<Integer>> hasEnoughRegisteredClassed()
	{
		List<Set<Integer>> result = null;
		for (Map.Entry<Integer, Set<Integer>> classList : _classBasedRegisters.entrySet())
		{
			if ((classList.getValue() != null) && (classList.getValue().size() >= OlympiadConfig.ALT_OLY_CLASSED))
			{
				if (result == null)
				{
					result = new ArrayList<>();
				}
				
				result.add(classList.getValue());
			}
		}
		return result;
	}
	
	protected final boolean hasEnoughRegisteredNonClassed()
	{
		return _nonClassBasedRegisters.size() >= OlympiadConfig.ALT_OLY_NONCLASSED;
	}
	
	protected final void clearRegistered()
	{
		_nonClassBasedRegisters.clear();
		_classBasedRegisters.clear();
		MultiboxManager.getInstance().clearManager(Olympiad.getInstance());
	}
	
	public final boolean isRegistered(PlayerInstance noble)
	{
		return isRegistered(noble, noble, false);
	}
	
	private boolean isRegistered(PlayerInstance noble, PlayerInstance player, boolean showMessage)
	{
		final Integer objId = Integer.valueOf(noble.getObjectId());
		if (_nonClassBasedRegisters.contains(objId))
		{
			if (showMessage)
			{
				final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_IS_ALREADY_REGISTERED_ON_THE_WAITING_LIST_FOR_THE_ALL_CLASS_BATTLE);
				sm.addPcName(noble);
				player.sendPacket(sm);
			}
			return true;
		}
		
		final Set<Integer> classed = _classBasedRegisters.get(noble.getBaseClass());
		if ((classed != null) && classed.contains(objId))
		{
			if (showMessage)
			{
				final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_IS_ALREADY_REGISTERED_ON_THE_CLASS_MATCH_WAITING_LIST);
				sm.addPcName(noble);
				player.sendPacket(sm);
			}
			return true;
		}
		
		return false;
	}
	
	public final boolean isRegisteredInComp(PlayerInstance noble)
	{
		return isRegistered(noble, noble, false) || isInCompetition(noble, noble, false);
	}
	
	private boolean isInCompetition(PlayerInstance noble, PlayerInstance player, boolean showMessage)
	{
		if (!Olympiad._inCompPeriod)
		{
			return false;
		}
		
		AbstractOlympiadGame game;
		for (int i = OlympiadGameManager.getInstance().getNumberOfStadiums(); --i >= 0;)
		{
			game = OlympiadGameManager.getInstance().getOlympiadTask(i).getGame();
			if (game == null)
			{
				continue;
			}
			
			if (game.containsParticipant(noble.getObjectId()))
			{
				if (!showMessage)
				{
					return true;
				}
				
				switch (game.getType())
				{
					case CLASSED:
					{
						final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_IS_ALREADY_REGISTERED_ON_THE_CLASS_MATCH_WAITING_LIST);
						sm.addPcName(noble);
						player.sendPacket(sm);
						break;
					}
					case NON_CLASSED:
					{
						final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_IS_ALREADY_REGISTERED_ON_THE_WAITING_LIST_FOR_THE_ALL_CLASS_BATTLE);
						sm.addPcName(noble);
						player.sendPacket(sm);
						break;
					}
				}
				return true;
			}
		}
		return false;
	}
	
	public final boolean registerNoble(PlayerInstance player, CompetitionType type)
	{
		if (!Olympiad._inCompPeriod)
		{
			player.sendPacket(SystemMessageId.THE_OLYMPIAD_GAMES_ARE_NOT_CURRENTLY_IN_PROGRESS);
			return false;
		}
		
		if (Olympiad.getInstance().getMillisToCompEnd() < 600000)
		{
			player.sendPacket(SystemMessageId.PARTICIPATION_REQUESTS_ARE_NO_LONGER_BEING_ACCEPTED);
			return false;
		}
		
		final int charId = player.getObjectId();
		if (Olympiad.getInstance().getRemainingWeeklyMatches(charId) < 1)
		{
			player.sendPacket(SystemMessageId.THE_MAXIMUM_MATCHES_YOU_CAN_PARTICIPATE_IN_1_WEEK_IS_30);
			return false;
		}
		
		if (player.isInTraingCamp())
		{
			player.sendPacket(ActionFailed.STATIC_PACKET);
			// TODO: C1_IS_CURRENTLY_IN_THE_ROYAL_TRAINING_CAMP_AND_CANNOT_PARTICIPATE_IN_THE_OLYMPIAD ?
			return false;
		}
		
		switch (type)
		{
			case CLASSED:
			{
				if (player.isOnEvent())
				{
					player.sendMessage("You can't join olympiad while participating on TvT Event.");
					return false;
				}
				
				if (!MultiboxManager.getInstance().registerClient(Olympiad.getInstance(), player.getClient()))
				{
					MultiboxManager.getInstance().sendDefaultRestrictionMessage(Olympiad.getInstance(), player.getClient());
					return false;
				}
				
				if (Olympiad.getInstance().getRemainingWeeklyMatchesClassed(charId) < 1)
				{
					player.sendPacket(SystemMessageId.YOU_CAN_ENTER_UP_TO_30_FREE_FOR_ALL_BATTLES_AND_30_CLASS_SPECIFIC_BATTLES_PER_WEEK);
					return false;
				}
				
				_classBasedRegisters.computeIfAbsent(player.getBaseClass(), k -> ConcurrentHashMap.newKeySet()).add(charId);
				player.sendPacket(SystemMessageId.YOU_HAVE_BEEN_REGISTERED_FOR_THE_OLYMPIAD_WAITING_LIST_FOR_A_CLASS_BATTLE);
				break;
			}
			case NON_CLASSED:
			{
				if (player.isOnEvent())
				{
					player.sendMessage("You can't join olympiad while participating on TvT Event.");
					return false;
				}
				
				if (!MultiboxManager.getInstance().registerClient(Olympiad.getInstance(), player.getClient()))
				{
					MultiboxManager.getInstance().sendDefaultRestrictionMessage(Olympiad.getInstance(), player.getClient());
					return false;
				}
				
				if (Olympiad.getInstance().getRemainingWeeklyMatchesNonClassed(charId) < 1)
				{
					player.sendPacket(SystemMessageId.YOU_CAN_ENTER_UP_TO_30_FREE_FOR_ALL_BATTLES_AND_30_CLASS_SPECIFIC_BATTLES_PER_WEEK);
					return false;
				}
				
				_nonClassBasedRegisters.add(charId);
				player.sendPacket(SystemMessageId.YOU_ARE_CURRENTLY_REGISTERED_FOR_A_1V1_CLASS_IRRELEVANT_MATCH);
				break;
			}
		}
		return true;
	}
	
	public final boolean unRegisterNoble(PlayerInstance noble)
	{
		if (!Olympiad._inCompPeriod)
		{
			noble.sendPacket(SystemMessageId.THE_OLYMPIAD_GAMES_ARE_NOT_CURRENTLY_IN_PROGRESS);
			return false;
		}
		
		if (!noble.isNoble())
		{
			final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_DOES_NOT_MEET_THE_PARTICIPATION_REQUIREMENTS_ONLY_NOBLESSE_EXALTED_CHARACTERS_CAN_PARTICIPATE_IN_THE_OLYMPIAD);
			sm.addString(noble.getName());
			noble.sendPacket(sm);
			return false;
		}
		
		if (!isRegistered(noble, noble, false))
		{
			noble.sendPacket(SystemMessageId.YOU_ARE_NOT_CURRENTLY_REGISTERED_FOR_THE_OLYMPIAD);
			return false;
		}
		
		if (isInCompetition(noble, noble, false))
		{
			return false;
		}
		
		Integer objId = Integer.valueOf(noble.getObjectId());
		if (_nonClassBasedRegisters.remove(objId))
		{
			MultiboxManager.getInstance().unregisterClient(Olympiad.getInstance(), noble.getClient());
			
			noble.sendPacket(SystemMessageId.YOU_HAVE_BEEN_REMOVED_FROM_THE_OLYMPIAD_WAITING_LIST);
			return true;
		}
		
		final Set<Integer> classed = _classBasedRegisters.get(noble.getBaseClass());
		if ((classed != null) && classed.remove(objId))
		{
			MultiboxManager.getInstance().unregisterClient(Olympiad.getInstance(), noble.getClient());
			
			noble.sendPacket(SystemMessageId.YOU_HAVE_BEEN_REMOVED_FROM_THE_OLYMPIAD_WAITING_LIST);
			return true;
		}
		
		return false;
	}
	
	public final void removeDisconnectedCompetitor(PlayerInstance player)
	{
		final OlympiadGameTask task = OlympiadGameManager.getInstance().getOlympiadTask(player.getOlympiadGameId());
		if ((task != null) && task.isGameStarted())
		{
			task.getGame().handleDisconnect(player);
		}
		
		final Integer objId = Integer.valueOf(player.getObjectId());
		if (_nonClassBasedRegisters.remove(objId))
		{
			return;
		}
		
		_classBasedRegisters.getOrDefault(player.getBaseClass(), Collections.emptySet()).remove(objId);
	}
	
	public int getCountOpponents()
	{
		return _nonClassBasedRegisters.size() + _classBasedRegisters.size();
	}
	
	private static class SingletonHolder
	{
		protected static final OlympiadManager _instance = new OlympiadManager();
	}
}
