/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unsch.sample;

import org.unsch.impl.reflect.AppControllerReflect;
import org.unsch.impl.reflect.AppControllerReflectFactory;

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