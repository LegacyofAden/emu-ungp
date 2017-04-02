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
package org.l2junity.gameserver.model.base;

import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.data.xml.impl.ExperienceData;

/**
 * Character Sub-Class Definition <BR>
 * Used to store key information about a character's sub-class.
 *
 * @author Tempy
 */
public final class SubClass {
	private static final byte _maxLevel = PlayerConfig.MAX_SUBCLASS_LEVEL < ExperienceData.getInstance().getMaxLevel() ? PlayerConfig.MAX_SUBCLASS_LEVEL : (byte) (ExperienceData.getInstance().getMaxLevel() - 1);

	private ClassId _class;
	private long _exp = ExperienceData.getInstance().getExpForLevel(PlayerConfig.BASE_SUBCLASS_LEVEL);
	private long _sp = 0;
	private byte _level = PlayerConfig.BASE_SUBCLASS_LEVEL;
	private int _classIndex = 1;
	private int _vitalityPoints = 0;
	private boolean _dualClass = false;

	public SubClass() {
		// Used for specifying ALL attributes of a sub class directly,
		// using the preset default values.
	}

	public ClassId getClassDefinition() {
		return _class;
	}

	public int getClassId() {
		return _class.ordinal();
	}

	public long getExp() {
		return _exp;
	}

	public long getSp() {
		return _sp;
	}

	public byte getLevel() {
		return _level;
	}

	public int getVitalityPoints() {
		return _vitalityPoints;
	}

	public void setVitalityPoints(int value) {
		_vitalityPoints = value;
	}

	/**
	 * First Sub-Class is index 1.
	 *
	 * @return int _classIndex
	 */
	public int getClassIndex() {
		return _classIndex;
	}

	public void setClassId(int classId) {
		_class = ClassId.getClassId(classId);
	}

	public void setExp(long expValue) {
		if (!_dualClass && (expValue > (ExperienceData.getInstance().getExpForLevel(_maxLevel + 1) - 1))) {
			expValue = ExperienceData.getInstance().getExpForLevel(_maxLevel + 1) - 1;
		}

		_exp = expValue;
	}

	public void setSp(long spValue) {
		_sp = spValue;
	}

	public void setClassIndex(int classIndex) {
		_classIndex = classIndex;
	}

	public boolean isDualClass() {
		return _dualClass;
	}

	public void setIsDualClass(boolean dualClass) {
		_dualClass = dualClass;
	}

	public void setLevel(byte levelValue) {
		if (!_dualClass && (levelValue > _maxLevel)) {
			levelValue = _maxLevel;
		} else if (levelValue < PlayerConfig.BASE_SUBCLASS_LEVEL) {
			levelValue = PlayerConfig.BASE_SUBCLASS_LEVEL;
		}

		_level = levelValue;
	}

	public void incLevel() {
		if (!_dualClass && (getLevel() == _maxLevel)) {
			return;
		}

		_level++;
		setExp(ExperienceData.getInstance().getExpForLevel(getLevel()));
	}

	public void decLevel() {
		if (getLevel() == PlayerConfig.BASE_SUBCLASS_LEVEL) {
			return;
		}

		_level--;
		setExp(ExperienceData.getInstance().getExpForLevel(getLevel()));
	}
}
