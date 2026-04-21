package main.java.com.xenia.n11bootcamp.paymentprocessing.models;

public abstract class PaymentMethod {
	private static int idCounter = 0;

	public int id;
	public String name;
	private String cachedMethodType;

	protected PaymentMethod(String methodName) {
		this.id = idCounter++;
		this.name = methodName;
	}

	public final String methodType() {
		final var paymentStr = "Payment";
		final var methodStr = "Method";
		final var paymentMethodStr = paymentStr + methodStr;

		if (cachedMethodType == null) {
			var clsName = this.getClass().getSimpleName();

			int suffixLength = switch (clsName) {
				case String s when s.endsWith(paymentMethodStr) -> paymentMethodStr.length();
				case String s when s.endsWith(methodStr) -> methodStr.length();
				case String s when s.endsWith(paymentStr) -> paymentStr.length();
				default -> 0;
			};

			this.cachedMethodType = clsName.substring(0, clsName.length() - suffixLength);
		}
		
		return cachedMethodType;
	}
	
	@Override
	public String toString() {
		var sb = new StringBuilder();
		sb.append(String.format("Payment Method Id: %d\n", id));
		sb.append(String.format("Payment Method Type: %s\n", methodType()));
		sb.append(String.format("Payment Method Name: %s\n", name));
		sb.append(String.format("Payment Method Details:\n"));
		return sb.toString();
	}
}
