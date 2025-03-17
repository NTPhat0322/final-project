package dao;

import model.Service;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import mylib.DBUtils;

public class ServiceDAO {

    public List<Service> getServicesByMechanic(String mechanicID) {
        List<Service> list = new ArrayList<>();
        String query = "SELECT s.serviceID, s.serviceName, s.hourlyRate \n"
                + "                       FROM Service s \n"
                + "                       JOIN [dbo].[ServiceMehanic] sm ON s.serviceID = sm.serviceID\n"
                + "                       WHERE sm.mechanicID = ?";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, mechanicID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Service(
                        rs.getInt("serviceID"),
                        rs.getString("serviceName"),
                        rs.getDouble("hourlyRate")
                ));

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateService(Service service) {
        try (Connection conn = DBUtils.getConnection()) {
            String sql = "UPDATE services SET serviceName = ?, hourlyRate = ? WHERE serviceID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, service.getServiceName());
            stmt.setDouble(2, service.getHourlyRate());
            stmt.setInt(3, service.getServiceID());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addService(String serviceName, double hourlyRate) {
        boolean isSuccess = false;
        
        // Câu lệnh SQL để thêm dịch vụ mới
        String sql = "INSERT INTO Service (serviceName, hourlyRate) VALUES (?, ?)";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, serviceName);
            ps.setDouble(2, hourlyRate);
            
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                isSuccess = true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

}
