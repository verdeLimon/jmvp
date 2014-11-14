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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.unsch.core.Converter;
import org.unsch.core.adapter.MVPAdapter;
import org.unsch.core.adapter.MVPBinding;
import org.unsch.core.adapter.ModelBinding;
import org.unsch.core.exception.PropertyNotFoundException;
import org.unsch.core.exception.PropertyNotInitializedException;
import org.unsch.core.impl.swing.utils.LoggerUtils;

public class ModelBindingImpl implements ModelBinding {

	private ModelInfo modelInfo;
	private Object model;
	private Object view;
	private Converter converter;
	private MVPBinding mvpBinding;
	private Logger logger = LoggerUtils.getLogger();

	public ModelBindingImpl(Object view, Object model, ModelInfo modelInfo,MVPBinding mvpBinding) {

		this.model = model;
		this.view = view;
		this.modelInfo = modelInfo;
		this.mvpBinding=mvpBinding;

	}

	@Override
	public Object getComponent() {
		Object object = null;
		try {
			object = modelInfo.getMethod().invoke(view);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}

	@Override
	public String getPropertyName() {
		return modelInfo.getPropertyName();
	}

	@Override
	public String getInitPropertyName() {
		return modelInfo.getIniPropertyName();
	}

	@Override
	public void setPropertyValue(Object value) {
		try {
			Class modelClass = model.getClass();
			Field field = modelClass.getDeclaredField(getPropertyName());
			field.setAccessible(true);
			field.set(model, getConverter().convertComponentToModel(field.getType(), value));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			logger.fatal("Field "+getPropertyName()+" not found ");
			throw new PropertyNotFoundException(getPropertyName(), model.getClass());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Object getPropertyValue() {
		try {
			Class modelClass = model.getClass();
			Field field = modelClass.getDeclaredField(getPropertyName());
			field.setAccessible(true);
			Object object = field.get(model);
			if(object!=null){
			return getConverter().convertModelToComponent(object);
			}
		
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			logger.fatal("Field "+getPropertyName()+" not found ");
			throw new PropertyNotFoundException(getPropertyName(), model.getClass());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object getInitPropertyValue() {
		   Object fakeObject = new Object();
           Object object=fakeObject;
		try {
			Class modelClass = model.getClass();
			Method[] methods=modelClass.getDeclaredMethods();
			for (Method method : methods) {
				if(method.getName().equalsIgnoreCase("get"+getInitPropertyName())){
				
					object=method.invoke(model);
				}
			}
			if (object==fakeObject){
				throw new PropertyNotFoundException(getInitPropertyName(), model);
			}
			if(object==null ){
				throw new PropertyNotInitializedException(getInitPropertyName());
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}

	private Converter getConverter() {
		if(modelInfo.getComponentModel().getConverter()==null){
			converter = getMvpBinding().getGlobalConverter();
		}
		else{
			converter=modelInfo.getComponentModel().getConverter();
		}
		return converter;
	}

	public MVPBinding getMvpBinding() {
		return mvpBinding;
	}

	

	
	
	
	
	
    
}
