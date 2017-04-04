package org.l2junity.gameserver.model.onedayreward;

import org.l2junity.gameserver.model.OneDayRewardDataHolder;
import org.l2junity.gameserver.model.onedayreward.handlers.*;

import java.util.function.Function;

/**
 * @author ANZO
 * @since 03.04.2017
 */
public enum OneDayRewardType {
	none(null),
	level(LevelOneDayRewardHandler::new),
	// loginAllWeek(LoginAllWeekOneDayRewardHandler::new),
	// loginAllMonth(LoginAllWeekOneDayRewardHandler::new),
	quest(QuestOneDayRewardHandler::new),
	olympiad(OlympiadOneDayRewardHandler::new),
	siege(SiegeOneDayRewardHandler::new),
	ceremonyofchaos(CeremonyOfChaosOneDayRewardHandler::new),
	boss(BossOneDayRewardHandler::new),
	fishing(FishingOneDayRewardHandler::new);

	Function<OneDayRewardDataHolder, AbstractOneDayRewardHandler> function;

	OneDayRewardType(Function<OneDayRewardDataHolder, AbstractOneDayRewardHandler> function) {
		this.function = function;
	}

	public AbstractOneDayRewardHandler getNew(OneDayRewardDataHolder holder) {
		return function.apply(holder);
	}
}
