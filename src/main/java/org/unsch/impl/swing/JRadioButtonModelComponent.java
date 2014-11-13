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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

import org.apache.log4j.Logger;
import org.unsch.Converter;
import org.unsch.adapter.ModelBinding;
import org.unsch.adapter.ModelComponent;
import org.unsch.impl.swing.utils.LoggerUtils;

public class JRadioButtonModelComponent extends ModelComponent {

	private ActionListener actionListerner;
	private JRadioButton radionButton;
	private Logger logger = LoggerUtils.getLogger();

	
	public JRadioButtonModelComponent() {
	}

	@Override
	public void bind() {
		actionListerner = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object object = modelBinding.getInitPropertyValue();
				modelBinding.setPropertyValue(object);

			}
		};
		radionButton.addActionListener(actionListerner);

		if (modelBinding.getPropertyValue() != null) {
			if (modelBinding.getPropertyValue()
					.equals(modelBinding.getInitPropertyValue().toString())) {
				radionButton.setSelected(true);
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
		radionButton = (JRadioButton) modelBinding.getComponent();
		
	}

}
