package org.l2junity.commons.model;

import lombok.Data;
import org.l2junity.commons.util.Rnd;

import java.io.Serializable;

/**
 * @author ANZO
 * @since 04.04.2017
 */
@Data
public class SessionInfo implements Serializable {
	private String accountName;
	private long loginKey;
	private int playKey;
	private long sessionExpire;

	/***
	 * Constructor using for initial SessionInfo create
	 * @param accountName player account name
	 */
	public SessionInfo(String accountName) {
		this.accountName = accountName;
		this.loginKey = Rnd.nextLong();
		this.playKey = Rnd.nextInt();
		this.sessionExpire = System.currentTimeMillis() + 240000;
	}

	/***
	 * Constructor using for wrapping exist SessionInfo parameters in packets
	 * @param accountName player account name
	 * @param loginKey login session key
	 * @param playKey game session key
	 */
	public SessionInfo(String accountName, long loginKey, int playKey) {
		this.accountName = accountName;
		this.loginKey = loginKey;
		this.playKey = playKey;
	}

	public boolean equals(SessionInfo sessionInfo) {
		return sessionInfo.getLoginKey() == loginKey
				&& sessionInfo.getPlayKey() == playKey
				&& sessionInfo.getAccountName().equals(accountName);
	}
}