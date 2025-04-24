public class Vehicle {
    private int id;
    private String model;
    private String plateNumber;

    public Vehicle(int id, String model, String plateNumber) {
        this.id = id;
        this.model = model;
        this.plateNumber = plateNumber;
    }

    // Getters and setters will come later
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getPlateNumber() {
        return plateNumber;
    }
    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
}
