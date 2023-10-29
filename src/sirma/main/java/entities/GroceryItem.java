package entities;

import entities.enums.Category;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GroceryItem extends InventoryItem {

    private LocalDate expiryDate;
    private double weightInKilograms;

    public GroceryItem(long id, Category category, BigDecimal price, boolean breakable,
                       LocalDate expiryDate, double weightInKilograms, int quantity) {
        super(id, category, price, breakable, true, quantity);
        this.expiryDate = expiryDate;
        this.weightInKilograms = weightInKilograms;
    }

    public BigDecimal calculateItemValue() {
        return this.getItemPrice().multiply(BigDecimal.valueOf(this.weightInKilograms));
    }


}
