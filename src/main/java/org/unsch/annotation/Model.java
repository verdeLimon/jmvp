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
 * Use this to annotate the input component and bind it to the property name of the model
 * The value seized on the input component value will automatically stored in the value of associated property model
 * 
 * Example :
 * 
 * @Model(name="name")
 * TextField getTextFieldName(){
 * 	return textFieldName;
 * }
 * 
 * On the associated model we have 
 * 
 * public class TheModel{
 * 	private String name;
 * 
 * public void setName(String name){
 * 	this.name = name; 
 * }
 * <p>
 * The {@link AppController} will call the setter method(setName) to update model when the input text(textFieldName) 
 * value has been modified
 */


@Retention(RetentionPolicy.RUNTIME)

@Documented
public @interface Model {
	
	/**
	 * @return The property name of the model, that used by component 
	 */
	String property();
	
	/**
	 * @return The initialization property name of the model, 
	 * that used by component that needs data to be displayed at rendering. for example a comboBox
	 */
	String initProperty() default "";
}
