/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;


import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import models.Role;
import models.User;

/**
 *
 * @author 856622
 */
public class UserDB {
    public ArrayList<User> getAll() throws Exception {
        PreparedStatement statement = null;
        ResultSet results = null;
        
        ArrayList<User> userList = new ArrayList<User>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = null;
        if(pool != null) {
            con = pool.getConnection();
        }
        
        String query = "SELECT email, active, first_name, last_name, password, role, role_name " +
                       "FROM user, role " + 
                       "WHERE user.role = role.role_id;";
        
        try {
            statement = con.prepareStatement(query);
            results = statement.executeQuery();
            while(results.next()) {
                String email = results.getString(1);
                boolean active = results.getBoolean(2);
                String firstname = results.getString(3);
                String lastname = results.getString(4);
                String password = results.getString(5);
                Role role = new Role(results.getInt(6), results.getString(7));
                
                User newUser = new User(email, active, firstname, lastname, password, role);
                userList.add(newUser);
            }
        }
        finally  {
            DBUtil.closePreparedStatement(statement);
            DBUtil.closeResultSet(results);
            pool.freeConnection(con);
        }
        return userList;
    }

    public User get(String targetEmail) throws Exception {
        PreparedStatement statement = null;
        ResultSet results = null;
        Connection con = null;
        User userFound = null;
        
        ConnectionPool pool = ConnectionPool.getInstance();
        if(pool != null) {
            con = pool.getConnection();
        }
        
        String query = "SELECT email, active, first_name, last_name, password, role, role_name " +
                       "FROM user, role " + 
                       "WHERE user.role = role.role_id AND user.email = ?";
        
        try {      
            statement = con.prepareStatement(query);
            statement.setString(1, targetEmail);
            results = statement.executeQuery();
            
            results.next();
            String email = results.getString(1);
            boolean active = results.getBoolean(2);
            String firstname = results.getString(3);
            String lastname = results.getString(4);
            String password = results.getString(5);
            Role role = new Role(results.getInt(6), results.getString(7)); 
            
            userFound = new User(email, active, firstname, lastname, password, role);
        }
        finally {
            DBUtil.closePreparedStatement(statement);
            DBUtil.closeResultSet(results);
            pool.freeConnection(con);
        }
        
        return userFound;
    }

    public void insert(User user) throws Exception {
        PreparedStatement statement = null;
        Connection con = null;
        
        ConnectionPool pool = ConnectionPool.getInstance();
        if(pool != null) {
            con = pool.getConnection();
        }
        
        String query = "INSERT INTO user (email, active, first_name, last_name, password, role) " +
                       "VALUES (?, ?, ?, ?, ?, ?); ";
        
        try {
            statement = con.prepareStatement(query);
            
            statement.setString(1, user.getEmail());
            statement.setBoolean(2, user.isActive());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getPassword());
            statement.setInt(6, user.getRole().getRole_id());
            
            statement.executeUpdate();
        }
        finally {
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(con);
        }
    }

    public void update(User user) throws Exception {
        PreparedStatement statement = null;
        Connection con = null;
        
        ConnectionPool pool = ConnectionPool.getInstance();
        if(pool != null) {
            con = pool.getConnection();
        }
        
        String query = "UPDATE user " +
                       "SET active = ? " +
                       ", first_name = ? " +
                       ", last_name = ? " +
                       ", password = ? " +
                       ", role = ? " +
                       "WHERE email = ?";
        
        try {
            statement = con.prepareStatement(query);
            
            statement.setBoolean(1, user.isActive());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getRole().getRole_id());
            statement.setString(6, user.getEmail());
            
            statement.executeUpdate();
        }
        finally {
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(con);
        }
    }

    public void delete(User user) throws Exception {
        PreparedStatement statement = null;
        Connection con = null;
        
        ConnectionPool pool = ConnectionPool.getInstance();
        if(pool != null) {
            con = pool.getConnection();
        }
        
        String query = "DELETE FROM user WHERE email = ?";
        
        try {
            statement = con.prepareStatement(query);
            statement.setString(1, user.getEmail());    
            statement.executeUpdate();
        }
        finally {
            DBUtil.closePreparedStatement(statement);
            pool.freeConnection(con);
        }
    }
}
