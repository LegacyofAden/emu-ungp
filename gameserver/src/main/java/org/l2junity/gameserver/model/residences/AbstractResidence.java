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
package org.l2junity.gameserver.model.residences;

import org.l2junity.commons.sql.DatabaseFactory;
import org.l2junity.gameserver.data.xml.impl.SkillTreesData;
import org.l2junity.gameserver.model.SkillLearn;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.events.ListenersContainer;
import org.l2junity.gameserver.model.holders.SkillHolder;
import org.l2junity.gameserver.model.interfaces.INamable;
import org.l2junity.gameserver.model.zone.type.ResidenceZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xban1x
 */
public abstract class AbstractResidence extends ListenersContainer implements INamable {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	private final int _residenceId;
	private String _name;

	private ResidenceZone _zone = null;
	private final Map<Integer, ResidenceFunction> _functions = new ConcurrentHashMap<>();
	private final List<SkillHolder> _residentialSkills = new ArrayList<>();

	public AbstractResidence(int residenceId) {
		_residenceId = residenceId;
		initResidentialSkills();
	}

	protected abstract void load();

	protected abstract void initResidenceZone();

	public abstract int getOwnerId();

	protected void initResidentialSkills() {
		final List<SkillLearn> residentialSkills = SkillTreesData.getInstance().getAvailableResidentialSkills(getResidenceId());
		for (SkillLearn s : residentialSkills) {
			_residentialSkills.add(new SkillHolder(s.getSkillId(), s.getSkillLevel()));
		}
	}

	public final int getResidenceId() {
		return _residenceId;
	}

	@Override
	public final String getName() {
		return _name;
	}

	// TODO: Remove it later when both castles and forts are loaded from same table.
	public final void setName(String name) {
		_name = name;
	}

	public ResidenceZone getResidenceZone() {
		return _zone;
	}

	protected void setResidenceZone(ResidenceZone zone) {
		_zone = zone;
	}

	public final List<SkillHolder> getResidentialSkills() {
		return _residentialSkills;
	}

	public void giveResidentialSkills(Player player) {
		if ((_residentialSkills != null) && !_residentialSkills.isEmpty()) {
			for (SkillHolder sh : _residentialSkills) {
				player.addSkill(sh.getSkill(), false);
			}
		}
	}

	public void removeResidentialSkills(Player player) {
		if ((_residentialSkills != null) && !_residentialSkills.isEmpty()) {
			for (SkillHolder sh : _residentialSkills) {
				player.removeSkill(sh.getSkill(), false);
			}
		}
	}

	/**
	 * Initializes all available functions for the current residence
	 */
	protected void initFunctions() {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement ps = con.prepareStatement("SELECT * FROM residence_functions WHERE residenceId = ?")) {
			ps.setInt(1, getResidenceId());
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					final int id = rs.getInt("id");
					final int level = rs.getInt("level");
					final long expiration = rs.getLong("expiration");
					final ResidenceFunction func = new ResidenceFunction(id, level, expiration, this);
					if ((expiration <= System.currentTimeMillis()) && !func.reactivate()) {
						removeFunction(func);
						continue;
					}
					_functions.put(id, func);
				}
			}
		} catch (Exception e) {
			LOGGER.warn("Failed to initialize functions for residence: {}", getResidenceId(), e);
		}
	}

	public void addFunction(int id, int level) {
		addFunction(new ResidenceFunction(id, level, this));
	}

	/**
	 * Adds new function and removes old if matches same id
	 *
	 * @param func
	 */
	public void addFunction(ResidenceFunction func) {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement ps = con.prepareStatement("INSERT INTO residence_functions (id, level, expiration, residenceId) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE level = ?, expiration = ?")) {
			ps.setInt(1, func.getId());
			ps.setInt(2, func.getLevel());
			ps.setLong(3, func.getExpiration());
			ps.setInt(4, getResidenceId());

			ps.setInt(5, func.getLevel());
			ps.setLong(6, func.getExpiration());
			ps.execute();
		} catch (Exception e) {
			LOGGER.warn("Failed to add function: {} for residence: {}", func.getId(), getResidenceId(), e);
		} finally {
			if (_functions.containsKey(func.getId())) {
				removeFunction(_functions.get(func.getId()));
			}
			_functions.put(func.getId(), func);
		}
	}

	/**
	 * Removes the specified function
	 *
	 * @param func
	 */
	public void removeFunction(ResidenceFunction func) {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement ps = con.prepareStatement("DELETE FROM residence_functions WHERE residenceId = ? and id = ?")) {
			ps.setInt(1, getResidenceId());
			ps.setInt(2, func.getId());
			ps.execute();
		} catch (Exception e) {
			LOGGER.warn("Failed to remove function: {} residence: {}", func.getId(), getResidenceId(), e);
		} finally {
			func.cancelExpiration();
			_functions.remove(func.getId());
		}
	}

	/**
	 * Removes all functions
	 */
	public void removeFunctions() {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement ps = con.prepareStatement("DELETE FROM residence_functions WHERE residenceId = ?")) {
			ps.setInt(1, getResidenceId());
			ps.execute();
		} catch (Exception e) {
			LOGGER.warn("Failed to remove functions for residence: {}", getResidenceId(), e);
		} finally {
			_functions.values().forEach(ResidenceFunction::cancelExpiration);
			_functions.clear();
		}
	}

	/**
	 * @param type
	 * @return {@code true} if function is available, {@code false} otherwise
	 */
	public boolean hasFunction(ResidenceFunctionType type) {
		return _functions.values().stream().map(ResidenceFunction::getTemplate).anyMatch(func -> func.getType() == type);
	}

	/**
	 * @param type
	 * @return the function template by type, null if not available
	 */
	public ResidenceFunction getFunction(ResidenceFunctionType type) {
		return _functions.values().stream().filter(func -> func.getType() == type).findFirst().orElse(null);
	}

	/**
	 * @param id
	 * @param level
	 * @return the function by id and level, null if not available
	 */
	public ResidenceFunction getFunction(int id, int level) {
		return _functions.values().stream().filter(func -> (func.getId() == id) && (func.getLevel() == level)).findFirst().orElse(null);
	}

	/**
	 * @param id
	 * @return the function by id, null if not available
	 */
	public ResidenceFunction getFunction(int id) {
		return _functions.values().stream().filter(func -> (func.getId() == id)).findFirst().orElse(null);
	}

	/**
	 * @param type
	 * @return level of function, 0 if not available
	 */
	public int getFunctionLevel(ResidenceFunctionType type) {
		final ResidenceFunction func = getFunction(type);
		return func != null ? func.getLevel() : 0;
	}

	/**
	 * @param type
	 * @return the expiration of function by type, -1 if not available
	 */
	public long getFunctionExpiration(ResidenceFunctionType type) {
		final ResidenceFunction function = _functions.values().stream().filter(func -> func.getTemplate().getType() == type).findFirst().orElse(null);
		return function != null ? function.getExpiration() : -1;
	}

	/**
	 * @return all avaible functions
	 */
	public Collection<ResidenceFunction> getFunctions() {
		return _functions.values();
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof AbstractResidence) && (((AbstractResidence) obj).getResidenceId() == getResidenceId());
	}

	@Override
	public String toString() {
		return getName() + " (" + getResidenceId() + ")";
	}
}
