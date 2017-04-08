package org.l2junity.core.startup;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ANZO
 * @since 27.12.2016
 */
@Slf4j
public enum StartupLevel implements IStartupLevel {
	BeforeStart,
	Configure,
	Threading,
	Service,
}