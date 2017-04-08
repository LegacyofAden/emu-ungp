package org.l2junity.gameserver.retail.model.actor;

import org.l2junity.gameserver.retail.AiEventId;
import org.l2junity.gameserver.retail.ai.MakerEventHandler;
import org.l2junity.gameserver.retail.model.AtomicValue;
import org.l2junity.gameserver.retail.model.SpawnDefine;

/***
 * @author ANZO, Camellion
 * @since 20.03.2017
 */
public class NpcMaker {
    @AiEventId(1090)
    public String name;

    @AiEventId(1188)
    public int def_count;

    @AiEventId(1192)
    public int npc_count;

    @AiEventId(1196)
    public int maximum_npc;

    @AiEventId(1200)
    public int i_quest0;

    @AiEventId(1208)
    public int i_quest1;

    @AiEventId(1216)
    public int i_quest2;

    @AiEventId(1224)
    public int i_quest3;

    @AiEventId(1144)
    public int i_quest4;

    @AiEventId(1152)
    public int i_quest5;

    @AiEventId(1160)
    public int i_quest6;

    @AiEventId(1168)
    public int i_quest7;

    @AiEventId(1176)
    public int i_quest8;

    @AiEventId(1272)
    public int i_quest9;

    @AiEventId(1280)
    public int i_ai0;

    @AiEventId(1284)
    public int i_ai1;

    @AiEventId(1288)
    public int i_ai2;

    @AiEventId(1292)
    public int i_ai3;

    @AiEventId(1296)
    public int i_ai4;

    @AiEventId(1300)
    public int i_ai5;

    @AiEventId(1304)
    public int i_ai6;

    @AiEventId(1308)
    public int i_ai7;

    @AiEventId(1312)
    public int i_ai8;

    @AiEventId(1316)
    public int i_ai9;

    @AiEventId(1320)
    public Creature c_ai0;

    @AiEventId(1440)
    public int enabled;

    @AiEventId(1464)
    public AtomicValue av_ai0;

    private MakerEventHandler eventHandler;

    public MakerEventHandler getEventHandler() {
        /*if (eventHandler == null) {
            eventHandler = new MakerEventHandler(this);
        }*/
        return eventHandler;
    }

    @AiEventId(218169344)
    public SpawnDefine GetSpawnDefine(int index) {
        return null;
    }

    @AiEventId(218300426)
    public boolean AtomicIncreaseTotal(SpawnDefine define, int count, int unk) {
        return false;
    }

    @AiEventId(218234882)
    public void AddTimerEx(int timer_id, int delay) {
    }

    @AiEventId(218169349)
    public void RegisterSiegeEventEx(int dominion_id) {
    }

    @AiEventId(218169355)
    public void RegisterFieldCycleEventEx(int field_cycle) {
    }

    @AiEventId(218300428)
    public void RegisterFortressEventEx(int fortress_id, int event_id, int reply) {
    }

    @AiEventId(218169347)
    public void RegisterNpcPosEvent(String event_name) {
    }

    @AiEventId(218169345)
    public SpawnDefine GetSpawnDefineByNick(String nick) {
        return null;
    }

    @AiEventId(218103816)
    public void DoRespawn() {
    }

    @AiEventId(218103817)
    public void ResetRespawn() {
    }

    @AiEventId(218234884)
    public void RegisterDoorEvent(String door_name, int inzone_type_param) {
    }

    @AiEventId(218365965)
    public void RegisterInstantZoneEventEx(int inzone_type_param, int inzone_cluster_id, int reply, int unk) {
    }

    @AiEventId(218103824)
    public int GetInZoneID() {
        return 0;
    }

    @AiEventId(218103822)
    public void RegisterOlympiadFieldEventEx() {
    }

    @AiEventId(218300423)
    public void RegisterRespawn(int respawn_time, int unk, SpawnDefine spawnDefine) {
    }

    @AiEventId(218169350)
    public void RegisterAgitSiegeEventEx(int castle_id) {
    }

    @AiEventId(218103829)
    public void RegisterInstantZoneNotifySubscriber() {
    }

    @AiEventId(218365975)
    public void NotifyToInstantZoneMaker(int inzone_id, int script_event_arg, int unk0, int unk1) {
    }

    @AiEventId(218169359)
    public void RegisterOlympiadZoneEventEx(int inzone_type_param) {
    }

    @AiEventId(218169364)
    public void RegisterPledgeGameZoneEventEx(int inzone_type_param) {
    }

    @AiEventId(218169362)
    public int Residence_GetCastleType(int castle_id) {
        return 0;
    }
}
