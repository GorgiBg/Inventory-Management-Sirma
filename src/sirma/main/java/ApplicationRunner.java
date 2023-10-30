package sirma.main.java;

import sirma.main.java.services.InventoryService;

import java.io.IOException;
import java.util.Scanner;

public class ApplicationRunner {
    public static void run() throws IOException {
        Scanner sc = new Scanner(System.in);
        InventoryService service = new InventoryService();

        while (true) {
            displayMenu();
            int choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1:
                    service.addItem(sc);
                    break;
                case 2:
                    service.removeItem(sc);
                    break;
                case 3:
                    service.displayItems();
                    break;
                case 4:
                    service.categorizeItems();
                    break;
                case 5:
                    service.calculateTotalPrice();
                    break;
                case 6:
                    service.placeOrder();
                    break;
                case 7:
                    System.out.println("Have a nice day.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    public static void displayMenu() {
        System.out.println("Welcome to the Inventory Management System");
        System.out.println("1. Add new item");
        System.out.println("2. Remove item by ID");
        System.out.println("3. Display a list of items");
        System.out.println("4. Categorize items");
        System.out.println("5. Calculate price of items");
        System.out.println("6. Place an order");
        System.out.println("7. Exit");
        System.out.print("Enter your choice (1-7): ");
    }
}