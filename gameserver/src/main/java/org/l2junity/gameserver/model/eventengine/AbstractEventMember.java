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
package org.l2junity.gameserver.model.eventengine;

import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.network.packets.GameServerPacket;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * @param <T>
 * @author UnAfraid
 */
public abstract class AbstractEventMember<T extends AbstractEvent<?>> {
	private final int _objectId;
	private final T _event;
	private final AtomicInteger _score = new AtomicInteger();

	public AbstractEventMember(Player player, T event) {
		_objectId = player.getObjectId();
		_event = event;
	}

	public final int getObjectId() {
		return _objectId;
	}

	public Player getPlayer() {
		return WorldManager.getInstance().getPlayer(_objectId);
	}

	public void sendPacket(GameServerPacket... packets) {
		final Player player = getPlayer();
		if (player != null) {
			for (GameServerPacket packet : packets) {
				player.sendPacket(packet);
			}
		}
	}

	public int getClassId() {
		final Player player = getPlayer();
		if (player != null) {
			return player.getClassId().getId();
		}
		return 0;
	}

	public void setScore(int score) {
		_score.set(score);
	}

	public int getScore() {
		return _score.get();
	}

	public int incrementScore() {
		return _score.incrementAndGet();
	}

	public int decrementScore() {
		return _score.decrementAndGet();
	}

	public int addScore(int score) {
		return _score.addAndGet(score);
	}

	public final T getEvent() {
		return _event;
	}
}
