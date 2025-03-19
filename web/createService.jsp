<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tạo Dịch Vụ Mới</title>
</head>
<body>
    <h2>Create new Service</h2>
    <form action="MainServlet" method="post">
        <label for="serviceName">Service Name:</label>
        <input type="text" id="serviceName" name="serviceName" required><br><br>

        <label for="hourlyRate">HourlyRate:</label>
        <input type="number" id="hourlyRate" name="hourlyRate" required><br><br>
        
        <input type="hidden" name="action" value="createSV"/>
        <button type="submit">Create</button>
    </form>
        
    <br>
    <a href="MainServlet?action=viewservice">Back</a>
</body>
</html>
