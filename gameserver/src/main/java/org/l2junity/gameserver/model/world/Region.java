package org.l2junity.gameserver.model.world;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.gameserver.ai.CtrlIntention;
import org.l2junity.gameserver.datatables.SpawnTable;
import org.l2junity.gameserver.model.L2Spawn;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Attackable;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.Vehicle;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

/**
 * @author PointerRage
 */
@Slf4j
public class Region {
	@Getter
	private final Map<Integer, WorldObject> objects = new ConcurrentHashMap<>(0);

	@Getter
	private final int worldId;
	@Getter
	private final int x;
	@Getter
	private final int y;
	@Getter
	private final int z;

	@Getter
	private boolean isActive;

	private ScheduledFuture<?> neighborsTask;

	protected Region(int worldId, int x, int y, int z) {
		this.worldId = worldId;
		this.x = x;
		this.y = y;
		this.z = z;
		isActive = GeneralConfig.GRIDS_ALWAYS_ON;
	}

	public void addVisibleObject(WorldObject object) {
		objects.put(object.getObjectId(), object);

		if (object.isPlayable()) {
			if (!isActive() && !GeneralConfig.GRIDS_ALWAYS_ON) {
				startActivation();
			}
		}
	}

	public void removeVisibleObject(WorldObject object) {
		objects.remove(object.getObjectId());

		if (object.isPlayable()) {
			if (isNeighborsEmpty() && !GeneralConfig.GRIDS_ALWAYS_ON) {
				startDeactivation();
			}
		}
	}

	public boolean forEachSurroundingRegion(Predicate<Region> p) {
		final GameWorld world = WorldManager.getInstance().getWorld(worldId);
		if (world == null) {
			return false;
		}

		final Region[][][] regions = world.getRegions();
		for (int x = this.x - 1; x <= this.x + 1; x++) {
			for (int y = this.y - 1; y <= this.y + 1; y++) {
				for (int z = this.z - 1; z <= this.z + 1; z++) {
					if (!WorldData.isValidRegion(x, y, z)) {
						continue;
					}

					final Region region = regions[x][y][z];
					if (region == null) {
						continue;
					}

					if (!p.test(region)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public boolean isSurroundingRegion(Region region) {
		return region != null && x >= region.getX() - 1 && x <= region.getX() + 1
				&& y >= region.getY() - 1 && y <= region.getY() + 1
				&& z >= region.getZ() - 1 && z <= region.getZ() + 1
				&& worldId == region.getWorldId();
	}

	public boolean isNeighborsEmpty() {
		return forEachSurroundingRegion(w -> !(w.isActive() && w.getObjects().values().stream().anyMatch(WorldObject::isPlayable)));
	}

	public void setActive(boolean active) {
		if (isActive == active) {
			return;
		}

		isActive = active;
		switchAI(active);

		log.debug("{} Grid {}", active ? "Starting" : "Stopping", this);
	}

	public void deleteVisibleNpcSpawns() {
		log.debug("Deleting all visible NPC's in Region: {}", this);
		for (WorldObject obj : objects.values()) {
			if (obj instanceof Npc) {
				Npc target = (Npc) obj;
				target.deleteMe();
				L2Spawn spawn = target.getSpawn();
				if (spawn != null) {
					spawn.stopRespawn();
					SpawnTable.getInstance().deleteSpawn(spawn, false);
				}
				log.trace("Removed NPC {}", target);
			}
		}
		log.info("All visible NPC's deleted in Region: {}", this);
	}

	public class NeighborsTask implements Runnable {
		private final boolean isActivating;

		public NeighborsTask(boolean isActivating) {
			this.isActivating = isActivating;
		}

		@Override
		public void run() {
			forEachSurroundingRegion(w ->
			{
				if (isActivating || w.isNeighborsEmpty()) {
					w.setActive(isActivating);
				}
				return true;
			});
		}
	}

	private void switchAI(boolean isOn) {
		int c = 0;
		if (!isOn) {
			for (WorldObject o : objects.values()) {
				if (o instanceof Attackable) {
					c++;
					Attackable mob = (Attackable) o;

					mob.setTarget(null);
					mob.stopMove(null);
					mob.stopAllEffects();

					mob.clearAggroList();
					mob.getAttackByList().clear();

					if (mob.hasAI()) {
						mob.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
						mob.getAI().stopAITask();
					}
				} else if (o instanceof Vehicle) {
					c++;
				}
			}
			log.debug("{} mobs were turned off", c);
		} else {
			for (WorldObject o : objects.values()) {
				if (o instanceof Attackable) {
					c++;
					((Attackable) o).getStatus().startHpMpRegeneration();
				} else if (o instanceof Npc) {
					((Npc) o).startRandomAnimationTask();
				}
			}
			log.debug("{} mobs were turned on", c);
		}
	}

	private void startActivation() {
		setActive(true);

		synchronized (this) {
			if (neighborsTask != null && !neighborsTask.isDone()) {
				neighborsTask.cancel(false);
			}
			neighborsTask = ThreadPool.getInstance().scheduleGeneral(new NeighborsTask(true), GeneralConfig.GRID_NEIGHBOR_TURNON_TIME, TimeUnit.SECONDS);
		}
	}

	private void startDeactivation() {
		synchronized (this) {
			if (neighborsTask != null && !neighborsTask.isDone()) {
				neighborsTask.cancel(false);
			}
			neighborsTask = ThreadPool.getInstance().scheduleGeneral(new NeighborsTask(false), GeneralConfig.GRID_NEIGHBOR_TURNOFF_TIME, TimeUnit.SECONDS);
		}
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}
}
