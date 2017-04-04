package org.l2junity.core.configs;

import org.l2junity.commons.config.annotation.ConfigComments;
import org.l2junity.commons.config.annotation.ConfigFile;
import org.l2junity.commons.config.annotation.ConfigProperty;

/**
 * @author ANZO
 * @since 02.04.2017
 */
@ConfigFile(name = "configs/admin.properties")
public class AdminConfig {
	@ConfigProperty(name = "GMHeroAura", value = "false")
	@ConfigComments(comment = {
			"Enable GMs to have the glowing aura of a Hero character on login.",
			"Notes:",
			"	GMs can do '///hero' on themselves and get this aura voluntarily.",
			"	It's advised to keep this off due to graphic lag."
	})
	public static boolean GM_HERO_AURA;

	@ConfigProperty(name = "GMStartupBuilderHide", value = "true")
	@ConfigComments(comment = {
			"Whether GM logins in builder hide mode by default."
	})
	public static boolean GM_STARTUP_BUILDER_HIDE;

	@ConfigProperty(name = "GMStartupInvulnerable", value = "false")
	@ConfigComments(comment = {
			"Auto set invulnerable status to a GM on login."
	})
	public static boolean GM_STARTUP_INVULNERABLE;

	@ConfigProperty(name = "GMStartupInvisible", value = "false")
	@ConfigComments(comment = {
			"Auto set invisible status to a GM on login."
	})
	public static boolean GM_STARTUP_INVISIBLE;

	@ConfigProperty(name = "GMStartupSilence", value = "false")
	@ConfigComments(comment = {
			"Auto block private messages to a GM on login."
	})
	public static boolean GM_STARTUP_SILENCE;

	@ConfigProperty(name = "GMStartupAutoList", value = "false")
	@ConfigComments(comment = {
			"Auto list GMs in GM list (/gmlist) on login."
	})
	public static boolean GM_STARTUP_AUTO_LIST;

	@ConfigProperty(name = "GMStartupDietMode", value = "false")
	@ConfigComments(comment = {
			"Auto set diet mode on to a GM on login (affects your weight penalty)."
	})
	public static boolean GM_STARTUP_DIET_MODE;

	@ConfigProperty(name = "GMItemRestriction", value = "true")
	@ConfigComments(comment = {
			"Item restrictions apply to GMs as well? (True = restricted usage)"
	})
	public static boolean GM_ITEM_RESTRICTION;

	@ConfigProperty(name = "GMSkillRestriction", value = "true")
	@ConfigComments(comment = {
			"Skill restrictions apply to GMs as well? (True = restricted usage)"
	})
	public static boolean GM_SKILL_RESTRICTION;

	@ConfigProperty(name = "GMTradeRestrictedItems", value = "false")
	@ConfigComments(comment = {
			"Allow GMs to drop/trade non-tradable and quest(drop only) items"
	})
	public static boolean GM_TRADE_RESTRICTED_ITEMS;

	@ConfigProperty(name = "GMRestartFighting", value = "true")
	@ConfigComments(comment = {
			"Allow GMs to restart/exit while is fighting stance"
	})
	public static boolean GM_RESTART_FIGHTING;

	@ConfigProperty(name = "GMShowAnnouncerName", value = "false")
	@ConfigComments(comment = {
			"Show the GM's name behind an announcement made by him",
			"example: 'Announce: hi (HanWik)'"
	})
	public static boolean GM_ANNOUNCER_NAME;

	@ConfigProperty(name = "GMShowCritAnnouncerName", value = "false")
	@ConfigComments(comment = {
			"Show the GM's name before an announcement made by him",
			"example: 'Nyaran: hi'"
	})
	public static boolean GM_CRITANNOUNCER_NAME;

	@ConfigProperty(name = "GMGiveSpecialSkills", value = "false")
	@ConfigComments(comment = {
			"Give special skills for every GM",
			"7029,7041-7064,7088-7096,23238-23249 (Master's Blessing)"
	})
	public static boolean GM_GIVE_SPECIAL_SKILLS;

	@ConfigProperty(name = "GMGiveSpecialAuraSkills", value = "false")
	@ConfigComments(comment = {
			"Give special aura skills for every GM",
			"7029,23238-23249,23253-23296 (Master's Blessing)"
	})
	public static boolean GM_GIVE_SPECIAL_AURA_SKILLS;

	@ConfigProperty(name = "UseSuperHasteAsGMSpeed", value = "false")
	@ConfigComments(comment = {
			"In case you are not satisfied with the retail-like implementation of //gmspeed",
			"with this config you can rollback it to the old custom L2J version of the GM Speed."
	})
	public static boolean USE_SUPER_HASTE_AS_GM_SPEED;
}
