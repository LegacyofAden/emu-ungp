package org.l2junity.core.configs;

import org.l2junity.commons.config.annotation.ConfigComments;
import org.l2junity.commons.config.annotation.ConfigFile;
import org.l2junity.commons.config.annotation.ConfigProperty;
import org.l2junity.commons.util.BasePathProvider;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author ANZO
 * @since 01.04.2017
 */
@ConfigFile(name = "configs/gameserver.properties")
public class GameserverConfig {
	@ConfigProperty(name = "RequestServerID", value = "1")
	@ConfigComments(comment = {
			"This is the server ID that the Game Server will request.",
			"Example: 1 = Bartz"
	})
	public static int SERVER_ID;

	@ConfigProperty(name = "DatapackRoot", value = ".")
	@ConfigComments(comment = {
			"Datapack root directory.",
			"Defaults to current directory from which the server is started unless the below line is uncommented.",
			"WARNING: <u><b><font color='red'>If the specified path is invalid, it will lead to multiple errors!</font></b></u>"
	})
	public static Path DATAPACK_ROOT;

	@ConfigProperty(name = "LoginHost", value = "127.0.0.1")
	@ConfigComments(comment = {
			"Where's the Login server this gameserver should connect to",
			"WARNING: <u><b><font color='red'>Please don't change default IPs here if you don't know what are you doing!</font></b></u>",
			"WARNING: <u><b><font color='red'>External/Internal IPs are now inside 'ipconfig.xml' file.</font></b></u>"
	})
	public static String GAME_SERVER_LOGIN_HOST;

	@ConfigProperty(name = "LoginPort", value = "9014")
	@ConfigComments(comment = "TCP port the login server listen to for gameserver connection requests")
	public static int GAME_SERVER_LOGIN_PORT;

	@ConfigProperty(name = "AllowedProtocolRevisions", value = "64")
	@ConfigComments(comment = {
			"Numbers of protocol revisions that server allows to connect.",
			"WARNING: <u><b><font color=\"red\">Changing the protocol revision may result in incompatible communication and many errors in game!</font></b></u>",
	})
	public static List<Integer> PROTOCOL_LIST = new ArrayList<>();

	@ConfigProperty(name = "MaximumOnlineUsers", value = "100")
	@ConfigComments(comment = "Define how many players are allowed to play simultaneously on your server.")
	public static int MAXIMUM_ONLINE_USERS;

	@ConfigProperty(name = "CnameTemplate", value = ".*")
	@ConfigComments(comment = {
			"Character name template.",
			"Examples:",
			"CnameTemplate = [A-Z][a-z]{3,3}[A-Za-z0-9]*",
			"The above setting will allow names with first capital letter, next three small letters,",
			"and any letter (case insensitive) or number, like OmfgWTF1",
			"CnameTemplate = [A-Z][a-z]*",
			"The above setting will allow names only of letters with first one capital, like Omfgwtf",
			"The .* (allows any symbol)",
	})
	public static Pattern CHARNAME_TEMPLATE_PATTERN;

	@ConfigProperty(name = "PetNameTemplate", value = ".*")
	@ConfigComments(comment = {
			"This setting restricts names players can give to their pets.",
			"See CnameTemplate for details"
	})
	public static String PET_NAME_TEMPLATE;

	@ConfigProperty(name = "ClanNameTemplate", value = ".*")
	@ConfigComments(comment = {
			"This setting restricts clan/subpledge names players can set.",
			"See CnameTemplate for details"
	})
	public static String CLAN_NAME_TEMPLATE;

	@ConfigProperty(name = "CharMaxNumber", value = "7")
	@ConfigComments(comment = "Maximum number of characters per account.")
	public static int MAX_CHARACTERS_NUMBER_PER_ACCOUNT;

	/**
	 * Creates a path that points to a resource in the datapack.<BR>
	 * <BR>
	 * The path is {@link #DATAPACK_ROOT}/data/[{@code elements}].<BR>
	 * The root may be accessed by pasing {@code ..} as the first element.<BR>
	 * <BR>
	 * This method does not access the file system.
	 *
	 * @param elements file tree nodes relative to the datapack location
	 * @return path to a datapack resource
	 */
	public static final Path getDatapackResource(String... elements) {
		return BasePathProvider.resolveDatapackPath(DATAPACK_ROOT, Paths.get("data", elements));
	}
}
