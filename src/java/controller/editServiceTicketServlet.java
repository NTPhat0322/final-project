/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ServiceTicketDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ServiceTicket;
import model.ServiceTicketDetail;

/**
 *
 * @author admin
 */
@WebServlet(name = "editServiceTicketServlet", urlPatterns = {"/editServiceTicketServlet"})
public class editServiceTicketServlet extends HttpServlet {

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
            HttpSession sesion = request.getSession();
            ServiceTicketDetail std = (ServiceTicketDetail) sesion.getAttribute("ticket");
            if (std == null) {
                response.sendRedirect("MainServlet?action=home");
                return;
            }

            String ticketID = request.getParameter("ticketID");
            if (ticketID == null || ticketID.isEmpty()) {
                response.sendRedirect("MainServlet?action=edit&error=Missing ticket ID");
                return;
            }

            ServiceTicketDAO dao = new ServiceTicketDAO();
            ServiceTicket ticket = dao.getServiceTicketById(ticketID);
            if (ticket == null) {
                response.sendRedirect("MainServlet?action=edit&ticketID=" + ticketID + "&error=Ticket not found");
                return;
            }

            // Lấy dữ liệu hiện tại
            int hours = std.getHours();
            double rating = std.getRate();
            String comments = std.getComment();
            String serviceID = std.getServiceID(); // Lấy serviceID từ session object

            // Kiểm tra nếu request có giá trị mới
            try {
                String hoursStr = request.getParameter("hours");
                String ratingStr = request.getParameter("rating");

                if (hoursStr != null && !hoursStr.isEmpty()) {
                    hours = Integer.parseInt(hoursStr);
                }
                if (ratingStr != null && !ratingStr.isEmpty()) {
                    rating = Double.parseDouble(ratingStr);
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("MainServlet?action=edit&ticketID=" + ticketID + "&error=Invalid input for hours or rating");
                return;
            }

            if (request.getParameter("comments") != null) {
                String inputComment = request.getParameter("comments").trim();
                if (inputComment.isEmpty()) {
                    comments = null;
                } else {
                    comments = inputComment;
                }
            }
            // Gọi DAO để cập nhật ticket
            boolean updated = dao.updateServiceTicket(ticketID, serviceID, hours, rating, comments);

            if (updated) {
                response.sendRedirect("MainServlet?action=viewServiceticket&success=true");
            } else {
                response.sendRedirect("MainServlet?action=edit&ticketID=" + ticketID + "&error=Could not update the service ticket.");
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
