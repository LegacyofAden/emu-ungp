package org.l2junity.core.startup;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.util.ServerInfoUtils;
import org.l2junity.commons.versioning.Version;
import org.l2junity.loginserver.network.LoginNetworkThread;

import java.awt.*;
import java.io.IOException;
import java.lang.management.ManagementFactory;

/**
 * @author n3k0nation, ANZO
 * @since 27.12.2016
 */
@Slf4j
@SuppressWarnings("unused")
public enum StartupLevel implements IStartupLevel {
	BeforeStart {
		@Override
		public void invokeDepends() {
			Version.getInstance().init(getClass());
		}
	},
	Configure,
	Threading,
	Database,
	Service,
	Data,
	Network,
	AfterStart {
		@Override
		public void invokeDepends() {
			System.gc();
			System.runFinalization();
			Toolkit.getDefaultToolkit().beep();

			for (String line : ServerInfoUtils.getMemUsage()) {
				log.info(line);
			}

			try {
				LoginNetworkThread.getInstance().startup();
			} catch (IOException e) {
				log.error("Error while starting network thread", e);
			}

			log.info("Server loaded in {} millisecond(s).", ServerInfoUtils.formatNumber(ManagementFactory.getRuntimeMXBean().getUptime()));
		}
	};
}