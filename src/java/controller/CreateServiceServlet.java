/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Mechanic;
import model.Service;

/**
 *
 * @author admin
 */
@WebServlet(name = "CreateServiceServlet", urlPatterns = {"/CreateServiceServlet"})
public class CreateServiceServlet extends HttpServlet {

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
            Mechanic m = (Mechanic) session.getAttribute("mechanic");
            if (m == null) {
                response.sendRedirect("MainServlet?action=home");
                return;
            }
            String mechanicID = m.getId();
            String serviceName = request.getParameter("serviceName");
            String serviceID_raw = request.getParameter("serviceID");
            String hourlyRate_raw =request.getParameter("hourlyRate");
            int serviceID;
            double hourlyRate;
            // Lưu dịch vụ mới vào cơ sở dữ liệu
            ServiceDAO serviceDAO = new ServiceDAO();
            try {
                serviceID = Integer.parseInt(serviceID_raw);
                hourlyRate = Double.parseDouble(hourlyRate_raw);
                Service s = serviceDAO.getServiceBySVID(serviceID);

                if (s == null) {
                    Service sNew = new Service(serviceID, serviceName, hourlyRate);
                    serviceDAO.addService(serviceName, serviceID, hourlyRate);
                    response.sendRedirect("MainServlet?action=viewservice");
                } else {
                    request.setAttribute("error", serviceID + "existed");
                    request.getRequestDispatcher("MainServlet?action=createService").forward(request, response);
                }
            } catch (Exception e) {
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
