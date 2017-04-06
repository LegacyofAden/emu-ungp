package org.l2junity.gameserver.model.world;

import lombok.Getter;
import org.l2junity.gameserver.model.items.instance.ItemInstance;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Global item storage. This storage not intended for storage dropped items.
 *
 * @author PointerRage
 */
public class ItemStorage {
	@Getter(lazy = true)
	private final static ItemStorage instance = new ItemStorage();

	private final Map<Integer, ItemInstance> items = new ConcurrentHashMap<>();

	private ItemStorage() {

	}

	public void add(ItemInstance item) {
		items.put(item.getObjectId(), item);
	}

	public boolean remove(ItemInstance item) {
		return remove(item.getObjectId());
	}

	public boolean remove(int objectId) {
		return items.remove(objectId) != null;
	}

	public boolean contains(ItemInstance item) {
		return contains(item.getObjectId());
	}

	public boolean contains(int objectId) {
		return items.containsKey(objectId);
	}

	public ItemInstance get(int objectId) {
		return items.get(objectId);
	}

}
