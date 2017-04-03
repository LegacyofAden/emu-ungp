package org.l2junity.gameserver.model.world;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.l2junity.commons.idfactory.AbstractIdFactory;
import org.l2junity.commons.idfactory.DefaultIdFactory;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.instance.PetInstance;
import org.l2junity.gameserver.model.actor.instance.Player;

import lombok.Getter;

/**
 * 
 * @author PointerRage
 *
 */
public class WorldManager {
	@Getter(lazy=true) private final static WorldManager instance = new WorldManager();
	
	public final static int MAIN_WORLD_ID = 0;
	
	private final AbstractIdFactory idFactory = new DefaultIdFactory();
	private final Map<Integer, GameWorld> worlds = new ConcurrentHashMap<>(1, 0.8f);
	private final GameWorld mainWorld;
	
	public WorldManager() {
		mainWorld = new GameWorld(MAIN_WORLD_ID);
		mainWorld.initAllRegions();
	}
	
	public GameWorld getMainWorld() {
		return mainWorld;
	}
	
	public GameWorld getWorld(int id) {
		return worlds.get(id);
	}
	
	public GameWorld createWorld() {
		final int id = idFactory.getNextId();
		final GameWorld world = new GameWorld(id);
		//GeoData.getEngine().createWorld(id);
		worlds.put(id, world);
		return world;
	}
	
	public boolean destroyWorld(GameWorld world) {
		return destroyWorld(world.getId());
	}
	
	public boolean destroyWorld(int id) {
		if(id == MAIN_WORLD_ID) {
			throw new RuntimeException("Doesnt destroy main world");
		}
		
		final GameWorld world = worlds.get(id);
		if(world == null) {
			return false;
		}
		
		world.dispose();
		worlds.remove(id);
		//GeoData.getEngine().disposeWorld(id);
		
		return true;
	}
	
	public Player getPlayer(int objectId) {
		for(GameWorld world : worlds.values()) {
			final Player player = world.getPlayer(objectId);
			if(player != null) {
				return player;
			}
		}
		
		return null;
	}
	
	public Player getPlayer(String nickname) {
		for(GameWorld world : worlds.values()) {
			final Player player = world.getPlayer(nickname);
			if(player != null) {
				return player;
			}
		}
		
		return null;
	}
	
	public PetInstance getPet(int ownerObjectId) {
		for(GameWorld world : worlds.values()) {
			final PetInstance pet = world.getPet(ownerObjectId);
			if(pet != null) {
				return pet;
			}
		}
		
		return null;
	}
	
	public WorldObject getObject(int objectId) {
		for(GameWorld world : worlds.values()) {
			final WorldObject object = world.findObject(objectId);
			if(object != null) {
				return object;
			}
		}
		
		return null;
	}
}
