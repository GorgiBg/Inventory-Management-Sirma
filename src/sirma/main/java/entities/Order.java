package sirma.main.java.entities;

import sirma.main.java.entities.enums.PaymentMethod;

import java.math.BigDecimal;
import java.util.Map;

public class Order {
    private Map<InventoryItem, Integer> items;

    public Order(Map<InventoryItem, Integer> items) {
        this.items = items;
    }

    public Map<InventoryItem, Integer> getItems() {
        return items;
    }

    public BigDecimal calculateOrderTotal() {

        BigDecimal orderTotal = BigDecimal.ZERO;
        for (Map.Entry<InventoryItem, Integer> entry : items.entrySet()) {
            BigDecimal itemPrice = entry.getKey().getItemPrice();
            int itemQuantity = entry.getValue();
            BigDecimal itemTotal = itemPrice.multiply(BigDecimal.valueOf(itemQuantity));
            orderTotal = orderTotal.add(itemTotal);
        }

        return orderTotal;
    }

    public void processPayment(Payment payment) {

    }

    public PaymentMethod getPaymentMethod() {
        return null;
    }
}