import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class RentalManager {
    private ArrayList<Rentals> rentals;
    private ArrayList<User> users;
    private ArrayList<Vehicle> vehicles;
    private Scanner scanner;
    private int rentalCounter;
    public void rentalManager(ArrayList<Rentals> rentals, ArrayList<User> users, ArrayList<Vehicle> vehicles, Scanner scanner, int rentalCounter) {
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
    
        System.out.print("Enter rental start date: ");
        String startDate = scanner.nextLine();
        System.out.print("Enter rental end date: ");
        String endDate = scanner.nextLine();
    
        Rentals rental = new Rentals(rentalCounter++, selectedUser.getId(), selectedVehicle.getId(), startDate, endDate, true);
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
                System.out.println("  Model: " + vehicle.getModel());
                System.out.println("  Plate: " + vehicle.getPlateNumber());
                break;
            }
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
}
