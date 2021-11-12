/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.util.List;
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
    
    public List<User> getAll() throws Exception{
        UserDB userdb = new UserDB();
        List<User> users = userdb.getAll();
        return users;
    }
    
    public void insert(String email, Boolean isActive, String firstName, String lastName, String password, int roleId) throws Exception{
        RoleService rs = new RoleService();
        List<Role> roles = rs.getAll();
        
        Role role = null;
        for (Role r : roles) {
            if(r.getRoleId() == roleId){
                role = r;
            }                
        }
        
        User newUser = new User (email, isActive, firstName, lastName, password);
        newUser.setRole(role);
        UserDB userdb = new UserDB();
        userdb.insert(newUser);        
    }
     
    public void update(String email, Boolean isActive, String firstName, String lastName, String password, int roleId) throws Exception{
        RoleService rs = new RoleService();
        List<Role> roles = rs.getAll();
        
        Role role = null;
        for(Role r : roles){
            if(r.getRoleId() == roleId){
                role = r;
            }                
        }
        
        User newUser = new User (email, isActive, firstName, lastName, password);
        newUser.setRole(role);
        UserDB userdb = new UserDB();
        userdb.update(newUser);        
    }
    
    public void delete(String email) throws Exception{
        UserDB userdb = new UserDB();
        User user = userdb.get(email);
        System.out.println("User" + user.getEmail());
        userdb.delete(user);
    }
}
