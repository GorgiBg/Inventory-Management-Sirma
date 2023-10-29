package entities;

import entities.interfaces.*;

public abstract class AbstractItem implements Item, Categorizable, Breakable, Perishable, Sellable {

    Category category;

    public String getItemDetails() {
        return ;
        // TODO;
    }
}


