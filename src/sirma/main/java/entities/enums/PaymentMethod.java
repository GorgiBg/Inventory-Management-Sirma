package sirma.main.java.entities.enums;

public enum PaymentMethod {
    CASH(1),
    CREDIT_CARD(2),
    DEBIT_CARD(3),
    PAYPAL(4);

    final int selectNumber;

    PaymentMethod(int selectNumber) {
        this.selectNumber = selectNumber;
    }

    public int getSelectNumber() {
        return selectNumber;
    }
}
