package org.l2junity.gameserver.model.world;

import java.util.ArrayList;
import java.util.List;
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
	
	/** Predefined & default world id */
	public final static int MAIN_WORLD_ID = 0;
	
	private final AbstractIdFactory idFactory = new DefaultIdFactory(); //TODO Pointer: need to setup ids bounds
	private final Map<Integer, GameWorld> worlds = new ConcurrentHashMap<>(1, 0.8f);
	private final GameWorld mainWorld;
	
	private volatile int partyCount;
	private volatile int partyMemberCount;
	
	private WorldManager() {
		mainWorld = new GameWorld(MAIN_WORLD_ID);
		mainWorld.initAllRegions();
		worlds.put(MAIN_WORLD_ID, mainWorld);
	}
	
	/**
	 * Return predefined & default world.
	 * @return main world
	 */
	public GameWorld getMainWorld() {
		return mainWorld;
	}
	
	/**
	 * Return specified world.
	 * @param id 
	 * 			world id
	 * @return world model or null
	 */
	public GameWorld getWorld(int id) {
		return worlds.get(id);
	}
	
	/**
	 * Return list (this list is copy) with all exists worlds.
	 * @return world model list
	 */
	public List<GameWorld> getWorlds() {
		return new ArrayList<>(worlds.values());
	}
	
	/**
	 * Create world with auto-generated ID.
	 * @return created world
	 */
	public GameWorld createWorld() {
		final int id = idFactory.getNextId();
		final GameWorld world = new GameWorld(id);
		//GeoData.getEngine().createWorld(id);
		worlds.put(id, world);
		return world;
	}
	
	/**
	 * Create world with specified ID.
	 * @param id 
	 * 			world id
	 * @return created world
	 * @throws RuntimeException
	 * 			throws if world with this id already exists 
	 */
	public GameWorld createWorld(int id) throws RuntimeException {
		if(worlds.containsKey(id)) {
			throw new RuntimeException("World already exists!");
		}
		
		final GameWorld world = new GameWorld(id);
		//GeoData.getEngine().createWorld(id);
		worlds.put(id, world);
		return world;
	}
	
	public boolean destroyWorld(GameWorld world) {
		return destroyWorld(world.getId());
	}
	
	/**
	 * Destroy specified world.
	 * @param id 
	 * 			world id
	 * @return result of destruction
	 * @throws RuntimeException
	 * 			throws if ID equals MAIN_WORLD_ID
	 */
	public boolean destroyWorld(int id) throws RuntimeException {
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
	
	/**
	 * Remove world object from all worlds.
	 * @param object
	 */
	public void removeObject(WorldObject object) {
		for(GameWorld world : worlds.values()) {
			world.removeObject(object);
		}
	}
	
	/**
	 * Remove pet from world
	 * @param ownerObjectId 
	 * 			pet owner object id
	 * @return operation result
	 */
	public boolean removePet(int ownerObjectId) {
		for(GameWorld world : worlds.values()) {
			if(world.removePet(ownerObjectId)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Return specified player from any world.
	 * @param objectId 
	 * 			player object id
	 * @return player model or null
	 */
	public Player getPlayer(int objectId) {
		for(GameWorld world : worlds.values()) {
			final Player player = world.getPlayer(objectId);
			if(player != null) {
				return player;
			}
		}
		
		return null;
	}
	
	/**
	 * Return specified player from any world by nickname (name).
	 * @param nickname 
	 * 			player name
	 * @return player model or null
	 */
	public Player getPlayer(String nickname) {
		for(GameWorld world : worlds.values()) {
			final Player player = world.getPlayer(nickname);
			if(player != null) {
				return player;
			}
		}
		
		return null;
	}
	
	/** 
	 * Return specified pet from any world.
	 * @param ownerObjectId 
	 * 			pet owner object id
	 * @return pet model or null 
	 */
	public PetInstance getPet(int ownerObjectId) {
		for(GameWorld world : worlds.values()) {
			final PetInstance pet = world.getPet(ownerObjectId);
			if(pet != null) {
				return pet;
			}
		}
		
		return null;
	}
	
	/**
	 * Return specified world object from any world.
	 * @param objectId 
	 * 			world object id
	 * @return world object model or null
	 */
	public WorldObject getObject(int objectId) {
		for(GameWorld world : worlds.values()) {
			final WorldObject object = world.findObject(objectId);
			if(object != null) {
				return object;
			}
		}
		
		return null;
	}
	
	/**
	 * Return player model list from all worlds.
	 * @return player model list
	 */
	public List<Player> getAllPlayers() {
		return worlds.values().stream()
			.map(world -> world.getPlayers())
			.collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);
	}
	
	/**
	 * Return player count as sum from all worlds.
	 * @return player count
	 */
	public int getPlayerCount() {
		return worlds.values().stream()
				.mapToInt(world -> world.getPlayers().size())
				.sum();
	}
	
	/**
	 * Return world object count as sum from all worlds.
	 * @return world object count
	 */
	public long getObjectCount() {
		return worlds.values().stream()
				.mapToLong(world -> (long)world.getVisibleObjectsCount())
				.sum();
	}
	
	public void incrementPartyCount() {
		partyCount++;
	}
	
	public void decrementPartyCount() {
		partyCount--;
	}
	
	public int getPartyCount() {
		return partyCount;
	}
	
	public void incrementPartyMemberCount() {
		partyMemberCount++;
	}
	
	public void decrementPartyMemberCount() {
		partyMemberCount--;
	}
	
	public int getPartyMemberCount() {
		return partyMemberCount;
	}
}
