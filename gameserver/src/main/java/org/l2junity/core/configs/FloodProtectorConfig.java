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
package org.l2junity.core.configs;

import org.l2junity.commons.config.annotation.ConfigAfterLoad;
import org.l2junity.commons.config.annotation.ConfigFile;
import org.l2junity.commons.util.PropertiesParser;
import org.l2junity.gameserver.util.FloodProtectorConfigEntry;

/**
 * @author lord_rex
 *         TODO: Rework to config system
 */
@ConfigFile(name = "configs/floodprotector.properties")
public final class FloodProtectorConfig {
	public static FloodProtectorConfigEntry FLOOD_PROTECTOR_USE_ITEM;
	public static FloodProtectorConfigEntry FLOOD_PROTECTOR_ROLL_DICE;
	public static FloodProtectorConfigEntry FLOOD_PROTECTOR_FIREWORK;
	public static FloodProtectorConfigEntry FLOOD_PROTECTOR_ITEM_PET_SUMMON;
	public static FloodProtectorConfigEntry FLOOD_PROTECTOR_HERO_VOICE;
	public static FloodProtectorConfigEntry FLOOD_PROTECTOR_GLOBAL_CHAT;
	public static FloodProtectorConfigEntry FLOOD_PROTECTOR_SUBCLASS;
	public static FloodProtectorConfigEntry FLOOD_PROTECTOR_DROP_ITEM;
	public static FloodProtectorConfigEntry FLOOD_PROTECTOR_SERVER_BYPASS;
	public static FloodProtectorConfigEntry FLOOD_PROTECTOR_MULTISELL;
	public static FloodProtectorConfigEntry FLOOD_PROTECTOR_TRANSACTION;
	public static FloodProtectorConfigEntry FLOOD_PROTECTOR_MANUFACTURE;
	public static FloodProtectorConfigEntry FLOOD_PROTECTOR_MANOR;
	public static FloodProtectorConfigEntry FLOOD_PROTECTOR_SENDMAIL;
	public static FloodProtectorConfigEntry FLOOD_PROTECTOR_CHARACTER_SELECT;
	public static FloodProtectorConfigEntry FLOOD_PROTECTOR_ITEM_AUCTION;

	@ConfigAfterLoad
	@SuppressWarnings("unused")
	public void afterLoad() {
		FLOOD_PROTECTOR_USE_ITEM = new FloodProtectorConfigEntry("UseItemFloodProtector");
		FLOOD_PROTECTOR_ROLL_DICE = new FloodProtectorConfigEntry("RollDiceFloodProtector");
		FLOOD_PROTECTOR_FIREWORK = new FloodProtectorConfigEntry("FireworkFloodProtector");
		FLOOD_PROTECTOR_ITEM_PET_SUMMON = new FloodProtectorConfigEntry("ItemPetSummonFloodProtector");
		FLOOD_PROTECTOR_HERO_VOICE = new FloodProtectorConfigEntry("HeroVoiceFloodProtector");
		FLOOD_PROTECTOR_GLOBAL_CHAT = new FloodProtectorConfigEntry("GlobalChatFloodProtector");
		FLOOD_PROTECTOR_SUBCLASS = new FloodProtectorConfigEntry("SubclassFloodProtector");
		FLOOD_PROTECTOR_DROP_ITEM = new FloodProtectorConfigEntry("DropItemFloodProtector");
		FLOOD_PROTECTOR_SERVER_BYPASS = new FloodProtectorConfigEntry("ServerBypassFloodProtector");
		FLOOD_PROTECTOR_MULTISELL = new FloodProtectorConfigEntry("MultiSellFloodProtector");
		FLOOD_PROTECTOR_TRANSACTION = new FloodProtectorConfigEntry("TransactionFloodProtector");
		FLOOD_PROTECTOR_MANUFACTURE = new FloodProtectorConfigEntry("ManufactureFloodProtector");
		FLOOD_PROTECTOR_MANOR = new FloodProtectorConfigEntry("ManorFloodProtector");
		FLOOD_PROTECTOR_SENDMAIL = new FloodProtectorConfigEntry("SendMailFloodProtector");
		FLOOD_PROTECTOR_CHARACTER_SELECT = new FloodProtectorConfigEntry("CharacterSelectFloodProtector");
		FLOOD_PROTECTOR_ITEM_AUCTION = new FloodProtectorConfigEntry("ItemAuctionFloodProtector");

		// Load FloodProtector L2Properties file
		final PropertiesParser floodProtectors = new PropertiesParser("./configs/floodprotector.properties");
		loadFloodProtectorConfigs(floodProtectors);
	}

	/**
	 * Loads flood protector configurations.
	 *
	 * @param properties the properties object containing the actual values of the flood protector configs
	 */
	public static void loadFloodProtectorConfigs(final PropertiesParser properties) {
		loadFloodProtectorConfig(properties, FLOOD_PROTECTOR_USE_ITEM, "UseItem", 4);
		loadFloodProtectorConfig(properties, FLOOD_PROTECTOR_ROLL_DICE, "RollDice", 42);
		loadFloodProtectorConfig(properties, FLOOD_PROTECTOR_FIREWORK, "Firework", 42);
		loadFloodProtectorConfig(properties, FLOOD_PROTECTOR_ITEM_PET_SUMMON, "ItemPetSummon", 16);
		loadFloodProtectorConfig(properties, FLOOD_PROTECTOR_HERO_VOICE, "HeroVoice", 100);
		loadFloodProtectorConfig(properties, FLOOD_PROTECTOR_GLOBAL_CHAT, "GlobalChat", 5);
		loadFloodProtectorConfig(properties, FLOOD_PROTECTOR_SUBCLASS, "Subclass", 20);
		loadFloodProtectorConfig(properties, FLOOD_PROTECTOR_DROP_ITEM, "DropItem", 10);
		loadFloodProtectorConfig(properties, FLOOD_PROTECTOR_SERVER_BYPASS, "ServerBypass", 5);
		loadFloodProtectorConfig(properties, FLOOD_PROTECTOR_MULTISELL, "MultiSell", 1);
		loadFloodProtectorConfig(properties, FLOOD_PROTECTOR_TRANSACTION, "Transaction", 10);
		loadFloodProtectorConfig(properties, FLOOD_PROTECTOR_MANUFACTURE, "Manufacture", 3);
		loadFloodProtectorConfig(properties, FLOOD_PROTECTOR_MANOR, "Manor", 30);
		loadFloodProtectorConfig(properties, FLOOD_PROTECTOR_SENDMAIL, "SendMail", 100);
		loadFloodProtectorConfig(properties, FLOOD_PROTECTOR_CHARACTER_SELECT, "CharacterSelect", 30);
		loadFloodProtectorConfig(properties, FLOOD_PROTECTOR_ITEM_AUCTION, "ItemAuction", 9);
	}

	/**
	 * Loads single flood protector configuration.
	 *
	 * @param properties      properties file reader
	 * @param config          flood protector configuration instance
	 * @param configString    flood protector configuration string that determines for which flood protector configuration should be read
	 * @param defaultInterval default flood protector interval
	 */
	private static void loadFloodProtectorConfig(final PropertiesParser properties, final FloodProtectorConfigEntry config, final String configString, final int defaultInterval) {
		config.FLOOD_PROTECTION_INTERVAL = properties.getInt("FloodProtector" + configString + "Interval", defaultInterval);
		config.LOG_FLOODING = properties.getBoolean("FloodProtector" + configString + "LogFlooding", false);
		config.PUNISHMENT_LIMIT = properties.getInt("FloodProtector" + configString + "PunishmentLimit", 0);
		config.PUNISHMENT_TYPE = properties.getString("FloodProtector" + configString + "PunishmentType", "none");
		config.PUNISHMENT_TIME = properties.getInt("FloodProtector" + configString + "PunishmentTime", 0) * 60000;
	}
}
