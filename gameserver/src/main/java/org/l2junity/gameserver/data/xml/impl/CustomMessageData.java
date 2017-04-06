package org.l2junity.gameserver.data.xml.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.data.xml.IGameXmlReader;
import org.l2junity.gameserver.model.Language;
import org.l2junity.gameserver.network.packets.s2c.string.CustomMessage;
import org.w3c.dom.Document;

import java.nio.file.Path;

/**
 * Created by Hack
 * Date: 03.04.2017 7:57
 */
@Slf4j
@StartupComponent(value = "Data")
public class CustomMessageData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final CustomMessageData instance = new CustomMessageData();

	CustomMessageData() {
		reload();
	}

	private void reload() {
		parseDatapackFile("data/message/custom_messages.xml");
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		forEach(doc, "list", listNode -> forEach(listNode, "message", msgNode -> {
			try {
				CustomMessage message = CustomMessage.valueOf(parseString(msgNode.getAttributes(), "name"));
				forEach(msgNode, "lang", lang -> {
					message.addMessage(Language.valueOfShort(parseString(lang.getAttributes(), "name")),
							lang.getTextContent());
				});
			} catch (IllegalArgumentException e) {
				log.warn("Incorrect custom message: " + parseString(msgNode.getAttributes(), "name") + e.getMessage());
			}
		}));
	}
}
