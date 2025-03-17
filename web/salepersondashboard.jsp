<%-- 
    Document   : salepersondashboard
    Created on : Feb 18, 2025, 11:31:47 PM
    Author     : ASUS
--%>

<%@page import="model.SalePerson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .head-page {
                display: flex;
                justify-content: space-between;
                align-items: center;
            }
        </style>
        
    </head>
    <body>
        <!--salepersondashboard-->
        <%
            HttpSession s = request.getSession();
            SalePerson sp = (SalePerson)s.getAttribute("sale");
            if(sp != null) {
        %>
                <div class="head-page">
                    <h1>Welcome to sale person dashboard</h1>
                    <form>
                        <div><input type="submit" name="action" value="logout"></div>
                    </form>
                </div>
                <h3>Function for customer</h3>  
                <div><a href='MainServlet?action=searchCust'>Search customer</a></div>
                <div><a href='MainServlet?action=createCust'>Create new customer</a></div>
                               
                <h3>Function for cars</h3> 
                <div><a href='MainServlet?action=searchCar'>Search car</a></div>
                <div><a href='MainServlet?action=createCar'>Create new car</a></div>
                
                <h3>Service tickets</h3>
                <div><a href='MainServlet?action=searchServiceTicketPage'>Search service ticket</a></div>
                <div><a href='MainServlet?action=createTicketPage'>Create service ticket</a></div>
                
                <h3>Function for parts</h3>
                <div><a href="MainServlet?action=searchPart">Search part</a></div>
                <div><a href="MainServlet?action=createPartPage">Create part</a></div>
                
                <h3>Create invoice for customer</h3>
                <div><a href="MainServlet?action=createInvoicePage">Create Invoice</a></div>
                <div><a href="MainServlet?action=reportPage">Report</a></div>
        <%
            }else {
                request.getRequestDispatcher("MainServlet?action=home").forward(request, response);
            }
        %>
    </body>
</html>
