<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <title>Login Mechanic</title>
    <style>
        body { font-family: Arial, sans-serif; }
        form { margin: 20px; }
        input[type="text"] { width: 200px; padding: 10px; margin: 5px 0; }
        button { padding: 10px 15px; }
    </style>
</head>
<body>
    <h2>Login Mechanic</h2>
    <form action="LoginMechanicServlet" method="post">
        <input type="text" name="name" placeholder="Enter Name" required>
        <button type="submit">Login</button>
    </form>
    <%
        if (request.getAttribute("ERROR") != null) {
            out.print("<p style='color:red;'>" + request.getAttribute("ERROR") + "</p>");
        }
    %>
</body>
</html>