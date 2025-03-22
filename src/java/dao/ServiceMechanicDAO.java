package dao;

import model.ServiceMechanic;
import model.Mechanic;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import mylib.DBUtils;

public class ServiceMechanicDAO {
    
    public List<ServiceMechanic> getServiceMechanicsByTicketID(String serviceTicketID) {
        Connection cn = null;
        
        List<ServiceMechanic> mechanics = new ArrayList<>();
        String query = "SELECT * FROM ServiceMehanic WHERE serviceTicketID = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, serviceTicketID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                mechanics.add(new ServiceMechanic(
                    rs.getString("mechanicID"),
                    rs.getString("serviceTicketID"),
                    rs.getString("serviceID"),
                    rs.getDouble("hours"),
                    rs.getString("comment"),
                    rs.getDouble("rate")
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return mechanics;
    }

    public Mechanic getMechanicById(String mechanicID) {
        String query = "SELECT * FROM Mechanic WHERE mechanicID = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, mechanicID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Mechanic(
                    rs.getString("mechanicID"),
                    rs.getString("name")
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
