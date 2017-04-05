package org.l2junity.gameserver.network.client.send.string;

import org.l2junity.gameserver.model.Language;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.client.send.SystemMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by Hack
 * Date: 03.04.2017 8:09
 */
public enum CustomMessage {
    YOU_CANNOT_CHEAT_YOUR_WAY_OUT_OF_HERE_YOU_MUST_WAIT_UNTIL_YOUR_JAIL_TIME_IS_OVER,
    PRICE_OF_$_HAS_BEEN_CHANGED_TO_$,
    THE_NEXT_AUCTION_WILL_BEGIN_ON_THE_$,
    YOU_CAN_NOT_OBSERVE_GAMES_WHILE_REGISTERED_ON_AN_EVENT,
    YOUR_LIST_OF_BUFFS_IS_EMPTY_PLEASE_ADD_SOME_BUFFS_FIRST,
    YOUR_TITLE_CANNOT_EXCEED_29_CHARACTERS_IN_LENGTH_PLEASE_TRY_AGAIN,
    TOO_BIG_PRICE_MAXIMAL_PRICE_IS_$,
    $_SKIPPING_WAITING_TIME,
    $_YOU_CANT_SKIP_WAITING_TIME_RIGHT_NOW,
    $_ANTHARAS_HAS_BEEN_RESPAWNED,
    $_YOU_CANT_RESPAWN_ANTHARAS_WHILE_ANTHARAS_IS_ALIVE,
    $_ALL_MINIONS_HAS_BEEN_DELETED,
    $_YOU_CANT_DESPAWN_MINIONS_RIGHT_NOW,
    $_FIGHT_HAS_BEEN_ABORTED,
    $_YOU_CANT_ABORT_FIGHT_RIGHT_NOW,

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

    public void send(Player player, String ... args) {
        player.sendPacket(SystemMessage.sendString(get(player.getLang(), args)));
    }

    public void send(Player player, Object ... args) {
        if (args.length > 0) {
            String[] arr = new String[args.length];
            for (int i = 0; i < args.length; i++)
                arr[i] = args[i].toString();
            send(player, arr);
        }
        send(player);
    }

    public static boolean contains(String value) {
        return Stream.of(values()).anyMatch(e -> e.name().equals(value));
    }
}
