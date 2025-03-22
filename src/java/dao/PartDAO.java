 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import model.Part;
import mylib.DBUtils;

/**
 *
 * @author ASUS
 */
public class PartDAO {
    public ArrayList<Part> getAllPart(){
        ArrayList<Part> rs = new ArrayList<>();
        Connection cn=null;
        try {
            cn = DBUtils.getConnection();
            if(cn != null){
                String sql = "SELECT * from Parts";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet table = pst.executeQuery();
                
                if(table != null){
                    while(table.next()){
                        int partid = table.getInt("partID");
                        String partname = table.getString("partName");
                        Double pprice = table.getDouble("purchasePrice");
                        Double rprice = table.getDouble("retailPrice");
                        rs.add(new Part(partid, partname, pprice, rprice));
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
    
    public ArrayList<Part> getPartByName(String pname){
        ArrayList<Part> rs = new ArrayList<>();
        Connection cn=null;
        try {
            cn = DBUtils.getConnection();
            if(cn != null){
                String sql = "SELECT [partID], [partName], [purchasePrice], [retailPrice] FROM [dbo].[Parts]\n" +
                            "WHERE partName LIKE ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + pname + "%");
                ResultSet table = pst.executeQuery();
                if(table != null){
                    while(table.next()){
                        int partid = table.getInt("partID");
                        String partname = table.getString("partName");
                        Double pprice = table.getDouble("purchasePrice");
                        Double rprice = table.getDouble("retailPrice");
                        rs.add(new Part(partid, partname, pprice, rprice));
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
    //ham nay de insert part vao DB
    public int insertPart(int partID, String partName, Double purchasePrice, Double retailPrice){
        int rs = 0;
        Connection cn=null;
        try {
            cn = DBUtils.getConnection();
            if(cn != null){
                String sql = "INSERT Parts (partID, partName, purchasePrice, retailPrice)\n" +
                                "VALUES (?, ?, ?, ?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, partID);
                pst.setString(2, partName);
                pst.setDouble(3, purchasePrice);
                pst.setDouble(4, retailPrice);
                rs = pst.executeUpdate();
                
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
    //lay part dua vao id
    public Part getPartById(int partid){
        Part result = null;
        Connection cn=null;
        try {
            cn = DBUtils.getConnection();
            if(cn != null){
                String sql = "SELECT [partID],[partName],[purchasePrice],[retailPrice] FROM [dbo].[Parts]\n" +
                                "WHERE [partID] = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, partid);
                ResultSet table = pst.executeQuery();
                if(table != null){
                    while(table.next()){
                        int partID = table.getInt("partID");
                        String partname = table.getString("partName");
                        Double pprice = table.getDouble("purchasePrice");
                        Double rprice = table.getDouble("retailPrice");
                        result = new Part(partID, partname, pprice, rprice);
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
        return result;
    }
    //ham nay xoa part
    public void DeletePart(int partid){
        
        Connection cn=null;
        try {
            cn = DBUtils.getConnection();
            if(cn != null){
                String sql = "DELETE FROM Parts\n" +
                                "WHERE partID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, partid);
                pst.executeUpdate();
                
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
    }
    
    public void UpdatePart(int partID, String partName, Double purchasePrice, Double retailPrice){
        Connection cn=null;
        try {
            cn = DBUtils.getConnection();
            if(cn != null){
                String sql = "UPDATE Parts\n" +
                            "set partName = ?, purchasePrice = ?, retailPrice = ?\n" +
                            "Where partID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, partName);
                pst.setDouble(2, purchasePrice);
                pst.setDouble(3, retailPrice);
                pst.setInt(4, partID);
                pst.executeUpdate();
                
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
    }
    
    public LinkedHashMap getBestUsedParts(){
        LinkedHashMap<String, Integer> rs = new LinkedHashMap<>();
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT TOP 5 B.partName AS pName, COUNT(A.partID) AS soluong FROM PartsUsed A LEFT OUTER JOIN Parts B ON A.partID = B.partID\n" +
                                "GROUP BY B.partName\n" +
                                "ORDER BY soluong DESC";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet table = pst.executeQuery();
                if(table != null){
                    while(table.next()){
                        rs.put(table.getString("pName"), table.getInt("soluong"));
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
