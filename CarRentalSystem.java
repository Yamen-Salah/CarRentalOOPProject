import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class CarRentalSystem {

    static ArrayList<Rentals> rentals = new ArrayList<>();
    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Vehicle> vehicles = new ArrayList<>();

    static Scanner scanner = new Scanner(System.in);
    static int rentalCounter = 1;
    static int userCounter = 1;
    static int vehicleCounter = 1;

    public static void main(String[] args) {
        loadData();

        RentalManager rentalManager = new RentalManager(rentals, users, vehicles, scanner, rentalCounter);
        UserManager userManager = new UserManager(users, scanner, userCounter);
        VehicleManager vehicleManager = new VehicleManager(vehicles, scanner, vehicleCounter);

        while (true) {
            printMainMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1: viewAllRentals(rentalManager, userManager, vehicleManager); break;
                case 2: searchRentalById(rentalManager, userManager, vehicleManager); break;
                case 3: rentalManager.createRental(); break;
                case 4: userManager.createUser(); break;
                case 5: vehicleManager.createVehicle(); break;
                case 6: rentalManager.editRental(); break;
                case 0:
                    rentalCounter = rentalManager.getRentalCounter();
                    userCounter = userManager.getUserCounter();
                    vehicleCounter = vehicleManager.getVehicleCounter();
                    System.out.println("Saving data and exiting...");
                    saveData();
                    System.exit(0);
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    static void printMainMenu() {
        System.out.println("\n=== Car Rental System ===");
        System.out.println("1. View all rentals");
        System.out.println("2. Search rental by ID");
        System.out.println("3. Create new rental");
        System.out.println("4. Create new user");
        System.out.println("5. Create new vehicle");
        System.out.println("6. Edit rental");
        System.out.println("0. Exit and Save");
        System.out.print("Choose an option: ");
    }

    static void viewAllRentals(RentalManager rentalManager, UserManager userManager, VehicleManager vehicleManager) {
        ArrayList<Rentals> allRentals = rentalManager.getAllRentals();

        if (allRentals.isEmpty()) {
            System.out.println("No rentals found.");
            return;
        }

        for (int i = 0; i < allRentals.size(); i++) {
            System.out.println("\nRental #" + (i + 1));
            displayRentalDetails(allRentals.get(i), userManager, vehicleManager);
        }
    }

    static void searchRentalById(RentalManager rentalManager, UserManager userManager, VehicleManager vehicleManager) {
        System.out.print("\nEnter Rental ID to search: ");
        int id = Integer.parseInt(scanner.nextLine());
        ArrayList<Rentals> allRentals = rentalManager.getAllRentals();
        for (Rentals rental : allRentals) {
            if (rental.getId() == id) {
                displayRentalDetails(rental, userManager, vehicleManager);
                return;
            }
        }
        System.out.println("Rental not found.");
    }

    static void displayRentalDetails(Rentals rental, UserManager userManager, VehicleManager vehicleManager) {
        int userId = rental.getUserId();
        int vehicleId = rental.getVehicleId();

        System.out.println("\n--- Rental Details ---");
        System.out.println("Rental ID: " + rental.getId());

        // Display User Info
        for (User user : userManager.getAllUsers()) {
            if (user.getId() == userId) {
                System.out.println("User: " + user.getName() + " (" + user.getPhone() + ")");
                break;
            }
        }

        // Display Vehicle Info
        for (Vehicle vehicle : vehicleManager.getAllVehicles()) {
            if (vehicle.getId() == vehicleId) {
                vehicle.displayInfo();
                break;
            }
        }

        System.out.println("Start Date: " + rental.getStartDate());
        System.out.println("End Date: " + rental.getEndDate());
        System.out.println("Is Active: " + rental.isActive());

        // Detect rental type
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

    static void loadData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("users.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 3);
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String phone = parts[2];
                users.add(new User(id, name, phone));
                userCounter = Math.max(userCounter, id + 1);
            }
            br.close();

            br = new BufferedReader(new FileReader("vehicles.txt"));
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 6);
                int id = Integer.parseInt(parts[0]);
                String make = parts[1];
                String model = parts[2];
                int year = Integer.parseInt(parts[3]);
                String plate = parts[4];
                double price = Double.parseDouble(parts[5]);
                vehicles.add(new Vehicle(id, make, model, java.time.Year.of(year), plate, price));
                vehicleCounter = Math.max(vehicleCounter, id + 1);
            }
            br.close();

            br = new BufferedReader(new FileReader("rentals.txt"));
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                String type = parts[0];
                int id = Integer.parseInt(parts[1]);
                int userId = Integer.parseInt(parts[2]);
                int vehicleId = Integer.parseInt(parts[3]);
                java.time.LocalDate startDate = java.time.LocalDate.parse(parts[4]);
                java.time.LocalDate endDate = java.time.LocalDate.parse(parts[5]);
                boolean isActive = Boolean.parseBoolean(parts[6]);

                if (type.equals("Fixed")) {
                    int days = Integer.parseInt(parts[7]);
                    boolean insurance = Boolean.parseBoolean(parts[8]);
                    double discount = Double.parseDouble(parts[9]);
                    rentals.add(new FixedDurationRental(id, userId, vehicleId, startDate, endDate, isActive, days, insurance, discount));
                } else if (type.equals("Monthly")) {
                    int months = Integer.parseInt(parts[7]);
                    boolean autoRenew = Boolean.parseBoolean(parts[8]);
                    java.time.LocalDate billingEnd = java.time.LocalDate.parse(parts[9]);
                    rentals.add(new MonthlyAutoRenewalRental(id, userId, vehicleId, startDate, endDate, isActive, months, autoRenew, billingEnd));
                } else if (type.equals("RentToBuy")) {
                    int months = Integer.parseInt(parts[7]);
                    boolean autoRenew = Boolean.parseBoolean(parts[8]);
                    java.time.LocalDate billingEnd = java.time.LocalDate.parse(parts[9]);
                    double purchasePrice = Double.parseDouble(parts[10]);
                    double monthlyInstallment = Double.parseDouble(parts[11]);
                    int monthsUntilOwnership = Integer.parseInt(parts[12]);
                    boolean hasPurchased = Boolean.parseBoolean(parts[13]);
                    rentals.add(new RentToBuyRental(id, userId, vehicleId, startDate, endDate, isActive, months, autoRenew, billingEnd, purchasePrice, monthlyInstallment, monthsUntilOwnership, hasPurchased));
                }

                rentalCounter = Math.max(rentalCounter, id + 1);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading files.");
        }
    }

    static void saveData() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("users.txt"));
            for (User u : users) {
                pw.println(u.getId() + "," + u.getName() + "," + u.getPhone());
            }
            pw.close();

            pw = new PrintWriter(new FileWriter("vehicles.txt"));
            for (Vehicle v : vehicles) {
                pw.println(v.getId() + "," + v.getMake() + "," + v.getModel() + "," + v.getYear().getValue() + "," + v.getPlateNumber() + "," + v.getPricePerDay());
            }
            pw.close();

            pw = new PrintWriter(new FileWriter("rentals.txt"));
            for (Rentals r : rentals) {
                if (r instanceof FixedDurationRental) {
                    FixedDurationRental f = (FixedDurationRental) r;
                    pw.println("Fixed," + f.getId() + "," + f.getUserId() + "," + f.getVehicleId() + "," + f.getStartDate() + "," + f.getEndDate() + "," + f.isActive()
                            + "," + f.getNumberOfDays() + "," + f.isInsuranceIncluded() + "," + f.getDiscountRate());
                } else if (r instanceof RentToBuyRental) {
                    RentToBuyRental b = (RentToBuyRental) r;
                    pw.println("RentToBuy," + b.getId() + "," + b.getUserId() + "," + b.getVehicleId() + "," + b.getStartDate() + "," + b.getEndDate() + "," + b.isActive()
                            + "," + b.getMonthsRented() + "," + b.isAutoRenewing() + "," + b.getBillingEnd()
                            + "," + b.getPurchasePrice() + "," + b.getMonthlyInstallment() + "," + b.getMonthsUntilOwnership() + "," + b.hasPurchased());
                } else if (r instanceof MonthlyAutoRenewalRental) {
                    MonthlyAutoRenewalRental m = (MonthlyAutoRenewalRental) r;
                    pw.println("Monthly," + m.getId() + "," + m.getUserId() + "," + m.getVehicleId() + "," + m.getStartDate() + "," + m.getEndDate() + "," + m.isActive()
                            + "," + m.getMonthsRented() + "," + m.isAutoRenewing() + "," + m.getBillingEnd());
                }
            }
            pw.close();

            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving files.");
        }
    }
}
