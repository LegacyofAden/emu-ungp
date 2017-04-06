package org.l2junity.gameserver.network;

/**
 * @author ANZO
 * @since 06.04.2017
 */
public enum GameClientState {
	CONNECTED,
	DISCONNECTED,
	CLOSING,
	AUTHENTICATED,
	IN_GAME
}