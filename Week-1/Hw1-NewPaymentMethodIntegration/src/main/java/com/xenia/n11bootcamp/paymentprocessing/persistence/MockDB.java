package main.java.com.xenia.n11bootcamp.paymentprocessing.persistence;

import java.util.ArrayList;
import java.util.List;

import main.java.com.xenia.n11bootcamp.paymentprocessing.ioc.Inject;
import main.java.com.xenia.n11bootcamp.paymentprocessing.models.PaymentMethod;

public class MockDB implements IPaymentRepository {
    private static final List<PaymentMethod> paymentMethods = new ArrayList<>();

    @Inject
    public MockDB() {
    }

    @Override
    public List<PaymentMethod> paymentMethods() {
        return paymentMethods;
    }
}
