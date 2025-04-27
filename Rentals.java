import java.time.LocalDate;
public class Rentals {
    private int id;
    private int userId;
    private int vehicleId;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isActive;

    public Rentals(int id, int userId, int vehicleId, LocalDate startDate, LocalDate endDate, boolean isActive) {
        this.id = id;
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }

    public double calculateCost(Vehicle vehicle) {
        long days = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
        return days * vehicle.getPricePerDay();
    }
    public LocalDate getReturnDate() {
        return endDate;
    }
}
