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
/**
 * <p>
 * This interface is used to retrieve the binding informations between the event 
 * of the current component in the view and associated action method in the presenter
 * and simplify add event and action call operations  when writing the {@link ActionComponenet} 
 * to support a specific component during implementing an adapter class {@link MVPAdapter} to a specific library.
 * <p>
 * The user can use the instance to add an event to the current view component
 *  and call the associated action method in the current presenter.
 *
 */
public interface ActionBinding {
	/** 
	 * @return the current action name
	 */
	public String getActionName();	
	
	/** 
	 * The type of event to add to the current view component, if is null use the default event type.
	 * if the event type is not supported throw an {@link IllegalArgumentException}
	 * 
	 * @return The current Event Type
	 */
	public Class<?> getEventType();
	/**
	 * 
	 * @return the method within the event's supported methods that it will be implemented for the current component
	 */
	public String getEventAction();
	/** 
	 * The current component.
	 * 
	 * @return The current component.
	 */
	public Object getComponent();

	/**
	 * <p>
	 * Use this method to call the action, in the body event handler,
	 * so when the event happened the action must be invoked.
	 * @param the eventObject represents the event object registered by the listener and passed to the action
	 */	
	public  void callAction(Object eventObject);
}
