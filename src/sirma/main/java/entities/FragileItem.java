package sirma.main.java.entities;

import sirma.main.java.entities.enums.Category;

import java.math.BigDecimal;

public class FragileItem extends InventoryItem {

    private double weightInKilograms;

    public FragileItem(long id, String name, Category category, BigDecimal price, double weightInKilograms,
                       int quantity) {
        super(id, name, category, price, true, false, quantity);
        this.weightInKilograms = weightInKilograms;
    }
}
