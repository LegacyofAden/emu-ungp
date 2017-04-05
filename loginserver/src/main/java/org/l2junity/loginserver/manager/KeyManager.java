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
package org.l2junity.loginserver.manager;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.util.Rnd;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.loginserver.network.crypt.ScrambledRSAKeyPair;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.KeyPairGenerator;
import java.security.spec.RSAKeyGenParameterSpec;

/**
 * Manages the keys and key pairs required for network communication.
 *
 * @author NosBit
 */
@Slf4j
@StartupComponent("Service")
public class KeyManager {
	@Getter(lazy = true)
	private static final KeyManager instance = new KeyManager();

	private KeyGenerator _blowfishKeyGenerator;
	private ScrambledRSAKeyPair[] _scrambledRSAKeyPairs = new ScrambledRSAKeyPair[50];

	protected KeyManager() {
		try {
			_blowfishKeyGenerator = KeyGenerator.getInstance("Blowfish");

			final KeyPairGenerator rsaKeyPairGenerator = KeyPairGenerator.getInstance("RSA");
			final RSAKeyGenParameterSpec spec = new RSAKeyGenParameterSpec(1024, RSAKeyGenParameterSpec.F4);
			rsaKeyPairGenerator.initialize(spec);

			for (int i = 0; i < _scrambledRSAKeyPairs.length; i++) {
				_scrambledRSAKeyPairs[i] = new ScrambledRSAKeyPair(rsaKeyPairGenerator.generateKeyPair());
			}

			log.info("Cached {} RSA key pairs.", _scrambledRSAKeyPairs.length);
		} catch (Exception e) {
			log.error("Error while initializing KeyManager", e);
		}
	}

	/**
	 * Generates a Blowfish key.
	 *
	 * @return the blowfish {@code SecretKey}
	 */
	public SecretKey generateBlowfishKey() {
		return _blowfishKeyGenerator.generateKey();
	}

	/**
	 * Gets a random pre-cached {@code ScrambledRSAKeyPair}.
	 *
	 * @return the {@code ScrambledRSAKeyPair}
	 */
	public ScrambledRSAKeyPair getRandomScrambledRSAKeyPair() {
		return _scrambledRSAKeyPairs[Rnd.nextInt(_scrambledRSAKeyPairs.length)];
	}
}
