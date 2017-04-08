package org.l2junity.gameserver.retail.model.item;

import org.l2junity.gameserver.retail.AiEventId;

/***
 * @author ANZO, Camellion
 * @since 20.03.2017
 */
public class ItemData {
    @AiEventId(84)
    public int dbid;

    @AiEventId(88)
    public int class_id;

    @AiEventId(112)
    public int pet_level;

    /*public ItemTemplate getTemplate(int classId) {
        return ItemDatasHolder.getInstance().getTemplate(classId);
    }*/
}
