/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.InvoiceDetail;
import mylib.DBUtils;

/**
 *
 * @author Admin
 */
public class InvoiceDetailDAO {
    public ArrayList<InvoiceDetail> getCustDetail(int custId){
        ArrayList<InvoiceDetail> rs = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if(cn != null){
                String sql = "SELECT A.invoiceID, A.invoiceDate, C.salesName, B.serialNumber, B.model, B.colour, B.year, B.price FROM SalesInvoice A\n" +
                                "LEFT OUTER JOIN Cars B ON A.carID = B.carID\n" +
                                "LEFT OUTER JOIN SalesPerson C ON A.salesID = C.salesID\n" +
                                "WHERE A.custID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, custId);
                ResultSet table = pst.executeQuery();
                if(table != null){
                    while(table.next()){
                        int invoiceId = table.getInt("invoiceID");
                        Date invoiceDate = table.getDate("invoiceDate");
                        String saleName = table.getString("salesName");
                        String carSerial = table.getString("serialNumber");
                        String carModel = table.getString("model");
                        String carColor = table.getString("colour");
                        int carYear = table.getInt("year");
                        Double carPrice = table.getDouble("price");
                        rs.add(new InvoiceDetail(invoiceId, invoiceDate, saleName, carSerial, carModel, carColor, carYear, carPrice));
                    }
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(cn!=null) cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return rs;
    }
}
