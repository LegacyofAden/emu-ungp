package org.l2junity.commons.idfactory;

import lombok.Getter;

/**
 * @author ANZO
 * @since 02.04.2017
 */
public class DefaultIdFactory extends AbstractIdFactory {
	@Getter(lazy = true)
	private static final DefaultIdFactory instance = new DefaultIdFactory();

	@Override
	public void lockIds() {
		// Nothing
	}
}
