
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.SalePerson;
import mylib.DBUtils;

public class SalePersonDAO {
    //s1: connect
    //s2: viết sql và run
    //s3: xử lý dữ liệu trả về
    //s4: đóng connection và return
    public SalePerson getSalePersonByName(String name) {
        SalePerson rs = null;
        Connection cn = null;
        try{
            cn = DBUtils.getConnection(); //connect
            if(cn != null) {
                String sql = "SELECT salesID, salesName, birthday, sex, salesAddress\n"
                        + "FROM SalesPerson\n"
                        + "WHERE salesName = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, name);
                ResultSet table = pst.executeQuery();
                if(table != null) {
                    while (table.next()) {
                        String salesid = table.getString("salesID");
                        String salesname = table.getString("salesName");
                        String bd = "" + table.getString("birthday");
                        String sex = table.getString("sex");
                        String salesadd = table.getString("salesAddress");
                        rs = new SalePerson(salesid, salesname, bd, salesadd, sex);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(cn != null) cn.close();
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        return rs;
    }
}
