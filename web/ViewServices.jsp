<%@ page import="model.Mechanic, dao.ServiceDAO, java.util.List, model.Service" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<meta charset="UTF-8">
<!DOCTYPE html>
<html>
    <head>
        <title>Danh sách dịch vụ</title>
        <script type="text/javascript">
            // Function to confirm delete action
            function confirmDelete(serviceID) {
                if (confirm("Bạn có chắc chắn muốn xóa dịch vụ này?")) {
                    window.location.href = "deleteServiceAction.jsp?serviceID=" + serviceID;
                }
            }
        </script>
    </head>
    <body>
        <h2>Dịch vụ của cơ khí</h2>
        <%
            // Lấy mechanicID từ session
            Mechanic m = (Mechanic) session.getAttribute("mechanic");
            String mechanicID = m.getId();

            if (mechanicID == null) {
                out.println("<p>Vui lòng đăng nhập.</p>");
            } else {
                ServiceDAO serviceDAO = new ServiceDAO();
                List<Service> services = serviceDAO.getServicesByMechanic(mechanicID);

                if (services.isEmpty()) {
                    out.println("<p>Không có dịch vụ nào.</p>");
                } else {
        %>
        <table border="1">
            <tr>
                <th>Service ID</th>
                <th>Service Name</th>
                <th>Hourly Rate</th>
                <th>Actions</th>
            </tr>
            <% for (Service service : services) {%>
            <tr>
                <td><%= service.getServiceID() %></td>
                <td>
                    <!-- Form chỉnh sửa dịch vụ -->
                    <form action="updateServiceAction.jsp" method="post" accept-charset="UTF-8">
                        <input type="hidden" name="serviceID" value="<%= service.getServiceID() %>">
                        <input type="text" name="serviceName" value="<%= service.getServiceName() %>" required>
                    </td>
                    <td>
                        <input type="number" name="hourlyRate" value="<%= service.getHourlyRate() %>" required>
                    </td>
                    <td>
                        <!-- Nút Update -->
                        <button type="submit">Update</button>
                    </form>

                    <!-- Nút Delete với xác nhận -->
                    <button onclick="confirmDelete('<%= service.getServiceID() %>')">Delete</button>
                </td>
            </tr>
            <% } %>
        </table>
        <br>
        <!-- Nút tạo dịch vụ mới -->
        <a href="MainServlet?action=createService"><button>Tạo Dịch Vụ Mới</button></a>
        <%
                }
            }
        %>
    </body>
</html>
