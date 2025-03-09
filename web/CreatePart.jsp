<%-- 
    Document   : CreatePart
    Created on : Mar 8, 2025, 11:20:01 AM
    Author     : ASUS
--%>

<%@page import="model.SalePerson"%>
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
            SalePerson sp = (SalePerson)s.getAttribute("sale");
            if(sp != null) {
        %>
        <h1>Create part</h1>
        <p><a href="MainServlet?action=salePersonDashBoard">BACK</a></p>
        
        <%
            String message =(String) request.getAttribute("message");
            if(message != null){
        %>
        <p style="color: red"><%= message %></p>
        <%
            }
        %>
        <div>
            <form action="MainServlet">
                <input type="hidden" name="action" value="createPart"/>
                <div>
                    <label>Part id: </label>
                    <input type="text" name="partid" placeholder="Enter part id" pattern="[0-9]{1,3}" required=""/>
                </div>
                <div>
                    <label>Part name: </label>
                    <input type="text" name="partname" placeholder="Enter part name" required=""/>
                </div>
                <div>
                    <label>Purchase price: </label>
                    <input type="text" name="pprice" placeholder="Enter purchase price" pattern="^[0-9]+(\.[0-9]{1,3})?$"/>
                </div>
                <div>
                    <label>Retail price: </label>
                    <input type="text" name="rprice" placeholder="Enter retail price" pattern="^[0-9]+(\.[0-9]{1,3})?$"/>
                </div>
                <div><input type="submit" value="Create"/></div>
                
            </form>
        </div>
       
        
        <%
            }
        %>
    </body>
</html>
