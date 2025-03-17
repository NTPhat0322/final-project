<%@ page import="dao.ServiceDAO" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<meta charset="UTF-8">
<!DOCTYPE html>
<html>
    <head>
        <title>Cập nhật dịch vụ - Kết quả</title>
    </head>
    <body>
        <h2>Kết quả cập nhật dịch vụ</h2>
        <%
            // Nhận các tham số từ form
            String serviceID = request.getParameter("serviceID");
            String serviceName = request.getParameter("serviceName");
            String hourlyRate = request.getParameter("hourlyRate");

            // Cập nhật dịch vụ vào cơ sở dữ liệu
            ServiceDAO serviceDAO = new ServiceDAO();
                boolean success = serviceDAO.updateService(serviceID, serviceName, hourlyRate);

            if (success) {
                out.println("<p>Dịch vụ đã được cập nhật thành công.</p>");
            } else {
                out.println("<p>Cập nhật thất bại. Vui lòng thử lại.</p>");
            }

            out.println("<br><a href='ViewServices.jsp'>Quay lại danh sách dịch vụ</a>");
        %>
    </body>
</html>
