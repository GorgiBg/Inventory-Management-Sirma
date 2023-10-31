package sirma.main.java.entities;

import sirma.main.java.entities.enums.PaymentMethod;

import java.math.BigDecimal;

public class Payment {
    private final PaymentMethod paymentMethod;
    private BigDecimal amount;

    public Payment(BigDecimal amount, PaymentMethod paymentMethod) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
}

