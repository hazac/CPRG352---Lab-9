/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.util.ArrayList;
import models.Role;
import models.User;

/**
 *
 * @author 856622
 */
public class UserService {
    public User get(String email) throws Exception{
        UserDB userdb = new UserDB();
        User user = userdb.get(email);
        return user;
    }
    
    public ArrayList<User> getAll() throws Exception{
        UserDB userdb = new UserDB();
        ArrayList<User> users = userdb.getAll();
        return users;
    }
    
    public void insert(String email, Boolean isActive, String firstName, String lastName, String password, int roleId) throws Exception{
        RoleService rs = new RoleService();
        ArrayList<Role> roles = rs.getAll();
        
        Role role = null;
        for(Role r : roles){
            if(r.getRole_id() == roleId){
                role = r;
            }                
        }
        
        User newUser = new User (email, isActive, firstName, lastName, password, role);
        UserDB userdb = new UserDB();
        userdb.insert(newUser);        
    }
     
    public void update(String email, Boolean isActive, String firstName, String lastName, String password, int roleId) throws Exception{
        RoleService rs = new RoleService();
        ArrayList<Role> roles = rs.getAll();
        
        Role role = null;
        for(Role r : roles){
            if(r.getRole_id() == roleId){
                role = r;
            }                
        }
        
        User newUser = new User (email, isActive, firstName, lastName, password, role);
        UserDB userdb = new UserDB();
        userdb.update(newUser);        
    }
    
    public void delete(String email) throws Exception{
        User user = get(email);
        UserDB userdb = new UserDB();
        userdb.delete(user);
    }
}
