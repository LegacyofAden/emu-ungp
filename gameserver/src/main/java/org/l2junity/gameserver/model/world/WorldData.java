package org.l2junity.gameserver.model.world;

/**
 * 
 * @author PointerRage
 *
 */
public final class WorldData {
	/**
	 * Gracia border Flying objects not allowed to the east of it.
	 */
	public static final int GRACIA_MAX_X = -166168;
	public static final int GRACIA_MAX_Z = 6105;
	public static final int GRACIA_MIN_Z = -895;

	/**
	 * Bit shift, defines number of regions note, shifting by 15 will result in regions corresponding to map tiles shifting by 11 divides one tile to 16x16 regions.
	 */
	public static final int SHIFT_BY = 11;
	public static final int SHIFT_BY_Z = 10;

	private static final int TILE_SIZE = 32768;

	/**
	 * Map dimensions
	 */
	public static final int TILE_X_MIN = 11;
	public static final int TILE_Y_MIN = 10;
	public static final int TILE_X_MAX = 28;
	public static final int TILE_Y_MAX = 26;
	public static final int TILE_ZERO_COORD_X = 20;
	public static final int TILE_ZERO_COORD_Y = 18;
	public static final int MAP_MIN_X = (TILE_X_MIN - TILE_ZERO_COORD_X) * TILE_SIZE;
	public static final int MAP_MIN_Y = (TILE_Y_MIN - TILE_ZERO_COORD_Y) * TILE_SIZE;
	public static final int MAP_MIN_Z = -TILE_SIZE / 2;

	public static final int MAP_MAX_X = ((TILE_X_MAX - TILE_ZERO_COORD_X) + 1) * TILE_SIZE;
	public static final int MAP_MAX_Y = ((TILE_Y_MAX - TILE_ZERO_COORD_Y) + 1) * TILE_SIZE;
	public static final int MAP_MAX_Z = TILE_SIZE / 2;

	/**
	 * calculated offset used so top left region is 0,0
	 */
	public static final int OFFSET_X = Math.abs(MAP_MIN_X >> SHIFT_BY);
	public static final int OFFSET_Y = Math.abs(MAP_MIN_Y >> SHIFT_BY);
	public static final int OFFSET_Z = Math.abs(MAP_MIN_Z >> SHIFT_BY_Z);

	/**
	 * number of regions
	 */
	public static final int REGIONS_X = (MAP_MAX_X >> SHIFT_BY) + OFFSET_X;
	public static final int REGIONS_Y = (MAP_MAX_Y >> SHIFT_BY) + OFFSET_Y;
	public static final int REGIONS_Z = (MAP_MAX_Z >> SHIFT_BY_Z) + OFFSET_Z;

	public static final int REGION_MIN_DIMENSION = Math.min(TILE_SIZE / (TILE_SIZE >> SHIFT_BY_Z), TILE_SIZE / (TILE_SIZE >> SHIFT_BY));
	
	
	public static boolean isValidRegion(int x, int y, int z) {
        return 0 <= x && x <= REGIONS_X && 0 <= y && y <= REGIONS_Y && 0 <= z && z <= REGIONS_Z;
    }
}
