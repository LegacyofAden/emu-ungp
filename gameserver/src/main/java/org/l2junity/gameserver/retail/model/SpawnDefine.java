package org.l2junity.gameserver.retail.model;

import org.l2junity.gameserver.retail.AiEventId;

/***
 * @author ANZO, Camellion
 * @since 20.03.2017
 */
public class SpawnDefine {
    @AiEventId(90)
    public String name;

    @AiEventId(140)
    public int total;

    @AiEventId(144)
    public int npc_count;

    @AiEventId(148)
    public int respawn_time;

    @AiEventId(152)
    public int respawn_rand;

    @AiEventId(208)
    public boolean has_dbname;

    @AiEventId(285409286)
    public void SendScriptEvent(int event_id, int script_event_arg1, int script_event_arg2) {
    }

    @AiEventId(285278213)
    public void LoadDBNpcInfo(int unk) {
    }

    @AiEventId(285278215)
    public void SetDBLoaded(int value) {
    }

    @AiEventId(285409282)
    public void Spawn2(int count, int respawn, int repsawn_rand) {
    }

    @AiEventId(285802497)
    public void SpawnEx(int unk1, int unk2, int x, int y, int z, int angle, double hp, double mp, int db_value) {
    }

    @AiEventId(285212676)
    public void Despawn() {
    }

    @AiEventId(285278216)
    public void RegToRespawnTimer(int respawn_times) {
    }

    @AiEventId(285343744)
    public void Spawn(int total, int unk) {
    }
}