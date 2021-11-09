/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Role;

/**
 *
 * @author 856622
 */
public class RoleDB {
    public List<Role> getAll() throws Exception {
        PreparedStatement statement = null;
        ResultSet results = null;
        
        ArrayList<Role> roleList = new ArrayList<Role>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = null;
        if(pool != null) {
            con = pool.getConnection();
        }
        
        String query = "SELECT * FROM role;";
        
        try {
            statement = con.prepareStatement(query);
            results = statement.executeQuery();
            while(results.next()) {
                int roleID = results.getInt(1);
                String roleName = results.getString(2);
                
                Role role = new Role(roleID, roleName);
                roleList.add(role);
            }
        }
        finally  {
            DBUtil.closePreparedStatement(statement);
            DBUtil.closeResultSet(results);
            pool.freeConnection(con);
        }
        return roleList;
    }
}
