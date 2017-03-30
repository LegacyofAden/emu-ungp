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
package org.l2junity.loginserver.model.data;

import java.util.Objects;

import org.l2junity.commons.util.GoogleAuthenticator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author HorridoJoho
 */
public final class AccountInfo
{
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountInfo.class);
	
	private final String _login;
	private final String _passHash;
	private final int _accessLevel;
	private final int _lastServer;
	private String _otpKey;
	
	public AccountInfo(final String login, final String passHash, final int accessLevel, final int lastServer)
	{
		Objects.requireNonNull(login, "login");
		Objects.requireNonNull(passHash, "passHash");
		
		if (login.isEmpty())
		{
			throw new IllegalArgumentException("login");
		}
		if (passHash.isEmpty())
		{
			throw new IllegalArgumentException("passHash");
		}
		
		_login = login.toLowerCase();
		_passHash = passHash;
		_accessLevel = accessLevel;
		_lastServer = lastServer;
	}
	
	public void setOTP(String otpKey)
	{
		_otpKey = otpKey;
	}
	
	public boolean checkOTP(int otp)
	{
		if (_otpKey == null)
		{
			// No OTP set
			return true;
		}
		
		try
		{
			return GoogleAuthenticator.authorize(_otpKey, otp);
		}
		catch (Exception e)
		{
			LOGGER.warn("Failed to verify token: {} for account: {}", otp, _login, e);
			return false;
		}
	}
	
	public boolean checkPassHash(final String passHash)
	{
		return _passHash.equals(passHash);
	}
	
	public String getLogin()
	{
		return _login;
	}
	
	public int getAccessLevel()
	{
		return _accessLevel;
	}
	
	public int getLastServer()
	{
		return _lastServer;
	}
}
