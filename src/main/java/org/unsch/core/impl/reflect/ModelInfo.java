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
package org.unsch.core.impl.reflect;

import java.lang.reflect.Method;

import org.unsch.core.adapter.ModelComponent;

public class ModelInfo {

	private String propertyName;
	private String iniPropertyName;
	private Method method;
	private ModelComponent componentModel;

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getIniPropertyName() {
		return iniPropertyName;
	}

	public void setIniPropertyName(String iniPropertyName) {
		this.iniPropertyName = iniPropertyName;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public ModelComponent getComponentModel() {
		return componentModel;
	}

	public void setComponentModel(ModelComponent componentModel) {
		this.componentModel = componentModel;
	}
	
	

}
