package org.l2junity.core.configs;

import org.l2junity.commons.config.annotation.ConfigComments;
import org.l2junity.commons.config.annotation.ConfigFile;
import org.l2junity.commons.config.annotation.ConfigProperty;
import org.l2junity.commons.network.enums.ENetBufferType;
import org.l2junity.commons.network.enums.ENetIOExecMode;
import org.l2junity.commons.network.enums.ENetPacketExecMode;

/**
 * @author ANZO
 * @since 01.04.2017
 */
@ConfigFile(name = "configs/network.properties")
public class NetworkConfig {
	@ConfigComments(comment = {"Host for server binding.", "Default: *"})
	@ConfigProperty(name = "Host", value = "*")
	public static String HOST;

	@ConfigComments(comment = {"Port for server binding.", "Default: 2106"})
	@ConfigProperty(name = "Port", value = "2106")
	public static int PORT;

	@ConfigComments(comment = {"Packet executing mode.",
			"DIRECT - The worst. Income packets are directly executed after receiving and decrypting. As packets here implemented terribly, it not only increases latency, but decrease overall performance significant.",
			"OFFLOAD - Offloads execution to a IOExecutorService(when IOExecutionMode set to POOLED) or a default ThreadPoolManager. Good option with POOLED IOExec mode. Increases throughput but also may increase context switch count.",
			"QUEUED - Same as OFFLOAD but packets are queued before execution. May decrease latency, may not.",
			"Default: DIRECT"})
	@ConfigProperty(name = "PacketExecMode", value = "DIRECT")
	public static ENetPacketExecMode PACKET_EXEC_MODE;

	@ConfigComments(comment = {"Type of buffer for income packets. DIRECT or HEAP", "Default: DIRECT"})
	@ConfigProperty(name = "RecvBufferType", value = "DIRECT")
	public static ENetBufferType RECV_BUFFER_TYPE;

	@ConfigComments(comment = {"Type of buffer for outcome packets. DIRECT or HEAP.", "Default: DIRECT"})
	@ConfigProperty(name = "SendBufferType", value = "DIRECT")
	public static ENetBufferType SEND_BUFFER_TYPE;

	@ConfigComments(comment = {"Receive buffer size.", "Default: 32768"})
	@ConfigProperty(name = "RecvBufferSize", value = "32768")
	public static int RECV_BUFFER_SIZE;

	@ConfigComments(comment = {"Send buffer size.", "Default: 65536"})
	@ConfigProperty(name = "SendBufferSize", value = "65536")
	public static int SEND_BUFFER_SIZE;

	@ConfigComments(comment = {"NetworkThread socket backlog size.",
			"See: http://www.linuxjournal.com/files/linuxjournal.com/linuxjournal/articles/023/2333/2333s2.html",
			"Default: 50"})
	@ConfigProperty(name = "ServerSocketBacklog", value = "50")
	public static int SERVER_SOCKET_BACKLOG;

	@ConfigComments(comment = {"Client socket options.",
			"SO_SNDBUF - the size of the socket's send buffer. On most systems this the size of a kernel buffer so be careful! See RFC1323.",
			"SO_RCVBUF - the size of the socket's receive buffer. On most systems this the size of a kernel buffer so be careful! See RFC1323.",
			"TCP_NODELAY - The Nagle algorithm. Enabling it increases throughput but also increases latency. See RFC1122.",
			"Default: SO_SNDBUF(8192);SO_RCVBUF(8192);TCP_NODELAY(true)"})
	@ConfigProperty(name = "ClientSocketOptions", value = "SO_SNDBUF(8192);SO_RCVBUF(8192);TCP_NODELAY(true)")
	public static String CLIENT_SOCKET_OPTIONS;

	@ConfigComments(comment = {"NetworkThread socket options.",
			"SO_REUSEADDR - if true, prevents socket from usage until all opened sockets are really closed. See RFC793.",
			"Default: SO_REUSEADDR(true);SO_RCVBUF(4096)"})
	@ConfigProperty(name = "ServerSocketOptions", value = "SO_REUSEADDR(false);SO_RCVBUF(4096)")
	public static String SERVER_SOCKET_OPTIONS;

	@ConfigComments(comment = {"IO Network thread execution fill delay.", "Default: 16"})
	@ConfigProperty(name = "IOExecutionFillDelay", value = "16")
	public static int IO_EXECUTION_FILL_DELAY;

	@ConfigComments(comment = {"IO Network thread execution mode.",
			"POOLED - All IO operations are executed in a special thread IO execution pool",
			"FIXED - All IO operations execution is spread across fixed number of treads",
			"Default: POOLED"})
	@ConfigProperty(name = "IOExecutionMode", value = "POOLED")
	public static ENetIOExecMode IO_EXECUTION_MODE;

	@ConfigComments(comment = {"Number of IO Network threads.", "Default: -1 (Processor count)"})
	@ConfigProperty(name = "IOExecutionThreadNum", value = "-1")
	public static int IO_EXECUTION_THREAD_NUM;

	@ConfigComments(comment = {"Enable logging failed to write packets.", "Default: false"})
	@ConfigProperty(name = "LogPacketWriteFailed", value = "false")
	public static boolean LOG_FAILED_WRITE_ATTEMPT;

	@ConfigComments(comment = {"Income packet header size.", "Default: 2"})
	@ConfigProperty(name = "IncomePacketHeaderSize", value = "2")
	public static byte INCOME_PACKET_HEADER_SIZE;

	@ConfigComments(comment = {"Max income packet size.", "Default: 16384"})
	@ConfigProperty(name = "MaxIncomePacketSize", value = "16384")
	public static int MAX_INCOME_PACKET_SIZE;

	@ConfigComments(comment = {"Outcome packet header size.", "Default: 2"})
	@ConfigProperty(name = "OutcomePacketHeaderSize", value = "2")
	public static byte OUTCOME_PACKET_HEADER_SIZE;

	@ConfigComments(comment = {"Max outcome packet size.", "Default: 16384"})
	@ConfigProperty(name = "MaxOutcomePacketSize", value = "16384")
	public static int MAX_OUTCOME_PACKET_SIZE;
}