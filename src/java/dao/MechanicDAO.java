
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import model.Mechanic;
import mylib.DBUtils;

/**
 *
 * @author ASUS
 */
public class MechanicDAO {
    //connect
    //sql and run
    //xu ly data
    //dong connection va return
    public Mechanic getMechanicByName(String name) {
        Mechanic rs = null;
        Connection cn = null;
        try{
            cn = DBUtils.getConnection();
            if(cn != null) {
                String sql = "SELECT mechanicID, mechanicName\n"
                        + "FROM Mechanic\n"
                        + "WHERE mechanicName = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, name);
                ResultSet table = pst.executeQuery();
                if(table != null) {
                    while (table.next()) {
                        String mechenicid = table.getString("mechanicID");
                        String mechanicname = table.getString("mechanicName");
                        rs = new Mechanic(mechenicid, mechanicname);
                    }
                }
            }
            
        }catch(Exception e) {
            e.printStackTrace();
        }finally{
            try{
                if(cn!= null) cn.close();
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        return rs;
    }
    
    public LinkedHashMap getMechanicGood(){
        LinkedHashMap<String, Integer> rs = new LinkedHashMap<>();
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT TOP 3 B.mechanicName AS Mechanicname, COUNT(A.serviceTicketID) AS sl FROM ServiceMehanic A LEFT OUTER JOIN Mechanic B ON A.mechanicID = B.mechanicID\n" +
                            "GROUP BY B.mechanicID, B.mechanicName\n" +
                            "ORDER BY sl DESC";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet table = pst.executeQuery();
                if(table != null){
                    while(table.next()){
                        rs.put(table.getString("Mechanicname"), table.getInt("sl"));
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
