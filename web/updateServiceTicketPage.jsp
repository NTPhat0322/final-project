<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.ServiceTicketDetail"%>
<%@page import="dao.ServiceTicketDAO"%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Service Ticket</title>
</head>
<body>
    <h2>Edit Service Ticket</h2>
    <%
        int ticketID = Integer.parseInt(request.getParameter("ticketID"));
        ServiceTicketDAO dao = new ServiceTicketDAO();
        ServiceTicketDetail ticket = dao.getServiceTicketsDetailByMechanicID(ticketID);
        if (ticket == null) {
    %>
        <h3>Ticket not found!</h3>
    <%
        } else {
    %>
    <form action="UpdateServiceTicketServlet" method="POST">
        <input type="hidden" name="ticketID" value="<%= ticket.getServiceTicketID() %>">
        <label>Hours:</label>
        <input type="number" name="hours" value="<%= ticket.getHours() %>" required><br>
        <label>Comment:</label>
        <input type="text" name="comment" value="<%= ticket.getComment() %>" required><br>
        <label>Rate:</label>
        <input type="number" name="rate" step="0.1" value="<%= ticket.getRate() %>" required><br>
        <input type="submit" value="Update">
    </form>
    <%
        }
    %>
</body>
</html>