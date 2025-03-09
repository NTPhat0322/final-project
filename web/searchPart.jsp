<%-- 
    Document   : searchPart
    Created on : Mar 8, 2025, 11:27:07 AM
    Author     : ASUS
--%>

<%@page import="model.SalePerson"%>
<%@page import="java.util.ArrayList"%>
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
            HttpSession s = request.getSession();
            SalePerson sp = (SalePerson)s.getAttribute("sale");
            if(sp != null){
        %>
        <h1>Search part</h1>
        <p><a href="MainServlet?action=salePersonDashBoard">BACK</a></p>
        <div>
            <form action="MainServlet">
                <input type="hidden" name="action" value="SearchPartByName"/>
                <input type="text" name="partName" placeholder="Enter part name"/>
                <input type="submit" value="Find"/>
            </form>
        </div>
        <!--in part ở đây-->
        <%
            ArrayList<Part> partList = (ArrayList<Part>) request.getAttribute("partlist");
            if(partList != null){
                if(partList.size() > 0){
        %>
        <table border="1">
            <tr>
                <th>Part Id</th>
                <th>Part Name</th>
                <th>Purchase Price</th>
                <th>Retail Price</th>
            </tr>
            <%
                for (Part p : partList) {
            %>
                        <tr>
                            <td><%= p.getPartId() %></td>
                            <td><%= p.getPartName() %></td>
                            <td><%= p.getPurchasePrice() %></td>
                            <td><%= p.getRetailPrice() %></td>
                            <td><a href="#" onclick="confirmMess(<%= p.getPartId() %>)">Delete</a></td>
                            <td><a href="MainServlet?action=updatePart&partID=<%= p.getPartId() %>">Update</a></td>
                        </tr>
            <%
                    }
            %>
        </table>
                    
        <%
                }else{
        %>
        <p>There is not part</p>
        <%
                }
            }
        %>
        
        
        <%
            }else{
                request.getRequestDispatcher("MainServlet?action=home").forward(request, response);
            }
        %>
    </body>
    
    
    <script>
        function confirmMess(id){
            var option = confirm('Ban muon xoa part nay khong?');
            if(option === true){
                window.location.href = 'MainServlet?action=deletePart&partID=' + id;
            }
        }
    </script>
    </body>
</html>
