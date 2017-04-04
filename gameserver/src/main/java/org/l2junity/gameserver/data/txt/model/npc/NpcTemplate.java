package org.l2junity.gameserver.data.txt.model.npc;

import lombok.Data;
import org.l2junity.gameserver.data.txt.gen.NpcDatasParser;
import org.l2junity.gameserver.data.txt.model.constants.NpcType;

/**
 * This class designed to be immutable, thread safe npc template.
 * Instances of this class can be shared across thread without any limitations.
 *
 * @author ANZO
 * @since 04.04.17
 */
@Data
public final class NpcTemplate {
	private final int npcId;
	private final NpcType npcType;
	private final String name;

	public NpcTemplate(NpcDatasParser.NpcContext ctx) {
		npcId = ctx.npc_id().value;
		npcType = ctx.npc_type().value;
		name = ctx.npc_name().value;
	}
}
