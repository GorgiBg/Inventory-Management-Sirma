package sirma.main.java.entities;


import sirma.main.java.entities.enums.Category;
import sirma.main.java.entities.interfaces.*;

import java.math.BigDecimal;

public abstract class AbstractItem implements Item, Categorizable, Breakable, Perishable, Sellable {

    protected Category category;
    protected BigDecimal price;
    protected int quantity;
    protected boolean breakable;
    protected boolean perishable;

    public AbstractItem(Category category, BigDecimal price, boolean breakable, boolean perishable,
                        int quantity) {
        this.category = category;
        this.price = price;
        this.breakable = breakable;
        this.perishable = perishable;
        this.quantity = quantity;
    }

    public AbstractItem() {
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getItemDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("This item is of category %s%n",
                this.getCategory()))
            .append(String.format("This item price is %s%n",
                this.getItemPrice()))
            .append(String.format("This item available quantity is: %d",
                this.getQuantity()));
        return sb.toString();
    }

    @Override
    public boolean isBreakable() {
        return breakable;
    }

    public void setBreakable(boolean breakable) {
        this.breakable = breakable;
    }

    @Override
    public boolean isPerishable() {
        return perishable;
    }

    public void setPerishable(boolean perishable) {
        this.perishable = perishable;
    }

    @Override
    public BigDecimal calculateItemValue() {
        return this.getItemPrice().multiply(BigDecimal.valueOf(this.quantity));
    }

    public int getQuantity() {
        return this.quantity;
    }

    @Override
    public String toString() {
        return super.toString() + this.category.name();
    }
}


