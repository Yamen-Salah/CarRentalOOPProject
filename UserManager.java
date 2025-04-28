import java.util.ArrayList;
import java.util.Scanner;
class UserManager {
    private ArrayList<User> users;
    private Scanner scanner;
    private int userCounter;

    UserManager(ArrayList<User> users, Scanner scanner, int userCounter) {
        this.users = users;
        this.scanner = scanner;
        this.userCounter = userCounter;
    }
    int getUserCounter() {
        return userCounter;
    }
    void viewAllUsers() {
        System.out.println("\n--- All Users ---");
        for (User user : users) {
            System.out.println("User ID: " + user.getId() + ", Name: " + user.getName() + ", Phone: " + user.getPhone());
        }
    }
    void createUser() {
        System.out.println("\n--- Create User ---");
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();
        System.out.print("Enter user phone: ");
        String phone = scanner.nextLine();

        User user = new User(userCounter++, name, phone);
        users.add(user);
        System.out.println("User created with ID: " + user.getId());
    }   

    ArrayList<User> getAllUsers() {
        return users;
    }
    
}
