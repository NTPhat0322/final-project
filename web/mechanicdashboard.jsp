<%@page import="model.Mechanic"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Mechanic Dashboard</title>
    <style>
        body { font-family: Arial, sans-serif; }
        ul { list-style-type: none; }
        li { margin: 10px 0; }
    </style>
</head>
<body>
    <%
        HttpSession s = request.getSession();
        Mechanic m = (Mechanic) s.getAttribute("mechanic");
        if (m == null) {
    %>
    <h2>You are not valid</h2>
    <%
        } else {
    %>
    <h2>Welcome, <%= m.getName() %>!</h2>
    <h3>Mechanic Dashboard</h3>
    <ul>
        <li><a href="ViewServiceTickets.jsp">View Service Tickets</a></li>
        <li><a href="ViewServices.jsp">View Services</a></li>
    </ul>
    <%
        }
    %>
</body>
</html>