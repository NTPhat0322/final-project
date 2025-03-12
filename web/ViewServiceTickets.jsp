<%-- 
    Document   : ViewServiceTickets
    Created on : Mar 12, 2025, 8:19:47 AM
    Author     : admin
--%>

<%@page import="model.Mechanic"%>
<%@page import="java.util.List"%>
<%@page import="model.ServiceTicket"%>
<%@page import="dao.ServiceTicketDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Danh sách Service Ticket</h1>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Date Received</th>
                <th>Date Returned</th>
                <th>Customer ID</th>
                <th>Car ID</th>
                <th>Action</th>
            </tr>
            <%
                // Gọi DAO để lấy danh sách service tickets
                HttpSession s = request.getSession();
                Mechanic mechanic = (Mechanic) s.getAttribute("mechanic");
                if (mechanic == null) {
                    response.sendRedirect("LoginMechanic.jsp");
                    return;
                }

                String mechanicName = mechanic.getName();
                ServiceTicketDAO dao = new ServiceTicketDAO();
                List<ServiceTicket> tickets = dao.getServiceTicketsByMechanicName(mechanicName);

                // Duyệt danh sách và hiển thị từng ticket
                for (ServiceTicket ticket : tickets) {
            %>
            <tr>
                <td><%= ticket.getSeviceTicketID()%></td>
                <td><%= ticket.getDateReceived()%></td>
                <td><%= ticket.getDateReturned()%></td>
                <td><%= ticket.getCustID()%></td>
                <td><%= ticket.getCarID()%></td>


                <td>
                    <a href="UpdateServiceTicketServlet?ticketID=<%= ticket.getSeviceTicketID()%>">
                        Update
                    </a>
                </td>

            </tr>
            <% }%>
        </table>
        <a href="mechanicDashboard.jsp">Quay lại</a>
    </body>
</html>

