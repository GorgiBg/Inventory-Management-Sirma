package entities;

import entities.enums.Category;

import java.math.BigDecimal;

public class FragileItem extends InventoryItem {

    double weightInKilograms;

    public FragileItem(long id, Category category, BigDecimal price, double weightInKilograms,
                       int quantity) {
        super(id, category, price, true, false, quantity);
        this.weightInKilograms = weightInKilograms;
    }
}
