<%@page import="dao.ServiceDAO"%>
<%@page import="model.Service"%>
<%@page import="model.ServiceMechanic"%>
<%@page import="java.util.List"%>
<%@page import="model.ServiceTicket"%>
<%@page import="dao.ServiceMechanicDAO"%>
<%@page import="dao.ServiceTicketDAO"%>
<%@page import="dao.PartDAO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Service Ticket Detail</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body class="container mt-4">

        <h1 class="text-primary">Service Ticket Detail</h1>

        <%
            String ticketID = request.getParameter("id");
            if (ticketID == null || ticketID.isEmpty()) {
        %>
        <p class="alert alert-danger">Không tìm thấy phiếu dịch vụ!</p>
        <a href="CustomerViewServiceTicket.jsp" class="btn btn-secondary">Quay lại</a>
        <%
                return;
            }

            ServiceTicketDAO ticketDAO = new ServiceTicketDAO();
            ServiceMechanicDAO mechanicDAO = new ServiceMechanicDAO();
            ServiceDAO svDAO = new ServiceDAO();

            ServiceTicket ticket = ticketDAO.getServiceTicketById(ticketID);
            List<ServiceMechanic> mechanics = mechanicDAO.getServiceMechanicsByTicketID(ticketID);

            if (ticket == null) {
        %>
        <p class="alert alert-danger">Phiếu dịch vụ không tồn tại!</p>
        <a href="MainServlet?action=homecust" class="btn btn-secondary">Quay lại</a>
        <%
                return;
            }

            if (mechanics.isEmpty()) {
        %>
        <p class="alert alert-warning">Service Ticket ID <strong><%= ticket.getSeviceTicketID()%></strong> không có chi tiết.</p>
        <a href="MainServlet?action=CustomerViewServiceTicket" class="btn btn-secondary">Quay lại</a>
        <%
                return;
            }
        %>

        <h2 class="mt-4">Thông tin phiếu dịch vụ</h2>
        <table class="table table-bordered">
            <tr><th>Service Ticket ID</th><td><%= ticket.getSeviceTicketID()%></td></tr>
            <tr><th>Date Received</th><td><%= ticket.getDateReceived()%></td></tr>
            <tr><th>Date Returned</th><td><%= ticket.getDateReturned()%></td></tr>
            <tr><th>Customer ID</th><td><%= ticket.getCustID()%></td></tr>
            <tr><th>Car ID</th><td><%= ticket.getCarID()%></td></tr>
        </table>

        <form action="MainServlet"">
            <input type="hidden" name="action" value="updateMechanicDetails">
            <input type="hidden" name="serviceTicketID" value="<%= ticket.getSeviceTicketID()%>">

            <table class="table table-hover">
                <thead class="table-dark">
                    <tr>  
                        <th>Mechanic ID</th>
                        <th>Service Name</th>
                        <th>Hours Worked</th>
                        <th>Comments</th>
                        <th>Rating</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (ServiceMechanic mechanic : mechanics) {
                            String svName = svDAO.getServiceNameByServiceID(mechanic.getServiceID()); // Lấy danh sách serviceName
%>
                    <tr>
                        <td><%= mechanic.getMechanicID()%></td>

                        <td>
                            <%
                                if (svName.isEmpty()) {
                                    out.print("Không có linh kiện");
                                } else {
                                    out.print(svName + "<br>"); // Xuống dòng nếu có nhiều linh kiện 
                                }
                            %>
                        </td>
                        <td>
                            <input type="number" name="hours_<%= mechanic.getMechanicID()%>" 
                                   value="<%= mechanic.getHours()%>" class="form-control" readonly="">
                        </td>
                        <td>
                            <input type="text" name="comment_<%= mechanic.getMechanicID()%>" 
                                   value="<%= mechanic.getComment()%>" class="form-control" readonly="">
                        </td>
                        <td>
                            <input type="number" name="rate_<%= mechanic.getMechanicID()%>" 
                                   value="<%= mechanic.getRate()%>" class="form-control" readonly="">
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </form>

        <a href="MainServlet?action=CustomerViewServiceTicket" class="btn btn-secondary mt-3">Quay lại</a>

    </body>
</html>
