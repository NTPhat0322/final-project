/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;

/**
 *
 * @author ASUS
 */
public class CreateNewCustomerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");
            String custIDS = request.getParameter("custID");
            String custName = request.getParameter("custName");
            //nếu người dùng k nhập phone thì phone sẽ nhận dc chuỗi rỗng ""
            String custPhone = request.getParameter("custPhone");
            //nếu người dùng k nhập sex thì sex sẽ nhận dc chuỗi rỗng ""
            String custSex = request.getParameter("custSex");
            //nếu người dùng k nhập address thì address sẽ nhận dc chuỗi rỗng ""
            String custAddress = request.getParameter("custAddress");
            if(custIDS == null || custName == null || custPhone == null ||
                custSex == null || custAddress == null) { 
                request.getRequestDispatcher("MainServlet?action=home").forward(request, response);
            }
            
            //không có dữ liệu nào null
            
            int custID = Integer.parseInt(custIDS);
            CustomerDAO d = new CustomerDAO();
            Customer c = new Customer(custID, custName, custPhone, custSex, custAddress);
            int rs = d.createCustomer(c);
            if(rs == 0) {
                request.setAttribute("result", "create fail");
            }else {
                request.setAttribute("result", "create successfully");
            }
            request.getRequestDispatcher("MainServlet?action=createCust").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
