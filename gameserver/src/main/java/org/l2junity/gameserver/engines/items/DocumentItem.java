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
package org.l2junity.gameserver.engines.items;

import org.l2junity.gameserver.data.xml.IGameXmlReader;
import org.l2junity.gameserver.enums.CastleSide;
import org.l2junity.gameserver.enums.CategoryType;
import org.l2junity.gameserver.enums.ItemSkillType;
import org.l2junity.gameserver.enums.Race;
import org.l2junity.gameserver.model.ExtractableProduct;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.conditions.*;
import org.l2junity.gameserver.model.holders.ItemChanceHolder;
import org.l2junity.gameserver.model.holders.ItemSkillHolder;
import org.l2junity.gameserver.model.items.ItemTemplate;
import org.l2junity.gameserver.model.stats.DoubleStat;
import org.l2junity.gameserver.model.stats.functions.FuncTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.*;

/**
 * @author mkizub, JIV
 */
public final class DocumentItem implements IGameXmlReader {
	protected final Logger _log = LoggerFactory.getLogger(getClass());
	private final File _file;
	private Item _currentItem = null;
	private final List<ItemTemplate> _itemsInFile = new LinkedList<>();

	/**
	 * @param file
	 */
	public DocumentItem(File file) {
		this._file = file;
	}

	protected void parseDocument(Document doc) {
		for (Node n = doc.getFirstChild(); n != null; n = n.getNextSibling()) {
			if ("list".equalsIgnoreCase(n.getNodeName())) {

				for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling()) {
					if ("item".equalsIgnoreCase(d.getNodeName())) {
						try {
							_currentItem = new Item();
							parseItem(d);
							_itemsInFile.add(_currentItem.item);
						} catch (Exception e) {
							_log.warn("Cannot create item {} in file: {}", _currentItem.id, doc.getBaseURI(), e);
						}
					}
				}
			}
		}
	}

	protected void parseItem(Node n) throws InvocationTargetException {
		int itemId = Integer.parseInt(n.getAttributes().getNamedItem("id").getNodeValue());
		String className = n.getAttributes().getNamedItem("type").getNodeValue();
		String itemName = n.getAttributes().getNamedItem("name").getNodeValue();

		_currentItem.id = itemId;
		_currentItem.name = itemName;
		_currentItem.type = className;
		_currentItem.set = new StatsSet();
		_currentItem.set.set("item_id", itemId);
		_currentItem.set.set("name", itemName);

		Node first = n.getFirstChild();
		for (n = first; n != null; n = n.getNextSibling()) {
			if ("set".equalsIgnoreCase(n.getNodeName())) {
				if (_currentItem.item != null) {
					throw new IllegalStateException("Item created but set node found! Item " + itemId);
				}
				parseBeanSet(n, _currentItem.set, 1);
			} else if ("stats".equalsIgnoreCase(n.getNodeName())) {
				makeItem();
				for (Node b = n.getFirstChild(); b != null; b = b.getNextSibling()) {
					if ("stat".equalsIgnoreCase(b.getNodeName())) {
						final DoubleStat type = DoubleStat.valueOfXml(b.getAttributes().getNamedItem("type").getNodeValue());
						final double value = Double.valueOf(b.getTextContent());
						_currentItem.item.addFunctionTemplate(new FuncTemplate(null, null, "add", 0x00, type, value));
					}
				}
			} else if ("skills".equalsIgnoreCase(n.getNodeName())) {
				makeItem();
				for (Node b = n.getFirstChild(); b != null; b = b.getNextSibling()) {
					if ("skill".equalsIgnoreCase(b.getNodeName())) {
						final int id = parseInteger(b.getAttributes(), "id");
						final int level = parseInteger(b.getAttributes(), "level");
						final int subLevel = parseInteger(b.getAttributes(), "subLevel", 0);
						final ItemSkillType type = parseEnum(b.getAttributes(), ItemSkillType.class, "type", ItemSkillType.NORMAL);
						final int chance = parseInteger(b.getAttributes(), "type_chance", 0);
						final int value = parseInteger(b.getAttributes(), "type_value", 0);
						_currentItem.item.addSkill(new ItemSkillHolder(id, level, subLevel, type, chance, value));
					}
				}
			} else if ("capsuled_items".equalsIgnoreCase(n.getNodeName())) {
				makeItem();
				for (Node b = n.getFirstChild(); b != null; b = b.getNextSibling()) {
					if ("item".equals(b.getNodeName())) {
						final int id = parseInteger(b.getAttributes(), "id");
						final int min = parseInteger(b.getAttributes(), "min");
						final int max = parseInteger(b.getAttributes(), "max");
						final double chance = parseDouble(b.getAttributes(), "chance");
						_currentItem.item.addCapsuledItem(new ExtractableProduct(id, min, max, chance));
					}
				}
			} else if ("createItems".equalsIgnoreCase(n.getNodeName())) {
				makeItem();
				for (Node b = n.getFirstChild(); b != null; b = b.getNextSibling()) {
					if ("item".equals(b.getNodeName())) {
						final int id = parseInteger(b.getAttributes(), "id");
						final int count = parseInteger(b.getAttributes(), "count");
						final double chance = parseDouble(b.getAttributes(), "chance");
						_currentItem.item.addCreateItem(new ItemChanceHolder(id, chance, count));
					}
				}
			} else if ("cond".equalsIgnoreCase(n.getNodeName())) {
				makeItem();
				Condition condition = parseCondition(n.getFirstChild());
				Node msg = n.getAttributes().getNamedItem("msg");
				Node msgId = n.getAttributes().getNamedItem("msgId");
				if ((condition != null) && (msg != null)) {
					condition.setMessage(msg.getNodeValue());
				} else if ((condition != null) && (msgId != null)) {
					condition.setMessageId(Integer.decode(msgId.getNodeValue()));
					Node addName = n.getAttributes().getNamedItem("addName");
					if ((addName != null) && (Integer.decode(msgId.getNodeValue()) > 0)) {
						condition.addName();
					}
				}
				_currentItem.item.attachCondition(condition);
			}
		}
		// bah! in this point item doesn't have to be still created
		makeItem();
	}

	private void makeItem() throws InvocationTargetException {
		// If item exists just reload the data.
		if (_currentItem.item != null) {
			_currentItem.item.set(_currentItem.set);
			return;
		}

		try {
			final Constructor<?> itemClass = Class.forName(ItemTemplate.class.getPackage().getName() + "." + _currentItem.type).getConstructor(StatsSet.class);
			_currentItem.item = (ItemTemplate) itemClass.newInstance(_currentItem.set);
		} catch (Exception e) {
			throw new InvocationTargetException(e);
		}
	}

	/**
	 * @return
	 */
	public List<ItemTemplate> getItemList() {
		return _itemsInFile;
	}

	@Override
	public void parseDocument(Document doc, Path path) {
	}

	public Document parse() {
		Document doc = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(false);
			factory.setIgnoringComments(true);
			doc = factory.newDocumentBuilder().parse(_file);
			parseDocument(doc);
		} catch (Exception e) {
			_log.error("Error loading file " + _file, e);
		}
		return doc;
	}

	protected Condition parseCondition(Node n) {
		while ((n != null) && (n.getNodeType() != Node.ELEMENT_NODE)) {
			n = n.getNextSibling();
		}

		Condition condition = null;
		if (n != null) {
			switch (n.getNodeName().toLowerCase()) {
				case "and": {
					condition = parseLogicAnd(n);
					break;
				}
				case "player": {
					condition = parsePlayerCondition(n);
					break;
				}
			}
		}
		return condition;
	}

	protected Condition parseLogicAnd(Node n) {
		ConditionLogicAnd cond = new ConditionLogicAnd();
		for (n = n.getFirstChild(); n != null; n = n.getNextSibling()) {
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				cond.add(parseCondition(n));
			}
		}
		if ((cond.conditions == null) || (cond.conditions.length == 0)) {
			_log.error("Empty <and> condition in " + _file);
		}
		return cond;
	}

	protected Condition parsePlayerCondition(Node n) {
		Condition cond = null;
		NamedNodeMap attrs = n.getAttributes();
		for (int i = 0; i < attrs.getLength(); i++) {
			Node a = attrs.item(i);
			switch (a.getNodeName().toLowerCase()) {
				case "races": {
					final String[] racesVal = a.getNodeValue().split(",");
					final Race[] races = new Race[racesVal.length];
					for (int r = 0; r < racesVal.length; r++) {
						if (racesVal[r] != null) {
							races[r] = Race.valueOf(racesVal[r]);
						}
					}
					cond = joinAnd(cond, new ConditionPlayerRace(races));
					break;
				}
				case "level": {
					int lvl = Integer.decode(a.getNodeValue());
					cond = joinAnd(cond, new ConditionPlayerLevel(lvl));
					break;
				}
				case "levelrange": {
					String[] range = a.getNodeValue().split(";");
					if (range.length == 2) {
						int[] lvlRange = new int[2];
						lvlRange[0] = Integer.decode(a.getNodeValue().split(";")[0]);
						lvlRange[1] = Integer.decode(a.getNodeValue().split(";")[1]);
						cond = joinAnd(cond, new ConditionPlayerLevelRange(lvlRange));
					}
					break;
				}
				case "chaotic": {
					boolean val = Boolean.parseBoolean(a.getNodeValue());
					cond = joinAnd(cond, new ConditionPlayerChaotic(val));
					break;
				}
				case "ishero": {
					boolean val = Boolean.parseBoolean(a.getNodeValue());
					cond = joinAnd(cond, new ConditionPlayerIsHero(val));
					break;
				}
				case "isnoble": {
					cond = joinAnd(cond, new ConditionPlayerIsNoble(Boolean.parseBoolean(a.getNodeValue())));
					break;
				}
				case "pkcount": {
					int expIndex = Integer.decode(a.getNodeValue());
					cond = joinAnd(cond, new ConditionPlayerPkCount(expIndex));
					break;
				}
				case "siegezone": {
					int value = Integer.decode(a.getNodeValue());
					cond = joinAnd(cond, new ConditionSiegeZone(value, true));
					break;
				}
				case "isclanleader": {
					boolean val = Boolean.parseBoolean(a.getNodeValue());
					cond = joinAnd(cond, new ConditionPlayerIsClanLeader(val));
					break;
				}
				case "pledgeclass": {
					int pledgeClass = Integer.decode(a.getNodeValue());
					cond = joinAnd(cond, new ConditionPlayerPledgeClass(pledgeClass));
					break;
				}
				case "clanhall": {
					StringTokenizer st = new StringTokenizer(a.getNodeValue(), ",");
					ArrayList<Integer> array = new ArrayList<>(st.countTokens());
					while (st.hasMoreTokens()) {
						String item = st.nextToken().trim();
						array.add(Integer.decode(item));
					}
					cond = joinAnd(cond, new ConditionPlayerHasClanHall(array));
					break;
				}
				case "fort": {
					int fort = Integer.decode(a.getNodeValue());
					cond = joinAnd(cond, new ConditionPlayerHasFort(fort));
					break;
				}
				case "castle": {
					int castle = Integer.decode(a.getNodeValue());
					cond = joinAnd(cond, new ConditionPlayerHasCastle(castle));
					break;
				}
				case "sex": {
					int sex = Integer.decode(a.getNodeValue());
					cond = joinAnd(cond, new ConditionPlayerSex(sex));
					break;
				}
				case "flymounted": {
					boolean val = Boolean.parseBoolean(a.getNodeValue());
					cond = joinAnd(cond, new ConditionPlayerFlyMounted(val));
					break;
				}
				case "vehiclemounted": {
					boolean val = Boolean.parseBoolean(a.getNodeValue());
					cond = joinAnd(cond, new ConditionPlayerVehicleMounted(val));
					break;
				}
				case "class_id_restriction": {
					StringTokenizer st = new StringTokenizer(a.getNodeValue(), ",");
					ArrayList<Integer> array = new ArrayList<>(st.countTokens());
					while (st.hasMoreTokens()) {
						String item = st.nextToken().trim();
						array.add(Integer.decode(item));
					}
					cond = joinAnd(cond, new ConditionPlayerClassIdRestriction(array));
					break;
				}
				case "subclass": {
					boolean val = Boolean.parseBoolean(a.getNodeValue());
					cond = joinAnd(cond, new ConditionPlayerSubclass(val));
					break;
				}
				case "instanceid": {
					StringTokenizer st = new StringTokenizer(a.getNodeValue(), ",");
					ArrayList<Integer> array = new ArrayList<>(st.countTokens());
					while (st.hasMoreTokens()) {
						String item = st.nextToken().trim();
						array.add(Integer.decode(item));
					}
					cond = joinAnd(cond, new ConditionPlayerInstanceId(array));
					break;
				}
				case "cloakstatus": {
					boolean val = Boolean.parseBoolean(a.getNodeValue());
					cond = joinAnd(cond, new ConditionPlayerCloakStatus(val));
					break;
				}
				case "insidezoneid": {
					StringTokenizer st = new StringTokenizer(a.getNodeValue(), ",");
					List<Integer> array = new ArrayList<>(st.countTokens());
					while (st.hasMoreTokens()) {
						String item = st.nextToken().trim();
						array.add(Integer.decode(item));
					}
					cond = joinAnd(cond, new ConditionPlayerInsideZoneId(array));
					break;
				}
				case "categorytype": {
					final String[] values = a.getNodeValue().split(",");
					final Set<CategoryType> array = new HashSet<>(values.length);
					for (String value : values) {
						array.add(CategoryType.valueOf(value));
					}
					cond = joinAnd(cond, new ConditionCategoryType(array));
					break;
				}
				case "isonside": {
					cond = joinAnd(cond, new ConditionPlayerIsOnSide(Enum.valueOf(CastleSide.class, a.getNodeValue())));
					break;
				}
			}
		}

		if (cond == null) {
			_log.error("Unrecognized <player> condition in " + _file);
		}
		return cond;
	}

	protected void parseBeanSet(Node n, StatsSet set, Integer level) {
		String name = n.getAttributes().getNamedItem("name").getNodeValue().trim();
		String value = n.getAttributes().getNamedItem("val").getNodeValue().trim();
		set.set(name, value);
	}

	protected Condition joinAnd(Condition cond, Condition c) {
		if (cond == null) {
			return c;
		}
		if (cond instanceof ConditionLogicAnd) {
			((ConditionLogicAnd) cond).add(c);
			return cond;
		}
		ConditionLogicAnd and = new ConditionLogicAnd();
		and.add(cond);
		and.add(c);
		return and;
	}
}
