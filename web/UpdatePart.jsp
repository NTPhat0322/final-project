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
        <style>
            .form label{
                display: inline-block;
                width: 100px;
            }
        
        </style>
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
                <input type="hidden" value="updatePart1" name="action"/>
                <input type="hidden" value="<%= p.getPartId() %>" name="partid"/>
                <div class="form">
                    <label>Part name: </label>
                    <input type="text" name="txtpartname" value="<%= p.getPartName() %>"/>
                </div>
                <div class="form">
                    <label>Purchaseprice: </label>
                    <input type="text" name="purchaseprice" value="<%= p.getPurchasePrice() %>" pattern="^[0-9]+(\.[0-9]{1,3})?$"/>
                </div>
                <div class="form">
                    <label>Retailprice: </label>
                    <input type="text" name="retailprice" value="<%= p.getRetailPrice() %>" pattern="^[0-9]+(\.[0-9]{1,3})?$"/>
                </div>
                <div>
                    <input type="submit" value="Update"/>
                </div>
                
            </form>
            <form action="MainServlet">
                <input type="hidden" value="searchPart" name="action"/>
                <input type="submit" value="Back"/>
            </form>
        </div>
    </body>
</html>
