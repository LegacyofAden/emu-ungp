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
package org.l2junity.gameserver.model.holders;

import java.io.Serializable;

import org.l2junity.gameserver.data.xml.impl.SkillData;
import org.l2junity.gameserver.model.skills.Skill;

/**
 * Simple class for storing skill id/level.
 * @author BiggBoss
 */
public class SkillHolder implements Serializable
{
	private static final long serialVersionUID = -9109518778685462799L;
	private final int _skillId;
	private final int _skillLevel;
	private final int _skillSubLevel;
	
	public SkillHolder(int skillId, int skillLevel)
	{
		_skillId = skillId;
		_skillLevel = skillLevel;
		_skillSubLevel = 0;
	}
	
	public SkillHolder(int skillId, int skillLevel, int skillSubLevel)
	{
		_skillId = skillId;
		_skillLevel = skillLevel;
		_skillSubLevel = skillSubLevel;
	}
	
	public SkillHolder(Skill skill)
	{
		_skillId = skill.getId();
		_skillLevel = skill.getLevel();
		_skillSubLevel = skill.getSubLevel();
	}
	
	public final int getSkillId()
	{
		return _skillId;
	}
	
	public final int getSkillLevel()
	{
		return _skillLevel;
	}
	
	public final int getSkillSubLevel()
	{
		return _skillSubLevel;
	}
	
	public final Skill getSkill()
	{
		return SkillData.getInstance().getSkill(_skillId, Math.max(_skillLevel, 1), _skillSubLevel);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		
		if (!(obj instanceof SkillHolder))
		{
			return false;
		}
		
		final SkillHolder holder = (SkillHolder) obj;
		return (holder.getSkillId() == _skillId) && (holder.getSkillLevel() == _skillLevel) && (holder.getSkillSubLevel() == _skillSubLevel);
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = (prime * result) + _skillId;
		result = (prime * result) + _skillLevel;
		result = (prime * result) + _skillSubLevel;
		return result;
	}
	
	@Override
	public String toString()
	{
		return "[SkillId: " + _skillId + " Level: " + _skillLevel + " SubLevel: " + _skillSubLevel + "]";
	}
}