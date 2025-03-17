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
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
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
    
    public HashMap getCarSoldByYear(){
        HashMap<Integer, Integer> rs = new HashMap<>();
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT YEAR(invoiceDate) AS NAM, COUNT(carID) AS SL FROM [dbo].[SalesInvoice]\n" +
                            "GROUP BY YEAR(invoiceDate)";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet table = pst.executeQuery();
                if(table != null){
                    while(table.next()){
                        rs.put(table.getInt("NAM"), table.getInt("SL"));
                    }
                }
                
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
    
    public HashMap getRevenueByYear(){
        HashMap<Integer, Double> rs = new HashMap<>();
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT YEAR(A.invoiceDate) AS T_Year, SUM(B.price) AS Revenue FROM SalesInvoice A LEFT OUTER JOIN Cars B ON A.carID = B.carID\n" +
                                "GROUP BY YEAR(A.invoiceDate)\n" +
                                "order by T_Year";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet table = pst.executeQuery();
                if(table != null){
                    while(table.next()){
                        rs.put(table.getInt("T_Year"), table.getDouble("Revenue"));
                    }
                }
                
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
    
    public LinkedHashMap getBestSaler(){
        LinkedHashMap<String, Integer> rs = new LinkedHashMap<>();
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT TOP 5 B.model AS Model, COUNT(A.carID) AS NoUS FROM SalesInvoice A LEFT OUTER JOIN Cars B ON A.carID = B.carID\n" +
                                "GROUP BY B.model\n" +
                                "ORDER BY NoUS DESC";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet table = pst.executeQuery();
                if(table != null){
                    while(table.next()){
                        rs.put(table.getString("Model"), table.getInt("NoUS"));
                    }
                }
                
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
