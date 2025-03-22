<%@ page import="model.Mechanic, dao.ServiceDAO, java.util.List, model.Service" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<meta charset="UTF-8">
<!DOCTYPE html>
<html>
    <head>
        <title>Danh sách dịch vụ</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 20px;
                padding: 0;
                background-color: #f4f4f4;
                text-align: center;
            }
            h2 {
                color: #333;
            }
            table {
                width: 80%;
                margin: 20px auto;
                border-collapse: collapse;
                background: #fff;
                border-radius: 8px;
                overflow: hidden;
                box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            }
            th, td {
                padding: 12px;
                border: 1px solid #ddd;
                text-align: center;
            }
            th {
                background: #007bff;
                color: #fff;
            }
            tr:nth-child(even) {
                background: #f9f9f9;
            }
            button {
                padding: 8px 12px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }
            .update-btn {
                background: #28a745;
                color: white;
            }
            .delete-btn {
                background: #dc3545;
                color: white;
            }
            .create-btn {
                display: block;
                width: 200px;
                text-align: center;
                margin: 20px auto;
                background: #17a2b8;
                color: white;
                text-decoration: none;
                padding: 10px;
                border-radius: 5px;
            }
            .back-btn {
                display: inline-block;
                margin-top: 20px;
                padding: 10px 20px;
                background: #6c757d;
                color: white;
                text-decoration: none;
                border-radius: 5px;
            }
            .message {
                text-align: center;
                font-size: 16px;
                margin: 10px 0;
            }
            .success {
                color: green;
            }
            .error {
                color: red;
            }
            input[type="text"], input[type="number"] {
                padding: 5px;
                width: 90%;
                border: 1px solid #ccc;
                border-radius: 5px;
                text-align: center;
            }
        </style>
        <script type="text/javascript">
            function confirmDelete(serviceID) {
                if (confirm("Bạn có chắc chắn muốn xóa dịch vụ này?")) {
                    window.location.href = "MainServlet?action=deleteService&serviceID=" + serviceID;
                }
            }
        </script>
    </head>
    <body>
        <h2>Dịch vụ của cơ khí</h2>
        <%
            String message = (String) request.getAttribute("message");
            String error = (String) request.getAttribute("error");
        %>

        <% if (message != null) {%>
        <p class="message success"><%= message%></p>
        <% } %>

        <% if (error != null) {%>
        <p class="message error"><%= error%></p>
        <% } %>

        <%
            Mechanic m = (Mechanic) session.getAttribute("mechanic");
            if (m == null) {
                response.sendRedirect("MainServlet?action=home"); 
                return;
            }
            String mechanicID = m.getId();

            if (mechanicID == null) {
                out.println("<p class='error'>Vui lòng đăng nhập.</p>");
            } else {
                ServiceDAO serviceDAO = new ServiceDAO();
                List<Service> services = serviceDAO.getServicesByMechanic(mechanicID);

                if (services.isEmpty()) {
                    out.println("<p class='message'>Không có dịch vụ nào.</p>");
                } else {
        %>
        <table>
            <tr>
                <th>Service ID</th>
                <th>Service Name</th>
                <th>Hourly Rate</th>
                <th>Actions</th>
            </tr>
            <% for (Service service : services) {%>
            <tr>
                <td><%= service.getServiceID()%></td>
                <td>
                    <form action="MainServlet" accept-charset="UTF-8">
                        <input type="hidden" name="serviceID" value="<%= service.getServiceID()%>">
                        <input type="text" name="serviceName" value="<%= service.getServiceName()%>" required>
                        <input type="hidden" name="action" value="updateservice"/>
                </td>
                <td>
                    <input type="number" name="hourlyRate" value="<%= service.getHourlyRate()%>" required>
                </td>
                <td>
                    <button type="submit" class="update-btn">Update</button>
                    </form>
                    <button class="delete-btn" onclick="confirmDelete(<%= service.getServiceID()%>)">Delete</button>
                </td>
            </tr>
            <% } %>
        </table>
        <br>
        <a href="MainServlet?action=createService" class="create-btn">Tạo Dịch Vụ Mới</a>

    <%
            }
        }
    %>
    <a href="MainServlet?action=mechanicDashBoard" class="back-btn">Back</a>
</body>
</html>
