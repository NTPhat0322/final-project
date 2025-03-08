/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ASUS
 */
public class Customer {
    private int custID;
    private String custName;
    private String custPhone;
    private String custSex;
    private String custAddress;
    
    private boolean status;

    public Customer() {
    }

    public Customer(int custID, String custName, String custPhone, String custSex, String custAddress, boolean status) {
        this.custID = custID;
        this.custName = custName;
        this.custPhone = custPhone;
        this.custSex = custSex;
        this.custAddress = custAddress;
        this.status = status;
    }
    

    public Customer(int custID, String custName, String custPhone, String custSex, String custAddress) {
        this.custID = custID;
        this.custName = custName;
        this.custPhone = custPhone;
        this.custSex = custSex;
        this.custAddress = custAddress;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
   
    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustSex() {
        return custSex;
    }

    public void setCustSex(String custSex) {
        this.custSex = custSex;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    @Override
    public String toString() {
        return "Customer{" + "custID=" + custID + ", custName=" + custName + ", custPhone=" + custPhone + ", custSex=" + custSex + ", custAddress=" + custAddress + '}';
    }
    
    
}
