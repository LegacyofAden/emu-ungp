package org.l2junity.commons.rmi;

import org.l2junity.commons.model.GameServer;
import org.l2junity.commons.model.enums.RegisterResult;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author ANZO
 * @since 03.04.2017
 */
public interface ILoginServerRMI extends Remote {
	boolean testConnection() throws RemoteException;
	RegisterResult registerGameServer(IGameServerRMI connection, GameServer gameServerInfo) throws RemoteException;
}