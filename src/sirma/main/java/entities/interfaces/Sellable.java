package sirma.main.java.entities.interfaces;

import java.math.BigDecimal;

public interface Sellable {

    BigDecimal getItemPrice();

    void setItemPrice(BigDecimal price);
}
