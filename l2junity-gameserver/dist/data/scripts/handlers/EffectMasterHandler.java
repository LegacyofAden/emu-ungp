/*
 * Copyright (C) 2004-2015 L2J Unity
 *
 * This file is part of L2J Unity.
 *
 * L2J Unity is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * L2J Unity is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package handlers;

import org.l2junity.gameserver.handler.EffectHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import handlers.effecthandlers.Confuse;
import handlers.effecthandlers.CounterPhysicalSkill;
import handlers.effecthandlers.Fear;
import handlers.effecthandlers.FlyAway;
import handlers.effecthandlers.ModifyVital;
import handlers.effecthandlers.OpenChest;
import handlers.effecthandlers.OpenDoor;
import handlers.effecthandlers.RechargeVitalPoint;
import handlers.effecthandlers.ResurrectionSpecial;
import handlers.effecthandlers.SetHp;
import handlers.effecthandlers.SilentMove;
import handlers.effecthandlers.StopConsumeVitalPoint;
import handlers.effecthandlers.SummonAgathion;
import handlers.effecthandlers.consume.ConsumeChameleonRest;
import handlers.effecthandlers.consume.ConsumeFakeDeath;
import handlers.effecthandlers.consume.ConsumeHp;
import handlers.effecthandlers.consume.ConsumeMp;
import handlers.effecthandlers.consume.ConsumeMpByLevel;
import handlers.effecthandlers.consume.ConsumeRest;
import handlers.effecthandlers.instant.*;
import handlers.effecthandlers.pump.*;
import handlers.effecthandlers.tick.TickCp;
import handlers.effecthandlers.tick.TickGetEnergy;
import handlers.effecthandlers.tick.TickHp;
import handlers.effecthandlers.tick.TickHpFatal;
import handlers.effecthandlers.tick.TickHpMagic;
import handlers.effecthandlers.tick.TickHpToOwner;
import handlers.effecthandlers.tick.TickMp;
import handlers.effecthandlers.tick.TickSynergySkill;

/**
 * Effect Master handler.
 * @author NosBit
 */
public final class EffectMasterHandler
{
	private static final Logger LOGGER = LoggerFactory.getLogger(EffectMasterHandler.class);
	
	public static void main(String[] args)
	{
		EffectHandler.getInstance().registerHandler("c_chameleon_rest", ConsumeChameleonRest::new);
		EffectHandler.getInstance().registerHandler("c_fake_death", ConsumeFakeDeath::new);
		EffectHandler.getInstance().registerHandler("c_hp", ConsumeHp::new);
		EffectHandler.getInstance().registerHandler("c_mp", ConsumeMp::new);
		EffectHandler.getInstance().registerHandler("c_mp_by_level", ConsumeMpByLevel::new);
		EffectHandler.getInstance().registerHandler("c_rest", ConsumeRest::new);
		
		EffectHandler.getInstance().registerHandler("i_abnormal_time_change", InstantAbnormalTimeChange::new);
		EffectHandler.getInstance().registerHandler("i_add_hate", InstantAddHate::new);
		EffectHandler.getInstance().registerHandler("i_add_hate_mymaster", InstantAddHateMyMaster::new);
		EffectHandler.getInstance().registerHandler("i_align_direction", InstantAlignDirection::new);
		EffectHandler.getInstance().registerHandler("i_backstab", InstantBackstab::new);
		EffectHandler.getInstance().registerHandler("i_blink", InstantBlink::new);
		EffectHandler.getInstance().registerHandler("i_bonuscount_up", InstantBonusCountUp::new);
		EffectHandler.getInstance().registerHandler("i_bookmark_add_slot", InstantBookmarkAddSlot::new);
		EffectHandler.getInstance().registerHandler("i_call_party", InstantCallParty::new);
		EffectHandler.getInstance().registerHandler("i_call_pc", InstantCallPc::new);
		EffectHandler.getInstance().registerHandler("i_call_skill", InstantCallSkill::new);
		EffectHandler.getInstance().registerHandler("i_call_target_party", InstantCallTargetParty::new);
		EffectHandler.getInstance().registerHandler("i_capture_flag", InstantCaptureFlag::new);
		EffectHandler.getInstance().registerHandler("i_capture_flag_start", InstantCaptureFlagStart::new);
		EffectHandler.getInstance().registerHandler("i_change_face", InstantChangeFace::new);
		EffectHandler.getInstance().registerHandler("i_change_hair_color", InstantChangeHairColor::new);
		EffectHandler.getInstance().registerHandler("i_change_hair_style", InstantChangeHairStyle::new);
		EffectHandler.getInstance().registerHandler("i_change_skill_level", InstantChangeSkillLevel::new);
		EffectHandler.getInstance().registerHandler("i_class_change", InstantChangeClass::new);
		EffectHandler.getInstance().registerHandler("i_come_to_me", InstantComeToMe::new);
		EffectHandler.getInstance().registerHandler("i_consume_body", InstantConsumeBody::new);
		EffectHandler.getInstance().registerHandler("i_convert_item", InstantConvertItem::new);
		EffectHandler.getInstance().registerHandler("i_cp", InstantCp::new);
		EffectHandler.getInstance().registerHandler("i_cp_per_max", InstantCpPerMax::new);
		EffectHandler.getInstance().registerHandler("i_create_item_random", InstantCreateItemRandom::new);
		EffectHandler.getInstance().registerHandler("i_death_link", InstantDeathLink::new);
		EffectHandler.getInstance().registerHandler("i_defuse_trap", InstantDefuseTrap::new);
		EffectHandler.getInstance().registerHandler("i_delete_hate", InstantDeleteHate::new);
		EffectHandler.getInstance().registerHandler("i_delete_hate_of_me", InstantDeleteHateOfMe::new);
		EffectHandler.getInstance().registerHandler("i_delete_topaggro", InstantDeleteTopAgro::new);
		EffectHandler.getInstance().registerHandler("i_despawn", InstantDespawn::new);
		EffectHandler.getInstance().registerHandler("i_detect_object", InstantDetectObject::new);
		EffectHandler.getInstance().registerHandler("i_detect_trap", InstantDetectTrap::new);
		EffectHandler.getInstance().registerHandler("i_dismount_for_event", InstantDismountForEvent::new);
		EffectHandler.getInstance().registerHandler("i_dispel_all", InstantDispelAll::new);
		EffectHandler.getInstance().registerHandler("i_dispel_by_category", InstantDispelByCategory::new);
		EffectHandler.getInstance().registerHandler("i_dispel_by_name", InstantDispelByName::new);
		EffectHandler.getInstance().registerHandler("i_dispel_by_slot", InstantDispelBySlot::new);
		EffectHandler.getInstance().registerHandler("i_dispel_by_slot_myself", InstantDispelBySlotMyself::new);
		EffectHandler.getInstance().registerHandler("i_dispel_by_slot_probability", InstantDispelBySlotProbability::new);
		EffectHandler.getInstance().registerHandler("i_enchant_armor", InstantEnchantArmor::new);
		EffectHandler.getInstance().registerHandler("i_enchant_armor_rate", InstantEnchantArmorRate::new);
		EffectHandler.getInstance().registerHandler("i_enchant_attribute", InstantEnchantAttribute::new);
		EffectHandler.getInstance().registerHandler("i_enchant_item_multi", InstantEnchantItemMulti::new);
		EffectHandler.getInstance().registerHandler("i_enchant_weapon", InstantEnchantWeapon::new);
		EffectHandler.getInstance().registerHandler("i_enchant_weapon_rate", InstantEnchantWeaponRate::new);
		EffectHandler.getInstance().registerHandler("i_energy_attack", InstantEnergyAttack::new);
		EffectHandler.getInstance().registerHandler("i_escape", InstantEscape::new);
		EffectHandler.getInstance().registerHandler("i_death", InstantDeath::new);
		EffectHandler.getInstance().registerHandler("i_fatal_blow", InstantFatalBlow::new);
		EffectHandler.getInstance().registerHandler("i_fatal_blow_abnormal", InstantFatalBlowAbnormal::new);
		EffectHandler.getInstance().registerHandler("i_focus_energy", InstantFocusEnergy::new);
		EffectHandler.getInstance().registerHandler("i_focus_max_energy", InstantFocusMaxEnergy::new);
		EffectHandler.getInstance().registerHandler("i_focus_soul", InstantFocusSoul::new);
		EffectHandler.getInstance().registerHandler("i_food_for_pet", InstantFoodForPet::new);
		EffectHandler.getInstance().registerHandler("i_get_agro", InstantGetAgro::new);
		EffectHandler.getInstance().registerHandler("i_get_exp", InstantGetExp::new);
		EffectHandler.getInstance().registerHandler("i_harvesting", InstantHarvesting::new);
		EffectHandler.getInstance().registerHandler("i_heal", InstantHeal::new);
		EffectHandler.getInstance().registerHandler("i_heal_special", InstantHealSpecial::new);
		EffectHandler.getInstance().registerHandler("i_holything_possess", InstantHolythingPossess::new);
		EffectHandler.getInstance().registerHandler("i_hp", InstantHp::new);
		EffectHandler.getInstance().registerHandler("i_hp_by_level", InstantHpByLevelSelf::new);
		EffectHandler.getInstance().registerHandler("i_hp_drain", InstantHpDrain::new);
		EffectHandler.getInstance().registerHandler("i_hp_per_max", InstantHpPerMax::new);
		EffectHandler.getInstance().registerHandler("i_hp_self", InstantHpSelf::new);
		EffectHandler.getInstance().registerHandler("i_hp_special", InstantHpSpecial::new);
		EffectHandler.getInstance().registerHandler("i_install_camp", InstantInstallCamp::new);
		EffectHandler.getInstance().registerHandler("i_install_camp_ex", InstantInstallCampEx::new);
		EffectHandler.getInstance().registerHandler("i_karma_count", InstantKarmaCount::new);
		EffectHandler.getInstance().registerHandler("i_knockback", InstantKnockBack::new);
		EffectHandler.getInstance().registerHandler("i_m_attack", InstantMagicalAttack::new);
		EffectHandler.getInstance().registerHandler("i_m_attack_by_abnormal", InstantMAttackByAbnormal::new);
		EffectHandler.getInstance().registerHandler("i_m_attack_by_abnormal_slot", InstantMAttackByAbnormalSlot::new);
		EffectHandler.getInstance().registerHandler("i_m_attack_by_dist", InstantMAttackByDist::new);
		EffectHandler.getInstance().registerHandler("i_m_attack_over_hit", InstantMagicalAttackOverHit::new);
		EffectHandler.getInstance().registerHandler("i_m_attack_mp", InstantMAttackMp::new);
		EffectHandler.getInstance().registerHandler("i_m_attack_range", InstantMAttackRange::new);
		EffectHandler.getInstance().registerHandler("i_m_soul_attack", InstantMSoulAttack::new);
		EffectHandler.getInstance().registerHandler("i_mount_for_event", InstantMountRideForEvent::new);
		EffectHandler.getInstance().registerHandler("i_mp", InstantMp::new);
		EffectHandler.getInstance().registerHandler("i_mp_by_level", InstantMpByLevel::new);
		EffectHandler.getInstance().registerHandler("i_mp_by_level_self", InstantMpByLevelSelf::new);
		EffectHandler.getInstance().registerHandler("i_mp_per_max", InstantMpPerMax::new);
		EffectHandler.getInstance().registerHandler("i_my_summon_kill", InstantMySummonKill::new);
		EffectHandler.getInstance().registerHandler("i_npc_kill", InstantNpcKill::new);
		EffectHandler.getInstance().registerHandler("i_open_common_recipebook", InstantOpenCommonRecipeBook::new);
		EffectHandler.getInstance().registerHandler("i_open_dwarf_recipebook", InstantOpenDwarfRecipeBook::new);
		EffectHandler.getInstance().registerHandler("i_p_attack", InstantPhysicalAttack::new);
		EffectHandler.getInstance().registerHandler("i_p_attack_save_hp", InstantPhysicalAttackSaveHp::new);
		EffectHandler.getInstance().registerHandler("i_p_attack_weapon_bonus", InstantPhysicalAttackWeaponBonus::new);
		EffectHandler.getInstance().registerHandler("i_p_soul_attack", InstantPhysicalSoulAttack::new);
		EffectHandler.getInstance().registerHandler("i_party_teleport_to_npc", InstantPartyTeleportToNpc::new);
		EffectHandler.getInstance().registerHandler("i_pcbang_point_up", InstantPcBangPointUp::new);
		EffectHandler.getInstance().registerHandler("i_physical_attack_hp_link", InstantPhysicalAttackHpLink::new);
		EffectHandler.getInstance().registerHandler("i_pk_count", InstantPkCount::new);
		EffectHandler.getInstance().registerHandler("i_pledge_send_system_message", InstantPledgeSendSystemMessage::new);
		EffectHandler.getInstance().registerHandler("i_plunder", InstantPlunder::new);
		EffectHandler.getInstance().registerHandler("i_position_change", InstantPositionChange::new);
		EffectHandler.getInstance().registerHandler("i_pull", InstantPull::new);
		EffectHandler.getInstance().registerHandler("i_pvppoint", InstantPvPPoint::new);
		EffectHandler.getInstance().registerHandler("i_randomize_hate", InstantRandomizeHate::new);
		EffectHandler.getInstance().registerHandler("i_real_damage", InstantRealDamage::new);
		EffectHandler.getInstance().registerHandler("i_rebalance_hp", InstantRebalanceHp::new);
		EffectHandler.getInstance().registerHandler("i_rebalance_hp_summon", InstantRebalanceHpSummon::new);
		EffectHandler.getInstance().registerHandler("i_refuel_airship", InstantRefuelAirship::new);
		EffectHandler.getInstance().registerHandler("i_remove_soul", InstantRemoveSoul::new);
		EffectHandler.getInstance().registerHandler("i_reset_quest", InstantResetQuest::new);
		EffectHandler.getInstance().registerHandler("i_resurrection", InstantResurrection::new);
		EffectHandler.getInstance().registerHandler("i_restoration", InstantRestoration::new);
		EffectHandler.getInstance().registerHandler("i_restoration_by_category", InstantRestorationByCategory::new); // Not confirmed
		EffectHandler.getInstance().registerHandler("i_restoration_random", InstantRestorationRandom::new);
		EffectHandler.getInstance().registerHandler("i_restore_vital_point_noncount", InstantRestoreVitalPointNonCount::new);
		EffectHandler.getInstance().registerHandler("i_set_hair_accessory_slot", InstantSetHairAccessorySlot::new); // Not confirmed
		EffectHandler.getInstance().registerHandler("i_set_skill", InstantSetSkill::new);
		EffectHandler.getInstance().registerHandler("i_skill_turning", InstantSkillTurning::new);
		EffectHandler.getInstance().registerHandler("i_soul_blow", InstantSoulBlow::new);
		EffectHandler.getInstance().registerHandler("i_sow", InstantSow::new);
		EffectHandler.getInstance().registerHandler("i_sp", InstantSp::new);
		EffectHandler.getInstance().registerHandler("i_spoil", InstantSpoil::new);
		EffectHandler.getInstance().registerHandler("i_steal_abnormal", InstantStealAbnormal::new);
		EffectHandler.getInstance().registerHandler("i_summon", InstantSummon::new);
		EffectHandler.getInstance().registerHandler("i_summon_cubic", InstantSummonCubic::new);
		EffectHandler.getInstance().registerHandler("i_summon_hallucination", InstantSummonHallucination::new);
		EffectHandler.getInstance().registerHandler("i_summon_multi", InstantSummonMulti::new);
		EffectHandler.getInstance().registerHandler("i_summon_npc", InstantSummonNpc::new);
		EffectHandler.getInstance().registerHandler("i_summon_pet", InstantSummonPet::new);
		EffectHandler.getInstance().registerHandler("i_summon_trap", InstantSummonTrap::new);
		EffectHandler.getInstance().registerHandler("i_summon_unique_npc", InstantSummonUniqueNpc::new);
		EffectHandler.getInstance().registerHandler("i_sweeper", InstantSweeper::new);
		EffectHandler.getInstance().registerHandler("i_target_cancel", InstantTargetCancel::new);
		EffectHandler.getInstance().registerHandler("i_target_me", InstantTargetMe::new);
		EffectHandler.getInstance().registerHandler("i_teleport", InstantTeleport::new);
		EffectHandler.getInstance().registerHandler("i_teleport_to_npc", InstantTeleportToNpc::new);
		EffectHandler.getInstance().registerHandler("i_teleport_to_summon", InstantTeleportToSummon::new);
		EffectHandler.getInstance().registerHandler("i_teleport_to_target", InstantTeleportToTarget::new);
		EffectHandler.getInstance().registerHandler("i_transfer_hate", InstantTransferHate::new);
		EffectHandler.getInstance().registerHandler("i_unsummon_agathion", InstantUnsummonAgathion::new);
		
		EffectHandler.getInstance().registerHandler("p_reduce_cancel", PumpReduceCancel::new);
		EffectHandler.getInstance().registerHandler("p_reduce_drop_penalty", PumpReduceDropPenalty::new);
		EffectHandler.getInstance().registerHandler("p_remove_equip_penalty", PumpRemoveEquipPenalty::new);
		EffectHandler.getInstance().registerHandler("p_ignore_skill", PumpIgnoreSkill::new);
		
		EffectHandler.getInstance().registerHandler("p_2h_blunt_bonus", PumpTwoHandedBluntBonus::new);
		EffectHandler.getInstance().registerHandler("p_2h_sword_bonus", PumpTwoHandedSwordBonus::new);
		EffectHandler.getInstance().registerHandler("p_ability_change", PumpAbilityChange::new);
		EffectHandler.getInstance().registerHandler("p_abnormal_shield", PumpAbnormalShield::new);
		EffectHandler.getInstance().registerHandler("p_absorb_damage", PumpAbsorbDamage::new); // Not confirmed
		EffectHandler.getInstance().registerHandler("p_area_damage", PumpAreaDamage::new);
		EffectHandler.getInstance().registerHandler("p_attack_attribute", PumpAttackAttribute::new);
		EffectHandler.getInstance().registerHandler("p_attack_attribute_add", PumpAttackAttributeAdd::new); // Not confirmed
		EffectHandler.getInstance().registerHandler("p_attack_behind", PumpAttackBehind::new); // Not confirmed
		EffectHandler.getInstance().registerHandler("p_attack_damage_position", PumpAttackDamagePosition::new); // Not confirmed
		EffectHandler.getInstance().registerHandler("p_attack_range", PumpAttackRange::new);
		EffectHandler.getInstance().registerHandler("p_attack_speed", PumpAttackSpeed::new);
		EffectHandler.getInstance().registerHandler("p_attack_speed_by_hp2", PumpAttackSpeedByHp2::new);
		EffectHandler.getInstance().registerHandler("p_attack_trait", PumpAttackTrait::new);
		EffectHandler.getInstance().registerHandler("p_aura", PumpAura::new);
		EffectHandler.getInstance().registerHandler("p_avoid", PumpAvoid::new);
		EffectHandler.getInstance().registerHandler("p_avoid_by_move_mode", PumpAvoidByMoveMode::new);
		EffectHandler.getInstance().registerHandler("p_avoid_rate_by_hp1", PumpAvoidRateByHp1::new);
		EffectHandler.getInstance().registerHandler("p_avoid_rate_by_hp2", PumpAvoidRateByHp2::new);
		EffectHandler.getInstance().registerHandler("p_avoid_skill", PumpAvoidSkill::new);
		EffectHandler.getInstance().registerHandler("p_betray", PumpBetray::new);
		EffectHandler.getInstance().registerHandler("p_block_act", PumpBlockAct::new);
		EffectHandler.getInstance().registerHandler("p_block_attack", PumpBlockAttack::new);
		EffectHandler.getInstance().registerHandler("p_block_buff", PumpBlockBuff::new);
		EffectHandler.getInstance().registerHandler("p_block_buff_slot", PumpBlockBuffSlot::new);
		EffectHandler.getInstance().registerHandler("p_block_chat", PumpBlockChat::new);
		EffectHandler.getInstance().registerHandler("p_block_controll", PumpBlockControl::new);
		EffectHandler.getInstance().registerHandler("p_block_debuff", PumpBlockDebuff::new);
		EffectHandler.getInstance().registerHandler("p_block_escape", PumpBlockEscape::new);
		EffectHandler.getInstance().registerHandler("p_block_getdamage", PumpBlockGetdamage::new);
		EffectHandler.getInstance().registerHandler("p_block_move", PumpBlockMove::new);
		EffectHandler.getInstance().registerHandler("p_block_party", PumpBlockParty::new);
		EffectHandler.getInstance().registerHandler("p_block_resurrection", PumpBlockResurrection::new);
		EffectHandler.getInstance().registerHandler("p_block_skill", PumpBlockSkill::new);
		EffectHandler.getInstance().registerHandler("p_block_skill_physical", PumpBlockSkillPhysical::new);
		EffectHandler.getInstance().registerHandler("p_block_spell", PumpBlockSpell::new);
		EffectHandler.getInstance().registerHandler("p_block_target", PumpBlockTarget::new);
		EffectHandler.getInstance().registerHandler("p_breath", PumpBreath::new);
		EffectHandler.getInstance().registerHandler("p_changebody", PumpChangebody::new);
		EffectHandler.getInstance().registerHandler("p_channel_clan", PumpChannelClan::new);
		EffectHandler.getInstance().registerHandler("p_cheapshot", PumpCheapShot::new);
		EffectHandler.getInstance().registerHandler("p_condition_block_act_item", PumpConditionBlockActItem::new);
		EffectHandler.getInstance().registerHandler("p_condition_block_act_skill", PumpConditionBlockActSkill::new);
		EffectHandler.getInstance().registerHandler("p_cp_regen", PumpCpRegen::new);
		EffectHandler.getInstance().registerHandler("p_crafting_critical", PumpCraftingCritical::new); // Not confirmed
		EffectHandler.getInstance().registerHandler("p_create_common_item", PumpCreateCommonItem::new);
		EffectHandler.getInstance().registerHandler("p_create_item", PumpCreateItem::new);
		EffectHandler.getInstance().registerHandler("p_critical_damage", PumpCriticalDamage::new);
		EffectHandler.getInstance().registerHandler("p_critical_damage_position", PumpCriticalDamagePosition::new);
		EffectHandler.getInstance().registerHandler("p_critical_rate", PumpCriticalRate::new);
		EffectHandler.getInstance().registerHandler("p_critical_rate_by_hp1", PumpCriticalRateByHp1::new);
		EffectHandler.getInstance().registerHandler("p_critical_rate_by_hp2", PumpCriticalRateByHp2::new);
		EffectHandler.getInstance().registerHandler("p_critical_rate_position_bonus", PumpCriticalRatePositionBonus::new);
		EffectHandler.getInstance().registerHandler("p_crystal_grade_modify", PumpCrystalGradeModify::new);
		EffectHandler.getInstance().registerHandler("p_crystallize", PumpCrystallize::new);
		EffectHandler.getInstance().registerHandler("p_cubic_mastery", PumpCubicMastery::new);
		EffectHandler.getInstance().registerHandler("p_damage_by_attack", PumpDamageByAttack::new);
		EffectHandler.getInstance().registerHandler("p_damage_shield", PumpDamageShield::new);
		EffectHandler.getInstance().registerHandler("p_damage_shield_resist", PumpDamageShieldResist::new);
		EffectHandler.getInstance().registerHandler("p_dc_mode", PumpDcMode::new);
		EffectHandler.getInstance().registerHandler("p_defence_attribute", PumpDefenceAttribute::new);
		EffectHandler.getInstance().registerHandler("p_defence_critical_damage", PumpDefenceCriticalDamage::new);
		EffectHandler.getInstance().registerHandler("p_defence_critical_rate", PumpDefenceCriticalRate::new);
		EffectHandler.getInstance().registerHandler("p_defence_trait", PumpDefenceTrait::new);
		EffectHandler.getInstance().registerHandler("p_disappear_target", PumpDisappearTarget::new);
		EffectHandler.getInstance().registerHandler("p_disarm", PumpDisarm::new);
		EffectHandler.getInstance().registerHandler("p_disarmor", PumpDisarmor::new); // Not confirmed
		EffectHandler.getInstance().registerHandler("p_droprate_modify", PumpDroprateModify::new);
		EffectHandler.getInstance().registerHandler("p_enlarge_abnormal_slot", PumpEnlargeAbnormalSlot::new);
		EffectHandler.getInstance().registerHandler("p_enlarge_storage", PumpEnlargeStorage::new);
		EffectHandler.getInstance().registerHandler("p_exp_modify", PumpExpModify::new);
		EffectHandler.getInstance().registerHandler("p_expand_deco_slot", PumpExpandDecoSlot::new);
		EffectHandler.getInstance().registerHandler("p_expand_jewel_slot", PumpExpandJewelSlot::new); // Not confirmed
		EffectHandler.getInstance().registerHandler("p_face_off", PumpFaceoff::new); // Not confirmed
		EffectHandler.getInstance().registerHandler("p_fatal_blow_rate", PumpFatalBlowRate::new);
		EffectHandler.getInstance().registerHandler("p_focus_energy", PumpFocusEnergy::new);
		EffectHandler.getInstance().registerHandler("p_get_damage_limit", PumpGetDamageLimit::new);
		EffectHandler.getInstance().registerHandler("p_get_item_by_exp", PumpGetItemByExp::new);
		EffectHandler.getInstance().registerHandler("p_hate_attack", PumpHateAttack::new);
		EffectHandler.getInstance().registerHandler("p_heal_effect", PumpHealEffect::new);
		EffectHandler.getInstance().registerHandler("p_heal_special_critical", PumpHealSpecialCritical::new); // Not confirmed
		EffectHandler.getInstance().registerHandler("p_hide", PumpHide::new);
		EffectHandler.getInstance().registerHandler("p_hit", PumpHit::new);
		EffectHandler.getInstance().registerHandler("p_hit_at_night", PumpHitAtNight::new);
		EffectHandler.getInstance().registerHandler("p_hit_number", PumpHitNumber::new);
		EffectHandler.getInstance().registerHandler("p_hp_regen", PumpHpRegen::new);
		EffectHandler.getInstance().registerHandler("p_hp_regen_by_move_mode", PumpHpRegenByMoveMode::new);
		EffectHandler.getInstance().registerHandler("p_ignore_death", PumpIgnoreDeath::new);
		EffectHandler.getInstance().registerHandler("p_instant_kill_resist", PumpInstantKillResist::new);
		EffectHandler.getInstance().registerHandler("p_limit_cp", PumpLimitCp::new);
		EffectHandler.getInstance().registerHandler("p_limit_hp", PumpLimitHp::new);
		EffectHandler.getInstance().registerHandler("p_limit_mp", PumpLimitMp::new);
		EffectHandler.getInstance().registerHandler("p_luck", PumpLuck::new);
		EffectHandler.getInstance().registerHandler("p_magic_abnormal_resist", PumpMagicAbnormalResist::new);
		EffectHandler.getInstance().registerHandler("p_magic_avoid", PumpMagicAvoid::new);
		EffectHandler.getInstance().registerHandler("p_magic_critical_dmg", PumpMagicCriticalDmg::new);
		EffectHandler.getInstance().registerHandler("p_magic_critical_rate", PumpMagicCriticalRate::new);
		EffectHandler.getInstance().registerHandler("p_magic_defence_critical_dmg", PumpMagicDefenceCriticalDmg::new);
		EffectHandler.getInstance().registerHandler("p_magic_defence_critical_rate", PumpMagicDefenceCriticalRate::new); // Not confirmed
		EffectHandler.getInstance().registerHandler("p_magic_hit", PumpMagicHit::new);
		EffectHandler.getInstance().registerHandler("p_magic_mp_cost", PumpMagicMpCost::new);
		EffectHandler.getInstance().registerHandler("p_magic_speed", PumpMagicSpeed::new);
		EffectHandler.getInstance().registerHandler("p_magical_attack", PumpMagicalAttack::new);
		EffectHandler.getInstance().registerHandler("p_magical_attack_add", PumpMagicalAttackAdd::new);
		EffectHandler.getInstance().registerHandler("p_magical_defence", PumpMagicalDefence::new);
		EffectHandler.getInstance().registerHandler("p_mana_charge", PumpManaCharge::new);
		EffectHandler.getInstance().registerHandler("p_max_cp", PumpMaxCp::new);
		EffectHandler.getInstance().registerHandler("p_max_hp", PumpMaxHp::new);
		EffectHandler.getInstance().registerHandler("p_max_mp", PumpMaxMp::new);
		EffectHandler.getInstance().registerHandler("p_max_mp_add", PumpMaxMpAdd::new);
		EffectHandler.getInstance().registerHandler("p_mp_regen", PumpMpRegen::new);
		EffectHandler.getInstance().registerHandler("p_mp_regen_add", PumpMpRegenAdd::new);
		EffectHandler.getInstance().registerHandler("p_mp_regen_by_move_mode", PumpMpRegenByMoveMode::new);
		EffectHandler.getInstance().registerHandler("p_mp_shield", PumpMpShield::new);
		EffectHandler.getInstance().registerHandler("p_mp_vampiric_attack", PumpMpVampiricAttack::new);
		EffectHandler.getInstance().registerHandler("p_passive", PumpPassive::new);
		EffectHandler.getInstance().registerHandler("p_physical_abnormal_resist", PumpPhysicalAbnormalResist::new);
		EffectHandler.getInstance().registerHandler("p_physical_attack", PumpPhysicalAttack::new);
		EffectHandler.getInstance().registerHandler("p_physical_attack_by_hp1", PumpPhysicalAttackByHp1::new);
		EffectHandler.getInstance().registerHandler("p_physical_attack_by_hp2", PumpPhysicalAttackByHp2::new);
		EffectHandler.getInstance().registerHandler("p_physical_defence", PumpPhysicalDefence::new);
		EffectHandler.getInstance().registerHandler("p_physical_defence_by_hp1", PumpPhysicalDefenceByHp1::new);
		EffectHandler.getInstance().registerHandler("p_physical_polarm_target_single", PumpPhysicalPolarmTargetSingle::new);
		EffectHandler.getInstance().registerHandler("p_physical_shield_defence", PumpPhysicalShieldDefence::new);
		EffectHandler.getInstance().registerHandler("p_physical_shield_defence_angle_all", PumpPhysicalShieldDefenceAngleAll::new);
		EffectHandler.getInstance().registerHandler("p_pk_protect", PumpPkProtect::new);
		EffectHandler.getInstance().registerHandler("p_pve_magical_skill_dmg_bonus", PumpPveMagicalSkillDmgBonus::new);
		EffectHandler.getInstance().registerHandler("p_pve_magical_skill_defence_bonus", PumpPveMagicalSkillDefenceBonus::new); // Not confirmed
		EffectHandler.getInstance().registerHandler("p_pve_physical_attack_dmg_bonus", PumpPvePhysicalAttackDmgBonus::new);
		EffectHandler.getInstance().registerHandler("p_pve_physical_attack_defence_bonus", PumpPvePhysicalAttackDefenceBonus::new); // Not confirmed
		EffectHandler.getInstance().registerHandler("p_pve_physical_skill_dmg_bonus", PumpPvePhysicalSkillDmgBonus::new);
		EffectHandler.getInstance().registerHandler("p_pve_physical_skill_defence_bonus", PumpPvePhysicalSkillDefenceBonus::new); // Not confirmed
		EffectHandler.getInstance().registerHandler("p_pvp_magical_skill_dmg_bonus", PumpPvpMagicalSkillDmgBonus::new);
		EffectHandler.getInstance().registerHandler("p_pvp_magical_skill_defence_bonus", PumpPvpMagicalSkillDefenceBonus::new);
		EffectHandler.getInstance().registerHandler("p_pvp_physical_attack_dmg_bonus", PumpPvpPhysicalAttackDmgBonus::new);
		EffectHandler.getInstance().registerHandler("p_pvp_physical_attack_defence_bonus", PumpPvpPhysicalAttackDefenceBonus::new);
		EffectHandler.getInstance().registerHandler("p_pvp_physical_skill_dmg_bonus", PumpPvpPhysicalSkillDmgBonus::new);
		EffectHandler.getInstance().registerHandler("p_pvp_physical_skill_defence_bonus", PumpPvpPhysicalSkillDefenceBonus::new);
		EffectHandler.getInstance().registerHandler("p_preserve_abnormal", PumpPreserveAbnormal::new);
		EffectHandler.getInstance().registerHandler("p_protect_death_penalty", PumpProtectDeathPenalty::new);
		EffectHandler.getInstance().registerHandler("p_reflect_dd", PumpReflectDD::new);
		EffectHandler.getInstance().registerHandler("p_reflect_skill", PumpReflectSkill::new);
		EffectHandler.getInstance().registerHandler("p_resist_abnormal_by_category", PumpResistAbnormalByCategory::new);
		EffectHandler.getInstance().registerHandler("p_resist_dd_magic", PumpResistDDMagic::new);
		EffectHandler.getInstance().registerHandler("p_resist_dispel_by_category", PumpResistDispelByCategory::new);
		EffectHandler.getInstance().registerHandler("p_reuse_delay", PumpReuseDelay::new);
		EffectHandler.getInstance().registerHandler("p_safe_fall_height", PumpSafeFallHeight::new);
		EffectHandler.getInstance().registerHandler("p_set_cloak_slot", PumpSetCloakSlot::new);
		EffectHandler.getInstance().registerHandler("p_shield_defence_rate", PumpShieldDefenceRate::new);
		EffectHandler.getInstance().registerHandler("p_skill_critical", PumpSkillCritical::new);
		EffectHandler.getInstance().registerHandler("p_skill_critical_damage", PumpSkillCriticalDamage::new); // Not confirmed
		EffectHandler.getInstance().registerHandler("p_skill_critical_rate", PumpSkillCriticalRate::new); // Not confirmed
		EffectHandler.getInstance().registerHandler("p_skill_critical_probability", PumpSkillCriticalProbability::new);
		EffectHandler.getInstance().registerHandler("p_skill_power", PumpSkillPower::new);
		EffectHandler.getInstance().registerHandler("p_soul_eating", PumpSoulEating::new);
		EffectHandler.getInstance().registerHandler("p_sp_modify", PumpSpModify::new);
		EffectHandler.getInstance().registerHandler("p_spell_power", PumpSpellPower::new);
		EffectHandler.getInstance().registerHandler("p_speed", PumpSpeed::new);
		EffectHandler.getInstance().registerHandler("p_speed_out_of_fight", PumpSpeedOutOfFight::new);
		EffectHandler.getInstance().registerHandler("p_spheric_barrier", PumpSphericBarrier::new); // Not confirmed
		EffectHandler.getInstance().registerHandler("p_stat_up", PumpStatUp::new);
		EffectHandler.getInstance().registerHandler("p_stat_up_at_night", PumpStatUpAtNight::new);
		EffectHandler.getInstance().registerHandler("p_statbonus_skillcritical", PumpStatBonusSkillCritical::new);
		EffectHandler.getInstance().registerHandler("p_statbonus_speed", PumpStatBonusSpeed::new);
		EffectHandler.getInstance().registerHandler("p_stop_vitality_effect", PumpStopVitalityEffect::new);
		EffectHandler.getInstance().registerHandler("p_summon_point", PumpSummonPoint::new);
		EffectHandler.getInstance().registerHandler("p_target_me", PumpTargetMe::new);
		EffectHandler.getInstance().registerHandler("p_transform", PumpTransform::new);
		EffectHandler.getInstance().registerHandler("p_transfer_damage_pc", PumpTransferDamagePc::new);
		EffectHandler.getInstance().registerHandler("p_transfer_damage_summon", PumpTransferDamageSummon::new);
		EffectHandler.getInstance().registerHandler("p_transform_hangover", PumpTransformHangover::new);
		EffectHandler.getInstance().registerHandler("p_trigger_skill_by_attack", PumpTriggerSkillByAttack::new);
		EffectHandler.getInstance().registerHandler("p_trigger_skill_by_avoid", PumpTriggerSkillByAvoid::new);
		EffectHandler.getInstance().registerHandler("p_trigger_skill_by_death", PumpTriggerSkillByDeath::new); // Not confirmed
		EffectHandler.getInstance().registerHandler("p_trigger_skill_by_dmg", PumpTriggerSkillByDmg::new);
		EffectHandler.getInstance().registerHandler("p_trigger_skill_by_kill", PumpTriggerSkillByKill::new);
		EffectHandler.getInstance().registerHandler("p_trigger_skill_by_magic_type", PumpTriggerSkillByMagicType::new);
		EffectHandler.getInstance().registerHandler("p_trigger_skill_by_skill", PumpTriggerSkillBySkill::new);
		EffectHandler.getInstance().registerHandler("p_vampiric_attack", PumpVampiricAttack::new);
		EffectHandler.getInstance().registerHandler("p_vampiric_defence", PumpVampiricDefence::new);
		EffectHandler.getInstance().registerHandler("p_violet_boy", PumpVioletBoy::new);
		EffectHandler.getInstance().registerHandler("p_weight_limit", PumpWeightLimit::new);
		EffectHandler.getInstance().registerHandler("p_weight_penalty", PumpWeightPenalty::new);
		EffectHandler.getInstance().registerHandler("p_world_chat_point", PumpWorldChatPoints::new); // Not confirmed
		EffectHandler.getInstance().registerHandler("p_wrong_casting", PumpWrongCasting::new);
		
		EffectHandler.getInstance().registerHandler("t_cp", TickCp::new);
		EffectHandler.getInstance().registerHandler("t_get_energy", TickGetEnergy::new);
		EffectHandler.getInstance().registerHandler("t_hp", TickHp::new);
		EffectHandler.getInstance().registerHandler("t_hp_fatal", TickHpFatal::new);
		EffectHandler.getInstance().registerHandler("t_hp_magic", TickHpMagic::new); // Not confirmed
		EffectHandler.getInstance().registerHandler("t_hp_to_owner", TickHpToOwner::new);
		EffectHandler.getInstance().registerHandler("t_mp", TickMp::new);
		EffectHandler.getInstance().registerHandler("t_synergy", TickSynergySkill::new); // Custom
		
		// Rework
		EffectHandler.getInstance().registerHandler("Confuse", Confuse::new);
		EffectHandler.getInstance().registerHandler("CounterPhysicalSkill", CounterPhysicalSkill::new);
		EffectHandler.getInstance().registerHandler("Fear", Fear::new);
		EffectHandler.getInstance().registerHandler("ModifyVital", ModifyVital::new);
		EffectHandler.getInstance().registerHandler("OpenChest", OpenChest::new);
		EffectHandler.getInstance().registerHandler("OpenDoor", OpenDoor::new);
		EffectHandler.getInstance().registerHandler("RechargeVitalPoint", RechargeVitalPoint::new);
		EffectHandler.getInstance().registerHandler("ResurrectionSpecial", ResurrectionSpecial::new);
		EffectHandler.getInstance().registerHandler("SetHp", SetHp::new);
		EffectHandler.getInstance().registerHandler("SilentMove", SilentMove::new);
		EffectHandler.getInstance().registerHandler("StopConsumeVitalPoint", StopConsumeVitalPoint::new);
		EffectHandler.getInstance().registerHandler("SummonAgathion", SummonAgathion::new);
		EffectHandler.getInstance().registerHandler("FlyAway", FlyAway::new);
		EffectHandler.getInstance().registerHandler("VitalityExpRate", PumpVitalityExpRate::new);
		EffectHandler.getInstance().registerHandler("VitalityPointsRate", PumpVitalityPointsRate::new);
		EffectHandler.getInstance().registerHandler("VitalityPointUp", InstantRestoreVitalPoint::new);
		
		LOGGER.info("Loaded {} effect handlers.", EffectHandler.getInstance().size());
	}
}
