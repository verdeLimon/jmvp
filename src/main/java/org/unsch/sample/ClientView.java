/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unsch.sample;

import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent.EventType;
import org.unsch.annotation.Action;
import org.unsch.annotation.MVP;
import org.unsch.annotation.Model;


/**
 *
 * @author verde
 */
@MVP(modelClass = ClientModel.class, presenterClass = ClientPresenter.class)
public class ClientView extends JFrame {

   @Action(name = "actionExemple", EventType=MouseListener.class , EventAction="mouseClicked")
   @Model(initProperty="users", property = "user")
   private JTable table;

   @Action(name = "removeUser")
   JButton delButton;

   
   public ClientView() {
                setTitle("Client Application");
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setContentPane(new JPanel());
                setSize(1000, 600);
                setLocationRelativeTo(null);
                setLayout(null);
               add(getScrollPane());
               add(getDelButton());
        }

    public JTable getTable() {
                if(table==null){
                        table= new JTable();
                }
                return table;
        }

     public JScrollPane getScrollPane() {
                JScrollPane scrollPane = new JScrollPane(getTable());
                scrollPane.setBounds(10, 10, 350, 350);
                return scrollPane;
        }

      public JButton getDelButton() {
                if (delButton == null) {
                        delButton = new JButton("Delete");
                        delButton.setBounds(850, 10, 70, 40);
                }
                return delButton;
        }
}