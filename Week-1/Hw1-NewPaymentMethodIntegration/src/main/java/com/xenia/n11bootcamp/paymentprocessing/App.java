package main.java.com.xenia.n11bootcamp.paymentprocessing;

import main.java.com.xenia.n11bootcamp.paymentprocessing.ioc.ServiceContainer;
import main.java.com.xenia.n11bootcamp.paymentprocessing.ioc.ServiceRegistry;
import main.java.com.xenia.n11bootcamp.paymentprocessing.ioc.TypeLiteral;
import main.java.com.xenia.n11bootcamp.paymentprocessing.models.BankCardPaymentMethod;
import main.java.com.xenia.n11bootcamp.paymentprocessing.models.CryptoPaymentMethod;
import main.java.com.xenia.n11bootcamp.paymentprocessing.models.PayPalPaymentMethod;
import main.java.com.xenia.n11bootcamp.paymentprocessing.models.PaymentMethod;
import main.java.com.xenia.n11bootcamp.paymentprocessing.persistence.IPaymentRepository;
import main.java.com.xenia.n11bootcamp.paymentprocessing.persistence.MockDB;
import main.java.com.xenia.n11bootcamp.paymentprocessing.strategies.GenericPaymentStrategy;
import main.java.com.xenia.n11bootcamp.paymentprocessing.strategies.IPaymentStrategy;
import main.java.com.xenia.n11bootcamp.paymentprocessing.strategies.PayPalPaymentStrategy;
import main.java.com.xenia.n11bootcamp.paymentprocessing.tui.PaymentTerminalUI;

public class App {
    public static void main(String[] args) throws Exception {
        ServiceRegistry registry = new ServiceRegistry();
        configureServices(registry);
        ServiceContainer services = registry.build();

        var paymentMethods = services.get(IPaymentRepository.class).paymentMethods();

        paymentMethods.add(
            new BankCardPaymentMethod.Builder()
            .methodName("Yapıkredi Debit Card")
            .cardHolderName("John Doe")
            .cardNumber("1234 5678 9101 1121")
            .expiryMonth(7)
            .expiryYear(2029)
            .cvv(567)
            .build()
        );

        paymentMethods.add(
            new CryptoPaymentMethod.Builder()
            .methodName("BTC wallet")
            .walletAddress("bf1qxy2jkpygjrsqtzq2m0ryf1238p83kkfjhx0wlh")
            .network("Bitcoin")
            .currency("BTC")
            .build()
        );

        paymentMethods.add(
            new PayPalPaymentMethod.Builder()
            .methodName("PayPal Account")
            .payPalEmail("fake.user@paypal.com")
            .payerId("PAYER-9Q2L3J5X8M")
            .build()
        );

        services.get(PaymentTerminalUI.class).start();
    }

    private static void configureServices(ServiceRegistry registry) {
        registry.addTransient(IPaymentRepository.class, MockDB.class);
        
        registry.addTransient(
            new TypeLiteral<IPaymentStrategy<PaymentMethod>>() {},
            new TypeLiteral<GenericPaymentStrategy<PaymentMethod>>() {}
        );

        registry.addTransient(
            new TypeLiteral<GenericPaymentStrategy<PayPalPaymentMethod>>() {},
            PayPalPaymentStrategy.class
        );
    }
}
