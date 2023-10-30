package sirma.main.java.services;

import sirma.main.java.constants.StringConstants;
import sirma.main.java.entities.InventoryItem;
import sirma.main.java.entities.enums.Category;
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
        if (currentItem == null) {
            throw new IllegalArgumentException("Item with ID " + id + " does not exist.");
        }
        if (currentItem.getQuantity()<quantity) {
            throw new IllegalArgumentException("Not enough quantity available for item with ID " + id);
        }
        yourItems.put(id, quantity);
    }

    public void removeItem(Scanner sc) {
        System.out.println("Please enter id of item.");
        int id = Integer.parseInt(sc.nextLine().trim());
        if (yourItems.isEmpty() || !yourItems.containsKey(id)) {
            System.out.printf("There is no item with id %d.%n", id);
            return;
        }
        yourItems.remove(id);
    }

    public void displayItems() {
        System.out.println("This is the list of all items.");
        for (InventoryItem item : allItems) {
            System.out.println(item.getItemDetails());
        }
    }

    // shows your items separated by category, you get items from one category first then the others
    public void categorizeItems() {
        if (yourItems.isEmpty()) {
            System.out.println("You still have no items in your cart.");
            return;
        }
        Map<Category, List<InventoryItem>> categorizedItems = yourItems.entrySet().stream()
            .map(entry -> {
                int itemId = entry.getKey();
                int quantity = entry.getValue();
                InventoryItem item = allItems.get(itemId);
                return new AbstractMap.SimpleEntry<>(item.getCategory(), item);
            })
            .collect(Collectors.groupingBy(Map.Entry::getKey,
                Collectors.mapping(Map.Entry::getValue, Collectors.toList())));

        categorizedItems.forEach((category, items) -> {
            System.out.println("Category: " + category);
            items.forEach(item -> System.out.println(item.getItemDetails()));
        });
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

    public void placeOrder() {

    }


    public List<InventoryItem> getAllItems (String filename) throws IOException {
        return MyObjectMapper.loadItemsFromJson(filename);
    }
}
