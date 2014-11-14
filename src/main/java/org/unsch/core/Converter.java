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
package org.unsch.core;

import org.unsch.core.adapter.MVPAdapter;

/**
 * <p>
 * The converter class to convert an object value to an instance of specified type.
 * <p>
 * Use this to set a specific converter by calling {@link AppController#setConverter(Converter)}
 * <p>
 * The {@link AppController} will use the converter to convert component value before updating the associated
 * model property value.
 * <p>
 * When developing an adapter class {@link MVPAdapter}, create a customized converter depending 
 * on the adapter and return it in the method {@link MVPAdapter#getConvertor()}
 * 
 *
 */
public interface Converter {
	/**
	 * Convert an object value to an instance of specified type. 
	 * 
	 * If is not possible to convert throw a new {@link IllegalArgumentException}
	 * 
	 * @param type the target type of the instance converted from the value object
	 * @param value the object value to convert
	 * @return the result of conversion
	 */
	public Object convertComponentToModel(Object type, Object value);
	
	  // todo javaDoc
	public Object convertModelToComponent(Object value);

}
