package sirma.main.java.entities;


import sirma.main.java.entities.enums.Category;
import sirma.main.java.entities.enums.Power;

import java.math.BigDecimal;

public class ElectronicItem extends InventoryItem {


    private final Power power;

    public ElectronicItem(long id, Category category, BigDecimal price, Power power, int quantity) {
        super(id, category, price, true, false, quantity);
        this.power = power;
    }


}
