<%-- 
    Document   : customerDashboard
    Created on : Mar 11, 2025, 10:29:45 AM
    Author     : ASUS
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
            Customer c =(Customer) s.getAttribute("cus");
            
            if(c != null){
        %>
        <h1>Welcome <%= c.getCustName() %></h1>
        <p><a href="MainServlet?action=viewinvoice&custid=<%= c.getCustID() %>">View invoice</a></p>
        <%
            }else{
                request.getRequestDispatcher("MainServlet?action=home").forward(request, response);
            }
        %>
        <div>
            <form action="MainServlet">
                <input type="hidden" name="action" value="logoutcust"/>
                <input type="submit" value="logout"/>
            </form>
        </div>
    </body>
</html>
