/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Role;
import models.User;

/**
 *
 * @author 856622
 */
public class UserDB {
    public List<User> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try{
           List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();           
           System.out.println("inside doGet try");
           return users;
        }            
        finally{
            em.close();
        }
    }
    
    public User get(String targetEmail) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try{
            User user = em.find(User.class, targetEmail);
            return user;
        }
        finally{
            em.close();
        }
    }
    
    public void insert(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
            
        }
        finally{
            em.close();
        }
    }

    public void update(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
            
        }
        finally{
            em.close();
        }
    }

    public void delete(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
            
        }
        finally{
            em.close();
        }
    }        
}
