package sirma.main.java.entities;

import java.util.Scanner;

public class ApplicationRunner {
    public static void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            displayMenu();
            int choice = Integer.parseInt(sc.nextLine().trim());


            switch (choice) {
                case 1:
                    inventorySystem.addItem();
                    break;
                case 2:
                    inventorySystem.removeItem();
                    break;
                case 3:
                    inventorySystem.displayItems();
                    break;
                case 4:
                    inventorySystem.categorizeItems();
                    break;
                case 5:
                    inventorySystem.placeOrder();
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    public static void displayMenu() {
        System.out.println("Welcome to the Inventory Management System");
        System.out.println("1. Add an item");
        System.out.println("2. Remove an item by ID");
        System.out.println("3. Display a list of items");
        System.out.println("4. Categorize items");
        System.out.println("5. Place an order");
        System.out.println("6. Exit");
        System.out.print("Enter your choice (1-6): ");
    }
}
    }
}
