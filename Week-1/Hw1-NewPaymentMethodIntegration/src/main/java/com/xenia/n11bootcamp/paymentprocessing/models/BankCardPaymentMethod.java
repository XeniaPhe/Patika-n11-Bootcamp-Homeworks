package main.java.com.xenia.n11bootcamp.paymentprocessing.models;

public class BankCardPaymentMethod extends PaymentMethod {
	public String cardHolderName;
	public String cardNumber;
	public int expiryMonth;
	public int expiryYear;
	public int cvv;

	private BankCardPaymentMethod(
		String methodName,
		String cardHolderName,
		String cardNumber,
		int expiryMonth,
		int expiryYear,
		int cvv
	) {
		super(methodName);
		this.cardHolderName = cardHolderName;
		this.cardNumber = cardNumber;
		this.expiryMonth = expiryMonth;
		this.expiryYear = expiryYear;
		this.cvv = cvv;
	}

	@Override
	public String toString() {
		var sb = new StringBuilder(super.toString());
		sb.append(String.format("\tCard Holder Name: %s\n", cardHolderName));
		sb.append(String.format("\tCard Number: %s\n", cardNumber));
		sb.append(String.format("\tExpiration Date: %d/%d\n", expiryMonth, expiryYear));
		sb.append(String.format("\tCvv: %d", cvv));
		return sb.toString();
	}

	public static class Builder {
		private String methodName;
		private String cardHolderName;
		private String cardNumber;
		private int expiryMonth;
		private int expiryYear;
		private int cvv;

		public Builder methodName(String methodName) {
			this.methodName = methodName;
			return this;
		}

		public Builder cardHolderName(String cardHolderName) {
			this.cardHolderName = cardHolderName;
			return this;
		}

		public Builder cardNumber(String cardNumber) {
			this.cardNumber = cardNumber;
			return this;
		}

		public Builder expiryMonth(int expiryMonth) {
			this.expiryMonth = expiryMonth;
			return this;
		}

		public Builder expiryYear(int expiryYear) {
			this.expiryYear = expiryYear;
			return this;
		}

		public Builder cvv(int cvv) {
			this.cvv = cvv;
			return this;
		}

		public BankCardPaymentMethod build() {
			return new BankCardPaymentMethod(
				methodName,
				cardHolderName,
				cardNumber,
				expiryMonth,
				expiryYear,
				cvv
			);
		}
	}
}
