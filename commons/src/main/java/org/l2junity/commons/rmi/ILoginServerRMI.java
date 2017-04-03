package org.l2junity.commons.rmi;

import org.l2junity.commons.model.GameServerInfo;
import org.l2junity.commons.model.enums.RegisterResult;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author ANZO
 * @since 03.04.2017
 */
public interface ILoginServerRMI extends Remote {
	boolean testConnection() throws RemoteException;
	RegisterResult registerGameServer(IGameServerRMI connection, GameServerInfo gameServerInfo) throws RemoteException;
	void updateGameServer(IGameServerRMI connection, GameServerInfo gameServerInfo) throws RemoteException;
}