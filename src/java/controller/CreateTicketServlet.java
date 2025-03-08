/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CarDAO;
import dao.ServiceTicketDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Car;
import model.ServiceTicket;

/**
 *
 * @author ASUS
 */
public class CreateTicketServlet extends HttpServlet {

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
            String dateReceived = request.getParameter("dateReceived");
            String dateReturned = request.getParameter("dateReturned");
            String custIDs = request.getParameter("custID");
            String carID = request.getParameter("carID");
            
            if(dateReceived == null || dateReturned == null || custIDs == null || carID == null)
                request.getRequestDispatcher("MainServlet?action=createTicketPage").forward(request, response);
            
            //tạo id cho service ticket
            String seviceTicketID = custIDs + LocalTime.now().getSecond();
            
            //chuyển date từ String sang Localdate
            //do date string ta lấy từ form về đang thuộc dạng ISO-8601(yyyy-mm-dd)
            //nên chúng ta có thể tạo 1 LocalDate trực tiếp từ chuỗi đó mà không 
            //cần định dạng lại
            LocalDate localDateReceived = LocalDate.parse(dateReceived);
            LocalDate localDateReturned = LocalDate.parse(dateReturned);
            //customer id là int
            int custID = Integer.parseInt(custIDs);
            
            //gọi DAO để insert vào DB
            ServiceTicket st = new ServiceTicket(seviceTicketID, localDateReceived, localDateReturned, carID, custID);
            ServiceTicketDAO d = new ServiceTicketDAO();
            //xử lý đầu ra và gửi cho jsp
            int rs = d.createServiceTicket(st);
            if(rs > 0) {
                request.setAttribute("result", "Create successfully");
                request.getRequestDispatcher("MainServlet?action=createTicketPage").forward(request, response);
            }else {
                request.setAttribute("result", "Create fail");
                request.getRequestDispatcher("MainServlet?action=createTicketPage").forward(request, response);
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
