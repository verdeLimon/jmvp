/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
