package org.l2junity.gameserver.retail.model.actor;

import org.l2junity.gameserver.retail.EventId;
import org.l2junity.gameserver.retail.ai.MakerEventHandler;
import org.l2junity.gameserver.retail.model.AtomicValue;
import org.l2junity.gameserver.retail.model.SpawnDefine;

/***
 * @author ANZO, Camellion
 * @since 20.03.2017
 */
public class NpcMaker {
    @EventId(1090)
    public String name;

    @EventId(1188)
    public int def_count;

    @EventId(1192)
    public int npc_count;

    @EventId(1196)
    public int maximum_npc;

    @EventId(1200)
    public int i_quest0;

    @EventId(1208)
    public int i_quest1;

    @EventId(1216)
    public int i_quest2;

    @EventId(1224)
    public int i_quest3;

    @EventId(1144)
    public int i_quest4;

    @EventId(1152)
    public int i_quest5;

    @EventId(1160)
    public int i_quest6;

    @EventId(1168)
    public int i_quest7;

    @EventId(1176)
    public int i_quest8;

    @EventId(1272)
    public int i_quest9;

    @EventId(1280)
    public int i_ai0;

    @EventId(1284)
    public int i_ai1;

    @EventId(1288)
    public int i_ai2;

    @EventId(1292)
    public int i_ai3;

    @EventId(1296)
    public int i_ai4;

    @EventId(1300)
    public int i_ai5;

    @EventId(1304)
    public int i_ai6;

    @EventId(1308)
    public int i_ai7;

    @EventId(1312)
    public int i_ai8;

    @EventId(1316)
    public int i_ai9;

    @EventId(1320)
    public Creature c_ai0;

    @EventId(1440)
    public int enabled;

    @EventId(1464)
    public AtomicValue av_ai0;

    private MakerEventHandler eventHandler;

    public MakerEventHandler getEventHandler() {
        /*if (eventHandler == null) {
            eventHandler = new MakerEventHandler(this);
        }*/
        return eventHandler;
    }

    @EventId(218169344)
    public SpawnDefine GetSpawnDefine(int index) {
        return null;
    }

    @EventId(218300426)
    public boolean AtomicIncreaseTotal(SpawnDefine define, int count, int unk) {
        return false;
    }

    @EventId(218234882)
    public void AddTimerEx(int timer_id, int delay) {
    }

    @EventId(218169349)
    public void RegisterSiegeEventEx(int dominion_id) {
    }

    @EventId(218169355)
    public void RegisterFieldCycleEventEx(int field_cycle) {
    }

    @EventId(218300428)
    public void RegisterFortressEventEx(int fortress_id, int event_id, int reply) {
    }

    @EventId(218169347)
    public void RegisterNpcPosEvent(String event_name) {
    }

    @EventId(218169345)
    public SpawnDefine GetSpawnDefineByNick(String nick) {
        return null;
    }

    @EventId(218103816)
    public void DoRespawn() {
    }

    @EventId(218103817)
    public void ResetRespawn() {
    }

    @EventId(218234884)
    public void RegisterDoorEvent(String door_name, int inzone_type_param) {
    }

    @EventId(218365965)
    public void RegisterInstantZoneEventEx(int inzone_type_param, int inzone_cluster_id, int reply, int unk) {
    }

    @EventId(218103824)
    public int GetInZoneID() {
        return 0;
    }

    @EventId(218103822)
    public void RegisterOlympiadFieldEventEx() {
    }

    @EventId(218300423)
    public void RegisterRespawn(int respawn_time, int unk, SpawnDefine spawnDefine) {
    }

    @EventId(218169350)
    public void RegisterAgitSiegeEventEx(int castle_id) {
    }

    @EventId(218103829)
    public void RegisterInstantZoneNotifySubscriber() {
    }

    @EventId(218365975)
    public void NotifyToInstantZoneMaker(int inzone_id, int script_event_arg, int unk0, int unk1) {
    }

    @EventId(218169359)
    public void RegisterOlympiadZoneEventEx(int inzone_type_param) {
    }

    @EventId(218169364)
    public void RegisterPledgeGameZoneEventEx(int inzone_type_param) {
    }

    @EventId(218169362)
    public int Residence_GetCastleType(int castle_id) {
        return 0;
    }
}
