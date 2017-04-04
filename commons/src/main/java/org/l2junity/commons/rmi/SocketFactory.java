package org.l2junity.commons.rmi;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;

/**
 * @author ANZO
 * @since 03.04.2017
 */
public class SocketFactory extends RMISocketFactory {
	@Override
	public Socket createSocket(String host, int port) throws IOException {
		Socket s = new Socket();
		s.connect(new InetSocketAddress(InetAddress.getByName(host), port));
		return s;
	}

	@Override
	public ServerSocket createServerSocket(int port) throws IOException {
		ServerSocket s = new ServerSocket();
		s.bind(new InetSocketAddress(port));
		return s;
	}
}