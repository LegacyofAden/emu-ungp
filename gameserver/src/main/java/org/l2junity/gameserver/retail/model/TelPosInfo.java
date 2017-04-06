package org.l2junity.gameserver.retail.model;

/***
 * @author ANZO, Camellion
 * @since 20.03.2017
 */
public class TelPosInfo {
    private final int fStringName;
    private final int x;
    private final int y;
    private final int z;
    private final int adenaCost;
    private final int[] castleIds;

    public TelPosInfo(int fStringName, int x, int y, int z, int adenaCost, int... castleIds) {
        this.fStringName = fStringName;
        this.x = x;
        this.y = y;
        this.z = z;
        this.adenaCost = adenaCost;
        this.castleIds = castleIds;
    }

    @Override
    public String toString() {
        return "new TelPosInfo(" + fStringName + ", " + x + ", " + y + ", " + z + ", " + adenaCost + ", new int[]{" + arrayToString(castleIds, ",") + "})";
    }

    private static String arrayToString(int[] theAray, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < theAray.length; i++) {
            if (i > 0) {
                sb.append(delimiter);
            }
            int item = theAray[i];
            sb.append(item);
        }
        return sb.toString();
    }
}
