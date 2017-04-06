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
import org.l2junity.commons.sql.DatabaseFactory;
import org.l2junity.core.configs.L2JModsConfig;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.GameServer;
import org.l2junity.gameserver.enums.PrivateStoreType;
import org.l2junity.gameserver.model.TradeItem;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.holders.SellBuffHolder;
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.network.Disconnection;
import org.l2junity.gameserver.network.GameClient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@Slf4j
@StartupComponent("Service")
public class OfflineTradersTable {
	@Getter(lazy = true)
	private static final OfflineTradersTable instance = new OfflineTradersTable();

	// SQL DEFINITIONS
	private static final String SAVE_OFFLINE_STATUS = "INSERT INTO character_offline_trade (`charId`,`time`,`type`,`title`) VALUES (?,?,?,?)";
	private static final String SAVE_ITEMS = "INSERT INTO character_offline_trade_items (`charId`,`item`,`count`,`price`) VALUES (?,?,?,?)";
	private static final String CLEAR_OFFLINE_TABLE = "DELETE FROM character_offline_trade";
	private static final String CLEAR_OFFLINE_TABLE_ITEMS = "DELETE FROM character_offline_trade_items";
	private static final String LOAD_OFFLINE_STATUS = "SELECT * FROM character_offline_trade";
	private static final String LOAD_OFFLINE_ITEMS = "SELECT * FROM character_offline_trade_items WHERE charId = ?";

	private OfflineTradersTable() {
		if ((L2JModsConfig.OFFLINE_TRADE_ENABLE || L2JModsConfig.OFFLINE_CRAFT_ENABLE) && L2JModsConfig.RESTORE_OFFLINERS) {
			restoreOfflineTraders();
		}
	}

	public void storeOffliners() {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement stm1 = con.prepareStatement(CLEAR_OFFLINE_TABLE);
			 PreparedStatement stm2 = con.prepareStatement(CLEAR_OFFLINE_TABLE_ITEMS);
			 PreparedStatement stm3 = con.prepareStatement(SAVE_OFFLINE_STATUS);
			 PreparedStatement stm_items = con.prepareStatement(SAVE_ITEMS)) {
			stm1.execute();
			stm2.execute();
			con.setAutoCommit(false); // avoid halfway done

			for (Player pc : WorldManager.getInstance().getAllPlayers()) {
				try {
					if ((pc.getPrivateStoreType() != PrivateStoreType.NONE) && ((pc.getClient() == null) || pc.getClient().isDetached())) {
						stm3.setInt(1, pc.getObjectId()); // Char Id
						stm3.setLong(2, pc.getOfflineStartTime());
						stm3.setInt(3, pc.isSellingBuffs() ? 9 : pc.getPrivateStoreType().getId()); // store type
						String title = null;

						switch (pc.getPrivateStoreType()) {
							case BUY:
								if (!L2JModsConfig.OFFLINE_TRADE_ENABLE) {
									continue;
								}
								title = pc.getBuyList().getTitle();
								for (TradeItem i : pc.getBuyList().getItems()) {
									stm_items.setInt(1, pc.getObjectId());
									stm_items.setInt(2, i.getItem().getId());
									stm_items.setLong(3, i.getCount());
									stm_items.setLong(4, i.getPrice());
									stm_items.executeUpdate();
									stm_items.clearParameters();
								}
								break;
							case SELL:
							case PACKAGE_SELL:
								if (!L2JModsConfig.OFFLINE_TRADE_ENABLE) {
									continue;
								}
								title = pc.getSellList().getTitle();
								if (pc.isSellingBuffs()) {
									for (SellBuffHolder holder : pc.getSellingBuffs()) {
										stm_items.setInt(1, pc.getObjectId());
										stm_items.setInt(2, holder.getSkillId());
										stm_items.setLong(3, 0);
										stm_items.setLong(4, holder.getPrice());
										stm_items.executeUpdate();
										stm_items.clearParameters();
									}
								} else {
									for (TradeItem i : pc.getSellList().getItems()) {
										stm_items.setInt(1, pc.getObjectId());
										stm_items.setInt(2, i.getObjectId());
										stm_items.setLong(3, i.getCount());
										stm_items.setLong(4, i.getPrice());
										stm_items.executeUpdate();
										stm_items.clearParameters();
									}
								}
								break;
							case MANUFACTURE:
								if (!L2JModsConfig.OFFLINE_CRAFT_ENABLE) {
									continue;
								}
								title = pc.getStoreName();
								for (Entry<Integer, Long> entry : pc.getManufactureItems().entrySet()) {
									stm_items.setInt(1, pc.getObjectId());
									stm_items.setInt(2, entry.getKey());
									stm_items.setLong(3, 0);
									stm_items.setLong(4, entry.getValue());
									stm_items.executeUpdate();
									stm_items.clearParameters();
								}
						}
						stm3.setString(4, title);
						stm3.executeUpdate();
						stm3.clearParameters();
						con.commit(); // flush
					}
				} catch (Exception e) {
					log.warn("Error while saving offline trader: {} ", pc, e);
				}
			}
			log.info("Offline traders stored.");
		} catch (Exception e) {
			log.warn("Error while saving offline traders: {}", e);
		}
	}

	public void restoreOfflineTraders() {
		log.info("Loading offline traders...");
		int nTraders = 0;
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 Statement stm = con.createStatement();
			 ResultSet rs = stm.executeQuery(LOAD_OFFLINE_STATUS)) {
			while (rs.next()) {
				long time = rs.getLong("time");
				if (L2JModsConfig.OFFLINE_MAX_DAYS > 0) {
					Calendar cal = Calendar.getInstance();
					cal.setTimeInMillis(time);
					cal.add(Calendar.DAY_OF_YEAR, L2JModsConfig.OFFLINE_MAX_DAYS);
					if (cal.getTimeInMillis() <= System.currentTimeMillis()) {
						continue;
					}
				}

				final int typeId = rs.getInt("type");
				boolean isSellBuff = false;

				if (typeId == 9) {
					isSellBuff = true;
				}

				final PrivateStoreType type = isSellBuff ? PrivateStoreType.PACKAGE_SELL : PrivateStoreType.findById(typeId);

				if (type == null) {
					log.warn("PrivateStoreType with id {} could not be found.", rs.getInt("type"));
					continue;
				}

				if (type == PrivateStoreType.NONE) {
					continue;
				}

				Player player = null;

				try {
					GameClient client = new GameClient(null);
					player = Player.load(rs.getInt("charId"));
					client.setActiveChar(player);
					player.setOnlineStatus(true, false);
					client.setAccountName(player.getAccountNamePlayer());
					player.setClient(client);
					player.setOfflineStartTime(time);

					if (isSellBuff) {
						player.setIsSellingBuffs(true);
					}

					player.spawnMe(player.getX(), player.getY(), player.getZ());

					GameServer.getInstance().getRmi().addAccountInGame(player.getAccountName(), client);

					try (PreparedStatement stm_items = con.prepareStatement(LOAD_OFFLINE_ITEMS)) {
						stm_items.setInt(1, player.getObjectId());
						try (ResultSet items = stm_items.executeQuery()) {
							switch (type) {
								case BUY:
									while (items.next()) {
										if (player.getBuyList().addItemByItemId(items.getInt(2), items.getLong(3), items.getLong(4)) == null) {
											throw new NullPointerException();
										}
									}
									player.getBuyList().setTitle(rs.getString("title"));
									break;
								case SELL:
								case PACKAGE_SELL:
									if (player.isSellingBuffs()) {
										while (items.next()) {
											player.getSellingBuffs().add(new SellBuffHolder(items.getInt("item"), items.getLong("price")));
										}
									} else {
										while (items.next()) {
											if (player.getSellList().addItem(items.getInt(2), items.getLong(3), items.getLong(4)) == null) {
												throw new NullPointerException();
											}
										}
									}
									player.getSellList().setTitle(rs.getString("title"));
									player.getSellList().setPackaged(type == PrivateStoreType.PACKAGE_SELL);
									break;
								case MANUFACTURE:
									final Map<Integer, Long> manufactureItems = new HashMap<>();
									while (items.next()) {
										manufactureItems.put(items.getInt(2), items.getLong(4));
									}
									player.setManufactureItems(manufactureItems);
									player.setStoreName(rs.getString("title"));
									break;
							}
						}
					}
					player.sitDown();
					if (L2JModsConfig.OFFLINE_SET_NAME_COLOR) {
						player.getAppearance().setNameColor(L2JModsConfig.OFFLINE_NAME_COLOR);
					}
					player.setPrivateStoreType(type);
					player.setOnlineStatus(true, true);
					player.restoreEffects();
					player.broadcastUserInfo();
					nTraders++;
				} catch (Exception e) {
					log.warn("Error loading trader: " + player, e);
					if (player != null) {
						Disconnection.of(player).defaultSequence(false);
					}
				}
			}

			log.info("Loaded: " + nTraders + " offline trader(s)");

			try (Statement stm1 = con.createStatement()) {
				stm1.execute(CLEAR_OFFLINE_TABLE);
				stm1.execute(CLEAR_OFFLINE_TABLE_ITEMS);
			}
		} catch (Exception e) {
			log.warn("Error while loading offline traders: ", e);
		}
	}
}