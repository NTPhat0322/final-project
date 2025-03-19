<%-- 
    Document   : CreateInvoice
    Created on : Mar 12, 2025, 3:18:55 PM
    Author     : Admin
--%>

<%@page import="model.Customer"%>
<%@page import="dao.CustomerDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.CarDAO"%>
<%@page import="model.Car"%>
<%@page import="model.SalePerson"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Date date = new Date();
            SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
            String datecurr = d.format(date);
        %>
        
        <%
            HttpSession s = request.getSession();
            SalePerson sp = (SalePerson) s.getAttribute("sale");
            if(sp != null){
        %>
        
        <%
            CarDAO cd = new CarDAO();
            ArrayList<Car> carlist = cd.getAllCar();
        %>
        
        <%
            CustomerDAO cust = new CustomerDAO();
            ArrayList<Customer> tmp = cust.searchCustomerByName("");
            ArrayList<Customer> custlist = new ArrayList<>();
            for (Customer cust1 : tmp) {
                    if(cust1.isStatus()){
                        custlist.add(cust1);
                    }
                }
        %>
        <h1>Trang tao invoice</h1>
        <div>
            <form action="MainServlet" accept-charset="UTF-8">
                
                <input type="hidden" name="invoiceDate" value="<%= datecurr %>"/>
                <input type="hidden" name="saleId" value="<%= sp.getId() %>"/>
                <div>
                    <label>Invoice ID: </label>
                    <input type="text" name="invoiceId" required="" pattern="[0-9]+"/>
                </div>

                <div>
                    <label>Car: </label>
                    <select name="carId" required="">
                        <option value="">--None--</option>
                        <%
                            for (Car c : carlist) {
                               if(c.isStatus()) {
                        %>
                        <option value="<%= c.getCarID() %>">
                            Serial: <%= c.getSerialNumber() %> - 
                            Model: <%= c.getModel() %> - 
                            Color: <%= c.getColour() %> - 
                            Year: <%= c.getYear() %>
                        </option>
                        <%
                               }
                            }
                        %>
                    </select>
                </div>
                    
                <div>
                    <label>Customer: </label>
                    <select name="custId" required="">
                        <option value="">--None--</option>
                        <%
                            for (Customer cust2 : custlist) {
                        %>
                        <option value="<%= cust2.getCustID() %>">
                            Customer name: <%= cust2.getCustName() %> - 
                            Phone number: <%= cust2.getCustPhone() %> - 
                            Sex: <%= cust2.getCustSex() %> - 
                            Address: <%= cust2.getCustAddress() %>
                        </option>
                        <%
                                }
                        %>
                    </select>
                </div>
                <div>
                    <input type="submit" value="Create"/>
                </div>
                <input type="hidden" name="action" value="createInvoice"/>
            </form>
        </div>
                    <br/>
        <div>
            <form action="MainServlet">
                <input type="hidden" name="action" value="salePersonDashBoard"/>
                <input type="submit" value="BACK"/>
            </form>
        </div>
        <%
            if(request.getAttribute("messageInvoice") != null){
        %>
            <p><%= request.getAttribute("messageInvoice") %></p>
        <%
            }
        %>
                    
        <%
            }else{
                request.getRequestDispatcher("MainServlet?action=home").forward(request, response);
            }
        %>
        
    </body>
</html>
