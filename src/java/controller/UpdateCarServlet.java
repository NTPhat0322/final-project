/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CarDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Car;

/**
 *
 * @author ASUS
 */
public class UpdateCarServlet extends HttpServlet {

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
            String serialNumber = request.getParameter("serialNum");
            String model = request.getParameter("model");
            String colour = request.getParameter("colour");
            String yearS = request.getParameter("year");
            String carID = request.getParameter("carID");
            String price = request.getParameter("price");
            if(serialNumber == null || model == null ||colour == null ||yearS == null || price == null) {
                request.getRequestDispatcher("MainServlet?action=home").forward(request, response);
            }
            CarDAO cD = new CarDAO();
            Car c = cD.getCarByCarID(Integer.parseInt(carID));
            if(!serialNumber.isEmpty()) {
                c.setSerialNumber(serialNumber);
            }
            if(!model.isEmpty()) {
                c.setModel(model);
            }
            if(!colour.isEmpty()) {
                c.setColour(colour);
            }
            if(!yearS.isEmpty()) {
                c.setYear(Integer.parseInt(yearS));
            }
            if(!price.isEmpty()) {
                c.setPrice(Double.parseDouble(price));
            }
            
            int rs = cD.updateCar(c);
            if(rs == 0){
                request.setAttribute("result", "update fail");
            }else{
                request.setAttribute("result", "update successfully");
                request.setAttribute("newCar", c);
            }
            request.getRequestDispatcher("MainServlet?action=updateCar").forward(request, response);
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
