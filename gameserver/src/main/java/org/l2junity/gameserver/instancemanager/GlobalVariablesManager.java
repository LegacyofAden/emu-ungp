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
package org.l2junity.gameserver.instancemanager;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.sql.DatabaseFactory;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.model.variables.AbstractVariables;

import java.sql.*;
import java.util.Map.Entry;

/**
 * Global Variables Manager.
 *
 * @author xban1x
 */
@Slf4j
@StartupComponent("Service")
public final class GlobalVariablesManager extends AbstractVariables {
	@Getter(lazy = true)
	private static final GlobalVariablesManager instance = new GlobalVariablesManager();

	// SQL Queries.
	private static final String SELECT_QUERY = "SELECT * FROM global_variables";
	private static final String DELETE_QUERY = "DELETE FROM global_variables";
	private static final String INSERT_QUERY = "INSERT INTO global_variables (var, value) VALUES (?, ?)";

	// Variables
	public static final String RECORD_ONLINE_PLAYERS_VAR = "RecordOnlinePlayers";
	public static final String VENOM_STATUS_VAR = "VenomStatus";
	public static final String CORE_ATTACKED_VAR = "Core_Attacked";
	public static final String GTM_GAME_TIME_VAR = "gtm.gameTime";
	public static final String SOD_STATE_VAR = "SoDState";
	public static final String SOD_TIAT_KILLED_VAR = "SoDTiatKilled";
	public static final String SOD_LSC_DATE_VAR = "SoDLSCDate";
	public static final String SEED_NEXT_STATUS_CHANGE_VAR = "SeedNextStatusChange";
	public static final String SEED_BUFFS_LIST_VAR = "SeedBuffsList";
	public static final String SAILREN_RESPAWN_VAR = "SailrenRespawn";
	public static final String QUEEN_SHYEED_RESPAWN_VAR = "QueenShyeedRespawn";

	protected GlobalVariablesManager() {
		restoreMe();
	}

	@Override
	public boolean restoreMe() {
		// Restore previous variables.
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 Statement st = con.createStatement();
			 ResultSet rset = st.executeQuery(SELECT_QUERY)) {
			while (rset.next()) {
				set(rset.getString("var"), rset.getString("value"));
			}
		} catch (SQLException e) {
			log.warn("Couldn't restore global variables", e);
			return false;
		} finally {
			compareAndSetChanges(true, false);
		}
		log.info("Loaded {} variables.", getSet().size());
		return true;
	}

	@Override
	public boolean storeMe() {
		// No changes, nothing to store.
		if (!hasChanges()) {
			return false;
		}

		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 Statement del = con.createStatement();
			 PreparedStatement st = con.prepareStatement(INSERT_QUERY)) {
			// Clear previous entries.
			del.execute(DELETE_QUERY);

			// Insert all variables.
			for (Entry<String, Object> entry : getSet().entrySet()) {
				st.setString(1, entry.getKey());
				st.setString(2, String.valueOf(entry.getValue()));
				st.addBatch();
			}
			st.executeBatch();
		} catch (SQLException e) {
			log.warn("Couldn't save global variables to database.", e);
			return false;
		} finally {
			compareAndSetChanges(true, false);
		}
		log.info("Stored {} variables.", getSet().size());
		return true;
	}

	@Override
	public boolean deleteMe() {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 Statement del = con.createStatement()) {
			del.execute(DELETE_QUERY);
		} catch (Exception e) {
			log.warn("Couldn't delete global variables to database.", e);
			return false;
		}
		return true;
	}
}