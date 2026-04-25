import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Side 1: ");
        double side1 = scanner.nextDouble();

        if (side1 < 0) {
            System.out.println("Triangle side must be positive");
            System.exit(1);
        }

        System.out.print("Side 2: ");
        double side2 = scanner.nextDouble();

        if (side2 < 0) {
            System.out.println("Triangle side must be positive");
            System.exit(1);
        }

        System.out.printf("Hypotenuse: %.3f\n", Math.sqrt(side1 * side1 + side2 * side2));
    }
}
