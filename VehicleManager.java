import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;
class VehicleManager {
    private ArrayList<Vehicle> vehicles;
    private Scanner scanner;
    private int vehicleCounter;

    VehicleManager(ArrayList<Vehicle> vehicles, Scanner scanner, int vehicleCounter) {
        this.vehicles = vehicles;
        this.scanner = scanner;
        this.vehicleCounter = vehicleCounter;
    }

    int getVehicleCounter() {
        return vehicleCounter;
    }

    void viewAllVehicles() {
        System.out.println("\n--- All Vehicles ---");
        for (Vehicle vehicle : vehicles) {
            System.out.println("Vehicle ID: " + vehicle.getId() + ", Model: " + vehicle.getModel() + ", Plate: " + vehicle.getPlateNumber());
        }
    }

    void createVehicle() {
        System.out.println("\n--- Create Vehicle ---");
        System.out.print("Enter vehicle make: ");
        String make = scanner.nextLine();
        System.out.print("Enter vehicle model: ");
        String model = scanner.nextLine();
        System.out.print("Enter vehicle year (YYYY): ");
        int yearInput = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter vehicle plate number: ");
        String plateNumber = scanner.nextLine();
        System.out.print("Enter price per day: ");
        double pricePerDay = Double.parseDouble(scanner.nextLine());

        Vehicle vehicle = new Vehicle(vehicleCounter++, make, model, Year.of(yearInput), plateNumber, pricePerDay);
        vehicles.add(vehicle);

        System.out.println("Vehicle created with ID: " + vehicle.getId());
    }

    ArrayList<Vehicle> getAllVehicles() {
        return vehicles;
    }    

    void addNewVehicle() {
        System.out.println("\n--- Add New Vehicle ---");
        System.out.print("Enter vehicle make: ");
        String make = scanner.nextLine();
        System.out.print("Enter vehicle model: ");
        String model = scanner.nextLine();
        System.out.print("Enter vehicle year (YYYY): ");
        int yearInput = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter vehicle plate number: ");
        String plateNumber = scanner.nextLine();
        System.out.print("Enter price per day: ");
        double pricePerDay = Double.parseDouble(scanner.nextLine());

        Vehicle vehicle = new Vehicle(vehicleCounter++, make, model, Year.of(yearInput), plateNumber, pricePerDay);
        vehicles.add(vehicle);

        System.out.println("Vehicle added with ID: " + vehicle.getId());
    }
}
