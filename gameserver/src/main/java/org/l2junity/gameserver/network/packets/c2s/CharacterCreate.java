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
package org.l2junity.gameserver.network.packets.c2s;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.core.configs.GameserverConfig;
import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.data.sql.impl.CharNameTable;
import org.l2junity.gameserver.data.xml.impl.CategoryData;
import org.l2junity.gameserver.data.xml.impl.InitialEquipmentData;
import org.l2junity.gameserver.data.xml.impl.InitialShortcutData;
import org.l2junity.gameserver.data.xml.impl.PlayerTemplateData;
import org.l2junity.gameserver.data.xml.impl.SkillData;
import org.l2junity.gameserver.data.xml.impl.SkillTreesData;
import org.l2junity.gameserver.enums.CategoryType;
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.SkillLearn;
import org.l2junity.gameserver.model.actor.appearance.PcAppearance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.actor.stat.PcStat;
import org.l2junity.gameserver.model.actor.templates.PcTemplate;
import org.l2junity.gameserver.model.base.ClassId;
import org.l2junity.gameserver.model.events.Containers;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerCreate;
import org.l2junity.gameserver.model.items.PcItemTemplate;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.network.Disconnection;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.CharCreateFail;
import org.l2junity.gameserver.network.packets.s2c.CharCreateOk;
import org.l2junity.gameserver.network.packets.s2c.CharSelectionInfo;
import org.l2junity.gameserver.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@SuppressWarnings("unused")
public final class CharacterCreate extends GameClientPacket {
	protected static final Logger logAccounting = LoggerFactory.getLogger("accounting");

	// cSdddddddddddd
	private String _name;
	private int _race;
	private byte _sex;
	private int _classId;
	private int _int;
	private int _str;
	private int _con;
	private int _men;
	private int _dex;
	private int _wit;
	private byte _hairStyle;
	private byte _hairColor;
	private byte _face;

	@Override
	public void readImpl() {
		_name = readS();
		_race = readD();
		_sex = (byte) readD();
		_classId = readD();
		_int = readD();
		_str = readD();
		_con = readD();
		_men = readD();
		_dex = readD();
		_wit = readD();
		_hairStyle = (byte) readD();
		_hairColor = (byte) readD();
		_face = (byte) readD();
	}

	@Override
	public void runImpl() {
		// Last Verified: May 30, 2009 - Gracia Final - Players are able to create characters with names consisting of as little as 1,2,3 letter/number combinations.
		if ((_name.length() < 1) || (_name.length() > 16)) {

			getClient().sendPacket(new CharCreateFail(CharCreateFail.REASON_16_ENG_CHARS));
			return;
		}

		if (PlayerConfig.FORBIDDEN_NAMES.length > 1) {
			for (String st : PlayerConfig.FORBIDDEN_NAMES) {
				if (_name.toLowerCase().contains(st.toLowerCase())) {
					getClient().sendPacket(new CharCreateFail(CharCreateFail.REASON_INCORRECT_NAME));
					return;
				}
			}
		}

		// Last Verified: May 30, 2009 - Gracia Final
		if (!Util.isAlphaNumeric(_name) || !isValidName(_name)) {
			getClient().sendPacket(new CharCreateFail(CharCreateFail.REASON_INCORRECT_NAME));
			return;
		}

		if ((_face > 2) || (_face < 0)) {
			log.warn("Character Creation Failure: Character face " + _face + " is invalid. Possible client hack. " + getClient());

			getClient().sendPacket(new CharCreateFail(CharCreateFail.REASON_CREATION_FAILED));
			return;
		}

		if ((_hairStyle < 0) || ((_sex == 0) && (_hairStyle > 4)) || ((_sex != 0) && (_hairStyle > 6))) {
			log.warn("Character Creation Failure: Character hair style " + _hairStyle + " is invalid. Possible client hack. " + getClient());

			getClient().sendPacket(new CharCreateFail(CharCreateFail.REASON_CREATION_FAILED));
			return;
		}

		if ((_hairColor > 3) || (_hairColor < 0)) {
			log.warn("Character Creation Failure: Character hair color " + _hairColor + " is invalid. Possible client hack. " + getClient());

			getClient().sendPacket(new CharCreateFail(CharCreateFail.REASON_CREATION_FAILED));
			return;
		}

		Player newChar = null;
		PcTemplate template = null;

		/*
		 * DrHouse: Since checks for duplicate names are done using SQL, lock must be held until data is written to DB as well.
		 */
		synchronized (CharNameTable.getInstance()) {
			if ((CharNameTable.getInstance().getAccountCharacterCount(getClient().getAccountName()) >= GameserverConfig.MAX_CHARACTERS_NUMBER_PER_ACCOUNT) && (GameserverConfig.MAX_CHARACTERS_NUMBER_PER_ACCOUNT != 0)) {
				getClient().sendPacket(new CharCreateFail(CharCreateFail.REASON_TOO_MANY_CHARACTERS));
				return;
			} else if (CharNameTable.getInstance().doesCharNameExist(_name)) {

				getClient().sendPacket(new CharCreateFail(CharCreateFail.REASON_NAME_ALREADY_EXISTS));
				return;
			}

			template = PlayerTemplateData.getInstance().getTemplate(_classId);
			if ((template == null) || (ClassId.getClassId(_classId).getParent() != null) || !CategoryData.getInstance().isInCategory(CategoryType.FIRST_CLASS_GROUP, _classId)) {

				getClient().sendPacket(new CharCreateFail(CharCreateFail.REASON_CREATION_FAILED));
				return;
			}
			final PcAppearance app = new PcAppearance(_face, _hairColor, _hairStyle, _sex != 0);
			newChar = Player.create(template, getClient().getAccountName(), _name, app);
		}

		// HP and MP are at maximum and CP is zero by default.
		newChar.setCurrentHp(newChar.getMaxHp());
		newChar.setCurrentMp(newChar.getMaxMp());
		// newChar.setMaxLoad(template.getBaseLoad());

		getClient().sendPacket(CharCreateOk.STATIC_PACKET);

		initNewChar(getClient(), newChar);

		logAccounting.info("Created new character, {}, {}", newChar, getClient());
	}

	private static boolean isValidName(String text) {
		return GameserverConfig.CHARNAME_TEMPLATE_PATTERN.matcher(text).matches();
	}

	private void initNewChar(GameClient client, Player newChar) {
		WorldManager.getInstance().getMainWorld().addObject(newChar);

		if (PlayerConfig.STARTING_ADENA > 0) {
			newChar.addAdena("Init", PlayerConfig.STARTING_ADENA, null, false);
		}

		final PcTemplate template = newChar.getTemplate();
		Location createLoc = template.getCreationPoint();
		newChar.setXYZInvisible(createLoc.getX(), createLoc.getY(), createLoc.getZ());
		newChar.setTitle("");

		if (PlayerConfig.ENABLE_VITALITY) {
			newChar.setVitalityPoints(Math.min(PlayerConfig.STARTING_VITALITY_POINTS, PcStat.MAX_VITALITY_POINTS), true);
		}
		if (PlayerConfig.STARTING_LEVEL > 1) {
			newChar.getStat().addLevel((byte) (PlayerConfig.STARTING_LEVEL - 1));
		}
		if (PlayerConfig.STARTING_SP > 0) {
			newChar.getStat().addSp(PlayerConfig.STARTING_SP);
		}

		final List<PcItemTemplate> initialItems = InitialEquipmentData.getInstance().getEquipmentList(newChar.getClassId());
		if (initialItems != null) {
			for (PcItemTemplate ie : initialItems) {
				final ItemInstance item = newChar.getInventory().addItem("Init", ie.getId(), ie.getCount(), newChar, null);
				if (item == null) {
					log.warn("Could not create item during char creation: itemId " + ie.getId() + ", amount " + ie.getCount() + ".");
					continue;
				}

				if (item.isEquipable() && ie.isEquipped()) {
					newChar.getInventory().equipItem(item);
				}
			}
		}

		for (SkillLearn skill : SkillTreesData.getInstance().getAvailableSkills(newChar, newChar.getClassId(), false, true)) {
			newChar.addSkill(SkillData.getInstance().getSkill(skill.getSkillId(), skill.getSkillLevel()), true);
		}

		// Register all shortcuts for actions, skills and items for this new character.
		InitialShortcutData.getInstance().registerAllShortcuts(newChar);

		EventDispatcher.getInstance().notifyEvent(new OnPlayerCreate(newChar, newChar.getObjectId(), newChar.getName(), client), Containers.Players());

		newChar.setOnlineStatus(true, false);
		if (PlayerConfig.SHOW_GOD_VIDEO_INTRO) {
			newChar.getVariables().set("intro_god_video", true);
		}
		Disconnection.of(client, newChar).storeMe().deleteMe();

		final CharSelectionInfo charSelectionInfo = new CharSelectionInfo(getClient().getSessionInfo());
		getClient().setCharSelectionInfo(charSelectionInfo.getCharInfo());
	}
}
