package org.unsch.impl.swing;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;
import java.util.List;

import javax.swing.JComboBox;

import org.apache.log4j.Logger;
import org.unsch.Converter;
import org.unsch.adapter.ModelBinding;
import org.unsch.adapter.ModelComponent;
import org.unsch.exception.PropertyNotBindableException;
import org.unsch.impl.swing.utils.LoggerUtils;

public class JComboBoxModelComponent extends ModelComponent {

	private JComboBox jcombobox;
	private ItemListener itemlistener;
	private List<Object> initValues;
	private Logger logger = LoggerUtils.getLogger();
	
	public JComboBoxModelComponent() {
	}


	@Override
	public void bind() {
       
		if(modelBinding.getPropertyValue()!=null){
		
			for(int i=0;i<initValues.size();i++){
				if(initValues.get(i).toString().equals(modelBinding.getPropertyValue())){
					jcombobox.setSelectedIndex(i);
					break;
				}
				else{
					jcombobox.setSelectedIndex(0);
				}
			}
	
		}
		if (!initValues.isEmpty()) {

			if(jcombobox.getItemCount()==0){
			for (Object object : initValues) {
				jcombobox.addItem(object);
				
			}
			}
		} else {
			logger.error("The value of Init Property is empty!!!");
			throw new IllegalArgumentException();
		}

		modelBinding.setPropertyValue(initValues.get(jcombobox
				.getSelectedIndex()));
       
		itemlistener = new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				
				modelBinding.setPropertyValue(initValues.get(jcombobox
						.getSelectedIndex()));
               
			}
		};
		jcombobox.addItemListener(itemlistener);
		
		 
	}

	@Override
	public void unbind() {
	}

	@Override
	public void setConverter(Converter converter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Converter getConverter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(ModelBinding modelBinding) {
		super.init(modelBinding);
		jcombobox = (JComboBox) modelBinding.getComponent();
		try {
			initValues = (List<Object>) modelBinding.getInitPropertyValue();
		}
		catch (ClassCastException e) {
			logger.error("initProperty '"+modelBinding.getInitPropertyName()+"' must be Collection");
        	throw new PropertyNotBindableException(
					modelBinding.getInitPropertyName(), modelBinding
							.getInitPropertyName().getClass(),
							jcombobox.getClass());
		}
		
	}


}
