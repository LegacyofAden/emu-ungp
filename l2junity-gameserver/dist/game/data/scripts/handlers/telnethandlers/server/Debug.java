/*
 * Copyright (C) 2004-2013 L2J Unity
 * 
 * This file is part of L2J Unity.
 * 
 * L2J Unity is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J Unity is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package handlers.telnethandlers.server;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.l2junity.commons.util.SystemUtil;
import org.l2junity.commons.util.concurrent.ThreadPool;
import org.l2junity.gameserver.LoginServerThread;
import org.l2junity.gameserver.data.xml.impl.AdminData;
import org.l2junity.gameserver.enums.ItemLocation;
import org.l2junity.gameserver.instancemanager.GameTimeManager;
import org.l2junity.gameserver.model.World;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.L2MonsterInstance;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.client.send.AdminForgePacket;
import org.l2junity.gameserver.network.telnet.ITelnetCommand;
import org.l2junity.gameserver.network.telnet.TelnetServer;
import org.l2junity.gameserver.taskmanager.DecayTaskManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author UnAfraid
 */
public class Debug implements ITelnetCommand
{
	private static final Logger LOGGER = LoggerFactory.getLogger(Debug.class);
	
	private Debug()
	{
	}
	
	@Override
	public String getCommand()
	{
		return "debug";
	}
	
	@Override
	public String getUsage()
	{
		return "Debug <decay/packetsend/PacketTP/IOPacketTP/GeneralTP/full>";
	}
	
	@Override
	public String handle(String ipAddress, String[] args)
	{
		if ((args.length == 0) || args[0].isEmpty())
		{
			return null;
		}
		switch (args[0])
		{
			case "decay":
			{
				return DecayTaskManager.getInstance().toString();
			}
			case "packetsend":
			{
				if (args.length < 4)
				{
					return "Usage: debug packetsend <charName> <packetData>";
				}
				final PlayerInstance player = World.getInstance().getPlayer(args[1]);
				if (player == null)
				{
					return "Couldn't find player with such name.";
				}
				
				final AdminForgePacket sp = new AdminForgePacket();
				for (int i = 2; i < args.length; i++)
				{
					String b = args[i];
					if (!b.isEmpty())
					{
						sp.addPart("C".getBytes()[0], "0x" + b);
					}
				}
				player.sendPacket(sp);
				return "Packet has been sent!";
			}
			case "full":
			{
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
				
				StringBuilder sb = new StringBuilder();
				sb.append(sdf.format(cal.getTime()));
				sb.append("\r\nL2J Unity");
				sb.append("\r\n");
				sb.append(getServerStatus());
				sb.append("\r\n");
				sb.append("\r\n## Java Platform Information ##");
				sb.append("\r\nJava Runtime Name: " + System.getProperty("java.runtime.name"));
				sb.append("\r\nJava Version: " + System.getProperty("java.version"));
				sb.append("\r\nJava Class Version: " + System.getProperty("java.class.version"));
				sb.append("\r\n");
				sb.append("\r\n## Virtual Machine Information ##");
				sb.append("\r\nVM Name: " + System.getProperty("java.vm.name"));
				sb.append("\r\nVM Version: " + System.getProperty("java.vm.version"));
				sb.append("\r\nVM Vendor: " + System.getProperty("java.vm.vendor"));
				sb.append("\r\nVM Info: " + System.getProperty("java.vm.info"));
				sb.append("\r\n");
				sb.append("\r\n## OS Information ##");
				sb.append("\r\nName: " + System.getProperty("os.name"));
				sb.append("\r\nArchiteture: " + System.getProperty("os.arch"));
				sb.append("\r\nVersion: " + System.getProperty("os.version"));
				sb.append("\r\n");
				sb.append("\r\n## Runtime Information ##");
				sb.append("\r\nCPU Count: " + Runtime.getRuntime().availableProcessors());
				sb.append("\r\nCurrent Free Heap Size: " + (Runtime.getRuntime().freeMemory() / 1024 / 1024) + " mb");
				sb.append("\r\nCurrent Heap Size: " + (Runtime.getRuntime().totalMemory() / 1024 / 1024) + " mb");
				sb.append("\r\nMaximum Heap Size: " + (Runtime.getRuntime().maxMemory() / 1024 / 1024) + " mb");
				
				sb.append("\r\n");
				sb.append("\r\n## Class Path Information ##\r\n");
				String cp = System.getProperty("java.class.path");
				String[] libs = cp.split(File.pathSeparator);
				for (String lib : libs)
				{
					sb.append(lib);
					sb.append("\r\n");
				}
				
				sb.append("\r\n");
				sb.append("## Threads Information ##\r\n");
				Map<Thread, StackTraceElement[]> allThread = Thread.getAllStackTraces();
				
				final List<Entry<Thread, StackTraceElement[]>> entries = new ArrayList<>(allThread.entrySet());
				Collections.sort(entries, (e1, e2) -> e1.getKey().getName().compareTo(e2.getKey().getName()));
				
				for (Entry<Thread, StackTraceElement[]> entry : entries)
				{
					StackTraceElement[] stes = entry.getValue();
					Thread t = entry.getKey();
					sb.append("--------------\r\n");
					sb.append(t.toString() + " (" + t.getId() + ")\r\n");
					sb.append("State: " + t.getState() + "\r\n");
					sb.append("isAlive: " + t.isAlive() + " | isDaemon: " + t.isDaemon() + " | isInterrupted: " + t.isInterrupted() + "\r\n");
					sb.append("\r\n");
					for (StackTraceElement ste : stes)
					{
						sb.append(ste.toString());
						sb.append("\r\n");
					}
					sb.append("\r\n");
				}
				
				sb.append("\r\n");
				ThreadMXBean mbean = ManagementFactory.getThreadMXBean();
				long[] ids = findDeadlockedThreads(mbean);
				if ((ids != null) && (ids.length > 0))
				{
					Thread[] threads = new Thread[ids.length];
					for (int i = 0; i < threads.length; i++)
					{
						threads[i] = findMatchingThread(mbean.getThreadInfo(ids[i]));
					}
					sb.append("Deadlocked Threads:\r\n");
					sb.append("-------------------\r\n");
					for (Thread thread : threads)
					{
						System.err.println(thread);
						for (StackTraceElement ste : thread.getStackTrace())
						{
							sb.append("\t" + ste);
							sb.append("\r\n");
						}
					}
				}
				
				sb.append("\r\n## Thread Pool Manager Statistics ##\r\n");
				for (String line : ThreadPool.getStats())
				{
					sb.append(line);
					sb.append("\r\n");
				}
				
				int i = 0;
				File f = new File("./log/Debug-" + i + ".txt");
				while (f.exists())
				{
					i++;
					f = new File("./log/Debug-" + i + ".txt");
				}
				f.getParentFile().mkdirs();
				
				try
				{
					Files.write(f.toPath(), sb.toString().getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
				}
				catch (IOException e)
				{
					LOGGER.warn("Couldn't write packet tp.", e);
				}
				return "Debug output saved to log/" + f.getName();
			}
		}
		return null;
	}
	
	private long[] findDeadlockedThreads(ThreadMXBean mbean)
	{
		// JDK 1.5 only supports the findMonitorDeadlockedThreads()
		// method, so you need to comment out the following three lines
		if (mbean.isSynchronizerUsageSupported())
		{
			return mbean.findDeadlockedThreads();
		}
		return mbean.findMonitorDeadlockedThreads();
	}
	
	private Thread findMatchingThread(ThreadInfo inf)
	{
		for (Thread thread : Thread.getAllStackTraces().keySet())
		{
			if (thread.getId() == inf.getThreadId())
			{
				return thread;
			}
		}
		throw new IllegalStateException("Deadlocked Thread not found");
	}
	
	public static String getServerStatus()
	{
		int playerCount = 0, objectCount = 0;
		int max = LoginServerThread.getInstance().getMaxPlayer();
		
		playerCount = World.getInstance().getPlayers().size();
		objectCount = World.getInstance().getVisibleObjectsCount();
		
		int itemCount = 0;
		int itemVoidCount = 0;
		int monsterCount = 0;
		int minionCount = 0;
		int minionsGroupCount = 0;
		int npcCount = 0;
		int charCount = 0;
		int pcCount = 0;
		int detachedCount = 0;
		int doorCount = 0;
		int summonCount = 0;
		int AICount = 0;
		
		for (WorldObject obj : World.getInstance().getVisibleObjects())
		{
			if (obj == null)
			{
				continue;
			}
			if (obj.isCreature())
			{
				if (((Creature) obj).hasAI())
				{
					AICount++;
				}
			}
			if (obj.isItem())
			{
				if (((ItemInstance) obj).getItemLocation() == ItemLocation.VOID)
				{
					itemVoidCount++;
				}
				else
				{
					itemCount++;
				}
			}
			else if (obj.isMonster())
			{
				monsterCount++;
				if (((L2MonsterInstance) obj).hasMinions())
				{
					minionCount += ((L2MonsterInstance) obj).getMinionList().countSpawnedMinions();
					minionsGroupCount += ((L2MonsterInstance) obj).getMinionList().lazyCountSpawnedMinionsGroups();
				}
			}
			else if (obj.isNpc())
			{
				npcCount++;
			}
			else if (obj.isPlayer())
			{
				pcCount++;
				if ((((PlayerInstance) obj).getClient() != null) && ((PlayerInstance) obj).getClient().isDetached())
				{
					detachedCount++;
				}
			}
			else if (obj.isSummon())
			{
				summonCount++;
			}
			else if (obj.isDoor())
			{
				doorCount++;
			}
			else if (obj.isCreature())
			{
				charCount++;
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append("Server Status: ");
		sb.append("\r\n  --->  Player Count: " + playerCount + "/" + max);
		sb.append("\r\n  ---> Offline Count: " + detachedCount + "/" + playerCount);
		sb.append("\r\n  +-->  Object Count: " + objectCount);
		sb.append("\r\n  +-->      AI Count: " + AICount);
		sb.append("\r\n  +.... L2Item(Void): " + itemVoidCount);
		sb.append("\r\n  +.......... L2Item: " + itemCount);
		sb.append("\r\n  +....... L2Monster: " + monsterCount);
		sb.append("\r\n  +......... Minions: " + minionCount);
		sb.append("\r\n  +.. Minions Groups: " + minionsGroupCount);
		sb.append("\r\n  +........... L2Npc: " + npcCount);
		sb.append("\r\n  +............ L2Pc: " + pcCount);
		sb.append("\r\n  +........ L2Summon: " + summonCount);
		sb.append("\r\n  +.......... L2Door: " + doorCount);
		sb.append("\r\n  +.......... L2Char: " + charCount);
		sb.append("\r\n  --->   Ingame Time: " + GameTimeManager.getInstance().getGameTime());
		sb.append("\r\n  ---> Server Uptime: " + SystemUtil.getUptime());
		sb.append("\r\n  --->      GM Count: " + getOnlineGMS());
		sb.append("\r\n  --->       Threads: " + Thread.activeCount());
		sb.append("\r\n  RAM Used: " + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576)); // 1024 * 1024 = 1048576
		sb.append("\r\n");
		
		return sb.toString();
	}
	
	private static int getOnlineGMS()
	{
		return AdminData.getInstance().getAllGms(true).size();
	}
	
	public static void main(String[] args)
	{
		TelnetServer.getInstance().addHandler(new Debug());
	}
}
