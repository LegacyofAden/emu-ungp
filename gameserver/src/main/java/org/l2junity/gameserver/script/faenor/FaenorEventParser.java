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
package org.l2junity.gameserver.script.faenor;

import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.gameserver.script.DateRange;
import org.l2junity.gameserver.script.Parser;
import org.l2junity.gameserver.script.ParserFactory;
import org.l2junity.gameserver.script.ScriptEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

import javax.script.ScriptContext;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Luis Arias
 */
public class FaenorEventParser extends FaenorParser {
	static Logger _log = LoggerFactory.getLogger(FaenorEventParser.class);
	private DateRange _eventDates = null;

	@Override
	public void parseScript(final Node eventNode, ScriptContext context) {
		String ID = attribute(eventNode, "ID");
		_eventDates = DateRange.parse(attribute(eventNode, "Active"), DATE_FORMAT);

		Date currentDate = new Date();
		if (_eventDates.getEndDate().before(currentDate)) {
			_log.info("Event ID: (" + ID + ") has passed... Ignored.");
			return;
		}

		if (_eventDates.getStartDate().after(currentDate)) {
			_log.info("Event ID: (" + ID + ") is not active yet... Ignored.");
			ThreadPool.getInstance().scheduleGeneral(() -> parseEventDropAndMessage(eventNode), _eventDates.getStartDate().getTime() - currentDate.getTime(), TimeUnit.MILLISECONDS);
			return;
		}

		parseEventDropAndMessage(eventNode);
	}

	protected void parseEventDropAndMessage(Node eventNode) {
		for (Node node = eventNode.getFirstChild(); node != null; node = node.getNextSibling()) {
			if (isNodeName(node, "DropList")) {
				parseEventDropList(node);
			} else if (isNodeName(node, "Message")) {
				parseEventMessage(node);
			}
		}
	}

	private void parseEventMessage(Node sysMsg) {
		try {
			String type = attribute(sysMsg, "Type");
			String message = attribute(sysMsg, "Msg");

			if (type.equalsIgnoreCase("OnJoin")) {
				_bridge.onPlayerLogin(message, _eventDates);
			}
		} catch (Exception e) {
			_log.warn("Error in event parser: " + e.getMessage(), e);
		}
	}

	private void parseEventDropList(Node dropList) {
		for (Node node = dropList.getFirstChild(); node != null; node = node.getNextSibling()) {
			if (isNodeName(node, "AllDrop")) {
				parseEventDrop(node);
			}
		}
	}

	private void parseEventDrop(Node drop) {
		try {
			int[] items = Arrays.stream(attribute(drop, "Items").split("(,|-)")).mapToInt(Integer::parseInt).toArray();
			int[] count = Arrays.stream(attribute(drop, "Count").split("(,|-)")).mapToInt(Integer::parseInt).toArray();
			double chance = getPercent(attribute(drop, "Chance"));

			_bridge.addEventDrop(items, count, chance, _eventDates);
		} catch (Exception e) {
			_log.warn("ERROR(parseEventDrop):" + e.getMessage(), e);
		}
	}

	static class FaenorEventParserFactory extends ParserFactory {
		@Override
		public Parser create() {
			return (new FaenorEventParser());
		}
	}

	static {
		ScriptEngine.parserFactories.put(getParserName("Event"), new FaenorEventParserFactory());
	}
}
