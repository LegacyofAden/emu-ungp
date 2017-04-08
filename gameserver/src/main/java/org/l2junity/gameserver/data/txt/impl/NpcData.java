package org.l2junity.gameserver.data.txt.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.l2junity.core.configs.ScriptsConfig;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.data.txt.gen.NpcDatasBaseListener;
import org.l2junity.gameserver.data.txt.gen.NpcDatasLexer;
import org.l2junity.gameserver.data.txt.gen.NpcDatasParser;
import org.l2junity.gameserver.data.txt.model.npc.NpcTemplate;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ANZO
 * @since 08.04.17
 */
@Slf4j
@StartupComponent("Threading")
public class NpcData extends NpcDatasBaseListener {
    @Getter(lazy = true)
    private static final NpcData instance = new NpcData();

    private final Map<Integer, NpcTemplate> npcData = new HashMap<>(10500);

    NpcData() {
        log.info("Loading data file: npcdata.txt");
        final Path filePath = Paths.get(ScriptsConfig.DATA_ROOT.toString(), "scripts", "npcdata.txt");
        if (Files.exists(filePath)) {
            try (InputStream is = Files.newInputStream(filePath)) {
                final ANTLRInputStream input = new ANTLRInputStream(is);
                final NpcDatasLexer lexer = new NpcDatasLexer(input);
                final CommonTokenStream tokens = new CommonTokenStream(lexer);
                final NpcDatasParser parser = new NpcDatasParser(tokens);

                //parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
                //parser.setErrorHandler(new BailErrorStrategy());
                //parser.setProfile(false);

                final ParseTree tree = parser.file();
                final ParseTreeWalker walker = new ParseTreeWalker();
                walker.walk(this, tree);
            }
            catch (Exception e) {
                log.error("Error while loading npcdata.txt", e);
            }
            finally {
                log.info("Loaded {} NpcTemplate's from npcdata.txt", npcData.size());
            }
        }
    }

    @Override
    public void exitNpc(NpcDatasParser.NpcContext ctx) {
        try {
            NpcTemplate data = new NpcTemplate(ctx);
            assert !npcData.containsKey(data.getNpcId()) : "Found duplicate Npc ID in data: " + data.getNpcId();
            npcData.put(data.getNpcId(), data);
        }
        catch (Exception e) {
            log.error("Error while parsing npcId={}", ctx.npc_id().value, e);
        }
    }

    public NpcTemplate getTemplate(int id) {
        return npcData.get(id);
    }
}
