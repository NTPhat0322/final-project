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
            <h1>Update customer jsp</h1>
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
        <div><%= c.toString() %></div>
        <form action="MainServlet">
            <p>*If you do not want to update, you make it blank</p>
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
                    if(request.getAttribute("updatedCust") != null) {
            %>
            <h5>Customer after updating</h5>
            <h5><%= request.getAttribute("updatedCust") %></h5>
            <%
                    }
                }
            %>
    </body>
</html>
