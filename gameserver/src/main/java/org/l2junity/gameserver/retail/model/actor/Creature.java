package org.l2junity.gameserver.retail.model.actor;

import org.l2junity.gameserver.retail.EventId;

/***
 * @author ANZO, Camellion
 * @since 20.03.2017
 */
public class Creature {
    @EventId(8)
    public int x;

    @EventId(16)
    public int y;

    @EventId(24)
    public int z;

    @EventId(36)
    public int id;

    @EventId(68)
    public boolean is_pc;

    @EventId(72)
    public boolean alive;

    @EventId(76)
    public int age;

    @EventId(88)
    public int subjob_id;

    @EventId(96)
    public int subjob1_level;

    @EventId(100)
    public int subjob2_level;

    @EventId(104)
    public int subjob3_level;

    @EventId(108)
    public int nobless_type;

    @EventId(112)
    public int hero_type;

    @EventId(120)
    public int subjob0_class;

    @EventId(124)
    public int subjob1_class;

    @EventId(128)
    public int subjob2_class;

    @EventId(132)
    public int subjob3_class;

    @EventId(136)
    public int dual_class;

    @EventId(144)
    public int race;

    @EventId(148)
    public int occupation;

    @EventId(200)
    public int sp;

    @EventId(360)
    public double hp;

    @EventId(384)
    public String name;

    @EventId(368)
    public double mp;

    @EventId(444)
    public int stop_mode;

    @EventId(580)
    public int target_index;

    @EventId(584)
    public int target_id;

    @EventId(596)
    public int pk_count;

    @EventId(604)
    public int karma;

    @EventId(10584)
    public int quest_last_reward_time;

    @EventId(10596)
    public int pledge_id;

    @EventId(10604)
    public int is_pledge_master;

    @EventId(10664)
    public int residence_id;

    @Deprecated
    @EventId(6528)
    public int death_penalty_level;

    @EventId(10824)
    public int dbid;

    @EventId(10972)
    public int level;

    @EventId(11040)
    public double max_hp;

    @EventId(11048)
    public double max_mp;

    @EventId(11088)
    public double hp_regen;

    @EventId(11120)
    public int equiped_weapon_class_id;

    @EventId(11124)
    public int attack_type;

    @EventId(11360)
    public int builder_level;

    @EventId(11376)
    public int last_blow_weapon_class_id;

    @EventId(11464)
    public boolean in_peacezone;

    @EventId(11468)
    public boolean in_battlefield;

    @EventId(11552)
    public String ai;

    @EventId(11796)
    public int summon_type;

    @EventId(11804)
    public int summoner_id;

    @EventId(11808)
    public int boss_id;

    @EventId(11812)
    public int npc_class_id;

    @EventId(11816)
    public int weight_point;

    @EventId(11820)
    public int respawn_time;

    @EventId(11864)
    public int p_state;

    @EventId(11880)
    public Creature master;

    @EventId(11888)
    public int action;

    @EventId(11936)
    public int flag;

    @EventId(11940)
    public int yongma_type;

    // на страйдере, виверне, ездовом питомце, итд
    @EventId(11948)
    public int strider_level;

    @EventId(11960)
    public int pet_dbid;

    @EventId(11968)
    public int max_magic_level;

    @EventId(11976)
    public int transformID;

    @EventId(11980)
    public int instant_zone_id;

    @EventId(11984)
    public int instant_zone_type_id;

    @EventId(11988)
    public int instant_zone_type_id_in_use;

    @EventId(12112)
    public int straight_attacker;

    @EventId(12113)
    public int turn_to_followee;

    @EventId(12116)
    public int summon_ai;

    @EventId(13284)
    public int db_value;

    @EventId(13288)
    public int param1;

    @EventId(13292)
    public int param2;

    @EventId(13296)
    public int param3;

    @EventId(13323)
    public int absolute_defence;

    @EventId(13324)
    public int no_attack_damage;
}