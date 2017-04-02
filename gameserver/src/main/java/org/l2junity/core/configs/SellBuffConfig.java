package org.l2junity.core.configs;

import org.l2junity.commons.config.annotation.ConfigComments;
import org.l2junity.commons.config.annotation.ConfigFile;
import org.l2junity.commons.config.annotation.ConfigProperty;

/**
 * @author ANZO
 * @since 01.04.2017
 */
@ConfigFile(name = "configs/mods/sellbuff.properties")
public class SellBuffConfig {
	@ConfigProperty(name = "SellBuffEnable", value = "false")
	@ConfigComments(comment = {
			"Enable/Disable selling buffs"
	})
	public static boolean SELLBUFF_ENABLED;

	@ConfigProperty(name = "MpCostMultipler", value = "1")
	@ConfigComments(comment = {
			"Multipler for mana cost of buffs"
	})
	public static int SELLBUFF_MP_MULTIPLER;

	@ConfigProperty(name = "PaymentID", value = "57")
	@ConfigComments(comment = {
			"Payment for Sell Buff System, can be changed to custom server coin"
	})
	public static int SELLBUFF_PAYMENT_ID;

	@ConfigProperty(name = "MinimalPrice", value = "100000")
	@ConfigComments(comment = {
			"Minimal price of every buff"
	})
	public static long SELLBUFF_MIN_PRICE;

	@ConfigProperty(name = "MaximalPrice", value = "100000000")
	@ConfigComments(comment = {
			"Maximal price of every buff"
	})
	public static long SELLBUFF_MAX_PRICE;

	@ConfigProperty(name = "MaxBuffs", value = "20")
	@ConfigComments(comment = {
			"Maximum count of buffs in sell list"
	})
	public static int SELLBUFF_MAX_BUFFS;
}