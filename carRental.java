import java.util.*;
import java.io.*;
import java.time.LocalDate;

public class carRental {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String[]> vehicles = new ArrayList<>();

        // Load vehicles from file
        try {s
            BufferedReader reader = new BufferedReader(new FileReader("vehicles.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    vehicles.add(parts);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading vehicles.txt.");
            return;
        }

        boolean running = true;

        while (running) {
            System.out.println("\n--- Car Rental System ---");
            System.out.println("1. View available vehicles");
            System.out.println("2. Rent a vehicle");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("\nAvailable Vehicles:");
                    for (int i = 0; i < vehicles.size(); i++) {
                        String[] v = vehicles.get(i);
                        System.out.printf("%d. %s %s (%s) - $%s/day\n", i + 1, v[0], v[1], v[2], v[3]);
                    }
                    break;

                case 2:
                    System.out.print("\nEnter your name: ");
                    String customerName = scanner.nextLine();

                    System.out.print("Enter rental type (fixed/monthly/renttobuy): ");
                    String rentalType = scanner.nextLine().toLowerCase();

                    System.out.print("Enter start date (YYYY-MM-DD): ");
                    String startDate = scanner.nextLine();

                    LocalDate startLocalDate;
                    try {
                        startLocalDate = LocalDate.parse(startDate);
                    } catch (Exception e) {
                        System.out.println("Invalid date format.");
                        break;
                    }

                    System.out.println("Select a vehicle to rent:");
                    for (int i = 0; i < vehicles.size(); i++) {
                        String[] v = vehicles.get(i);
                        System.out.printf("%d. %s %s (%s) - $%s/day\n", i + 1, v[0], v[1], v[2], v[3]);
                    }

                    System.out.print("Enter vehicle number: ");
                    int vehicleIndex = Integer.parseInt(scanner.nextLine()) - 1;

                    if (vehicleIndex < 0 || vehicleIndex >= vehicles.size()) {
                        System.out.println("Invalid vehicle selection.");
                        break;
                    }

                    String[] selectedVehicle = vehicles.get(vehicleIndex);
                    String make = selectedVehicle[0];
                    String model = selectedVehicle[1];
                    int year = Integer.parseInt(selectedVehicle[2]);
                    double pricePerDay = Double.parseDouble(selectedVehicle[3]);
                    double totalCost = 0;
                    LocalDate returnDate = startLocalDate;

                    if (rentalType.equals("fixed")) {
                        System.out.print("Enter number of days: ");
                        int days = Integer.parseInt(scanner.nextLine());
                        totalCost = days * pricePerDay;
                        returnDate = startLocalDate.plusDays(days);
                        System.out.println("Rental type: Fixed Duration (" + days + " days)");
                    } else if (rentalType.equals("monthly")) {
                        System.out.print("Enter number of months: ");
                        int months = Integer.parseInt(scanner.nextLine());
                        int totalDays = months * 30;
                        totalCost = totalDays * pricePerDay * 0.9;
                        returnDate = startLocalDate.plusDays(totalDays);
                        System.out.println("Rental type: Monthly Auto-Renewal (" + months + " months)");
                    } else if (rentalType.equals("renttobuy")) {
                        System.out.print("Enter number of months: ");
                        int months = Integer.parseInt(scanner.nextLine());
                        int totalDays = months * 30;
                        double purchasePrice = 10000;
                        totalCost = totalDays * pricePerDay * 0.9;
                        if (months >= 12) {
                            totalCost += purchasePrice;
                            System.out.println("You are eligible to buy the vehicle! Purchase price added.");
                        }
                        returnDate = startLocalDate.plusDays(totalDays);
                        System.out.println("Rental type: Rent-To-Buy (" + months + " months)");
                    } else {
                        System.out.println("Invalid rental type.");
                        break;
                    }

                    // Summary
                    System.out.println("\n--- Rental Summary ---");
                    System.out.println("Customer: " + customerName);
                    System.out.println("Start Date: " + startDate);
                    System.out.println("Return Date: " + returnDate);
                    System.out.println("Vehicle: " + make + " " + model + " (" + year + ")");
                    System.out.printf("Total Cost: $%.2f\n", totalCost);
                    break;

                case 3:
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }
}
