package org.unsch.impl.swing;

import javax.swing.JLabel;

import org.unsch.Converter;
import org.unsch.adapter.ModelBinding;
import org.unsch.adapter.ModelComponent;

public class JLabelModelComponent extends ModelComponent{

	  
	private JLabel jlabel;

	
	public JLabelModelComponent() {
	}

	@Override
	public void bind(){
		
		jlabel.setText(modelBinding.getInitPropertyName().toString());
		if(modelBinding.getPropertyValue()!=null){
		jlabel.setText((String)modelBinding.getPropertyValue());
		}
		
	}

	@Override
	public void unbind() {
		// TODO Auto-generated method stub
		
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
		jlabel=(JLabel)modelBinding.getComponent();
	}


}
