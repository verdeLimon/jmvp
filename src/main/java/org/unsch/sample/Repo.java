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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author verde
 */
public class Repo {
    public static List<UserDTO> getUsersRepo(){
     List<UserDTO> users = new ArrayList<>();
            users.add(new UserDTO(1, "u1",""));
            users.add(new UserDTO(8, "u",""));
           users.add(new UserDTO(4, "u41",""));
            users.add(new UserDTO(5, "u5",""));
            
            return users;
    }
    
}
