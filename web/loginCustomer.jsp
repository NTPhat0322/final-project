<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Customer</title>
    </head>
    <body>
        <h1>Login Customer</h1>

        <%-- Hiển thị thông báo lỗi nếu có --%>
        <%
            if (request.getAttribute("ERROR") != null) {
        %>
        <p style="color: red;"><%= request.getAttribute("ERROR")%></p>
        <%
            }
        %>

        <form action="LoginCustomerServlet" method="post">
            <label for="name">Tên:</label>
            <input type="text" id="name" name="name" required><br><br>

            <label for="phone">Số điện thoại:</label>
            <input type="text" id="phone" name="phone" required><br><br>

            <input type="submit" value="Đăng nhập">
        </form>
    </body>
</html>
