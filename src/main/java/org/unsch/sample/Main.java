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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // TODO code application logic here
         ClientView view = new ClientView();
         ClientModel model = new ClientModel(Repo.getUsersRepo());
         ClientPresenter presenter = new ClientPresenter(view, model);
                
         view.setVisible(true);
                
                
        AppControllerReflect appController = AppControllerReflectFactory
                                .getAppControllerInstance();
        appController.bind(view, model, presenter);
        
    }
    
}
