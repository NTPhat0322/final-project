<%-- 
    Document   : ViewServiceTickets
    Created on : Mar 12, 2025, 8:19:47 AM
    Author     : admin
--%>

<%@page import="model.ServiceTicketDetail"%>
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
                <th>Service Ticket ID</th>
                <th>Date Received</th>
                <th>Date Returned</th>
                <th>CustID</th>
                <th>CarID</th>
                <th>Hours</th>
                <th>Comment</th>
                <th>Rate</th>
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

                String mechanicID = mechanic.getId();
                ServiceTicketDAO dao = new ServiceTicketDAO();
                List<ServiceTicketDetail> tickets = dao.getServiceTicketsDetailByMechanicName(mechanicID);

                // Duyệt danh sách và hiển thị từng ticket
                for (ServiceTicketDetail ticket : tickets) {
            %>
            <tr>
                <td><%= ticket.getServiceTicketID()%></td>
                <td><%= ticket.getDateReceived()%></td>
                <td><%= ticket.getDateReturned()%></td>
                <td><%= ticket.getCustID()%></td>
                <td><%= ticket.getCarID()%></td>
                <td>
                    <form id="form-<%= ticket.getServiceTicketID()%>" action="UpdateServiceTicketServlet" method="post">
                        <input type="hidden" name="serviceTicketID" value="<%= ticket.getServiceTicketID()%>">
                        <input type="number" name="hours" value="<%= ticket.getHours()%>">
                        </td>
                        <td>
                            <input type="text" name="comment" value="<%= ticket.getComment() == null ? "" : ticket.getComment()%>">
                        </td>
                        <td>
                            <input type="number" name="rate" value="<%= ticket.getRate()%>">
                        </td>
                        <td>
                            <button type="submit">Update</button>
                    </form>
                </td>
            </tr>
            <% }%>
        </table>
        <a href="mechanicdashboard.jsp">Quay lại</a>
    </body>
</html>

