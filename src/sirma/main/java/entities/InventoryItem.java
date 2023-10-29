package entities;

import entities.enums.Category;

import java.math.BigDecimal;

public class InventoryItem extends AbstractItem {

    private String id;
    private int quantity;

    public InventoryItem(Category category, BigDecimal price, boolean breakable, boolean perishable) {
        super(category, price, breakable, perishable);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean isBreakable() {
        return false;
    }

    @Override
    public BigDecimal calculateDamageCost() {
        return null;
    }

    @Override
    public Category getItemCategory() {
        return null;
    }

    @Override
    public Category setItemCategory(Category category) {
        return null;
    }

    @Override
    public BigDecimal calculateItemValue() {
        return this.getItemPrice().multiply(BigDecimal.valueOf(this.quantity));
    }

    @Override
    public String getItemDescription() {
        StringBuilder sb= new StringBuilder();
        sb.append(String.format("This item is of category %s%n",
            this.getItemCategory().getDisplayName()))
            .append(String.format("This item price is %s%n",
                this.getItemPrice()))
            .append(String.format("This item available quantity is: %d",
                this.getQuantity()));

        return sb.toString();
    }

    @Override
    public boolean isPerishable() {
        return false;
    }

    @Override
    public BigDecimal calculatePerishCosts() {
        return null;
    }

    @Override
    public BigDecimal setItemPrice() {
        return null;
    }

    @Override
    public BigDecimal getItemPrice() {
        return null;
    }
}
