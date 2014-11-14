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
package org.unsch.core.exception;

/**
 * <p>
 * A runtime exception that describes that a Java Bean property could not be
 * found.
 */

public class PropertyNotFoundException extends RuntimeException {

	/**
	 * Constructs a new exception instance with the specified detail message.
	 * 
	 * @param propertyName
	 *            the name of the property that could not be found
	 * @param bean
	 *            the Model Java Bean used to bind the property
	 */

	public PropertyNotFoundException(String propertyName, Object bean) {
		super("Property '" + propertyName + "' not found in bean " + bean);
	}

}
