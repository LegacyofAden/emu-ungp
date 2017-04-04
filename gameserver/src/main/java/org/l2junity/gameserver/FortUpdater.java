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
package org.l2junity.gameserver;

import org.l2junity.core.configs.FeatureConfig;
import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.entity.Fort;
import org.l2junity.gameserver.model.itemcontainer.Inventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class managing periodical events with castle
 *
 * @author Vice - 2008
 */
public class FortUpdater implements Runnable {
	protected static Logger LOGGER = LoggerFactory.getLogger(FortUpdater.class);
	private final Clan _clan;
	private final Fort _fort;
	private int _runCount;
	private final UpdaterType _updaterType;

	public enum UpdaterType {
		MAX_OWN_TIME, // gives fort back to NPC clan
		PERIODIC_UPDATE // raise blood oath/supply level
	}

	public FortUpdater(Fort fort, Clan clan, int runCount, UpdaterType ut) {
		_fort = fort;
		_clan = clan;
		_runCount = runCount;
		_updaterType = ut;
	}

	@Override
	public void run() {
		try {
			switch (_updaterType) {
				case PERIODIC_UPDATE:
					_runCount++;
					if ((_fort.getOwnerClan() == null) || (_fort.getOwnerClan() != _clan)) {
						return;
					}

					_fort.getOwnerClan().increaseBloodOathCount();

					if (_fort.getFortState() == 2) {
						if (_clan.getWarehouse().getAdena() >= FeatureConfig.FS_FEE_FOR_CASTLE) {
							_clan.getWarehouse().destroyItemByItemId("FS_fee_for_Castle", Inventory.ADENA_ID, FeatureConfig.FS_FEE_FOR_CASTLE, null, null);
							_fort.getContractedCastle().addToTreasuryNoTax(FeatureConfig.FS_FEE_FOR_CASTLE);
							_fort.raiseSupplyLvL();
						} else {
							_fort.setFortState(1, 0);
						}
					}
					_fort.saveFortVariables();
					break;
				case MAX_OWN_TIME:
					if ((_fort.getOwnerClan() == null) || (_fort.getOwnerClan() != _clan)) {
						return;
					}
					if (_fort.getOwnedTime() > (FeatureConfig.FS_MAX_OWN_TIME * 3600)) {
						_fort.removeOwner(true);
						_fort.setFortState(0, 0);
					}
					break;
			}
		} catch (Exception e) {
			LOGGER.warn("", e);
		}
	}

	public int getRunCount() {
		return _runCount;
	}
}