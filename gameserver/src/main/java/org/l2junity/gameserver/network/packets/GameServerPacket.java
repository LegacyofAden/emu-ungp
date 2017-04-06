package org.l2junity.gameserver.network.packets;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.network.PacketBody;
import org.l2junity.commons.network.SendablePacket;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.interfaces.IUpdateTypeComponent;
import org.l2junity.gameserver.model.itemcontainer.Inventory;
import org.l2junity.gameserver.network.GameClient;

import java.nio.ByteBuffer;

/**
 * @author ANZO
 * @since 06.04.2017
 */
@Slf4j
public abstract class GameServerPacket extends SendablePacket<GameClient> {
	protected static int[] PAPERDOLL_ORDER = new int[]
			{
					Inventory.PAPERDOLL_UNDER,
					Inventory.PAPERDOLL_REAR,
					Inventory.PAPERDOLL_LEAR,
					Inventory.PAPERDOLL_NECK,
					Inventory.PAPERDOLL_RFINGER,
					Inventory.PAPERDOLL_LFINGER,
					Inventory.PAPERDOLL_HEAD,
					Inventory.PAPERDOLL_RHAND,
					Inventory.PAPERDOLL_LHAND,
					Inventory.PAPERDOLL_GLOVES,
					Inventory.PAPERDOLL_CHEST,
					Inventory.PAPERDOLL_LEGS,
					Inventory.PAPERDOLL_FEET,
					Inventory.PAPERDOLL_CLOAK,
					Inventory.PAPERDOLL_RHAND,
					Inventory.PAPERDOLL_HAIR,
					Inventory.PAPERDOLL_HAIR2,
					Inventory.PAPERDOLL_RBRACELET,
					Inventory.PAPERDOLL_LBRACELET,
					Inventory.PAPERDOLL_DECO1,
					Inventory.PAPERDOLL_DECO2,
					Inventory.PAPERDOLL_DECO3,
					Inventory.PAPERDOLL_DECO4,
					Inventory.PAPERDOLL_DECO5,
					Inventory.PAPERDOLL_DECO6,
					Inventory.PAPERDOLL_BELT,
					Inventory.PAPERDOLL_BROOCH,
					Inventory.PAPERDOLL_BROOCH_JEWEL1,
					Inventory.PAPERDOLL_BROOCH_JEWEL2,
					Inventory.PAPERDOLL_BROOCH_JEWEL3,
					Inventory.PAPERDOLL_BROOCH_JEWEL4,
					Inventory.PAPERDOLL_BROOCH_JEWEL5,
					Inventory.PAPERDOLL_BROOCH_JEWEL6

			};

	protected static int[] PAPERDOLL_ORDER_AUGMENT = new int[]
			{
					Inventory.PAPERDOLL_RHAND,
					Inventory.PAPERDOLL_LHAND,
					Inventory.PAPERDOLL_RHAND
			};

	protected static int[] PAPERDOLL_ORDER_VISUAL_ID = new int[]
			{
					Inventory.PAPERDOLL_RHAND,
					Inventory.PAPERDOLL_LHAND,
					Inventory.PAPERDOLL_RHAND,
					Inventory.PAPERDOLL_GLOVES,
					Inventory.PAPERDOLL_CHEST,
					Inventory.PAPERDOLL_LEGS,
					Inventory.PAPERDOLL_FEET,
					Inventory.PAPERDOLL_HAIR,
					Inventory.PAPERDOLL_HAIR2
			};

	protected int[] getPaperdollOrder() {
		return PAPERDOLL_ORDER;
	}

	protected int[] getPaperdollOrderAugument() {
		return PAPERDOLL_ORDER_AUGMENT;
	}

	protected int[] getPaperdollOrderVisualId() {
		return PAPERDOLL_ORDER_VISUAL_ID;
	}

	/**
	 * @param masks Masks to check
	 * @param type  mask type
	 * @return {@code true} if the mask contains the current update component type
	 */
	static boolean containsMask(int masks, IUpdateTypeComponent type) {
		return (masks & type.getMask()) == type.getMask();
	}

	protected abstract void writeImpl(PacketBody body);

	@Override
	protected boolean write(GameClient client, ByteBuffer buffer) {
		try {
			PacketBody body = new PacketBody<>(client, buffer);
			writeImpl(body);
		} catch (Exception e) {
			log.error("Sending {} to {} failed", getClass().getSimpleName(), client, e);
			return false;
		}
		return true;
	}

	public void run(Player player) {
	}

	public void sendTo(WorldObject player) {
		player.sendPacket(this);
	}
}