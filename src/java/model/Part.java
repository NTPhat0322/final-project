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
public class Part {
    private int partId;
    private String partName;
    private Double purchasePrice;
    private Double retailPrice;

    public Part() {
    }

    public Part(int partId, String partName, Double purchasePrice, Double retailPrice) {
        this.partId = partId;
        this.partName = partName;
        this.purchasePrice = purchasePrice;
        this.retailPrice = retailPrice;
    }

    public Part(int partId, String partName) {
        this.partId = partId;
        this.partName = partName;
    }
    
    

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }
}
