package org.l2junity.gameserver.data.xml.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.data.xml.IGameXmlReader;
import org.l2junity.gameserver.model.holders.MinionHolder;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ANZO
 * @since 01.04.2017
 */
@Slf4j
@StartupComponent("Data")
public class MinionData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final MinionData instance = new MinionData();

	public final Map<Integer, List<MinionHolder>> _tempMinions = new HashMap<>();

	private MinionData() {
		reload();
	}

	private void reload() {
		_tempMinions.clear();
		parseDatapackFile("data/minionData.xml");
		log.info("Loaded {} minions data.", _tempMinions.size());
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		for (Node node = doc.getFirstChild(); node != null; node = node.getNextSibling()) {
			if ("list".equals(node.getNodeName())) {
				for (Node list_node = node.getFirstChild(); list_node != null; list_node = list_node.getNextSibling()) {
					if ("npc".equals(list_node.getNodeName())) {
						final List<MinionHolder> minions = new ArrayList<>(1);
						NamedNodeMap attrs = list_node.getAttributes();
						int id = parseInteger(attrs, "id");
						for (Node npc_node = list_node.getFirstChild(); npc_node != null; npc_node = npc_node.getNextSibling()) {
							if ("minion".equals(npc_node.getNodeName())) {
								attrs = npc_node.getAttributes();
								minions.add(new MinionHolder(parseInteger(attrs, "id"), parseInteger(attrs, "count"), parseInteger(attrs, "respawnTime"), 0));
							}
						}
						_tempMinions.put(id, minions);
					}
				}
			}
		}
	}
}