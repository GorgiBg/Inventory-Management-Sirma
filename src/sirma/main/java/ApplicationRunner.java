package sirma.main.java;

import sirma.main.java.services.InventoryService;

import java.util.Scanner;

public class ApplicationRunner {
    public static void run()  {
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
                    service.sortItemsByPriceAscending();
                    break;
                case 9:
                    service.sortItemsByPriceDescending();
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
        System.out.println("\n*** Inventory Management System Menu ***");
        System.out.println("1. Add a New Item");
        System.out.println("2. Remove an Item by ID");
        System.out.println("3. Display All Items");
        System.out.println("4. Print Items in Your Order by Category");
        System.out.println("5. Calculate Total Price of Items in Your Order");
        System.out.println("6. Search for an Item by Name");
        System.out.println("7. Search for Items by Category");
        System.out.println("8. Sort Items by Price (Low to High)");
        System.out.println("9. Sort Items by Price (High to Low)");
        System.out.println("10. Place an Order");
        System.out.println("11. Exit");
        System.out.println("Enter your choice (1-11): ");
    }
}