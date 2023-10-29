package entities;

import entities.enums.Category;
import entities.enums.Power;

import java.math.BigDecimal;

public class ElectronicItem extends InventoryItem {

    private Power power;

    public ElectronicItem(Category category, BigDecimal price, boolean breakable
        , Power power, int quantity) {
        super(category, price, breakable, false, quantity);
        this.power = power;
    }
}
