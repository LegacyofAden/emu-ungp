package org.l2junity.loginserver.network.packets.s2c;

import org.l2junity.commons.network.PacketBody;
import org.l2junity.loginserver.network.packets.LoginServerPacket;

/**
 * @author ANZO
 * @since 25.03.2017
 */
public class LoginFail extends LoginServerPacket {
	/**
	 * Message: There is a system error. Please try logging in again later.
	 */
	public static final LoginFail THERE_IS_A_SYSTEM_ERROR_PLEASE_TRY_LOGGING_IN_AGAIN_LATER = new LoginFail((byte) 0x01);

	/**
	 * Message: The username and password do not match. Please check your account information and try logging in again.
	 */
	public static final LoginFail THE_USERNAME_AND_PASSWORD_DO_NOT_MATCH_PLEASE_CHECK_YOUR_ACCOUNT_INFORMATION_AND_TRY_LOGGING_IN_AGAIN = new LoginFail((byte) 0x02);

	/**
	 * Message: The username and password do not match. Please check your account information and try logging in again.
	 */
	public static final LoginFail THE_USERNAME_AND_PASSWORD_DO_NOT_MATCH_PLEASE_CHECK_YOUR_ACCOUNT_INFORMATION_AND_TRY_LOGGING_IN_AGAIN2 = new LoginFail((byte) 0x03);

	/**
	 * Message: Access failed. Please try again later. .
	 */
	public static final LoginFail ACCESS_FAILED_PLEASE_TRY_AGAIN_LATER = new LoginFail((byte) 0x04);

	/**
	 * Message: Incorrect account information. Please inquire through the (font color='#FFDF4C')Lineage II Customer Service Center(/font) or the (font color='#FFDF4C')1:1 support(/font) in the official website.
	 */
	public static final LoginFail INCORRECT_ACCOUNT_INFORMATION_PLEASE_INQUIRE_THROUGH_THE_LINEAGE_II_CUSTOMER_SERVICE_CENTER_OR_THE_1_1_SUPPORT_IN_THE_OFFICIAL_WEBSITE = new LoginFail((byte) 0x05);

	/**
	 * Message: Access failed. Please try again later. .
	 */
	public static final LoginFail ACCESS_FAILED_PLEASE_TRY_AGAIN_LATER2 = new LoginFail((byte) 0x06);

	/**
	 * Message: Account is already in use.
	 */
	public static final LoginFail ACCOUNT_IS_ALREADY_IN_USE = new LoginFail((byte) 0x07);

	/**
	 * Message: Access failed. Please try again later. .
	 */
	public static final LoginFail ACCESS_FAILED_PLEASE_TRY_AGAIN_LATER3 = new LoginFail((byte) 0x08);

	/**
	 * Message: Access failed. Please try again later. .
	 */
	public static final LoginFail ACCESS_FAILED_PLEASE_TRY_AGAIN_LATER4 = new LoginFail((byte) 0x09);

	/**
	 * Message: Access failed. Please try again later. .
	 */
	public static final LoginFail ACCESS_FAILED_PLEASE_TRY_AGAIN_LATER5 = new LoginFail((byte) 0x0A);

	/**
	 * Message: Access failed. Please try again later. .
	 */
	public static final LoginFail ACCESS_FAILED_PLEASE_TRY_AGAIN_LATER6 = new LoginFail((byte) 0x0B);

	/**
	 * Message: In order to play Lineage II, you must be (font color='#FFDF4C')Ages 15 or above(/font). You must be (font color='#FFDF4C')18 or above(/font) in order to use the PvP servers.
	 */
	public static final LoginFail IN_ORDER_TO_PLAY_LINEAGE_II_YOU_MUST_BE_AGES_15_OR_ABOVE_YOU_MUST_BE_18_OR_ABOVE_IN_ORDER_TO_USE_THE_PVP_SERVERS = new LoginFail((byte) 0x0C);

	/**
	 * Message: Access failed. Please try again later. .
	 */
	public static final LoginFail ACCESS_FAILED_PLEASE_TRY_AGAIN_LATER7 = new LoginFail((byte) 0x0D);

	/**
	 * Message: Access failed. Please try again later. .
	 */
	public static final LoginFail ACCESS_FAILED_PLEASE_TRY_AGAIN_LATER8 = new LoginFail((byte) 0x0E);

	/**
	 * Message: Due to high server traffic, your login attempt has failed. Please try again soon.
	 */
	public static final LoginFail DUE_TO_HIGH_SERVER_TRAFFIC_YOUR_LOGIN_ATTEMPT_HAS_FAILED_PLEASE_TRY_AGAIN_SOON = new LoginFail((byte) 0x0F);

	/**
	 * Message: We are currently undergoing game server maintenance. Please log in again later.
	 */
	public static final LoginFail WE_ARE_CURRENTLY_UNDERGOING_GAME_SERVER_MAINTENANCE_PLEASE_LOG_IN_AGAIN_LATER = new LoginFail((byte) 0x10);

	/**
	 * Message: Please login after changing your temporary password.
	 */
	public static final LoginFail PLEASE_LOGIN_AFTER_CHANGING_YOUR_TEMPORARY_PASSWORD = new LoginFail((byte) 0x11);

	/**
	 * Message: Your game time has expired. You can not login. To continue playing, please purchase Lineage II either directly from the PlayNC Store or from any leading games retailer.
	 */
	public static final LoginFail YOUR_GAME_TIME_HAS_EXPIRED_YOU_CAN_NOT_LOGIN_TO_CONTINUE_PLAYING_PLEASE_PURCHASE_LINEAGE_II_EITHER_DIRECTLY_FROM_THE_PLAYNC_STORE_OR_FROM_ANY_LEADING_GAMES_RETAILER = new LoginFail((byte) 0x12);

	/**
	 * Message: There is no time left on this account.
	 */
	public static final LoginFail THERE_IS_NO_TIME_LEFT_ON_THIS_ACCOUNT = new LoginFail((byte) 0x13);

	/**
	 * Message: System error.
	 */
	public static final LoginFail SYSTEM_ERROR = new LoginFail((byte) 0x14);

	/**
	 * Message: Access failed.
	 */
	public static final LoginFail ACCESS_FAILED = new LoginFail((byte) 0x15);

	/**
	 * Message: Game connection attempted through a restricted IP.
	 */
	public static final LoginFail GAME_CONNECTION_ATTEMPTED_THROUGH_A_RESTRICTED_IP = new LoginFail((byte) 0x16);

	/**
	 * Message: This week's usage time has finished.
	 */
	public static final LoginFail THIS_WEEK_S_USAGE_TIME_HAS_FINISHED = new LoginFail((byte) 0x1E);

	/**
	 * Message: Please enter the card number for number
	 */
	public static final LoginFail PLEASE_ENTER_THE_CARD_NUMBER_FOR_NUMBER = new LoginFail((byte) 0x1F);

	/**
	 * Message: Users who did not complete the (font color='#FFDF4C')Age 18 Verification(/font) may not login between (font color='#FFDF4C')10PM(/font) and (font color='#FFDF4C')6AM(/font) the next day.
	 */
	public static final LoginFail USERS_WHO_DID_NOT_COMPLETE_THE_AGE_18_VERIFICATION_MAY_NOT_LOGIN_BETWEEN_10PM_AND_6AM_THE_NEXT_DAY = new LoginFail((byte) 0x20);

	/**
	 * Message: This server cannot be accessed with the coupon you are using.
	 */
	public static final LoginFail THIS_SERVER_CANNOT_BE_ACCESSED_WITH_THE_COUPON_YOU_ARE_USING = new LoginFail((byte) 0x21);

	/**
	 * Message: You are using a computer that does not allow you to log in with two accounts at the same time.
	 */
	public static final LoginFail YOU_ARE_USING_A_COMPUTER_THAT_DOES_NOT_ALLOW_YOU_TO_LOG_IN_WITH_TWO_ACCOUNTS_AT_THE_SAME_TIME = new LoginFail((byte) 0x23);

	/**
	 * Message: Your account is currently inactive because you have not logged into the game for some time. You may reactivate your account by visit Lineage II's Support Website (https://support.lineage2.com).
	 */
	public static final LoginFail YOUR_ACCOUNT_IS_CURRENTLY_INACTIVE_BECAUSE_YOU_HAVE_NOT_LOGGED_INTO_THE_GAME_FOR_SOME_TIME_YOU_MAY_REACTIVATE_YOUR_ACCOUNT_BY_VISIT_LINEAGE_II_S_SUPPORT_WEBSITE_HTTPS_SUPPORT_LINEAGE2_COM = new LoginFail((byte) 0x24);

	/**
	 * Message: Your account has not yet been authenticated. Please visit the (font color='#FFDF4C')homepage((/font)(font color='#6699FF')(a href='asfunction:homePage')https://support.lineage2.com)(/a)(/font) and confirm your account authentication.
	 */
	public static final LoginFail YOUR_ACCOUNT_HAS_NOT_YET_BEEN_AUTHENTICATED_PLEASE_VISIT_THE_HOMEPAGE_HTTPS_SUPPORT_LINEAGE2_COM_AND_CONFIRM_YOUR_ACCOUNT_AUTHENTICATION = new LoginFail((byte) 0x25);

	/**
	 * Message: Your account has not completed the (font color='#FFDF4C')Parental Agreement(/font).(br) Please complete the (font color='#FFDF4C')Parental Agreement(/font) before logging in.
	 */
	public static final LoginFail YOUR_ACCOUNT_HAS_NOT_COMPLETED_THE_PARENTAL_AGREEMENT_PLEASE_COMPLETE_THE_PARENTAL_AGREEMENT_BEFORE_LOGGING_IN = new LoginFail((byte) 0x26);

	/**
	 * Message: This account has declined the User Agreement or has requested for membership withdrawal. Please try again after (br)(font color='#FFDF4C')cancelling the Game Agreement declination(/font) or (font color='#FFDF4C')cancelling the membership withdrawal request(/font).
	 */
	public static final LoginFail THIS_ACCOUNT_HAS_DECLINED_THE_USER_AGREEMENT_OR_HAS_REQUESTED_FOR_MEMBERSHIP_WITHDRAWAL_PLEASE_TRY_AGAIN_AFTER_CANCELLING_THE_GAME_AGREEMENT_DECLINATION_OR_CANCELLING_THE_MEMBERSHIP_WITHDRAWAL_REQUEST = new LoginFail((byte) 0x27);

	/**
	 * Message: All permissions on your account are restricted. (br)Please go to http://us.ncsoft.com/en/ for details.
	 */
	public static final LoginFail ALL_PERMISSIONS_ON_YOUR_ACCOUNT_ARE_RESTRICTED_PLEASE_GO_TO_HTTP_US_NCSOFT_COM_EN_FOR_DETAILS = new LoginFail((byte) 0x28);

	/**
	 * Message: You must change your password and secret question in order to log in. Please the (font color='#FFDF4C')Lineage II Support Website(/font)((font color='#6699FF')(a href='asfunction:homePage')https://support.lineage2.com(/a)(/font)) and (font color='#FFDF4C')change the password and
	 * secret question(/font).
	 */
	public static final LoginFail YOU_MUST_CHANGE_YOUR_PASSWORD_AND_SECRET_QUESTION_IN_ORDER_TO_LOG_IN_PLEASE_THE_LINEAGE_II_SUPPORT_WEBSITE_HTTPS_SUPPORT_LINEAGE2_COM_AND_CHANGE_THE_PASSWORD_AND_SECRET_QUESTION = new LoginFail((byte) 0x29);

	/**
	 * Message: You are currently logged into 10 of your accounts and can no longer access your other accounts.
	 */
	public static final LoginFail YOU_ARE_CURRENTLY_LOGGED_INTO_10_OF_YOUR_ACCOUNTS_AND_CAN_NO_LONGER_ACCESS_YOUR_OTHER_ACCOUNTS = new LoginFail((byte) 0x2A);

	/**
	 * Message: Your master account has been restricted.
	 */
	public static final LoginFail YOUR_MASTER_ACCOUNT_HAS_BEEN_RESTRICTED = new LoginFail((byte) 0x2B);

	/**
	 * Message: Authentication has failed as you have entered an incorrect authentication number or did not enter the authentication number. If you fail authentication (font color='#FFDF4C')3 times(/font) in a row, game access will be restricted for (font color='#FFDF4C')30 minutes(/font).
	 */
	public static final LoginFail AUTHENTICATION_HAS_FAILED_AS_YOU_HAVE_ENTERED_AN_INCORRECT_AUTHENTICATION_NUMBER_OR_DID_NOT_ENTER_THE_AUTHENTICATION_NUMBER_IF_YOU_FAIL_AUTHENTICATION_3_TIMES_IN_A_ROW_GAME_ACCESS_WILL_BE_RESTRICTED_FOR_30_MINUTES = new LoginFail((byte) 0x2E);

	/**
	 * Message: Due to problems with communications, our telephone certification service is currently unavailable. Please try again later.
	 */
	public static final LoginFail DUE_TO_PROBLEMS_WITH_COMMUNICATIONS_OUR_TELEPHONE_CERTIFICATION_SERVICE_IS_CURRENTLY_UNAVAILABLE_PLEASE_TRY_AGAIN_LATER = new LoginFail((byte) 0x2F);

	/**
	 * Message: Due to problems with communications, telephone signals are being delayed. Please try again later.
	 */
	public static final LoginFail DUE_TO_PROBLEMS_WITH_COMMUNICATIONS_TELEPHONE_SIGNALS_ARE_BEING_DELAYED_PLEASE_TRY_AGAIN_LATER = new LoginFail((byte) 0x30);

	/**
	 * Message: The certification failed because the line was busy or the call was not received. Please try again.
	 */
	public static final LoginFail THE_CERTIFICATION_FAILED_BECAUSE_THE_LINE_WAS_BUSY_OR_THE_CALL_WAS_NOT_RECEIVED_PLEASE_TRY_AGAIN = new LoginFail((byte) 0x31);

	/**
	 * Message: An unexpected error has occured. Please contact our Customer Support Team at https://support.lineage2.com
	 */
	public static final LoginFail AN_UNEXPECTED_ERROR_HAS_OCCURED_PLEASE_CONTACT_OUR_CUSTOMER_SUPPORT_TEAM_AT_HTTPS_SUPPORT_LINEAGE2_COM = new LoginFail((byte) 0x32);

	/**
	 * Message: The telephone certification service is currently being checked. Please try again later.
	 */
	public static final LoginFail THE_TELEPHONE_CERTIFICATION_SERVICE_IS_CURRENTLY_BEING_CHECKED_PLEASE_TRY_AGAIN_LATER = new LoginFail((byte) 0x33);

	/**
	 * Message: Due to heavy volume, the telephone certification service cannot be used at this time. Please try again later.
	 */
	public static final LoginFail DUE_TO_HEAVY_VOLUME_THE_TELEPHONE_CERTIFICATION_SERVICE_CANNOT_BE_USED_AT_THIS_TIME_PLEASE_TRY_AGAIN_LATER = new LoginFail((byte) 0x34);

	/**
	 * Message: An unexpected error has occured. Please contact our Customer Support Team at https://support.lineage2.com
	 */
	public static final LoginFail AN_UNEXPECTED_ERROR_HAS_OCCURED_PLEASE_CONTACT_OUR_CUSTOMER_SUPPORT_TEAM_AT_HTTPS_SUPPORT_LINEAGE2_COM2 = new LoginFail((byte) 0x35);

	/**
	 * Message: If you fail authentication (font color='#FFDF4C')3 times(/font) in a row, game access will be restricted for (font color='#FFDF4C')30 minutes(/font). Please try again later.
	 */
	public static final LoginFail IF_YOU_FAIL_AUTHENTICATION_3_TIMES_IN_A_ROW_GAME_ACCESS_WILL_BE_RESTRICTED_FOR_30_MINUTES_PLEASE_TRY_AGAIN_LATER = new LoginFail((byte) 0x36);

	/**
	 * Message: The number of uses of the daily telephone certification service has been exceeded.
	 */
	public static final LoginFail THE_NUMBER_OF_USES_OF_THE_DAILY_TELEPHONE_CERTIFICATION_SERVICE_HAS_BEEN_EXCEEDED = new LoginFail((byte) 0x37);

	/**
	 * Message: Telephone certification is already underway. Please try again later.
	 */
	public static final LoginFail TELEPHONE_CERTIFICATION_IS_ALREADY_UNDERWAY_PLEASE_TRY_AGAIN_LATER = new LoginFail((byte) 0x38);

	/**
	 * Message: You can't log in with an unregistered PC.
	 */
	public static final LoginFail YOU_CAN_T_LOG_IN_WITH_AN_UNREGISTERED_PC = new LoginFail((byte) 0x3B);

	/**
	 * Message: That account is pending email authentication. Please verify authentication email with registered email account.
	 */
	public static final LoginFail THAT_ACCOUNT_IS_PENDING_EMAIL_AUTHENTICATION_PLEASE_VERIFY_AUTHENTICATION_EMAIL_WITH_REGISTERED_EMAIL_ACCOUNT = new LoginFail((byte) 0x48);

	/**
	 * Message: The NC OTP number is incorrect. Please check the number and enter it again.
	 */
	public static final LoginFail THE_NC_OTP_NUMBER_IS_INCORRECT_PLEASE_CHECK_THE_NUMBER_AND_ENTER_IT_AGAIN = new LoginFail((byte) 0x49);

	/**
	 * Message: Could not connect to Authentication Server. Please try again later.
	 */
	public static final LoginFail COULD_NOT_CONNECT_TO_AUTHENTICATION_SERVER_PLEASE_TRY_AGAIN_LATER = new LoginFail((byte) 0x4A);

	/**
	 * Message: Failed to view the rank.
	 */
	public static final LoginFail FAILED_TO_VIEW_THE_RANK = new LoginFail((byte) 0x4B);

	/**
	 * Message: The account has been blocked because OTP verification failed.
	 */
	public static final LoginFail THE_ACCOUNT_HAS_BEEN_BLOCKED_BECAUSE_OTP_VERIFICATION_FAILED = new LoginFail((byte) 0xD2);

	/**
	 * Message: There is an error in OTP system.
	 */
	public static final LoginFail THERE_IS_AN_ERROR_IN_OTP_SYSTEM = new LoginFail((byte) 0xD3);

	private int reason;

	LoginFail(int reason) {
		this.reason = reason;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		body.writeC(0x01);
		body.writeC(reason);
	}
}
