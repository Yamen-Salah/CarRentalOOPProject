import java.time.Year;

class Vehicle {
    private int id;
    private String make;
    private String model;
    private Year year;
    private String plateNumber;
    private double pricePerDay;

    Vehicle(int id, String make, String model, Year year, String plateNumber, double pricePerDay) {  
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.plateNumber = plateNumber;
        this.pricePerDay = pricePerDay;
    }

    int getId() {
        return id;
    }
    void setId(int id) {
        this.id = id;
    }
    String getMake() {
        return make;
    }
    void setMake(String make) {
        this.make = make;
    }
    String getModel() {
        return model;
    }
    void setModel(String model) {
        this.model = model;
    }
    Year getYear() {
        return year;
    }
    void setYear(Year year) {
        this.year = year;
    }
    String getPlateNumber() {
        return plateNumber;
    }
    void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
    double getPricePerDay() {
        return pricePerDay;
    }
    void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
    void displayInfo()
    {
        System.out.println(make + " " + model + " (" + year + "), Plate: " + plateNumber + ", Price/Day: $" + pricePerDay);
    }
}
