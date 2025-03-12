<%-- 
    Document   : mechanicdashboard
    Created on : Feb 19, 2025, 12:42:22 AM
    Author     : ASUS
--%>

<%@page import="model.Mechanic"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            HttpSession s = request.getSession();
            Mechanic m = (Mechanic) s.getAttribute("mechanic");
            if (m == null) {
        %>
        <h2>You are not valid</h2>
        <%
            } else {
        %>
        <h2>welcome to mechanic dashboard</h2>
        <ul>
            <li><a href="ViewServiceTickets.jsp">View Service Ticket</a></li>
            <li><a href="SearchServiceTicket.jsp">Search Service Ticket</a></li>
            <li><a href="crud_services.jsp">CRUD Services</a></li>
        </ul>
        <%
            }
        %>

    </body>
</html>
