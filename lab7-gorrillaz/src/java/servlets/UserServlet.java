/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;
//hellllllo

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.UserService;

/**
 *
 * @author 856622
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService service = new UserService();
        request.setAttribute("activeMessage", "Active");
        ArrayList<User> userList = null;
        try {
            userList = service.getAll();
            request.setAttribute("list", userList);
            for(User s : userList){
                System.out.println(s.getRole().getRole_name());
            }

        } catch (Exception e) {
        }

        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService service = new UserService();
        String action = request.getParameter("action");
        //request.setAttribute("activeMessage", "Active");
       
        if (action.equals("add")) {
           
            String userEmail = request.getParameter("email");
            boolean userActive = false;
            if (request.getParameter("active") != null) {
                userActive = true;
            }
            String userFirstname = request.getParameter("firstName");
            String userLastname = request.getParameter("lastName");
            String userPassword = request.getParameter("password");
            int userRole = Integer.parseInt(request.getParameter("role"));
            

            try {
                service.insert(userEmail, userActive, userFirstname, userLastname, userPassword, userRole);                
                request.setAttribute("message", "User successfully added to database");
                doGet(request, response);
            } 
            catch(Exception e) {
                //If the user being added has an email that is already in the database
                request.setAttribute("message", "Email already exist in database");
                System.out.println(e.toString());
                doGet(request,response);
            }
            
     

        }
        else if (action.equals("fillEdit")) {
            String mail = request.getParameter("email");
            try{
                User user = service.get(mail); 
                
                request.setAttribute("email_edit", user.getEmail());  
                request.setAttribute("fname_edit", user.getFirstName());   
                request.setAttribute("lname_edit", user.getLastName());
                request.setAttribute("password_edit", user.getPassword());
            } 
            catch (Exception e) {
                System.out.println(e.toString());
            }
            doGet(request, response);
            return;
        }
        else if (action.equals("edit")) {
            String userEmail = request.getParameter("email_edit");
            String userFirstname = request.getParameter("fname_edit");
            String userLastname = request.getParameter("lname_edit");
            String userPassword = request.getParameter("password_edit");
            int userRole = Integer.parseInt(request.getParameter("role_edit"));
            boolean userActive = false;
            if (request.getParameter("active_edit") != null) {
                userActive = true;
            }
            
            try{
                service.update(userEmail, userActive, userFirstname, userLastname, userPassword, userRole);
                request.setAttribute("messageEdit", "Edit was successful");
                
                doGet(request, response);
            } 
            catch (Exception e) {
                System.out.print(e.toString());
            }

        }
        else if (action.equals("delete")) {
            String mail = request.getParameter("email");
            try {
                service.delete(mail);
            }
            catch (Exception e){
                System.out.println(e.toString()); 
            }
            request.setAttribute("messageDelete", "User successfully deleted from database");
            doGet(request,response);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

}
