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
package org.unsch.impl.swing;


import java.util.List;

import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.unsch.Converter;
import org.unsch.adapter.ModelBinding;
import org.unsch.adapter.ModelComponent;

public class JSpinnerModelComponent extends ModelComponent {

	private JSpinner jspinner;
	private ChangeListener changeListener;
	private List<Object> initValues;

	public JSpinnerModelComponent() {
	}

	@Override
	public void bind() {
		SpinnerListModel spinnerModel =new SpinnerListModel();
	    initValues=(List<Object>) modelBinding.getInitPropertyValue();
	    spinnerModel.setList(initValues);
	    jspinner.setModel(spinnerModel);
	    if(modelBinding.getPropertyValue()==null){
	    	  modelBinding.setPropertyValue(jspinner.getValue());
	    }
	 
	    changeListener=new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				modelBinding.setPropertyValue(jspinner.getValue());
				
			}
	    	
	    };
	   
	    jspinner.addChangeListener(changeListener);
	    
	    if(modelBinding.getPropertyValue()!=null){
	    	for(int i=0;i<initValues.size();i++){
	    		
	    		if(initValues.get(i).toString().equals(modelBinding.getPropertyValue())){
	    			spinnerModel.setValue(spinnerModel.getList().get(i));
	    		}
	    	}
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
		jspinner=(JSpinner)modelBinding.getComponent();
		
	}

}
