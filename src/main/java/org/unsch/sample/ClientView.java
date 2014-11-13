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

import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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