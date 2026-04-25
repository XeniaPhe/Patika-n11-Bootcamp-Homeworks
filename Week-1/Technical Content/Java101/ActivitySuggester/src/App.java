import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Temperature: ");
        int temperature = scanner.nextInt();

        if (temperature < 5) {
            System.out.println("You should go skiing");
        } else if (temperature <= 15) {
            System.out.println("You should go to the cinema");
        } else if (temperature <= 25) {
            System.out.println("You should have a picnic");
        } else {
            System.out.println("You should go swimming");
        }
    }
}
