import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        float weight = inputFloat(scanner, "Weight (kilograms)", "Weight");
        float height = inputFloat(scanner, "Height (meters)", "Height");
        float bodyMassIndex = weight / (height * height);
        System.out.printf("Body mass index: %.3f\n", bodyMassIndex);
    }

    private static float inputFloat(Scanner scanner, String prompt, String inputName) {
        System.out.printf("%s: ", prompt);
        float input = scanner.nextFloat();

        if (input < 0) {
            System.out.printf("%s must be positive\n", inputName);
            System.exit(1);
        }

        return input;
    }
}
