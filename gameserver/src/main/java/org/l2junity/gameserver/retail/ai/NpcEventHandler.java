package org.l2junity.gameserver.retail.ai;


import org.l2junity.gameserver.retail.EventId;
import org.l2junity.gameserver.retail.model.*;
import org.l2junity.gameserver.retail.model.actor.*;
import org.l2junity.gameserver.retail.model.formation.Clan;
import org.l2junity.gameserver.retail.model.formation.MPCC;
import org.l2junity.gameserver.retail.model.formation.Party;
import org.l2junity.gameserver.retail.model.item.ItemData;
import org.l2junity.gameserver.retail.model.item.ItemIndexList;


/**
 * @author ANZO, Camellion
 * @since 20.03.2017
 */
public class NpcEventHandler {
    /*public NpcEventHandler(Npc myself) {
        this.myself = myself;
    }*/

    @EventId(808)
    protected Npc myself = null;

    @EventId(712)
    protected GlobalObject gg = null;

    @EventId(64)
    protected Creature _private;

    @EventId(664)
    protected RoomInfoList rlist0;
    @EventId(648)
    protected RoomInfo room0;
    @EventId(656)
    protected RoomInfo room1;
    @EventId(512)
    protected StaticObject so0;
    @EventId(680)
    protected Point pos0;
    @EventId(688)
    protected Point pos1;
    @EventId(560)
    protected Npc npc0;
    @EventId(632)
    protected NpcMaker maker0;
    @EventId(640)
    protected NpcMaker maker1;
    @EventId(584)
    protected String s0;
    @EventId(592)
    protected String s1;
    @EventId(600)
    protected FHTML fhtml0;
    @EventId(608)
    protected FHTML fhtml1;
    @EventId(496)
    protected ItemData item0;
    @EventId(384)
    protected double f0;
    @EventId(388)
    protected double f1;
    @EventId(392)
    protected double f2;
    @EventId(396)
    protected double f3;
    @EventId(400)
    protected double f4;
    @EventId(424)
    protected Creature c0;
    @EventId(432)
    protected Creature c1;
    @EventId(440)
    protected Creature c2;
    @EventId(448)
    protected Creature c3;
    @EventId(456)
    protected Creature c4;
    @EventId(480)
    protected Party party0;
    @EventId(488)
    protected Party party1;
    @EventId(464)
    protected Clan pledge0;
    @EventId(value = 472)
    protected Clan pledge1; // TODO: Incorrect?
    @EventId(520)
    protected HateInfo h0;
    @EventId(528)
    protected HateInfo h1;
    @EventId(288)
    protected int i0;
    @EventId(296)
    protected int i1;
    @EventId(304)
    protected int i2;
    @EventId(312)
    protected int i3;
    @EventId(320)
    protected int i4;
    @EventId(328)
    protected int i5;
    @EventId(336)
    protected int i6;
    @EventId(344)
    protected int i7;
    @EventId(352)
    protected int i8;
    @EventId(360)
    protected int i9;
    @EventId(368)
    protected int i10;
    @EventId(376)
    protected int i11;

    @EventId(744)
    protected SpawnDefine def0;
    @EventId(776)
    protected CodeInfoList random1_list;
    @EventId(784)
    protected CodeInfoList always_list;

    @EventId(0)
    protected void NO_DESIRE() {
    }

    @EventId(1)
    protected void ATTACKED(@EventId(name = "attacker", value = 48) Creature attacker,
                            @EventId(name = "victim", value = 56) Creature victim,
                            @EventId(name = "target", value = 96) Creature target,
                            @EventId(name = "skill_id", value = 164) int skill_id,
                            @EventId(name = "skill_name_id", value = 176) int skill_name_id,
                            @EventId(name = "damage", value = 144) int damage,
                            @EventId(name = "IsCritical", value = 268) int IsCritical,
                            @EventId(name = "inzone_pc", value = 999999) int inzone_pc) {
    }

    @EventId(2)
    protected void SPELLED(@EventId(name = "attacker", value = 48) Creature attacker,
                           @EventId(name = "speller", value = 88) Creature speller,
                           @EventId(name = "skill_id", value = 164) int skill_id,
                           @EventId(name = "skill_name_id", value = 176) int skill_name_id) {
    }

    @EventId(3)
    protected void TALKED(@EventId(name = "talker", value = 40) Creature talker,
                          @EventId(name = "ask", value = 160) int ask,
                          @EventId(name = "reply", value = 280) int reply,
                          @EventId(name = "_from_choice", value = 624) int _from_choice,
                          @EventId(name = "_choiceN", value = 620) int _choiceN,
                          @EventId(name = "_code", value = 616) int _code) {
    }

    @EventId(4)
    protected void TALK_SELECTED(@EventId(name = "talker", value = 40) Creature talker,
                                 @EventId(name = "ask", value = 160) int ask,
                                 @EventId(name = "reply", value = 280) int reply,
                                 @EventId(name = "_from_choice", value = 624) int _from_choice,
                                 @EventId(name = "_choiceN", value = 620) int _choiceN,
                                 @EventId(name = "_code", value = 616) int _code) {
    }

    @EventId(5)
    protected void SEE_CREATURE(@EventId(name = "creature", value = 112) Creature creature) {
    }

    @EventId(7)
    protected void SEE_ITEM(@EventId(name = "item_index_list", value = 800) ItemIndexList item_index_list) {
    }

    @EventId(8)
    protected void ON_DOOR_EVENT(@EventId(name = "enabled", value = 1440) int enabled) {
    }

    @EventId(9)
    protected void MY_DYING(@EventId(name = "last_attacker", value = 736) Creature last_attacker,
                            @EventId(name = "attacker", value = 48) Creature attacker,
                            @EventId(name = "target", value = 96) Creature target,
                            @EventId(name = "talker", value = 40) Creature talker,
                            @EventId(name = "creature", value = 112) Creature creature,
                            @EventId(name = "reply", value = 280) int reply,
                            @EventId(name = "skill_name_id", value = 176) int skill_name_id,
                            @EventId(name = "private", value = 64) Creature _private,
                            @EventId(name = "code_info", value = 792) CodeInfo code_info,
                            @EventId(name = "lparty", value = 752) Party lparty,
                            @EventId(name = "lmpcc", value = 760) MPCC lmpcc,
                            @EventId(name = "aparty", value = 768) Party aparty) {
    }

    @EventId(10)
    protected void ON_END_SIEGE_EVENT() {
    }

    @EventId(11)
    protected void TIMER_FIRED_EX(@EventId(name = "timer_id", value = 244) int timer_id,
                                  @EventId(name = "talker", value = 40) Creature talker,
                                  @EventId(name = "attacker", value = 48) Creature attacker,
                                  @EventId(name = "private", value = 64) Creature _private,
                                  @EventId(name = "target", value = 96) Creature target,
                                  @EventId(name = "creature", value = 112) Creature creature) {
    }

    @EventId(12)
    protected void CREATED(@EventId(name = "ask", value = 160) int ask,
                           @EventId(name = "reply", value = 280) int reply,
                           @EventId(name = "creature", value = 112) Creature creature,
                           @EventId(name = "attacker", value = 48) Creature attacker) {
    }

    @EventId(13)
    protected void ON_CANCEL_SIEGE_EVENT() {
    }

    @EventId(14)
    protected void ON_START_TEAMBATTLEAGIT_FINAL_EVENT() {
    }

    @EventId(15)
    protected void SEE_SPELL(@EventId(name = "speller", value = 88) Creature speller,
                             @EventId(name = "target", value = 96) Creature target,
                             @EventId(name = "skill_id", value = 164) int skill_id,
                             @EventId(name = "skill_name_id", value = 176) int skill_name_id,
                             @EventId(name = "x", value = 204) int x,
                             @EventId(name = "y", value = 208) int y,
                             @EventId(name = "z", value = 212) int z) {
    }

    @EventId(16)
    protected void OUT_OF_TERRITORY() {
    }

    @EventId(17)
    protected void DESIRE_MANIPULATION(@EventId(name = "speller", value = 88) Creature speller,
                                       @EventId(name = "skill_name_id", value = 176) int skill_name_id,
                                       @EventId(name = "desire", value = 220) int desire) {
    }

    @EventId(18)
    protected void PARTY_ATTACKED(@EventId(name = "attacker", value = 48) Creature attacker,
                                  @EventId(name = "damage", value = 144) int damage,
                                  @EventId(name = "skill_name_id", value = 176) int skill_name_id,
                                  @EventId(name = "private", value = 64) Creature _private) {
    }

    @EventId(20)
    protected void PARTY_DIED(@EventId(name = "private", value = 64) Creature _private,
                              @EventId(name = "attacker", value = 48) Creature attacker) {
    }

    @EventId(21)
    protected void ON_DECLARE_DOMINION_EVENT() {
    }

    @EventId(22)
    protected void CLAN_ATTACKED(@EventId(name = "attacker", value = 48) Creature attacker,
                                 @EventId(name = "damage", value = 144) int damage,
                                 @EventId(name = "skill_name_id", value = 176) int skill_name_id,
                                 @EventId(name = "victim", value = 56) Creature victim) {
    }

    @EventId(24)
    protected void STATIC_OBJECT_CLAN_ATTACKED(@EventId(name = "attacker", value = 48) Creature attacker,
                                               @EventId(name = "damage", value = 144) int damage,
                                               @EventId(name = "skill_name_id", value = 176) int skill_name_id) {
    }

    @EventId(27)
    protected void TELEPORT_REQUESTED(@EventId(name = "talker", value = 40) Creature talker) {
    }

    @EventId(30)
    protected void QUEST_ACCEPTED(@EventId(name = "quest_id", value = 156) int quest_id,
                                  @EventId(name = "talker", value = 40) Creature talker) {
    }

    @EventId(31)
    protected void MENU_SELECTED(@EventId(name = "talker", value = 40) Creature talker,
                                 @EventId(name = "ask", value = 160) int ask,
                                 @EventId(name = "reply", value = 280) int reply,
                                 @EventId(name = "state", value = 148) int state,
                                 @EventId(name = "quest_id", value = 156) int quest_id,
                                 @EventId(name = "action_id", value = 264) int action_id) {
    }

    @EventId(32)
    protected void LEARN_SKILL_REQUESTED(@EventId(name = "talker", value = 40) Creature talker) {
    }

    @EventId(33)
    protected void ENCHANT_SKILL_REQUESTED(@EventId(name = "talker", value = 40) Creature talker,
                                           @EventId(name = "action_id", value = 264) int action_id) {
    }

    @EventId(34)
    protected void ONE_SKILL_SELECTED(@EventId(name = "talker", value = 40) Creature talker,
                                      @EventId(name = "skill_name_id", value = 176) int skill_name_id,
                                      @EventId(name = "i0", value = 288) int i0) {
    }

    @EventId(35)
    protected void ONE_ENCHANT_SKILL_SELECTED(@EventId(name = "talker", value = 40) Creature talker,
                                              @EventId(name = "skill_name_id", value = 176) int skill_name_id,
                                              @EventId(name = "action_id", value = 264) int action_id) {
    }

    @EventId(37)
    protected void CLASS_CHANGE_REQUESTED(@EventId(name = "talker", value = 40) Creature talker,
                                          @EventId(name = "occupation_name_id", value = 180) int occupation_name_id) {
    }

    @EventId(38)
    protected void MANOR_MENU_SELECTED(@EventId(name = "talker", value = 40) Creature talker,
                                       @EventId(name = "ask", value = 160) int ask,
                                       @EventId(name = "state", value = 148) int state,
                                       @EventId(name = "time", value = 192) int time) {
    }

    @EventId(44)
    protected void CREATE_PLEDGE(@EventId(name = "talker", value = 40) Creature talker,
                                 @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(45)
    protected void DISMISS_PLEDGE(@EventId(name = "talker", value = 40) Creature talker,
                                  @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(46)
    protected void REVIVE_PLEDGE(@EventId(name = "talker", value = 40) Creature talker,
                                 @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(47)
    protected void LEVEL_UP_PLEDGE(@EventId(name = "talker", value = 40) Creature talker,
                                   @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(50)
    protected void CREATE_ALLIANCE(@EventId(name = "talker", value = 40) Creature talker,
                                   @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(51)
    protected void SCRIPT_EVENT(@EventId(name = "script_event_arg1", value = 248) int script_event_arg1,
                                @EventId(name = "script_event_arg2", value = 252) int script_event_arg2,
                                @EventId(name = "script_event_arg3", value = 256) int script_event_arg3,
                                @EventId(name = "private", value = 64) Creature _private,
                                @EventId(name = "attacker", value = 48) Creature attacker) {
    }

    @EventId(52)
    protected void TUTORIAL_EVENT(@EventId(name = "talker", value = 40) Creature talker,
                                  @EventId(name = "event_id", value = 236) int event_id) {
    }

    @EventId(53)
    protected void QUESTION_MARK_CLICKED(@EventId(name = "talker", value = 40) Creature talker,
                                         @EventId(name = "question_id", value = 240) int question_id) {
    }

    @EventId(54)
    protected void USER_CONNECTED(@EventId(name = "talker", value = 40) Creature talker) {
    }

    @EventId(57)
    protected void ATTACK_FINISHED(@EventId(name = "target", value = 96) Creature target) {
    }

    @EventId(60)
    protected void GET_ITEM_FINISHED(@EventId(name = "item", value = 128) ItemData item,
                                     @EventId(name = "success", value = 200) int success) {
    }

    @EventId(63)
    protected void MOVE_TO_WAY_POINT_FINISHED(@EventId(name = "way_point_index", value = 184) int way_point_index,
                                              @EventId(name = "next_way_point_index", value = 188) int next_way_point_index) {
    }

    @EventId(64)
    protected void USE_SKILL_FINISHED(@EventId(name = "attacker", value = 48) Creature attacker,
                                      @EventId(name = "target", value = 96) Creature target,
                                      @EventId(name = "skill_id", value = 164) int skill_id,
                                      @EventId(name = "skill_name_id", value = 176) int skill_name_id,
                                      @EventId(name = "success", value = 200) int success,
                                      @EventId(name = "ask", value = 160) int ask,
                                      @EventId(name = "reply", value = 280) int reply,
                                      @EventId(name = "creature", value = 112) Creature creature) {
    }

    @EventId(65)
    protected void MOVE_TO_FINISHED(@EventId(name = "x", value = 204) int x,
                                    @EventId(name = "y", value = 208) int y,
                                    @EventId(name = "z", value = 212) int z,
                                    @EventId(name = "attacker", value = 48) Creature attacker) {
    }

    @EventId(77)
    protected void DOOR_HP_LEVEL_INFORMED(@EventId(name = "talker", value = 40) Creature talker,
                                          @EventId(name = "level", value = 260) int level) {
    }

    @EventId(78)
    protected void CONTROLTOWER_LEVEL_INFORMED(@EventId(name = "talker", value = 40) Creature talker,
                                               @EventId(name = "level", value = 260) int level) {
    }

    @EventId(79)
    protected void TB_REGISTER_PLEDGE_RETURNED(@EventId(name = "talker", value = 40) Creature talker,
                                               @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(80)
    protected void TB_REGISTER_MEMBER_RETURNED(@EventId(name = "talker", value = 40) Creature talker,
                                               @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(81)
    protected void TB_GET_NPC_TYPE_INFORMED(@EventId(name = "talker", value = 40) Creature talker,
                                            @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(82)
    protected void TB_SET_NPC_TYPE_RETURNED(@EventId(name = "talker", value = 40) Creature talker,
                                            @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(83)
    protected void TB_GET_PLEDGE_REGISTER_STATUS_INFORMED(@EventId(name = "talker", value = 40) Creature talker,
                                                          @EventId(name = "reply", value = 280) int reply,
                                                          @EventId(name = "pledge0", value = 464) Clan pledge0,
                                                          @EventId(name = "fhtml0", value = 600) FHTML fhtml0) {
    }

    @EventId(84)
    protected void TB_GET_BATTLE_ROYAL_PLEDGE_LIST_INFORMED(@EventId(name = "talker", value = 40) Creature talker) {
    }

    @EventId(85)
    protected void SUBJOB_LIST_INFORMED(@EventId(name = "talker", value = 40) Creature talker,
                                        @EventId(name = "ask", value = 160) int ask,
                                        @EventId(name = "i0", value = 288) int i0,
                                        @EventId(name = "i1", value = 296) int i1,
                                        @EventId(name = "i2", value = 304) int i2,
                                        @EventId(name = "i3", value = 312) int i3,
                                        @EventId(name = "i4", value = 320) int i4,
                                        @EventId(name = "i5", value = 328) int i5,
                                        @EventId(name = "i6", value = 336) int i6,
                                        @EventId(name = "i7", value = 344) int i7,
                                        @EventId(name = "i8", value = 352) int i8,
                                        @EventId(name = "i9", value = 360) int i9,
                                        @EventId(name = "i10", value = 368) int i10,
                                        @EventId(name = "i11", value = 376) int i11) {
    }

    @EventId(86)
    protected void SUBJOB_CREATED(@EventId(name = "talker", value = 40) Creature talker,
                                  @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(87)
    protected void SUBJOB_CHANGED(@EventId(name = "talker", value = 40) Creature talker,
                                  @EventId(name = "reply", value = 280) int reply,
                                  @EventId(name = "level", value = 260) int level) {
    }

    @EventId(88)
    protected void SUBJOB_RENEWED(@EventId(name = "talker", value = 40) Creature talker,
                                  @EventId(name = "reply", value = 280) int reply,
                                  @EventId(name = "i0", value = 288) int i0) {
    }

    @EventId(89)
    protected void ON_SSQ_SYSTEM_EVENT(@EventId(name = "talker", value = 40) Creature talker,
                                       @EventId(name = "ask", value = 160) int ask,
                                       @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(90)
    protected void SET_AGIT_DECO_RETURNED(@EventId(name = "talker", value = 40) Creature talker,
                                          @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(91)
    protected void RESET_AGIT_DECO_RETURNED(@EventId(name = "talker", value = 40) Creature talker,
                                            @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(92)
    protected void AUCTION_AGIT_GET_COST_INFO_RETURNED(@EventId(name = "talker", value = 40) Creature talker,
                                                       @EventId(name = "reply", value = 280) int reply,
                                                       @EventId(name = "s0", value = 584) String s0) {
    }

    @EventId(93)
    protected void CLAN_DIED(@EventId(name = "victim", value = 56) Creature victim,
                             @EventId(name = "attacker", value = 48) Creature attacker) {
    }

    @EventId(95)
    protected void SET_HERO_RETURNED(@EventId(name = "talker", value = 40) Creature talker,
                                     @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(97)
    protected void DELETE_OLYMPIAD_TRADE_POINT_RETURNED(@EventId(name = "talker", value = 40) Creature talker,
                                                        @EventId(name = "ask", value = 160) int ask,
                                                        @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(98)
    protected void MG_REGISTER_PLEDGE_RETURNED(@EventId(name = "talker", value = 40) Creature talker,
                                               @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(99)
    protected void MG_UNREGISTER_PLEDGE_RETURNED(@EventId(name = "talker", value = 40) Creature talker,
                                                 @EventId(name = "reply", value = 280) int reply,
                                                 @EventId(name = "i0", value = 288) int i0) {
    }

    @EventId(101)
    protected void MG_JOIN_GAME_RETURNED(@EventId(name = "talker", value = 40) Creature talker,
                                         @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(102)
    protected void MG_GET_UNRETURNED_POINT_RETURNED(@EventId(name = "talker", value = 40) Creature talker,
                                                    @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(103)
    protected void CREATE_ACADEMY(@EventId(name = "talker", value = 40) Creature talker,
                                  @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(104)
    protected void CREATE_SUBPLEDGE(@EventId(name = "talker", value = 40) Creature talker,
                                    @EventId(name = "reply", value = 280) int reply,
                                    @EventId(name = "i0", value = 288) int i0,
                                    @EventId(name = "i1", value = 296) int i1) {
    }

    @EventId(105)
    protected void CHECK_CURSED_USER_RETURNED(@EventId(name = "talker", value = 40) Creature talker,
                                              @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(106)
    protected void UPDATE_SUBPLEDGE_MASTER(@EventId(name = "talker", value = 40) Creature talker,
                                           @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(107)
    protected void UPGRADE_SUBPLEDGE_MEMBER_COUNT(@EventId(name = "talker", value = 40) Creature talker,
                                                  @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(108)
    protected void MPCC_TELEPORTED(@EventId(name = "talker", value = 40) Creature talker,
                                   @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(109)
    protected void PLEDGE_MASTER_TRANSFER(@EventId(name = "talker", value = 40) Creature talker,
                                          @EventId(name = "reply", value = 280) int reply,
                                          @EventId(name = "s0", value = 584) String s0) {
    }

    @EventId(110)
    protected void PLEDGE_MASTER_TRANSFER_CANCEL(@EventId(name = "talker", value = 40) Creature talker,
                                                 @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(111)
    protected void TB_CHECK_MEMBER_REGISTER_STATUS(@EventId(name = "talker", value = 40) Creature talker,
                                                   @EventId(name = "reply", value = 280) int reply,
                                                   @EventId(name = "pledge0", value = 464) Clan pledge0) {
    }

    @EventId(112)
    protected void RENAME_SUBPLEDGE(@EventId(name = "talker", value = 40) Creature talker,
                                    @EventId(name = "reply", value = 280) int reply,
                                    @EventId(name = "s0", value = 584) String s0,
                                    @EventId(name = "i0", value = 288) int i0) {
    }

    @EventId(113)
    protected void ABNORMAL_STATUS_CHANGED(@EventId(value = 88, name = "speller") Creature speller,
                                           @EventId(name = "skill_id", value = 164) int skill_id,
                                           @EventId(name = "skill_level", value = 168) int skill_level,
                                           @EventId(name = "skill_name_id", value = 176) int skill_name_id) {
    }

    @EventId(114)
    protected void TELEPORT_TO_USER_RES(@EventId(name = "talker", value = 40) Creature talker,
                                        @EventId(name = "success", value = 200) int success,
                                        @EventId(name = "c0", value = 424) Creature c0, @EventId(name = "i0", value = 288) int i0) {
    }

    @EventId(115)
    protected void NODE_ARRIVED(@EventId(name = "script_event_arg1", value = 248) int script_event_arg1,
                                @EventId(name = "script_event_arg2", value = 252) int script_event_arg2,
                                @EventId(name = "script_event_arg3", value = 256) int script_event_arg3,
                                @EventId(name = "state", value = 148) int state,
                                @EventId(name = "success", value = 200) int success) {
    }

    @EventId(116)
    protected void TRAP_STEP_IN(@EventId(name = "i0", value = 288) int i0,
                                @EventId(name = "i1", value = 296) int i1,
                                @EventId(name = "i2", value = 304) int i2,
                                @EventId(name = "i3", value = 312) int i3) {
    }

    @EventId(117)
    protected void TRAP_STEP_OUT(@EventId(name = "c0", value = 424) Creature c0,
                                 @EventId(name = "i0", value = 288) int i0,
                                 @EventId(name = "i1", value = 296) int i1) {
    }

    @EventId(118)
    protected void TRAP_ACTIVATED() {
    }

    @EventId(119)
    protected void TRAP_DEFUSED() {
    }

    @EventId(120)
    protected void TRAP_DETECTED() {
    }

    @EventId(121)
    protected void SHOW_ENCHANT_SKILL_DRAWER(@EventId(name = "talker", value = 40) Creature talker,
                                             @EventId(name = "skill_name_id", value = 176) int skill_name_id,
                                             @EventId(name = "action_id", value = 264) int action_id) {
    }

    @EventId(123)
    protected void INSTANT_ZONE_ENTER_RETURNED(@EventId(name = "talker", value = 40) Creature talker,
                                               @EventId(name = "reply", value = 280) int reply,
                                               @EventId(name = "state", value = 148) int state) {
    }

    @EventId(125)
    protected void FORTRESS_SIEGE_REGISTER_RETURNED(@EventId(name = "talker", value = 40) Creature talker,
                                                    @EventId(name = "reply", value = 280) int reply,
                                                    @EventId(name = "i0", value = 288) int i0) {
    }

    @EventId(126)
    protected void FORTRESS_SIEGE_UNREGISTER_RETURNED(@EventId(name = "talker", value = 40) Creature talker,
                                                      @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(127)
    protected void FORTRESS_CONTRACT_CASTLE_RETURNED(@EventId(name = "talker", value = 40) Creature talker,
                                                     @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(128)
    protected void FORTRESS_UPGRADE_FACILITY_RETURNED(@EventId(name = "talker", value = 40) Creature talker,
                                                      @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(129)
    protected void ON_OLYMPIAD_GAME_PREPARED(@EventId(name = "event_id", value = 236) int event_id,
                                             @EventId(name = "i0", value = 288) int i0) {
    }

    @EventId(130)
    protected void PLEDGE_CASTLE_SIEGE_DEFENCE_COUNT_DECREASE_RETURNED(@EventId(name = "talker", value = 40) Creature talker,
                                                                       @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(131)
    protected void REGISTER_PVP_MATCH_RESULT(@EventId(name = "talker", value = 40) Creature talker,
                                             @EventId(name = "reply", value = 280) int reply,
                                             @EventId(name = "maker0", value = 632) NpcMaker maker0,
                                             @EventId(name = "party0", value = 480) Party party0,
                                             @EventId(name = "party1", value = 488) Party party1,
                                             @EventId(name = "fhtml0", value = 600) FHTML fhtml0,
                                             @EventId(name = "fhtml1", value = 608) FHTML fhtml1,
                                             @EventId(name = "c0", value = 424) Creature c0,
                                             @EventId(name = "i0", value = 288) int i0,
                                             @EventId(name = "i1", value = 296) int i1,
                                             @EventId(name = "i2", value = 304) int i2,
                                             @EventId(name = "i3", value = 312) int i3,
                                             @EventId(name = "i4", value = 320) int i4,
                                             @EventId(name = "i5", value = 328) int i5,
                                             @EventId(name = "i6", value = 336) int i6,
                                             @EventId(name = "i7", value = 344) int i7) {
    }

    @EventId(132)
    protected void UNREGISTER_PVP_MATCH_RESULT(@EventId(name = "talker", value = 40) Creature talker,
                                               @EventId(name = "party0", value = 480) Party party0,
                                               @EventId(name = "party1", value = 488) Party party1,
                                               @EventId(name = "fhtml0", value = 600) FHTML fhtml0,
                                               @EventId(name = "c0", value = 424) Creature c0,
                                               @EventId(name = "c1", value = 432) Creature c1,
                                               @EventId(name = "c2", value = 440) Creature c2,
                                               @EventId(name = "c3", value = 448) Creature c3,
                                               @EventId(name = "c4", value = 456) Creature c4,
                                               @EventId(name = "i0", value = 288) int i0,
                                               @EventId(name = "i1", value = 296) int i1,
                                               @EventId(name = "i2", value = 304) int i2,
                                               @EventId(name = "i3", value = 312) int i3,
                                               @EventId(name = "i4", value = 320) int i4,
                                               @EventId(name = "i5", value = 328) int i5,
                                               @EventId(name = "i6", value = 336) int i6,
                                               @EventId(name = "i7", value = 344) int i7,
                                               @EventId(name = "i8", value = 352) int i8) {
    }

    @EventId(133)
    protected void START_PVP_MATCH_RESULT(@EventId(name = "reply", value = 280) int reply,
                                          @EventId(name = "maker0", value = 632) NpcMaker maker0,
                                          @EventId(name = "party0", value = 480) Party party0,
                                          @EventId(name = "party1", value = 488) Party party1,
                                          @EventId(name = "h0", value = 520) HateInfo h0,
                                          @EventId(name = "c0", value = 424) Creature c0,
                                          @EventId(name = "i0", value = 288) int i0,
                                          @EventId(name = "i1", value = 296) int i1,
                                          @EventId(name = "i2", value = 304) int i2,
                                          @EventId(name = "i3", value = 312) int i3,
                                          @EventId(name = "i4", value = 320) int i4,
                                          @EventId(name = "i5", value = 328) int i5,
                                          @EventId(name = "i6", value = 336) int i6,
                                          @EventId(name = "i7", value = 344) int i7) {
    }

    @EventId(134)
    protected void END_PVP_MATCH_RESULT(@EventId(name = "party0", value = 480) Party party0,
                                        @EventId(name = "party1", value = 488) Party party1,
                                        @EventId(name = "c0", value = 424) Creature c0,
                                        @EventId(name = "i0", value = 288) int i0,
                                        @EventId(name = "i1", value = 296) int i1,
                                        @EventId(name = "i2", value = 304) int i2,
                                        @EventId(name = "i3", value = 312) int i3,
                                        @EventId(name = "i4", value = 320) int i4,
                                        @EventId(name = "i5", value = 328) int i5,
                                        @EventId(name = "i6", value = 336) int i6,
                                        @EventId(name = "i7", value = 344) int i7,
                                        @EventId(name = "i8", value = 352) int i8,
                                        @EventId(name = "i9", value = 360) int i9) {
    }

    @EventId(136)
    protected void CHECK_REGISTER_PARTY_RESULT2(@EventId(name = "reply", value = 280) int reply,
                                                @EventId(name = "i0", value = 288) int i0,
                                                @EventId(name = "i1", value = 296) int i1,
                                                @EventId(name = "i2", value = 304) int i2,
                                                @EventId(name = "i3", value = 312) int i3,
                                                @EventId(name = "i4", value = 320) int i4,
                                                @EventId(name = "i5", value = 328) int i5,
                                                @EventId(name = "i6", value = 336) int i6,
                                                @EventId(name = "i7", value = 344) int i7) {
    }

    @EventId(137)
    protected void REGISTER_RESURRECTION_TOWER_RESULT(@EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(138)
    protected void CHECK_REGISTER_USER_RESULT(@EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(139)
    protected void IS_USER_PVPMATCHING_RESULT(@EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(140)
    protected void REGISTER_USER_PVP_MATCH_RESULT(@EventId(name = "talker", value = 40) Creature talker,
                                                  @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(141)
    protected void UNREGISTER_USER_PVP_MATCH_RESULT(@EventId(name = "talker", value = 40) Creature talker,
                                                    @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(142)
    protected void REGISTER_USER_RESURRECTION_TOWER_RESULT(@EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(143)
    protected void KILLED_USER_PVP_MATCH_RESULT(@EventId(name = "talker", value = 40) Creature talker,
                                                @EventId(name = "reply", value = 280) int reply,
                                                @EventId(name = "attacker", value = 48) Creature attacker) {
    }

    @EventId(144)
    protected void GET_RANK_USER_PVP_MATCH_RESULT(@EventId(name = "talker", value = 40) Creature talker,
                                                  @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(145)
    protected void LET_IN_USER_PVP_MATCH(@EventId(name = "talker", value = 40) Creature talker) {
    }

    @EventId(146)
    protected void START_USER_PVP_MATCH_RESULT(@EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(147)
    protected void WITHDRAW_USER_PVP_MATCH_RESULT(@EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(148)
    protected void END_USER_PVP_MATCH_RESULT(@EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(149)
    protected void GET_RELATED_FORTRESS_LIST_RETURNED(@EventId(name = "talker", value = 40) Creature talker) {
    }

    @EventId(154)
    protected void DOMINION_SIEGE_START(@EventId(name = "talker", value = 40) Creature talker) {
    }

    @EventId(155)
    protected void DOMINION_SIEGE_END(@EventId(name = "talker", value = 40) Creature talker) {
    }

    @EventId(156)
    protected void DOMINION_SIEGE_PC_KILLED(@EventId(name = "talker", value = 40) Creature talker,
                                            @EventId(name = "attacker", value = 48) Creature attacker) {
    }

    @EventId(157)
    protected void CLEFT_STATE_CHANGED() {
    }

    @EventId(158)
    protected void BLOCK_UPSET_STARTED() {
    }

    @EventId(159)
    protected void BLOCK_UPSET_FINISHED() {
    }

    @EventId(160)
    protected void LEVEL_UP(@EventId(name = "talker", value = 40) Creature talker,
                            @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(161)
    protected void LOAD_DBSAVING_MAP_RETURNED(@EventId(name = "talker", value = 40) Creature talker) {
    }

    @EventId(162)
    protected void DOMINION_SCRIPT_EVENT(@EventId(name = "talker", value = 40) Creature talker,
                                         @EventId(name = "script_event_arg1", value = 248) int script_event_arg1,
                                         @EventId(name = "script_event_arg2", value = 252) int script_event_arg2,
                                         @EventId(name = "script_event_arg3", value = 256) int script_event_arg3) {
    }

    @EventId(163)
    protected void DIE_SET(@EventId(name = "talker", value = 40) Creature talker) {
    }

    @EventId(165)
    protected void SOCIAL_ACTION_EVENT(@EventId(name = "talker", value = 40) Creature talker,
                                       @EventId(name = "action_id", value = 264) int action_id) {
    }

    @EventId(166)
    protected void PET_DIED() {
    }

    @EventId(167)
    protected void WAS_COLLECTED(@EventId(name = "attacker", value = 48) Creature attacker) {
    }

    @EventId(168)
    protected void ON_AIRSHIP_EVENT(@EventId(name = "state", value = 148) int state) {
    }

    @EventId(169)
    protected void DOMINION_SUPPLY_DESTRUCTED(@EventId(name = "attacker", value = 48) Creature attacker) {
    }

    @EventId(170)
    protected void CREATE_NPC_TO_WINNER(@EventId(name = "target", value = 96) Creature target) {
    }

    @EventId(172)
    protected void DEBUG_AI(@EventId(name = "creature", value = 112) Creature creature,
                            @EventId(name = "talker", value = 40) Creature talker,
                            @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(173)
    protected void IS_TOGGLE_SKILL_ONOFF(@EventId(name = "i0", value = 288) int i0) {
    }

    @EventId(174)
    protected void FIELD_CYCLE_STEP_EXPIRED(@EventId(name = "event_id", value = 236) int event_id,
                                            @EventId(name = "i0", value = 288) int i0,
                                            @EventId(name = "i1", value = 296) int i1) {
    }

    @EventId(175)
    protected void SPELL_SUCCESSED(@EventId(name = "target", value = 96) Creature target,
                                   @EventId(name = "skill_name_id", value = 176) int skill_name_id,
                                   @EventId(name = "pos0", value = 680) Point pos0,
                                   @EventId(name = "maker0", value = 632) NpcMaker maker0,
                                   @EventId(name = "i0", value = 288) int i0) {
    }

    @EventId(176)
    protected void GET_EVENT_RANKER_INFO(@EventId(name = "talker", value = 40) Creature talker,
                                         @EventId(name = "i0", value = 288) int i0,
                                         @EventId(name = "i1", value = 296) int i1,
                                         @EventId(name = "i2", value = 304) int i2) {
    }

    @EventId(177)
    protected void OLYMPIAD_MATCH_RESULT_EVENT(@EventId(name = "talker", value = 40) Creature talker,
                                               @EventId(name = "ask", value = 160) int ask,
                                               @EventId(name = "reply", value = 280) int reply,
                                               @EventId(name = "i0", value = 288) int i0) {
    }

    @EventId(178)
    protected void GET_PLAYING_USER_COUNT(@EventId(name = "i0", value = 288) int i0) {
    }

    @EventId(179)
    protected void FIND_RANDOM_USER(@EventId(name = "talker", value = 40) Creature talker) {
    }

    @EventId(180)
    protected void GIVE_EVENT_DATA(@EventId(name = "talker", value = 40) Creature talker) {
    }

    @EventId(181)
    protected void PET_AI(@EventId(name = "talker", value = 40) Creature talker,
                          @EventId(name = "state", value = 148) int state) {
    }

    @EventId(182)
    protected void PET_REACTION(@EventId(name = "talker", value = 40) Creature talker,
                                @EventId(name = "state", value = 148) int state) {
    }

    @EventId(183)
    protected void USE_SKILL_STARTED(@EventId(name = "skill_name_id", value = 176) int skill_name_id) {
    }

    @EventId(184)
    protected void INZONE_ALL_USER(@EventId(name = "target", value = 96) Creature target,
                                   @EventId(name = "c0", value = 424) Creature c0,
                                   @EventId(name = "party0", value = 480) Party party0,
                                   @EventId(name = "pos0", value = 680) Point pos0,
                                   @EventId(name = "i0", value = 288) int i0) {
    }

    @EventId(186)
    protected void DOOR_STATUS(@EventId(name = "i0", value = 288) int i0,
                               @EventId(name = "i1", value = 296) int i1,
                               @EventId(name = "s0", value = 584) String s0) {
    }

    @EventId(187)
    protected void MASTER_ATTACK(@EventId(name = "attacker", value = 48) Creature attacker,
                                 @EventId(name = "target", value = 96) Creature target,
                                 @EventId(name = "skill_id", value = 164) int skill_id,
                                 @EventId(name = "skill_level", value = 168) int skill_level,
                                 @EventId(name = "skill_name_id", value = 176) int skill_name_id) {
    }

    @EventId(191)
    protected void CALL_TO_CHANGE_CLASS(@EventId(name = "talker", value = 40) Creature talker) {
    }

    @EventId(192)
    protected void CHANGE_TO_AWAKENED_CLASS(@EventId(name = "talker", value = 40) Creature talker,
                                            @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(193)
    protected void MASTER_ATTACKED(@EventId(name = "attacker", value = 48) Creature attacker,
                                   @EventId(name = "target", value = 96) Creature target,
                                   @EventId(name = "damage", value = 144) int damage) {
    }

    @EventId(194)
    protected void TOTAL_REWARD_SKILL_ENCHANT_COUNT(@EventId(name = "talker", value = 40) Creature talker) {
    }

    @EventId(195)
    protected void INSTANT_AGIT_SET_OWNER(@EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(196)
    protected void CHANGE_CLASS(@EventId(name = "talker", value = 40) Creature talker,
                                @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(197)
    protected void CAN_CHANGE_TO_SUBCLASS(@EventId(name = "talker", value = 40) Creature talker,
                                          @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(198)
    protected void SCENE_STOPPED(@EventId(name = "talker", value = 40) Creature talker) {
    }
}