package sirma.main.java.entities;

import sirma.main.java.entities.enums.PaymentMethod;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private Map<InventoryItem, Integer> items;
    private Map<InventoryItem, Integer> dbItems;
    private Payment payment;

    public Order(Map<InventoryItem, Integer> dbItems) {
        this.items = new HashMap<>();
        this.dbItems = dbItems;
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
        BigDecimal paymentAmount = payment.getAmount();
        PaymentMethod paymentMethod = payment.getPaymentMethod();
        System.out.printf("You just payed %.2f via %s%n", paymentAmount, paymentMethod);
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public int getItemQuantity(InventoryItem item) {
        return items.getOrDefault(item, 0);
    }
}