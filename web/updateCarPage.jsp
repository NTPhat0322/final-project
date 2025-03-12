<%-- 
    Document   : updateCarPage
    Created on : Feb 24, 2025, 12:21:18 PM
    Author     : ASUS
--%>

<%@page import="java.time.LocalDate"%>
<%@page import="model.Car"%>
<%@page import="dao.CarDAO"%>
<%@page import="model.SalePerson"%>
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
            <h1>Updating car page</h1>
            <a href="MainServlet?action=salePersonDashBoard">Back to sale person dashboard</a>
        </div>
        <%
            SalePerson sp = (SalePerson)session.getAttribute("sale");
            if(sp == null) request.getRequestDispatcher("MainServlet?action=home").forward(request, response);
        %>
        <%
            String carIDs = request.getParameter("carID");
            if(carIDs == null || carIDs.isEmpty())
                request.getRequestDispatcher("MainServlet?action=home").forward(request, response);
            int carID = Integer.parseInt(carIDs);
            CarDAO cD = new CarDAO();
            Car c = cD.getCarByCarID(carID);
        %>
        <p>Car before updating</p>
        <table border="1">
            <tr>
                <th>Car ID</th>
                <th>Car Number</th>
                <th>Car Model</th>
                <th>Car Color</th>
                <th>Car Year</th>
            </tr>
            <tr>
                <td><%= c.getCarID()%></td>
                <td><%= c.getSerialNumber()%></td>
                <td><%= c.getModel()%></td>
                <td><%= c.getColour()%></td>
                <td><%= c.getYear()%></td>
            </tr>
        </table>
        
        <p style="color: red">*If you do not want to update, you make it blank</p>
        
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
            <div><input type="submit" value="update"></div>
            <input type="hidden" name="action" value="updateCarServ">
            <input type="hidden" name="carID" value="<%= c.getCarID() %>">
        </form>
        
        
        <%
            String result = (String)request.getAttribute("result");
            if(result != null) {
        %>
        <h5><%= result %></h5>
        <%      
            }
        %>
    </body>
</html>
