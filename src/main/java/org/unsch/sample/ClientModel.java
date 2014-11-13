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
