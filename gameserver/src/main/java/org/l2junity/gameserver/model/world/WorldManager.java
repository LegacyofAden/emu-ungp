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
	
	public final static int MAIN_WORLD_ID = 0;
	
	private final AbstractIdFactory idFactory = new DefaultIdFactory();
	private final Map<Integer, GameWorld> worlds = new ConcurrentHashMap<>(1, 0.8f);
	private final GameWorld mainWorld;
	
	private volatile int partyCount;
	private volatile int partyMemberCount;
	
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
	
	public List<GameWorld> getWorlds() {
		return new ArrayList<>(worlds.values());
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
	
	public void removeObject(WorldObject object) {
		for(GameWorld world : worlds.values()) {
			world.removeObject(object);
		}
	}
	
	public boolean removePet(int ownerObjectId) {
		for(GameWorld world : worlds.values()) {
			if(world.removePet(ownerObjectId)) {
				return true;
			}
		}
		return false;
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
	
	public List<Player> getAllPlayers() {
		return worlds.values().stream()
			.map(world -> world.getPlayers())
			.collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);
	}
	
	public int getPlayerCount() {
		return worlds.values().stream()
				.mapToInt(world -> world.getPlayers().size())
				.sum();
	}
	
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
