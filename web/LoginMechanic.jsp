<%-- 
    Document   : LoginMechanic
    Created on : Mar 12, 2025, 7:38:29 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Mechanic</title>
    </head>
    <body>
        <form action="LoginMechanicServlet" method="post" >
            <input type="text" name="name" placeholder="Enter Name" required= >
            <button type="submit">Login</button>
        </form>
        <%
            if (request.getAttribute("ERROR") != null) {
                out.print(request.getAttribute("ERROR"));
            }
        %>
    </body>
</html>
