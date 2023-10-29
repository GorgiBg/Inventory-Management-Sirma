package entities;

import entities.enums.Category;
import entities.interfaces.*;

import java.math.BigDecimal;

public abstract class AbstractItem implements Item, Categorizable, Breakable, Perishable, Sellable {

    private Category category;
    protected BigDecimal price;
    private boolean breakable;
    private boolean perishable;

    public AbstractItem(Category category, BigDecimal price, boolean breakable, boolean perishable) {
        this.category = category;
        this.price = price;
        this.breakable = breakable;
        this.perishable = perishable;
    }

    public Category getCategory() {
        return category;
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
        return this.toString();
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
    public String toString() {
        return super.toString() + this.category.name();
    }
}


