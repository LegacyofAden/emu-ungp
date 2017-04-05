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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.StringTokenizer;

import org.l2junity.commons.sql.DatabaseFactory;
import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.core.configs.PvpConfig;
import org.l2junity.gameserver.data.sql.impl.CharNameTable;
import org.l2junity.gameserver.data.xml.impl.ClassListData;
import org.l2junity.gameserver.data.xml.impl.SkillTreesData;
import org.l2junity.gameserver.enums.NobleStatus;
import org.l2junity.gameserver.handler.AdminCommandHandler;
import org.l2junity.gameserver.handler.IAdminCommandHandler;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.Playable;
import org.l2junity.gameserver.model.actor.Summon;
import org.l2junity.gameserver.model.actor.instance.PetInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.base.ClassId;
import org.l2junity.gameserver.model.html.PageBuilder;
import org.l2junity.gameserver.model.html.PageResult;
import org.l2junity.gameserver.model.stats.DoubleStat;
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.network.client.L2GameClient;
import org.l2junity.gameserver.network.client.send.ExVoteSystemInfo;
import org.l2junity.gameserver.network.client.send.GMViewItemList;
import org.l2junity.gameserver.network.client.send.NpcHtmlMessage;
import org.l2junity.gameserver.network.client.send.PartySmallWindowAll;
import org.l2junity.gameserver.network.client.send.PartySmallWindowDeleteAll;
import org.l2junity.gameserver.network.client.send.SystemMessage;
import org.l2junity.gameserver.network.client.send.UserInfo;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * EditChar admin command implementation.
 */
public class AdminEditChar implements IAdminCommandHandler {
	private static Logger _log = LoggerFactory.getLogger(AdminEditChar.class);

	private static final String[] ADMIN_COMMANDS =
			{
					"admin_edit_character",
					"admin_current_player",
					"admin_setreputation", // sets reputation of target char to any amount. //setreputation <amout>
					"admin_nokarma", // sets reputation to 0 if its negative.
					"admin_setfame", // sets fame of target char to any amount. //setfame <fame>
					"admin_character_list", // same as character_info, kept for compatibility purposes
					"admin_character_info", // given a player name, displays an information window
					"admin_show_characters", // list of characters
					"admin_find_character", // find a player by his name or a part of it (case-insensitive)
					"admin_find_ip", // find all the player connections from a given IPv4 number
					"admin_find_account", // list all the characters from an account (useful for GMs w/o DB access)
					"admin_find_dualbox", // list all the IPs with more than 1 char logged in (dualbox)
					"admin_strict_find_dualbox",
					"admin_tracert",
					"admin_rec", // gives recommendation points
					"admin_settitle", // changes char title
					"admin_changename", // changes char name
					"admin_setsex", // changes characters' sex
					"admin_setcolor", // change charnames' color display
					"admin_settcolor", // change char title color
					"admin_setclass", // changes chars' classId
					"admin_setpk", // changes PK count
					"admin_setpvp", // changes PVP count
					"admin_set_pvp_flag",
					"admin_fullfood", // fulfills a pet's food bar
					"admin_remove_clan_penalty", // removes clan penalties
					"admin_summon_info", // displays an information window about target summon
					"admin_unsummon",
					"admin_summon_setlvl",
					"admin_show_pet_inv",
					"admin_partyinfo",
					"admin_setnoble",
					"admin_set_hp",
					"admin_set_mp",
					"admin_set_cp",
					"admin_setparam",
					"admin_unsetparam"
			};

	@Override
	public boolean useAdminCommand(String command, Player activeChar) {
		if (command.equals("admin_current_player")) {
			showCharacterInfo(activeChar, activeChar);
		} else if (command.startsWith("admin_character_info")) {
			String[] data = command.split(" ");
			if ((data.length > 1)) {
				showCharacterInfo(activeChar, WorldManager.getInstance().getPlayer(data[1]));
			} else if (activeChar.getTarget() instanceof Player) {
				showCharacterInfo(activeChar, activeChar.getTarget().getActingPlayer());
			} else {
				activeChar.sendPacket(SystemMessageId.INVALID_TARGET);
			}
		} else if (command.startsWith("admin_character_list")) {
			listCharacters(activeChar, 0);
		} else if (command.startsWith("admin_show_characters")) {
			try {
				String val = command.substring(22);
				int page = Integer.parseInt(val);
				listCharacters(activeChar, page);
			} catch (StringIndexOutOfBoundsException e) {
				// Case of empty page number
				activeChar.sendMessage("Usage: //show_characters <page_number>");
			}
		} else if (command.startsWith("admin_find_character")) {
			try {
				String val = command.substring(21);
				findCharacter(activeChar, val);
			} catch (StringIndexOutOfBoundsException e) { // Case of empty character name
				activeChar.sendMessage("Usage: //find_character <character_name>");
				listCharacters(activeChar, 0);
			}
		} else if (command.startsWith("admin_find_ip")) {
			try {
				String val = command.substring(14);
				findCharactersPerIp(activeChar, val);
			} catch (Exception e) { // Case of empty or malformed IP number
				activeChar.sendMessage("Usage: //find_ip <www.xxx.yyy.zzz>");
				listCharacters(activeChar, 0);
			}
		} else if (command.startsWith("admin_find_account")) {
			try {
				String val = command.substring(19);
				findCharactersPerAccount(activeChar, val);
			} catch (Exception e) { // Case of empty or malformed player name
				activeChar.sendMessage("Usage: //find_account <player_name>");
				listCharacters(activeChar, 0);
			}
		} else if (command.startsWith("admin_edit_character")) {
			String[] data = command.split(" ");
			if ((data.length > 1)) {
				editCharacter(activeChar, data[1]);
			} else if (activeChar.getTarget() instanceof Player) {
				editCharacter(activeChar, null);
			} else {
				activeChar.sendPacket(SystemMessageId.INVALID_TARGET);
			}
		} else if (command.startsWith("admin_setreputation")) {
			try {
				final String val = command.substring(20);
				final int reputation = Integer.parseInt(val);
				setTargetReputation(activeChar, reputation);
			} catch (Exception e) {
				if (GeneralConfig.DEVELOPER) {
					_log.warn("Set reputation error: " + e);
				}
				activeChar.sendMessage("Usage: //setreputation <new_reputation_value>");
			}
		} else if (command.startsWith("admin_nokarma")) {
			if ((activeChar.getTarget() == null) || !activeChar.getTarget().isPlayer()) {
				activeChar.sendMessage("You must target a player.");
				return false;
			}

			if (activeChar.getTarget().getActingPlayer().getReputation() < 0) {
				setTargetReputation(activeChar, 0);
			}
		} else if (command.startsWith("admin_setpk")) {
			try {
				final String val = command.substring(12);
				final int pk = Integer.parseInt(val);
				final WorldObject target = activeChar.getTarget();
				if (target.isPlayer()) {
					final Player player = target.getActingPlayer();
					player.setPkKills(pk);
					player.broadcastUserInfo();
					player.sendPacket(new UserInfo(player));
					player.sendMessage("A GM changed your PK count to " + pk);
					activeChar.sendMessage(player.getName() + "'s PK count changed to " + pk);
				} else {
					activeChar.sendPacket(SystemMessageId.INVALID_TARGET);
				}
			} catch (Exception e) {
				if (GeneralConfig.DEVELOPER) {
					_log.warn("Set pk error: " + e);
				}
				activeChar.sendMessage("Usage: //setpk <pk_count>");
			}
		} else if (command.startsWith("admin_setpvp")) {
			try {
				String val = command.substring(13);
				int pvp = Integer.parseInt(val);
				WorldObject target = activeChar.getTarget();
				if (target instanceof Player) {
					Player player = (Player) target;
					player.setPvpKills(pvp);
					player.broadcastUserInfo();
					player.sendPacket(new UserInfo(player));
					player.sendMessage("A GM changed your PVP count to " + pvp);
					activeChar.sendMessage(player.getName() + "'s PVP count changed to " + pvp);
				} else {
					activeChar.sendPacket(SystemMessageId.INVALID_TARGET);
				}
			} catch (Exception e) {
				if (GeneralConfig.DEVELOPER) {
					_log.warn("Set pvp error: " + e);
				}
				activeChar.sendMessage("Usage: //setpvp <pvp_count>");
			}
		} else if (command.startsWith("admin_setfame")) {
			try {
				String val = command.substring(14);
				int fame = Integer.parseInt(val);
				WorldObject target = activeChar.getTarget();
				if (target instanceof Player) {
					Player player = (Player) target;
					player.setFame(fame);
					player.broadcastUserInfo();
					player.sendPacket(new UserInfo(player));
					player.sendMessage("A GM changed your Reputation points to " + fame);
					activeChar.sendMessage(player.getName() + "'s Fame changed to " + fame);
				} else {
					activeChar.sendPacket(SystemMessageId.INVALID_TARGET);
				}
			} catch (Exception e) {
				if (GeneralConfig.DEVELOPER) {
					_log.warn("Set Fame error: " + e);
				}
				activeChar.sendMessage("Usage: //setfame <new_fame_value>");
			}
		} else if (command.startsWith("admin_rec")) {
			try {
				String val = command.substring(10);
				int recVal = Integer.parseInt(val);
				WorldObject target = activeChar.getTarget();
				if (target instanceof Player) {
					Player player = (Player) target;
					player.setRecomHave(recVal);
					player.broadcastUserInfo();
					player.sendPacket(new UserInfo(player));
					player.sendPacket(new ExVoteSystemInfo(player));
					player.sendMessage("A GM changed your Recommend points to " + recVal);
					activeChar.sendMessage(player.getName() + "'s Recommend changed to " + recVal);
				} else {
					activeChar.sendPacket(SystemMessageId.INVALID_TARGET);
				}
			} catch (Exception e) {
				activeChar.sendMessage("Usage: //rec number");
			}
		} else if (command.startsWith("admin_setclass")) {
			try {
				String val = command.substring(15).trim();
				int classidval = Integer.parseInt(val);
				WorldObject target = activeChar.getTarget();
				if ((target == null) || !target.isPlayer()) {
					return false;
				}
				final Player player = target.getActingPlayer();
				if ((ClassId.getClassId(classidval) != null) && (player.getClassId().getId() != classidval)) {
					player.setClassId(classidval);

					if (player.isSubClassActive()) {
						player.getSubClasses().get(player.getClassIndex()).setClassId(player.getActiveClass());
					} else {
						player.setBaseClass(player.getActiveClass());
					}

					final String newclass = ClassListData.getInstance().getClass(player.getClassId()).getClassName();
					player.storeMe();
					player.sendMessage("A GM changed your class to " + newclass + ".");
					player.broadcastUserInfo();
					if (player.isAwakenedClass()) {
						SkillTreesData.getInstance().cleanSkillUponAwakening(player);
					}
					player.sendSkillList();
					activeChar.sendMessage(player.getName() + " is a " + newclass + ".");
				} else {
					activeChar.sendMessage("Usage: //setclass <valid_new_classid>");
				}
			} catch (StringIndexOutOfBoundsException e) {
				AdminHtml.showAdminHtml(activeChar, "setclass/human_fighter.htm");
			} catch (NumberFormatException e) {
				activeChar.sendMessage("Usage: //setclass <valid_new_classid>");
			}
		} else if (command.startsWith("admin_settitle")) {
			try {
				String val = command.substring(15);
				WorldObject target = activeChar.getTarget();
				Player player = null;
				if (target instanceof Player) {
					player = (Player) target;
				} else {
					return false;
				}
				player.setTitle(val);
				player.sendMessage("Your title has been changed by a GM");
				player.broadcastTitleInfo();
			} catch (StringIndexOutOfBoundsException e) { // Case of empty character title
				activeChar.sendMessage("You need to specify the new title.");
			}
		} else if (command.startsWith("admin_changename")) {
			try {
				String val = command.substring(17);
				WorldObject target = activeChar.getTarget();
				Player player = null;
				if (target instanceof Player) {
					player = (Player) target;
				} else {
					return false;
				}
				if (CharNameTable.getInstance().getIdByName(val) > 0) {
					activeChar.sendMessage("Warning, player " + val + " already exists");
					return false;
				}
				player.setName(val);
				if (GeneralConfig.CACHE_CHAR_NAMES) {
					CharNameTable.getInstance().addName(player);
				}
				player.storeMe();

				activeChar.sendMessage("Changed name to " + val);
				player.sendMessage("Your name has been changed by a GM.");
				player.broadcastUserInfo();

				if (player.isInParty()) {
					// Delete party window for other party members
					player.getParty().broadcastToPartyMembers(player, PartySmallWindowDeleteAll.STATIC_PACKET);
					for (Player member : player.getParty().getMembers()) {
						// And re-add
						if (member != player) {
							member.sendPacket(new PartySmallWindowAll(member, player.getParty()));
						}
					}
				}
				if (player.getClan() != null) {
					player.getClan().broadcastClanStatus();
				}
			} catch (StringIndexOutOfBoundsException e) { // Case of empty character name
				activeChar.sendMessage("Usage: //setname new_name_for_target");
			}
		} else if (command.startsWith("admin_setsex")) {
			WorldObject target = activeChar.getTarget();
			Player player = null;
			if (target instanceof Player) {
				player = (Player) target;
			} else {
				return false;
			}
			player.getAppearance().setSex(player.getAppearance().getSex() ? false : true);
			player.sendMessage("Your gender has been changed by a GM");
			player.broadcastUserInfo();
		} else if (command.startsWith("admin_setcolor")) {
			try {
				String val = command.substring(15);
				WorldObject target = activeChar.getTarget();
				Player player = null;
				if (target instanceof Player) {
					player = (Player) target;
				} else {
					return false;
				}
				player.getAppearance().setNameColor(Integer.decode("0x" + val));
				player.sendMessage("Your name color has been changed by a GM");
				player.broadcastUserInfo();
			} catch (Exception e) { // Case of empty color or invalid hex string
				activeChar.sendMessage("You need to specify a valid new color.");
			}
		} else if (command.startsWith("admin_settcolor")) {
			try {
				String val = command.substring(16);
				WorldObject target = activeChar.getTarget();
				Player player = null;
				if (target instanceof Player) {
					player = (Player) target;
				} else {
					return false;
				}
				player.getAppearance().setTitleColor(Integer.decode("0x" + val));
				player.sendMessage("Your title color has been changed by a GM");
				player.broadcastUserInfo();
			} catch (Exception e) { // Case of empty color or invalid hex string
				activeChar.sendMessage("You need to specify a valid new color.");
			}
		} else if (command.startsWith("admin_fullfood")) {
			WorldObject target = activeChar.getTarget();
			if (target instanceof PetInstance) {
				PetInstance targetPet = (PetInstance) target;
				targetPet.setCurrentFed(targetPet.getMaxFed());
				targetPet.broadcastStatusUpdate();
			} else {
				activeChar.sendPacket(SystemMessageId.INVALID_TARGET);
			}
		} else if (command.startsWith("admin_remove_clan_penalty")) {
			try {
				StringTokenizer st = new StringTokenizer(command, " ");
				if (st.countTokens() != 3) {
					activeChar.sendMessage("Usage: //remove_clan_penalty join|create charname");
					return false;
				}

				st.nextToken();

				boolean changeCreateExpiryTime = st.nextToken().equalsIgnoreCase("create");

				String playerName = st.nextToken();
				Player player = null;
				player = WorldManager.getInstance().getPlayer(playerName);

				if (player == null) {
					Connection con = DatabaseFactory.getInstance().getConnection();
					PreparedStatement ps = con.prepareStatement("UPDATE characters SET " + (changeCreateExpiryTime ? "clan_create_expiry_time" : "clan_join_expiry_time") + " WHERE char_name=? LIMIT 1");

					ps.setString(1, playerName);
					ps.execute();
				} else {
					// removing penalty
					if (changeCreateExpiryTime) {
						player.setClanCreateExpiryTime(0);
					} else {
						player.setClanJoinExpiryTime(0);
					}
				}

				activeChar.sendMessage("Clan penalty successfully removed to character: " + playerName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.startsWith("admin_find_dualbox")) {
			int multibox = 2;
			try {
				String val = command.substring(19);
				multibox = Integer.parseInt(val);
				if (multibox < 1) {
					activeChar.sendMessage("Usage: //find_dualbox [number > 0]");
					return false;
				}
			} catch (Exception e) {
			}
			findDualbox(activeChar, multibox);
		} else if (command.startsWith("admin_strict_find_dualbox")) {
			int multibox = 2;
			try {
				String val = command.substring(26);
				multibox = Integer.parseInt(val);
				if (multibox < 1) {
					activeChar.sendMessage("Usage: //strict_find_dualbox [number > 0]");
					return false;
				}
			} catch (Exception e) {
			}
			findDualboxStrict(activeChar, multibox);
		} else if (command.startsWith("admin_tracert")) {
			String[] data = command.split(" ");
			Player pl = null;
			if ((data.length > 1)) {
				pl = WorldManager.getInstance().getPlayer(data[1]);
			} else {
				WorldObject target = activeChar.getTarget();
				if (target instanceof Player) {
					pl = (Player) target;
				}
			}

			if (pl == null) {
				activeChar.sendPacket(SystemMessageId.INVALID_TARGET);
				return false;
			}

			final L2GameClient client = pl.getClient();
			if (client == null) {
				activeChar.sendMessage("Client is null.");
				return false;
			}

			if (client.isDetached()) {
				activeChar.sendMessage("Client is detached.");
				return false;
			}

			String ip;
			int[][] trace = client.getTrace();
			for (int i = 0; i < trace.length; i++) {
				ip = "";
				for (int o = 0; o < trace[0].length; o++) {
					ip = ip + trace[i][o];
					if (o != (trace[0].length - 1)) {
						ip = ip + ".";
					}
				}
				activeChar.sendMessage("Hop" + i + ": " + ip);
			}
		} else if (command.startsWith("admin_summon_info")) {
			WorldObject target = activeChar.getTarget();
			if (target instanceof Summon) {
				gatherSummonInfo((Summon) target, activeChar);
			} else {
				activeChar.sendMessage("Invalid target.");
			}
		} else if (command.startsWith("admin_unsummon")) {
			WorldObject target = activeChar.getTarget();
			if (target instanceof Summon) {
				((Summon) target).unSummon(((Summon) target).getOwner());
			} else {
				activeChar.sendMessage("Usable only with Pets/Summons");
			}
		} else if (command.startsWith("admin_summon_setlvl")) {
			WorldObject target = activeChar.getTarget();
			if (target instanceof PetInstance) {
				PetInstance pet = (PetInstance) target;
				try {
					String val = command.substring(20);
					int level = Integer.parseInt(val);
					long newexp, oldexp = 0;
					oldexp = pet.getStat().getExp();
					newexp = pet.getStat().getExpForLevel(level);
					if (oldexp > newexp) {
						pet.getStat().removeExp(oldexp - newexp);
					} else if (oldexp < newexp) {
						pet.getStat().addExp(newexp - oldexp);
					}
				} catch (Exception e) {
				}
			} else {
				activeChar.sendMessage("Usable only with Pets");
			}
		} else if (command.startsWith("admin_show_pet_inv")) {
			WorldObject target;
			try {
				String val = command.substring(19);
				int objId = Integer.parseInt(val);
				target = WorldManager.getInstance().getPet(objId);
			} catch (Exception e) {
				target = activeChar.getTarget();
			}

			if (target instanceof PetInstance) {
				activeChar.sendPacket(new GMViewItemList((PetInstance) target));
			} else {
				activeChar.sendMessage("Usable only with Pets");
			}

		} else if (command.startsWith("admin_partyinfo")) {
			WorldObject target;
			try {
				String val = command.substring(16);
				target = WorldManager.getInstance().getPlayer(val);
				if (target == null) {
					target = activeChar.getTarget();
				}
			} catch (Exception e) {
				target = activeChar.getTarget();
			}

			if (target instanceof Player) {
				if (((Player) target).isInParty()) {
					gatherPartyInfo((Player) target, activeChar);
				} else {
					activeChar.sendMessage("Not in party.");
				}
			} else {
				activeChar.sendPacket(SystemMessageId.INVALID_TARGET);
			}

		} else if (command.equals("admin_setnoble")) {
			Player player = null;
			if (activeChar.getTarget() == null) {
				player = activeChar;
			} else if ((activeChar.getTarget() != null) && (activeChar.getTarget() instanceof Player)) {
				player = (Player) activeChar.getTarget();
			}

			if (player != null) {
				final NobleStatus status = player.getNobleStatus();
				final NobleStatus newStatus = NobleStatus.valueOf(status.ordinal() + 1);
				player.setNoble(newStatus != null ? newStatus : NobleStatus.NONE);
				if (player.getObjectId() != activeChar.getObjectId()) {
					activeChar.sendMessage("You've changed nobless status of: " + player.getName());
				}
				player.broadcastUserInfo();
				player.sendMessage("GM changed your nobless status from: " + status + " to: " + player.getNobleStatus());
			}
		} else if (command.startsWith("admin_set_hp")) {
			final String[] data = command.split(" ");
			try {
				final WorldObject target = activeChar.getTarget();
				if ((target == null) || !target.isCreature()) {
					activeChar.sendPacket(SystemMessageId.INVALID_TARGET);
					return false;
				}
				((Creature) target).setCurrentHp(Double.parseDouble(data[1]));
			} catch (Exception e) {
				activeChar.sendMessage("Usage: //set_hp 1000");
			}
		} else if (command.startsWith("admin_set_mp")) {
			final String[] data = command.split(" ");
			try {
				final WorldObject target = activeChar.getTarget();
				if ((target == null) || !target.isCreature()) {
					activeChar.sendPacket(SystemMessageId.INVALID_TARGET);
					return false;
				}
				((Creature) target).setCurrentMp(Double.parseDouble(data[1]));
			} catch (Exception e) {
				activeChar.sendMessage("Usage: //set_mp 1000");
			}
		} else if (command.startsWith("admin_set_cp")) {
			final String[] data = command.split(" ");
			try {
				final WorldObject target = activeChar.getTarget();
				if ((target == null) || !target.isCreature()) {
					activeChar.sendPacket(SystemMessageId.INVALID_TARGET);
					return false;
				}
				((Creature) target).setCurrentCp(Double.parseDouble(data[1]));
			} catch (Exception e) {
				activeChar.sendMessage("Usage: //set_cp 1000");
			}
		} else if (command.startsWith("admin_set_pvp_flag")) {
			try {
				final WorldObject target = activeChar.getTarget();
				if ((target == null) || !target.isPlayable()) {
					activeChar.sendPacket(SystemMessageId.INVALID_TARGET);
					return false;
				}
				final Playable playable = ((Playable) target);
				playable.updatePvPFlag(Math.abs(playable.getPvpFlag() - 1));
			} catch (Exception e) {
				activeChar.sendMessage("Usage: //set_pvp_flag");
			}
		} else if (command.startsWith("admin_setparam")) {
			final WorldObject target = activeChar.getTarget();
			if ((target == null) || !target.isCreature()) {
				activeChar.sendPacket(SystemMessageId.INVALID_TARGET);
				return false;
			}
			final StringTokenizer st = new StringTokenizer(command, " ");
			st.nextToken(); // admin_setparam
			if (!st.hasMoreTokens()) {
				activeChar.sendMessage("Syntax: //setparam <stat> <value>");
				return false;
			}
			final String statName = st.nextToken();
			if (!st.hasMoreTokens()) {
				activeChar.sendMessage("Syntax: //setparam <stat> <value>");
				return false;
			}

			try {
				DoubleStat stat = null;
				for (DoubleStat stats : DoubleStat.values()) {
					if (statName.equalsIgnoreCase(stats.name()) || statName.equalsIgnoreCase(stats.getValue())) {
						stat = stats;
						break;
					}
				}
				if (stat == null) {
					activeChar.sendMessage("Couldn't find such stat!");
					return false;
				}

				double value = Double.parseDouble(st.nextToken());
				if ((value >= 0) && target.isCreature()) {
					final Creature targetCreature = target.asCreature();
					targetCreature.getStat().addFixedValue(stat, value);
					targetCreature.getStat().recalculateStats(false);
					if (targetCreature.isPlayer()) {
						targetCreature.asPlayer().broadcastUserInfo();
					} else {
						targetCreature.broadcastInfo();
					}
					activeChar.sendMessage("Fixed stat: " + stat + " has been set to " + value);
				} else {
					activeChar.sendMessage("Non negative values are only allowed!");
				}
			} catch (Exception e) {
				activeChar.sendMessage("Syntax: //setparam <stat> <value>");
				return false;
			}
		} else if (command.startsWith("admin_unsetparam")) {
			final WorldObject target = activeChar.getTarget();
			if ((target == null) || !target.isCreature()) {
				activeChar.sendPacket(SystemMessageId.INVALID_TARGET);
				return false;
			}
			final StringTokenizer st = new StringTokenizer(command, " ");
			st.nextToken(); // admin_setparam
			if (!st.hasMoreTokens()) {
				activeChar.sendMessage("Syntax: //unsetparam <stat>");
				return false;
			}
			final String statName = st.nextToken();

			DoubleStat stat = null;
			for (DoubleStat stats : DoubleStat.values()) {
				if (statName.equalsIgnoreCase(stats.name()) || statName.equalsIgnoreCase(stats.getValue())) {
					stat = stats;
					break;
				}
			}
			if (stat == null) {
				activeChar.sendMessage("Couldn't find such stat!");
				return false;
			}
			if (target.isCreature()) {
				final Creature targetCreature = target.asCreature();
				targetCreature.getStat().removeFixedValue(stat);
				targetCreature.getStat().recalculateStats(false);
				if (targetCreature.isPlayer()) {
					targetCreature.asPlayer().broadcastUserInfo();
				} else {
					targetCreature.broadcastInfo();
				}
			}
			activeChar.sendMessage("Fixed stat: " + stat + " has been removed.");
		}
		return true;
	}

	@Override
	public String[] getAdminCommandList() {
		return ADMIN_COMMANDS;
	}

	private void listCharacters(Player activeChar, int page) {
		final List<Player> players = WorldManager.getInstance().getAllPlayers();
		players.sort(Comparator.comparingLong(Player::getUptime));

		final NpcHtmlMessage html = new NpcHtmlMessage(0, 1);
		html.setFile(activeChar.getLang(), "admin/charlist.htm");

		final PageResult result = PageBuilder.newBuilder(players, 20, "bypass -h admin_show_characters").currentPage(page).bodyHandler((pages, player, sb) ->
		{
			sb.append("<tr>");
			sb.append("<td width=80><a action=\"bypass -h admin_character_info " + player.getName() + "\">" + player.getName() + "</a></td>");
			sb.append("<td width=110>" + ClassListData.getInstance().getClass(player.getClassId()).getClientCode() + "</td><td width=40>" + player.getLevel() + "</td>");
			sb.append("</tr>");
		}).build();

		if (result.getPages() > 0) {
			html.replace("%pages%", "<table width=280 cellspacing=0><tr>" + result.getPagerTemplate() + "</tr></table>");
		} else {
			html.replace("%pages%", "");
		}

		html.replace("%players%", result.getBodyTemplate().toString());
		activeChar.sendPacket(html);
	}

	private void showCharacterInfo(Player activeChar, Player player) {
		if (player == null) {
			WorldObject target = activeChar.getTarget();
			if (target instanceof Player) {
				player = (Player) target;
			} else {
				return;
			}
		} else {
			activeChar.setTarget(player);
		}
		gatherCharacterInfo(activeChar, player, "charinfo.htm");
	}

	/**
	 * Retrieve and replace player's info in filename htm file, sends it to activeChar as NpcHtmlMessage.
	 *
	 * @param activeChar
	 * @param player
	 * @param filename
	 */
	private void gatherCharacterInfo(Player activeChar, Player player, String filename) {
		String ip = "N/A";

		if (player == null) {
			activeChar.sendMessage("Player is null.");
			return;
		}

		final L2GameClient client = player.getClient();
		if (client == null) {
			activeChar.sendMessage("Client is null.");
		} else if (client.isDetached()) {
			activeChar.sendMessage("Client is detached.");
		} else {
			ip = client.getConnectionAddress().getHostAddress();
		}

		final NpcHtmlMessage adminReply = new NpcHtmlMessage(0, 1);
		adminReply.setFile(activeChar.getLang(), "admin/" + filename);
		adminReply.replace("%name%", player.getName());
		adminReply.replace("%level%", String.valueOf(player.getLevel()));
		adminReply.replace("%clan%", String.valueOf(player.getClan() != null ? "<a action=\"bypass -h admin_clan_info " + player.getObjectId() + "\">" + player.getClan().getName() + "</a>" : null));
		adminReply.replace("%xp%", String.valueOf(player.getExp()));
		adminReply.replace("%sp%", String.valueOf(player.getSp()));
		adminReply.replace("%class%", ClassListData.getInstance().getClass(player.getClassId()).getClientCode());
		adminReply.replace("%ordinal%", String.valueOf(player.getClassId().ordinal()));
		adminReply.replace("%classid%", String.valueOf(player.getClassId()));
		adminReply.replace("%baseclass%", ClassListData.getInstance().getClass(player.getBaseClass()).getClientCode());
		adminReply.replace("%x%", String.valueOf(player.getX()));
		adminReply.replace("%y%", String.valueOf(player.getY()));
		adminReply.replace("%z%", String.valueOf(player.getZ()));
		adminReply.replace("%currenthp%", String.valueOf((int) player.getCurrentHp()));
		adminReply.replace("%maxhp%", String.valueOf(player.getMaxHp()));
		adminReply.replace("%reputation%", String.valueOf(player.getReputation()));
		adminReply.replace("%currentmp%", String.valueOf((int) player.getCurrentMp()));
		adminReply.replace("%maxmp%", String.valueOf(player.getMaxMp()));
		adminReply.replace("%pvpflag%", String.valueOf(player.getPvpFlag()));
		adminReply.replace("%currentcp%", String.valueOf((int) player.getCurrentCp()));
		adminReply.replace("%maxcp%", String.valueOf(player.getMaxCp()));
		adminReply.replace("%pvpkills%", String.valueOf(player.getPvpKills()));
		adminReply.replace("%pkkills%", String.valueOf(player.getPkKills()));
		adminReply.replace("%currentload%", String.valueOf(player.getCurrentLoad()));
		adminReply.replace("%maxload%", String.valueOf(player.getMaxLoad()));
		adminReply.replace("%percent%", String.format("%.2f", (((float) player.getCurrentLoad() / (float) player.getMaxLoad()) * 100)));
		adminReply.replace("%patk%", String.valueOf(player.getPAtk()));
		adminReply.replace("%matk%", String.valueOf(player.getMAtk()));
		adminReply.replace("%pdef%", String.valueOf(player.getPDef()));
		adminReply.replace("%mdef%", String.valueOf(player.getMDef()));
		adminReply.replace("%accuracy%", String.valueOf(player.getAccuracy()));
		adminReply.replace("%evasion%", String.valueOf(player.getEvasionRate()));
		adminReply.replace("%critical%", String.valueOf(player.getCriticalHit()));
		adminReply.replace("%runspeed%", String.valueOf(player.getRunSpeed()));
		adminReply.replace("%patkspd%", String.valueOf(player.getPAtkSpd()));
		adminReply.replace("%matkspd%", String.valueOf(player.getMAtkSpd()));
		adminReply.replace("%access%", player.getAccessLevel().getLevel() + " (" + player.getAccessLevel().getName() + ")");
		adminReply.replace("%account%", player.getAccountName());
		adminReply.replace("%ip%", ip);
		adminReply.replace("%ai%", String.valueOf(player.getAI().getIntention().name()));
		adminReply.replace("%inst%", player.isInInstance() ? "<tr><td>InstanceId:</td><td><a action=\"bypass -h admin_instance_spawns " + String.valueOf(player.getInstanceId()) + "\">" + String.valueOf(player.getInstanceId()) + "</a></td></tr>" : "");
		adminReply.replace("%noblesse%", player.isNoble() ? "Yes" : "No");
		activeChar.sendPacket(adminReply);
	}

	private void setTargetReputation(Player activeChar, int newReputation) {
		final WorldObject target = activeChar.getTarget();
		Player player = null;
		if (target.isPlayer()) {
			player = (Player) target;
		} else {
			return;
		}

		if (newReputation > PvpConfig.MAX_REPUTATION) {
			newReputation = PvpConfig.MAX_REPUTATION;
		}

		final int oldReputation = player.getReputation();
		player.setReputation(newReputation);
		final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOUR_REPUTATION_HAS_BEEN_CHANGED_TO_S1);
		sm.addInt(newReputation);
		player.sendPacket(sm);
		activeChar.sendMessage("Successfully Changed karma for " + player.getName() + " from (" + oldReputation + ") to (" + newReputation + ").");
		if (GeneralConfig.DEBUG) {
			_log.debug("[SET KARMA] [GM]" + activeChar.getName() + " Changed karma for " + player.getName() + " from (" + oldReputation + ") to (" + newReputation + ").");
		}
	}

	private void editCharacter(Player activeChar, String targetName) {
		WorldObject target = null;
		if (targetName != null) {
			target = WorldManager.getInstance().getPlayer(targetName);
		} else {
			target = activeChar.getTarget();
		}

		if (target instanceof Player) {
			Player player = (Player) target;
			gatherCharacterInfo(activeChar, player, "charedit.htm");
		}
	}

	/**
	 * @param activeChar
	 * @param CharacterToFind
	 */
	private void findCharacter(Player activeChar, String CharacterToFind) {
		int CharactersFound = 0;
		String name;
		final NpcHtmlMessage adminReply = new NpcHtmlMessage(0, 1);
		adminReply.setFile(activeChar.getLang(), "admin/charfind.htm");

		final StringBuilder replyMSG = new StringBuilder(1000);

		final List<Player> players = WorldManager.getInstance().getAllPlayers();
		players.sort(Comparator.comparingLong(Player::getUptime));
		for (Player player : players) { // Add player info into new Table row
			name = player.getName();
			if (name.toLowerCase().contains(CharacterToFind.toLowerCase())) {
				CharactersFound = CharactersFound + 1;
				replyMSG.append("<tr><td width=80><a action=\"bypass -h admin_character_info ");
				replyMSG.append(name);
				replyMSG.append("\">");
				replyMSG.append(name);
				replyMSG.append("</a></td><td width=110>");
				replyMSG.append(ClassListData.getInstance().getClass(player.getClassId()).getClientCode());
				replyMSG.append("</td><td width=40>");
				replyMSG.append(player.getLevel());
				replyMSG.append("</td></tr>");
			}
			if (CharactersFound > 20) {
				break;
			}
		}
		adminReply.replace("%results%", replyMSG.toString());

		final String replyMSG2;

		if (CharactersFound == 0) {
			replyMSG2 = "s. Please try again.";
		} else if (CharactersFound > 20) {
			adminReply.replace("%number%", " more than 20");
			replyMSG2 = "s.<br>Please refine your search to see all of the results.";
		} else if (CharactersFound == 1) {
			replyMSG2 = ".";
		} else {
			replyMSG2 = "s.";
		}

		adminReply.replace("%number%", String.valueOf(CharactersFound));
		adminReply.replace("%end%", replyMSG2);
		activeChar.sendPacket(adminReply);
	}

	/**
	 * @param activeChar
	 * @param IpAdress
	 * @throws IllegalArgumentException
	 */
	private void findCharactersPerIp(Player activeChar, String IpAdress) throws IllegalArgumentException {
		boolean findDisconnected = false;

		if (IpAdress.equals("disconnected")) {
			findDisconnected = true;
		} else {
			if (!IpAdress.matches("^(?:(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2(?:[0-4][0-9]|5[0-5]))\\.){3}(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2(?:[0-4][0-9]|5[0-5]))$")) {
				throw new IllegalArgumentException("Malformed IPv4 number");
			}
		}

		int CharactersFound = 0;
		L2GameClient client;
		String name, ip = "0.0.0.0";
		final StringBuilder replyMSG = new StringBuilder(1000);
		final NpcHtmlMessage adminReply = new NpcHtmlMessage(0, 1);
		adminReply.setFile(activeChar.getLang(), "admin/ipfind.htm");

		final List<Player> players = WorldManager.getInstance().getAllPlayers();
		players.sort(Comparator.comparingLong(Player::getUptime));
		for (Player player : players) {
			client = player.getClient();
			if (client == null) {
				continue;
			}

			if (client.isDetached()) {
				if (!findDisconnected) {
					continue;
				}
			} else {
				if (findDisconnected) {
					continue;
				}

				ip = client.getConnectionAddress().getHostAddress();
				if (!ip.equals(IpAdress)) {
					continue;
				}
			}

			name = player.getName();
			CharactersFound = CharactersFound + 1;
			replyMSG.append("<tr><td width=80><a action=\"bypass -h admin_character_info ");
			replyMSG.append(name);
			replyMSG.append("\">");
			replyMSG.append(name);
			replyMSG.append("</a></td><td width=110>");
			replyMSG.append(ClassListData.getInstance().getClass(player.getClassId()).getClientCode());
			replyMSG.append("</td><td width=40>");
			replyMSG.append(player.getLevel());
			replyMSG.append("</td></tr>");

			if (CharactersFound > 20) {
				break;
			}
		}
		adminReply.replace("%results%", replyMSG.toString());

		final String replyMSG2;

		if (CharactersFound == 0) {
			replyMSG2 = "s. Maybe they got d/c? :)";
		} else if (CharactersFound > 20) {
			adminReply.replace("%number%", " more than " + String.valueOf(CharactersFound));
			replyMSG2 = "s.<br>In order to avoid you a client crash I won't <br1>display results beyond the 20th character.";
		} else if (CharactersFound == 1) {
			replyMSG2 = ".";
		} else {
			replyMSG2 = "s.";
		}
		adminReply.replace("%ip%", IpAdress);
		adminReply.replace("%number%", String.valueOf(CharactersFound));
		adminReply.replace("%end%", replyMSG2);
		activeChar.sendPacket(adminReply);
	}

	/**
	 * @param activeChar
	 * @param characterName
	 * @throws IllegalArgumentException
	 */
	private void findCharactersPerAccount(Player activeChar, String characterName) throws IllegalArgumentException {
		Player player = WorldManager.getInstance().getPlayer(characterName);
		if (player == null) {
			throw new IllegalArgumentException("Player doesn't exist");
		}

		final Map<Integer, String> chars = player.getAccountChars();
		final StringJoiner replyMSG = new StringJoiner("<br1>");
		chars.values().stream().forEachOrdered(replyMSG::add);

		final NpcHtmlMessage adminReply = new NpcHtmlMessage(0, 1);
		adminReply.setFile(activeChar.getLang(), "admin/accountinfo.htm");
		adminReply.replace("%account%", player.getAccountName());
		adminReply.replace("%player%", characterName);
		adminReply.replace("%characters%", replyMSG.toString());
		activeChar.sendPacket(adminReply);
	}

	/**
	 * @param activeChar
	 * @param multibox
	 */
	private void findDualbox(Player activeChar, int multibox) {
		Map<String, List<Player>> ipMap = new HashMap<>();
		String ip = "0.0.0.0";
		L2GameClient client;
		final Map<String, Integer> dualboxIPs = new HashMap<>();

		final List<Player> players = WorldManager.getInstance().getAllPlayers();
		players.sort(Comparator.comparingLong(Player::getUptime));
		for (Player player : players) {
			client = player.getClient();
			if ((client == null) || client.isDetached()) {
				continue;
			}

			ip = client.getConnectionAddress().getHostAddress();
			if (ipMap.get(ip) == null) {
				ipMap.put(ip, new ArrayList<Player>());
			}
			ipMap.get(ip).add(player);

			if (ipMap.get(ip).size() >= multibox) {
				Integer count = dualboxIPs.get(ip);
				if (count == null) {
					dualboxIPs.put(ip, multibox);
				} else {
					dualboxIPs.put(ip, count + 1);
				}
			}
		}

		List<String> keys = new ArrayList<>(dualboxIPs.keySet());
		keys.sort(Comparator.comparing(s -> dualboxIPs.get(s)).reversed());

		final StringBuilder results = new StringBuilder();
		for (String dualboxIP : keys) {
			results.append("<a action=\"bypass -h admin_find_ip " + dualboxIP + "\">" + dualboxIP + " (" + dualboxIPs.get(dualboxIP) + ")</a><br1>");
		}

		final NpcHtmlMessage adminReply = new NpcHtmlMessage(0, 1);
		adminReply.setFile(activeChar.getLang(), "admin/dualbox.htm");
		adminReply.replace("%multibox%", String.valueOf(multibox));
		adminReply.replace("%results%", results.toString());
		adminReply.replace("%strict%", "");
		activeChar.sendPacket(adminReply);
	}

	private void findDualboxStrict(Player activeChar, int multibox) {
		Map<IpPack, List<Player>> ipMap = new HashMap<>();
		L2GameClient client;
		final Map<IpPack, Integer> dualboxIPs = new HashMap<>();

		final List<Player> players = WorldManager.getInstance().getAllPlayers();
		players.sort(Comparator.comparingLong(Player::getUptime));
		for (Player player : players) {
			client = player.getClient();
			if ((client == null) || client.isDetached()) {
				continue;
			}

			IpPack pack = new IpPack(client.getConnectionAddress().getHostAddress(), client.getTrace());
			if (ipMap.get(pack) == null) {
				ipMap.put(pack, new ArrayList<Player>());
			}
			ipMap.get(pack).add(player);

			if (ipMap.get(pack).size() >= multibox) {
				Integer count = dualboxIPs.get(pack);
				if (count == null) {
					dualboxIPs.put(pack, multibox);
				} else {
					dualboxIPs.put(pack, count + 1);
				}
			}
		}

		List<IpPack> keys = new ArrayList<>(dualboxIPs.keySet());
		keys.sort(Comparator.comparing(s -> dualboxIPs.get(s)).reversed());

		final StringBuilder results = new StringBuilder();
		for (IpPack dualboxIP : keys) {
			results.append("<a action=\"bypass -h admin_find_ip " + dualboxIP.ip + "\">" + dualboxIP.ip + " (" + dualboxIPs.get(dualboxIP) + ")</a><br1>");
		}

		final NpcHtmlMessage adminReply = new NpcHtmlMessage(0, 1);
		adminReply.setFile(activeChar.getLang(), "admin/dualbox.htm");
		adminReply.replace("%multibox%", String.valueOf(multibox));
		adminReply.replace("%results%", results.toString());
		adminReply.replace("%strict%", "strict_");
		activeChar.sendPacket(adminReply);
	}

	private final class IpPack {
		String ip;
		int[][] tracert;

		public IpPack(String ip, int[][] tracert) {
			this.ip = ip;
			this.tracert = tracert;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = (prime * result) + ((ip == null) ? 0 : ip.hashCode());
			for (int[] array : tracert) {
				result = (prime * result) + Arrays.hashCode(array);
			}
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			IpPack other = (IpPack) obj;
			if (!getOuterType().equals(other.getOuterType())) {
				return false;
			}
			if (ip == null) {
				if (other.ip != null) {
					return false;
				}
			} else if (!ip.equals(other.ip)) {
				return false;
			}
			for (int i = 0; i < tracert.length; i++) {
				for (int o = 0; o < tracert[0].length; o++) {
					if (tracert[i][o] != other.tracert[i][o]) {
						return false;
					}
				}
			}
			return true;
		}

		private AdminEditChar getOuterType() {
			return AdminEditChar.this;
		}
	}

	private void gatherSummonInfo(Summon target, Player activeChar) {
		final NpcHtmlMessage html = new NpcHtmlMessage(0, 1);
		html.setFile(activeChar.getLang(), "admin/petinfo.htm");
		String name = target.getName();
		html.replace("%name%", name == null ? "N/A" : name);
		html.replace("%level%", Integer.toString(target.getLevel()));
		html.replace("%exp%", Long.toString(target.getStat().getExp()));
		String owner = target.getActingPlayer().getName();
		html.replace("%owner%", " <a action=\"bypass -h admin_character_info " + owner + "\">" + owner + "</a>");
		html.replace("%class%", target.getClass().getSimpleName());
		html.replace("%ai%", target.hasAI() ? String.valueOf(target.getAI().getIntention().name()) : "NULL");
		html.replace("%hp%", (int) target.getStatus().getCurrentHp() + "/" + target.getStat().getMaxHp());
		html.replace("%mp%", (int) target.getStatus().getCurrentMp() + "/" + target.getStat().getMaxMp());
		html.replace("%karma%", Integer.toString(target.getReputation()));
		html.replace("%race%", target.getTemplate().getRace().toString());
		if (target.isPet()) {
			final int objId = target.getActingPlayer().getObjectId();
			html.replace("%inv%", " <a action=\"bypass admin_show_pet_inv " + objId + "\">view</a>");
		} else {
			html.replace("%inv%", "none");
		}
		if (target instanceof PetInstance) {
			html.replace("%food%", ((PetInstance) target).getCurrentFed() + "/" + ((PetInstance) target).getPetLevelData().getPetMaxFeed());
			html.replace("%load%", target.getInventory().getTotalWeight() + "/" + target.getMaxLoad());
		} else {
			html.replace("%food%", "N/A");
			html.replace("%load%", "N/A");
		}
		activeChar.sendPacket(html);
	}

	private void gatherPartyInfo(Player target, Player activeChar) {
		boolean color = true;
		final NpcHtmlMessage html = new NpcHtmlMessage(0, 1);
		html.setFile(activeChar.getLang(), "admin/partyinfo.htm");
		StringBuilder text = new StringBuilder(400);
		for (Player member : target.getParty().getMembers()) {
			if (color) {
				text.append("<tr><td><table width=270 border=0 bgcolor=131210 cellpadding=2><tr><td width=30 align=right>");
			} else {
				text.append("<tr><td><table width=270 border=0 cellpadding=2><tr><td width=30 align=right>");
			}
			text.append(member.getLevel() + "</td><td width=130><a action=\"bypass -h admin_character_info " + member.getName() + "\">" + member.getName() + "</a>");
			text.append("</td><td width=110 align=right>" + member.getClassId().toString() + "</td></tr></table></td></tr>");
			color = !color;
		}
		html.replace("%player%", target.getName());
		html.replace("%party%", text.toString());
		activeChar.sendPacket(html);
	}

	public static void main(String[] args) {
		AdminCommandHandler.getInstance().registerHandler(new AdminEditChar());
	}
}