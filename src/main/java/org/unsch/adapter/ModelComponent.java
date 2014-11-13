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


/**
 * <p>
 * This class is used to simplify updating model for a given component
 * during implementing an adapter class {@link MVPAdapter} to a specific library.
 * <p>
 * The user can use the field modelBinding of type {@link ModelBinding} 
 * to add the change event to the current view component and call the setPropertyValue to update the current property in the model.
 * 
 * Example with JTextField component in Swing adapter :
 * 
 *  
public class JTextFieldModelComponent extends ModelComponent{
	
	JTextField textField;
	private PropertyChangeListener listener;
	
	private ModelBinding modelBinding;
	

	public JTextFieldModelComponent(ModelBinding modelBinding) {
		super(modelBinding);
		textField = (JTextField)modelBinding.getComponent();
	}

	@Override
	public void bind() {
		listener = new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				modelBinding.setPropertyValue(textField.getText());
				
			}
		};
		textField.addPropertyChangeListener(listener);
		
	}

	@Override
	public void unbind() {
		textField.removePropertyChangeListener(listener);
		
	}

}
 *
 */
public abstract class  ModelComponent {
	/**
	 * This field is useful to interact with the model and get all binding informations
	 * between component and model
	 */
	protected ModelBinding modelBinding;	
	
	
	/**
	 * initialize the modelComponent with the necessary parameters  
	 * @param modelBinding
	 */
	public  void init(ModelBinding modelBinding){
		this.modelBinding=modelBinding;
	}

	/**
	 * Bind the current component with the current model
	 * Add the change event to the current view component and call the setPropertyValue
	 * to update the current property in the model when the event is called
	 */
	public abstract void bind();
	
	/**
	 * Unbind the current component with the current model,
	 *  remove the event added by the method bind
	 */
	public abstract void unbind();
	
	/**
	 * Get the field to get all binding informations between component and model
	 * and useful methods to interact with current property in the model.
	 * 
	 * return ModelBinding
	 */
	public ModelBinding getModelBinding() {
		return modelBinding;
	}
	
	/**
	 * Modify the current specific converter of the modelComponent used to convert data in component in order to update the model
	 */
	public abstract void setConverter(Converter converter);
	
	
	/**
	 * The ModelComponent can define it's specific converter to customize the data conversion.
	 * 
	 * @return Get the current converter used to convert data in component to data in the model
	 */
	public abstract Converter getConverter();
	
}
