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
package org.l2junity.gameserver.util;

import org.l2junity.commons.sql.DatabaseFactory;
import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.gameserver.data.xml.impl.NpcData;
import org.l2junity.gameserver.data.xml.impl.PetDataTable;
import org.l2junity.gameserver.model.PetData;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.Summon;
import org.l2junity.gameserver.model.actor.instance.PetInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.actor.templates.NpcTemplate;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.packets.s2c.InventoryUpdate;
import org.l2junity.gameserver.network.packets.s2c.MagicSkillLaunched;
import org.l2junity.gameserver.network.packets.s2c.MagicSkillUse;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.concurrent.TimeUnit;

/**
 * UnAfraid: TODO: MOVE IT TO DP AI
 */
public final class Evolve {
	protected static final Logger _log = LoggerFactory.getLogger(Evolve.class);

	public static final boolean doEvolve(Player player, Npc npc, int itemIdtake, int itemIdgive, int petminlvl) {
		if ((itemIdtake == 0) || (itemIdgive == 0) || (petminlvl == 0)) {
			return false;
		}

		final Summon pet = player.getPet();
		if (pet == null) {
			return false;
		}

		final PetInstance currentPet = (PetInstance) pet;
		if (currentPet.isAlikeDead()) {
			Util.handleIllegalPlayerAction(player, "Player " + player.getName() + " tried to use death pet exploit!", GeneralConfig.DEFAULT_PUNISH);
			return false;
		}

		ItemInstance item = null;
		long petexp = currentPet.getStat().getExp();
		String oldname = currentPet.getName();
		double oldX = currentPet.getX();
		double oldY = currentPet.getY();
		double oldZ = currentPet.getZ();

		PetData oldData = PetDataTable.getInstance().getPetDataByItemId(itemIdtake);

		if (oldData == null) {
			return false;
		}

		int oldnpcID = oldData.getNpcId();

		if ((currentPet.getStat().getLevel() < petminlvl) || (currentPet.getId() != oldnpcID)) {
			return false;
		}

		PetData petData = PetDataTable.getInstance().getPetDataByItemId(itemIdgive);

		if (petData == null) {
			return false;
		}

		int npcID = petData.getNpcId();

		if (npcID == 0) {
			return false;
		}

		NpcTemplate npcTemplate = NpcData.getInstance().getTemplate(npcID);

		currentPet.unSummon(player);

		// deleting old pet item
		currentPet.destroyControlItem(player, true);

		item = player.getInventory().addItem("Evolve", itemIdgive, 1, player, npc);

		// Summoning new pet
		PetInstance petSummon = PetInstance.spawnPet(npcTemplate, player, item);

		if (petSummon == null) {
			return false;
		}

		// Fix for non-linear baby pet exp
		long _minimumexp = petSummon.getStat().getExpForLevel(petminlvl);
		if (petexp < _minimumexp) {
			petexp = _minimumexp;
		}

		petSummon.getStat().addExp(petexp);
		petSummon.setCurrentHp(petSummon.getMaxHp());
		petSummon.setCurrentMp(petSummon.getMaxMp());
		petSummon.setCurrentFed(petSummon.getMaxFed());
		petSummon.setTitle(player.getName());
		petSummon.setName(oldname);
		petSummon.setRunning();
		petSummon.storeMe();

		player.setPet(petSummon);

		player.sendPacket(new MagicSkillUse(npc, 2046, 1, 1000, 600000));
		player.sendPacket(SystemMessageId.SUMMONING_YOUR_PET);
		petSummon.spawnMe(oldX, oldY, oldZ);
		petSummon.startFeed();
		item.setEnchantLevel(petSummon.getLevel());

		ThreadPool.getInstance().scheduleGeneral(new EvolveFinalizer(player, petSummon), 900, TimeUnit.MILLISECONDS);

		if (petSummon.getCurrentFed() <= 0) {
			ThreadPool.getInstance().scheduleGeneral(new EvolveFeedWait(player, petSummon), 60000, TimeUnit.MILLISECONDS);
		} else {
			petSummon.startFeed();
		}

		return true;
	}

	public static final boolean doRestore(Player player, Npc npc, int itemIdtake, int itemIdgive, int petminlvl) {
		if ((itemIdtake == 0) || (itemIdgive == 0) || (petminlvl == 0)) {
			return false;
		}

		ItemInstance item = player.getInventory().getItemByItemId(itemIdtake);
		if (item == null) {
			return false;
		}

		int oldpetlvl = item.getEnchantLevel();
		if (oldpetlvl < petminlvl) {
			oldpetlvl = petminlvl;
		}

		PetData oldData = PetDataTable.getInstance().getPetDataByItemId(itemIdtake);
		if (oldData == null) {
			return false;
		}

		PetData petData = PetDataTable.getInstance().getPetDataByItemId(itemIdgive);
		if (petData == null) {
			return false;
		}

		int npcId = petData.getNpcId();
		if (npcId == 0) {
			return false;
		}

		NpcTemplate npcTemplate = NpcData.getInstance().getTemplate(npcId);

		// deleting old pet item
		ItemInstance removedItem = player.getInventory().destroyItem("PetRestore", item, player, npc);
		SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_DISAPPEARED);
		sm.addItemName(removedItem);
		player.sendPacket(sm);

		// Give new pet item
		ItemInstance addedItem = player.getInventory().addItem("PetRestore", itemIdgive, 1, player, npc);

		// Summoning new pet
		PetInstance petSummon = PetInstance.spawnPet(npcTemplate, player, addedItem);
		if (petSummon == null) {
			return false;
		}

		long _maxexp = petSummon.getStat().getExpForLevel(oldpetlvl);

		petSummon.getStat().addExp(_maxexp);
		petSummon.setCurrentHp(petSummon.getMaxHp());
		petSummon.setCurrentMp(petSummon.getMaxMp());
		petSummon.setCurrentFed(petSummon.getMaxFed());
		petSummon.setTitle(player.getName());
		petSummon.setRunning();
		petSummon.storeMe();

		player.setPet(petSummon);

		player.sendPacket(new MagicSkillUse(npc, 2046, 1, 1000, 600000));
		player.sendPacket(SystemMessageId.SUMMONING_YOUR_PET);
		petSummon.spawnMe(player.getX(), player.getY(), player.getZ());
		petSummon.startFeed();
		addedItem.setEnchantLevel(petSummon.getLevel());

		// Inventory update
		InventoryUpdate iu = new InventoryUpdate();
		iu.addRemovedItem(removedItem);
		player.sendInventoryUpdate(iu);

		player.broadcastUserInfo();

		ThreadPool.getInstance().scheduleGeneral(new EvolveFinalizer(player, petSummon), 900, TimeUnit.MILLISECONDS);

		if (petSummon.getCurrentFed() <= 0) {
			ThreadPool.getInstance().scheduleGeneral(new EvolveFeedWait(player, petSummon), 60000, TimeUnit.MILLISECONDS);
		} else {
			petSummon.startFeed();
		}

		// pet control item no longer exists, delete the pet from the db
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement ps = con.prepareStatement("DELETE FROM pets WHERE item_obj_id=?")) {
			ps.setInt(1, removedItem.getObjectId());
			ps.execute();
		} catch (Exception e) {
		}
		return true;
	}

	static final class EvolveFeedWait implements Runnable {
		private final Player _activeChar;
		private final PetInstance _petSummon;

		EvolveFeedWait(Player activeChar, PetInstance petSummon) {
			_activeChar = activeChar;
			_petSummon = petSummon;
		}

		@Override
		public void run() {
			try {
				if (_petSummon.getCurrentFed() <= 0) {
					_petSummon.unSummon(_activeChar);
				} else {
					_petSummon.startFeed();
				}
			} catch (Exception e) {
				_log.warn("", e);
			}
		}
	}

	static final class EvolveFinalizer implements Runnable {
		private final Player _activeChar;
		private final PetInstance _petSummon;

		EvolveFinalizer(Player activeChar, PetInstance petSummon) {
			_activeChar = activeChar;
			_petSummon = petSummon;
		}

		@Override
		public void run() {
			try {
				_activeChar.sendPacket(new MagicSkillLaunched(_activeChar, 2046, 1));
				_petSummon.setFollowStatus(true);
				_petSummon.setShowSummonAnimation(false);
			} catch (Throwable e) {
				_log.warn("", e);
			}
		}
	}
}
