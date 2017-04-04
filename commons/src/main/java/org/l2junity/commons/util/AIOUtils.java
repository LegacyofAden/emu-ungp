package org.l2junity.commons.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.SocketOption;
import java.net.StandardSocketOptions;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.NetworkChannel;
import java.util.*;

/**
 * AIO utility class
 *
 * @author izen
 */
@Slf4j
public class AIOUtils {
	private static final SocketOption[] STANDARD_SOCKET_OPTIONS;

	static {
		ArrayList<SocketOption> stdSockOptions = new ArrayList<>();
		Class<StandardSocketOptions> ssoClass = StandardSocketOptions.class;
		for (Field ssoClassField : ssoClass.getDeclaredFields()) {
			if (Modifier.isStatic(ssoClassField.getModifiers()) &&
					ssoClassField.getType().isAssignableFrom(SocketOption.class)) {
				try {
					SocketOption sockOpt = (SocketOption) ssoClassField.get(null);
					stdSockOptions.add(sockOpt);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		STANDARD_SOCKET_OPTIONS = stdSockOptions.toArray(new SocketOption[stdSockOptions.size()]);
	}

	/**
	 * Parses standard socket options string.
	 * The option string must be in such format:
	 * {@code &lt;OPT1&gt;(&lt;VAL1&gt;);&lt;OPT2&gt;(&lt;VAL2&gt;);...;&lt;OPTn&gt;(&lt;VALn&gt;)}
	 *
	 * @param sockOptStr options string
	 * @return parsed options and theirs values
	 * @throws IllegalArgumentException in case of malformed options string
	 */
	public static Pair<SocketOption, Object>[] parseSocketOptions(String sockOptStr) throws IllegalArgumentException {
		Map<SocketOption, Object> options = new HashMap<SocketOption, Object>();
		StringTokenizer st = new StringTokenizer(sockOptStr, ";,");
		while (st.hasMoreTokens()) {
			String tok = st.nextToken().trim();
			if (tok.isEmpty()) {
				continue;
			}
			SocketOption key = null;
			Object val = null;
			for (SocketOption opt : STANDARD_SOCKET_OPTIONS) {
				if (tok.startsWith(opt.name())) {
					int vbi = opt.name().length();
					int vei = tok.length() - 1;
					for (; vbi < vei; vbi++) {
						if (!Character.isSpaceChar(tok.charAt(vbi))) {
							break;
						}
					}
					for (; vei > vbi; vei--) {
						if (!Character.isSpaceChar(tok.charAt(vei))) {
							break;
						}
					}
					if (vbi == vei || tok.charAt(vbi) != '(' || tok.charAt(vei) != ')') {
						continue;
					}
					String valStr = tok.substring(vbi + 1, vei).trim();
					if (opt.type() == Boolean.class) {
						key = opt;
						val = Boolean.parseBoolean(valStr);
					} else if (opt.type() == Integer.class) {
						key = opt;
						try {
							val = Integer.parseInt(valStr);
						} catch (NumberFormatException nfe) {
							throw new IllegalArgumentException("Unknown socket option type for \"" + tok + "\"", nfe);
						}
					} else {
						throw new IllegalArgumentException("Unknown socket option type for \"" + tok + "\"");
					}
				}
			}
			if (key == null || val == null) {
				throw new IllegalArgumentException("Unknown socket option for \"" + tok + "\"");
			}
			options.put(key, val);
		}

		ArrayList<Pair<SocketOption, Object>> result = new ArrayList<Pair<SocketOption, Object>>(options.size());
		for (Map.Entry<SocketOption, Object> e : options.entrySet()) {
			result.add(Pair.<SocketOption, Object>of(e.getKey(), e.getValue()));
		}
		return result.toArray(new Pair[result.size()]);
	}

	public static <TNetworkChannel extends NetworkChannel> TNetworkChannel applySocketOptions(TNetworkChannel networkChannel, Pair<SocketOption, Object>[] options) throws IOException {
		Set<SocketOption<?>> supportedOptions = networkChannel.supportedOptions();
		for (Pair<SocketOption, Object> soPair : options) {
			SocketOption option = soPair.getKey();
			Object value = soPair.getValue();
			if (!supportedOptions.contains(option)) {
				log.warn("Socket option \"{}\" is not supported.", option.name());
				continue;
			}
			networkChannel = (TNetworkChannel) networkChannel.setOption(option, value);
		}
		return networkChannel;
	}

	public static final void closeAsyncChannelSilent(AsynchronousSocketChannel socketChannel) {
		try {
			socketChannel.shutdownInput();
		} catch (IOException e) {
		}
		try {
			socketChannel.shutdownOutput();
		} catch (IOException e) {
		}
		try {
			socketChannel.close();
		} catch (IOException e) {
		}
	}
}
