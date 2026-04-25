import java.util.Scanner;

public class App {
    enum Product {
        Pear(0),
        Apple(1),
        Tomato(2),
        Banana(3),
        Eggplant(4);

        private final int value;

        Product(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    static Float[] prices = new Float[] {
        2.14f,
        3.67f,
        1.11f,
        0.95f,
        5.00f
    };

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        float totalPrice = 0;

        for (Product p : Product.values()) {
            System.out.printf("How many kilograms of %s?: ", p.name().toLowerCase());
            float weight = scanner.nextFloat();

            if (weight < 0) {
                System.out.println("Weights of fruits must be positive");
                System.exit(1);
            }

            totalPrice += weight * prices[p.getValue()];
        }

        System.out.printf("Total price: %.2f TL\n", totalPrice);
    }
}
