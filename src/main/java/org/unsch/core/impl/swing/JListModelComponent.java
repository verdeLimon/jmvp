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
package org.unsch.core.impl.swing;

import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.log4j.Logger;
import org.unsch.core.Converter;
import org.unsch.core.adapter.ModelBinding;
import org.unsch.core.adapter.ModelComponent;
import org.unsch.core.exception.PropertyNotBindableException;
import org.unsch.core.impl.swing.utils.LoggerUtils;

public class JListModelComponent extends ModelComponent {

	private JList list;
	private MouseListener mouseListener;
	private List<Object> initValues;
	private Logger logger = LoggerUtils.getLogger();

	public JListModelComponent() {
	}

	@Override
	public void bind() {
		try{
		initValues = (List<Object>) modelBinding.getInitPropertyValue();
		}
		catch (ClassCastException e) {
			logger.error("initProperty '"+modelBinding.getInitPropertyName()+"' must be Collection");
        	throw new PropertyNotBindableException(
					modelBinding.getInitPropertyName(), modelBinding
							.getInitPropertyName().getClass(),
							list.getClass());
		}
		if (initValues instanceof Collection) {
		DefaultListModel listModel = new DefaultListModel();
		for (Object object : initValues) {
			listModel.addElement(object);
		}
		list.setModel(listModel);

		ListSelectionListener selectionListener = new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try{
				initValues = (List<Object>) modelBinding.getInitPropertyValue();
				modelBinding.setPropertyValue(initValues.get(list
						.getSelectedIndex()));
				}
				catch (ArrayIndexOutOfBoundsException a) {
				}
			}
		};
		list.addListSelectionListener(selectionListener);
		}
		else{
			logger.error("initProperty '"+modelBinding.getInitPropertyName()+"' must be Collection");
			throw new PropertyNotBindableException(
					modelBinding.getInitPropertyName(), modelBinding
							.getInitPropertyName().getClass(),
					list.getClass());
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
		list = (JList) modelBinding.getComponent();
		
	}

}
