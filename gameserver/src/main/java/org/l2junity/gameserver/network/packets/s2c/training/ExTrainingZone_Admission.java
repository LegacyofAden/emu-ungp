/*
 * Copyright (C) 2004-2016 L2J Unity
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
package org.l2junity.gameserver.network.packets.s2c.training;

import org.l2junity.commons.network.PacketBody;
import org.l2junity.core.configs.TrainingCampConfig;
import org.l2junity.gameserver.data.xml.impl.ExperienceData;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author Sdw
 */
public class ExTrainingZone_Admission extends GameServerPacket {
	private final long _timeElapsed;
	private final long _timeRemaining;
	private final double _maxExp;
	private final double _maxSp;

	public ExTrainingZone_Admission(Player player) {
		_timeElapsed = 0;
		_timeRemaining = TrainingCampConfig.MAX_DURATION;
		_maxExp = (ExperienceData.getInstance().getExpForLevel(player.getLevel()) * ExperienceData.getInstance().getTrainingRate(player.getLevel())) / TrainingCampConfig.MAX_DURATION;
		_maxSp = _maxExp / 250d;
	}

	public ExTrainingZone_Admission(int level, long timeElapsed, long timeRemaing) {
		_timeElapsed = timeElapsed;
		_timeRemaining = timeRemaing;
		_maxExp = (ExperienceData.getInstance().getExpForLevel(level) * ExperienceData.getInstance().getTrainingRate(level)) / TrainingCampConfig.MAX_DURATION;
		_maxSp = _maxExp / 250d;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_TRAINING_ZONE_ADMISSION.writeId(body);
		body.writeD((int) _timeElapsed); // Training time elapsed in minutes, max : 600 - 10hr RU / 300 - 5hr NA
		body.writeD((int) _timeRemaining); // Time remaining in seconds, max : 36000 - 10hr RU / 18000 - 5hr NA
		body.writeF(_maxExp); // Training time elapsed in minutes * this value = acquired exp IN GAME DOESN'T SEEM LIKE THE FIELD IS LIMITED
		body.writeF(_maxSp); // Training time elapsed in minutes * this value = acquired sp IN GAME LIMITED TO INTEGER.MAX_VALUE SO THE MULTIPLY WITH REMAINING TIME CANT EXCEED IT (so field max value can't exceed 3579139.0 for 10hr) !! // Note real sp gain is exp gained / 250
	}
}
