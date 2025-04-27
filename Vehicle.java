import java.time.Year;

public class Vehicle {
    private int id;
    private String make;
    private String model;
    private Year year;
    private String plateNumber;
    private double pricePerDay;

    public Vehicle(int id, String make, String model, Year year, String plateNumber, double pricePerDay) {  
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.plateNumber = plateNumber;
        this.pricePerDay = pricePerDay;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public Year getYear() {
        return year;
    }
    public void setYear(Year year) {
        this.year = year;
    }
    public String getPlateNumber() {
        return plateNumber;
    }
    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
    public double getPricePerDay() {
        return pricePerDay;
    }
    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
    public void displayInfo()
    {
        System.out.println(make + " " + model + " (" + year + "), Plate: " + plateNumber + ", Price/Day: $" + pricePerDay);
    }
}
