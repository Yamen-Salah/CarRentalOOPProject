import java.time.LocalDate;

public class FixedDurationRental extends Rentals {
    private int numberOfDays;
    private boolean insuranceIncluded;
    private double discountRate;

    public FixedDurationRental(int id, int userId, int vehicleId, LocalDate startDate, LocalDate endDate, boolean isActive,
                                int numberOfDays, boolean insuranceIncluded, double discountRate) {
        super(id, userId, vehicleId, startDate, endDate, isActive);
        this.numberOfDays = numberOfDays;
        this.insuranceIncluded = insuranceIncluded;
        this.discountRate = discountRate;
    }

    @Override
    public double calculateCost(Vehicle vehicle) {
        double baseCost = numberOfDays * vehicle.getPricePerDay();
        if (insuranceIncluded) {
            baseCost += 15 * numberOfDays; // Example insurance fee per day
        }
        if (isEligibleForDiscount()) {
            baseCost = applyDiscountIfEligible(baseCost);
        }
        return baseCost;
    }

    @Override
    public LocalDate getReturnDate() {
        return getStartDate().plusDays(numberOfDays);
    }

    public boolean isEligibleForDiscount() {
        return numberOfDays >= 10; // Discount eligibility
    }

    public double applyDiscountIfEligible(double baseCost) {
        return baseCost * (1 - discountRate);
    }
}
