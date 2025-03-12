/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
public class MainServlet extends HttpServlet {

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
            String a = request.getParameter("action");
            if(a == null) a = "home";
            String url = "";
            switch(a) {
                case "home":
                    url = "index.jsp";
                    break;
                case "loginCustForm":
                    url = "logincustpage.jsp";
                    break;
                case "loginStaffForm":
                    url = "loginstaffpage.jsp";
                    break;
                case "loginCust":
                    url = "LoginCustServlet";
                    break;
                case "loginStaff":
                    url = "LoginStaffServlet";
                    break;
                case "customerDashboard":
                    url = "customerDashboard.jsp";
                    break;
                case "salePersonDashBoard":
                    url = "salepersondashboard.jsp";
                    break;
                case "mechanicDashBoard":
                    url = "mechanicdashboard.jsp";
                    break;
                case "logout":
                    url = "LogoutStaffServlet";
                    break;
                case "logoutcust":
                    url = "LogoutCustomerServlet";
                    break;
                    
                //---------------------------
                case "createCust":
                    url = "createNewCust.jsp";
                    break;  
                case "searchCust":
                    url = "searchCust.jsp";
                    break;
                case "searchCustByName":
                    url = "SearchCustomerServlet";
                    break;
                case "createCustServ":
                    url = "CreateNewCustomerServlet";
                    break;
                case "updateCust":
                    url = "updateCustPage.jsp";
                    break;
                case "update":
                    url = "updateCustServlet";
                    break;
                case "deleteCust":
                    url = "DeleteCustServlet";
                    break;
                //----------------------------
                case "searchCar":
                    url = "searchCarPage.jsp";
                    break;
                case "seachCarServ":
                    url = "SearchCarServlet";
                    break;
                case "createCar":
                    url = "createCarPage.jsp";
                    break;
                case "createCarServ":
                    url = "CreateCarServlet";
                    break;
                case "updateCar":
                    url = "updateCarPage.jsp";
                    break;
                case "updateCarServ":
                    url = "UpdateCarServlet";
                    break;
                case "deleteCarServ":
                    url = "DeleteCarServlet";
                    break;    
                //-------------------------------    
                case "createTicketPage":
                    url = "createTicketPage.jsp";
                    break;
                case "createTicketServ":
                    url = "CreateTicketServlet";
                    break;
                case "searchServiceTicketPage":
                    url = "searchServiceTicketPage.jsp";
                    break;
                case "searchServiceTicketServ":
                    url = "SearchServiceTicketServlet";
                    break;
                //-------------------------------
                case "searchPart":
                    url = "searchPart.jsp";
                    break;
                case "SearchPartByName":
                    url = "SearchPartServlet";
                    break;
                case "createPartPage":
                    url = "CreatePart.jsp";
                    break;
                case "createPart":
                    url = "CreatePartServlet";
                    break;
                case "deletePart":
                    url = "DeletePartServlet";
                    break;
                case "updatePartPage":
                    url = "UpdatePart.jsp";
                    break;
                case "updatePart":
                    url = "UpdatePartServlet";
                    break;
                case "updatePart1":
                    url = "UpdatePartServlet1";
                    break;
            }
            request.getRequestDispatcher(url).forward(request, response);
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
