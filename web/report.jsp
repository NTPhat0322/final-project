<%-- 
    Document   : report
    Created on : Mar 13, 2025, 3:21:28 AM
    Author     : Admin
--%>

<%@page import="dao.MechanicDAO"%>
<%@page import="dao.PartDAO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="dao.SaleInvoiceDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .infor{
                display: flex;
                justify-content: space-around;
            }
            .title{
                text-align: center;
            }
            
        </style>
    </head>
    <body>
        <h1 class="title">REPORT</h1>
        <div class="infor">
            <!--so lượng xe bán được theo năm-->
            <div>
                <h3>Cars sold by year</h3>
                <table border="1">
                    <tr>
                        <th>Year</th>
                        <th>Quantity</th>
                    </tr>
            <%
                SaleInvoiceDAO sid = new SaleInvoiceDAO();
                HashMap<Integer, Integer> rs = sid.getCarSoldByYear();
                if(rs != null && !rs.isEmpty()){
                    for (int p : rs.keySet()) {
            %>
            <tr>
                <td><%= p %></td>
                <td><%= rs.get(p) %></td>
            </tr>    
            <%
                        }
                }
            %>
                </table>
            </div>
            <!--doanh thu bán theo năm-->    
            <div>
                <h3>Car sale revenue</h3>
                <table border="1">
                    <tr>
                        <th>Year</th>
                        <th>Revenue</th>
                    </tr>
                    <%
                    HashMap<Integer, Double> rsr = sid.getRevenueByYear();
                    if(rsr != null && !rsr.isEmpty()){
                        for (int y : rsr.keySet()) {
                                %>
                                <tr>
                                    <td><%= y %></td>
                                    <td><%= String.format("%.0f", rsr.get(y)) %></td>
                                </tr>    

                <%
                            }
                    }
                %>
                </table>
            </div>
            <!--Mẫu xe bán chạy-->
            <div>
                <h3>Best-selling car models</h3>
                <table border="1">
                    <tr>
                        <th>Model</th>
                        <th>Units Sold</th>
                    </tr>
                    <%
                        LinkedHashMap<String, Integer> rsbest = sid.getBestSaler();


                        if(rsbest != null && !rsbest.isEmpty()){
                            for (Map.Entry<String, Integer> b : rsbest.entrySet()) {
                    %>
                                    <tr>
                                        <td><%= b.getKey() %></td>
                                        <td><%= b.getValue() %></td>
                                    </tr>
                    <%
                                }
                        }
                    %>

                </table>
            </div>

    <!--        //linh kiện sài nhiều-->
            <div>
                <h3>Best used parts</h3>
                <table border="1">
                    <tr>
                        <th>Parts</th>
                    </tr>
                    <%
                        PartDAO partdao = new PartDAO();
                        LinkedHashMap<String, Integer> rspart = partdao.getBestUsedParts();
                        if(rspart != null && !rspart.isEmpty()){
                            for (Map.Entry<String, Integer> part : rspart.entrySet()) {
                    %>
                    <tr>
                        <td><%= part.getKey() %></td>
                        
                    </tr>
                    <%
                            }
                        }
                    %>
                </table>
            </div>
            <!--TOP 3 thợ máy tiêu biểu-->
            <div>
                <h3>TOP GOOD MECHANIC</h3>
                <table border="1">
                    <tr>
                        <th>Mechanic name</th>
                    </tr>
                    <%
                        MechanicDAO mdao = new MechanicDAO();
                        LinkedHashMap<String, Integer> rsMechanic = mdao.getMechanicGood();
                        if(rsMechanic != null && !rsMechanic.isEmpty()){
                            for (Map.Entry<String, Integer> Mech : rsMechanic.entrySet()) {
                    %>
                    <tr>
                        <td><%= Mech.getKey() %></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </table>
            </div>
        </div>
        <div>
            <form action="MainServlet">
                <input type="hidden" value="salePersonDashBoard" name="action"/>
                <input type="submit" value="BACK"/>
            </form>
        </div>
    </body>
</html>
