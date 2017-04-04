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
package org.l2junity.gameserver.data.sql.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.gameserver.engines.IdFactory;
import org.l2junity.commons.sql.DatabaseFactory;
import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.communitybbs.Manager.ForumsBBSManager;
import org.l2junity.gameserver.data.xml.impl.ClanHallData;
import org.l2junity.gameserver.data.xml.impl.ClanRewardData;
import org.l2junity.gameserver.data.xml.impl.SkillData;
import org.l2junity.gameserver.enums.UserInfoType;
import org.l2junity.gameserver.instancemanager.FortManager;
import org.l2junity.gameserver.instancemanager.FortSiegeManager;
import org.l2junity.gameserver.instancemanager.SiegeManager;
import org.l2junity.gameserver.model.ClanMember;
import org.l2junity.gameserver.model.ClanPrivilege;
import org.l2junity.gameserver.model.ClanWar;
import org.l2junity.gameserver.model.ClanWar.ClanWarState;
import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.entity.ClanHall;
import org.l2junity.gameserver.model.entity.Fort;
import org.l2junity.gameserver.model.entity.FortSiege;
import org.l2junity.gameserver.model.entity.Siege;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerClanCreate;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerClanDestroy;
import org.l2junity.gameserver.model.events.impl.clan.OnClanWarFinish;
import org.l2junity.gameserver.network.client.send.PledgeShowInfoUpdate;
import org.l2junity.gameserver.network.client.send.PledgeShowMemberListAll;
import org.l2junity.gameserver.network.client.send.PledgeShowMemberListUpdate;
import org.l2junity.gameserver.network.client.send.SystemMessage;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;
import org.l2junity.gameserver.util.EnumIntBitmask;
import org.l2junity.gameserver.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
@StartupComponent(value = "Data", dependency = {SkillData.class, ClanRewardData.class})
public class ClanTable {
	@Getter(lazy = true)
	private static final ClanTable instance = new ClanTable();

	private final Map<Integer, Clan> _clans = new ConcurrentHashMap<>();

	protected ClanTable() {
		// forums has to be loaded before clan data, because of last forum id used should have also memo included
		if (GeneralConfig.ENABLE_COMMUNITY_BOARD) {
			ForumsBBSManager.getInstance().initRoot();
		}

		Clan clan;
		// Count the clans
		int clanCount = 0;
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 Statement s = con.createStatement();
			 ResultSet rs = s.executeQuery("SELECT clan_id FROM clan_data")) {
			while (rs.next()) {
				int clanId = rs.getInt("clan_id");
				_clans.put(clanId, new Clan(clanId));
				clan = getClan(clanId);
				if (clan.getDissolvingExpiryTime() != 0) {
					scheduleRemoveClan(clan.getId());
				}
				clanCount++;
			}
		} catch (Exception e) {
			log.error("Error restoring ClanTable.", e);
		}
		log.info("Restored " + clanCount + " clans from the database.");
		allianceCheck();
		restoreClanWars();
	}

	/**
	 * Gets the clans.
	 *
	 * @return the clans
	 */
	public Collection<Clan> getClans() {
		return _clans.values();
	}

	/**
	 * Gets the clan count.
	 *
	 * @return the clan count
	 */
	public int getClanCount() {
		return _clans.size();
	}

	/**
	 * @param clanId
	 * @return
	 */
	public Clan getClan(int clanId) {
		return _clans.get(clanId);
	}

	public Clan getClanByName(String clanName) {
		return getClans().stream().filter(c -> c.getName().equalsIgnoreCase(clanName)).findFirst().orElse(null);
	}

	/**
	 * Creates a new clan and store clan info to database
	 *
	 * @param player
	 * @param clanName
	 * @return NULL if clan with same name already exists
	 */
	public Clan createClan(Player player, String clanName) {
		if (player == null) {
			return null;
		}

		if (player.getLevel() < 10) {
			player.sendPacket(SystemMessageId.YOU_DO_NOT_MEET_THE_CRITERIA_IN_ORDER_TO_CREATE_A_CLAN);
			return null;
		}
		if (player.getClanId() != 0) {
			player.sendPacket(SystemMessageId.YOU_HAVE_FAILED_TO_CREATE_A_CLAN);
			return null;
		}
		if (System.currentTimeMillis() < player.getClanCreateExpiryTime()) {
			player.sendPacket(SystemMessageId.YOU_MUST_WAIT_10_DAYS_BEFORE_CREATING_A_NEW_CLAN);
			return null;
		}
		if (!Util.isAlphaNumeric(clanName) || (clanName.length() < 2)) {
			player.sendPacket(SystemMessageId.CLAN_NAME_IS_INVALID);
			return null;
		}
		if (clanName.length() > 16) {
			player.sendPacket(SystemMessageId.CLAN_NAME_S_LENGTH_IS_INCORRECT);
			return null;
		}

		if (getClanByName(clanName) != null) {
			// clan name is already taken
			SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_ALREADY_EXISTS);
			sm.addString(clanName);
			player.sendPacket(sm);
			return null;
		}

		final Clan clan = new Clan(IdFactory.getInstance().getNextId(), clanName);
		final ClanMember leader = new ClanMember(clan, player);
		clan.setLeader(leader);
		leader.setPlayerInstance(player);
		clan.store();
		player.setClan(clan);
		player.setPledgeClass(ClanMember.calculatePledgeClass(player));
		player.setClanPrivileges(new EnumIntBitmask<>(ClanPrivilege.class, true));

		_clans.put(Integer.valueOf(clan.getId()), clan);

		// should be update packet only
		player.sendPacket(new PledgeShowInfoUpdate(clan));
		PledgeShowMemberListAll.sendAllTo(player);
		player.sendPacket(new PledgeShowMemberListUpdate(player));
		player.sendPacket(SystemMessageId.YOUR_CLAN_HAS_BEEN_CREATED);
		player.broadcastUserInfo(UserInfoType.RELATION, UserInfoType.CLAN);

		// Notify to scripts
		EventDispatcher.getInstance().notifyEventAsync(new OnPlayerClanCreate(player, clan));
		return clan;
	}

	public synchronized void destroyClan(int clanId) {
		Clan clan = getClan(clanId);
		if (clan == null) {
			return;
		}

		clan.broadcastToOnlineMembers(SystemMessage.getSystemMessage(SystemMessageId.CLAN_HAS_DISPERSED));
		int castleId = clan.getCastleId();
		if (castleId == 0) {
			for (Siege siege : SiegeManager.getInstance().getSieges()) {
				siege.removeSiegeClan(clan);
			}
		}

		int fortId = clan.getFortId();
		if (fortId == 0) {
			for (FortSiege siege : FortSiegeManager.getInstance().getSieges()) {
				siege.removeAttacker(clan);
			}
		}

		final ClanHall hall = ClanHallData.getInstance().getClanHallByClan(clan);
		if (hall != null) {
			hall.setOwner(null);
		}

		ClanMember leaderMember = clan.getLeader();
		if (leaderMember == null) {
			clan.getWarehouse().destroyAllItems("ClanRemove", null, null);
		} else {
			clan.getWarehouse().destroyAllItems("ClanRemove", clan.getLeader().getPlayerInstance(), null);
		}

		for (ClanMember member : clan.getMembers()) {
			clan.removeClanMember(member.getObjectId(), 0);
		}

		_clans.remove(clanId);
		IdFactory.getInstance().releaseId(clanId);

		try (Connection con = DatabaseFactory.getInstance().getConnection()) {
			try (PreparedStatement ps = con.prepareStatement("DELETE FROM clan_data WHERE clan_id=?")) {
				ps.setInt(1, clanId);
				ps.execute();
			}

			try (PreparedStatement ps = con.prepareStatement("DELETE FROM clan_privs WHERE clan_id=?")) {
				ps.setInt(1, clanId);
				ps.execute();
			}

			try (PreparedStatement ps = con.prepareStatement("DELETE FROM clan_skills WHERE clan_id=?")) {
				ps.setInt(1, clanId);
				ps.execute();
			}

			try (PreparedStatement ps = con.prepareStatement("DELETE FROM clan_subpledges WHERE clan_id=?")) {
				ps.setInt(1, clanId);
				ps.execute();
			}

			try (PreparedStatement ps = con.prepareStatement("DELETE FROM clan_wars WHERE clan1=? OR clan2=?")) {
				ps.setInt(1, clanId);
				ps.setInt(2, clanId);
				ps.execute();
			}

			try (PreparedStatement ps = con.prepareStatement("DELETE FROM clan_notices WHERE clan_id=?")) {
				ps.setInt(1, clanId);
				ps.execute();
			}

			if (fortId != 0) {
				Fort fort = FortManager.getInstance().getFortById(fortId);
				if (fort != null) {
					Clan owner = fort.getOwnerClan();
					if (clan == owner) {
						fort.removeOwner(true);
					}
				}
			}
		} catch (Exception e) {
			log.error("Error removing clan from DB: {}", e);
		}

		// Notify to scripts
		EventDispatcher.getInstance().notifyEventAsync(new OnPlayerClanDestroy(leaderMember, clan));
	}

	public void scheduleRemoveClan(final int clanId) {
		ThreadPool.getInstance().scheduleGeneral(() ->
		{
			if (getClan(clanId) == null) {
				return;
			}
			if (getClan(clanId).getDissolvingExpiryTime() != 0) {
				destroyClan(clanId);
			}
		}, Math.max(getClan(clanId).getDissolvingExpiryTime() - System.currentTimeMillis(), 300000), TimeUnit.MILLISECONDS);
	}

	public boolean isAllyExists(String allyName) {
		for (Clan clan : getClans()) {
			if ((clan.getAllyName() != null) && clan.getAllyName().equalsIgnoreCase(allyName)) {
				return true;
			}
		}
		return false;
	}

	public void storeClansWar(ClanWar war) {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement ps = con.prepareStatement("REPLACE INTO clan_wars (clan1, clan2, clan1Kill, clan2Kill, winnerClan, startTime, endTime, state) VALUES(?,?,?,?,?,?,?,?)")) {
			ps.setInt(1, war.getAttackerClanId());
			ps.setInt(2, war.getAttackedClanId());
			ps.setInt(3, war.getAttackerKillCount());
			ps.setInt(4, war.getAttackedKillCount());
			ps.setInt(5, war.getWinnerClanId());
			ps.setLong(6, war.getStartTime());
			ps.setLong(7, war.getEndTime());
			ps.setInt(8, war.getState().ordinal());
			ps.execute();
		} catch (Exception e) {
			log.error("Error storing clan wars data: {}", e);
		}
	}

	public void deleteClansWar(int clanId1, int clanId2) {
		final Clan clan1 = ClanTable.getInstance().getClan(clanId1);
		final Clan clan2 = ClanTable.getInstance().getClan(clanId2);

		EventDispatcher.getInstance().notifyEventAsync(new OnClanWarFinish(clan1, clan2));

		clan1.deleteWar(clan2.getId());
		clan2.deleteWar(clan1.getId());
		clan1.broadcastClanStatus();
		clan2.broadcastClanStatus();

		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement ps = con.prepareStatement("DELETE FROM clan_wars WHERE clan1=? AND clan2=?")) {
			ps.setInt(1, clanId1);
			ps.setInt(2, clanId2);
			ps.execute();
		} catch (Exception e) {
			log.error("Error removing clan wars data: {}", e);
		}
	}

	private void restoreClanWars() {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 Statement statement = con.createStatement();
			 ResultSet rset = statement.executeQuery("SELECT clan1, clan2, clan1Kill, clan2Kill, winnerClan, startTime, endTime, state FROM clan_wars")) {
			while (rset.next()) {
				final Clan attacker = getClan(rset.getInt("clan1"));
				final Clan attacked = getClan(rset.getInt("clan2"));
				if ((attacker != null) && (attacked != null)) {
					final ClanWarState state = ClanWarState.values()[rset.getInt("state")];

					final ClanWar clanWar = new ClanWar(attacker, attacked, rset.getInt("clan1Kill"), rset.getInt("clan2Kill"), rset.getInt("winnerClan"), rset.getLong("startTime"), rset.getLong("endTime"), state);
					attacker.addWar(attacked.getId(), clanWar);
					attacked.addWar(attacker.getId(), clanWar);
				} else {
					log.warn("restorewars one of clans is null clan1:" + attacker + " clan2:" + attacked);
				}
			}
		} catch (Exception e) {
			log.error("Error restoring clan wars data: {}", e);
		}
	}

	/**
	 * Check for nonexistent alliances
	 */
	private void allianceCheck() {
		for (Clan clan : _clans.values()) {
			int allyId = clan.getAllyId();
			if ((allyId != 0) && (clan.getId() != allyId)) {
				if (!_clans.containsKey(allyId)) {
					clan.setAllyId(0);
					clan.setAllyName(null);
					clan.changeAllyCrest(0, true);
					clan.updateClanInDB();
					log.info("Removed alliance from clan: {}", clan);
				}
			}
		}
	}

	public List<Clan> getClanAllies(int allianceId) {
		final List<Clan> clanAllies = new ArrayList<>();
		if (allianceId != 0) {
			for (Clan clan : _clans.values()) {
				if ((clan != null) && (clan.getAllyId() == allianceId)) {
					clanAllies.add(clan);
				}
			}
		}
		return clanAllies;
	}

	public void shutdown() {
		for (Clan clan : _clans.values()) {
			clan.updateInDB();
		}
	}
}
