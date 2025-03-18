/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author admin
 */
public class ServiceTicketDetail {

    private String serviceTicketID;
    private LocalDate dateReceived;
    private LocalDate dateReturned;
    private int carID;
    private int custID;
    private int hours;
    private String comment;
    private double rate;
    private String mechanicName;
    private String serviceName;
    private String partName;
    private double price;

    public ServiceTicketDetail() {
    }

    public ServiceTicketDetail(String serviceTicketID, LocalDate dateReceived, LocalDate dateReturned, int carID, int custID, int hours, String comment, double rate, String mechanicName, String serviceName, String partName, double price) {
        this.serviceTicketID = serviceTicketID;
        this.dateReceived = dateReceived;
        this.dateReturned = dateReturned;
        this.carID = carID;
        this.custID = custID;
        this.hours = hours;
        this.comment = comment;
        this.rate = rate;
        this.mechanicName = mechanicName;
        this.serviceName = serviceName;
        this.partName = partName;
        this.price = price;
    }

    public String getServiceTicketID() {
        return serviceTicketID;
    }

    public void setServiceTicketID(String serviceTicketID) {
        this.serviceTicketID = serviceTicketID;
    }

    public LocalDate getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(LocalDate dateReceived) {
        this.dateReceived = dateReceived;
    }

    public LocalDate getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(LocalDate dateReturned) {
        this.dateReturned = dateReturned;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
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

    public String getMechanicName() {
        return mechanicName;
    }

    public void setMechanicName(String mechanicName) {
        this.mechanicName = mechanicName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ServiceTicketDetail{" + "serviceTicketID=" + serviceTicketID + ", dateReceived=" + dateReceived + ", dateReturned=" + dateReturned + ", carID=" + carID + ", custID=" + custID + ", hours=" + hours + ", comment=" + comment + ", rate=" + rate + ", mechanicName=" + mechanicName + ", serviceName=" + serviceName + ", partName=" + partName + ", price=" + price + '}';
    }

    
}