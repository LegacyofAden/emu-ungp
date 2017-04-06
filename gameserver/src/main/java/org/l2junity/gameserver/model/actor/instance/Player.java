/*
 * Copyright (C) 2004-2015 L2J Unity
 * 
 * This file is part of L2J Unity.
 * 
 * L2J Unity is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J Unity is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.l2junity.gameserver.model.actor.instance;

import lombok.Getter;
import lombok.Setter;
import org.l2junity.commons.sql.DatabaseFactory;
import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.commons.util.CommonUtil;
import org.l2junity.commons.util.Rnd;
import org.l2junity.core.configs.*;
import org.l2junity.gameserver.GameServer;
import org.l2junity.gameserver.ItemsAutoDestroy;
import org.l2junity.gameserver.ai.CharacterAI;
import org.l2junity.gameserver.ai.CtrlIntention;
import org.l2junity.gameserver.ai.PlayerAI;
import org.l2junity.gameserver.ai.SummonAI;
import org.l2junity.gameserver.cache.WarehouseCacheManager;
import org.l2junity.gameserver.communitybbs.BB.Forum;
import org.l2junity.gameserver.communitybbs.Manager.ForumsBBSManager;
import org.l2junity.gameserver.data.sql.impl.CharNameTable;
import org.l2junity.gameserver.data.sql.impl.CharSummonTable;
import org.l2junity.gameserver.data.sql.impl.ClanTable;
import org.l2junity.gameserver.data.xml.impl.*;
import org.l2junity.gameserver.datatables.ItemTable;
import org.l2junity.gameserver.engines.IdFactory;
import org.l2junity.gameserver.enums.*;
import org.l2junity.gameserver.geodata.GeoData;
import org.l2junity.gameserver.handler.IItemHandler;
import org.l2junity.gameserver.handler.ItemHandler;
import org.l2junity.gameserver.instancemanager.*;
import org.l2junity.gameserver.model.*;
import org.l2junity.gameserver.model.ClanWar.ClanWarState;
import org.l2junity.gameserver.model.Party.MessageType;
import org.l2junity.gameserver.model.actor.*;
import org.l2junity.gameserver.model.actor.appearance.PcAppearance;
import org.l2junity.gameserver.model.actor.request.AbstractRequest;
import org.l2junity.gameserver.model.actor.request.SayuneRequest;
import org.l2junity.gameserver.model.actor.stat.PcStat;
import org.l2junity.gameserver.model.actor.status.PcStatus;
import org.l2junity.gameserver.model.actor.tasks.player.*;
import org.l2junity.gameserver.model.actor.templates.PcTemplate;
import org.l2junity.gameserver.model.actor.transform.Transform;
import org.l2junity.gameserver.model.base.ClassId;
import org.l2junity.gameserver.model.base.SubClass;
import org.l2junity.gameserver.model.ceremonyofchaos.CeremonyOfChaosEvent;
import org.l2junity.gameserver.model.cubic.CubicInstance;
import org.l2junity.gameserver.model.debugger.DebugType;
import org.l2junity.gameserver.model.effects.L2EffectType;
import org.l2junity.gameserver.model.entity.*;
import org.l2junity.gameserver.model.eventengine.AbstractEvent;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.character.player.*;
import org.l2junity.gameserver.model.events.impl.restriction.*;
import org.l2junity.gameserver.model.events.returns.BooleanReturn;
import org.l2junity.gameserver.model.events.returns.CombatStateReturn;
import org.l2junity.gameserver.model.events.returns.LongReturn;
import org.l2junity.gameserver.model.events.returns.TerminateReturn;
import org.l2junity.gameserver.model.holders.*;
import org.l2junity.gameserver.model.instancezone.Instance;
import org.l2junity.gameserver.model.interfaces.ILocational;
import org.l2junity.gameserver.model.itemcontainer.*;
import org.l2junity.gameserver.model.items.*;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.items.type.*;
import org.l2junity.gameserver.model.matching.MatchingRoom;
import org.l2junity.gameserver.model.olympiad.OlympiadGameManager;
import org.l2junity.gameserver.model.olympiad.OlympiadGameTask;
import org.l2junity.gameserver.model.olympiad.OlympiadManager;
import org.l2junity.gameserver.model.punishment.PunishmentAffect;
import org.l2junity.gameserver.model.punishment.PunishmentTask;
import org.l2junity.gameserver.model.punishment.PunishmentType;
import org.l2junity.gameserver.model.quest.Quest;
import org.l2junity.gameserver.model.quest.QuestState;
import org.l2junity.gameserver.model.skills.*;
import org.l2junity.gameserver.model.stats.*;
import org.l2junity.gameserver.model.variables.AccountVariables;
import org.l2junity.gameserver.model.variables.PlayerVariables;
import org.l2junity.gameserver.model.world.ItemStorage;
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.model.zone.ZoneId;
import org.l2junity.gameserver.model.zone.ZoneType;
import org.l2junity.gameserver.network.Disconnection;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.s2c.*;
import org.l2junity.gameserver.network.packets.s2c.ability.ExAcquireAPSkillList;
import org.l2junity.gameserver.network.packets.s2c.commission.ExResponseCommissionInfo;
import org.l2junity.gameserver.network.packets.s2c.friend.L2FriendStatus;
import org.l2junity.gameserver.network.packets.s2c.string.CustomMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.gameserver.taskmanager.AttackStanceTaskManager;
import org.l2junity.gameserver.util.Broadcast;
import org.l2junity.gameserver.util.EnumIntBitmask;
import org.l2junity.gameserver.util.FloodProtectors;
import org.l2junity.gameserver.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * This class represents all player characters in the world.<br>
 * There is always a client-thread connected to this (except if a player-store is activated upon logout).
 */
public final class Player extends Playable {
	private static final Logger LOGGER = LoggerFactory.getLogger(Player.class);

	// Character Skill SQL String Definitions:
	private static final String RESTORE_SKILLS_FOR_CHAR = "SELECT skill_id,skill_level,skill_sub_level FROM character_skills WHERE charId=? AND class_index=?";
	private static final String ADD_NEW_SKILL = "INSERT INTO character_skills (charId,skill_id,skill_level,skill_sub_level,class_index) VALUES (?,?,?,?,?)";
	private static final String UPDATE_CHARACTER_SKILL_LEVEL = "UPDATE character_skills SET skill_level=?, skill_sub_level=?  WHERE skill_id=? AND charId=? AND class_index=?";
	private static final String ADD_NEW_SKILLS = "REPLACE INTO character_skills (charId,skill_id,skill_level,skill_sub_level,class_index) VALUES (?,?,?,?,?)";
	private static final String DELETE_SKILL_FROM_CHAR = "DELETE FROM character_skills WHERE skill_id=? AND charId=? AND class_index=?";
	private static final String DELETE_CHAR_SKILLS = "DELETE FROM character_skills WHERE charId=? AND class_index=?";

	// Character Skill Save SQL String Definitions:
	private static final String ADD_SKILL_SAVE = "INSERT INTO character_skills_save (charId,skill_id,skill_level,skill_sub_level,remaining_time,reuse_delay,systime,restore_type,class_index,buff_index) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private static final String RESTORE_SKILL_SAVE = "SELECT skill_id,skill_level,skill_sub_level,remaining_time, reuse_delay, systime, restore_type FROM character_skills_save WHERE charId=? AND class_index=? ORDER BY buff_index ASC";
	private static final String DELETE_SKILL_SAVE = "DELETE FROM character_skills_save WHERE charId=? AND class_index=?";

	// Character Item Reuse Time String Definition:
	private static final String ADD_ITEM_REUSE_SAVE = "INSERT INTO character_item_reuse_save (charId,itemId,itemObjId,reuseDelay,systime) VALUES (?,?,?,?,?)";
	private static final String RESTORE_ITEM_REUSE_SAVE = "SELECT charId,itemId,itemObjId,reuseDelay,systime FROM character_item_reuse_save WHERE charId=?";
	private static final String DELETE_ITEM_REUSE_SAVE = "DELETE FROM character_item_reuse_save WHERE charId=?";

	// Character Character SQL String Definitions:
	private static final String INSERT_CHARACTER = "INSERT INTO characters (account_name,charId,char_name,level,maxHp,curHp,maxCp,curCp,maxMp,curMp,face,hairStyle,hairColor,sex,exp,sp,reputation,fame,raidbossPoints,pvpkills,pkkills,clanid,race,classid,deletetime,cancraft,title,title_color,online,clan_privs,wantspeace,base_class,nobless,power_grade,vitality_points,createDate) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_CHARACTER = "UPDATE characters SET level=?,maxHp=?,curHp=?,maxCp=?,curCp=?,maxMp=?,curMp=?,face=?,hairStyle=?,hairColor=?,sex=?,heading=?,x=?,y=?,z=?,exp=?,expBeforeDeath=?,sp=?,reputation=?,fame=?,raidbossPoints=?,pvpkills=?,pkkills=?,clanid=?,race=?,classid=?,deletetime=?,title=?,title_color=?,online=?,clan_privs=?,wantspeace=?,base_class=?,onlinetime=?,nobless=?,power_grade=?,subpledge=?,lvl_joined_academy=?,apprentice=?,sponsor=?,clan_join_expiry_time=?,clan_create_expiry_time=?,char_name=?,bookmarkslot=?,vitality_points=?,language=? WHERE charId=?";
	private static final String UPDATE_CHARACTER_ACCESS = "UPDATE characters SET accesslevel = ? WHERE charId = ?";
	private static final String RESTORE_CHARACTER = "SELECT * FROM characters WHERE charId=?";

	// Character Teleport Bookmark:
	private static final String INSERT_TP_BOOKMARK = "INSERT INTO character_tpbookmark (charId,Id,x,y,z,icon,tag,name) values (?,?,?,?,?,?,?,?)";
	private static final String UPDATE_TP_BOOKMARK = "UPDATE character_tpbookmark SET icon=?,tag=?,name=? where charId=? AND Id=?";
	private static final String RESTORE_TP_BOOKMARK = "SELECT Id,x,y,z,icon,tag,name FROM character_tpbookmark WHERE charId=?";
	private static final String DELETE_TP_BOOKMARK = "DELETE FROM character_tpbookmark WHERE charId=? AND Id=?";

	// Character Subclass SQL String Definitions:
	private static final String RESTORE_CHAR_SUBCLASSES = "SELECT class_id,exp,sp,level,vitality_points,class_index,dual_class FROM character_subclasses WHERE charId=? ORDER BY class_index ASC";
	private static final String ADD_CHAR_SUBCLASS = "INSERT INTO character_subclasses (charId,class_id,exp,sp,level,vitality_points,class_index,dual_class) VALUES (?,?,?,?,?,?,?,?)";
	private static final String UPDATE_CHAR_SUBCLASS = "UPDATE character_subclasses SET exp=?,sp=?,level=?,vitality_points=?,class_id=?,dual_class=? WHERE charId=? AND class_index =?";
	private static final String DELETE_CHAR_SUBCLASS = "DELETE FROM character_subclasses WHERE charId=? AND class_index=?";

	// Character Henna SQL String Definitions:
	private static final String RESTORE_CHAR_HENNAS = "SELECT slot,symbol_id FROM character_hennas WHERE charId=? AND class_index=?";
	private static final String ADD_CHAR_HENNA = "INSERT INTO character_hennas (charId,symbol_id,slot,class_index) VALUES (?,?,?,?)";
	private static final String DELETE_CHAR_HENNA = "DELETE FROM character_hennas WHERE charId=? AND slot=? AND class_index=?";
	private static final String DELETE_CHAR_HENNAS = "DELETE FROM character_hennas WHERE charId=? AND class_index=?";

	// Character Shortcut SQL String Definitions:
	private static final String DELETE_CHAR_SHORTCUTS = "DELETE FROM character_shortcuts WHERE charId=? AND class_index=?";

	// Character Recipe List Save
	private static final String DELETE_CHAR_RECIPE_SHOP = "DELETE FROM character_recipeshoplist WHERE charId=?";
	private static final String INSERT_CHAR_RECIPE_SHOP = "REPLACE INTO character_recipeshoplist (`charId`, `recipeId`, `price`, `index`) VALUES (?, ?, ?, ?)";
	private static final String RESTORE_CHAR_RECIPE_SHOP = "SELECT * FROM character_recipeshoplist WHERE charId=? ORDER BY `index`";

	// Character Faction SQL String Definitions:
	private static final String ADD_FACTION = "REPLACE INTO character_factions (charId, faction_name, points) VALUES (?,?,?)";
	private static final String RESTORE_FACTIONS = "SELECT faction_name, points FROM character_factions WHERE charId=?";

	private static final String COND_OVERRIDE_KEY = "cond_override";

	public static final int ID_NONE = -1;

	public static final int REQUEST_TIMEOUT = 15;

	@Getter
	@Setter
	private GameClient client;

	private final String _accountName;
	private long _deleteTimer;
	private Calendar _createDate = Calendar.getInstance();

	private volatile boolean _isOnline = false;
	private long _onlineTime;
	private long _onlineBeginTime;
	private long _lastAccess;
	private long _uptime;

	private final ReentrantLock _subclassLock = new ReentrantLock();
	protected int _baseClass;
	protected int _activeClass;
	protected int _classIndex = 0;

	/**
	 * data for mounted pets
	 */
	private int _controlItemId;
	private PetData _data;
	private PetLevelData _leveldata;
	private int _curFeed;
	protected Future<?> _mountFeedTask;
	private ScheduledFuture<?> _dismountTask;
	private boolean _petItems = false;

	/**
	 * The list of sub-classes this character has.
	 */
	private volatile Map<Integer, SubClass> _subClasses;

	private final PcAppearance _appearance;

	/**
	 * The Experience of the L2PcInstance before the last Death Penalty
	 */
	private long _expBeforeDeath;

	/**
	 * The number of player killed during a PvP (the player killed was PvP Flagged)
	 */
	private int _pvpKills;

	/**
	 * The PK counter of the L2PcInstance (= Number of non PvP Flagged player killed)
	 */
	private int _pkKills;

	/**
	 * The PvP Flag state of the L2PcInstance (0=White, 1=Purple)
	 */
	private byte _pvpFlag;

	/**
	 * The Fame of this L2PcInstance
	 */
	private int _fame;
	private ScheduledFuture<?> _fameTask;

	/**
	 * The Raidboss points of this Player
	 */
	private int _raidbossPoints;

	private volatile ScheduledFuture<?> _teleportWatchdog;

	/**
	 * The Siege state of the L2PcInstance
	 */
	private byte _siegeState = 0;

	/**
	 * The id of castle/fort which the L2PcInstance is registered for siege
	 */
	private int _siegeSide = 0;

	private int _curWeightPenalty = 0;

	private int _lastCompassZone; // the last compass zone update send to the client

	private final ContactList _contactList = new ContactList(this);

	private int _bookmarkslot = 0; // The Teleport Bookmark Slot

	private final Map<Integer, TeleportBookmark> _tpbookmarks = new ConcurrentSkipListMap<>();

	private boolean _canFeed;
	private boolean _isInSiege;
	private boolean _isInHideoutSiege = false;

	/**
	 * Olympiad
	 */
	private boolean _inOlympiadMode = false;
	private boolean _OlympiadStart = false;
	private int _olympiadGameId = -1;
	private int _olympiadSide = -1;
	/**
	 * Olympiad buff count.
	 */
	private int _olyBuffsCount = 0;

	/**
	 * Duel
	 */
	private boolean _isInDuel = false;
	private int _duelState = Duel.DUELSTATE_NODUEL;
	private int _duelId = 0;
	private SystemMessageId _noDuelReason = SystemMessageId.THERE_IS_NO_OPPONENT_TO_RECEIVE_YOUR_CHALLENGE_FOR_A_DUEL;

	/**
	 * Faceoff
	 */
	private int _attackerObjId = 0;

	/**
	 * Boat and AirShip
	 */
	private Vehicle _vehicle = null;
	private Location _inVehiclePosition;

	private MountType _mountType = MountType.NONE;
	private int _mountNpcId;
	private int _mountLevel;
	/**
	 * Store object used to summon the strider you are mounting
	 **/
	private int _mountObjectID = 0;

	private AdminTeleportType _teleportType = AdminTeleportType.NORMAL;

	private volatile boolean _isCrafting;

	private long _offlineShopStart = 0;

	/**
	 * The table containing all L2RecipeList of the L2PcInstance
	 */
	private final Map<Integer, RecipeHolder> _dwarvenRecipeBook = new ConcurrentSkipListMap<>();
	private final Map<Integer, RecipeHolder> _commonRecipeBook = new ConcurrentSkipListMap<>();

	/**
	 * Premium Items
	 */
	private final Map<Integer, PremiumItem> _premiumItems = new ConcurrentSkipListMap<>();

	/**
	 * True if the L2PcInstance is sitting
	 */
	private boolean _waitTypeSitting = false;

	/**
	 * Location before entering Observer Mode
	 */
	private Location _lastLoc;
	private boolean _observerMode = false;

	/**
	 * Stored from last ValidatePosition
	 **/
	private final Location _lastServerPosition = new Location(0, 0, 0);

	/**
	 * The number of recommendation obtained by the L2PcInstance
	 */
	private int _recomHave; // how much I was recommended by others
	/**
	 * The number of recommendation that the L2PcInstance can give
	 */
	private int _recomLeft; // how many recommendations I can give to others
	/**
	 * Recommendation task
	 **/
	private ScheduledFuture<?> _recoGiveTask;
	/**
	 * Recommendation Two Hours bonus
	 **/
	protected boolean _recoTwoHoursGiven = false;

	private ScheduledFuture<?> _onlineTimeUpdateTask;

	private final PcInventory _inventory = new PcInventory(this);
	private final PcFreight _freight = new PcFreight(this);
	private PcWarehouse _warehouse;
	private PcRefund _refund;

	private PrivateStoreType _privateStoreType = PrivateStoreType.NONE;

	private TradeList _activeTradeList;
	private ItemContainer _activeWarehouse;
	private volatile Map<Integer, Long> _manufactureItems;
	private String _storeName = "";
	private TradeList _sellList;
	private TradeList _buyList;

	// Multisell
	private PreparedMultisellListHolder _currentMultiSell = null;

	private NobleStatus _noble = NobleStatus.NONE;
	private boolean _hero = false;
	private boolean _trueHero = false;

	/**
	 * The L2FolkInstance corresponding to the last Folk which one the player talked.
	 */
	private Npc _lastFolkNpc = null;

	/**
	 * Last NPC Id talked on a quest
	 */
	private int _questNpcObject = 0;

	/**
	 * The table containing all Quests began by the L2PcInstance
	 */
	private final Map<String, QuestState> _quests = new ConcurrentHashMap<>();

	/**
	 * The list containing all shortCuts of this player.
	 */
	private final ShortCuts _shortCuts = new ShortCuts(this);

	/**
	 * The list containing all macros of this player.
	 */
	private final MacroList _macros = new MacroList(this);

	private final Set<Player> _snoopListener = ConcurrentHashMap.newKeySet();
	private final Set<Player> _snoopedPlayer = ConcurrentHashMap.newKeySet();

	// hennas
	private final Henna[] _henna = new Henna[3];
	private final Map<BaseStats, Integer> _hennaBaseStats = new ConcurrentHashMap<>();

	/**
	 * The Pet of the L2PcInstance
	 */
	private PetInstance _pet = null;
	/**
	 * Servitors of the L2PcInstance
	 */
	private volatile Map<Integer, Summon> _servitors = null;
	/**
	 * The L2Agathion of the L2PcInstance
	 */
	private int _agathionId = 0;
	// apparently, a L2PcInstance CAN have both a summon AND a tamed beast at the same time!!
	// after Freya players can control more than one tamed beast
	private volatile Set<TamedBeastInstance> _tamedBeast = null;

	// client radar
	// TODO: This needs to be better integrated and saved/loaded
	private final Radar _radar;

	private MatchingRoom _matchingRoom;

	// Clan related attributes
	/**
	 * The Clan Identifier of the L2PcInstance
	 */
	private int _clanId;

	/**
	 * The Clan object of the L2PcInstance
	 */
	private Clan _clan;

	/**
	 * Apprentice and Sponsor IDs
	 */
	private int _apprentice = 0;
	private int _sponsor = 0;

	private long _clanJoinExpiryTime;
	private long _clanCreateExpiryTime;

	private int _powerGrade = 0;
	private volatile EnumIntBitmask<ClanPrivilege> _clanPrivileges = new EnumIntBitmask<>(ClanPrivilege.class, false);

	/**
	 * L2PcInstance's pledge class (knight, Baron, etc.)
	 */
	private int _pledgeClass = 0;
	private int _pledgeType = 0;

	/**
	 * Level at which the player joined the clan as an academy member
	 */
	private int _lvlJoinedAcademy = 0;

	private int _wantsPeace = 0;

	// charges
	private final AtomicInteger _charges = new AtomicInteger();
	private ScheduledFuture<?> _chargeTask = null;

	// Absorbed Souls
	private int _souls = 0;
	private ScheduledFuture<?> _soulTask = null;

	// WorldPosition used by TARGET_SIGNET_GROUND
	private ILocational _currentSkillWorldPosition;

	private AccessLevel _accessLevel;

	private boolean _messageRefusal = false; // message refusal mode

	private boolean _silenceMode = false; // silence mode
	private List<Integer> _silenceModeExcluded; // silence mode
	private boolean _dietMode = false; // ignore weight penalty
	private boolean _tradeRefusal = false; // Trade refusal
	private boolean _exchangeRefusal = false; // Exchange refusal

	private Party _party;

	// this is needed to find the inviting player for Party response
	// there can only be one active party request at once
	private Player _activeRequester;
	private long _requestExpireTime = 0;
	private final L2Request _request = new L2Request(this);

	// Used for protection after teleport
	private long _protectEndTime = 0;

	private volatile Map<Integer, ExResponseCommissionInfo> _lastCommissionInfos;

	@SuppressWarnings("rawtypes")
	private volatile Map<Class<? extends AbstractEvent>, AbstractEvent<?>> _events;

	public boolean isSpawnProtected() {
		return _protectEndTime > GameTimeManager.getInstance().getGameTicks();
	}

	private long _teleportProtectEndTime = 0;

	public boolean isTeleportProtected() {
		return _teleportProtectEndTime > GameTimeManager.getInstance().getGameTicks();
	}

	// protects a char from aggro mobs when getting up from fake death
	private long _recentFakeDeathEndTime = 0;

	/**
	 * The fists L2Weapon of the L2PcInstance (used when no weapon is equipped)
	 */
	private Weapon _fistsWeaponItem;

	private final Map<Integer, String> _chars = new ConcurrentSkipListMap<>();

	// private byte _updateKnownCounter = 0;

	private int _createItemLevel;
	private int _createCommonItemLevel;
	private ItemGrade _crystallizeGrade = ItemGrade.NONE;
	private CrystalType _expertiseLevel = CrystalType.NONE;
	private int _expertiseArmorPenalty = 0;
	private int _expertiseWeaponPenalty = 0;
	private int _expertisePenaltyBonus = 0;

	private volatile Map<Class<? extends AbstractRequest>, AbstractRequest> _requests;

	protected boolean _inventoryDisable = false;
	/**
	 * Player's cubics.
	 */
	private final Map<Integer, CubicInstance> _cubics = new ConcurrentSkipListMap<>();
	/**
	 * Active shots.
	 */
	protected Set<Integer> _activeSoulShots = ConcurrentHashMap.newKeySet();

	public final ReentrantLock soulShotLock = new ReentrantLock();

	private byte _handysBlockCheckerEventArena = -1;

	/**
	 * new loto ticket
	 **/
	private final int _loto[] = new int[5];
	// public static int _loto_nums[] = {0,1,2,3,4,5,6,7,8,9,};
	/**
	 * new race ticket
	 **/
	private final int _race[] = new int[2];

	private final BlockList _blockList = new BlockList(this);

	private volatile Map<Integer, Skill> _transformSkills;
	private ScheduledFuture<?> _taskRentPet;
	private ScheduledFuture<?> _taskWater;

	/**
	 * Last Html Npcs, 0 = last html was not bound to an npc
	 */
	private final int[] _htmlActionOriginObjectIds = new int[HtmlActionScope.values().length];
	/**
	 * Origin of the last incoming html action request.<br>
	 * This can be used for htmls continuing the conversation with an npc.
	 */
	private int _lastHtmlActionOriginObjId;

	/**
	 * Bypass validations
	 */
	@SuppressWarnings("unchecked")
	private final LinkedList<String>[] _htmlActionCaches = new LinkedList[HtmlActionScope.values().length];

	private Forum _forumMail;
	private Forum _forumMemo;

	/**
	 * Skills queued because a skill is already in progress
	 */
	private SkillUseHolder _queuedSkill;

	private int _cursedWeaponEquippedId = 0;
	private boolean _combatFlagEquippedId = false;

	private boolean _canRevive = true;
	private int _reviveRequested = 0;
	private double _revivePower = 0;
	private boolean _revivePet = false;

	private double _cpUpdateIncCheck = .0;
	private double _cpUpdateDecCheck = .0;
	private double _cpUpdateInterval = .0;
	private double _mpUpdateIncCheck = .0;
	private double _mpUpdateDecCheck = .0;
	private double _mpUpdateInterval = .0;

	private double _originalCp = .0;
	private double _originalHp = .0;
	private double _originalMp = .0;

	/**
	 * Char Coords from Client
	 */
	private int _clientX;
	private int _clientY;
	private int _clientZ;
	private int _clientHeading;

	// during fall validations will be disabled for 10 ms.
	private static final int FALLING_VALIDATION_DELAY = 10000;
	private volatile long _fallingTimestamp = 0;

	private int _multiSocialTarget = 0;
	private int _multiSociaAction = 0;

	private MovieHolder _movieHolder = null;

	private String _adminConfirmCmd = null;

	private volatile long _lastItemAuctionInfoRequest = 0;

	private Future<?> _PvPRegTask;

	private long _pvpFlagLasts;

	private long _notMoveUntil = 0;

	/**
	 * Map containing all custom skills of this player.
	 */
	private Map<Integer, Skill> _customSkills = null;

	private volatile int _actionMask;

	private int _questZoneId = -1;

	private final Fishing _fishing = new Fishing(this);

	private Future<?> _autoSaveTask = null;

	private volatile Map<Faction, Integer> _factionScore;

	public void setPvpFlagLasts(long time) {
		_pvpFlagLasts = time;
	}

	public long getPvpFlagLasts() {
		return _pvpFlagLasts;
	}

	public void startPvPFlag() {
		updatePvPFlag(1);

		if (_PvPRegTask == null) {
			_PvPRegTask = ThreadPool.getInstance().scheduleGeneralAtFixedRate(new PvPFlagTask(this), 1000, 1000, TimeUnit.MILLISECONDS);
		}
	}

	public void stopPvpRegTask() {
		if (_PvPRegTask != null) {
			_PvPRegTask.cancel(true);
			_PvPRegTask = null;
		}
	}

	public void stopPvPFlag() {
		stopPvpRegTask();

		updatePvPFlag(0);

		_PvPRegTask = null;
	}

	// Character UI
	private UIKeysSettings _uiKeySettings;

	// Save responder name for log it
	private String _lastPetitionGmName = null;

	private boolean _hasCharmOfCourage = false;

	private final Set<Integer> _whisperers = ConcurrentHashMap.newKeySet();

	// Selling buffs system
	private boolean _isSellingBuffs = false;
	private List<SellBuffHolder> _sellingBuffs = null;

	public boolean isSellingBuffs() {
		return _isSellingBuffs;
	}

	public void setIsSellingBuffs(boolean val) {
		_isSellingBuffs = val;
	}

	public List<SellBuffHolder> getSellingBuffs() {
		if (_sellingBuffs == null) {
			_sellingBuffs = new ArrayList<>();
		}
		return _sellingBuffs;
	}

	/**
	 * Create a new L2PcInstance and add it in the characters table of the database.<br>
	 * <B><U> Actions</U> :</B>
	 * <ul>
	 * <li>Create a new L2PcInstance with an account name</li>
	 * <li>Set the name, the Hair Style, the Hair Color and the Face type of the L2PcInstance</li>
	 * <li>Add the player in the characters table of the database</li>
	 * </ul>
	 *
	 * @param template    The PcTemplate to apply to the L2PcInstance
	 * @param accountName The name of the L2PcInstance
	 * @param name        The name of the L2PcInstance
	 * @param app         the player's appearance
	 * @return The L2PcInstance added to the database or null
	 */
	public static Player create(PcTemplate template, String accountName, String name, PcAppearance app) {
		// Create a new L2PcInstance with an account name
		Player player = new Player(template, accountName, app);
		// Set the name of the L2PcInstance
		player.setName(name);
		// Set access level
		player.setAccessLevel(0, false, false);
		// Set Character's create time
		player.setCreateDate(Calendar.getInstance());
		// Set the base class ID to that of the actual class ID.
		player.setBaseClass(player.getClassId());
		// Give 20 recommendations
		player.setRecomLeft(20);
		// Add the player in the characters table of the database
		if (player.createDb()) {
			if (GeneralConfig.CACHE_CHAR_NAMES) {
				CharNameTable.getInstance().addName(player);
			}
			return player;
		}
		return null;
	}

	public String getAccountName() {
		if (getClient() == null) {
			return getAccountNamePlayer();
		}
		return getClient().getAccountName();
	}

	public String getAccountNamePlayer() {
		return _accountName;
	}

	public Map<Integer, String> getAccountChars() {
		return _chars;
	}

	public int getRelation(Player target) {
		final Clan clan = getClan();
		final Party party = getParty();
		final Clan targetClan = target.getClan();
		int result = 0;

		if (clan != null) {
			result |= RelationChanged.RELATION_CLAN_MEMBER;
			if (clan == target.getClan()) {
				result |= RelationChanged.RELATION_CLAN_MATE;
			}
			if (getAllyId() != 0) {
				result |= RelationChanged.RELATION_ALLY_MEMBER;
			}
		}
		if (isClanLeader()) {
			result |= RelationChanged.RELATION_LEADER;
		}
		if ((party != null) && (party == target.getParty())) {
			result |= RelationChanged.RELATION_HAS_PARTY;
			for (int i = 0; i < party.getMembers().size(); i++) {
				if (party.getMembers().get(i) != this) {
					continue;
				}
				switch (i) {
					case 0:
						result |= RelationChanged.RELATION_PARTYLEADER; // 0x10
						break;
					case 1:
						result |= RelationChanged.RELATION_PARTY4; // 0x8
						break;
					case 2:
						result |= RelationChanged.RELATION_PARTY3 + RelationChanged.RELATION_PARTY2 + RelationChanged.RELATION_PARTY1; // 0x7
						break;
					case 3:
						result |= RelationChanged.RELATION_PARTY3 + RelationChanged.RELATION_PARTY2; // 0x6
						break;
					case 4:
						result |= RelationChanged.RELATION_PARTY3 + RelationChanged.RELATION_PARTY1; // 0x5
						break;
					case 5:
						result |= RelationChanged.RELATION_PARTY3; // 0x4
						break;
					case 6:
						result |= RelationChanged.RELATION_PARTY2 + RelationChanged.RELATION_PARTY1; // 0x3
						break;
					case 7:
						result |= RelationChanged.RELATION_PARTY2; // 0x2
						break;
					case 8:
						result |= RelationChanged.RELATION_PARTY1; // 0x1
						break;
				}
			}
		}
		if (getSiegeState() != 0) {
			result |= RelationChanged.RELATION_INSIEGE;
			if (getSiegeState() != target.getSiegeState()) {
				result |= RelationChanged.RELATION_ENEMY;
			} else {
				result |= RelationChanged.RELATION_ALLY;
			}
			if (getSiegeState() == 1) {
				result |= RelationChanged.RELATION_ATTACKER;
			}
		}
		if ((clan != null) && (targetClan != null)) {
			if (!target.isAcademyMember() && !isAcademyMember()) {
				ClanWar war = clan.getWarWith(target.getClan().getId());
				if (war != null) {
					switch (war.getState()) {
						case DECLARATION:
						case BLOOD_DECLARATION: {
							result |= RelationChanged.RELATION_DECLARED_WAR;
							break;
						}
						case MUTUAL: {
							result |= RelationChanged.RELATION_DECLARED_WAR;
							result |= RelationChanged.RELATION_MUTUAL_WAR;
							break;
						}
					}
				}
			}
		}
		if (getBlockCheckerArena() != -1) {
			result |= RelationChanged.RELATION_INSIEGE;
			ArenaParticipantsHolder holder = HandysBlockCheckerManager.getInstance().getHolder(getBlockCheckerArena());
			if (holder.getPlayerTeam(this) == 0) {
				result |= RelationChanged.RELATION_ENEMY;
			} else {
				result |= RelationChanged.RELATION_ALLY;
			}
			result |= RelationChanged.RELATION_ATTACKER;
		}
		return result;
	}

	/**
	 * Retrieve a L2PcInstance from the characters table of the database and add it in _allObjects of the L2world (call restore method).<br>
	 * <B><U> Actions</U> :</B>
	 * <ul>
	 * <li>Retrieve the L2PcInstance from the characters table of the database</li>
	 * <li>Add the L2PcInstance object in _allObjects</li>
	 * <li>Set the x,y,z position of the L2PcInstance and make it invisible</li>
	 * <li>Update the overloaded status of the L2PcInstance</li>
	 * </ul>
	 *
	 * @param objectId Identifier of the object to initialized
	 * @return The L2PcInstance loaded from the database
	 */
	public static Player load(int objectId) {
		return restore(objectId);
	}

	private void initPcStatusUpdateValues() {
		_cpUpdateInterval = getMaxCp() / 352.0;
		_cpUpdateIncCheck = getMaxCp();
		_cpUpdateDecCheck = getMaxCp() - _cpUpdateInterval;
		_mpUpdateInterval = getMaxMp() / 352.0;
		_mpUpdateIncCheck = getMaxMp();
		_mpUpdateDecCheck = getMaxMp() - _mpUpdateInterval;
	}

	/**
	 * Constructor of L2PcInstance (use L2Character constructor).<br>
	 * <B><U> Actions</U> :</B>
	 * <ul>
	 * <li>Call the L2Character constructor to create an empty _skills slot and copy basic Calculator set to this L2PcInstance</li>
	 * <li>Set the name of the L2PcInstance</li>
	 * </ul>
	 * <FONT COLOR=#FF0000><B> <U>Caution</U> : This method SET the level of the L2PcInstance to 1</B></FONT>
	 *
	 * @param objectId    Identifier of the object to initialized
	 * @param template    The PcTemplate to apply to the L2PcInstance
	 * @param accountName The name of the account including this L2PcInstance
	 * @param app
	 */
	private Player(int objectId, PcTemplate template, String accountName, PcAppearance app) {
		super(objectId, template);
		setInstanceType(InstanceType.L2PcInstance);
		super.initCharStatusUpdateValues();
		initPcStatusUpdateValues();

		for (int i = 0; i < _htmlActionCaches.length; ++i) {
			_htmlActionCaches[i] = new LinkedList<>();
		}

		_accountName = accountName;
		app.setOwner(this);
		_appearance = app;

		// Create an AI
		getAI();

		// Create a L2Radar object
		_radar = new Radar(this);
	}

	/**
	 * Creates a player.
	 *
	 * @param template    the player template
	 * @param accountName the account name
	 * @param app         the player appearance
	 */
	private Player(PcTemplate template, String accountName, PcAppearance app) {
		this(IdFactory.getInstance().getNextId(), template, accountName, app);
	}

	@Override
	public final PcStat getStat() {
		return (PcStat) super.getStat();
	}

	@Override
	public void initCharStat() {
		setStat(new PcStat(this));
	}

	@Override
	public final PcStatus getStatus() {
		return (PcStatus) super.getStatus();
	}

	@Override
	public void initCharStatus() {
		setStatus(new PcStatus(this));
	}

	public final PcAppearance getAppearance() {
		return _appearance;
	}

	public final boolean isHairAccessoryEnabled() {
		return getVariables().getBoolean(PlayerVariables.HAIR_ACCESSORY_VARIABLE_NAME, true);
	}

	public final void setHairAccessoryEnabled(boolean enabled) {
		getVariables().set(PlayerVariables.HAIR_ACCESSORY_VARIABLE_NAME, enabled);
	}

	/**
	 * @return the base PcTemplate link to the L2PcInstance.
	 */
	public final PcTemplate getBaseTemplate() {
		return PlayerTemplateData.getInstance().getTemplate(_baseClass);
	}

	/**
	 * @return the PcTemplate link to the L2PcInstance.
	 */
	@Override
	public final PcTemplate getTemplate() {
		return (PcTemplate) super.getTemplate();
	}

	/**
	 * @param newclass
	 */
	public void setTemplate(ClassId newclass) {
		super.setTemplate(PlayerTemplateData.getInstance().getTemplate(newclass));
	}

	@Override
	protected CharacterAI initAI() {
		return new PlayerAI(this);
	}

	/**
	 * Return the Level of the L2PcInstance.
	 */
	@Override
	public final int getLevel() {
		return getStat().getLevel();
	}

	public void setBaseClass(int baseClass) {
		_baseClass = baseClass;
	}

	public void setBaseClass(ClassId classId) {
		_baseClass = classId.ordinal();
	}

	public boolean isInStoreMode() {
		return getPrivateStoreType() != PrivateStoreType.NONE;
	}

	public boolean isCrafting() {
		return _isCrafting;
	}

	public void setIsCrafting(boolean isCrafting) {
		_isCrafting = isCrafting;
	}

	/**
	 * @return a table containing all Common L2RecipeList of the L2PcInstance.
	 */
	public Collection<RecipeHolder> getCommonRecipeBook() {
		return _commonRecipeBook.values();
	}

	/**
	 * @return a table containing all Dwarf L2RecipeList of the L2PcInstance.
	 */
	public Collection<RecipeHolder> getDwarvenRecipeBook() {
		return _dwarvenRecipeBook.values();
	}

	/**
	 * Add a new L2RecipList to the table _commonrecipebook containing all L2RecipeList of the L2PcInstance
	 *
	 * @param recipe   The L2RecipeList to add to the _recipebook
	 * @param saveToDb
	 */
	public void registerCommonRecipeList(RecipeHolder recipe, boolean saveToDb) {
		_commonRecipeBook.put(recipe.getId(), recipe);

		if (saveToDb) {
			insertNewRecipeData(recipe.getId(), false);
		}
	}

	/**
	 * Add a new L2RecipList to the table _recipebook containing all L2RecipeList of the L2PcInstance
	 *
	 * @param recipe   The L2RecipeList to add to the _recipebook
	 * @param saveToDb
	 */
	public void registerDwarvenRecipeList(RecipeHolder recipe, boolean saveToDb) {
		_dwarvenRecipeBook.put(recipe.getId(), recipe);

		if (saveToDb) {
			insertNewRecipeData(recipe.getId(), true);
		}
	}

	/**
	 * @param recipeId The Identifier of the L2RecipeList to check in the player's recipe books
	 * @return {@code true}if player has the recipe on Common or Dwarven Recipe book else returns {@code false}
	 */
	public boolean hasRecipeList(int recipeId) {
		return _dwarvenRecipeBook.containsKey(recipeId) || _commonRecipeBook.containsKey(recipeId);
	}

	/**
	 * Tries to remove a L2RecipList from the table _DwarvenRecipeBook or from table _CommonRecipeBook, those table contain all L2RecipeList of the L2PcInstance
	 *
	 * @param recipeId The Identifier of the L2RecipeList to remove from the _recipebook
	 */
	public void unregisterRecipeList(int recipeId) {
		if (_dwarvenRecipeBook.remove(recipeId) != null) {
			deleteRecipeData(recipeId, true);
		} else if (_commonRecipeBook.remove(recipeId) != null) {
			deleteRecipeData(recipeId, false);
		} else {
			LOGGER.warn("Attempted to remove unknown RecipeList: " + recipeId);
		}

		for (Shortcut sc : getAllShortCuts()) {
			if ((sc != null) && (sc.getId() == recipeId) && (sc.getType() == ShortcutType.RECIPE)) {
				deleteShortCut(sc.getSlot(), sc.getPage());
			}
		}
	}

	private void insertNewRecipeData(int recipeId, boolean isDwarf) {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement("INSERT INTO character_recipebook (charId, id, classIndex, type) values(?,?,?,?)")) {
			statement.setInt(1, getObjectId());
			statement.setInt(2, recipeId);
			statement.setInt(3, isDwarf ? _classIndex : 0);
			statement.setInt(4, isDwarf ? 1 : 0);
			statement.execute();
		} catch (SQLException e) {
			LOGGER.warn("SQL exception while inserting recipe: " + recipeId + " from character " + getObjectId(), e);
		}
	}

	private void deleteRecipeData(int recipeId, boolean isDwarf) {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement("DELETE FROM character_recipebook WHERE charId=? AND id=? AND classIndex=?")) {
			statement.setInt(1, getObjectId());
			statement.setInt(2, recipeId);
			statement.setInt(3, isDwarf ? _classIndex : 0);
			statement.execute();
		} catch (SQLException e) {
			LOGGER.warn("SQL exception while deleting recipe: " + recipeId + " from character " + getObjectId(), e);
		}
	}

	/**
	 * @return the Id for the last talked quest NPC.
	 */
	public int getLastQuestNpcObject() {
		return _questNpcObject;
	}

	public void setLastQuestNpcObject(int npcId) {
		_questNpcObject = npcId;
	}

	/**
	 * @param quest The name of the quest
	 * @return the QuestState object corresponding to the quest name.
	 */
	public QuestState getQuestState(String quest) {
		return _quests.get(quest);
	}

	/**
	 * Add a QuestState to the table _quest containing all quests began by the L2PcInstance.
	 *
	 * @param qs The QuestState to add to _quest
	 */
	public void setQuestState(QuestState qs) {
		_quests.put(qs.getQuestName(), qs);
	}

	/**
	 * Verify if the player has the quest state.
	 *
	 * @param quest the quest state to check
	 * @return {@code true} if the player has the quest state, {@code false} otherwise
	 */
	public boolean hasQuestState(String quest) {
		return _quests.containsKey(quest);
	}

	/**
	 * Checks if this player has completed the given quest.
	 *
	 * @param quest to check if its completed or not.
	 * @return {@code true} if the player has completed the given quest, {@code false} otherwise.
	 */
	public boolean hasQuestCompleted(String quest) {
		final QuestState qs = _quests.get(quest);
		return (qs != null) && qs.isCompleted();
	}

	/**
	 * Remove a QuestState from the table _quest containing all quests began by the L2PcInstance.
	 *
	 * @param quest The name of the quest
	 */
	public void delQuestState(String quest) {
		_quests.remove(quest);
	}

	/**
	 * @return List of {@link QuestState}s of the current player.
	 */
	public List<QuestState> getAllQuestStates() {
		return new ArrayList<>(_quests.values());
	}

	/**
	 * @return a table containing all Quest in progress from the table _quests.
	 */
	public List<Quest> getAllActiveQuests() {
		//@formatter:off
		return _quests.values().stream()
				.filter(QuestState::isStarted)
				.map(QuestState::getQuest)
				.filter(Objects::nonNull)
				.filter(q -> q.getId() > 1)
				.collect(Collectors.toList());
		//@formatter:on
	}

	public void processQuestEvent(String questName, String event) {
		final Quest quest = QuestManager.getInstance().getQuest(questName);
		if ((quest == null) || (event == null) || event.isEmpty()) {
			return;
		}

		final Npc target = getLastFolkNPC();

		if ((target != null) && isInRadius2d(target, Npc.INTERACTION_DISTANCE)) {
			quest.notifyEvent(event, target, this);
		} else if (getLastQuestNpcObject() > 0) {
			final WorldObject object = getWorld().findObject(getLastQuestNpcObject());
			if (object.isNpc() && isInRadius2d(object, Npc.INTERACTION_DISTANCE)) {
				quest.notifyEvent(event, (Npc) object, this);
			}
		}
	}

	/**
	 * @return a table containing all L2ShortCut of the L2PcInstance.
	 */
	public Shortcut[] getAllShortCuts() {
		return _shortCuts.getAllShortCuts();
	}

	/**
	 * @param slot The slot in which the shortCuts is equipped
	 * @param page The page of shortCuts containing the slot
	 * @return the L2ShortCut of the L2PcInstance corresponding to the position (page-slot).
	 */
	public Shortcut getShortCut(int slot, int page) {
		return _shortCuts.getShortCut(slot, page);
	}

	/**
	 * Add a L2shortCut to the L2PcInstance _shortCuts
	 *
	 * @param shortcut
	 */
	public void registerShortCut(Shortcut shortcut) {
		_shortCuts.registerShortCut(shortcut);
	}

	/**
	 * Updates the shortcut bars with the new skill.
	 *
	 * @param skillId       the skill Id to search and update.
	 * @param skillLevel    the skill level to update.
	 * @param skillSubLevel the skill sub level to update.
	 */
	public void updateShortCuts(int skillId, int skillLevel, int skillSubLevel) {
		_shortCuts.updateShortCuts(skillId, skillLevel, skillSubLevel);
	}

	/**
	 * Delete the L2ShortCut corresponding to the position (page-slot) from the L2PcInstance _shortCuts.
	 *
	 * @param slot
	 * @param page
	 */
	public void deleteShortCut(int slot, int page) {
		_shortCuts.deleteShortCut(slot, page);
	}

	/**
	 * @param macro the macro to add to this L2PcInstance.
	 */
	public void registerMacro(Macro macro) {
		_macros.registerMacro(macro);
	}

	/**
	 * @param id the macro Id to delete.
	 */
	public void deleteMacro(int id) {
		_macros.deleteMacro(id);
	}

	/**
	 * @return all L2Macro of the L2PcInstance.
	 */
	public MacroList getMacros() {
		return _macros;
	}

	/**
	 * Set the siege state of the L2PcInstance.
	 *
	 * @param siegeState 1 = attacker, 2 = defender, 0 = not involved
	 */
	public void setSiegeState(byte siegeState) {
		_siegeState = siegeState;
	}

	/**
	 * Get the siege state of the L2PcInstance.
	 *
	 * @return 1 = attacker, 2 = defender, 0 = not involved
	 */
	public byte getSiegeState() {
		return _siegeState;
	}

	/**
	 * Set the siege Side of the L2PcInstance.
	 *
	 * @param val
	 */
	public void setSiegeSide(int val) {
		_siegeSide = val;
	}

	public boolean isRegisteredOnThisSiegeField(int val) {
		if ((_siegeSide != val) && ((_siegeSide < 81) || (_siegeSide > 89))) {
			return false;
		}
		return true;
	}

	public int getSiegeSide() {
		return _siegeSide;
	}

	/**
	 * Set the PvP Flag of the L2PcInstance.
	 *
	 * @param pvpFlag
	 */
	public void setPvpFlag(int pvpFlag) {
		_pvpFlag = (byte) pvpFlag;
	}

	@Override
	public byte getPvpFlag() {
		return _pvpFlag;
	}

	@Override
	public void updatePvPFlag(int value) {
		if (getPvpFlag() == value) {
			return;
		}
		setPvpFlag(value);

		final StatusUpdate su = new StatusUpdate(this);
		computeStatusUpdate(su, StatusUpdateType.PVP_FLAG);
		if (su.hasUpdates()) {
			broadcastPacket(su);
			sendPacket(su);
		}

		// If this player has a pet update the pets pvp flag as well
		if (hasSummon()) {
			getServitorsAndPets().forEach(Summon::broadcastPvpFlag);
			final RelationChanged rc = new RelationChanged();
			final Summon pet = getPet();
			if ((pet != null) && pet.isVisibleFor(this)) {
				rc.addRelation(pet, getRelation(this), false);
			}
			if (hasServitors()) {
				getServitors().values().stream().filter(s -> s.isVisibleFor(this)).forEach(s -> rc.addRelation(s, getRelation(this), false));
			}
			sendPacket(rc);
		}

		broadcastRelationChanged();
	}

	@Override
	public void revalidateZone(boolean force) {
		// Cannot validate if not in a world region (happens during teleport)
		if (getWorldRegion() == null) {
			return;
		}

		// This function is called too often from movement code
		if (force) {
			_zoneValidateCounter = 4;
		} else {
			_zoneValidateCounter--;
			if (_zoneValidateCounter < 0) {
				_zoneValidateCounter = 4;
			} else {
				return;
			}
		}

		ZoneManager.getInstance().getRegion(this).revalidateZones(this);

		if (GeneralConfig.ALLOW_WATER) {
			checkWaterState();
		}

		if (isInsideZone(ZoneId.ALTERED)) {
			if (_lastCompassZone == ExSetCompassZoneCode.ALTEREDZONE) {
				return;
			}
			_lastCompassZone = ExSetCompassZoneCode.ALTEREDZONE;
			ExSetCompassZoneCode cz = new ExSetCompassZoneCode(ExSetCompassZoneCode.ALTEREDZONE);
			sendPacket(cz);
		} else if (isInsideZone(ZoneId.SIEGE)) {
			if (_lastCompassZone == ExSetCompassZoneCode.SIEGEWARZONE2) {
				return;
			}
			_lastCompassZone = ExSetCompassZoneCode.SIEGEWARZONE2;
			ExSetCompassZoneCode cz = new ExSetCompassZoneCode(ExSetCompassZoneCode.SIEGEWARZONE2);
			sendPacket(cz);
		} else if (isInsideZone(ZoneId.PVP)) {
			if (_lastCompassZone == ExSetCompassZoneCode.PVPZONE) {
				return;
			}
			_lastCompassZone = ExSetCompassZoneCode.PVPZONE;
			ExSetCompassZoneCode cz = new ExSetCompassZoneCode(ExSetCompassZoneCode.PVPZONE);
			sendPacket(cz);
		} else if (isInsideZone(ZoneId.PEACE)) {
			if (_lastCompassZone == ExSetCompassZoneCode.PEACEZONE) {
				return;
			}
			_lastCompassZone = ExSetCompassZoneCode.PEACEZONE;
			ExSetCompassZoneCode cz = new ExSetCompassZoneCode(ExSetCompassZoneCode.PEACEZONE);
			sendPacket(cz);
		} else {
			if (_lastCompassZone == ExSetCompassZoneCode.GENERALZONE) {
				return;
			}
			if (_lastCompassZone == ExSetCompassZoneCode.SIEGEWARZONE2) {
				updatePvPStatus();
			}
			_lastCompassZone = ExSetCompassZoneCode.GENERALZONE;
			ExSetCompassZoneCode cz = new ExSetCompassZoneCode(ExSetCompassZoneCode.GENERALZONE);
			sendPacket(cz);
		}
	}

	/**
	 * @return the maximum dwarven recipe level this character can craft.
	 */
	public int getCreateItemLevel() {
		return _createItemLevel;
	}

	public void setCreateItemLevel(int createItemLevel) {
		_createItemLevel = createItemLevel;
	}

	/**
	 * @return the maximum common recipe level this character can craft.
	 */
	public int getCreateCommonItemLevel() {
		return _createCommonItemLevel;
	}

	public void setCreateCommonItemLevel(int createCommonItemLevel) {
		_createCommonItemLevel = createCommonItemLevel;
	}

	public ItemGrade getCrystallizeGrade() {
		return _crystallizeGrade;
	}

	public void setCrystallizeGrade(ItemGrade crystallizeGrade) {
		_crystallizeGrade = crystallizeGrade != null ? crystallizeGrade : ItemGrade.NONE;
	}

	/**
	 * @return the PK counter of the L2PcInstance.
	 */
	@Override
	public int getPkKills() {
		return _pkKills;
	}

	/**
	 * Set the PK counter of the L2PcInstance.
	 *
	 * @param pkKills
	 */
	public void setPkKills(int pkKills) {
		EventDispatcher.getInstance().notifyEventAsync(new OnPlayerPKChanged(this, _pkKills, pkKills), this);
		_pkKills = pkKills;
	}

	/**
	 * @return the _deleteTimer of the L2PcInstance.
	 */
	public long getDeleteTimer() {
		return _deleteTimer;
	}

	/**
	 * Set the _deleteTimer of the L2PcInstance.
	 *
	 * @param deleteTimer
	 */
	public void setDeleteTimer(long deleteTimer) {
		_deleteTimer = deleteTimer;
	}

	/**
	 * @return the number of recommendation obtained by the L2PcInstance.
	 */
	public int getRecomHave() {
		return _recomHave;
	}

	/**
	 * Increment the number of recommendation obtained by the L2PcInstance (Max : 255).
	 */
	protected void incRecomHave() {
		if (_recomHave < 255) {
			_recomHave++;
		}
	}

	/**
	 * Set the number of recommendation obtained by the L2PcInstance (Max : 255).
	 *
	 * @param value
	 */
	public void setRecomHave(int value) {
		_recomHave = Math.min(Math.max(value, 0), 255);
	}

	/**
	 * Set the number of recommendation obtained by the L2PcInstance (Max : 255).
	 *
	 * @param value
	 */
	public void setRecomLeft(int value) {
		_recomLeft = Math.min(Math.max(value, 0), 255);
	}

	/**
	 * @return the number of recommendation that the L2PcInstance can give.
	 */
	public int getRecomLeft() {
		return _recomLeft;
	}

	/**
	 * Increment the number of recommendation that the L2PcInstance can give.
	 */
	protected void decRecomLeft() {
		if (_recomLeft > 0) {
			_recomLeft--;
		}
	}

	public void giveRecom(Player target) {
		target.incRecomHave();
		decRecomLeft();
	}

	/**
	 * Set the exp of the L2PcInstance before a death
	 *
	 * @param exp
	 */
	public void setExpBeforeDeath(long exp) {
		_expBeforeDeath = exp;
	}

	public long getExpBeforeDeath() {
		return _expBeforeDeath;
	}

	/**
	 * Set the reputation of the Player and send a Server->Client packet StatusUpdate (broadcast).
	 *
	 * @param reputation
	 */
	@Override
	public void setReputation(int reputation) {
		// Notify to scripts.
		EventDispatcher.getInstance().notifyEventAsync(new OnPlayerReputationChanged(this, getReputation(), reputation), this);

		if (reputation > PvpConfig.MAX_REPUTATION) // Max count of positive reputation
		{
			reputation = PvpConfig.MAX_REPUTATION;
		}

		if (getReputation() == reputation) {
			return;
		}

		super.setReputation(reputation);

		if ((getReputation() >= 0) && (reputation < 0)) {
			getWorld().forEachVisibleObject(this, GuardInstance.class, object ->
			{
				if (object.getAI().getIntention() == CtrlIntention.AI_INTENTION_IDLE) {
					object.getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
				}
			});
		}

		sendPacket(SystemMessage.getSystemMessage(SystemMessageId.YOUR_REPUTATION_HAS_BEEN_CHANGED_TO_S1).addInt(getReputation()));
		broadcastReputation();
	}

	public int getExpertiseArmorPenalty() {
		return _expertiseArmorPenalty;
	}

	public int getExpertiseWeaponPenalty() {
		return _expertiseWeaponPenalty;
	}

	public int getExpertisePenaltyBonus() {
		return _expertisePenaltyBonus;
	}

	public void setExpertisePenaltyBonus(int bonus) {
		_expertisePenaltyBonus = bonus;
	}

	public int getWeightPenalty() {
		return _dietMode ? 0 : _curWeightPenalty;
	}

	/**
	 * Update the overloaded status of the L2PcInstance.
	 *
	 * @param broadcast
	 */
	public void refreshOverloaded(boolean broadcast) {
		int maxLoad = getMaxLoad();
		if (maxLoad > 0) {
			double weightRatioPercent = ((getCurrentLoad() - getBonusWeightPenalty()) * 100d) / maxLoad;
			int newWeightPenalty;
			if ((weightRatioPercent < 50) || _dietMode) {
				newWeightPenalty = 0;
			} else if (weightRatioPercent < 66.6) {
				newWeightPenalty = 1;
			} else if (weightRatioPercent < 80) {
				newWeightPenalty = 2;
			} else if (weightRatioPercent < 100) {
				newWeightPenalty = 3;
			} else {
				newWeightPenalty = 4;
			}

			if (_curWeightPenalty != newWeightPenalty) {
				_curWeightPenalty = newWeightPenalty;
				if (newWeightPenalty > 0) {
					addSkill(SkillData.getInstance().getSkill(CommonSkill.WEIGHT_PENALTY.getId(), newWeightPenalty));
				} else {
					removeSkill(getKnownSkill(CommonSkill.WEIGHT_PENALTY.getId()), false, true);
				}

				if (broadcast) {
					sendPacket(new EtcStatusUpdate(this));
					broadcastUserInfo();
				}
			}
		}
	}

	public void refreshExpertisePenalty() {
		if (!PlayerConfig.EXPERTISE_PENALTY) {
			return;
		}

		final CrystalType expertiseLevel = getExpertiseLevel().plusLevel(getExpertisePenaltyBonus());

		int armorPenalty = 0;
		int weaponPenalty = 0;

		for (ItemInstance item : getInventory().getPaperdollItems(i -> (i != null) && ((i.getItemType() != EtcItemType.ARROW) && (i.getItemType() != EtcItemType.BOLT)) && i.getItem().getCrystalType().isGreater(expertiseLevel))) {
			if (item.isArmor()) {
				// Armor penalty level increases depending on amount of penalty armors equipped, not grade level difference.
				armorPenalty = CommonUtil.constrain(armorPenalty + 1, 0, 4);
			} else {
				// Weapon penalty level increases based on grade difference.
				weaponPenalty = CommonUtil.constrain(item.getItem().getCrystalType().getLevel() - expertiseLevel.getLevel(), 0, 4);
			}
		}

		boolean changed = false;

		if ((getExpertiseWeaponPenalty() != weaponPenalty) || (getSkillLevel(CommonSkill.WEAPON_GRADE_PENALTY.getId()) != weaponPenalty)) {
			_expertiseWeaponPenalty = weaponPenalty;
			if (_expertiseWeaponPenalty > 0) {
				addSkill(SkillData.getInstance().getSkill(CommonSkill.WEAPON_GRADE_PENALTY.getId(), _expertiseWeaponPenalty));
			} else {
				removeSkill(CommonSkill.WEAPON_GRADE_PENALTY.getId(), true);
			}
			changed = true;
		}

		if ((getExpertiseArmorPenalty() != armorPenalty) || (getSkillLevel(CommonSkill.ARMOR_GRADE_PENALTY.getId()) != armorPenalty)) {
			_expertiseArmorPenalty = armorPenalty;
			if (_expertiseArmorPenalty > 0) {
				addSkill(SkillData.getInstance().getSkill(CommonSkill.ARMOR_GRADE_PENALTY.getId(), _expertiseArmorPenalty));
			} else {
				removeSkill(CommonSkill.ARMOR_GRADE_PENALTY.getId(), true);
			}
			changed = true;
		}

		if (changed) {
			sendSkillList(); // Update expertise penalty icon in skill list.
			sendPacket(new EtcStatusUpdate(this));
		}
	}

	public void useEquippableItem(ItemInstance item, boolean abortAttack) {
		// Equip or unEquip
		ItemInstance[] items = null;
		final boolean isEquiped = item.isEquipped();
		final int oldInvLimit = getInventoryLimit();
		SystemMessage sm = null;

		if (isEquiped) {
			if (item.getEnchantLevel() > 0) {
				sm = SystemMessage.getSystemMessage(SystemMessageId.THE_EQUIPMENT_S1_S2_HAS_BEEN_REMOVED);
				sm.addInt(item.getEnchantLevel());
				sm.addItemName(item);
			} else {
				sm = SystemMessage.getSystemMessage(SystemMessageId.S1_HAS_BEEN_UNEQUIPPED);
				sm.addItemName(item);
			}
			sendPacket(sm);

			int slot = getInventory().getSlotFromItem(item);
			// we can't unequip talisman by body slot
			if ((slot == ItemTemplate.SLOT_DECO) || (slot == ItemTemplate.SLOT_BROOCH_JEWEL)) {
				items = getInventory().unEquipItemInSlotAndRecord(item.getLocationSlot());
			} else {
				items = getInventory().unEquipItemInBodySlotAndRecord(slot);
			}
		} else {
			items = getInventory().equipItemAndRecord(item);

			if (item.isEquipped()) {
				if (item.getEnchantLevel() > 0) {
					sm = SystemMessage.getSystemMessage(SystemMessageId.EQUIPPED_S1_S2);
					sm.addInt(item.getEnchantLevel());
					sm.addItemName(item);
				} else {
					sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_EQUIPPED_YOUR_S1);
					sm.addItemName(item);
				}
				sendPacket(sm);

				// Consume mana - will start a task if required; returns if item is not a shadow item
				item.decreaseMana(false);

				if ((item.getItem().getBodyPart() & ItemTemplate.SLOT_MULTI_ALLWEAPON) != 0) {
					rechargeShots(true, true, false);
				}
			} else {
				sendPacket(SystemMessageId.YOU_DO_NOT_MEET_THE_REQUIRED_CONDITION_TO_EQUIP_THAT_ITEM);
			}
		}
		refreshExpertisePenalty();

		InventoryUpdate iu = new InventoryUpdate();
		iu.addItems(Arrays.asList(items));
		sendInventoryUpdate(iu);

		if (abortAttack) {
			abortAttack();
		}

		if (getInventoryLimit() != oldInvLimit) {
			sendPacket(new ExStorageMaxCount(this));
		}

		// Notify to scripts
		EventDispatcher.getInstance().notifyEventAsync(new OnPlayerEquipItem(this, item), this);
	}

	/**
	 * @return the the PvP Kills of the L2PcInstance (Number of player killed during a PvP).
	 */
	public int getPvpKills() {
		return _pvpKills;
	}

	/**
	 * Set the the PvP Kills of the L2PcInstance (Number of player killed during a PvP).
	 *
	 * @param pvpKills
	 */
	public void setPvpKills(int pvpKills) {
		EventDispatcher.getInstance().notifyEventAsync(new OnPlayerPvPChanged(this, _pvpKills, pvpKills), this);
		_pvpKills = pvpKills;
	}

	/**
	 * @return the Fame of this L2PcInstance
	 */
	public int getFame() {
		return _fame;
	}

	/**
	 * Set the Fame of this L2PcInstane
	 *
	 * @param fame
	 */
	public void setFame(int fame) {
		EventDispatcher.getInstance().notifyEventAsync(new OnPlayerFameChanged(this, _fame, fame), this);
		_fame = (fame > PlayerConfig.MAX_PERSONAL_FAME_POINTS) ? PlayerConfig.MAX_PERSONAL_FAME_POINTS : fame;
	}

	/**
	 * @return the Raidboss points of this Player
	 */
	public int getRaidbossPoints() {
		return _raidbossPoints;
	}

	/**
	 * Set the Raidboss points of this Player
	 *
	 * @param points
	 */
	public void setRaidbossPoints(int points) {
		_raidbossPoints = points;
	}

	/**
	 * Increase the Raidboss points of this Player
	 *
	 * @param increasePoints
	 */
	public void increaseRaidbossPoints(int increasePoints) {
		setRaidbossPoints(getRaidbossPoints() + increasePoints);
	}

	/**
	 * @return the ClassId object of the L2PcInstance contained in PcTemplate.
	 */
	public ClassId getClassId() {
		return getTemplate().getClassId();
	}

	/**
	 * Set the template of the L2PcInstance.
	 *
	 * @param Id The Identifier of the PcTemplate to set to the L2PcInstance
	 */
	public void setClassId(int Id) {
		if (!_subclassLock.tryLock()) {
			return;
		}

		try {
			if ((getLvlJoinedAcademy() != 0) && (_clan != null) && CategoryData.getInstance().isInCategory(CategoryType.THIRD_CLASS_GROUP, Id)) {
				if (getLvlJoinedAcademy() <= 16) {
					_clan.addReputationScore(FeatureConfig.JOIN_ACADEMY_MAX_REP_SCORE, true);
				} else if (getLvlJoinedAcademy() >= 39) {
					_clan.addReputationScore(FeatureConfig.JOIN_ACADEMY_MIN_REP_SCORE, true);
				} else {
					_clan.addReputationScore((FeatureConfig.JOIN_ACADEMY_MAX_REP_SCORE - ((getLvlJoinedAcademy() - 16) * 20)), true);
				}
				setLvlJoinedAcademy(0);
				// oust pledge member from the academy, cuz he has finished his 2nd class transfer
				SystemMessage msg = SystemMessage.getSystemMessage(SystemMessageId.CLAN_MEMBER_S1_HAS_BEEN_EXPELLED);
				msg.addPcName(this);
				_clan.broadcastToOnlineMembers(msg);
				_clan.broadcastToOnlineMembers(new PledgeShowMemberListDelete(getName()));
				_clan.removeClanMember(getObjectId(), 0);
				sendPacket(SystemMessageId.CONGRATULATIONS_YOU_WILL_NOW_GRADUATE_FROM_THE_CLAN_ACADEMY_AND_LEAVE_YOUR_CURRENT_CLAN_YOU_CAN_NOW_JOIN_A_CLAN_WITHOUT_BEING_SUBJECT_TO_ANY_PENALTIES);

				// receive graduation gift
				getInventory().addItem("Gift", 8181, 1, this, null); // give academy circlet
			}
			if (isSubClassActive()) {
				getSubClasses().get(_classIndex).setClassId(Id);
			}
			setTarget(this);
			broadcastPacket(new MagicSkillUse(this, 5103, 1, 1000, 0));
			setClassTemplate(Id);
			if (isInCategory(CategoryType.FOURTH_CLASS_GROUP)) {
				sendPacket(SystemMessageId.CONGRATULATIONS_YOU_VE_COMPLETED_YOUR_THIRD_CLASS_TRANSFER_QUEST);
			} else {
				sendPacket(SystemMessageId.CONGRATULATIONS_YOU_VE_COMPLETED_A_CLASS_TRANSFER);
			}

			// Update class icon in party and clan
			if (isInParty()) {
				getParty().broadcastPacket(new PartySmallWindowUpdate(this, true));
			}

			if (getClan() != null) {
				getClan().broadcastToOnlineMembers(new PledgeShowMemberListUpdate(this));
			}

			sendPacket(new ExSubjobInfo(this, SubclassInfoType.CLASS_CHANGED));

			// Add AutoGet skills and normal skills and/or learnByFS depending on configurations.
			rewardSkills();

			if (!canOverrideCond(PcCondOverride.SKILL_CONDITIONS) && PlayerConfig.DECREASE_SKILL_LEVEL) {
				checkPlayerSkills();
			}

			notifyFriends(L2FriendStatus.MODE_CLASS);
		} finally {
			_subclassLock.unlock();
		}
	}

	/**
	 * @return the Experience of the L2PcInstance.
	 */
	public long getExp() {
		return getStat().getExp();
	}

	/**
	 * Set the fists weapon of the L2PcInstance (used when no weapon is equiped).
	 *
	 * @param weaponItem The fists L2Weapon to set to the L2PcInstance
	 */
	public void setFistsWeaponItem(Weapon weaponItem) {
		_fistsWeaponItem = weaponItem;
	}

	/**
	 * @return the fists weapon of the L2PcInstance (used when no weapon is equipped).
	 */
	public Weapon getFistsWeaponItem() {
		return _fistsWeaponItem;
	}

	/**
	 * @param classId
	 * @return the fists weapon of the L2PcInstance Class (used when no weapon is equipped).
	 */
	public Weapon findFistsWeaponItem(int classId) {
		Weapon weaponItem = null;
		if ((classId >= 0x00) && (classId <= 0x09)) {
			// human fighter fists
			ItemTemplate temp = ItemTable.getInstance().getTemplate(246);
			weaponItem = (Weapon) temp;
		} else if ((classId >= 0x0a) && (classId <= 0x11)) {
			// human mage fists
			ItemTemplate temp = ItemTable.getInstance().getTemplate(251);
			weaponItem = (Weapon) temp;
		} else if ((classId >= 0x12) && (classId <= 0x18)) {
			// elven fighter fists
			ItemTemplate temp = ItemTable.getInstance().getTemplate(244);
			weaponItem = (Weapon) temp;
		} else if ((classId >= 0x19) && (classId <= 0x1e)) {
			// elven mage fists
			ItemTemplate temp = ItemTable.getInstance().getTemplate(249);
			weaponItem = (Weapon) temp;
		} else if ((classId >= 0x1f) && (classId <= 0x25)) {
			// dark elven fighter fists
			ItemTemplate temp = ItemTable.getInstance().getTemplate(245);
			weaponItem = (Weapon) temp;
		} else if ((classId >= 0x26) && (classId <= 0x2b)) {
			// dark elven mage fists
			ItemTemplate temp = ItemTable.getInstance().getTemplate(250);
			weaponItem = (Weapon) temp;
		} else if ((classId >= 0x2c) && (classId <= 0x30)) {
			// orc fighter fists
			ItemTemplate temp = ItemTable.getInstance().getTemplate(248);
			weaponItem = (Weapon) temp;
		} else if ((classId >= 0x31) && (classId <= 0x34)) {
			// orc mage fists
			ItemTemplate temp = ItemTable.getInstance().getTemplate(252);
			weaponItem = (Weapon) temp;
		} else if ((classId >= 0x35) && (classId <= 0x39)) {
			// dwarven fists
			ItemTemplate temp = ItemTable.getInstance().getTemplate(247);
			weaponItem = (Weapon) temp;
		}

		return weaponItem;
	}

	/**
	 * This method reward all AutoGet skills and Normal skills if Auto-Learn configuration is true.
	 */
	public void rewardSkills() {
		// Give all normal skills if activated Auto-Learn is activated, included AutoGet skills.
		if (PlayerConfig.AUTO_LEARN_SKILLS) {
			giveAvailableSkills(PlayerConfig.AUTO_LEARN_FS_SKILLS, true);
		} else {
			giveAvailableAutoGetSkills();
		}

		checkPlayerSkills();
		checkItemRestriction();
		sendSkillList();
	}

	/**
	 * Re-give all skills which aren't saved to database, like Noble, Hero, Clan Skills.<br>
	 */
	public void regiveTemporarySkills() {
		// Do not call this on enterworld or char load

		// Add noble skills if noble
		if (isNoble()) {
			setNoble(getNobleStatus());
		}

		// Add Hero skills if hero
		if (isHero()) {
			setHero(true);
		}

		// Add clan skills
		if (getClan() != null) {
			Clan clan = getClan();
			clan.addSkillEffects(this);

			if ((clan.getLevel() >= SiegeManager.getInstance().getSiegeClanMinLevel()) && isClanLeader()) {
				SiegeManager.getInstance().addSiegeSkills(this);
			}
			if (getClan().getCastleId() > 0) {
				CastleManager.getInstance().getCastleByOwner(getClan()).giveResidentialSkills(this);
			}
			if (getClan().getFortId() > 0) {
				FortManager.getInstance().getFortByOwner(getClan()).giveResidentialSkills(this);
			}
		}

		// Reload passive skills from armors / jewels / weapons
		getInventory().reloadEquippedItems();
	}

	/**
	 * Give all available skills to the player.
	 *
	 * @param includedByFs   if {@code true} forgotten scroll skills present in the skill tree will be added
	 * @param includeAutoGet if {@code true} auto-get skills present in the skill tree will be added
	 * @return the amount of new skills earned
	 */
	public int giveAvailableSkills(boolean includedByFs, boolean includeAutoGet) {
		int skillCounter = 0;
		// Get available skills
		Collection<Skill> skills = SkillTreesData.getInstance().getAllAvailableSkills(this, getClassId(), includedByFs, includeAutoGet);
		List<Skill> skillsForStore = new ArrayList<>();

		for (Skill skill : skills) {
			if (getKnownSkill(skill.getId()) == skill) {
				continue;
			}

			if (getSkillLevel(skill.getId()) == -1) {
				skillCounter++;
			}

			// fix when learning toggle skills
			if (skill.isToggle() && !skill.isNecessaryToggle() && isAffectedBySkill(skill.getId())) {
				stopSkillEffects(true, skill.getId());
			}

			addSkill(skill, false);
			skillsForStore.add(skill);
		}
		storeSkills(skillsForStore, -1);
		if (PlayerConfig.AUTO_LEARN_SKILLS && (skillCounter > 0)) {
			sendMessage("You have learned " + skillCounter + " new skills.");
		}
		return skillCounter;
	}

	/**
	 * Give all available auto-get skills to the player.
	 */
	public void giveAvailableAutoGetSkills() {
		// Get available skills
		final List<SkillLearn> autoGetSkills = SkillTreesData.getInstance().getAvailableAutoGetSkills(this);
		final SkillData st = SkillData.getInstance();
		Skill skill;
		for (SkillLearn s : autoGetSkills) {
			skill = st.getSkill(s.getSkillId(), s.getSkillLevel());
			if (skill != null) {
				addSkill(skill, true);
			} else {
				LOGGER.warn("Skipping null auto-get skill for player: " + toString());
			}
		}
	}

	/**
	 * Set the Experience value of the L2PcInstance.
	 *
	 * @param exp
	 */
	public void setExp(long exp) {
		if (exp < 0) {
			exp = 0;
		}

		getStat().setExp(exp);
	}

	/**
	 * @return the Race object of the L2PcInstance.
	 */
	@Override
	public Race getRace() {
		if (!isSubClassActive()) {
			return getTemplate().getRace();
		}
		return PlayerTemplateData.getInstance().getTemplate(_baseClass).getRace();
	}

	public Radar getRadar() {
		return _radar;
	}

	/**
	 * @return the SP amount of the L2PcInstance.
	 */
	@Override
	public long getSp() {
		return getStat().getSp();
	}

	/**
	 * Set the SP amount of the L2PcInstance.
	 *
	 * @param sp
	 */
	public void setSp(long sp) {
		if (sp < 0) {
			sp = 0;
		}

		super.getStat().setSp(sp);
	}

	/**
	 * @param castleId
	 * @return true if this L2PcInstance is a clan leader in ownership of the passed castle
	 */
	public boolean isCastleLord(int castleId) {
		Clan clan = getClan();

		// player has clan and is the clan leader, check the castle info
		if ((clan != null) && (clan.getLeader().getPlayerInstance() == this)) {
			// if the clan has a castle and it is actually the queried castle, return true
			Castle castle = CastleManager.getInstance().getCastleByOwner(clan);
			if ((castle != null) && (castle == CastleManager.getInstance().getCastleById(castleId))) {
				return true;
			}
		}

		return false;
	}

	/**
	 * @return the Clan Identifier of the L2PcInstance.
	 */
	@Override
	public int getClanId() {
		return _clanId;
	}

	/**
	 * @return the Clan Crest Identifier of the L2PcInstance or 0.
	 */
	public int getClanCrestId() {
		if (_clan != null) {
			return _clan.getCrestId();
		}

		return 0;
	}

	/**
	 * @return The Clan CrestLarge Identifier or 0
	 */
	public int getClanCrestLargeId() {
		if ((_clan != null) && ((_clan.getCastleId() != 0) || (_clan.getHideoutId() != 0))) {
			return _clan.getCrestLargeId();
		}
		return 0;
	}

	public long getClanJoinExpiryTime() {
		return _clanJoinExpiryTime;
	}

	public void setClanJoinExpiryTime(long time) {
		_clanJoinExpiryTime = time;
	}

	public long getClanCreateExpiryTime() {
		return _clanCreateExpiryTime;
	}

	public void setClanCreateExpiryTime(long time) {
		_clanCreateExpiryTime = time;
	}

	public void setOnlineTime(long time) {
		_onlineTime = time;
		_onlineBeginTime = System.currentTimeMillis();
	}

	/**
	 * Return the PcInventory Inventory of the L2PcInstance contained in _inventory.
	 */
	@Override
	public PcInventory getInventory() {
		return _inventory;
	}

	/**
	 * Delete a ShortCut of the L2PcInstance _shortCuts.
	 *
	 * @param objectId
	 */
	public void removeItemFromShortCut(int objectId) {
		_shortCuts.deleteShortCutByObjectId(objectId);
	}

	/**
	 * @return True if the L2PcInstance is sitting.
	 */
	public boolean isSitting() {
		return _waitTypeSitting;
	}

	/**
	 * Set _waitTypeSitting to given value
	 *
	 * @param state
	 */
	public void setIsSitting(boolean state) {
		_waitTypeSitting = state;
	}

	/**
	 * Sit down the L2PcInstance, set the AI Intention to AI_INTENTION_REST and send a Server->Client ChangeWaitType packet (broadcast)
	 */
	public void sitDown() {
		sitDown(true);
	}

	public void sitDown(boolean checkCast) {
		if (checkCast && isCastingNow()) {
			sendMessage("Cannot sit while casting");
			return;
		}

		if (!_waitTypeSitting && !isAttackingDisabled() && !isControlBlocked() && !isImmobilized()) {
			breakAttack();
			setIsSitting(true);
			getAI().setIntention(CtrlIntention.AI_INTENTION_REST);
			broadcastPacket(new ChangeWaitType(this, ChangeWaitType.WT_SITTING));
			// Schedule a sit down task to wait for the animation to finish
			ThreadPool.getInstance().scheduleGeneral(new SitDownTask(this), 2500, TimeUnit.MILLISECONDS);
			setBlockActions(true);
		}
	}

	/**
	 * Stand up the L2PcInstance, set the AI Intention to AI_INTENTION_IDLE and send a Server->Client ChangeWaitType packet (broadcast)
	 */
	public void standUp() {
		final BooleanReturn term = EventDispatcher.getInstance().notifyEvent(new CanPlayerStandUp(this), this, BooleanReturn.class);
		if ((term != null) && !term.getValue()) {
			return;
		}

		if (_waitTypeSitting && !isInStoreMode() && !isAlikeDead()) {
			if (getStat().has(BooleanStat.RELAX)) {
				getEffectList().stopSkillEffects(true, 226);
				getEffectList().stopSkillEffects(true, 296);
				getEffectList().stopSkillEffects(true, 39102);
				// TODO Fix this because they have no abnormal. stopEffects(EffectFlag.RELAXING);
			}

			broadcastPacket(new ChangeWaitType(this, ChangeWaitType.WT_STANDING));
			// Schedule a stand up task to wait for the animation to finish
			ThreadPool.getInstance().scheduleGeneral(new StandUpTask(this), 2500, TimeUnit.MILLISECONDS);
		}
	}

	/**
	 * @return the PcWarehouse object of the L2PcInstance.
	 */
	public PcWarehouse getWarehouse() {
		if (_warehouse == null) {
			_warehouse = new PcWarehouse(this);
			_warehouse.restore();
		}
		if (GeneralConfig.WAREHOUSE_CACHE) {
			WarehouseCacheManager.getInstance().addCacheTask(this);
		}
		return _warehouse;
	}

	/**
	 * Free memory used by Warehouse
	 */
	public void clearWarehouse() {
		if (_warehouse != null) {
			_warehouse.deleteMe();
		}
		_warehouse = null;
	}

	/**
	 * @return the PcFreight object of the L2PcInstance.
	 */
	public PcFreight getFreight() {
		return _freight;
	}

	/**
	 * @return true if refund list is not empty
	 */
	public boolean hasRefund() {
		return (_refund != null) && (_refund.getSize() > 0) && GeneralConfig.ALLOW_REFUND;
	}

	/**
	 * @return refund object or create new if not exist
	 */
	public PcRefund getRefund() {
		if (_refund == null) {
			_refund = new PcRefund(this);
		}
		return _refund;
	}

	/**
	 * Clear refund
	 */
	public void clearRefund() {
		if (_refund != null) {
			_refund.deleteMe();
		}
		_refund = null;
	}

	/**
	 * @return the Adena amount of the L2PcInstance.
	 */
	public long getAdena() {
		return _inventory.getAdena();
	}

	/**
	 * @return the Ancient Adena amount of the L2PcInstance.
	 */
	public long getAncientAdena() {
		return _inventory.getAncientAdena();
	}

	/**
	 * @return the Beauty Tickets of the L2PcInstance.
	 */
	public long getBeautyTickets() {
		return _inventory.getBeautyTickets();
	}

	/**
	 * @return the Fortune Reading Tickets of the Player.
	 */
	public long getFortuneReadingTickets() {
		return _inventory.getFortuneReadingTickets();
	}

	/**
	 * @return the Luxury Fortune Reading Tickets of the Player.
	 */
	public long getLuxuryFortuneReadingTickets() {
		return _inventory.getLuxuryFortuneReadingTickets();
	}

	/**
	 * Add adena to Inventory of the L2PcInstance and send a Server->Client InventoryUpdate packet to the L2PcInstance.
	 *
	 * @param process     : String Identifier of process triggering this action
	 * @param count       : int Quantity of adena to be added
	 * @param reference   : L2Object Object referencing current action like NPC selling item or previous item in transformation
	 * @param sendMessage : boolean Specifies whether to send message to Client about this action
	 */
	public void addAdena(String process, long count, WorldObject reference, boolean sendMessage) {
		if (!ItemContainer.validateCount(Inventory.ADENA_ID, (getAdena() + count))) {
			if (sendMessage) {
				sendPacket(SystemMessageId.YOUR_INVENTORY_IS_FULL);
			}
			return;
		}

		if (sendMessage) {
			SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_EARNED_S1_ADENA);
			sm.addLong(count);
			sendPacket(sm);
		}

		if (count > 0) {
			_inventory.addAdena(process, count, this, reference);

			// Send update packet
			if (!GeneralConfig.FORCE_INVENTORY_UPDATE) {
				InventoryUpdate iu = new InventoryUpdate();
				iu.addItem(_inventory.getAdenaInstance());
				sendInventoryUpdate(iu);
			} else {
				sendItemList(false);
			}
		}
	}

	/**
	 * Reduce adena in Inventory of the L2PcInstance and send a Server->Client InventoryUpdate packet to the L2PcInstance.
	 *
	 * @param process     : String Identifier of process triggering this action
	 * @param count       : long Quantity of adena to be reduced
	 * @param reference   : L2Object Object referencing current action like NPC selling item or previous item in transformation
	 * @param sendMessage : boolean Specifies whether to send message to Client about this action
	 * @return boolean informing if the action was successful
	 */
	public boolean reduceAdena(String process, long count, WorldObject reference, boolean sendMessage) {
		if (count > getAdena()) {
			if (sendMessage) {
				sendPacket(SystemMessageId.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
			}
			return false;
		}

		if (count > 0) {
			ItemInstance adenaItem = _inventory.getAdenaInstance();
			if (!_inventory.reduceAdena(process, count, this, reference)) {
				return false;
			}

			// Send update packet
			if (!GeneralConfig.FORCE_INVENTORY_UPDATE) {
				InventoryUpdate iu = new InventoryUpdate();
				iu.addItem(adenaItem);
				sendInventoryUpdate(iu);
			} else {
				sendItemList(false);
			}

			if (sendMessage) {
				SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_ADENA_DISAPPEARED);
				sm.addLong(count);
				sendPacket(sm);
			}
		}

		return true;
	}

	/**
	 * Reduce Beauty Tickets in Inventory of the L2PcInstance and send a Server->Client InventoryUpdate packet to the L2PcInstance.
	 *
	 * @param process     : String Identifier of process triggering this action
	 * @param count       : long Quantity of Beauty Tickets to be reduced
	 * @param reference   : L2Object Object referencing current action like NPC selling item or previous item in transformation
	 * @param sendMessage : boolean Specifies whether to send message to Client about this action
	 * @return boolean informing if the action was successful
	 */
	public boolean reduceBeautyTickets(String process, long count, WorldObject reference, boolean sendMessage) {
		if (count > getBeautyTickets()) {
			if (sendMessage) {
				sendPacket(SystemMessageId.INCORRECT_ITEM_COUNT2);
			}
			return false;
		}

		if (count > 0) {
			ItemInstance beautyTickets = _inventory.getBeautyTicketsInstance();
			if (!_inventory.reduceBeautyTickets(process, count, this, reference)) {
				return false;
			}

			// Send update packet
			if (!GeneralConfig.FORCE_INVENTORY_UPDATE) {
				InventoryUpdate iu = new InventoryUpdate();
				iu.addItem(beautyTickets);
				sendInventoryUpdate(iu);
			} else {
				sendItemList(false);
			}

			if (sendMessage) {
				if (count > 1) {
					SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S2_S1_S_DISAPPEARED);
					sm.addItemName(Inventory.BEAUTY_TICKET_ID);
					sm.addLong(count);
					sendPacket(sm);
				} else {
					SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_DISAPPEARED);
					sm.addItemName(Inventory.BEAUTY_TICKET_ID);
					sendPacket(sm);
				}
			}
		}

		return true;
	}

	/**
	 * Add ancient adena to Inventory of the L2PcInstance and send a Server->Client InventoryUpdate packet to the L2PcInstance.
	 *
	 * @param process     : String Identifier of process triggering this action
	 * @param count       : int Quantity of ancient adena to be added
	 * @param reference   : L2Object Object referencing current action like NPC selling item or previous item in transformation
	 * @param sendMessage : boolean Specifies whether to send message to Client about this action
	 */
	public void addAncientAdena(String process, long count, WorldObject reference, boolean sendMessage) {
		if (!ItemContainer.validateCount(Inventory.ANCIENT_ADENA_ID, (getAncientAdena() + count))) {
			if (sendMessage) {
				sendPacket(SystemMessageId.YOUR_INVENTORY_IS_FULL);
			}
			return;
		}

		if (sendMessage) {
			SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_EARNED_S2_S1_S);
			sm.addItemName(Inventory.ANCIENT_ADENA_ID);
			sm.addLong(count);
			sendPacket(sm);
		}

		if (count > 0) {
			_inventory.addAncientAdena(process, count, this, reference);

			if (!GeneralConfig.FORCE_INVENTORY_UPDATE) {
				InventoryUpdate iu = new InventoryUpdate();
				iu.addItem(_inventory.getAncientAdenaInstance());
				sendInventoryUpdate(iu);
			} else {
				sendItemList(false);
			}
		}
	}

	/**
	 * Reduce ancient adena in Inventory of the L2PcInstance and send a Server->Client InventoryUpdate packet to the L2PcInstance.
	 *
	 * @param process     : String Identifier of process triggering this action
	 * @param count       : long Quantity of ancient adena to be reduced
	 * @param reference   : L2Object Object referencing current action like NPC selling item or previous item in transformation
	 * @param sendMessage : boolean Specifies whether to send message to Client about this action
	 * @return boolean informing if the action was successful
	 */
	public boolean reduceAncientAdena(String process, long count, WorldObject reference, boolean sendMessage) {
		if (count > getAncientAdena()) {
			if (sendMessage) {
				sendPacket(SystemMessageId.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
			}

			return false;
		}

		if (count > 0) {
			ItemInstance ancientAdenaItem = _inventory.getAncientAdenaInstance();
			if (!_inventory.reduceAncientAdena(process, count, this, reference)) {
				return false;
			}

			if (!GeneralConfig.FORCE_INVENTORY_UPDATE) {
				InventoryUpdate iu = new InventoryUpdate();
				iu.addItem(ancientAdenaItem);
				sendInventoryUpdate(iu);
			} else {
				sendItemList(false);
			}

			if (sendMessage) {
				if (count > 1) {
					SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S2_S1_S_DISAPPEARED);
					sm.addItemName(Inventory.ANCIENT_ADENA_ID);
					sm.addLong(count);
					sendPacket(sm);
				} else {
					SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_DISAPPEARED);
					sm.addItemName(Inventory.ANCIENT_ADENA_ID);
					sendPacket(sm);
				}
			}
		}

		return true;
	}

	/**
	 * Adds item to inventory and send a Server->Client InventoryUpdate packet to the L2PcInstance.
	 *
	 * @param process     : String Identifier of process triggering this action
	 * @param item        : L2ItemInstance to be added
	 * @param reference   : L2Object Object referencing current action like NPC selling item or previous item in transformation
	 * @param sendMessage : boolean Specifies whether to send message to Client about this action
	 */
	public void addItem(String process, ItemInstance item, WorldObject reference, boolean sendMessage) {
		if (item.getCount() > 0) {
			if (!ItemContainer.validateCount(item.getId(), (getInventory().getInventoryItemCount(item.getId(), 0) + item.getCount()))) {
				if (sendMessage) {
					sendPacket(SystemMessageId.YOUR_INVENTORY_IS_FULL);
				}
				return;
			}

			// Sends message to client if requested
			if (sendMessage) {
				if (item.getCount() > 1) {
					SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_OBTAINED_S2_S1);
					sm.addItemName(item);
					sm.addLong(item.getCount());
					sendPacket(sm);
				} else if (item.getEnchantLevel() > 0) {
					SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_OBTAINED_A_S1_S2);
					sm.addInt(item.getEnchantLevel());
					sm.addItemName(item);
					sendPacket(sm);
				} else {
					SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_OBTAINED_S1);
					sm.addItemName(item);
					sendPacket(sm);
				}
			}

			// Add the item to inventory
			ItemInstance newitem = _inventory.addItem(process, item, this, reference);

			// If over capacity, drop the item
			if (!canOverrideCond(PcCondOverride.ITEM_CONDITIONS) && !_inventory.validateCapacity(0, item.isQuestItem()) && newitem.isDropable() && (!newitem.isStackable() || (newitem.getLastChange() != ItemInstance.MODIFIED))) {
				dropItem("InvDrop", newitem, null, true, true);
			} else if (CursedWeaponsManager.getInstance().isCursed(newitem.getId())) {
				CursedWeaponsManager.getInstance().activate(this, newitem);
			}

			// Combat Flag
			else if (FortSiegeManager.getInstance().isCombat(item.getId())) {
				if (FortSiegeManager.getInstance().activateCombatFlag(this, item)) {
					Fort fort = FortManager.getInstance().getFort(this);
					fort.getSiege().announceToPlayer(SystemMessage.getSystemMessage(SystemMessageId.C1_HAS_ACQUIRED_THE_FLAG), getName());
				}
			}
		}
	}

	/**
	 * Adds item to Inventory and send a Server->Client InventoryUpdate packet to the L2PcInstance.
	 *
	 * @param process     : String Identifier of process triggering this action
	 * @param itemId      : int Item Identifier of the item to be added
	 * @param count       : long Quantity of items to be added
	 * @param reference   : L2Object Object referencing current action like NPC selling item or previous item in transformation
	 * @param sendMessage : boolean Specifies whether to send message to Client about this action
	 * @return
	 */
	public ItemInstance addItem(String process, int itemId, long count, WorldObject reference, boolean sendMessage) {
		return addItem(process, itemId, count, reference, sendMessage, true);
	}

	public ItemInstance addItem(String process, int itemId, long count, WorldObject reference, boolean sendMessage, boolean sendInventoryUpdate) {
		if (count > 0) {
			final ItemTemplate item = ItemTable.getInstance().getTemplate(itemId);
			if (item == null) {
				LOGGER.error("Item doesn't exist so cannot be added. Item ID: " + itemId);
				return null;
			}

			if (!ItemContainer.validateCount(itemId, (getInventory().getInventoryItemCount(itemId, 0) + count))) {
				if (sendMessage) {
					sendPacket(SystemMessageId.YOUR_INVENTORY_IS_FULL);
				}
				return null;
			}

			// Sends message to client if requested
			if (sendMessage && ((!isCastingNow() && item.hasExImmediateEffect()) || !item.hasExImmediateEffect())) {
				if (count > 1) {
					if (process.equalsIgnoreCase("Sweeper") || process.equalsIgnoreCase("Quest")) {
						SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_EARNED_S2_S1_S);
						sm.addItemName(itemId);
						sm.addLong(count);
						sendPacket(sm);
					} else {
						SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_OBTAINED_S2_S1);
						sm.addItemName(itemId);
						sm.addLong(count);
						sendPacket(sm);
					}
				} else {
					if (process.equalsIgnoreCase("Sweeper") || process.equalsIgnoreCase("Quest")) {
						SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_EARNED_S1);
						sm.addItemName(itemId);
						sendPacket(sm);
					} else {
						SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_OBTAINED_S1);
						sm.addItemName(itemId);
						sendPacket(sm);
					}
				}
			}

			// Auto-use herbs.
			if (item.hasExImmediateEffect()) {
				final IItemHandler handler = ItemHandler.getInstance().getHandler(item instanceof EtcItem ? (EtcItem) item : null);
				if (handler == null) {
					LOGGER.warn("No item handler registered for Herb ID " + item.getId() + "!");
				} else {
					handler.useItem(this, new ItemInstance(itemId), false);
				}
			} else {
				// Add the item to inventory
				final ItemInstance createdItem = _inventory.addItem(process, itemId, count, this, reference, sendInventoryUpdate);

				// If over capacity, drop the item
				if (!canOverrideCond(PcCondOverride.ITEM_CONDITIONS) && !_inventory.validateCapacity(0, item.isQuestItem()) && createdItem.isDropable() && (!createdItem.isStackable() || (createdItem.getLastChange() != ItemInstance.MODIFIED))) {
					dropItem("InvDrop", createdItem, null, true);
				} else if (CursedWeaponsManager.getInstance().isCursed(createdItem.getId())) {
					CursedWeaponsManager.getInstance().activate(this, createdItem);
				}
				return createdItem;
			}
		}
		return null;
	}

	/**
	 * @param process     the process name
	 * @param item        the item holder
	 * @param reference   the reference object
	 * @param sendMessage if {@code true} a system message will be sent
	 */
	public void addItem(String process, ItemHolder item, WorldObject reference, boolean sendMessage) {
		addItem(process, item.getId(), item.getCount(), reference, sendMessage);
	}

	/**
	 * Destroy item from inventory and send a Server->Client InventoryUpdate packet to the L2PcInstance.
	 *
	 * @param process     : String Identifier of process triggering this action
	 * @param item        : L2ItemInstance to be destroyed
	 * @param reference   : L2Object Object referencing current action like NPC selling item or previous item in transformation
	 * @param sendMessage : boolean Specifies whether to send message to Client about this action
	 * @return boolean informing if the action was successful
	 */
	public boolean destroyItem(String process, ItemInstance item, WorldObject reference, boolean sendMessage) {
		return destroyItem(process, item, item.getCount(), reference, sendMessage);
	}

	/**
	 * Destroy item from inventory and send a Server->Client InventoryUpdate packet to the L2PcInstance.
	 *
	 * @param process     : String Identifier of process triggering this action
	 * @param item        : L2ItemInstance to be destroyed
	 * @param count
	 * @param reference   : L2Object Object referencing current action like NPC selling item or previous item in transformation
	 * @param sendMessage : boolean Specifies whether to send message to Client about this action
	 * @return boolean informing if the action was successful
	 */
	public boolean destroyItem(String process, ItemInstance item, long count, WorldObject reference, boolean sendMessage) {
		item = _inventory.destroyItem(process, item, count, this, reference);

		if (item == null) {
			if (sendMessage) {
				sendPacket(SystemMessageId.INCORRECT_ITEM_COUNT2);
			}
			return false;
		}

		// Send inventory update packet
		if (!GeneralConfig.FORCE_INVENTORY_UPDATE) {
			InventoryUpdate playerIU = new InventoryUpdate();
			playerIU.addItem(item);
			sendInventoryUpdate(playerIU);
		} else {
			sendItemList(false);
		}

		// Sends message to client if requested
		if (sendMessage) {
			if (count > 1) {
				SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S2_S1_S_DISAPPEARED);
				sm.addItemName(item);
				sm.addLong(count);
				sendPacket(sm);
			} else {
				SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_DISAPPEARED);
				sm.addItemName(item);
				sendPacket(sm);
			}
		}

		return true;
	}

	/**
	 * Destroys item from inventory and send a Server->Client InventoryUpdate packet to the L2PcInstance.
	 *
	 * @param process     : String Identifier of process triggering this action
	 * @param objectId    : int Item Instance identifier of the item to be destroyed
	 * @param count       : int Quantity of items to be destroyed
	 * @param reference   : L2Object Object referencing current action like NPC selling item or previous item in transformation
	 * @param sendMessage : boolean Specifies whether to send message to Client about this action
	 * @return boolean informing if the action was successful
	 */
	@Override
	public boolean destroyItem(String process, int objectId, long count, WorldObject reference, boolean sendMessage) {
		ItemInstance item = _inventory.getItemByObjectId(objectId);

		if (item == null) {
			if (sendMessage) {
				sendPacket(SystemMessageId.INCORRECT_ITEM_COUNT2);
			}

			return false;
		}
		return destroyItem(process, item, count, reference, sendMessage);
	}

	/**
	 * Destroys shots from inventory without logging and only occasional saving to database. Sends a Server->Client InventoryUpdate packet to the L2PcInstance.
	 *
	 * @param process     : String Identifier of process triggering this action
	 * @param objectId    : int Item Instance identifier of the item to be destroyed
	 * @param count       : int Quantity of items to be destroyed
	 * @param reference   : L2Object Object referencing current action like NPC selling item or previous item in transformation
	 * @param sendMessage : boolean Specifies whether to send message to Client about this action
	 * @return boolean informing if the action was successful
	 */
	public boolean destroyItemWithoutTrace(String process, int objectId, long count, WorldObject reference, boolean sendMessage) {
		ItemInstance item = _inventory.getItemByObjectId(objectId);

		if ((item == null) || (item.getCount() < count)) {
			if (sendMessage) {
				sendPacket(SystemMessageId.INCORRECT_ITEM_COUNT2);
			}

			return false;
		}

		return destroyItem(null, item, count, reference, sendMessage);
	}

	/**
	 * Destroy item from inventory by using its <B>itemId</B> and send a Server->Client InventoryUpdate packet to the L2PcInstance.
	 *
	 * @param process     : String Identifier of process triggering this action
	 * @param itemId      : int Item identifier of the item to be destroyed
	 * @param count       : int Quantity of items to be destroyed
	 * @param reference   : L2Object Object referencing current action like NPC selling item or previous item in transformation
	 * @param sendMessage : boolean Specifies whether to send message to Client about this action
	 * @return boolean informing if the action was successful
	 */
	@Override
	public boolean destroyItemByItemId(String process, int itemId, long count, WorldObject reference, boolean sendMessage) {
		if (itemId == Inventory.ADENA_ID) {
			return reduceAdena(process, count, reference, sendMessage);
		}

		ItemInstance item = _inventory.getItemByItemId(itemId);

		if ((item == null) || (item.getCount() < count) || (_inventory.destroyItemByItemId(process, itemId, count, this, reference) == null)) {
			if (sendMessage) {
				sendPacket(SystemMessageId.INCORRECT_ITEM_COUNT2);
			}

			return false;
		}

		// Send inventory update packet
		if (!GeneralConfig.FORCE_INVENTORY_UPDATE) {
			InventoryUpdate playerIU = new InventoryUpdate();
			playerIU.addItem(item);
			sendInventoryUpdate(playerIU);
		} else {
			sendItemList(false);
		}

		// Sends message to client if requested
		if (sendMessage) {
			if (count > 1) {
				SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S2_S1_S_DISAPPEARED);
				sm.addItemName(itemId);
				sm.addLong(count);
				sendPacket(sm);
			} else {
				SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_DISAPPEARED);
				sm.addItemName(itemId);
				sendPacket(sm);
			}
		}

		return true;
	}

	/**
	 * Transfers item to another ItemContainer and send a Server->Client InventoryUpdate packet to the L2PcInstance.
	 *
	 * @param process   : String Identifier of process triggering this action
	 * @param objectId  : int Item Identifier of the item to be transfered
	 * @param count     : long Quantity of items to be transfered
	 * @param target
	 * @param reference : L2Object Object referencing current action like NPC selling item or previous item in transformation
	 * @return L2ItemInstance corresponding to the new item or the updated item in inventory
	 */
	public ItemInstance transferItem(String process, int objectId, long count, Inventory target, WorldObject reference) {
		ItemInstance oldItem = checkItemManipulation(objectId, count, "transfer");
		if (oldItem == null) {
			return null;
		}
		ItemInstance newItem = getInventory().transferItem(process, objectId, count, target, this, reference);
		if (newItem == null) {
			return null;
		}

		// Send inventory update packet
		if (!GeneralConfig.FORCE_INVENTORY_UPDATE) {
			InventoryUpdate playerIU = new InventoryUpdate();

			if ((oldItem.getCount() > 0) && (oldItem != newItem)) {
				playerIU.addModifiedItem(oldItem);
			} else {
				playerIU.addRemovedItem(oldItem);
			}

			sendInventoryUpdate(playerIU);
		} else {
			sendItemList(false);
		}

		// Send target update packet
		if (target instanceof PcInventory) {
			Player targetPlayer = ((PcInventory) target).getOwner();

			if (!GeneralConfig.FORCE_INVENTORY_UPDATE) {
				InventoryUpdate playerIU = new InventoryUpdate();

				if (newItem.getCount() > count) {
					playerIU.addModifiedItem(newItem);
				} else {
					playerIU.addNewItem(newItem);
				}

				targetPlayer.sendPacket(playerIU);
			} else {
				targetPlayer.sendItemList(false);
			}
		} else if (target instanceof PetInventory) {
			PetInventoryUpdate petIU = new PetInventoryUpdate();

			if (newItem.getCount() > count) {
				petIU.addModifiedItem(newItem);
			} else {
				petIU.addNewItem(newItem);
			}

			((PetInventory) target).getOwner().sendPacket(petIU);
		}
		return newItem;
	}

	/**
	 * Use instead of calling {@link #addItem(String, ItemInstance, WorldObject, boolean)} and {@link #destroyItemByItemId(String, int, long, WorldObject, boolean)}<br>
	 * This method validates slots and weight limit, for stackable and non-stackable items.
	 *
	 * @param process     a generic string representing the process that is exchanging this items
	 * @param reference   the (probably NPC) reference, could be null
	 * @param coinId      the item Id of the item given on the exchange
	 * @param cost        the amount of items given on the exchange
	 * @param rewardId    the item received on the exchange
	 * @param count       the amount of items received on the exchange
	 * @param sendMessage if {@code true} it will send messages to the acting player
	 * @return {@code true} if the player successfully exchanged the items, {@code false} otherwise
	 */
	public boolean exchangeItemsById(String process, WorldObject reference, int coinId, long cost, int rewardId, long count, boolean sendMessage) {
		final PcInventory inv = getInventory();
		if (!inv.validateCapacityByItemId(rewardId, count)) {
			if (sendMessage) {
				sendPacket(SystemMessageId.YOUR_INVENTORY_IS_FULL);
			}
			return false;
		}

		if (!inv.validateWeightByItemId(rewardId, count)) {
			if (sendMessage) {
				sendPacket(SystemMessageId.YOU_HAVE_EXCEEDED_THE_WEIGHT_LIMIT);
			}
			return false;
		}

		if (destroyItemByItemId(process, coinId, cost, reference, sendMessage)) {
			addItem(process, rewardId, count, reference, sendMessage);
			return true;
		}
		return false;
	}

	/**
	 * Drop item from inventory and send a Server->Client InventoryUpdate packet to the L2PcInstance.
	 *
	 * @param process     String Identifier of process triggering this action
	 * @param item        L2ItemInstance to be dropped
	 * @param reference   L2Object Object referencing current action like NPC selling item or previous item in transformation
	 * @param sendMessage boolean Specifies whether to send message to Client about this action
	 * @param protectItem whether or not dropped item must be protected temporary against other players
	 * @return boolean informing if the action was successful
	 */
	public boolean dropItem(String process, ItemInstance item, WorldObject reference, boolean sendMessage, boolean protectItem) {
		item = _inventory.dropItem(process, item, this, reference);

		if (item == null) {
			if (sendMessage) {
				sendPacket(SystemMessageId.INCORRECT_ITEM_COUNT2);
			}

			return false;
		}

		item.dropMe(this, (getX() + Rnd.get(50)) - 25, (getY() + Rnd.get(50)) - 25, getZ() + 20);

		if ((GeneralConfig.AUTODESTROY_ITEM_AFTER > 0) && GeneralConfig.DESTROY_DROPPED_PLAYER_ITEM && !GeneralConfig.LIST_PROTECTED_ITEMS.contains(item.getId())) {
			if (!item.isEquipable() || GeneralConfig.DESTROY_EQUIPABLE_PLAYER_ITEM) {
				ItemsAutoDestroy.getInstance().addItem(item);
			}
		}

		// protection against auto destroy dropped item
		if (GeneralConfig.DESTROY_DROPPED_PLAYER_ITEM) {
			if (!item.isEquipable() || (item.isEquipable() && GeneralConfig.DESTROY_EQUIPABLE_PLAYER_ITEM)) {
				item.setProtected(false);
			} else {
				item.setProtected(true);
			}
		} else {
			item.setProtected(true);
		}

		// retail drop protection
		if (protectItem) {
			item.getDropProtection().protect(this);
		}

		// Send inventory update packet
		if (!GeneralConfig.FORCE_INVENTORY_UPDATE) {
			InventoryUpdate playerIU = new InventoryUpdate();
			playerIU.addItem(item);
			sendInventoryUpdate(playerIU);
		} else {
			sendItemList(false);
		}

		// Sends message to client if requested
		if (sendMessage) {
			SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_DROPPED_S1);
			sm.addItemName(item);
			sendPacket(sm);
		}

		return true;
	}

	public boolean dropItem(String process, ItemInstance item, WorldObject reference, boolean sendMessage) {
		return dropItem(process, item, reference, sendMessage, false);
	}

	/**
	 * Drop item from inventory by using its <B>objectID</B> and send a Server->Client InventoryUpdate packet to the L2PcInstance.
	 *
	 * @param process     : String Identifier of process triggering this action
	 * @param objectId    : int Item Instance identifier of the item to be dropped
	 * @param count       : long Quantity of items to be dropped
	 * @param x           : int coordinate for drop X
	 * @param y           : int coordinate for drop Y
	 * @param z           : int coordinate for drop Z
	 * @param reference   : L2Object Object referencing current action like NPC selling item or previous item in transformation
	 * @param sendMessage : boolean Specifies whether to send message to Client about this action
	 * @param protectItem
	 * @return L2ItemInstance corresponding to the new item or the updated item in inventory
	 */
	public ItemInstance dropItem(String process, int objectId, long count, int x, int y, int z, WorldObject reference, boolean sendMessage, boolean protectItem) {
		ItemInstance invitem = _inventory.getItemByObjectId(objectId);
		ItemInstance item = _inventory.dropItem(process, objectId, count, this, reference);

		if (item == null) {
			if (sendMessage) {
				sendPacket(SystemMessageId.INCORRECT_ITEM_COUNT2);
			}

			return null;
		}

		item.dropMe(this, x, y, z);

		if ((GeneralConfig.AUTODESTROY_ITEM_AFTER > 0) && GeneralConfig.DESTROY_DROPPED_PLAYER_ITEM && !GeneralConfig.LIST_PROTECTED_ITEMS.contains(item.getId())) {
			if (!item.isEquipable() || GeneralConfig.DESTROY_EQUIPABLE_PLAYER_ITEM) {
				ItemsAutoDestroy.getInstance().addItem(item);
			}
		}
		if (GeneralConfig.DESTROY_DROPPED_PLAYER_ITEM) {
			if (!item.isEquipable() || (item.isEquipable() && GeneralConfig.DESTROY_EQUIPABLE_PLAYER_ITEM)) {
				item.setProtected(false);
			} else {
				item.setProtected(true);
			}
		} else {
			item.setProtected(true);
		}

		// retail drop protection
		if (protectItem) {
			item.getDropProtection().protect(this);
		}

		// Send inventory update packet
		if (!GeneralConfig.FORCE_INVENTORY_UPDATE) {
			InventoryUpdate playerIU = new InventoryUpdate();
			playerIU.addItem(invitem);
			sendInventoryUpdate(playerIU);
		} else {
			sendItemList(false);
		}

		// Sends message to client if requested
		if (sendMessage) {
			SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_DROPPED_S1);
			sm.addItemName(item);
			sendPacket(sm);
		}

		return item;
	}

	public ItemInstance checkItemManipulation(int objectId, long count, String action) {
		// TODO: if we remove objects that are not visisble from the L2World, we'll have to remove this check
		if (!ItemStorage.getInstance().contains(objectId)) {
			LOGGER.trace(getObjectId() + ": player tried to " + action + " item not available in L2World");
			return null;
		}

		ItemInstance item = getInventory().getItemByObjectId(objectId);
		if ((item == null) || (item.getOwnerId() != getObjectId())) {
			LOGGER.trace(getObjectId() + ": player tried to " + action + " item he is not owner of");
			return null;
		}

		if ((count < 0) || ((count > 1) && !item.isStackable())) {
			LOGGER.trace(getObjectId() + ": player tried to " + action + " item with invalid count: " + count);
			return null;
		}

		if (count > item.getCount()) {
			LOGGER.trace(getObjectId() + ": player tried to " + action + " more items than he owns");
			return null;
		}

		// Pet is summoned and not the item that summoned the pet AND not the buggle from strider you're mounting
		final Summon pet = getPet();
		if (((pet != null) && (pet.getControlObjectId() == objectId)) || (getMountObjectID() == objectId)) {
			if (GeneralConfig.DEBUG) {
				LOGGER.trace(getObjectId() + ": player tried to " + action + " item controling pet");
			}

			return null;
		}

		if (isProcessingItem(objectId)) {
			if (GeneralConfig.DEBUG) {
				LOGGER.trace(getObjectId() + ":player tried to " + action + " an enchant scroll he was using");
			}
			return null;
		}

		// We cannot put a Weapon with Augmention in WH while casting (Possible Exploit)
		if (item.isAugmented() && isCastingNow()) {
			return null;
		}

		return item;
	}

	/**
	 * Set _protectEndTime according settings.
	 *
	 * @param protect
	 */
	public void setProtection(boolean protect) {
		if (GeneralConfig.DEVELOPER && (protect || (_protectEndTime > 0))) {
			LOGGER.warn(getName() + ": Protection " + (protect ? "ON " + (GameTimeManager.getInstance().getGameTicks() + (PlayerConfig.PLAYER_SPAWN_PROTECTION * GameTimeManager.TICKS_PER_SECOND)) : "OFF") + " (currently " + GameTimeManager.getInstance().getGameTicks() + ")");
		}

		_protectEndTime = protect ? GameTimeManager.getInstance().getGameTicks() + (PlayerConfig.PLAYER_SPAWN_PROTECTION * GameTimeManager.TICKS_PER_SECOND) : 0;
	}

	public void setTeleportProtection(boolean protect) {
		if (GeneralConfig.DEVELOPER && (protect || (_teleportProtectEndTime > 0))) {
			LOGGER.warn(getName() + ": Tele Protection " + (protect ? "ON " + (GameTimeManager.getInstance().getGameTicks() + (PlayerConfig.PLAYER_TELEPORT_PROTECTION * GameTimeManager.TICKS_PER_SECOND)) : "OFF") + " (currently " + GameTimeManager.getInstance().getGameTicks() + ")");
		}

		_teleportProtectEndTime = protect ? GameTimeManager.getInstance().getGameTicks() + (PlayerConfig.PLAYER_TELEPORT_PROTECTION * GameTimeManager.TICKS_PER_SECOND) : 0;
	}

	/**
	 * Set protection from agro mobs when getting up from fake death, according settings.
	 *
	 * @param protect
	 */
	public void setRecentFakeDeath(boolean protect) {
		_recentFakeDeathEndTime = protect ? GameTimeManager.getInstance().getGameTicks() + (PlayerConfig.PLAYER_FAKEDEATH_UP_PROTECTION * GameTimeManager.TICKS_PER_SECOND) : 0;
	}

	public boolean isRecentFakeDeath() {
		return _recentFakeDeathEndTime > GameTimeManager.getInstance().getGameTicks();
	}

	public final boolean isFakeDeath() {
		return getStat().has(BooleanStat.FAKE_DEATH);
	}

	@Override
	public final boolean isAlikeDead() {
		return super.isAlikeDead() || isFakeDeath();
	}

	public String getIPAddress() {
		return getIPAddress(null);
	}

	public String getIPAddress(String defaultIfNotAvailable) {
		String ip = defaultIfNotAvailable;
		if (client != null) {
			ip = client.getIP();
		}
		return ip;
	}

	public boolean hasSameIP(Player target) {
		if ((getIPAddress(null) == null) || (target.getIPAddress(null) == null)) {
			return false;
		}
		return client.getIP().equalsIgnoreCase(target.getClient().getIP());
	}

	public String getHWID() {
		return getHWID(null);
	}

	public String getHWID(String defaultIfNotAvailable) {
		String hwid = defaultIfNotAvailable;
		if ((client != null) && (client.getHWID() != null)) {
			hwid = client.getHWID();
		}
		return hwid;
	}

	public boolean hasSameHWID(Player target) {
		if ((getHWID(null) == null) || (target.getHWID(null) == null)) {
			return false;
		}
		return client.getHWID().equalsIgnoreCase(target.getClient().getHWID());
	}

	public ILocational getCurrentSkillWorldPosition() {
		return _currentSkillWorldPosition;
	}

	public void setCurrentSkillWorldPosition(ILocational worldPosition) {
		_currentSkillWorldPosition = worldPosition;
	}

	/**
	 * Returns true if cp update should be done, false if not
	 *
	 * @return boolean
	 */
	private boolean needCpUpdate() {
		double currentCp = getCurrentCp();

		if ((currentCp <= 1.0) || (getMaxCp() < MAX_HP_BAR_PX)) {
			return true;
		}

		if ((currentCp <= _cpUpdateDecCheck) || (currentCp >= _cpUpdateIncCheck)) {
			if (currentCp == getMaxCp()) {
				_cpUpdateIncCheck = currentCp + 1;
				_cpUpdateDecCheck = currentCp - _cpUpdateInterval;
			} else {
				double doubleMulti = currentCp / _cpUpdateInterval;
				int intMulti = (int) doubleMulti;

				_cpUpdateDecCheck = _cpUpdateInterval * (doubleMulti < intMulti ? intMulti-- : intMulti);
				_cpUpdateIncCheck = _cpUpdateDecCheck + _cpUpdateInterval;
			}

			return true;
		}

		return false;
	}

	/**
	 * Returns true if mp update should be done, false if not
	 *
	 * @return boolean
	 */
	private boolean needMpUpdate() {
		double currentMp = getCurrentMp();

		if ((currentMp <= 1.0) || (getMaxMp() < MAX_HP_BAR_PX)) {
			return true;
		}

		if ((currentMp <= _mpUpdateDecCheck) || (currentMp >= _mpUpdateIncCheck)) {
			if (currentMp == getMaxMp()) {
				_mpUpdateIncCheck = currentMp + 1;
				_mpUpdateDecCheck = currentMp - _mpUpdateInterval;
			} else {
				double doubleMulti = currentMp / _mpUpdateInterval;
				int intMulti = (int) doubleMulti;

				_mpUpdateDecCheck = _mpUpdateInterval * (doubleMulti < intMulti ? intMulti-- : intMulti);
				_mpUpdateIncCheck = _mpUpdateDecCheck + _mpUpdateInterval;
			}

			return true;
		}

		return false;
	}

	/**
	 * Send packet StatusUpdate with current HP,MP and CP to the L2PcInstance and only current HP, MP and Level to all other L2PcInstance of the Party. <B><U> Actions</U> :</B>
	 * <li>Send the Server->Client packet StatusUpdate with current HP, MP and CP to this L2PcInstance</li><BR>
	 * <li>Send the Server->Client packet PartySmallWindowUpdate with current HP, MP and Level to all other L2PcInstance of the Party</li> <FONT COLOR=#FF0000><B> <U>Caution</U> : This method DOESN'T SEND current HP and MP to all L2PcInstance of the _statusListener</B></FONT>
	 */
	@Override
	public void broadcastStatusUpdate(Creature caster) {
		final StatusUpdate su = new StatusUpdate(this);
		if (caster != null) {
			su.addCaster(caster);
		}

		computeStatusUpdate(su, StatusUpdateType.LEVEL);
		computeStatusUpdate(su, StatusUpdateType.MAX_HP);
		computeStatusUpdate(su, StatusUpdateType.CUR_HP);
		computeStatusUpdate(su, StatusUpdateType.MAX_MP);
		computeStatusUpdate(su, StatusUpdateType.CUR_MP);
		computeStatusUpdate(su, StatusUpdateType.MAX_CP);
		computeStatusUpdate(su, StatusUpdateType.CUR_CP);
		if (su.hasUpdates()) {
			broadcastPacket(su);
		}

		final boolean needCpUpdate = needCpUpdate();
		final boolean needHpUpdate = needHpUpdate();
		final boolean needMpUpdate = needMpUpdate();

		final Party party = getParty();

		// Check if a party is in progress and party window update is usefull
		if ((party != null) && (needCpUpdate || needHpUpdate || needMpUpdate)) {
			final PartySmallWindowUpdate partyWindow = new PartySmallWindowUpdate(this, false);
			if (needCpUpdate) {
				partyWindow.addComponentType(PartySmallWindowUpdateType.CURRENT_CP);
				partyWindow.addComponentType(PartySmallWindowUpdateType.MAX_CP);
			}
			if (needHpUpdate) {
				partyWindow.addComponentType(PartySmallWindowUpdateType.CURRENT_HP);
				partyWindow.addComponentType(PartySmallWindowUpdateType.MAX_HP);
			}
			if (needMpUpdate) {
				partyWindow.addComponentType(PartySmallWindowUpdateType.CURRENT_MP);
				partyWindow.addComponentType(PartySmallWindowUpdateType.MAX_MP);
			}
			party.broadcastToPartyMembers(this, partyWindow);
		}

		if (isInOlympiadMode() && isOlympiadStart() && (needCpUpdate || needHpUpdate)) {
			final OlympiadGameTask game = OlympiadGameManager.getInstance().getOlympiadTask(getOlympiadGameId());
			if ((game != null) && game.isBattleStarted()) {
				game.getStadium().broadcastStatusUpdate(this);
			}
		}

		// In duel MP updated only with CP or HP
		if (isInDuel() && (needCpUpdate || needHpUpdate)) {
			DuelManager.getInstance().broadcastToOppositTeam(this, new ExDuelUpdateUserInfo(this));
		}
	}

	/**
	 * Send a Server->Client packet UserInfo to this L2PcInstance and CharInfo to all L2PcInstance in its _KnownPlayers. <B><U> Concept</U> :</B> Others L2PcInstance in the detection area of the L2PcInstance are identified in <B>_knownPlayers</B>. In order to inform other players of this
	 * L2PcInstance state modifications, server just need to go through _knownPlayers to send Server->Client Packet <B><U> Actions</U> :</B>
	 * <li>Send a Server->Client packet UserInfo to this L2PcInstance (Public and Private Data)</li>
	 * <li>Send a Server->Client packet CharInfo to all L2PcInstance in _KnownPlayers of the L2PcInstance (Public data only)</li> <FONT COLOR=#FF0000><B> <U>Caution</U> : DON'T SEND UserInfo packet to other players instead of CharInfo packet. Indeed, UserInfo packet contains PRIVATE DATA as MaxHP,
	 * STR, DEX...</B></FONT>
	 */
	public final void broadcastUserInfo() {
		// Send user info to the current player
		sendPacket(new UserInfo(this));
		sendPacket(new ExBrPremiumState(this));

		// Broadcast char info to known players
		broadcastCharInfo();
	}

	public final void broadcastUserInfo(UserInfoType... types) {
		// Send user info to the current player
		UserInfo ui = new UserInfo(this, false);
		ui.addComponentType(types);
		sendPacket(ui);

		// Broadcast char info to all known players
		broadcastCharInfo();
	}

	public final void broadcastCharInfo() {
		final CharInfo charInfo = new CharInfo(this, false);
		getWorld().forEachVisibleObject(this, Player.class, player ->
		{
			if (isVisibleFor(player)) {
				if (isInvisible() && player.canOverrideCond(PcCondOverride.SEE_ALL_PLAYERS)) {
					player.sendPacket(new CharInfo(this, true));
				} else {
					player.sendPacket(charInfo);
				}

				sendRelationChanged(player);
			}
		});
	}

	public final void broadcastTitleInfo() {
		// Send a Server->Client packet UserInfo to this L2PcInstance
		UserInfo ui = new UserInfo(this, false);
		ui.addComponentType(UserInfoType.CLAN);
		sendPacket(ui);

		// Send a Server->Client packet TitleUpdate to all L2PcInstance in _KnownPlayers of the L2PcInstance
		broadcastPacket(new NicknameChanged(this));
	}

	@Override
	public final void broadcastPacket(GameServerPacket packet) {
		if (packet instanceof CharInfo) {
			throw new IllegalArgumentException("CharInfo is being sent via broadcastPacket. Do NOT do that! Use broadcastCharInfo() instead.");
		}

		sendPacket(packet);

		getWorld().forEachVisibleObject(this, Player.class, player ->
		{
			if (!isVisibleFor(player)) {
				return;
			}

			player.sendPacket(packet);
		});
	}

	@Override
	public void broadcastPacket(GameServerPacket mov, int radiusInKnownlist) {
		if (mov instanceof CharInfo) {
			throw new IllegalArgumentException("CharInfo is being sent via broadcastPacket. Do NOT do that! Use broadcastCharInfo() instead.");
		}

		sendPacket(mov);

		getWorld().forEachVisibleObjectInRadius(this, Player.class, radiusInKnownlist, player ->
		{
			if (!isVisibleFor(player)) {
				return;
			}
			player.sendPacket(mov);
		});
	}

	public void sendRelationChanged(Player target) {
		// Update relation.
		final int relation = getRelation(target);
		Integer oldrelation = getKnownRelations().get(target.getObjectId());
		if ((oldrelation == null) || (oldrelation != relation)) {
			// Do not add any objects invisible for the target in the relation changed packet.
			final RelationChanged rc = new RelationChanged();
			if (isVisibleFor(target)) {
				rc.addRelation(this, relation, isAutoAttackable(target));
			}
			if (hasSummon()) {
				final Summon pet = getPet();
				if ((pet != null) && pet.isVisibleFor(target)) {
					rc.addRelation(pet, relation, isAutoAttackable(target));
				}
				if (hasServitors()) {
					getServitors().values().stream().filter(s -> !s.isVisibleFor(target)).forEach(s -> rc.addRelation(s, relation, isAutoAttackable(target)));
				}
			}
			target.sendPacket(rc);
			getKnownRelations().put(target.getObjectId(), relation);
		}
	}

	public void broadcastRelationChanged() {
		getWorld().forEachVisibleObject(this, Player.class, player ->
		{
			if (!isVisibleFor(player)) {
				return;
			}

			sendRelationChanged(player);
		});
	}

	/**
	 * @return the Alliance Identifier of the L2PcInstance.
	 */
	@Override
	public int getAllyId() {
		if (_clan == null) {
			return 0;
		}
		return _clan.getAllyId();
	}

	public int getAllyCrestId() {
		if (getClanId() == 0) {
			return 0;
		}
		if (getClan().getAllyId() == 0) {
			return 0;
		}
		return getClan().getAllyCrestId();
	}

	//@formatter:off
	/*
	public void queryGameGuard()
	{
		if (getClient() != null)
		{
			getClient().setGameGuardOk(false);
			sendPacket(GameGuardQuery.STATIC_PACKET);
		}
		if (Config.GAMEGUARD_ENFORCE)
		{
			ThreadPool.getInstance().scheduleGeneral(new GameGuardCheckTask(this), 30 * 1000);
		}
	}*/
	//@formatter:on

	/**
	 * Send a Server->Client packet StatusUpdate to the L2PcInstance.
	 */
	@Override
	public void sendPacket(GameServerPacket... packets) {
		if (client != null) {
			for (GameServerPacket packet : packets) {
				client.sendPacket(packet);

				if (isDebug() && !(packet instanceof SystemMessage)) {
					final StringBuilder sb = new StringBuilder();
					final StackTraceElement[] stack = Thread.currentThread().getStackTrace();
					for (StackTraceElement element : stack) {
						sb.append(" < " + element.getFileName() + ":" + element.getLineNumber());
					}
					sendDebugMessage("[" + packet.getClass().getSimpleName() + "]" + sb.toString(), DebugType.PACKET);
				}
			}
		}
	}

	/**
	 * Send SystemMessage packet.
	 *
	 * @param id SystemMessageId
	 */
	@Override
	public void sendPacket(SystemMessageId id) {
		sendPacket(SystemMessage.getSystemMessage(id));
	}

	/**
	 * Manage Interact Task with another L2PcInstance. <B><U> Actions</U> :</B>
	 * <li>If the private store is a STORE_PRIVATE_SELL, send a Server->Client PrivateBuyListSell packet to the L2PcInstance</li>
	 * <li>If the private store is a STORE_PRIVATE_BUY, send a Server->Client PrivateBuyListBuy packet to the L2PcInstance</li>
	 * <li>If the private store is a STORE_PRIVATE_MANUFACTURE, send a Server->Client RecipeShopSellList packet to the L2PcInstance</li>
	 *
	 * @param target The L2Character targeted
	 */
	public void doInteract(Creature target) {
		if (target instanceof Player) {
			final Player targetPlayer = (Player) target;
			sendPacket(ActionFailed.STATIC_PACKET);

			if ((targetPlayer.getPrivateStoreType() == PrivateStoreType.SELL) || (targetPlayer.getPrivateStoreType() == PrivateStoreType.PACKAGE_SELL)) {
				if (isSellingBuffs()) {
					SellBuffsManager.getInstance().sendBuffMenu(this, targetPlayer, 0);
				} else {
					sendPacket(new PrivateStoreListSell(this, targetPlayer));
				}
			} else if (targetPlayer.getPrivateStoreType() == PrivateStoreType.BUY) {
				sendPacket(new PrivateStoreListBuy(this, targetPlayer));
			} else if (targetPlayer.getPrivateStoreType() == PrivateStoreType.MANUFACTURE) {
				sendPacket(new RecipeShopSellList(this, targetPlayer));
			}

		} else {
			// _interactTarget=null should never happen but one never knows ^^;
			if (target != null) {
				target.onAction(this);
			}
		}
	}

	/**
	 * Manages AutoLoot Task.<br>
	 * <ul>
	 * <li>Send a system message to the player.</li>
	 * <li>Add the item to the player's inventory.</li>
	 * <li>Send a Server->Client packet InventoryUpdate to this player with NewItem (use a new slot) or ModifiedItem (increase amount).</li>
	 * <li>Send a Server->Client packet StatusUpdate to this player with current weight.</li>
	 * </ul>
	 * <font color=#FF0000><B><U>Caution</U>: If a party is in progress, distribute the items between the party members!</b></font>
	 *
	 * @param target    the NPC dropping the item
	 * @param itemId    the item ID
	 * @param itemCount the item count
	 */
	public void doAutoLoot(Attackable target, int itemId, long itemCount) {
		if (isInParty() && !ItemTable.getInstance().getTemplate(itemId).hasExImmediateEffect()) {
			getParty().distributeItem(this, itemId, itemCount, false, target);
		} else if (itemId == Inventory.ADENA_ID) {
			addAdena("Loot", itemCount, target, true);
		} else {
			addItem("Loot", itemId, itemCount, target, true);
		}
	}

	/**
	 * Method overload for {@link Player#doAutoLoot(Attackable, int, long)}
	 *
	 * @param target the NPC dropping the item
	 * @param item   the item holder
	 */
	public void doAutoLoot(Attackable target, ItemHolder item) {
		doAutoLoot(target, item.getId(), item.getCount());
	}

	/**
	 * Manage Pickup Task. <B><U> Actions</U> :</B>
	 * <li>Send a Server->Client packet StopMove to this L2PcInstance</li>
	 * <li>Remove the L2ItemInstance from the world and send server->client GetItem packets</li>
	 * <li>Send a System Message to the L2PcInstance : YOU_PICKED_UP_S1_ADENA or YOU_PICKED_UP_S1_S2</li>
	 * <li>Add the Item to the L2PcInstance inventory</li>
	 * <li>Send a Server->Client packet InventoryUpdate to this L2PcInstance with NewItem (use a new slot) or ModifiedItem (increase amount)</li>
	 * <li>Send a Server->Client packet StatusUpdate to this L2PcInstance with current weight</li> <FONT COLOR=#FF0000><B> <U>Caution</U> : If a Party is in progress, distribute Items between party members</B></FONT>
	 *
	 * @param object The L2ItemInstance to pick up
	 */
	@Override
	public void doPickupItem(WorldObject object) {
		if (isAlikeDead() || isFakeDeath()) {
			return;
		}

		// Set the AI Intention to AI_INTENTION_IDLE
		getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);

		// Check if the L2Object to pick up is a L2ItemInstance
		if (!(object instanceof ItemInstance)) {
			// dont try to pickup anything that is not an item :)
			LOGGER.warn(this + " trying to pickup wrong target." + getTarget());
			return;
		}

		ItemInstance target = (ItemInstance) object;

		// Send a Server->Client packet ActionFailed to this L2PcInstance
		sendPacket(ActionFailed.STATIC_PACKET);

		// Send a Server->Client packet StopMove to this L2PcInstance
		StopMove sm = new StopMove(this);
		sendPacket(sm);

		SystemMessage smsg = null;
		synchronized (target) {
			final BooleanReturn term = EventDispatcher.getInstance().notifyEvent(new CanPlayerPickUp(this, target), this, BooleanReturn.class);
			if ((term != null) && !term.getValue()) {
				return;
			}

			// Check if the target to pick up is visible
			if (!target.isSpawned()) {
				// Send a Server->Client packet ActionFailed to this L2PcInstance
				sendPacket(ActionFailed.STATIC_PACKET);
				return;
			}

			if (!target.getDropProtection().tryPickUp(this)) {
				sendPacket(ActionFailed.STATIC_PACKET);
				smsg = SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_FAILED_TO_PICK_UP_S1);
				smsg.addItemName(target);
				sendPacket(smsg);
				return;
			}

			if (((isInParty() && (getParty().getDistributionType() == PartyDistributionType.FINDERS_KEEPERS)) || !isInParty()) && !_inventory.validateCapacity(target)) {
				sendPacket(ActionFailed.STATIC_PACKET);
				sendPacket(SystemMessageId.YOUR_INVENTORY_IS_FULL);
				return;
			}

			if (isInvul() && !canOverrideCond(PcCondOverride.ITEM_CONDITIONS)) {
				sendPacket(ActionFailed.STATIC_PACKET);
				smsg = SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_FAILED_TO_PICK_UP_S1);
				smsg.addItemName(target);
				sendPacket(smsg);
				return;
			}

			if ((target.getOwnerId() != 0) && (target.getOwnerId() != getObjectId()) && !isInLooterParty(target.getOwnerId())) {
				if (target.getId() == Inventory.ADENA_ID) {
					smsg = SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_FAILED_TO_PICK_UP_S1_ADENA);
					smsg.addLong(target.getCount());
				} else if (target.getCount() > 1) {
					smsg = SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_FAILED_TO_PICK_UP_S2_S1_S);
					smsg.addItemName(target);
					smsg.addLong(target.getCount());
				} else {
					smsg = SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_FAILED_TO_PICK_UP_S1);
					smsg.addItemName(target);
				}
				sendPacket(ActionFailed.STATIC_PACKET);
				sendPacket(smsg);
				return;
			}

			// You can pickup only 1 combat flag
			if (FortSiegeManager.getInstance().isCombat(target.getId())) {
				if (!FortSiegeManager.getInstance().checkIfCanPickup(this)) {
					return;
				}
			}

			if ((target.getItemLootShedule() != null) && ((target.getOwnerId() == getObjectId()) || isInLooterParty(target.getOwnerId()))) {
				target.resetOwnerTimer();
			}

			// Remove the L2ItemInstance from the world and send server->client GetItem packets
			target.pickupMe(this);
			if (GeneralConfig.SAVE_DROPPED_ITEM) {
				ItemsOnGroundManager.getInstance().removeObject(target);
			}
		}

		// Auto use herbs - pick up
		if (target.getItem().hasExImmediateEffect()) {
			IItemHandler handler = ItemHandler.getInstance().getHandler(target.getEtcItem());
			if (handler == null) {
				LOGGER.warn("No item handler registered for item ID: " + target.getId() + ".");
			} else {
				handler.useItem(this, target, false);
			}
			ItemTable.getInstance().destroyItem("Consume", target, this, null);
		}
		// Cursed Weapons are not distributed
		else if (CursedWeaponsManager.getInstance().isCursed(target.getId())) {
			addItem("Pickup", target, null, true);
		} else if (FortSiegeManager.getInstance().isCombat(target.getId())) {
			addItem("Pickup", target, null, true);
		} else {
			// if item is instance of L2ArmorType or L2WeaponType broadcast an "Attention" system message
			if ((target.getItemType() instanceof ArmorType) || (target.getItemType() instanceof WeaponType)) {
				if (target.getEnchantLevel() > 0) {
					smsg = SystemMessage.getSystemMessage(SystemMessageId.ATTENTION_C1_HAS_PICKED_UP_S2_S3);
					smsg.addPcName(this);
					smsg.addInt(target.getEnchantLevel());
					smsg.addItemName(target.getId());
					broadcastPacket(smsg, 1400);
				} else {
					smsg = SystemMessage.getSystemMessage(SystemMessageId.ATTENTION_C1_HAS_PICKED_UP_S2);
					smsg.addPcName(this);
					smsg.addItemName(target.getId());
					broadcastPacket(smsg, 1400);
				}
			}

			// Check if a Party is in progress
			if (isInParty()) {
				getParty().distributeItem(this, target);
			} else if ((target.getId() == Inventory.ADENA_ID) && (getInventory().getAdenaInstance() != null)) {
				addAdena("Pickup", target.getCount(), null, true);
				ItemTable.getInstance().destroyItem("Pickup", target, this, null);
			} else {
				addItem("Pickup", target, null, true);
				// Auto-Equip arrows/bolts if player has a bow/crossbow and player picks up arrows/bolts.
				final ItemInstance weapon = getInventory().getPaperdollItem(Inventory.PAPERDOLL_RHAND);
				if (weapon != null) {
					final EtcItem etcItem = target.getEtcItem();
					if (etcItem != null) {
						final EtcItemType itemType = etcItem.getItemType();
						if (((weapon.getItemType() == WeaponType.BOW) && (itemType == EtcItemType.ARROW)) || (((weapon.getItemType() == WeaponType.CROSSBOW) || (weapon.getItemType() == WeaponType.TWOHANDCROSSBOW)) && (itemType == EtcItemType.BOLT))) {
							checkAndEquipAmmunition(itemType);
						}
					}
				}
			}
		}
	}

	@Override
	public void doAutoAttack(Creature target) {
		super.doAutoAttack(target);
		setRecentFakeDeath(false);
	}

	@Override
	public void doCast(Skill skill) {
		super.doCast(skill);
		setRecentFakeDeath(false);
	}

	public boolean canOpenPrivateStore() {
		return !isSellingBuffs() && !isAlikeDead() && !isInOlympiadMode() && !isMounted() && !isInsideZone(ZoneId.NO_STORE) && !isCastingNow();
	}

	public void tryOpenPrivateBuyStore() {
		// Player shouldn't be able to set stores if he/she is alike dead (dead or fake death)
		if (canOpenPrivateStore()) {
			if ((getPrivateStoreType() == PrivateStoreType.BUY) || (getPrivateStoreType() == PrivateStoreType.BUY_MANAGE)) {
				setPrivateStoreType(PrivateStoreType.NONE);
			}
			if (getPrivateStoreType() == PrivateStoreType.NONE) {
				if (isSitting()) {
					standUp();
				}
				setPrivateStoreType(PrivateStoreType.BUY_MANAGE);
				sendPacket(new PrivateStoreManageListBuy(this));
			}
		} else {
			if (isInsideZone(ZoneId.NO_STORE)) {
				sendPacket(SystemMessageId.YOU_CANNOT_OPEN_A_PRIVATE_STORE_HERE);
			}
			sendPacket(ActionFailed.STATIC_PACKET);
		}
	}

	public final PreparedMultisellListHolder getMultiSell() {
		return _currentMultiSell;
	}

	public final void setMultiSell(PreparedMultisellListHolder list) {
		_currentMultiSell = list;
	}

	/**
	 * Set a target. <B><U> Actions</U> :</B>
	 * <ul>
	 * <li>Remove the L2PcInstance from the _statusListener of the old target if it was a L2Character</li>
	 * <li>Add the L2PcInstance to the _statusListener of the new target if it's a L2Character</li>
	 * <li>Target the new L2Object (add the target to the L2PcInstance _target, _knownObject and L2PcInstance to _KnownObject of the L2Object)</li>
	 * </ul>
	 *
	 * @param newTarget The L2Object to target
	 */
	@Override
	public void setTarget(WorldObject newTarget) {
		if (newTarget != null) {
			boolean isInParty = (newTarget.isPlayer() && isInParty() && getParty().containsPlayer(newTarget.getActingPlayer()));

			// Prevents /target exploiting
			if (!isInParty && (Math.abs(newTarget.getZ() - getZ()) > 1000)) {
				newTarget = null;
			}

			// Check if the new target is visible
			if ((newTarget != null) && !isInParty && !newTarget.isSpawned()) {
				newTarget = null;
			}

			// vehicles cant be targeted
			if (!isGM() && (newTarget instanceof Vehicle)) {
				newTarget = null;
			}
		}

		// Get the current target
		WorldObject oldTarget = getTarget();

		if (oldTarget != null) {
			if (oldTarget.equals(newTarget)) // no target change?
			{
				// Validate location of the target.
				if ((newTarget != null) && (newTarget.getObjectId() != getObjectId())) {
					sendPacket(new ValidateLocation(newTarget));
				}
				return;
			}

			// Remove the target from the status listener.
			oldTarget.removeStatusListener(this);
		}

		if (newTarget instanceof Creature) {
			final Creature target = (Creature) newTarget;

			// Validate location of the new target.
			if (newTarget.getObjectId() != getObjectId()) {
				sendPacket(new ValidateLocation(target));
			}

			// Show the client his new target.
			sendPacket(new MyTargetSelected(this, target));

			// Register target to listen for hp changes.
			target.addStatusListener(this);

			// Send max/current hp.
			final StatusUpdate su = new StatusUpdate(target);
			su.addUpdate(StatusUpdateType.MAX_HP, target.getMaxHp());
			su.addUpdate(StatusUpdateType.CUR_HP, (int) target.getCurrentHp());
			sendPacket(su);

			// To others the new target, and not yourself!
			Broadcast.toKnownPlayers(this, new TargetSelected(getObjectId(), newTarget.getObjectId(), getX(), getY(), getZ()));

			// Send buffs
			sendPacket(new ExAbnormalStatusUpdateFromTarget(target));
		}

		// Target was removed?
		if ((newTarget == null) && (getTarget() != null)) {
			broadcastPacket(new TargetUnselected(this));
		}

		// Target the new L2Object (add the target to the L2PcInstance _target, _knownObject and L2PcInstance to _KnownObject of the L2Object)
		super.setTarget(newTarget);
	}

	/**
	 * Return the active weapon instance (always equiped in the right hand).
	 */
	@Override
	public ItemInstance getActiveWeaponInstance() {
		return getInventory().getPaperdollItem(Inventory.PAPERDOLL_RHAND);
	}

	/**
	 * Return the active weapon item (always equiped in the right hand).
	 */
	@Override
	public Weapon getActiveWeaponItem() {
		final ItemInstance weapon = getActiveWeaponInstance();
		if (weapon == null) {
			return getFistsWeaponItem();
		}

		return (Weapon) weapon.getItem();
	}

	public ItemInstance getChestArmorInstance() {
		return getInventory().getPaperdollItem(Inventory.PAPERDOLL_CHEST);
	}

	public ItemInstance getLegsArmorInstance() {
		return getInventory().getPaperdollItem(Inventory.PAPERDOLL_LEGS);
	}

	public Armor getActiveChestArmorItem() {
		ItemInstance armor = getChestArmorInstance();

		if (armor == null) {
			return null;
		}

		return (Armor) armor.getItem();
	}

	public Armor getActiveLegsArmorItem() {
		ItemInstance legs = getLegsArmorInstance();

		if (legs == null) {
			return null;
		}

		return (Armor) legs.getItem();
	}

	public boolean isWearingHeavyArmor() {
		ItemInstance legs = getLegsArmorInstance();
		ItemInstance armor = getChestArmorInstance();

		if ((armor != null) && (legs != null)) {
			if ((legs.getItemType() == ArmorType.HEAVY) && (armor.getItemType() == ArmorType.HEAVY)) {
				return true;
			}
		}
		if (armor != null) {
			if (((getInventory().getPaperdollItem(Inventory.PAPERDOLL_CHEST).getItem().getBodyPart() == ItemTemplate.SLOT_FULL_ARMOR) && (armor.getItemType() == ArmorType.HEAVY))) {
				return true;
			}
		}
		return false;
	}

	public boolean isWearingLightArmor() {
		ItemInstance legs = getLegsArmorInstance();
		ItemInstance armor = getChestArmorInstance();

		if ((armor != null) && (legs != null)) {
			if ((legs.getItemType() == ArmorType.LIGHT) && (armor.getItemType() == ArmorType.LIGHT)) {
				return true;
			}
		}
		if (armor != null) {
			if (((getInventory().getPaperdollItem(Inventory.PAPERDOLL_CHEST).getItem().getBodyPart() == ItemTemplate.SLOT_FULL_ARMOR) && (armor.getItemType() == ArmorType.LIGHT))) {
				return true;
			}
		}
		return false;
	}

	public boolean isWearingMagicArmor() {
		ItemInstance legs = getLegsArmorInstance();
		ItemInstance armor = getChestArmorInstance();

		if ((armor != null) && (legs != null)) {
			if ((legs.getItemType() == ArmorType.MAGIC) && (armor.getItemType() == ArmorType.MAGIC)) {
				return true;
			}
		}
		if (armor != null) {
			if (((getInventory().getPaperdollItem(Inventory.PAPERDOLL_CHEST).getItem().getBodyPart() == ItemTemplate.SLOT_FULL_ARMOR) && (armor.getItemType() == ArmorType.MAGIC))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Return the secondary weapon instance (always equiped in the left hand).
	 */
	@Override
	public ItemInstance getSecondaryWeaponInstance() {
		return getInventory().getPaperdollItem(Inventory.PAPERDOLL_LHAND);
	}

	/**
	 * Return the secondary ItemTemplate item (always equiped in the left hand).<BR>
	 * Arrows, Shield..<BR>
	 */
	@Override
	public ItemTemplate getSecondaryWeaponItem() {
		final ItemInstance item = getInventory().getPaperdollItem(Inventory.PAPERDOLL_LHAND);
		if (item != null) {
			return item.getItem();
		}
		return null;
	}

	/**
	 * Kill the L2Character, Apply Death Penalty, Manage gain/loss Karma and Item Drop. <B><U> Actions</U> :</B>
	 * <li>Reduce the Experience of the L2PcInstance in function of the calculated Death Penalty</li>
	 * <li>If necessary, unsummon the Pet of the killed L2PcInstance</li>
	 * <li>Manage Karma gain for attacker and Karam loss for the killed L2PcInstance</li>
	 * <li>If the killed L2PcInstance has Karma, manage Drop Item</li>
	 * <li>Kill the L2PcInstance</li>
	 *
	 * @param killer
	 */
	@Override
	public boolean doDie(Creature killer) {
		if (killer != null) {
			final Player pk = killer.getActingPlayer();
			if (pk != null) {
				EventDispatcher.getInstance().notifyEventAsync(new OnPlayerPvPKill(pk, this), this);
			}

			broadcastStatusUpdate();
			// Clear resurrect xp calculation
			setExpBeforeDeath(0);

			// Calculate Shilen's Breath debuff level. It must happen right before death, because buffs aren't applied on dead characters.
			calculateShilensBreathDebuffLevel(killer);

			// Kill the L2PcInstance
			if (!super.doDie(killer)) {
				return false;
			}

			// Issues drop of Cursed Weapon.
			if (isCursedWeaponEquipped()) {
				CursedWeaponsManager.getInstance().drop(_cursedWeaponEquippedId, killer);
			} else if (isCombatFlagEquipped()) {
				final Fort fort = FortManager.getInstance().getFort(this);
				if (fort != null) {
					FortSiegeManager.getInstance().dropCombatFlag(this, fort.getResidenceId());
				} else {
					final int slot = getInventory().getSlotFromItem(getInventory().getItemByItemId(9819));
					getInventory().unEquipItemInBodySlot(slot);
					destroyItem("CombatFlag", getInventory().getItemByItemId(9819), null, true);
				}
			} else {
				final boolean insidePvpZone = isInsideZone(ZoneId.PVP) || isInsideZone(ZoneId.SIEGE);
				if ((pk == null) || !pk.isCursedWeaponEquipped()) {
					onDieDropItem(killer); // Check if any item should be dropped

					if (!insidePvpZone && (pk != null)) {
						final Clan pkClan = pk.getClan();
						if ((pkClan != null) && (getClan() != null) && !isAcademyMember() && !(pk.isAcademyMember())) {
							final ClanWar clanWar = _clan.getWarWith(pkClan.getId());
							if ((clanWar != null) && AntiFeedManager.getInstance().check(killer, this)) {
								clanWar.onKill(pk, this);
							}
						}
					}
					// If player is Lucky shouldn't get penalized.
					if (PlayerConfig.ALT_GAME_DELEVEL && !insidePvpZone) {
						calculateDeathExpPenalty(killer);
					}
				}
			}
		}

		if (isMounted()) {
			stopFeed();
		}
		synchronized (this) {
			if (isFakeDeath()) {
				stopFakeDeath(true);
			}
		}

		// Unsummon Cubics
		if (!_cubics.isEmpty()) {
			_cubics.values().forEach(CubicInstance::deactivate);
			_cubics.clear();
		}

		if (isChannelized()) {
			getSkillChannelized().abortChannelization();
		}

		if (getAgathionId() != 0) {
			setAgathionId(0);
			sendPacket(new ExUserInfoCubic(this));
			broadcastUserInfo();
		}

		stopRentPet();
		stopWaterTask();

		AntiFeedManager.getInstance().updateTimeOfDeath(getObjectId());

		return true;
	}

	private void onDieDropItem(Creature killer) {
		if (killer == null) {
			return;
		}

		final Player pk = killer.getActingPlayer();
		if ((getReputation() >= 0) && (pk != null) && (pk.getClan() != null) && (getClan() != null) && (pk.getClan().isAtWarWith(getClanId())
				// || getClan().isAtWarWith(((L2PcInstance)killer).getClanId())
		)) {
			return;
		}

		if ((!isInsideZone(ZoneId.PVP) || (pk == null)) && (!isGM() || PvpConfig.KARMA_DROP_GM)) {
			boolean isKarmaDrop = false;
			int pkLimit = PvpConfig.KARMA_PK_LIMIT;

			int dropEquip = 0;
			int dropEquipWeapon = 0;
			int dropItem = 0;
			int dropLimit = 0;
			int dropPercent = 0;

			if ((getReputation() < 0) && (getPkKills() >= pkLimit)) {
				isKarmaDrop = true;
				dropPercent = RatesConfig.KARMA_RATE_DROP;
				dropEquip = RatesConfig.KARMA_RATE_DROP_EQUIP;
				dropEquipWeapon = RatesConfig.KARMA_RATE_DROP_EQUIP_WEAPON;
				dropItem = RatesConfig.KARMA_RATE_DROP_ITEM;
				dropLimit = RatesConfig.KARMA_DROP_LIMIT;
			} else if (killer.isNpc() && (getLevel() > 4)) {
				dropPercent = RatesConfig.PLAYER_RATE_DROP;
				dropEquip = RatesConfig.PLAYER_RATE_DROP_EQUIP;
				dropEquipWeapon = RatesConfig.PLAYER_RATE_DROP_EQUIP_WEAPON;
				dropItem = RatesConfig.PLAYER_RATE_DROP_ITEM;
				dropLimit = RatesConfig.PLAYER_DROP_LIMIT;
			}

			if ((dropPercent > 0) && (Rnd.get(100) < dropPercent)) {
				int dropCount = 0;
				int itemDropPercent = 0;
				final Summon pet = getPet();

				for (ItemInstance itemDrop : getInventory().getItems()) {
					// Don't drop
					if (itemDrop.isShadowItem() || // Dont drop Shadow Items
							itemDrop.isTimeLimitedItem() || // Dont drop Time Limited Items
							!itemDrop.isDropable() || (itemDrop.getId() == Inventory.ADENA_ID) || // Adena
							(itemDrop.getItem().getType2() == ItemTemplate.TYPE2_QUEST) || // Quest Items
							((pet != null) && (pet.getControlObjectId() == itemDrop.getId())) || // Control Item of active pet
							(Arrays.binarySearch(PvpConfig.KARMA_LIST_NONDROPPABLE_ITEMS, itemDrop.getId()) >= 0) || // Item listed in the non droppable item list
							(Arrays.binarySearch(PvpConfig.KARMA_LIST_NONDROPPABLE_PET_ITEMS, itemDrop.getId()) >= 0 // Item listed in the non droppable pet item list
							)) {
						continue;
					}

					if (itemDrop.isEquipped()) {
						// Set proper chance according to Item type of equipped Item
						itemDropPercent = itemDrop.getItem().getType2() == ItemTemplate.TYPE2_WEAPON ? dropEquipWeapon : dropEquip;
						getInventory().unEquipItemInSlot(itemDrop.getLocationSlot());
					} else {
						itemDropPercent = dropItem; // Item in inventory
					}

					// NOTE: Each time an item is dropped, the chance of another item being dropped gets lesser (dropCount * 2)
					if (Rnd.get(100) < itemDropPercent) {
						dropItem("DieDrop", itemDrop, killer, true);

						if (isKarmaDrop) {
							LOGGER.warn(getName() + " has karma and dropped id = " + itemDrop.getId() + ", count = " + itemDrop.getCount());
						} else {
							LOGGER.warn(getName() + " dropped id = " + itemDrop.getId() + ", count = " + itemDrop.getCount());
						}

						if (++dropCount >= dropLimit) {
							break;
						}
					}
				}
			}
		}
	}

	public void onPlayerKill(Playable killedPlayable) {
		final Player player = getActingPlayer();
		final Player killedPlayer = killedPlayable.getActingPlayer();

		// Avoid nulls && check if player != killedPlayer
		if ((player == null) || (killedPlayer == null) || (player == killedPlayer)) {
			return;
		}

		// Cursed weapons progress
		if (player.isCursedWeaponEquipped() && killedPlayer.isPlayer()) {
			CursedWeaponsManager.getInstance().increaseKills(getCursedWeaponEquippedId());
			return;
		}

		// Duel support
		if (player.isInDuel() && killedPlayer.getActingPlayer().isInDuel()) {
			return;
		}

		// Do nothing if both players are in PVP zone
		if (player.isInsideZone(ZoneId.PVP) && killedPlayer.isInsideZone(ZoneId.PVP)) {
			return;
		}

		// If both players are in SIEGE zone just increase siege kills/deaths
		if (player.isInsideZone(ZoneId.SIEGE) && killedPlayer.isInsideZone(ZoneId.SIEGE)) {
			if ((player.getSiegeState() > 0) && (killedPlayer.getSiegeState() > 0) && (player.getSiegeState() != killedPlayer.getSiegeState())) {
				final Clan killerClan = player.getClan();
				final Clan targetClan = killedPlayer.getClan();
				if ((killerClan != null) && (targetClan != null)) {
					killerClan.addSiegeKill();
					targetClan.addSiegeDeath();
				}
			}
			return;
		}

		if (player.checkIfPvP(killedPlayer)) {
			// Check if player should get + rep
			if (killedPlayer.getReputation() < 0) {
				final int levelDiff = killedPlayer.getLevel() - player.getLevel();
				if ((player.getReputation() >= 0) && (levelDiff < 11) && (levelDiff > -11)) // TODO: Time check, same player can't be killed again in 8 hours
				{
					player.setReputation(player.getReputation() + PvpConfig.REPUTATION_INCREASE);
				}
			}

			player.setPvpKills(player.getPvpKills() + 1);
		} else {
			if ((getReputation() > 0) && (getPkKills() == 0)) {
				player.setReputation(0);
				player.setPkKills(player.getPkKills() + 1);
			} else {
				// Calculate new karma and increase pk count
				player.setReputation(player.getReputation() - Formulas.calculateKarmaGain(player.getPkKills(), killedPlayable.isSummon()));
				if (!killedPlayable.isSummon()) {
					player.setPkKills(player.getPkKills() + 1);
				}
			}
		}

		final UserInfo ui = new UserInfo(this, false);
		ui.addComponentType(UserInfoType.SOCIAL);
		player.sendPacket(ui);
		player.checkItemRestriction();
	}

	public void updatePvPStatus() {
		if (isInsideZone(ZoneId.PVP)) {
			return;
		}
		setPvpFlagLasts(System.currentTimeMillis() + PvpConfig.PVP_NORMAL_TIME);

		if (getPvpFlag() == 0) {
			startPvPFlag();
		}
	}

	public void updatePvPStatus(Creature target) {
		final Player player_target = target.getActingPlayer();
		if (player_target == null) {
			return;
		}

		if (this == player_target) {
			return;
		}

		if ((isInDuel() && (player_target.getDuelId() == getDuelId()))) {
			return;
		}

		if ((!isInsideZone(ZoneId.PVP) || !player_target.isInsideZone(ZoneId.PVP)) && (player_target.getReputation() >= 0)) {
			if (checkIfPvP(player_target)) {
				setPvpFlagLasts(System.currentTimeMillis() + PvpConfig.PVP_PVP_TIME);
			} else {
				setPvpFlagLasts(System.currentTimeMillis() + PvpConfig.PVP_NORMAL_TIME);
			}

			if (getPvpFlag() == 0) {
				startPvPFlag();
			}
		}
	}

	/**
	 * Restore the specified % of experience this L2PcInstance has lost and sends a Server->Client StatusUpdate packet.
	 *
	 * @param restorePercent
	 */
	public void restoreExp(double restorePercent) {
		if (getExpBeforeDeath() > 0) {
			// Restore the specified % of lost experience.
			getStat().addExp(Math.round(((getExpBeforeDeath() - getExp()) * restorePercent) / 100));
			setExpBeforeDeath(0);
		}
	}

	/**
	 * Reduce the Experience (and level if necessary) of the L2PcInstance in function of the calculated Death Penalty.<BR>
	 * <B><U> Actions</U> :</B>
	 * <li>Calculate the Experience loss</li>
	 * <li>Set the value of _expBeforeDeath</li>
	 * <li>Set the new Experience value of the L2PcInstance and Decrease its level if necessary</li>
	 * <li>Send a Server->Client StatusUpdate packet with its new Experience</li>
	 *
	 * @param killer
	 */
	public void calculateDeathExpPenalty(Creature killer) {
		final int lvl = getLevel();
		double percentLost = PlayerXpPercentLostData.getInstance().getXpPercent(getLevel());

		if (killer != null) {
			if (killer.isRaid()) {
				percentLost *= getStat().getValue(DoubleStat.REDUCE_EXP_LOST_BY_RAID, 1);
			} else if (killer.isMonster()) {
				percentLost *= getStat().getValue(DoubleStat.REDUCE_EXP_LOST_BY_MOB, 1);
			} else if (killer.isPlayable()) {
				percentLost *= getStat().getValue(DoubleStat.REDUCE_EXP_LOST_BY_PVP, 1);
			}
		}

		if (getReputation() < 0) {
			percentLost *= RatesConfig.RATE_KARMA_EXP_LOST;
		}

		// Calculate the Experience loss
		long lostExp = 0;

		if (lvl < ExperienceData.getInstance().getMaxLevel()) {
			lostExp = Math.round(((getStat().getExpForLevel(lvl + 1) - getStat().getExpForLevel(lvl)) * percentLost) / 100);
		} else {
			lostExp = Math.round(((getStat().getExpForLevel(ExperienceData.getInstance().getMaxLevel()) - getStat().getExpForLevel(ExperienceData.getInstance().getMaxLevel() - 1)) * percentLost) / 100);
		}

		if ((killer != null) && killer.isPlayable() && atWarWith(killer.getActingPlayer())) {
			lostExp /= 4.0;
		}

		final LongReturn term = EventDispatcher.getInstance().notifyEvent(new OnPlayerDeathExpPenalty(this, killer, lostExp), this, LongReturn.class);
		if (term != null) {
			if (term.terminate()) {
				return;
			} else if (term.override()) {
				lostExp = term.getValue();
			}
		}

		setExpBeforeDeath(getExp());
		getStat().removeExp(lostExp);
	}

	/**
	 * Stop the HP/MP/CP Regeneration task. <B><U> Actions</U> :</B>
	 * <li>Set the RegenActive flag to False</li>
	 * <li>Stop the HP/MP/CP Regeneration task</li>
	 */
	public void stopAllTimers() {
		stopHpMpRegeneration();
		stopWarnUserTakeBreak();
		stopWaterTask();
		stopFeed();
		clearPetData();
		storePetFood(_mountNpcId);
		stopRentPet();
		stopPvpRegTask();
		stopSoulTask();
		stopChargeTask();
		stopFameTask();
		stopRecoGiveTask();
		stopOnlineTimeUpdateTask();
	}

	@Override
	public PetInstance getPet() {
		return _pet;
	}

	@Override
	public Map<Integer, Summon> getServitors() {
		return _servitors == null ? Collections.emptyMap() : _servitors;
	}

	public Summon getAnyServitor() {
		return getServitors().values().stream().findAny().orElse(null);
	}

	public Summon getFirstServitor() {
		return getServitors().values().stream().findFirst().orElse(null);
	}

	@Override
	public Summon getServitor(int objectId) {
		return getServitors().get(objectId);
	}

	public List<Summon> getServitorsAndPets() {
		final List<Summon> summons = new ArrayList<>();
		summons.addAll(getServitors().values());

		final PetInstance pet = getPet();
		if (pet != null) {
			summons.add(pet);
		}

		return summons;
	}

	/**
	 * @return any summoned trap by this player or null.
	 */
	public TrapInstance getTrap() {
		return getSummonedNpcs().stream().filter(Npc::isTrap).map(TrapInstance.class::cast).findAny().orElse(null);
	}

	/**
	 * Set the summoned Pet of the L2PcInstance.
	 *
	 * @param pet
	 */
	public void setPet(PetInstance pet) {
		_pet = pet;
	}

	public void addServitor(Summon servitor) {
		if (_servitors == null) {
			synchronized (this) {
				if (_servitors == null) {
					_servitors = new ConcurrentHashMap<>(1);
				}
			}
		}
		_servitors.put(servitor.getObjectId(), servitor);
	}

	/**
	 * @return the L2Summon of the L2PcInstance or null.
	 */
	public Set<TamedBeastInstance> getTrainedBeasts() {
		return _tamedBeast;
	}

	/**
	 * Set the L2Summon of the L2PcInstance.
	 *
	 * @param tamedBeast
	 */
	public void addTrainedBeast(TamedBeastInstance tamedBeast) {
		if (_tamedBeast == null) {
			synchronized (this) {
				if (_tamedBeast == null) {
					_tamedBeast = ConcurrentHashMap.newKeySet();
				}
			}
		}
		_tamedBeast.add(tamedBeast);
	}

	/**
	 * @return the L2PcInstance requester of a transaction (ex : FriendInvite, JoinAlly, JoinParty...).
	 */
	public L2Request getRequest() {
		return _request;
	}

	/**
	 * Set the L2PcInstance requester of a transaction (ex : FriendInvite, JoinAlly, JoinParty...).
	 *
	 * @param requester
	 */
	public void setActiveRequester(Player requester) {
		_activeRequester = requester;
	}

	/**
	 * @return the L2PcInstance requester of a transaction (ex : FriendInvite, JoinAlly, JoinParty...).
	 */
	public Player getActiveRequester() {
		Player requester = _activeRequester;
		if (requester != null) {
			if (requester.isRequestExpired() && (_activeTradeList == null)) {
				_activeRequester = null;
			}
		}
		return _activeRequester;
	}

	/**
	 * @return True if a transaction is in progress.
	 */
	public boolean isProcessingRequest() {
		return (getActiveRequester() != null) || (_requestExpireTime > GameTimeManager.getInstance().getGameTicks());
	}

	/**
	 * @return True if a transaction is in progress.
	 */
	public boolean isProcessingTransaction() {
		return (getActiveRequester() != null) || (_activeTradeList != null) || (_requestExpireTime > GameTimeManager.getInstance().getGameTicks());
	}

	/**
	 * Select the Warehouse to be used in next activity.
	 *
	 * @param partner
	 */
	public void onTransactionRequest(Player partner) {
		_requestExpireTime = GameTimeManager.getInstance().getGameTicks() + (REQUEST_TIMEOUT * GameTimeManager.TICKS_PER_SECOND);
		partner.setActiveRequester(this);
	}

	/**
	 * Return true if last request is expired.
	 *
	 * @return
	 */
	public boolean isRequestExpired() {
		return !(_requestExpireTime > GameTimeManager.getInstance().getGameTicks());
	}

	/**
	 * Select the Warehouse to be used in next activity.
	 */
	public void onTransactionResponse() {
		_requestExpireTime = 0;
	}

	/**
	 * Select the Warehouse to be used in next activity.
	 *
	 * @param warehouse
	 */
	public void setActiveWarehouse(ItemContainer warehouse) {
		_activeWarehouse = warehouse;
	}

	/**
	 * @return active Warehouse.
	 */
	public ItemContainer getActiveWarehouse() {
		return _activeWarehouse;
	}

	/**
	 * Select the TradeList to be used in next activity.
	 *
	 * @param tradeList
	 */
	public void setActiveTradeList(TradeList tradeList) {
		_activeTradeList = tradeList;
	}

	/**
	 * @return active TradeList.
	 */
	public TradeList getActiveTradeList() {
		return _activeTradeList;
	}

	public void onTradeStart(Player partner) {
		_activeTradeList = new TradeList(this);
		_activeTradeList.setPartner(partner);

		SystemMessage msg = SystemMessage.getSystemMessage(SystemMessageId.YOU_BEGIN_TRADING_WITH_C1);
		msg.addPcName(partner);
		sendPacket(msg);
		sendPacket(new TradeStart(this));
	}

	public void onTradeConfirm(Player partner) {
		SystemMessage msg = SystemMessage.getSystemMessage(SystemMessageId.C1_HAS_CONFIRMED_THE_TRADE);
		msg.addPcName(partner);
		sendPacket(msg);
		sendPacket(TradeOtherDone.STATIC_PACKET);
	}

	public void onTradeCancel(Player partner) {
		if (_activeTradeList == null) {
			return;
		}

		_activeTradeList.lock();
		_activeTradeList = null;

		sendPacket(new TradeDone(0));
		SystemMessage msg = SystemMessage.getSystemMessage(SystemMessageId.C1_HAS_CANCELLED_THE_TRADE);
		msg.addPcName(partner);
		sendPacket(msg);
	}

	public void onTradeFinish(boolean successfull) {
		_activeTradeList = null;
		sendPacket(new TradeDone(1));
		if (successfull) {
			sendPacket(SystemMessageId.YOUR_TRADE_WAS_SUCCESSFUL);
		}
	}

	public void startTrade(Player partner) {
		onTradeStart(partner);
		partner.onTradeStart(this);
	}

	public void cancelActiveTrade() {
		if (_activeTradeList == null) {
			return;
		}

		Player partner = _activeTradeList.getPartner();
		if (partner != null) {
			partner.onTradeCancel(this);
		}
		onTradeCancel(this);
	}

	public boolean hasManufactureShop() {
		return (_manufactureItems != null) && !_manufactureItems.isEmpty();
	}

	/**
	 * Get the manufacture items map of this player.
	 *
	 * @return the the manufacture items map
	 */
	public Map<Integer, Long> getManufactureItems() {
		if (_manufactureItems == null) {
			return Collections.emptyMap();
		}

		return _manufactureItems;
	}

	public void setManufactureItems(Map<Integer, Long> manufactureItems) {
		_manufactureItems = manufactureItems;
	}

	/**
	 * Get the store name, if any.
	 *
	 * @return the store name
	 */
	public String getStoreName() {
		return _storeName;
	}

	/**
	 * Set the store name.
	 *
	 * @param name the store name to set
	 */
	public void setStoreName(String name) {
		_storeName = name == null ? "" : name;
	}

	/**
	 * @return the _buyList object of the L2PcInstance.
	 */
	public TradeList getSellList() {
		if (_sellList == null) {
			_sellList = new TradeList(this);
		}
		return _sellList;
	}

	/**
	 * @return the _buyList object of the L2PcInstance.
	 */
	public TradeList getBuyList() {
		if (_buyList == null) {
			_buyList = new TradeList(this);
		}
		return _buyList;
	}

	/**
	 * Set the Private Store type of the L2PcInstance. <B><U> Values </U> :</B>
	 * <li>0 : STORE_PRIVATE_NONE</li>
	 * <li>1 : STORE_PRIVATE_SELL</li>
	 * <li>2 : sellmanage</li><BR>
	 * <li>3 : STORE_PRIVATE_BUY</li><BR>
	 * <li>4 : buymanage</li><BR>
	 * <li>5 : STORE_PRIVATE_MANUFACTURE</li><BR>
	 *
	 * @param privateStoreType
	 */
	public void setPrivateStoreType(PrivateStoreType privateStoreType) {
		_privateStoreType = privateStoreType;

		if (L2JModsConfig.OFFLINE_DISCONNECT_FINISHED && (privateStoreType == PrivateStoreType.NONE) && ((getClient() == null) || getClient().isDetached())) {
			deleteMe();
		}
	}

	/**
	 * <B><U> Values </U> :</B>
	 * <li>0 : STORE_PRIVATE_NONE</li>
	 * <li>1 : STORE_PRIVATE_SELL</li>
	 * <li>2 : sellmanage</li><BR>
	 * <li>3 : STORE_PRIVATE_BUY</li><BR>
	 * <li>4 : buymanage</li><BR>
	 * <li>5 : STORE_PRIVATE_MANUFACTURE</li><BR>
	 *
	 * @return the Private Store type of the L2PcInstance.
	 */
	public PrivateStoreType getPrivateStoreType() {
		return _privateStoreType;
	}

	/**
	 * Set the _clan object, _clanId, _clanLeader Flag and title of the L2PcInstance.
	 *
	 * @param clan
	 */
	public void setClan(Clan clan) {
		_clan = clan;
		setTitle("");

		if (clan == null) {
			_clanId = 0;
			_clanPrivileges = new EnumIntBitmask<>(ClanPrivilege.class, false);
			_pledgeType = 0;
			_powerGrade = 0;
			_lvlJoinedAcademy = 0;
			_apprentice = 0;
			_sponsor = 0;
			_activeWarehouse = null;
			return;
		}

		if (!clan.isMember(getObjectId())) {
			// char has been kicked from clan
			setClan(null);
			return;
		}

		_clanId = clan.getId();
	}

	/**
	 * @return the _clan object of the L2PcInstance.
	 */
	@Override
	public Clan getClan() {
		return _clan;
	}

	/**
	 * @return True if the L2PcInstance is the leader of its clan.
	 */
	@Override
	public boolean isClanLeader() {
		if (getClan() == null) {
			return false;
		}
		return getObjectId() == getClan().getLeaderId();
	}

	/**
	 * Equip arrows needed in left hand and send a Server->Client packet ItemList to the L2PcINstance then return True.
	 *
	 * @param type
	 */
	@Override
	protected boolean checkAndEquipAmmunition(EtcItemType type) {
		ItemInstance arrows = getInventory().getPaperdollItem(Inventory.PAPERDOLL_LHAND);
		if (arrows == null) {
			Weapon weapon = getActiveWeaponItem();
			if (type == EtcItemType.ARROW) {
				arrows = getInventory().findArrowForBow(weapon);
			} else if (type == EtcItemType.BOLT) {
				arrows = getInventory().findBoltForCrossBow(weapon);
			}
			if (arrows != null) {
				// Equip arrows needed in left hand
				getInventory().setPaperdollItem(Inventory.PAPERDOLL_LHAND, arrows);
				sendItemList(false);
				return true;
			}
		} else {
			return true;
		}
		return false;
	}

	/**
	 * Disarm the player's weapon.
	 *
	 * @return {@code true} if the player was disarmed or doesn't have a weapon to disarm, {@code false} otherwise.
	 */
	@Override
	public boolean unequipWeapon() {
		// If there is no weapon to disarm then return true.
		final ItemInstance wpn = getInventory().getPaperdollItem(Inventory.PAPERDOLL_RHAND);
		if (wpn == null) {
			return true;
		}

		// Don't allow disarming a cursed weapon
		if (isCursedWeaponEquipped()) {
			return false;
		}

		// Don't allow disarming a Combat Flag or Territory Ward.
		if (isCombatFlagEquipped()) {
			return false;
		}

		// Don't allow disarming if the weapon is force equip.
		if (wpn.getWeaponItem().isForceEquip()) {
			return false;
		}

		final ItemInstance[] unequiped = getInventory().unEquipItemInBodySlotAndRecord(wpn.getItem().getBodyPart());
		final InventoryUpdate iu = new InventoryUpdate();
		for (ItemInstance itm : unequiped) {
			iu.addModifiedItem(itm);
		}

		sendInventoryUpdate(iu);
		abortAttack();
		broadcastUserInfo();

		// This can be 0 if the user pressed the right mousebutton twice very fast.
		if (unequiped.length > 0) {
			final SystemMessage sm;
			if (unequiped[0].getEnchantLevel() > 0) {
				sm = SystemMessage.getSystemMessage(SystemMessageId.THE_EQUIPMENT_S1_S2_HAS_BEEN_REMOVED);
				sm.addInt(unequiped[0].getEnchantLevel());
				sm.addItemName(unequiped[0]);
			} else {
				sm = SystemMessage.getSystemMessage(SystemMessageId.S1_HAS_BEEN_UNEQUIPPED);
				sm.addItemName(unequiped[0]);
			}
			sendPacket(sm);
		}
		return true;
	}

	/**
	 * Disarm the player's shield.
	 *
	 * @return {@code true}.
	 */
	public boolean disarmShield() {
		ItemInstance sld = getInventory().getPaperdollItem(Inventory.PAPERDOLL_LHAND);
		if (sld != null) {
			ItemInstance[] unequiped = getInventory().unEquipItemInBodySlotAndRecord(sld.getItem().getBodyPart());
			InventoryUpdate iu = new InventoryUpdate();
			for (ItemInstance itm : unequiped) {
				iu.addModifiedItem(itm);
			}
			sendInventoryUpdate(iu);

			abortAttack();
			broadcastUserInfo();

			// this can be 0 if the user pressed the right mousebutton twice very fast
			if (unequiped.length > 0) {
				SystemMessage sm = null;
				if (unequiped[0].getEnchantLevel() > 0) {
					sm = SystemMessage.getSystemMessage(SystemMessageId.THE_EQUIPMENT_S1_S2_HAS_BEEN_REMOVED);
					sm.addInt(unequiped[0].getEnchantLevel());
					sm.addItemName(unequiped[0]);
				} else {
					sm = SystemMessage.getSystemMessage(SystemMessageId.S1_HAS_BEEN_UNEQUIPPED);
					sm.addItemName(unequiped[0]);
				}
				sendPacket(sm);
			}
		}
		return true;
	}

	public boolean mount(Summon pet) {
		if (!unequipWeapon() || !disarmShield() || isTransformed()) {
			return false;
		}

		getEffectList().stopAllToggles();
		setMount(pet.getId(), pet.getLevel());
		setMountObjectID(pet.getControlObjectId());
		clearPetData();
		startFeed(pet.getId());
		broadcastPacket(new Ride(this));

		// Notify self and others about speed change
		broadcastUserInfo();

		pet.unSummon(this);
		return true;
	}

	public boolean mount(int npcId, int controlItemObjId, boolean useFood) {
		if (!unequipWeapon() || !disarmShield() || isTransformed()) {
			return false;
		}

		getEffectList().stopAllToggles();
		setMount(npcId, getLevel());
		clearPetData();
		setMountObjectID(controlItemObjId);
		broadcastPacket(new Ride(this));

		// Notify self and others about speed change
		broadcastUserInfo();
		if (useFood) {
			startFeed(npcId);
		}
		return true;
	}

	public boolean mountPlayer(Summon pet) {
		if ((pet != null) && pet.isMountable() && !isMounted() && !isBetrayed()) {
			if (isDead()) {
				// A strider cannot be ridden when dead
				sendPacket(ActionFailed.STATIC_PACKET);
				sendPacket(SystemMessageId.A_STRIDER_CANNOT_BE_RIDDEN_WHEN_DEAD);
				return false;
			} else if (pet.isDead()) {
				// A dead strider cannot be ridden.
				sendPacket(ActionFailed.STATIC_PACKET);
				sendPacket(SystemMessageId.A_DEAD_STRIDER_CANNOT_BE_RIDDEN);
				return false;
			} else if (pet.isInCombat() || pet.isImmobilized()) {
				// A strider in battle cannot be ridden
				sendPacket(ActionFailed.STATIC_PACKET);
				sendPacket(SystemMessageId.A_STRIDER_IN_BATTLE_CANNOT_BE_RIDDEN);
				return false;

			} else if (isInCombat()) {
				// A strider cannot be ridden while in battle
				sendPacket(ActionFailed.STATIC_PACKET);
				sendPacket(SystemMessageId.A_STRIDER_CANNOT_BE_RIDDEN_WHILE_IN_BATTLE);
				return false;
			} else if (isSitting()) {
				// A strider can be ridden only when standing
				sendPacket(ActionFailed.STATIC_PACKET);
				sendPacket(SystemMessageId.A_STRIDER_CAN_BE_RIDDEN_ONLY_WHEN_STANDING);
				return false;
			} else if (isFishing()) {
				// You can't mount, dismount, break and drop items while fishing
				sendPacket(ActionFailed.STATIC_PACKET);
				sendPacket(SystemMessageId.YOU_CANNOT_DO_THAT_WHILE_FISHING2);
				return false;
			} else if (isTransformed() || isCursedWeaponEquipped()) {
				// no message needed, player while transformed doesn't have mount action
				sendPacket(ActionFailed.STATIC_PACKET);
				return false;
			} else if (getInventory().getItemByItemId(9819) != null) {
				sendPacket(ActionFailed.STATIC_PACKET);
				// FIXME: Wrong Message
				sendMessage("You cannot mount a steed while holding a flag.");
				return false;
			} else if (pet.isHungry()) {
				sendPacket(ActionFailed.STATIC_PACKET);
				sendPacket(SystemMessageId.A_HUNGRY_STRIDER_CANNOT_BE_MOUNTED_OR_DISMOUNTED);
				return false;
			} else if (!Util.checkIfInRange(200, this, pet, true)) {
				sendPacket(ActionFailed.STATIC_PACKET);
				sendPacket(SystemMessageId.YOU_ARE_TOO_FAR_AWAY_FROM_YOUR_MOUNT_TO_RIDE);
				return false;
			} else if (!pet.isDead() && !isMounted()) {
				mount(pet);
			}
		} else if (isRentedPet()) {
			stopRentPet();
		} else if (isMounted()) {
			if ((getMountType() == MountType.WYVERN) && isInsideZone(ZoneId.NO_LANDING)) {
				sendPacket(ActionFailed.STATIC_PACKET);
				sendPacket(SystemMessageId.YOU_ARE_NOT_ALLOWED_TO_DISMOUNT_IN_THIS_LOCATION);
				return false;
			} else if (isHungry()) {
				sendPacket(ActionFailed.STATIC_PACKET);
				sendPacket(SystemMessageId.A_HUNGRY_STRIDER_CANNOT_BE_MOUNTED_OR_DISMOUNTED);
				return false;
			} else {
				dismount();
			}
		}
		return true;
	}

	public boolean dismount() {
		boolean wasFlying = isFlying();

		sendPacket(new SetupGauge(3, 0, 0));
		int petId = _mountNpcId;
		setMount(0, 0);
		stopFeed();
		clearPetData();
		if (wasFlying) {
			removeSkill(CommonSkill.WYVERN_BREATH.getSkill());
		}
		broadcastPacket(new Ride(this));
		setMountObjectID(0);
		storePetFood(petId);
		// Notify self and others about speed change
		broadcastUserInfo();
		return true;
	}

	public void setUptime(long time) {
		_uptime = time;
	}

	public long getUptime() {
		return System.currentTimeMillis() - _uptime;
	}

	/**
	 * Return True if the L2PcInstance is invulnerable.
	 */
	@Override
	public boolean isInvul() {
		final BooleanReturn term = EventDispatcher.getInstance().notifyEvent(new IsPlayerInvul(this), this, BooleanReturn.class);
		if (term != null) {
			return term.getValue();
		}

		return super.isInvul() || (_teleportProtectEndTime > GameTimeManager.getInstance().getGameTicks());
	}

	/**
	 * Return True if the L2PcInstance has a Party in progress.
	 */
	@Override
	public boolean isInParty() {
		return _party != null;
	}

	/**
	 * Set the _party object of the L2PcInstance (without joining it).
	 *
	 * @param party
	 */
	public void setParty(Party party) {
		_party = party;
	}

	/**
	 * Set the _party object of the L2PcInstance AND join it.
	 *
	 * @param party
	 */
	public void joinParty(Party party) {
		if (party != null) {
			// First set the party otherwise this wouldn't be considered
			// as in a party into the L2Character.updateEffectIcons() call.
			_party = party;
			party.addPartyMember(this);
		}
	}

	/**
	 * Manage the Leave Party task of the L2PcInstance.
	 */
	public void leaveParty() {
		final BooleanReturn term = EventDispatcher.getInstance().notifyEvent(new CanPlayerLeaveParty(this), this, BooleanReturn.class);
		if ((term != null) && !term.getValue()) {
			return;
		}

		if (isInParty()) {
			_party.removePartyMember(this, MessageType.DISCONNECTED);
			_party = null;
		}
	}

	/**
	 * Return the _party object of the L2PcInstance.
	 */
	@Override
	public Party getParty() {
		return _party;
	}

	public boolean isInCommandChannel() {
		return isInParty() && getParty().isInCommandChannel();
	}

	public CommandChannel getCommandChannel() {
		return (isInCommandChannel()) ? getParty().getCommandChannel() : null;
	}

	/**
	 * Return True if the L2PcInstance is a GM.
	 */
	@Override
	public boolean isGM() {
		return getAccessLevel().isGm();
	}

	/**
	 * Set the _accessLevel of the L2PcInstance.
	 *
	 * @param level
	 * @param broadcast
	 * @param updateInDb
	 */
	public void setAccessLevel(int level, boolean broadcast, boolean updateInDb) {
		AccessLevel accessLevel = AdminData.getInstance().getAccessLevel(level);
		if (accessLevel == null) {
			LOGGER.warn("Can't find access level {} for character {}", level, toString());
			accessLevel = AdminData.getInstance().getAccessLevel(0);
		}

		if ((accessLevel.getLevel() == 0) && (GeneralConfig.DEFAULT_ACCESS_LEVEL > 0)) {
			accessLevel = AdminData.getInstance().getAccessLevel(GeneralConfig.DEFAULT_ACCESS_LEVEL);
			if (accessLevel == null) {
				LOGGER.warn("Config's default access level ({}) is not defined, defaulting to 0!", GeneralConfig.DEFAULT_ACCESS_LEVEL);
				accessLevel = AdminData.getInstance().getAccessLevel(0);
				GeneralConfig.DEFAULT_ACCESS_LEVEL = 0;
			}
		}

		_accessLevel = accessLevel;

		getAppearance().setNameColor(_accessLevel.getNameColor());
		getAppearance().setTitleColor(_accessLevel.getTitleColor());
		if (broadcast) {
			broadcastUserInfo();
		}

		if (updateInDb) {
			try (Connection con = DatabaseFactory.getInstance().getConnection();
				 PreparedStatement ps = con.prepareStatement(UPDATE_CHARACTER_ACCESS)) {
				ps.setInt(1, accessLevel.getLevel());
				ps.setInt(2, getObjectId());
				ps.executeUpdate();
			} catch (SQLException e) {
				LOGGER.warn("Failed to update character's accesslevel in db: {}", toString(), e);
			}
		}

		CharNameTable.getInstance().addName(this);

		if (accessLevel == null) {
			LOGGER.warn("Tryed to set unregistered access level " + level + " for " + toString() + ". Setting access level without privileges!");
		} else if (level > 0) {
			LOGGER.warn(_accessLevel.getName() + " access level set for character " + getName() + "! Just a warning to be careful ;)");
		}
	}

	public void setAccountAccesslevel(int level) {
		GameServer.getInstance().getRmi().changeAccessLevel(getAccountName(), level);
	}

	/**
	 * @return the _accessLevel of the L2PcInstance.
	 */
	@Override
	public AccessLevel getAccessLevel() {
		return _accessLevel;
	}

	/**
	 * Update Stats of the L2PcInstance client side by sending Server->Client packet UserInfo/StatusUpdate to this L2PcInstance and CharInfo/StatusUpdate to all L2PcInstance in its _KnownPlayers (broadcast).
	 *
	 * @param broadcastType
	 */
	public void updateAndBroadcastStatus(int broadcastType) {
		refreshOverloaded(true);
		refreshExpertisePenalty();
		// Send a Server->Client packet UserInfo to this L2PcInstance and CharInfo to all L2PcInstance in its _KnownPlayers (broadcast)
		if (broadcastType == 1) {
			sendPacket(new UserInfo(this));
		}
		if (broadcastType == 2) {
			broadcastUserInfo();
		}
	}

	/**
	 * Send a Server->Client StatusUpdate packet with Karma to the L2PcInstance and all L2PcInstance to inform (broadcast).
	 */
	public void broadcastReputation() {
		broadcastUserInfo(UserInfoType.SOCIAL);
		if (hasSummon()) {
			getServitorsAndPets().forEach(Summon::broadcastReputation);
		}
	}

	/**
	 * Set the online Flag to True or False and update the characters table of the database with online status and lastAccess (called when login and logout).
	 *
	 * @param isOnline
	 * @param updateInDb
	 */
	public void setOnlineStatus(boolean isOnline, boolean updateInDb) {
		if (_isOnline != isOnline) {
			_isOnline = isOnline;
		}

		// Update the characters table of the database with online status and lastAccess (called when login and logout)
		if (updateInDb) {
			updateOnlineStatus();
		}
	}

	/**
	 * Update the characters table of the database with online status and lastAccess of this L2PcInstance (called when login and logout).
	 */
	public void updateOnlineStatus() {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement("UPDATE characters SET online=?, lastAccess=? WHERE charId=?")) {
			statement.setInt(1, isOnlineInt());
			statement.setLong(2, System.currentTimeMillis());
			statement.setInt(3, getObjectId());
			statement.execute();
		} catch (Exception e) {
			LOGGER.error("Failed updating character online status.", e);
		}
	}

	/**
	 * Create a new player in the characters table of the database.
	 *
	 * @return
	 */
	private boolean createDb() {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement(INSERT_CHARACTER)) {
			statement.setString(1, _accountName);
			statement.setInt(2, getObjectId());
			statement.setString(3, getName());
			statement.setInt(4, getLevel());
			statement.setInt(5, getMaxHp());
			statement.setDouble(6, getCurrentHp());
			statement.setInt(7, getMaxCp());
			statement.setDouble(8, getCurrentCp());
			statement.setInt(9, getMaxMp());
			statement.setDouble(10, getCurrentMp());
			statement.setInt(11, getAppearance().getFace());
			statement.setInt(12, getAppearance().getHairStyle());
			statement.setInt(13, getAppearance().getHairColor());
			statement.setInt(14, getAppearance().getSex() ? 1 : 0);
			statement.setLong(15, getExp());
			statement.setLong(16, getSp());
			statement.setInt(17, getReputation());
			statement.setInt(18, getFame());
			statement.setInt(19, getRaidbossPoints());
			statement.setInt(20, getPvpKills());
			statement.setInt(21, getPkKills());
			statement.setInt(22, getClanId());
			statement.setInt(23, getRace().ordinal());
			statement.setInt(24, getClassId().getId());
			statement.setLong(25, getDeleteTimer());
			statement.setInt(26, getCreateItemLevel() > 0 ? 1 : 0);
			statement.setString(27, getTitle());
			statement.setInt(28, getAppearance().getTitleColor());
			statement.setInt(29, isOnlineInt());
			statement.setInt(30, getClanPrivileges().getBitmask());
			statement.setInt(31, getWantsPeace());
			statement.setInt(32, getBaseClass());
			statement.setInt(33, isNoble() ? 1 : 0);
			statement.setLong(34, 0);
			statement.setInt(35, PcStat.MIN_VITALITY_POINTS);
			statement.setDate(36, new Date(getCreateDate().getTimeInMillis()));
			statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.error("Could not insert char data: " + e.getMessage(), e);
			return false;
		}
		return true;
	}

	/**
	 * Retrieve a L2PcInstance from the characters table of the database and add it in _allObjects of the L2world. <B><U> Actions</U> :</B>
	 * <li>Retrieve the L2PcInstance from the characters table of the database</li>
	 * <li>Add the L2PcInstance object in _allObjects</li>
	 * <li>Set the x,y,z position of the L2PcInstance and make it invisible</li>
	 * <li>Update the overloaded status of the L2PcInstance</li>
	 *
	 * @param objectId Identifier of the object to initialized
	 * @return The L2PcInstance loaded from the database
	 */
	private static Player restore(int objectId) {
		Player player = null;
		double currentCp = 0;
		double currentHp = 0;
		double currentMp = 0;
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement(RESTORE_CHARACTER)) {
			// Retrieve the L2PcInstance from the characters table of the database
			statement.setInt(1, objectId);
			try (ResultSet rset = statement.executeQuery()) {
				if (rset.next()) {
					final int activeClassId = rset.getInt("classid");
					final boolean female = rset.getInt("sex") != Sex.MALE.ordinal();
					final PcTemplate template = PlayerTemplateData.getInstance().getTemplate(activeClassId);
					final PcAppearance app = new PcAppearance(rset.getByte("face"), rset.getByte("hairColor"), rset.getByte("hairStyle"), female);

					player = new Player(objectId, template, rset.getString("account_name"), app);
					player.setName(rset.getString("char_name"));
					player.setLastAccess(rset.getLong("lastAccess"));

					player.getStat().setExp(rset.getLong("exp"));
					player.setExpBeforeDeath(rset.getLong("expBeforeDeath"));
					player.getStat().setLevel(rset.getByte("level"));
					player.getStat().setSp(rset.getLong("sp"));

					player.setWantsPeace(rset.getInt("wantspeace"));

					player.setHeading(rset.getInt("heading"));

					player.setReputation(rset.getInt("reputation"));
					player.setFame(rset.getInt("fame"));
					player.setRaidbossPoints(rset.getInt("raidbossPoints"));
					player.setPvpKills(rset.getInt("pvpkills"));
					player.setPkKills(rset.getInt("pkkills"));
					player.setOnlineTime(rset.getLong("onlinetime"));
					player.setNoble(NobleStatus.valueOf(rset.getInt("nobless")));

					player.setClanJoinExpiryTime(rset.getLong("clan_join_expiry_time"));
					if (player.getClanJoinExpiryTime() < System.currentTimeMillis()) {
						player.setClanJoinExpiryTime(0);
					}
					player.setClanCreateExpiryTime(rset.getLong("clan_create_expiry_time"));
					if (player.getClanCreateExpiryTime() < System.currentTimeMillis()) {
						player.setClanCreateExpiryTime(0);
					}

					int clanId = rset.getInt("clanid");
					player.setPowerGrade(rset.getInt("power_grade"));
					player.getStat().setVitalityPoints(rset.getInt("vitality_points"));
					player.setPledgeType(rset.getInt("subpledge"));
					// player.setApprentice(rset.getInt("apprentice"));

					// Set Hero status if it applies
					player.setHero(Hero.getInstance().isHero(objectId));

					if (clanId > 0) {
						player.setClan(ClanTable.getInstance().getClan(clanId));
					}

					if (player.getClan() != null) {
						if (player.getClan().getLeaderId() != player.getObjectId()) {
							if (player.getPowerGrade() == 0) {
								player.setPowerGrade(5);
							}
							player.setClanPrivileges(player.getClan().getRankPrivs(player.getPowerGrade()));
						} else {
							player.getClanPrivileges().setAll();
							player.setPowerGrade(1);
						}
						player.setPledgeClass(ClanMember.calculatePledgeClass(player));
					} else {
						if (player.isNoble()) {
							player.setPledgeClass(5);
						}

						if (player.isHero()) {
							player.setPledgeClass(8);
						}

						player.getClanPrivileges().clear();
					}

					player.setDeleteTimer(rset.getLong("deletetime"));
					player.setTitle(rset.getString("title"));
					player.setAccessLevel(rset.getInt("accesslevel"), false, false);
					int titleColor = rset.getInt("title_color");
					if (titleColor != PcAppearance.DEFAULT_TITLE_COLOR) {
						player.getAppearance().setTitleColor(titleColor);
					}
					player.setFistsWeaponItem(player.findFistsWeaponItem(activeClassId));
					player.setUptime(System.currentTimeMillis());

					currentHp = rset.getDouble("curHp");
					currentCp = rset.getDouble("curCp");
					currentMp = rset.getDouble("curMp");

					player.setClassIndex(0);
					try {
						player.setBaseClass(rset.getInt("base_class"));
					} catch (Exception e) {
						player.setBaseClass(activeClassId);
						LOGGER.warn("Exception during player.setBaseClass for player: base class: {}", player, rset.getInt("base_class"), e);
					}

					// Restore Subclass Data (cannot be done earlier in function)
					if (restoreSubClassData(player)) {
						if (activeClassId != player.getBaseClass()) {
							for (SubClass subClass : player.getSubClasses().values()) {
								if (subClass.getClassId() == activeClassId) {
									player.setClassIndex(subClass.getClassIndex());
								}
							}
						}
					}
					if ((player.getClassIndex() == 0) && (activeClassId != player.getBaseClass())) {
						// Subclass in use but doesn't exist in DB -
						// a possible restart-while-modifysubclass cheat has been attempted.
						// Switching to use base class
						player.setClassId(player.getBaseClass());
						LOGGER.warn("Player " + player.getName() + " reverted to base class. Possibly has tried a relogin exploit while subclassing.");
					} else {
						player._activeClass = activeClassId;
					}

					player.setApprentice(rset.getInt("apprentice"));
					player.setSponsor(rset.getInt("sponsor"));
					player.setLvlJoinedAcademy(rset.getInt("lvl_joined_academy"));

					CursedWeaponsManager.getInstance().checkPlayer(player);

					// Set the x,y,z position of the L2PcInstance and make it invisible
					player.setXYZInvisible(rset.getInt("x"), rset.getInt("y"), rset.getInt("z"));

					// Set Teleport Bookmark Slot
					player.setBookMarkSlot(rset.getInt("BookmarkSlot"));

					// character creation Time
					player.getCreateDate().setTime(rset.getDate("createDate"));

					// Retrieve the name and ID of the other characters assigned to this account.
					try (PreparedStatement stmt = con.prepareStatement("SELECT charId, char_name FROM characters WHERE account_name=? AND charId<>?")) {
						stmt.setString(1, player._accountName);
						stmt.setInt(2, objectId);
						try (ResultSet chars = stmt.executeQuery()) {
							while (chars.next()) {
								player._chars.put(chars.getInt("charId"), chars.getString("char_name"));
							}
						}
					}
				}
			}

			if (player == null) {
				return null;
			}

			if (player.isGM()) {
				final long masks = player.getVariables().getLong(COND_OVERRIDE_KEY, PcCondOverride.getAllExceptionsMask());
				player.setOverrideCond(masks);
			}

			// Retrieve from the database all skills of this L2PcInstance and add them to _skills.
			player.restoreSkills();

			// Reward auto-get skills and all available skills if auto-learn skills is true.
			player.rewardSkills();

			// Retrieve from the database all items of this L2PcInstance and add them to _inventory
			player.getInventory().restore();
			player.getFreight().restore();
			if (!GeneralConfig.WAREHOUSE_CACHE) {
				player.getWarehouse();
			}

			player.restoreItemReuse();

			// Retrieve from the database all secondary data of this L2PcInstance
			// Note that Clan, Noblesse and Hero skills are given separately and not here.
			// Retrieve from the database all skills of this L2PcInstance and add them to _skills
			player.restoreCharData();

			// Initialize status update cache
			player.initStatusUpdateCache();

			// Restore current Cp, HP and MP values
			player.setCurrentCp(currentCp);
			player.setCurrentHp(currentHp);
			player.setCurrentMp(currentMp);

			player.setOriginalCpHpMp(currentCp, currentHp, currentMp);

			if (currentHp < 0.5) {
				player.setIsDead(true);
				player.stopHpMpRegeneration();
			}

			// Restore pet if exists in the world
			player.setPet(WorldManager.getInstance().getPet(player.getObjectId()));
			final Summon pet = player.getPet();
			if (pet != null) {
				pet.setOwner(player);
			}

			if (player.hasServitors()) {
				for (Summon summon : player.getServitors().values()) {
					summon.setOwner(player);
				}
			}

			// Recalculate all stats
			player.getStat().recalculateStats(false);

			// Update the overloaded status of the L2PcInstance
			player.refreshOverloaded(false);

			// Update the expertise status of the L2PcInstance
			player.refreshExpertisePenalty();

			player.restoreFriendList();

			if (PlayerConfig.STORE_UI_SETTINGS) {
				player.restoreUISettings();
			}

			player.loadRecommendations();
			player.startRecoGiveTask();
			player.startOnlineTimeUpdateTask();

			player.setOnlineStatus(true, false);

			player.startAutoSaveTask();
		} catch (Exception e) {
			LOGGER.error("Failed loading character.", e);
		}
		return player;
	}

	/**
	 * @return
	 */
	public Forum getMail() {
		if (_forumMail == null) {
			setMail(ForumsBBSManager.getInstance().getForumByName("MailRoot").getChildByName(getName()));

			if (_forumMail == null) {
				ForumsBBSManager.getInstance().createNewForum(getName(), ForumsBBSManager.getInstance().getForumByName("MailRoot"), Forum.MAIL, Forum.OWNERONLY, getObjectId());
				setMail(ForumsBBSManager.getInstance().getForumByName("MailRoot").getChildByName(getName()));
			}
		}

		return _forumMail;
	}

	/**
	 * @param forum
	 */
	public void setMail(Forum forum) {
		_forumMail = forum;
	}

	/**
	 * @return
	 */
	public Forum getMemo() {
		if (_forumMemo == null) {
			setMemo(ForumsBBSManager.getInstance().getForumByName("MemoRoot").getChildByName(_accountName));

			if (_forumMemo == null) {
				ForumsBBSManager.getInstance().createNewForum(_accountName, ForumsBBSManager.getInstance().getForumByName("MemoRoot"), Forum.MEMO, Forum.OWNERONLY, getObjectId());
				setMemo(ForumsBBSManager.getInstance().getForumByName("MemoRoot").getChildByName(_accountName));
			}
		}

		return _forumMemo;
	}

	/**
	 * @param forum
	 */
	public void setMemo(Forum forum) {
		_forumMemo = forum;
	}

	/**
	 * Restores sub-class data for the L2PcInstance, used to check the current class index for the character.
	 *
	 * @param player
	 * @return
	 */
	private static boolean restoreSubClassData(Player player) {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement(RESTORE_CHAR_SUBCLASSES)) {
			statement.setInt(1, player.getObjectId());
			try (ResultSet rset = statement.executeQuery()) {
				while (rset.next()) {
					SubClass subClass = new SubClass();
					subClass.setClassId(rset.getInt("class_id"));
					subClass.setIsDualClass(rset.getBoolean("dual_class"));
					subClass.setVitalityPoints(rset.getInt("vitality_points"));
					subClass.setLevel(rset.getByte("level"));
					subClass.setExp(rset.getLong("exp"));
					subClass.setSp(rset.getLong("sp"));
					subClass.setClassIndex(rset.getInt("class_index"));

					// Enforce the correct indexing of _subClasses against their class indexes.
					player.getSubClasses().put(subClass.getClassIndex(), subClass);
				}
			}
		} catch (Exception e) {
			LOGGER.warn("Could not restore classes for " + player.getName() + ": " + e.getMessage(), e);
		}
		return true;
	}

	/**
	 * Restores:
	 * <ul>
	 * <li>Macros</li>
	 * <li>Short-cuts</li>
	 * <li>Henna</li>
	 * <li>Teleport Bookmark</li>
	 * <li>Recipe Book</li>
	 * <li>Recipe Shop List (If configuration enabled)</li>
	 * <li>Premium Item List</li>
	 * <li>Pet Inventory Items</li>
	 * </ul>
	 */
	private void restoreCharData() {
		// Retrieve from the database all macroses of this L2PcInstance and add them to _macros.
		_macros.restoreMe();

		// Retrieve from the database all shortCuts of this L2PcInstance and add them to _shortCuts.
		_shortCuts.restoreMe();

		// Retrieve from the database all henna of this L2PcInstance and add them to _henna.
		restoreHenna();

		// Retrieve from the database all teleport bookmark of this L2PcInstance and add them to _tpbookmark.
		restoreTeleportBookmark();

		// Retrieve from the database the recipe book of this L2PcInstance.
		restoreRecipeBook(true);

		// Restore Recipe Shop list.
		if (PlayerConfig.STORE_RECIPE_SHOPLIST) {
			restoreRecipeShopList();
		}

		// Load Premium Item List.
		loadPremiumItemList();

		// Restore items in pet inventory.
		restorePetInventoryItems();

		// Restore factions
		restoreFaction();
	}

	/**
	 * Restore recipe book data for this L2PcInstance.
	 *
	 * @param loadCommon
	 */
	private void restoreRecipeBook(boolean loadCommon) {
		final String sql = loadCommon ? "SELECT id, type, classIndex FROM character_recipebook WHERE charId=?" : "SELECT id FROM character_recipebook WHERE charId=? AND classIndex=? AND type = 1";
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setInt(1, getObjectId());
			if (!loadCommon) {
				statement.setInt(2, _classIndex);
			}

			try (ResultSet rset = statement.executeQuery()) {
				_dwarvenRecipeBook.clear();

				RecipeHolder recipe;
				RecipeData rd = RecipeData.getInstance();
				while (rset.next()) {
					recipe = rd.getRecipe(rset.getInt("id"));
					if (loadCommon) {
						if (rset.getInt(2) == 1) {
							if (rset.getInt(3) == _classIndex) {
								registerDwarvenRecipeList(recipe, false);
							}
						} else {
							registerCommonRecipeList(recipe, false);
						}
					} else {
						registerDwarvenRecipeList(recipe, false);
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("Could not restore recipe book data:" + e.getMessage(), e);
		}
	}

	public Map<Integer, PremiumItem> getPremiumItemList() {
		return _premiumItems;
	}

	private void loadPremiumItemList() {
		final String sql = "SELECT itemNum, itemId, itemCount, itemSender FROM character_premium_items WHERE charId=?";
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setInt(1, getObjectId());
			try (ResultSet rset = statement.executeQuery()) {
				while (rset.next()) {
					int itemNum = rset.getInt("itemNum");
					int itemId = rset.getInt("itemId");
					long itemCount = rset.getLong("itemCount");
					String itemSender = rset.getString("itemSender");
					_premiumItems.put(itemNum, new PremiumItem(itemId, itemCount, itemSender));
				}
			}
		} catch (Exception e) {
			LOGGER.error("Could not restore premium items: " + e.getMessage(), e);
		}
	}

	public void updatePremiumItem(int itemNum, long newcount) {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement("UPDATE character_premium_items SET itemCount=? WHERE charId=? AND itemNum=? ")) {
			statement.setLong(1, newcount);
			statement.setInt(2, getObjectId());
			statement.setInt(3, itemNum);
			statement.execute();
		} catch (Exception e) {
			LOGGER.error("Could not update premium items: " + e.getMessage(), e);
		}
	}

	public void deletePremiumItem(int itemNum) {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement("DELETE FROM character_premium_items WHERE charId=? AND itemNum=? ")) {
			statement.setInt(1, getObjectId());
			statement.setInt(2, itemNum);
			statement.execute();
		} catch (Exception e) {
			LOGGER.error("Could not delete premium item: " + e);
		}
	}

	private void storeFactions() {
		try (Connection con = DatabaseFactory.getInstance().getConnection()) {
			try (PreparedStatement st = con.prepareStatement(ADD_FACTION)) {
				con.setAutoCommit(false);
				for (Entry<Faction, Integer> entry : getFactionsPoints().entrySet()) {
					st.setInt(1, getObjectId());
					st.setString(2, entry.getKey().toString());
					st.setInt(3, entry.getValue());
					st.addBatch();
				}
				st.executeBatch();
				con.commit();
			}
		} catch (Exception e) {
			LOGGER.error("Could not store factions points for playerId " + getObjectId() + ": ", e);
		}
	}

	/**
	 * Update L2PcInstance stats in the characters table of the database.
	 *
	 * @param storeActiveEffects
	 */
	public synchronized void store(boolean storeActiveEffects) {
		storeCharBase();
		storeCharSub();
		storeEffect(storeActiveEffects);
		storeItemReuseDelay();
		if (PlayerConfig.STORE_RECIPE_SHOPLIST) {
			storeRecipeShopList();
		}
		if (PlayerConfig.STORE_UI_SETTINGS) {
			storeUISettings();
		}

		final PlayerVariables vars = getScript(PlayerVariables.class);
		if (vars != null) {
			vars.storeMe();
		}

		final AccountVariables aVars = getScript(AccountVariables.class);
		if (aVars != null) {
			aVars.storeMe();
		}
		storeFactions();
	}

	@Override
	public void storeMe() {
		store(true);
	}

	private void storeCharBase() {
		// Get the exp, level, and sp of base class to store in base table
		long exp = getStat().getBaseExp();
		int level = getStat().getBaseLevel();
		long sp = getStat().getBaseSp();
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement(UPDATE_CHARACTER)) {
			statement.setInt(1, level);
			statement.setInt(2, getMaxHp());
			statement.setDouble(3, getCurrentHp());
			statement.setInt(4, getMaxCp());
			statement.setDouble(5, getCurrentCp());
			statement.setInt(6, getMaxMp());
			statement.setDouble(7, getCurrentMp());
			statement.setInt(8, getAppearance().getFace());
			statement.setInt(9, getAppearance().getHairStyle());
			statement.setInt(10, getAppearance().getHairColor());
			statement.setInt(11, getAppearance().getSex() ? 1 : 0);
			statement.setInt(12, getHeading());
			statement.setInt(13, (int) (_lastLoc != null ? _lastLoc.getX() : getX()));
			statement.setInt(14, (int) (_lastLoc != null ? _lastLoc.getY() : getY()));
			statement.setInt(15, (int) (_lastLoc != null ? _lastLoc.getZ() : getZ()));
			statement.setLong(16, exp);
			statement.setLong(17, getExpBeforeDeath());
			statement.setLong(18, sp);
			statement.setInt(19, getReputation());
			statement.setInt(20, getFame());
			statement.setInt(21, getRaidbossPoints());
			statement.setInt(22, getPvpKills());
			statement.setInt(23, getPkKills());
			statement.setInt(24, getClanId());
			statement.setInt(25, getRace().ordinal());
			statement.setInt(26, getClassId().getId());
			statement.setLong(27, getDeleteTimer());
			statement.setString(28, getTitle());
			statement.setInt(29, getAppearance().getTitleColor());
			statement.setInt(30, isOnlineInt());
			statement.setInt(31, getClanPrivileges().getBitmask());
			statement.setInt(32, getWantsPeace());
			statement.setInt(33, getBaseClass());

			long totalOnlineTime = _onlineTime;
			if (_onlineBeginTime > 0) {
				totalOnlineTime += (System.currentTimeMillis() - _onlineBeginTime) / 1000;
			}

			statement.setLong(34, totalOnlineTime);
			statement.setInt(35, getNobleStatus().ordinal());
			statement.setInt(36, getPowerGrade());
			statement.setInt(37, getPledgeType());
			statement.setInt(38, getLvlJoinedAcademy());
			statement.setLong(39, getApprentice());
			statement.setLong(40, getSponsor());
			statement.setLong(41, getClanJoinExpiryTime());
			statement.setLong(42, getClanCreateExpiryTime());
			statement.setString(43, getName());
			statement.setInt(44, getBookMarkSlot());
			statement.setInt(45, getStat().getBaseVitalityPoints());
			statement.setString(46, ""); // TODO: Remove
			statement.setInt(47, getObjectId());
			statement.execute();
		} catch (Exception e) {
			LOGGER.warn("Could not store char base data: " + this + " - " + e.getMessage(), e);
		}
	}

	private void storeCharSub() {
		if (getTotalSubClasses() <= 0) {
			return;
		}

		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement(UPDATE_CHAR_SUBCLASS)) {
			for (SubClass subClass : getSubClasses().values()) {
				statement.setLong(1, subClass.getExp());
				statement.setLong(2, subClass.getSp());
				statement.setInt(3, subClass.getLevel());
				statement.setInt(4, subClass.getVitalityPoints());
				statement.setInt(5, subClass.getClassId());
				statement.setBoolean(6, subClass.isDualClass());
				statement.setInt(7, getObjectId());
				statement.setInt(8, subClass.getClassIndex());
				statement.execute();
				statement.clearParameters();
			}
		} catch (Exception e) {
			LOGGER.warn("Could not store sub class data for " + getName() + ": " + e.getMessage(), e);
		}
	}

	@Override
	public void storeEffect(boolean storeEffects) {
		if (!PlayerConfig.STORE_SKILL_COOLTIME) {
			return;
		}

		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement delete = con.prepareStatement(DELETE_SKILL_SAVE);
			 PreparedStatement statement = con.prepareStatement(ADD_SKILL_SAVE)) {
			// Delete all current stored effects for char to avoid dupe
			delete.setInt(1, getObjectId());
			delete.setInt(2, getClassIndex());
			delete.execute();

			int buff_index = 0;
			final List<Long> storedSkills = new ArrayList<>();

			// Store all effect data along with calulated remaining
			// reuse delays for matching skills. 'restore_type'= 0.
			if (storeEffects) {
				for (BuffInfo info : getEffectList().getEffects()) {
					if (info == null) {
						continue;
					}

					final Skill skill = info.getSkill();

					// Do not store those effects.
					if (skill.isDeleteAbnormalOnLeave()) {
						continue;
					}

					// Do not save heals.
					if (skill.getAbnormalType() == AbnormalType.LIFE_FORCE_OTHERS) {
						continue;
					}

					// Toggles are skipped, unless they are necessary to be always on.
					if ((skill.isToggle() && !skill.isNecessaryToggle())) {
						continue;
					}

					if (skill.isMentoring()) {
						continue;
					}

					// Dances and songs are not kept in retail.
					if (skill.isDance() && !PlayerConfig.ALT_STORE_DANCES) {
						continue;
					}

					if (storedSkills.contains(skill.getReuseHashCode())) {
						continue;
					}

					storedSkills.add(skill.getReuseHashCode());

					statement.setInt(1, getObjectId());
					statement.setInt(2, skill.getId());
					statement.setInt(3, skill.getLevel());
					statement.setInt(4, skill.getSubLevel());
					statement.setInt(5, info.getTime());

					final TimeStamp t = getSkillReuseTimeStamp(skill.getReuseHashCode());
					statement.setLong(6, (t != null) && t.hasNotPassed() ? t.getReuse() : 0);
					statement.setDouble(7, (t != null) && t.hasNotPassed() ? t.getStamp() : 0);

					statement.setInt(8, 0); // Store type 0, active buffs/debuffs.
					statement.setInt(9, getClassIndex());
					statement.setInt(10, ++buff_index);
					statement.execute();
				}
			}

			// Skills under reuse.
			final Map<Long, TimeStamp> reuseTimeStamps = getSkillReuseTimeStamps();
			if (reuseTimeStamps != null) {
				for (Entry<Long, TimeStamp> ts : reuseTimeStamps.entrySet()) {
					final long hash = ts.getKey();
					if (storedSkills.contains(hash)) {
						continue;
					}

					final TimeStamp t = ts.getValue();
					if ((t != null) && t.hasNotPassed()) {
						storedSkills.add(hash);

						statement.setInt(1, getObjectId());
						statement.setInt(2, t.getSkillId());
						statement.setInt(3, t.getSkillLvl());
						statement.setInt(4, t.getSkillSubLvl());
						statement.setInt(5, -1);
						statement.setLong(6, t.getReuse());
						statement.setDouble(7, t.getStamp());
						statement.setInt(8, 1); // Restore type 1, skill reuse.
						statement.setInt(9, getClassIndex());
						statement.setInt(10, ++buff_index);
						statement.execute();
					}
				}
			}
		} catch (Exception e) {
			LOGGER.warn("Could not store char effect data: ", e);
		}
	}

	private void storeItemReuseDelay() {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement ps1 = con.prepareStatement(DELETE_ITEM_REUSE_SAVE);
			 PreparedStatement ps2 = con.prepareStatement(ADD_ITEM_REUSE_SAVE)) {
			ps1.setInt(1, getObjectId());
			ps1.execute();

			final Map<Integer, TimeStamp> itemReuseTimeStamps = getItemReuseTimeStamps();
			if (itemReuseTimeStamps != null) {
				for (TimeStamp ts : itemReuseTimeStamps.values()) {
					if ((ts != null) && ts.hasNotPassed()) {
						ps2.setInt(1, getObjectId());
						ps2.setInt(2, ts.getItemId());
						ps2.setInt(3, ts.getItemObjectId());
						ps2.setLong(4, ts.getReuse());
						ps2.setDouble(5, ts.getStamp());
						ps2.execute();
					}
				}
			}
		} catch (Exception e) {
			LOGGER.warn("Could not store char item reuse data: ", e);
		}
	}

	/**
	 * @return True if the L2PcInstance is on line.
	 */
	public boolean isOnline() {
		return _isOnline;
	}

	public int isOnlineInt() {
		if (_isOnline && (getClient() != null)) {
			return getClient().isDetached() ? 2 : 1;
		}
		return 0;
	}

	@Override
	public Skill addSkill(Skill newSkill) {
		addCustomSkill(newSkill);
		return super.addSkill(newSkill);
	}

	/**
	 * Add a skill to the L2PcInstance _skills and its Func objects to the calculator set of the L2PcInstance and save update in the character_skills table of the database. <B><U> Concept</U> :</B> All skills own by a L2PcInstance are identified in <B>_skills</B> <B><U> Actions</U> :</B>
	 * <li>Replace oldSkill by newSkill or Add the newSkill</li>
	 * <li>If an old skill has been replaced, remove all its Func objects of L2Character calculator set</li>
	 * <li>Add Func objects of newSkill to the calculator set of the L2Character</li>
	 *
	 * @param newSkill The L2Skill to add to the L2Character
	 * @param store
	 * @return The L2Skill replaced or null if just added a new L2Skill
	 */
	public Skill addSkill(Skill newSkill, boolean store) {
		// Add a skill to the L2PcInstance _skills and its Func objects to the calculator set of the L2PcInstance
		final Skill oldSkill = addSkill(newSkill);
		// Add or update a L2PcInstance skill in the character_skills table of the database
		if (store) {
			storeSkill(newSkill, oldSkill, -1);
		}
		return oldSkill;
	}

	@Override
	public Skill removeSkill(Skill skill, boolean store) {
		removeCustomSkill(skill);
		return store ? removeSkill(skill) : super.removeSkill(skill, true);
	}

	public Skill removeSkill(Skill skill, boolean store, boolean cancelEffect) {
		removeCustomSkill(skill);
		return store ? removeSkill(skill) : super.removeSkill(skill, cancelEffect);
	}

	/**
	 * Remove a skill from the L2Character and its Func objects from calculator set of the L2Character and save update in the character_skills table of the database. <B><U> Concept</U> :</B> All skills own by a L2Character are identified in <B>_skills</B> <B><U> Actions</U> :</B>
	 * <li>Remove the skill from the L2Character _skills</li>
	 * <li>Remove all its Func objects from the L2Character calculator set</li> <B><U> Overridden in </U> :</B>
	 * <li>L2PcInstance : Save update in the character_skills table of the database</li>
	 *
	 * @param skill The L2Skill to remove from the L2Character
	 * @return The L2Skill removed
	 */
	public Skill removeSkill(Skill skill) {
		removeCustomSkill(skill);

		// Remove a skill from the Creature and its stats
		final Skill oldSkill = super.removeSkill(skill, true);
		if (oldSkill != null) {
			try (Connection con = DatabaseFactory.getInstance().getConnection();
				 PreparedStatement statement = con.prepareStatement(DELETE_SKILL_FROM_CHAR)) {
				// Remove or update a L2PcInstance skill from the character_skills table of the database
				statement.setInt(1, oldSkill.getId());
				statement.setInt(2, getObjectId());
				statement.setInt(3, getClassIndex());
				statement.execute();
			} catch (Exception e) {
				LOGGER.warn("Error could not delete skill: " + e.getMessage(), e);
			}
		}

		if ((getTransformationId() > 0) || isCursedWeaponEquipped()) {
			return oldSkill;
		}

		if (skill != null) {
			for (Shortcut sc : getAllShortCuts()) {
				if ((sc != null) && (sc.getId() == skill.getId()) && (sc.getType() == ShortcutType.SKILL) && !((skill.getId() >= 3080) && (skill.getId() <= 3259))) {
					deleteShortCut(sc.getSlot(), sc.getPage());
				}
			}
		}
		return oldSkill;
	}

	/**
	 * Add or update a L2PcInstance skill in the character_skills table of the database.<br>
	 * If newClassIndex > -1, the skill will be stored with that class index, not the current one.
	 *
	 * @param newSkill
	 * @param oldSkill
	 * @param newClassIndex
	 */
	private void storeSkill(Skill newSkill, Skill oldSkill, int newClassIndex) {
		final int classIndex = (newClassIndex > -1) ? newClassIndex : _classIndex;
		try (Connection con = DatabaseFactory.getInstance().getConnection()) {
			if ((oldSkill != null) && (newSkill != null)) {
				try (PreparedStatement ps = con.prepareStatement(UPDATE_CHARACTER_SKILL_LEVEL)) {
					ps.setInt(1, newSkill.getLevel());
					ps.setInt(2, newSkill.getSubLevel());
					ps.setInt(3, oldSkill.getId());
					ps.setInt(4, getObjectId());
					ps.setInt(5, classIndex);
					ps.execute();
				}
			} else if (newSkill != null) {
				try (PreparedStatement ps = con.prepareStatement(ADD_NEW_SKILL)) {
					ps.setInt(1, getObjectId());
					ps.setInt(2, newSkill.getId());
					ps.setInt(3, newSkill.getLevel());
					ps.setInt(4, newSkill.getSubLevel());
					ps.setInt(5, classIndex);
					ps.execute();
				}
			} else {
				LOGGER.warn("Could not store new skill, it's null!");
			}
		} catch (Exception e) {
			LOGGER.warn("Error could not store char skills: " + e.getMessage(), e);
		}
	}

	/**
	 * Adds or updates player's skills in the database.
	 *
	 * @param newSkills     the list of skills to store
	 * @param newClassIndex if newClassIndex > -1, the skills will be stored for that class index, not the current one
	 */
	private void storeSkills(List<Skill> newSkills, int newClassIndex) {
		if (newSkills.isEmpty()) {
			return;
		}

		final int classIndex = (newClassIndex > -1) ? newClassIndex : _classIndex;
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement ps = con.prepareStatement(ADD_NEW_SKILLS)) {
			con.setAutoCommit(false);
			for (final Skill addSkill : newSkills) {

				ps.setInt(1, getObjectId());
				ps.setInt(2, addSkill.getId());
				ps.setInt(3, addSkill.getLevel());
				ps.setInt(4, addSkill.getSubLevel());
				ps.setInt(5, classIndex);
				ps.addBatch();
			}
			ps.executeBatch();
			con.commit();
		} catch (SQLException e) {
			LOGGER.warn("Error could not store char skills: " + e.getMessage(), e);
		}
	}

	/**
	 * Retrieve from the database all skills of this L2PcInstance and add them to _skills.
	 */
	private void restoreSkills() {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement(RESTORE_SKILLS_FOR_CHAR)) {
			// Retrieve all skills of this L2PcInstance from the database
			statement.setInt(1, getObjectId());
			statement.setInt(2, getClassIndex());
			try (ResultSet rset = statement.executeQuery()) {
				while (rset.next()) {
					final int id = rset.getInt("skill_id");
					final int level = rset.getInt("skill_level");
					final int subLevel = rset.getInt("skill_sub_level");

					// Create a L2Skill object for each record
					final Skill skill = SkillData.getInstance().getSkill(id, level, subLevel);

					if (skill == null) {
						LOGGER.warn("Skipped null skill Id: " + id + " Level: " + level + " while restoring player skills for playerObjId: " + getObjectId());
						continue;
					}

					// Add the L2Skill object to the L2Character _skills and its Func objects to the calculator set of the L2Character
					addSkill(skill);

					if (GeneralConfig.SKILL_CHECK_ENABLE && (!canOverrideCond(PcCondOverride.SKILL_CONDITIONS) || GeneralConfig.SKILL_CHECK_GM)) {
						if (!SkillTreesData.getInstance().isSkillAllowed(this, skill)) {
							Util.handleIllegalPlayerAction(this, "Player " + getName() + " has invalid skill " + skill.getName() + " (" + skill.getId() + "/" + skill.getLevel() + "), class:" + ClassListData.getInstance().getClass(getClassId()).getClassName(), IllegalActionPunishmentType.BROADCAST);
							if (GeneralConfig.SKILL_CHECK_REMOVE) {
								removeSkill(skill);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LOGGER.warn("Could not restore character " + this + " skills: " + e.getMessage(), e);
		}
	}

	/**
	 * Retrieve from the database all skill effects of this L2PcInstance and add them to the player.
	 */
	@Override
	public void restoreEffects() {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement(RESTORE_SKILL_SAVE)) {
			statement.setInt(1, getObjectId());
			statement.setInt(2, getClassIndex());
			try (ResultSet rset = statement.executeQuery()) {
				while (rset.next()) {
					int remainingTime = rset.getInt("remaining_time");
					long reuseDelay = rset.getLong("reuse_delay");
					long systime = rset.getLong("systime");
					int restoreType = rset.getInt("restore_type");

					final Skill skill = SkillData.getInstance().getSkill(rset.getInt("skill_id"), rset.getInt("skill_level"), rset.getInt("skill_sub_level"));
					if (skill == null) {
						continue;
					}

					final long time = systime - System.currentTimeMillis();
					if (time > 10) {
						addTimeStamp(skill, reuseDelay, systime);
					}

					// Restore Type 1 The remaning skills lost effect upon logout but were still under a high reuse delay.
					if (restoreType > 0) {
						continue;
					}

					// Restore Type 0 These skill were still in effect on the character upon logout.
					// Some of which were self casted and might still have had a long reuse delay which also is restored.
					skill.applyEffects(this, this, false, remainingTime);
				}
			}
			// Remove previously restored skills
			try (PreparedStatement delete = con.prepareStatement(DELETE_SKILL_SAVE)) {
				delete.setInt(1, getObjectId());
				delete.setInt(2, getClassIndex());
				delete.executeUpdate();
			}
		} catch (Exception e) {
			LOGGER.warn("Could not restore " + this + " active effect data: " + e.getMessage(), e);
		}
	}

	/**
	 * Retrieve from the database all Item Reuse Time of this L2PcInstance and add them to the player.
	 */
	private void restoreItemReuse() {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement(RESTORE_ITEM_REUSE_SAVE);
			 PreparedStatement delete = con.prepareStatement(DELETE_ITEM_REUSE_SAVE)) {
			statement.setInt(1, getObjectId());
			try (ResultSet rset = statement.executeQuery()) {
				int itemId;
				long reuseDelay;
				long systime;
				boolean isInInventory;
				long remainingTime;
				while (rset.next()) {
					itemId = rset.getInt("itemId");
					rset.getInt("itemObjId");
					reuseDelay = rset.getLong("reuseDelay");
					systime = rset.getLong("systime");
					isInInventory = true;

					// Using item Id
					ItemInstance item = getInventory().getItemByItemId(itemId);
					if (item == null) {
						item = getWarehouse().getItemByItemId(itemId);
						isInInventory = false;
					}

					if ((item != null) && (item.getId() == itemId) && (item.getReuseDelay() > 0)) {
						remainingTime = systime - System.currentTimeMillis();
						// Hardcoded to 10 seconds.
						if (remainingTime > 10) {
							addTimeStampItem(item, reuseDelay, systime);

							if (isInInventory && item.isEtcItem()) {
								final int group = item.getSharedReuseGroup();
								if (group > 0) {
									sendPacket(new ExUseSharedGroupItem(itemId, group, (int) remainingTime, (int) reuseDelay));
								}
							}
						}
					}
				}
			}

			// Delete item reuse.
			delete.setInt(1, getObjectId());
			delete.executeUpdate();
		} catch (Exception e) {
			LOGGER.warn("Could not restore " + this + " Item Reuse data: " + e.getMessage(), e);
		}
	}

	private void restoreFaction() {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement(RESTORE_FACTIONS)) {
			statement.setInt(1, getObjectId());
			try (ResultSet rset = statement.executeQuery()) {
				while (rset.next()) {
					addFactionPoints(Faction.valueOf(rset.getString("faction_name")), rset.getInt("points"));
				}
			}
		} catch (Exception e) {
			LOGGER.error("Failed restoring character " + this + " factions.", e);
		}
	}

	/**
	 * Retrieve from the database all Henna of this L2PcInstance, add them to _henna and calculate stats of the L2PcInstance.
	 */
	private void restoreHenna() {
		for (int i = 0; i < 3; i++) {
			_henna[i] = null;
		}

		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement(RESTORE_CHAR_HENNAS)) {
			statement.setInt(1, getObjectId());
			statement.setInt(2, getClassIndex());
			try (ResultSet rset = statement.executeQuery()) {
				int slot;
				int symbolId;
				while (rset.next()) {
					slot = rset.getInt("slot");
					if ((slot < 1) || (slot > 3)) {
						continue;
					}

					symbolId = rset.getInt("symbol_id");
					if (symbolId == 0) {
						continue;
					}
					_henna[slot - 1] = HennaData.getInstance().getHenna(symbolId);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Failed restoing character " + this + " hennas.", e);
		}

		// Calculate henna modifiers of this player.
		recalcHennaStats();
	}

	/**
	 * @return the number of Henna empty slot of the L2PcInstance.
	 */
	public int getHennaEmptySlots() {
		int totalSlots = 0;
		if (isInCategory(CategoryType.SECOND_CLASS_GROUP)) {
			totalSlots = 2;
		} else {
			totalSlots = 3;
		}

		for (int i = 0; i < 3; i++) {
			if (_henna[i] != null) {
				totalSlots--;
			}
		}

		if (totalSlots <= 0) {
			return 0;
		}

		return totalSlots;
	}

	/**
	 * Remove a Henna of the L2PcInstance, save update in the character_hennas table of the database and send Server->Client HennaInfo/UserInfo packet to this L2PcInstance.
	 *
	 * @param slot
	 * @return
	 */
	public boolean removeHenna(int slot) {
		if ((slot < 1) || (slot > 3)) {
			return false;
		}

		slot--;

		Henna henna = _henna[slot];
		if (henna == null) {
			return false;
		}

		_henna[slot] = null;

		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement(DELETE_CHAR_HENNA)) {
			statement.setInt(1, getObjectId());
			statement.setInt(2, slot + 1);
			statement.setInt(3, getClassIndex());
			statement.execute();
		} catch (Exception e) {
			LOGGER.error("Failed remocing character henna.", e);
		}

		// Calculate Henna modifiers of this L2PcInstance
		recalcHennaStats();

		// Send Server->Client HennaInfo packet to this L2PcInstance
		sendPacket(new HennaInfo(this));

		// Send Server->Client UserInfo packet to this L2PcInstance
		UserInfo ui = new UserInfo(this, false);
		ui.addComponentType(UserInfoType.BASE_STATS, UserInfoType.MAX_HPCPMP, UserInfoType.STATS, UserInfoType.SPEED);
		sendPacket(ui);
		// Add the recovered dyes to the player's inventory and notify them.
		getInventory().addItem("Henna", henna.getDyeItemId(), henna.getCancelCount(), this, null);
		reduceAdena("Henna", henna.getCancelFee(), this, false);

		final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_EARNED_S2_S1_S);
		sm.addItemName(henna.getDyeItemId());
		sm.addLong(henna.getCancelCount());
		sendPacket(sm);
		sendPacket(SystemMessageId.THE_SYMBOL_HAS_BEEN_DELETED);

		// Notify to scripts
		EventDispatcher.getInstance().notifyEventAsync(new OnPlayerHennaRemove(this, henna), this);
		return true;
	}

	/**
	 * Add a Henna to the L2PcInstance, save update in the character_hennas table of the database and send Server->Client HennaInfo/UserInfo packet to this L2PcInstance.
	 *
	 * @param henna the henna to add to the player.
	 * @return {@code true} if the henna is added to the player, {@code false} otherwise.
	 */
	public boolean addHenna(Henna henna) {
		for (int i = 0; i < 3; i++) {
			if (_henna[i] == null) {
				_henna[i] = henna;

				// Calculate Henna modifiers of this L2PcInstance
				recalcHennaStats();

				try (Connection con = DatabaseFactory.getInstance().getConnection();
					 PreparedStatement statement = con.prepareStatement(ADD_CHAR_HENNA)) {
					statement.setInt(1, getObjectId());
					statement.setInt(2, henna.getDyeId());
					statement.setInt(3, i + 1);
					statement.setInt(4, getClassIndex());
					statement.execute();
				} catch (Exception e) {
					LOGGER.error("Failed saving character henna.", e);
				}

				// Send Server->Client HennaInfo packet to this L2PcInstance
				sendPacket(new HennaInfo(this));

				// Send Server->Client UserInfo packet to this L2PcInstance
				UserInfo ui = new UserInfo(this, false);
				ui.addComponentType(UserInfoType.BASE_STATS, UserInfoType.MAX_HPCPMP, UserInfoType.STATS, UserInfoType.SPEED);
				sendPacket(ui);

				// Notify to scripts
				EventDispatcher.getInstance().notifyEventAsync(new OnPlayerHennaAdd(this, henna), this);
				return true;
			}
		}
		return false;
	}

	/**
	 * Calculate Henna modifiers of this L2PcInstance.
	 */
	private void recalcHennaStats() {
		_hennaBaseStats.clear();
		for (Henna henna : _henna) {
			if (henna == null) {
				continue;
			}

			for (Entry<BaseStats, Integer> entry : henna.getBaseStats().entrySet()) {
				_hennaBaseStats.merge(entry.getKey(), entry.getValue(), Integer::sum);
			}
		}
	}

	/**
	 * @param slot the character inventory henna slot.
	 * @return the Henna of this L2PcInstance corresponding to the selected slot.
	 */
	public Henna getHenna(int slot) {
		if ((slot < 1) || (slot > 3)) {
			return null;
		}
		return _henna[slot - 1];
	}

	/**
	 * @return {@code true} if player has at least 1 henna symbol, {@code false} otherwise.
	 */
	public boolean hasHennas() {
		for (Henna henna : _henna) {
			if (henna != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return the henna holder for this player.
	 */
	public Henna[] getHennaList() {
		return _henna;
	}

	/**
	 * @param stat
	 * @return the henna bonus of specified base stat
	 */
	public int getHennaValue(BaseStats stat) {
		return _hennaBaseStats.getOrDefault(stat, 0);
	}

	/**
	 * @return map of all henna base stats bonus
	 */
	public Map<BaseStats, Integer> getHennaBaseStats() {
		return _hennaBaseStats;
	}

	/**
	 * Checks if the player has basic property resist towards mesmerizing debuffs.
	 *
	 * @return {@code true} if the player has resist towards mesmerizing debuffs, {@code false} otherwise
	 */
	@Override
	public boolean hasBasicPropertyResist() {
		return isAwakenedClass();
	}

	/**
	 * Return True if the L2PcInstance is autoAttackable.<br>
	 * <B><U>Actions</U>:</B>
	 * <ul>
	 * <li>Check if the attacker isn't the L2PcInstance Pet</li>
	 * <li>Check if the attacker is MonsterInstance</li>
	 * <li>If the attacker is a L2PcInstance, check if it is not in the same party</li>
	 * <li>Check if the L2PcInstance has Karma</li>
	 * <li>If the attacker is a L2PcInstance, check if it is not in the same siege clan (Attacker, Defender)</li>
	 * </ul>
	 */
	@Override
	public boolean isAutoAttackable(Creature attacker) {
		if (attacker == null) {
			return false;
		}

		// Check if the attacker isn't the L2PcInstance Pet
		if ((attacker == this) || (attacker == getPet()) || attacker.hasServitor(attacker.getObjectId())) {
			return false;
		}

		final CombatStateReturn term = EventDispatcher.getInstance().notifyEvent(new GetPlayerCombatState(this, attacker), this, CombatStateReturn.class);
		if (term != null) {
			switch (term.getValue()) {
				case ENEMY:
					return true;
				case FRIEND:
					return false;
			}
		}

		// Friendly mobs doesn't attack players
		if (attacker instanceof FriendlyMobInstance) {
			return false;
		}

		// Check if the attacker is a MonsterInstance
		if (attacker.isMonster()) {
			return true;
		}

		// is AutoAttackable if both players are in the same duel and the duel is still going on
		if (attacker.isPlayable() && (getDuelState() == Duel.DUELSTATE_DUELLING) && (getDuelId() == attacker.getActingPlayer().getDuelId())) {
			return true;
		}

		// Check if the attacker is not in the same party. NOTE: Party checks goes before oly checks in order to prevent patry member autoattack at oly.
		if (isInParty() && getParty().getMembers().contains(attacker)) {
			return false;
		}

		// Check if the attacker is in olympia and olympia start
		if (attacker.isPlayer() && attacker.getActingPlayer().isInOlympiadMode()) {
			if (isInOlympiadMode() && isOlympiadStart() && (((Player) attacker).getOlympiadGameId() == getOlympiadGameId())) {
				return true;
			}
			return false;
		}

		// Check if the attacker is in TvT and TvT is started
		if (isOnEvent()) {
			return true;
		}

		// Check if the attacker is a L2Playable
		if (attacker.isPlayable()) {
			if (isInsideZone(ZoneId.PEACE)) {
				return false;
			}

			// Get L2PcInstance
			final Player attackerPlayer = attacker.getActingPlayer();
			final Clan clan = getClan();
			final Clan attackerClan = attackerPlayer.getClan();
			if (clan != null) {
				Siege siege = SiegeManager.getInstance().getSiege(getX(), getY(), getZ());
				if (siege != null) {
					// Check if a siege is in progress and if attacker and the L2PcInstance aren't in the Defender clan
					if (siege.checkIsDefender(attackerClan) && siege.checkIsDefender(clan)) {
						return false;
					}

					// Check if a siege is in progress and if attacker and the L2PcInstance aren't in the Attacker clan
					if (siege.checkIsAttacker(attackerClan) && siege.checkIsAttacker(clan)) {
						return false;
					}
				}

				// Check if clan is at war
				if ((attackerClan != null) && (getWantsPeace() == 0) && (attackerPlayer.getWantsPeace() == 0) && !isAcademyMember()) {
					final ClanWar war = attackerClan.getWarWith(getClanId());
					if ((war != null) && (war.getState() == ClanWarState.MUTUAL)) {
						return true;
					}
				}
			}

			// Check if the L2PcInstance is in an arena, but NOT siege zone. NOTE: This check comes before clan/ally checks, but after party checks.
			// This is done because in arenas, clan/ally members can autoattack if they arent in party.
			if ((isInsideZone(ZoneId.PVP) && attackerPlayer.isInsideZone(ZoneId.PVP)) && !(isInsideZone(ZoneId.SIEGE) && attackerPlayer.isInsideZone(ZoneId.SIEGE))) {
				return true;
			}

			// Check if the attacker is not in the same clan
			if ((clan != null) && clan.isMember(attacker.getObjectId())) {
				return false;
			}

			// Check if the attacker is not in the same ally
			if (attacker.isPlayer() && (getAllyId() != 0) && (getAllyId() == attackerPlayer.getAllyId())) {
				return false;
			}

			// Now check again if the L2PcInstance is in pvp zone, but this time at siege PvP zone, applying clan/ally checks
			if ((isInsideZone(ZoneId.PVP) && attackerPlayer.isInsideZone(ZoneId.PVP)) && (isInsideZone(ZoneId.SIEGE) && attackerPlayer.isInsideZone(ZoneId.SIEGE))) {
				return true;
			}
		} else if (attacker instanceof DefenderInstance) {
			if (getClan() != null) {
				Siege siege = SiegeManager.getInstance().getSiege(this);
				return ((siege != null) && siege.checkIsAttacker(getClan()));
			}
		} else if (attacker instanceof GuardInstance) {
			return (getReputation() < 0); // Guards attack only PK players.
		}

		// Check if the L2PcInstance has Karma
		if ((getReputation() < 0) || (getPvpFlag() > 0)) {
			return true;
		}

		return false;
	}

	/**
	 * Check if the active L2Skill can be casted.<br>
	 * <B><U>Actions</U>:</B>
	 * <ul>
	 * <li>Check if the skill isn't toggle and is offensive</li>
	 * <li>Check if the target is in the skill cast range</li>
	 * <li>Check if the skill is Spoil type and if the target isn't already spoiled</li>
	 * <li>Check if the caster owns enought consummed Item, enough HP and MP to cast the skill</li>
	 * <li>Check if the caster isn't sitting</li>
	 * <li>Check if all skills are enabled and this skill is enabled</li>
	 * <li>Check if the caster own the weapon needed</li>
	 * <li>Check if the skill is active</li>
	 * <li>Check if all casting conditions are completed</li>
	 * <li>Notify the AI with AI_INTENTION_CAST and target</li>
	 * </ul>
	 *
	 * @param skill    The L2Skill to use
	 * @param forceUse used to force ATTACK on players
	 * @param dontMove used to prevent movement, if not in range
	 */
	@Override
	public boolean useMagic(Skill skill, ILocational target, ItemInstance item, boolean forceUse, boolean dontMove) {
		// Passive skills cannot be used.
		if (skill.isPassive()) {
			sendPacket(ActionFailed.STATIC_PACKET);
			return false;
		}

		if (isTransformed() && !hasTransformSkill(skill)) {
			sendPacket(ActionFailed.STATIC_PACKET);
			return false;
		}

		// If Alternate rule Karma punishment is set to true, forbid skill Return to player with Karma
		if (!PlayerConfig.ALT_GAME_KARMA_PLAYER_CAN_TELEPORT && (getReputation() < 0) && skill.hasEffectType(L2EffectType.TELEPORT)) {
			sendPacket(ActionFailed.STATIC_PACKET);
			return false;
		}

		// players mounted on pets cannot use any toggle skills
		if (skill.isToggle() && isMounted()) {
			sendPacket(ActionFailed.STATIC_PACKET);
			return false;
		}

		// Support for wizard skills with stances (Fire, Water, Wind, Earth)
		final Skill attachedSkill = skill.getAttachedSkill(this);
		if (attachedSkill != null) {
			skill = attachedSkill;
		}

		// ************************************* Check Player State *******************************************

		// Abnormal effects(ex : Stun, Sleep...) are checked in L2Character useMagic()
		if (isControlBlocked() || (getStat().has(BooleanStat.BLOCK_ACTIONS) && !getStat().isBlockedActionsAllowedSkill(skill)) || hasBlockActions()) {
			sendPacket(ActionFailed.STATIC_PACKET);
			return false;
		}

		// Check if the player is dead
		if (isDead()) {
			sendPacket(ActionFailed.STATIC_PACKET);
			return false;
		}

		// Check if fishing and trying to use non-fishing skills.
		if (isFishing()) {
			sendPacket(SystemMessageId.YOU_CANNOT_DO_THAT_WHILE_FISHING3);
			return false;
		}

		if (inObserverMode()) {
			sendPacket(SystemMessageId.OBSERVERS_CANNOT_PARTICIPATE);
			sendPacket(ActionFailed.STATIC_PACKET);
			return false;
		}

		if (isSkillDisabled(skill)) {
			final SystemMessage sm;
			if (hasSkillReuse(skill.getReuseHashCode())) {
				int remainingTime = (int) (getSkillRemainingReuseTime(skill.getReuseHashCode()) / 1000);
				int hours = remainingTime / 3600;
				int minutes = (remainingTime % 3600) / 60;
				int seconds = (remainingTime % 60);
				if (hours > 0) {
					sm = SystemMessage.getSystemMessage(SystemMessageId.THERE_ARE_S2_HOUR_S_S3_MINUTE_S_AND_S4_SECOND_S_REMAINING_IN_S1_S_RE_USE_TIME);
					sm.addSkillName(skill);
					sm.addInt(hours);
					sm.addInt(minutes);
				} else if (minutes > 0) {
					sm = SystemMessage.getSystemMessage(SystemMessageId.THERE_ARE_S2_MINUTE_S_S3_SECOND_S_REMAINING_IN_S1_S_RE_USE_TIME);
					sm.addSkillName(skill);
					sm.addInt(minutes);
				} else {
					sm = SystemMessage.getSystemMessage(SystemMessageId.THERE_ARE_S2_SECOND_S_REMAINING_IN_S1_S_RE_USE_TIME);
					sm.addSkillName(skill);
				}

				sm.addInt(seconds);
			} else {
				sm = SystemMessage.getSystemMessage(SystemMessageId.S1_IS_NOT_AVAILABLE_AT_THIS_TIME_BEING_PREPARED_FOR_REUSE);
				sm.addSkillName(skill);
			}

			sendPacket(sm);
			return false;
		}

		// Check if the caster is sitting
		if (isSitting()) {
			sendPacket(SystemMessageId.YOU_CANNOT_MOVE_WHILE_SITTING);
			sendPacket(ActionFailed.STATIC_PACKET);
			return false;
		}

		// Check if the skill type is toggle and disable it, unless the toggle is necessary to be on.
		if (skill.isToggle()) {
			if (isAffectedBySkill(skill.getId())) {
				if (!skill.isNecessaryToggle()) {
					stopSkillEffects(true, skill.getId());
				}
				sendPacket(ActionFailed.STATIC_PACKET);
				return false;
			} else if (skill.getToggleGroupId() > 0) {
				getEffectList().stopAllTogglesOfGroup(skill.getToggleGroupId());
			}
		}

		// Check if the player uses "Fake Death" skill
		// Note: do not check this before TOGGLE reset
		if (isFakeDeath()) {
			sendPacket(ActionFailed.STATIC_PACKET);
			return false;
		}

		// ************************************* Check Target *******************************************
		// Create and set a L2Object containing the target of the skill
		final WorldObject targetObject;
		if (target instanceof WorldObject) {
			setCurrentSkillWorldPosition(null);
			targetObject = skill.getTarget(this, (WorldObject) target, forceUse, dontMove, true);
		} else {
			setCurrentSkillWorldPosition(target);
			targetObject = skill.getTarget(this, this, forceUse, dontMove, true);
		}

		// Check the validity of the target
		if (targetObject == null) {
			sendPacket(ActionFailed.STATIC_PACKET);
			return false;
		}

		// Check if all casting conditions are completed
		if (!skill.checkCondition(this, targetObject)) {
			sendPacket(ActionFailed.STATIC_PACKET);

			// Upon failed conditions, next action is called.
			if ((skill.getNextAction() != NextActionType.NONE) && (targetObject != this) && targetObject.isAutoAttackable(this)) {
				if ((getAI().getNextIntention() == null) || (getAI().getNextIntention().getCtrlIntention() != CtrlIntention.AI_INTENTION_MOVE_TO)) {
					if (skill.getNextAction() == NextActionType.ATTACK) {
						getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, targetObject);
					} else if (skill.getNextAction() == NextActionType.CAST) {
						getAI().setIntention(CtrlIntention.AI_INTENTION_CAST, skill, targetObject, item, false, false);
					}
				}
			}

			return false;
		}

		boolean doubleCast = getStat().has(BooleanStat.DOUBLE_CAST) && skill.canDoubleCast();

		// If a skill is currently being used, queue this one if this is not the same
		// In case of double casting, check if both slots are occupied, then queue skill.
		if ((!doubleCast && isCastingNow(SkillCaster::isAnyNormalType)) || (isCastingNow(s -> s.getCastingType() == SkillCastingType.NORMAL) && isCastingNow(s -> s.getCastingType() == SkillCastingType.NORMAL_SECOND))) {
			// Create a new SkillDat object and queue it in the player _queuedSkill
			setQueuedSkill(skill, item, forceUse, dontMove);
			sendPacket(ActionFailed.STATIC_PACKET);
			return false;
		}

		if (getQueuedSkill() != null) {
			setQueuedSkill(null, null, false, false);
		}

		// Notify the AI with AI_INTENTION_CAST and target
		getAI().setIntention(CtrlIntention.AI_INTENTION_CAST, skill, targetObject, item, forceUse, dontMove);
		return true;
	}

	public boolean isInLooterParty(int LooterId) {
		Player looter = getWorld().getPlayer(LooterId);

		// if L2PcInstance is in a CommandChannel
		if (isInParty() && getParty().isInCommandChannel() && (looter != null)) {
			return getParty().getCommandChannel().getMembers().contains(looter);
		}

		if (isInParty() && (looter != null)) {
			return getParty().getMembers().contains(looter);
		}

		return false;
	}

	public boolean isMounted() {
		return _mountType != MountType.NONE;
	}

	public boolean checkLandingState() {
		// Check if char is in a no landing zone
		if (isInsideZone(ZoneId.NO_LANDING)) {
			return true;
		} else
			// if this is a castle that is currently being sieged, and the rider is NOT a castle owner
			// he cannot land.
			// castle owner is the leader of the clan that owns the castle where the pc is
			if (isInsideZone(ZoneId.SIEGE) && !((getClan() != null) && (CastleManager.getInstance().getCastle(this) == CastleManager.getInstance().getCastleByOwner(getClan())) && (this == getClan().getLeader().getPlayerInstance()))) {
				return true;
			}

		return false;
	}

	// returns false if the change of mount type fails.
	public void setMount(int npcId, int npcLevel) {
		final MountType type = MountType.findByNpcId(npcId);
		switch (type) {
			case NONE: // None
			{
				setEnvironment(EEnvType.ET_GROUND);
				setIsFlying(false);
				break;
			}
			case STRIDER: // Strider
			{
				if (npcId >= 12526 && npcId <= 12528)
					setEnvironment(EEnvType.ET_HOVER);
				if (isNoble()) {
					addSkill(CommonSkill.STRIDER_SIEGE_ASSAULT.getSkill(), false);
				}
				break;
			}
			case WYVERN: // Wyvern
			{
				setEnvironment(EEnvType.ET_AIR);
				setIsFlying(true);
				break;
			}
		}

		_mountType = type;
		_mountNpcId = npcId;
		_mountLevel = npcLevel;
	}

	/**
	 * @return the type of Pet mounted (0 : none, 1 : Strider, 2 : Wyvern, 3: Wolf).
	 */
	public MountType getMountType() {
		return _mountType;
	}

	@Override
	public final void stopAllEffects() {
		super.stopAllEffects();
		updateAndBroadcastStatus(2);
	}

	@Override
	public final void stopAllEffectsExceptThoseThatLastThroughDeath() {
		super.stopAllEffectsExceptThoseThatLastThroughDeath();
		updateAndBroadcastStatus(2);
	}

	public final void stopCubics() {
		if (!_cubics.isEmpty()) {
			_cubics.values().forEach(CubicInstance::deactivate);
			_cubics.clear();
		}
	}

	public final void stopCubicsByOthers() {
		if (!_cubics.isEmpty()) {
			boolean broadcast = false;
			for (CubicInstance cubic : _cubics.values()) {
				if (cubic.isGivenByOther()) {
					cubic.deactivate();
					_cubics.remove(cubic.getTemplate().getId());
					broadcast = true;
				}
			}
			if (broadcast) {
				sendPacket(new ExUserInfoCubic(this));
				broadcastUserInfo();
			}
		}
	}

	/**
	 * Send a Server->Client packet UserInfo to this L2PcInstance and CharInfo to all L2PcInstance in its _KnownPlayers.<br>
	 * <B><U>Concept</U>:</B><br>
	 * Others L2PcInstance in the detection area of the L2PcInstance are identified in <B>_knownPlayers</B>.<br>
	 * In order to inform other players of this L2PcInstance state modifications, server just need to go through _knownPlayers to send Server->Client Packet<br>
	 * <B><U>Actions</U>:</B>
	 * <ul>
	 * <li>Send a Server->Client packet UserInfo to this L2PcInstance (Public and Private Data)</li>
	 * <li>Send a Server->Client packet CharInfo to all L2PcInstance in _KnownPlayers of the L2PcInstance (Public data only)</li>
	 * </ul>
	 * <FONT COLOR=#FF0000><B> <U>Caution</U> : DON'T SEND UserInfo packet to other players instead of CharInfo packet. Indeed, UserInfo packet contains PRIVATE DATA as MaxHP, STR, DEX...</B></FONT>
	 */
	@Override
	public void updateAbnormalVisualEffects() {
		sendPacket(new ExUserInfoAbnormalVisualEffect(this));
		broadcastCharInfo();
	}

	/**
	 * Disable the Inventory and create a new task to enable it after 1.5s.
	 *
	 * @param val
	 */
	public void setInventoryBlockingStatus(boolean val) {
		_inventoryDisable = val;
		if (val) {
			ThreadPool.getInstance().scheduleGeneral(new InventoryEnableTask(this), 1500, TimeUnit.MILLISECONDS);
		}
	}

	/**
	 * @return True if the Inventory is disabled.
	 */
	public boolean isInventoryDisabled() {
		return _inventoryDisable;
	}

	/**
	 * Add a cubic to this player.
	 *
	 * @param cubic
	 * @return the old cubic for this cubic ID if any, otherwise {@code null}
	 */
	public CubicInstance addCubic(CubicInstance cubic) {
		return _cubics.put(cubic.getTemplate().getId(), cubic);
	}

	/**
	 * Get the player's cubics.
	 *
	 * @return the cubics
	 */
	public Map<Integer, CubicInstance> getCubics() {
		return _cubics;
	}

	/**
	 * Get the player cubic by cubic ID, if any.
	 *
	 * @param cubicId the cubic ID
	 * @return the cubic with the given cubic ID, {@code null} otherwise
	 */
	public CubicInstance getCubicById(int cubicId) {
		return _cubics.get(cubicId);
	}

	/**
	 * @return the modifier corresponding to the Enchant Effect of the Active Weapon (Min : 127).
	 */
	public int getEnchantEffect() {
		ItemInstance wpn = getActiveWeaponInstance();

		if (wpn == null) {
			return 0;
		}

		return Math.min(127, wpn.getEnchantLevel());
	}

	/**
	 * Set the _lastFolkNpc of the L2PcInstance corresponding to the last Folk wich one the player talked.
	 *
	 * @param folkNpc
	 */
	public void setLastFolkNPC(Npc folkNpc) {
		_lastFolkNpc = folkNpc;
	}

	/**
	 * @return the _lastFolkNpc of the L2PcInstance corresponding to the last Folk wich one the player talked.
	 */
	public Npc getLastFolkNPC() {
		return _lastFolkNpc;
	}

	public void addAutoSoulShot(int itemId) {
		_activeSoulShots.add(itemId);
	}

	public boolean removeAutoSoulShot(int itemId) {
		return _activeSoulShots.remove(itemId);
	}

	public Set<Integer> getAutoSoulShot() {
		return _activeSoulShots;
	}

	@Override
	public void rechargeShots(boolean physical, boolean magic, boolean fish) {
		for (int itemId : _activeSoulShots) {
			final ItemInstance item = getInventory().getItemByItemId(itemId);
			if (item == null) {
				removeAutoSoulShot(itemId);
				continue;
			}

			final IItemHandler handler = ItemHandler.getInstance().getHandler(item.getEtcItem());
			if (handler == null) {
				continue;
			}

			final ActionType defaultAction = item.getItem().getDefaultAction();
			if ((magic && (defaultAction == ActionType.SPIRITSHOT)) || (physical && (defaultAction == ActionType.SOULSHOT)) || (fish && (defaultAction == ActionType.FISHINGSHOT))) {
				handler.useItem(this, item, false);
			}
		}
	}

	/**
	 * Cancel autoshot for all shots matching crystaltype {@link ItemTemplate#getCrystalType()}.
	 *
	 * @param crystalType int type to disable
	 */
	public void disableAutoShotByCrystalType(int crystalType) {
		for (int itemId : _activeSoulShots) {
			if (ItemTable.getInstance().getTemplate(itemId).getCrystalType().getLevel() == crystalType) {
				disableAutoShot(itemId);
			}
		}
	}

	/**
	 * Cancel autoshot use for shot itemId
	 *
	 * @param itemId int id to disable
	 * @return true if canceled.
	 */
	public boolean disableAutoShot(int itemId) {
		if (_activeSoulShots.contains(itemId)) {
			removeAutoSoulShot(itemId);
			sendPacket(new ExAutoSoulShot(itemId, false, 0));

			SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.THE_AUTOMATIC_USE_OF_S1_HAS_BEEN_DEACTIVATED);
			sm.addItemName(itemId);
			sendPacket(sm);
			return true;
		}
		return false;
	}

	/**
	 * Cancel all autoshots for player
	 */
	public void disableAutoShotsAll() {
		for (int itemId : _activeSoulShots) {
			sendPacket(new ExAutoSoulShot(itemId, false, 0));
			SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.THE_AUTOMATIC_USE_OF_S1_HAS_BEEN_DEACTIVATED);
			sm.addItemName(itemId);
			sendPacket(sm);
		}
		_activeSoulShots.clear();
	}

	private ScheduledFuture<?> _taskWarnUserTakeBreak;

	public EnumIntBitmask<ClanPrivilege> getClanPrivileges() {
		return _clanPrivileges;
	}

	public void setClanPrivileges(EnumIntBitmask<ClanPrivilege> clanPrivileges) {
		_clanPrivileges = clanPrivileges.clone();
	}

	public boolean hasClanPrivilege(ClanPrivilege privilege) {
		return _clanPrivileges.has(privilege);
	}

	// baron etc
	public void setPledgeClass(int classId) {
		_pledgeClass = classId;
		checkItemRestriction();
	}

	public int getPledgeClass() {
		return _pledgeClass;
	}

	public void setPledgeType(int typeId) {
		_pledgeType = typeId;
	}

	@Override
	public int getPledgeType() {
		return _pledgeType;
	}

	public int getApprentice() {
		return _apprentice;
	}

	public void setApprentice(int apprentice_id) {
		_apprentice = apprentice_id;
	}

	public int getSponsor() {
		return _sponsor;
	}

	public void setSponsor(int sponsor_id) {
		_sponsor = sponsor_id;
	}

	public int getBookMarkSlot() {
		return _bookmarkslot;
	}

	public void setBookMarkSlot(int slot) {
		_bookmarkslot = slot;
		sendPacket(new ExGetBookMarkInfoPacket(this));
	}

	@Deprecated
	@Override
	public void sendMessage(String message) {
		sendPacket(SystemMessage.sendString(message));
	}

	@Override
	public void sendMessage(CustomMessage msg, String... args) {
		msg.send(this, args);
	}

	@Override
	public void sendMessage(CustomMessage msg, Object... args) {
		msg.send(this, args);
	}

	public void setObserving(boolean state) {
		_observerMode = state;
		setTarget(null);
		setBlockActions(state);
		setIsInvul(state);
		setInvisible(state);
		if (hasAI() && !state) {
			getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
		}
	}

	public void enterObserverMode(Location loc) {
		setLastLocation();

		// Remove Hide.
		getEffectList().stopEffects(AbnormalType.HIDE);

		setObserving(true);
		sendPacket(new ObservationMode(loc));

		teleToLocation(loc, false);

		broadcastUserInfo();
	}

	public void setLastLocation() {
		_lastLoc = new Location(getX(), getY(), getZ());
	}

	public void unsetLastLocation() {
		_lastLoc = null;
	}

	public void enterOlympiadObserverMode(Location loc, int id) {
		final Summon pet = getPet();
		if (pet != null) {
			pet.unSummon(this);
		}

		if (hasServitors()) {
			getServitors().values().forEach(s -> s.unSummon(this));
		}

		// Remove Hide.
		getEffectList().stopEffects(AbnormalType.HIDE);

		if (!_cubics.isEmpty()) {
			_cubics.values().forEach(CubicInstance::deactivate);
			_cubics.clear();
			sendPacket(new ExUserInfoCubic(this));
		}

		if (getParty() != null) {
			getParty().removePartyMember(this, MessageType.EXPELLED);
		}

		_olympiadGameId = id;
		if (isSitting()) {
			standUp();
		}
		if (!_observerMode) {
			setLastLocation();
		}

		_observerMode = true;
		setTarget(null);
		setIsInvul(true);
		setInvisible(true);
		setInstance(OlympiadGameManager.getInstance().getOlympiadTask(id).getStadium().getInstance());
		teleToLocation(loc, false);
		sendPacket(new ExOlympiadMode(3));

		broadcastUserInfo();
	}

	public void leaveObserverMode() {
		setTarget(null);
		setInstance(null);
		teleToLocation(_lastLoc, false);
		unsetLastLocation();
		sendPacket(new ObservationReturn(getLocation()));

		setBlockActions(false);
		if (!isGM()) {
			setInvisible(false);
			setIsInvul(false);
		}
		if (hasAI()) {
			getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
		}

		setFalling(); // prevent receive falling damage
		_observerMode = false;

		broadcastUserInfo();
	}

	public void leaveOlympiadObserverMode() {
		if (_olympiadGameId == -1) {
			return;
		}
		_olympiadGameId = -1;
		_observerMode = false;
		setTarget(null);
		sendPacket(new ExOlympiadMode(0));
		setInstance(null);
		teleToLocation(_lastLoc, true);
		if (!isGM()) {
			setInvisible(false);
			setIsInvul(false);
		}
		if (hasAI()) {
			getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
		}
		unsetLastLocation();
		broadcastUserInfo();
	}

	public void setOlympiadSide(int i) {
		_olympiadSide = i;
	}

	public int getOlympiadSide() {
		return _olympiadSide;
	}

	public void setOlympiadGameId(int id) {
		_olympiadGameId = id;
	}

	public int getOlympiadGameId() {
		return _olympiadGameId;
	}

	/**
	 * Gets the player's olympiad buff count.
	 *
	 * @return the olympiad's buff count
	 */
	public int getOlympiadBuffCount() {
		return _olyBuffsCount;
	}

	/**
	 * Sets the player's olympiad buff count.
	 *
	 * @param buffs the olympiad's buff count
	 */
	public void setOlympiadBuffCount(int buffs) {
		_olyBuffsCount = buffs;
	}

	public Location getLastLocation() {
		return _lastLoc;
	}

	public boolean inObserverMode() {
		return _observerMode;
	}

	public AdminTeleportType getTeleMode() {
		return _teleportType;
	}

	public void setTeleMode(AdminTeleportType type) {
		_teleportType = type;
	}

	public void setLoto(int i, int val) {
		_loto[i] = val;
	}

	public int getLoto(int i) {
		return _loto[i];
	}

	public void setRace(int i, int val) {
		_race[i] = val;
	}

	public int getRace(int i) {
		return _race[i];
	}

	public boolean getMessageRefusal() {
		return _messageRefusal;
	}

	public void setMessageRefusal(boolean mode) {
		_messageRefusal = mode;
		sendPacket(new EtcStatusUpdate(this));
	}

	public void setDietMode(boolean mode) {
		_dietMode = mode;
	}

	public boolean getDietMode() {
		return _dietMode;
	}

	public void setTradeRefusal(boolean mode) {
		_tradeRefusal = mode;
	}

	public boolean getTradeRefusal() {
		return _tradeRefusal;
	}

	public void setExchangeRefusal(boolean mode) {
		_exchangeRefusal = mode;
	}

	public boolean getExchangeRefusal() {
		return _exchangeRefusal;
	}

	public BlockList getBlockList() {
		return _blockList;
	}

	/**
	 * @param player
	 * @return returns {@code true} if player is current player cannot accepting messages from the target player, {@code false} otherwise
	 */
	public boolean isBlocking(Player player) {
		return _blockList.isBlockAll() || _blockList.isInBlockList(player);
	}

	/**
	 * @param player
	 * @return returns {@code true} if player is current player can accepting messages from the target player, {@code false} otherwise
	 */
	public boolean isNotBlocking(Player player) {
		return !_blockList.isBlockAll() && !_blockList.isInBlockList(player);
	}

	/**
	 * @param player
	 * @return returns {@code true} if player is target player cannot accepting messages from the current player, {@code false} otherwise
	 */
	public boolean isBlocked(Player player) {
		return player.getBlockList().isBlockAll() || player.getBlockList().isInBlockList(this);
	}

	/**
	 * @param player
	 * @return returns {@code true} if player is target player can accepting messages from the current player, {@code false} otherwise
	 */
	public boolean isNotBlocked(Player player) {
		return !player.getBlockList().isBlockAll() && !player.getBlockList().isInBlockList(this);
	}

	public void setHero(boolean hero) {
		if (hero && (_baseClass == _activeClass)) {
			for (Skill skill : SkillTreesData.getInstance().getHeroSkillTree()) {
				addSkill(skill, false); // Don't persist hero skills into database
			}
		} else {
			for (Skill skill : SkillTreesData.getInstance().getHeroSkillTree()) {
				removeSkill(skill, false, true); // Just remove skills from non-hero players
			}
		}
		_hero = hero;

		sendSkillList();
	}

	public void setIsInOlympiadMode(boolean b) {
		_inOlympiadMode = b;
	}

	public void setIsOlympiadStart(boolean b) {
		_OlympiadStart = b;
	}

	public boolean isOlympiadStart() {
		return _OlympiadStart;
	}

	public boolean isHero() {
		return _hero;
	}

	public boolean isInOlympiadMode() {
		return _inOlympiadMode;
	}

	public boolean isInDuel() {
		return _isInDuel;
	}

	public int getDuelId() {
		return _duelId;
	}

	public void setDuelState(int mode) {
		_duelState = mode;
	}

	public int getDuelState() {
		return _duelState;
	}

	/**
	 * Sets up the duel state using a non 0 duelId.
	 *
	 * @param duelId 0=not in a duel
	 */
	public void setIsInDuel(int duelId) {
		if (duelId > 0) {
			_isInDuel = true;
			_duelState = Duel.DUELSTATE_DUELLING;
			_duelId = duelId;
		} else {
			if (_duelState == Duel.DUELSTATE_DEAD) {
				enableAllSkills();
				getStatus().startHpMpRegeneration();
			}
			_isInDuel = false;
			_duelState = Duel.DUELSTATE_NODUEL;
			_duelId = 0;
		}
	}

	/**
	 * This returns a SystemMessage stating why the player is not available for duelling.
	 *
	 * @return S1_CANNOT_DUEL... message
	 */
	public SystemMessage getNoDuelReason() {
		SystemMessage sm = SystemMessage.getSystemMessage(_noDuelReason);
		sm.addPcName(this);
		_noDuelReason = SystemMessageId.THERE_IS_NO_OPPONENT_TO_RECEIVE_YOUR_CHALLENGE_FOR_A_DUEL;
		return sm;
	}

	/**
	 * Checks if this player might join / start a duel.<br>
	 * To get the reason use getNoDuelReason() after calling this function.
	 *
	 * @return true if the player might join/start a duel.
	 */
	public boolean canDuel() {
		if (isInCombat() || isJailed()) {
			_noDuelReason = SystemMessageId.C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_ENGAGED_IN_BATTLE;
			return false;
		}
		if (isDead() || isAlikeDead() || ((getCurrentHp() < (getMaxHp() / 2)) || (getCurrentMp() < (getMaxMp() / 2)))) {
			_noDuelReason = SystemMessageId.C1_CANNOT_DUEL_BECAUSE_C1_S_HP_OR_MP_IS_BELOW_50;
			return false;
		}
		if (isInDuel()) {
			_noDuelReason = SystemMessageId.C1_CANNOT_DUEL_BECAUSE_C1_IS_ALREADY_ENGAGED_IN_A_DUEL;
			return false;
		}
		if (isInOlympiadMode() || isOnEvent(CeremonyOfChaosEvent.class)) {
			_noDuelReason = SystemMessageId.C1_CANNOT_DUEL_BECAUSE_C1_IS_PARTICIPATING_IN_THE_OLYMPIAD_OR_THE_CEREMONY_OF_CHAOS;
			return false;
		}
		if (isCursedWeaponEquipped()) {
			_noDuelReason = SystemMessageId.C1_CANNOT_DUEL_BECAUSE_C1_IS_IN_A_CHAOTIC_OR_PURPLE_STATE;
			return false;
		}
		if (getPrivateStoreType() != PrivateStoreType.NONE) {
			_noDuelReason = SystemMessageId.C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_ENGAGED_IN_A_PRIVATE_STORE_OR_MANUFACTURE;
			return false;
		}
		if (isMounted() || isInBoat()) {
			_noDuelReason = SystemMessageId.C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_RIDING_A_BOAT_FENRIR_OR_STRIDER;
			return false;
		}
		if (isFishing()) {
			_noDuelReason = SystemMessageId.C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_FISHING;
			return false;
		}
		if (isInsideZone(ZoneId.PVP) || isInsideZone(ZoneId.PEACE) || isInsideZone(ZoneId.SIEGE)) {
			_noDuelReason = SystemMessageId.C1_CANNOT_MAKE_A_CHALLENGE_TO_A_DUEL_BECAUSE_C1_IS_CURRENTLY_IN_A_DUEL_PROHIBITED_AREA_PEACEFUL_ZONE_BATTLE_ZONE_NEAR_WATER_RESTART_PROHIBITED_AREA;
			return false;
		}
		return true;
	}

	public boolean isNoble() {
		return _noble != NobleStatus.NONE;
	}

	public boolean isExalted() {
		return _noble.ordinal() >= NobleStatus.EXALTED_1.ordinal();
	}

	public NobleStatus getNobleStatus() {
		return _noble;
	}

	public void setNoble(NobleStatus status) {
		if (status != NobleStatus.NONE) {
			SkillTreesData.getInstance().getNobleSkillAutoGetTree().forEach(skill -> addSkill(skill, false));
		} else {
			SkillTreesData.getInstance().getNobleSkillTree().forEach(skill -> removeSkill(skill, false, true));
		}

		_noble = status;

		sendSkillList();
		if ((status != NobleStatus.NONE) && (getLevel() >= 99)) {
			sendPacket(new ExAcquireAPSkillList(this));
		}
	}

	public void setLvlJoinedAcademy(int lvl) {
		_lvlJoinedAcademy = lvl;
	}

	public int getLvlJoinedAcademy() {
		return _lvlJoinedAcademy;
	}

	@Override
	public boolean isAcademyMember() {
		return getPledgeType() == Clan.SUBUNIT_ACADEMY;
	}

	@Override
	public void setTeam(Team team) {
		super.setTeam(team);
		broadcastUserInfo();
		final Summon pet = getPet();
		if (pet != null) {
			pet.broadcastStatusUpdate();
		}
		if (hasServitors()) {
			getServitors().values().forEach(Summon::broadcastStatusUpdate);
		}
	}

	public void setWantsPeace(int wantsPeace) {
		_wantsPeace = wantsPeace;
	}

	public int getWantsPeace() {
		return _wantsPeace;
	}

	public void sendSkillList() {
		sendSkillList(0);
	}

	public void sendSkillList(int lastLearnedSkillId) {
		boolean isDisabled = false;
		SkillList sl = new SkillList();

		for (Skill s : getSkillList()) {
			if (getClan() != null) {
				isDisabled = s.isClanSkill() && (getClan().getReputationScore() < 0);
			}

			sl.addSkill(s.getDisplayId(), s.getReuseDelayGroup(), s.getDisplayLevel(), s.getSubLevel(), s.isPassive(), isDisabled, s.isEnchantable());
		}
		if (lastLearnedSkillId > 0) {
			sl.setLastLearnedSkillId(lastLearnedSkillId);
		}
		sendPacket(sl);

		sendPacket(new AcquireSkillList(this));
	}

	/**
	 * 1. Add the specified class ID as a subclass (up to the maximum number of <b>three</b>) for this character.<BR>
	 * 2. This method no longer changes the active _classIndex of the player. This is only done by the calling of setActiveClass() method as that should be the only way to do so.
	 *
	 * @param classId
	 * @param classIndex
	 * @param isDualClass
	 * @return boolean subclassAdded
	 */
	public boolean addSubClass(int classId, int classIndex, boolean isDualClass) {
		if (!_subclassLock.tryLock()) {
			return false;
		}

		try {
			if ((getTotalSubClasses() == PlayerConfig.MAX_SUBCLASS) || (classIndex == 0)) {
				return false;
			}

			if (getSubClasses().containsKey(classIndex)) {
				return false;
			}

			// Note: Never change _classIndex in any method other than setActiveClass().

			final SubClass newClass = new SubClass();
			newClass.setClassId(classId);
			newClass.setClassIndex(classIndex);
			newClass.setVitalityPoints(PcStat.MAX_VITALITY_POINTS);
			if (isDualClass) {
				newClass.setIsDualClass(true);
				newClass.setExp(ExperienceData.getInstance().getExpForLevel(PlayerConfig.BASE_DUALCLASS_LEVEL));
				newClass.setLevel(PlayerConfig.BASE_DUALCLASS_LEVEL);
			}

			try (Connection con = DatabaseFactory.getInstance().getConnection();
				 PreparedStatement statement = con.prepareStatement(ADD_CHAR_SUBCLASS)) {
				// Store the basic info about this new sub-class.
				statement.setInt(1, getObjectId());
				statement.setInt(2, newClass.getClassId());
				statement.setLong(3, newClass.getExp());
				statement.setLong(4, newClass.getSp());
				statement.setInt(5, newClass.getLevel());
				statement.setInt(6, newClass.getVitalityPoints());
				statement.setInt(7, newClass.getClassIndex());
				statement.setBoolean(8, newClass.isDualClass());
				statement.execute();
			} catch (Exception e) {
				LOGGER.warn("WARNING: Could not add character sub class for " + getName() + ": " + e.getMessage(), e);
				return false;
			}

			// Commit after database INSERT incase exception is thrown.
			getSubClasses().put(newClass.getClassIndex(), newClass);

			final ClassId subTemplate = ClassId.getClassId(classId);
			final Map<Long, SkillLearn> skillTree = SkillTreesData.getInstance().getCompleteClassSkillTree(subTemplate);
			final Map<Integer, Skill> prevSkillList = new HashMap<>();
			for (SkillLearn skillInfo : skillTree.values()) {
				if (skillInfo.getGetLevel() <= newClass.getLevel()) {
					final Skill prevSkill = prevSkillList.get(skillInfo.getSkillId());
					final Skill newSkill = SkillData.getInstance().getSkill(skillInfo.getSkillId(), skillInfo.getSkillLevel());

					if (((prevSkill != null) && (prevSkill.getLevel() > newSkill.getLevel())) || SkillTreesData.getInstance().isRemoveSkill(subTemplate, skillInfo.getSkillId())) {
						continue;
					}

					prevSkillList.put(newSkill.getId(), newSkill);
					storeSkill(newSkill, prevSkill, classIndex);
				}
			}
			return true;
		} finally {
			_subclassLock.unlock();
		}
	}

	/**
	 * 1. Completely erase all existance of the subClass linked to the classIndex.<br>
	 * 2. Send over the newClassId to addSubClass() to create a new instance on this classIndex.<br>
	 * 3. Upon Exception, revert the player to their BaseClass to avoid further problems.
	 *
	 * @param classIndex  the class index to delete
	 * @param newClassId  the new class Id
	 * @param isDualClass is subclass dualclass
	 * @return {@code true} if the sub-class was modified, {@code false} otherwise
	 */
	public boolean modifySubClass(int classIndex, int newClassId, boolean isDualClass) {
		if (!_subclassLock.tryLock()) {
			return false;
		}

		try {
			final SubClass subClass = getSubClasses().remove(classIndex);
			if (subClass == null) {
				return false;
			}

			if (subClass.isDualClass()) {
				getVariables().remove(PlayerVariables.ABILITY_POINTS_DUAL_CLASS);
				getVariables().remove(PlayerVariables.ABILITY_POINTS_USED_DUAL_CLASS);
				int revelationSkill = getVariables().getInt(PlayerVariables.REVELATION_SKILL_1_DUAL_CLASS, 0);
				if (revelationSkill != 0) {
					removeSkill(revelationSkill);
				}
				revelationSkill = getVariables().getInt(PlayerVariables.REVELATION_SKILL_2_DUAL_CLASS, 0);
				if (revelationSkill != 0) {
					removeSkill(revelationSkill);
				}
			}

			final TrainingHolder holder = getAccountVariables().getObject(AccountVariables.TRAINING_CAMP, TrainingHolder.class);
			if ((holder != null) && (holder.getClassIndex() == classIndex)) {
				getAccountVariables().remove(AccountVariables.TRAINING_CAMP);
			}

			try (Connection con = DatabaseFactory.getInstance().getConnection();
				 PreparedStatement deleteHennas = con.prepareStatement(DELETE_CHAR_HENNAS);
				 PreparedStatement deleteShortcuts = con.prepareStatement(DELETE_CHAR_SHORTCUTS);
				 PreparedStatement deleteSkillReuse = con.prepareStatement(DELETE_SKILL_SAVE);
				 PreparedStatement deleteSkills = con.prepareStatement(DELETE_CHAR_SKILLS);
				 PreparedStatement deleteSubclass = con.prepareStatement(DELETE_CHAR_SUBCLASS)) {
				// Remove all henna info stored for this sub-class.
				deleteHennas.setInt(1, getObjectId());
				deleteHennas.setInt(2, classIndex);
				deleteHennas.execute();

				// Remove all shortcuts info stored for this sub-class.
				deleteShortcuts.setInt(1, getObjectId());
				deleteShortcuts.setInt(2, classIndex);
				deleteShortcuts.execute();

				// Remove all effects info stored for this sub-class.
				deleteSkillReuse.setInt(1, getObjectId());
				deleteSkillReuse.setInt(2, classIndex);
				deleteSkillReuse.execute();

				// Remove all skill info stored for this sub-class.
				deleteSkills.setInt(1, getObjectId());
				deleteSkills.setInt(2, classIndex);
				deleteSkills.execute();

				// Remove all basic info stored about this sub-class.
				deleteSubclass.setInt(1, getObjectId());
				deleteSubclass.setInt(2, classIndex);
				deleteSubclass.execute();
			} catch (Exception e) {
				LOGGER.warn("Could not modify sub class for " + getName() + " to class index " + classIndex + ": " + e.getMessage(), e);
				return false;
			}
		} finally {
			_subclassLock.unlock();
		}

		return addSubClass(newClassId, classIndex, isDualClass);
	}

	public boolean isSubClassActive() {
		return _classIndex > 0;
	}

	public void setDualClass(int classIndex) {
		if (isSubClassActive()) {
			getSubClasses().get(_classIndex).setIsDualClass(true);
		}
	}

	@Override
	public boolean isDualClassActive() {
		return isSubClassActive() && getSubClasses().get(_classIndex).isDualClass();
	}

	public boolean hasDualClass() {
		return getSubClasses().values().stream().anyMatch(SubClass::isDualClass);
	}

	public SubClass getDualClass() {
		return getSubClasses().values().stream().filter(SubClass::isDualClass).findFirst().orElse(null);
	}

	public Map<Integer, SubClass> getSubClasses() {
		if (_subClasses == null) {
			synchronized (this) {
				if (_subClasses == null) {
					_subClasses = new ConcurrentHashMap<>();
				}
			}
		}

		return _subClasses;
	}

	public int getTotalSubClasses() {
		return getSubClasses().size();
	}

	public int getBaseClass() {
		return _baseClass;
	}

	public int getActiveClass() {
		return _activeClass;
	}

	public int getClassIndex() {
		return _classIndex;
	}

	protected void setClassIndex(int classIndex) {
		_classIndex = classIndex;
	}

	private void setClassTemplate(int classId) {
		_activeClass = classId;

		final PcTemplate pcTemplate = PlayerTemplateData.getInstance().getTemplate(classId);
		if (pcTemplate == null) {
			LOGGER.error("Missing template for classId: " + classId);
			throw new Error();
		}
		// Set the template of the L2PcInstance
		setTemplate(pcTemplate);

		// Notify to scripts
		EventDispatcher.getInstance().notifyEventAsync(new OnPlayerProfessionChange(this, pcTemplate, isSubClassActive()), this);
	}

	/**
	 * Changes the character's class based on the given class index.<br>
	 * An index of zero specifies the character's original (base) class, while indexes 1-3 specifies the character's sub-classes respectively.<br>
	 * <font color="00FF00"/>WARNING: Use only on subclase change</font>
	 *
	 * @param classIndex
	 * @return
	 */
	public boolean setActiveClass(int classIndex) {
		if (!_subclassLock.tryLock()) {
			return false;
		}

		try {
			// Cannot switch or change subclasses while transformed
			if (isTransformed()) {
				return false;
			}

			// Remove active item skills before saving char to database
			// because next time when choosing this class, weared items can
			// be different
			for (ItemInstance item : getInventory().getPaperdollItems(ItemInstance::isAugmented)) {
				if ((item != null) && item.isEquipped()) {
					item.getAugmentation().removeBonus(this);
				}
			}

			// abort any kind of cast.
			abortCast();

			if (isChannelized()) {
				getSkillChannelized().abortChannelization();
			}

			// 1. Call store() before modifying _classIndex to avoid skill effects rollover.
			// 2. Register the correct _classId against applied 'classIndex'.
			store(PlayerConfig.SUBCLASS_STORE_SKILL_COOLTIME);

			if (_sellingBuffs != null) {
				_sellingBuffs.clear();
			}

			resetTimeStamps();

			// clear charges
			_charges.set(0);
			stopChargeTask();

			if (hasServitors()) {
				getServitors().values().forEach(s -> s.unSummon(this));
			}

			if (classIndex == 0) {
				setClassTemplate(getBaseClass());
			} else {
				try {
					setClassTemplate(getSubClasses().get(classIndex).getClassId());
				} catch (Exception e) {
					LOGGER.warn("Could not switch " + getName() + "'s sub class to class index " + classIndex + ": " + e.getMessage(), e);
					return false;
				}
			}
			_classIndex = classIndex;

			if (isInParty()) {
				getParty().recalculatePartyLevel();
			}

			// Update the character's change in class status.
			// 1. Remove any active cubics from the player.
			// 2. Renovate the characters table in the database with the new class info, storing also buff/effect data.
			// 3. Remove all existing skills.
			// 4. Restore all the learned skills for the current class from the database.
			// 5. Restore effect/buff data for the new class.
			// 6. Restore henna data for the class, applying the new stat modifiers while removing existing ones.
			// 7. Reset HP/MP/CP stats and send Server->Client character status packet to reflect changes.
			// 8. Restore shortcut data related to this class.
			// 9. Resend a class change animation effect to broadcast to all nearby players.
			for (Skill oldSkill : getAllSkills()) {
				removeSkill(oldSkill, false, true);
			}

			stopAllEffectsExceptThoseThatLastThroughDeath();
			stopAllEffects();
			stopCubics();

			restoreRecipeBook(false);

			restoreSkills();
			rewardSkills();
			regiveTemporarySkills();

			restoreEffects();

			sendPacket(new EtcStatusUpdate(this));

			for (int i = 0; i < 3; i++) {
				_henna[i] = null;
			}

			restoreHenna();
			sendPacket(new HennaInfo(this));

			if (getCurrentHp() > getMaxHp()) {
				setCurrentHp(getMaxHp());
			}
			if (getCurrentMp() > getMaxMp()) {
				setCurrentMp(getMaxMp());
			}
			if (getCurrentCp() > getMaxCp()) {
				setCurrentCp(getMaxCp());
			}

			refreshOverloaded(true);
			refreshExpertisePenalty();
			broadcastUserInfo();

			// Clear resurrect xp calculation
			setExpBeforeDeath(0);

			_shortCuts.restoreMe();
			sendPacket(new ShortCutInit(this));

			broadcastPacket(new SocialAction(getObjectId(), SocialAction.LEVEL_UP));
			sendPacket(new SkillCoolTime(this));
			sendPacket(new ExStorageMaxCount(this));

			EventDispatcher.getInstance().notifyEventAsync(new OnPlayerSubChange(this), this);
			return true;
		} finally {
			_subclassLock.unlock();
		}
	}

	public boolean isLocked() {
		return _subclassLock.isLocked();
	}

	public void stopWarnUserTakeBreak() {
		if (_taskWarnUserTakeBreak != null) {
			_taskWarnUserTakeBreak.cancel(true);
			_taskWarnUserTakeBreak = null;
		}
	}

	public void startWarnUserTakeBreak() {
		if (_taskWarnUserTakeBreak == null) {
			_taskWarnUserTakeBreak = ThreadPool.getInstance().scheduleGeneralAtFixedRate(new WarnUserTakeBreakTask(this), 3600000, 3600000, TimeUnit.MILLISECONDS);
		}
	}

	public void stopRentPet() {
		if (_taskRentPet != null) {
			// if the rent of a wyvern expires while over a flying zone, tp to down before unmounting
			if (checkLandingState() && (getMountType() == MountType.WYVERN)) {
				teleToLocation(TeleportWhereType.TOWN);
			}

			if (dismount()) // this should always be true now, since we teleported already
			{
				_taskRentPet.cancel(true);
				_taskRentPet = null;
			}
		}
	}

	public void startRentPet(int seconds) {
		if (_taskRentPet == null) {
			_taskRentPet = ThreadPool.getInstance().scheduleGeneralAtFixedRate(new RentPetTask(this), seconds * 1000L, seconds * 1000L, TimeUnit.MILLISECONDS);
		}
	}

	public boolean isRentedPet() {
		if (_taskRentPet != null) {
			return true;
		}

		return false;
	}

	public void stopWaterTask() {
		if (_taskWater != null) {
			_taskWater.cancel(false);
			_taskWater = null;
			sendPacket(new SetupGauge(getObjectId(), 2, 0));
		}
	}

	public void startWaterTask() {
		if (!isDead() && (_taskWater == null)) {
			int timeinwater = (int) getStat().getValue(DoubleStat.BREATH, 60000);

			sendPacket(new SetupGauge(getObjectId(), 2, timeinwater));
			_taskWater = ThreadPool.getInstance().scheduleGeneralAtFixedRate(new WaterTask(this), timeinwater, 1000, TimeUnit.MILLISECONDS);
		}
	}

	public boolean isInWater() {
		if (_taskWater != null) {
			return true;
		}

		return false;
	}

	public void checkWaterState() {
		if (isInsideZone(ZoneId.WATER)) {
			startWaterTask();
		} else {
			stopWaterTask();
		}
	}

	public void onPlayerEnter() {
		startWarnUserTakeBreak();

		if (isGM() && !AdminConfig.GM_STARTUP_BUILDER_HIDE) {
			// Bleah, see L2J custom below.
			if (AdminConfig.GM_STARTUP_INVULNERABLE && isInvul()) {
				sendMessage("Entering world in Invulnerable mode.");
			}
			if (AdminConfig.GM_STARTUP_INVISIBLE && isInvisible()) {
				sendMessage("Entering world in Invisible mode.");
			}
			if (AdminConfig.GM_STARTUP_SILENCE && isSilenceMode()) {
				sendMessage("Entering world in Silence mode.");
			}
		}

		// Buff and status icons
		if (PlayerConfig.STORE_SKILL_COOLTIME) {
			restoreEffects();
		}

		// TODO : Need to fix that hack!
		if (!isDead()) {
			setCurrentCp(_originalCp);
			setCurrentHp(_originalHp);
			setCurrentMp(_originalMp);
		}

		revalidateZone(true);

		notifyFriends(L2FriendStatus.MODE_ONLINE);
		if (!canOverrideCond(PcCondOverride.SKILL_CONDITIONS) && PlayerConfig.DECREASE_SKILL_LEVEL) {
			checkPlayerSkills();
		}

		try {
			final SayuneRequest sayune = getRequest(SayuneRequest.class);
			if (sayune != null) {
				sayune.onLogout();
			}
		} catch (Exception e) {
			LOGGER.error("deleteMe()", e);
		}

		try {
			for (ZoneType zone : ZoneManager.getInstance().getZones(this)) {
				zone.onPlayerLoginInside(this);
			}
		} catch (Exception e) {
			LOGGER.error("", e);
		}

		// Update record online players if a new record is reached.
		final int currentOnlinePlayers = WorldManager.getInstance().getPlayerCount();
		if (currentOnlinePlayers > GlobalVariablesManager.getInstance().getInt(GlobalVariablesManager.RECORD_ONLINE_PLAYERS_VAR, 0)) {
			GlobalVariablesManager.getInstance().set(GlobalVariablesManager.RECORD_ONLINE_PLAYERS_VAR, currentOnlinePlayers);
		}

		EventDispatcher.getInstance().notifyEventAsync(new OnPlayerLogin(this), this);

		if (isMentee()) {
			// Notify to scripts
			EventDispatcher.getInstance().notifyEventAsync(new OnPlayerMenteeStatus(this, true), this);
		} else if (isMentor()) {
			// Notify to scripts
			EventDispatcher.getInstance().notifyEventAsync(new OnPlayerMentorStatus(this, true), this);
		}
	}

	public long getLastAccess() {
		return _lastAccess;
	}

	protected void setLastAccess(long lastAccess) {
		_lastAccess = lastAccess;
	}

	@Override
	public void doRevive() {
		super.doRevive();
		sendPacket(new EtcStatusUpdate(this));
		_revivePet = false;
		_reviveRequested = 0;
		_revivePower = 0;

		if (isMounted()) {
			startFeed(_mountNpcId);
		}

		// Notify instance
		final Instance instance = getInstanceWorld();
		if (instance != null) {
			instance.doRevive(this);
		}
	}

	@Override
	public void doRevive(double revivePower) {
		// Restore the player's lost experience,
		// depending on the % return of the skill used (based on its power).
		restoreExp(revivePower);
		doRevive();
	}

	public void reviveRequest(Player reviver, Skill skill, boolean Pet, int power) {
		if (isResurrectionBlocked()) {
			return;
		}

		if (_reviveRequested == 1) {
			if (_revivePet == Pet) {
				reviver.sendPacket(SystemMessageId.RESURRECTION_HAS_ALREADY_BEEN_PROPOSED); // Resurrection is already been proposed.
			} else {
				if (Pet) {
					reviver.sendPacket(SystemMessageId.A_PET_CANNOT_BE_RESURRECTED_WHILE_IT_S_OWNER_IS_IN_THE_PROCESS_OF_RESURRECTING); // A pet cannot be resurrected while it's owner is in the process of resurrecting.
				} else {
					reviver.sendPacket(SystemMessageId.WHILE_A_PET_IS_BEING_RESURRECTED_IT_CANNOT_HELP_IN_RESURRECTING_ITS_MASTER); // While a pet is attempting to resurrect, it cannot help in resurrecting its master.
				}
			}
			return;
		}
		final Summon pet = getPet();
		if ((Pet && (pet != null) && pet.isDead()) || (!Pet && isDead())) {
			_reviveRequested = 1;
			_revivePower = Formulas.calculateSkillResurrectRestorePercent(power, reviver);
			_revivePet = Pet;

			if (hasCharmOfCourage()) {
				final ConfirmDlg dlg = new ConfirmDlg(SystemMessageId.YOUR_CHARM_OF_COURAGE_IS_TRYING_TO_RESURRECT_YOU_WOULD_YOU_LIKE_TO_RESURRECT_NOW.getId());
				dlg.addTime(60000);
				sendPacket(dlg);
				return;
			}

			final long restoreExp = Math.round(((getExpBeforeDeath() - getExp()) * _revivePower) / 100);
			ConfirmDlg dlg = new ConfirmDlg(SystemMessageId.C1_IS_ATTEMPTING_TO_DO_A_RESURRECTION_THAT_RESTORES_S2_S3_XP_ACCEPT.getId());
			dlg.addPcName(reviver);
			dlg.addLong(restoreExp);
			dlg.addInt(power);
			sendPacket(dlg);
		}
	}

	public void reviveAnswer(int answer) {
		final Summon pet = getPet();
		if ((_reviveRequested != 1) || (!isDead() && !_revivePet) || (_revivePet && (pet != null) && !pet.isDead())) {
			return;
		}

		if (answer == 1) {
			if (!_revivePet) {
				if (_revivePower != 0) {
					doRevive(_revivePower);
				} else {
					doRevive();
				}
			} else if (pet != null) {
				if (_revivePower != 0) {
					pet.doRevive(_revivePower);
				} else {
					pet.doRevive();
				}
			}
		}
		_reviveRequested = 0;
		_revivePower = 0;
	}

	public boolean isReviveRequested() {
		return (_reviveRequested == 1);
	}

	public boolean isRevivingPet() {
		return _revivePet;
	}

	public void removeReviving() {
		_reviveRequested = 0;
		_revivePower = 0;
	}

	public void onActionRequest() {
		if (isSpawnProtected()) {
			sendPacket(SystemMessageId.YOU_ARE_NO_LONGER_PROTECTED_FROM_AGGRESSIVE_MONSTERS);

			if (PlayerConfig.RESTORE_SERVITOR_ON_RECONNECT && !hasSummon() && CharSummonTable.getInstance().getServitors().containsKey(getObjectId())) {
				CharSummonTable.getInstance().restoreServitor(this);
			}
			if (PlayerConfig.RESTORE_PET_ON_RECONNECT && !hasSummon() && CharSummonTable.getInstance().getPets().containsKey(getObjectId())) {
				CharSummonTable.getInstance().restorePet(this);
			}
		}
		if (isTeleportProtected()) {
			sendMessage("Teleport spawn protection ended.");
		}
		setProtection(false);
		setTeleportProtection(false);
	}

	/**
	 * Expertise of the L2PcInstance (None=0, D=1, C=2, B=3, A=4, S=5, S80=6, S84=7, R=8, R95=9, R99=10)
	 *
	 * @return CrystalTyperepresenting expertise level..
	 */
	public CrystalType getExpertiseLevel() {
		return _expertiseLevel;
	}

	public void setExpertiseLevel(CrystalType crystalType) {
		_expertiseLevel = crystalType != null ? crystalType : CrystalType.NONE;
	}

	@Override
	public void teleToLocation(ILocational loc, boolean allowRandomOffset) {
		if ((getVehicle() != null) && !getVehicle().isTeleporting()) {
			setVehicle(null);
		}

		if (isFlyingMounted() && (loc.getZ() < -1005)) {
			super.teleToLocation(loc.getX(), loc.getY(), -1005, loc.getHeading());
		}
		super.teleToLocation(loc, allowRandomOffset);
	}

	@Override
	public final void onTeleported() {
		super.onTeleported();

		if (isInAirShip()) {
			getAirShip().sendInfo(this);
		}

		// Force a revalidation
		revalidateZone(true);

		checkItemRestriction();

		if ((PlayerConfig.PLAYER_TELEPORT_PROTECTION > 0) && !isInOlympiadMode()) {
			setTeleportProtection(true);
		}

		// Trained beast is lost after teleport
		if (getTrainedBeasts() != null) {
			for (TamedBeastInstance tamedBeast : getTrainedBeasts()) {
				tamedBeast.deleteMe();
			}
			getTrainedBeasts().clear();
		}

		// Modify the position of the pet if necessary
		final Summon pet = getPet();
		if (pet != null) {
			pet.setFollowStatus(false);
			pet.teleToLocation(getLocation(), false);
			((SummonAI) pet.getAI()).setStartFollowController(true);
			pet.setFollowStatus(true);
			pet.setInstance(getInstanceWorld());
			pet.updateAndBroadcastStatus(0);
		}

		getServitors().values().forEach(s ->
		{
			s.setFollowStatus(false);
			s.teleToLocation(getLocation(), false);
			((SummonAI) s.getAI()).setStartFollowController(true);
			s.setFollowStatus(true);
			s.setInstance(getInstanceWorld());
			s.updateAndBroadcastStatus(0);
		});
	}

	@Override
	public void setIsTeleporting(boolean teleport) {
		setIsTeleporting(teleport, true);
	}

	public void setIsTeleporting(boolean teleport, boolean useWatchDog) {
		super.setIsTeleporting(teleport);
		if (!useWatchDog) {
			return;
		}
		if (teleport) {
			if ((_teleportWatchdog == null) && (PlayerConfig.TELEPORT_WATCHDOG_TIMEOUT > 0)) {
				synchronized (this) {
					if (_teleportWatchdog == null) {
						_teleportWatchdog = ThreadPool.getInstance().scheduleGeneral(new TeleportWatchdogTask(this), PlayerConfig.TELEPORT_WATCHDOG_TIMEOUT * 1000, TimeUnit.MILLISECONDS);
					}
				}
			}
		} else {
			if (_teleportWatchdog != null) {
				_teleportWatchdog.cancel(false);
				_teleportWatchdog = null;
			}
		}
	}

	public void setLastServerPosition(double x, double y, double z) {
		_lastServerPosition.setXYZ(x, y, z);
	}

	public Location getLastServerPosition() {
		return _lastServerPosition;
	}

	@Override
	public void addExpAndSp(double addToExp, double addToSp) {
		getStat().addExpAndSp(addToExp, addToSp, false);
	}

	public void addExpAndSp(double addToExp, double addToSp, boolean useVitality) {
		getStat().addExpAndSp(addToExp, addToSp, useVitality);
	}

	public void removeExpAndSp(long removeExp, long removeSp) {
		getStat().removeExpAndSp(removeExp, removeSp, true);
	}

	public void removeExpAndSp(long removeExp, long removeSp, boolean sendMessage) {
		getStat().removeExpAndSp(removeExp, removeSp, sendMessage);
	}

	@Override
	public void reduceCurrentHp(double value, Creature attacker, Skill skill, boolean isDOT, boolean directlyToHp, boolean critical, boolean reflect) {
		super.reduceCurrentHp(value, attacker, skill, isDOT, directlyToHp, critical, reflect);

		// notify the tamed beast of attacks
		if (getTrainedBeasts() != null) {
			for (TamedBeastInstance tamedBeast : getTrainedBeasts()) {
				tamedBeast.onOwnerGotAttacked(attacker);
			}
		}
	}

	public void broadcastSnoop(ChatType type, String name, String _text) {
		if (!_snoopListener.isEmpty()) {
			final Snoop sn = new Snoop(getObjectId(), getName(), type, name, _text);

			for (Player pci : _snoopListener) {
				if (pci != null) {
					pci.sendPacket(sn);
				}
			}
		}
	}

	public void addSnooper(Player pci) {
		if (!_snoopListener.contains(pci)) {
			_snoopListener.add(pci);
		}
	}

	public void removeSnooper(Player pci) {
		_snoopListener.remove(pci);
	}

	public void addSnooped(Player pci) {
		if (!_snoopedPlayer.contains(pci)) {
			_snoopedPlayer.add(pci);
		}
	}

	public void removeSnooped(Player pci) {
		_snoopedPlayer.remove(pci);
	}

	public void addHtmlAction(HtmlActionScope scope, String action) {
		_htmlActionCaches[scope.ordinal()].add(action);
	}

	public void clearHtmlActions(HtmlActionScope scope) {
		_htmlActionCaches[scope.ordinal()].clear();
	}

	public void setHtmlActionOriginObjectId(HtmlActionScope scope, int npcObjId) {
		if (npcObjId < 0) {
			throw new IllegalArgumentException();
		}

		_htmlActionOriginObjectIds[scope.ordinal()] = npcObjId;
	}

	public int getLastHtmlActionOriginId() {
		return _lastHtmlActionOriginObjId;
	}

	private boolean validateHtmlAction(Iterable<String> actionIter, String action) {
		for (String cachedAction : actionIter) {
			if (cachedAction.charAt(cachedAction.length() - 1) == AbstractHtmlPacket.VAR_PARAM_START_CHAR) {
				if (action.startsWith(cachedAction.substring(0, cachedAction.length() - 1).trim())) {
					return true;
				}
			} else if (cachedAction.equals(action)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Check if the HTML action was sent in a HTML packet.<br>
	 * If the HTML action was not sent for whatever reason, -1 is returned.<br>
	 * Otherwise, the NPC object ID or 0 is returned.<br>
	 * 0 means the HTML action was not bound to an NPC<br>
	 * and no range checks need to be made.
	 *
	 * @param action the HTML action to check
	 * @return NPC object ID, 0 or -1
	 */
	public int validateHtmlAction(String action) {
		for (int i = 0; i < _htmlActionCaches.length; ++i) {
			if (validateHtmlAction(_htmlActionCaches[i], action)) {
				_lastHtmlActionOriginObjId = _htmlActionOriginObjectIds[i];
				return _lastHtmlActionOriginObjId;
			}
		}

		return -1;
	}

	/**
	 * Performs following tests:
	 * <ul>
	 * <li>Inventory contains item</li>
	 * <li>Item owner id == owner id</li>
	 * <li>It isnt pet control item while mounting pet or pet summoned</li>
	 * <li>It isnt active enchant item</li>
	 * <li>It isnt cursed weapon/item</li>
	 * <li>It isnt wear item</li>
	 * </ul>
	 *
	 * @param objectId item object id
	 * @param action   just for login porpouse
	 * @return
	 */
	public boolean validateItemManipulation(int objectId, String action) {
		ItemInstance item = getInventory().getItemByObjectId(objectId);

		if ((item == null) || (item.getOwnerId() != getObjectId())) {
			LOGGER.trace(getObjectId() + ": player tried to " + action + " item he is not owner of");
			return false;
		}

		// Pet is summoned and not the item that summoned the pet AND not the buggle from strider you're mounting
		final Summon pet = getPet();
		if (((pet != null) && (pet.getControlObjectId() == objectId)) || (getMountObjectID() == objectId)) {
			if (GeneralConfig.DEBUG) {
				LOGGER.trace(getObjectId() + ": player tried to " + action + " item controling pet");
			}

			return false;
		}

		if (isProcessingItem(objectId)) {
			if (GeneralConfig.DEBUG) {
				LOGGER.trace(getObjectId() + ":player tried to " + action + " an enchant scroll he was using");
			}
			return false;
		}

		if (CursedWeaponsManager.getInstance().isCursed(item.getId())) {
			// can not trade a cursed weapon
			return false;
		}

		return true;
	}

	/**
	 * @return Returns the inBoat.
	 */
	public boolean isInBoat() {
		return (_vehicle != null) && _vehicle.isBoat();
	}

	/**
	 * @return
	 */
	public BoatInstance getBoat() {
		return (BoatInstance) _vehicle;
	}

	/**
	 * @return Returns the inAirShip.
	 */
	public boolean isInAirShip() {
		return (_vehicle != null) && _vehicle.isAirShip();
	}

	/**
	 * @return
	 */
	public AirShipInstance getAirShip() {
		return (AirShipInstance) _vehicle;
	}

	public boolean isInShuttle() {
		return _vehicle instanceof ShuttleInstance;
	}

	public ShuttleInstance getShuttle() {
		return (ShuttleInstance) _vehicle;
	}

	public Vehicle getVehicle() {
		return _vehicle;
	}

	public void setVehicle(Vehicle v) {
		if ((v == null) && (_vehicle != null)) {
			_vehicle.removePassenger(this);
		}

		_vehicle = v;
	}

	public boolean isInVehicle() {
		return _vehicle != null;
	}

	/**
	 * @return
	 */
	public Location getInVehiclePosition() {
		return _inVehiclePosition;
	}

	public void setInVehiclePosition(Location pt) {
		_inVehiclePosition = pt;
	}

	/**
	 * Manage the delete task of a L2PcInstance (Leave Party, Unsummon pet, Save its inventory in the database, Remove it from the world...).<br>
	 * <B><U>Actions</U>:</B>
	 * <ul>
	 * <li>If the L2PcInstance is in observer mode, set its position to its position before entering in observer mode</li>
	 * <li>Set the online Flag to True or False and update the characters table of the database with online status and lastAccess</li>
	 * <li>Stop the HP/MP/CP Regeneration task</li>
	 * <li>Cancel Crafting, Attack or Cast</li>
	 * <li>Remove the L2PcInstance from the world</li>
	 * <li>Stop Party and Unsummon Pet</li>
	 * <li>Update database with items in its inventory and remove them from the world</li>
	 * <li>Remove all L2Object from _knownObjects and _knownPlayer of the L2Character then cancel Attack or Cast and notify AI</li>
	 * <li>Close the connection with the client</li>
	 * </ul>
	 * <br>
	 * Remember this method is not to be used to half-ass disconnect players! This method is dedicated only to erase the player from the world.<br>
	 * If you intend to disconnect a player please use {@link Disconnection}
	 */
	@Override
	public boolean deleteMe() {
		try {
			for (ZoneType zone : ZoneManager.getInstance().getZones(this)) {
				zone.onPlayerLogoutInside(this);
			}
		} catch (Exception e) {
			LOGGER.error("deleteMe()", e);
		}

		// Set the online Flag to True or False and update the characters table of the database with online status and lastAccess (called when login and logout)
		try {
			setOnlineStatus(false, true);
		} catch (Exception e) {
			LOGGER.error("deleteMe()", e);
		}

		try {
			if (GeneralConfig.ENABLE_BLOCK_CHECKER_EVENT && (getBlockCheckerArena() != -1)) {
				HandysBlockCheckerManager.getInstance().onDisconnect(this);
			}
		} catch (Exception e) {
			LOGGER.error("deleteMe()", e);
		}

		try {
			_isOnline = false;
			abortAttack();
			abortCast();
			stopMove(null);
			removeDebuggers();
		} catch (Exception e) {
			LOGGER.error("deleteMe()", e);
		}

		// remove combat flag
		try {
			if (getInventory().getItemByItemId(9819) != null) {
				Fort fort = FortManager.getInstance().getFort(this);
				if (fort != null) {
					FortSiegeManager.getInstance().dropCombatFlag(this, fort.getResidenceId());
				} else {
					int slot = getInventory().getSlotFromItem(getInventory().getItemByItemId(9819));
					getInventory().unEquipItemInBodySlot(slot);
					destroyItem("CombatFlag", getInventory().getItemByItemId(9819), null, true);
				}
			}
		} catch (Exception e) {
			LOGGER.error("deleteMe()", e);
		}

		try {
			if (_matchingRoom != null) {
				_matchingRoom.deleteMember(this, false);
			}
			MatchingRoomManager.getInstance().removeFromWaitingList(this);
		} catch (Exception e) {
			LOGGER.error("deleteMe()", e);
		}

		try {
			if (isFlying()) {
				removeSkill(CommonSkill.WYVERN_BREATH.getSkill());
			}
		} catch (Exception e) {
			LOGGER.error("deleteMe()", e);
		}

		// Recommendations must be saved before task (timer) is canceled
		try {
			storeRecommendations();
		} catch (Exception e) {
			LOGGER.error("deleteMe()", e);
		}
		// Stop the HP/MP/CP Regeneration task (scheduled tasks)
		try {
			stopAllTimers();
		} catch (Exception e) {
			LOGGER.error("deleteMe()", e);
		}

		try {
			setIsTeleporting(false);
		} catch (Exception e) {
			LOGGER.error("deleteMe()", e);
		}

		// Cancel Attak or Cast
		try {
			setTarget(null);
		} catch (Exception e) {
			LOGGER.error("deleteMe()", e);
		}

		if (isChannelized()) {
			getSkillChannelized().abortChannelization();
		}

		// Stop all toggles.
		getEffectList().stopAllToggles();

		// Remove from world regions zones
		ZoneManager.getInstance().getRegion(this).removeFromZones(this, true);

		// Remove the L2PcInstance from the world
		try {
			decayMe();
		} catch (Exception e) {
			LOGGER.error("deleteMe()", e);
		}

		// If a Party is in progress, leave it (and festival party)
		if (isInParty()) {
			try {
				leaveParty();
			} catch (Exception e) {
				LOGGER.error("deleteMe()", e);
			}
		}

		if (OlympiadManager.getInstance().isRegistered(this) || (getOlympiadGameId() != -1)) {
			OlympiadManager.getInstance().removeDisconnectedCompetitor(this);
		}

		// If the L2PcInstance has Pet, unsummon it
		if (hasSummon()) {
			try {
				Summon pet = getPet();
				if (pet != null) {
					pet.setRestoreSummon(true);
					pet.unSummon(this);
					// Dead pet wasn't unsummoned, broadcast npcinfo changes (pet will be without owner name - means owner offline)
					pet = getPet();
					if (pet != null) {
						pet.broadcastNpcInfo(0);
					}
				}

				getServitors().values().forEach(s ->
				{
					s.setRestoreSummon(true);
					s.unSummon(this);
				});
			} catch (Exception e) {
				LOGGER.error("deleteMe()", e);
			} // returns pet to control item
		}

		if (getClan() != null) {
			// set the status for pledge member list to OFFLINE
			try {
				ClanMember clanMember = getClan().getClanMember(getObjectId());
				if (clanMember != null) {
					clanMember.setPlayerInstance(null);
				}

			} catch (Exception e) {
				LOGGER.error("deleteMe()", e);
			}
		}

		if (getActiveRequester() != null) {
			// deals with sudden exit in the middle of transaction
			setActiveRequester(null);
			cancelActiveTrade();
		}

		// If the L2PcInstance is a GM, remove it from the GM List
		if (isGM()) {
			try {
				AdminData.getInstance().deleteGm(this);
			} catch (Exception e) {
				LOGGER.error("deleteMe()", e);
			}
		}

		try {
			// Check if the L2PcInstance is in observer mode to set its position to its position
			// before entering in observer mode
			if (inObserverMode()) {
				setLocationInvisible(_lastLoc);
			}

			if (getVehicle() != null) {
				getVehicle().oustPlayer(this);
			}
		} catch (Exception e) {
			LOGGER.error("deleteMe()", e);
		}

		// remove player from instance
		final Instance inst = getInstanceWorld();
		if (inst != null) {
			try {
				inst.onPlayerLogout(this);
			} catch (Exception e) {
				LOGGER.error("deleteMe()", e);
			}
		}

		try {
			stopCubics();
		} catch (Exception e) {
			LOGGER.error("deleteMe()", e);
		}

		// Update database with items in its inventory and remove them from the world
		try {
			getInventory().deleteMe();
		} catch (Exception e) {
			LOGGER.error("deleteMe()", e);
		}

		// Update database with items in its warehouse and remove them from the world
		try {
			clearWarehouse();
		} catch (Exception e) {
			LOGGER.error("deleteMe()", e);
		}
		if (GeneralConfig.WAREHOUSE_CACHE) {
			WarehouseCacheManager.getInstance().remCacheTask(this);
		}

		try {
			getFreight().deleteMe();
		} catch (Exception e) {
			LOGGER.error("deleteMe()", e);
		}

		try {
			clearRefund();
		} catch (Exception e) {
			LOGGER.error("deleteMe()", e);
		}

		if (isCursedWeaponEquipped()) {
			try {
				CursedWeaponsManager.getInstance().getCursedWeapon(_cursedWeaponEquippedId).setPlayer(null);
			} catch (Exception e) {
				LOGGER.error("deleteMe()", e);
			}
		}

		if (getClanId() > 0) {
			getClan().broadcastToOtherOnlineMembers(new PledgeShowMemberListUpdate(this), this);
			getClan().broadcastToOnlineMembers(new ExPledgeCount(getClan()));
			// ClanTable.getInstance().getClan(getClanId()).broadcastToOnlineMembers(new PledgeShowMemberListAdd(this));
		}

		for (Player player : _snoopedPlayer) {
			player.removeSnooper(this);
		}

		for (Player player : _snoopListener) {
			player.removeSnooped(this);
		}

		if (isMentee()) {
			// Notify to scripts
			EventDispatcher.getInstance().notifyEventAsync(new OnPlayerMenteeStatus(this, false), this);
		} else if (isMentor()) {
			// Notify to scripts
			EventDispatcher.getInstance().notifyEventAsync(new OnPlayerMentorStatus(this, false), this);
		}

		try {
			notifyFriends(L2FriendStatus.MODE_OFFLINE);
			getBlockList().playerLogout();
		} catch (Exception e) {
			LOGGER.warn("Exception on deleteMe() notifyFriends: " + e.getMessage(), e);
		}

		// Stop all passives and augment options without broadcasting changes.
		getEffectList().stopAllOptions(false, false);

		stopAutoSaveTask();

		return super.deleteMe();
	}

	public int getInventoryLimit() {
		int ivlim;
		if (isGM()) {
			ivlim = PlayerConfig.INVENTORY_MAXIMUM_GM;
		} else if (getRace() == Race.DWARF) {
			ivlim = PlayerConfig.INVENTORY_MAXIMUM_DWARF;
		} else {
			ivlim = PlayerConfig.INVENTORY_MAXIMUM_NO_DWARF;
		}
		ivlim += (int) getStat().getValue(DoubleStat.INVENTORY_NORMAL, 0);

		return ivlim;
	}

	public int getWareHouseLimit() {
		int whlim;
		if (getRace() == Race.DWARF) {
			whlim = PlayerConfig.WAREHOUSE_SLOTS_DWARF;
		} else {
			whlim = PlayerConfig.WAREHOUSE_SLOTS_NO_DWARF;
		}

		whlim += (int) getStat().getValue(DoubleStat.STORAGE_PRIVATE, 0);

		return whlim;
	}

	public int getPrivateSellStoreLimit() {
		int pslim;

		if (getRace() == Race.DWARF) {
			pslim = PlayerConfig.MAX_PVTSTORESELL_SLOTS_DWARF;
		} else {
			pslim = PlayerConfig.MAX_PVTSTORESELL_SLOTS_OTHER;
		}

		pslim += (int) getStat().getValue(DoubleStat.TRADE_SELL, 0);

		return pslim;
	}

	public int getPrivateBuyStoreLimit() {
		int pblim;

		if (getRace() == Race.DWARF) {
			pblim = PlayerConfig.MAX_PVTSTOREBUY_SLOTS_DWARF;
		} else {
			pblim = PlayerConfig.MAX_PVTSTOREBUY_SLOTS_OTHER;
		}
		pblim += (int) getStat().getValue(DoubleStat.TRADE_BUY, 0);

		return pblim;
	}

	public int getDwarfRecipeLimit() {
		int recdlim = PlayerConfig.DWARF_RECIPE_LIMIT;
		recdlim += (int) getStat().getValue(DoubleStat.RECIPE_DWARVEN, 0);
		return recdlim;
	}

	public int getCommonRecipeLimit() {
		int recclim = PlayerConfig.COMMON_RECIPE_LIMIT;
		recclim += (int) getStat().getValue(DoubleStat.RECIPE_COMMON, 0);
		return recclim;
	}

	/**
	 * @return Returns the mountNpcId.
	 */
	public int getMountNpcId() {
		return _mountNpcId;
	}

	/**
	 * @return Returns the mountLevel.
	 */
	public int getMountLevel() {
		return _mountLevel;
	}

	public void setMountObjectID(int newID) {
		_mountObjectID = newID;
	}

	public int getMountObjectID() {
		return _mountObjectID;
	}

	public SkillUseHolder getQueuedSkill() {
		return _queuedSkill;
	}

	/**
	 * Create a new SkillDat object and queue it in the player _queuedSkill.
	 *
	 * @param queuedSkill
	 * @param item
	 * @param ctrlPressed
	 * @param shiftPressed
	 */
	public void setQueuedSkill(Skill queuedSkill, ItemInstance item, boolean ctrlPressed, boolean shiftPressed) {
		if (queuedSkill == null) {
			_queuedSkill = null;
			return;
		}
		_queuedSkill = new SkillUseHolder(queuedSkill, item, ctrlPressed, shiftPressed);
	}

	/**
	 * @return {@code true} if player is jailed, {@code false} otherwise.
	 */
	public boolean isJailed() {
		return PunishmentManager.getInstance().hasPunishment(getObjectId(), PunishmentAffect.CHARACTER, PunishmentType.JAIL) //
				|| PunishmentManager.getInstance().hasPunishment(getAccountName(), PunishmentAffect.ACCOUNT, PunishmentType.JAIL) //
				|| PunishmentManager.getInstance().hasPunishment(getIPAddress(), PunishmentAffect.IP, PunishmentType.JAIL) //
				|| PunishmentManager.getInstance().hasPunishment(getHWID(), PunishmentAffect.HWID, PunishmentType.JAIL);
	}

	/**
	 * @return {@code true} if player is chat banned, {@code false} otherwise.
	 */
	public boolean isChatBanned() {
		return PunishmentManager.getInstance().hasPunishment(getObjectId(), PunishmentAffect.CHARACTER, PunishmentType.CHAT_BAN) //
				|| PunishmentManager.getInstance().hasPunishment(getAccountName(), PunishmentAffect.ACCOUNT, PunishmentType.CHAT_BAN) //
				|| PunishmentManager.getInstance().hasPunishment(getIPAddress(), PunishmentAffect.IP, PunishmentType.CHAT_BAN) //
				|| PunishmentManager.getInstance().hasPunishment(getHWID(), PunishmentAffect.HWID, PunishmentType.CHAT_BAN);
	}

	public void startFameTask(long delay, int fameFixRate) {
		if ((getLevel() < 40) || isInOneOfCategory(CategoryType.FIRST_CLASS_GROUP, CategoryType.SECOND_CLASS_GROUP)) {
			return;
		}
		if (_fameTask == null) {
			_fameTask = ThreadPool.getInstance().scheduleGeneralAtFixedRate(new FameTask(this, fameFixRate), delay, delay, TimeUnit.MILLISECONDS);
		}
	}

	public void stopFameTask() {
		if (_fameTask != null) {
			_fameTask.cancel(false);
			_fameTask = null;
		}
	}

	public int getPowerGrade() {
		return _powerGrade;
	}

	public void setPowerGrade(int power) {
		_powerGrade = power;
	}

	public boolean isCursedWeaponEquipped() {
		return _cursedWeaponEquippedId != 0;
	}

	public void setCursedWeaponEquippedId(int value) {
		_cursedWeaponEquippedId = value;
	}

	public int getCursedWeaponEquippedId() {
		return _cursedWeaponEquippedId;
	}

	public boolean isCombatFlagEquipped() {
		return _combatFlagEquippedId;
	}

	public void setCombatFlagEquipped(boolean value) {
		_combatFlagEquippedId = value;
	}

	/**
	 * Returns the Number of Souls this L2PcInstance got.
	 *
	 * @return
	 */
	public int getChargedSouls() {
		return _souls;
	}

	/**
	 * Increase Souls
	 *
	 * @param count
	 */
	public void increaseSouls(int count) {
		_souls += count;
		SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOUR_SOUL_COUNT_HAS_INCREASED_BY_S1_IT_IS_NOW_AT_S2);
		sm.addInt(count);
		sm.addInt(_souls);
		sendPacket(sm);
		restartSoulTask();
		sendPacket(new EtcStatusUpdate(this));
	}

	/**
	 * Decreases existing Souls.
	 *
	 * @param count
	 * @return
	 */
	public boolean decreaseSouls(int count) {
		if ((getChargedSouls() - count) < 0) {
			return false;
		}

		_souls -= count;

		if (getChargedSouls() < 0) {
			_souls = 0;
		}

		if (getChargedSouls() == 0) {
			stopSoulTask();
		} else {
			restartSoulTask();
		}

		sendPacket(new EtcStatusUpdate(this));
		return true;
	}

	/**
	 * Clear out all Souls from this L2PcInstance
	 */
	public void clearSouls() {
		_souls = 0;
		stopSoulTask();
		sendPacket(new EtcStatusUpdate(this));
	}

	/**
	 * Starts/Restarts the SoulTask to Clear Souls after 10 Mins.
	 */
	private void restartSoulTask() {
		if (_soulTask != null) {
			_soulTask.cancel(false);
			_soulTask = null;
		}
		_soulTask = ThreadPool.getInstance().scheduleGeneral(new ResetSoulsTask(this), 600000, TimeUnit.MILLISECONDS);

	}

	/**
	 * Stops the Clearing Task.
	 */
	public void stopSoulTask() {
		if (_soulTask != null) {
			_soulTask.cancel(false);
			_soulTask = null;
		}
	}

	public int getShilensBreathDebuffLevel() {
		final BuffInfo buff = getEffectList().getBuffInfoBySkillId(CommonSkill.SHILENS_BREATH.getId());
		return buff == null ? 0 : buff.getSkill().getLevel();
	}

	public void calculateShilensBreathDebuffLevel(Creature killer) {
		if (killer == null) {
			LOGGER.warn(this + " called calculateShilensBreathDebuffLevel with killer null!");
			return;
		}

		if (getStat().has(BooleanStat.RESURRECTION_SPECIAL) || isBlockedFromDeathPenalty() || isInsideZone(ZoneId.PVP) || isInsideZone(ZoneId.SIEGE) || canOverrideCond(PcCondOverride.DEATH_PENALTY)) {
			return;
		}

		final TerminateReturn term = EventDispatcher.getInstance().notifyEvent(new OnPlayerDeathPenalty(this, killer), this, TerminateReturn.class);
		if ((term != null) && term.terminate()) {
			return;
		}

		double percent = 1.0;

		if (killer.isRaid()) {
			percent *= getStat().getValue(DoubleStat.REDUCE_DEATH_PENALTY_BY_RAID, 1);
		} else if (killer.isMonster()) {
			percent *= getStat().getValue(DoubleStat.REDUCE_DEATH_PENALTY_BY_MOB, 1);
		} else if (killer.isPlayable()) {
			percent *= getStat().getValue(DoubleStat.REDUCE_DEATH_PENALTY_BY_PVP, 1);
		}

		if ((killer.isNpc() && ((Npc) killer).getTemplate().isDeathPenalty()) || (Rnd.get(1, 100) <= ((PlayerConfig.DEATH_PENALTY_CHANCE) * percent))) {
			if (!killer.isPlayable() || (getReputation() < 0)) {
				increaseShilensBreathDebuff();
			}
		}
	}

	public void increaseShilensBreathDebuff() {
		int nextLv = getShilensBreathDebuffLevel() + 1;
		if (nextLv > 5) {
			nextLv = 5;
		}

		final Skill skill = SkillData.getInstance().getSkill(CommonSkill.SHILENS_BREATH.getId(), nextLv);
		if (skill != null) {
			skill.applyEffects(this, this);
			sendPacket(SystemMessage.getSystemMessage(SystemMessageId.YOU_VE_BEEN_AFFLICTED_BY_SHILEN_S_BREATH_LEVEL_S1).addInt(nextLv));
		}
	}

	public void decreaseShilensBreathDebuff() {
		final int nextLv = getShilensBreathDebuffLevel() - 1;
		if (nextLv > 0) {
			final Skill skill = SkillData.getInstance().getSkill(CommonSkill.SHILENS_BREATH.getId(), nextLv);
			skill.applyEffects(this, this);
			sendPacket(SystemMessage.getSystemMessage(SystemMessageId.YOU_VE_BEEN_AFFLICTED_BY_SHILEN_S_BREATH_LEVEL_S1).addInt(nextLv));
		} else {
			sendPacket(SystemMessageId.SHILEN_S_BREATH_HAS_BEEN_PURIFIED);
		}
	}

	public void setShilensBreathDebuffLevel(int level) {
		if (level > 0) {
			final Skill skill = SkillData.getInstance().getSkill(CommonSkill.SHILENS_BREATH.getId(), level);
			skill.applyEffects(this, this);
			sendPacket(SystemMessage.getSystemMessage(SystemMessageId.YOU_VE_BEEN_AFFLICTED_BY_SHILEN_S_BREATH_LEVEL_S1).addInt(level));
		}
	}

	@Override
	public Player getActingPlayer() {
		return this;
	}

	@Override
	public void sendDamageMessage(Creature target, Skill skill, int damage, boolean crit, boolean miss) {
		// Check if hit is missed
		if (miss) {
			if (skill == null) {
				if (target.isPlayer()) {
					SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_HAS_EVADED_C2_S_ATTACK);
					sm.addPcName(target.getActingPlayer());
					sm.addCharName(this);
					target.sendPacket(sm);
				}
				SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_S_ATTACK_WENT_ASTRAY);
				sm.addPcName(this);
				sendPacket(sm);
			} else {
				sendPacket(new ExMagicAttackInfo(getObjectId(), target.getObjectId(), ExMagicAttackInfo.EVADED));
			}
			return;
		}

		// Check if hit is critical
		if (crit) {
			if ((skill == null) || !skill.isMagic()) {
				SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_LANDED_A_CRITICAL_HIT);
				sm.addPcName(this);
				sendPacket(sm);
			} else {
				sendPacket(SystemMessageId.M_CRITICAL);
			}

			if (skill != null) {
				sendPacket(new ExMagicAttackInfo(getObjectId(), target.getObjectId(), ExMagicAttackInfo.CRITICAL));
			}
		}

		if (isInOlympiadMode() && target.isPlayer() && target.getActingPlayer().isInOlympiadMode() && (target.getActingPlayer().getOlympiadGameId() == getOlympiadGameId())) {
			OlympiadGameManager.getInstance().notifyCompetitorDamage(this, damage);
		}

		final SystemMessage sm;

		if ((target.isHpBlocked() && !target.isNpc()) || (target.isPlayer() && target.getStat().has(BooleanStat.FACE_OFF) && (target.getActingPlayer().getAttackerObjId() != getObjectId()))) {
			sm = SystemMessage.getSystemMessage(SystemMessageId.THE_ATTACK_HAS_BEEN_BLOCKED);
		} else if (target.isDoor() || (target instanceof ControlTowerInstance)) {
			sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_HIT_FOR_S1_DAMAGE);
			sm.addInt(damage);
		} else {
			sm = SystemMessage.getSystemMessage(SystemMessageId.C1_HAS_INFLICTED_S3_DAMAGE_ON_C2_S4);
			sm.addPcName(this);
			sm.addCharName(target);
			sm.addInt(damage);
			sm.addPopup(target.getObjectId(), getObjectId(), -damage);
		}
		sendPacket(sm);
	}

	/**
	 * @param npcId
	 */
	public void setAgathionId(int npcId) {
		_agathionId = npcId;
	}

	/**
	 * @return
	 */
	public int getAgathionId() {
		return _agathionId;
	}

	public int getVitalityPoints() {
		return getStat().getVitalityPoints();
	}

	public void setVitalityPoints(int points, boolean quiet) {
		getStat().setVitalityPoints(points, quiet);
	}

	public void updateVitalityPoints(int points, boolean useRates, boolean quiet) {
		getStat().updateVitalityPoints(points, useRates, quiet);
	}

	/**
	 * @return {@code true} if vitality is in a state of no bonus and no consumption, {@code false} otherwise.
	 */
	public boolean isVitalityDisabled() {
		return !PlayerConfig.ENABLE_VITALITY || getStat().has(BooleanStat.DISABLE_VITALITY);
	}

	public void checkItemRestriction() {
		for (int i = 0; i < Inventory.PAPERDOLL_TOTALSLOTS; i++) {
			ItemInstance equippedItem = getInventory().getPaperdollItem(i);
			if ((equippedItem != null) && !equippedItem.getItem().checkCondition(this, this, false)) {
				getInventory().unEquipItemInSlot(i);

				InventoryUpdate iu = new InventoryUpdate();
				iu.addModifiedItem(equippedItem);
				sendInventoryUpdate(iu);

				SystemMessage sm = null;
				if (equippedItem.getItem().getBodyPart() == ItemTemplate.SLOT_BACK) {
					sendPacket(SystemMessageId.YOUR_CLOAK_HAS_BEEN_UNEQUIPPED_BECAUSE_YOUR_ARMOR_SET_IS_NO_LONGER_COMPLETE);
					return;
				}

				if (equippedItem.getEnchantLevel() > 0) {
					sm = SystemMessage.getSystemMessage(SystemMessageId.THE_EQUIPMENT_S1_S2_HAS_BEEN_REMOVED);
					sm.addInt(equippedItem.getEnchantLevel());
					sm.addItemName(equippedItem);
				} else {
					sm = SystemMessage.getSystemMessage(SystemMessageId.S1_HAS_BEEN_UNEQUIPPED);
					sm.addItemName(equippedItem);
				}
				sendPacket(sm);
			}
		}
	}

	public void addTransformSkill(Skill skill) {
		if (_transformSkills == null) {
			synchronized (this) {
				if (_transformSkills == null) {
					_transformSkills = new HashMap<>();
				}
			}
		}
		_transformSkills.put(skill.getId(), skill);
	}

	public boolean hasTransformSkill(Skill skill) {
		if (checkTransformed(Transform::allowAllSkills)) {
			return true;
		}

		return (_transformSkills != null) && (_transformSkills.get(skill.getId()) == skill);
	}

	public boolean hasTransformSkills() {
		return (_transformSkills != null);
	}

	public Collection<Skill> getAllTransformSkills() {
		final Map<Integer, Skill> transformSkills = _transformSkills;
		return transformSkills != null ? transformSkills.values() : Collections.emptyList();
	}

	public synchronized void removeAllTransformSkills() {
		_transformSkills = null;
	}

	/**
	 * @param skillId the id of the skill that this player might have.
	 * @return {@code skill} object refered to this skill id that this player has, {@code null} otherwise.
	 */
	@Override
	public final Skill getKnownSkill(int skillId) {
		final Map<Integer, Skill> transformSkills = _transformSkills;
		return transformSkills != null ? transformSkills.getOrDefault(skillId, super.getKnownSkill(skillId)) : super.getKnownSkill(skillId);
	}

	/**
	 * @return all visible skills that appear on Alt+K for this player.
	 */
	public Collection<Skill> getSkillList() {
		Collection<Skill> currentSkills = getAllSkills();

		if (isTransformed()) {
			final Map<Integer, Skill> transformSkills = _transformSkills;
			if (transformSkills != null) {
				if (checkTransformed(Transform::allowAllSkills)) {
					// Include all skills and transformation skills.
					currentSkills.addAll(transformSkills.values());
				} else {
					// Include transformation skills and those skills that are allowed during transformation.
					currentSkills = currentSkills.stream().filter(Skill::allowOnTransform).collect(Collectors.toList());
					currentSkills.addAll(transformSkills.values());
				}
			}
		}

		//@formatter:off
		return currentSkills.stream()
				.filter(Objects::nonNull)
				.filter(s -> !s.isBlockActionUseSkill()) // Skills that are blocked from player use are not shown in skill list.
				.filter(s -> !SkillTreesData.getInstance().isAlchemySkill(s.getId(), s.getLevel()))
				.filter(s -> s.isDisplayInList())
				.collect(Collectors.toList());
		//@formatter:on
	}

	protected void startFeed(int npcId) {
		_canFeed = npcId > 0;
		if (!isMounted()) {
			return;
		}
		if (hasPet()) {
			final Summon pet = getPet();
			setCurrentFeed(((PetInstance) pet).getCurrentFed());
			_controlItemId = pet.getControlObjectId();
			sendPacket(new SetupGauge(3, (getCurrentFeed() * 10000) / getFeedConsume(), (getMaxFeed() * 10000) / getFeedConsume()));
			if (!isDead()) {
				_mountFeedTask = ThreadPool.getInstance().scheduleGeneralAtFixedRate(new PetFeedTask(this), 10000, 10000, TimeUnit.MILLISECONDS);
			}
		} else if (_canFeed) {
			setCurrentFeed(getMaxFeed());
			SetupGauge sg = new SetupGauge(3, (getCurrentFeed() * 10000) / getFeedConsume(), (getMaxFeed() * 10000) / getFeedConsume());
			sendPacket(sg);
			if (!isDead()) {
				_mountFeedTask = ThreadPool.getInstance().scheduleGeneralAtFixedRate(new PetFeedTask(this), 10000, 10000, TimeUnit.MILLISECONDS);
			}
		}
	}

	public void stopFeed() {
		if (_mountFeedTask != null) {
			_mountFeedTask.cancel(false);
			_mountFeedTask = null;
		}
	}

	private void clearPetData() {
		_data = null;
	}

	public final PetData getPetData(int npcId) {
		if (_data == null) {
			_data = PetDataTable.getInstance().getPetData(npcId);
		}
		return _data;
	}

	private PetLevelData getPetLevelData(int npcId) {
		if (_leveldata == null) {
			_leveldata = PetDataTable.getInstance().getPetData(npcId).getPetLevelData(getMountLevel());
		}
		return _leveldata;
	}

	public int getCurrentFeed() {
		return _curFeed;
	}

	public int getFeedConsume() {
		// if pet is attacking
		if (isAttackingNow()) {
			return getPetLevelData(_mountNpcId).getPetFeedBattle();
		}
		return getPetLevelData(_mountNpcId).getPetFeedNormal();
	}

	public void setCurrentFeed(int num) {
		boolean lastHungryState = isHungry();
		_curFeed = num > getMaxFeed() ? getMaxFeed() : num;
		SetupGauge sg = new SetupGauge(3, (getCurrentFeed() * 10000) / getFeedConsume(), (getMaxFeed() * 10000) / getFeedConsume());
		sendPacket(sg);
		// broadcast move speed change when strider becomes hungry / full
		if (lastHungryState != isHungry()) {
			broadcastUserInfo();
		}
	}

	private int getMaxFeed() {
		return getPetLevelData(_mountNpcId).getPetMaxFeed();
	}

	public boolean isHungry() {
		return hasPet() && _canFeed && (getCurrentFeed() < ((getPetData(getMountNpcId()).getPetLevelData(getPet().getLevel()).getHungryLimit() / 100f) * getPetLevelData(getMountNpcId()).getPetMaxFeed()));
	}

	public void enteredNoLanding(int delay) {
		_dismountTask = ThreadPool.getInstance().scheduleGeneral(new DismountTask(this), delay * 1000, TimeUnit.MILLISECONDS);
	}

	public void exitedNoLanding() {
		if (_dismountTask != null) {
			_dismountTask.cancel(true);
			_dismountTask = null;
		}
	}

	public void storePetFood(int petId) {
		if ((_controlItemId != 0) && (petId != 0)) {
			String req;
			req = "UPDATE pets SET fed=? WHERE item_obj_id = ?";
			try (Connection con = DatabaseFactory.getInstance().getConnection();
				 PreparedStatement statement = con.prepareStatement(req)) {
				statement.setInt(1, getCurrentFeed());
				statement.setInt(2, _controlItemId);
				statement.executeUpdate();
				_controlItemId = 0;
			} catch (Exception e) {
				LOGGER.error("Failed to store Pet [NpcId: " + petId + "] data", e);
			}
		}
	}

	public void setIsInSiege(boolean b) {
		_isInSiege = b;
	}

	public boolean isInSiege() {
		return _isInSiege;
	}

	/**
	 * @param isInHideoutSiege sets the value of {@link #_isInHideoutSiege}.
	 */
	public void setIsInHideoutSiege(boolean isInHideoutSiege) {
		_isInHideoutSiege = isInHideoutSiege;
	}

	/**
	 * @return the value of {@link #_isInHideoutSiege}, {@code true} if the player is participing on a Hideout Siege, otherwise {@code false}.
	 */
	public boolean isInHideoutSiege() {
		return _isInHideoutSiege;
	}

	public FloodProtectors getFloodProtectors() {
		return getClient().getFloodProtectors();
	}

	public boolean isFlyingMounted() {
		return checkTransformed(Transform::isFlying);
	}

	/**
	 * Returns the Number of Charges this L2PcInstance got.
	 *
	 * @return
	 */
	public int getCharges() {
		return _charges.get();
	}

	public void setCharges(int count) {
		restartChargeTask();
		_charges.set(count);
	}

	public boolean decreaseCharges(int count) {
		if (_charges.get() < count) {
			return false;
		}

		// Charge clear task should be reset every time a charge is decreased and stopped when charges become 0.
		if (_charges.addAndGet(-count) == 0) {
			stopChargeTask();
		} else {
			restartChargeTask();
		}

		sendPacket(new EtcStatusUpdate(this));
		return true;
	}

	public void clearCharges() {
		_charges.set(0);
		sendPacket(new EtcStatusUpdate(this));
	}

	/**
	 * Starts/Restarts the ChargeTask to Clear Charges after 10 Mins.
	 */
	private void restartChargeTask() {
		if (_chargeTask != null) {
			_chargeTask.cancel(false);
			_chargeTask = null;
		}
		_chargeTask = ThreadPool.getInstance().scheduleGeneral(new ResetChargesTask(this), 600000, TimeUnit.MILLISECONDS);
	}

	/**
	 * Stops the Charges Clearing Task.
	 */
	public void stopChargeTask() {
		if (_chargeTask != null) {
			_chargeTask.cancel(false);
			_chargeTask = null;
		}
	}

	public void teleportBookmarkModify(int id, int icon, String tag, String name) {
		final TeleportBookmark bookmark = _tpbookmarks.get(id);
		if (bookmark != null) {
			bookmark.setIcon(icon);
			bookmark.setTag(tag);
			bookmark.setName(name);

			try (Connection con = DatabaseFactory.getInstance().getConnection();
				 PreparedStatement statement = con.prepareStatement(UPDATE_TP_BOOKMARK)) {
				statement.setInt(1, icon);
				statement.setString(2, tag);
				statement.setString(3, name);
				statement.setInt(4, getObjectId());
				statement.setInt(5, id);
				statement.execute();
			} catch (Exception e) {
				LOGGER.warn("Could not update character teleport bookmark data: " + e.getMessage(), e);
			}
		}

		sendPacket(new ExGetBookMarkInfoPacket(this));
	}

	public void teleportBookmarkDelete(int id) {
		if (_tpbookmarks.remove(id) != null) {
			try (Connection con = DatabaseFactory.getInstance().getConnection();
				 PreparedStatement statement = con.prepareStatement(DELETE_TP_BOOKMARK)) {
				statement.setInt(1, getObjectId());
				statement.setInt(2, id);
				statement.execute();
			} catch (Exception e) {
				LOGGER.warn("Could not delete character teleport bookmark data: " + e.getMessage(), e);
			}

			sendPacket(new ExGetBookMarkInfoPacket(this));
		}
	}

	public void teleportBookmarkGo(int id) {
		if (!teleportBookmarkCondition(0)) {
			return;
		}
		if (getInventory().getInventoryItemCount(13016, 0) == 0) {
			sendPacket(SystemMessageId.YOU_CANNOT_TELEPORT_BECAUSE_YOU_DO_NOT_HAVE_A_TELEPORT_ITEM);
			return;
		}
		SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_DISAPPEARED);
		sm.addItemName(13016);
		sendPacket(sm);

		final TeleportBookmark bookmark = _tpbookmarks.get(id);
		if (bookmark != null) {
			destroyItem("Consume", getInventory().getItemByItemId(13016).getObjectId(), 1, null, false);
			teleToLocation(bookmark, false);
		}
		sendPacket(new ExGetBookMarkInfoPacket(this));
	}

	public boolean teleportBookmarkCondition(int type) {
		if (isInCombat()) {
			sendPacket(SystemMessageId.YOU_CANNOT_USE_MY_TELEPORTS_DURING_A_BATTLE);
			return false;
		} else if (isInSiege() || (getSiegeState() != 0)) {
			sendPacket(SystemMessageId.YOU_CANNOT_USE_MY_TELEPORTS_WHILE_PARTICIPATING_A_LARGE_SCALE_BATTLE_SUCH_AS_A_CASTLE_SIEGE_FORTRESS_SIEGE_OR_CLAN_HALL_SIEGE);
			return false;
		} else if (isInDuel()) {
			sendPacket(SystemMessageId.YOU_CANNOT_USE_MY_TELEPORTS_DURING_A_DUEL);
			return false;
		} else if (isFlying()) {
			sendPacket(SystemMessageId.YOU_CANNOT_USE_MY_TELEPORTS_WHILE_FLYING);
			return false;
		} else if (isInOlympiadMode()) {
			sendPacket(SystemMessageId.YOU_CANNOT_USE_MY_TELEPORTS_WHILE_PARTICIPATING_IN_AN_OLYMPIAD_MATCH);
			return false;
		} else if (hasBlockActions() && hasAbnormalType(AbnormalType.PARALYZE)) {
			sendPacket(SystemMessageId.YOU_CANNOT_USE_MY_TELEPORTS_WHILE_YOU_ARE_IN_A_PETRIFIED_OR_PARALYZED_STATE);
			return false;
		} else if (isDead()) {
			sendPacket(SystemMessageId.YOU_CANNOT_USE_MY_TELEPORTS_WHILE_YOU_ARE_DEAD);
			return false;
		} else if (isInWater()) {
			sendPacket(SystemMessageId.YOU_CANNOT_USE_MY_TELEPORTS_UNDERWATER);
			return false;
		} else if ((type == 1) && (isInsideZone(ZoneId.SIEGE) || isInsideZone(ZoneId.CLAN_HALL) || isInsideZone(ZoneId.JAIL) || isInsideZone(ZoneId.CASTLE) || isInsideZone(ZoneId.NO_SUMMON_FRIEND) || isInsideZone(ZoneId.FORT))) {
			sendPacket(SystemMessageId.YOU_CANNOT_USE_MY_TELEPORTS_TO_REACH_THIS_AREA);
			return false;
		} else if (isInsideZone(ZoneId.NO_BOOKMARK) || isInBoat() || isInAirShip()) {
			if (type == 0) {
				sendPacket(SystemMessageId.YOU_CANNOT_USE_MY_TELEPORTS_IN_THIS_AREA);
			} else if (type == 1) {
				sendPacket(SystemMessageId.YOU_CANNOT_USE_MY_TELEPORTS_TO_REACH_THIS_AREA);
			}
			return false;
		}
		/*
		 * TODO: Instant Zone still not implemented else if (isInsideZone(ZoneId.INSTANT)) { sendPacket(SystemMessage.getSystemMessage(2357)); return; }
		 */
		else {
			return true;
		}
	}

	public void teleportBookmarkAdd(int x, int y, int z, int icon, String tag, String name) {
		if (!teleportBookmarkCondition(1)) {
			return;
		}

		if (getInventory().getInventoryItemCount(20033, 0) == 0) {
			sendPacket(SystemMessageId.YOU_CANNOT_BOOKMARK_THIS_LOCATION_BECAUSE_YOU_DO_NOT_HAVE_A_MY_TELEPORT_FLAG);
			return;
		}

		if (_tpbookmarks.size() >= _bookmarkslot) {
			sendPacket(SystemMessageId.YOU_HAVE_NO_SPACE_TO_SAVE_THE_TELEPORT_LOCATION);
			return;
		}

		int id;
		for (id = 1; id <= _bookmarkslot; ++id) {
			if (!_tpbookmarks.containsKey(id)) {
				break;
			}
		}
		_tpbookmarks.put(id, new TeleportBookmark(id, x, y, z, icon, tag, name));

		destroyItem("Consume", getInventory().getItemByItemId(20033).getObjectId(), 1, null, false);

		SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_DISAPPEARED);
		sm.addItemName(20033);
		sendPacket(sm);

		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement(INSERT_TP_BOOKMARK)) {
			statement.setInt(1, getObjectId());
			statement.setInt(2, id);
			statement.setInt(3, x);
			statement.setInt(4, y);
			statement.setInt(5, z);
			statement.setInt(6, icon);
			statement.setString(7, tag);
			statement.setString(8, name);
			statement.execute();
		} catch (Exception e) {
			LOGGER.warn("Could not insert character teleport bookmark data: " + e.getMessage(), e);
		}
		sendPacket(new ExGetBookMarkInfoPacket(this));
	}

	public void restoreTeleportBookmark() {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement(RESTORE_TP_BOOKMARK)) {
			statement.setInt(1, getObjectId());
			try (ResultSet rset = statement.executeQuery()) {
				while (rset.next()) {
					_tpbookmarks.put(rset.getInt("Id"), new TeleportBookmark(rset.getInt("Id"), rset.getInt("x"), rset.getInt("y"), rset.getInt("z"), rset.getInt("icon"), rset.getString("tag"), rset.getString("name")));
				}
			}
		} catch (Exception e) {
			LOGGER.error("Failed restoing character teleport bookmark.", e);
		}
	}

	@Override
	public void sendInfo(Player activeChar) {
		if (isInBoat()) {
			setXYZ(getBoat().getLocation());

			activeChar.sendPacket(new CharInfo(this, isInvisible() && activeChar.canOverrideCond(PcCondOverride.SEE_ALL_PLAYERS)));
			activeChar.sendPacket(new GetOnVehicle(getObjectId(), getBoat().getObjectId(), getInVehiclePosition()));
		} else if (isInAirShip()) {
			setXYZ(getAirShip().getLocation());
			activeChar.sendPacket(new CharInfo(this, isInvisible() && activeChar.canOverrideCond(PcCondOverride.SEE_ALL_PLAYERS)));
			activeChar.sendPacket(new ExGetOnAirShip(this, getAirShip()));
		} else {
			activeChar.sendPacket(new CharInfo(this, isInvisible() && activeChar.canOverrideCond(PcCondOverride.SEE_ALL_PLAYERS)));
		}

		sendRelationChanged(activeChar);
		activeChar.sendRelationChanged(this);

		switch (getPrivateStoreType()) {
			case SELL:
				activeChar.sendPacket(new PrivateStoreMsgSell(this));
				break;
			case PACKAGE_SELL:
				activeChar.sendPacket(new ExPrivateStoreSetWholeMsg(this));
				break;
			case BUY:
				activeChar.sendPacket(new PrivateStoreMsgBuy(this));
				break;
			case MANUFACTURE:
				activeChar.sendPacket(new RecipeShopMsg(this));
				break;
		}
	}

	public void playMovie(MovieHolder holder) {
		if (getMovieHolder() != null) {
			return;
		}
		abortAttack();
		// abortCast(); Confirmed in retail, playing a movie does not abort cast.
		stopMove(null);
		setMovieHolder(holder);
		sendPacket(new ExStartScenePlayer(holder.getMovie()));
	}

	public void stopMovie() {
		sendPacket(new ExStopScenePlayer(getMovieHolder().getMovie()));
		setMovieHolder(null);
	}

	public boolean isAllowedToEnchantSkills() {
		if (isLocked()) {
			return false;
		}
		if (isTransformed()) {
			return false;
		}
		if (AttackStanceTaskManager.getInstance().hasAttackStanceTask(this)) {
			return false;
		}
		if (isCastingNow()) {
			return false;
		}
		if (isInBoat() || isInAirShip()) {
			return false;
		}
		return true;
	}

	/**
	 * Set the _createDate of the L2PcInstance.
	 *
	 * @param createDate
	 */
	public void setCreateDate(Calendar createDate) {
		_createDate = createDate;
	}

	/**
	 * @return the _createDate of the L2PcInstance.
	 */
	public Calendar getCreateDate() {
		return _createDate;
	}

	/**
	 * @return number of days to char birthday.
	 */
	public int checkBirthDay() {
		Calendar now = Calendar.getInstance();

		// "Characters with a February 29 creation date will receive a gift on February 28."
		if ((_createDate.get(Calendar.DAY_OF_MONTH) == 29) && (_createDate.get(Calendar.MONTH) == 1)) {
			_createDate.add(Calendar.HOUR_OF_DAY, -24);
		}

		if ((now.get(Calendar.MONTH) == _createDate.get(Calendar.MONTH)) && (now.get(Calendar.DAY_OF_MONTH) == _createDate.get(Calendar.DAY_OF_MONTH)) && (now.get(Calendar.YEAR) != _createDate.get(Calendar.YEAR))) {
			return 0;
		}

		int i;
		for (i = 1; i < 6; i++) {
			now.add(Calendar.HOUR_OF_DAY, 24);
			if ((now.get(Calendar.MONTH) == _createDate.get(Calendar.MONTH)) && (now.get(Calendar.DAY_OF_MONTH) == _createDate.get(Calendar.DAY_OF_MONTH)) && (now.get(Calendar.YEAR) != _createDate.get(Calendar.YEAR))) {
				return i;
			}
		}
		return -1;
	}

	public int getBirthdays() {
		long time = (System.currentTimeMillis() - getCreateDate().getTimeInMillis()) / 1000;
		time /= TimeUnit.DAYS.toMillis(365);
		return (int) time;
	}

	/**
	 * list of character friends
	 */
	private final Set<Integer> _friendList = ConcurrentHashMap.newKeySet();

	public Set<Integer> getFriendList() {
		return _friendList;
	}

	public void restoreFriendList() {
		_friendList.clear();

		final String sqlQuery = "SELECT friendId FROM character_friends WHERE charId=? AND relation=0";
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement(sqlQuery)) {
			statement.setInt(1, getObjectId());
			try (ResultSet rset = statement.executeQuery()) {
				while (rset.next()) {
					int friendId = rset.getInt("friendId");
					if (friendId == getObjectId()) {
						continue;
					}
					_friendList.add(friendId);
				}
			}
		} catch (Exception e) {
			LOGGER.warn("Error found in " + getName() + "'s FriendList: " + e.getMessage(), e);
		}
	}

	public void notifyFriends(int type) {
		if (type == L2FriendStatus.MODE_ONLINE) {
			CreatureSay cs = new CreatureSay(0, ChatType.NPC_WHISPER, "", SystemMessageId.YOUR_FRIEND_S1_JUST_LOGGED_IN);
			cs.addStringParameter(getName());
			getFriendList().stream().map(WorldManager.getInstance()::getPlayer).filter(Objects::nonNull).forEach(cs::sendTo);
		} else if (type == L2FriendStatus.MODE_OFFLINE) {
			CreatureSay cs = new CreatureSay(0, ChatType.NPC_WHISPER, "", SystemMessageId.YOUR_FRIEND_S1_JUST_LOGGED_OUT);
			cs.addStringParameter(getName());
			getFriendList().stream().map(WorldManager.getInstance()::getPlayer).filter(Objects::nonNull).forEach(cs::sendTo);
		}

		L2FriendStatus pkt = new L2FriendStatus(this, type);
		getFriendList().stream().map(WorldManager.getInstance()::getPlayer).filter(Objects::nonNull).forEach(pkt::sendTo);
	}

	/**
	 * Verify if this player is in silence mode.
	 *
	 * @return the {@code true} if this player is in silence mode, {@code false} otherwise
	 */
	public boolean isSilenceMode() {
		return _silenceMode;
	}

	/**
	 * While at silenceMode, checks if this player blocks PMs for this user
	 *
	 * @param playerObjId the player object Id
	 * @return {@code true} if the given Id is not excluded and this player is in silence mode, {@code false} otherwise
	 */
	public boolean isSilenceMode(int playerObjId) {
		if (PlayerConfig.SILENCE_MODE_EXCLUDE && _silenceMode && (_silenceModeExcluded != null)) {
			return !_silenceModeExcluded.contains(playerObjId);
		}
		return _silenceMode;
	}

	/**
	 * Set the silence mode.
	 *
	 * @param mode the value
	 */
	public void setSilenceMode(boolean mode) {
		_silenceMode = mode;
		if (_silenceModeExcluded != null) {
			_silenceModeExcluded.clear(); // Clear the excluded list on each setSilenceMode
		}
		sendPacket(new EtcStatusUpdate(this));
	}

	/**
	 * Add a player to the "excluded silence mode" list.
	 *
	 * @param playerObjId the player's object Id
	 */
	public void addSilenceModeExcluded(int playerObjId) {
		if (_silenceModeExcluded == null) {
			_silenceModeExcluded = new ArrayList<>(1);
		}
		_silenceModeExcluded.add(playerObjId);
	}

	private void storeRecipeShopList() {
		if (hasManufactureShop()) {
			try (Connection con = DatabaseFactory.getInstance().getConnection()) {
				try (PreparedStatement st = con.prepareStatement(DELETE_CHAR_RECIPE_SHOP)) {
					st.setInt(1, getObjectId());
					st.execute();
				}

				try (PreparedStatement st = con.prepareStatement(INSERT_CHAR_RECIPE_SHOP)) {
					AtomicInteger slot = new AtomicInteger(1);
					con.setAutoCommit(false);
					for (Entry<Integer, Long> entry : _manufactureItems.entrySet()) {
						st.setInt(1, getObjectId());
						st.setInt(2, entry.getKey());
						st.setLong(3, entry.getValue());
						st.setInt(4, slot.getAndIncrement());
						st.addBatch();
					}
					st.executeBatch();
					con.commit();
				}
			} catch (Exception e) {
				LOGGER.error("Could not store recipe shop for playerId " + getObjectId() + ": ", e);
			}
		}
	}

	private void restoreRecipeShopList() {
		final Map<Integer, Long> manufactureItems = new HashMap<>();

		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement(RESTORE_CHAR_RECIPE_SHOP)) {
			statement.setInt(1, getObjectId());
			try (ResultSet rset = statement.executeQuery()) {
				while (rset.next()) {
					manufactureItems.put(rset.getInt("recipeId"), rset.getLong("price"));
				}
			}
		} catch (Exception e) {
			LOGGER.error("Could not restore recipe shop list data for playerId: " + getObjectId(), e);
		}

		_manufactureItems = manufactureItems;
	}

	@Override
	public double getCollisionRadius() {
		if (isMounted() && (getMountNpcId() > 0)) {
			return NpcData.getInstance().getTemplate(getMountNpcId()).getfCollisionRadius();
		}

		final double defaultCollisionRadius = getAppearance().getSex() ? getBaseTemplate().getFCollisionRadiusFemale() : getBaseTemplate().getfCollisionRadius();
		return getTransformation().map(transform -> transform.getCollisionRadius(this, defaultCollisionRadius)).orElse(defaultCollisionRadius);
	}

	@Override
	public double getCollisionHeight() {
		if (isMounted() && (getMountNpcId() > 0)) {
			return NpcData.getInstance().getTemplate(getMountNpcId()).getfCollisionHeight();
		}

		final double defaultCollisionHeight = getAppearance().getSex() ? getBaseTemplate().getFCollisionHeightFemale() : getBaseTemplate().getfCollisionHeight();
		return getTransformation().map(transform -> transform.getCollisionHeight(this, defaultCollisionHeight)).orElse(defaultCollisionHeight);
	}

	public final int getClientX() {
		return _clientX;
	}

	public final int getClientY() {
		return _clientY;
	}

	public final int getClientZ() {
		return _clientZ;
	}

	public final int getClientHeading() {
		return _clientHeading;
	}

	public final void setClientX(int val) {
		_clientX = val;
	}

	public final void setClientY(int val) {
		_clientY = val;
	}

	public final void setClientZ(int val) {
		_clientZ = val;
	}

	public final void setClientHeading(int val) {
		_clientHeading = val;
	}

	/**
	 * @param z
	 * @return true if character falling now on the start of fall return false for correct coord sync!
	 */
	public final boolean isFalling(int z) {
		if (isDead() || isFlying() || isFlyingMounted() || isInsideZone(ZoneId.WATER)) {
			return false;
		}

		if (System.currentTimeMillis() < _fallingTimestamp) {
			return true;
		}

		final double deltaZ = getZ() - z;
		if (deltaZ <= getBaseTemplate().getSafeFallHeight()) {
			return false;
		}

		// If there is no geodata loaded for the place we are client Z correction might cause falling damage.
		if (!GeoData.getInstance().hasGeo(getX(), getY())) {
			return false;
		}

		final int damage = (int) Formulas.calcFallDam(this, deltaZ);
		if (damage > 0) {
			reduceCurrentHp(Math.min(damage, getCurrentHp() - 1), this, null, false, true, false, false);
			SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_RECEIVED_S1_FALLING_DAMAGE);
			sm.addInt(damage);
			sendPacket(sm);
		}

		setFalling();

		return false;
	}

	/**
	 * Set falling timestamp
	 */
	public final void setFalling() {
		_fallingTimestamp = System.currentTimeMillis() + FALLING_VALIDATION_DELAY;
	}

	/**
	 * @return the _movie
	 */
	public MovieHolder getMovieHolder() {
		return _movieHolder;
	}

	public void setMovieHolder(MovieHolder movie) {
		_movieHolder = movie;
	}

	/**
	 * Update last item auction request timestamp to current
	 */
	public void updateLastItemAuctionRequest() {
		_lastItemAuctionInfoRequest = System.currentTimeMillis();
	}

	/**
	 * @return true if receiving item auction requests<br>
	 * (last request was in 2 seconds before)
	 */
	public boolean isItemAuctionPolling() {
		return (System.currentTimeMillis() - _lastItemAuctionInfoRequest) < 2000;
	}

	@Override
	public boolean isMovementDisabled() {
		return super.isMovementDisabled() || (getMovieHolder() != null) || _fishing.isFishing();
	}

	private void restoreUISettings() {
		_uiKeySettings = new UIKeysSettings(getObjectId());
	}

	private void storeUISettings() {
		if (_uiKeySettings == null) {
			return;
		}

		if (!_uiKeySettings.isSaved()) {
			_uiKeySettings.saveInDB();
		}
	}

	public UIKeysSettings getUISettings() {
		return _uiKeySettings;
	}

	public Language getLang() {
		return getVariables().getEnum("language", Language.class, Language.ENGLISH);
	}

	public void setLang(Language lang) {
		getVariables().set("language", lang);
	}

	public long getOfflineStartTime() {
		return _offlineShopStart;
	}

	public void setOfflineStartTime(long time) {
		_offlineShopStart = time;
	}

	/**
	 * Check all player skills for skill level. If player level is lower than skill learn level - 9, skill level is decreased to next possible level.
	 */
	public void checkPlayerSkills() {
		SkillLearn learn;
		for (Entry<Integer, Skill> e : getSkills().entrySet()) {
			learn = SkillTreesData.getInstance().getClassSkill(e.getKey(), e.getValue().getLevel() % 100, getClassId());
			if (learn != null) {
				int lvlDiff = e.getKey() == CommonSkill.EXPERTISE.getId() ? 0 : 9;
				if (getLevel() < (learn.getGetLevel() - lvlDiff)) {
					deacreaseSkillLevel(e.getValue(), lvlDiff);
				}
			}
		}
	}

	private void deacreaseSkillLevel(Skill skill, int lvlDiff) {
		int nextLevel = -1;
		final Map<Long, SkillLearn> skillTree = SkillTreesData.getInstance().getCompleteClassSkillTree(getClassId());
		for (SkillLearn sl : skillTree.values()) {
			if ((sl.getSkillId() == skill.getId()) && (nextLevel < sl.getSkillLevel()) && (getLevel() >= (sl.getGetLevel() - lvlDiff))) {
				nextLevel = sl.getSkillLevel(); // next possible skill level
			}
		}

		if (nextLevel == -1) {
			LOGGER.info("Removing skill " + skill + " from player " + toString());
			removeSkill(skill, true); // there is no lower skill
		} else {
			LOGGER.info("Decreasing skill " + skill + " to " + nextLevel + " for player " + toString());
			addSkill(SkillData.getInstance().getSkill(skill.getId(), nextLevel), true); // replace with lower one
		}
	}

	public boolean canMakeSocialAction() {
		return ((getPrivateStoreType() == PrivateStoreType.NONE) && (getActiveRequester() == null) && !isAlikeDead() && !isAllSkillsDisabled() && !isCastingNow() && (getAI().getIntention() == CtrlIntention.AI_INTENTION_IDLE));
	}

	public void setMultiSocialAction(int id, int targetId) {
		_multiSociaAction = id;
		_multiSocialTarget = targetId;
	}

	public int getMultiSociaAction() {
		return _multiSociaAction;
	}

	public int getMultiSocialTarget() {
		return _multiSocialTarget;
	}

	public Collection<TeleportBookmark> getTeleportBookmarks() {
		return _tpbookmarks.values();
	}

	public int getBookmarkslot() {
		return _bookmarkslot;
	}

	/**
	 * @return
	 */
	public int getQuestInventoryLimit() {
		return PlayerConfig.INVENTORY_MAXIMUM_QUEST_ITEMS;
	}

	public boolean canAttackCharacter(Creature cha) {
		if (cha.isAttackable()) {
			return true;
		} else if (cha.isPlayable()) {
			if (cha.isInsideZone(ZoneId.PVP) && !cha.isInsideZone(ZoneId.SIEGE)) {
				return true;
			}

			final Player target = cha.isSummon() ? ((Summon) cha).getOwner() : (Player) cha;

			if (isInDuel() && target.isInDuel() && (target.getDuelId() == getDuelId())) {
				return true;
			} else if (isInParty() && target.isInParty()) {
				if (getParty() == target.getParty()) {
					return false;
				}
				if (((getParty().getCommandChannel() != null) || (target.getParty().getCommandChannel() != null)) && (getParty().getCommandChannel() == target.getParty().getCommandChannel())) {
					return false;
				}
			} else if ((getClan() != null) && (target.getClan() != null)) {
				if (getClanId() == target.getClanId()) {
					return false;
				}
				if (((getAllyId() > 0) || (target.getAllyId() > 0)) && (getAllyId() == target.getAllyId())) {
					return false;
				}
				if (getClan().isAtWarWith(target.getClan().getId()) && target.getClan().isAtWarWith(getClan().getId())) {
					return true;
				}
			} else if ((getClan() == null) || (target.getClan() == null)) {
				if ((target.getPvpFlag() == 0) && (target.getReputation() >= 0)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Test if player inventory is under 90% capaity
	 *
	 * @param includeQuestInv check also quest inventory
	 * @return
	 */
	public boolean isInventoryUnder90(boolean includeQuestInv) {
		return (getInventory().getSize(item -> !item.isQuestItem() || includeQuestInv) <= (getInventoryLimit() * 0.9));
	}

	/**
	 * Test if player inventory is under 80% capaity
	 *
	 * @param includeQuestInv check also quest inventory
	 * @return
	 */
	public boolean isInventoryUnder80(boolean includeQuestInv) {
		return (getInventory().getSize(item -> !item.isQuestItem() || includeQuestInv) <= (getInventoryLimit() * 0.8));
	}

	public boolean havePetInvItems() {
		return _petItems;
	}

	public void setPetInvItems(boolean haveit) {
		_petItems = haveit;
	}

	/**
	 * Restore Pet's inventory items from database.
	 */
	private void restorePetInventoryItems() {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement("SELECT object_id FROM `items` WHERE `owner_id`=? AND (`loc`='PET' OR `loc`='PET_EQUIP') LIMIT 1;")) {
			statement.setInt(1, getObjectId());
			try (ResultSet rset = statement.executeQuery()) {
				setPetInvItems(rset.next() && (rset.getInt("object_id") > 0));
			}
		} catch (Exception e) {
			LOGGER.error("Could not check Items in Pet Inventory for playerId: " + getObjectId(), e);
		}
	}

	public String getAdminConfirmCmd() {
		return _adminConfirmCmd;
	}

	public void setAdminConfirmCmd(String adminConfirmCmd) {
		_adminConfirmCmd = adminConfirmCmd;
	}

	public void setBlockCheckerArena(byte arena) {
		_handysBlockCheckerEventArena = arena;
	}

	public int getBlockCheckerArena() {
		return _handysBlockCheckerEventArena;
	}

	/**
	 * Load L2PcInstance Recommendations data.
	 */
	private void loadRecommendations() {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement("SELECT rec_have, rec_left FROM character_reco_bonus WHERE charId = ?")) {
			statement.setInt(1, getObjectId());
			try (ResultSet rset = statement.executeQuery()) {
				if (rset.next()) {
					setRecomHave(rset.getInt("rec_have"));
					setRecomLeft(rset.getInt("rec_left"));
				}
			}
		} catch (Exception e) {
			LOGGER.error("Could not restore Recommendations for player: " + getObjectId(), e);
		}
	}

	/**
	 * Update L2PcInstance Recommendations data.
	 */
	public void storeRecommendations() {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement("INSERT INTO character_reco_bonus (charId,rec_have,rec_left,time_left) VALUES (?,?,?,?) ON DUPLICATE KEY UPDATE rec_have=?, rec_left=?, time_left=?")) {
			statement.setInt(1, getObjectId());
			statement.setInt(2, getRecomHave());
			statement.setInt(3, getRecomLeft());
			statement.setLong(4, 0);
			// Update part
			statement.setInt(5, getRecomHave());
			statement.setInt(6, getRecomLeft());
			statement.setLong(7, 0);
			statement.execute();
		} catch (Exception e) {
			LOGGER.error("Could not update Recommendations for player: " + getObjectId(), e);
		}
	}

	public void startRecoGiveTask() {
		// Create task to give new recommendations
		_recoGiveTask = ThreadPool.getInstance().scheduleGeneralAtFixedRate(new RecoGiveTask(this), 7200000, 3600000, TimeUnit.MILLISECONDS);

		// Store new data
		storeRecommendations();
	}

	public void stopRecoGiveTask() {
		if (_recoGiveTask != null) {
			_recoGiveTask.cancel(false);
			_recoGiveTask = null;
		}
	}

	public boolean isRecoTwoHoursGiven() {
		return _recoTwoHoursGiven;
	}

	public void setRecoTwoHoursGiven(boolean val) {
		_recoTwoHoursGiven = val;
	}

	public void setLastPetitionGmName(String gmName) {
		_lastPetitionGmName = gmName;
	}

	public String getLastPetitionGmName() {
		return _lastPetitionGmName;
	}

	public ContactList getContactList() {
		return _contactList;
	}

	public long getNotMoveUntil() {
		return _notMoveUntil;
	}

	public void updateNotMoveUntil() {
		_notMoveUntil = System.currentTimeMillis() + PlayerConfig.PLAYER_MOVEMENT_BLOCK_TIME;
	}

	@Override
	public boolean isPlayer() {
		return true;
	}

	@Override
	public Player asPlayer() {
		return this;
	}

	@Override
	public boolean isChargedShot(ShotType type) {
		final ItemInstance weapon = getActiveWeaponInstance();
		return (weapon != null) && weapon.isChargedShot(type);
	}

	@Override
	public void setChargedShot(ShotType type, boolean charged) {
		final ItemInstance weapon = getActiveWeaponInstance();
		if (weapon != null) {
			weapon.setChargedShot(type, charged);
		}
	}

	/**
	 * @param skillId the display skill Id
	 * @return the custom skill
	 */
	public final Skill getCustomSkill(int skillId) {
		return (_customSkills != null) ? _customSkills.get(skillId) : null;
	}

	/**
	 * Add a skill level to the custom skills map.
	 *
	 * @param skill the skill to add
	 */
	private void addCustomSkill(Skill skill) {
		if ((skill != null) && (skill.getDisplayId() != skill.getId())) {
			if (_customSkills == null) {
				_customSkills = new ConcurrentSkipListMap<>();
			}
			_customSkills.put(skill.getDisplayId(), skill);
		}
	}

	/**
	 * Remove a skill level from the custom skill map.
	 *
	 * @param skill the skill to remove
	 */
	private void removeCustomSkill(Skill skill) {
		if ((skill != null) && (_customSkills != null) && (skill.getDisplayId() != skill.getId())) {
			_customSkills.remove(skill.getDisplayId());
		}
	}

	/**
	 * @return {@code true} if current player can revive and shows 'To Village' button upon death, {@code false} otherwise.
	 */
	@Override
	public boolean canRevive() {
		if (_events != null) {
			for (AbstractEvent<?> listener : _events.values()) {
				if (listener.isOnEvent(this) && !listener.canRevive(this)) {
					return false;
				}
			}
		}
		return _canRevive;
	}

	/**
	 * This method can prevent from displaying 'To Village' button upon death.
	 *
	 * @param val
	 */
	@Override
	public void setCanRevive(boolean val) {
		_canRevive = val;
	}

	/**
	 * @return {@code true} if player is on event, {@code false} otherwise.
	 */
	@Override
	public boolean isOnEvent() {
		if (_events != null) {
			for (AbstractEvent<?> listener : _events.values()) {
				if (listener.isOnEvent(this)) {
					return true;
				}
			}
		}
		return super.isOnEvent();
	}

	public boolean isBlockedFromExit() {
		if (_events != null) {
			for (AbstractEvent<?> listener : _events.values()) {
				if (listener.isOnEvent(this) && listener.isBlockingExit(this)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isBlockedFromDeathPenalty() {
		if (_events != null) {
			for (AbstractEvent<?> listener : _events.values()) {
				if (listener.isOnEvent(this) && listener.isBlockingDeathPenalty(this)) {
					return true;
				}
			}
		}
		return getStat().has(BooleanStat.PROTECT_DEATH_PENALTY);
	}

	public void setOriginalCpHpMp(double cp, double hp, double mp) {
		_originalCp = cp;
		_originalHp = hp;
		_originalMp = mp;
	}

	@Override
	public void addOverrideCond(PcCondOverride... excs) {
		super.addOverrideCond(excs);
		getVariables().set(COND_OVERRIDE_KEY, Long.toString(_exceptions));
	}

	@Override
	public void removeOverridedCond(PcCondOverride... excs) {
		super.removeOverridedCond(excs);
		getVariables().set(COND_OVERRIDE_KEY, Long.toString(_exceptions));
	}

	/**
	 * @return {@code true} if {@link PlayerVariables} instance is attached to current player's scripts, {@code false} otherwise.
	 */
	public boolean hasVariables() {
		return getScript(PlayerVariables.class) != null;
	}

	/**
	 * @return {@link PlayerVariables} instance containing parameters regarding player.
	 */
	public PlayerVariables getVariables() {
		final PlayerVariables vars = getScript(PlayerVariables.class);
		return vars != null ? vars : addScript(new PlayerVariables(getObjectId()));
	}

	/**
	 * @return {@code true} if {@link AccountVariables} instance is attached to current player's scripts, {@code false} otherwise.
	 */
	public boolean hasAccountVariables() {
		return getScript(AccountVariables.class) != null;
	}

	/**
	 * @return {@link AccountVariables} instance containing parameters regarding player.
	 */
	public AccountVariables getAccountVariables() {
		final AccountVariables vars = getScript(AccountVariables.class);
		return vars != null ? vars : addScript(new AccountVariables(getAccountName()));
	}

	@Override
	public int getId() {
		return getClassId().getId();
	}

	public boolean isPartyBanned() {
		return PunishmentManager.getInstance().hasPunishment(getObjectId(), PunishmentAffect.CHARACTER, PunishmentType.PARTY_BAN);
	}

	/**
	 * @param act
	 * @return {@code true} if action was added successfully, {@code false} otherwise.
	 */
	public boolean addAction(PlayerAction act) {
		if (!hasAction(act)) {
			_actionMask |= act.getMask();
			return true;
		}
		return false;
	}

	/**
	 * @param act
	 * @return {@code true} if action was removed successfully, {@code false} otherwise.
	 */
	public boolean removeAction(PlayerAction act) {
		if (hasAction(act)) {
			_actionMask &= ~act.getMask();
			return true;
		}
		return false;
	}

	/**
	 * @param act
	 * @return {@code true} if action is present, {@code false} otherwise.
	 */
	public boolean hasAction(PlayerAction act) {
		return (_actionMask & act.getMask()) == act.getMask();
	}

	/**
	 * Set true/false if character got Charm of Courage
	 *
	 * @param val true/false
	 */
	public void setCharmOfCourage(boolean val) {
		_hasCharmOfCourage = val;

	}

	/**
	 * @return {@code true} if effect is present, {@code false} otherwise.
	 */
	public boolean hasCharmOfCourage() {
		return _hasCharmOfCourage;

	}

	/**
	 * @param target the target
	 * @return {@code true} if this player got war with the target, {@code false} otherwise.
	 */
	public boolean atWarWith(Playable target) {
		if (target == null) {
			return false;
		}
		if ((_clan != null) && !isAcademyMember()) // Current player
		{
			if ((target.getClan() != null) && !target.isAcademyMember()) // Target player
			{
				return _clan.isAtWarWith(target.getClan());
			}
		}
		return false;
	}

	/**
	 * Sets the beauty shop hair
	 *
	 * @param hairId
	 */
	public void setVisualHair(int hairId) {
		getVariables().set(PlayerVariables.VISUAL_HAIR_ID, hairId);
	}

	/**
	 * Sets the beauty shop hair color
	 *
	 * @param colorId
	 */
	public void setVisualHairColor(int colorId) {
		getVariables().set(PlayerVariables.VISUAL_HAIR_COLOR_ID, colorId);
	}

	/**
	 * Sets the beauty shop modified face
	 *
	 * @param faceId
	 */
	public void setVisualFace(int faceId) {
		getVariables().set(PlayerVariables.VISUAL_FACE_ID, faceId);
	}

	/**
	 * @return the beauty shop hair, or his normal if not changed.
	 */
	public int getVisualHair() {
		return getVariables().getInt(PlayerVariables.VISUAL_HAIR_ID, getAppearance().getHairStyle());
	}

	/**
	 * @return the beauty shop hair color, or his normal if not changed.
	 */
	public int getVisualHairColor() {
		return getVariables().getInt(PlayerVariables.VISUAL_HAIR_COLOR_ID, getAppearance().getHairColor());
	}

	/**
	 * @return the beauty shop modified face, or his normal if not changed.
	 */
	public int getVisualFace() {
		return getVariables().getInt(PlayerVariables.VISUAL_FACE_ID, getAppearance().getFace());
	}

	/**
	 * @return {@code true} if player has mentees, {@code false} otherwise
	 */
	public boolean isMentor() {
		return MentorManager.getInstance().isMentor(getObjectId());
	}

	/**
	 * @return {@code true} if player has mentor, {@code false} otherwise
	 */
	public boolean isMentee() {
		return MentorManager.getInstance().isMentee(getObjectId());
	}

	/**
	 * @return the amount of ability points player can spend on learning skills.
	 */
	public int getAbilityPoints() {
		return getVariables().getInt(isDualClassActive() ? PlayerVariables.ABILITY_POINTS_DUAL_CLASS : PlayerVariables.ABILITY_POINTS_MAIN_CLASS, 0);
	}

	/**
	 * Sets the amount of ability points player can spend on learning skills.
	 *
	 * @param points
	 */
	public void setAbilityPoints(int points) {
		getVariables().set(isDualClassActive() ? PlayerVariables.ABILITY_POINTS_DUAL_CLASS : PlayerVariables.ABILITY_POINTS_MAIN_CLASS, points);
	}

	/**
	 * @return how much ability points player has spend on learning skills.
	 */
	public int getAbilityPointsUsed() {
		return getVariables().getInt(isDualClassActive() ? PlayerVariables.ABILITY_POINTS_USED_DUAL_CLASS : PlayerVariables.ABILITY_POINTS_USED_MAIN_CLASS, 0);
	}

	/**
	 * Sets how much ability points player has spend on learning skills.
	 *
	 * @param points
	 */
	public void setAbilityPointsUsed(int points) {
		EventDispatcher.getInstance().notifyEventAsync(new OnPlayerAbilityPointsChanged(this, getAbilityPointsUsed(), points), this);
		getVariables().set(isDualClassActive() ? PlayerVariables.ABILITY_POINTS_USED_DUAL_CLASS : PlayerVariables.ABILITY_POINTS_USED_MAIN_CLASS, points);
	}

	/**
	 * @return The amount of times player can use world chat
	 */
	public int getWorldChatPoints() {
		return (int) getStat().getValue(DoubleStat.WORLD_CHAT_POINTS, GeneralConfig.WORLD_CHAT_POINTS_PER_DAY);
	}

	/**
	 * @return The amount of times player has used world chat
	 */
	public int getWorldChatUsed() {
		return getVariables().getInt(PlayerVariables.WORLD_CHAT_VARIABLE_NAME, 0);
	}

	/**
	 * Sets the amount of times player can use world chat
	 *
	 * @param timesUsed how many times world chat has been used up until now.
	 */
	public void setWorldChatUsed(int timesUsed) {
		getVariables().set(PlayerVariables.WORLD_CHAT_VARIABLE_NAME, timesUsed);
	}

	public void prohibiteCeremonyOfChaos() {
		if (!PunishmentManager.getInstance().hasPunishment(getObjectId(), PunishmentAffect.CHARACTER, PunishmentType.COC_BAN)) {
			PunishmentManager.getInstance().startPunishment(new PunishmentTask(getObjectId(), PunishmentAffect.CHARACTER, PunishmentType.COC_BAN, 0, "", getClass().getSimpleName()));
			final int penalties = getVariables().getInt(PlayerVariables.CEREMONY_OF_CHAOS_PROHIBITED_PENALTIES, 0);
			getVariables().set(PlayerVariables.CEREMONY_OF_CHAOS_PROHIBITED_PENALTIES, penalties + 1);
		}
	}

	public boolean isCeremonyOfChaosProhibited() {
		return PunishmentManager.getInstance().hasPunishment(getObjectId(), PunishmentAffect.CHARACTER, PunishmentType.COC_BAN) || (getVariables().getInt(PlayerVariables.CEREMONY_OF_CHAOS_PROHIBITED_PENALTIES, 0) >= 30);
	}

	/**
	 * @return Side of the player.
	 */
	public CastleSide getPlayerSide() {
		if ((getClan() == null) || (getClan().getCastleId() == 0)) {
			return CastleSide.NEUTRAL;
		}
		return CastleManager.getInstance().getCastleById(getClan().getCastleId()).getSide();
	}

	/**
	 * @return {@code true} if player is on Dark side, {@code false} otherwise.
	 */
	public boolean isOnDarkSide() {
		return getPlayerSide() == CastleSide.DARK;
	}

	/**
	 * @return {@code true} if player is on Light side, {@code false} otherwise.
	 */
	public boolean isOnLightSide() {
		return getPlayerSide() == CastleSide.LIGHT;
	}

	/**
	 * @return the maximum amount of points that player can use
	 */
	public int getMaxSummonPoints() {
		return (int) getStat().getValue(DoubleStat.MAX_SUMMON_POINTS, 0);
	}

	/**
	 * @return the amount of points that player used
	 */
	public int getSummonPoints() {
		return getServitors().values().stream().mapToInt(Summon::getSummonPoints).sum();
	}

	/**
	 * @param request
	 * @return {@code true} if the request was registered successfully, {@code false} otherwise.
	 */
	public boolean addRequest(AbstractRequest request) {
		if (_requests == null) {
			synchronized (this) {
				if (_requests == null) {
					_requests = new ConcurrentHashMap<>();
				}
			}
		}
		return canRequest(request) && (_requests.putIfAbsent(request.getClass(), request) == null);
	}

	public boolean canRequest(AbstractRequest request) {
		return (_requests != null) && _requests.values().stream().allMatch(request::canWorkWith);
	}

	/**
	 * @param clazz
	 * @return {@code true} if request was successfully removed, {@code false} in case processing set is not created or not containing the request.
	 */
	public boolean removeRequest(Class<? extends AbstractRequest> clazz) {
		return (_requests != null) && (_requests.remove(clazz) != null);
	}

	/**
	 * @param <T>
	 * @param requestClass
	 * @return object that is instance of {@code requestClass} param, {@code null} if not instance or not set.
	 */
	public <T extends AbstractRequest> T getRequest(Class<T> requestClass) {
		return _requests != null ? requestClass.cast(_requests.get(requestClass)) : null;
	}

	/**
	 * @return {@code true} if player has any processing request set, {@code false} otherwise.
	 */
	public boolean hasRequests() {
		return (_requests != null) && !_requests.isEmpty();
	}

	public boolean hasItemRequest() {
		return (_requests != null) && _requests.values().stream().anyMatch(AbstractRequest::isItemRequest);
	}

	/**
	 * @param requestClass
	 * @param classes
	 * @return {@code true} if player has the provided request and processing it, {@code false} otherwise.
	 */
	@SafeVarargs
	public final boolean hasRequest(Class<? extends AbstractRequest> requestClass, Class<? extends AbstractRequest>... classes) {
		if (_requests != null) {
			for (Class<? extends AbstractRequest> clazz : classes) {
				if (_requests.containsKey(clazz)) {
					return true;
				}
			}
			return _requests.containsKey(requestClass);
		}
		return false;
	}

	/**
	 * @param objectId
	 * @return {@code true} if item object id is currently in use by some request, {@code false} otherwise.
	 */
	public boolean isProcessingItem(int objectId) {
		return (_requests != null) && _requests.values().stream().anyMatch(req -> req.isUsing(objectId));
	}

	/**
	 * Removing all requests associated with the item object id provided.
	 *
	 * @param objectId
	 */
	public void removeRequestsThatProcessesItem(int objectId) {
		if (_requests != null) {
			_requests.values().removeIf(req -> req.isUsing(objectId));
		}
	}

	/**
	 * @return the pc cafe points of the player.
	 */
	public int getPrimePoints() {
		return getAccountVariables().getInt("PRIME_POINTS", 0);
	}

	/**
	 * Sets prime shop for current player.
	 *
	 * @param points
	 */
	public void setPrimePoints(int points) {
		// Immediate store upon change
		final AccountVariables vars = getAccountVariables();
		vars.set("PRIME_POINTS", points);
		vars.storeMe();
	}

	/**
	 * @return the prime shop points of the player.
	 */
	public long getPcCafePoints() {
		return getAccountVariables().getLong(AccountVariables.PC_CAFE_POINTS, 0);
	}

	public void increasePcCafePoints(long count) {
		increasePcCafePoints(count, false);
	}

	public void increasePcCafePoints(long count, boolean doubleAmount) {
		count = doubleAmount ? count * 2 : count;
		final long newAmount = Math.min(getAccountVariables().getLong(AccountVariables.PC_CAFE_POINTS, 0) + count, 200_000);
		getAccountVariables().set(AccountVariables.PC_CAFE_POINTS, newAmount);
		sendPacket(SystemMessage.getSystemMessage(doubleAmount ? SystemMessageId.DOUBLE_POINTS_YOU_EARNED_S1_PC_POINT_S : SystemMessageId.YOU_EARNED_S1_PC_POINT_S).addLong(count));
		sendPacket(new ExPCCafePointInfo(newAmount, count, doubleAmount ? PcCafeConsumeType.DOUBLE_ADD : PcCafeConsumeType.ADD));
	}

	public void decreasePcCafePoints(long count) {
		final long newAmount = Math.max(getAccountVariables().getLong(AccountVariables.PC_CAFE_POINTS, 0) - count, 0);
		getAccountVariables().set(AccountVariables.PC_CAFE_POINTS, newAmount);
		sendPacket(SystemMessage.getSystemMessage(SystemMessageId.YOU_ARE_USING_S1_POINT).addLong(count));
		sendPacket(new ExPCCafePointInfo(newAmount, -count, PcCafeConsumeType.CONSUME));
	}

	/**
	 * Gets the last commission infos.
	 *
	 * @return the last commission infos
	 */
	public Map<Integer, ExResponseCommissionInfo> getLastCommissionInfos() {
		if (_lastCommissionInfos == null) {
			synchronized (this) {
				if (_lastCommissionInfos == null) {
					_lastCommissionInfos = new ConcurrentHashMap<>();
				}
			}
		}
		return _lastCommissionInfos;
	}

	/**
	 * Gets the whisperers.
	 *
	 * @return the whisperers
	 */
	public Set<Integer> getWhisperers() {
		return _whisperers;
	}

	public MatchingRoom getMatchingRoom() {
		return _matchingRoom;
	}

	public void setMatchingRoom(MatchingRoom matchingRoom) {
		_matchingRoom = matchingRoom;
	}

	public boolean isInMatchingRoom() {
		return _matchingRoom != null;
	}

	public int getVitalityItemsUsed() {
		return getVariables().getInt(PlayerVariables.VITALITY_ITEMS_USED_VARIABLE_NAME, 0);
	}

	public void setVitalityItemsUsed(int used) {
		final PlayerVariables vars = getVariables();
		vars.set(PlayerVariables.VITALITY_ITEMS_USED_VARIABLE_NAME, used);
		vars.storeMe();
	}

	@Override
	public boolean isVisibleFor(Player player) {
		return (super.isVisibleFor(player) || ((player.getParty() != null) && (player.getParty() == getParty())));
	}

	/**
	 * Set the Quest zone ID.
	 *
	 * @param id the quest zone ID
	 */
	public void setQuestZoneId(int id) {
		_questZoneId = id;
	}

	/**
	 * Gets the Quest zone ID.
	 *
	 * @return int the quest zone ID
	 */
	public int getQuestZoneId() {
		return _questZoneId;
	}

	/**
	 * @param iu
	 */
	public void sendInventoryUpdate(InventoryUpdate iu) {
		sendPacket(iu);
		sendPacket(new ExAdenaInvenCount(this));
		sendPacket(new ExUserInfoInvenWeight(this));
	}

	/**
	 * @param open
	 */
	public void sendItemList(boolean open) {
		sendPacket(new ItemList(this, open));
		sendPacket(new ExQuestItemList(this));
		sendPacket(new ExAdenaInvenCount(this));
		sendPacket(new ExUserInfoInvenWeight(this));
	}

	/**
	 * @param event
	 * @return {@code true} if event is successfuly registered, {@code false} in case events map is not initialized yet or event is not registered
	 */
	public boolean registerOnEvent(AbstractEvent<?> event) {
		if (_events == null) {
			synchronized (this) {
				if (_events == null) {
					_events = new ConcurrentHashMap<>();
				}
			}
		}
		return _events.putIfAbsent(event.getClass(), event) == null;
	}

	/**
	 * @param event
	 * @return {@code true} if event is successfuly removed, {@code false} in case events map is not initialized yet or event is not registered
	 */
	public boolean removeFromEvent(AbstractEvent<?> event) {
		if (_events == null) {
			return false;
		}
		return _events.remove(event.getClass()) != null;
	}

	/**
	 * @param <T>
	 * @param clazz
	 * @return the event instance or null in case events map is not initialized yet or event is not registered
	 */
	public <T extends AbstractEvent<?>> T getEvent(Class<T> clazz) {
		if (_events == null) {
			return null;
		}

		return _events.values().stream().filter(event -> clazz.isAssignableFrom(event.getClass())).map(clazz::cast).findFirst().orElse(null);
	}

	/**
	 * @return the first event that player participates on or null if he doesn't
	 */
	public AbstractEvent<?> getEvent() {
		if (_events == null) {
			return null;
		}

		return _events.values().stream().findFirst().orElse(null);
	}

	/**
	 * @param clazz
	 * @return {@code true} if player is registered on specified event, {@code false} in case events map is not initialized yet or event is not registered
	 */
	public boolean isOnEvent(Class<? extends AbstractEvent<?>> clazz) {
		if (_events == null) {
			return false;
		}

		return _events.containsKey(clazz);
	}

	public void setAttackerObjId(int attackerObjId) {
		_attackerObjId = attackerObjId;
	}

	public int getAttackerObjId() {
		return _attackerObjId;
	}

	public Fishing getFishing() {
		return _fishing;
	}

	public boolean isFishing() {
		return _fishing.isFishing();
	}

	@Override
	public MoveType getMoveType() {
		if (isSitting()) {
			return MoveType.SITTING;
		}
		return super.getMoveType();
	}

	private void startOnlineTimeUpdateTask() {
		if (_onlineTimeUpdateTask != null) {
			stopOnlineTimeUpdateTask();
		}

		_onlineTimeUpdateTask = ThreadPool.getInstance().scheduleGeneralAtFixedRate(this::updateOnlineTime, 60 * 1000L, 60 * 1000L, TimeUnit.MILLISECONDS);
	}

	private void updateOnlineTime() {
		final Clan clan = getClan();
		if (clan != null) {
			clan.addMemberOnlineTime(this);
		}
	}

	private void stopOnlineTimeUpdateTask() {
		if (_onlineTimeUpdateTask != null) {
			_onlineTimeUpdateTask.cancel(true);
			_onlineTimeUpdateTask = null;
		}
	}

	public GroupType getGroupType() {
		return isInParty() ? (getParty().isInCommandChannel() ? GroupType.COMMAND_CHANNEL : GroupType.PARTY) : GroupType.NONE;
	}

	public boolean isTrueHero() {
		return _trueHero;
	}

	public void setTrueHero(boolean val) {
		_trueHero = val;
	}

	@Override
	protected void initStatusUpdateCache() {
		super.initStatusUpdateCache();
		addStatusUpdateValue(StatusUpdateType.LEVEL);
		addStatusUpdateValue(StatusUpdateType.MAX_CP);
		addStatusUpdateValue(StatusUpdateType.CUR_CP);
	}

	public boolean tryLuck() {
		if ((Rnd.nextDouble() < BaseStats.LUC.getValue(getLUC())) && !hasSkillReuse(CommonSkill.LUCKY_CLOVER.getSkill().getReuseHashCode())) {
			SkillCaster.triggerCast(this, this, CommonSkill.LUCKY_CLOVER.getSkill());
			sendPacket(SystemMessageId.LADY_LUCK_SMILES_UPON_YOU);
			return true;
		}
		return false;
	}

	public boolean isAwakenedClass() {
		return isInCategory(CategoryType.SIXTH_CLASS_GROUP);
	}

	private void startAutoSaveTask() {
		if ((GeneralConfig.CHAR_DATA_STORE_INTERVAL > 0) && (_autoSaveTask == null)) {
			_autoSaveTask = ThreadPool.getInstance().scheduleGeneralAtFixedRate(this::autoSave, 300_000L, TimeUnit.MINUTES.toMillis(GeneralConfig.CHAR_DATA_STORE_INTERVAL), TimeUnit.MILLISECONDS);
		}
	}

	private void stopAutoSaveTask() {
		if (_autoSaveTask != null) {
			_autoSaveTask.cancel(false);
			_autoSaveTask = null;
		}
	}

	protected void autoSave() {
		storeMe();
		storeRecommendations();

		if (GeneralConfig.UPDATE_ITEMS_ON_CHAR_STORE) {
			getInventory().updateDatabase();
			getWarehouse().updateDatabase();
		}
	}

	public boolean canLogout() {
		if (hasItemRequest()) {
			return false;
		}

		if (isLocked()) {
			LOGGER.warn("Player " + getName() + " tried to restart/logout during class change.");
			return false;
		}

		if (AttackStanceTaskManager.getInstance().hasAttackStanceTask(this) && !(isGM() && AdminConfig.GM_RESTART_FIGHTING)) {
			sendPacket(SystemMessageId.YOU_CANNOT_EXIT_THE_GAME_WHILE_IN_COMBAT);
			if (GeneralConfig.DEBUG) {
				LOGGER.debug("Player " + getName() + " tried to restart/logout while fighting.");
			}

			return false;
		}

		if (isBlockedFromExit()) {
			return false;
		}

		final BooleanReturn term = EventDispatcher.getInstance().notifyEvent(new CanPlayerLogout(this), this, BooleanReturn.class);
		return (term == null) || term.getValue(); // true by default.
	}

	public boolean isPremium() {
		return getAccountVariables().getBoolean(AccountVariables.PREMIUM_ACCOUNT, false);
	}

	public void setPremium(boolean isPremium) {
		getAccountVariables().set(AccountVariables.PREMIUM_ACCOUNT, isPremium);
	}

	public boolean isInTraingCamp() {
		final TrainingHolder trainingHolder = getAccountVariables().getObject(AccountVariables.TRAINING_CAMP, TrainingHolder.class);
		return (trainingHolder != null) && (trainingHolder.getEndTime() == -1);
	}

	public boolean isOfflineShop() {
		return isSitting() && ((getClient() == null) || getClient().isDetached());
	}

	public Map<Faction, Integer> getFactionsPoints() {
		return _factionScore == null ? Collections.emptyMap() : _factionScore;
	}

	public int getFactionPoints(Faction faction) {
		return getFactionsPoints().getOrDefault(faction, 0);
	}

	public void addFactionPoints(Faction faction, int points) {
		if (_factionScore == null) {
			synchronized (this) {
				if (_factionScore == null) {
					_factionScore = new ConcurrentHashMap<>(FactionData.getInstance().getLoadedElementsCount());
				}
			}
		}

		_factionScore.put(faction, _factionScore.getOrDefault(faction, 0) + points);
	}

	public void removeFactionPoints(Faction faction, int points) {
		getFactionsPoints().put(faction, Math.max(getFactionsPoints().getOrDefault(faction, 0) - points, 0));
	}
}
