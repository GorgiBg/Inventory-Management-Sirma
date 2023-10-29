package entities;

import entities.enums.Category;
import entities.enums.Power;

import java.math.BigDecimal;

public class ElectronicItem extends InventoryItem{

     private Power power;

     public ElectronicItem(Category category, BigDecimal price, boolean breakable, boolean perishable
     , Power power) {
          super(category, price, breakable, perishable);
          this.power = power;
     }
}
