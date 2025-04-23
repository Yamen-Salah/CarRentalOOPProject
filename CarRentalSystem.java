import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CarRentalSystem {
    static ArrayList<Integer> rentalIds = new ArrayList<>();
    static ArrayList<Integer> rentalUserIds = new ArrayList<>();
    static ArrayList<Integer> rentalVehicleIds = new ArrayList<>();

    static ArrayList<Integer> userIds = new ArrayList<>();
    static ArrayList<String> userNames = new ArrayList<>();
    static ArrayList<String> userPhones = new ArrayList<>();

    static ArrayList<Integer> vehicleIds = new ArrayList<>();
    static ArrayList<String> vehicleModels = new ArrayList<>();
    static ArrayList<String> vehiclePlates = new ArrayList<>();

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
            BufferedReader br;

            br = new BufferedReader(new FileReader("users.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 3);
                userIds.add(Integer.parseInt(parts[0]));
                userNames.add(parts[1]);
                userPhones.add(parts[2]);
                userCounter = Math.max(userCounter, Integer.parseInt(parts[0]) + 1);
            }
            br.close();

            br = new BufferedReader(new FileReader("vehicles.txt"));
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 3);
                vehicleIds.add(Integer.parseInt(parts[0]));
                vehicleModels.add(parts[1]);
                vehiclePlates.add(parts[2]);
                vehicleCounter = Math.max(vehicleCounter, Integer.parseInt(parts[0]) + 1);
            }
            br.close();

            br = new BufferedReader(new FileReader("rentals.txt"));
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 3);
                rentalIds.add(Integer.parseInt(parts[0]));
                rentalUserIds.add(Integer.parseInt(parts[1]));
                rentalVehicleIds.add(Integer.parseInt(parts[2]));
                rentalCounter = Math.max(rentalCounter, Integer.parseInt(parts[0]) + 1);
            }
            br.close();

        } catch (IOException e) {
            System.out.println("Error reading files.");
        }
    }

    static void saveData() {
        try {
            PrintWriter pw;

            pw = new PrintWriter(new FileWriter("users.txt"));
            for (int i = 0; i < userIds.size(); i++) {
                pw.println(userIds.get(i) + "," + userNames.get(i) + "," + userPhones.get(i));
            }
            pw.close();

            pw = new PrintWriter(new FileWriter("vehicles.txt"));
            for (int i = 0; i < vehicleIds.size(); i++) {
                pw.println(vehicleIds.get(i) + "," + vehicleModels.get(i) + "," + vehiclePlates.get(i));
            }
            pw.close();

            pw = new PrintWriter(new FileWriter("rentals.txt"));
            for (int i = 0; i < rentalIds.size(); i++) {
                pw.println(rentalIds.get(i) + "," + rentalUserIds.get(i) + "," + rentalVehicleIds.get(i));
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
        for (int i = 0; i < rentalIds.size(); i++) {
            System.out.println("Rental ID: " + rentalIds.get(i));
        }
    }

    static void searchRentalById() {
        System.out.print("\nEnter Rental ID to search: ");
        int id = Integer.parseInt(scanner.nextLine());
        int index = rentalIds.indexOf(id);
        if (index == -1) {
            System.out.println("Rental not found.");
            return;
        }
        displayRentalDetails(index);
    }

    static void displayRentalDetails(int index) {
        int userId = rentalUserIds.get(index);
        int vehicleId = rentalVehicleIds.get(index);

        System.out.println("\n--- Rental Details ---");
        System.out.println("Rental ID: " + rentalIds.get(index));
        System.out.println("User:");
        int userIndex = userIds.indexOf(userId);
        System.out.println("  Name: " + userNames.get(userIndex));
        System.out.println("  Phone: " + userPhones.get(userIndex));

        System.out.println("Vehicle:");
        int vehicleIndex = vehicleIds.indexOf(vehicleId);
        System.out.println("  Model: " + vehicleModels.get(vehicleIndex));
        System.out.println("  Plate: " + vehiclePlates.get(vehicleIndex));
    }

    static void createRental() {
        if (userIds.isEmpty() || vehicleIds.isEmpty()) {
            System.out.println("You must create at least one user and vehicle before creating a rental.");
            return;
        }

        System.out.println("\n--- Create Rental ---");

        System.out.println("Select user:");
        for (int i = 0; i < userIds.size(); i++) {
            System.out.println((i + 1) + ". " + userNames.get(i));
        }
        int userIndex = Integer.parseInt(scanner.nextLine()) - 1;

        System.out.println("Select vehicle:");
        for (int i = 0; i < vehicleIds.size(); i++) {
            System.out.println((i + 1) + ". " + vehicleModels.get(i));
        }
        int vehicleIndex = Integer.parseInt(scanner.nextLine()) - 1;

        rentalIds.add(rentalCounter++);
        rentalUserIds.add(userIds.get(userIndex));
        rentalVehicleIds.add(vehicleIds.get(vehicleIndex));
        System.out.println("Rental created successfully.");
    }

    static void createUser() {
        System.out.print("\nEnter user name: ");
        String name = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        userIds.add(userCounter++);
        userNames.add(name);
        userPhones.add(phone);

        System.out.println("User created.");
    }

    static void createVehicle() {
        System.out.print("\nEnter vehicle model: ");
        String model = scanner.nextLine();
        System.out.print("Enter plate number: ");
        String plate = scanner.nextLine();

        vehicleIds.add(vehicleCounter++);
        vehicleModels.add(model);
        vehiclePlates.add(plate);

        System.out.println("Vehicle created.");
    }

    static void editRental() {
        System.out.print("\nEnter Rental ID to edit: ");
        int id = Integer.parseInt(scanner.nextLine());
        int index = rentalIds.indexOf(id);
        if (index == -1) {
            System.out.println("Rental not found.");
            return;
        }

        System.out.println("Edit user:");
        for (int i = 0; i < userIds.size(); i++) {
            System.out.println((i + 1) + ". " + userNames.get(i));
        }
        int userIndex = Integer.parseInt(scanner.nextLine()) - 1;

        System.out.println("Edit vehicle:");
        for (int i = 0; i < vehicleIds.size(); i++) {
            System.out.println((i + 1) + ". " + vehicleModels.get(i));
        }
        int vehicleIndex = Integer.parseInt(scanner.nextLine()) - 1;

        rentalUserIds.set(index, userIds.get(userIndex));
        rentalVehicleIds.set(index, vehicleIds.get(vehicleIndex));

        System.out.println("Rental updated.");
    }
}
