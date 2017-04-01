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
package org.l2junity.gameserver.script;

import java.util.Hashtable;

import org.l2junity.gameserver.script.faenor.FaenorInterface;

/**
 * @author Luis Arias
 */
public class ScriptEngine
{
	protected EngineInterface _utils = FaenorInterface.getInstance();
	public static final Hashtable<String, ParserFactory> parserFactories = new Hashtable<>();
	
	public static Parser createParser(String name) throws ParserNotCreatedException
	{
		ParserFactory s = parserFactories.get(name);
		if (s == null) // shape not found
		{
			try
			{
				Class.forName(ScriptEngine.class.getPackage().getName() + "." + name);
				// By now the static block with no function would
				// have been executed if the shape was found.
				// the shape is expected to have put its factory
				// in the hashtable.
				
				s = parserFactories.get(name);
				if (s == null) // if the shape factory is not there even now
				{
					throw (new ParserNotCreatedException());
				}
			}
			catch (ClassNotFoundException e)
			{
				// We'll throw an exception to indicate that
				// the shape could not be created
				throw (new ParserNotCreatedException());
			}
		}
		return (s.create());
	}
}
