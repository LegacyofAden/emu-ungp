package org.l2junity.gameserver.data.txt.model.npc;

import lombok.Data;
import org.l2junity.gameserver.data.txt.gen.NpcDatasParser;
import org.l2junity.gameserver.data.txt.model.constants.*;
import org.l2junity.gameserver.data.txt.model.item.AttributeAttack;
import org.l2junity.gameserver.model.stats.DoubleStat;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * This class designed to be immutable, thread safe npc template.
 * Instances of this class can be shared across thread without any limitations.
 *
 * @author ANZO
 * @since 04.04.17
 */
@Data
public final class NpcTemplate {
	private final int npcId;
	private final NpcType npcType;
	private final String name;
	private int level;
	private long exp;
	private boolean exCrtEffect;
	private boolean unique;
	private double sNpcPropHpRate;
	private RaceType race;
	private Sex sex;
	private Object skillList;
	private String slotChest;
	private String slotRHand;
	private String slotLHand;
	private double hitTimeFactor;
	private double hitTimeFactorSkill;
	private List<Double> groundHigh;
	private List<Double> groundLow;
	private int safeHeight;
	private int soulshotCount;
	private int spiritshotCount;
	private List<String> clan;
	private List<String> ignoreClanList;
	private int clanHelpRange;
	private boolean undying;
	private boolean canBeAttacked;
	private int corpseTime;
	private boolean sleepMode;
	private int agroRange;
	private int passableDoor;
	private boolean canMove;
	private boolean flying;
	private boolean hasSummoner;
	private boolean targetable;
	private boolean showNameTag;
	private boolean isDeathPenalty;
	private String aiName;
	private List<NpcDatasParser.Ai_paramContext> aiParams;
	private int eventFlag;
	private boolean unsowing;
	private boolean privateRespawnLog;
	private double acquireExpRate;
	private double acquireSp;
	private double acquireRp;
	private List<NpcDatasParser.Make_itemContext> corpseMakeList;
	private List<NpcDatasParser.Make_itemContext> additionalMakeList;
	private NpcDatasParser.Make_group_listContext additionalMakeMultiList;
	private NpcDatasParser.Make_group_listContext exItemDropList;
	private NpcDatasParser.Make_group_listContext vitalityItemDropList;

	private double mpRewardValue;
	private MpRewardType mpRewardType;
	private double mpRewardTicks;
	private MpRewardAffectType mpRewardAffectType;

	private int fakeClassId;
	private int eventDrop;
	private List<Integer> exDrop;
	private boolean enableMoveAfterTalk;
	private List<Integer> broadcastCond;

	private WeaponType baseAttackType;

	private double collisionRadius;
	private double collisionHeight;
	private double collisionRadiusGrown;
	private double collisionHeightGrown;

	protected final Map<DoubleStat, Double> baseValues = new EnumMap<>(DoubleStat.class);

	public NpcTemplate(NpcDatasParser.NpcContext ctx) {
		npcId = ctx.npc_id().value;
		npcType = ctx.npc_type().value;
		name = ctx.npc_name().value;
		level = ctx.level().int_object().value;
		exp = ctx.exp().long_object().value;
		exCrtEffect = ctx.ex_crt_effect().bool_object().value;
		unique = ctx.unique().bool_object().value;
		sNpcPropHpRate = ctx.s_npc_prop_hp_rate().double_object().value;
		race = ctx.race().value;
		sex = ctx.sex().value;
		skillList = ctx.skill_list().category_list().value;
		slotChest = ctx.slot_chest().empty_name_object() == null ? ctx.slot_chest().name_object().value : "";
		slotRHand = ctx.slot_rhand().empty_name_object() == null ? ctx.slot_rhand().name_object().value : "";
		slotLHand = ctx.slot_lhand().empty_name_object() == null ? ctx.slot_lhand().name_object().value : "";
		hitTimeFactor = ctx.hit_time_factor().double_object().value;
		hitTimeFactorSkill = ctx.hit_time_factor_skill().int_object().value;

		baseAttackType = ctx.base_attack_type().value;

		// Base stats
		baseValues.put(DoubleStat.STAT_STR, (double)ctx.str().int_object().value);
		baseValues.put(DoubleStat.STAT_CON, (double)ctx.con().int_object().value);
		baseValues.put(DoubleStat.STAT_DEX, (double)ctx.dex().int_object().value);
		baseValues.put(DoubleStat.STAT_INT, (double)ctx.int_().int_object().value);
		baseValues.put(DoubleStat.STAT_WIT, (double)ctx.wit().int_object().value);
		baseValues.put(DoubleStat.STAT_MEN, (double)ctx.men().int_object().value);
		baseValues.put(DoubleStat.STAT_LUC, 0D);
		baseValues.put(DoubleStat.STAT_CHA, 0D);

		// Max HP/MP/CP
		baseValues.put(DoubleStat.MAX_HP, ctx.org_hp().double_object().value);
		baseValues.put(DoubleStat.MAX_MP, ctx.org_mp().double_object().value);
		baseValues.put(DoubleStat.MAX_CP, 0D);

		// Regenerate HP/MP/CP
		baseValues.put(DoubleStat.REGENERATE_HP_RATE, ctx.org_hp_regen().double_object().value);
		baseValues.put(DoubleStat.REGENERATE_MP_RATE, ctx.org_mp_regen().double_object().value);
		baseValues.put(DoubleStat.REGENERATE_CP_RATE, 0D);

		// Attack and Defense
		baseValues.put(DoubleStat.PHYSICAL_ATTACK,  ctx.base_physical_attack().double_object().value);
		baseValues.put(DoubleStat.MAGIC_ATTACK, ctx.base_magic_attack().double_object().value);
		baseValues.put(DoubleStat.PHYSICAL_DEFENCE, ctx.base_defend().double_object().value);
		baseValues.put(DoubleStat.MAGICAL_DEFENCE, ctx.base_magic_defend().double_object().value);

		// Attack speed
		baseValues.put(DoubleStat.PHYSICAL_ATTACK_SPEED, ctx.base_attack_speed().double_object().value);
		baseValues.put(DoubleStat.MAGIC_ATTACK_SPEED, 333D);

		// Misc
		baseValues.put(DoubleStat.SHIELD_DEFENCE, ctx.shield_defense().double_object().value);
		baseValues.put(DoubleStat.PHYSICAL_ATTACK_RANGE, (double)ctx.base_attack_range().int_object().value);
		baseValues.put(DoubleStat.RANDOM_DAMAGE, (double)ctx.base_rand_dam().int_object().value);

		// Shield and critical rates
		baseValues.put(DoubleStat.SHIELD_DEFENCE_RATE, (double)ctx.shield_defense_rate().int_object().value);
		baseValues.put(DoubleStat.CRITICAL_RATE, ctx.base_critical().double_object().value);
		baseValues.put(DoubleStat.MAGIC_CRITICAL_RATE, 5D);

		// Breath under water
		baseValues.put(DoubleStat.BREATH, 100D);

		// Elemental Attributes
		// Attack
		AttributeAttack attributeAttack = ctx.base_attribute_attack().value;
		for (AttributeType attributeType : AttributeType.values()) {
			if (attributeType != AttributeType.NONE) {
				if (attributeAttack.getType() == attributeType) {
					baseValues.put(attributeType.getAttackStat(), (double)attributeAttack.getValue());
				}
				else {
					baseValues.put(attributeType.getAttackStat(), 0D);
				}
			}
		}

		// Defense
		List<Double> attributeDefend = ctx.base_attribute_defend().value;
		baseValues.put(DoubleStat.FIRE_RES, attributeDefend.get(0));
		baseValues.put(DoubleStat.WATER_RES, attributeDefend.get(1));
		baseValues.put(DoubleStat.WIND_RES, attributeDefend.get(2));
		baseValues.put(DoubleStat.EARTH_RES, attributeDefend.get(3));
		baseValues.put(DoubleStat.HOLY_RES, attributeDefend.get(4));
		baseValues.put(DoubleStat.DARK_RES, attributeDefend.get(5));
		baseValues.put(DoubleStat.BASE_ATTRIBUTE_RES, attributeDefend.get(6));

		// Accuracy / Evasion (Used for NPC only)
		baseValues.put(DoubleStat.ACCURACY_COMBAT, ctx.physical_hit_modify().double_object().value);
		baseValues.put(DoubleStat.EVASION_RATE, (double)ctx.physical_avoid_modify().int_object().value);

		// Basic property
		baseValues.put(DoubleStat.ABNORMAL_RESIST_PHYSICAL, (double)ctx.abnormal_resist().int_list().value.get(0));
		baseValues.put(DoubleStat.ABNORMAL_RESIST_MAGICAL, (double)ctx.abnormal_resist().int_list().value.get(1));

		/*params.set("distance", ctx.base_damage_range().int_list().value.get(2));// TODO: Implement me
		params.set("width", ctx.base_damage_range().int_list().value.get(3));// TODO: Implement me
		params.set("reuseDelay", ctx.base_reuse_delay().int_object().value);// TODO: Implement me*/

		// Speed
		baseValues.put(DoubleStat.RUN_SPEED, ctx.ground_high().double_list().value.get(0));
		baseValues.put(DoubleStat.WALK_SPEED, ctx.ground_low().double_list().value.get(0));

		// Swimming
		baseValues.put(DoubleStat.SWIM_RUN_SPEED, ctx.ground_high().double_list().value.get(0));
		baseValues.put(DoubleStat.SWIM_WALK_SPEED, ctx.ground_low().double_list().value.get(0));

		// Flying
		baseValues.put(DoubleStat.FLY_RUN_SPEED, ctx.ground_high().double_list().value.get(0));
		baseValues.put(DoubleStat.FLY_WALK_SPEED, ctx.ground_low().double_list().value.get(0));

		collisionRadius = ctx.collision_radius().double_list().value.get(0);
		collisionHeight = ctx.collision_height().double_list().value.get(0);
		collisionRadiusGrown = ctx.collision_radius().double_list().value.get(1);
		collisionHeightGrown = ctx.collision_height().double_list().value.get(1);

		safeHeight = ctx.safe_height().int_object().value;
		soulshotCount = ctx.soulshot_count().int_object().value;
		spiritshotCount = ctx.spiritshot_count().int_object().value;
		clan = ctx.clan().int_object() == null ? ctx.clan().category_list().value : Collections.emptyList();
		ignoreClanList = ctx.ignore_clan_list().category_list().value;
		clanHelpRange = ctx.clan_help_range().int_object().value;
		undying = ctx.undying().bool_object().value;
		canBeAttacked = ctx.can_be_attacked().bool_object().value;
		corpseTime = ctx.corpse_time().int_object().value;
		sleepMode = ctx.no_sleep_mode().bool_object().value;
		agroRange = ctx.agro_range().int_object().value;
		passableDoor = ctx.passable_door().int_object().value;
		canMove = ctx.can_move().bool_object().value;
		flying = ctx.flying().bool_object().value;
		hasSummoner = ctx.has_summoner().bool_object().value;
		targetable = ctx.targetable().bool_object().value;
		showNameTag = ctx.show_name_tag().bool_object().value;
		isDeathPenalty = ctx.is_death_penalty().bool_object().value;
		aiName = ctx.npc_ai().name_object().value;
		aiParams = ctx.npc_ai().ai_param();
		eventFlag = ctx.event_flag().int_object().value;
		unsowing = ctx.unsowing().bool_object().value;
		privateRespawnLog = ctx.private_respawn_log().bool_object().value;
		acquireExpRate = ctx.acquire_exp_rate().double_object().value;
		acquireSp = ctx.acquire_sp().double_object().value;
		acquireRp = ctx.acquire_rp().double_object().value;
		corpseMakeList = ctx.corpse_make_list().make_item_list().make_item();
		additionalMakeList = ctx.additional_make_list().make_item_list().make_item();
		additionalMakeMultiList = ctx.additional_make_multi_list().make_group_list();
		exItemDropList = ctx.ex_item_drop_list().make_group_list();
		vitalityItemDropList = ctx.vitality_item_drop_list().make_group_list();
		mpRewardValue = ctx.mp_reward().rewardValue.value;
		mpRewardType = ctx.mp_reward().mp_reward_type().value;
		mpRewardTicks = ctx.mp_reward().rewardAffectValue.value;
		mpRewardAffectType = ctx.mp_reward().mp_reward_affect_type().value;
		fakeClassId = ctx.fake_class_id().int_object().value;
		eventDrop = ctx.event_drop().int_object().value;
		exDrop = ctx.ex_drop().int_list().value;
		enableMoveAfterTalk = ctx.enable_move_after_talk().bool_object().value;
		broadcastCond = ctx.broadcast_cond().int_list().value;
	}
}