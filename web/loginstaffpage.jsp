<%-- 
    Document   : loginstaffpage
    Created on : Feb 18, 2025, 11:12:57 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .login-form {
                margin: 100px auto;
                width: 350px;
                padding: 20px;
                background-color: #ffffff;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                border-radius: 10px;
                box-sizing: border-box;
            }
            .login-form label {
                display: block;
                margin-bottom: 15px;
                font-weight: bold;
                color: #333;
            }
            
            .login-form input[type="text"],
            .login-form input[type="submit"] {
                width: 100%;
                padding: 10px;
                margin-bottom: 15px;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 14px;
                box-sizing: border-box;
            }
            
            .login-form input[type="text"]:focus {
                border-color: #007bff;
                outline: none;
            }
            .login-form input[type="submit"] {
                background-color: #007bff;
                color: #ffffff;
                border: none;
                cursor: pointer;
                transition: background-color 0.3s;
            }
            .login-form input[type="submit"]:hover {
                background-color: #0056b3;
            }
            .login-form .error-message {
                color: #ff0000;
                font-size: 14px;
                text-align: center;
            }
        </style>
    </head>
    <body>     
        <form action="MainServlet" accept-charset="UTF-8" class="login-form">
            <label for="staff_name">Staff name</label>
            <div><input type="text" id="staff_name" name="txtname" placeholder="Enter staff's name" required></div>
            <div><input type="submit" value="Login"></div>
            <input type="hidden" name="action" value="loginStaff"> 

        </form>
        <div>
            <%
                if (request.getAttribute("result") != null) {
                    out.print(request.getAttribute("result"));
                }
            %>
            <a href="MainServlet?action=home">Back</a>
        </div>

            <div class="error-message">
                <% if (request.getAttribute("result") != null) {
                    out.print(request.getAttribute("result"));
                } %>
            </div>
        </form>
    </body>
</html>
