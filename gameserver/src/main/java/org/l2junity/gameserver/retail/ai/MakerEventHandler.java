package org.l2junity.gameserver.retail.ai;

import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.retail.EventId;
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

    @EventId(736)
    protected NpcMaker myself = null;

    @EventId(712)
    protected GlobalObject gg = null;

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
    @EventId(384)
    protected int i12;
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

    @EventId(584)
    protected String s0;
    @EventId(592)
    protected String s1;

    @EventId(632)
    protected NpcMaker maker0;
    @EventId(640)
    protected NpcMaker maker1;
    @EventId(648)
    protected NpcMaker maker2;
    @EventId(656)
    protected NpcMaker maker3;

    @EventId(744)
    protected SpawnDefine def0;

    @EventId(0)
    protected void ON_START() {
    }

    @EventId(1)
    protected void ON_NPC_DELETED(@EventId(name = "reply", value = 280) int reply,
                                  @EventId(name = "deleted_def", value = 752) SpawnDefine deleted_def,
                                  @EventId(name = "deleted_npc", value = 776) Npc deleted_npc,
                                  @EventId(name = "died", value = 804) int died,
                                  @EventId(name = "c0", value = 424) Creature c0,
                                  @EventId(name = "s0", value = 584) String s0) {
    }

    @EventId(2)
    protected void ON_ALL_NPC_DELETED(@EventId(name = "def0", value = 744) SpawnDefine def0,
                                      @EventId(name = "deleted_def", value = 752) SpawnDefine deleted_def) {
    }

    @EventId(3)
    protected void ON_SCRIPT_EVENT(@EventId(name = "script_event_arg1", value = 248) int script_event_arg1,
                                   @EventId(name = "script_event_arg2", value = 252) int script_event_arg2,
                                   @EventId(name = "script_event_arg3", value = 256) int script_event_arg3) {
    }

    @EventId(4)
    protected void ON_DB_NPC_INFO(@EventId(name = "loaded_def", value = 760) SpawnDefine loaded_def,
                                  @EventId(name = "record0", value = 792) RecordInfo record0) {
    }

    @EventId(5)
    protected void ON_TIMER(@EventId(name = "timer_id", value = 244) int timer_id,
                            @EventId(name = "script_event_arg1", value = 248) int script_event_arg1,
                            @EventId(name = "script_event_arg2", value = 252) int script_event_arg2,
                            @EventId(name = "script_event_arg3", value = 256) int script_event_arg3) {
    }

    @EventId(6)
    protected void ON_NPC_CREATED(@EventId(name = "created_def", value = 768) SpawnDefine created_def,
                                  @EventId(name = "created_npc", value = 784) Npc created_npc,
                                  @EventId(name = "record0", value = 792) RecordInfo record0) {
    }

    @EventId(7)
    protected void ON_NPCPOS_EVENT(@EventId(name = "enabled", value = 800) int enabled) {
    }

    @EventId(8)
    protected void ON_DOOR_EVENT(@EventId(name = "enabled", value = 800) int enabled) {
    }

    @EventId(9)
    protected void ON_START_SIEGE_EVENT() {
    }

    @EventId(10)
    protected void ON_END_SIEGE_EVENT() {
    }

    @EventId(11)
    protected void ON_PROCLAIM_SIEGE_EVENT() {
    }

    @EventId(12)
    protected void ON_DESTRUCT_CTRL_TOWER_EVENT() {
    }

    @EventId(13)
    protected void ON_CANCEL_SIEGE_EVENT() {
    }

    @EventId(14)
    protected void ON_START_TEAMBATTLEAGIT_FINAL_EVENT() {
    }

    @EventId(15)
    protected void ON_FIELD_CYCLE_CHANGED_EVENT(@EventId(name = "event_id", value = 236) int event_id,
                                                @EventId(name = "state", value = 148) int state) {
    }

    @EventId(16)
    protected void ON_FORTRESS_EVENT(@EventId(name = "residence_id", value = 224) int residence_id,
                                     @EventId(name = "event_id", value = 236) int event_id,
                                     @EventId(name = "reply", value = 280) int reply) {
    }

    @EventId(17)
    protected void ON_INSTANT_ZONE_EVENT(@EventId(name = "reply", value = 280) int reply,
                                         @EventId(name = "event_id", value = 236) int event_id,
                                         @EventId(name = "action_id", value = 264) int action_id,
                                         @EventId(name = "script_event_arg1", value = 248) int script_event_arg1,
                                         @EventId(name = "script_event_arg2", value = 252) int script_event_arg2,
                                         @EventId(name = "script_event_arg3", value = 256) int script_event_arg3) {
    }

    @EventId(18)
    protected void ON_OLYMPIAD_FIELD_STEP_CHANGED_EVENT(@EventId(name = "inzone_id", value = 228) int inzone_id,
                                                        @EventId(name = "event_id", value = 236) int event_id,
                                                        @EventId(name = "state", value = 148) int state) {
    }

    @EventId(21)
    protected void ON_DECLARE_DOMINION_EVENT() {
    }

    @EventId(22)
    protected void ON_START_CHANGE_TIME_EVENT() {
    }

    @EventId(23)
    protected void ON_END_CHANGE_TIME_EVENT() {
    }

    @EventId(24)
    protected void ON_DEBUG_MAKER_EVENT(@EventId(name = "reply", value = 280) int reply,
                                        @EventId(name = "creature", value = 112) Creature creature) {
    }

    @EventId(25)
    protected void ON_DYNAMIC_QUEST_EVENT(@EventId(name = "reply", value = 280) int reply,
                                          @EventId(name = "success", value = 200) int success) {
    }

    @EventId(26)
    protected void ON_DYNAMIC_QUEST_INFO(@EventId(name = "quest_id", value = 156) int quest_id,
                                         @EventId(name = "event_id", value = 236) int event_id) {
    }

    @EventId(29)
    protected void ON_EVENT_CAMPAIGN_EVENT(@EventId(name = "reply", value = 280) int reply,
                                           @EventId(name = "success", value = 200) int success) {
    }
}
