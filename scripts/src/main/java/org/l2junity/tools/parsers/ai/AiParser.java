package org.l2junity.tools.parsers.ai;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.text.StrBuilder;
import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.core.startup.StartupLevel;
import org.l2junity.core.startup.StartupManager;
import org.l2junity.tools.parsers.ai.model.PlainClassData;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ANZO
 * @since 21.03.2017
 */
@Slf4j
public class AiParser {
	public static void main(String[] args) {
		try {
			new AiParser();
		} catch (Exception ex) {
			log.error("Error while starting AiParser", ex);
		}
	}

	private AiParser() {
		StartupManager.getInstance().startup(StartupLevel.class);
		readAiObj();
	}

	private void readAiObj() {
		try {
			Files.walkFileTree(Paths.get("src/main/java/org/l2junity/gameserver/retail/ai"), new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					if (!Files.isDirectory(file)) {
						Files.delete(file);
					}
					return super.visitFile(file, attrs);
				}
			});
		} catch (IOException e) {
			log.error("Error while cleaning up old AI scripts", e);
		}

		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data/scripts/ai.obj"), "UTF-8"))) {
			String line = br.readLine(); // SizeofPointer 8
			line = br.readLine(); // SharedFactoryVersion 121
			line = br.readLine(); // NPCHVersion 182
			line = br.readLine(); // NASCVersion 56
			line = br.readLine(); // NPCEventHVersion 4
			line = br.readLine(); // Debug 0
			line = br.readLine(); // empty

			List<PlainClassData> classDatas = new ArrayList<>();
			while ((line = br.readLine()) != null) {
				if (line.startsWith("class")) {
					classDatas.add(readClass(line, br));
				}
			}
			for (PlainClassData classData : classDatas) {
				ThreadPool.getInstance().executeGeneral(() -> {
					LLParser.getInstance().processClass(classData);
				});
			}

			while (ThreadPool.getInstance().hasGeneralTasks()) {
			}
			log.info("All parser tasks completed.");
			System.exit(0);
		} catch (Exception ex) {
			log.error("Error while reading ai.obj file", ex);
		}
	}

	private PlainClassData readClass(String line, BufferedReader br) throws IOException {
		String parts[] = line.split(" ");
		int type = Integer.parseInt(parts[1]);
		String className = parts[2];
		String parent = parts[4].equals("(null)") ? null : parts[4];

		StrBuilder buffer = new StrBuilder();

		while (!(line = br.readLine()).equals("class_end")) {
			if (!line.isEmpty()) {
				if (line.indexOf("//") > 0) {
					line = line.substring(0, line.indexOf("//"));
				}
				buffer.appendln(line.trim());
			}
		}

		PlainClassData classData = new PlainClassData();
		classData.setType(type);
		classData.setClassName(className);
		classData.setParent(parent);
		classData.setClassBody(buffer.build());
		return classData;
	}
}
