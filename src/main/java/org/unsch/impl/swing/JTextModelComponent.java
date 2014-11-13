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

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.text.JTextComponent;

import org.apache.log4j.Logger;
import org.unsch.Converter;
import org.unsch.adapter.ModelBinding;
import org.unsch.adapter.ModelComponent;
import org.unsch.impl.swing.utils.LoggerUtils;

public class JTextModelComponent extends ModelComponent {

	private JTextComponent textField;
	private KeyListener keyListener;
	private FocusListener focusListener;
	private Converter converter;
	private Logger logger = LoggerUtils.getLogger();

	public JTextModelComponent() {
	}

	@Override
	public void bind() {

		keyListener = new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				modelBinding.setPropertyValue(textField.getText());

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		};
		focusListener = new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
			
				modelBinding.setPropertyValue(textField.getText());

			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		};
		//textField.addKeyListener(keyListener);
		textField.addFocusListener(focusListener);
      
		if (modelBinding.getPropertyValue() == null || modelBinding.getPropertyValue().equals(0)) {
			textField.setText("");
		} else {
			String string = modelBinding.getPropertyValue()+"";
			textField.setText(string);
		}

	}

	@Override
	public void unbind() {
		textField.removeKeyListener(keyListener);
		textField.removeFocusListener(focusListener);
	}

	@Override
	public Converter getConverter() {
		return converter;
	}

	@Override
	public void setConverter(Converter converter) {
		this.converter = converter;
	}

	@Override
	public void init(ModelBinding modelBinding) {
		super.init(modelBinding);
		textField = (JTextComponent) modelBinding.getComponent();
		
	}


	

	
	

}
