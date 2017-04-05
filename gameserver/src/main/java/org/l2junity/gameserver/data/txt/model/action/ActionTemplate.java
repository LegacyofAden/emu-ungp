package org.l2junity.gameserver.data.txt.model.action;

import lombok.Data;
import org.l2junity.gameserver.data.txt.gen.UserBasicActionsParser;
import org.l2junity.gameserver.data.txt.model.constants.ActionHandlerType;

/**
 * @author Camelion
 * @since 20.01.16
 */
@Data
public final class ActionTemplate {
	private final int id;
	private final ActionHandlerType handler;
	private final Object option;

	public ActionTemplate(UserBasicActionsParser.ActionContext ctx) {
		id = ctx.id().value;
		handler = ctx.handler() == null ? null : ctx.handler().value;
		option = ctx.option() == null ? null : ctx.option().value;
	}
}
