package org.l2junity.commons.network;

public abstract class Client<TClient extends Client> {
	private final Connection<TClient> _connection;

	public Client(Connection<TClient> connection) {
		_connection = connection;
	}

	protected Connection<TClient> getConnection() {
		return _connection;
	}

	public int getConnectionId() {
		return getConnection().getConnectionId();
	}

	public boolean isConnected() {
		return !getConnection().isClose();
	}

	protected void onOpen() {
	}

	protected void onClose() {
	}
}