package main.java.com.xenia.n11bootcamp.paymentprocessing.strategies;

import main.java.com.xenia.n11bootcamp.paymentprocessing.ioc.Inject;
import main.java.com.xenia.n11bootcamp.paymentprocessing.models.PayPalPaymentMethod;

public class PayPalPaymentStrategy extends GenericPaymentStrategy<PayPalPaymentMethod> {

    @Inject
    public PayPalPaymentStrategy() {
    }

    @Override
    public boolean makePayment(PayPalPaymentMethod paymentMethod, double amount) {
        System.out.println("Doing PayPal-specific work");
        return super.makePayment(paymentMethod, amount);
    }
}
