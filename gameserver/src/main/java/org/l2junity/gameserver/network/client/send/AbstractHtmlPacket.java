/*
 * Copyright (C) 2004-2015 L2J Unity
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
package org.l2junity.gameserver.network.client.send;

import org.l2junity.gameserver.cache.HtmCache;
import org.l2junity.gameserver.enums.ChatType;
import org.l2junity.gameserver.enums.HtmlActionScope;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.util.Util;

/**
 * @author HorridoJoho
 */
public abstract class AbstractHtmlPacket implements IClientOutgoingPacket
{
	public static final char VAR_PARAM_START_CHAR = '$';
	
	private final int _npcObjId;
	private String _html = null;
	private String _path = null;
	private boolean _disabledValidation = false;
	
	protected AbstractHtmlPacket()
	{
		_npcObjId = 0;
	}
	
	protected AbstractHtmlPacket(int npcObjId)
	{
		if (npcObjId < 0)
		{
			throw new IllegalArgumentException();
		}
		
		_npcObjId = npcObjId;
	}
	
	protected AbstractHtmlPacket(String html)
	{
		_npcObjId = 0;
		setHtml(html);
	}
	
	protected AbstractHtmlPacket(int npcObjId, String html)
	{
		if (npcObjId < 0)
		{
			throw new IllegalArgumentException();
		}
		
		_npcObjId = npcObjId;
		setHtml(html);
	}
	
	public final void disableValidation()
	{
		_disabledValidation = true;
	}
	
	public void setPath(String path)
	{
		_path = path;
	}
	
	public final void setHtml(String html)
	{
		if (html.length() > 17200)
		{
			_log.warn("Html is too long! this will crash the client!", new Throwable());
			_html = html.substring(0, 17200);
		}
		
		if (!html.contains("<html") && !html.startsWith("..\\L2"))
		{
			html = "<html><body>" + html + "</body></html>";
		}
		
		_html = html;
	}
	
	public final boolean setFile(String prefix, String path)
	{
		setPath(path);
		
		String content = HtmCache.getInstance().getHtm(prefix, path);
		if (content == null)
		{
			setHtml("<html><body>My Text is missing:<br>" + path + "</body></html>");
			_log.warn("Missing html page: " + path);
			return false;
		}
		
		setHtml(content);
		return true;
	}
	
	public final void replace(String pattern, String value)
	{
		_html = _html.replaceAll(pattern, value.replaceAll("\\$", "\\\\\\$"));
	}
	
	public final void replace(String pattern, CharSequence value)
	{
		replace(pattern, String.valueOf(value));
	}
	
	public final void replace(String pattern, boolean val)
	{
		replace(pattern, String.valueOf(val));
	}
	
	public final void replace(String pattern, int val)
	{
		replace(pattern, String.valueOf(val));
	}
	
	public final void replace(String pattern, long val)
	{
		replace(pattern, String.valueOf(val));
	}
	
	public final void replace(String pattern, double val)
	{
		replace(pattern, String.valueOf(val));
	}
	
	@Override
	public final void runImpl(PlayerInstance player)
	{
		if (player == null)
		{
			return;
		}
		
		if (!_disabledValidation)
		{
			player.clearHtmlActions(getScope());
			Util.buildHtmlActionCache(player, getScope(), _npcObjId, _html);
		}
		
		if (player.isGM() && player.isDebug() && (_path != null) && !_path.isEmpty())
		{
			player.sendPacket(new CreatureSay(0, ChatType.GENERAL, getChatName(), _path));
		}
	}
	
	public final int getNpcObjId()
	{
		return _npcObjId;
	}
	
	public final String getHtml()
	{
		return _html;
	}
	
	protected abstract String getChatName();
	
	public abstract HtmlActionScope getScope();
}
