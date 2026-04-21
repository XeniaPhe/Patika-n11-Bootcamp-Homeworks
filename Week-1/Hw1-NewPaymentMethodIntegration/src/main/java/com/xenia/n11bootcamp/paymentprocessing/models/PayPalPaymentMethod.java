package main.java.com.xenia.n11bootcamp.paymentprocessing.models;

public class PayPalPaymentMethod extends PaymentMethod {
    public String payPalEmail;
    public String payerId;

    private PayPalPaymentMethod(String methodName, String payPalEmail, String payerId) {
        super(methodName);
        this.payPalEmail = payPalEmail;
        this.payerId = payerId;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder(super.toString());
        sb.append(String.format("\tPayPal Email: %s\n", payPalEmail));
        sb.append(String.format("\tPayer Id: %s", payerId));
        return sb.toString();
    }

    public static class Builder {
        private String methodName;
        private String payPalEmail;
        private String payerId;

        public Builder methodName(String methodName) {
            this.methodName = methodName;
            return this;
        }

        public Builder payPalEmail(String payPalEmail) {
            this.payPalEmail = payPalEmail;
            return this;
        }

        public Builder payerId(String payerId) {
            this.payerId = payerId;
            return this;
        }

        public PayPalPaymentMethod build() {
            return new PayPalPaymentMethod(
                methodName,
                payPalEmail,
                payerId
            );
        }
    }
}