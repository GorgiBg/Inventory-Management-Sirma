package sirma.main.java.entities;


import sirma.main.java.entities.enums.Category;
import sirma.main.java.entities.interfaces.*;

import java.math.BigDecimal;

public abstract class AbstractItem implements Item, Categorizable, Breakable, Perishable, Sellable {
    protected String name;

    protected Category category;
    protected BigDecimal price;
    protected int quantity;
    protected boolean breakable;
    protected boolean perishable;

    public AbstractItem(String name, Category category, BigDecimal price, boolean breakable, boolean perishable,
                        int quantity) {
        this.name =name;
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

    public BigDecimal getPrice() {
        return this.price;
    }

    public String getItemDetails() {
        return String.format("This item is %s%n", this.getName()) +
            String.format("This item is of category %s%n",
                this.getCategory()) +
            String.format("This item price is %s%n",
                this.getItemPrice()) +
            String.format("This item available quantity is: %d",
                this.getQuantity());
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isBreakable() {
        return breakable;
    }

    @Override
    public boolean isPerishable() {
        return perishable;
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


