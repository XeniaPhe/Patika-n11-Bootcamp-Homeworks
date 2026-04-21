package main.java.com.xenia.n11bootcamp.paymentprocessing.models;

public class CryptoPaymentMethod extends PaymentMethod {
	public String walletAddress;
	public String network;
	public String currency;

	private CryptoPaymentMethod(String methodName, String walletAddress,
								String network, String currency) {
		super(methodName);
		this.walletAddress = walletAddress;
		this.network = network;
		this.currency = currency;
	}

	@Override
	public String toString() {
		var sb = new StringBuilder(super.toString());
		sb.append(String.format("\tWallet Address: %s\n", walletAddress));
		sb.append(String.format("\tNetwork: %s\n", network));
		sb.append(String.format("\tCurrency: %s", currency));
		return sb.toString();
	}

	public static class Builder {
		private String methodName;
		private String walletAddress;
		private String network;
		private String currency;

		public Builder methodName(String methodName) {
			this.methodName = methodName;
			return this;
		}

		public Builder walletAddress(String walletAddress) {
			this.walletAddress = walletAddress;
			return this;
		}

		public Builder network(String network) {
			this.network = network;
			return this;
		}

		public Builder currency(String currency) {
			this.currency = currency;
			return this;
		}

		public CryptoPaymentMethod build() {
			return new CryptoPaymentMethod(
				methodName,
				walletAddress,
				network,
				currency
			);
		}
	}
}