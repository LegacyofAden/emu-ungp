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
package org.l2junity.gameserver.network.packets.c2s;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.core.configs.AdminConfig;
import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.core.configs.L2JModsConfig;
import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.data.HtmRepository;
import org.l2junity.gameserver.data.sql.impl.AnnouncementsTable;
import org.l2junity.gameserver.data.sql.impl.CharacterQuests;
import org.l2junity.gameserver.data.xml.impl.AdminData;
import org.l2junity.gameserver.data.xml.impl.BeautyShopData;
import org.l2junity.gameserver.data.xml.impl.ClanHallData;
import org.l2junity.gameserver.data.xml.impl.SkillTreesData;
import org.l2junity.gameserver.enums.PcCafeConsumeType;
import org.l2junity.gameserver.enums.Race;
import org.l2junity.gameserver.enums.SubclassInfoType;
import org.l2junity.gameserver.instancemanager.*;
import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.PcCondOverride;
import org.l2junity.gameserver.model.TeleportWhereType;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.entity.*;
import org.l2junity.gameserver.model.instancezone.Instance;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.variables.PlayerVariables;
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.model.zone.ZoneId;
import org.l2junity.gameserver.network.Disconnection;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.*;
import org.l2junity.gameserver.network.packets.s2c.ability.ExAcquireAPSkillList;
import org.l2junity.gameserver.network.packets.s2c.friend.L2FriendList;
import org.l2junity.gameserver.network.packets.s2c.onedayreward.ExOneDayReceiveRewardList;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.gameserver.util.BuilderUtil;

/**
 * Enter World Packet Handler
 * <p>
 * <p>
 * 0000: 03
 * <p>
 * packet format rev87 bddddbdcccccccccccccccccccc
 * <p>
 */
@Slf4j
public class EnterWorld extends GameClientPacket {
	private final int[][] tracert = new int[5][4];

	@Override
	public void readImpl() {
		for (int i = 0; i < 5; i++) {
			for (int o = 0; o < 4; o++) {
				tracert[i][o] = readC();
			}
		}
		readD(); // Unknown Value
		readD(); // Unknown Value
		readD(); // Unknown Value
		readD(); // Unknown Value
		readB(64); // Unknown Byte Array
		readD(); // Unknown Value
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			log.warn("EnterWorld failed! activeChar returned 'null'.");
			Disconnection.of(getClient()).defaultSequence(false);
			return;
		}

		String[] address = new String[5];
		for (int i = 0; i < 5; i++) {
			address[i] = tracert[i][0] + "." + tracert[i][1] + "." + tracert[i][2] + "." + tracert[i][3];
		}
		getClient().setTrace(tracert);

		activeChar.broadcastUserInfo();

		// Restore to instanced area if enabled
		if (GeneralConfig.RESTORE_PLAYER_INSTANCE) {
			final PlayerVariables vars = activeChar.getVariables();
			final Instance instance = InstanceManager.getInstance().getPlayerInstance(activeChar, false);
			if ((instance != null) && (instance.getId() == vars.getInt(PlayerVariables.INSTANCE_RESTORE, 0))) {
				activeChar.setInstance(instance);
			}
			vars.remove(PlayerVariables.INSTANCE_RESTORE);
		}

		if (WorldManager.getInstance().getObject(activeChar.getObjectId()) != null) {
			if (GeneralConfig.DEBUG) {
				log.warn("User already exists in Object ID map! User " + activeChar.getName() + " is a character clone.");
			}
		}

		// Apply special GM properties to the GM when entering
		if (activeChar.isGM()) {
			gmStartupProcess:
			{
				if (AdminConfig.GM_STARTUP_BUILDER_HIDE && AdminData.getInstance().hasAccess("admin_hide", activeChar.getAccessLevel())) {
					BuilderUtil.setHiding(activeChar, true);

					BuilderUtil.sendSysMessage(activeChar, "hide is default for builder.");
					BuilderUtil.sendSysMessage(activeChar, "FriendAddOff is default for builder.");
					BuilderUtil.sendSysMessage(activeChar, "whisperoff is default for builder.");

					// It isn't recommend to use the below custom L2J GMStartup functions together with retail-like GMStartupBuilderHide, so breaking the process at that stage.
					break gmStartupProcess;
				}

				if (AdminConfig.GM_STARTUP_INVULNERABLE && AdminData.getInstance().hasAccess("admin_invul", activeChar.getAccessLevel())) {
					activeChar.setIsInvul(true);
				}

				if (AdminConfig.GM_STARTUP_INVISIBLE && AdminData.getInstance().hasAccess("admin_invisible", activeChar.getAccessLevel())) {
					activeChar.setInvisible(true);
				}

				if (AdminConfig.GM_STARTUP_SILENCE && AdminData.getInstance().hasAccess("admin_silence", activeChar.getAccessLevel())) {
					activeChar.setSilenceMode(true);
				}

				if (AdminConfig.GM_STARTUP_DIET_MODE && AdminData.getInstance().hasAccess("admin_diet", activeChar.getAccessLevel())) {
					activeChar.setDietMode(true);
					activeChar.refreshOverloaded(true);
				}

				if (AdminConfig.GM_STARTUP_AUTO_LIST && AdminData.getInstance().hasAccess("admin_gmliston", activeChar.getAccessLevel())) {
					AdminData.getInstance().addGm(activeChar, false);
				} else {
					AdminData.getInstance().addGm(activeChar, true);
				}
			}

			if (AdminConfig.GM_GIVE_SPECIAL_SKILLS) {
				SkillTreesData.getInstance().addSkills(activeChar, false);
			}

			if (AdminConfig.GM_GIVE_SPECIAL_AURA_SKILLS) {
				SkillTreesData.getInstance().addSkills(activeChar, true);
			}
		}

		// Set dead status if applies
		if (activeChar.getCurrentHp() < 0.5) {
			activeChar.setIsDead(true);
		}

		boolean showClanNotice = false;

		// Clan related checks are here
		final Clan clan = activeChar.getClan();
		if (clan != null) {
			notifyClanMembers(activeChar);
			notifySponsorOrApprentice(activeChar);

			for (Siege siege : SiegeManager.getInstance().getSieges()) {
				if (!siege.isInProgress()) {
					continue;
				}

				if (siege.checkIsAttacker(clan)) {
					activeChar.setSiegeState((byte) 1);
					activeChar.setSiegeSide(siege.getCastle().getResidenceId());
				} else if (siege.checkIsDefender(clan)) {
					activeChar.setSiegeState((byte) 2);
					activeChar.setSiegeSide(siege.getCastle().getResidenceId());
				}
			}

			for (FortSiege siege : FortSiegeManager.getInstance().getSieges()) {
				if (!siege.isInProgress()) {
					continue;
				}

				if (siege.checkIsAttacker(clan)) {
					activeChar.setSiegeState((byte) 1);
					activeChar.setSiegeSide(siege.getFort().getResidenceId());
				} else if (siege.checkIsDefender(clan)) {
					activeChar.setSiegeState((byte) 2);
					activeChar.setSiegeSide(siege.getFort().getResidenceId());
				}
			}

			// Residential skills support
			if (activeChar.getClan().getCastleId() > 0) {
				CastleManager.getInstance().getCastleByOwner(clan).giveResidentialSkills(activeChar);
			}

			if (activeChar.getClan().getFortId() > 0) {
				FortManager.getInstance().getFortByOwner(clan).giveResidentialSkills(activeChar);
			}

			showClanNotice = clan.isNoticeEnabled();
		}

		if (PlayerConfig.ENABLE_VITALITY) {
			activeChar.sendPacket(new ExVitalityEffectInfo(activeChar));
		}

		// Send Macro List
		activeChar.getMacros().sendAllMacros();

		// Send Teleport Bookmark List
		getClient().sendPacket(new ExGetBookMarkInfoPacket(activeChar));

		// Send Item List
		getClient().sendPacket(new ItemList(activeChar, false));

		// Send Quest Item List
		getClient().sendPacket(new ExQuestItemList(activeChar));

		// Send Adena and Inventory Count
		getClient().sendPacket(new ExAdenaInvenCount(activeChar));

		// Send Shortcuts
		getClient().sendPacket(new ShortCutInit(activeChar));

		// Send Action list
		activeChar.sendPacket(ExBasicActionList.STATIC_PACKET);

		// Send blank skill list
		activeChar.sendPacket(new SkillList());

		// Send castle state.
		for (Castle castle : CastleManager.getInstance().getCastles()) {
			activeChar.sendPacket(new ExCastleState(castle));
		}

		// Send GG check
		// activeChar.queryGameGuard();

		// Send Dye Information
		activeChar.sendPacket(new HennaInfo(activeChar));

		// Send Skill list
		activeChar.sendSkillList();

		// Send EtcStatusUpdate
		activeChar.sendPacket(new EtcStatusUpdate(activeChar));

		// Clan packets
		if (clan != null) {
			clan.broadcastToOnlineMembers(new PledgeShowMemberListUpdate(activeChar));
			PledgeShowMemberListAll.sendAllTo(activeChar);
			clan.broadcastToOnlineMembers(new ExPledgeCount(clan));
			activeChar.sendPacket(new PledgeSkillList(clan));
			activeChar.sendPacket(new PledgeShowInfoUpdate(clan));
			final ClanHall ch = ClanHallData.getInstance().getClanHallByClan(clan);
			if ((ch != null) && (ch.getCostFailDay() > 0)) {
				final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.PAYMENT_FOR_YOUR_CLAN_HALL_HAS_NOT_BEEN_MADE_PLEASE_MAKE_PAYMENT_TO_YOUR_CLAN_WAREHOUSE_BY_S1_TOMORROW);
				sm.addInt(ch.getLease());
				activeChar.sendPacket(sm);
			}
		} else {
			activeChar.sendPacket(ExPledgeWaitingListAlarm.STATIC_PACKET);
		}

		// Send SubClass Info
		activeChar.sendPacket(new ExSubjobInfo(activeChar, SubclassInfoType.NO_CHANGES));

		// Send Inventory Info
		activeChar.sendPacket(new ExUserInfoInvenWeight(activeChar));

		// Send Adena / Inventory Count Info
		activeChar.sendPacket(new ExAdenaInvenCount(activeChar));

		// Send Equipped Items
		activeChar.sendPacket(new ExUserInfoEquipSlot(activeChar));

		// Send Unread Mail Count
		if (MailManager.getInstance().hasUnreadPost(activeChar)) {
			activeChar.sendPacket(new ExUnReadMailCount(activeChar));
		}

		// Load player quests and send QuestList packet
		CharacterQuests.getInstance().loadPlayerQuests(activeChar);
		activeChar.sendPacket(new QuestList(activeChar));

		if (PlayerConfig.PLAYER_SPAWN_PROTECTION > 0) {
			activeChar.setProtection(true);
		}

		activeChar.spawnMe(activeChar.getX(), activeChar.getY(), activeChar.getZ());

		activeChar.getInventory().applyItemSkills();

		if (activeChar.isCursedWeaponEquipped()) {
			CursedWeaponsManager.getInstance().getCursedWeapon(activeChar.getCursedWeaponEquippedId()).cursedOnLogin();
		}

		// Expand Skill
		activeChar.sendPacket(new ExStorageMaxCount(activeChar));

		// Friend list
		getClient().sendPacket(new L2FriendList(activeChar));

		if (PlayerConfig.SHOW_GOD_VIDEO_INTRO && activeChar.getVariables().getBoolean("intro_god_video", false)) {
			activeChar.getVariables().remove("intro_god_video");
			if (activeChar.getRace() == Race.ERTHEIA) {
				activeChar.sendPacket(ExShowUsm.ERTHEIA_INTRO_FOR_ERTHEIA);
			} else {
				activeChar.sendPacket(ExShowUsm.ERTHEIA_INTRO_FOR_OTHERS);
			}
		}

		activeChar.sendPacket(SystemMessageId.WELCOME_TO_THE_WORLD_OF_LINEAGE_II);

		AnnouncementsTable.getInstance().showAnnouncements(activeChar);

		if (showClanNotice) {
			final NpcHtmlMessage notice = new NpcHtmlMessage();
			notice.setFile(activeChar.getLang(), "clanNotice.htm");
			notice.replace("%clan_name%", activeChar.getClan().getName());
			notice.replace("%notice_text%", activeChar.getClan().getNotice());
			notice.disableValidation();
			getClient().sendPacket(notice);
		}

		if (GeneralConfig.SERVER_NEWS) {
			String serverNews = HtmRepository.getInstance().getCustomHtm("servnews.htm");
			if (serverNews != null) {
				getClient().sendPacket(new NpcHtmlMessage(0, 1, serverNews));
			}
		}

		if (PlayerConfig.PETITIONING_ALLOWED) {
			PetitionManager.getInstance().checkPetitionMessages(activeChar);
		}

		if (activeChar.isAlikeDead()) // dead or fake dead
		{
			// no broadcast needed since the player will already spawn dead to others
			getClient().sendPacket(new Die(activeChar));
		}

		activeChar.onPlayerEnter();

		getClient().sendPacket(new SkillCoolTime(activeChar));
		getClient().sendPacket(new ExVoteSystemInfo(activeChar));

		for (ItemInstance i : activeChar.getInventory().getItems()) {
			if (i.isTimeLimitedItem()) {
				i.scheduleLifeTimeTask();
			}
			if (i.isShadowItem() && i.isEquipped()) {
				i.decreaseMana(false);
			}
		}

		for (ItemInstance i : activeChar.getWarehouse().getItems()) {
			if (i.isTimeLimitedItem()) {
				i.scheduleLifeTimeTask();
			}
		}

		if (activeChar.getClanJoinExpiryTime() > System.currentTimeMillis()) {
			activeChar.sendPacket(SystemMessageId.YOU_HAVE_RECENTLY_BEEN_DISMISSED_FROM_A_CLAN_YOU_ARE_NOT_ALLOWED_TO_JOIN_ANOTHER_CLAN_FOR_24_HOURS);
		}

		// remove combat flag before teleporting
		if (activeChar.getInventory().getItemByItemId(9819) != null) {
			final Fort fort = FortManager.getInstance().getFort(activeChar);
			if (fort != null) {
				FortSiegeManager.getInstance().dropCombatFlag(activeChar, fort.getResidenceId());
			} else {
				int slot = activeChar.getInventory().getSlotFromItem(activeChar.getInventory().getItemByItemId(9819));
				activeChar.getInventory().unEquipItemInBodySlot(slot);
				activeChar.destroyItem("CombatFlag", activeChar.getInventory().getItemByItemId(9819), null, true);
			}
		}

		// Attacker or spectator logging in to a siege zone.
		// Actually should be checked for inside castle only?
		if (!activeChar.canOverrideCond(PcCondOverride.ZONE_CONDITIONS) && activeChar.isInsideZone(ZoneId.SIEGE) && (!activeChar.isInSiege() || (activeChar.getSiegeState() < 2))) {
			activeChar.teleToLocation(TeleportWhereType.TOWN);
		}

		if (GeneralConfig.ALLOW_MAIL) {
			if (MailManager.getInstance().hasUnreadPost(activeChar)) {
				getClient().sendPacket(ExNoticePostArrived.valueOf(false));
			}
		}

		if (L2JModsConfig.WELCOME_MESSAGE_ENABLED) {
			activeChar.sendPacket(new ExShowScreenMessage(L2JModsConfig.WELCOME_MESSAGE_TEXT, L2JModsConfig.WELCOME_MESSAGE_TIME));
		}

		int birthday = activeChar.checkBirthDay();
		if (birthday == 0) {
			activeChar.sendPacket(SystemMessageId.HAPPY_BIRTHDAY_ALEGRIA_HAS_SENT_YOU_A_BIRTHDAY_GIFT);
			// activeChar.sendPacket(new ExBirthdayPopup()); Removed in H5?
		} else if (birthday != -1) {
			SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.THERE_ARE_S1_DAYS_REMAINING_UNTIL_YOUR_BIRTHDAY_ON_YOUR_BIRTHDAY_YOU_WILL_RECEIVE_A_GIFT_THAT_ALEGRIA_HAS_CAREFULLY_PREPARED);
			sm.addString(Integer.toString(birthday));
			activeChar.sendPacket(sm);
		}

		if (!activeChar.getPremiumItemList().isEmpty()) {
			activeChar.sendPacket(ExNotifyPremiumItem.STATIC_PACKET);
		}

		activeChar.broadcastUserInfo();

		if (BeautyShopData.getInstance().hasBeautyData(activeChar.getRace(), activeChar.getAppearance().getSexType())) {
			activeChar.sendPacket(new ExBeautyItemList(activeChar));
		}

		activeChar.sendPacket(new ExAcquireAPSkillList(activeChar));
		activeChar.sendPacket(new ExWorldChatCnt(activeChar));
		activeChar.sendPacket(new ExConnectedTimeAndGettableReward(activeChar));
		activeChar.sendPacket(new ExOneDayReceiveRewardList(activeChar, true));

		// Handle soulshots, disable all on EnterWorld
		activeChar.sendPacket(new ExAutoSoulShot(0, true, 0));
		activeChar.sendPacket(new ExAutoSoulShot(0, true, 1));
		activeChar.sendPacket(new ExAutoSoulShot(0, true, 2));
		activeChar.sendPacket(new ExAutoSoulShot(0, true, 3));

		// Re-send abnormal visual effects (more specifically for items such as talismans where packet is sent before character appears to world).
		if (!activeChar.getEffectList().getCurrentAbnormalVisualEffects().isEmpty()) {
			activeChar.updateAbnormalVisualEffects();
		}

		activeChar.sendPacket(new ExPCCafePointInfo(activeChar.getPcCafePoints(), 0, PcCafeConsumeType.NORMAL));
	}

	/**
	 * @param activeChar
	 */
	private void notifyClanMembers(Player activeChar) {
		final Clan clan = activeChar.getClan();
		if (clan != null) {
			clan.getClanMember(activeChar.getObjectId()).setPlayerInstance(activeChar);

			final SystemMessage msg = SystemMessage.getSystemMessage(SystemMessageId.CLAN_MEMBER_S1_HAS_LOGGED_INTO_GAME);
			msg.addString(activeChar.getName());
			clan.broadcastToOtherOnlineMembers(msg, activeChar);
			clan.broadcastToOtherOnlineMembers(new PledgeShowMemberListUpdate(activeChar), activeChar);
		}
	}

	/**
	 * @param activeChar
	 */
	private void notifySponsorOrApprentice(Player activeChar) {
		if (activeChar.getSponsor() != 0) {
			final Player sponsor = WorldManager.getInstance().getPlayer(activeChar.getSponsor());
			if (sponsor != null) {
				final SystemMessage msg = SystemMessage.getSystemMessage(SystemMessageId.YOUR_APPRENTICE_S1_HAS_LOGGED_IN);
				msg.addString(activeChar.getName());
				sponsor.sendPacket(msg);
			}
		} else if (activeChar.getApprentice() != 0) {
			final Player apprentice = WorldManager.getInstance().getPlayer(activeChar.getApprentice());
			if (apprentice != null) {
				final SystemMessage msg = SystemMessage.getSystemMessage(SystemMessageId.YOUR_SPONSOR_C1_HAS_LOGGED_IN);
				msg.addString(activeChar.getName());
				apprentice.sendPacket(msg);
			}
		}
	}
}