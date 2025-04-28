import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class RentalManager {
    private ArrayList<Rentals> rentals;
    private ArrayList<User> users;
    private ArrayList<Vehicle> vehicles;
    private Scanner scanner;
    private int rentalCounter;
    public RentalManager(ArrayList<Rentals> rentals, ArrayList<User> users, ArrayList<Vehicle> vehicles, Scanner scanner, int rentalCounter) {
        this.rentals = rentals;
        this.users = users;
        this.vehicles = vehicles;
        this.scanner = new Scanner(System.in);
        this.rentalCounter = rentalCounter;
    }
    public int getRentalCounter() {
        return rentalCounter;
    }
    


    public void viewAllRentals() {
        System.out.println("\n--- All Rentals ---");
        for (Rentals rental : rentals) {
            System.out.println("Rental ID: " + rental.getId());
        }
    }



    public void createRental() {
        if (users.isEmpty() || vehicles.isEmpty()) {
            System.out.println("You must create at least one user and vehicle before creating a rental.");
            return;
        }
    
        System.out.println("\n--- Create Rental ---");
    
        System.out.println("Select user:");
        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + ". " + users.get(i).getName());
        }
        int userIndex = Integer.parseInt(scanner.nextLine()) - 1;
        User selectedUser = users.get(userIndex);
    
        System.out.println("Select vehicle:");
        for (int i = 0; i < vehicles.size(); i++) {
            System.out.println((i + 1) + ". " + vehicles.get(i).getModel());
        }
        int vehicleIndex = Integer.parseInt(scanner.nextLine()) - 1;
        Vehicle selectedVehicle = vehicles.get(vehicleIndex);
    
        System.out.print("Enter rental start date (YYYY-MM-DD): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());
    
        System.out.print("Enter rental end date (YYYY-MM-DD): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());
    
        System.out.println("\nChoose Rental Type:");
        System.out.println("1. Fixed Duration Rental");
        System.out.println("2. Monthly Auto-Renewal Rental");
        System.out.println("3. Rent-To-Buy Rental");
        int rentalTypeChoice = Integer.parseInt(scanner.nextLine());
    
        Rentals rental = null;
    
        switch (rentalTypeChoice) {
            case 1:
                System.out.print("Enter number of days: ");
                int numberOfDays = Integer.parseInt(scanner.nextLine());
                System.out.print("Include insurance? (true/false): ");
                boolean insuranceIncluded = Boolean.parseBoolean(scanner.nextLine());
                System.out.print("Enter discount rate (e.g., 0.10 for 10%): ");
                double discountRate = Double.parseDouble(scanner.nextLine());
    
                rental = new FixedDurationRental(rentalCounter++, selectedUser.getId(), selectedVehicle.getId(),
                        startDate, endDate, true, numberOfDays, insuranceIncluded, discountRate);
                break;
    
            case 2:
                System.out.print("Enter months rented: ");
                int monthsRented = Integer.parseInt(scanner.nextLine());
                System.out.print("Is auto-renewing? (true/false): ");
                boolean autoRenewing = Boolean.parseBoolean(scanner.nextLine());
                System.out.print("Enter billing end date (YYYY-MM-DD): ");
                LocalDate billingEnd = LocalDate.parse(scanner.nextLine());
    
                rental = new MonthlyAutoRenewalRental(rentalCounter++, selectedUser.getId(), selectedVehicle.getId(),
                        startDate, endDate, true, monthsRented, autoRenewing, billingEnd);
                break;
    
            case 3:
                System.out.print("Enter months rented: ");
                int months = Integer.parseInt(scanner.nextLine());
                System.out.print("Is auto-renewing? (true/false): ");
                boolean autoRenew = Boolean.parseBoolean(scanner.nextLine());
                System.out.print("Enter billing end date (YYYY-MM-DD): ");
                LocalDate billEnd = LocalDate.parse(scanner.nextLine());
                System.out.print("Enter purchase price: ");
                double purchasePrice = Double.parseDouble(scanner.nextLine());
                System.out.print("Enter monthly installment amount: ");
                double monthlyInstallment = Double.parseDouble(scanner.nextLine());
                System.out.print("Enter months until ownership: ");
                int monthsUntilOwnership = Integer.parseInt(scanner.nextLine());
    
                rental = new RentToBuyRental(rentalCounter++, selectedUser.getId(), selectedVehicle.getId(),
                        startDate, endDate, true, months, autoRenew, billEnd,
                        purchasePrice, monthlyInstallment, monthsUntilOwnership, false);
                break;
    
            default:
                System.out.println("Invalid rental type choice.");
                return;
        }
    
        rentals.add(rental);
        System.out.println("Rental created successfully.");
    }
    


    public void displayRentalDetails(int index) {
        Rentals rental = rentals.get(index);
        int userId = rental.getUserId();
        int vehicleId = rental.getVehicleId();
    
        System.out.println("\n--- Rental Details ---");
        System.out.println("Rental ID: " + rental.getId());
        
        System.out.println("User:");
        for (User user : users) {
            if (user.getId() == userId) {
                System.out.println("  Name: " + user.getName());
                System.out.println("  Phone: " + user.getPhone());
                break;
            }
        }
    
        System.out.println("Vehicle:");
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId() == vehicleId) {
                vehicle.displayInfo();
                break;
            }
        }
    
        System.out.println("Start Date: " + rental.getStartDate());
        System.out.println("End Date: " + rental.getEndDate());
        System.out.println("Is Active: " + rental.isActive());
    
        if (rental instanceof FixedDurationRental) {
            FixedDurationRental fixed = (FixedDurationRental) rental;
            System.out.println("Rental Type: Fixed Duration");
            System.out.println("  Number of Days: " + fixed.getNumberOfDays());
            System.out.println("  Insurance Included: " + fixed.isInsuranceIncluded());
            System.out.println("  Discount Rate: " + fixed.getDiscountRate());
        } else if (rental instanceof RentToBuyRental) {
            RentToBuyRental rentToBuy = (RentToBuyRental) rental;
            System.out.println("Rental Type: Rent-To-Buy");
            System.out.println("  Months Until Ownership: " + rentToBuy.getMonthsUntilOwnership());
            System.out.println("  Monthly Installment: " + rentToBuy.getMonthlyInstallment());
            System.out.println("  Has Purchased: " + rentToBuy.hasPurchased());
        } else if (rental instanceof MonthlyAutoRenewalRental) {
            MonthlyAutoRenewalRental monthly = (MonthlyAutoRenewalRental) rental;
            System.out.println("Rental Type: Monthly Auto-Renewal");
            System.out.println("  Months Rented: " + monthly.getMonthsRented());
            System.out.println("  Auto-Renewing: " + monthly.isAutoRenewing());
            System.out.println("  Billing End Date: " + monthly.getBillingEnd());
        }
    }
    

    public void editRental() {
        System.out.print("\nEnter Rental ID to edit: ");
        int id = Integer.parseInt(scanner.nextLine());
    
        int index = -1;
        for (int i = 0; i < rentals.size(); i++) {
            if (rentals.get(i).getId() == id) {
                index = i;
                break;
            }
        }
    
        if (index == -1) {
            System.out.println("Rental not found.");
            return;
        }
    
        System.out.println("Edit user:");
        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + ". " + users.get(i).getName());
        }
        int userIndex = Integer.parseInt(scanner.nextLine()) - 1;
        int newUserId = users.get(userIndex).getId();
    
        System.out.println("Edit vehicle:");
        for (int i = 0; i < vehicles.size(); i++) {
            System.out.println((i + 1) + ". " + vehicles.get(i).getModel());
        }
        int vehicleIndex = Integer.parseInt(scanner.nextLine()) - 1;
        int newVehicleId = vehicles.get(vehicleIndex).getId();
    
        Rentals rental = rentals.get(index);
        rental.setUserId(newUserId);
        rental.setVehicleId(newVehicleId);
    
        System.out.println("Rental updated.");
    }


    public void searchRentalById() {
        System.out.print("\nEnter Rental ID to search: ");
        int id = Integer.parseInt(scanner.nextLine());
    
        for (int i = 0; i < rentals.size(); i++) {
            if (rentals.get(i).getId() == id) {
                displayRentalDetails(i);
                return;
            }
        }
        System.out.println("Rental not found.");
    }

    public ArrayList<Rentals> getAllRentals() {
        return rentals;
    }
    
}
