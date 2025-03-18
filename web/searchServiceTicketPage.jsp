<%-- 
    Document   : searchServiceTicketPage
    Created on : Feb 27, 2025, 8:39:41 PM
    Author     : ASUS
--%>

<%@page import="model.ServiceTicket"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        option:first-child {
            text-align: center;
        }
        a {
            text-decoration: none;
        }
        </style>
    </head>
    <body>
        <!--check sale person valid-->
        <%
            SalePerson sp = (SalePerson)session.getAttribute("sale");
            if(sp == null) request.getRequestDispatcher("MainServlet?action=home").forward(request, response);
        %>
        
        <%
            CustomerDAO custD = new CustomerDAO();
            ArrayList<Customer> tmp = custD.searchCustomerByName("");
            ArrayList<Customer> custList = new ArrayList<>();
                for (Customer c : tmp) {
                    if(c.isStatus())
                        custList.add(c);
            }
        %>

        <div class="head-page">
            <h1>Search service ticket page</h1>
            <a href="MainServlet?action=salePersonDashBoard">Back to sale person dashboard</a>
        </div>
        <form action="MainServlet">
            <div>
                <label>Customer ID: </label>
                <select name="custID">
                    <option value="">---------None---------</option>
                    <%
                        for (Customer c : custList) {
                    %>
                    <option value="<%= c.getCustID()%>">
                        ID: <%= c.getCustID()%>,
                        Name: <%= c.getCustName()%>,
                        Phone: <%= c.getCustPhone()%>,
                        Sex: <%= c.getCustSex()%>,
                        Address: <%= c.getCustAddress()%>
                    </option>
                    <%
                        }
                    %>
                </select>
                <input type="submit" value="search">
            </div>
            <input type="hidden" name="action" value="searchServiceTicketServ">
        </form>
                
        <!--lấy kq từ servlet-->
    <c:if test="${requestScope.error != null}">
        ${requestScope.error}
    </c:if>
    <%
        ArrayList<ServiceTicket> list = (ArrayList<ServiceTicket>)request.getAttribute("result");
        if(list != null) {
            if(!list.isEmpty()) {
    %>
    <table border="1">
        <tr>
            <th>Service Ticket ID</th>
            <th>Date Received</th>
            <th>Date Returned</th>
            <th>Car ID</th>
            <th>Customer ID</th>
        </tr>
        <%
            for (ServiceTicket s : list) {
        %>
        <tr>
            <td><%= s.getSeviceTicketID() %></td>
            <td><%= s.getDateReceived() %></td>
            <td><%= s.getDateReturned()%></td>
            <td><%= s.getCarID()%></td>
            <td><%= s.getCustID()%></td>
        </tr>
        <%
            }
        %>
    </table>
    <%
            }else{
    %>
    <h5>There is no service ticket</h5>
    <%
            }
        }
    %>
     
    </body>
</html>
