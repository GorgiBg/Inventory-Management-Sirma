package entities;

import entities.enums.Category;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GroceryItem extends InventoryItem{

    LocalDate expiryDate;
    double weightInKilograms;

    public GroceryItem(Category category, BigDecimal price, boolean breakable, boolean perishable,
                       LocalDate expiryDate, double weightInKilograms) {
        super(category, price, breakable, perishable);
        this.expiryDate = expiryDate;
        this.weightInKilograms = weightInKilograms;
    }
}
