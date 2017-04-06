package org.l2junity.gameserver.enums;

/**
 * Unreal Engine 2.5 Lineage 2 Interlude deasm
 *
 * @author PointerRage, deasm izen
 */
public enum EEnvType {
	ET_NONE(-1),
	/** на земле */
	ET_GROUND(0x00),
	/** под водой */
	ET_UNDERWATER(0x01),
	/** воздух */
	ET_AIR(0x02),
	/** маунт */
	ET_HOVER(0x03);

	private final int type;
	EEnvType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
}