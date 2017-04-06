/*
 * Copyright (C) 2004-2015 L2J DataPack
 * 
 * This file is part of L2J DataPack.
 * 
 * L2J DataPack is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J DataPack is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.l2junity.gameserver.model.effects.effecttypes.instant;

import org.l2junity.gameserver.data.xml.impl.NpcData;
import org.l2junity.gameserver.data.xml.impl.PetDataTable;
import org.l2junity.gameserver.model.PetData;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.PetInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.actor.templates.NpcTemplate;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.holders.PetItemHolder;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.packets.s2c.PetItemList;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Summon Pet effect implementation.
 *
 * @author UnAfraid
 */
public final class InstantSummonPet extends AbstractEffect {
	private static final Logger LOGGER = LoggerFactory.getLogger(InstantSummonPet.class);

	public InstantSummonPet(StatsSet params) {
	}

	@Override
	public void instant(Creature caster, WorldObject target, Skill skill, ItemInstance item) {
		final Player casterPlayer = caster.asPlayer();
		if (casterPlayer == null) {
			return;
		}

		if (casterPlayer.isAlikeDead()) {
			return;
		}

		if (casterPlayer.hasPet() || casterPlayer.isMounted()) {
			casterPlayer.sendPacket(SystemMessageId.YOU_ALREADY_HAVE_A_PET);
			return;
		}

		final PetItemHolder holder = casterPlayer.removeScript(PetItemHolder.class);
		if (holder == null) {
			LOGGER.warn("Summoning pet without attaching PetItemHandler!", new Throwable());
			return;
		}

		final ItemInstance collar = holder.getItem();
		if (casterPlayer.getInventory().getItemByObjectId(collar.getObjectId()) != collar) {
			LOGGER.warn("Player: {} is trying to summon pet from item that he doesn't owns.", casterPlayer);
			return;
		}

		final PetData petData = PetDataTable.getInstance().getPetDataByItemId(collar.getId());
		if ((petData == null) || (petData.getNpcId() == -1)) {
			return;
		}

		final NpcTemplate npcTemplate = NpcData.getInstance().getTemplate(petData.getNpcId());
		final PetInstance pet = PetInstance.spawnPet(npcTemplate, casterPlayer, collar);

		pet.setShowSummonAnimation(true);
		if (!pet.isRespawned()) {
			pet.setCurrentHp(pet.getMaxHp());
			pet.setCurrentMp(pet.getMaxMp());
			pet.getStat().setExp(pet.getExpForThisLevel());
			pet.setCurrentFed(pet.getMaxFed());
		}

		pet.setRunning();

		if (!pet.isRespawned()) {
			pet.storeMe();
		}

		collar.setEnchantLevel(pet.getLevel());
		casterPlayer.setPet(pet);
		pet.spawnMe(casterPlayer.getX() + 50, casterPlayer.getY() + 100, casterPlayer.getZ());
		pet.startFeed();
		pet.setFollowStatus(true);
		pet.getOwner().sendPacket(new PetItemList(pet.getInventory().getItems()));
		pet.broadcastStatusUpdate();
	}
}
