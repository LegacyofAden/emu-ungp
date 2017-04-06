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
package org.l2junity.gameserver.network.packets.c2s;

import org.l2junity.gameserver.data.xml.impl.SecondaryAuthData;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.Ex2ndPasswordAck;
import org.l2junity.gameserver.security.SecondaryPasswordAuth;


/**
 * (ch)cS{S} c: change pass? S: current password S: new password
 *
 * @author mrTJO
 */
public class RequestEx2ndPasswordReq extends GameClientPacket {
	private int _changePass;
	private String _password, _newPassword;

	@Override
	public void readImpl() {
		_changePass = readC();
		_password = readS();
		if (_changePass == 2) {
			_newPassword = readS();
		}
	}

	@Override
	public void runImpl() {
		if (!SecondaryAuthData.getInstance().isEnabled()) {
			return;
		}

		SecondaryPasswordAuth secondAuth = getClient().getSecondaryAuth();
		boolean success = false;

		if ((_changePass == 0) && !secondAuth.passwordExist()) {
			success = secondAuth.savePassword(_password);
		} else if ((_changePass == 2) && secondAuth.passwordExist()) {
			success = secondAuth.changePassword(_password, _newPassword);
		}

		if (success) {
			getClient().sendPacket(new Ex2ndPasswordAck(_changePass, Ex2ndPasswordAck.SUCCESS));
		}
	}
}