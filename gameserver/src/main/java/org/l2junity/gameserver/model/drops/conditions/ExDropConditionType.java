package org.l2junity.gameserver.model.drops.conditions;

import org.l2junity.gameserver.model.StatsSet;

import java.util.function.Function;

/**
 * @author ANZO
 * @since 02.04.2017
 */
public enum ExDropConditionType {
	CategoryType(CategoryTypeExDropCondition::new),
	NpcLevel(NpcLevelExDropCondition::new),
	PlayerLevel(PlayerLevelExDropCondition::new);

	Function<StatsSet, IExDropCondition> function;

	ExDropConditionType(Function<StatsSet, IExDropCondition> function) {
		this.function = function;
	}

	public IExDropCondition getNew(StatsSet set) {
		return function.apply(set);
	}
}