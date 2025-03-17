
package model;

public class Car{
    private String carID;
    private String serialNumber;
    private String model;
    private String colour;
    private int year;
    private boolean status;
    private double price;
    
    public Car() {
    }

    public Car(String carID, String serialNumber, String model, String colour, int year, boolean status, double price) {
        this.carID = carID;
        this.serialNumber = serialNumber;
        this.model = model;
        this.colour = colour;
        this.year = year;
        this.status = status;
        this.price = price;
    }
    

    public Car(String carID, String serialNumber, String model, String colour, int year, boolean status) {
        this.carID = carID;
        this.serialNumber = serialNumber;
        this.model = model;
        this.colour = colour;
        this.year = year;
        this.status = status;
    }

    
    
    public Car(String carID, String serialNumber, String model, String colour, int year) {
        this.carID = carID;
        this.serialNumber = serialNumber;
        this.model = model;
        this.colour = colour;
        this.year = year;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    
    
    @Override
    public String toString() {
        return "Car{" + "carID=" + carID + ", serialNumber=" + serialNumber + ", model=" + model + ", colour=" + colour + ", year=" + year + '}';
    }
}
