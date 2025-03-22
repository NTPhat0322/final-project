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
            String sql = "UPDATE Service SET serviceName = ?, hourlyRate = ? WHERE serviceID = ?";
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

    public void addService(String serviceName, int serviceID, double hourlyRate) {
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            String sql = "INSERT INTO Service (serviceID, serviceName, hourlyRate) VALUES (?, ?, ?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, serviceID);
            pst.setString(2, serviceName);
            pst.setDouble(3, hourlyRate);
            pst.executeUpdate();
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
    }

    public String getServiceNameByServiceID(String svid) {
        String t = "";
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            String sql = "Select serviceName from Service where serviceID = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, svid);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                t = rs.getString("serviceName");
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

        return t;
    }

    public Service getServiceBySVID(int id) {
        Connection cn = null;
        Service s = null;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "Select * from Service where serviceID = ? ;";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, id);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    s = new Service(rs.getInt("serviceID"),
                            rs.getString("serviceName"),
                            rs.getDouble("hourlyRate"));
                    return s;
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
        return null;
    }

    public Service getServiceById(int serviceID) {
        Connection cn = null;
        Service s = null;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT * FROM Service WHERE serviceID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, serviceID);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    s = new Service(rs.getInt("serviceID"),
                            rs.getString("serviceName"),
                            rs.getDouble("hourlyRate"));
                    return s;
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
        return null;
    }

    public boolean deleteService(int serviceID) {
        Connection cn = null;
        boolean success = false;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "DELETE FROM Service WHERE serviceID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, serviceID);
                success = pst.executeUpdate() > 0;
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
        return success;
    }
    
    public boolean deleteServiceMechanic(int serviceID) {
        Connection cn = null;
        boolean success = false;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "DELETE FROM ServiceMehanic WHERE serviceID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, serviceID);
                success = pst.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return success;
    }
    
    public boolean deleteServiceWithMechanic(int serviceID) {
        boolean deletedMechanic = deleteServiceMechanic(serviceID); // Bước 1: Xóa ServiceMechanic
        boolean deletedService = deleteService(serviceID); // Bước 2: Xóa Service

        return deletedService; // Trả về kết quả cuối cùng
    }

}
