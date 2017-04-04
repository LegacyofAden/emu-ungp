package org.l2junity.gameserver.data.txt.model.item;

import lombok.Data;
import org.l2junity.gameserver.data.txt.gen.ItemDatasParser;
import org.l2junity.gameserver.data.txt.model.constants.*;
import org.l2junity.gameserver.data.txt.model.item.condition.Condition;

import java.util.List;

/**
 * This class designed to be immutable, thread safe item template.
 * Instances of this class can be shared between multiple characters without any limitations.
 *
 * @author ANZO, Camelion
 * @since 07.01.16
 */
@Data
public final class ItemTemplate {
	private final int itemId;
	private final ItemClass itemClass;
	private final String name;
	private final ItemClass itemType;
	private final List<SlotBitType> slotBitTypes;

	private final ItemTypes.ArmorType armorType;
	private final ItemTypes.EtcItemType etcItemType;
	private final ItemTypes.WeaponType weaponType;
	private final int delayShareGroup;
	private final List<String> itemMultiSkillList;
	private final int recipeId;
	private final int blessed;
	private final int weight;
	private final DefaultAction defaultAction;
	private final ConsumeType consumeType;
	private final int initialCount;
	private final int soulshotCount;
	private final int spiritshotCount;
	// two size or empty list
	private final List<Integer> reducedSoulshot;
	// not used in datas, always empty list
	private final List<Integer> reducedSpiritshot;
	// two size or empty list
	private final List<Integer> reducedMpConsume;
	private final int immediateEffect;
	private final int exImmediateEffect;
	private final int dropPeriod;
	// -1, or positive value
	private final int duration;
	private final int useSkillDistime;
	private final int period;
	private final int equipReuseDelay;
	// TODO: 13.01.16 unknown
	private final long price;
	private final long defaultPrice;
	// `none` or skill name
	private final String itemSkill;
	// `none` or skill name
	private final String criticalAttackSkill;
	private final String attackSkill;
	private final String magicSkill;
	private final int magicSkillUnknownValue;
	private final String itemSkillEnchantedFour;
	private final List<CapsuledItemData> capsuledItems;
	private final MaterialType materialType;
	private final CrystalType crystalType;
	private final int crystalCount;
	private final boolean isTrade;
	private final boolean isDrop;
	private final boolean isDestruct;
	private final boolean isPrivateStore;
	private final byte keepType;

	private final int physicalDamage;
	private final int randomDamage;
	private final int critical;
	// can't test, because can be any value
	private final double hitModify;
	private final int avoidModify;
	private final int dualFhitRate;
	private final int shieldDefense;
	private final int shieldDefenseRate;
	private final int attackRange;
	private final List<Integer> damageRange;
	private final int attackSpeed;

	private final int reuseDelay;
	private final int mpConsume;
	private final int magicalDamage;
	private final int durability;
	// always false
	private final boolean damaged;
	private final int physicalDefense;
	private final int magicalDefense;
	private final int mpBonus;
	// not used in datas, always EMPTY ARRAY
	private final List<String> category;
	private final int enchanted;
	private final AttributeAttack baseAttributeAttack;
	private final List<Integer> baseAttributeDefend;
	private final String html;
	// can't test
	private final boolean magicWeapon;
	private final int enchantEnable;
	private final boolean elementalEnable;
	private final List<String> unequipSkill;
	// can't test
	private boolean forNpc;
	private final List<String> itemEquipOption;
	private final List<Condition> useCondition;
	private final List<Condition> equipCondition;
	private final boolean isOlympiadCanUse;
	private final boolean canMove;
	private final boolean isPremium;
	/**
	 * calculated from {@link #slotBitTypes}
	 */
	private int slotBitTypeMask;

	public ItemTemplate(ItemDatasParser.ItemContext ctx) {
		itemId = ctx.item_id().value;
		itemClass = ctx.item_class().value;
		name = ctx.name_object().value;
		itemType = ctx.item_type().value;
		slotBitTypes = ctx.slot_bit_type_list().value;
		slotBitTypeMask = calcSlotBitTypeMask();
		armorType = ctx.armor_type_wrapper().value;
		weaponType = ctx.weapon_type_wrapper().value;
		etcItemType = ctx.etcitem_type_wrapper().value;
		delayShareGroup = ctx.delay_share_group().value;
		itemMultiSkillList = ctx.item_multi_skill_list().value;
		recipeId = ctx.recipe_id().value;
		blessed = ctx.blessed().value;
		weight = ctx.weight().value;
		defaultAction = ctx.default_action_wrapper().value;
		consumeType = ctx.consume_type_wrapper().value;
		initialCount = ctx.initial_count().value;
		soulshotCount = ctx.soulshot_count().value;
		spiritshotCount = ctx.spiritshot_count().value;
		reducedSoulshot = ctx.reduced_soulshot().value;
		reducedSpiritshot = ctx.reduced_spiritshot().value;
		reducedMpConsume = ctx.reduced_mp_consume().value;
		immediateEffect = ctx.immediate_effect().value;
		exImmediateEffect = ctx.ex_immediate_effect().value;
		dropPeriod = ctx.drop_period().value;
		duration = ctx.duration().value;
		useSkillDistime = ctx.use_skill_distime().value;
		period = ctx.period().value;
		equipReuseDelay = ctx.equip_reuse_delay().value;
		price = ctx.price().value;
		defaultPrice = ctx.default_price().value;
		itemSkill = ctx.item_skill().value;
		criticalAttackSkill = ctx.critical_attack_skill().value;
		attackSkill = ctx.attack_skill().value;
		magicSkill = ctx.magic_skill().value;
		magicSkillUnknownValue = ctx.magic_skill().unk;
		itemSkillEnchantedFour = ctx.item_skill_enchanted_four().value;
		capsuledItems = ctx.capsuled_items().value;
		materialType = ctx.material_type_wrapper().value;
		crystalType = ctx.crystal_type_wrapper().value;
		crystalCount = ctx.crystal_count().value;
		isTrade = ctx.is_trade().value;
		isDrop = ctx.is_drop().value;
		isDestruct = ctx.is_destruct().value;
		isPrivateStore = ctx.is_private_store().value;
		keepType = ctx.keep_type().value;
		physicalDamage = ctx.physical_damage().value;
		randomDamage = ctx.random_damage().value;
		critical = ctx.critical().value;
		hitModify = ctx.hit_modify().value;
		avoidModify = ctx.avoid_modify().value;
		dualFhitRate = ctx.dual_fhit_rate().value;
		shieldDefense = ctx.shield_defense().value;
		shieldDefenseRate = ctx.shield_defense_rate().value;
		attackRange = ctx.attack_range().value;
		damageRange = ctx.damage_range().value;
		attackSpeed = ctx.attack_speed().value;
		reuseDelay = ctx.reuse_delay().value;
		mpConsume = ctx.mp_consume().value;
		magicalDamage = ctx.magical_damage().value;
		durability = ctx.durability().value;
		damaged = ctx.damaged().value;
		physicalDefense = ctx.physical_defense().value;
		magicalDefense = ctx.magical_defense().value;
		mpBonus = ctx.mp_bonus().value;
		category = ctx.category().value;
		enchanted = ctx.enchanted().value;
		baseAttributeAttack = ctx.base_attribute_attack().value;
		baseAttributeDefend = ctx.base_attribute_defend().value;
		html = ctx.html().value;
		magicWeapon = ctx.magic_weapon().value;
		enchantEnable = ctx.enchant_enable().value;
		elementalEnable = ctx.elemental_enable().value;
		unequipSkill = ctx.unequip_skill().value;
		forNpc = ctx.for_npc().value;
		itemEquipOption = ctx.item_equip_option().value;
		isOlympiadCanUse = ctx.is_olympiad_can_use().value;
		canMove = ctx.can_move().value;
		isPremium = ctx.is_premium().value;
		useCondition = ctx.use_condition().value;
		equipCondition = ctx.equip_condition().value;
	}

	private int calcSlotBitTypeMask() {
		return slotBitTypes.size() == 1 ? slotBitTypes.get(0).mask()
				: slotBitTypes
				.stream()
				.mapToInt(SlotBitType::mask)
				.reduce((a, b) -> a | b).getAsInt();
	}
}