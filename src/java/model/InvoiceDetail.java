/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class InvoiceDetail {
    private int invoiceId;
    private Date invoiceDate;
    private String saleName;
    private String carSerial;
    private String carModel;
    private String carColor;
    private int carYear;
    private Double carPrice;

    public InvoiceDetail() {
    }

    public InvoiceDetail(int invoiceId, Date invoiceDate, String saleName, String carSerial, String carModel, String carColor, int carYear, Double carPrice) {
        this.invoiceId = invoiceId;
        this.invoiceDate = invoiceDate;
        this.saleName = saleName;
        this.carSerial = carSerial;
        this.carModel = carModel;
        this.carColor = carColor;
        this.carYear = carYear;
        this.carPrice = carPrice;
    }

    public String getCarSerial() {
        return carSerial;
    }

    public void setCarSerial(String carSerial) {
        this.carSerial = carSerial;
    }

    

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public int getCarYear() {
        return carYear;
    }

    public void setCarYear(int carYear) {
        this.carYear = carYear;
    }

    public Double getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(Double carPrice) {
        this.carPrice = carPrice;
    }

   
    
    
    
}
