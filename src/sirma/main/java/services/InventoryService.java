package sirma.main.java.services;

import sirma.main.java.entities.InventoryItem;
import sirma.main.java.entities.enums.Category;
import sirma.main.java.utility.MyObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InventoryService {

    private List<InventoryItem> yourItems;
    private static List<InventoryItem> allItems;

    static {
        try {
            allItems = MyObjectMapper.loadItemsFromJson("item_database.json");
        } catch (IOException e) {
            System.out.println("Can`t read items from Database!");
        }
    }

    public InventoryService() throws IOException {
        this.yourItems = new ArrayList<>();
    }

    public void addItem(int id) throws IOException {
       yourItems.add(allItems.get(id));
    }

    public void removeItem(int id) {
        InventoryItem item = allItems.get(id);
        if (!yourItems.isEmpty()) {
            yourItems.remove(item);
        }
    }

    public void displayItems() throws IOException {
        List<InventoryItem> allItems = MyObjectMapper.loadItemsFromJson("item_database.json");
        for (InventoryItem item : allItems) {
            System.out.println(item.getItemDetails());
        }
    }

    public void categorizeItems() {
        Map<Category, List<InventoryItem>> categorizedItems = yourItems.stream()
            .collect(Collectors.groupingBy(InventoryItem::getCategory));

        categorizedItems.forEach((category, items) -> {
            System.out.println("Category: " + category);
            items.forEach(System.out::println);
        });
    }

    public void calculateTotalPrice() {
        double totalPrice = yourItems.stream()
            .mapToDouble(item -> item.calculateItemValue().doubleValue())
            .sum();

        System.out.println("Total Price: " + BigDecimal.valueOf(totalPrice));
    }

    public void placeOrder() {

    }
}
