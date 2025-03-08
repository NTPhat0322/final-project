
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import model.ServiceTicket;
import mylib.DBUtils;

public class ServiceTicketDAO {
    public int createServiceTicket(ServiceTicket s) {
        int rs = 0;
        Connection cn = null;
        
        try{
            cn = DBUtils.getConnection();
            if(cn != null) {
               String sql = "insert ServiceTicket \n"
                        + "values(?, ?, ?, ?, ?)";
               PreparedStatement pst = cn.prepareStatement(sql);
               //ticket id trong java là string, nhma db là decimal nên chuyển
               pst.setInt(1, Integer.parseInt(s.getSeviceTicketID()));
               pst.setString(2, s.getDateReceived().toString());
               pst.setString(3, s.getDateReturned().toString());
               //cust id là int nên k cần chuyển
               pst.setInt(4, s.getCustID());
               //car id trong java là string, nhma db là decimal nên chuyển
               pst.setDouble(5, Double.parseDouble(s.getCarID()));
               rs = pst.executeUpdate();
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(cn != null) {
                    cn.close();
                }
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        
        return rs;
    }

    public ArrayList<ServiceTicket> searchTicketByCustID(int custID) {
        ArrayList<ServiceTicket> rs = new ArrayList<>();
        Connection cn = null;
        try{
            cn = DBUtils.getConnection(); //connect
            String sql = "select serviceTicketID, dateReceived, dateReturned, custID, carID\n"
                    + "from ServiceTicket\n"
                    + "where custID = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, custID);
            ResultSet table = pst.executeQuery();
            if(table != null) {
                while(table.next()) {
                    String seviceTicketID = table.getString("serviceTicketID");
                    LocalDate dateReceived = LocalDate.parse(table.getString("dateReceived"));
                    LocalDate dateReturned = LocalDate.parse(table.getString("dateReturned"));
                    String carID = table.getString("carID");
                    
                    rs.add(new ServiceTicket(seviceTicketID, dateReceived, dateReturned, carID, custID));
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
