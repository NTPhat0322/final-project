<%@page import="model.ServiceTicketDetail"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.ServiceTicket"%>
<%@page import="dao.ServiceTicketDAO"%>
<%@page import="model.Mechanic"%>
<%@page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Service Tickets</title>
        <style>
            body { font-family: Arial, sans-serif; }
            table { width: 100%; border-collapse: collapse; }
            th, td { border: 1px solid #ddd; padding: 8px; }
            th { background-color: #f2f2f2; }
        </style> 
    </head>
    <body>
        <h2>Service Tickets</h2>
        <form method="GET" action="serviceTicketsServlet">
            <label for="searchValue">Search by Car ID or Customer ID:</label>
            <input type="text" id="searchValue" name="searchValue" value="<%= request.getParameter("searchValue") != null ? request.getParameter("searchValue") : ""%>">

            <label for="searchDate">Search by Date Received:</label>
            <input type="date" id="searchDate" name="searchDate" value="<%= request.getParameter("searchDate") != null ? request.getParameter("searchDate") : ""%>">

            <button type="submit">Search</button>
        </form>
        <%
            HttpSession s = request.getSession();
            Mechanic mechanic = (Mechanic) s.getAttribute("mechanic");
            if (mechanic == null) {
        %>
        <h3>You are not authorized to view this page.</h3>
        <%
        } else {
            String mechanicID = mechanic.getId();
            ServiceTicketDAO dao = new ServiceTicketDAO();
            List<ServiceTicketDetail> tickets = dao.getListServiceTicketsDetailByMechanicID(mechanicID);
        %>
        <table>
            <tr>
                <th>Ticket ID</th>
                <th>Car ID</th>
                <th>Customer ID</th>
                <th>Date Received</th>
                <th>Date Returned</th>
                <th>Hours</th>
                <th>Comment</th>
                <th>Rate</th>
                <th>Actions</th>
            </tr>
            <%
                for (ServiceTicketDetail ticket : tickets) {
            %>
            <tr>
                <td><%= ticket.getServiceTicketID()%></td>
                <td><%= ticket.getCarID()%></td>
                <td><%= ticket.getCustID()%></td>
                <td><%= ticket.getDateReceived()%></td>
                <td><%= ticket.getDateReturned()%></td>
                <td><%= ticket.getHours()%></td>
                <td><%= ticket.getComment()%></td>
                <td><%= ticket.getRate()%></td>
                <td>
                    <a href="editServiceTicket.jsp?ticketID=<%= mechanicID%>">Update</a>
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