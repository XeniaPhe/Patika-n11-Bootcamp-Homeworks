import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Distance traveled in kilometers: ");
        float distance = scanner.nextFloat();

        float price = 10.0f + distance * 2.2f;
        price = price < 20.0f ? 20.0f : price;

        System.out.printf("Price: %.2f\n", price);

    }
}
