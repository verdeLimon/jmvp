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
package org.unsch.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.unsch.AppController;

/**
 * <p>
 * The scope of this annotation is the view 
 * <p>
 * Use this to annotate a class as view and associate two class as presenter and model view
 * <p>
 * Example :
 * 
 * @MVP(viewClass = ClientPresenter.class, modelClass = ClientModel.class)
 * public class ClientFrame extends JFrame{
 * 	
 * }
 * 
 * The {@link AppController#processView(Class)} will analyse this annotation and associate the with presenter and model classes
 *  
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface MVP {
	/**
	 * Specify the model class of the current view 
	 * 
     * @return 
	 */
	public Class<?> modelClass();
	/**
	 * Specify the presenter class of the current view 
	 * 
     * @return 
	 */
	public Class<?> presenterClass();

}
