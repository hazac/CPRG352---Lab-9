
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
        <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
        <title>Lab 7</title>
    </head>

    <body  style="background-color:darkgray;">
        <div class="row">
            <div class="col p-3" >
                <h1>Add User</h1>

                <form method="POST" action="user">

                    <input type="text" name="email" placeholder="Email"
                           pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" required>
                    <br>
                    <label class="container"> Active
                        <input type="checkbox" name="active">
                        <span class="checkmark"></span>
                    </label>

                    <input type="text" name="firstName" placeholder="First Name" required>
                    <br>
                    <input type="text" name="lastName" placeholder="Last Name" required>
                    <br>
                    <input type="password" name="password" placeholder="Password" required>
                    <br>
                    <select name="role" >

                        <option value="1" name="userRole">System Admin</option>
                        <option value="2" name="userRole">Regular User</option>
                        <option value="3" name="userRole">Company Admin</option>
                    </select>
                    <br>
                    <input type="hidden" name="action" value="add">
                    <input type="submit" value="Save">
                    <p>${message}</p>

                </form>
            </div>


            <div class="col p-3" >

                <h1>Manage users</h1>
                <p>${messageDelete}</p>
                <table id="table">
                    <tr>
                        <th>Email</th>
                        <th>Active</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr> 
                    <c:forEach items="${list}" var="item">
                        <tr>
                            <td>${item.email}</td>
                            <td><c:if test = "${item.getActive()}">
                                    ${activeMessage}
                                </c:if></td>
                            <td>${item.firstName}</td>
                            <td>${item.lastName}</td>
                            <form method="POST" action="user" float="left">
                                <td>
                                    <input type="hidden" name="email" value="${item.email}">
                                    <input type="hidden" name="action" value="fillEdit">                                     
                                    <input type="submit" value="Edit">
                                </td>
                            </form>
                            <form method="POST" action="user" float="left">
                                <td>
                                    <input type="hidden" name="email" value="${item.email}">
                                    <input type="hidden" name="action" value="delete">                                     
                                    <input type="submit" value="Delete">
                                </td>
                            </form>
                        </tr>
                    </c:forEach> </table>



                </form>

            </div>

            <div class="col p-3" >

                <h1>Edit user</h1>               
                <form float="left" method="POST" action="user">

                    <input type="text" id="eml" name="email_edit" value="${email_edit}"
                           pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" readonly>
                    <br>
                    <label class="container"> Active
                        <input type="checkbox" name="active_edit" value="${active_edit}">
                        <span class="checkmark"></span>
                    </label>

                    <input type="text" id="fname" name="fname_edit" value="${fname_edit}" >
                    <br>
                    <input type="text" id="lname" name="lname_edit" value="${lname_edit}" >
                    <br>
                    <input type="password" id="pass" name="password_edit" value="${password_edit}">
                    <br>
                    <select name="role_edit" id="role" aria-placeholder="user.role.role_name">
                        <option value="1" name="userRole">System Admin</option>
                        <option value="2" name="userRole">Regular User</option>
                        <option value="3" name="userRole">Company Admin</option>
                    </select>
                    <br>                    
                    <input type="hidden" name="action" value="edit">
                    <input type="submit" value="Save">
                    <input type="reset" value="Cancel">
                    <p>${messageEdit}</p>
                </form>                
            </div>
        </div>



    </body>
</html>
