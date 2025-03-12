/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import java.sql.Date;
import mylib.DBUtils;

/**
 *
 * @author Admin
 */
public class SaleInvoiceDAO {
    //ham nay insert invoice vao DB
    public int createInvoice(int invoiceId, Date invoiceDate, Long saleId, Long carId, int custId){
        int rs = 0;
        Connection cn = null;
        
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "INSERT [dbo].[SalesInvoice] ([invoiceID],[invoiceDate],[salesID],[carID],[custID])\n" +
                                "VALUES (?,?,?,?,?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                
                pst.setInt(1, invoiceId);
                pst.setDate(2, invoiceDate);
                pst.setLong(3, saleId);
                pst.setLong(4, carId);
                pst.setInt(5, custId);
                rs = pst.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rs;
    }
}
