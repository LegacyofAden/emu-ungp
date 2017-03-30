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
package org.l2junity.gameserver.model.events;

import org.l2junity.commons.util.ArrayUtil;
import org.l2junity.gameserver.model.events.impl.IBaseEvent;
import org.l2junity.gameserver.model.events.impl.OnDayNightChange;
import org.l2junity.gameserver.model.events.impl.ceremonyofchaos.OnCeremonyOfChaosMatchResult;
import org.l2junity.gameserver.model.events.impl.character.OnCreatureAttack;
import org.l2junity.gameserver.model.events.impl.character.OnCreatureAttackAvoid;
import org.l2junity.gameserver.model.events.impl.character.OnCreatureAttacked;
import org.l2junity.gameserver.model.events.impl.character.OnCreatureDamageDealt;
import org.l2junity.gameserver.model.events.impl.character.OnCreatureDamageReceived;
import org.l2junity.gameserver.model.events.impl.character.OnCreatureDeath;
import org.l2junity.gameserver.model.events.impl.character.OnCreatureHpChange;
import org.l2junity.gameserver.model.events.impl.character.OnCreatureKilled;
import org.l2junity.gameserver.model.events.impl.character.OnCreatureSee;
import org.l2junity.gameserver.model.events.impl.character.OnCreatureSkillFinishCast;
import org.l2junity.gameserver.model.events.impl.character.OnCreatureSkillUse;
import org.l2junity.gameserver.model.events.impl.character.OnCreatureSkillUsed;
import org.l2junity.gameserver.model.events.impl.character.OnCreatureTeleport;
import org.l2junity.gameserver.model.events.impl.character.OnCreatureTeleported;
import org.l2junity.gameserver.model.events.impl.character.OnCreatureZoneEnter;
import org.l2junity.gameserver.model.events.impl.character.OnCreatureZoneExit;
import org.l2junity.gameserver.model.events.impl.character.npc.OnAttackableAggroRangeEnter;
import org.l2junity.gameserver.model.events.impl.character.npc.OnAttackableAttack;
import org.l2junity.gameserver.model.events.impl.character.npc.OnAttackableFactionCall;
import org.l2junity.gameserver.model.events.impl.character.npc.OnAttackableHate;
import org.l2junity.gameserver.model.events.impl.character.npc.OnAttackableKill;
import org.l2junity.gameserver.model.events.impl.character.npc.OnNpcCanBeSeen;
import org.l2junity.gameserver.model.events.impl.character.npc.OnNpcCreatureSee;
import org.l2junity.gameserver.model.events.impl.character.npc.OnNpcDespawn;
import org.l2junity.gameserver.model.events.impl.character.npc.OnNpcEventReceived;
import org.l2junity.gameserver.model.events.impl.character.npc.OnNpcFirstTalk;
import org.l2junity.gameserver.model.events.impl.character.npc.OnNpcManorBypass;
import org.l2junity.gameserver.model.events.impl.character.npc.OnNpcMenuSelect;
import org.l2junity.gameserver.model.events.impl.character.npc.OnNpcMoveFinished;
import org.l2junity.gameserver.model.events.impl.character.npc.OnNpcMoveNodeArrived;
import org.l2junity.gameserver.model.events.impl.character.npc.OnNpcMoveRouteFinished;
import org.l2junity.gameserver.model.events.impl.character.npc.OnNpcSkillFinished;
import org.l2junity.gameserver.model.events.impl.character.npc.OnNpcSkillSee;
import org.l2junity.gameserver.model.events.impl.character.npc.OnNpcSpawn;
import org.l2junity.gameserver.model.events.impl.character.npc.OnNpcTeleport;
import org.l2junity.gameserver.model.events.impl.character.npc.OnNpcTeleportRequest;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayableExpChanged;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerAbilityPointsChanged;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerAugment;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerBypass;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerCallToChangeClass;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerChangeToAwakenedClass;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerChat;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerClanCreate;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerClanDestroy;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerClanJoin;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerClanLeaderChange;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerClanLeft;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerClanLvlUp;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerClanWHItemAdd;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerClanWHItemDestroy;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerClanWHItemTransfer;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerCreate;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerDeathExpPenalty;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerDeathPenalty;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerDelete;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerDlgAnswer;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerEquipItem;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerFameChanged;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerFishing;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerHennaAdd;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerHennaRemove;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerItemAdd;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerItemDestroy;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerItemDrop;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerItemPickup;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerItemTransfer;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerLevelChanged;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerLogin;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerLogout;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerMenteeAdd;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerMenteeLeft;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerMenteeRemove;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerMenteeStatus;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerMentorStatus;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerMoveRequest;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerPKChanged;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerPressTutorialMark;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerProfessionChange;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerPvPChanged;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerPvPKill;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerQuestAbort;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerQuestComplete;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerReputationChanged;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerRequestAcquireSkillInfo;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerRestore;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerSelect;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerSkillLearn;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerSocialAction;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerSubChange;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerSummonSpawn;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerSummonTalk;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerTransform;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerVitalityConsume;
import org.l2junity.gameserver.model.events.impl.character.player.OnTrapAction;
import org.l2junity.gameserver.model.events.impl.clan.OnClanWarFinish;
import org.l2junity.gameserver.model.events.impl.clan.OnClanWarStart;
import org.l2junity.gameserver.model.events.impl.instance.OnInstanceCreated;
import org.l2junity.gameserver.model.events.impl.instance.OnInstanceDestroy;
import org.l2junity.gameserver.model.events.impl.instance.OnInstanceEnter;
import org.l2junity.gameserver.model.events.impl.instance.OnInstanceFinish;
import org.l2junity.gameserver.model.events.impl.instance.OnInstanceLeave;
import org.l2junity.gameserver.model.events.impl.instance.OnInstanceStatusChange;
import org.l2junity.gameserver.model.events.impl.item.OnItemBypassEvent;
import org.l2junity.gameserver.model.events.impl.item.OnItemCreate;
import org.l2junity.gameserver.model.events.impl.item.OnItemTalk;
import org.l2junity.gameserver.model.events.impl.olympiad.OnOlympiadMatchResult;
import org.l2junity.gameserver.model.events.impl.restriction.CanPlayerAttack;
import org.l2junity.gameserver.model.events.impl.restriction.CanPlayerInviteToAlly;
import org.l2junity.gameserver.model.events.impl.restriction.CanPlayerInviteToClan;
import org.l2junity.gameserver.model.events.impl.restriction.CanPlayerInviteToParty;
import org.l2junity.gameserver.model.events.impl.restriction.CanPlayerJoinSiege;
import org.l2junity.gameserver.model.events.impl.restriction.CanPlayerLearnSkill;
import org.l2junity.gameserver.model.events.impl.restriction.CanPlayerLeaveParty;
import org.l2junity.gameserver.model.events.impl.restriction.CanPlayerLogout;
import org.l2junity.gameserver.model.events.impl.restriction.CanPlayerPickUp;
import org.l2junity.gameserver.model.events.impl.restriction.CanPlayerRequestRevive;
import org.l2junity.gameserver.model.events.impl.restriction.CanPlayerStandUp;
import org.l2junity.gameserver.model.events.impl.restriction.CanPlayerTrade;
import org.l2junity.gameserver.model.events.impl.restriction.CanPlayerUseAction;
import org.l2junity.gameserver.model.events.impl.restriction.CanPlayerUseItem;
import org.l2junity.gameserver.model.events.impl.restriction.CanPlayerUseSkill;
import org.l2junity.gameserver.model.events.impl.restriction.GetPlayerCombatState;
import org.l2junity.gameserver.model.events.impl.restriction.IsPlayerInvul;
import org.l2junity.gameserver.model.events.impl.restriction.IsWorldObjectVisibleFor;
import org.l2junity.gameserver.model.events.impl.server.OnDailyReset;
import org.l2junity.gameserver.model.events.impl.server.OnPacketReceived;
import org.l2junity.gameserver.model.events.impl.server.OnPacketSent;
import org.l2junity.gameserver.model.events.impl.sieges.OnCastleSiegeFinish;
import org.l2junity.gameserver.model.events.impl.sieges.OnCastleSiegeOwnerChange;
import org.l2junity.gameserver.model.events.impl.sieges.OnCastleSiegeStart;
import org.l2junity.gameserver.model.events.impl.sieges.OnFortSiegeFinish;
import org.l2junity.gameserver.model.events.impl.sieges.OnFortSiegeStart;
import org.l2junity.gameserver.model.events.returns.BooleanReturn;
import org.l2junity.gameserver.model.events.returns.ChatFilterReturn;
import org.l2junity.gameserver.model.events.returns.CombatStateReturn;
import org.l2junity.gameserver.model.events.returns.DamageReturn;
import org.l2junity.gameserver.model.events.returns.IntegerReturn;
import org.l2junity.gameserver.model.events.returns.LocationReturn;
import org.l2junity.gameserver.model.events.returns.LongReturn;
import org.l2junity.gameserver.model.events.returns.TerminateReturn;

/**
 * @author UnAfraid
 */
public enum EventType
{
	// Attackable events
	ON_ATTACKABLE_AGGRO_RANGE_ENTER(OnAttackableAggroRangeEnter.class, void.class),
	ON_ATTACKABLE_ATTACK(OnAttackableAttack.class, void.class),
	ON_ATTACKABLE_FACTION_CALL(OnAttackableFactionCall.class, void.class),
	ON_ATTACKABLE_KILL(OnAttackableKill.class, void.class),
	
	// Castle events
	ON_CASTLE_SIEGE_FINISH(OnCastleSiegeFinish.class, void.class),
	ON_CASTLE_SIEGE_OWNER_CHANGE(OnCastleSiegeOwnerChange.class, void.class),
	ON_CASTLE_SIEGE_START(OnCastleSiegeStart.class, void.class),
	
	// Clan events
	ON_CLAN_WAR_FINISH(OnClanWarFinish.class, void.class),
	ON_CLAN_WAR_START(OnClanWarStart.class, void.class),
	
	// Creature events
	ON_CREATURE_ATTACK(OnCreatureAttack.class, void.class, void.class),
	ON_CREATURE_ATTACK_AVOID(OnCreatureAttackAvoid.class, void.class, void.class),
	ON_CREATURE_ATTACKED(OnCreatureAttacked.class, void.class, void.class),
	ON_CREATURE_DAMAGE_RECEIVED(OnCreatureDamageReceived.class, void.class, DamageReturn.class),
	ON_CREATURE_DAMAGE_DEALT(OnCreatureDamageDealt.class, void.class),
	ON_CREATURE_HP_CHANGE(OnCreatureHpChange.class, void.class),
	ON_CREATURE_DEATH(OnCreatureDeath.class, void.class),
	ON_CREATURE_KILLED(OnCreatureKilled.class, void.class, TerminateReturn.class),
	ON_CREATURE_SEE(OnCreatureSee.class, void.class),
	ON_CREATURE_SKILL_USE(OnCreatureSkillUse.class, void.class, TerminateReturn.class),
	ON_CREATURE_SKILL_USED(OnCreatureSkillUsed.class, void.class),
	ON_CREATURE_SKILL_FINISH_CAST(OnCreatureSkillFinishCast.class, void.class),
	ON_CREATURE_TELEPORT(OnCreatureTeleport.class, void.class, LocationReturn.class),
	ON_CREATURE_TELEPORTED(OnCreatureTeleported.class, void.class),
	ON_CREATURE_ZONE_ENTER(OnCreatureZoneEnter.class, void.class),
	ON_CREATURE_ZONE_EXIT(OnCreatureZoneExit.class, void.class),
	
	// Fortress events
	ON_FORT_SIEGE_FINISH(OnFortSiegeFinish.class, void.class),
	ON_FORT_SIEGE_START(OnFortSiegeStart.class, void.class),
	
	// Item events
	ON_ITEM_BYPASS_EVENT(OnItemBypassEvent.class, void.class),
	ON_ITEM_CREATE(OnItemCreate.class, void.class),
	ON_ITEM_TALK(OnItemTalk.class, void.class),
	
	// Npcs events
	ON_NPC_CAN_BE_SEEN(OnNpcCanBeSeen.class, void.class, TerminateReturn.class),
	ON_NPC_CREATURE_SEE(OnNpcCreatureSee.class, void.class),
	ON_NPC_EVENT_RECEIVED(OnNpcEventReceived.class, void.class),
	ON_NPC_FIRST_TALK(OnNpcFirstTalk.class, void.class),
	ON_NPC_HATE(OnAttackableHate.class, void.class, TerminateReturn.class),
	ON_NPC_MOVE_FINISHED(OnNpcMoveFinished.class, void.class),
	ON_NPC_MOVE_NODE_ARRIVED(OnNpcMoveNodeArrived.class, void.class),
	ON_NPC_MOVE_ROUTE_FINISHED(OnNpcMoveRouteFinished.class, void.class),
	ON_NPC_QUEST_START(null, void.class),
	ON_NPC_SKILL_FINISHED(OnNpcSkillFinished.class, void.class),
	ON_NPC_SKILL_SEE(OnNpcSkillSee.class, void.class),
	ON_NPC_SPAWN(OnNpcSpawn.class, void.class),
	ON_NPC_TALK(null, void.class),
	ON_NPC_TELEPORT(OnNpcTeleport.class, void.class),
	ON_NPC_MANOR_BYPASS(OnNpcManorBypass.class, void.class),
	ON_NPC_MENU_SELECT(OnNpcMenuSelect.class, void.class),
	ON_NPC_DESPAWN(OnNpcDespawn.class, void.class),
	ON_NPC_TELEPORT_REQUEST(OnNpcTeleportRequest.class, void.class, TerminateReturn.class),
	
	// Olympiad events
	ON_OLYMPIAD_MATCH_RESULT(OnOlympiadMatchResult.class, void.class),
	
	// Ceremony of Chaos events
	ON_CEREMONY_OF_CHAOS_MATCH_RESULT(OnCeremonyOfChaosMatchResult.class, void.class),
	
	// Playable events
	ON_PLAYABLE_EXP_CHANGED(OnPlayableExpChanged.class, void.class, TerminateReturn.class),
	
	// Player events
	ON_PLAYER_AUGMENT(OnPlayerAugment.class, void.class),
	ON_PLAYER_BYPASS(OnPlayerBypass.class, void.class, TerminateReturn.class),
	ON_PLAYER_CALL_TO_CHANGE_CLASS(OnPlayerCallToChangeClass.class, void.class),
	ON_PLAYER_CHAT(OnPlayerChat.class, void.class, ChatFilterReturn.class),
	ON_PLAYER_ABILITY_POINTS_CHANGED(OnPlayerAbilityPointsChanged.class, void.class),
	// Clan events
	ON_PLAYER_CLAN_CREATE(OnPlayerClanCreate.class, void.class),
	ON_PLAYER_CLAN_DESTROY(OnPlayerClanDestroy.class, void.class),
	ON_PLAYER_CLAN_JOIN(OnPlayerClanJoin.class, void.class),
	ON_PLAYER_CLAN_LEADER_CHANGE(OnPlayerClanLeaderChange.class, void.class),
	ON_PLAYER_CLAN_LEFT(OnPlayerClanLeft.class, void.class),
	ON_PLAYER_CLAN_LVLUP(OnPlayerClanLvlUp.class, void.class),
	// Clan warehouse events
	ON_PLAYER_CLAN_WH_ITEM_ADD(OnPlayerClanWHItemAdd.class, void.class),
	ON_PLAYER_CLAN_WH_ITEM_DESTROY(OnPlayerClanWHItemDestroy.class, void.class),
	ON_PLAYER_CLAN_WH_ITEM_TRANSFER(OnPlayerClanWHItemTransfer.class, void.class),
	ON_PLAYER_CREATE(OnPlayerCreate.class, void.class),
	ON_PLAYER_DELETE(OnPlayerDelete.class, void.class),
	ON_PLAYER_DLG_ANSWER(OnPlayerDlgAnswer.class, void.class, TerminateReturn.class),
	ON_PLAYER_EQUIP_ITEM(OnPlayerEquipItem.class, void.class),
	ON_PLAYER_FAME_CHANGED(OnPlayerFameChanged.class, void.class),
	ON_PLAYER_FISHING(OnPlayerFishing.class, void.class),
	// Henna events
	ON_PLAYER_HENNA_ADD(OnPlayerHennaAdd.class, void.class),
	ON_PLAYER_HENNA_REMOVE(OnPlayerHennaRemove.class, void.class),
	// Inventory events
	ON_PLAYER_ITEM_ADD(OnPlayerItemAdd.class, void.class),
	ON_PLAYER_ITEM_DESTROY(OnPlayerItemDestroy.class, void.class),
	ON_PLAYER_ITEM_DROP(OnPlayerItemDrop.class, void.class),
	ON_PLAYER_ITEM_PICKUP(OnPlayerItemPickup.class, void.class),
	ON_PLAYER_ITEM_TRANSFER(OnPlayerItemTransfer.class, void.class),
	// Mentoring events
	ON_PLAYER_MENTEE_ADD(OnPlayerMenteeAdd.class, void.class),
	ON_PLAYER_MENTEE_LEFT(OnPlayerMenteeLeft.class, void.class),
	ON_PLAYER_MENTEE_REMOVE(OnPlayerMenteeRemove.class, void.class),
	ON_PLAYER_MENTEE_STATUS(OnPlayerMenteeStatus.class, void.class),
	ON_PLAYER_MENTOR_STATUS(OnPlayerMentorStatus.class, void.class),
	// Other player events
	ON_PLAYER_REPUTATION_CHANGED(OnPlayerReputationChanged.class, void.class),
	ON_PLAYER_LEVEL_CHANGED(OnPlayerLevelChanged.class, void.class),
	ON_PLAYER_LOGIN(OnPlayerLogin.class, void.class),
	ON_PLAYER_LOGOUT(OnPlayerLogout.class, void.class),
	ON_PLAYER_PK_CHANGED(OnPlayerPKChanged.class, void.class),
	ON_PLAYER_PRESS_TUTORIAL_MARK(OnPlayerPressTutorialMark.class, void.class),
	ON_PLAYER_MOVE_REQUEST(OnPlayerMoveRequest.class, void.class, TerminateReturn.class),
	ON_PLAYER_PROFESSION_CHANGE(OnPlayerProfessionChange.class, void.class),
	ON_PLAYER_CHANGE_TO_AWAKENED_CLASS(OnPlayerChangeToAwakenedClass.class, void.class),
	ON_PLAYER_PVP_CHANGED(OnPlayerPvPChanged.class, void.class),
	ON_PLAYER_PVP_KILL(OnPlayerPvPKill.class, void.class),
	ON_PLAYER_REQUEST_ACQUIRE_SKILL_INFO(OnPlayerRequestAcquireSkillInfo.class, void.class, TerminateReturn.class),
	ON_PLAYER_RESTORE(OnPlayerRestore.class, void.class),
	ON_PLAYER_SELECT(OnPlayerSelect.class, void.class, TerminateReturn.class),
	ON_PLAYER_SOCIAL_ACTION(OnPlayerSocialAction.class, void.class),
	ON_PLAYER_SKILL_LEARN(OnPlayerSkillLearn.class, void.class),
	ON_PLAYER_SUMMON_SPAWN(OnPlayerSummonSpawn.class, void.class),
	ON_PLAYER_SUMMON_TALK(OnPlayerSummonTalk.class, void.class),
	ON_PLAYER_TRANSFORM(OnPlayerTransform.class, void.class),
	ON_PLAYER_SUB_CHANGE(OnPlayerSubChange.class, void.class),
	ON_PLAYER_QUEST_ABORT(OnPlayerQuestAbort.class, void.class),
	ON_PLAYER_QUEST_COMPLETE(OnPlayerQuestComplete.class, void.class),
	ON_PLAYER_DEATH_EXP_PENALTY(OnPlayerDeathExpPenalty.class, void.class, LongReturn.class),
	ON_PLAYER_DEATH_PENALTY(OnPlayerDeathPenalty.class, void.class, TerminateReturn.class),
	ON_PLAYER_VITALITY_CONSUME(OnPlayerVitalityConsume.class, void.class, IntegerReturn.class),
	
	// Trap events
	ON_TRAP_ACTION(OnTrapAction.class, void.class),
	
	ON_DAY_NIGHT_CHANGE(OnDayNightChange.class, void.class),
	
	ON_PACKET_RECEIVED(OnPacketReceived.class, void.class),
	ON_PACKET_SENT(OnPacketSent.class, void.class),
	
	ON_DAILY_RESET(OnDailyReset.class, void.class),
	
	// Instance events
	ON_INSTANCE_CREATED(OnInstanceCreated.class, void.class),
	ON_INSTANCE_DESTROY(OnInstanceDestroy.class, void.class),
	ON_INSTANCE_ENTER(OnInstanceEnter.class, void.class),
	ON_INSTANCE_FINISH(OnInstanceFinish.class, void.class),
	ON_INSTANCE_LEAVE(OnInstanceLeave.class, void.class),
	ON_INSTANCE_STATUS_CHANGE(OnInstanceStatusChange.class, void.class),
	
	// Restrictions - CAN
	CAN_PLAYER_ATTACK(CanPlayerAttack.class, BooleanReturn.class),
	CAN_PLAYER_INVITE_TO_PARTY(CanPlayerInviteToParty.class, BooleanReturn.class),
	CAN_PLAYER_LEARN_SKILL(CanPlayerLearnSkill.class, BooleanReturn.class),
	CAN_PLAYER_LEAVE_PARTY(CanPlayerLeaveParty.class, BooleanReturn.class),
	CAN_PLAYER_LOGOUT(CanPlayerLogout.class, BooleanReturn.class),
	CAN_PLAYER_INVITE_TO_ALLY(CanPlayerInviteToAlly.class, BooleanReturn.class),
	CAN_PLAYER_INVITE_TO_CLAN(CanPlayerInviteToClan.class, BooleanReturn.class),
	CAN_PLAYER_TRADE(CanPlayerTrade.class, BooleanReturn.class),
	CAN_PLAYER_JOIN_SIEGE(CanPlayerJoinSiege.class, BooleanReturn.class),
	CAN_PLAYER_USE_ITEM(CanPlayerUseItem.class, BooleanReturn.class),
	CAN_PLAYER_USE_SKILL(CanPlayerUseSkill.class, BooleanReturn.class),
	CAN_PLAYER_USE_ACTION(CanPlayerUseAction.class, BooleanReturn.class),
	CAN_PLAYER_STAND_UP(CanPlayerStandUp.class, BooleanReturn.class),
	CAN_PLAYER_PICK_UP(CanPlayerPickUp.class, BooleanReturn.class),
	CAN_PLAYER_REQUEST_REVIVE(CanPlayerRequestRevive.class, BooleanReturn.class),
	
	// Restrictions - IS
	IS_WORLD_OBJECT_VISIBLE_FOR(IsWorldObjectVisibleFor.class, BooleanReturn.class),
	IS_PLAYER_INVUL(IsPlayerInvul.class, BooleanReturn.class),
	
	// Restrictions - GET
	GET_COMBAT_STATE(GetPlayerCombatState.class, CombatStateReturn.class),
	
	// ...
	;
	
	private final Class<? extends IBaseEvent> _eventClass;
	private final Class<?>[] _returnClass;
	
	EventType(Class<? extends IBaseEvent> eventClass, Class<?>... returnClasss)
	{
		_eventClass = eventClass;
		_returnClass = returnClasss;
	}
	
	public Class<? extends IBaseEvent> getEventClass()
	{
		return _eventClass;
	}
	
	public Class<?>[] getReturnClasses()
	{
		return _returnClass;
	}
	
	public boolean isEventClass(Class<?> clazz)
	{
		return _eventClass == clazz;
	}
	
	public boolean isReturnClass(Class<?> clazz)
	{
		return ArrayUtil.contains(_returnClass, clazz);
	}
}
