/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unsch.sample;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author verde
 */
public class ClientModel implements Serializable {

        private List<UserDTO> users;
        private UserDTO user;
            
        
            
        public ClientModel(List<UserDTO> users){
            this.users = users;
        }
        public List<UserDTO> getUsers() {
                // some code to retrieve your users
            
                return users;
        }
        public boolean deleteUser(UserDTO user){
        return users.remove(user);
        }
        public void setUsers(List<UserDTO> users) {
                this.users = users;
        }

        public UserDTO getUser() {
                return user;
        }

        public void setUser(UserDTO user) {
                this.user = user;
        }
}
