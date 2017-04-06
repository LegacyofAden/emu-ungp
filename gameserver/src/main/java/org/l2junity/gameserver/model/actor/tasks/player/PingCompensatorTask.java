package org.l2junity.gameserver.model.actor.tasks.player;

import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.s2c.RequestNetPing;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Перед каждым действием игрока нужно иметь актуальный пинг для него,
 * чтобы можно было компенсировать лаги.
 * Данный слушатель основан на регистрации событий АИ игрока на любое его действие или
 * изменения ситуации.
 *
 * @author PointerRage
 */
public class PingCompensatorTask implements Runnable {
	private final static long delta = TimeUnit.SECONDS.toMillis(15);
	private final Player player;
	private ScheduledFuture<?> self;

	private PingCompensatorTask(Player player) {
		this.player = player;
	}

	public void setSelf(ScheduledFuture<?> self) {
		this.self = self;
	}

	@Override
	public void run() {
		if(!player.isPlayer() || player.isOfflineShop() || !player.getClient().isConnected()) {
			self.cancel(false);
			return;
		}
		player.sendPacket(new RequestNetPing(player));
	}

	public static long compensation(Player player, long original) {
		if(!player.getClient().isConnected())
			return original;
		return compensation(player.getClient(), original);
	}

	public static long compensation(GameClient client, long original) {
		return client.getPing() + original + 5l;
	}

	public static void attach(Player player) {
		PingCompensatorTask pc = new PingCompensatorTask(player);
		ScheduledFuture<?> future = ThreadPool.getInstance().scheduleGeneralAtFixedRate(pc, 0, delta, TimeUnit.MILLISECONDS);
		pc.setSelf(future);
	}
}