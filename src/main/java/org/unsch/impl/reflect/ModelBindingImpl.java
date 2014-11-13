package org.unsch.impl.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.unsch.Converter;
import org.unsch.adapter.MVPAdapter;
import org.unsch.adapter.MVPBinding;
import org.unsch.adapter.ModelBinding;
import org.unsch.exception.PropertyNotFoundException;
import org.unsch.exception.PropertyNotInitializedException;
import org.unsch.impl.swing.utils.LoggerUtils;

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
