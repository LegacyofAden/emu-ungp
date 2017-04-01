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
package org.l2junity.gameserver.model.primeshop;

import org.l2junity.gameserver.model.holders.ItemHolder;

/**
 * @author UnAfraid
 */

public class PrimeShopItem extends ItemHolder
{
	private static final long serialVersionUID = 4228850273393782548L;
	private final int _weight;
	private final int _isTradable;
	
	public PrimeShopItem(int itemId, int count, int weight, int isTradable)
	{
		super(itemId, count);
		_weight = weight;
		_isTradable = isTradable;
	}
	
	public int getWeight()
	{
		return _weight;
	}
	
	public int isTradable()
	{
		return _isTradable;
	}
}
