package org.l2junity.commons.network;

/**
 * A client factory.
 *
 * @author izen
 */
public interface IClientFactory<TClient extends Client> {
	/**
	 * Creates a new client for the supplied connection.
	 *
	 * @param connection The connection, a new client will be associated with.
	 * @return
	 */
	TClient createClient(Connection<TClient> connection);
}
