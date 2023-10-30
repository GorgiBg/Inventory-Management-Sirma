package sirma.main.java.services;

import sirma.main.java.constants.StringConstants;
import sirma.main.java.entities.InventoryItem;
import sirma.main.java.entities.Order;
import sirma.main.java.entities.Payment;
import sirma.main.java.entities.enums.Category;
import sirma.main.java.entities.enums.PaymentMethod;
import sirma.main.java.utility.MyObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class InventoryService {

    // Map yourItems holds id of item and quantity
    private Map<Integer, Integer> yourItems;
    private List<InventoryItem> allItems = getAllItems(StringConstants.DATABASE_FILE_NAME);

    public InventoryService() throws IOException {
        this.yourItems = new HashMap<>();
    }

    public void addItem(Scanner sc) {
        System.out.println("Please enter id of item.");
        int id = Integer.parseInt(sc.nextLine().trim());
        System.out.println("Please enter quantity.");
        int quantity = Integer.parseInt(sc.nextLine().trim());
        InventoryItem currentItem = allItems.get(id - 1);
        validityCheck(currentItem, id, quantity);
        yourItems.putIfAbsent(id, 0);
        yourItems.put(id, yourItems.get(id) + quantity);
    }

    private static void validityCheck(InventoryItem currentItem, int id, int quantity) {
        if (currentItem == null) {
            throw new IllegalArgumentException("Item with ID " + id + " does not exist.");
        }
        if (currentItem.getQuantity()< quantity) {
            throw new IllegalArgumentException("Not enough quantity available for item with ID " + id);
        }
    }

    public void removeItem(Scanner sc) {
        System.out.println("Please enter id of item.");
        int id = Integer.parseInt(sc.nextLine().trim());
        if (checkIfPresent(id)) return;
        yourItems.remove(id);
    }

    private boolean checkIfPresent(int id) {
        if (yourItems.isEmpty() || !yourItems.containsKey(id)) {
            System.out.printf("There is no item with id %d.%n", id);
            return true;
        }
        return false;
    }

    public void displayItems() {
        System.out.println("This is the list of all items.");
        for (InventoryItem item : allItems) {
            System.out.println(item.getItemDetails());
        }
    }

    // shows your items separated by category, you get items from one category first then the others
    public void categorizeItems() {
        if (checkIfEmpty()) return;
        Map<Category, List<InventoryItem>> categorizedItems = getCategorizedMap();
        printItemsByCategory(categorizedItems);
    }

    private static void printItemsByCategory(Map<Category, List<InventoryItem>> categorizedItems) {
        categorizedItems.forEach((category, items) -> {
            System.out.println("Category: " + category);
            items.forEach(item -> System.out.println(item.getItemDetails()));
        });
    }

    private Map<Category, List<InventoryItem>> getCategorizedMap() {
        return yourItems.entrySet().stream()
            .map(entry -> {
                int itemId = entry.getKey();
                int quantity = entry.getValue();
                InventoryItem item = allItems.get(itemId);
                return new AbstractMap.SimpleEntry<>(item.getCategory(), item);
            })
            .collect(Collectors.groupingBy(Map.Entry::getKey,
                Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }

    private boolean checkIfEmpty() {
        if (yourItems.isEmpty()) {
            System.out.println("You still have no items in your cart.");
            return true;
        }
        return false;
    }

    public void calculateTotalPrice() {
        BigDecimal totalPrice = BigDecimal.valueOf(yourItems.keySet().stream()
            .mapToDouble(itemId -> {
                InventoryItem currentItem = allItems.get(itemId - 1);
                return currentItem.getPrice().doubleValue() * yourItems.get(itemId);
            })
            .sum());

        System.out.println("Total Price: " + totalPrice);
    }

    public void placeOrder(Scanner sc) {
        Map<InventoryItem, Integer> orderItems = extractItemsForOrder();
        System.out.println("Enter your payment method");
        String paymentMethod = sc.nextLine().trim().toUpperCase();
        Payment payment = new Payment(BigDecimal.ZERO, PaymentMethod.valueOf(paymentMethod));
        Order order = new Order(orderItems, payment);
        boolean itemsInStock = true;
        for (Map.Entry<InventoryItem, Integer> entry : orderItems.entrySet()) {
            InventoryItem orderedItem = entry.getKey();
            int stockQuantity = allItems.get((int) (orderedItem.getId() - 1)).getQuantity();
            long itemId = entry.getKey().getId();
            int orderedQuantity = entry.getValue();
            int availableQuantity = yourItems.get((int) itemId);

            // Check if there are enough items in stock
            if (stockQuantity < orderedQuantity) {
                System.out.println("Not enough stock for item with ID " + itemId);
                itemsInStock = false;
                continue;
            }

            // Reduce item quantity in cart
            yourItems.put((int) itemId, availableQuantity - orderedQuantity);
            orderedItem.setQuantity(stockQuantity - orderedQuantity);
        }
        if (itemsInStock) {
            BigDecimal orderTotal = order.calculateOrderTotal();

            // Process the payment
            payment.setAmount(orderTotal);
            order.setPayment(payment);
            order.processPayment(payment);

            System.out.printf("Order placed successfully! The sum is %s%n", orderTotal);
        } else {
            System.out.println("Order not placed due to insufficient quantity.");
        }
    }

    private Map<InventoryItem, Integer> extractItemsForOrder() {
        Map<InventoryItem, Integer> itemQuantityMap = new HashMap<>();

        for (InventoryItem item : allItems) {
            int itemId = (int) item.getId();
            if (yourItems.containsKey(itemId)) {
                int quantity = yourItems.get(itemId);
                itemQuantityMap.put(item, quantity);
            }
        }

        return itemQuantityMap;
    }

    private boolean isItemInStock(int itemId) {
        return yourItems.containsKey(itemId);

    }

    // get items from DB(json) using my custom ObjectMapper
    public List<InventoryItem> getAllItems (String filename) throws IOException {
        return MyObjectMapper.loadItemsFromJson(filename);
    }
}
