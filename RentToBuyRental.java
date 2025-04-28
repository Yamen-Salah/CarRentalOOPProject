import java.time.LocalDate;

// RentToOwnRental class extends the Rentals class to represent a rent-to-own rental
public class RentToBuyRental extends MonthlyAutoRenewalRental {
    private double purchasePrice; // The total purchase price of the vehicle
    private double monthlyInstallment; // The monthly installment amount
    private int monthsUntilOwnership; // The number of months remaining until ownership
    private boolean hasPurchased; // Flag indicating if the vehicle has been fully purchased

    // Constructor to initialize all fields, including those from the parent Rentals class
    public RentToBuyRental(int id, int userId, int vehicleId, LocalDate startDate, LocalDate endDate, boolean isActive,int monthsRented, boolean autoRenewing, LocalDate billingEnd, double purchasePrice, double monthlyInstallment, int monthsUntilOwnership, boolean hasPurchased) {
        super(id, userId, vehicleId, startDate, endDate, isActive, monthsRented, autoRenewing, billingEnd);
        this.purchasePrice = purchasePrice; // Set the purchase price
        this.monthlyInstallment = monthlyInstallment; // Set the monthly installment
        this.monthsUntilOwnership = monthsUntilOwnership; // Set the months until ownership
        this.hasPurchased = hasPurchased; // Set the purchase status
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }
    
    public double getMonthlyInstallment() {
        return monthlyInstallment;
    }
    
    public int getMonthsUntilOwnership() {
        return monthsUntilOwnership;
    }
    
    public boolean hasPurchased() {
        return hasPurchased;
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