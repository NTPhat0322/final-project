/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.MechanicDAO;
import dao.SalePersonDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Mechanic;
import model.SalePerson;

/**
 *
 * @author ASUS
 */
public class LoginStaffServlet extends HttpServlet {

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
            request.setCharacterEncoding("UTF-8");
            String name = request.getParameter("txtname");
            if(name != null) {
                //kiểm tra xem staff này có phải là mechanic hay sale person hay không
                SalePersonDAO spd = new SalePersonDAO();
                SalePerson sp = spd.getSalePersonByName(name);
                //sp có thể là null hoặc là object
                if(sp == null) {
                    MechanicDAO md = new MechanicDAO();
                    Mechanic m = md.getMechanicByName(name);
                    if(m == null) {
                        //login fail
                        request.setAttribute("result", "sale person is not valid");
                        request.getRequestDispatcher("MainServlet?action=loginStaffForm").forward(request, response);
                    }else {
                        //login thành công thì lưu thông tin vào session
                        HttpSession s = request.getSession(true);
                        s.setAttribute("mechanic", m);
                        //chuyển đến dashboard của mechanic
                        request.getRequestDispatcher("MainServlet?action=mechanicDashBoard").forward(request, response);
                    }
                } else {
                    //login thành công thì lưu thông tin vào session
                    HttpSession s = request.getSession(true);
                    s.setAttribute("sale", sp);
                    //chuyển đến dashboard của sale person
                    request.getRequestDispatcher("MainServlet?action=salePersonDashBoard").forward(request, response);
                }
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
