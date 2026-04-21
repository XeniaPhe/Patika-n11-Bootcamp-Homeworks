package main.java.com.xenia.n11bootcamp.paymentprocessing.strategies;

import main.java.com.xenia.n11bootcamp.paymentprocessing.models.PaymentMethod;

public interface IPaymentStrategy<PM extends PaymentMethod> {
    boolean makePayment(PM paymentMethod, double amount);
}
