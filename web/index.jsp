<%-- 
    Document   : index
    Created on : Feb 18, 2025, 11:13:34 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            h1 {
                text-align: center;
            }
            .login-box {
                width: 300px;
                padding: 20px;
                margin: 50px auto;
                background-color: #ffffff;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                border-radius: 10px;
                text-align: center;
            }
            .login-box a {
                display: block;
                margin: 10px 0;
                padding: 10px;
                color: #ffffff;
                background-color: #007bff;
                text-decoration: none;
                border-radius: 5px;
                transition: background-color 0.3s;
            }
            .login-box a:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <h1>Welcome to ABC garage</h1>
        <div class="login-box">
            <div><a href="MainServlet?action=loginCustForm">Login Customer</a></div>
            <div><a href="MainServlet?action=loginStaffForm">Login Staff</a></div>
        </div>
    </body>
</html>
