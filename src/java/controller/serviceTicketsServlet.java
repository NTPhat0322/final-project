/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ServiceTicketDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Mechanic;
import model.ServiceTicketDetail;

/**
 *
 * @author admin
 */
public class serviceTicketsServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            String searchValue = request.getParameter("searchValue");
            String searchDate = request.getParameter("searchDate");
            HttpSession session = request.getSession();
            Mechanic mechanic = (Mechanic) session.getAttribute("mechanic");

            // Gọi DAO để lấy danh sách ServiceTicket theo searchValue
            ServiceTicketDAO dao = new ServiceTicketDAO();
            List<ServiceTicketDetail> tickets;

            if (searchValue == null || searchValue.trim().isEmpty()) {
                // Nếu không nhập gì, lấy toàn bộ danh sách
                tickets = dao.getListServiceTicketsDetailByMechanicID(mechanic.getId());
            } else {
                // Nếu có nhập, tìm kiếm theo carID hoặc custID
                tickets = dao.searchServiceTickets(searchValue, searchValue, searchDate);
            }

            // Đưa danh sách vào request để hiển thị trong JSP
            request.setAttribute("tickets", tickets);
            request.setAttribute("searchValue", searchValue);

            // Chuyển hướng về trang serviceTickets.jsp
            request.getRequestDispatcher("ViewServiceTickets.jsp").forward(request, response);
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
