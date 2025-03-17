package dao;

import model.PartsUsed;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Part;
import mylib.DBUtils;

public class PartsUsedDAO {

    private Connection conn;

    public PartsUsedDAO() {
    }

    public List<PartsUsed> getPartsUsedByTicketID(String serviceTicketID) {
        List<PartsUsed> partsUsedList = new ArrayList<>();
        String query = "SELECT * FROM [dbo].[PartsUsed] WHERE serviceTicketID = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, serviceTicketID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                partsUsedList.add(new PartsUsed(
                        rs.getString("partID"),
                        rs.getString("serviceTicketID"),
                        rs.getInt("numberUsed"),
                        rs.getBigDecimal("purchasePrice")
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return partsUsedList;
    }

    public Part getPartById(String partID) {
        String query = "SELECT * FROM [dbo].[Parts] WHERE partID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, partID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Part(
                        rs.getInt("partID"),
                        rs.getString("partName")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
