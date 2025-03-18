package model;

import java.math.BigDecimal;

public class PartsUsed {

    private String serviceTicketID;
    private String partID;
    private int numberUsed;
    private double purchasePrice; 
    private String partName; // Thêm thuộc tính này

    public String getPartName() {
        return partName;
    }

    // Constructor
    public PartsUsed(String serviceTicketID, String partID, int numberUsed, double purchasePrice) {
        this.partID = partID;
        this.serviceTicketID = serviceTicketID;
        this.numberUsed = numberUsed;
        this.purchasePrice = purchasePrice;
    }

    // Getters and Setters
    public String getPartID() {
        return partID;
    }

    public void setPartID(String partID) {
        this.partID = partID;
    }

    public String getServiceTicketID() {
        return serviceTicketID;
    }

    public void setServiceTicketID(String serviceTicketID) {
        this.serviceTicketID = serviceTicketID;
    }

    public int getNumberUsed() {
        return numberUsed;
    }

    public void setNumberUsed(int numberUsed) {
        this.numberUsed = numberUsed;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    @Override
    public String toString() {
        return "PartsUsed{" + "serviceTicketID=" + serviceTicketID + ", partID=" + partID + ", numberUsed=" + numberUsed + ", purchasePrice=" + purchasePrice + ", partName=" + partName + '}';
    }

}
