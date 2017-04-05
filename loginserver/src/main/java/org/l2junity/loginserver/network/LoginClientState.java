package org.l2junity.loginserver.network;

/**
 * @author ANZO
 * @since 05.04.2017
 */
public enum LoginClientState {
	CONNECTED,
	AUTHING,
	AUTHED_GG,
	AUTHED_LICENCE,
	AUTHED_SERVER_LIST,
	CLOSED
}