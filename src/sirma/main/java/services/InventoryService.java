package sirma.main.java.services;

import sirma.main.java.constants.StringConstants;
import sirma.main.java.entities.InventoryItem;
import sirma.main.java.entities.Order;
import sirma.main.java.entities.Payment;
import sirma.main.java.entities.enums.Category;
import sirma.main.java.entities.enums.PaymentMethod;
import sirma.main.java.utility.MyObjectMapper;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class InventoryService {

    // all items from DB with value quantity of item
    private Map<InventoryItem, Integer> databaseItems;
    // Order holds map of items and Payment
    private Order order;

    public InventoryService() {
        this.databaseItems = getAllItems(StringConstants.DATABASE_FILE_NAME);
        this.order = new Order(databaseItems);
    }

    private static void printItemsByCategory(Map<Category, List<InventoryItem>> categorizedItems) {
        categorizedItems.forEach((category, items) -> {
            System.out.println("Category: " + category);
            items.forEach(item -> System.out.println(item.getItemDetails()));
        });
    }

    private static PaymentMethod getPaymentMethod(int selected) {
        PaymentMethod paymentMethod = null;
        for (PaymentMethod value : PaymentMethod.values()) {
            if (value.getSelectNumber() == selected) {
                paymentMethod = value;
                break;
            }
        }
        return paymentMethod;
    }

    // get items from DB(json) using my custom ObjectMapper and add to MAP<item, quantity>
    public Map<InventoryItem, Integer> getAllItems(String filename) {
        List<InventoryItem> items = null;
        items = MyObjectMapper.loadItemsFromJson(filename);

        Map<InventoryItem, Integer> dbItems = new HashMap<>();
        for (InventoryItem item : Objects.requireNonNull(items)) {
            dbItems.put(item, item.getQuantity());
        }

        return dbItems;
    }

    public void addItem(Scanner sc) {
        System.out.println(StringConstants.ENTER_ID);
        int id = Integer.parseInt(sc.nextLine().trim());
        System.out.println("Please enter quantity.");
        int quantity = Integer.parseInt(sc.nextLine().trim());
        InventoryItem currentItem = findItemById(id);
        Integer orderedQuantity = checkIfQuantityIsValid(currentItem, quantity, id);
        if (orderedQuantity == null) return;
        // Add the item if not present in the order and update the selected quantity
        order.getItems().put(currentItem, orderedQuantity + quantity);
        System.out.printf("Item with id: %d and quantity: %d added to your Order%n" +
            "now you have %d quantity in your Order%n", id, quantity, order.getItemQuantity(currentItem));
        databaseItems.put(currentItem, databaseItems.get(currentItem) - quantity);
    }

    private Integer checkIfQuantityIsValid(InventoryItem currentItem, int quantity, int id) {
        if (currentItem.getQuantity() < quantity) {
            System.out.printf("Not enough quantity of item with id %d in the database.%n", id);
            return null;
        }
        int orderedQuantity = order.getItems().getOrDefault(currentItem, 0);
        if (currentItem.getQuantity() < orderedQuantity + quantity) {
            System.out.printf("Not enough quantity of item with id %d.%n", id);
            return null;
        }
        return orderedQuantity;
    }

    private InventoryItem findItemById(int id) {
        for (Map.Entry<InventoryItem, Integer> entry : databaseItems.entrySet()) {
            if (entry.getKey().getId() == id) {
                return entry.getKey();
            }
        }
        throw new NoSuchElementException(StringConstants.NO_SUCH_ELEMENT_EXCEPTION_MESSAGE);
    }

    public void removeItem(Scanner sc) {
        System.out.println(StringConstants.ENTER_ID);
        int id = Integer.parseInt(sc.nextLine().trim());
        if (checkIfPresent(id)) return;
        order.getItems().remove(findItemById(id));
    }

    private boolean checkIfPresent(int id) {
        if (order.getItems().isEmpty() || !order.getItems().containsKey(findItemById(id))) {
            System.out.printf("There is no item with id %d.%n", id);
            return true;
        }
        return false;
    }

    public void displayItems() {
        System.out.println(StringConstants.ALL_ITEMS_DISPLAY);
        for (InventoryItem item : databaseItems.keySet()) {
            System.out.println(item.getItemDetails());
        }
    }

    public void printByCategory() {
        if (checkIfEmpty()) return;
        Map<Category, List<InventoryItem>> categorizedItems = getCategorizedMap();
        printItemsByCategory(categorizedItems);
    }

    private Map<Category, List<InventoryItem>> getCategorizedMap() {
        return order.getItems().keySet().stream()
            .map(item -> new AbstractMap.SimpleEntry<>(item.getCategory(), item))
            .collect(Collectors.groupingBy(Map.Entry::getKey,
                Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }

    private boolean checkIfEmpty() {
        if (order.getItems().isEmpty()) {
            System.out.println(StringConstants.EMPTY_CART);
            return true;
        }
        return false;
    }

    public void calculateTotalPrice() {
        BigDecimal totalPrice = BigDecimal.valueOf(order.getItems().entrySet().stream()
            .mapToDouble(entry -> {
                InventoryItem item = entry.getKey();
                return item.getPrice().doubleValue() * entry.getValue();
            })
            .sum());

        System.out.println("Total Price: " + totalPrice);
    }

    public void placeOrder(Scanner sc) {

        System.out.println(StringConstants.ENTER_PAYMENT_METHOD);
        int selected = Integer.parseInt(sc.nextLine().trim());
        PaymentMethod paymentMethod = getPaymentMethod(selected);

        if (paymentMethod == null) {
            System.out.println(StringConstants.INVALID_METHOD);
            return;
        }

        Payment payment = new Payment(BigDecimal.ZERO, paymentMethod);
        BigDecimal orderTotal = processOrder(payment);
        System.out.printf("Congratulations, your order is complete! The sum is %s%n", orderTotal);
        System.out.println(StringConstants.NICE_DAY);
        System.exit(0);
    }

    private BigDecimal processOrder(Payment payment) {
        order.setPayment(payment);
        BigDecimal orderTotal = order.calculateOrderTotal();
        payment.setAmount(orderTotal);
        order.setPayment(payment);
        order.processPayment(payment);
        return orderTotal;
    }

    public void searchItemByName(Scanner sc) {
        System.out.println("Enter the name of the product:");
        String name = sc.nextLine().trim();
        InventoryItem item = databaseItems.keySet().stream()
            .filter(e -> e.getName().equalsIgnoreCase(name)).toList().get(0);
        System.out.println(item.getItemDetails());
    }

    public void searchItemsByCategory(Scanner sc) {
        System.out.println(StringConstants.ENTER_CATEGORY);
        String category = sc.nextLine().trim();
        List<InventoryItem> selectedItems = databaseItems.keySet().stream()
            .filter(item -> Objects.equals(item.getCategory().getDisplayName(), category)).toList();
        for (InventoryItem item : selectedItems) {
            System.out.println(item.getItemDetails());
        }
    }

    public void sortItemsByPriceAscending() {
        databaseItems.keySet().stream()
            .sorted((Comparator.comparing(InventoryItem::getItemPrice)))
            .forEach(item -> System.out.println(item.getItemDetails()));
    }

    public void sortItemsByPriceDescending() {
        databaseItems.keySet().stream()
            .sorted((Comparator.comparing(InventoryItem::getItemPrice).reversed()))
            .forEach(item -> System.out.println(item.getItemDetails()));
    }
}

