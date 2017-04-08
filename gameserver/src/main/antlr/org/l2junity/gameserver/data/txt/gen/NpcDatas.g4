grammar NpcDatas;

import Lang;

@header {
import org.l2junity.gameserver.data.txt.model.constants.*;
}

file : npc+;

npc :
    'npc_begin'
    npc_type
    npc_id
    npc_name
    category
    level
    exp
    ex_crt_effect
    unique
    s_npc_prop_hp_rate
    race
    sex
    skill_list
    slot_chest
    slot_rhand
    slot_lhand
    collision_radius
    collision_height
    hit_time_factor
    hit_time_factor_skill
    ground_high
    ground_low
    str
    int_
    dex
    wit
    con
    men
    org_hp
    org_hp_regen
    org_mp
    org_mp_regen
    base_attack_type
    base_attack_range
    base_damage_range
    base_rand_dam
    base_physical_attack
    base_critical
    physical_hit_modify
    base_attack_speed
    base_reuse_delay
    base_magic_attack
    base_defend
    base_magic_defend
    base_attribute_attack
    base_attribute_defend
    physical_avoid_modify
    shield_defense_rate
    shield_defense
    safe_height
    soulshot_count
    spiritshot_count
    clan
    ignore_clan_list
    clan_help_range
    undying
    can_be_attacked
    corpse_time
    no_sleep_mode
    agro_range
    passable_door
    can_move
    flying
    has_summoner
    targetable
    show_name_tag
    abnormal_resist
    is_death_penalty
    npc_ai
    event_flag
    unsowing
    private_respawn_log
    acquire_exp_rate
    acquire_sp
    acquire_rp
    corpse_make_list
    additional_make_list
    additional_make_multi_list
    ex_item_drop_list
    vitality_item_drop_list
    mp_reward
    fake_class_id
    event_drop
    ex_drop
    enable_move_after_talk
    broadcast_cond
    'npc_end';

npc_type
    returns[NpcType value]
    : WARRIOR {$ctx.value = NpcType.WARRIOR;}
    | TREASURE {$ctx.value = NpcType.TREASURE;}
    | CITIZEN {$ctx.value = NpcType.CITIZEN;}
    | ZZOLDAGU {$ctx.value = NpcType.ZZOLDAGU;}
    | BOSS {$ctx.value = NpcType.BOSS;}
    | FIELDBOSS {$ctx.value = NpcType.FIELDBOSS;}
    | WORLD_TRAP {$ctx.value = NpcType.WORLD_TRAP;}
    | MERCHANT {$ctx.value = NpcType.MERCHANT;}
    | HOLYTHING {$ctx.value = NpcType.HOLYTHING;}
    | WAREHOUSE_KEEPER {$ctx.value = NpcType.WAREHOUSE_KEEPER;}
    | BLACKSMITH {$ctx.value = NpcType.BLACKSMITH;}
    | TELEPORTER {$ctx.value = NpcType.TELEPORTER;}
    | GUILD_MASTER {$ctx.value = NpcType.GUILD_MASTER;}
    | GUILD_COACH {$ctx.value = NpcType.GUILD_COACH;}
    | GUARD {$ctx.value = NpcType.GUARD;}
    | MONRACE {$ctx.value = NpcType.MONRACE;}
    | PC_TRAP {$ctx.value = NpcType.PC_TRAP;}
    | XMASTREE {$ctx.value = NpcType.XMASTREE;}
    | PET {$ctx.value = NpcType.PET;}
    | SUMMON {$ctx.value = NpcType.SUMMON;}
    | DOPPELGANGER {$ctx.value = NpcType.DOPPELGANGER;}
    | OWNTHING {$ctx.value = NpcType.OWNTHING;}
    | SIEGE_ATTACKER {$ctx.value = NpcType.SIEGE_ATTACKER;}
    | MRKEEPER {$ctx.value = NpcType.MRKEEPER;}
    | COLLECTION {$ctx.value = NpcType.COLLECTION;}
    | PACKAGE_KEEPER {$ctx.value = NpcType.PACKAGE_KEEPER;};

npc_id
    returns[int value]: io=int_object {$ctx.value = $io.value;};

npc_name
    returns[String value]: no=name_object {$ctx.value = $no.value;};

category: 'category' '=' '{' '}';

level : 'level' '=' io=int_object;

exp : 'exp' '=' lo=long_object;

ex_crt_effect : 'ex_crt_effect' '=' (bo=bool_object | '{' bo=bool_object '}');

unique : 'unique' '=' bo=bool_object;

s_npc_prop_hp_rate: 's_npc_prop_hp_rate' '=' d=double_object;

race returns[RaceType value]:
    'race' '=' (
    FAIRY {$ctx.value = RaceType.FAIRY;}
    | ANIMAL {$ctx.value = RaceType.ANIMAL;}
    | HUMANOID {$ctx.value = RaceType.HUMANOID;}
    | PLANT {$ctx.value = RaceType.PLANT;}
    | UNDEAD {$ctx.value = RaceType.UNDEAD;}
    | CONSTRUCT {$ctx.value = RaceType.CONSTRUCT;}
    | BEAST {$ctx.value = RaceType.BEAST;}
    | BUG {$ctx.value = RaceType.BUG;}
    | ELEMENTAL {$ctx.value = RaceType.ELEMENTAL;}
    | DEMONIC {$ctx.value = RaceType.DEMONIC;}
    | DRAGON {$ctx.value = RaceType.DRAGON;}
    | GIANT {$ctx.value = RaceType.GIANT;}
    | DIVINE {$ctx.value = RaceType.DIVINE;}
    | ETC {$ctx.value = RaceType.ETC;}
    | SUMMON {$ctx.value = RaceType.SUMMON;}
    | PET {$ctx.value = RaceType.PET;}
    | HOLYTHING {$ctx.value = RaceType.HOLYTHING;}
    | DWARF {$ctx.value = RaceType.DWARF;}
    | MERCHANT {$ctx.value = RaceType.MERCHANT;}
    | ELF {$ctx.value = RaceType.ELF;}
    | KAMAEL {$ctx.value = RaceType.KAMAEL;}
    | ORC {$ctx.value = RaceType.ORC;}
    | MERCENARY {$ctx.value = RaceType.MERCENARY;}
    | CASTLE_GUARD {$ctx.value = RaceType.CASTLE_GUARD;}
    | HUMAN {$ctx.value = RaceType.HUMAN;}
    | BOSS {$ctx.value = RaceType.BOSS;}
    | ZZOLDAGU {$ctx.value = RaceType.ZZOLDAGU;}
    | WORLD_TRAP {$ctx.value = RaceType.WORLD_TRAP;}
    | MONRACE {$ctx.value = RaceType.MONRACE;}
    | GUARD {$ctx.value = RaceType.GUARD;}
    | TELEPORTER {$ctx.value = RaceType.TELEPORTER;}
    | WAREHOUSE_KEEPER {$ctx.value = RaceType.WAREHOUSE_KEEPER;}
    | DARKELF {$ctx.value = RaceType.DARKELF;}
    | SIEGE_WEAPON {$ctx.value = RaceType.SIEGE_WEAPON;}
    | FRIEND {$ctx.value = RaceType.FRIEND;}
    );

sex returns[Sex value]:
 	'sex' '=' (
 	MALE {$ctx.value = Sex.MALE;}
 	| FEMALE {$ctx.value = Sex.FEMALE;}
  	| ETC {$ctx.value = Sex.ETC;}
  	);

skill_list: 'skill_list' '=' category_list;

slot_chest: 'slot_chest' '=' (empty_name_object | name_object);
slot_rhand: 'slot_rhand' '=' (empty_name_object | name_object);
slot_lhand: 'slot_lhand' '=' (empty_name_object | name_object);

empty_name_object: '[' ']';

collision_radius: 'collision_radius' '=' double_list;
collision_height: 'collision_height' '=' double_list;

hit_time_factor: 'hit_time_factor' '=' double_object;
hit_time_factor_skill: 'hit_time_factor_skill' '=' int_object;

ground_high: 'ground_high' '=' double_list;
ground_low: 'ground_low' '=' double_list;

str: 'str' '=' int_object;
int_: 'int' '=' int_object;
dex: 'dex' '=' int_object;
wit: 'wit' '=' int_object;
con: 'con' '=' int_object;
men: 'men' '=' int_object;

org_hp: 'org_hp' '=' double_object;
org_hp_regen: 'org_hp_regen' '=' double_object;
org_mp: 'org_mp' '=' double_object;
org_mp_regen: 'org_mp_regen' '=' double_object;

base_attack_type returns[WeaponType value]:
	'base_attack_type' '=' (
	SWORD {$ctx.value = WeaponType.SWORD;}
	| BLUNT {$ctx.value = WeaponType.BLUNT;}
	| BOW {$ctx.value = WeaponType.BOW;}
	| DAGGER {$ctx.value = WeaponType.DAGGER;}
	| FIST {$ctx.value = WeaponType.FIST;}
	| POLE {$ctx.value = WeaponType.POLE;}
	| DUAL {$ctx.value = WeaponType.DUAL;}
	| DUALFIST {$ctx.value = WeaponType.DUALFIST;}
	);

base_attack_range: 'base_attack_range' '=' io=int_object;

base_damage_range: 'base_damage_range' '=' il=int_list;
base_rand_dam: 'base_rand_dam' '=' io=int_object;

base_physical_attack: 'base_physical_attack' '=' d=double_object;
base_critical: 'base_critical' '=' d=double_object;

physical_hit_modify: 'physical_hit_modify' '=' d=double_object;

base_attack_speed: 'base_attack_speed' '=' doBas=double_object;

base_reuse_delay: 'base_reuse_delay' '=' io=int_object;

base_magic_attack: 'base_magic_attack' '=' d=double_object;

base_defend: 'base_defend' '=' d=double_object;
base_magic_defend: 'base_magic_defend' '=' d=double_object;

base_attribute_defend
    returns[List<Double> value]: 'base_attribute_defend' '=' il=double_list {$ctx.value = $il.value;};

physical_avoid_modify: 'physical_avoid_modify' '=' io=int_object;
shield_defense_rate: 'shield_defense_rate' '=' io=int_object;
shield_defense: 'shield_defense' '=' d=double_object;
safe_height: 'safe_height' '=' io=int_object;

soulshot_count: 'soulshot_count' '=' io=int_object;
spiritshot_count: 'spiritshot_count' '=' io=int_object;

clan: 'clan' '=' ('{' int_object '}' | category_list);
ignore_clan_list: 'ignore_clan_list' '=' category_list;

clan_help_range: 'clan_help_range' '=' io=int_object;
undying: 'undying' '=' bo=bool_object;
can_be_attacked: 'can_be_attacked' '=' bo=bool_object;
corpse_time: 'corpse_time' '=' io=int_object;
no_sleep_mode: 'no_sleep_mode' '=' bo=bool_object;

agro_range: 'agro_range' '=' io=int_object;
passable_door: 'passable_door' '=' io=int_object;

can_move: 'can_move' '=' bo=bool_object;
flying: 'flying' '=' bo=bool_object;
has_summoner: 'has_summoner' '=' bo=bool_object;
targetable: 'targetable' '=' bo=bool_object;
show_name_tag: 'show_name_tag' '=' bo=bool_object;
abnormal_resist: 'abnormal_resist' '=' int_list;
is_death_penalty: 'is_death_penalty' '=' bo=bool_object;

npc_ai: 'npc_ai' '=' '{' no=name_object ((';' | ';' ai_param)*) '}';
ai_param: '{' no1=name_object '=' param_value '}';
param_value: int_object | double_object | category_object | npc_privates | fstring_object | identifier_object;

npc_privates: '[' identifier_object (';' identifier_object)* ']';
fstring_object: '[' io=int_object ']';

event_flag: 'event_flag' '=' '{' int_object '}';

unsowing: 'unsowing' '=' bo=bool_object;
private_respawn_log: 'private_respawn_log' '=' bo=bool_object;

acquire_exp_rate: 'acquire_exp_rate' '=' d=double_object;
acquire_sp: 'acquire_sp' '=' d=double_object;
acquire_rp: 'acquire_rp' '=' d=double_object;

corpse_make_list: 'corpse_make_list' '=' make_item_list;

make_item_list: '{''}' | '{' make_item (';' make_item)* '}';

make_item: '{' name_object ';' min=int_object ';' max=int_object ';' chance=double_object '}';

additional_make_list: 'additional_make_list' '=' make_item_list;

additional_make_multi_list: 'additional_make_multi_list' '=' make_group_list;

make_group_list: '{' '}' | '{''{' make_item_list ';' gc=double_object '}' (';' '{' make_item_list ';' gc=double_object '}')* '}';

ex_item_drop_list: 'ex_item_drop_list' '=' make_group_list;

vitality_item_drop_list: 'vitality_item_drop_list' '=' make_group_list;

mp_reward: 'mp_reward' '=' '{' rewardValue=double_object ';' mp_reward_type ';'
							   rewardAffectValue=double_object ';' mp_reward_affect_type '}';

mp_reward_type returns[MpRewardType value]: (
	'per' {$ctx.value = MpRewardType.PER;}
	| 'diff' {$ctx.value = MpRewardType.DIFF;}
	);

mp_reward_affect_type returns[MpRewardAffectType value]: (
	'solo' {$ctx.value = MpRewardAffectType.SOLO;}
	| 'party' {$ctx.value = MpRewardAffectType.PARTY;}
	);

fake_class_id: 'fake_class_id' '=' io=int_object;

event_drop: 'event_drop' '=' int_object;

ex_drop: 'ex_drop' '=' int_list;

enable_move_after_talk: 'enable_move_after_talk' '=' bool_object;

broadcast_cond: 'broadcast_cond' '=' int_list;

// sex
MALE : 'male';
FEMALE : 'female';
ETC : 'etc';