package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.ServiceTicket;
import model.ServiceTicketDetail;
import mylib.DBUtils;

public class ServiceTicketDAO {

    public int createServiceTicket(ServiceTicket s) {
        int rs = 0;
        Connection cn = null;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
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

    public ArrayList<ServiceTicket> searchTicketByCustID(int custID) {
        ArrayList<ServiceTicket> rs = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.getConnection(); //connect
            String sql = "select serviceTicketID, dateReceived, dateReturned, custID, carID\n"
                    + "from ServiceTicket\n"
                    + "where custID = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, custID);
            ResultSet table = pst.executeQuery();
            if (table != null) {
                while (table.next()) {
                    String seviceTicketID = table.getString("serviceTicketID");
                    LocalDate dateReceived = LocalDate.parse(table.getString("dateReceived"));
                    LocalDate dateReturned = LocalDate.parse(table.getString("dateReturned"));
                    String carID = table.getString("carID");

                    rs.add(new ServiceTicket(seviceTicketID, dateReceived, dateReturned, carID, custID));
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

    public List<ServiceTicket> getAllServiceTickets() {
        List<ServiceTicket> list = new ArrayList<>();
        Connection cn = null;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT serviceTicketID, dateReceived, dateReturned, carID, custID FROM ServiceTicket";
                PreparedStatement stmt = cn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    list.add(new ServiceTicket(
                            rs.getString("serviceTicketID"),
                            rs.getDate("dateReceived") != null ? rs.getDate("dateReceived").toLocalDate() : null,
                            rs.getDate("dateReturned") != null ? rs.getDate("dateReturned").toLocalDate() : null,
                            rs.getString("carID"),
                            rs.getInt("custID")
                    ));
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
        return list;
    }

    public List<ServiceTicket> getServiceTicketsByMechanicID(String id) {
        List<ServiceTicket> list = new ArrayList<>();
        Connection cn = null;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT st.serviceTicketID, dateReceived, dateReturned, custID, carID "
                        + "FROM [dbo].[ServiceTicket] st "
                        + "JOIN [dbo].[ServiceMehanic] sm ON st.serviceTicketID = sm.serviceTicketID "
                        + "WHERE sm.mechanicID = ?;";

                PreparedStatement stmt = cn.prepareStatement(sql);
                stmt.setString(1, id);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    list.add(new ServiceTicket(
                            rs.getString("serviceTicketID"),
                            rs.getDate("dateReceived") != null ? rs.getDate("dateReceived").toLocalDate() : null,
                            rs.getDate("dateReturned") != null ? rs.getDate("dateReturned").toLocalDate() : null,
                            rs.getString("carID"),
                            rs.getInt("custID")
                    ));
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
        return list;
    }

    //serviceTiketDetail
    public ServiceTicketDetail getServiceTicketsDetailByServiceTicketID(String id) {
        Connection cn = null;
        ServiceTicketDetail serviceTicketDetail = null;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT \n"
                        + "    st.serviceTicketID, \n"
                        + "    st.dateReceived, \n"
                        + "    st.dateReturned, \n"
                        + "    st.carID, \n"
                        + "    st.custID, \n"
                        + "    sm.hours, \n"
                        + "    sm.comment, \n"
                        + "    sm.rate, \n"
                        + "    m.mechanicName, \n"
                        + "    p.partName, \n"
                        + "    pu.price\n"
                        + "FROM ServiceTicket st\n"
                        + "LEFT JOIN [dbo].[ServiceMehanic] sm ON st.serviceTicketID = sm.serviceTicketID\n"
                        + "LEFT JOIN Mechanic m ON sm.mechanicID = m.mechanicID\n"
                        + "LEFT JOIN PartsUsed pu ON st.serviceTicketID = pu.serviceTicketID\n"
                        + "LEFT JOIN Parts p ON pu.partID = p.partID WHERE st.serviceTicketID = ?;";

                PreparedStatement stmt = cn.prepareStatement(sql);
                stmt.setString(1, id);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    serviceTicketDetail = new ServiceTicketDetail(
                            rs.getString("serviceTicketID"),
                            rs.getDate("dateReceived") != null ? rs.getDate("dateReceived").toLocalDate() : null,
                            rs.getDate("dateReturned") != null ? rs.getDate("dateReturned").toLocalDate() : null,
                            rs.getString("carID"),
                            rs.getInt("custID"),
                            rs.getInt("hours"),
                            rs.getString("comment"),
                            rs.getDouble("rate"),
                            rs.getString("mechanicName"),
                            rs.getString("serviceName"),
                            rs.getString("partName"),
                            rs.getDouble("price")
                    );
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
        return serviceTicketDetail;
    }

    public void updateService(int serviceID, String comment, int rate, int hours) {
        Connection cn = null;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "UPDATE Service SET comment = ?, rate = ?, hours = ? WHERE serviceID = ?";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, comment);
                ps.setInt(2, rate);
                ps.setInt(3, hours);
                ps.setInt(4, serviceID);
                ps.executeUpdate();
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
    }

    public boolean updateServiceTicket(String serviceTicketID, String serviceID, int hours, double rate, String comment) {
        String sql = "UPDATE [dbo].[ServiceMehanic] SET hours = ?, rate = ?, comment = ? WHERE serviceTicketID = ? AND serviceID = ?";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, hours);
            ps.setDouble(2, rate);
            ps.setString(3, comment);
            ps.setString(4, serviceTicketID);
            ps.setString(5, serviceID);

            int updatedRows = ps.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ServiceTicket> getServices(int custID) {
        List<ServiceTicket> list = new ArrayList<>();
        Connection cn = null;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT serviceTicketID, dateReceived, dateReturned, carID \n"
                        + " FROM ServiceTicket WHERE custID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, custID);
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    ServiceTicket ticket = new ServiceTicket();
                    ticket.setSeviceTicketID(rs.getString("serviceTicketID"));
                    ticket.setDateReceived(rs.getDate("dateReceived").toLocalDate());
                    ticket.setDateReturned(rs.getDate("dateReturned").toLocalDate());
                    ticket.setCarID(rs.getString("carID"));
                    list.add(ticket);
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
        return list;
    }

    public ServiceTicket getServiceTicketById(String serviceTicketID) {
        Connection cn = null;
        ServiceTicket ticket = null;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String query = "SELECT * FROM ServiceTicket WHERE serviceTicketID = ?";
                PreparedStatement stmt = cn.prepareStatement(query);
                stmt.setString(1, serviceTicketID);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    ticket = new ServiceTicket(
                            rs.getString("serviceTicketID"),
                            rs.getDate("dateReceived") != null ? rs.getDate("dateReceived").toLocalDate() : null,
                            rs.getDate("dateReturned") != null ? rs.getDate("dateReturned").toLocalDate() : null,
                            rs.getString("carID"),
                            rs.getInt("custID")
                    );
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
        return ticket;
    }

    public List<ServiceTicketDetail> getListServiceTicketsDetailByMechanicID(String id) {
        List<ServiceTicketDetail> list = new ArrayList<>();
        Connection cn = null;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT st.serviceTicketID,sm.serviceID, st.dateReceived, st.dateReturned, st.custID, st.carID, \n"
                        + "                       sm.hours, sm.comment, s.[hourlyRate] \n"
                        + "                       FROM [dbo].[ServiceMehanic] sm \n"
                        + "                       JOIN ServiceTicket st ON sm.serviceTicketID = st.serviceTicketID \n"
                        + "                       JOIN Service s ON sm.serviceID = s.serviceID \n"
                        + "                       WHERE sm.mechanicID = ?";

                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, id);
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    list.add(new ServiceTicketDetail(rs.getString("serviceTicketID"),
                            rs.getString("serviceID"),
                            rs.getDate("dateReceived") != null ? rs.getDate("dateReceived").toLocalDate() : null,
                            rs.getDate("dateReturned") != null ? rs.getDate("dateReturned").toLocalDate() : null,
                            rs.getString("carID"),
                            rs.getInt("custID"),
                            rs.getInt("hours"),
                            rs.getString("comment"),
                            rs.getDouble("hourlyRate")));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi SQL: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    public List<ServiceTicketDetail> searchServiceTickets(String mechanicID, String searchValue, String searchDate) {
        List<ServiceTicketDetail> list = new ArrayList<>();
        Connection cn = null;
        String newsql = "";

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT st.serviceTicketID,sm.serviceID, st.dateReceived, st.dateReturned, st.custID, st.carID, "
                        + "sm.hours, sm.comment, s.hourlyRate "
                        + "FROM ServiceMechanic sm "
                        + "JOIN ServiceTicket st ON sm.serviceTicketID = st.serviceTicketID "
                        + "JOIN Service s ON sm.serviceID = s.serviceID "
                        + "WHERE sm.mechanicID = ?";

                List<Object> params = new ArrayList<>();
                params.add(mechanicID); // Thêm mechanicID vào đầu tiên

                // Kiểm tra điều kiện tìm kiếm
                if (searchValue != null && !searchValue.trim().isEmpty()) {
                    if (searchDate != null && !searchDate.trim().isEmpty()) {
                        newsql = sql + " AND (st.carID = ? OR st.custID = ?) AND st.dateReceived = ?";
                        params.add(searchValue);
                        params.add(searchValue);
                        params.add(Date.valueOf(searchDate)); // Chuyển đổi sang kiểu Date
                    } else {
                        newsql = sql + " AND (st.carID = ? OR st.custID = ?)";
                        params.add(searchValue);
                        params.add(searchValue);
                    }
                } else if (searchDate != null && !searchDate.trim().isEmpty()) {
                    newsql = sql + " AND st.dateReceived = ?";
                    params.add(Date.valueOf(searchDate));
                } else {
                    // Nếu không có điều kiện tìm kiếm, gọi lại hàm lấy toàn bộ danh sách
                    return getListServiceTicketsDetailByMechanicID(mechanicID);
                }

                PreparedStatement pst = cn.prepareStatement(newsql);

                // Gán tham số vào PreparedStatement
                for (int i = 0; i < params.size(); i++) {
                    if (params.get(i) instanceof Date) {
                        pst.setDate(i + 1, (Date) params.get(i));
                    } else {
                        pst.setString(i + 1, params.get(i).toString());
                    }
                }

                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    list.add(new ServiceTicketDetail(
                            rs.getString("serviceTicketID"),
                            rs.getString("serviceID"),
                            rs.getDate("dateReceived") != null ? rs.getDate("dateReceived").toLocalDate() : null,
                            rs.getDate("dateReturned") != null ? rs.getDate("dateReturned").toLocalDate() : null,
                            rs.getString("carID"),
                            rs.getInt("custID"),
                            rs.getInt("hours"),
                            rs.getString("comment"),
                            rs.getDouble("hourlyRate")));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi SQL: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    public ServiceTicketDetail getServiceTicketsDetailByMechanicID(String id, String ticketID) {
        ServiceTicketDetail s = new ServiceTicketDetail();
        Connection cn = null;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT st.serviceTicketID,sm.serviceID, st.dateReceived, st.dateReturned, st.custID, st.carID, \n"
                        + "                       sm.hours, sm.comment, s.[hourlyRate] \n"
                        + "                       FROM [dbo].[ServiceMehanic] sm \n"
                        + "                       JOIN ServiceTicket st ON sm.serviceTicketID = st.serviceTicketID \n"
                        + "                       JOIN Service s ON sm.serviceID = s.serviceID \n"
                        + "                       WHERE sm.mechanicID = ? AND st.serviceTicketID = ?";

                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, id);
                pst.setString(2, ticketID);
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    s = new ServiceTicketDetail(rs.getString("serviceTicketID"),
                            rs.getString("serviceID"),
                            rs.getDate("dateReceived") != null ? rs.getDate("dateReceived").toLocalDate() : null,
                            rs.getDate("dateReturned") != null ? rs.getDate("dateReturned").toLocalDate() : null,
                            rs.getString("carID"),
                            rs.getInt("custID"),
                            rs.getInt("hours"),
                            rs.getString("comment"),
                            rs.getDouble("hourlyRate"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi SQL: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return s;
    }
}
