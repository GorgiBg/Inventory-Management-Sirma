package entities;

import entities.enums.Category;

import java.math.BigDecimal;

public class FragileItem extends InventoryItem {

    double weightInKilograms;

    public FragileItem(Category category, BigDecimal price, double weightInKilograms,
                       int quantity) {
        super(category, price, true, false, quantity);
        this.weightInKilograms = weightInKilograms;
    }
}
