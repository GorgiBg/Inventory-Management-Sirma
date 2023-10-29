package entities;

import entities.enums.Category;
import entities.interfaces.*;

public abstract class AbstractItem implements Item, Categorizable, Breakable, Perishable, Sellable {

    Category category;

    public String getItemDetails() {
        return this.toString();
    }

    @Override
    public String toString() {
        return super.toString() + this.category.name();
    }
}


