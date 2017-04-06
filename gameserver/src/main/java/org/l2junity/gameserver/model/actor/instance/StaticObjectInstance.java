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
package org.l2junity.gameserver.model.actor.instance;

import org.l2junity.gameserver.ai.CharacterAI;
import org.l2junity.gameserver.enums.InstanceType;
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.templates.CharTemplate;
import org.l2junity.gameserver.model.items.Weapon;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.packets.s2c.ShowTownMap;
import org.l2junity.gameserver.network.packets.s2c.StaticObject;

/**
 * Static Object instance.
 *
 * @author godson
 */
public final class StaticObjectInstance extends Creature {
	/**
	 * The interaction distance of the StaticObjectInstance
	 */
	public static final int INTERACTION_DISTANCE = 150;

	private final int _staticObjectId;
	private int _meshIndex = 0; // 0 - static objects, alternate static objects
	private int _type = -1; // 0 - map signs, 1 - throne , 2 - arena signs
	private ShowTownMap _map;

	@Override
	protected CharacterAI initAI() {
		return null;
	}

	/**
	 * Gets the static object ID.
	 *
	 * @return the static object ID
	 */
	@Override
	public int getId() {
		return _staticObjectId;
	}

	/**
	 * @param template
	 * @param staticId
	 */
	public StaticObjectInstance(CharTemplate template, int staticId) {
		super(template);
		setInstanceType(InstanceType.L2StaticObjectInstance);
		_staticObjectId = staticId;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public void setMap(String texture, int x, int y) {
		_map = new ShowTownMap("town_map." + texture, x, y);
	}

	public ShowTownMap getMap() {
		return _map;
	}

	@Override
	public final int getLevel() {
		return 1;
	}

	@Override
	public ItemInstance getActiveWeaponInstance() {
		return null;
	}

	@Override
	public Weapon getActiveWeaponItem() {
		return null;
	}

	@Override
	public ItemInstance getSecondaryWeaponInstance() {
		return null;
	}

	@Override
	public Weapon getSecondaryWeaponItem() {
		return null;
	}

	@Override
	public boolean isAutoAttackable(Creature attacker) {
		return false;
	}

	/**
	 * Set the meshIndex of the object.<br>
	 * <B><U> Values </U> :</B>
	 * <ul>
	 * <li>default textures : 0</li>
	 * <li>alternate textures : 1</li>
	 * </ul>
	 *
	 * @param meshIndex
	 */
	public void setMeshIndex(int meshIndex) {
		_meshIndex = meshIndex;
		this.broadcastPacket(new StaticObject(this));
	}

	/**
	 * <B><U> Values </U> :</B>
	 * <ul>
	 * <li>default textures : 0</li>
	 * <li>alternate textures : 1</li>
	 * </ul>
	 *
	 * @return the meshIndex of the object
	 */
	public int getMeshIndex() {
		return _meshIndex;
	}

	@Override
	public void sendInfo(Player activeChar) {
		activeChar.sendPacket(new StaticObject(this));
	}

	@Override
	public void moveToLocation(double x, double y, double z, int offset) {
	}

	@Override
	public void stopMove(Location loc) {
	}

	@Override
	public void doAutoAttack(Creature target) {
	}

	@Override
	public void doCast(Skill skill) {
	}
}
