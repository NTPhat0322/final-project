<%-- 
    Document   : logincustpage
    Created on : Mar 11, 2025, 9:56:52 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login customer</h1>
        <div>
            <div>
                <form action="MainServlet">
                    <p><input type="text" name="cusname" placeholder="Enter your name" checked=""/></p>
                    <p><input type="submit" value="Login"/></p>
                    <input type="hidden" name="action" value="loginCust"/>
                </form>
            </div>
            <div>
                <a href="MainServlet?action=home">BACK</a>
            </div>
        </div>
        <%
            if(request.getAttribute("messcust") != null){
                %>
                <p><%= request.getAttribute("messcust") %></p>
        <%
            }
        %>
    </body>
</html>
