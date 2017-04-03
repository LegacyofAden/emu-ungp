package org.l2junity.commons.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author ANZO
 * @since 03.04.2017
 */
public interface IGameServerRMI extends Remote {
	boolean testConnection() throws RemoteException;
}