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
package org.unsch.adapter;

import org.unsch.Converter;

public interface MVPAdapter {
	
	
	
	
	
	/**
	 * For each Component supported, this method must return an instance of ActionComponenet
	 * to define the binding rules between a component and it's action in the associated presenter
	 * if the component class is not supported than throw an {@link IllegalArgumentException}
	 * @param componentKlass the component class
	 * @return EventActionComponent instance
	 */
	public Class<? extends ActionComponent> getComponentAction(Class<?> componentKlass);
	
	
	
	
	
	
	/**
	 * For each Component supported, this method must return an instance of ModelComponent
	 * to define the binding rules between a component and it's property name in the associated model. 
	 * if the component class is not supported than throw an {@link IllegalArgumentException}
	 * @param componentKlass the component class
	 * @return ModelComponent instance
	 */
	public Class<? extends ModelComponent> getComponentModel(Class<?> componentKlass);
	
	
}
