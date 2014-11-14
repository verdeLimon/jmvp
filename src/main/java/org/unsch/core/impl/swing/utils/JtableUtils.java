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
package org.unsch.core.impl.swing.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

public class JtableUtils {

	private static final Logger logger = LoggerUtils.getLogger();
	static List<Field> fields;

	public static List<Object> getColumns(Object initListObject,
			Map<String, String> customizedColumns) {

		List<Object> columns = new ArrayList();

		if (customizedColumns.isEmpty()) {
			initFields(initListObject, customizedColumns);
			for (Field field : getFields()) {
				field.setAccessible(true);
				columns.add(field.getName());
			}
			return columns;

		} else {
			
			for (Object object : customizedColumns.values().toArray()) {
				columns.add(object.toString());
			}
			initFields(initListObject, customizedColumns);
			return columns;
		}

	}

	public static List getRow(Object object) {
		List row = new ArrayList();
		for (Field field : fields) {

			field.setAccessible(true);
			try {
				row.add(field.get(object));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return row;
	}

	public static List<Field> getFields() {
		return fields;
	}

	public static void initFields(Object initListObject,	Map<String, String> customizedColumns) {

		fields = new ArrayList<>();
		List objectList = (List) initListObject;
		if (objectList.isEmpty()) {
			logger.info("The init property is NOT initialized correctly");
			//throw new IllegalArgumentException();
                       
                        List<Dummy> lst = new ArrayList<>();
                        lst.add(new Dummy(1, "table is empty"));                                
                        objectList = lst;
		}
		Object object = objectList.get(0);
		Class objectClass = object.getClass();
		
		
		if (customizedColumns.isEmpty()) {
      
			Field[] allFields = objectClass.getDeclaredFields();
                        fields.addAll(Arrays.asList(allFields));
		} else {
			Field[] allFields = objectClass.getDeclaredFields();

			Set<String> oldValues =  customizedColumns.keySet();
			List<String> oldValuesList = new ArrayList<>();
			for (String string : oldValues) {
				oldValuesList.add(string);
			}
			Collections.sort(oldValuesList);
			for (String string : oldValues) {
				for (Field field : allFields) {
					if(field.getName()==string){
						fields.add(field);
					}
				}
			}

					
			
		}
	}

}
