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
package org.l2junity.gameserver.instancemanager;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.l2junity.commons.loader.annotations.InstanceGetter;
import org.l2junity.commons.loader.annotations.Load;
import org.l2junity.commons.sql.DatabaseFactory;
import org.l2junity.commons.util.BasePathProvider;
import org.l2junity.gameserver.loader.LoadGroup;
import org.l2junity.gameserver.model.CombatFlag;
import org.l2junity.gameserver.model.FortSiegeSpawn;
import org.l2junity.gameserver.model.L2Clan;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.entity.Fort;
import org.l2junity.gameserver.model.entity.FortSiege;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.skills.CommonSkill;
import org.l2junity.gameserver.network.client.send.SystemMessage;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class FortSiegeManager
{
	private static final Logger LOGGER = LoggerFactory.getLogger(FortSiegeManager.class);
	
	private int _attackerMaxClans = 500; // Max number of clans
	
	// Fort Siege settings
	private Map<Integer, List<FortSiegeSpawn>> _commanderSpawnList;
	private Map<Integer, List<CombatFlag>> _flagList;
	private boolean _justToTerritory = true; // Changeable in fortsiege.properties
	private int _flagMaxCount = 1; // Changeable in fortsiege.properties
	private int _siegeClanMinLevel = 4; // Changeable in fortsiege.properties
	private int _siegeLength = 60; // Time in minute. Changeable in fortsiege.properties
	private int _countDownLength = 10; // Time in minute. Changeable in fortsiege.properties
	private int _suspiciousMerchantRespawnDelay = 180; // Time in minute. Changeable in fortsiege.properties
	private List<FortSiege> _sieges;
	
	protected FortSiegeManager()
	{
	}
	
	@Load(group = LoadGroup.class)
	private void load()
	{
		final Properties siegeSettings = new Properties();
		// TODO normal config, please...
		try (InputStream is = Files.newInputStream(BasePathProvider.resolvePath(Paths.get("config", "FortSiege.properties"))))
		{
			siegeSettings.load(is);
		}
		catch (Exception e)
		{
			LOGGER.warn("Error while loading Fort Siege Manager settings!", e);
		}
		
		// Siege setting
		_justToTerritory = Boolean.parseBoolean(siegeSettings.getProperty("JustToTerritory", "true"));
		_attackerMaxClans = Integer.decode(siegeSettings.getProperty("AttackerMaxClans", "500"));
		_flagMaxCount = Integer.decode(siegeSettings.getProperty("MaxFlags", "1"));
		_siegeClanMinLevel = Integer.decode(siegeSettings.getProperty("SiegeClanMinLevel", "4"));
		_siegeLength = Integer.decode(siegeSettings.getProperty("SiegeLength", "60"));
		_countDownLength = Integer.decode(siegeSettings.getProperty("CountDownLength", "10"));
		_suspiciousMerchantRespawnDelay = Integer.decode(siegeSettings.getProperty("SuspiciousMerchantRespawnDelay", "180"));
		
		// Siege spawns settings
		_commanderSpawnList = new ConcurrentHashMap<>();
		_flagList = new ConcurrentHashMap<>();
		
		for (Fort fort : FortManager.getInstance().getForts())
		{
			List<FortSiegeSpawn> _commanderSpawns = new CopyOnWriteArrayList<>();
			List<CombatFlag> _flagSpawns = new CopyOnWriteArrayList<>();
			for (int i = 1; i < 5; i++)
			{
				final String _spawnParams = siegeSettings.getProperty(fort.getName().replace(" ", "") + "Commander" + i, "");
				if (_spawnParams.isEmpty())
				{
					break;
				}
				final StringTokenizer st = new StringTokenizer(_spawnParams.trim(), ",");
				
				try
				{
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					int z = Integer.parseInt(st.nextToken());
					int heading = Integer.parseInt(st.nextToken());
					int npc_id = Integer.parseInt(st.nextToken());
					
					_commanderSpawns.add(new FortSiegeSpawn(fort.getResidenceId(), x, y, z, heading, npc_id, i));
				}
				catch (Exception e)
				{
					LOGGER.warn("Error while loading commander(s) for " + fort.getName() + " fort.");
				}
			}
			
			_commanderSpawnList.put(fort.getResidenceId(), _commanderSpawns);
			
			for (int i = 1; i < 4; i++)
			{
				final String _spawnParams = siegeSettings.getProperty(fort.getName().replace(" ", "") + "Flag" + i, "");
				if (_spawnParams.isEmpty())
				{
					break;
				}
				final StringTokenizer st = new StringTokenizer(_spawnParams.trim(), ",");
				
				try
				{
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					int z = Integer.parseInt(st.nextToken());
					int flag_id = Integer.parseInt(st.nextToken());
					
					_flagSpawns.add(new CombatFlag(fort.getResidenceId(), x, y, z, 0, flag_id));
				}
				catch (Exception e)
				{
					LOGGER.warn("Error while loading flag(s) for " + fort.getName() + " fort.");
				}
			}
			_flagList.put(fort.getResidenceId(), _flagSpawns);
		}
	}
	
	public final void addSiegeSkills(PlayerInstance character)
	{
		character.addSkill(CommonSkill.SEAL_OF_RULER.getSkill(), false);
		character.addSkill(CommonSkill.BUILD_HEADQUARTERS.getSkill(), false);
	}
	
	/**
	 * @param clan The L2Clan of the player
	 * @param fortid
	 * @return true if the clan is registered or owner of a fort
	 */
	public final boolean checkIsRegistered(L2Clan clan, int fortid)
	{
		if (clan == null)
		{
			return false;
		}
		
		boolean register = false;
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT clan_id FROM fortsiege_clans where clan_id=? and fort_id=?"))
		{
			ps.setInt(1, clan.getId());
			ps.setInt(2, fortid);
			try (ResultSet rs = ps.executeQuery())
			{
				if (rs.next())
				{
					register = true;
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.warn("Exception: checkIsRegistered(): " + e.getMessage(), e);
		}
		return register;
	}
	
	public final void removeSiegeSkills(PlayerInstance character)
	{
		character.removeSkill(CommonSkill.SEAL_OF_RULER.getSkill());
		character.removeSkill(CommonSkill.BUILD_HEADQUARTERS.getSkill());
	}
	
	public final List<FortSiegeSpawn> getCommanderSpawnList(int _fortId)
	{
		return _commanderSpawnList.get(_fortId);
	}
	
	public final List<CombatFlag> getFlagList(int _fortId)
	{
		return _flagList.get(_fortId);
	}
	
	public final int getAttackerMaxClans()
	{
		return _attackerMaxClans;
	}
	
	public final int getFlagMaxCount()
	{
		return _flagMaxCount;
	}
	
	public final boolean canRegisterJustTerritory()
	{
		return _justToTerritory;
	}
	
	public final int getSuspiciousMerchantRespawnDelay()
	{
		return _suspiciousMerchantRespawnDelay;
	}
	
	public final FortSiege getSiege(WorldObject activeObject)
	{
		return getSiege(activeObject.getX(), activeObject.getY(), activeObject.getZ());
	}
	
	public final FortSiege getSiege(double x, double y, double z)
	{
		for (Fort fort : FortManager.getInstance().getForts())
		{
			if (fort.getSiege().checkIfInZone(x, y, z))
			{
				return fort.getSiege();
			}
		}
		return null;
	}
	
	public final int getSiegeClanMinLevel()
	{
		return _siegeClanMinLevel;
	}
	
	public final int getSiegeLength()
	{
		return _siegeLength;
	}
	
	public final int getCountDownLength()
	{
		return _countDownLength;
	}
	
	public final List<FortSiege> getSieges()
	{
		if (_sieges == null)
		{
			_sieges = new CopyOnWriteArrayList<>();
		}
		return _sieges;
	}
	
	public final void addSiege(FortSiege fortSiege)
	{
		getSieges().add(fortSiege);
	}
	
	public boolean isCombat(int itemId)
	{
		return (itemId == 9819);
	}
	
	public boolean activateCombatFlag(PlayerInstance player, ItemInstance item)
	{
		if (!checkIfCanPickup(player))
		{
			return false;
		}
		
		final Fort fort = FortManager.getInstance().getFort(player);
		
		final List<CombatFlag> fcf = _flagList.get(fort.getResidenceId());
		for (CombatFlag cf : fcf)
		{
			if (cf.getCombatFlagInstance() == item)
			{
				cf.activate(player, item);
			}
		}
		return true;
	}
	
	public boolean checkIfCanPickup(PlayerInstance player)
	{
		final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.THE_FORTRESS_BATTLE_OF_S1_HAS_FINISHED);
		sm.addItemName(9819);
		// Cannot own 2 combat flag
		if (player.isCombatFlagEquipped())
		{
			player.sendPacket(sm);
			return false;
		}
		
		// here check if is siege is in progress
		// here check if is siege is attacker
		final Fort fort = FortManager.getInstance().getFort(player);
		
		if ((fort == null) || (fort.getResidenceId() <= 0))
		{
			player.sendPacket(sm);
			return false;
		}
		else if (!fort.getSiege().isInProgress())
		{
			player.sendPacket(sm);
			return false;
		}
		else if (fort.getSiege().getAttackerClan(player.getClan()) == null)
		{
			player.sendPacket(sm);
			return false;
		}
		return true;
	}
	
	public void dropCombatFlag(PlayerInstance player, int fortId)
	{
		final Fort fort = FortManager.getInstance().getFortById(fortId);
		final List<CombatFlag> fcf = _flagList.get(fort.getResidenceId());
		for (CombatFlag cf : fcf)
		{
			if (cf.getPlayerObjectId() == player.getObjectId())
			{
				cf.dropIt();
				if (fort.getSiege().isInProgress())
				{
					cf.spawnMe();
				}
			}
		}
	}
	
	@InstanceGetter
	public static FortSiegeManager getInstance()
	{
		return SingletonHolder.INSTANCE;
	}
	
	private static class SingletonHolder
	{
		protected static final FortSiegeManager INSTANCE = new FortSiegeManager();
	}
}
