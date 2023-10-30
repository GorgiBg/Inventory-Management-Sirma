package sirma.main.java.entities;

import sirma.main.java.entities.enums.PaymentMethod;

import java.math.BigDecimal;

public class Payment {
    private BigDecimal amount;
    private PaymentMethod paymentMethod;

    public Payment(BigDecimal amount, PaymentMethod paymentMethod) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
}

