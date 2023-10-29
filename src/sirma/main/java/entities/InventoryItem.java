package entities;

import java.math.BigDecimal;

public class InventoryItem extends AbstractItem {

    private String id;
    private int quantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean isBreakable() {
        return false;
    }

    @Override
    public boolean isBroken() {
        return false;
    }

    @Override
    public Category getItemCategory() {
        return null;
    }

    @Override
    public Category setItemCategory() {
        return null;
    }

    @Override
    public double calculateItemValue() {
        return 0;
    }

    @Override
    public String getItemDescription() {
        return null;
    }

    @Override
    public boolean isPerishable() {
        return false;
    }

    @Override
    public boolean isPerished() {
        return false;
    }

    @Override
    public BigDecimal setItemPrice() {
        return null;
    }

    @Override
    public BigDecimal getItemPrice() {
        return null;
    }
}
