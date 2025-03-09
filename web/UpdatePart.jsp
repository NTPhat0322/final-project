<%-- 
    Document   : UpdatePart
    Created on : Mar 8, 2025, 11:32:23 AM
    Author     : ASUS
--%>

<%@page import="model.SalePerson"%>
<%@page import="model.Part" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            SalePerson sp = (SalePerson)session.getAttribute("sale");
            if(sp == null) request.getRequestDispatcher("MainServlet?action=home").forward(request, response);
        %>
        
        <%
            Part p = (Part) request.getAttribute("part");
        %>
        <div>
            <form action="MainServlet">
                <p>ID: <%= p.getPartId() %></p>
                <p><input type="hidden" value="updatePart1" name="action"</p>
                <p><input type="hidden" value="<%= p.getPartId() %>" name="partid"/></p>
                <p>Part name: <input type="text" name="txtpartname" value="<%= p.getPartName() %>"/></p>
                <p>Purchaseprice: <input type="text" name="purchaseprice" value="<%= p.getPurchasePrice() %>"/></p>
                <p>Retailprice: <input type="text" name="retailprice" value="<%= p.getRetailPrice() %>"/></p>
                <p><input type="submit" value="Update"/></p>
                <p><a href="MainServlet?action=searchPart">BACK</a></p>
            </form>
        </div>
    </body>
</html>
