
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
}
