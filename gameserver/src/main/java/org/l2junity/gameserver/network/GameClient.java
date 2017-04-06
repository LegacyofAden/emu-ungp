package org.l2junity.gameserver.network;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.concurrent.CloseableReentrantLock;
import org.l2junity.commons.model.SessionInfo;
import org.l2junity.commons.network.Client;
import org.l2junity.commons.network.Connection;
import org.l2junity.commons.sql.DatabaseFactory;
import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.GameServer;
import org.l2junity.gameserver.data.sql.impl.CharNameTable;
import org.l2junity.gameserver.data.sql.impl.ClanTable;
import org.l2junity.gameserver.data.xml.impl.SecondaryAuthData;
import org.l2junity.gameserver.enums.CharacterDeleteFailType;
import org.l2junity.gameserver.instancemanager.CommissionManager;
import org.l2junity.gameserver.instancemanager.MailManager;
import org.l2junity.gameserver.instancemanager.MentorManager;
import org.l2junity.gameserver.model.CharSelectInfoPackage;
import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.server.OnPacketSent;
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.network.crypt.BlowFishKeygen;
import org.l2junity.gameserver.network.crypt.GameCrypt;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.s2c.LeaveWorld;
import org.l2junity.gameserver.network.packets.s2c.ServerClose;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.gameserver.security.SecondaryPasswordAuth;
import org.l2junity.gameserver.service.GameServerRMI;
import org.l2junity.gameserver.util.FloodProtectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author ANZO
 * @since 06.04.2017
 */
@Slf4j
public class GameClient extends Client<GameClient> {
	private static final Logger logAccounting = LoggerFactory.getLogger("accounting");

	private final AtomicReference<GameClientState> state = new AtomicReference<>(GameClientState.CONNECTED);

	@Getter
	@Setter
	private SessionInfo sessionInfo;

	@Getter
	@Setter
	private boolean protocolOk;

	@Getter
	@Setter
	private boolean isAuthedGG;

	@Getter
	@Setter
	private int[][] trace;

	@Getter
	private long ping;

	@Setter
	private long lastPingSendTime;

	@Getter
	private String accountName;

	@Getter
	private SecondaryPasswordAuth secondaryAuth;

	@Setter
	private CharSelectInfoPackage[] charSelectionInfo;

	@Getter
	private final CloseableReentrantLock activeCharLock = new CloseableReentrantLock();

	@Getter
	private final FloodProtectors floodProtectors = new FloodProtectors(this);

	@Getter
	@Setter
	private Player activeChar;

	@Getter
	@Setter
	private boolean isDetached;

	public GameClient(Connection<GameClient> connection) {
		super(connection);
		if (connection != null) {
			connection.setCipher(new GameCrypt(this));
		}
		else {
			isDetached = true;
		}
	}

	public boolean compareAndSetState(GameClientState compare, GameClientState set) {
		return state.compareAndSet(compare, set);
	}

	public GameClientState getState() {
		return state.get();
	}

	@Override
	protected void onOpen() {
		logAccounting.debug("Client Connected: {}", this);
	}

	@Override
	protected void onClose() {
		logAccounting.debug("Client Disconnected: {}", this);
		GameServer.getInstance().getRmi().removeAccountInGame(accountName);
		Disconnection.of(this).onDisconnection();
	}

	public byte[] enableCrypt() {
		byte[] key = BlowFishKeygen.getRandomKey();
		getConnection().getCipher().setKey(key);
		return key;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;

		if (SecondaryAuthData.getInstance().isEnabled()) {
			secondaryAuth = new SecondaryPasswordAuth(this);
		}
	}

	public void sendPacket(GameServerPacket packet) {
		if (isDetached || (packet == null)) {
			return;
		}
		//log.info("Sending packet: {}", packet.getClass().getSimpleName());
		getConnection().sendPackets(new GameServerPacket[]{packet});
		EventDispatcher.getInstance().notifyEvent(new OnPacketSent(this, packet));
	}

	public void sendPacket(SystemMessageId smId) {
		sendPacket(SystemMessage.getSystemMessage(smId));
	}

	public void closeForce() {
		state.set(GameClientState.CLOSING);
		getConnection().sendAndClose(null);
	}

	public void close(GameServerPacket packet) {
		if (!isConnected()) {
			return;
		}
		state.set(GameClientState.CLOSING);
		getConnection().sendAndClose(new GameServerPacket[]{packet});
	}

	public void close(boolean toLoginScreen) {
		close(toLoginScreen ? ServerClose.STATIC_PACKET : LeaveWorld.STATIC_PACKET);
	}

	public String getIP() {
		if (isConnected()) {
			return getConnection().getSocketAddress().getAddress().getHostAddress();
		}
		return "not connected";
	}

	public String getHWID() {
		return null;
	}

	public CharSelectInfoPackage getCharSelection(int charslot) {
		if ((charSelectionInfo == null) || (charslot < 0) || (charslot >= charSelectionInfo.length)) {
			return null;
		}
		return charSelectionInfo[charslot];
	}

	public void setPing(long ping)
	{
		this.ping = (ping - lastPingSendTime) / 2;
	}

	/**
	 * Method to handle character deletion
	 *
	 * @param characterSlot
	 * @return a byte:
	 * <li>-1: Error: No char was found for such charslot, caught exception, etc...
	 * <li>0: character is not member of any clan, proceed with deletion
	 * <li>1: character is member of a clan, but not clan leader
	 * <li>2: character is clan leader
	 */
	public CharacterDeleteFailType markToDeleteChar(int characterSlot) {
		final int objectId = getObjectIdForSlot(characterSlot);
		if (objectId < 0) {
			return CharacterDeleteFailType.UNKNOWN;
		}

		if (MentorManager.getInstance().isMentor(objectId)) {
			return CharacterDeleteFailType.MENTOR;
		} else if (MentorManager.getInstance().isMentee(objectId)) {
			return CharacterDeleteFailType.MENTEE;
		} else if (CommissionManager.getInstance().hasCommissionItems(objectId)) {
			return CharacterDeleteFailType.COMMISSION;
		} else if (MailManager.getInstance().getMailsInProgress(objectId) > 0) {
			return CharacterDeleteFailType.MAIL;
		} else {
			final int clanId = CharNameTable.getInstance().getClassIdById(objectId);
			if (clanId > 0) {
				final Clan clan = ClanTable.getInstance().getClan(clanId);
				if (clan != null) {
					if (clan.getLeaderId() == objectId) {
						return CharacterDeleteFailType.PLEDGE_MASTER;
					}
					return CharacterDeleteFailType.PLEDGE_MEMBER;
				}
			}
		}

		if (PlayerConfig.DELETE_DAYS == 0) {
			delete(objectId);
		} else {
			try (java.sql.Connection con = DatabaseFactory.getInstance().getConnection();
				 PreparedStatement ps2 = con.prepareStatement("UPDATE characters SET deletetime=? WHERE charId=?")) {
				ps2.setLong(1, System.currentTimeMillis() + (PlayerConfig.DELETE_DAYS * 86400000L)); // 24*60*60*1000 = 86400000
				ps2.setInt(2, objectId);
				ps2.execute();
			} catch (SQLException e) {
				log.warn("Failed to update char delete time: ", e);
			}
		}

		logAccounting.info("Delete, {}, {}", objectId, this);
		return CharacterDeleteFailType.NONE;
	}

	/**
	 * @param characterSlot
	 * @return
	 */
	private int getObjectIdForSlot(int characterSlot) {
		final CharSelectInfoPackage info = getCharSelection(characterSlot);
		if (info == null) {
			log.warn("{} tried to delete Character in slot {} but no characters exits at that slot.", toString(), characterSlot);
			return -1;
		}
		return info.getObjectId();
	}

	public void restore(int characterSlot) {
		final int objectId = getObjectIdForSlot(characterSlot);
		if (objectId < 0) {
			return;
		}

		try (java.sql.Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement("UPDATE characters SET deletetime=0 WHERE charId=?")) {
			statement.setInt(1, objectId);
			statement.execute();
		} catch (Exception e) {
			log.error("Error restoring character.", e);
		}

		logAccounting.info("Restore, {}, {}", objectId, this);
	}

	public static void delete(int objectId) {
		if (objectId < 0) {
			return;
		}

		CharNameTable.getInstance().removeName(objectId);

		try (java.sql.Connection con = DatabaseFactory.getInstance().getConnection()) {
			try (PreparedStatement ps = con.prepareStatement("DELETE FROM character_contacts WHERE charId=? OR contactId=?")) {
				ps.setInt(1, objectId);
				ps.setInt(2, objectId);
				ps.execute();
			}

			try (PreparedStatement ps = con.prepareStatement("DELETE FROM character_friends WHERE charId=? OR friendId=?")) {
				ps.setInt(1, objectId);
				ps.setInt(2, objectId);
				ps.execute();
			}

			try (PreparedStatement ps = con.prepareStatement("DELETE FROM character_hennas WHERE charId=?")) {
				ps.setInt(1, objectId);
				ps.execute();
			}

			try (PreparedStatement ps = con.prepareStatement("DELETE FROM character_macroses WHERE charId=?")) {
				ps.setInt(1, objectId);
				ps.execute();
			}

			try (PreparedStatement ps = con.prepareStatement("DELETE FROM character_quests WHERE charId=?")) {
				ps.setInt(1, objectId);
				ps.execute();
			}

			try (PreparedStatement ps = con.prepareStatement("DELETE FROM character_recipebook WHERE charId=?")) {
				ps.setInt(1, objectId);
				ps.execute();
			}

			try (PreparedStatement ps = con.prepareStatement("DELETE FROM character_shortcuts WHERE charId=?")) {
				ps.setInt(1, objectId);
				ps.execute();
			}

			try (PreparedStatement ps = con.prepareStatement("DELETE FROM character_skills WHERE charId=?")) {
				ps.setInt(1, objectId);
				ps.execute();
			}

			try (PreparedStatement ps = con.prepareStatement("DELETE FROM character_skills_save WHERE charId=?")) {
				ps.setInt(1, objectId);
				ps.execute();
			}

			try (PreparedStatement ps = con.prepareStatement("DELETE FROM character_subclasses WHERE charId=?")) {
				ps.setInt(1, objectId);
				ps.execute();
			}

			try (PreparedStatement ps = con.prepareStatement("DELETE FROM heroes WHERE charId=?")) {
				ps.setInt(1, objectId);
				ps.execute();
			}

			try (PreparedStatement ps = con.prepareStatement("DELETE FROM olympiad_nobles WHERE charId=?")) {
				ps.setInt(1, objectId);
				ps.execute();
			}

			try (PreparedStatement ps = con.prepareStatement("DELETE FROM pets WHERE item_obj_id IN (SELECT object_id FROM items WHERE items.owner_id=?)")) {
				ps.setInt(1, objectId);
				ps.execute();
			}

			try (PreparedStatement ps = con.prepareStatement("DELETE FROM item_variations WHERE itemId IN (SELECT object_id FROM items WHERE items.owner_id=?)")) {
				ps.setInt(1, objectId);
				ps.execute();
			}

			try (PreparedStatement ps = con.prepareStatement("DELETE FROM item_special_abilities WHERE objectId IN (SELECT object_id FROM items WHERE items.owner_id=?)")) {
				ps.setInt(1, objectId);
				ps.execute();
			}

			try (PreparedStatement ps = con.prepareStatement("DELETE FROM item_variables WHERE id IN (SELECT object_id FROM items WHERE items.owner_id=?)")) {
				ps.setInt(1, objectId);
				ps.execute();
			}

			try (PreparedStatement ps = con.prepareStatement("DELETE FROM items WHERE owner_id=?")) {
				ps.setInt(1, objectId);
				ps.execute();
			}

			try (PreparedStatement ps = con.prepareStatement("DELETE FROM merchant_lease WHERE player_id=?")) {
				ps.setInt(1, objectId);
				ps.execute();
			}

			try (PreparedStatement ps = con.prepareStatement("DELETE FROM character_reco_bonus WHERE charId=?")) {
				ps.setInt(1, objectId);
				ps.execute();
			}

			try (PreparedStatement ps = con.prepareStatement("DELETE FROM character_instance_time WHERE charId=?")) {
				ps.setInt(1, objectId);
				ps.execute();
			}

			try (PreparedStatement ps = con.prepareStatement("DELETE FROM character_variables WHERE charId=?")) {
				ps.setInt(1, objectId);
				ps.execute();
			}

			try (PreparedStatement ps = con.prepareStatement("DELETE FROM characters WHERE charId=?")) {
				ps.setInt(1, objectId);
				ps.execute();
			}
		} catch (Exception e) {
			log.error("Error deleting character.", e);
		}
	}

	public Player load(int characterSlot) {
		final int objectId = getObjectIdForSlot(characterSlot);
		if (objectId < 0) {
			return null;
		}

		Player player = WorldManager.getInstance().getPlayer(objectId);
		if (player != null) {
			// exploit prevention, should not happens in normal way
			log.error("Attempt of double login: {}({}) {}", player.getName(), objectId, getAccountName());
			Disconnection.of(player).defaultSequence(false);
			return null;
		}

		player = Player.load(objectId);
		if (player == null) {
			log.error("Could not restore in slot: {}", characterSlot);
		}

		return player;
	}

	@Override
	public String toString() {
		try {
			final Player player = getActiveChar();
			switch (getState()) {
				case CONNECTED:
					return "[IP: " + getIP() + "]";
				case AUTHENTICATED:
					return "[Account: " + getAccountName() + " - IP: " + getIP() + "]";
				case IN_GAME:
					return "[Character: " + (player == null ? "disconnected" : player.getName() + "[" + player.getObjectId() + "]") + " - Account: " + getAccountName() + " - IP: " + getIP() + "]";
				default:
					throw new IllegalStateException("Missing state on switch");
			}
		} catch (NullPointerException e) {
			return "[Character read failed due to disconnect]";
		}
	}
}