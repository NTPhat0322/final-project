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
        <meta charset="UTF-8">
        <title>Edit Service Ticket</title>
        <style>
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f5f5f5;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }
            .container {
                background: #fff;
                padding: 25px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 400px;
                text-align: center;
            }
            h2 {
                color: #333;
            }
            form {
                display: flex;
                flex-direction: column;
                gap: 10px;
            }
            label {
                font-weight: bold;
                text-align: left;
            }
            input, textarea {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 14px;
            }
            textarea {
                resize: vertical;
                height: 80px;
            }
            button {
                background-color: #28a745;
                color: white;
                padding: 10px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
                margin-top: 10px;
            }
            button:hover {
                background-color: #218838;
            }
            .error {
                color: red;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Update Service Ticket</h2>
            <%
                String ticketID = request.getParameter("ticketID");
                HttpSession s = request.getSession();
                Mechanic m = (Mechanic) s.getAttribute("mechanic");
                if (m == null) {
            %>
            <p class="error">Error: Mechanic session not found!</p>
            <%
                    return;
                }
                String id = m.getId();
                if (ticketID == null || ticketID.isEmpty()) {
            %>
            <p class="error">Error: Ticket ID is missing!</p>
            <%
            } else {
                ServiceTicketDAO dao = new ServiceTicketDAO();
                ServiceTicketDetail ticket = dao.getServiceTicketsDetailByMechanicID(id, ticketID);
                HttpSession ss = request.getSession();
                ss.setAttribute("ticket", ticket);
                if (ticket == null) {
            %>
            <p class="error">Service Ticket not found!</p>
            <%
            } else {
            %>
            <form action="MainServlet" accept-charset="UTF-8">
                <input type="hidden" name="ticketID" value="<%= ticketID%>">

                <label>Car ID:</label>
                <input type="text" name="carID" value="<%= ticket.getCarID()%>" readonly>

                <label>Customer ID:</label>
                <input type="text" name="custID" value="<%= ticket.getCustID()%>" readonly>

                <label>Date Received:</label>
                <input type="text" name="dateReceived" value="<%= ticket.getDateReceived()%>" readonly>

                <label>Hours Worked:</label>
                <input type="number" name="hours" value="<%= ticket.getHours()%>">

                <label>Comments:</label>
                <textarea name="comments"><%= ticket.getComment()%></textarea>

                <label>Rating:</label>
                <input type="number" name="rating" value="<%= ticket.getRate()%>" min="0">

                <input type="hidden" name="mechanicID" value="<%= m.getId()%>"/>
                <input type="hidden" name="serviceID" value="<%=ticket.getServiceID() %>"/>
                <input type="hidden" name="action" value="editServlet"/>

                <button type="submit">Update Ticket</button>
            </form>
            <%
                    }
                }
            %>
        </div>
    </body>
</html>
