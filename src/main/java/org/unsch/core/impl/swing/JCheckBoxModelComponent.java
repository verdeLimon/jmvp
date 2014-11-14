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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JCheckBox;

import org.apache.log4j.Logger;
import org.unsch.core.Converter;
import org.unsch.core.adapter.ModelBinding;
import org.unsch.core.adapter.ModelComponent;
import org.unsch.core.impl.swing.utils.LoggerUtils;

public class JCheckBoxModelComponent extends ModelComponent {

	private ActionListener actionListerner;
	private JCheckBox checkBox;
	private Logger logger = LoggerUtils.getLogger();

	private static Collection list;

	public JCheckBoxModelComponent() {
	}

	@Override
	public void bind() {
		actionListerner = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object object = modelBinding.getInitPropertyValue();
				if (checkBox.isSelected()) {
					list = getList();
					if (!list.contains(object)) {
						list.add(object);
						modelBinding.setPropertyValue(list);
					}

				}
				if (!checkBox.isSelected()) {
					list = getList();
					if (list.contains(object)) {
						list.remove(object);
						modelBinding.setPropertyValue(list);
					}
				}
			}
		};
		checkBox.addActionListener(actionListerner);
		if (modelBinding.getPropertyValue() != null) {
			Collection list = (Collection) modelBinding.getPropertyValue();
			
			checkBox.setSelected(list.contains(modelBinding
					.getInitPropertyValue()));
		}

	}

	@Override
	public void unbind() {
		// TODO Auto-generated method stub

	}

	public Collection getList() {
		if (list == null) {
			list = new ArrayList();
		}
		return list;
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
		checkBox = (JCheckBox) modelBinding.getComponent();
		
	}



}
