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
package org.unsch.sample;

import org.unsch.core.impl.reflect.AppControllerReflect;
import org.unsch.core.impl.reflect.AppControllerReflectFactory;

/**
 *
 * @author verde
 */
public class ClientPresenter {

        ClientView view;
        ClientModel model;

        
        public ClientPresenter(ClientView view, ClientModel model) {
                this.view = view;
                this.model = model;
        }

        public void removeUser(){
          // this is the user selected by the JTable
          UserDTO user = model.getUser();
            System.out.println("user:"+ user);
            System.out.println("return: "+model.deleteUser(user));
          // some code to delete the user from the database
          
          AppControllerReflect appController = AppControllerReflectFactory.getAppControllerInstance();
          // call the AppController to refresh the view
          appController.refreshView(view);
          for (UserDTO temp : model.getUsers()) {
			System.out.println("temp: "+temp);
		}
        }
        public void actionExemple(){
             UserDTO user = model.getUser();
            System.out.println("user: "+ user);
        
        }

        
}