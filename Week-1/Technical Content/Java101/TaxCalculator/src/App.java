import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        float priceToTax = 0;

        while (true) {
            System.out.print("Enter the price to tax: ");
            var input = scanner.nextLine();

            try {
                priceToTax = Float.parseFloat(input);
                if (priceToTax >= 0) {
                    break;
                }
            } catch (Exception e) {
                continue;
            }
        }

        float tax = 1.18f;

        if (priceToTax > 1000) {
            tax = 1.08f;
        }

        float afterTaxes = priceToTax * tax;
        float taxAmount = afterTaxes - priceToTax;

        System.out.printf("Taxed price: %.2f\n", afterTaxes);
        System.out.printf("Tax amount: %.2f\n", taxAmount);
    }
}
