import java.util.ArrayList;
import java.util.Scanner;
public class VehicleManager {
    private ArrayList<Vehicle> vehicles;
    private Scanner scanner;
    private int vehicleCounter;

    public VehicleManager(ArrayList<Vehicle> vehicles, Scanner scanner, int vehicleCounter) {
        this.vehicles = vehicles;
        this.scanner = scanner;
        this.vehicleCounter = vehicleCounter;
    }

    public int getVehicleCounter() {
        return vehicleCounter;
    }

    public void viewAllVehicles() {
        System.out.println("\n--- All Vehicles ---");
        for (Vehicle vehicle : vehicles) {
            System.out.println("Vehicle ID: " + vehicle.getId() + ", Model: " + vehicle.getModel() + ", Plate: " + vehicle.getPlateNumber());
        }
    }

    public void createVehicle() {
        System.out.println("\n--- Create Vehicle ---");
        System.out.print("Enter vehicle model: ");
        String model = scanner.nextLine();
        System.out.print("Enter vehicle plate number: ");
        String plateNumber = scanner.nextLine();

        Vehicle vehicle = new Vehicle(vehicleCounter++, model, plateNumber);
        vehicles.add(vehicle);
        System.out.println("Vehicle created with ID: " + vehicle.getId());
    }
}
