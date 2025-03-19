package dao;

import model.Service;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import mylib.DBUtils;

public class ServiceDAO {

    public ArrayList<Service> getServicesByMechanic(String mechanicID) {
        ArrayList<Service> services = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            String sql = "SELECT s.serviceID, s.serviceName, s.hourlyRate "
                    + "FROM Service s "
                    + "JOIN [dbo].[ServiceMehanic] sm ON s.serviceID = sm.serviceID "
                    + "WHERE sm.mechanicID = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, mechanicID);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int serviceID = rs.getInt("serviceID");
                    String serviceName = rs.getString("serviceName");
                    double hourlyRate = rs.getDouble("hourlyRate");

                    services.add(new Service(serviceID, serviceName, hourlyRate));
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
        return services;
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

    public int getNextServiceID() {
        int nextID = 1;
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            String sql = "SELECT MAX(serviceID) FROM Service";
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs != null && rs.next()) {
                nextID = rs.getInt(1) + 1;
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
        return nextID;
    }

    public boolean addService(String serviceName, double hourlyRate) {
        boolean success = false;
        Connection cn = null;
        try {
            int newID = getNextServiceID();
            cn = DBUtils.getConnection();
            String sql = "INSERT INTO Service (serviceID, serviceName, hourlyRate) VALUES (?, ?, ?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, newID);
            pst.setString(2, serviceName);
            pst.setDouble(3, hourlyRate);

            success = pst.executeUpdate() > 0;
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
        return success;
    }

}
