/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.RoleDB;
import java.util.ArrayList;
import models.Role;

/**
 *
 * @author 856622
 */
public class RoleService {
    
    /*public Role getRole(int id) throws Exception{
        ArrayList<Role> roles = getAll();
        for(Role r : roles){
            if(r.getRole_id() == id){
                return r;
            }                
        }
        return null;
    }*/
    
    public ArrayList<Role> getAll() throws Exception{
        RoleDB roledb = new RoleDB();
        ArrayList<Role> roles = (ArrayList)roledb.getAll();
        return roles;
    }
    
}
