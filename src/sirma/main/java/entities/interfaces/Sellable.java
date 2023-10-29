package sirma.main.java.entities.interfaces;

import java.math.BigDecimal;

public interface Sellable {

    void setItemPrice(BigDecimal price);
    BigDecimal getItemPrice();
}
