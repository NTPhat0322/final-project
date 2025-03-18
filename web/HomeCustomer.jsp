<%-- 
    Document   : HomeCustomer
    Created on : Mar 16, 2025, 3:13:16 PM
    Author     : admin
--%>

<%@page import="model.Customer"%>
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
            Customer c = (Customer) s.getAttribute("customer");
            if(c != null){
        %>
        <h2>Welcome <%= c.getCustName() %></h2>
        <ul>
            <li><a href="CustomerViewServiceTicket.jsp">View Service Ticket</a></li>
            <li><a href="MainServlet?action=viewinvoice&custid=<%= c.getCustID() %>">View invoice</a></li>
            <li><a href="customerProfile.jsp">Change Profile</a></li>
        </ul>
        <a href="MainServlet?action=loginCustForm">Logout</a>
        
        <%
            }
        %>
        
    </body>
</html>
