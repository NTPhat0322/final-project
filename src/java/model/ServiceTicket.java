
package model;

import java.time.LocalDate;

public class ServiceTicket {
    private String seviceTicketID;
    private LocalDate dateReceived;
    private LocalDate dateReturned;
    private String carID;
    private int custID;

    public ServiceTicket() {
    }

    public ServiceTicket(String seviceTicketID, LocalDate dateReceived, LocalDate dateReturned, String carID,int custID ) {
        this.seviceTicketID = seviceTicketID;
        this.dateReceived = dateReceived;
        this.dateReturned = dateReturned;   
        this.custID = custID;
        this.carID = carID;

    }


    public String getSeviceTicketID() {
        return seviceTicketID;
    }

    public void setSeviceTicketID(String seviceTicketID) {
        this.seviceTicketID = seviceTicketID;
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

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    @Override
    public String toString() {
        return "ServiceTicket{" + "seviceTicketID=" + seviceTicketID + ", dateReceived=" + dateReceived + ", dateReturned=" + dateReturned + ", carID=" + carID + ", custID=" + custID + '}';
    }
    
    
}
