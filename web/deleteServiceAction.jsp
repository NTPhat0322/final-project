<%@ page import="dao.ServiceDAO" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<meta charset="UTF-8">
<%
    String serviceID = request.getParameter("serviceID");
    if (serviceID != null) {
        ServiceDAO serviceDAO = new ServiceDAO();
        boolean result = serviceDAO.deleteService(serviceID);

        if (result) {
            out.println("<p>Dịch vụ đã được xóa thành công.</p>");
        } else {
            out.println("<p>Đã có lỗi xảy ra khi xóa dịch vụ.</p>");
        }
    }
%>
<a href="ViewServices.jsp">Quay lại danh sách dịch vụ</a>
