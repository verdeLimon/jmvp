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
import org.unsch.core.adapter.MVPBinding;


/**
 * <p>
 * The application entry to process view class and bind the view instance with it's model and presenter instances.
 *
 */
public interface AppController {
	
	/**
	 * <p>
	 * Bind the view to the model instance, the data seized in the view will
	 * automatically synchronized with the stored in the model
	 * <p>
	 * Bind the presenter instance to the view instance, the events happened in the view will
	 * automatically call the actions in the presenter
	 * 
	 * <p>
	 *
	 * throw {@link IllegalArgumentException} if the view, model and presenter instances classes are not associated
	 * 
	 * <p>
	 * Use the current adapter to bind annotated component in the view with the associated model
	 * properties and presenter actions.
	 * 
	 * @param model store the data 
	 * @param view display the data to the user
	 * @param presenter manage the view
     * @return 
	 */
	public MVPBinding bind(Object view, Object model,  Object presenter);
	
	/**
	 * <p>
	 * Bind the view to the model instance, the data seized in the view will
	 * automatically synchronized with the stored in the model
	 * 
	 * <p>
	 * 
	 * throw {@link IllegalArgumentException} if the model and view instances classes are not associated.
	 * 
	 * 
	 * <p>
	 * Use the current adapter to bind annotated component in the view with the associated model
	 * properties.
	 * 
	 * @param model store the data 
	 * @param view display the data to the user
	 */	
	public MVPBinding bindModel(Object view, Object model);
	
	/** 
	 * <p>
	 * Bind the presenter instance to the view instance, the events happened in the view will
	 * automatically call the actions in the presenter
	 * 
	 * <p>
	 * 
	 * throw {@link IllegalArgumentException} if the view and presenter instances classes are not associated
	 * 
	 * <p>
	 * Use the current adapter to bind annotated component in the view with the associated 
	 * presenter actions.
	 * 
	 * @param view display the data to the user
	 * @param presenter manage the view
	 */	
	public MVPBinding bindPresenter(Object view, Object presenter);
	
	/**
	 * Refresh the view with data model associated, if data in model change, the call to this method will 
	 * if no model associated than do nothing
	 * @param view
	 */
	public void refreshView(Object view);
	

	/** 
	 * <p>
	 * Set the adapter to support a specific library, you can define a new adapter for a given library, 
	 * for example : swing, swt, gwt,...
	 * if no adapter is specified, the default adapter is used.
	 * 
	 * @param adapter
	 */	
	public void setAdapter(MVPAdapter adapter);
	
	
	
	
	
	

}
