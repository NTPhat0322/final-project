package model;

import java.math.BigDecimal;

public class ServiceMechanic {

    private String mechanicID;
    private String serviceTicketID;
    private String serviceID;
    private double hours;
    private String comment;
    private Double rate; // Thay đổi kiểu dữ liệu từ int -> BigDecimal
    private String serviceName;

    public String getServiceName() {
        return serviceName;
    }

    // Constructor
    public ServiceMechanic(String mechanicID, String serviceTicketID, String serviceID, double hours, String comment, Double rate) {
        this.mechanicID = mechanicID;
        this.serviceTicketID = serviceTicketID;
        this.serviceID = serviceID;
        this.hours = hours;
        this.comment = comment;
        this.rate = rate;
    }

    // Getters and Setters
    public String getMechanicID() {
        return mechanicID;
    }

    public void setMechanicID(String mechanicID) {
        this.mechanicID = mechanicID;
    }

    public String getServiceTicketID() {
        return serviceTicketID;
    }

    public void setServiceTicketID(String serviceTicketID) {
        this.serviceTicketID = serviceTicketID;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    // toString() để debug dễ dàng hơn
    @Override
    public String toString() {
        return "ServiceMechanic{"
                + "mechanicID='" + mechanicID + '\''
                + ", serviceTicketID='" + serviceTicketID + '\''
                + ", serviceID='" + serviceID + '\''
                + ", hours=" + hours
                + ", comment='" + comment + '\''
                + ", rate=" + rate
                + '}';
    }
}
