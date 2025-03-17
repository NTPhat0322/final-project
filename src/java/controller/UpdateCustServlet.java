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
public class UpdateCustServlet extends HttpServlet {

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
            String idS = request.getParameter("custID");
            String name = request.getParameter("nCustName");
            String phone = request.getParameter("nCustPhone");
            String sex = request.getParameter("nCustSex"); 
            String address = request.getParameter("nCustAddress");
            
            if(idS == null || name == null || phone == null || sex == null || address == null) {
                request.getRequestDispatcher("MainServlet?action=home").forward(request, response);
            }
            int id = Integer.parseInt(idS);
            //lấy customer có id đó ra
            CustomerDAO d = new CustomerDAO();
            Customer updatedCust = d.searchCustById(id);
            //kiểm tra xem dữ liệu nào không rỗng thì cập nhật cho cust đó
            //cust lúc này vừa có dữ liệu cũ và mới -> ghi vào DB
            if(!name.isEmpty()) {
                updatedCust.setCustName(name);
            }
            if(!phone.isEmpty()) {
                updatedCust.setCustPhone(phone);
            }
            if(!sex.isEmpty()) {
                updatedCust.setCustSex(sex);
            }
            if(!address.isEmpty()) {
                updatedCust.setCustAddress(address);
            }
            int rs = d.updateCust(updatedCust);
            if(rs > 0) {
                request.setAttribute("result", "update successfully");
                request.setAttribute("updatedCust", updatedCust);
                request.getRequestDispatcher("MainServlet?action=updateCust").forward(request, response);
            }else{
                request.setAttribute("result", "update fail");
                request.getRequestDispatcher("MainServlet?action=updateCust").forward(request, response);
            }
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
