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
package org.l2junity.gameserver.model.entity;

import org.l2junity.commons.sql.DatabaseFactory;
import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.data.sql.impl.ClanTable;
import org.l2junity.gameserver.data.xml.impl.SiegeScheduleData;
import org.l2junity.gameserver.enums.SiegeClanType;
import org.l2junity.gameserver.enums.SiegeTeleportWhoType;
import org.l2junity.gameserver.instancemanager.CastleManager;
import org.l2junity.gameserver.instancemanager.SiegeGuardManager;
import org.l2junity.gameserver.instancemanager.SiegeManager;
import org.l2junity.gameserver.model.*;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.L2ControlTowerInstance;
import org.l2junity.gameserver.model.actor.instance.L2FlameTowerInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.sieges.OnCastleSiegeFinish;
import org.l2junity.gameserver.model.events.impl.sieges.OnCastleSiegeOwnerChange;
import org.l2junity.gameserver.model.events.impl.sieges.OnCastleSiegeStart;
import org.l2junity.gameserver.network.client.send.SiegeInfo;
import org.l2junity.gameserver.network.client.send.SystemMessage;
import org.l2junity.gameserver.network.client.send.UserInfo;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;
import org.l2junity.gameserver.util.Broadcast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Siege implements Siegable {
	protected static final Logger LOGGER = LoggerFactory.getLogger(Siege.class);

	// typeId's
	public static final byte OWNER = -1;
	public static final byte DEFENDER = 0;
	public static final byte ATTACKER = 1;
	public static final byte DEFENDER_NOT_APPROVED = 2;

	private int _controlTowerCount;

	public class ScheduleEndSiegeTask implements Runnable {
		private final Castle _castleInst;

		public ScheduleEndSiegeTask(Castle pCastle) {
			_castleInst = pCastle;
		}

		@Override
		public void run() {
			if (!isInProgress()) {
				return;
			}

			try {
				long timeRemaining = _siegeEndDate.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
				if (timeRemaining > 3600000) {
					SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_HOUR_S_UNTIL_CASTLE_SIEGE_CONCLUSION);
					sm.addInt(2);
					announceToPlayer(sm, true);
					ThreadPool.getInstance().scheduleGeneral(new ScheduleEndSiegeTask(_castleInst), timeRemaining - 3600000, TimeUnit.MILLISECONDS); // Prepare task for 1 hr left.
				} else if ((timeRemaining <= 3600000) && (timeRemaining > 600000)) {
					SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_MINUTE_S_UNTIL_CASTLE_SIEGE_CONCLUSION);
					sm.addInt((int) timeRemaining / 60000);
					announceToPlayer(sm, true);
					ThreadPool.getInstance().scheduleGeneral(new ScheduleEndSiegeTask(_castleInst), timeRemaining - 600000, TimeUnit.MILLISECONDS); // Prepare task for 10 minute left.
				} else if ((timeRemaining <= 600000) && (timeRemaining > 300000)) {
					SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_MINUTE_S_UNTIL_CASTLE_SIEGE_CONCLUSION);
					sm.addInt((int) timeRemaining / 60000);
					announceToPlayer(sm, true);
					ThreadPool.getInstance().scheduleGeneral(new ScheduleEndSiegeTask(_castleInst), timeRemaining - 300000, TimeUnit.MILLISECONDS); // Prepare task for 5 minute left.
				} else if ((timeRemaining <= 300000) && (timeRemaining > 10000)) {
					SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_MINUTE_S_UNTIL_CASTLE_SIEGE_CONCLUSION);
					sm.addInt((int) timeRemaining / 60000);
					announceToPlayer(sm, true);
					ThreadPool.getInstance().scheduleGeneral(new ScheduleEndSiegeTask(_castleInst), timeRemaining - 10000, TimeUnit.MILLISECONDS); // Prepare task for 10 seconds count down
				} else if ((timeRemaining <= 10000) && (timeRemaining > 0)) {
					SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.THIS_CASTLE_SIEGE_WILL_END_IN_S1_SECOND_S);
					sm.addInt((int) timeRemaining / 1000);
					announceToPlayer(sm, true);
					ThreadPool.getInstance().scheduleGeneral(new ScheduleEndSiegeTask(_castleInst), timeRemaining, TimeUnit.MILLISECONDS); // Prepare task for second count down
				} else {
					_castleInst.getSiege().endSiege();
				}
			} catch (Exception e) {
				LOGGER.error("Error while running siege end task for castle: {}", _castleInst, e);
			}
		}
	}

	public class ScheduleStartSiegeTask implements Runnable {
		private final Castle _castleInst;

		public ScheduleStartSiegeTask(Castle pCastle) {
			_castleInst = pCastle;
		}

		@Override
		public void run() {
			_scheduledStartSiegeTask.cancel(false);
			if (isInProgress()) {
				return;
			}

			try {
				if (!getIsTimeRegistrationOver()) {
					long regTimeRemaining = getTimeRegistrationOverDate().getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
					if (regTimeRemaining > 0) {
						_scheduledStartSiegeTask = ThreadPool.getInstance().scheduleGeneral(new ScheduleStartSiegeTask(_castleInst), regTimeRemaining, TimeUnit.MILLISECONDS);
						return;
					}
					endTimeRegistration(true);
				}

				long timeRemaining = getSiegeDate().getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
				if (timeRemaining > 86400000) {
					_scheduledStartSiegeTask = ThreadPool.getInstance().scheduleGeneral(new ScheduleStartSiegeTask(_castleInst), timeRemaining - 86400000, TimeUnit.MILLISECONDS); // Prepare task for 24 before siege start to end registration
				} else if ((timeRemaining <= 86400000) && (timeRemaining > 13600000)) {
					SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.THE_REGISTRATION_TERM_FOR_S1_HAS_ENDED);
					sm.addCastleId(getCastle().getResidenceId());
					Broadcast.toAllOnlinePlayers(sm);
					_isRegistrationOver = true;
					clearSiegeWaitingClan();
					_scheduledStartSiegeTask = ThreadPool.getInstance().scheduleGeneral(new ScheduleStartSiegeTask(_castleInst), timeRemaining - 13600000, TimeUnit.MILLISECONDS); // Prepare task for 1 hr left before siege start.
				} else if ((timeRemaining <= 13600000) && (timeRemaining > 600000)) {
					_scheduledStartSiegeTask = ThreadPool.getInstance().scheduleGeneral(new ScheduleStartSiegeTask(_castleInst), timeRemaining - 600000, TimeUnit.MILLISECONDS); // Prepare task for 10 minute left.
				} else if ((timeRemaining <= 600000) && (timeRemaining > 300000)) {
					_scheduledStartSiegeTask = ThreadPool.getInstance().scheduleGeneral(new ScheduleStartSiegeTask(_castleInst), timeRemaining - 300000, TimeUnit.MILLISECONDS); // Prepare task for 5 minute left.
				} else if ((timeRemaining <= 300000) && (timeRemaining > 10000)) {
					_scheduledStartSiegeTask = ThreadPool.getInstance().scheduleGeneral(new ScheduleStartSiegeTask(_castleInst), timeRemaining - 10000, TimeUnit.MILLISECONDS); // Prepare task for 10 seconds count down
				} else if ((timeRemaining <= 10000) && (timeRemaining > 0)) {
					_scheduledStartSiegeTask = ThreadPool.getInstance().scheduleGeneral(new ScheduleStartSiegeTask(_castleInst), timeRemaining, TimeUnit.MILLISECONDS); // Prepare task for second count down
				} else {
					_castleInst.getSiege().startSiege();
				}
			} catch (Exception e) {
				LOGGER.error("Error while running siege start task for castle: {}", _castleInst, e);
			}
		}
	}

	// must support Concurrent Modifications
	private final List<SiegeClan> _attackerClans = new CopyOnWriteArrayList<>();
	private final List<SiegeClan> _defenderClans = new CopyOnWriteArrayList<>();
	private final List<SiegeClan> _defenderWaitingClans = new CopyOnWriteArrayList<>();

	// Castle setting
	private final List<L2ControlTowerInstance> _controlTowers = new ArrayList<>();
	private final List<L2FlameTowerInstance> _flameTowers = new ArrayList<>();
	private final Castle _castle;
	private boolean _isInProgress = false;
	private boolean _isNormalSide = true; // true = Atk is Atk, false = Atk is Def
	protected boolean _isRegistrationOver = false;
	protected Calendar _siegeEndDate;
	protected ScheduledFuture<?> _scheduledStartSiegeTask = null;
	protected int _firstOwnerClanId = -1;

	public Siege(Castle castle) {
		_castle = castle;

		startAutoTask();
	}

	@Override
	public void endSiege() {
		if (isInProgress()) {
			SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.THE_S1_SIEGE_HAS_FINISHED);
			sm.addCastleId(getCastle().getResidenceId());
			Broadcast.toAllOnlinePlayers(sm);

			if (getCastle().getOwnerId() > 0) {
				L2Clan clan = ClanTable.getInstance().getClan(getCastle().getOwnerId());
				sm = SystemMessage.getSystemMessage(SystemMessageId.CLAN_S1_IS_VICTORIOUS_OVER_S2_S_CASTLE_SIEGE);
				sm.addString(clan.getName());
				sm.addCastleId(getCastle().getResidenceId());
				Broadcast.toAllOnlinePlayers(sm);

				if (clan.getId() == _firstOwnerClanId) {
					// Owner is unchanged
					clan.increaseBloodAllianceCount();
				} else {
					getCastle().setTicketBuyCount(0);
					for (ClanMember member : clan.getMembers()) {
						if (member != null) {
							Player player = member.getPlayerInstance();
							if ((player != null) && player.isNoble()) {
								Hero.getInstance().setCastleTaken(player.getObjectId(), getCastle().getResidenceId());
							}
						}
					}
				}
			} else {
				sm = SystemMessage.getSystemMessage(SystemMessageId.THE_SIEGE_OF_S1_HAS_ENDED_IN_A_DRAW);
				sm.addCastleId(getCastle().getResidenceId());
				Broadcast.toAllOnlinePlayers(sm);
			}

			for (SiegeClan attackerClan : getAttackerClans()) {
				final L2Clan clan = ClanTable.getInstance().getClan(attackerClan.getClanId());
				if (clan == null) {
					continue;
				}

				for (Player member : clan.getOnlineMembers(0)) {
					member.checkItemRestriction();
				}

				clan.clearSiegeKills();
				clan.clearSiegeDeaths();
			}

			for (SiegeClan defenderClan : getDefenderClans()) {
				final L2Clan clan = ClanTable.getInstance().getClan(defenderClan.getClanId());
				if (clan == null) {
					continue;
				}

				for (Player member : clan.getOnlineMembers(0)) {
					member.checkItemRestriction();
				}

				clan.clearSiegeKills();
				clan.clearSiegeDeaths();
			}

			getCastle().updateClansReputation();
			removeFlags(); // Removes all flags. Note: Remove flag before teleporting players
			teleportPlayer(SiegeTeleportWhoType.NotOwner, TeleportWhereType.TOWN); // Teleport to the second closest town
			_isInProgress = false; // Flag so that siege instance can be started
			updatePlayerSiegeStateFlags(true);
			saveCastleSiege(); // Save castle specific data
			clearSiegeClan(); // Clear siege clan from db
			removeTowers(); // Remove all towers from this castle
			SiegeGuardManager.getInstance().unspawnSiegeGuard(getCastle()); // Remove all spawned siege guard from this castle
			if (getCastle().getOwnerId() > 0) {
				SiegeGuardManager.getInstance().removeSiegeGuards(getCastle());
			}
			getCastle().spawnDoor(); // Respawn door to castle
			getCastle().getZone().setIsActive(false);
			getCastle().getZone().updateZoneStatusForCharactersInside();
			getCastle().getZone().setSiegeInstance(null);

			// Notify to scripts.
			EventDispatcher.getInstance().notifyEventAsync(new OnCastleSiegeFinish(this), getCastle());
		}
	}

	private void removeDefender(SiegeClan sc) {
		if (sc != null) {
			getDefenderClans().remove(sc);
		}
	}

	private void removeAttacker(SiegeClan sc) {
		if (sc != null) {
			getAttackerClans().remove(sc);
		}
	}

	private void addDefender(SiegeClan sc, SiegeClanType type) {
		if (sc == null) {
			return;
		}
		sc.setType(type);
		getDefenderClans().add(sc);
	}

	private void addAttacker(SiegeClan sc) {
		if (sc == null) {
			return;
		}
		sc.setType(SiegeClanType.ATTACKER);
		getAttackerClans().add(sc);
	}

	/**
	 * When control of castle changed during siege<BR>
	 * <BR>
	 */
	public void midVictory() {
		if (isInProgress()) // Siege still in progress
		{
			if (getCastle().getOwnerId() > 0) {
				SiegeGuardManager.getInstance().removeSiegeGuards(getCastle()); // Remove all merc entry from db
			}

			if (getDefenderClans().isEmpty() && // If defender doesn't exist (Pc vs Npc)
					(getAttackerClans().size() == 1 // Only 1 attacker
					)) {
				SiegeClan sc_newowner = getAttackerClan(getCastle().getOwnerId());
				removeAttacker(sc_newowner);
				addDefender(sc_newowner, SiegeClanType.OWNER);
				endSiege();
				return;
			}
			if (getCastle().getOwnerId() > 0) {
				int allyId = ClanTable.getInstance().getClan(getCastle().getOwnerId()).getAllyId();
				if (getDefenderClans().isEmpty()) // If defender doesn't exist (Pc vs Npc)
				// and only an alliance attacks
				{
					// The player's clan is in an alliance
					if (allyId != 0) {
						boolean allinsamealliance = true;
						for (SiegeClan sc : getAttackerClans()) {
							if (sc != null) {
								if (ClanTable.getInstance().getClan(sc.getClanId()).getAllyId() != allyId) {
									allinsamealliance = false;
								}
							}
						}
						if (allinsamealliance) {
							SiegeClan sc_newowner = getAttackerClan(getCastle().getOwnerId());
							removeAttacker(sc_newowner);
							addDefender(sc_newowner, SiegeClanType.OWNER);
							endSiege();
							return;
						}
					}
				}

				for (SiegeClan sc : getDefenderClans()) {
					if (sc != null) {
						removeDefender(sc);
						addAttacker(sc);
					}
				}

				SiegeClan sc_newowner = getAttackerClan(getCastle().getOwnerId());
				removeAttacker(sc_newowner);
				addDefender(sc_newowner, SiegeClanType.OWNER);

				// The player's clan is in an alliance
				for (L2Clan clan : ClanTable.getInstance().getClanAllies(allyId)) {
					final SiegeClan sc = getAttackerClan(clan.getId());
					if (sc != null) {
						removeAttacker(sc);
						addDefender(sc, SiegeClanType.DEFENDER);
					}
				}
				teleportPlayer(SiegeTeleportWhoType.Attacker, TeleportWhereType.SIEGEFLAG); // Teleport to the second closest town
				teleportPlayer(SiegeTeleportWhoType.Spectator, TeleportWhereType.TOWN); // Teleport to the second closest town

				removeDefenderFlags(); // Removes defenders' flags
				getCastle().removeUpgrade(); // Remove all castle upgrade
				getCastle().spawnDoor(true); // Respawn door to castle but make them weaker (50% hp)
				removeTowers(); // Remove all towers from this castle
				_controlTowerCount = 0;// Each new siege midvictory CT are completely respawned.
				spawnControlTower();
				spawnFlameTower();
				updatePlayerSiegeStateFlags(false);

				// Notify to scripts.
				EventDispatcher.getInstance().notifyEventAsync(new OnCastleSiegeOwnerChange(this), getCastle());
			}
		}
	}

	/**
	 * When siege starts<BR>
	 * <BR>
	 */
	@Override
	public void startSiege() {
		if (!isInProgress()) {
			_firstOwnerClanId = getCastle().getOwnerId();

			if (getAttackerClans().isEmpty()) {
				SystemMessage sm;
				if (_firstOwnerClanId <= 0) {
					sm = SystemMessage.getSystemMessage(SystemMessageId.THE_SIEGE_OF_S1_HAS_BEEN_CANCELED_DUE_TO_LACK_OF_INTEREST);
				} else {
					sm = SystemMessage.getSystemMessage(SystemMessageId.S1_S_SIEGE_WAS_CANCELED_BECAUSE_THERE_WERE_NO_CLANS_THAT_PARTICIPATED);
					final L2Clan ownerClan = ClanTable.getInstance().getClan(_firstOwnerClanId);
					ownerClan.increaseBloodAllianceCount();
				}
				sm.addCastleId(getCastle().getResidenceId());
				Broadcast.toAllOnlinePlayers(sm);
				saveCastleSiege();
				return;
			}

			_isNormalSide = true; // Atk is now atk
			_isInProgress = true; // Flag so that same siege instance cannot be started again

			loadSiegeClan(); // Load siege clan from db
			updatePlayerSiegeStateFlags(false);
			teleportPlayer(SiegeTeleportWhoType.NotOwner, TeleportWhereType.TOWN); // Teleport to the closest town
			_controlTowerCount = 0;
			spawnControlTower(); // Spawn control tower
			spawnFlameTower(); // Spawn control tower
			getCastle().spawnDoor(); // Spawn door
			spawnSiegeGuard(); // Spawn siege guard
			SiegeGuardManager.getInstance().deleteTickets(getCastle().getResidenceId()); // remove the tickets from the ground
			getCastle().getZone().setSiegeInstance(this);
			getCastle().getZone().setIsActive(true);
			getCastle().getZone().updateZoneStatusForCharactersInside();

			// Schedule a task to prepare auto siege end
			_siegeEndDate = Calendar.getInstance();
			_siegeEndDate.add(Calendar.MINUTE, SiegeManager.getInstance().getSiegeLength());
			ThreadPool.getInstance().scheduleGeneral(new ScheduleEndSiegeTask(getCastle()), 1000, TimeUnit.MILLISECONDS); // Prepare auto end task

			SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.THE_S1_SIEGE_HAS_STARTED);
			sm.addCastleId(getCastle().getResidenceId());
			Broadcast.toAllOnlinePlayers(sm);

			// Notify to scripts.
			EventDispatcher.getInstance().notifyEventAsync(new OnCastleSiegeStart(this), getCastle());
		}
	}

	/**
	 * Announce to player.<BR>
	 * <BR>
	 *
	 * @param message   The SystemMessage to send to player
	 * @param bothSides True - broadcast to both attackers and defenders. False - only to defenders.
	 */
	public void announceToPlayer(SystemMessage message, boolean bothSides) {
		for (SiegeClan siegeClans : getDefenderClans()) {
			L2Clan clan = ClanTable.getInstance().getClan(siegeClans.getClanId());
			if (clan != null) {
				clan.getOnlineMembers(0).forEach(message::sendTo);
			}
		}

		if (bothSides) {
			for (SiegeClan siegeClans : getAttackerClans()) {
				L2Clan clan = ClanTable.getInstance().getClan(siegeClans.getClanId());
				if (clan != null) {
					clan.getOnlineMembers(0).forEach(message::sendTo);
				}
			}
		}
	}

	public void updatePlayerSiegeStateFlags(boolean clear) {
		L2Clan clan;
		for (SiegeClan siegeclan : getAttackerClans()) {
			if (siegeclan == null) {
				continue;
			}

			clan = ClanTable.getInstance().getClan(siegeclan.getClanId());
			for (Player member : clan.getOnlineMembers(0)) {
				if (clear) {
					member.setSiegeState((byte) 0);
					member.setSiegeSide(0);
					member.setIsInSiege(false);
					member.stopFameTask();
				} else {
					member.setSiegeState((byte) 1);
					member.setSiegeSide(getCastle().getResidenceId());
					if (checkIfInZone(member)) {
						member.setIsInSiege(true);
						member.startFameTask(PlayerConfig.CASTLE_ZONE_FAME_TASK_FREQUENCY * 1000, PlayerConfig.CASTLE_ZONE_FAME_AQUIRE_POINTS);
					}
				}
				member.sendPacket(new UserInfo(member));
				member.broadcastRelationChanged();
			}
		}
		for (SiegeClan siegeclan : getDefenderClans()) {
			if (siegeclan == null) {
				continue;
			}

			clan = ClanTable.getInstance().getClan(siegeclan.getClanId());
			for (Player member : clan.getOnlineMembers(0)) {
				if (clear) {
					member.setSiegeState((byte) 0);
					member.setSiegeSide(0);
					member.setIsInSiege(false);
					member.stopFameTask();
				} else {
					member.setSiegeState((byte) 2);
					member.setSiegeSide(getCastle().getResidenceId());
					if (checkIfInZone(member)) {
						member.setIsInSiege(true);
						member.startFameTask(PlayerConfig.CASTLE_ZONE_FAME_TASK_FREQUENCY * 1000, PlayerConfig.CASTLE_ZONE_FAME_AQUIRE_POINTS);
					}
				}

				member.sendPacket(new UserInfo(member));
				member.broadcastRelationChanged();
			}
		}
	}

	/**
	 * Approve clan as defender for siege<BR>
	 * <BR>
	 *
	 * @param clanId The int of player's clan id
	 */
	public void approveSiegeDefenderClan(int clanId) {
		if (clanId <= 0) {
			return;
		}
		saveSiegeClan(ClanTable.getInstance().getClan(clanId), DEFENDER, true);
		loadSiegeClan();
	}

	/**
	 * @param object
	 * @return true if object is inside the zone
	 */
	public boolean checkIfInZone(WorldObject object) {
		return checkIfInZone(object.getX(), object.getY(), object.getZ());
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 * @return true if object is inside the zone
	 */
	public boolean checkIfInZone(double x, double y, double z) {
		return (isInProgress() && (getCastle().checkIfInZone(x, y, z))); // Castle zone during siege
	}

	/**
	 * Return true if clan is attacker<BR>
	 * <BR>
	 *
	 * @param clan The L2Clan of the player
	 */
	@Override
	public boolean checkIsAttacker(L2Clan clan) {
		return (getAttackerClan(clan) != null);
	}

	/**
	 * Return true if clan is defender<BR>
	 * <BR>
	 *
	 * @param clan The L2Clan of the player
	 */
	@Override
	public boolean checkIsDefender(L2Clan clan) {
		return (getDefenderClan(clan) != null);
	}

	/**
	 * @param clan The L2Clan of the player
	 * @return true if clan is defender waiting approval
	 */
	public boolean checkIsDefenderWaiting(L2Clan clan) {
		return (getDefenderWaitingClan(clan) != null);
	}

	/**
	 * Clear all registered siege clans from database for castle
	 */
	public void clearSiegeClan() {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement("DELETE FROM siege_clans WHERE castle_id=?")) {
			statement.setInt(1, getCastle().getResidenceId());
			statement.execute();

			if (getCastle().getOwnerId() > 0) {
				try (PreparedStatement delete = con.prepareStatement("DELETE FROM siege_clans WHERE clan_id=?")) {
					delete.setInt(1, getCastle().getOwnerId());
					delete.execute();
				}
			}

			getAttackerClans().clear();
			getDefenderClans().clear();
			getDefenderWaitingClans().clear();
		} catch (Exception e) {
			LOGGER.warn("Error while clearing siege clans for castle: {}", _castle, e);
		}
	}

	/**
	 * Clear all siege clans waiting for approval from database for castle
	 */
	public void clearSiegeWaitingClan() {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement("DELETE FROM siege_clans WHERE castle_id=? and type = 2")) {
			statement.setInt(1, getCastle().getResidenceId());
			statement.execute();

			getDefenderWaitingClans().clear();
		} catch (Exception e) {
			LOGGER.warn("Error while clearing siege waiting clans for castle: {}", _castle, e);
		}
	}

	/**
	 * Return list of L2PcInstance registered as attacker in the zone.
	 */
	@Override
	public List<Player> getAttackersInZone() {
		//@formatter:off
		return getAttackerClans().stream()
				.map(siegeclan -> ClanTable.getInstance().getClan(siegeclan.getClanId()))
				.filter(Objects::nonNull)
				.flatMap(clan -> clan.getOnlineMembers(0).stream())
				.filter(Player::isInSiege)
				.collect(Collectors.toList());
		//@formatter:on
	}

	/**
	 * @return list of L2PcInstance in the zone.
	 */
	public List<Player> getPlayersInZone() {
		return getCastle().getZone().getPlayersInside();
	}

	/**
	 * @return list of L2PcInstance owning the castle in the zone.
	 */
	public List<Player> getOwnersInZone() {
		//@formatter:off
		return getDefenderClans().stream()
				.filter(siegeclan -> siegeclan.getClanId() == _castle.getOwnerId())
				.map(siegeclan -> ClanTable.getInstance().getClan(siegeclan.getClanId()))
				.filter(Objects::nonNull)
				.flatMap(clan -> clan.getOnlineMembers(0).stream())
				.filter(Player::isInSiege)
				.collect(Collectors.toList());
		//@formatter:on
	}

	/**
	 * @return list of L2PcInstance not registered as attacker or defender in the zone.
	 */
	public List<Player> getSpectatorsInZone() {
		return getCastle().getZone().getPlayersInside().stream().filter(p -> !p.isInSiege()).collect(Collectors.toList());
	}

	/**
	 * Control Tower was killed
	 *
	 * @param ct
	 */
	public void killedCT(Npc ct) {
		_controlTowerCount = Math.max(_controlTowerCount - 1, 0);
	}

	/**
	 * Remove the flag that was killed
	 *
	 * @param flag
	 */
	public void killedFlag(Npc flag) {
		getAttackerClans().forEach(siegeClan -> siegeClan.removeFlag(flag));
	}

	/**
	 * Display list of registered clans
	 *
	 * @param player
	 */
	public void listRegisterClan(Player player) {
		player.sendPacket(new SiegeInfo(getCastle(), player));
	}

	/**
	 * Register clan as attacker<BR>
	 * <BR>
	 *
	 * @param player The L2PcInstance of the player trying to register
	 */
	public void registerAttacker(Player player) {
		registerAttacker(player, false);
	}

	public void registerAttacker(Player player, boolean force) {
		if (player.getClan() == null) {
			return;
		}
		int allyId = 0;
		if (getCastle().getOwnerId() != 0) {
			allyId = ClanTable.getInstance().getClan(getCastle().getOwnerId()).getAllyId();
		}
		if (allyId != 0) {
			if ((player.getClan().getAllyId() == allyId) && !force) {
				player.sendPacket(SystemMessageId.YOU_CANNOT_REGISTER_AS_AN_ATTACKER_BECAUSE_YOU_ARE_IN_AN_ALLIANCE_WITH_THE_CASTLE_OWNING_CLAN);
				return;
			}
		}

		if (force) {
			if (SiegeManager.getInstance().checkIsRegistered(player.getClan(), getCastle().getResidenceId())) {
				player.sendPacket(SystemMessageId.YOU_HAVE_ALREADY_REQUESTED_A_CASTLE_SIEGE);
			} else {
				saveSiegeClan(player.getClan(), ATTACKER, false); // Save to database
			}
			return;
		}

		if (checkIfCanRegister(player, ATTACKER)) {
			saveSiegeClan(player.getClan(), ATTACKER, false); // Save to database
		}
	}

	/**
	 * Register a clan as defender.
	 *
	 * @param player the player to register
	 */
	public void registerDefender(Player player) {
		registerDefender(player, false);
	}

	public void registerDefender(Player player, boolean force) {
		if (getCastle().getOwnerId() <= 0) {
			player.sendMessage("You cannot register as a defender because " + getCastle().getName() + " is owned by NPC.");
			return;
		}

		if (force) {
			if (SiegeManager.getInstance().checkIsRegistered(player.getClan(), getCastle().getResidenceId())) {
				player.sendPacket(SystemMessageId.YOU_HAVE_ALREADY_REQUESTED_A_CASTLE_SIEGE);
			} else {
				saveSiegeClan(player.getClan(), DEFENDER_NOT_APPROVED, false); // Save to database
			}
			return;
		}

		if (checkIfCanRegister(player, DEFENDER_NOT_APPROVED)) {
			saveSiegeClan(player.getClan(), DEFENDER_NOT_APPROVED, false); // Save to database
		}
	}

	/**
	 * Remove clan from siege<BR>
	 * <BR>
	 *
	 * @param clanId The int of player's clan id
	 */
	public void removeSiegeClan(int clanId) {
		if (clanId <= 0) {
			return;
		}

		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement("DELETE FROM siege_clans WHERE castle_id=? and clan_id=?")) {
			statement.setInt(1, getCastle().getResidenceId());
			statement.setInt(2, clanId);
			statement.execute();

			loadSiegeClan();
		} catch (Exception e) {
			LOGGER.warn("Error while removing siege clans for castle: {}", _castle, e);
		}
	}

	/**
	 * Remove clan from siege<BR>
	 * <BR>
	 *
	 * @param clan clan being removed
	 */
	public void removeSiegeClan(L2Clan clan) {
		if ((clan == null) || (clan.getCastleId() == getCastle().getResidenceId()) || !SiegeManager.getInstance().checkIsRegistered(clan, getCastle().getResidenceId())) {
			return;
		}
		removeSiegeClan(clan.getId());
	}

	/**
	 * Remove clan from siege<BR>
	 * <BR>
	 *
	 * @param player The L2PcInstance of player/clan being removed
	 */
	public void removeSiegeClan(Player player) {
		removeSiegeClan(player.getClan());
	}

	/**
	 * Start the auto tasks<BR>
	 * <BR>
	 */
	public void startAutoTask() {
		correctSiegeDateTime();

		LOGGER.info("Siege of {}: {}", _castle.getName(), _castle.getSiegeDate().getTime());

		loadSiegeClan();

		// Schedule siege auto start
		if (_scheduledStartSiegeTask != null) {
			_scheduledStartSiegeTask.cancel(false);
		}
		_scheduledStartSiegeTask = ThreadPool.getInstance().scheduleGeneral(new Siege.ScheduleStartSiegeTask(getCastle()), 1000, TimeUnit.MILLISECONDS);
	}

	/**
	 * Teleport players
	 *
	 * @param teleportWho
	 * @param teleportWhere
	 */
	public void teleportPlayer(SiegeTeleportWhoType teleportWho, TeleportWhereType teleportWhere) {
		final List<Player> players;
		switch (teleportWho) {
			case Owner: {
				players = getOwnersInZone();
				break;
			}
			case NotOwner: {
				players = getPlayersInZone();
				final Iterator<Player> it = players.iterator();
				while (it.hasNext()) {
					final Player player = it.next();
					if ((player == null) || player.inObserverMode() || ((player.getClanId() > 0) && (player.getClanId() == getCastle().getOwnerId()))) {
						it.remove();
					}
				}
				break;
			}
			case Attacker: {
				players = getAttackersInZone();
				break;
			}
			case Spectator: {
				players = getSpectatorsInZone();
				break;
			}
			default: {
				players = Collections.emptyList();
			}
		}

		for (Player player : players) {
			if (player.canOverrideCond(PcCondOverride.CASTLE_CONDITIONS) || player.isJailed()) {
				continue;
			}
			player.teleToLocation(teleportWhere);
		}
	}

	/**
	 * Add clan as attacker<BR>
	 * <BR>
	 *
	 * @param clanId The int of clan's id
	 */
	private void addAttacker(int clanId) {
		getAttackerClans().add(new SiegeClan(clanId, SiegeClanType.ATTACKER)); // Add registered attacker to attacker list
	}

	/**
	 * Add clan as defender<BR>
	 * <BR>
	 *
	 * @param clanId The int of clan's id
	 */
	private void addDefender(int clanId) {
		getDefenderClans().add(new SiegeClan(clanId, SiegeClanType.DEFENDER)); // Add registered defender to defender list
	}

	/**
	 * <p>
	 * Add clan as defender with the specified type
	 * </p>
	 *
	 * @param clanId The int of clan's id
	 * @param type   the type of the clan
	 */
	private void addDefender(int clanId, SiegeClanType type) {
		getDefenderClans().add(new SiegeClan(clanId, type));
	}

	/**
	 * Add clan as defender waiting approval<BR>
	 * <BR>
	 *
	 * @param clanId The int of clan's id
	 */
	private void addDefenderWaiting(int clanId) {
		getDefenderWaitingClans().add(new SiegeClan(clanId, SiegeClanType.DEFENDER_PENDING)); // Add registered defender to defender list
	}

	/**
	 * @param player The L2PcInstance of the player trying to register
	 * @param typeId -1 = owner 0 = defender, 1 = attacker, 2 = defender waiting
	 * @return true if the player can register.
	 */
	private boolean checkIfCanRegister(Player player, byte typeId) {
		if (getIsRegistrationOver()) {
			SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.THE_DEADLINE_TO_REGISTER_FOR_THE_SIEGE_OF_S1_HAS_PASSED);
			sm.addCastleId(getCastle().getResidenceId());
			player.sendPacket(sm);
		} else if (isInProgress()) {
			player.sendPacket(SystemMessageId.THIS_IS_NOT_THE_TIME_FOR_SIEGE_REGISTRATION_AND_SO_REGISTRATION_AND_CANCELLATION_CANNOT_BE_DONE);
		} else if ((player.getClan() == null) || (player.getClan().getLevel() < SiegeManager.getInstance().getSiegeClanMinLevel())) {
			player.sendPacket(SystemMessageId.ONLY_CLANS_OF_LEVEL_5_OR_ABOVE_MAY_REGISTER_FOR_A_CASTLE_SIEGE);
		} else if (player.getClan().getId() == getCastle().getOwnerId()) {
			player.sendPacket(SystemMessageId.CASTLE_OWNING_CLANS_ARE_AUTOMATICALLY_REGISTERED_ON_THE_DEFENDING_SIDE);
		} else if (player.getClan().getCastleId() > 0) {
			player.sendPacket(SystemMessageId.A_CLAN_THAT_OWNS_A_CASTLE_CANNOT_PARTICIPATE_IN_ANOTHER_SIEGE);
		} else if (SiegeManager.getInstance().checkIsRegistered(player.getClan(), getCastle().getResidenceId())) {
			player.sendPacket(SystemMessageId.YOU_HAVE_ALREADY_REQUESTED_A_CASTLE_SIEGE);
		} else if (checkIfAlreadyRegisteredForSameDay(player.getClan())) {
			player.sendPacket(SystemMessageId.YOUR_APPLICATION_HAS_BEEN_DENIED_BECAUSE_YOU_HAVE_ALREADY_SUBMITTED_A_REQUEST_FOR_ANOTHER_CASTLE_SIEGE);
		} else if ((typeId == ATTACKER) && (getAttackerClans().size() >= SiegeManager.getInstance().getAttackerMaxClans())) {
			player.sendPacket(SystemMessageId.NO_MORE_REGISTRATIONS_MAY_BE_ACCEPTED_FOR_THE_ATTACKER_SIDE);
		} else if (((typeId == DEFENDER) || (typeId == DEFENDER_NOT_APPROVED) || (typeId == OWNER)) && ((getDefenderClans().size() + getDefenderWaitingClans().size()) >= SiegeManager.getInstance().getDefenderMaxClans())) {
			player.sendPacket(SystemMessageId.NO_MORE_REGISTRATIONS_MAY_BE_ACCEPTED_FOR_THE_DEFENDER_SIDE);
		} else {
			return true;
		}

		return false;
	}

	/**
	 * @param clan The L2Clan of the player trying to register
	 * @return true if the clan has already registered to a siege for the same day.
	 */
	public boolean checkIfAlreadyRegisteredForSameDay(L2Clan clan) {
		for (Siege siege : SiegeManager.getInstance().getSieges()) {
			if (siege == this) {
				continue;
			}
			if (siege.getSiegeDate().get(Calendar.DAY_OF_WEEK) == getSiegeDate().get(Calendar.DAY_OF_WEEK)) {
				if (siege.checkIsAttacker(clan)) {
					return true;
				}
				if (siege.checkIsDefender(clan)) {
					return true;
				}
				if (siege.checkIsDefenderWaiting(clan)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Return the correct siege date as Calendar.<BR>
	 * <BR>
	 */
	public void correctSiegeDateTime() {
		boolean corrected = false;

		if (getCastle().getSiegeDate().getTimeInMillis() < Calendar.getInstance().getTimeInMillis()) {
			// Since siege has past reschedule it to the next one
			// This is usually caused by server being down
			corrected = true;
			setNextSiegeDate();
		}

		if (corrected) {
			saveSiegeDate();
		}
	}

	/**
	 * Load siege clans.
	 */
	private void loadSiegeClan() {

		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement("SELECT clan_id,type FROM siege_clans where castle_id=?")) {
			getAttackerClans().clear();
			getDefenderClans().clear();
			getDefenderWaitingClans().clear();

			// Add castle owner as defender (add owner first so that they are on the top of the defender list)
			if (getCastle().getOwnerId() > 0) {
				addDefender(getCastle().getOwnerId(), SiegeClanType.OWNER);
			}

			statement.setInt(1, getCastle().getResidenceId());
			try (ResultSet rs = statement.executeQuery()) {
				int typeId;
				while (rs.next()) {
					typeId = rs.getInt("type");
					if (typeId == DEFENDER) {
						addDefender(rs.getInt("clan_id"));
					} else if (typeId == ATTACKER) {
						addAttacker(rs.getInt("clan_id"));
					} else if (typeId == DEFENDER_NOT_APPROVED) {
						addDefenderWaiting(rs.getInt("clan_id"));
					}
				}
			}
		} catch (Exception e) {
			LOGGER.warn("Error while loading siege clans for castle: {}", _castle, e);
		}
	}

	/**
	 * Remove all spawned towers.
	 */
	private void removeTowers() {
		for (L2FlameTowerInstance ct : _flameTowers) {
			ct.deleteMe();
		}

		for (L2ControlTowerInstance ct : _controlTowers) {
			ct.deleteMe();
		}

		_flameTowers.clear();
		_controlTowers.clear();
	}

	/**
	 * Remove all flags.
	 */
	private void removeFlags() {
		for (SiegeClan sc : getAttackerClans()) {
			if (sc != null) {
				sc.removeFlags();
			}
		}
		for (SiegeClan sc : getDefenderClans()) {
			if (sc != null) {
				sc.removeFlags();
			}
		}
	}

	/**
	 * Remove flags from defenders.
	 */
	private void removeDefenderFlags() {
		for (SiegeClan sc : getDefenderClans()) {
			if (sc != null) {
				sc.removeFlags();
			}
		}
	}

	/**
	 * Save castle siege related to database.
	 */
	private void saveCastleSiege() {
		setNextSiegeDate(); // Set the next set date for 2 weeks from now
		// Schedule Time registration end
		getTimeRegistrationOverDate().setTimeInMillis(Calendar.getInstance().getTimeInMillis());
		getTimeRegistrationOverDate().add(Calendar.DAY_OF_MONTH, 1);
		getCastle().setIsTimeRegistrationOver(false);

		saveSiegeDate(); // Save the new date
		startAutoTask(); // Prepare auto start siege and end registration
	}

	/**
	 * Save siege date to database.
	 */
	public void saveSiegeDate() {
		if (_scheduledStartSiegeTask != null) {
			_scheduledStartSiegeTask.cancel(true);
			_scheduledStartSiegeTask = ThreadPool.getInstance().scheduleGeneral(new Siege.ScheduleStartSiegeTask(getCastle()), 1000, TimeUnit.MILLISECONDS);
		}

		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement("UPDATE castle SET siegeDate = ?, regTimeEnd = ?, regTimeOver = ?  WHERE id = ?")) {
			statement.setLong(1, getSiegeDate().getTimeInMillis());
			statement.setLong(2, getTimeRegistrationOverDate().getTimeInMillis());
			statement.setString(3, String.valueOf(getIsTimeRegistrationOver()));
			statement.setInt(4, getCastle().getResidenceId());
			statement.execute();
		} catch (Exception e) {
			LOGGER.warn("Error while saving siege date for castle: {}", _castle, e);
		}
	}

	/**
	 * Save registration to database.<BR>
	 * <BR>
	 *
	 * @param clan                 The L2Clan of player
	 * @param typeId               -1 = owner 0 = defender, 1 = attacker, 2 = defender waiting
	 * @param isUpdateRegistration
	 */
	private void saveSiegeClan(L2Clan clan, byte typeId, boolean isUpdateRegistration) {
		if (clan.getCastleId() > 0) {
			return;
		}

		try (Connection con = DatabaseFactory.getInstance().getConnection()) {
			if ((typeId == DEFENDER) || (typeId == DEFENDER_NOT_APPROVED) || (typeId == OWNER)) {
				if ((getDefenderClans().size() + getDefenderWaitingClans().size()) >= SiegeManager.getInstance().getDefenderMaxClans()) {
					return;
				}
			} else {
				if (getAttackerClans().size() >= SiegeManager.getInstance().getAttackerMaxClans()) {
					return;
				}
			}

			if (!isUpdateRegistration) {
				try (PreparedStatement statement = con.prepareStatement("INSERT INTO siege_clans (clan_id,castle_id,type,castle_owner) values (?,?,?,0)")) {
					statement.setInt(1, clan.getId());
					statement.setInt(2, getCastle().getResidenceId());
					statement.setInt(3, typeId);
					statement.execute();
				}
			} else {
				try (PreparedStatement statement = con.prepareStatement("UPDATE siege_clans SET type = ? WHERE castle_id = ? AND clan_id = ?")) {
					statement.setInt(1, typeId);
					statement.setInt(2, getCastle().getResidenceId());
					statement.setInt(3, clan.getId());
					statement.execute();
				}
			}

			if ((typeId == DEFENDER) || (typeId == OWNER)) {
				addDefender(clan.getId());
			} else if (typeId == ATTACKER) {
				addAttacker(clan.getId());
			} else if (typeId == DEFENDER_NOT_APPROVED) {
				addDefenderWaiting(clan.getId());
			}
		} catch (Exception e) {
			LOGGER.warn("Error while saving siege clans for castle: {}", _castle, e);
		}
	}

	/**
	 * Set the date for the next siege.
	 */
	private void setNextSiegeDate() {
		final Calendar cal = getCastle().getSiegeDate();
		if (cal.getTimeInMillis() < System.currentTimeMillis()) {
			cal.setTimeInMillis(System.currentTimeMillis());
		}

		for (SiegeScheduleDate holder : SiegeScheduleData.getInstance().getScheduleDates()) {
			cal.set(Calendar.DAY_OF_WEEK, holder.getDay());
			cal.set(Calendar.HOUR_OF_DAY, holder.getHour());
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			if (cal.before(Calendar.getInstance())) {
				cal.add(Calendar.WEEK_OF_YEAR, 2);
			}

			if (CastleManager.getInstance().getSiegeDates(cal.getTimeInMillis()) < holder.getMaxConcurrent()) {
				CastleManager.getInstance().registerSiegeDate(getCastle().getResidenceId(), cal.getTimeInMillis());
				break;
			}
		}

		SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_HAS_ANNOUNCED_THE_NEXT_CASTLE_SIEGE_TIME);
		sm.addCastleId(getCastle().getResidenceId());
		Broadcast.toAllOnlinePlayers(sm);

		_isRegistrationOver = false; // Allow registration for next siege
	}

	/**
	 * Spawn control tower.
	 */
	private void spawnControlTower() {
		try {
			for (TowerSpawn ts : SiegeManager.getInstance().getControlTowers(getCastle().getResidenceId())) {
				final L2Spawn spawn = new L2Spawn(ts.getId());
				spawn.setLocation(ts.getLocation());
				_controlTowers.add((L2ControlTowerInstance) spawn.doSpawn());
			}
		} catch (Exception e) {
			LOGGER.warn("Error while spawning control towers for castle: {}", _castle, e);
		}
		_controlTowerCount = _controlTowers.size();
	}

	/**
	 * Spawn flame tower.
	 */
	private void spawnFlameTower() {
		try {
			for (TowerSpawn ts : SiegeManager.getInstance().getFlameTowers(getCastle().getResidenceId())) {
				final L2Spawn spawn = new L2Spawn(ts.getId());
				spawn.setLocation(ts.getLocation());
				final L2FlameTowerInstance tower = (L2FlameTowerInstance) spawn.doSpawn();
				tower.setUpgradeLevel(ts.getUpgradeLevel());
				tower.setZoneList(ts.getZoneList());
				_flameTowers.add(tower);
			}
		} catch (Exception e) {
			LOGGER.warn("Error while spawning flame towers for castle: {}", _castle, e);
		}
	}

	/**
	 * Spawn siege guard.
	 */
	private void spawnSiegeGuard() {
		SiegeGuardManager.getInstance().spawnSiegeGuard(getCastle());

		// Register guard to the closest Control Tower
		// When CT dies, so do all the guards that it controls
		final Set<L2Spawn> spawned = SiegeGuardManager.getInstance().getSpawnedGuards(getCastle().getResidenceId());
		if (!spawned.isEmpty()) {
			L2ControlTowerInstance closestCt;
			double distance;
			double distanceClosest = 0;
			for (L2Spawn spawn : spawned) {
				if (spawn == null) {
					continue;
				}

				closestCt = null;
				distanceClosest = Integer.MAX_VALUE;

				for (L2ControlTowerInstance ct : _controlTowers) {
					if (ct == null) {
						continue;
					}

					distance = ct.distance3d(spawn);

					if (distance < distanceClosest) {
						closestCt = ct;
						distanceClosest = distance;
					}
				}
				if (closestCt != null) {
					closestCt.registerGuard(spawn);
				}
			}
		}
	}

	@Override
	public final SiegeClan getAttackerClan(L2Clan clan) {
		if (clan == null) {
			return null;
		}
		return getAttackerClan(clan.getId());
	}

	@Override
	public final SiegeClan getAttackerClan(int clanId) {
		for (SiegeClan sc : getAttackerClans()) {
			if ((sc != null) && (sc.getClanId() == clanId)) {
				return sc;
			}
		}
		return null;
	}

	@Override
	public final List<SiegeClan> getAttackerClans() {
		if (_isNormalSide) {
			return _attackerClans;
		}
		return _defenderClans;
	}

	public final int getAttackerRespawnDelay() {
		return (SiegeManager.getInstance().getAttackerRespawnDelay());
	}

	public final Castle getCastle() {
		if (_castle == null) {
			return null;
		}
		return _castle;
	}

	@Override
	public final SiegeClan getDefenderClan(L2Clan clan) {
		if (clan == null) {
			return null;
		}
		return getDefenderClan(clan.getId());
	}

	@Override
	public final SiegeClan getDefenderClan(int clanId) {
		for (SiegeClan sc : getDefenderClans()) {
			if ((sc != null) && (sc.getClanId() == clanId)) {
				return sc;
			}
		}
		return null;
	}

	@Override
	public final List<SiegeClan> getDefenderClans() {
		if (_isNormalSide) {
			return _defenderClans;
		}
		return _attackerClans;
	}

	public final SiegeClan getDefenderWaitingClan(L2Clan clan) {
		if (clan == null) {
			return null;
		}
		return getDefenderWaitingClan(clan.getId());
	}

	public final SiegeClan getDefenderWaitingClan(int clanId) {
		for (SiegeClan sc : getDefenderWaitingClans()) {
			if ((sc != null) && (sc.getClanId() == clanId)) {
				return sc;
			}
		}
		return null;
	}

	public final List<SiegeClan> getDefenderWaitingClans() {
		return _defenderWaitingClans;
	}

	public final boolean isInProgress() {
		return _isInProgress;
	}

	public final boolean getIsRegistrationOver() {
		return _isRegistrationOver;
	}

	public final boolean getIsTimeRegistrationOver() {
		return getCastle().getIsTimeRegistrationOver();
	}

	@Override
	public final Calendar getSiegeDate() {
		return getCastle().getSiegeDate();
	}

	public final Calendar getTimeRegistrationOverDate() {
		return getCastle().getTimeRegistrationOverDate();
	}

	public void endTimeRegistration(boolean automatic) {
		getCastle().setIsTimeRegistrationOver(true);
		if (!automatic) {
			saveSiegeDate();
		}
	}

	@Override
	public Set<Npc> getFlag(L2Clan clan) {
		if (clan != null) {
			SiegeClan sc = getAttackerClan(clan);
			if (sc != null) {
				return sc.getFlag();
			}
		}
		return null;
	}

	public int getControlTowerCount() {
		return _controlTowerCount;
	}

	@Override
	public boolean giveFame() {
		return true;
	}

	@Override
	public int getFameFrequency() {
		return PlayerConfig.CASTLE_ZONE_FAME_TASK_FREQUENCY;
	}

	@Override
	public int getFameAmount() {
		return PlayerConfig.CASTLE_ZONE_FAME_AQUIRE_POINTS;
	}

	@Override
	public void updateSiege() {
	}
}
