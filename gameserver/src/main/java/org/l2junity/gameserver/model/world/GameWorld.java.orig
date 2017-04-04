package org.l2junity.gameserver.model.world;

import static org.l2junity.gameserver.model.world.WorldData.OFFSET_X;
import static org.l2junity.gameserver.model.world.WorldData.OFFSET_Y;
import static org.l2junity.gameserver.model.world.WorldData.OFFSET_Z;
import static org.l2junity.gameserver.model.world.WorldData.REGIONS_X;
import static org.l2junity.gameserver.model.world.WorldData.REGIONS_Y;
import static org.l2junity.gameserver.model.world.WorldData.REGIONS_Z;
import static org.l2junity.gameserver.model.world.WorldData.REGION_MIN_DIMENSION;
import static org.l2junity.gameserver.model.world.WorldData.SHIFT_BY;
import static org.l2junity.gameserver.model.world.WorldData.SHIFT_BY_Z;
import static org.l2junity.gameserver.model.world.WorldData.isValidRegion;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.l2junity.commons.util.CommonUtil;
import org.l2junity.gameserver.ai.CharacterAI;
import org.l2junity.gameserver.ai.CtrlEvent;
import org.l2junity.gameserver.ai.CtrlIntention;
import org.l2junity.gameserver.data.sql.impl.CharNameTable;
import org.l2junity.gameserver.model.TeleportWhereType;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.L2DefenderInstance;
import org.l2junity.gameserver.model.actor.instance.L2FriendlyMobInstance;
import org.l2junity.gameserver.model.actor.instance.L2GuardInstance;
import org.l2junity.gameserver.model.actor.instance.PetInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.entity.Castle;
import org.l2junity.gameserver.model.entity.Fort;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.character.npc.OnNpcCreatureSee;
import org.l2junity.gameserver.model.interfaces.IDeletable;
import org.l2junity.gameserver.model.interfaces.ILocational;
import org.l2junity.gameserver.network.client.Disconnection;
import org.l2junity.gameserver.network.client.send.DeleteObject;
import org.l2junity.gameserver.util.Util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author PointerRage
 *
 */
@Slf4j
public class GameWorld {
	private final Map<Integer, WorldObject> objects = new ConcurrentHashMap<>();
	private final Map<Integer, Player> players = new ConcurrentHashMap<>();
	private final Map<Integer, PetInstance> pets = new ConcurrentHashMap<>();
	
	@Getter(AccessLevel.PROTECTED) private final Region[][][] regions = new Region[REGIONS_X + 1][REGIONS_Y + 1][REGIONS_Z + 1];
	
	@Getter private final int id;
	protected GameWorld(int id) {
		this.id = id;
	}
	
	/**
	 * Adds an object to the world.<br>
	 * <B><U>Example of use</U>:</B>
	 * <ul>
	 * <li>Withdraw an item from the warehouse, create an item</li>
	 * <li>Spawn a L2Character (PC, NPC, Pet)</li>
	 * </ul>
	 *
	 * @param object
	 */
	public void addObject(WorldObject object) {
		if (objects.putIfAbsent(object.getObjectId(), object) != null) {
			log.warn("Object {} already exists in the world. Stack Trace:{}", object, CommonUtil.getTraceString(Thread.currentThread().getStackTrace()));
		}

		if (object.isPlayer()) {
			final Player newPlayer = (Player) object;
			if (newPlayer.isTeleporting()) // TODO: drop when we stop removing player from the world while teleporting.
			{
				return;
			}

			final Player existingPlayer = players.putIfAbsent(object.getObjectId(), newPlayer);
			if (existingPlayer != null) {
				Disconnection.of(existingPlayer).defaultSequence(false);
				Disconnection.of(newPlayer).defaultSequence(false);
				log.warn("Duplicate character!? Disconnected both characters ({})", newPlayer.getName());
			}
		}
	}
	
	/**
	 * Removes an object from the world.<br>
	 * <B><U>Example of use</U>:</B>
	 * <ul>
	 * <li>Delete item from inventory, transfer Item from inventory to warehouse</li>
	 * <li>Crystallize item</li>
	 * <li>Remove NPC/PC/Pet from the world</li>
	 * </ul>
	 *
	 * @param object the object to remove
	 */
	public void removeObject(WorldObject object) {
		objects.remove(object.getObjectId());
		if (object.isPlayer()) {
			final Player player = (Player) object;
			if (player.isTeleporting()) // TODO: drop when we stop removing player from the world while teleportingq.
			{
				return;
			}
			players.remove(object.getObjectId());
		}
	}
	
	/**
	 * <B><U> Example of use</U>:</B>
	 * <ul>
	 * <li>Client packets : Action, AttackRequest, RequestJoinParty, RequestJoinPledge...</li>
	 * </ul>
	 *
	 * @param objectId Identifier of the L2Object
	 * @return the L2Object object that belongs to an ID or null if no object found.
	 */
	public WorldObject findObject(int objectId) {
		return objects.get(objectId);
	}

	public Collection<WorldObject> getVisibleObjects() {
		return objects.values();
	}

	/**
	 * Get the count of all visible objects in world.
	 *
	 * @return count off all L2World objects
	 */
	public int getVisibleObjectsCount() {
		return objects.size();
	}

	public Collection<Player> getPlayers() {
		return players.values();
	}

	/**
	 * <B>If you have access to player objectId use {@link #getPlayer(int playerObjId)}</B>
	 *
	 * @param name Name of the player to get Instance
	 * @return the player instance corresponding to the given name.
	 */
	public Player getPlayer(String name) {
		return getPlayer(CharNameTable.getInstance().getIdByName(name));
	}

	/**
	 * @param objectId of the player to get Instance
	 * @return the player instance corresponding to the given object ID.
	 */
	public Player getPlayer(int objectId) {
		return players.get(objectId);
	}

	/**
	 * @param ownerId ID of the owner
	 * @return the pet instance from the given ownerId.
	 */
	public PetInstance getPet(int ownerId) {
		return pets.get(ownerId);
	}

	/**
	 * Add the given pet instance from the given ownerId.
	 *
	 * @param ownerId ID of the owner
	 * @param pet     PetInstance of the pet
	 * @return
	 */
	public PetInstance addPet(int ownerId, PetInstance pet) {
		return pets.put(ownerId, pet);
	}

	/**
	 * Remove the given pet instance.
	 *
	 * @param ownerId ID of the owner
	 * @return result
	 */
	public boolean removePet(int ownerId) {
		return pets.remove(ownerId) != null;
	}

	/**
	 * Add a L2Object in the world. <B><U> Concept</U> :</B> L2Object (including Player) are identified in <B>_visibleObjects</B> of his current WorldRegion and in <B>_knownObjects</B> of other surrounding L2Characters <BR>
	 * Player are identified in <B>_allPlayers</B> of L2World, in <B>_allPlayers</B> of his current WorldRegion and in <B>_knownPlayer</B> of other surrounding L2Characters <B><U> Actions</U> :</B>
	 * <li>Add the L2Object object in _allPlayers* of L2World</li>
	 * <li>Add the L2Object object in _gmList** of GmListTable</li>
	 * <li>Add object in _knownObjects and _knownPlayer* of all surrounding WorldRegion L2Characters</li><BR>
	 * <li>If object is a L2Character, add all surrounding L2Object in its _knownObjects and all surrounding Player in its _knownPlayer</li><BR>
	 * <I>* only if object is a Player</I><BR>
	 * <I>** only if object is a GM Player</I> <FONT COLOR=#FF0000><B> <U>Caution</U> : This method DOESN'T ADD the object in _visibleObjects and _allPlayers* of WorldRegion (need synchronisation)</B></FONT><BR>
	 * <FONT COLOR=#FF0000><B> <U>Caution</U> : This method DOESN'T ADD the object to _allObjects and _allPlayers* of L2World (need synchronisation)</B></FONT> <B><U> Example of use </U> :</B>
	 * <li>Drop an Item</li>
	 * <li>Spawn a L2Character</li>
	 * <li>Apply Death Penalty of a Player</li>
	 *
	 * @param object    L2object to add in the world
	 * @param newRegion WorldRegion in wich the object will be add (not used)
	 */
	public void addVisibleObject(WorldObject object, Region newRegion) {
		if (!newRegion.isActive()) {
			return;
		}

		forEachVisibleObject(object, WorldObject.class, 1, wo ->
		{
			if (object.isPlayer() && wo.isVisibleFor((Player) object)) {
				final Player player = object.getActingPlayer();
				wo.sendInfo(player);
				if (wo.isCreature()) {
					final CharacterAI ai = ((Creature) wo).getAI();
					if (ai != null) {
						ai.describeStateToPlayer(player);
						if (wo.isMonster() || (wo instanceof L2FriendlyMobInstance)) {
							if (ai.getIntention() == CtrlIntention.AI_INTENTION_IDLE) {
								ai.setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
							}
						} else if (wo instanceof L2DefenderInstance) {
							final Castle castle = ((Npc) wo).getCastle();
							final Fort fortress = ((Npc) wo).getFort();
							final int activeSiegeId = (fortress != null ? fortress.getResidenceId() : (castle != null ? castle.getResidenceId() : 0));
							if ((((player.getSiegeState() == 2) && !player.isRegisteredOnThisSiegeField(activeSiegeId)) || (player.getSiegeState() == 0)) && (ai.getIntention() == CtrlIntention.AI_INTENTION_IDLE)) {
								ai.setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
							}
						} else if ((wo instanceof L2GuardInstance) && (player.getReputation() < 0) && (ai.getIntention() == CtrlIntention.AI_INTENTION_IDLE)) {
							ai.setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
						}
					}
				}
			}

			if (wo.isPlayer() && object.isVisibleFor((Player) wo)) {
				final Player player = wo.getActingPlayer();
				object.sendInfo(player);
				if (object.isCreature()) {
					final CharacterAI ai = ((Creature) object).getAI();
					if (ai != null) {
						ai.describeStateToPlayer(player);
						if (object.isMonster() || (object instanceof L2FriendlyMobInstance)) {
							if (ai.getIntention() == CtrlIntention.AI_INTENTION_IDLE) {
								ai.setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
							}
						} else if (object instanceof L2DefenderInstance) {
							final Castle castle = ((Npc) object).getCastle();
							final Fort fortress = ((Npc) object).getFort();
							final int activeSiegeId = (fortress != null ? fortress.getResidenceId() : (castle != null ? castle.getResidenceId() : 0));
							if ((((player.getSiegeState() == 2) && !player.isRegisteredOnThisSiegeField(activeSiegeId)) || (player.getSiegeState() == 0)) && (ai.getIntention() == CtrlIntention.AI_INTENTION_IDLE)) {
								ai.setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
							}
						} else if ((object instanceof L2GuardInstance) && (player.getReputation() < 0) && (ai.getIntention() == CtrlIntention.AI_INTENTION_IDLE)) {
							ai.setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
						}
					}
				}
			}

			if (wo.isNpc() && object.isCreature()) {
				EventDispatcher.getInstance().notifyEventAsync(new OnNpcCreatureSee((Npc) wo, (Creature) object, object.isSummon()), (Npc) wo);
			}

			if (object.isNpc() && wo.isCreature()) {
				EventDispatcher.getInstance().notifyEventAsync(new OnNpcCreatureSee((Npc) object, (Creature) wo, wo.isSummon()), (Npc) object);
			}
		});
	}

	/**
	 * Remove a L2Object from the world. <B><U> Concept</U> :</B> L2Object (including Player) are identified in <B>_visibleObjects</B> of his current WorldRegion and in <B>_knownObjects</B> of other surrounding L2Characters <BR>
	 * Player are identified in <B>_allPlayers</B> of L2World, in <B>_allPlayers</B> of his current WorldRegion and in <B>_knownPlayer</B> of other surrounding L2Characters <B><U> Actions</U> :</B>
	 * <li>Remove the L2Object object from _allPlayers* of L2World</li>
	 * <li>Remove the L2Object object from _visibleObjects and _allPlayers* of WorldRegion</li>
	 * <li>Remove the L2Object object from _gmList** of GmListTable</li>
	 * <li>Remove object from _knownObjects and _knownPlayer* of all surrounding WorldRegion L2Characters</li><BR>
	 * <li>If object is a L2Character, remove all L2Object from its _knownObjects and all Player from its _knownPlayer</li> <FONT COLOR=#FF0000><B> <U>Caution</U> : This method DOESN'T REMOVE the object from _allObjects of L2World</B></FONT> <I>* only if object is a Player</I><BR>
	 * <I>** only if object is a GM Player</I> <B><U> Example of use </U> :</B>
	 * <li>Pickup an Item</li>
	 * <li>Decay a L2Character</li>
	 *
	 * @param object    L2object to remove from the world
	 * @param oldRegion WorldRegion in which the object was before removing
	 */
	public void removeVisibleObject(WorldObject object, Region oldRegion) {
		if (oldRegion != null) {
			oldRegion.removeVisibleObject(object);

			// Go through all surrounding WorldRegion Creatures
			oldRegion.forEachSurroundingRegion(w ->
			{
				for (WorldObject wo : w.getObjects().values()) {
					if (wo == object) {
						continue;
					}

					if (object.isCreature()) {
						final Creature objectCreature = (Creature) object;
						final CharacterAI ai = objectCreature.getAI();
						if (ai != null) {
							ai.notifyEvent(CtrlEvent.EVT_FORGET_OBJECT, wo);
						}

						if (objectCreature.getTarget() == wo) {
							objectCreature.setTarget(null);
						}

						if (object.isPlayer()) {
							object.sendPacket(new DeleteObject(wo));
						}
					}

					if (wo.isCreature()) {
						final Creature woCreature = (Creature) wo;
						final CharacterAI ai = woCreature.getAI();
						if (ai != null) {
							ai.notifyEvent(CtrlEvent.EVT_FORGET_OBJECT, object);
						}

						if (woCreature.getTarget() == object) {
							woCreature.setTarget(null);
						}

						if (wo.isPlayer()) {
							wo.sendPacket(new DeleteObject(object));
						}
					}
				}
				return true;
			});
		}
	}

	public void switchRegion(WorldObject object, Region newRegion) {
		final Region oldRegion = object.getWorldRegion();
		if (oldRegion == null || oldRegion == newRegion) {
			return;
		}

		oldRegion.forEachSurroundingRegion(w ->
		{
			if (!newRegion.isSurroundingRegion(w)) {
				for (WorldObject wo : w.getObjects().values()) {
					if (wo == object) {
						continue;
					}

					if (object.isCreature()) {
						final Creature objectCreature = (Creature) object;
						final CharacterAI ai = objectCreature.getAI();
						if (ai != null) {
							ai.notifyEvent(CtrlEvent.EVT_FORGET_OBJECT, wo);
						}

						if (objectCreature.getTarget() == wo) {
							objectCreature.setTarget(null);
						}

						if (object.isPlayer()) {
							object.sendPacket(new DeleteObject(wo));
						}
					}

					if (wo.isCreature()) {
						final Creature woCreature = (Creature) wo;
						final CharacterAI ai = woCreature.getAI();
						if (ai != null) {
							ai.notifyEvent(CtrlEvent.EVT_FORGET_OBJECT, object);
						}

						if (woCreature.getTarget() == object) {
							woCreature.setTarget(null);
						}

						if (wo.isPlayer()) {
							wo.sendPacket(new DeleteObject(object));
						}
					}
				}
			}
			return true;
		});

		newRegion.forEachSurroundingRegion(w ->
		{
			if (!oldRegion.isSurroundingRegion(w)) {
				for (WorldObject wo : w.getObjects().values()) {
					if (wo == object || wo.getInstanceWorld() != object.getInstanceWorld()) {
						continue;
					}

					if (object.isPlayer() && wo.isVisibleFor((Player) object)) {
						final Player player = object.getActingPlayer();
						wo.sendInfo(player);
						if (wo.isCreature()) {
							final CharacterAI ai = ((Creature) wo).getAI();
							if (ai != null) {
								ai.describeStateToPlayer((Player) object);
								if (wo.isMonster() || wo instanceof L2FriendlyMobInstance) {
									if (ai.getIntention() == CtrlIntention.AI_INTENTION_IDLE) {
										ai.setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
									}
								} else if (wo instanceof L2DefenderInstance) {
									final Castle castle = ((Npc) wo).getCastle();
									final Fort fortress = ((Npc) wo).getFort();
									final int activeSiegeId = (fortress != null ? fortress.getResidenceId() : (castle != null ? castle.getResidenceId() : 0));
									if ((((player.getSiegeState() == 2) && !player.isRegisteredOnThisSiegeField(activeSiegeId)) || (player.getSiegeState() == 0)) && (ai.getIntention() == CtrlIntention.AI_INTENTION_IDLE)) {
										ai.setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
									}
								} else if ((wo instanceof L2GuardInstance) && (player.getReputation() < 0) && (ai.getIntention() == CtrlIntention.AI_INTENTION_IDLE)) {
									ai.setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
								}
							}
						}
					}

					if (wo.isPlayer() && object.isVisibleFor((Player) wo)) {
						final Player player = wo.getActingPlayer();
						object.sendInfo(player);
						if (object.isCreature()) {
							final CharacterAI ai = ((Creature) object).getAI();
							if (ai != null) {
								ai.describeStateToPlayer((Player) wo);
								if (object.isMonster() || (object instanceof L2FriendlyMobInstance)) {
									if (ai.getIntention() == CtrlIntention.AI_INTENTION_IDLE) {
										ai.setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
									}
								} else if (object instanceof L2DefenderInstance) {
									final Castle castle = ((Npc) object).getCastle();
									final Fort fortress = ((Npc) object).getFort();
									final int activeSiegeId = (fortress != null ? fortress.getResidenceId() : (castle != null ? castle.getResidenceId() : 0));
									if ((((player.getSiegeState() == 2) && !player.isRegisteredOnThisSiegeField(activeSiegeId)) || (player.getSiegeState() == 0)) && (ai.getIntention() == CtrlIntention.AI_INTENTION_IDLE)) {
										ai.setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
									}
								} else if ((object instanceof L2GuardInstance) && (player.getReputation() < 0) && (ai.getIntention() == CtrlIntention.AI_INTENTION_IDLE)) {
									ai.setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
								}
							}
						}
					}

					if (wo.isNpc() && object.isCreature()) {
						EventDispatcher.getInstance().notifyEventAsync(new OnNpcCreatureSee((Npc) wo, (Creature) object, object.isSummon()), (Npc) wo);
					}

					if (object.isNpc() && wo.isCreature()) {
						EventDispatcher.getInstance().notifyEventAsync(new OnNpcCreatureSee((Npc) object, (Creature) wo, wo.isSummon()), (Npc) object);
					}
				}
			}
			return true;
		});
	}

	private <T extends WorldObject> void forEachVisibleObject(WorldObject object, Class<T> clazz, int depth, Consumer<T> c) {
		if (object == null) {
			return;
		}

		final Region centerWorldRegion = getRegion(object);
		if (centerWorldRegion == null) {
			return;
		}

		for (int x = Math.max(centerWorldRegion.getX() - depth, 0); x <= Math.min(centerWorldRegion.getX() + depth, REGIONS_X); x++) {
			for (int y = Math.max(centerWorldRegion.getY() - depth, 0); y <= Math.min(centerWorldRegion.getY() + depth, REGIONS_Y); y++) {
				for (int z = Math.max(centerWorldRegion.getZ() - depth, 0); z <= Math.min(centerWorldRegion.getZ() + depth, REGIONS_Z); z++) {
					if(regions[x][y][z] == null) {
						continue;
					}
					
					for (WorldObject visibleObject : regions[x][y][z].getObjects().values()) {
						if ((visibleObject == null) || (visibleObject == object) || !clazz.isInstance(visibleObject)) {
							continue;
						}

						if (visibleObject.getInstanceWorld() != object.getInstanceWorld()) {
							continue;
						}

						c.accept(clazz.cast(visibleObject));
					}
				}
			}
		}
	}

	public <T extends WorldObject> void forEachVisibleObject(WorldObject object, Class<T> clazz, Consumer<T> c) {
		forEachVisibleObject(object, clazz, 1, c);
	}

	public <T extends WorldObject> void forEachVisibleObjectInRadius(WorldObject object, Class<T> clazz, int radius, Consumer<T> c) {
		if (object == null) {
			return;
		}

		final Region centerWorldRegion = getRegion(object);
		if (centerWorldRegion == null) {
			return;
		}

		final int depth = (radius / REGION_MIN_DIMENSION) + 1;
		for (int x = Math.max(centerWorldRegion.getX() - depth, 0); x <= Math.min(centerWorldRegion.getX() + depth, REGIONS_X); x++) {
			for (int y = Math.max(centerWorldRegion.getY() - depth, 0); y <= Math.min(centerWorldRegion.getY() + depth, REGIONS_Y); y++) {
				for (int z = Math.max(centerWorldRegion.getZ() - depth, 0); z <= Math.min(centerWorldRegion.getZ() + depth, REGIONS_Z); z++) {
					if(regions[x][y][z] == null) {
						continue;
					}
					
					final int x1 = (x - OFFSET_X) << SHIFT_BY;
					final int y1 = (y - OFFSET_Y) << SHIFT_BY;
					final int z1 = (z - OFFSET_Z) << SHIFT_BY_Z;
					final int x2 = ((x + 1) - OFFSET_X) << SHIFT_BY;
					final int y2 = ((y + 1) - OFFSET_Y) << SHIFT_BY;
					final int z2 = ((z + 1) - OFFSET_Z) << SHIFT_BY_Z;
					if (Util.cubeIntersectsSphere(x1, y1, z1, x2, y2, z2, object.getX(), object.getY(), object.getZ(), radius)) {
						for (WorldObject visibleObject : regions[x][y][z].getObjects().values()) {
							if ((visibleObject == null) || (visibleObject == object) || !clazz.isInstance(visibleObject)) {
								continue;
							}

							if (visibleObject.getInstanceWorld() != object.getInstanceWorld()) {
								continue;
							}

							if (visibleObject.isInRadius3d(object, radius)) {
								c.accept(clazz.cast(visibleObject));
							}
						}
					}
				}
			}
		}
	}

	public <T extends WorldObject> List<T> getVisibleObjects(WorldObject object, Class<T> clazz) {
		final List<T> result = new LinkedList<>();
		forEachVisibleObject(object, clazz, result::add);
		return result;
	}

	public <T extends WorldObject> List<T> getVisibleObjects(WorldObject object, Class<T> clazz, Predicate<T> predicate) {
		final List<T> result = new LinkedList<>();
		forEachVisibleObject(object, clazz, o ->
		{
			if (predicate.test(o)) {
				result.add(o);
			}
		});
		return result;
	}

	public <T extends WorldObject> List<T> getVisibleObjects(WorldObject object, Class<T> clazz, int radius) {
		final List<T> result = new LinkedList<>();
		forEachVisibleObjectInRadius(object, clazz, radius, result::add);
		return result;
	}

	public <T extends WorldObject> List<T> getVisibleObjects(WorldObject object, Class<T> clazz, int radius, Predicate<T> predicate) {
		final List<T> result = new LinkedList<>();
		forEachVisibleObjectInRadius(object, clazz, radius, o ->
		{
			if (predicate.test(o)) {
				result.add(o);
			}
		});
		return result;
	}

	/**
	 * Calculate the current WorldRegions of the object according to its position (x,y). <B><U> Example of use </U> :</B>
	 * <li>Set position of a new L2Object (drop, spawn...)</li>
	 * <li>Update position of a L2Object after a movement</li><BR>
	 *
	 * @param point position of the object
	 * @return
	 */
	public Region getRegion(ILocational point) {
		return getRegion(point.getX(), point.getY(), point.getZ());
	}

	public Region getRegion(double x, double y, double z) {
		final int rx = ((int) x >> SHIFT_BY) + OFFSET_X;
		final int ry = ((int) y >> SHIFT_BY) + OFFSET_Y;
		final int rz = ((int) z >> SHIFT_BY_Z) + OFFSET_Z;
		
//		try {
			return getRegionByTile(rx, ry, rz);
//		} catch (ArrayIndexOutOfBoundsException e) {
//			log.warn("Incorrect world region X: {} Y: {} Z: {} for coordinates x: {} y: {} z: {}", (((int) x >> SHIFT_BY) + OFFSET_X), (((int) y >> SHIFT_BY) + OFFSET_Y), (((int) z >> SHIFT_BY_Z) + OFFSET_Z), x, y, z);
//			return null;
//		}
	}

	/**
	 * Returns the whole 3d array containing the world regions used by ZoneData.java to setup zones inside the world regions
	 *
	 * @return
	 */
	public Region[][][] getWorldRegions() {
		return regions;
	}
	
	public Region getRegionByTile(int x, int y, int z) {
		if(!isValidRegion(x, y, z)) {
    		return null;
    	}
		
		if(regions[x][y][z] == null) {
        	synchronized (regions) {
        		createRegionBlock(x, y, z);
			}
        }
		
		return regions[x][y][z];
	}

	/**
	 * Deleted all spawns in the world.
	 */
	public void deleteVisibleNpcSpawns() {
		log.info("Deleting all visible NPC's.");
		for (int x = 0; x <= REGIONS_X; x++) {
			for (int y = 0; y <= REGIONS_Y; y++) {
				for (int z = 0; z <= REGIONS_Z; z++) {
					final Region region = regions[x][y][z];
					if(region == null) {
						continue;
					}
					region.deleteVisibleNpcSpawns();
				}
			}
		}
		log.info("All visible NPC's deleted.");
	}
	
	protected void initAllRegions() {
		synchronized (regions) {
	        for (int i = 0; i <= REGIONS_X; i++) {
	            for (int j = 0; j <= REGIONS_Y; j++) {
	            	for(int k = 0; k <= REGIONS_Z; k++) {
		            	if(!isValidRegion(i, j, k)) {
		            		continue;
		            	}
		            	
		                if(regions[i][j][k] == null) {
		                	createRegionBlock(i, j, k);
		                }
	            	}
	            }
	        }
		}
    }
    
	private void createRegionBlock(int x, int y, int z) {
		for (int a = -1; a <= 1; a++) {
			for (int b = -1; b <= 1; b++) {
				for (int c = -1; c <= 1; c++) {
					if (isValidRegion(x + a, y + b, z + c) && regions[x + a][y + b][z + c] == null) {
						regions[x + a][y + b][z + c] = new Region(id, x + a, y + b, z + c);
					}
				}
			}
		}
	}
	
	 protected void dispose() {
	    	for(Player player : players.values()) {
	    		player.teleToLocation(TeleportWhereType.TOWN, null);
	    	}
	    	players.clear();
	    	
	    	for(WorldObject object : objects.values()) {
	    		if(object instanceof IDeletable) {
	    			final IDeletable deletable = (IDeletable) object;
	    			deletable.deleteMe();
	    		} else {
	    			object.decayMe();
	    		}
	    	}
	    	objects.clear();
	    }
}
