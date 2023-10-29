package entities;

import entities.enums.Category;

import java.math.BigDecimal;

public class FragileItem extends InventoryItem{

    double weightInKilograms;

    public FragileItem(Category category, BigDecimal price, boolean breakable, boolean perishable,
                       double weightInKilograms) {
        super(category, price, breakable, perishable);
        this.weightInKilograms = weightInKilograms;
    }
}
