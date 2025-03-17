package model;

import java.math.BigDecimal;

public class PartsUsed {

    private String serviceTicketID;
    private String partID;
    private int numberUsed;
    private BigDecimal purchasePrice; // Dùng BigDecimal để đảm bảo độ chính xác của giá phụ tùng
    private String partName; // Thêm thuộc tính này

    public String getPartName() {
        return partName;
    }

    // Constructor
    public PartsUsed(String serviceTicketID, String partID, int numberUsed, BigDecimal purchasePrice) {
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

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    // Tính tổng giá trị phụ tùng sử dụng
    public BigDecimal getTotalCost() {
        return purchasePrice.multiply(BigDecimal.valueOf(numberUsed));
    }

    // toString() để debug
    @Override
    public String toString() {
        return "PartsUsed{" + "serviceTicketID=" + serviceTicketID + ", partID=" + partID + ", numberUsed=" + numberUsed + ", purchasePrice=" + purchasePrice + '}';
    }

}
