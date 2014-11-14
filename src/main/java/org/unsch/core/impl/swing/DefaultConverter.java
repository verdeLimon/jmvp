/* 
 * Copyright (C) 2014 verde
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.unsch.core.impl.swing;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.unsch.core.Converter;
import org.unsch.core.impl.swing.utils.LoggerUtils;

public class DefaultConverter implements Converter {

	private Logger logger = LoggerUtils.getLogger();
	

	@Override
	public Object convertComponentToModel(Object type, Object value) {
		if (value instanceof String) {
			if (type == Integer.class)
				return new Integer((String) value);
			else if(type==int.class)
				return new Integer((String) value);
			else if(type==float.class)
				return new Float((String) value);
			else if (type == Float.class)
				return new Float((String) value);
			else if (type == String.class)
				return value.toString();
			else if(type==double.class)
				return new Double((String) value);
			else if (type == Double.class)
				return new Double((String) value);
			
			logger.error("The type : " + type.toString()
					+ " is not supported with the value of type String");
			throw new IllegalArgumentException();
		}
		else if (value instanceof Object)
			return value;
		else {
			logger.error("Unable to convert value " + value
					+ " to any supported type");
			throw new IllegalArgumentException();
		}
	}


	@Override
	public Object convertModelToComponent(Object value) {
		if(value instanceof Collection){
			
			return value;
		}
		return value.toString();
	}
	
}
