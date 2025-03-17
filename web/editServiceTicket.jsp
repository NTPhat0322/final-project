<%@page import="model.Mechanic"%>
<%@page import="model.ServiceTicketDetail"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.ServiceTicket"%>
<%@page import="dao.ServiceTicketDAO"%>
<%@page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Service Ticket</title>
        <style>
            body { font-family: Arial, sans-serif; }
            form { width: 50%; margin: auto; }
            label { font-weight: bold; display: block; margin-top: 10px; }
            input, textarea { width: 100%; padding: 8px; margin-top: 5px; }
            button { margin-top: 15px; padding: 10px; cursor: pointer; }
        </style>
    </head>
    <body>
        <h2>Edit Service Ticket</h2>
        <%
            // Lấy ticketID từ URL
            String ticketID =request.getParameter("ticketID");
            HttpSession s = request.getSession();
            Mechanic m = (Mechanic) s.getAttribute("mechanic");
            String id = m.getId();
            if (ticketID == null || ticketID.isEmpty()) {
        %>
            <p>Error: Ticket ID is missing!</p>
        <%
            } else {
                // Lấy dữ liệu từ database
                ServiceTicketDAO dao = new ServiceTicketDAO();
                ServiceTicketDetail ticket = dao.getServiceTicketsDetailByMechanicID(id);
                
                if (ticket == null) {
        %>
            <p>Service Ticket not found!</p>
        <%
                } else {
        %>
            <form action="UpdateServiceTicketServlet" method="post">
                <!-- Hidden input để gửi ticketID -->
                <input type="hidden" name="ticketID" value="<%= id%>">

                <label>Car ID:</label>
                <input type="text" name="carID" value="<%= ticket.getCarID() %>" readonly>

                <label>Customer ID:</label>
                <input type="text" name="custID" value="<%= ticket.getCustID() %>" readonly>

                <label>Date Received:</label>
                <input type="text" name="dateReceived" value="<%= ticket.getDateReceived() %>" readonly>

                <label>Hours Worked:</label>
                <input type="number" name="hours" value="<%= ticket.getHours() %>">

                <label>Comments:</label>
                <textarea name="comments"><%= ticket.getComment()%></textarea>

                <label>Rating:</label>
                <input type="number" name="rating" value="<%= ticket.getRate()%>" min="0" >

                <button type="submit">Update Ticket</button>
            </form>
        <%
                }
            }
        %>
    </body>
</html>
