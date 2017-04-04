package org.l2junity.commons.util;

import com.sun.management.HotSpotDiagnosticMXBean;
import lombok.extern.slf4j.Slf4j;

import javax.management.MBeanServer;
import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Slf4j
public final class ServerInfoUtils {
	private ServerInfoUtils() {
	}

	/**
	 * Prints a named section to log.
	 *
	 * @param sectionName Section name
	 */
	public static void printSection(String sectionName) {
		final StringBuilder sb = new StringBuilder(120);

		for (int i = 0; i < (120 - 3 - sectionName.length() - 2); i++) {
			sb.append('-');
		}

		sb.append("={ ").append(sectionName).append(" }");
		log.info(sb.toString());
	}

	public static String getDoneMessage(String s) {
		while (s.length() < 83) {
			s += " ";
		}
		s = s + " ...done";
		return s;
	}

	// some sys info utils

	/**
	 * Returns the number of CPU cores <U>currently</U> available to this
	 * application.
	 *
	 * @return available CPU cores
	 * @see Runtime#availableProcessors()
	 */
	public static int getAvailableProcessors() {
		Runtime rt = Runtime.getRuntime();
		return rt.availableProcessors();
	}

	/**
	 * Returns the name of the underlying operating system.
	 *
	 * @return OS name
	 */
	public static String getOSName() {
		return System.getProperty("os.name");
	}

	/**
	 * Returns the version info of the underlying operating system.
	 *
	 * @return OS version
	 */
	public static String getOSVersion() {
		return System.getProperty("os.version");
	}

	/**
	 * Returns the information about the underlying CPU architecture that is
	 * supported by the underlying operating system.
	 *
	 * @return OS architecture
	 */
	public static String getOSArch() {
		return System.getProperty("os.arch");
	}

	/**
	 * Returns information about application's memory usage.
	 *
	 * @return heap memory usage
	 */
	public static String[] getMemUsage() {
		return getMemoryUsageStatistics();
	}

	/**
	 * Returns a number formatted with "," delimiter
	 *
	 * @param value number
	 * @return String formatted number
	 */
	public static String formatNumber(long value) {
		return NumberFormat.getInstance(Locale.ENGLISH).format(value);
	}

	public static String[] getMemoryUsageStatistics() {
		double max = Runtime.getRuntime().maxMemory() / 1024.0; // maxMemory is the upper limit the jvm can use
		double allocated = Runtime.getRuntime().totalMemory() / 1024.0; //totalMemory the size of the current allocation pool
		double nonAllocated = max - allocated; //non allocated memory till jvm limit
		double cached = Runtime.getRuntime().freeMemory() / 1024.0; // freeMemory the unused memory in the allocation pool
		double used = allocated - cached; // really used memory
		double useable = max - used; //allocated, but non-used and non-allocated memory

		SimpleDateFormat sdf = new SimpleDateFormat("H:mm:ss");
		DecimalFormat df = new DecimalFormat(" (0.0000'%')");
		DecimalFormat df2 = new DecimalFormat(" # 'KB'");

		return new String[]{
				"+----",// ...
				"| Global Memory Informations at " + sdf.format(new Date()) + ":", // ...
				"|    |", // ...
				"| Allowed Memory:" + df2.format(max),
				"|    |= Allocated Memory:" + df2.format(allocated) + df.format(allocated / max * 100),
				"|    |= Non-Allocated Memory:" + df2.format(nonAllocated) + df.format(nonAllocated / max * 100),
				"| Allocated Memory:" + df2.format(allocated),
				"|    |= Used Memory:" + df2.format(used) + df.format(used / max * 100),
				"|    |= Unused (cached) Memory:" + df2.format(cached) + df.format(cached / max * 100),
				"| Useable Memory:" + df2.format(useable) + df.format(useable / max * 100), // ...
				"+----"};
	}

	public static long usedMemory() {
		return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576;
	}

	/**
	 * To load full static utility classes.
	 *
	 * @param clazz
	 */
	public static void load(Class<?> clazz) {
		try {
			Class.forName(clazz.getName());
		} catch (ClassNotFoundException e) {
			// should never happen
			throw new Error(e);
		}
	}

	/**
	 * Returns application lifetime in an user-friendly string.
	 *
	 * @return time since launch
	 */
	public static String getUptime() {
		final long uptimeInSec = (long) Math.ceil(ManagementFactory.getRuntimeMXBean().getUptime() / 1000.0);

		final long s = uptimeInSec / 1 % 60;
		final long m = uptimeInSec / 60 % 60;
		final long h = uptimeInSec / 3600 % 24;
		final long d = uptimeInSec / 86400;

		final StringBuilder tb = new StringBuilder();

		if (d > 0) {
			tb.append(d + " day(s), ");
		}

		if (h > 0 || tb.length() != 0) {
			tb.append(h + " hour(s), ");
		}

		if (m > 0 || tb.length() != 0) {
			tb.append(m + " minute(s), ");
		}

		if (s > 0 || tb.length() != 0) {
			tb.append(s + " second(s)");
		}

		return tb.toString();
	}

	/**
	 * Returns application lifetime in a short string.
	 *
	 * @return time since launch
	 */
	public static String getShortUptime() {
		final long uptimeInSec = (long) Math.ceil(ManagementFactory.getRuntimeMXBean().getUptime() / 1000.0);

		final long s = uptimeInSec / 1 % 60;
		final long m = uptimeInSec / 60 % 60;
		final long h = uptimeInSec / 3600 % 24;
		final long d = uptimeInSec / 86400;

		final StringBuilder tb = new StringBuilder();

		if (d > 0) {
			tb.append(d).append("d");
		}

		if (h > 0 || tb.length() != 0) {
			tb.append(h).append("h");
		}

		if (m > 0 || tb.length() != 0) {
			tb.append(m).append("m");
		}

		if (s > 0 || tb.length() != 0) {
			tb.append(s).append("s");
		}

		return tb.toString();
	}

	private static final class HotSpotDiagnosticMXBeanHolder {

		static {
			try {
				final MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
				final String mXBeanName = "com.sun.management:type=HotSpotDiagnostic";
				final Class<HotSpotDiagnosticMXBean> mXBeanInterface = HotSpotDiagnosticMXBean.class;

				INSTANCE = ManagementFactory.newPlatformMXBeanProxy(mBeanServer, mXBeanName, mXBeanInterface);
			} catch (Exception e) {
				throw new Error(e);
			}
		}

		public static final HotSpotDiagnosticMXBean INSTANCE;
	}

}
