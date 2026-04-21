package main.java.com.xenia.n11bootcamp.paymentprocessing.strategies;

import java.util.stream.Collectors;

import main.java.com.xenia.n11bootcamp.paymentprocessing.ioc.Inject;
import main.java.com.xenia.n11bootcamp.paymentprocessing.models.PaymentMethod;

public class GenericPaymentStrategy<PM extends PaymentMethod> implements IPaymentStrategy<PM> {
	@Inject
	public GenericPaymentStrategy() {
	}

    @Override
    public boolean makePayment(PM paymentMethod, double amount) {
        var sb = new StringBuilder(
			String.format("A payment of %.2f TL has successfully been made with a " +
				"'%s' payment method.\nDetails of the payment method:\n",
				amount,
				paymentMethod.methodType()
			)
		);
		
		sb.append(paymentMethod.toString()
			.lines()
			.map(line -> "\t" + line)
			.collect(Collectors.joining("\n"))
		);

        System.out.println(sb.toString());
        return true;
    }
}
