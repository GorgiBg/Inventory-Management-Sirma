package entities.interfaces;

import java.math.BigDecimal;

public interface Breakable {

    boolean isBreakable();
    BigDecimal calculateDamageCost();

}
