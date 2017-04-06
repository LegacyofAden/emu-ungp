package org.l2junity.gameserver.model.effects;

import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.effects.effecttypes.consume.*;
import org.l2junity.gameserver.model.effects.effecttypes.instant.*;
import org.l2junity.gameserver.model.effects.effecttypes.pump.*;
import org.l2junity.gameserver.model.effects.effecttypes.tick.*;

import java.util.function.Function;

/**
 * @author ANZO
 * @since 02.04.2017
 */
public enum EffectType {
	c_chameleon_rest(ConsumeChameleonRest::new),
	c_fake_death(ConsumeFakeDeath::new),
	c_hp(ConsumeHp::new),
	c_mp(ConsumeMp::new),
	c_mp_by_level(ConsumeMpByLevel::new),
	c_rest(ConsumeRest::new),

	i_abnormal_time_change(InstantAbnormalTimeChange::new),
	i_add_hate(InstantAddHate::new),
	i_add_hate_mymaster(InstantAddHateMyMaster::new),
	i_align_direction(InstantAlignDirection::new),
	i_backstab(InstantBackstab::new),
	i_blink(InstantBlink::new),
	i_bonuscount_up(InstantBonusCountUp::new),
	i_bookmark_add_slot(InstantBookmarkAddSlot::new),
	i_call_party(InstantCallParty::new),
	i_call_pc(InstantCallPc::new),
	i_call_skill(InstantCallSkill::new),
	i_call_target_party(InstantCallTargetParty::new),
	i_capture_flag(InstantCaptureFlag::new),
	i_capture_flag_start(InstantCaptureFlagStart::new),
	i_change_face(InstantChangeFace::new),
	i_change_hair_color(InstantChangeHairColor::new),
	i_change_hair_style(InstantChangeHairStyle::new),
	i_change_skill_level(InstantChangeSkillLevel::new),
	i_class_change(InstantChangeClass::new),
	i_come_to_me(InstantComeToMe::new),
	i_consume_body(InstantConsumeBody::new),
	i_convert_item(InstantConvertItem::new),
	i_cp(InstantCp::new),
	i_cp_per_max(InstantCpPerMax::new),
	i_create_item_random(InstantCreateItemRandom::new),
	i_death_link(InstantDeathLink::new),
	i_defuse_trap(InstantDefuseTrap::new),
	i_delete_hate(InstantDeleteHate::new),
	i_delete_hate_of_me(InstantDeleteHateOfMe::new),
	i_delete_topaggro(InstantDeleteTopAgro::new),
	i_despawn(InstantDespawn::new),
	i_detect_object(InstantDetectObject::new),
	i_detect_trap(InstantDetectTrap::new),
	i_dismount_for_event(InstantDismountForEvent::new),
	i_dispel_all(InstantDispelAll::new),
	i_dispel_by_category(InstantDispelByCategory::new),
	i_dispel_by_name(InstantDispelByName::new),
	i_dispel_by_slot(InstantDispelBySlot::new),
	i_dispel_by_slot_myself(InstantDispelBySlotMyself::new),
	i_dispel_by_slot_probability(InstantDispelBySlotProbability::new),
	i_enchant_armor(InstantEnchantArmor::new),
	i_enchant_armor_rate(InstantEnchantArmorRate::new),
	i_enchant_attribute(InstantEnchantAttribute::new),
	i_enchant_item_multi(InstantEnchantItemMulti::new),
	i_enchant_weapon(InstantEnchantWeapon::new),
	i_enchant_weapon_rate(InstantEnchantWeaponRate::new),
	i_energy_attack(InstantEnergyAttack::new),
	i_escape(InstantEscape::new),
	i_death(InstantDeath::new),
	i_fatal_blow(InstantFatalBlow::new),
	i_fatal_blow_abnormal(InstantFatalBlowAbnormal::new),
	i_focus_energy(InstantFocusEnergy::new),
	i_focus_max_energy(InstantFocusMaxEnergy::new),
	i_focus_soul(InstantFocusSoul::new),
	i_food_for_pet(InstantFoodForPet::new),
	i_get_agro(InstantGetAgro::new),
	i_get_exp(InstantGetExp::new),
	i_harvesting(InstantHarvesting::new),
	i_heal(InstantHeal::new),
	i_heal_special(InstantHealSpecial::new),
	i_holything_possess(InstantHolythingPossess::new),
	i_hp(InstantHp::new),
	i_hp_by_level(InstantHpByLevelSelf::new),
	i_hp_drain(InstantHpDrain::new),
	i_hp_per_max(InstantHpPerMax::new),
	i_hp_self(InstantHpSelf::new),
	i_hp_special(InstantHpSpecial::new),
	i_install_camp(InstantInstallCamp::new),
	i_install_camp_ex(InstantInstallCampEx::new),
	i_karma_count(InstantKarmaCount::new),
	i_knockback(InstantKnockBack::new),
	i_m_attack(InstantMagicalAttack::new),
	i_m_attack_by_abnormal(InstantMAttackByAbnormal::new),
	i_m_attack_by_abnormal_slot(InstantMAttackByAbnormalSlot::new),
	i_m_attack_by_dist(InstantMAttackByDist::new),
	i_m_attack_over_hit(InstantMagicalAttackOverHit::new),
	i_m_attack_mp(InstantMAttackMp::new),
	i_m_attack_range(InstantMAttackRange::new),
	i_m_soul_attack(InstantMSoulAttack::new),
	i_mount_for_event(InstantMountRideForEvent::new),
	i_mp(InstantMp::new),
	i_mp_by_level(InstantMpByLevel::new),
	i_mp_by_level_self(InstantMpByLevelSelf::new),
	i_mp_per_max(InstantMpPerMax::new),
	i_my_summon_kill(InstantMySummonKill::new),
	i_npc_kill(InstantNpcKill::new),
	i_open_common_recipebook(InstantOpenCommonRecipeBook::new),
	i_open_dwarf_recipebook(InstantOpenDwarfRecipeBook::new),
	i_p_attack(InstantPhysicalAttack::new),
	i_p_attack_save_hp(InstantPhysicalAttackSaveHp::new),
	i_p_attack_weapon_bonus(InstantPhysicalAttackWeaponBonus::new),
	i_p_soul_attack(InstantPhysicalSoulAttack::new),
	i_party_teleport_to_npc(InstantPartyTeleportToNpc::new),
	i_pcbang_point_up(InstantPcBangPointUp::new),
	i_physical_attack_hp_link(InstantPhysicalAttackHpLink::new),
	i_pk_count(InstantPkCount::new),
	i_pledge_send_system_message(InstantPledgeSendSystemMessage::new),
	i_plunder(InstantPlunder::new),
	i_position_change(InstantPositionChange::new),
	i_pull(InstantPull::new),
	i_pvppoint(InstantPvPPoint::new),
	i_randomize_hate(InstantRandomizeHate::new),
	i_real_damage(InstantRealDamage::new),
	i_rebalance_hp(InstantRebalanceHp::new),
	i_rebalance_hp_summon(InstantRebalanceHpSummon::new),
	i_refuel_airship(InstantRefuelAirship::new),
	i_remove_soul(InstantRemoveSoul::new),
	i_reset_quest(InstantResetQuest::new),
	i_resurrection(InstantResurrection::new),
	i_restoration(InstantRestoration::new),
	i_restoration_by_category(InstantRestorationByCategory::new),
	// Not confirmed
	i_restoration_random(InstantRestorationRandom::new),
	i_restore_vital_point_noncount(InstantRestoreVitalPointNonCount::new),
	i_set_hair_accessory_slot(InstantSetHairAccessorySlot::new),
	// Not confirmed
	i_set_skill(InstantSetSkill::new),
	i_skill_turning(InstantSkillTurning::new),
	i_soul_blow(InstantSoulBlow::new),
	i_sow(InstantSow::new),
	i_sp(InstantSp::new),
	i_spoil(InstantSpoil::new),
	i_steal_abnormal(InstantStealAbnormal::new),
	i_summon(InstantSummon::new),
	i_summon_cubic(InstantSummonCubic::new),
	i_summon_hallucination(InstantSummonHallucination::new),
	i_summon_multi(InstantSummonMulti::new),
	i_summon_npc(InstantSummonNpc::new),
	i_summon_pet(InstantSummonPet::new),
	i_summon_trap(InstantSummonTrap::new),
	i_summon_unique_npc(InstantSummonUniqueNpc::new),
	i_sweeper(InstantSweeper::new),
	i_target_cancel(InstantTargetCancel::new),
	i_target_me(InstantTargetMe::new),
	i_teleport(InstantTeleport::new),
	i_teleport_to_npc(InstantTeleportToNpc::new),
	i_teleport_to_summon(InstantTeleportToSummon::new),
	i_teleport_to_target(InstantTeleportToTarget::new),
	i_transfer_hate(InstantTransferHate::new),
	i_unsummon_agathion(InstantUnsummonAgathion::new),

	p_reduce_cancel(PumpReduceCancel::new),
	p_reduce_drop_penalty(PumpReduceDropPenalty::new),
	p_remove_equip_penalty(PumpRemoveEquipPenalty::new),
	p_ignore_skill(PumpIgnoreSkill::new),

	p_2h_blunt_bonus(PumpTwoHandedBluntBonus::new),
	p_2h_sword_bonus(PumpTwoHandedSwordBonus::new),
	p_ability_change(PumpAbilityChange::new),
	p_abnormal_shield(PumpAbnormalShield::new),
	p_absorb_damage(PumpAbsorbDamage::new),
	// Not confirmed
	p_area_damage(PumpAreaDamage::new),
	p_attack_attribute(PumpAttackAttribute::new),
	p_attack_attribute_add(PumpAttackAttributeAdd::new),
	// Not confirmed
	p_attack_behind(PumpAttackBehind::new),
	// Not confirmed
	p_attack_damage_position(PumpAttackDamagePosition::new),
	// Not confirmed
	p_attack_range(PumpAttackRange::new),
	p_attack_speed(PumpAttackSpeed::new),
	p_attack_speed_by_hp2(PumpAttackSpeedByHp2::new),
	p_attack_trait(PumpAttackTrait::new),
	p_aura(PumpAura::new),
	p_avoid(PumpAvoid::new),
	p_avoid_by_move_mode(PumpAvoidByMoveMode::new),
	p_avoid_rate_by_hp1(PumpAvoidRateByHp1::new),
	p_avoid_rate_by_hp2(PumpAvoidRateByHp2::new),
	p_avoid_skill(PumpAvoidSkill::new),
	p_betray(PumpBetray::new),
	p_block_act(PumpBlockAct::new),
	p_block_attack(PumpBlockAttack::new),
	p_block_buff(PumpBlockBuff::new),
	p_block_buff_slot(PumpBlockBuffSlot::new),
	p_block_chat(PumpBlockChat::new),
	p_block_controll(PumpBlockControl::new),
	p_block_debuff(PumpBlockDebuff::new),
	p_block_escape(PumpBlockEscape::new),
	p_block_getdamage(PumpBlockGetdamage::new),
	p_block_move(PumpBlockMove::new),
	p_block_party(PumpBlockParty::new),
	p_block_resurrection(PumpBlockResurrection::new),
	p_block_skill(PumpBlockSkill::new),
	p_block_skill_physical(PumpBlockSkillPhysical::new),
	p_block_spell(PumpBlockSpell::new),
	p_block_target(PumpBlockTarget::new),
	p_breath(PumpBreath::new),
	p_changebody(PumpChangebody::new),
	p_channel_clan(PumpChannelClan::new),
	p_cheapshot(PumpCheapShot::new),
	p_condition_block_act_item(PumpConditionBlockActItem::new),
	p_condition_block_act_skill(PumpConditionBlockActSkill::new),
	p_cp_regen(PumpCpRegen::new),
	p_crafting_critical(PumpCraftingCritical::new),
	// Not confirmed
	p_create_common_item(PumpCreateCommonItem::new),
	p_create_item(PumpCreateItem::new),
	p_critical_damage(PumpCriticalDamage::new),
	p_critical_damage_position(PumpCriticalDamagePosition::new),
	p_critical_rate(PumpCriticalRate::new),
	p_critical_rate_by_hp1(PumpCriticalRateByHp1::new),
	p_critical_rate_by_hp2(PumpCriticalRateByHp2::new),
	p_critical_rate_position_bonus(PumpCriticalRatePositionBonus::new),
	p_crystal_grade_modify(PumpCrystalGradeModify::new),
	p_crystallize(PumpCrystallize::new),
	p_cubic_mastery(PumpCubicMastery::new),
	p_damage_by_attack(PumpDamageByAttack::new),
	p_damage_shield(PumpDamageShield::new),
	p_damage_shield_resist(PumpDamageShieldResist::new),
	p_dc_mode(PumpDcMode::new),
	p_defence_attribute(PumpDefenceAttribute::new),
	p_defence_critical_damage(PumpDefenceCriticalDamage::new),
	p_defence_critical_rate(PumpDefenceCriticalRate::new),
	p_defence_trait(PumpDefenceTrait::new),
	p_disappear_target(PumpDisappearTarget::new),
	p_disarm(PumpDisarm::new),
	p_disarmor(PumpDisarmor::new),
	// Not confirmed
	p_droprate_modify(PumpDroprateModify::new),
	p_enlarge_abnormal_slot(PumpEnlargeAbnormalSlot::new),
	p_enlarge_storage(PumpEnlargeStorage::new),
	p_exp_modify(PumpExpModify::new),
	p_expand_deco_slot(PumpExpandDecoSlot::new),
	p_expand_jewel_slot(PumpExpandJewelSlot::new),
	// Not confirmed
	p_face_off(PumpFaceoff::new),
	// Not confirmed
	p_fatal_blow_rate(PumpFatalBlowRate::new),
	p_focus_energy(PumpFocusEnergy::new),
	p_get_damage_limit(PumpGetDamageLimit::new),
	p_get_item_by_exp(PumpGetItemByExp::new),
	p_hate_attack(PumpHateAttack::new),
	p_heal_effect(PumpHealEffect::new),
	p_heal_special_critical(PumpHealSpecialCritical::new),
	// Not confirmed
	p_hide(PumpHide::new),
	p_hit(PumpHit::new),
	p_hit_at_night(PumpHitAtNight::new),
	p_hit_number(PumpHitNumber::new),
	p_hp_regen(PumpHpRegen::new),
	p_hp_regen_by_move_mode(PumpHpRegenByMoveMode::new),
	p_ignore_death(PumpIgnoreDeath::new),
	p_instant_kill_resist(PumpInstantKillResist::new),
	p_limit_cp(PumpLimitCp::new),
	p_limit_hp(PumpLimitHp::new),
	p_limit_mp(PumpLimitMp::new),
	p_luck(PumpLuck::new),
	p_magic_abnormal_resist(PumpMagicAbnormalResist::new),
	p_magic_avoid(PumpMagicAvoid::new),
	p_magic_critical_dmg(PumpMagicCriticalDmg::new),
	p_magic_critical_rate(PumpMagicCriticalRate::new),
	p_magic_defence_critical_dmg(PumpMagicDefenceCriticalDmg::new),
	p_magic_defence_critical_rate(PumpMagicDefenceCriticalRate::new),
	// Not confirmed
	p_magic_hit(PumpMagicHit::new),
	p_magic_mp_cost(PumpMagicMpCost::new),
	p_magic_speed(PumpMagicSpeed::new),
	p_magical_attack(PumpMagicalAttack::new),
	p_magical_attack_add(PumpMagicalAttackAdd::new),
	p_magical_defence(PumpMagicalDefence::new),
	p_mana_charge(PumpManaCharge::new),
	p_max_cp(PumpMaxCp::new),
	p_max_hp(PumpMaxHp::new),
	p_max_mp(PumpMaxMp::new),
	p_max_mp_add(PumpMaxMpAdd::new),
	p_mp_regen(PumpMpRegen::new),
	p_mp_regen_add(PumpMpRegenAdd::new),
	p_mp_regen_by_move_mode(PumpMpRegenByMoveMode::new),
	p_mp_shield(PumpMpShield::new),
	p_mp_vampiric_attack(PumpMpVampiricAttack::new),
	p_passive(PumpPassive::new),
	p_physical_abnormal_resist(PumpPhysicalAbnormalResist::new),
	p_physical_attack(PumpPhysicalAttack::new),
	p_physical_attack_by_hp1(PumpPhysicalAttackByHp1::new),
	p_physical_attack_by_hp2(PumpPhysicalAttackByHp2::new),
	p_physical_defence(PumpPhysicalDefence::new),
	p_physical_defence_by_hp1(PumpPhysicalDefenceByHp1::new),
	p_physical_polarm_target_single(PumpPhysicalPolarmTargetSingle::new),
	p_physical_shield_defence(PumpPhysicalShieldDefence::new),
	p_physical_shield_defence_angle_all(PumpPhysicalShieldDefenceAngleAll::new),
	p_pk_protect(PumpPkProtect::new),
	p_pve_magical_skill_dmg_bonus(PumpPveMagicalSkillDmgBonus::new),
	p_pve_magical_skill_defence_bonus(PumpPveMagicalSkillDefenceBonus::new),
	// Not confirmed
	p_pve_physical_attack_dmg_bonus(PumpPvePhysicalAttackDmgBonus::new),
	p_pve_physical_attack_defence_bonus(PumpPvePhysicalAttackDefenceBonus::new),
	// Not confirmed
	p_pve_physical_skill_dmg_bonus(PumpPvePhysicalSkillDmgBonus::new),
	p_pve_physical_skill_defence_bonus(PumpPvePhysicalSkillDefenceBonus::new),
	// Not confirmed
	p_pvp_magical_skill_dmg_bonus(PumpPvpMagicalSkillDmgBonus::new),
	p_pvp_magical_skill_defence_bonus(PumpPvpMagicalSkillDefenceBonus::new),
	p_pvp_physical_attack_dmg_bonus(PumpPvpPhysicalAttackDmgBonus::new),
	p_pvp_physical_attack_defence_bonus(PumpPvpPhysicalAttackDefenceBonus::new),
	p_pvp_physical_skill_dmg_bonus(PumpPvpPhysicalSkillDmgBonus::new),
	p_pvp_physical_skill_defence_bonus(PumpPvpPhysicalSkillDefenceBonus::new),
	p_preserve_abnormal(PumpPreserveAbnormal::new),
	p_protect_death_penalty(PumpProtectDeathPenalty::new),
	p_reflect_dd(PumpReflectDD::new),
	p_reflect_skill(PumpReflectSkill::new),
	p_resist_abnormal_by_category(PumpResistAbnormalByCategory::new),
	p_resist_dd_magic(PumpResistDDMagic::new),
	p_resist_dispel_by_category(PumpResistDispelByCategory::new),
	p_reuse_delay(PumpReuseDelay::new),
	p_safe_fall_height(PumpSafeFallHeight::new),
	p_set_cloak_slot(PumpSetCloakSlot::new),
	p_shield_defence_rate(PumpShieldDefenceRate::new),
	p_skill_critical(PumpSkillCritical::new),
	p_skill_critical_damage(PumpSkillCriticalDamage::new),
	// Not confirmed
	p_skill_critical_rate(PumpSkillCriticalRate::new),
	// Not confirmed
	p_skill_critical_probability(PumpSkillCriticalProbability::new),
	p_skill_power(PumpSkillPower::new),
	p_soul_eating(PumpSoulEating::new),
	p_sp_modify(PumpSpModify::new),
	p_spell_power(PumpSpellPower::new),
	p_speed(PumpSpeed::new),
	p_speed_out_of_fight(PumpSpeedOutOfFight::new),
	p_spheric_barrier(PumpSphericBarrier::new),
	// Not confirmed
	p_stat_up(PumpStatUp::new),
	p_stat_up_at_night(PumpStatUpAtNight::new),
	p_statbonus_skillcritical(PumpStatBonusSkillCritical::new),
	p_statbonus_speed(PumpStatBonusSpeed::new),
	p_stop_vitality_effect(PumpStopVitalityEffect::new),
	p_summon_point(PumpSummonPoint::new),
	p_target_me(PumpTargetMe::new),
	p_transform(PumpTransform::new),
	p_transfer_damage_pc(PumpTransferDamagePc::new),
	p_transfer_damage_summon(PumpTransferDamageSummon::new),
	p_transform_hangover(PumpTransformHangover::new),
	p_trigger_skill_by_attack(PumpTriggerSkillByAttack::new),
	p_trigger_skill_by_avoid(PumpTriggerSkillByAvoid::new),
	p_trigger_skill_by_death(PumpTriggerSkillByDeath::new),
	// Not confirmed
	p_trigger_skill_by_dmg(PumpTriggerSkillByDmg::new),
	p_trigger_skill_by_kill(PumpTriggerSkillByKill::new),
	p_trigger_skill_by_magic_type(PumpTriggerSkillByMagicType::new),
	p_trigger_skill_by_skill(PumpTriggerSkillBySkill::new),
	p_vampiric_attack(PumpVampiricAttack::new),
	p_vampiric_defence(PumpVampiricDefence::new),
	p_violet_boy(PumpVioletBoy::new),
	p_weight_limit(PumpWeightLimit::new),
	p_weight_penalty(PumpWeightPenalty::new),
	p_world_chat_point(PumpWorldChatPoints::new),
	// Not confirmed
	p_wrong_casting(PumpWrongCasting::new),

	t_cp(TickCp::new),
	t_get_energy(TickGetEnergy::new),
	t_hp(TickHp::new),
	t_hp_fatal(TickHpFatal::new),
	t_hp_magic(TickHpMagic::new),
	// Not confirmed
	t_hp_to_owner(TickHpToOwner::new),
	t_mp(TickMp::new),
	t_synergy(TickSynergySkill::new),
	// Custom

	// Rework
	Confuse(org.l2junity.gameserver.model.effects.effecttypes.custom.Confuse::new),
	CounterPhysicalSkill(org.l2junity.gameserver.model.effects.effecttypes.custom.CounterPhysicalSkill::new),
	Fear(org.l2junity.gameserver.model.effects.effecttypes.custom.Fear::new),
	ModifyVital(org.l2junity.gameserver.model.effects.effecttypes.custom.ModifyVital::new),
	OpenChest(org.l2junity.gameserver.model.effects.effecttypes.custom.OpenChest::new),
	OpenDoor(org.l2junity.gameserver.model.effects.effecttypes.custom.OpenDoor::new),
	RechargeVitalPoint(org.l2junity.gameserver.model.effects.effecttypes.custom.RechargeVitalPoint::new),
	ResurrectionSpecial(org.l2junity.gameserver.model.effects.effecttypes.custom.ResurrectionSpecial::new),
	SetHp(org.l2junity.gameserver.model.effects.effecttypes.custom.SetHp::new),
	SilentMove(org.l2junity.gameserver.model.effects.effecttypes.custom.SilentMove::new),
	StopConsumeVitalPoint(org.l2junity.gameserver.model.effects.effecttypes.custom.StopConsumeVitalPoint::new),
	SummonAgathion(org.l2junity.gameserver.model.effects.effecttypes.custom.SummonAgathion::new),
	FlyAway(org.l2junity.gameserver.model.effects.effecttypes.custom.FlyAway::new),
	VitalityExpRate(PumpVitalityExpRate::new),
	VitalityPointsRate(PumpVitalityPointsRate::new),
	VitalityPointUp(InstantRestoreVitalPoint::new);

	Function<StatsSet, AbstractEffect> function;

	EffectType(Function<StatsSet, AbstractEffect> function) {
		this.function = function;
	}

	public AbstractEffect getNew(StatsSet set) {
		return function.apply(set);
	}
}