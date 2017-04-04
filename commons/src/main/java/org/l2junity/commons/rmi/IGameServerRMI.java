package org.l2junity.commons.rmi;

import org.l2junity.commons.model.AccountInfo;
import org.l2junity.commons.model.SessionInfo;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author ANZO
 * @since 03.04.2017
 */
public interface IGameServerRMI extends Remote {
	boolean testConnection() throws RemoteException;
	AccountInfo getAccountInfo(String account) throws RemoteException;

	void addLoginSession(SessionInfo sessionInfo) throws RemoteException;
	boolean isAccountOnServer(String account) throws RemoteException;
	void kickPlayerByAccount(String account) throws RemoteException;
}