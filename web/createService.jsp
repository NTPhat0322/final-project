<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tạo Dịch Vụ Mới</title>
</head>
<body>
    <h2>Tạo Dịch Vụ Mới</h2>
    <form action="CreateServiceServlet" method="post">
        <label for="serviceName">Tên Dịch Vụ:</label>
        <input type="text" id="serviceName" name="serviceName" required><br><br>

        <label for="hourlyRate">Giá Theo Giờ (VND):</label>
        <input type="number" id="hourlyRate" name="hourlyRate" required><br><br>

        <button type="submit">Tạo Dịch Vụ</button>
    </form>

    <br>
    <a href="ViewServices.jsp">Trở về danh sách dịch vụ</a>
</body>
</html>
