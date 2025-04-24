import java.io.*;
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
        while (true) {
            printMainMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1: viewAllRentals(); break;
                case 2: searchRentalById(); break;
                case 3: createRental(); break;
                case 4: createUser(); break;
                case 5: createVehicle(); break;
                case 6: editRental(); break;
                case 0: saveData(); System.exit(0);
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
                String[] parts = line.split(",", 3);
                int id = Integer.parseInt(parts[0]);
                String model = parts[1];
                String plate = parts[2];
                vehicles.add(new Vehicle(id, model, plate));
                
                vehicleCounter = Math.max(vehicleCounter, id + 1); // Update the vehicleCounter to ensure unique IDs for new vehicles
            }
            br.close();    

            br = new BufferedReader(new FileReader("rentals.txt"));
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 3);
                int id = Integer.parseInt(parts[0]);
                int userId = Integer.parseInt(parts[1]);
                int vehicleId = Integer.parseInt(parts[2]);
                rentals.add(new Rentals(id, userId, vehicleId, "", "", true));
                
                rentalCounter = Math.max(rentalCounter, id + 1); // Update the rentalCounter to ensure unique IDs for new rentals
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
                pw.println(v.getId() + "," + v.getModel() + "," + v.getPlateNumber());
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

    static void viewAllRentals() {
        System.out.println("\n--- All Rentals ---");
        for (Rentals rental : rentals) {
            System.out.println("Rental ID: " + rental.getId());
        }
    }
    

    static void searchRentalById() {
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
    
    

    static void displayRentalDetails(int index) {
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
                System.out.println("  Model: " + vehicle.getModel());
                System.out.println("  Plate: " + vehicle.getPlateNumber());
                break;
            }
        }
    }

    static void createRental() {
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
    
        System.out.print("Enter rental start date: ");
        String startDate = scanner.nextLine();
        System.out.print("Enter rental end date: ");
        String endDate = scanner.nextLine();
    
        Rentals rental = new Rentals(rentalCounter++, selectedUser.getId(), selectedVehicle.getId(), startDate, endDate, true);
        rentals.add(rental);
    
        System.out.println("Rental created successfully.");
    }
    static void createUser() {
        System.out.print("\nEnter user name: ");
        String name = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
    
        User newUser = new User(userCounter++, name, phone);
        users.add(newUser);
    
        System.out.println("User created.");
    }
    
    static void createVehicle() {
        System.out.print("\nEnter vehicle model: ");
        String model = scanner.nextLine();
        System.out.print("Enter plate number: ");
        String plate = scanner.nextLine();
    
        Vehicle newVehicle = new Vehicle(vehicleCounter++, model, plate);
        vehicles.add(newVehicle);
    
        System.out.println("Vehicle created.");
    }
    

    static void editRental() {
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
    
}
