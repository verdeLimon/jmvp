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
package org.unsch.core.adapter;

import org.unsch.core.Converter;

public interface MVPBinding {

	/**
	 * The adapter can define it's global converter to customize the data
	 * conversion.
	 * 
	 * @return Get the current converter used to convert data in component to
	 *         data in the model
	 */
	public Converter getGlobalConverter();

	/**
	 * Modify the current global converter used to convert data in component in
	 * order to update the model
	 */
	public void setGlobalConverter(Converter converter);

	/**
	 * For each Component supported, this method must set an instance of
	 * customized ModelComponent to define the binding rules between a component
	 * and it's property name in the associated model. if the component class is
	 * not supported than throw an {@link IllegalArgumentException}
	 * 
	 * @param componentKlass
	 *            the component class
	 * @param customizedModelComponent
	 *            the model component class
	 */

	public void setComponentModel(Object component,
			Class<? extends ModelComponent> customizedModelComponent);

	// TODO JavaDoc
	public ModelComponent getComponentModel(Object component);

	// TODO JavaDoc
	public Object getPresenter();

	// TODO JavaDoc
	public Object getModel();

	// TODO JavaDoc
	public Object getView();

	// TODO JavaDoc
	public void setPresenter(Object presenter);

	// TODO JavaDoc
	public void setModel(Object model);

	// TODO JavaDoc
	public void setView(Object view);

}
