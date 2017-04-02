/*
 * Copyright (C) 2004-2016 L2J Unity
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
package org.l2junity.gameserver.data.xml.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.data.xml.IGameXmlReader;
import org.l2junity.gameserver.handler.EffectHandler;
import org.l2junity.gameserver.handler.SkillConditionHandler;
import org.l2junity.gameserver.handler.TargetHandler;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.skills.EffectScope;
import org.l2junity.gameserver.model.skills.ISkillCondition;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.skills.SkillConditionScope;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.nio.file.Path;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Skill data parser.
 *
 * @author NosBit
 */
@Slf4j
@StartupComponent(value = "Data", dependency = {EffectHandler.class, SkillConditionHandler.class, TargetHandler.class, EnchantSkillGroupsData.class})
public class SkillData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final SkillData instance = new SkillData();

	private final Map<Long, Skill> _skills = new HashMap<>();
	private final Map<Integer, Integer> _skillsMaxLevel = new HashMap<>();

	private class NamedParamInfo {
		private final String _name;
		private final Integer _fromLevel;
		private final Integer _toLevel;
		private final Integer _fromSubLevel;
		private final Integer _toSubLevel;
		private final Map<Integer, Map<Integer, StatsSet>> _info;

		NamedParamInfo(String name, Integer fromLevel, Integer toLevel, Integer fromSubLevel, Integer toSubLevel, Map<Integer, Map<Integer, StatsSet>> info) {
			_name = name;
			_fromLevel = fromLevel;
			_toLevel = toLevel;
			_fromSubLevel = fromSubLevel;
			_toSubLevel = toSubLevel;
			_info = info;
		}

		public String getName() {
			return _name;
		}

		public Integer getFromLevel() {
			return _fromLevel;
		}

		public Integer getToLevel() {
			return _toLevel;
		}

		public Integer getFromSubLevel() {
			return _fromSubLevel;
		}

		public Integer getToSubLevel() {
			return _toSubLevel;
		}

		public Map<Integer, Map<Integer, StatsSet>> getInfo() {
			return _info;
		}
	}

	protected SkillData() {
		reload();
	}

	private void reload() {
		_skills.clear();
		_skillsMaxLevel.clear();
		parseDatapackDirectory("data/stats/skills/", true);
		log.info("Loaded {} Skills.", _skills.size());
	}

	/**
	 * Provides the skill hash
	 *
	 * @param skill The L2Skill to be hashed
	 * @return getSkillHashCode(skill.getId(), skill.getLevel())
	 */
	public static long getSkillHashCode(Skill skill) {
		return getSkillHashCode(skill.getId(), skill.getLevel(), skill.getSubLevel());
	}

	/**
	 * Centralized method for easier change of the hashing sys
	 *
	 * @param skillId    The Skill Id
	 * @param skillLevel The Skill Level
	 * @return The Skill hash number
	 */
	public static long getSkillHashCode(int skillId, int skillLevel) {
		return getSkillHashCode(skillId, skillLevel, 0);
	}

	/**
	 * Centralized method for easier change of the hashing sys
	 *
	 * @param skillId       The Skill Id
	 * @param skillLevel    The Skill Level
	 * @param subSkillLevel The skill sub level
	 * @return The Skill hash number
	 */
	public static long getSkillHashCode(int skillId, int skillLevel, int subSkillLevel) {
		return subSkillLevel > 0 ? ((skillId * 4294967296L) + (subSkillLevel * 65536) + skillLevel) : (skillId * 65536) + skillLevel;
	}

	public Skill getSkill(int skillId, int level) {
		return getSkill(skillId, level, 0);
	}

	public Skill getSkill(int skillId, int level, int subLevel) {
		final Skill result = _skills.get(getSkillHashCode(skillId, level, subLevel));
		if (result != null) {
			return result;
		}

		// skill/level not found, fix for transformation scripts
		final int maxLvl = getMaxLevel(skillId);
		// requested level too high
		if ((maxLvl > 0) && (level > maxLvl)) {
			log.warn("call to unexisting skill level id: {} requested level: {} max level: {}", skillId, level, maxLvl, new Throwable());
			return _skills.get(getSkillHashCode(skillId, maxLvl, 0));
		}

		log.warn("No skill info found for skill id {} and skill level {}", skillId, level);
		return null;
	}

	public int getMaxLevel(int skillId) {
		final Integer maxLevel = _skillsMaxLevel.get(skillId);
		return maxLevel != null ? maxLevel : 0;
	}

	@Override
	public boolean isValidating() {
		return false;
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		for (Node node = doc.getFirstChild(); node != null; node = node.getNextSibling()) {
			if ("list".equalsIgnoreCase(node.getNodeName())) {
				for (Node listNode = node.getFirstChild(); listNode != null; listNode = listNode.getNextSibling()) {
					if ("skill".equalsIgnoreCase(listNode.getNodeName())) {
						NamedNodeMap attributes = listNode.getAttributes();
						final Map<Integer, Set<Integer>> levels = new HashMap<>();
						final Map<Integer, Map<Integer, StatsSet>> skillInfo = new HashMap<>();
						final StatsSet generalSkillInfo = skillInfo.computeIfAbsent(-1, k -> new HashMap<>()).computeIfAbsent(-1, k -> new StatsSet());

						parseAttributes(attributes, "", generalSkillInfo);

						final Map<String, Map<Integer, Map<Integer, Object>>> variableValues = new HashMap<>();
						final Map<EffectScope, List<NamedParamInfo>> effectParamInfo = new HashMap<>();
						final Map<SkillConditionScope, List<NamedParamInfo>> conditionParamInfo = new HashMap<>();
						for (Node skillNode = listNode.getFirstChild(); skillNode != null; skillNode = skillNode.getNextSibling()) {
							final String skillNodeName = skillNode.getNodeName();
							switch (skillNodeName.toLowerCase()) {
								case "variable": {
									attributes = skillNode.getAttributes();
									final String name = "@" + parseString(attributes, "name");
									variableValues.put(name, parseValues(skillNode));
									break;
								}
								case "#text": {
									break;
								}
								default: {
									final EffectScope effectScope = EffectScope.findByXmlNodeName(skillNodeName);
									if (effectScope != null) {
										for (Node effectsNode = skillNode.getFirstChild(); effectsNode != null; effectsNode = effectsNode.getNextSibling()) {
											switch (effectsNode.getNodeName().toLowerCase()) {
												case "effect": {
													effectParamInfo.computeIfAbsent(effectScope, k -> new LinkedList<>()).add(parseNamedParamInfo(effectsNode, variableValues));
													break;
												}
											}
										}
										break;
									}
									final SkillConditionScope skillConditionScope = SkillConditionScope.findByXmlNodeName(skillNodeName);
									if (skillConditionScope != null) {
										for (Node conditionNode = skillNode.getFirstChild(); conditionNode != null; conditionNode = conditionNode.getNextSibling()) {
											switch (conditionNode.getNodeName().toLowerCase()) {
												case "condition": {
													conditionParamInfo.computeIfAbsent(skillConditionScope, k -> new LinkedList<>()).add(parseNamedParamInfo(conditionNode, variableValues));
													break;
												}
											}
										}
									} else {
										parseInfo(skillNode, variableValues, skillInfo);
									}
									break;
								}
							}
						}

						final int fromLevel = generalSkillInfo.getInt(".fromLevel", 1);
						final int toLevel = generalSkillInfo.getInt(".toLevel", 0);

						for (int i = fromLevel; i <= toLevel; i++) {
							levels.computeIfAbsent(i, k -> new HashSet<>()).add(0);
						}

						skillInfo.forEach((level, subLevelMap) ->
						{
							if (level == -1) {
								return;
							}
							subLevelMap.forEach((subLevel, statsSet) ->
							{
								if (subLevel == -1) {
									return;
								}
								levels.computeIfAbsent(level, k -> new HashSet<>()).add(subLevel);
							});
						});

						Stream.concat(effectParamInfo.values().stream(), conditionParamInfo.values().stream()).forEach(namedParamInfos ->
						{
							namedParamInfos.forEach(namedParamInfo ->
							{
								namedParamInfo.getInfo().forEach((level, subLevelMap) ->
								{
									if (level == -1) {
										return;
									}
									subLevelMap.forEach((subLevel, statsSet) ->
									{
										if (subLevel == -1) {
											return;
										}
										levels.computeIfAbsent(level, k -> new HashSet<>()).add(subLevel);
									});
								});

								if ((namedParamInfo.getFromLevel() != null) && (namedParamInfo.getToLevel() != null)) {
									for (int i = namedParamInfo.getFromLevel(); i <= namedParamInfo.getToLevel(); i++) {
										if ((namedParamInfo.getFromSubLevel() != null) && (namedParamInfo.getToSubLevel() != null)) {
											for (int j = namedParamInfo.getFromSubLevel(); j <= namedParamInfo.getToSubLevel(); j++) {

												levels.computeIfAbsent(i, k -> new HashSet<>()).add(j);
											}
										} else {
											levels.computeIfAbsent(i, k -> new HashSet<>()).add(0);
										}
									}
								}
							});
						});

						levels.forEach((level, subLevels) ->
						{
							subLevels.forEach(subLevel ->
							{
								final StatsSet statsSet = Optional.ofNullable(skillInfo.getOrDefault(level, Collections.emptyMap()).get(subLevel)).orElseGet(StatsSet::new);
								skillInfo.getOrDefault(level, Collections.emptyMap()).getOrDefault(-1, StatsSet.EMPTY_STATSET).getSet().forEach(statsSet.getSet()::putIfAbsent);
								skillInfo.getOrDefault(-1, Collections.emptyMap()).getOrDefault(-1, StatsSet.EMPTY_STATSET).getSet().forEach(statsSet.getSet()::putIfAbsent);
								statsSet.set(".level", level);
								statsSet.set(".subLevel", subLevel);
								final Skill skill = new Skill(statsSet);
								forEachNamedParamInfoParam(effectParamInfo, level, subLevel, ((effectScope, params) ->
								{
									final String effectName = params.getString(".name");
									params.remove(".name");
									try {
										final Function<StatsSet, AbstractEffect> effectFunction = EffectHandler.getInstance().getHandlerFactory(effectName);
										if (effectFunction != null) {
											skill.addEffect(effectScope, effectFunction.apply(params));
										} else {
											log.warn("Missing effect for Skill Id[{}] Level[{}] SubLevel[{}] Effect Scope[{}] Effect Name[{}]", statsSet.getInt(".id"), level, subLevel, effectScope, effectName);
										}
									} catch (Exception e) {
										log.warn("Failed loading effect for Skill Id[{}] Level[{}] SubLevel[{}] Effect Scope[{}] Effect Name[{}]", statsSet.getInt(".id"), level, subLevel, effectScope, effectName, e);
									}
								}));

								forEachNamedParamInfoParam(conditionParamInfo, level, subLevel, ((skillConditionScope, params) ->
								{
									final String conditionName = params.getString(".name");
									params.remove(".name");
									try {
										final Function<StatsSet, ISkillCondition> conditionFunction = SkillConditionHandler.getInstance().getHandlerFactory(conditionName);
										if (conditionFunction != null) {
											skill.addCondition(skillConditionScope, conditionFunction.apply(params));
										} else {
											log.warn("Missing condition for Skill Id[{}] Level[{}] SubLevel[{}] Effect Scope[{}] Effect Name[{}]", statsSet.getInt(".id"), level, subLevel, skillConditionScope, conditionName);
										}
									} catch (Exception e) {
										log.warn("Failed loading condition for Skill Id[{}] Level[{}] SubLevel[{}] Condition Scope[{}] Condition Name[{}]", statsSet.getInt(".id"), level, subLevel, skillConditionScope, conditionName, e);
									}
								}));

								_skills.put(getSkillHashCode(skill), skill);
								_skillsMaxLevel.merge(skill.getId(), skill.getLevel(), Integer::max);
								if (((skill.getSubLevel() % 1000) == 1) && !skill.isPassive()) {
									EnchantSkillGroupsData.getInstance().addRouteForSkill(skill.getId(), skill.getLevel(), skill.getSubLevel());
								}
							});
						});
					}
				}
			}
		}
	}

	private <T> void forEachNamedParamInfoParam(Map<T, List<NamedParamInfo>> paramInfo, int level, int subLevel, BiConsumer<T, StatsSet> consumer) {
		paramInfo.forEach((scope, namedParamInfos) ->
		{
			namedParamInfos.forEach(namedParamInfo ->
			{
				if (((namedParamInfo.getFromLevel() == null) && (namedParamInfo.getToLevel() == null)) || ((namedParamInfo.getFromLevel() <= level) && (namedParamInfo.getToLevel() >= level))) {
					if (((namedParamInfo.getFromSubLevel() == null) && (namedParamInfo.getToSubLevel() == null)) || ((namedParamInfo.getFromSubLevel() <= subLevel) && (namedParamInfo.getToSubLevel() >= subLevel))) {
						final StatsSet params = Optional.ofNullable(namedParamInfo.getInfo().getOrDefault(level, Collections.emptyMap()).get(subLevel)).orElseGet(StatsSet::new);
						namedParamInfo.getInfo().getOrDefault(level, Collections.emptyMap()).getOrDefault(-1, StatsSet.EMPTY_STATSET).getSet().forEach(params.getSet()::putIfAbsent);
						namedParamInfo.getInfo().getOrDefault(-1, Collections.emptyMap()).getOrDefault(-1, StatsSet.EMPTY_STATSET).getSet().forEach(params.getSet()::putIfAbsent);
						params.set(".name", namedParamInfo.getName());
						consumer.accept(scope, params);
					}
				}
			});
		});
	}

	private NamedParamInfo parseNamedParamInfo(Node node, Map<String, Map<Integer, Map<Integer, Object>>> variableValues) {
		final NamedNodeMap attributes = node.getAttributes();
		final String name = parseString(attributes, "name");
		final Integer level = parseInteger(attributes, "level");
		final Integer fromLevel = parseInteger(attributes, "fromLevel", level);
		final Integer toLevel = parseInteger(attributes, "toLevel", level);
		final Integer subLevel = parseInteger(attributes, "subLevel");
		final Integer fromSubLevel = parseInteger(attributes, "fromSubLevel", subLevel);
		final Integer toSubLevel = parseInteger(attributes, "toSubLevel", subLevel);
		final Map<Integer, Map<Integer, StatsSet>> info = new HashMap<>();
		for (node = node.getFirstChild(); node != null; node = node.getNextSibling()) {
			if (!node.getNodeName().equals("#text")) {
				parseInfo(node, variableValues, info);
			}
		}
		return new NamedParamInfo(name, fromLevel, toLevel, fromSubLevel, toSubLevel, info);
	}

	private void parseInfo(Node node, Map<String, Map<Integer, Map<Integer, Object>>> variableValues, Map<Integer, Map<Integer, StatsSet>> info) {
		Map<Integer, Map<Integer, Object>> values = parseValues(node);
		final Object generalValue = values.getOrDefault(-1, Collections.emptyMap()).get(-1);
		if (generalValue != null) {
			final String stringGeneralValue = String.valueOf(generalValue);
			if (stringGeneralValue.startsWith("@")) {
				Map<Integer, Map<Integer, Object>> variableValue = variableValues.get(stringGeneralValue);
				if (variableValue != null) {
					values = variableValue;
				} else {
					throw new IllegalArgumentException("undefined variable " + stringGeneralValue);
				}
			}
		}

		values.forEach((level, subLevelMap) ->
		{
			subLevelMap.forEach((subLevel, value) ->
			{
				info.computeIfAbsent(level, k -> new HashMap<>()).computeIfAbsent(subLevel, k -> new StatsSet()).set(node.getNodeName(), value);
			});
		});
	}

	private Map<Integer, Map<Integer, Object>> parseValues(Node node) {
		final Map<Integer, Map<Integer, Object>> values = new HashMap<>();
		Object parsedValue = parseValue(node, true, false, Collections.emptyMap());
		if (parsedValue != null) {
			values.computeIfAbsent(-1, k -> new HashMap<>()).put(-1, parsedValue);
		} else {
			for (node = node.getFirstChild(); node != null; node = node.getNextSibling()) {
				if (node.getNodeName().equalsIgnoreCase("value")) {
					final NamedNodeMap attributes = node.getAttributes();
					final Integer level = parseInteger(attributes, "level");
					if (level != null) {
						parsedValue = parseValue(node, false, false, Collections.emptyMap());
						if (parsedValue != null) {
							final Integer subLevel = parseInteger(attributes, "subLevel", -1);
							values.computeIfAbsent(level, k -> new HashMap<>()).put(subLevel, parsedValue);
						}
					} else {
						final int fromLevel = parseInteger(attributes, "fromLevel");
						final int toLevel = parseInteger(attributes, "toLevel");
						final int fromSubLevel = parseInteger(attributes, "fromSubLevel", -1);
						final int toSubLevel = parseInteger(attributes, "toSubLevel", -1);
						for (int i = fromLevel; i <= toLevel; i++) {
							for (int j = fromSubLevel; j <= toSubLevel; j++) {
								Map<Integer, Object> subValues = values.computeIfAbsent(i, k -> new HashMap<>());
								Map<String, Double> variables = new HashMap<>();
								variables.put("index", (i - fromLevel) + 1d);
								variables.put("subIndex", (j - fromSubLevel) + 1d);
								Object base = values.getOrDefault(i, Collections.emptyMap()).get(-1);
								if ((base != null) && !(base instanceof StatsSet)) {
									variables.put("base", Double.parseDouble(String.valueOf(base)));
								}
								parsedValue = parseValue(node, false, false, variables);
								if (parsedValue != null) {
									subValues.put(j, parsedValue);
								}
							}
						}
					}
				}
			}
		}
		return values;
	}

	static Object parseValue(Node node, boolean blockValue, boolean parseAttributes, Map<String, Double> variables) {
		StatsSet statsSet = null;
		List<Object> list = null;
		Object text = null;
		if (parseAttributes && (!node.getNodeName().equals("value") || !blockValue) && (node.getAttributes().getLength() > 0)) {
			statsSet = new StatsSet();
			parseAttributes(node.getAttributes(), "", statsSet, variables);
		}
		for (node = node.getFirstChild(); node != null; node = node.getNextSibling()) {
			final String nodeName = node.getNodeName();
			switch (node.getNodeName()) {
				case "#text": {
					final String value = node.getNodeValue().trim();
					if (!value.isEmpty()) {
						text = parseNodeValue(value, variables);
					}
					break;
				}
				case "item": {
					if (list == null) {
						list = new LinkedList<>();
					}

					final Object value = parseValue(node, false, true, variables);
					if (value != null) {
						list.add(value);
					}
					break;
				}
				case "value": {
					if (blockValue) {
						break;
					}
				}
				default: {
					final Object value = parseValue(node, false, true, variables);
					if (value != null) {
						if (statsSet == null) {
							statsSet = new StatsSet();
						}

						statsSet.set(nodeName, value);
					}
				}
			}
		}
		if (list != null) {
			if (text != null) {
				throw new IllegalArgumentException("Text and list in same node are not allowed. Node[" + node + "]");
			}
			if (statsSet != null) {
				statsSet.set(".", list);
			} else {
				return list;
			}
		}
		if (text != null) {
			if (list != null) {
				throw new IllegalArgumentException("Text and list in same node are not allowed. Node[" + node + "]");
			}
			if (statsSet != null) {
				statsSet.set(".", text);
			} else {
				return text;
			}
		}
		return statsSet;
	}

	static private void parseAttributes(NamedNodeMap attributes, String prefix, StatsSet statsSet, Map<String, Double> variables) {
		for (int i = 0; i < attributes.getLength(); i++) {
			final Node attributeNode = attributes.item(i);
			statsSet.set(prefix + "." + attributeNode.getNodeName(), parseNodeValue(attributeNode.getNodeValue(), variables));
		}
	}

	private static void parseAttributes(NamedNodeMap attributes, String prefix, StatsSet statsSet) {
		parseAttributes(attributes, prefix, statsSet, Collections.emptyMap());
	}

	private static Object parseNodeValue(String value, Map<String, Double> variables) {
		if (value.startsWith("{") && value.endsWith("}")) {
			return new ExpressionBuilder(value).variables(variables.keySet()).build().setVariables(variables).evaluate();
		}
		return value;
	}

	public int getSkillCount() {
		return _skills.size();
	}
}