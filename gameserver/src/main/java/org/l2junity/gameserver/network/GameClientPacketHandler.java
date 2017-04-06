package org.l2junity.gameserver.network;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.network.IPacketHandler;
import org.l2junity.commons.network.ReceivablePacket;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.network.packets.GameClientPacketType;
import org.l2junity.gameserver.network.packets.GameClientPacketTypeEx;

import java.nio.ByteBuffer;

/**
 * @author ANZO
 * @since 06.04.2017
 */
@Slf4j
@StartupComponent("Network")
public class GameClientPacketHandler implements IPacketHandler<GameClient> {
	@Getter(lazy = true)
	private final static GameClientPacketHandler instance = new GameClientPacketHandler();

	@Override
	public ReceivablePacket<GameClient> handleReceivedPacket(ByteBuffer buffer, GameClient client) {
		int opcode = buffer.get() & 0xFF;
		if ((opcode < 0) || (opcode >= GameClientPacketType.PACKET_ARRAY.length)) {
			log.warn("Client {} sended unknown packet with opcode {}", client, Integer.toHexString(opcode));
			return null;
		}

		GameClientPacketType packetType = GameClientPacketType.PACKET_ARRAY[opcode];

		if (!packetType.getConnectionStates().contains(client.getState())) {
			log.warn("{}: Connection at invalid state: {} Required States: {}", packetType, client.getState(), packetType.getConnectionStates());
			return null;
		}

		if (packetType == GameClientPacketType.EX_PACKET) {
			int exOpcode = buffer.getShort() & 0xFFFF;

			if ((opcode < 0) || (opcode >= GameClientPacketTypeEx.PACKET_ARRAY.length)) {
				log.warn("Client {} sended unknown expacket with opcode {}:{}", client, Integer.toHexString(opcode), Integer.toHexString(exOpcode));
				return null;
			}
			GameClientPacketTypeEx packetExType = GameClientPacketTypeEx.PACKET_ARRAY[exOpcode];
			return packetExType.newIncomingPacket();
		} else {
			return packetType.newIncomingPacket();
		}
	}
}