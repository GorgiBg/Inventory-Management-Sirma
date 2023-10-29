package entities;

import entities.enums.Category;
import entities.interfaces.*;

import java.math.BigDecimal;

public abstract class AbstractItem implements Item, Categorizable, Breakable, Perishable, Sellable {

    Category category;
    protected BigDecimal price;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getItemDetails() {
        return this.toString();
    }

    @Override
    public String toString() {
        return super.toString() + this.category.name();
    }
}


