package entities.interfaces;

import java.math.BigDecimal;

public interface Perishable {
    boolean isPerishable();
    BigDecimal calculatePerishCosts();
}
