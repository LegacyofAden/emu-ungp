/*
 * Copyright © 2016 BDO-Emu authors. All rights reserved.
 * Viewing, editing, running and distribution of this software strongly prohibited.
 * Author: xTz, Anton Lasevich, Tibald
 */

package org.l2junity.core.startup;

/**
 * @author ANZO
 * @since 27.12.2016
 */
public interface IStartupLevel {
	default void invokeDepends() {
	}
}
