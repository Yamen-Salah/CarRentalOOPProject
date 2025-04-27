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
        double cost = numberOfDays * vehicle.getPricePerDay();
        if (insuranceIncluded) {
            cost += 15 * numberOfDays; // example: $15/day insurance
        }
        if (isEligibleForDiscount()) {
            cost = applyDiscountIfEligible(cost);
        }
        return cost;
    }

    public boolean isEligibleForDiscount() {
        return numberOfDays >= 7; // example: discount if renting for a week+
    }

    public double applyDiscountIfEligible(double cost) {
        return cost * (1 - discountRate);
    }
}
