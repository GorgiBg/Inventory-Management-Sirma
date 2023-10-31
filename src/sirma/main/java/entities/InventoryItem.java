package sirma.main.java.entities;

import sirma.main.java.entities.enums.Category;

import java.math.BigDecimal;

public class InventoryItem extends AbstractItem {

    private long id;

    public InventoryItem(long id, String name, Category category, BigDecimal price, boolean breakable, boolean perishable
        , int quantity) {
        super(name, category, price, breakable, perishable, quantity);
        this.id = id;
    }

    public InventoryItem() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public BigDecimal calculateDamageCost() {
        return BigDecimal.valueOf(0);
    }


    @Override
    public String getItemCategory() {
        return this.getCategory().getDisplayName();
    }

    @Override
    public void setItemCategory(Category category) {
        this.category = category;
    }


    @Override
    public void displayItemDescription() {
        System.out.printf("Item: %s%n", getName());
        System.out.printf("Category: %s%n", getItemCategory());
        System.out.printf("Price: $%.2f%n", getItemPrice());
        System.out.printf("Available Quantity: %d%n", getQuantity());
    }

    @Override
    public String getItemDetails() {
        return String.format(
            "Item: %s%nID: %d%nCategory: %s%nPrice: $%.2f%nAvailable Quantity: %d%n",
            getName(), getId(), getCategory(), getItemPrice(), getQuantity()
        );
    }

    @Override
    public BigDecimal getItemPrice() {
        return super.getPrice();
    }

    @Override
    public void setItemPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public BigDecimal calculatePerishCosts() {
        return BigDecimal.valueOf(0);
    }
}
