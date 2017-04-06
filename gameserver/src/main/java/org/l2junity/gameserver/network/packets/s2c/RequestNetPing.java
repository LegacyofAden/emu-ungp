package org.l2junity.gameserver.network.packets.s2c;

import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author PointerRage
 */
public class RequestNetPing extends GameServerPacket {
	private int objectId;

	public RequestNetPing(Player player) {
		objectId = player.getObjectId();
		player.getClient().setLastPingSendTime(System.currentTimeMillis());
	}

	public RequestNetPing() {
		objectId = 0xCAFEBABE;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.NET_PING.writeId(body);
		body.writeD(objectId);
	}
}