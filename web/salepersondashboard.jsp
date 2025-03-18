<%-- 
    Document   : salepersondashboard
    Created on : Feb 18, 2025, 11:31:47 PM
    Author     : ASUS
--%>

<%@page import="model.SalePerson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .head-page {
                color: #0056b3;
                display: flex;
                justify-content: space-between;
                align-items: center;
            }

            .container {
                max-width: 600px; /* Giới hạn chiều rộng */
                margin: auto; /* Căn giữa */
                background: #ffffff;
                padding: 20px;
                border: 1px solid #ddd;
                border-radius: 8px; /* Bo tròn góc container */
                box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1); /* Hiệu ứng đổ bóng */
            }
            
            h3 {
                font-size: 1.5em;
                color: #0056b3; /* Màu xanh làm nổi bật tiêu đề */
                margin-bottom: 10px;
            }

            div {
                margin-bottom: 15px;
            }

            a {
                display: inline-block;
                text-decoration: none; /* Xóa gạch chân */
                color: #0056b3; /* Màu xanh */
                background-color: #e9f5ff; /* Màu nền nhẹ */
                padding: 10px 20px; /* Khoảng cách padding */
                border-radius: 5px; /* Bo tròn góc */
                transition: all 0.3s ease; /* Hiệu ứng hover */
            }

            a:hover {
                background-color: #0056b3; /* Đổi màu nền khi hover */
                color: #fff; /* Màu chữ đổi thành trắng khi hover */
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2); /* Hiệu ứng đổ bóng */
            }

            a:active {
                transform: scale(0.98); /* Hiệu ứng nhấn */
            }



            .container div {
                margin-bottom: 10px;
            }
        </style>
        
    </head>
    <body>
        <!--salepersondashboard-->
        <%
            HttpSession s = request.getSession();
            SalePerson sp = (SalePerson)s.getAttribute("sale");
            if(sp != null) {
        %>
                <div class="head-page">
                    <h1>Welcome to sale person dashboard</h1>
                    <form>
                        <div><input type="submit" name="action" value="logout"></div>
                    </form>
                </div>

                <div class="container">
                    <h3>Function for customer</h3>
                    <div><a href='MainServlet?action=searchCust'>Search customer</a></div>
                    <div><a href='MainServlet?action=createCust'>Create new customer</a></div>

                    <h3>Function for cars</h3>
                    <div><a href='MainServlet?action=searchCar'>Search car</a></div>
                    <div><a href='MainServlet?action=createCar'>Create new car</a></div>

                    <h3>Service tickets</h3>
                    <div><a href='MainServlet?action=searchServiceTicketPage'>Search service ticket</a></div>
                    <div><a href='MainServlet?action=createTicketPage'>Create service ticket</a></div>

                    <h3>Function for parts</h3>
                    <div><a href="MainServlet?action=searchPart">Search part</a></div>
                    <div><a href="MainServlet?action=createPartPage">Create part</a></div>

                    <h3>Create invoice for customer</h3>
                    <div><a href="MainServlet?action=createInvoicePage">Create Invoice</a></div>
                    <div><a href="MainServlet?action=reportPage">Report</a></div>
                </div>  
        <%
            }else {
                request.getRequestDispatcher("MainServlet?action=home").forward(request, response);
            }
        %>
    </body>
</html>
