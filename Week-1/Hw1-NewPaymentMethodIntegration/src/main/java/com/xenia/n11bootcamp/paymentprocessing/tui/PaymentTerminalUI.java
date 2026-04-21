package main.java.com.xenia.n11bootcamp.paymentprocessing.tui;

import java.util.Scanner;

import main.java.com.xenia.n11bootcamp.paymentprocessing.ioc.Inject;
import main.java.com.xenia.n11bootcamp.paymentprocessing.models.PaymentMethod;
import main.java.com.xenia.n11bootcamp.paymentprocessing.persistence.IPaymentRepository;
import main.java.com.xenia.n11bootcamp.paymentprocessing.strategies.IPaymentStrategy;

public class PaymentTerminalUI {
	IPaymentRepository paymentRepository;
	IPaymentStrategy<PaymentMethod> paymentStrategy;
	Scanner scanner;

	@Inject
	public PaymentTerminalUI(
		IPaymentRepository paymentRepository,
		IPaymentStrategy<PaymentMethod> paymentStrategy) {

		this.paymentRepository = paymentRepository;
		this.paymentStrategy = paymentStrategy;
		this.scanner = new Scanner(System.in);
	}
	
	private boolean showMenu() {
    	System.out.println("1 - Make Payment");
    	System.out.println("2 - List All Payment Methods\n");

    	while (true) {
    	    var input = prompt("Select action (q = quit): ");

    	    switch (input) {
    	        case "1":
    	            return makePayment();
    	        case "2":
    	            return listAllPaymentMethods();
    	        case "q":
    	            return false;
    	        default:
    	            System.out.println("Invalid selection");
    	    }
    	}
	}

	private boolean listAllPaymentMethods() {
    	System.out.println();
    	var paymentMethods = paymentRepository.paymentMethods();

    	for (int i = 0; i < paymentMethods.size(); i++) {
    	    var method = paymentMethods.get(i);
			System.out.println(method.toString());
			System.out.println();
    	}

		var response = prompt("Press m for menu, or anything else to quit: ");
		if (response.equals("m")) {
			return true;
		}
		
		return false;
	}

	private boolean makePayment() {
		System.out.println();
    	double amount;

    	while (true) {
    	    var response = prompt("Payment amount (m = menu, q = quit): ");

    	    if (response.equals("m")) {
    	        return true;
    	    }

    	    if (response.equals("q")) {
    	        return false;
    	    }

    	    try {
    	        amount = Double.parseDouble(response);

    	        if (amount <= 0) {
    	            System.out.println("Amount must be > 0");
    	            continue;
    	        }

    	        break;

    	    } catch (NumberFormatException e) {
    	        System.out.println("Invalid input");
    	    }
    	}

		System.out.println();
    	var paymentMethods = paymentRepository.paymentMethods();

    	for (int i = 0; i < paymentMethods.size(); i++) {
    	    System.out.printf("%d - %s\n", i + 1, paymentMethods.get(i).name);
    	}

		System.out.println();

    	while (true) {
    	    var input = prompt("Select payment method (m = menu, q = quit): ");

    	    if (input.equals("m")) {
    	        return true;
    	    }

    	    if (input.equals("q")) {
				return false;
    	    }

    	    try {
    	        int index = Integer.parseInt(input) - 1;

    	        if (index < 0 || index >= paymentMethods.size()) {
    	            System.out.println("Invalid selection");
    	            continue;
    	        }

    	        var selected = paymentMethods.get(index);
    	        System.out.printf("\nProcessing payment of %.2f TL via: %s\n\n", amount, selected.name);
				paymentStrategy.makePayment(selected, amount);
				
				var response = prompt("\nPress m for menu, or anything else to quit: ");
				if (response.equals("m")) {
					return true;
				}

				return false;
    	    } catch (NumberFormatException e) {
    	        System.out.println("Enter a valid number");
    	    }
    	}
	}

	public void start() {
		while (showMenu()) {
			System.out.println();
		}
	}

	private String prompt(String msg) {
		System.out.print(msg);
    	return scanner.nextLine().trim().toLowerCase();
	}
}
