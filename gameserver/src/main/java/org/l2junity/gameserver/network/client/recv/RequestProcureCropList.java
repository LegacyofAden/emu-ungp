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
package org.l2junity.gameserver.network.client.recv;

import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.datatables.ItemTable;
import org.l2junity.gameserver.instancemanager.CastleManorManager;
import org.l2junity.gameserver.model.CropProcure;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.L2MerchantInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.holders.UniqueItemHolder;
import org.l2junity.gameserver.model.items.L2Item;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.client.L2GameClient;
import org.l2junity.gameserver.network.client.send.ActionFailed;
import org.l2junity.gameserver.network.client.send.SystemMessage;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;
import org.l2junity.network.PacketReader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author l3x
 */
public class RequestProcureCropList implements IClientIncomingPacket {
	private static final int BATCH_LENGTH = 20; // length of the one item

	private List<CropHolder> _items = null;

	@Override
	public boolean read(L2GameClient client, PacketReader packet) {
		final int count = packet.readD();
		if ((count <= 0) || (count > PlayerConfig.MAX_ITEM_IN_PACKET) || ((count * BATCH_LENGTH) != packet.getReadableBytes())) {
			return false;
		}

		_items = new ArrayList<>(count);
		for (int i = 0; i < count; i++) {
			final int objId = packet.readD();
			final int itemId = packet.readD();
			final int manorId = packet.readD();
			final long cnt = packet.readQ();
			if ((objId < 1) || (itemId < 1) || (manorId < 0) || (cnt < 0)) {
				_items = null;
				return false;
			}
			_items.add(new CropHolder(objId, itemId, cnt, manorId));
		}
		return true;
	}

	@Override
	public void run(L2GameClient client) {
		if (_items == null) {
			return;
		}

		final Player player = client.getActiveChar();
		if (player == null) {
			return;
		}

		final CastleManorManager manor = CastleManorManager.getInstance();
		if (manor.isUnderMaintenance()) {
			client.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		final Npc manager = player.getLastFolkNPC();
		if (!(manager instanceof L2MerchantInstance) || !manager.canInteract(player)) {
			client.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		final int castleId = manager.getCastle().getResidenceId();
		if (manager.getParameters().getInt("manor_id", -1) != castleId) {
			client.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		int slots = 0, weight = 0;
		for (CropHolder i : _items) {
			final ItemInstance item = player.getInventory().getItemByObjectId(i.getObjectId());
			if ((item == null) || (item.getCount() < i.getCount()) || (item.getId() != i.getId())) {
				client.sendPacket(ActionFailed.STATIC_PACKET);
				return;
			}

			final CropProcure cp = i.getCropProcure();
			if ((cp == null) || (cp.getAmount() < i.getCount())) {
				client.sendPacket(ActionFailed.STATIC_PACKET);
				return;
			}

			final L2Item template = ItemTable.getInstance().getTemplate(i.getRewardId());
			weight += (i.getCount() * template.getWeight());

			if (!template.isStackable()) {
				slots += i.getCount();
			} else if (player.getInventory().getItemByItemId(i.getRewardId()) == null) {
				slots++;
			}
		}

		if (!player.getInventory().validateWeight(weight)) {
			player.sendPacket(SystemMessageId.YOU_HAVE_EXCEEDED_THE_WEIGHT_LIMIT);
			return;
		} else if (!player.getInventory().validateCapacity(slots)) {
			player.sendPacket(SystemMessageId.YOUR_INVENTORY_IS_FULL);
			return;
		}

		// Used when Config.ALT_MANOR_SAVE_ALL_ACTIONS == true
		final int updateListSize = GeneralConfig.ALT_MANOR_SAVE_ALL_ACTIONS ? _items.size() : 0;
		final List<CropProcure> updateList = new ArrayList<>(updateListSize);

		// Proceed the purchase
		for (CropHolder i : _items) {
			final long rewardPrice = ItemTable.getInstance().getTemplate(i.getRewardId()).getReferencePrice();
			if (rewardPrice == 0) {
				continue;
			}

			final long rewardItemCount = i.getPrice() / rewardPrice;
			if (rewardItemCount < 1) {
				final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.FAILED_IN_TRADING_S2_OF_S1_CROPS);
				sm.addItemName(i.getId());
				sm.addLong(i.getCount());
				player.sendPacket(sm);
				continue;
			}

			// Fee for selling to other manors
			final long fee = (castleId == i.getManorId()) ? 0 : ((long) (i.getPrice() * 0.05));
			if ((fee != 0) && (player.getAdena() < fee)) {
				SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.FAILED_IN_TRADING_S2_OF_S1_CROPS);
				sm.addItemName(i.getId());
				sm.addLong(i.getCount());
				player.sendPacket(sm);

				sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
				player.sendPacket(sm);
				continue;
			}

			final CropProcure cp = i.getCropProcure();
			if (!cp.decreaseAmount(i.getCount()) || ((fee > 0) && !player.reduceAdena("Manor", fee, manager, true)) || !player.destroyItem("Manor", i.getObjectId(), i.getCount(), manager, true)) {
				continue;
			}
			player.addItem("Manor", i.getRewardId(), rewardItemCount, manager, true);

			if (GeneralConfig.ALT_MANOR_SAVE_ALL_ACTIONS) {
				updateList.add(cp);
			}
		}

		if (GeneralConfig.ALT_MANOR_SAVE_ALL_ACTIONS) {
			manor.updateCurrentProcure(castleId, updateList);
		}
	}

	private final class CropHolder extends UniqueItemHolder {
		private static final long serialVersionUID = -8408582691243260781L;
		private final int _manorId;
		private CropProcure _cp;
		private int _rewardId = 0;

		public CropHolder(int objectId, int id, long count, int manorId) {
			super(id, objectId, count);
			_manorId = manorId;
		}

		public final int getManorId() {
			return _manorId;
		}

		public final long getPrice() {
			return getCount() * _cp.getPrice();
		}

		public final CropProcure getCropProcure() {
			if (_cp == null) {
				_cp = CastleManorManager.getInstance().getCropProcure(_manorId, getId(), false);
			}
			return _cp;
		}

		public final int getRewardId() {
			if (_rewardId == 0) {
				_rewardId = CastleManorManager.getInstance().getSeedByCrop(_cp.getId()).getReward(_cp.getReward());
			}
			return _rewardId;
		}
	}
}
