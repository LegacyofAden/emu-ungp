package org.l2junity.gameserver.data.txt.model.decodata;

import lombok.Data;
import org.l2junity.gameserver.data.txt.gen.DecoDatasParser;

/**
 * @author ANZO
 * @since 04.04.2017
 */
@Data
public class DecoDataTemplate {
	private final int id;
	private final String name;
	private final int level;
	private final DecoFunctionType funcType;
	private final double funcValue;
	private final int depth;
	private final DecoCostData cost;

	public DecoDataTemplate(DecoDatasParser.DecoDataContext ctx) {
		id = ctx.id().value;
		name = ctx.name().value;
		funcType = ctx.type().value;
		funcValue = ctx.funcValue().value;
		level = ctx.level().value;
		depth = ctx.depth_().value;
		cost = ctx.cost().value;
	}
}