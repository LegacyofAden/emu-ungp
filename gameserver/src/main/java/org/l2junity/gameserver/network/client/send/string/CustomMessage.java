package org.l2junity.gameserver.network.client.send.string;

import org.l2junity.gameserver.model.Language;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.network.client.send.SystemMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hack
 * Date: 03.04.2017 8:09
 */
public enum CustomMessage {
    YOU_CANNOT_CHEAT_YOUR_WAY_OUT_OF_HERE_YOU_MUST_WAIT_UNTIL_YOUR_JAIL_TIME_IS_OVER,
    PRICE_OF_$_HAS_BEEN_CHANGED_TO_$,
    ;

    private Map<Language, String> messages;

    CustomMessage() {
        messages = new HashMap<>(5);
    }

    public void addMessage(Language lang, String message) {
        messages.put(lang, message);
    }

    public String get(Language lang, String ... args) {
        String message = messages.get(lang);
        if (message == null)
            return "Error: message not found for your language.";
        if (args.length == 0)
            return message;
        for (int i = 0; i < args.length; i++)
            message = message.replaceFirst("%s%", args[i]);
        return message;
    }

    public void send(PlayerInstance player, String ... args) {
        player.sendPacket(SystemMessage.sendString(get(player.getLang(), args)));
    }
}
