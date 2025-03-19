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
            HttpSession session = request.getSession();
            Mechanic mechanic = (Mechanic) session.getAttribute("mechanic");

            if (mechanic == null) {
                response.sendRedirect("MainServlet?action=loginStaffForm"); // Chuyển hướng nếu chưa đăng nhập
                return;
            }

            // Lấy giá trị search từ request
            String searchValue = request.getParameter("searchValue");
            String searchDate = request.getParameter("searchDate");

            // Gọi DAO để lấy danh sách ServiceTicket theo searchValue
            ServiceTicketDAO dao = new ServiceTicketDAO();
            List<ServiceTicketDetail> tickets;

            if ((searchValue == null || searchValue.trim().isEmpty())
                    && (searchDate == null || searchDate.trim().isEmpty())) {
                // Nếu không nhập gì, lấy toàn bộ danh sách
                tickets = dao.getListServiceTicketsDetailByMechanicID(mechanic.getId());
            } else {
                // Nếu có nhập, tìm kiếm theo carID/custID hoặc dateReceived
                tickets = dao.searchServiceTickets(mechanic.getId(), searchValue, searchDate);
            }

            // Đưa danh sách vào request để hiển thị trong JSP
            request.setAttribute("tickets", tickets);
            request.setAttribute("searchValue", searchValue); // Giữ lại giá trị ô input search
            request.setAttribute("searchDate", searchDate); // Giữ lại ngày đã nhập

            // Chuyển hướng về trang ViewServiceTickets.jsp (không bị mất dữ liệu)
            request.getRequestDispatcher("MainServlet?action=viewServiceticket").forward(request, response);
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
