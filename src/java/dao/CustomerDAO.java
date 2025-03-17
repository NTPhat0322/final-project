/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.ServiceTicket;
import mylib.DBUtils;

/**
 *
 * @author ASUS
 */
public class CustomerDAO {
//    public int createCustomer() {
//        
//    } 

    public ArrayList<Customer> searchCustomerByName(String name) {
        ArrayList<Customer> rs = new ArrayList<>();
        Connection cn = null;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT custID, custName, phone, sex, cusAddress, status\n"
                        + "FROM Customer\n"
                        + "WHERE custName LIKE ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + name + "%");//'%?%'
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int custID = table.getInt("custID");
                        String custName = table.getString("custName");
                        //phone trong sql null thì nhận về null
                        String custPhone = table.getString("phone") != null ? table.getString("phone") : "";
                        //sex trong sql null thì nhận về null
                        String custSex = table.getString("sex") != null ? table.getString("sex") : "";
                        //address trong sql null thì nhận về null
                        String custAddress = table.getString("cusAddress") != null ? table.getString("cusAddress") : "";
                        //status
                        boolean status = table.getBoolean("status");
                        Customer c = new Customer(custID, custName, custPhone, custSex, custAddress, status);
                        rs.add(c);
                    }
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

    public int createCustomer(Customer c) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "insert Customer\n"
                        + "values (?, ?, ?, ?, ?, 1)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, c.getCustID());
                pst.setString(2, c.getCustName());
                double custPhone = 0;
                if (!c.getCustPhone().isEmpty()) {
                    custPhone = Double.parseDouble(c.getCustPhone());
                }
                //nếu staff k nhập phone thì sẽ lưu phone = 0
                pst.setDouble(3, custPhone);
                //nếu staff k nhập sex thì sẽ lưu sex rỗng
                pst.setString(4, c.getCustSex());
                //nếu staff k nhập addresss thì sẽ lưu addresss rỗng
                pst.setString(5, c.getCustAddress());
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

    public Customer searchCustById(int id) {
        Customer rs = null;
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select custName, phone, sex, cusAddress, status\n"
                        + "from customer where custID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, id);
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    if (table.next()) {
                        String custName = table.getString("custName");
                        String phone = table.getString("phone") != null ? table.getString("phone") : "";
                        String sex = table.getString("sex") != null ? table.getString("sex") : "";
                        String cusAddress = table.getString("cusAddress") != null ? table.getString("cusAddress") : "";
                        boolean status = table.getBoolean("status");
                        rs = new Customer(id, custName, phone, sex, cusAddress, status);
                    }
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

    public int updateCust(Customer c) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "update Customer \n"
                        + "set custName = ?, phone = ?, sex = ?, cusAddress = ?\n"
                        + "where custID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, c.getCustName());
                //phone lúc đầu có thể rỗng, và khi update thì vẫn k thêm phone vào
                //--> phone vẫn rỗng --> lưu là 0
                double phone = c.getCustPhone().isEmpty() ? 0 : Double.parseDouble(c.getCustPhone());
                pst.setDouble(2, phone);
                pst.setString(3, c.getCustSex());
                pst.setString(4, c.getCustAddress());
                pst.setInt(5, c.getCustID());
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

    public int deleteCust(int id) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "update Customer set status = 0\n"
                        + "where custID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, id);
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

    public Customer getCustomerByNameAndPhone(String name, int phone) {
        Customer rs = null;
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select * from Customer where custName = ? and  phone = ?;";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + name + "%");
                pst.setInt(2, phone);
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    if (table.next()) {
                        return new Customer(rs.getCustID(),
                                rs.getCustName(),
                                rs.getCustPhone(),
                                rs.getCustSex(),
                                rs.getCustAddress(),
                                rs.isStatus());
                    }
                }
            }
        } catch (Exception e) {
        }

        return null;
    }

    public Customer loginCustomer(String custName, String phone) {
        String sql = "SELECT * FROM Customer WHERE custName = ? AND phone = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, custName);
            stmt.setString(2, phone);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Customer(
                            rs.getInt("custID"),
                            rs.getString("custName"),
                            rs.getString("phone"),
                            rs.getString("sex"),
                            rs.getString("cusAddress")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Không tìm thấy khách hàng
    }

    public boolean updateCustomer(int custID, String custName, String phone, String sex, String cusAddress) {
        String sql = "UPDATE Customer SET custName = ?, phone = ?, sex = ?, cusAddress = ? WHERE custID = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, custName);
            ps.setString(2, phone);
            ps.setString(3, sex);
            ps.setString(4, cusAddress);
            ps.setInt(5, custID);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public Customer getCustomerById(int custID) {
        String sql = "SELECT * FROM Customer WHERE custID = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, custID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Customer(rs.getInt("custID"), rs.getString("custName"),
                                    rs.getString("phone"), rs.getString("sex"),
                                    rs.getString("cusAddress"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    
}
