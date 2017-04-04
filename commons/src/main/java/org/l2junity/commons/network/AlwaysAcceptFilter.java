package org.l2junity.commons.network;

import java.net.InetSocketAddress;

/**
 * @author ANZO
 * @since 24.03.2017
 */
public class AlwaysAcceptFilter implements IAcceptFilter {
	@Override
	public boolean isAllowedAddress(InetSocketAddress address) {
		return true;
	}
}
