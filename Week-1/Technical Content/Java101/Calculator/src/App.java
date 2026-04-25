import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("First number: ");
        int num1 = scanner.nextInt();
        System.out.print("Second number: ");
        int num2 = scanner.nextInt();

        System.out.print("1-Addition\n2-Subtraction\n3-Multiplication\n4-Division\n");
        System.out.print("Select operation: ");
        int op = scanner.nextInt();
        int result = 0;

        switch (op) {
            case 1: result = num1 + num2; break;
            case 2: result = num1 - num2; break;
            case 3: result = num1 * num2; break;
            case 4: result = num1 / num2; break;
            default:
                System.out.println("Invalid operation");
                System.exit(1);
        }

        System.out.printf("Result: %d\n", result);
    }
}
