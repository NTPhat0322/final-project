package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
        String sql = "SELECT serviceTicketID, dateReceived, dateReturned, carID, custID "
                + "FROM ServiceTicket ";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new ServiceTicket(
                        rs.getString("serviceTicketID"),
                        rs.getDate("dateReceived") != null ? rs.getDate("dateReceived").toLocalDate() : null,
                        rs.getDate("dateReturned") != null ? rs.getDate("dateReturned").toLocalDate() : null,
                        rs.getString("carID"),
                        rs.getInt("custID")
                ));

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ServiceTicket> getServiceTicketsByMechanicID(String id) {
        List<ServiceTicket> list = new ArrayList<>();
        String sql = "SELECT st.serviceTicketID, dateReceived, dateReturned, custID, carID "
                + "FROM [dbo].[ServiceTicket] st "
                + "JOIN [dbo].[ServiceMehanic] sm ON st.serviceTicketID = sm.serviceTicketID "
                + "WHERE sm.mechanicID = ?;";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            // **Thiết lập giá trị cho tham số "?"**
            stmt.setString(1, id);

            // **Thực thi truy vấn**
            try (ResultSet rs = stmt.executeQuery()) {
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
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    //serviceTiketDetail
    public ServiceTicketDetail getServiceTicketsDetailByMechanicID(String id) {
        String sql = "SELECT st.serviceTicketID, dateReceived, dateReturned, custID, carID, sm.hours, sm.comment, sm.rate "
                + "FROM [dbo].[ServiceTicket] st "
                + "JOIN [dbo].[ServiceMehanic] sm ON st.serviceTicketID = sm.serviceTicketID "
                + "WHERE sm.mechanicID = ?;";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            // **Thiết lập giá trị cho tham số "?"**
            stmt.setString(1, id);

            // **Thực thi truy vấn**
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    return new ServiceTicketDetail(
                            rs.getString("serviceTicketID"),
                            rs.getInt("carID"),
                            rs.getInt("custID"),
                            rs.getDate("dateReceived") != null ? rs.getDate("dateReceived").toLocalDate() : null,
                            rs.getDate("dateReturned") != null ? rs.getDate("dateReturned").toLocalDate() : null,
                            rs.getInt("hours"),
                            rs.getString("comment"),
                            rs.getBigDecimal("rate")
                    );
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ServiceTicketDetail> getListServiceTicketsDetailByMechanicID(String id) {
        String sql = "SELECT st.serviceTicketID, dateReceived, dateReturned, custID, carID, sm.hours, sm.comment, sm.rate "
                + "FROM [dbo].[ServiceTicket] st "
                + "JOIN [dbo].[ServiceMehanic] sm ON st.serviceTicketID = sm.serviceTicketID "
                + "WHERE sm.mechanicID = ?;";
        List<ServiceTicketDetail> list = new ArrayList<>();
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            // **Thiết lập giá trị cho tham số "?"**
            stmt.setString(1, id);

            // **Thực thi truy vấn**
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new ServiceTicketDetail(
                            rs.getString("serviceTicketID"),
                            rs.getInt("carID"),
                            rs.getInt("custID"),
                            rs.getDate("dateReceived") != null ? rs.getDate("dateReceived").toLocalDate() : null,
                            rs.getDate("dateReturned") != null ? rs.getDate("dateReturned").toLocalDate() : null,
                            rs.getInt("hours"),
                            rs.getString("comment"),
                            rs.getBigDecimal("rate")
                    ));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateServiceTicket(String serviceTicketID, int hours, String comment, BigDecimal rate) {
        String sql = "UPDATE ServiceMehanic SET hours = ?, comment = ?, rate = ? WHERE serviceTicketID = ?";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, hours);
            stmt.setString(2, comment);
            stmt.setBigDecimal(3, rate);
            stmt.setString(4, serviceTicketID);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cập nhật thành công!");
            } else {
                System.out.println("Không tìm thấy ServiceTicket để cập nhật!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateService(int serviceID, String comment, int rate, int hours) {
        String sql = "UPDATE Service SET comment = ?, rate = ?, hours = ? WHERE serviceID = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, comment);
            ps.setInt(2, rate);
            ps.setInt(3, hours);
            ps.setInt(4, serviceID);
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateServiceTicket(int ticketID, int hours, String comment, BigDecimal rate) {
        String sql = "UPDATE ServiceMehan ic SET hours = ?, comment = ?, rate = ? WHERE serviceTicketID = ?";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, hours);
            stmt.setString(2, comment);
            stmt.setBigDecimal(3, rate);
            stmt.setInt(4, ticketID);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cập nhật thành công!");
            } else {
                System.out.println("Không tìm thấy vé dịch vụ để cập nhật!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<ServiceTicketDetail> searchServiceTickets(String mechanicID, String searchValue, String searchDate) {
        List<ServiceTicketDetail> list = new ArrayList<>();
        String sql = "SELECT st.serviceTicketID, dateReceived, dateReturned, custID, carID, sm.hours, sm.comment, sm.rate "
                + "FROM [dbo].[ServiceTicket] st "
                + "JOIN [dbo].[ServiceMehanic] sm ON st.serviceTicketID = sm.serviceTicketID "
                + "WHERE sm.mechanicID = ? AND (carID = ? OR custID = ?)";

        // Nếu có ngày nhận, thêm điều kiện tìm theo ngày
        if (searchDate != null && !searchDate.isEmpty()) {
            sql += " OR dateReceived = ?)";
        }

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, mechanicID);
            stmt.setString(2, searchValue); // Tìm gần đúng carID
            stmt.setString(3, searchValue); // Tìm gần đúng custID

            if (searchDate != null && !searchDate.isEmpty()) {
                stmt.setDate(4, java.sql.Date.valueOf(searchDate));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new ServiceTicketDetail(
                            rs.getString("serviceTicketID"),
                            rs.getInt("carID"),
                            rs.getInt("custID"),
                            rs.getDate("dateReceived") != null ? rs.getDate("dateReceived").toLocalDate() : null,
                            rs.getDate("dateReturned") != null ? rs.getDate("dateReturned").toLocalDate() : null,
                            rs.getInt("hours"),
                            rs.getString("comment"),
                            rs.getBigDecimal("rate")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ServiceTicket> getServices(int custID) {
        List<ServiceTicket> list = new ArrayList<>();
        Connection cn = null;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT serviceTicketID, dateReceived, dateReturned, carID \n"
                        + "                        FROM ServiceTicket WHERE custID = ?";
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
        String query = "SELECT * FROM ServiceTicket WHERE serviceTicketID = ?";
        ServiceTicket ticket = null;

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, serviceTicketID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ServiceTicket(
                        rs.getString("serviceTicketID"),
                        rs.getDate("dateReceived") != null ? rs.getDate("dateReceived").toLocalDate() : null,
                        rs.getDate("dateReturned") != null ? rs.getDate("dateReturned").toLocalDate() : null,
                        rs.getString("custID"),
                        rs.getInt("carID")
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ticket;
    }

}
