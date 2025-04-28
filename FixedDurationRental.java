import java.time.LocalDate;

class FixedDurationRental extends Rentals {
    private int numberOfDays;
    private boolean insuranceIncluded;
    private double discountRate;

    FixedDurationRental(int id, int userId, int vehicleId, LocalDate startDate, LocalDate endDate, boolean isActive,
                                int numberOfDays, boolean insuranceIncluded, double discountRate) {
        super(id, userId, vehicleId, startDate, endDate, isActive);
        this.numberOfDays = numberOfDays;
        this.insuranceIncluded = insuranceIncluded;
        this.discountRate = discountRate;
    }

    int getNumberOfDays() {
        return numberOfDays;
    }
    
    boolean isInsuranceIncluded() {
        return insuranceIncluded;
    }
    
    double getDiscountRate() {
        return discountRate;
    }
    

    @Override
    double calculateCost(Vehicle vehicle) {
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
    LocalDate getReturnDate() {
        return getStartDate().plusDays(numberOfDays);
    }

    boolean isEligibleForDiscount() {
        return numberOfDays >= 10; // Discount eligibility
    }

    double applyDiscountIfEligible(double baseCost) {
        return baseCost * (1 - discountRate);
    }
}
