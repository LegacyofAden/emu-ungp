package org.l2junity.gameserver.retail.model.item;

/***
 * @author ANZO, Camellion
 * @since 20.03.2017
 */
public class BuySellItemInfo {
    private final int item_id;
    private final int unk1;
    private double unk2;
    private int unk3;

    private int constructor = 0;

    public BuySellItemInfo(int item_id, int unk1) {
        this.item_id = item_id;
        this.unk1 = unk1;

        constructor = 1;
    }

    public BuySellItemInfo(int item_id, int unk1, double unk2, int unk3) {
        this.item_id = item_id;
        this.unk1 = unk1;
        this.unk2 = unk2;
        this.unk3 = unk3;

        constructor = 2;
    }

    @Override
    public String toString() {
        switch (constructor) {
            case 1:
                return "new BuySellItemInfo(" + item_id + ", " + unk1 + ")";
            case 2:
                return "new BuySellItemInfo(" + item_id + ", " + unk1 + ", " + unk2 + ", " + unk3 + ")";
        }

        return super.toString();
    }
}
