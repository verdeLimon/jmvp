package org.unsch.impl.swing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;
import org.unsch.Converter;
import org.unsch.adapter.ModelBinding;
import org.unsch.adapter.ModelComponent;
import org.unsch.exception.PropertyNotBindableException;

import org.unsch.impl.swing.utils.JtableUtils;
import org.unsch.impl.swing.utils.LoggerUtils;

public class JTableModelComponent extends ModelComponent {

	private JTable table;
	private MouseListener mouseListener;
	private List<Object> initValues;
	private DefaultTableModel tableModel;
	private Map<String, String> customizedColumns;
	private final Logger logger = LoggerUtils.getLogger();

	public JTableModelComponent() {
	}

	@Override
	public void bind() {

		table.setModel( initTableModel(getCustomizedColumns()));

		mouseListener = new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {

				modelBinding.setPropertyValue(initValues.get(table
						.getSelectedRow()));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		};
		table.addMouseListener(mouseListener);
	}

	@Override
	public void unbind() {
		tableModel = null;

	}

	public DefaultTableModel initTableModel(Map<String, String> customizedColumns) {
		if (tableModel == null) {
			tableModel = new DefaultTableModel();
			initValues = (List<Object>) modelBinding.getInitPropertyValue();
			List<Object> listObject = JtableUtils.getColumns(initValues,
					getCustomizedColumns());
			List columns = new ArrayList();

			for (Object field : listObject) {
				columns.add(field.toString());
                                logger.info("Adding header : '"+field.toString()+ "' to table");
                                //System.out.println("gg: "+field.toString());
			}

			tableModel.setColumnIdentifiers(columns.toArray());

			for (Object object2 : (List) initValues) {
				tableModel.addRow(JtableUtils.getRow(object2).toArray());
			}

		}
		return tableModel;
	}

	public Map<String, String> getCustomizedColumns() {
		if (customizedColumns == null) {
			customizedColumns = new HashMap<>();
		}
		return customizedColumns;
	}

	public void setCustomizedColumns(Map<String, String> customizedColumns) {
		this.customizedColumns = customizedColumns;
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
		table = (JTable) modelBinding.getComponent();
		try {
			initValues = (List<Object>) modelBinding.getInitPropertyValue();
		} catch (ClassCastException e) {
			logger.error("initProperty '" + modelBinding.getInitPropertyName()
					+ "' must be Collection");
			throw new PropertyNotBindableException(
					modelBinding.getInitPropertyName(), modelBinding
							.getInitPropertyName().getClass(), table.getClass());

		}
		
	}

}
