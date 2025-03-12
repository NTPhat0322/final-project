package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.ServiceTicket;
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

    public List<ServiceTicket> getServiceTicketsByMechanicName(String name) {
        List<ServiceTicket> list = new ArrayList<>();
        String sql = "SELECT st.*\n"
                + "FROM [dbo].[ServiceTicket] st\n"
                + "JOIN [dbo].[ServiceMehanic] sm ON st.serviceTicketID = sm.serviceTicketID\n"
                + "JOIN [dbo].[Mechanic] m ON sm.mechanicID = m.mechanicID\n"
                + "WHERE m.mechanicName = ?;";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            // **Thiết lập giá trị cho tham số "?"**
            stmt.setString(1, name);

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

}
