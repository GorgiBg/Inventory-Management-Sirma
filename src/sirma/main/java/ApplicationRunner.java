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
                    service.printByCategory();
                    break;
                case 5:
                    service.calculateTotalPrice();
                    break;
                case 6:
                    service.searchItemByName(sc);
                    break;
                case 7:
                    service.searchItemsByCategory(sc);
                    break;
                case 8:
                    service.sortItemsByPriceAscending(sc);
                    break;
                case 9:
                    service.sortItemsByPriceDescending(sc);
                    break;
                case 10:
                    service.placeOrder(sc);
                    break;
                case 11:
                    System.out.println("Have a nice day.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    public static void displayMenu() {
        System.out.println();
        System.out.println("*** Select from the choices below ***");
        System.out.println("1. Add new item");
        System.out.println("2. Remove item by ID");
        System.out.println("3. Display all items");
        System.out.println("4. Print items in your Order by Category");
        System.out.println("5. Calculate price of items in Order");
        System.out.println("6. Search an item by name");
        System.out.println("7. Search items by category");
        System.out.println("8. Sort items by price ascending");
        System.out.println("9. Sort items by price descending");
        System.out.println("10. Place an order");
        System.out.println("11. Exit");
        System.out.println("Enter your choice (1-11): ");
    }
}