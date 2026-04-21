package main.java.com.xenia.n11bootcamp.paymentprocessing.persistence;

import java.util.List;

import main.java.com.xenia.n11bootcamp.paymentprocessing.models.PaymentMethod;

public interface IPaymentRepository {
    List<PaymentMethod> paymentMethods();
}
