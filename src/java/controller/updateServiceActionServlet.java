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
import model.Mechanic;
import model.Service;

/**
 *
 * @author admin
 */
@WebServlet(name = "updateServiceActionServlet", urlPatterns = {"/updateServiceActionServlet"})
public class updateServiceActionServlet extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            Mechanic m = (Mechanic) request.getSession().getAttribute("mechanic");
            if (m == null) {
                response.sendRedirect("MainServlet?action=home");
                return;
            }
            String serviceIDStr = request.getParameter("serviceID");
            String serviceName = request.getParameter("serviceName");
            String hourlyRateStr = request.getParameter("hourlyRate");
            int serviceID;
            double hourlyRate;
            // Lưu dịch vụ mới vào cơ sở dữ liệu
            ServiceDAO serviceDAO = new ServiceDAO();

            try {
                serviceID = Integer.parseInt(serviceIDStr);
                hourlyRate = Double.parseDouble(hourlyRateStr);
                if (hourlyRate <= 0) {
                    request.setAttribute("error", "Giá dịch vụ phải lớn hơn 0.");
                    request.getRequestDispatcher("MainServlet?action=viewservice").forward(request, response);
                    return;
                }

                // Lấy thông tin dịch vụ hiện tại
                Service existingService = serviceDAO.getServiceById(serviceID);
                if (existingService == null) {
                    request.setAttribute("error", "Dịch vụ không tồn tại.");
                    request.getRequestDispatcher("MainServlet?action=viewservice").forward(request, response);
                    return;
                }

                // Kiểm tra xem dữ liệu có thay đổi không
                if (existingService.getServiceName().equals(serviceName) && existingService.getHourlyRate() == hourlyRate) {
                    request.setAttribute("message", "Không có thay đổi nào được thực hiện.");
                    request.getRequestDispatcher("MainServlet?action=viewservice").forward(request, response);
                    return;
                }
                Service updatedService = new Service(serviceID, serviceName, hourlyRate);
                boolean success = serviceDAO.updateService(updatedService);

                if (success) {
                    request.setAttribute("message", "Cập nhật dịch vụ thành công!");
                } else {
                    request.setAttribute("error", "Cập nhật dịch vụ thất bại.");
                }
                request.getRequestDispatcher("MainServlet?action=viewservice").forward(request, response);

            } catch (NumberFormatException e) {
                request.setAttribute("error", "Dữ liệu nhập không hợp lệ.");
                request.getRequestDispatcher("MainServlet?action=viewservice").forward(request, response);
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
