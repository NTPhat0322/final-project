<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="dao.CustomerDAO, model.Customer" %>
<%
    HttpSession s = request.getSession();
    Customer c = (Customer) session.getAttribute("customer");
    int custID = c.getCustID();
    CustomerDAO dao = new CustomerDAO();
    Customer customer = dao.getCustomerById(custID);
    String updateStatus = (String) session.getAttribute("updateStatus");
    session.removeAttribute("updateStatus"); // Xóa thông báo sau khi hiển thị
%>
<html>
    <head>
        <title>Update Customer</title>
    </head>
    <body>
        <h2>Customer Profile</h2>
        <% if (updateStatus != null) {%>
        <p style="color: <%= updateStatus.contains("failed") ? "red" : "green"%>;">
            <%= updateStatus%>
        </p>
        <% }%>

        <form action="UpdateCustomerServlet" method="post">
            Customer ID: <input type="text" name="custID" value="<%= customer.getCustID()%>" readonly><br>
            Name: <input type="text" name="custName" value="<%= customer.getCustName()%>" required><br>
            Phone: <input type="text" name="phone" value="<%= customer.getCustPhone()%>" required><br>
            Sex: <input type="text" name="sex" value="<%= customer.getCustSex()%>" required><br>
            Address: <input type="text" name="cusAddress" value="<%= customer.getCustAddress()%>" required><br>
            <input type="submit" value="Update">
        </form>

        <!-- Nút Back chỉ hiển thị nếu cập nhật thất bại -->
        <% if (updateStatus != null && updateStatus.contains("failed")) { %>
        <br>
        <button onclick="window.history.back();">Back</button>
        <% }%>
    </body>
</html>