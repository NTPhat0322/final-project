<%-- 
    Document   : updateCustPage
    Created on : Feb 22, 2025, 6:19:05 PM
    Author     : ASUS
--%>

<%@page import="model.SalePerson"%>
<%@page import="model.Customer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.CustomerDAO"%>
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
        <div class="head-page">
            <h1>Updating customer page</h1>
            <a href="MainServlet?action=salePersonDashBoard">Back to sale person dashboard</a>
        </div>
        
        <%
            SalePerson sp = (SalePerson)session.getAttribute("sale");
            if(sp == null) request.getRequestDispatcher("MainServlet?action=home").forward(request, response);
        %>
        
        <%
            String custID = "";
            if(request.getParameter("custID") == null)
                request.getRequestDispatcher("MainServlet?action=home").forward(request, response);
            custID = request.getParameter("custID");
            CustomerDAO d = new CustomerDAO();
            Customer c = d.searchCustById(Integer.parseInt(custID));
        %>
        <p>Customer before updating</p>
        <table border="1">
            <tr>
                <th>Customer ID</th>
                <th>Customer Name</th>
                <th>Customer Phone</th>
                <th>Customer Sex</th>
                <th>Customer Address</th>
            </tr>
            <tr>
                <td><%= c.getCustID() %></td>
                <td><%= c.getCustName()%></td>
                <td><%= c.getCustPhone()%></td>
                <td><%= c.getCustSex()%></td>
                <td><%= c.getCustAddress()%></td>
            </tr>
        </table>
            <form action="MainServlet" accept-charset="UTF-8">
            <p style="color: red">*If you do not want to update, you make it blank</p>
            <div><input type="text" name="nCustName" placeholder="enter new cust name"></div>
            <div><input type="text" name="nCustPhone" placeholder="enter new cust phone" pattern="[0-9]+"></div>
            <div><input type="text" name="nCustSex" placeholder="enter new cust sex"></div>
            <div><input type="text" name="nCustAddress" placeholder="enter new cust address"></div>
            <div><input type="submit" value="update" name="action"></div>
            <input type="hidden" name="custID" value="<%= custID %>">
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
