<%@page import="java.math.BigDecimal"%>
<%@page import="dao.PartsUsedDAO"%>
<%@page import="dao.ServiceMechanicDAO"%>
<%@ page import="java.util.List" %>
<%@ page import="model.ServiceTicket, model.ServiceMechanic, model.PartsUsed, model.Mechanic, model.Part" %>
<%@ page import="dao.ServiceTicketDAO" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%
    // Lấy serviceTicketID từ request
    String serviceTicketID = request.getParameter("id");

    // Khởi tạo DAO để truy vấn database
    ServiceTicketDAO dao = new ServiceTicketDAO();
    ServiceMechanicDAO md = new ServiceMechanicDAO();
    PartsUsedDAO ud = new PartsUsedDAO();
    ServiceTicket ticket = null;
    List<ServiceMechanic> mechanics = null;
    List<PartsUsed> partsUsedList = null;

    if (serviceTicketID != null) {
        try {
            String id = serviceTicketID;
            ticket = dao.getServiceTicketById(id);
            mechanics = md.getServiceMechanicsByTicketID(id);
            partsUsedList = ud.getPartsUsedByTicketID(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    // Kiểm tra nếu không tìm thấy phiếu dịch vụ
    if (ticket == null) {
%>
<html>
    <head><title>Service Ticket Not Found</title></head>
    <body>
        <h2>Service Ticket Not Found!</h2>
        <a href="serviceTicketList.jsp">Back to List</a>
    </body>
</html>
<%
} else {
    // Tính tổng chi phí dịch vụ và phụ tùng
    BigDecimal totalServiceCost = BigDecimal.ZERO;
    BigDecimal totalPartsCost = BigDecimal.ZERO;

    for (ServiceMechanic sm : mechanics) {
        BigDecimal hoursWorked = BigDecimal.valueOf(sm.getHours());
        BigDecimal hourlyRate = BigDecimal.valueOf(50); // Giả sử 50$/giờ
        totalServiceCost = totalServiceCost.add(hoursWorked.multiply(hourlyRate));
    }

    for (PartsUsed part : partsUsedList) {
        BigDecimal numberUsed = BigDecimal.valueOf(part.getNumberUsed());
        BigDecimal cost = numberUsed.multiply(part.getPurchasePrice());
        totalPartsCost = totalPartsCost.add(cost);
    }

    BigDecimal totalRepairCost = totalServiceCost.add(totalPartsCost);
%>
<html>
    <head>
        <title>Service Ticket Details</title>
    </head>
    <body>
        <h2>Service Ticket Details</h2>
        <table border="1">
            <tr><th>Service Ticket ID</th><td><%= ticket.getSeviceTicketID()%></td></tr>
            <tr><th>Date Received</th><td><%= ticket.getDateReceived()%></td></tr>
            <tr><th>Date Returned</th><td><%= ticket.getDateReturned()%></td></tr>
            <tr><th>Customer ID</th><td><%= ticket.getCustID()%></td></tr>
            <tr><th>Car ID</th><td><%= ticket.getCarID()%></td></tr>
        </table>

        <h3>Mechanic Information</h3>
        <table border="1">
            <tr>
                <th>Mechanic Name</th><th>Hours Worked</th><th>Comments</th><th>Rating</th>
            </tr>
            <% for (ServiceMechanic sm : mechanics) {%>
            <tr>
                <td><%= sm.getServiceName()%></td>
                <td><%= sm.getHours()%></td>
                <td><%= sm.getComment()%></td>
                <td><%= sm.getRate()%></td>
            </tr>
            <% } %>
        </table>

        <h3>Services Used</h3>
        <table border="1">
            <tr><th>Service Name</th><th>Hours</th><th>Hourly Rate</th><th>Total Cost</th></tr>
                    <% for (ServiceMechanic sm : mechanics) {%>
            <tr>
                <td><%= sm.getServiceName()%></td>
                <td><%= sm.getHours()%></td>
                <td>50$</td> <!-- Giả sử hourly rate cố định -->
                <td><%= sm.getHours() * 50%></td>
            </tr>
            <% } %>
        </table>

        <h3>Parts Used</h3>
        <table border="1">
            <tr><th>Part Name</th><th>Number Used</th><th>Purchase Price</th><th>Total Cost</th></tr>
                    <% for (PartsUsed part : partsUsedList) {%>
            <tr>
                <td><%= part.getPartName()%></td>
                <td><%= part.getNumberUsed()%></td>
                <td><%= part.getPurchasePrice()%></td>
                <td><%= part.getTotalCost() %></td>
            </tr>
            <% }%>
        </table>

        <h3>Total Repair Cost</h3>
        <table border="1">
            <tr><th>Total Service Cost</th><td><%= totalServiceCost%></td></tr>
            <tr><th>Total Parts Cost</th><td><%= totalPartsCost%></td></tr>
            <tr><th>Total Repair Cost</th><td><%= totalRepairCost%></td></tr>
        </table>

        <br>
        <a href="CustomerViewServiceTicket.jsp">Back to List</a>
    </body>
</html>
<%
    }
%>
