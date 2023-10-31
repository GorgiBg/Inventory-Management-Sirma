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
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("This item is %s%n", this.getName()))
            .append(String.format("This item is of category %s%n",
                this.getItemCategory()))
            .append(String.format("This item price is %s%n",
                this.getItemPrice()))
            .append(String.format("This item available quantity is: %d",
                this.getQuantity()));

        System.out.println(sb);
    }

    @Override
    public String getItemDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("This item is %s%n", this.getName()))
            .append(String.format("This item id is %d%n", this.getId()))
            .append(String.format("This item is of category %s%n",
                this.getCategory()))
            .append(String.format("This item price is %s%n",
                this.getItemPrice()))
            .append(String.format("This item available quantity is: %d%n",
                this.getQuantity()));
        return sb.toString();
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
