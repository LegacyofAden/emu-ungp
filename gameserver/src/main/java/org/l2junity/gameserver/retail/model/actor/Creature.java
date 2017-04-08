package org.l2junity.gameserver.retail.model.actor;

import org.l2junity.gameserver.retail.AiEventId;

/***
 * @author ANZO, Camellion
 * @since 20.03.2017
 */
public class Creature {
    @AiEventId(8)
    // Used
    public int x;

    @AiEventId(16)
    // Used
    public int y;

    @AiEventId(24)
    // Used
    public int z;

    @AiEventId(36)
    // Used
    public int id;

    @AiEventId(68)
    // Used
    public boolean is_pc;

    @AiEventId(72)
    // Used
    public boolean alive;

    @AiEventId(76)
    public int age;

    @AiEventId(88)
    public int subjob_id;

    @AiEventId(96)
    public int subjob1_level;

    @AiEventId(100)
    public int subjob2_level;

    @AiEventId(104)
    public int subjob3_level;

    @AiEventId(108)
    public int nobless_type;

    @AiEventId(112)
    public int hero_type;

    @AiEventId(120)
    public int subjob0_class;

    @AiEventId(124)
    public int subjob1_class;

    @AiEventId(128)
    public int subjob2_class;

    @AiEventId(132)
    public int subjob3_class;

    @AiEventId(136)
    // Used
    public int dual_class;

    @AiEventId(144)
    // Used
    public int race;

    @AiEventId(148)
    public int occupation;

    @AiEventId(200)
    // Used
    public int sp;

    @AiEventId(360)
    // Used
    public double hp;

    @AiEventId(384)
    // Used
    public String name;

    @AiEventId(368)
    // Used
    public double mp;

    @AiEventId(444)
    public int stop_mode;

    @AiEventId(580)
    public int target_index;

    @AiEventId(584)
    // Used
    public int target_id;

    @AiEventId(596)
    // Used
    public int pk_count;

    @AiEventId(604)
    // Used
    public int karma;

    @AiEventId(10584)
    public int quest_last_reward_time;

    @AiEventId(10596)
    // Used
    public int pledge_id;

    @AiEventId(10604)
    // Used
    public int is_pledge_master;

    @AiEventId(10664)
    public int residence_id;

    @Deprecated
    @AiEventId(6528)
    public int death_penalty_level;

    @AiEventId(10824)
    public int dbid;

    @AiEventId(10972)
    // Used
    public int level;

    @AiEventId(11040)
    // Used
    public double max_hp;

    @AiEventId(11048)
    // Used
    public double max_mp;

    @AiEventId(11088)
    public double hp_regen;

    @AiEventId(11120)
    public int equiped_weapon_class_id;

    @AiEventId(11124)
    public int attack_type;

    @AiEventId(11360)
    // Used
    public int builder_level;

    @AiEventId(11376)
    public int last_blow_weapon_class_id;

    @AiEventId(11464)
    // Used
    public boolean in_peacezone;

    @AiEventId(11468)
    public boolean in_battlefield;

    @AiEventId(11552)
    public String ai;

    @AiEventId(11796)
    public int summon_type;

    @AiEventId(11804)
    public int summoner_id;

    @AiEventId(11808)
    public int boss_id;

    @AiEventId(11812)
    public int npc_class_id;

    @AiEventId(11816)
    public int weight_point;

    @AiEventId(11820)
    public int respawn_time;

    @AiEventId(11864)
    public int p_state;

    @AiEventId(11880)
    public Creature master;

    @AiEventId(11888)
    public int action;

    @AiEventId(11936)
    public int flag;

    @AiEventId(11940)
    public int yongma_type;

    // на страйдере, виверне, ездовом питомце, итд
    @AiEventId(11948)
    public int strider_level;

    @AiEventId(11960)
    public int pet_dbid;

    @AiEventId(11968)
    public int max_magic_level;

    @AiEventId(11976)
    // Used
    public int transformID;

    @AiEventId(11980)
    // Used (maybe scrambled with instant_zone_type_id)
    public int instant_zone_id;

    @AiEventId(11984)
    public int instant_zone_type_id;

    @AiEventId(11988)
    public int instant_zone_type_id_in_use;

    @AiEventId(12112)
    public int straight_attacker;

    @AiEventId(12113)
    public int turn_to_followee;

    @AiEventId(12116)
    public int summon_ai;

    @AiEventId(13284)
    public int db_value;

    @AiEventId(13288)
    public int param1;

    @AiEventId(13292)
    public int param2;

    @AiEventId(13296)
    public int param3;

    @AiEventId(13323)
    public int absolute_defence;

    @AiEventId(13324)
    public int no_attack_damage;
}