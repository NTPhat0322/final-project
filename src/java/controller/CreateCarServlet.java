/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CarDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Car;

/**
 *
 * @author ASUS
 */
public class CreateCarServlet extends HttpServlet {

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
            String serialNum = request.getParameter("serialNum");
            String model = request.getParameter("model");
            String color = request.getParameter("colour");
            String yearS = request.getParameter("year");
            String priceS = request.getParameter("price");
            if(serialNum == null || model == null || color == null || yearS == null || priceS == null)
                request.getRequestDispatcher("MainServlet?action=home").forward(request, response);
            int year = 0;
            if(!yearS.isEmpty()){
                year = Integer.parseInt(yearS);
            }
            double price = 0;
            if(!priceS.isEmpty()) {
                price = Double.parseDouble(priceS);
            }
            //xử lý carID
            CarDAO cD = new CarDAO();
            ArrayList<Car> carList = cD.getAllCar();
            //lấy thằng Car có ID cao nhất
            Car highestCar = carList.get(0);
            for (int i = 1; i < carList.size(); i++) {
                if(carList.get(i).getCarID().compareToIgnoreCase(highestCar.getCarID()) > 0) {
                    highestCar = carList.get(i);
                }
            }
            //tăng số cuối lên 1
            int custID = Integer.parseInt(highestCar.getCarID()) + 1;
            String custIDs = custID + "";
            Car c = new Car(custIDs, serialNum, model, color, year, true, price);
            
            int rs = cD.createCar(c);
            if(rs == 0) {
                request.setAttribute("result", "create fail");
            }else {
                request.setAttribute("result", "create successfully");
            }
            request.getRequestDispatcher("MainServlet?action=createCar").forward(request, response);
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
