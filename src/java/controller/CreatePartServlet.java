/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PartDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Part;

/**
 *
 * @author ASUS
 */
public class CreatePartServlet extends HttpServlet {

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
            String pid = request.getParameter("partid");
            int newpid = Integer.parseInt(pid);
            
            String pname = request.getParameter("partname");
            
            String pprice = request.getParameter("pprice");
            Double newpprice = 0.0;
            if(pprice != null && !pprice.isEmpty()){
                newpprice = Double.parseDouble(pprice);
            }
            
            String rprice = request.getParameter("rprice");
            Double newrprice = 0.0;
            if(rprice != null && !rprice.isEmpty()){
                newrprice = Double.parseDouble(rprice);
            }
            
            PartDAO pd = new PartDAO();
            Part part = pd.getPartById(newpid);
            
            if(part == null){
                int checkadd = pd.insertPart(newpid, pname, newpprice, newrprice);
                if(checkadd > 0){
                    request.setAttribute("message", "Add Success");
                }else{
                    request.setAttribute("message", "Add faile");
                }
            }else{
                request.setAttribute("message", "Part is duplicate");
            }
            request.getRequestDispatcher("MainServlet?action=createPartPage").forward(request, response);
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
