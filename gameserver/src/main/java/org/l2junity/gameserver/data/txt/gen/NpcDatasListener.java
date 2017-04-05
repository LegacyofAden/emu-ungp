// Generated from org\l2junity\gameserver\data\txt\gen\NpcDatas.g4 by ANTLR 4.7
package org.l2junity.gameserver.data.txt.gen;

import org.l2junity.gameserver.data.txt.model.constants.NpcType;


import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import org.l2junity.gameserver.data.txt.model.constants.AttributeType;
import org.l2junity.gameserver.data.txt.model.item.AttributeAttack;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link NpcDatasParser}.
 */
public interface NpcDatasListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(NpcDatasParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(NpcDatasParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#npc}.
	 * @param ctx the parse tree
	 */
	void enterNpc(NpcDatasParser.NpcContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#npc}.
	 * @param ctx the parse tree
	 */
	void exitNpc(NpcDatasParser.NpcContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#npc_type}.
	 * @param ctx the parse tree
	 */
	void enterNpc_type(NpcDatasParser.Npc_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#npc_type}.
	 * @param ctx the parse tree
	 */
	void exitNpc_type(NpcDatasParser.Npc_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#npc_id}.
	 * @param ctx the parse tree
	 */
	void enterNpc_id(NpcDatasParser.Npc_idContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#npc_id}.
	 * @param ctx the parse tree
	 */
	void exitNpc_id(NpcDatasParser.Npc_idContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#npc_name}.
	 * @param ctx the parse tree
	 */
	void enterNpc_name(NpcDatasParser.Npc_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#npc_name}.
	 * @param ctx the parse tree
	 */
	void exitNpc_name(NpcDatasParser.Npc_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#category}.
	 * @param ctx the parse tree
	 */
	void enterCategory(NpcDatasParser.CategoryContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#category}.
	 * @param ctx the parse tree
	 */
	void exitCategory(NpcDatasParser.CategoryContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#level}.
	 * @param ctx the parse tree
	 */
	void enterLevel(NpcDatasParser.LevelContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#level}.
	 * @param ctx the parse tree
	 */
	void exitLevel(NpcDatasParser.LevelContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(NpcDatasParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(NpcDatasParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#ex_crt_effect}.
	 * @param ctx the parse tree
	 */
	void enterEx_crt_effect(NpcDatasParser.Ex_crt_effectContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#ex_crt_effect}.
	 * @param ctx the parse tree
	 */
	void exitEx_crt_effect(NpcDatasParser.Ex_crt_effectContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#unique}.
	 * @param ctx the parse tree
	 */
	void enterUnique(NpcDatasParser.UniqueContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#unique}.
	 * @param ctx the parse tree
	 */
	void exitUnique(NpcDatasParser.UniqueContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#s_npc_prop_hp_rate}.
	 * @param ctx the parse tree
	 */
	void enterS_npc_prop_hp_rate(NpcDatasParser.S_npc_prop_hp_rateContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#s_npc_prop_hp_rate}.
	 * @param ctx the parse tree
	 */
	void exitS_npc_prop_hp_rate(NpcDatasParser.S_npc_prop_hp_rateContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#race}.
	 * @param ctx the parse tree
	 */
	void enterRace(NpcDatasParser.RaceContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#race}.
	 * @param ctx the parse tree
	 */
	void exitRace(NpcDatasParser.RaceContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#sex}.
	 * @param ctx the parse tree
	 */
	void enterSex(NpcDatasParser.SexContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#sex}.
	 * @param ctx the parse tree
	 */
	void exitSex(NpcDatasParser.SexContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#skill_list}.
	 * @param ctx the parse tree
	 */
	void enterSkill_list(NpcDatasParser.Skill_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#skill_list}.
	 * @param ctx the parse tree
	 */
	void exitSkill_list(NpcDatasParser.Skill_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#slot_chest}.
	 * @param ctx the parse tree
	 */
	void enterSlot_chest(NpcDatasParser.Slot_chestContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#slot_chest}.
	 * @param ctx the parse tree
	 */
	void exitSlot_chest(NpcDatasParser.Slot_chestContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#slot_rhand}.
	 * @param ctx the parse tree
	 */
	void enterSlot_rhand(NpcDatasParser.Slot_rhandContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#slot_rhand}.
	 * @param ctx the parse tree
	 */
	void exitSlot_rhand(NpcDatasParser.Slot_rhandContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#slot_lhand}.
	 * @param ctx the parse tree
	 */
	void enterSlot_lhand(NpcDatasParser.Slot_lhandContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#slot_lhand}.
	 * @param ctx the parse tree
	 */
	void exitSlot_lhand(NpcDatasParser.Slot_lhandContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#empty_name_object}.
	 * @param ctx the parse tree
	 */
	void enterEmpty_name_object(NpcDatasParser.Empty_name_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#empty_name_object}.
	 * @param ctx the parse tree
	 */
	void exitEmpty_name_object(NpcDatasParser.Empty_name_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#collision_radius}.
	 * @param ctx the parse tree
	 */
	void enterCollision_radius(NpcDatasParser.Collision_radiusContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#collision_radius}.
	 * @param ctx the parse tree
	 */
	void exitCollision_radius(NpcDatasParser.Collision_radiusContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#collision_height}.
	 * @param ctx the parse tree
	 */
	void enterCollision_height(NpcDatasParser.Collision_heightContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#collision_height}.
	 * @param ctx the parse tree
	 */
	void exitCollision_height(NpcDatasParser.Collision_heightContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#hit_time_factor}.
	 * @param ctx the parse tree
	 */
	void enterHit_time_factor(NpcDatasParser.Hit_time_factorContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#hit_time_factor}.
	 * @param ctx the parse tree
	 */
	void exitHit_time_factor(NpcDatasParser.Hit_time_factorContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#hit_time_factor_skill}.
	 * @param ctx the parse tree
	 */
	void enterHit_time_factor_skill(NpcDatasParser.Hit_time_factor_skillContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#hit_time_factor_skill}.
	 * @param ctx the parse tree
	 */
	void exitHit_time_factor_skill(NpcDatasParser.Hit_time_factor_skillContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#ground_high}.
	 * @param ctx the parse tree
	 */
	void enterGround_high(NpcDatasParser.Ground_highContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#ground_high}.
	 * @param ctx the parse tree
	 */
	void exitGround_high(NpcDatasParser.Ground_highContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#ground_low}.
	 * @param ctx the parse tree
	 */
	void enterGround_low(NpcDatasParser.Ground_lowContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#ground_low}.
	 * @param ctx the parse tree
	 */
	void exitGround_low(NpcDatasParser.Ground_lowContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#str}.
	 * @param ctx the parse tree
	 */
	void enterStr(NpcDatasParser.StrContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#str}.
	 * @param ctx the parse tree
	 */
	void exitStr(NpcDatasParser.StrContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#int_}.
	 * @param ctx the parse tree
	 */
	void enterInt_(NpcDatasParser.Int_Context ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#int_}.
	 * @param ctx the parse tree
	 */
	void exitInt_(NpcDatasParser.Int_Context ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#dex}.
	 * @param ctx the parse tree
	 */
	void enterDex(NpcDatasParser.DexContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#dex}.
	 * @param ctx the parse tree
	 */
	void exitDex(NpcDatasParser.DexContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#wit}.
	 * @param ctx the parse tree
	 */
	void enterWit(NpcDatasParser.WitContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#wit}.
	 * @param ctx the parse tree
	 */
	void exitWit(NpcDatasParser.WitContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#con}.
	 * @param ctx the parse tree
	 */
	void enterCon(NpcDatasParser.ConContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#con}.
	 * @param ctx the parse tree
	 */
	void exitCon(NpcDatasParser.ConContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#men}.
	 * @param ctx the parse tree
	 */
	void enterMen(NpcDatasParser.MenContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#men}.
	 * @param ctx the parse tree
	 */
	void exitMen(NpcDatasParser.MenContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#org_hp}.
	 * @param ctx the parse tree
	 */
	void enterOrg_hp(NpcDatasParser.Org_hpContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#org_hp}.
	 * @param ctx the parse tree
	 */
	void exitOrg_hp(NpcDatasParser.Org_hpContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#org_hp_regen}.
	 * @param ctx the parse tree
	 */
	void enterOrg_hp_regen(NpcDatasParser.Org_hp_regenContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#org_hp_regen}.
	 * @param ctx the parse tree
	 */
	void exitOrg_hp_regen(NpcDatasParser.Org_hp_regenContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#org_mp}.
	 * @param ctx the parse tree
	 */
	void enterOrg_mp(NpcDatasParser.Org_mpContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#org_mp}.
	 * @param ctx the parse tree
	 */
	void exitOrg_mp(NpcDatasParser.Org_mpContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#org_mp_regen}.
	 * @param ctx the parse tree
	 */
	void enterOrg_mp_regen(NpcDatasParser.Org_mp_regenContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#org_mp_regen}.
	 * @param ctx the parse tree
	 */
	void exitOrg_mp_regen(NpcDatasParser.Org_mp_regenContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#base_attack_type}.
	 * @param ctx the parse tree
	 */
	void enterBase_attack_type(NpcDatasParser.Base_attack_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#base_attack_type}.
	 * @param ctx the parse tree
	 */
	void exitBase_attack_type(NpcDatasParser.Base_attack_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#attack_type}.
	 * @param ctx the parse tree
	 */
	void enterAttack_type(NpcDatasParser.Attack_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#attack_type}.
	 * @param ctx the parse tree
	 */
	void exitAttack_type(NpcDatasParser.Attack_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#base_attack_range}.
	 * @param ctx the parse tree
	 */
	void enterBase_attack_range(NpcDatasParser.Base_attack_rangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#base_attack_range}.
	 * @param ctx the parse tree
	 */
	void exitBase_attack_range(NpcDatasParser.Base_attack_rangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#base_damage_range}.
	 * @param ctx the parse tree
	 */
	void enterBase_damage_range(NpcDatasParser.Base_damage_rangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#base_damage_range}.
	 * @param ctx the parse tree
	 */
	void exitBase_damage_range(NpcDatasParser.Base_damage_rangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#base_rand_dam}.
	 * @param ctx the parse tree
	 */
	void enterBase_rand_dam(NpcDatasParser.Base_rand_damContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#base_rand_dam}.
	 * @param ctx the parse tree
	 */
	void exitBase_rand_dam(NpcDatasParser.Base_rand_damContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#base_physical_attack}.
	 * @param ctx the parse tree
	 */
	void enterBase_physical_attack(NpcDatasParser.Base_physical_attackContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#base_physical_attack}.
	 * @param ctx the parse tree
	 */
	void exitBase_physical_attack(NpcDatasParser.Base_physical_attackContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#base_critical}.
	 * @param ctx the parse tree
	 */
	void enterBase_critical(NpcDatasParser.Base_criticalContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#base_critical}.
	 * @param ctx the parse tree
	 */
	void exitBase_critical(NpcDatasParser.Base_criticalContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#physical_hit_modify}.
	 * @param ctx the parse tree
	 */
	void enterPhysical_hit_modify(NpcDatasParser.Physical_hit_modifyContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#physical_hit_modify}.
	 * @param ctx the parse tree
	 */
	void exitPhysical_hit_modify(NpcDatasParser.Physical_hit_modifyContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#base_attack_speed}.
	 * @param ctx the parse tree
	 */
	void enterBase_attack_speed(NpcDatasParser.Base_attack_speedContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#base_attack_speed}.
	 * @param ctx the parse tree
	 */
	void exitBase_attack_speed(NpcDatasParser.Base_attack_speedContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#base_reuse_delay}.
	 * @param ctx the parse tree
	 */
	void enterBase_reuse_delay(NpcDatasParser.Base_reuse_delayContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#base_reuse_delay}.
	 * @param ctx the parse tree
	 */
	void exitBase_reuse_delay(NpcDatasParser.Base_reuse_delayContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#base_magic_attack}.
	 * @param ctx the parse tree
	 */
	void enterBase_magic_attack(NpcDatasParser.Base_magic_attackContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#base_magic_attack}.
	 * @param ctx the parse tree
	 */
	void exitBase_magic_attack(NpcDatasParser.Base_magic_attackContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#base_defend}.
	 * @param ctx the parse tree
	 */
	void enterBase_defend(NpcDatasParser.Base_defendContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#base_defend}.
	 * @param ctx the parse tree
	 */
	void exitBase_defend(NpcDatasParser.Base_defendContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#base_magic_defend}.
	 * @param ctx the parse tree
	 */
	void enterBase_magic_defend(NpcDatasParser.Base_magic_defendContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#base_magic_defend}.
	 * @param ctx the parse tree
	 */
	void exitBase_magic_defend(NpcDatasParser.Base_magic_defendContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#base_attribute_defend}.
	 * @param ctx the parse tree
	 */
	void enterBase_attribute_defend(NpcDatasParser.Base_attribute_defendContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#base_attribute_defend}.
	 * @param ctx the parse tree
	 */
	void exitBase_attribute_defend(NpcDatasParser.Base_attribute_defendContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#physical_avoid_modify}.
	 * @param ctx the parse tree
	 */
	void enterPhysical_avoid_modify(NpcDatasParser.Physical_avoid_modifyContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#physical_avoid_modify}.
	 * @param ctx the parse tree
	 */
	void exitPhysical_avoid_modify(NpcDatasParser.Physical_avoid_modifyContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#shield_defense_rate}.
	 * @param ctx the parse tree
	 */
	void enterShield_defense_rate(NpcDatasParser.Shield_defense_rateContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#shield_defense_rate}.
	 * @param ctx the parse tree
	 */
	void exitShield_defense_rate(NpcDatasParser.Shield_defense_rateContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#shield_defense}.
	 * @param ctx the parse tree
	 */
	void enterShield_defense(NpcDatasParser.Shield_defenseContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#shield_defense}.
	 * @param ctx the parse tree
	 */
	void exitShield_defense(NpcDatasParser.Shield_defenseContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#safe_height}.
	 * @param ctx the parse tree
	 */
	void enterSafe_height(NpcDatasParser.Safe_heightContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#safe_height}.
	 * @param ctx the parse tree
	 */
	void exitSafe_height(NpcDatasParser.Safe_heightContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#soulshot_count}.
	 * @param ctx the parse tree
	 */
	void enterSoulshot_count(NpcDatasParser.Soulshot_countContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#soulshot_count}.
	 * @param ctx the parse tree
	 */
	void exitSoulshot_count(NpcDatasParser.Soulshot_countContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#spiritshot_count}.
	 * @param ctx the parse tree
	 */
	void enterSpiritshot_count(NpcDatasParser.Spiritshot_countContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#spiritshot_count}.
	 * @param ctx the parse tree
	 */
	void exitSpiritshot_count(NpcDatasParser.Spiritshot_countContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#clan}.
	 * @param ctx the parse tree
	 */
	void enterClan(NpcDatasParser.ClanContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#clan}.
	 * @param ctx the parse tree
	 */
	void exitClan(NpcDatasParser.ClanContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#ignore_clan_list}.
	 * @param ctx the parse tree
	 */
	void enterIgnore_clan_list(NpcDatasParser.Ignore_clan_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#ignore_clan_list}.
	 * @param ctx the parse tree
	 */
	void exitIgnore_clan_list(NpcDatasParser.Ignore_clan_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#clan_help_range}.
	 * @param ctx the parse tree
	 */
	void enterClan_help_range(NpcDatasParser.Clan_help_rangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#clan_help_range}.
	 * @param ctx the parse tree
	 */
	void exitClan_help_range(NpcDatasParser.Clan_help_rangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#undying}.
	 * @param ctx the parse tree
	 */
	void enterUndying(NpcDatasParser.UndyingContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#undying}.
	 * @param ctx the parse tree
	 */
	void exitUndying(NpcDatasParser.UndyingContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#can_be_attacked}.
	 * @param ctx the parse tree
	 */
	void enterCan_be_attacked(NpcDatasParser.Can_be_attackedContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#can_be_attacked}.
	 * @param ctx the parse tree
	 */
	void exitCan_be_attacked(NpcDatasParser.Can_be_attackedContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#corpse_time}.
	 * @param ctx the parse tree
	 */
	void enterCorpse_time(NpcDatasParser.Corpse_timeContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#corpse_time}.
	 * @param ctx the parse tree
	 */
	void exitCorpse_time(NpcDatasParser.Corpse_timeContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#no_sleep_mode}.
	 * @param ctx the parse tree
	 */
	void enterNo_sleep_mode(NpcDatasParser.No_sleep_modeContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#no_sleep_mode}.
	 * @param ctx the parse tree
	 */
	void exitNo_sleep_mode(NpcDatasParser.No_sleep_modeContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#agro_range}.
	 * @param ctx the parse tree
	 */
	void enterAgro_range(NpcDatasParser.Agro_rangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#agro_range}.
	 * @param ctx the parse tree
	 */
	void exitAgro_range(NpcDatasParser.Agro_rangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#passable_door}.
	 * @param ctx the parse tree
	 */
	void enterPassable_door(NpcDatasParser.Passable_doorContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#passable_door}.
	 * @param ctx the parse tree
	 */
	void exitPassable_door(NpcDatasParser.Passable_doorContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#can_move}.
	 * @param ctx the parse tree
	 */
	void enterCan_move(NpcDatasParser.Can_moveContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#can_move}.
	 * @param ctx the parse tree
	 */
	void exitCan_move(NpcDatasParser.Can_moveContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#flying}.
	 * @param ctx the parse tree
	 */
	void enterFlying(NpcDatasParser.FlyingContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#flying}.
	 * @param ctx the parse tree
	 */
	void exitFlying(NpcDatasParser.FlyingContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#has_summoner}.
	 * @param ctx the parse tree
	 */
	void enterHas_summoner(NpcDatasParser.Has_summonerContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#has_summoner}.
	 * @param ctx the parse tree
	 */
	void exitHas_summoner(NpcDatasParser.Has_summonerContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#targetable}.
	 * @param ctx the parse tree
	 */
	void enterTargetable(NpcDatasParser.TargetableContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#targetable}.
	 * @param ctx the parse tree
	 */
	void exitTargetable(NpcDatasParser.TargetableContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#show_name_tag}.
	 * @param ctx the parse tree
	 */
	void enterShow_name_tag(NpcDatasParser.Show_name_tagContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#show_name_tag}.
	 * @param ctx the parse tree
	 */
	void exitShow_name_tag(NpcDatasParser.Show_name_tagContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#abnormal_resist}.
	 * @param ctx the parse tree
	 */
	void enterAbnormal_resist(NpcDatasParser.Abnormal_resistContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#abnormal_resist}.
	 * @param ctx the parse tree
	 */
	void exitAbnormal_resist(NpcDatasParser.Abnormal_resistContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#is_death_penalty}.
	 * @param ctx the parse tree
	 */
	void enterIs_death_penalty(NpcDatasParser.Is_death_penaltyContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#is_death_penalty}.
	 * @param ctx the parse tree
	 */
	void exitIs_death_penalty(NpcDatasParser.Is_death_penaltyContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#npc_ai}.
	 * @param ctx the parse tree
	 */
	void enterNpc_ai(NpcDatasParser.Npc_aiContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#npc_ai}.
	 * @param ctx the parse tree
	 */
	void exitNpc_ai(NpcDatasParser.Npc_aiContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#ai_param}.
	 * @param ctx the parse tree
	 */
	void enterAi_param(NpcDatasParser.Ai_paramContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#ai_param}.
	 * @param ctx the parse tree
	 */
	void exitAi_param(NpcDatasParser.Ai_paramContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#param_value}.
	 * @param ctx the parse tree
	 */
	void enterParam_value(NpcDatasParser.Param_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#param_value}.
	 * @param ctx the parse tree
	 */
	void exitParam_value(NpcDatasParser.Param_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#npc_privates}.
	 * @param ctx the parse tree
	 */
	void enterNpc_privates(NpcDatasParser.Npc_privatesContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#npc_privates}.
	 * @param ctx the parse tree
	 */
	void exitNpc_privates(NpcDatasParser.Npc_privatesContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#fstring_object}.
	 * @param ctx the parse tree
	 */
	void enterFstring_object(NpcDatasParser.Fstring_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#fstring_object}.
	 * @param ctx the parse tree
	 */
	void exitFstring_object(NpcDatasParser.Fstring_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#event_flag}.
	 * @param ctx the parse tree
	 */
	void enterEvent_flag(NpcDatasParser.Event_flagContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#event_flag}.
	 * @param ctx the parse tree
	 */
	void exitEvent_flag(NpcDatasParser.Event_flagContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#unsowing}.
	 * @param ctx the parse tree
	 */
	void enterUnsowing(NpcDatasParser.UnsowingContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#unsowing}.
	 * @param ctx the parse tree
	 */
	void exitUnsowing(NpcDatasParser.UnsowingContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#private_respawn_log}.
	 * @param ctx the parse tree
	 */
	void enterPrivate_respawn_log(NpcDatasParser.Private_respawn_logContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#private_respawn_log}.
	 * @param ctx the parse tree
	 */
	void exitPrivate_respawn_log(NpcDatasParser.Private_respawn_logContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#acquire_exp_rate}.
	 * @param ctx the parse tree
	 */
	void enterAcquire_exp_rate(NpcDatasParser.Acquire_exp_rateContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#acquire_exp_rate}.
	 * @param ctx the parse tree
	 */
	void exitAcquire_exp_rate(NpcDatasParser.Acquire_exp_rateContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#acquire_sp}.
	 * @param ctx the parse tree
	 */
	void enterAcquire_sp(NpcDatasParser.Acquire_spContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#acquire_sp}.
	 * @param ctx the parse tree
	 */
	void exitAcquire_sp(NpcDatasParser.Acquire_spContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#acquire_rp}.
	 * @param ctx the parse tree
	 */
	void enterAcquire_rp(NpcDatasParser.Acquire_rpContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#acquire_rp}.
	 * @param ctx the parse tree
	 */
	void exitAcquire_rp(NpcDatasParser.Acquire_rpContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#corpse_make_list}.
	 * @param ctx the parse tree
	 */
	void enterCorpse_make_list(NpcDatasParser.Corpse_make_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#corpse_make_list}.
	 * @param ctx the parse tree
	 */
	void exitCorpse_make_list(NpcDatasParser.Corpse_make_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#make_item_list}.
	 * @param ctx the parse tree
	 */
	void enterMake_item_list(NpcDatasParser.Make_item_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#make_item_list}.
	 * @param ctx the parse tree
	 */
	void exitMake_item_list(NpcDatasParser.Make_item_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#make_item}.
	 * @param ctx the parse tree
	 */
	void enterMake_item(NpcDatasParser.Make_itemContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#make_item}.
	 * @param ctx the parse tree
	 */
	void exitMake_item(NpcDatasParser.Make_itemContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#additional_make_list}.
	 * @param ctx the parse tree
	 */
	void enterAdditional_make_list(NpcDatasParser.Additional_make_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#additional_make_list}.
	 * @param ctx the parse tree
	 */
	void exitAdditional_make_list(NpcDatasParser.Additional_make_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#additional_make_multi_list}.
	 * @param ctx the parse tree
	 */
	void enterAdditional_make_multi_list(NpcDatasParser.Additional_make_multi_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#additional_make_multi_list}.
	 * @param ctx the parse tree
	 */
	void exitAdditional_make_multi_list(NpcDatasParser.Additional_make_multi_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#make_group_list}.
	 * @param ctx the parse tree
	 */
	void enterMake_group_list(NpcDatasParser.Make_group_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#make_group_list}.
	 * @param ctx the parse tree
	 */
	void exitMake_group_list(NpcDatasParser.Make_group_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#ex_item_drop_list}.
	 * @param ctx the parse tree
	 */
	void enterEx_item_drop_list(NpcDatasParser.Ex_item_drop_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#ex_item_drop_list}.
	 * @param ctx the parse tree
	 */
	void exitEx_item_drop_list(NpcDatasParser.Ex_item_drop_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#vitality_item_drop_list}.
	 * @param ctx the parse tree
	 */
	void enterVitality_item_drop_list(NpcDatasParser.Vitality_item_drop_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#vitality_item_drop_list}.
	 * @param ctx the parse tree
	 */
	void exitVitality_item_drop_list(NpcDatasParser.Vitality_item_drop_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#mp_reward}.
	 * @param ctx the parse tree
	 */
	void enterMp_reward(NpcDatasParser.Mp_rewardContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#mp_reward}.
	 * @param ctx the parse tree
	 */
	void exitMp_reward(NpcDatasParser.Mp_rewardContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#fake_class_id}.
	 * @param ctx the parse tree
	 */
	void enterFake_class_id(NpcDatasParser.Fake_class_idContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#fake_class_id}.
	 * @param ctx the parse tree
	 */
	void exitFake_class_id(NpcDatasParser.Fake_class_idContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#event_drop}.
	 * @param ctx the parse tree
	 */
	void enterEvent_drop(NpcDatasParser.Event_dropContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#event_drop}.
	 * @param ctx the parse tree
	 */
	void exitEvent_drop(NpcDatasParser.Event_dropContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#ex_drop}.
	 * @param ctx the parse tree
	 */
	void enterEx_drop(NpcDatasParser.Ex_dropContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#ex_drop}.
	 * @param ctx the parse tree
	 */
	void exitEx_drop(NpcDatasParser.Ex_dropContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#enable_move_after_talk}.
	 * @param ctx the parse tree
	 */
	void enterEnable_move_after_talk(NpcDatasParser.Enable_move_after_talkContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#enable_move_after_talk}.
	 * @param ctx the parse tree
	 */
	void exitEnable_move_after_talk(NpcDatasParser.Enable_move_after_talkContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#broadcast_cond}.
	 * @param ctx the parse tree
	 */
	void enterBroadcast_cond(NpcDatasParser.Broadcast_condContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#broadcast_cond}.
	 * @param ctx the parse tree
	 */
	void exitBroadcast_cond(NpcDatasParser.Broadcast_condContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#identifier_object}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier_object(NpcDatasParser.Identifier_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#identifier_object}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier_object(NpcDatasParser.Identifier_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#bool_object}.
	 * @param ctx the parse tree
	 */
	void enterBool_object(NpcDatasParser.Bool_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#bool_object}.
	 * @param ctx the parse tree
	 */
	void exitBool_object(NpcDatasParser.Bool_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#byte_object}.
	 * @param ctx the parse tree
	 */
	void enterByte_object(NpcDatasParser.Byte_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#byte_object}.
	 * @param ctx the parse tree
	 */
	void exitByte_object(NpcDatasParser.Byte_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#int_object}.
	 * @param ctx the parse tree
	 */
	void enterInt_object(NpcDatasParser.Int_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#int_object}.
	 * @param ctx the parse tree
	 */
	void exitInt_object(NpcDatasParser.Int_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#long_object}.
	 * @param ctx the parse tree
	 */
	void enterLong_object(NpcDatasParser.Long_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#long_object}.
	 * @param ctx the parse tree
	 */
	void exitLong_object(NpcDatasParser.Long_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#double_object}.
	 * @param ctx the parse tree
	 */
	void enterDouble_object(NpcDatasParser.Double_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#double_object}.
	 * @param ctx the parse tree
	 */
	void exitDouble_object(NpcDatasParser.Double_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#string_object}.
	 * @param ctx the parse tree
	 */
	void enterString_object(NpcDatasParser.String_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#string_object}.
	 * @param ctx the parse tree
	 */
	void exitString_object(NpcDatasParser.String_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#name_object}.
	 * @param ctx the parse tree
	 */
	void enterName_object(NpcDatasParser.Name_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#name_object}.
	 * @param ctx the parse tree
	 */
	void exitName_object(NpcDatasParser.Name_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#category_object}.
	 * @param ctx the parse tree
	 */
	void enterCategory_object(NpcDatasParser.Category_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#category_object}.
	 * @param ctx the parse tree
	 */
	void exitCategory_object(NpcDatasParser.Category_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#vector3D_object}.
	 * @param ctx the parse tree
	 */
	void enterVector3D_object(NpcDatasParser.Vector3D_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#vector3D_object}.
	 * @param ctx the parse tree
	 */
	void exitVector3D_object(NpcDatasParser.Vector3D_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#empty_list}.
	 * @param ctx the parse tree
	 */
	void enterEmpty_list(NpcDatasParser.Empty_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#empty_list}.
	 * @param ctx the parse tree
	 */
	void exitEmpty_list(NpcDatasParser.Empty_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#identifier_list}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier_list(NpcDatasParser.Identifier_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#identifier_list}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier_list(NpcDatasParser.Identifier_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#int_list}.
	 * @param ctx the parse tree
	 */
	void enterInt_list(NpcDatasParser.Int_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#int_list}.
	 * @param ctx the parse tree
	 */
	void exitInt_list(NpcDatasParser.Int_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#double_list}.
	 * @param ctx the parse tree
	 */
	void enterDouble_list(NpcDatasParser.Double_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#double_list}.
	 * @param ctx the parse tree
	 */
	void exitDouble_list(NpcDatasParser.Double_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#base_attribute_attack}.
	 * @param ctx the parse tree
	 */
	void enterBase_attribute_attack(NpcDatasParser.Base_attribute_attackContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#base_attribute_attack}.
	 * @param ctx the parse tree
	 */
	void exitBase_attribute_attack(NpcDatasParser.Base_attribute_attackContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#attack_attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttack_attribute(NpcDatasParser.Attack_attributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#attack_attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttack_attribute(NpcDatasParser.Attack_attributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(NpcDatasParser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(NpcDatasParser.AttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link NpcDatasParser#category_list}.
	 * @param ctx the parse tree
	 */
	void enterCategory_list(NpcDatasParser.Category_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link NpcDatasParser#category_list}.
	 * @param ctx the parse tree
	 */
	void exitCategory_list(NpcDatasParser.Category_listContext ctx);
}