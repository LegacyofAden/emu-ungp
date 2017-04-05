package org.l2junity.commons.network;

import org.apache.commons.lang3.builder.Builder;

import java.nio.channels.AsynchronousChannelGroup;

/**
 * AIO network settings interface
 *
 * @author izen
 */
public interface INetworkSettings {
	/**
	 * Get the async channel group builder.
	 *
	 * @return Channel group builder.
	 */
	Builder<AsynchronousChannelGroup> getChannelGroupBuilder();
}
