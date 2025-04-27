import java.time.LocalDate;

// MonthlyAutoRenewalRental class extends the Rentals class to represent a rental with monthly auto-renewal
public class MonthlyAutoRenewalRental extends Rentals {
    private int monthsRented; // The number of months the vehicle has been rented
    private boolean autoRenewing; // Flag indicating if the rental is set to auto-renew
    private LocalDate billingEnd; // The end date of the current billing period

    // Constructor to initialize all fields, including those from the parent Rentals class
    public MonthlyAutoRenewalRental(int id, int userId, int vehicleId, LocalDate startDate, LocalDate endDate, boolean isActive,
                                       int monthsRented, boolean autoRenewing, LocalDate billingEnd) {
        super(id, userId, vehicleId, startDate, endDate, isActive); // Call the parent class constructor
        this.monthsRented = monthsRented; // Set the number of months rented
        this.autoRenewing = autoRenewing; // Set the auto-renewal status
        this.billingEnd = billingEnd; // Set the billing end date
    }

    // Override the calculateCost method to calculate the total cost based on months rented
    @Override
    public double calculateCost(Vehicle vehicle) {
        return monthsRented * vehicle.getPricePerDay() * 30; // Calculate cost assuming 30 days per month
    }   

    // Override the getReturnDate method to return the billing end date
    @Override
    public LocalDate getReturnDate() {
        return billingEnd; // The return date is the end of the current billing period
    }

    // Method to get the next billing date
    public LocalDate getNextBillingDate() {
        return billingEnd.plusMonths(1); // Add one month to the current billing end date
    }

    // Method to cancel the auto-renewal feature
    public void cancelAutoRenew() {
        this.autoRenewing = false; // Set the auto-renewing flag to false
    }
}