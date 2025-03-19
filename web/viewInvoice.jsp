<%-- 
    Document   : viewInvoice
    Created on : Mar 16, 2025, 4:54:44 AM
    Author     : Admin
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.InvoiceDetail"%>
<%@page import="model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            table {
    width: 100%;
    border-collapse: collapse;
    text-align: center; /* Căn giữa nội dung trong bảng */
}

th, td {
    padding: 10px;
    text-align: center; /* Căn giữa theo chiều ngang */
    vertical-align: middle; /* Căn giữa theo chiều dọc */
}

th {
    background-color: #f2f2f2;
}

td ul {
    list-style: none; 
    padding: 0;
    margin: 0;
}

td ul li {
    white-space: nowrap;
    text-align: center; /* Căn giữa nội dung trong danh sách */
}

/* Điều chỉnh chiều rộng các cột */
td:nth-child(1), td:nth-child(2), td:nth-child(3) {
    width: 15%;
}

td:nth-child(4) {
    width: 30%;
}

td:nth-child(5) {
    width: 15%;
}

        </style>
    </head>
    <body>
        <h1>Your detail invoice</h1>
        <%
            ArrayList<InvoiceDetail> rs = (ArrayList<InvoiceDetail>) request.getAttribute("invoiceDetail");
            HttpSession s = request.getSession();
            Customer cust =(Customer) s.getAttribute("customer");
            if(cust != null){
                if(rs != null && !rs.isEmpty()){
                    for (InvoiceDetail iv : rs) {
        %>
        <div>
            <table border="1">
                <tr>
                    <th>Mã hóa đơn</th>
                    <th>Ngày hóa đơn</th>
                    <th>Người bán</th>
                    <th>Thông số xe</th>
                    <th>Đơn giá</th>
                </tr>
                <tr>
                    <td><%= iv.getInvoiceId() %></td>
                    <td><%= iv.getInvoiceDate() %></td>
                    <td><%= iv.getSaleName() %></td>
                    <td>
                        <ul>
                            <li>Số seri: <%= iv.getCarSerial() %></li>
                            <li>Hãng: <%= iv.getCarModel() %></li>
                            <li>Màu xe: <%= iv.getCarColor() %></li>
                            <li>Đời: <%= iv.getCarYear() %></li>
                        </ul>
                    </td>
                    <td><%= String.format("%.0f", iv.getCarPrice()) %></td>
                </tr>
            </table>
        </div>
        
        <%
                        }
                }else {
        %>
        <h4>Customer has no invoices</h4>
        <%
                }
        %>
        <br/>
        <div>
            <form action="MainServlet">
                <input type="hidden" name="action" value="homecust"/>
                <input type="submit" value="BACK"/>
            </form>
        </div>
        
        <%
            }else{
                request.getRequestDispatcher("MainServlet?action=home").forward(request, response);
            }
        %>
    </body>
</html>
