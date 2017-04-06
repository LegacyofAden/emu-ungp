package org.l2junity.gameserver.retail.model.item;

import org.l2junity.gameserver.retail.EventId;

/***
 * @author ANZO, Camellion
 * @since 20.03.2017
 */
public class ItemData {
    @EventId(84)
    public int dbid;

    @EventId(88)
    public int class_id;

    @EventId(112)
    public int pet_level;

    /*public ItemTemplate getTemplate(int classId) {
        return ItemDatasHolder.getInstance().getTemplate(classId);
    }*/
}
