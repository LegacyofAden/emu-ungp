package org.l2junity.commons.network;

import java.net.InetSocketAddress;

/**
 * Inbound connection acceptance filer
 *
 * @author izen
 */
public interface IAcceptFilter {
	/**
	 * Is connection from the supplied address allowed
	 *
	 * @param address connection address
	 * @return true if allowed
	 */
	boolean isAllowedAddress(InetSocketAddress address);
}
