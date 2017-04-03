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
package handlers.playeractions;

import org.l2junity.gameserver.handler.IPlayerActionHandler;
import org.l2junity.gameserver.handler.PlayerActionHandler;
import org.l2junity.gameserver.model.ActionDataHolder;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.instance.L2PetInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.holders.SkillHolder;
import org.l2junity.gameserver.model.skills.CommonSkill;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;

import java.util.Optional;
import java.util.OptionalInt;

/**
 * Pet skill use player action handler.
 *
 * @author Nik
 */
public final class PetSkillUse implements IPlayerActionHandler {
	@Override
	public void useAction(Player activeChar, ActionDataHolder data, boolean ctrlPressed, boolean shiftPressed) {
		if (activeChar.getTarget() == null) {
			return;
		}

		final L2PetInstance pet = activeChar.getPet();
		if (pet == null) {
			activeChar.sendPacket(SystemMessageId.YOU_DO_NOT_HAVE_A_PET);
		} else if (pet.isUncontrollable()) {
			activeChar.sendPacket(SystemMessageId.WHEN_YOUR_PET_S_HUNGER_GAUGE_IS_AT_0_YOU_CANNOT_USE_YOUR_PET);
		} else if (pet.isBetrayed()) {
			activeChar.sendPacket(SystemMessageId.YOUR_PET_SERVITOR_IS_UNRESPONSIVE_AND_WILL_NOT_OBEY_ANY_ORDERS);
		} else if ((pet.getLevel() - activeChar.getLevel()) > 20) {
			activeChar.sendPacket(SystemMessageId.YOUR_PET_IS_TOO_HIGH_LEVEL_TO_CONTROL);
		} else {
			final OptionalInt step = getStep(pet);
			final Optional<SkillHolder> holder = step.isPresent() ? getSkill(pet, step.getAsInt(), data.getOptionId()) : pet.getTemplate().getParameters().getSkillHolder(data.getOptionId());
			if (holder.isPresent()) {
				pet.setTarget(activeChar.getTarget());
				pet.useMagic(holder.get().getSkill(), pet.getTarget(), null, ctrlPressed, shiftPressed);
			}

			if (data.getOptionId() == CommonSkill.PET_SWITCH_STANCE.getId()) {
				pet.switchMode();
			}
		}
	}

	private static OptionalInt getStep(L2PetInstance pet) {
		OptionalInt step = OptionalInt.empty();
		for (int i = 0; i < 7; i++) {
			final int level = pet.getTemplate().getParameters().getInt("lv_step" + i, 0);
			if (pet.getLevel() >= level) {
				step = OptionalInt.of(i);
			}
		}
		return step;
	}

	private static Optional<SkillHolder> getSkill(L2PetInstance pet, int step, int skillId) {
		final StatsSet params = pet.getTemplate().getParameters();
		Optional<SkillHolder> skill = Optional.empty();

		for (int i = 0; i < 8; i++) {
			final SkillHolder holder = params.getSkillHolder("step" + step + "_skill0" + i);
			if ((holder != null) && (holder.getSkillId() == skillId)) {
				skill = Optional.of(holder);
				break;
			}
		}

		return skill;
	}

	public static void main(String[] args) {
		PlayerActionHandler.getInstance().registerHandler(new PetSkillUse());
	}
}
