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
package org.l2junity.loginserver;


import lombok.Getter;
import org.l2junity.core.startup.StartupLevel;
import org.l2junity.core.startup.StartupManager;
import org.l2junity.loginserver.service.LoginServerRMI;

/**
 * @author ANZO
 */
public class LoginServer {
	@Getter
	private static LoginServer instance;

	@Getter
	private static LoginServerRMI rmi;

	private LoginServer() throws Exception {
		StartupManager.getInstance().startup(StartupLevel.class);
		rmi = new LoginServerRMI();
	}

	public static void main(String[] args) throws Exception {
		instance = new LoginServer();
	}
}
