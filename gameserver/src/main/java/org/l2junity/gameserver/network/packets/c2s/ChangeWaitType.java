package org.l2junity.gameserver.network.packets.c2s;

import org.l2junity.gameserver.enums.MountType;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.actor.instance.StaticObjectInstance;
import org.l2junity.gameserver.network.packets.GameClientPacket;

/**
 * @author ANZO
 * @since 06.04.2017
 */
public class ChangeWaitType extends GameClientPacket {
	private boolean isStandUp;

	@Override
	protected void readImpl() {
		isStandUp = readD() == 1;
	}

	@Override
	protected void runImpl() {
		// TODO: Handle properly
		Player player = getClient().getActiveChar();
		if(player == null) {
			return;
		}


		if (player.getMountType() != MountType.NONE) { // prevent sit/stand if you riding
			return;
		}

		if(isStandUp && player.isSitting()) {
			player.standUp();
		}
	}
}
