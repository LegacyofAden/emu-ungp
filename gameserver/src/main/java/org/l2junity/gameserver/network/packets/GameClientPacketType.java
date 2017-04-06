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
package org.l2junity.gameserver.network.packets;

import org.l2junity.gameserver.network.GameClientState;
import org.l2junity.gameserver.network.packets.c2s.*;
import org.l2junity.gameserver.network.packets.c2s.friend.RequestAnswerFriendInvite;
import org.l2junity.gameserver.network.packets.c2s.friend.RequestFriendDel;
import org.l2junity.gameserver.network.packets.c2s.friend.RequestFriendList;
import org.l2junity.gameserver.network.packets.c2s.friend.RequestSendFriendMsg;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author UnAfraid
 */
public enum GameClientPacketType {
	LOGOUT(0x00, Logout::new, GameClientState.AUTHENTICATED, GameClientState.IN_GAME),
	ATTACK(0x01, Attack::new, GameClientState.IN_GAME),
	REQUEST_START_PLEDGE_WAR(0x03, RequestStartPledgeWar::new, GameClientState.IN_GAME),
	REQUEST_REPLY_START_PLEDGE(0x04, RequestReplyStartPledgeWar::new, GameClientState.IN_GAME),
	REQUEST_STOP_PLEDGE_WAR(0x05, RequestStopPledgeWar::new, GameClientState.IN_GAME),
	REQUEST_REPLY_STOP_PLEDGE_WAR(0x06, RequestReplyStopPledgeWar::new, GameClientState.IN_GAME),
	REQUEST_SURRENDER_PLEDGE_WAR(0x07, RequestSurrenderPledgeWar::new, GameClientState.IN_GAME),
	REQUEST_REPLY_SURRENDER_PLEDGE_WAR(0x08, RequestReplySurrenderPledgeWar::new, GameClientState.IN_GAME),
	REQUEST_SET_PLEDGE_CREST(0x09, RequestSetPledgeCrest::new, GameClientState.IN_GAME),
	REQUEST_GIVE_NICK_NAME(0x0B, RequestGiveNickName::new, GameClientState.IN_GAME),
	CHARACTER_CREATE(0x0C, CharacterCreate::new, GameClientState.AUTHENTICATED),
	CHARACTER_DELETE(0x0D, CharacterDelete::new, GameClientState.AUTHENTICATED),
	PROTOCOL_VERSION(0x0E, ProtocolVersion::new, GameClientState.CONNECTED),
	MOVE_BACKWARD_TO_LOCATION(0x0F, MoveBackwardToLocation::new, GameClientState.IN_GAME),
	ENTER_WORLD(0x11, EnterWorld::new, GameClientState.IN_GAME),
	CHARACTER_SELECT(0x12, CharacterSelect::new, GameClientState.AUTHENTICATED),
	NEW_CHARACTER(0x13, NewCharacter::new, GameClientState.AUTHENTICATED),
	REQUEST_ITEM_LIST(0x14, RequestItemList::new, GameClientState.IN_GAME),
	REQUEST_UN_EQUIP_ITEM(0x16, RequestUnEquipItem::new, GameClientState.IN_GAME),
	REQUEST_DROP_ITEM(0x17, RequestDropItem::new, GameClientState.IN_GAME),
	USE_ITEM(0x19, UseItem::new, GameClientState.IN_GAME),
	TRADE_REQUEST(0x1A, TradeRequest::new, GameClientState.IN_GAME),
	ADD_TRADE_ITEM(0x1B, AddTradeItem::new, GameClientState.IN_GAME),
	TRADE_DONE(0x1C, TradeDone::new, GameClientState.IN_GAME),
	ACTION(0x1F, Action::new, GameClientState.IN_GAME),
	REQUEST_LINK_HTML(0x22, RequestLinkHtml::new, GameClientState.IN_GAME),
	REQUEST_BYPASS_TO_SERVER(0x23, RequestBypassToServer::new, GameClientState.IN_GAME),
	REQUEST_BBS_WRITE(0x24, RequestBBSwrite::new, GameClientState.IN_GAME),
	REQUEST_JOIN_PLEDGE(0x26, RequestJoinPledge::new, GameClientState.IN_GAME),
	REQUEST_ANSWER_JOIN_PLEDGE(0x27, RequestAnswerJoinPledge::new, GameClientState.IN_GAME),
	REQUEST_WITHDRAWAL_PLEDGE(0x28, RequestWithdrawalPledge::new, GameClientState.IN_GAME),
	REQUEST_OUST_PLEDGE_MEMBER(0x29, RequestOustPledgeMember::new, GameClientState.IN_GAME),
	AUTH_LOGIN(0x2B, AuthLogin::new, GameClientState.CONNECTED),
	REQUEST_GET_ITEM_FROM_PET(0x2C, RequestGetItemFromPet::new, GameClientState.IN_GAME),
	REQUEST_ALLY_INFO(0x2E, RequestAllyInfo::new, GameClientState.IN_GAME),
	REQUEST_CRYSTALLIZE_ITEM(0x2F, RequestCrystallizeItem::new, GameClientState.IN_GAME),
	REQUEST_PRIVATE_STORE_MANAGE_SELL(0x30, RequestPrivateStoreManageSell::new, GameClientState.IN_GAME),
	SET_PRIVATE_STORE_LIST_SELL(0x31, SetPrivateStoreListSell::new, GameClientState.IN_GAME),
	ATTACK_REQUEST(0x32, AttackRequest::new, GameClientState.IN_GAME),
	REQUEST_TELEPORT(0x33, null, GameClientState.IN_GAME),
	SOCIAL_ACTION(0x34, null, GameClientState.IN_GAME),
	CHANGE_MOVE_TYPE(0x35, ChangeMoveType::new, GameClientState.IN_GAME),
	CHANGE_WAIT_TYPE(0x36, ChangeWaitType::new, GameClientState.IN_GAME),
	REQUEST_SELL_ITEM(0x37, RequestSellItem::new, GameClientState.IN_GAME),
	REQUEST_MAGIC_SKILL_LIST(0x38, RequestMagicSkillList::new, GameClientState.IN_GAME),
	REQUEST_MAGIC_SKILL_USE(0x39, RequestMagicSkillUse::new, GameClientState.IN_GAME),
	APPEARING(0x3A, Appearing::new, GameClientState.IN_GAME),
	SEND_WARE_HOUSE_DEPOSIT_LIST(0x3B, SendWareHouseDepositList::new, GameClientState.IN_GAME),
	SEND_WARE_HOUSE_WITH_DRAW_LIST(0x3C, SendWareHouseWithDrawList::new, GameClientState.IN_GAME),
	REQUEST_SHORT_CUT_REG(0x3D, RequestShortCutReg::new, GameClientState.IN_GAME),
	REQUEST_SHORT_CUT_DEL(0x3F, RequestShortCutDel::new, GameClientState.IN_GAME),
	REQUEST_BUY_ITEM(0x40, RequestBuyItem::new, GameClientState.IN_GAME),
	REQUEST_JOIN_PARTY(0x42, RequestJoinParty::new, GameClientState.IN_GAME),
	REQUEST_ANSWER_JOIN_PARTY(0x43, RequestAnswerJoinParty::new, GameClientState.IN_GAME),
	REQUEST_WITH_DRAWAL_PARTY(0x44, RequestWithDrawalParty::new, GameClientState.IN_GAME),
	REQUEST_OUST_PARTY_MEMBER(0x45, RequestOustPartyMember::new, GameClientState.IN_GAME),
	CANNOT_MOVE_ANYMORE(0x47, CannotMoveAnymore::new, GameClientState.IN_GAME),
	REQUEST_TARGET_CANCELD(0x48, RequestTargetCanceld::new, GameClientState.IN_GAME),
	SAY2(0x49, Say2::new, GameClientState.IN_GAME),
	REQUEST_PLEDGE_MEMBER_LIST(0x4D, RequestPledgeMemberList::new, GameClientState.IN_GAME),
	REQUEST_MAGIC_LIST(0x4F, null, GameClientState.IN_GAME),
	REQUEST_SKILL_LIST(0x50, RequestSkillList::new, GameClientState.IN_GAME),
	MOVE_WITH_DELTA(0x52, MoveWithDelta::new, GameClientState.IN_GAME),
	REQUEST_GET_ON_VEHICLE(0x53, RequestGetOnVehicle::new, GameClientState.IN_GAME),
	REQUEST_GET_OFF_VEHICLE(0x54, RequestGetOffVehicle::new, GameClientState.IN_GAME),
	ANSWER_TRADE_REQUEST(0x55, AnswerTradeRequest::new, GameClientState.IN_GAME),
	REQUEST_ACTION_USE(0x56, RequestActionUse::new, GameClientState.IN_GAME),
	REQUEST_RESTART(0x57, RequestRestart::new, GameClientState.IN_GAME),
	VALIDATE_POSITION(0x59, ValidatePosition::new, GameClientState.IN_GAME),
	START_ROTATING(0x5B, StartRotating::new, GameClientState.IN_GAME),
	FINISH_ROTATING(0x5C, FinishRotating::new, GameClientState.IN_GAME),
	REQUEST_SHOW_BOARD(0x5E, RequestShowBoard::new, GameClientState.IN_GAME),
	REQUEST_ENCHANT_ITEM(0x5F, RequestEnchantItem::new, GameClientState.IN_GAME),
	REQUEST_DESTROY_ITEM(0x60, RequestDestroyItem::new, GameClientState.IN_GAME),
	REQUEST_QUEST_LIST(0x62, RequestQuestList::new, GameClientState.IN_GAME),
	REQUEST_QUEST_ABORT(0x63, RequestQuestAbort::new, GameClientState.IN_GAME),
	REQUEST_PLEDGE_INFO(0x65, RequestPledgeInfo::new, GameClientState.IN_GAME),
	REQUEST_PLEDGE_EXTENDED_INFO(0x66, RequestPledgeExtendedInfo::new, GameClientState.IN_GAME),
	REQUEST_PLEDGE_CREST(0x67, RequestPledgeCrest::new, GameClientState.IN_GAME),
	REQUEST_SEND_FRIEND_MSG(0x6B, RequestSendFriendMsg::new, GameClientState.IN_GAME),
	REQUEST_SHOW_MINI_MAP(0x6C, RequestShowMiniMap::new, GameClientState.IN_GAME),
	REQUEST_RECORD_INFO(0x6E, RequestRecordInfo::new, GameClientState.IN_GAME),
	REQUEST_HENNA_EQUIP(0x6F, RequestHennaEquip::new, GameClientState.IN_GAME),
	REQUEST_HENNA_REMOVE_LIST(0x70, RequestHennaRemoveList::new, GameClientState.IN_GAME),
	REQUEST_HENNA_ITEM_REMOVE_INFO(0x71, RequestHennaItemRemoveInfo::new, GameClientState.IN_GAME),
	REQUEST_HENNA_REMOVE(0x72, RequestHennaRemove::new, GameClientState.IN_GAME),
	REQUEST_ACQUIRE_SKILL_INFO(0x73, RequestAcquireSkillInfo::new, GameClientState.IN_GAME),
	SEND_BYPASS_BUILD_CMD(0x74, SendBypassBuildCmd::new, GameClientState.IN_GAME),
	REQUEST_MOVE_TO_LOCATION_IN_VEHICLE(0x75, RequestMoveToLocationInVehicle::new, GameClientState.IN_GAME),
	CANNOT_MOVE_ANYMORE_IN_VEHICLE(0x76, CannotMoveAnymoreInVehicle::new, GameClientState.IN_GAME),
	REQUEST_FRIEND_INVITE(0x77, RequestFriendInvite::new, GameClientState.IN_GAME),
	REQUEST_ANSWER_FRIEND_INVITE(0x78, RequestAnswerFriendInvite::new, GameClientState.IN_GAME),
	REQUEST_FRIEND_LIST(0x79, RequestFriendList::new, GameClientState.IN_GAME),
	REQUEST_FRIEND_DEL(0x7A, RequestFriendDel::new, GameClientState.IN_GAME),
	CHARACTER_RESTORE(0x7B, CharacterRestore::new, GameClientState.AUTHENTICATED),
	REQUEST_ACQUIRE_SKILL(0x7C, RequestAcquireSkill::new, GameClientState.IN_GAME),
	REQUEST_RESTART_POINT(0x7D, RequestRestartPoint::new, GameClientState.IN_GAME),
	REQUEST_GM_COMMAND(0x7E, RequestGMCommand::new, GameClientState.IN_GAME),
	REQUEST_PARTY_MATCH_CONFIG(0x7F, RequestPartyMatchConfig::new, GameClientState.IN_GAME),
	REQUEST_PARTY_MATCH_LIST(0x80, RequestPartyMatchList::new, GameClientState.IN_GAME),
	REQUEST_PARTY_MATCH_DETAIL(0x81, RequestPartyMatchDetail::new, GameClientState.IN_GAME),
	REQUEST_PRIVATE_STORE_BUY(0x83, RequestPrivateStoreBuy::new, GameClientState.IN_GAME),
	REQUEST_TUTORIAL_LINK_HTML(0x85, RequestTutorialLinkHtml::new, GameClientState.IN_GAME),
	REQUEST_TUTORIAL_PASS_CMD_TO_SERVER(0x86, RequestTutorialPassCmdToServer::new, GameClientState.IN_GAME),
	REQUEST_TUTORIAL_QUESTION_MARK(0x87, RequestTutorialQuestionMark::new, GameClientState.IN_GAME),
	REQUEST_TUTORIAL_CLIENT_EVENT(0x88, RequestTutorialClientEvent::new, GameClientState.IN_GAME),
	REQUEST_PETITION(0x89, RequestPetition::new, GameClientState.IN_GAME),
	REQUEST_PETITION_CANCEL(0x8A, RequestPetitionCancel::new, GameClientState.IN_GAME),
	REQUEST_GM_LIST(0x8B, RequestGmList::new, GameClientState.IN_GAME),
	REQUEST_JOIN_ALLY(0x8C, RequestJoinAlly::new, GameClientState.IN_GAME),
	REQUEST_ANSWER_JOIN_ALLY(0x8D, RequestAnswerJoinAlly::new, GameClientState.IN_GAME),
	ALLY_LEAVE(0x8E, AllyLeave::new, GameClientState.IN_GAME),
	ALLY_DISMISS(0x8F, AllyDismiss::new, GameClientState.IN_GAME),
	REQUEST_DISMISS_ALLY(0x90, RequestDismissAlly::new, GameClientState.IN_GAME),
	REQUEST_SET_ALLY_CREST(0x91, RequestSetAllyCrest::new, GameClientState.IN_GAME),
	REQUEST_ALLY_CREST(0x92, RequestAllyCrest::new, GameClientState.IN_GAME),
	REQUEST_CHANGE_PET_NAME(0x93, RequestChangePetName::new, GameClientState.IN_GAME),
	REQUEST_PET_USE_ITEM(0x94, RequestPetUseItem::new, GameClientState.IN_GAME),
	REQUEST_GIVE_ITEM_TO_PET(0x95, RequestGiveItemToPet::new, GameClientState.IN_GAME),
	REQUEST_PRIVATE_STORE_QUIT_SELL(0x96, RequestPrivateStoreQuitSell::new, GameClientState.IN_GAME),
	SET_PRIVATE_STORE_MSG_SELL(0x97, SetPrivateStoreMsgSell::new, GameClientState.IN_GAME),
	REQUEST_PET_GET_ITEM(0x98, RequestPetGetItem::new, GameClientState.IN_GAME),
	REQUEST_PRIVATE_STORE_MANAGE_BUY(0x99, RequestPrivateStoreManageBuy::new, GameClientState.IN_GAME),
	SET_PRIVATE_STORE_LIST_BUY(0x9A, SetPrivateStoreListBuy::new, GameClientState.IN_GAME),
	REQUEST_PRIVATE_STORE_QUIT_BUY(0x9C, RequestPrivateStoreQuitBuy::new, GameClientState.IN_GAME),
	SET_PRIVATE_STORE_MSG_BUY(0x9D, SetPrivateStoreMsgBuy::new, GameClientState.IN_GAME),
	REQUEST_PRIVATE_STORE_SELL(0x9F, RequestPrivateStoreSell::new, GameClientState.IN_GAME),
	SEND_TIME_CHECK_PACKET(0xA0, null, GameClientState.IN_GAME),
	REQUEST_SKILL_COOL_TIME(0xA6, RequestSkillCoolTime::new, GameClientState.IN_GAME),
	REQUEST_PACKAGE_SENDABLE_ITEM_LIST(0xA7, RequestPackageSendableItemList::new, GameClientState.IN_GAME),
	REQUEST_PACKAGE_SEND(0xA8, RequestPackageSend::new, GameClientState.IN_GAME),
	REQUEST_BLOCK(0xA9, RequestBlock::new, GameClientState.IN_GAME),
	REQUEST_SIEGE_INFO(0xAA, RequestSiegeInfo::new, GameClientState.IN_GAME),
	REQUEST_SIEGE_ATTACKER_LIST(0xAB, RequestSiegeAttackerList::new, GameClientState.IN_GAME),
	REQUEST_SIEGE_DEFENDER_LIST(0xAC, RequestSiegeDefenderList::new, GameClientState.IN_GAME),
	REQUEST_JOIN_SIEGE(0xAD, RequestJoinSiege::new, GameClientState.IN_GAME),
	REQUEST_CONFIRM_SIEGE_WAITING_LIST(0xAE, RequestConfirmSiegeWaitingList::new, GameClientState.IN_GAME),
	REQUEST_SET_CASTLE_SIEGE_TIME(0xAF, RequestSetCastleSiegeTime::new, GameClientState.IN_GAME),
	MULTI_SELL_CHOOSE(0xB0, MultiSellChoose::new, GameClientState.IN_GAME),
	NET_PING(0xB1, NetPing::new, GameClientState.IN_GAME),
	REQUEST_REMAIN_TIME(0xB2, RequestRemainTime::new, GameClientState.IN_GAME),
	BYPASS_USER_CMD(0xB3, BypassUserCmd::new, GameClientState.IN_GAME),
	SNOOP_QUIT(0xB4, SnoopQuit::new, GameClientState.IN_GAME),
	REQUEST_RECIPE_BOOK_OPEN(0xB5, RequestRecipeBookOpen::new, GameClientState.IN_GAME),
	REQUEST_RECIPE_BOOK_DESTROY(0xB6, RequestRecipeBookDestroy::new, GameClientState.IN_GAME),
	REQUEST_RECIPE_ITEM_MAKE_INFO(0xB7, RequestRecipeItemMakeInfo::new, GameClientState.IN_GAME),
	REQUEST_RECIPE_ITEM_MAKE_SELF(0xB8, RequestRecipeItemMakeSelf::new, GameClientState.IN_GAME),
	REQUEST_RECIPE_SHOP_MANAGE_LIST(0xB9, null, GameClientState.IN_GAME),
	REQUEST_RECIPE_SHOP_MESSAGE_SET(0xBA, RequestRecipeShopMessageSet::new, GameClientState.IN_GAME),
	REQUEST_RECIPE_SHOP_LIST_SET(0xBB, RequestRecipeShopListSet::new, GameClientState.IN_GAME),
	REQUEST_RECIPE_SHOP_MANAGE_QUIT(0xBC, RequestRecipeShopManageQuit::new, GameClientState.IN_GAME),
	REQUEST_RECIPE_SHOP_MANAGE_CANCEL(0xBD, null, GameClientState.IN_GAME),
	REQUEST_RECIPE_SHOP_MAKE_INFO(0xBE, RequestRecipeShopMakeInfo::new, GameClientState.IN_GAME),
	REQUEST_RECIPE_SHOP_MAKE_ITEM(0xBF, RequestRecipeShopMakeItem::new, GameClientState.IN_GAME),
	REQUEST_RECIPE_SHOP_MANAGE_PREV(0xC0, RequestRecipeShopManagePrev::new, GameClientState.IN_GAME),
	OBSERVER_RETURN(0xC1, ObserverReturn::new, GameClientState.IN_GAME),
	REQUEST_EVALUATE(0xC2, null, GameClientState.IN_GAME),
	REQUEST_HENNA_ITEM_LIST(0xC3, RequestHennaItemList::new, GameClientState.IN_GAME),
	REQUEST_HENNA_ITEM_INFO(0xC4, RequestHennaItemInfo::new, GameClientState.IN_GAME),
	REQUEST_BUY_SEED(0xC5, RequestBuySeed::new, GameClientState.IN_GAME),
	DLG_ANSWER(0xC6, DlgAnswer::new, GameClientState.IN_GAME),
	REQUEST_PREVIEW_ITEM(0xC7, RequestPreviewItem::new, GameClientState.IN_GAME),
	REQUEST_SSQ_STATUS(0xC8, null, GameClientState.IN_GAME),
	REQUEST_PETITION_FEEDBACK(0xC9, RequestPetitionFeedback::new, GameClientState.IN_GAME),
	GAME_GUARD_REPLY(0xCB, GameGuardReply::new, GameClientState.IN_GAME),
	REQUEST_PLEDGE_POWER(0xCC, RequestPledgePower::new, GameClientState.IN_GAME),
	REQUEST_MAKE_MACRO(0xCD, RequestMakeMacro::new, GameClientState.IN_GAME),
	REQUEST_DELETE_MACRO(0xCE, RequestDeleteMacro::new, GameClientState.IN_GAME),
	REQUEST_BUY_PROCURE(0xCF, null, GameClientState.IN_GAME),
	EX_PACKET(0xD0, ExPacket::new, GameClientState.values()); // This packet has its own connection state checking so we allow all of them

	public static final GameClientPacketType[] PACKET_ARRAY;

	static {
		final short maxPacketId = (short) Arrays.stream(values()).mapToInt(GameClientPacketType::getPacketId).max().orElse(0);
		PACKET_ARRAY = new GameClientPacketType[maxPacketId + 1];
		for (GameClientPacketType incomingPacket : values()) {
			PACKET_ARRAY[incomingPacket.getPacketId()] = incomingPacket;
		}
	}

	private short _packetId;
	private Supplier<GameClientPacket> _incomingPacketFactory;
	private Set<GameClientState> _connectionStates;

	GameClientPacketType(int packetId, Supplier<GameClientPacket> incomingPacketFactory, GameClientState... connectionStates) {
		// packetId is an unsigned byte
		if (packetId > 0xFF) {
			throw new IllegalArgumentException("packetId must not be bigger than 0xFF");
		}

		_packetId = (short) packetId;
		_incomingPacketFactory = incomingPacketFactory != null ? incomingPacketFactory : () -> null;
		_connectionStates = new HashSet<>(Arrays.asList(connectionStates));
	}

	public int getPacketId() {
		return _packetId;
	}

	public GameClientPacket newIncomingPacket() {
		return _incomingPacketFactory.get();
	}

	public Set<GameClientState> getConnectionStates() {
		return _connectionStates;
	}
}
