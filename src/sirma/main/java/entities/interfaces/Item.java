package entities.interfaces;

import java.math.BigDecimal;

public interface Item {

    String getItemDetails();
    BigDecimal calculateItemValue();
    String getItemDescription();

}
