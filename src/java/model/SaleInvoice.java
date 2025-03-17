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
public class SaleInvoice {
    private int invoiceId;
    private Date invoiceDate;
    private long saleId;
    private long carId;
    private int custId;

    public SaleInvoice() {
    }

    public SaleInvoice(int invoiceId, Date invoiceDate, long saleId, long carId, int custId) {
        this.invoiceId = invoiceId;
        this.invoiceDate = invoiceDate;
        this.saleId = saleId;
        this.carId = carId;
        this.custId = custId;
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

    public long getSaleId() {
        return saleId;
    }

    public void setSaleId(long saleId) {
        this.saleId = saleId;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    

    
    
    
}
