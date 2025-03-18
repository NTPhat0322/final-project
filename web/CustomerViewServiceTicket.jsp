<%-- 
    Document   : CustomerViewServiceTicket
    Created on : Mar 16, 2025, 11:29:28 PM
    Author     : admin
--%>

<%@page import="java.util.List"%>
<%@page import="dao.ServiceTicketDAO"%>
<%@page import="model.ServiceTicket"%>
<%@page import="model.Customer"%>
<%@page import="model.Mechanic"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Customer Service Tickets</h1>
        <%
            HttpSession s = request.getSession();
            Customer customer = (Customer) s.getAttribute("customer");
            if (customer == null) {
        %>
        <p>Bạn chưa đăng nhập. Vui lòng <a href="loginCustomer.jsp">đăng nhập</a> để xem phiếu dịch vụ.</p>
        <%
                return; // Dừng hiển thị tiếp nội dung nếu chưa đăng nhập
            }

            // Lấy danh sách phiếu dịch vụ từ DAO
            ServiceTicketDAO dao = new ServiceTicketDAO();
            List<ServiceTicket> tickets = dao.getServices(customer.getCustID());

            if (tickets.isEmpty()) {
        %>
        <p>Không có phiếu dịch vụ nào.</p>
        <%
        } else {
        %>
        <table border="1">
            <tr>
                <th>ServiceTicketID</th>
                <th>dateReceived</th>
                <th>dateReturned</th>
                <th>carID</th>
                <th>View More</th>
            </tr>
            <%
                for (ServiceTicket ticket : tickets) {
            %>
            <tr>
                <td><%= ticket.getSeviceTicketID()%></td>
                <td><%= ticket.getDateReceived()%></td>
                <td><%= ticket.getDateReturned()%></td>
                <td><%= ticket.getCarID()%></td>
                <td>
                    <a href="MainServlet?action=serviceDetail&id=<%= ticket.getSeviceTicketID()%>">
                        View Detail
                    </a>
                </td>

            </tr>
            <%
                }
            %>
        </table>
        <%
            }
        %>

    </body>
</html>
