import java.io.*;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

public class CarRentalSystem {

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
                case 1: rentalManager.viewAllRentals(); break;
                case 2: rentalManager.searchRentalById(); break;
                case 3: rentalManager.createRental(); break;
                case 4: userManager.createUser(); break;
                case 5: vehicleManager.createVehicle(); break;
                case 6: rentalManager.editRental(); break;
                case 0: rentalCounter = rentalManager.getRentalCounter();
                        userCounter = userManager.getUserCounter();
                        vehicleCounter = vehicleManager.getVehicleCounter();
                        System.out.println("Saving data and exiting...");
                        saveData();
                        System.exit(0);
                default: System.out.println("Invalid option.");
            }
        }
    }
    // === File I/O ===
    static void loadData() {
        try {
            
            BufferedReader br = new BufferedReader(new FileReader("users.txt"));// Open the "users.txt" file for reading
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 3);//split
                int id = Integer.parseInt(parts[0]);// Parse 
                String name = parts[1];
                String phone = parts[2];
                users.add(new User(id, name, phone));// Create a new User object and add it to the users list
                
                userCounter = Math.max(userCounter, id + 1); // Update the userCounter to ensure unique IDs for new users
            }
            br.close();// Close the file after reading

            br = new BufferedReader(new FileReader("vehicles.txt"));
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 6);
                int id = Integer.parseInt(parts[0]);
                String make = parts[1];
                String model = parts[2];
                Year year = Year.of(Integer.parseInt(parts[3]));
                String plate = parts[4];
                double pricePerDay = Double.parseDouble(parts[5]);

                vehicles.add(new Vehicle(id, make, model, year, plate, pricePerDay));

                vehicleCounter = Math.max(vehicleCounter, id + 1);
            }
            br.close();   // Close the file after reading 

            br = new BufferedReader(new FileReader("rentals.txt"));
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 6);
                int id = Integer.parseInt(parts[0]);
                int userId = Integer.parseInt(parts[1]);
                int vehicleId = Integer.parseInt(parts[2]);
                LocalDate startDate = LocalDate.parse(parts[3]);
                LocalDate endDate = LocalDate.parse(parts[4]);
                boolean isActive = Boolean.parseBoolean(parts[5]);

                rentals.add(new Rentals(id, userId, vehicleId, startDate, endDate, isActive));

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
                pw.println(r.getId() + "," + r.getUserId() + "," + r.getVehicleId() + "," + r.getStartDate() + "," + r.getEndDate() + "," + r.isActive());
            }
            pw.close();
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving files.");
        }
    }

    // === UI and Functional Logic ===
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
}
