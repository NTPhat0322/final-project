<%-- 
    Document   : createNewCust
    Created on : Feb 19, 2025, 5:45:39 PM
    Author     : ASUS
--%>

<%@page import="model.Mechanic"%>
<%@page import="model.SalePerson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <style>
        label {
            display: inline-block;
            width: 100px;
        }
        
        .head-page{
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
    </style>
    
    <body>
        <%
            SalePerson sp = (SalePerson)session.getAttribute("sale");
            if(sp == null) request.getRequestDispatcher("MainServlet?action=home").forward(request, response);
        %>
        
        <div class="head-page">
            <h1>Creating customer page</h1>
            <a href="MainServlet?action=salePersonDashBoard">Back to sale person dashboard</a>
        </div>
        
        <form action="MainServlet" accept-charset="UTF-8">
            <div>
                <label>Cust ID: </label>
                <input type="text" name="custID" placeholder="enter cust id" pattern="[0-9]+" required>*
            </div>
            <div>
                <label>Cust Name: </label>
                <input type="text" name="custName" placeholder="enter cust name" required>*
            </div>
            <div>
                <label>Cust Phone: </label>
                <input type="text" name="custPhone" placeholder="enter cust phone" pattern="[0-9]+">
            </div>
            <div>
                <label>Cust Sex </label>
                <input type="text" name="custSex" placeholder="enter cust sex">
            </div>
            <div>
                <label>Cust Address: </label>
                <input type="text" name="custAddress" placeholder="enter cust address">
            </div>
            <div><input type="submit" value="create"></div>
            <input type="hidden" value="createCustServ" name="action">
        </form>
        
        
        
        <%
            if(request.getAttribute("result") != null) {
        %>
        <h5><%= request.getAttribute("result") %></h5>
        <%
            }
        %>
    </body>
</html>
