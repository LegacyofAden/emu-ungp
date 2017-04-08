package org.l2junity.gameserver.retail.ai;

import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.retail.AiEventId;
import org.l2junity.gameserver.retail.model.GlobalObject;
import org.l2junity.gameserver.retail.model.RecordInfo;
import org.l2junity.gameserver.retail.model.SpawnDefine;
import org.l2junity.gameserver.retail.model.actor.NpcMaker;

/**
 * @author ANZO, Camellion
 * @since 20.03.2017
 */
public class MakerEventHandler {
    /*public MakerEventHandler(NpcMaker myself) {
        this.myself = myself;
    }*/

    @AiEventId(736)
    protected NpcMaker myself = null;

    @AiEventId(712)
    protected GlobalObject gg = null;

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
    @AiEventId(384)
    protected int i12;
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

    @AiEventId(584)
    protected String s0;
    @AiEventId(592)
    protected String s1;

    @AiEventId(632)
    protected NpcMaker maker0;
    @AiEventId(640)
    protected NpcMaker maker1;
    @AiEventId(648)
    protected NpcMaker maker2;
    @AiEventId(656)
    protected NpcMaker maker3;

    @AiEventId(744)
    protected SpawnDefine def0;

    @AiEventId(0)
    protected void ON_START() {
    }

    @AiEventId(1)
    protected void ON_NPC_DELETED(@AiEventId(name = "reply", value = 280) int reply,
                                  @AiEventId(name = "deleted_def", value = 752) SpawnDefine deleted_def,
                                  @AiEventId(name = "deleted_npc", value = 776) Npc deleted_npc,
                                  @AiEventId(name = "died", value = 804) int died,
                                  @AiEventId(name = "c0", value = 424) Creature c0,
                                  @AiEventId(name = "s0", value = 584) String s0) {
    }

    @AiEventId(2)
    protected void ON_ALL_NPC_DELETED(@AiEventId(name = "def0", value = 744) SpawnDefine def0,
                                      @AiEventId(name = "deleted_def", value = 752) SpawnDefine deleted_def) {
    }

    @AiEventId(3)
    protected void ON_SCRIPT_EVENT(@AiEventId(name = "script_event_arg1", value = 248) int script_event_arg1,
                                   @AiEventId(name = "script_event_arg2", value = 252) int script_event_arg2,
                                   @AiEventId(name = "script_event_arg3", value = 256) int script_event_arg3) {
    }

    @AiEventId(4)
    protected void ON_DB_NPC_INFO(@AiEventId(name = "loaded_def", value = 760) SpawnDefine loaded_def,
                                  @AiEventId(name = "record0", value = 792) RecordInfo record0) {
    }

    @AiEventId(5)
    protected void ON_TIMER(@AiEventId(name = "timer_id", value = 244) int timer_id,
                            @AiEventId(name = "script_event_arg1", value = 248) int script_event_arg1,
                            @AiEventId(name = "script_event_arg2", value = 252) int script_event_arg2,
                            @AiEventId(name = "script_event_arg3", value = 256) int script_event_arg3) {
    }

    @AiEventId(6)
    protected void ON_NPC_CREATED(@AiEventId(name = "created_def", value = 768) SpawnDefine created_def,
                                  @AiEventId(name = "created_npc", value = 784) Npc created_npc,
                                  @AiEventId(name = "record0", value = 792) RecordInfo record0) {
    }

    @AiEventId(7)
    protected void ON_NPCPOS_EVENT(@AiEventId(name = "enabled", value = 800) int enabled) {
    }

    @AiEventId(8)
    protected void ON_DOOR_EVENT(@AiEventId(name = "enabled", value = 800) int enabled) {
    }

    @AiEventId(9)
    protected void ON_START_SIEGE_EVENT() {
    }

    @AiEventId(10)
    protected void ON_END_SIEGE_EVENT() {
    }

    @AiEventId(11)
    protected void ON_PROCLAIM_SIEGE_EVENT() {
    }

    @AiEventId(12)
    protected void ON_DESTRUCT_CTRL_TOWER_EVENT() {
    }

    @AiEventId(13)
    protected void ON_CANCEL_SIEGE_EVENT() {
    }

    @AiEventId(14)
    protected void ON_START_TEAMBATTLEAGIT_FINAL_EVENT() {
    }

    @AiEventId(15)
    protected void ON_FIELD_CYCLE_CHANGED_EVENT(@AiEventId(name = "event_id", value = 236) int event_id,
                                                @AiEventId(name = "state", value = 148) int state) {
    }

    @AiEventId(16)
    protected void ON_FORTRESS_EVENT(@AiEventId(name = "residence_id", value = 224) int residence_id,
                                     @AiEventId(name = "event_id", value = 236) int event_id,
                                     @AiEventId(name = "reply", value = 280) int reply) {
    }

    @AiEventId(17)
    protected void ON_INSTANT_ZONE_EVENT(@AiEventId(name = "reply", value = 280) int reply,
                                         @AiEventId(name = "event_id", value = 236) int event_id,
                                         @AiEventId(name = "action_id", value = 264) int action_id,
                                         @AiEventId(name = "script_event_arg1", value = 248) int script_event_arg1,
                                         @AiEventId(name = "script_event_arg2", value = 252) int script_event_arg2,
                                         @AiEventId(name = "script_event_arg3", value = 256) int script_event_arg3) {
    }

    @AiEventId(18)
    protected void ON_OLYMPIAD_FIELD_STEP_CHANGED_EVENT(@AiEventId(name = "inzone_id", value = 228) int inzone_id,
                                                        @AiEventId(name = "event_id", value = 236) int event_id,
                                                        @AiEventId(name = "state", value = 148) int state) {
    }

    @AiEventId(21)
    protected void ON_DECLARE_DOMINION_EVENT() {
    }

    @AiEventId(22)
    protected void ON_START_CHANGE_TIME_EVENT() {
    }

    @AiEventId(23)
    protected void ON_END_CHANGE_TIME_EVENT() {
    }

    @AiEventId(24)
    protected void ON_DEBUG_MAKER_EVENT(@AiEventId(name = "reply", value = 280) int reply,
                                        @AiEventId(name = "creature", value = 112) Creature creature) {
    }

    @AiEventId(25)
    protected void ON_DYNAMIC_QUEST_EVENT(@AiEventId(name = "reply", value = 280) int reply,
                                          @AiEventId(name = "success", value = 200) int success) {
    }

    @AiEventId(26)
    protected void ON_DYNAMIC_QUEST_INFO(@AiEventId(name = "quest_id", value = 156) int quest_id,
                                         @AiEventId(name = "event_id", value = 236) int event_id) {
    }

    @AiEventId(29)
    protected void ON_EVENT_CAMPAIGN_EVENT(@AiEventId(name = "reply", value = 280) int reply,
                                           @AiEventId(name = "success", value = 200) int success) {
    }
}
