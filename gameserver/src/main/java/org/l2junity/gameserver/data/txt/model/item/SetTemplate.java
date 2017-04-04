package org.l2junity.gameserver.data.txt.model.item;

import lombok.Data;
import org.l2junity.gameserver.data.txt.gen.ItemDatasParser;

import java.util.List;

/**
 * This class designed to be immutable, thread safe set definition.
 * Instances of this class can be shared between multiple characters without any limitations.
 *
 * @author ANZO, Camelion
 * @since 07.01.16
 */
@Data
public final class SetTemplate {
	private final int setId;
	private final int slotChest;
	private List<Integer> slotLegs;
	private List<Integer> slotHead;
	private List<Integer> slotGloves;
	private List<Integer> slotFeet;
	private List<Integer> slotLhand;
	private final String slotAdditional;
	private final String setSkill;
	private final String setEffectSkill;
	private final String setAdditionalEffectSkill;
	private int setAdditional2Condition;
	private String setAdditional2EffectSkill;
	private final List<Integer> strInc;
	private final List<Integer> conInc;
	private final List<Integer> dexInc;
	private final List<Integer> intInc;
	private final List<Integer> menInc;
	private final List<Integer> witInc;

	public SetTemplate(ItemDatasParser.SetContext ctx) {
		setId = ctx.int_object().value;
		slotChest = ctx.slot_chest().int_object().value;
		if (ctx.slot_legs() != null) {
			slotLegs = ctx.slot_legs().int_list().value;
		}
		if (ctx.slot_head() != null) {
			slotHead = ctx.slot_head().int_list().value;
		}

		if (ctx.slot_gloves() != null) {
			slotGloves = ctx.slot_gloves().int_list().value;
		}

		if (ctx.slot_feet() != null) {
			slotFeet = ctx.slot_feet().int_list().value;
		}

		if (ctx.slot_lhand() != null) {
			slotLhand = ctx.slot_lhand().int_list().value;
		}

		slotAdditional = ctx.slot_additional().name_object().identifier_object().getText();
		setSkill = ctx.set_skill().name_object().identifier_object().getText();
		setEffectSkill = ctx.set_effect_skill().name_object().identifier_object().getText();
		setAdditionalEffectSkill = ctx.set_additional_effect_skill().name_object().identifier_object().getText();

		if (ctx.set_additional2_condition() != null) {
			setAdditional2Condition = Integer.valueOf(ctx.set_additional2_condition().int_object().getText());
		}

		if (ctx.set_additional2_effect_skill() != null) {
			setAdditional2EffectSkill = ctx.set_additional2_effect_skill().name_object().identifier_object().getText();
		}

		strInc = ctx.str_inc().int_list().value;
		conInc = ctx.con_inc().int_list().value;
		dexInc = ctx.dex_inc().int_list().value;
		intInc = ctx.int_inc().int_list().value;
		menInc = ctx.men_inc().int_list().value;
		witInc = ctx.wit_inc().int_list().value;
	}
}
