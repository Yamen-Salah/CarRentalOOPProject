import java.time.LocalDate;

// RentToOwnRental class extends the Rentals class to represent a rent-to-own rental
public class RentToOwnRental extends Rentals {
    private double purchasePrice; // The total purchase price of the vehicle
    private double monthlyInstallment; // The monthly installment amount
    private int monthsUntilOwnership; // The number of months remaining until ownership
    private boolean hasPurchased; // Flag indicating if the vehicle has been fully purchased

    // Constructor to initialize all fields, including those from the parent Rentals class
    public RentToOwnRental(int id, int userId, int vehicleId, LocalDate startDate, LocalDate endDate, boolean isActive,
                            double purchasePrice, double monthlyInstallment, int monthsUntilOwnership, boolean hasPurchased) {
        super(id, userId, vehicleId, startDate, endDate, isActive); // Call the parent class constructor
        this.purchasePrice = purchasePrice; // Set the purchase price
        this.monthlyInstallment = monthlyInstallment; // Set the monthly installment
        this.monthsUntilOwnership = monthsUntilOwnership; // Set the months until ownership
        this.hasPurchased = hasPurchased; // Set the purchase status
    }

    // Override the calculateCost method to calculate the total cost based on monthly installments
    @Override
    public double calculateCost(Vehicle vehicle) {
        return monthsUntilOwnership * monthlyInstallment; // Total cost is the remaining installments
    }

    // Method to check if ownership has been reached
    public boolean isOwnershipReached() {
        return monthsUntilOwnership <= 0; // Ownership is reached if no months are remaining
    }

    // Method to mark the vehicle as fully purchased
    public void completePurchase() {
        this.hasPurchased = true; // Set the purchase flag to true
    }
}