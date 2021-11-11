/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import models.Role;
import models.User;

/**
 *
 * @author 856622
 */
public class RoleDB {
    public List<Role> getAll() throws Exception {
       EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try{
           ArrayList<Role> roles = (ArrayList<Role>) em.createNamedQuery("Role.findAll", Role.class).getResultList();
           return roles;
        }            
        finally{
            em.close();
        }
    }
}
