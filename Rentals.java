import java.time.LocalDate;
class Rentals {
    private int id;
    private int userId;
    private int vehicleId;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isActive;

    Rentals(int id, int userId, int vehicleId, LocalDate startDate, LocalDate endDate, boolean isActive) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Dates cannot be null.");
        }
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date.");
        }
        this.id = id;
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }
    int getId() {
        return id;
    }
    void setId(int id) {
        this.id = id;
    }
    int getUserId() {
        return userId;
    }
    void setUserId(int userId) {
        this.userId = userId;
    }
    int getVehicleId() {
        return vehicleId;
    }
    void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }
    LocalDate getStartDate() {
        return startDate;
    }
    void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    LocalDate getEndDate() {
        return endDate;
    }
    void setEndDate(LocalDate endDate) {
        if (endDate == null) {
            throw new IllegalArgumentException("End date cannot be null.");
        }
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date.");
        }
        this.endDate = endDate;
    }
    
    boolean isActive() {
        return isActive;
    }
    void setActive(boolean active) {
        isActive = active;
    }

    double calculateCost(Vehicle vehicle) {
        long days = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
        return days * vehicle.getPricePerDay();
    }
    LocalDate getReturnDate() {
        return endDate;
    }
}
