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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.unsch.Converter;
import org.unsch.adapter.ActionComponent;
import org.unsch.adapter.MVPAdapter;
import org.unsch.adapter.ModelBinding;
import org.unsch.adapter.ModelComponent;
import org.unsch.impl.reflect.AppControllerReflect;
import org.unsch.impl.reflect.AppControllerReflectFactory;
import org.unsch.impl.reflect.ModelBindingImpl;
import org.unsch.impl.reflect.ModelInfo;
import org.unsch.impl.reflect.ModelViewInfo;
import org.unsch.impl.swing.utils.LoggerUtils;

public class SwingAdapter implements MVPAdapter {

	private Logger logger = LoggerUtils.getLogger();
	private Map<Object, Object> customizedComponentsModels = new HashMap<Object, Object>();

	@Override
	public Class<? extends ActionComponent> getComponentAction(
			Class<?> componentKlass) {

		if (componentKlass.equals(JButton.class)) {
			return AbstractButtonActionComponent.class;
		} else if (componentKlass == JMenuItem.class)
			return AbstractButtonActionComponent.class;
		else if (componentKlass == JComboBox.class) {
			return JComboBoxActionComponent.class;
		} else if (componentKlass == JList.class) {
			return JListActionComponent.class;
		} else if (componentKlass == JTextField.class) {
			return JTextActionComponent.class;
		} else if (componentKlass == JRadioButton.class) {
			return JRadioButtonActionComponent.class;
		} else if (componentKlass == JTable.class) {
			return JTableActionComponent.class;
		} else if (componentKlass == JPasswordField.class) {
			return JTextActionComponent.class;
		} else if (componentKlass == JCheckBox.class) {
			return JCheckBoxActionComponent.class;
		} else if (componentKlass == JFormattedTextField.class) {
			return JTextActionComponent.class;
		} else if (componentKlass == JTextArea.class) {
			return JTextActionComponent.class;
		} else if (componentKlass == JSpinner.class) {
			return JSpinnerActionComponent.class;
		}

		logger.error("Action Component " + componentKlass.getName()
				+ "  is not supported");
		throw new IllegalArgumentException();
	}

	@Override
	public Class<? extends ModelComponent> getComponentModel(
			Class<?> componentKlass) {

		if (componentKlass == JTextField.class) {
			if (customizedComponentsModels.get(JTextField.class) == null)
				return JTextModelComponent.class;
			else
				return (Class<? extends ModelComponent>) customizedComponentsModels
						.get(JTextField.class);
		}

		else if (componentKlass == JRadioButton.class) {
			if (customizedComponentsModels.get(JRadioButton.class) == null)
				return JRadioButtonModelComponent.class;
			else
				return (Class<? extends ModelComponent>) customizedComponentsModels
						.get(JRadioButton.class);
		}

		else if (componentKlass == JComboBox.class) {
			if (customizedComponentsModels.get(JComboBox.class) == null)
				return JComboBoxModelComponent.class;
			else
				return (Class<? extends ModelComponent>) customizedComponentsModels
						.get(JComboBox.class);
		}

		else if (componentKlass == JTable.class) {
			if (customizedComponentsModels.get(JTable.class) == null)
				return JTableModelComponent.class;
			else
				return (Class<? extends ModelComponent>) customizedComponentsModels
						.get(JTable.class);
		}

		else if (componentKlass == JPasswordField.class) {

			if (customizedComponentsModels.get(JPasswordField.class) == null)
				return JTextModelComponent.class;
			else
				return (Class<? extends ModelComponent>) customizedComponentsModels
						.get(JPasswordField.class);
		}

		else if (componentKlass == JCheckBox.class) {
			if (customizedComponentsModels.get(JCheckBox.class) == null)
				return JCheckBoxModelComponent.class;
			else
				return (Class<? extends ModelComponent>) customizedComponentsModels
						.get(JCheckBox.class);
		}

		else if (componentKlass == JFormattedTextField.class) {
			if (customizedComponentsModels.get(JFormattedTextField.class) == null)
				return JTextModelComponent.class;
			else
				return (Class<? extends ModelComponent>) customizedComponentsModels
						.get(JFormattedTextField.class);
		}

		else if (componentKlass == JTextArea.class) {
			if (customizedComponentsModels.get(JTextArea.class) == null)
				return JTextModelComponent.class;
			else
				return (Class<? extends ModelComponent>) customizedComponentsModels
						.get(JTextArea.class);
		}

		else if (componentKlass == JLabel.class) {
			if (customizedComponentsModels.get(JLabel.class) == null)
				return JLabelModelComponent.class;
			else
				return (Class<? extends ModelComponent>) customizedComponentsModels
						.get(JLabel.class);
		}

		else if (componentKlass == JSpinner.class) {
			if (customizedComponentsModels.get(JSpinner.class) == null)
				return JSpinnerModelComponent.class;
			else
				return (Class<? extends ModelComponent>) customizedComponentsModels
						.get(JSpinner.class);
		} else if (componentKlass == JList.class) {
			if (customizedComponentsModels.get(JList.class) == null)
				return JListModelComponent.class;
			else
				return (Class<? extends ModelComponent>) customizedComponentsModels
						.get(JList.class);
		} else
			logger.error("Model Component " + componentKlass.getName()
					+ "  is not supported");
		throw new IllegalArgumentException();
	}




}
