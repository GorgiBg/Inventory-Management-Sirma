package entities;

import entities.enums.Category;

import java.math.BigDecimal;

public class InventoryItem extends AbstractItem {

    private String id;

    public InventoryItem(Category category, BigDecimal price, boolean breakable, boolean perishable
    , int quantity) {
        super(category, price, breakable, perishable, quantity);
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
    public String getItemCategory() {
        return this.getCategory().getDisplayName();
    }

    @Override
    public void setItemCategory(Category category) {
        this.category = category;
    }

    @Override
    public BigDecimal calculateItemValue() {
        BigDecimal multiply = this.getPrice().multiply(BigDecimal.valueOf(this.quantity));
        return multiply;
    }

    @Override
    public void displayItemDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("This item is of category %s%n",
            this.getItemCategory()))
            .append(String.format("This item price is %s%n",
                this.getItemPrice()))
            .append(String.format("This item available quantity is: %d",
                this.getQuantity()));

        System.out.println(sb);
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
    public void setItemPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public BigDecimal getItemPrice() {
        return super.getPrice();
    }

}
