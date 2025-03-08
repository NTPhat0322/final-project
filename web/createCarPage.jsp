<%-- 
    Document   : createCarPage
    Created on : Feb 24, 2025, 12:16:35 PM
    Author     : ASUS
--%>

<%@page import="model.SalePerson"%>
<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
        .head-page{
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        </style>
    </head>
    <body>
        <%
            SalePerson sp = (SalePerson)session.getAttribute("sale");
            if(sp == null) request.getRequestDispatcher("MainServlet?action=home").forward(request, response);
        %>
        <div class="head-page">
            <h1>Create car page</h1>
            <a href="MainServlet?action=salePersonDashBoard">Back to sale person dashboard</a>
        </div>
        <form action="MainServlet">
            <div>
                <label>Serial Number: </label>
                <input type="text" name="serialNum" placeholder="enter serial number" 
                       value="<%= request.getParameter("serialNum") != null ? request.getParameter("serialNum") : ""%>"
                >
            </div>
            <div>
                <label>Model: </label>
                <input type="text" name="model" placeholder="enter model" 
                       value="<%= request.getParameter("model") != null ? request.getParameter("model") : ""%>"
                >
            </div>
            <div>
                <label>Color: </label>
                <input type="text" name="colour" placeholder="enter color" 
                       value="<%= request.getParameter("colour") != null ? request.getParameter("colour") : ""%>"                >
            </div>
            <div>
                <label>Year: </label>
                <select name="year">
                    <option value="">-----none-----</option>
                    <%
                    LocalDate d = LocalDate.now();       
                    for (int i = d.getYear(); i >= 1000; i--) {
                    %>
                    <option value="<%= i %>"><%= i %></option>
                    <%
                    }
                    %>
                </select>
            </div>
            <div><input type="submit" value="create"></div>
            <input type="hidden" name="action" value="createCarServ">
        </form>
                
        <%
        String rs = (String)request.getAttribute("result");
        if(rs != null) {
        %>
        <h5><%= rs %></h5>
        <%
        }
        %>
    </body>
</html>
