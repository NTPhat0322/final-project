<%@page import="model.ServiceTicketDetail"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.ServiceTicket"%>
<%@page import="dao.ServiceTicketDAO"%>
<%@page import="model.Mechanic"%>
<%@page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Service Tickets</title>
        <style>
            body { font-family: Arial, sans-serif; background-color: #f4f4f4; }
            .container { width: 80%; margin: auto; padding-top: 20px; }
            h2 { text-align: center; }
            table { width: 100%; border-collapse: collapse; margin-top: 20px; background: white; }
            th, td { border: 1px solid #ddd; padding: 10px; text-align: center; }
            th { background-color: #007bff; color: white; }
            tr:nth-child(even) { background-color: #f9f9f9; }
            .search-box { display: flex; justify-content: center; gap: 10px; margin-bottom: 20px; }
            .search-box input, .search-box button { padding: 8px; font-size: 14px; }
            .btn-update { display: inline-block; padding: 8px 12px; background: #28a745; color: white; text-decoration: none; border-radius: 5px; }
            .btn-update:hover { background: #218838; }
        </style> 
    </head>
    <body>
        <div class="container">
            <h2>Service Tickets</h2>

            <form action="MainServlet" class="search-box">
                <input type="text" id="searchValue" name="searchValue" placeholder="Search by Car ID or Customer ID"
                       value="<%= request.getParameter("searchValue") != null ? request.getParameter("searchValue") : ""%>">
                <input type="date" id="searchDate" name="searchDate"
                       value="<%= request.getParameter("searchDate") != null ? request.getParameter("searchDate") : ""%>">
                <input type ="hidden" name ="action" value="viewServiceticket"/>
                <button type="submit">Search</button>
               
            </form>

            <%
                HttpSession s = request.getSession();
                Mechanic mechanic = (Mechanic) s.getAttribute("mechanic");
                if (mechanic == null) {
            %>
            <h3 style="text-align: center; color: red;">You are not authorized to view this page.</h3>
            <%
            } else {
                String mechanicID = mechanic.getId();
                ServiceTicketDAO dao = new ServiceTicketDAO();
                List<ServiceTicketDetail> tickets = dao.getListServiceTicketsDetailByMechanicID(mechanicID);

                // Lấy giá trị tìm kiếm từ request
                String searchValue = request.getParameter("searchValue");
                String searchDate = request.getParameter("searchDate");

                // Danh sách kết quả lọc
                List<ServiceTicketDetail> filteredTickets = new ArrayList<>();

                for (ServiceTicketDetail ticket : tickets) {
                    boolean matchValue = (searchValue == null || searchValue.isEmpty())
                            || ticket.getCarID().equalsIgnoreCase(searchValue)
                            || ticket.getCustID() == Integer.parseInt(searchValue);

                    boolean matchDate = (searchDate == null || searchDate.isEmpty())
                            || ticket.getDateReceived().equals(searchDate);

                    if (matchValue && matchDate) {
                        filteredTickets.add(ticket);
                    }
                }
            %>

            <table>
                <tr>
                    <th>Ticket ID</th>
                    <th>Service ID</th>
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
                    for (ServiceTicketDetail ticket : filteredTickets) {
                %>
                <tr>
                    <td><%= ticket.getServiceTicketID()%></td>
                    <td><%= ticket.getServiceID()%></td>
                    <td><%= ticket.getCarID()%></td>
                    <td><%= ticket.getCustID()%></td>
                    <td><%= ticket.getDateReceived()%></td>
                    <td><%= ticket.getDateReturned()%></td>
                    <td><%= ticket.getHours()%></td>
                    <td><%= ticket.getComment()%></td>
                    <td><%= ticket.getRate()%></td>
                    <td>
                        <a href="MainServlet?action=edit&ticketID=<%= ticket.getServiceTicketID()%>&serviceID=<%= ticket.getServiceID()%>" class="btn-update">Update</a>
                    </td>
                </tr>
                <%
                    }
                %>
            </table>
            <%
                }
            %>
        </div>
        <a href="MainServlet?action=mechanicDashBoard">Back</a>
    </body>
</html>
