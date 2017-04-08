package org.l2junity.gameserver.retail.ai;


import org.l2junity.gameserver.retail.AiEventId;
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

    @AiEventId(808)
    protected Npc myself = null;

    @AiEventId(712)
    protected GlobalObject gg = null;

    @AiEventId(64)
    protected Creature _private;

    @AiEventId(664)
    protected RoomInfoList rlist0;
    @AiEventId(648)
    protected RoomInfo room0;
    @AiEventId(656)
    protected RoomInfo room1;
    @AiEventId(512)
    protected StaticObject so0;
    @AiEventId(680)
    protected Point pos0;
    @AiEventId(688)
    protected Point pos1;
    @AiEventId(560)
    protected Npc npc0;
    @AiEventId(632)
    protected NpcMaker maker0;
    @AiEventId(640)
    protected NpcMaker maker1;
    @AiEventId(584)
    protected String s0;
    @AiEventId(592)
    protected String s1;
    @AiEventId(600)
    protected FHTML fhtml0;
    @AiEventId(608)
    protected FHTML fhtml1;
    @AiEventId(496)
    protected ItemData item0;
    @AiEventId(384)
    protected double f0;
    @AiEventId(388)
    protected double f1;
    @AiEventId(392)
    protected double f2;
    @AiEventId(396)
    protected double f3;
    @AiEventId(400)
    protected double f4;
    @AiEventId(424)
    protected Creature c0;
    @AiEventId(432)
    protected Creature c1;
    @AiEventId(440)
    protected Creature c2;
    @AiEventId(448)
    protected Creature c3;
    @AiEventId(456)
    protected Creature c4;
    @AiEventId(480)
    protected Party party0;
    @AiEventId(488)
    protected Party party1;
    @AiEventId(464)
    protected Clan pledge0;
    @AiEventId(value = 472)
    protected Clan pledge1; // TODO: Incorrect?
    @AiEventId(520)
    protected HateInfo h0;
    @AiEventId(528)
    protected HateInfo h1;
    @AiEventId(288)
    protected int i0;
    @AiEventId(296)
    protected int i1;
    @AiEventId(304)
    protected int i2;
    @AiEventId(312)
    protected int i3;
    @AiEventId(320)
    protected int i4;
    @AiEventId(328)
    protected int i5;
    @AiEventId(336)
    protected int i6;
    @AiEventId(344)
    protected int i7;
    @AiEventId(352)
    protected int i8;
    @AiEventId(360)
    protected int i9;
    @AiEventId(368)
    protected int i10;
    @AiEventId(376)
    protected int i11;

    @AiEventId(744)
    protected SpawnDefine def0;
    @AiEventId(776)
    protected CodeInfoList random1_list;
    @AiEventId(784)
    protected CodeInfoList always_list;

    @AiEventId(0)
    protected void NO_DESIRE() {
    }

    @AiEventId(1)
    protected void ATTACKED(@AiEventId(name = "attacker", value = 48) Creature attacker,
                            @AiEventId(name = "victim", value = 56) Creature victim,
                            @AiEventId(name = "target", value = 96) Creature target,
                            @AiEventId(name = "skill_id", value = 164) int skill_id,
                            @AiEventId(name = "skill_name_id", value = 176) int skill_name_id,
                            @AiEventId(name = "damage", value = 144) int damage,
                            @AiEventId(name = "IsCritical", value = 268) int IsCritical,
                            @AiEventId(name = "inzone_pc", value = 999999) int inzone_pc) {
    }

    @AiEventId(2)
    protected void SPELLED(@AiEventId(name = "attacker", value = 48) Creature attacker,
                           @AiEventId(name = "speller", value = 88) Creature speller,
                           @AiEventId(name = "skill_id", value = 164) int skill_id,
                           @AiEventId(name = "skill_name_id", value = 176) int skill_name_id) {
    }

    @AiEventId(3)
    protected void TALKED(@AiEventId(name = "talker", value = 40) Creature talker,
                          @AiEventId(name = "ask", value = 160) int ask,
                          @AiEventId(name = "reply", value = 280) int reply,
                          @AiEventId(name = "_from_choice", value = 624) int _from_choice,
                          @AiEventId(name = "_choiceN", value = 620) int _choiceN,
                          @AiEventId(name = "_code", value = 616) int _code) {
    }

    @AiEventId(4)
    protected void TALK_SELECTED(@AiEventId(name = "talker", value = 40) Creature talker,
                                 @AiEventId(name = "ask", value = 160) int ask,
                                 @AiEventId(name = "reply", value = 280) int reply,
                                 @AiEventId(name = "_from_choice", value = 624) int _from_choice,
                                 @AiEventId(name = "_choiceN", value = 620) int _choiceN,
                                 @AiEventId(name = "_code", value = 616) int _code) {
    }

    @AiEventId(5)
    protected void SEE_CREATURE(@AiEventId(name = "creature", value = 112) Creature creature) {
    }

    @AiEventId(7)
    protected void SEE_ITEM(@AiEventId(name = "item_index_list", value = 800) ItemIndexList item_index_list) {
    }

    @AiEventId(8)
    protected void ON_DOOR_EVENT(@AiEventId(name = "enabled", value = 1440) int enabled) {
    }

    @AiEventId(9)
    protected void MY_DYING(@AiEventId(name = "last_attacker", value = 736) Creature last_attacker,
                            @AiEventId(name = "attacker", value = 48) Creature attacker,
                            @AiEventId(name = "target", value = 96) Creature target,
                            @AiEventId(name = "talker", value = 40) Creature talker,
                            @AiEventId(name = "creature", value = 112) Creature creature,
                            @AiEventId(name = "reply", value = 280) int reply,
                            @AiEventId(name = "skill_name_id", value = 176) int skill_name_id,
                            @AiEventId(name = "private", value = 64) Creature _private,
                            @AiEventId(name = "code_info", value = 792) CodeInfo code_info,
                            @AiEventId(name = "lparty", value = 752) Party lparty,
                            @AiEventId(name = "lmpcc", value = 760) MPCC lmpcc,
                            @AiEventId(name = "aparty", value = 768) Party aparty) {
    }

    @AiEventId(10)
    protected void ON_END_SIEGE_EVENT() {
    }

    @AiEventId(11)
    protected void TIMER_FIRED_EX(@AiEventId(name = "timer_id", value = 244) int timer_id,
                                  @AiEventId(name = "talker", value = 40) Creature talker,
                                  @AiEventId(name = "attacker", value = 48) Creature attacker,
                                  @AiEventId(name = "private", value = 64) Creature _private,
                                  @AiEventId(name = "target", value = 96) Creature target,
                                  @AiEventId(name = "creature", value = 112) Creature creature) {
    }

    @AiEventId(12)
    protected void CREATED(@AiEventId(name = "ask", value = 160) int ask,
                           @AiEventId(name = "reply", value = 280) int reply,
                           @AiEventId(name = "creature", value = 112) Creature creature,
                           @AiEventId(name = "attacker", value = 48) Creature attacker) {
    }

    @AiEventId(13)
    protected void ON_CANCEL_SIEGE_EVENT() {
    }

    @AiEventId(14)
    protected void ON_START_TEAMBATTLEAGIT_FINAL_EVENT() {
    }

    @AiEventId(15)
    protected void SEE_SPELL(@AiEventId(name = "speller", value = 88) Creature speller,
                             @AiEventId(name = "target", value = 96) Creature target,
                             @AiEventId(name = "skill_id", value = 164) int skill_id,
                             @AiEventId(name = "skill_name_id", value = 176) int skill_name_id,
                             @AiEventId(name = "x", value = 204) int x,
                             @AiEventId(name = "y", value = 208) int y,
                             @AiEventId(name = "z", value = 212) int z) {
    }

    @AiEventId(16)
    protected void OUT_OF_TERRITORY() {
    }

    @AiEventId(17)
    protected void DESIRE_MANIPULATION(@AiEventId(name = "speller", value = 88) Creature speller,
                                       @AiEventId(name = "skill_name_id", value = 176) int skill_name_id,
                                       @AiEventId(name = "desire", value = 220) int desire) {
    }

    @AiEventId(18)
    protected void PARTY_ATTACKED(@AiEventId(name = "attacker", value = 48) Creature attacker,
                                  @AiEventId(name = "damage", value = 144) int damage,
                                  @AiEventId(name = "skill_name_id", value = 176) int skill_name_id,
                                  @AiEventId(name = "private", value = 64) Creature _private) {
    }

    @AiEventId(20)
    protected void PARTY_DIED(@AiEventId(name = "private", value = 64) Creature _private,
                              @AiEventId(name = "attacker", value = 48) Creature attacker) {
    }

    @AiEventId(21)
    protected void ON_DECLARE_DOMINION_EVENT() {
    }

    @AiEventId(22)
    protected void CLAN_ATTACKED(@AiEventId(name = "attacker", value = 48) Creature attacker,
                                 @AiEventId(name = "damage", value = 144) int damage,
                                 @AiEventId(name = "skill_name_id", value = 176) int skill_name_id,
                                 @AiEventId(name = "victim", value = 56) Creature victim) {
    }

    @AiEventId(24)
    protected void STATIC_OBJECT_CLAN_ATTACKED(@AiEventId(name = "attacker", value = 48) Creature attacker,
                                               @AiEventId(name = "damage", value = 144) int damage,
                                               @AiEventId(name = "skill_name_id", value = 176) int skill_name_id) {
    }

    @AiEventId(27)
    protected void TELEPORT_REQUESTED(@AiEventId(name = "talker", value = 40) Creature talker) {
    }

    @AiEventId(30)
    protected void QUEST_ACCEPTED(@AiEventId(name = "quest_id", value = 156) int quest_id,
                                  @AiEventId(name = "talker", value = 40) Creature talker) {
    }

    @AiEventId(31)
    protected void MENU_SELECTED(@AiEventId(name = "talker", value = 40) Creature talker,
                                 @AiEventId(name = "ask", value = 160) int ask,
                                 @AiEventId(name = "reply", value = 280) int reply,
                                 @AiEventId(name = "state", value = 148) int state,
                                 @AiEventId(name = "quest_id", value = 156) int quest_id,
                                 @AiEventId(name = "action_id", value = 264) int action_id) {
    }

    @AiEventId(32)
    protected void LEARN_SKILL_REQUESTED(@AiEventId(name = "talker", value = 40) Creature talker) {
    }

    @AiEventId(33)
    protected void ENCHANT_SKILL_REQUESTED(@AiEventId(name = "talker", value = 40) Creature talker,
                                           @AiEventId(name = "action_id", value = 264) int action_id) {
    }

    @AiEventId(34)
    protected void ONE_SKILL_SELECTED(@AiEventId(name = "talker", value = 40) Creature talker,
                                      @AiEventId(name = "skill_name_id", value = 176) int skill_name_id,
                                      @AiEventId(name = "i0", value = 288) int i0) {
    }

    @AiEventId(35)
    protected void ONE_ENCHANT_SKILL_SELECTED(@AiEventId(name = "talker", value = 40) Creature talker,
                                              @AiEventId(name = "skill_name_id", value = 176) int skill_name_id,
                                              @AiEventId(name = "action_id", value = 264) int action_id) {
    }

    @AiEventId(37)
    protected void CLASS_CHANGE_REQUESTED(@AiEventId(name = "talker", value = 40) Creature talker,
                                          @AiEventId(name = "occupation_name_id", value = 180) int occupation_name_id) {
    }

    @AiEventId(38)
    protected void MANOR_MENU_SELECTED(@AiEventId(name = "talker", value = 40) Creature talker,
                                       @AiEventId(name = "ask", value = 160) int ask,
                                       @AiEventId(name = "state", value = 148) int state,
                                       @AiEventId(name = "time", value = 192) int time) {
    }

    @AiEventId(44)
    protected void CREATE_PLEDGE(@AiEventId(name = "talker", value = 40) Creature talker,
                                 @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(45)
    protected void DISMISS_PLEDGE(@AiEventId(name = "talker", value = 40) Creature talker,
                                  @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(46)
    protected void REVIVE_PLEDGE(@AiEventId(name = "talker", value = 40) Creature talker,
                                 @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(47)
    protected void LEVEL_UP_PLEDGE(@AiEventId(name = "talker", value = 40) Creature talker,
                                   @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(50)
    protected void CREATE_ALLIANCE(@AiEventId(name = "talker", value = 40) Creature talker,
                                   @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(51)
    protected void SCRIPT_EVENT(@AiEventId(name = "script_event_arg1", value = 248) int script_event_arg1,
                                @AiEventId(name = "script_event_arg2", value = 252) int script_event_arg2,
                                @AiEventId(name = "script_event_arg3", value = 256) int script_event_arg3,
                                @AiEventId(name = "private", value = 64) Creature _private,
                                @AiEventId(name = "attacker", value = 48) Creature attacker) {
    }

    @AiEventId(52)
    protected void TUTORIAL_EVENT(@AiEventId(name = "talker", value = 40) Creature talker,
                                  @AiEventId(name = "event_id", value = 236) int event_id) {
    }

    @AiEventId(53)
    protected void QUESTION_MARK_CLICKED(@AiEventId(name = "talker", value = 40) Creature talker,
                                         @AiEventId(name = "question_id", value = 240) int question_id) {
    }

    @AiEventId(54)
    protected void USER_CONNECTED(@AiEventId(name = "talker", value = 40) Creature talker) {
    }

    @AiEventId(57)
    protected void ATTACK_FINISHED(@AiEventId(name = "target", value = 96) Creature target) {
    }

    @AiEventId(60)
    protected void GET_ITEM_FINISHED(@AiEventId(name = "item", value = 128) ItemData item,
                                     @AiEventId(name = "success", value = 200) int success) {
    }

    @AiEventId(63)
    protected void MOVE_TO_WAY_POINT_FINISHED(@AiEventId(name = "way_point_index", value = 184) int way_point_index,
                                              @AiEventId(name = "next_way_point_index", value = 188) int next_way_point_index) {
    }

    @AiEventId(64)
    protected void USE_SKILL_FINISHED(@AiEventId(name = "attacker", value = 48) Creature attacker,
                                      @AiEventId(name = "target", value = 96) Creature target,
                                      @AiEventId(name = "skill_id", value = 164) int skill_id,
                                      @AiEventId(name = "skill_name_id", value = 176) int skill_name_id,
                                      @AiEventId(name = "success", value = 200) int success,
                                      @AiEventId(name = "ask", value = 160) int ask,
                                      @AiEventId(name = "reply", value = 280) int reply,
                                      @AiEventId(name = "creature", value = 112) Creature creature) {
    }

    @AiEventId(65)
    protected void MOVE_TO_FINISHED(@AiEventId(name = "x", value = 204) int x,
                                    @AiEventId(name = "y", value = 208) int y,
                                    @AiEventId(name = "z", value = 212) int z,
                                    @AiEventId(name = "attacker", value = 48) Creature attacker) {
    }

    @AiEventId(77)
    protected void DOOR_HP_LEVEL_INFORMED(@AiEventId(name = "talker", value = 40) Creature talker,
                                          @AiEventId(name = "level", value = 260) int level) {
    }

    @AiEventId(78)
    protected void CONTROLTOWER_LEVEL_INFORMED(@AiEventId(name = "talker", value = 40) Creature talker,
                                               @AiEventId(name = "level", value = 260) int level) {
    }

    @AiEventId(79)
    protected void TB_REGISTER_PLEDGE_RETURNED(@AiEventId(name = "talker", value = 40) Creature talker,
                                               @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(80)
    protected void TB_REGISTER_MEMBER_RETURNED(@AiEventId(name = "talker", value = 40) Creature talker,
                                               @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(81)
    protected void TB_GET_NPC_TYPE_INFORMED(@AiEventId(name = "talker", value = 40) Creature talker,
                                            @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(82)
    protected void TB_SET_NPC_TYPE_RETURNED(@AiEventId(name = "talker", value = 40) Creature talker,
                                            @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(83)
    protected void TB_GET_PLEDGE_REGISTER_STATUS_INFORMED(@AiEventId(name = "talker", value = 40) Creature talker,
                                                          @AiEventId(name = "reply", value = 280) int reply,
                                                          @AiEventId(name = "pledge0", value = 464) Clan pledge0,
                                                          @AiEventId(name = "fhtml0", value = 600) FHTML fhtml0) {
    }

    @AiEventId(84)
    protected void TB_GET_BATTLE_ROYAL_PLEDGE_LIST_INFORMED(@AiEventId(name = "talker", value = 40) Creature talker) {
    }

    @AiEventId(85)
    protected void SUBJOB_LIST_INFORMED(@AiEventId(name = "talker", value = 40) Creature talker,
                                        @AiEventId(name = "ask", value = 160) int ask,
                                        @AiEventId(name = "i0", value = 288) int i0,
                                        @AiEventId(name = "i1", value = 296) int i1,
                                        @AiEventId(name = "i2", value = 304) int i2,
                                        @AiEventId(name = "i3", value = 312) int i3,
                                        @AiEventId(name = "i4", value = 320) int i4,
                                        @AiEventId(name = "i5", value = 328) int i5,
                                        @AiEventId(name = "i6", value = 336) int i6,
                                        @AiEventId(name = "i7", value = 344) int i7,
                                        @AiEventId(name = "i8", value = 352) int i8,
                                        @AiEventId(name = "i9", value = 360) int i9,
                                        @AiEventId(name = "i10", value = 368) int i10,
                                        @AiEventId(name = "i11", value = 376) int i11) {
    }

    @AiEventId(86)
    protected void SUBJOB_CREATED(@AiEventId(name = "talker", value = 40) Creature talker,
                                  @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(87)
    protected void SUBJOB_CHANGED(@AiEventId(name = "talker", value = 40) Creature talker,
                                  @AiEventId(name = "reply", value = 280) int reply,
                                  @AiEventId(name = "level", value = 260) int level) {
    }

    @AiEventId(88)
    protected void SUBJOB_RENEWED(@AiEventId(name = "talker", value = 40) Creature talker,
                                  @AiEventId(name = "reply", value = 280) int reply,
                                  @AiEventId(name = "i0", value = 288) int i0) {
    }

    @AiEventId(89)
    protected void ON_SSQ_SYSTEM_EVENT(@AiEventId(name = "talker", value = 40) Creature talker,
                                       @AiEventId(name = "ask", value = 160) int ask,
                                       @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(90)
    protected void SET_AGIT_DECO_RETURNED(@AiEventId(name = "talker", value = 40) Creature talker,
                                          @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(91)
    protected void RESET_AGIT_DECO_RETURNED(@AiEventId(name = "talker", value = 40) Creature talker,
                                            @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(92)
    protected void AUCTION_AGIT_GET_COST_INFO_RETURNED(@AiEventId(name = "talker", value = 40) Creature talker,
                                                       @AiEventId(name = "reply", value = 280) int reply,
                                                       @AiEventId(name = "s0", value = 584) String s0) {
    }

    @AiEventId(93)
    protected void CLAN_DIED(@AiEventId(name = "victim", value = 56) Creature victim,
                             @AiEventId(name = "attacker", value = 48) Creature attacker) {
    }

    @AiEventId(95)
    protected void SET_HERO_RETURNED(@AiEventId(name = "talker", value = 40) Creature talker,
                                     @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(97)
    protected void DELETE_OLYMPIAD_TRADE_POINT_RETURNED(@AiEventId(name = "talker", value = 40) Creature talker,
                                                        @AiEventId(name = "ask", value = 160) int ask,
                                                        @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(98)
    protected void MG_REGISTER_PLEDGE_RETURNED(@AiEventId(name = "talker", value = 40) Creature talker,
                                               @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(99)
    protected void MG_UNREGISTER_PLEDGE_RETURNED(@AiEventId(name = "talker", value = 40) Creature talker,
                                                 @AiEventId(name = "reply", value = 280) int reply,
                                                 @AiEventId(name = "i0", value = 288) int i0) {
    }

    @AiEventId(101)
    protected void MG_JOIN_GAME_RETURNED(@AiEventId(name = "talker", value = 40) Creature talker,
                                         @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(102)
    protected void MG_GET_UNRETURNED_POINT_RETURNED(@AiEventId(name = "talker", value = 40) Creature talker,
                                                    @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(103)
    protected void CREATE_ACADEMY(@AiEventId(name = "talker", value = 40) Creature talker,
                                  @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(104)
    protected void CREATE_SUBPLEDGE(@AiEventId(name = "talker", value = 40) Creature talker,
                                    @AiEventId(name = "reply", value = 280) int reply,
                                    @AiEventId(name = "i0", value = 288) int i0,
                                    @AiEventId(name = "i1", value = 296) int i1) {
    }

    @AiEventId(105)
    protected void CHECK_CURSED_USER_RETURNED(@AiEventId(name = "talker", value = 40) Creature talker,
                                              @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(106)
    protected void UPDATE_SUBPLEDGE_MASTER(@AiEventId(name = "talker", value = 40) Creature talker,
                                           @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(107)
    protected void UPGRADE_SUBPLEDGE_MEMBER_COUNT(@AiEventId(name = "talker", value = 40) Creature talker,
                                                  @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(108)
    protected void MPCC_TELEPORTED(@AiEventId(name = "talker", value = 40) Creature talker,
                                   @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(109)
    protected void PLEDGE_MASTER_TRANSFER(@AiEventId(name = "talker", value = 40) Creature talker,
                                          @AiEventId(name = "reply", value = 280) int reply,
                                          @AiEventId(name = "s0", value = 584) String s0) {
    }

    @AiEventId(110)
    protected void PLEDGE_MASTER_TRANSFER_CANCEL(@AiEventId(name = "talker", value = 40) Creature talker,
                                                 @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(111)
    protected void TB_CHECK_MEMBER_REGISTER_STATUS(@AiEventId(name = "talker", value = 40) Creature talker,
                                                   @AiEventId(name = "reply", value = 280) int reply,
                                                   @AiEventId(name = "pledge0", value = 464) Clan pledge0) {
    }

    @AiEventId(112)
    protected void RENAME_SUBPLEDGE(@AiEventId(name = "talker", value = 40) Creature talker,
                                    @AiEventId(name = "reply", value = 280) int reply,
                                    @AiEventId(name = "s0", value = 584) String s0,
                                    @AiEventId(name = "i0", value = 288) int i0) {
    }

    @AiEventId(113)
    protected void ABNORMAL_STATUS_CHANGED(@AiEventId(value = 88, name = "speller") Creature speller,
                                           @AiEventId(name = "skill_id", value = 164) int skill_id,
                                           @AiEventId(name = "skill_level", value = 168) int skill_level,
                                           @AiEventId(name = "skill_name_id", value = 176) int skill_name_id) {
    }

    @AiEventId(114)
    protected void TELEPORT_TO_USER_RES(@AiEventId(name = "talker", value = 40) Creature talker,
										@AiEventId(name = "success", value = 200) int success,
										@AiEventId(name = "c0", value = 424) Creature c0, @AiEventId(name = "i0", value = 288) int i0) {
    }

    @AiEventId(115)
    protected void NODE_ARRIVED(@AiEventId(name = "script_event_arg1", value = 248) int script_event_arg1,
                                @AiEventId(name = "script_event_arg2", value = 252) int script_event_arg2,
                                @AiEventId(name = "script_event_arg3", value = 256) int script_event_arg3,
                                @AiEventId(name = "state", value = 148) int state,
                                @AiEventId(name = "success", value = 200) int success) {
    }

    @AiEventId(116)
    protected void TRAP_STEP_IN(@AiEventId(name = "i0", value = 288) int i0,
                                @AiEventId(name = "i1", value = 296) int i1,
                                @AiEventId(name = "i2", value = 304) int i2,
                                @AiEventId(name = "i3", value = 312) int i3) {
    }

    @AiEventId(117)
    protected void TRAP_STEP_OUT(@AiEventId(name = "c0", value = 424) Creature c0,
                                 @AiEventId(name = "i0", value = 288) int i0,
                                 @AiEventId(name = "i1", value = 296) int i1) {
    }

    @AiEventId(118)
    protected void TRAP_ACTIVATED() {
    }

    @AiEventId(119)
    protected void TRAP_DEFUSED() {
    }

    @AiEventId(120)
    protected void TRAP_DETECTED() {
    }

    @AiEventId(121)
    protected void SHOW_ENCHANT_SKILL_DRAWER(@AiEventId(name = "talker", value = 40) Creature talker,
                                             @AiEventId(name = "skill_name_id", value = 176) int skill_name_id,
                                             @AiEventId(name = "action_id", value = 264) int action_id) {
    }

    @AiEventId(123)
    protected void INSTANT_ZONE_ENTER_RETURNED(@AiEventId(name = "talker", value = 40) Creature talker,
                                               @AiEventId(name = "reply", value = 280) int reply,
                                               @AiEventId(name = "state", value = 148) int state) {
    }

    @AiEventId(125)
    protected void FORTRESS_SIEGE_REGISTER_RETURNED(@AiEventId(name = "talker", value = 40) Creature talker,
                                                    @AiEventId(name = "reply", value = 280) int reply,
                                                    @AiEventId(name = "i0", value = 288) int i0) {
    }

    @AiEventId(126)
    protected void FORTRESS_SIEGE_UNREGISTER_RETURNED(@AiEventId(name = "talker", value = 40) Creature talker,
                                                      @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(127)
    protected void FORTRESS_CONTRACT_CASTLE_RETURNED(@AiEventId(name = "talker", value = 40) Creature talker,
                                                     @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(128)
    protected void FORTRESS_UPGRADE_FACILITY_RETURNED(@AiEventId(name = "talker", value = 40) Creature talker,
                                                      @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(129)
    protected void ON_OLYMPIAD_GAME_PREPARED(@AiEventId(name = "event_id", value = 236) int event_id,
                                             @AiEventId(name = "i0", value = 288) int i0) {
    }

    @AiEventId(130)
    protected void PLEDGE_CASTLE_SIEGE_DEFENCE_COUNT_DECREASE_RETURNED(@AiEventId(name = "talker", value = 40) Creature talker,
                                                                       @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(131)
    protected void REGISTER_PVP_MATCH_RESULT(@AiEventId(name = "talker", value = 40) Creature talker,
                                             @AiEventId(name = "reply", value = 280) int reply,
                                             @AiEventId(name = "maker0", value = 632) NpcMaker maker0,
                                             @AiEventId(name = "party0", value = 480) Party party0,
                                             @AiEventId(name = "party1", value = 488) Party party1,
                                             @AiEventId(name = "fhtml0", value = 600) FHTML fhtml0,
                                             @AiEventId(name = "fhtml1", value = 608) FHTML fhtml1,
                                             @AiEventId(name = "c0", value = 424) Creature c0,
                                             @AiEventId(name = "i0", value = 288) int i0,
                                             @AiEventId(name = "i1", value = 296) int i1,
                                             @AiEventId(name = "i2", value = 304) int i2,
                                             @AiEventId(name = "i3", value = 312) int i3,
                                             @AiEventId(name = "i4", value = 320) int i4,
                                             @AiEventId(name = "i5", value = 328) int i5,
                                             @AiEventId(name = "i6", value = 336) int i6,
                                             @AiEventId(name = "i7", value = 344) int i7) {
    }

    @AiEventId(132)
    protected void UNREGISTER_PVP_MATCH_RESULT(@AiEventId(name = "talker", value = 40) Creature talker,
                                               @AiEventId(name = "party0", value = 480) Party party0,
                                               @AiEventId(name = "party1", value = 488) Party party1,
                                               @AiEventId(name = "fhtml0", value = 600) FHTML fhtml0,
                                               @AiEventId(name = "c0", value = 424) Creature c0,
                                               @AiEventId(name = "c1", value = 432) Creature c1,
                                               @AiEventId(name = "c2", value = 440) Creature c2,
                                               @AiEventId(name = "c3", value = 448) Creature c3,
                                               @AiEventId(name = "c4", value = 456) Creature c4,
                                               @AiEventId(name = "i0", value = 288) int i0,
                                               @AiEventId(name = "i1", value = 296) int i1,
                                               @AiEventId(name = "i2", value = 304) int i2,
                                               @AiEventId(name = "i3", value = 312) int i3,
                                               @AiEventId(name = "i4", value = 320) int i4,
                                               @AiEventId(name = "i5", value = 328) int i5,
                                               @AiEventId(name = "i6", value = 336) int i6,
                                               @AiEventId(name = "i7", value = 344) int i7,
                                               @AiEventId(name = "i8", value = 352) int i8) {
    }

    @AiEventId(133)
    protected void START_PVP_MATCH_RESULT(@AiEventId(name = "reply", value = 280) int reply,
                                          @AiEventId(name = "maker0", value = 632) NpcMaker maker0,
                                          @AiEventId(name = "party0", value = 480) Party party0,
                                          @AiEventId(name = "party1", value = 488) Party party1,
                                          @AiEventId(name = "h0", value = 520) HateInfo h0,
                                          @AiEventId(name = "c0", value = 424) Creature c0,
                                          @AiEventId(name = "i0", value = 288) int i0,
                                          @AiEventId(name = "i1", value = 296) int i1,
                                          @AiEventId(name = "i2", value = 304) int i2,
                                          @AiEventId(name = "i3", value = 312) int i3,
                                          @AiEventId(name = "i4", value = 320) int i4,
                                          @AiEventId(name = "i5", value = 328) int i5,
                                          @AiEventId(name = "i6", value = 336) int i6,
                                          @AiEventId(name = "i7", value = 344) int i7) {
    }

    @AiEventId(134)
    protected void END_PVP_MATCH_RESULT(@AiEventId(name = "party0", value = 480) Party party0,
                                        @AiEventId(name = "party1", value = 488) Party party1,
                                        @AiEventId(name = "c0", value = 424) Creature c0,
                                        @AiEventId(name = "i0", value = 288) int i0,
                                        @AiEventId(name = "i1", value = 296) int i1,
                                        @AiEventId(name = "i2", value = 304) int i2,
                                        @AiEventId(name = "i3", value = 312) int i3,
                                        @AiEventId(name = "i4", value = 320) int i4,
                                        @AiEventId(name = "i5", value = 328) int i5,
                                        @AiEventId(name = "i6", value = 336) int i6,
                                        @AiEventId(name = "i7", value = 344) int i7,
                                        @AiEventId(name = "i8", value = 352) int i8,
                                        @AiEventId(name = "i9", value = 360) int i9) {
    }

    @AiEventId(136)
    protected void CHECK_REGISTER_PARTY_RESULT2(@AiEventId(name = "reply", value = 280) int reply,
                                                @AiEventId(name = "i0", value = 288) int i0,
                                                @AiEventId(name = "i1", value = 296) int i1,
                                                @AiEventId(name = "i2", value = 304) int i2,
                                                @AiEventId(name = "i3", value = 312) int i3,
                                                @AiEventId(name = "i4", value = 320) int i4,
                                                @AiEventId(name = "i5", value = 328) int i5,
                                                @AiEventId(name = "i6", value = 336) int i6,
                                                @AiEventId(name = "i7", value = 344) int i7) {
    }

    @AiEventId(137)
    protected void REGISTER_RESURRECTION_TOWER_RESULT(@AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(138)
    protected void CHECK_REGISTER_USER_RESULT(@AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(139)
    protected void IS_USER_PVPMATCHING_RESULT(@AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(140)
    protected void REGISTER_USER_PVP_MATCH_RESULT(@AiEventId(name = "talker", value = 40) Creature talker,
                                                  @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(141)
    protected void UNREGISTER_USER_PVP_MATCH_RESULT(@AiEventId(name = "talker", value = 40) Creature talker,
                                                    @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(142)
    protected void REGISTER_USER_RESURRECTION_TOWER_RESULT(@AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(143)
    protected void KILLED_USER_PVP_MATCH_RESULT(@AiEventId(name = "talker", value = 40) Creature talker,
                                                @AiEventId(name = "reply", value = 280) int reply,
                                                @AiEventId(name = "attacker", value = 48) Creature attacker) {
    }

    @AiEventId(144)
    protected void GET_RANK_USER_PVP_MATCH_RESULT(@AiEventId(name = "talker", value = 40) Creature talker,
                                                  @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(145)
    protected void LET_IN_USER_PVP_MATCH(@AiEventId(name = "talker", value = 40) Creature talker) {
    }

    @AiEventId(146)
    protected void START_USER_PVP_MATCH_RESULT(@AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(147)
    protected void WITHDRAW_USER_PVP_MATCH_RESULT(@AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(148)
    protected void END_USER_PVP_MATCH_RESULT(@AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(149)
    protected void GET_RELATED_FORTRESS_LIST_RETURNED(@AiEventId(name = "talker", value = 40) Creature talker) {
    }

    @AiEventId(154)
    protected void DOMINION_SIEGE_START(@AiEventId(name = "talker", value = 40) Creature talker) {
    }

    @AiEventId(155)
    protected void DOMINION_SIEGE_END(@AiEventId(name = "talker", value = 40) Creature talker) {
    }

    @AiEventId(156)
    protected void DOMINION_SIEGE_PC_KILLED(@AiEventId(name = "talker", value = 40) Creature talker,
                                            @AiEventId(name = "attacker", value = 48) Creature attacker) {
    }

    @AiEventId(157)
    protected void CLEFT_STATE_CHANGED() {
    }

    @AiEventId(158)
    protected void BLOCK_UPSET_STARTED() {
    }

    @AiEventId(159)
    protected void BLOCK_UPSET_FINISHED() {
    }

    @AiEventId(160)
    protected void LEVEL_UP(@AiEventId(name = "talker", value = 40) Creature talker,
                            @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(161)
    protected void LOAD_DBSAVING_MAP_RETURNED(@AiEventId(name = "talker", value = 40) Creature talker) {
    }

    @AiEventId(162)
    protected void DOMINION_SCRIPT_EVENT(@AiEventId(name = "talker", value = 40) Creature talker,
                                         @AiEventId(name = "script_event_arg1", value = 248) int script_event_arg1,
                                         @AiEventId(name = "script_event_arg2", value = 252) int script_event_arg2,
                                         @AiEventId(name = "script_event_arg3", value = 256) int script_event_arg3) {
    }

    @AiEventId(163)
    protected void DIE_SET(@AiEventId(name = "talker", value = 40) Creature talker) {
    }

    @AiEventId(165)
    protected void SOCIAL_ACTION_EVENT(@AiEventId(name = "talker", value = 40) Creature talker,
                                       @AiEventId(name = "action_id", value = 264) int action_id) {
    }

    @AiEventId(166)
    protected void PET_DIED() {
    }

    @AiEventId(167)
    protected void WAS_COLLECTED(@AiEventId(name = "attacker", value = 48) Creature attacker) {
    }

    @AiEventId(168)
    protected void ON_AIRSHIP_EVENT(@AiEventId(name = "state", value = 148) int state) {
    }

    @AiEventId(169)
    protected void DOMINION_SUPPLY_DESTRUCTED(@AiEventId(name = "attacker", value = 48) Creature attacker) {
    }

    @AiEventId(170)
    protected void CREATE_NPC_TO_WINNER(@AiEventId(name = "target", value = 96) Creature target) {
    }

    @AiEventId(172)
    protected void DEBUG_AI(@AiEventId(name = "creature", value = 112) Creature creature,
                            @AiEventId(name = "talker", value = 40) Creature talker,
                            @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(173)
    protected void IS_TOGGLE_SKILL_ONOFF(@AiEventId(name = "i0", value = 288) int i0) {
    }

    @AiEventId(174)
    protected void FIELD_CYCLE_STEP_EXPIRED(@AiEventId(name = "event_id", value = 236) int event_id,
                                            @AiEventId(name = "i0", value = 288) int i0,
                                            @AiEventId(name = "i1", value = 296) int i1) {
    }

    @AiEventId(175)
    protected void SPELL_SUCCESSED(@AiEventId(name = "target", value = 96) Creature target,
                                   @AiEventId(name = "skill_name_id", value = 176) int skill_name_id,
                                   @AiEventId(name = "pos0", value = 680) Point pos0,
                                   @AiEventId(name = "maker0", value = 632) NpcMaker maker0,
                                   @AiEventId(name = "i0", value = 288) int i0) {
    }

    @AiEventId(176)
    protected void GET_EVENT_RANKER_INFO(@AiEventId(name = "talker", value = 40) Creature talker,
                                         @AiEventId(name = "i0", value = 288) int i0,
                                         @AiEventId(name = "i1", value = 296) int i1,
                                         @AiEventId(name = "i2", value = 304) int i2) {
    }

    @AiEventId(177)
    protected void OLYMPIAD_MATCH_RESULT_EVENT(@AiEventId(name = "talker", value = 40) Creature talker,
                                               @AiEventId(name = "ask", value = 160) int ask,
                                               @AiEventId(name = "reply", value = 280) int reply,
                                               @AiEventId(name = "i0", value = 288) int i0) {
    }

    @AiEventId(178)
    protected void GET_PLAYING_USER_COUNT(@AiEventId(name = "i0", value = 288) int i0) {
    }

    @AiEventId(179)
    protected void FIND_RANDOM_USER(@AiEventId(name = "talker", value = 40) Creature talker) {
    }

    @AiEventId(180)
    protected void GIVE_EVENT_DATA(@AiEventId(name = "talker", value = 40) Creature talker) {
    }

    @AiEventId(181)
    protected void PET_AI(@AiEventId(name = "talker", value = 40) Creature talker,
                          @AiEventId(name = "state", value = 148) int state) {
    }

    @AiEventId(182)
    protected void PET_REACTION(@AiEventId(name = "talker", value = 40) Creature talker,
                                @AiEventId(name = "state", value = 148) int state) {
    }

    @AiEventId(183)
    protected void USE_SKILL_STARTED(@AiEventId(name = "skill_name_id", value = 176) int skill_name_id) {
    }

    @AiEventId(184)
    protected void INZONE_ALL_USER(@AiEventId(name = "target", value = 96) Creature target,
                                   @AiEventId(name = "c0", value = 424) Creature c0,
                                   @AiEventId(name = "party0", value = 480) Party party0,
                                   @AiEventId(name = "pos0", value = 680) Point pos0,
                                   @AiEventId(name = "i0", value = 288) int i0) {
    }

    @AiEventId(186)
    protected void DOOR_STATUS(@AiEventId(name = "i0", value = 288) int i0,
                               @AiEventId(name = "i1", value = 296) int i1,
                               @AiEventId(name = "s0", value = 584) String s0) {
    }

    @AiEventId(187)
    protected void MASTER_ATTACK(@AiEventId(name = "attacker", value = 48) Creature attacker,
                                 @AiEventId(name = "target", value = 96) Creature target,
                                 @AiEventId(name = "skill_id", value = 164) int skill_id,
                                 @AiEventId(name = "skill_level", value = 168) int skill_level,
                                 @AiEventId(name = "skill_name_id", value = 176) int skill_name_id) {
    }

    @AiEventId(191)
    protected void CALL_TO_CHANGE_CLASS(@AiEventId(name = "talker", value = 40) Creature talker) {
    }

    @AiEventId(192)
    protected void CHANGE_TO_AWAKENED_CLASS(@AiEventId(name = "talker", value = 40) Creature talker,
                                            @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(193)
    protected void MASTER_ATTACKED(@AiEventId(name = "attacker", value = 48) Creature attacker,
                                   @AiEventId(name = "target", value = 96) Creature target,
                                   @AiEventId(name = "damage", value = 144) int damage) {
    }

    @AiEventId(194)
    protected void TOTAL_REWARD_SKILL_ENCHANT_COUNT(@AiEventId(name = "talker", value = 40) Creature talker) {
    }

    @AiEventId(195)
    protected void INSTANT_AGIT_SET_OWNER(@AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(196)
    protected void CHANGE_CLASS(@AiEventId(name = "talker", value = 40) Creature talker,
                                @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(197)
    protected void CAN_CHANGE_TO_SUBCLASS(@AiEventId(name = "talker", value = 40) Creature talker,
                                          @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(198)
    protected void SCENE_STOPPED(@AiEventId(name = "talker", value = 40) Creature talker) {
    }
}