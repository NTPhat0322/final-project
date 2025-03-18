<%-- 
    Document   : searchCust
    Created on : Feb 19, 2025, 5:53:00 PM
    Author     : ASUS
--%>

<%@page import="model.SalePerson"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Customer"%>
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
            a {
                text-decoration: none;
            }
            
        </style>
    </head>
    <body>
        <%
            HttpSession s = request.getSession();
            SalePerson sp = (SalePerson)s.getAttribute("sale");
            if(sp != null) {
        %>

        <div class="head-page">
            <h1>Search cust</h1>
            <a href="MainServlet?action=salePersonDashBoard">Back to sale person dashboard</a>
        </div>
        
        <form action="MainServlet" accept-charset="UTF-8">
            <div>
                <label>Customer name: </label>
                <input type="text" name="custName" placeholder="enter cust name"
                       value="<%= request.getParameter("custName") != null ? request.getParameter("custName") : ""%>"
                       >
                <input type="submit" value="find">
            </div>
            <input type="hidden" name="action" value="searchCustByName">
        </form>
        
                
            <!--hiển thị kq của tìm kiếm-->
                                   
                <%

                    ArrayList<Customer> custList = (ArrayList<Customer>)request.getAttribute("custList");
                    if(custList != null) {
                        if(custList.size() > 0) {
                %>
                    <table border="1">
                    <tr>
                        <th>Cust ID</th>
                        <th>Cust Name</th>
                        <th>Cust Phone</th>
                        <th>Cust Sex</th>
                        <th>Cust Address</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                <%
                            for (Customer c : custList) {
                %>
                    <tr>
                        <td><%= c.getCustID() %></td>
                        <td><%= c.getCustName()%></td>
                        <td><%= c.getCustPhone()%></td>
                        <td><%= c.getCustSex()%></td>
                        <td><%= c.getCustAddress()%></td>
                        <td>
                            <a href='MainServlet?action=deleteCust&custID=<%= c.getCustID() %>'>Delete</a>
                        </td>
                        <td><a href='MainServlet?action=updateCust&custID=<%= c.getCustID() %>'>Update</a></td>
                    </tr>
                    
                <%
                            }
                %>
                    </table>
                <%
                        } else {
                %>
                <div>There is no customer</div>
                <%
                        }
                    }
                %>
                
            <!--hiển thị kết quả của việc delete-->
            
            <%
                if (request.getAttribute("result") != null) {
            %>
            <h5><%= request.getAttribute("result")%></h5>
            <%
                }
            %>
            
        <%
            }else {
                request.getRequestDispatcher("MainServlet?action=home").forward(request, response);
            }
        %>
    </body>
</html>
