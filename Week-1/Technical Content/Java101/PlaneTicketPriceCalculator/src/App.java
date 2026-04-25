import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Distance in kilometers: ");
        int dist = scanner.nextInt();

        if (dist <= 0) {
            System.out.println("Invalid distance");
            return;
        }

        System.out.print("Age: ");
        int age = scanner.nextInt();

        if (age <= 0) {
            System.out.println("Invalid age");
            return;
        }

        System.out.print("Ticket type (1=One-way, 2=Round-trip): ");
        int ticketType = scanner.nextInt();

        if (ticketType != 1 && ticketType != 2) {
            System.out.println("Invalid ticket type");
            return;
        }

        float price = ticketType * dist * 0.1f;

        if (age < 12) {
            price *= 0.5f;
        } else if (age < 24) {
            price *= 0.9f;
        } else if (age > 65) {
            price *= 0.7f;
        }

        if (ticketType == 2) {
            price *= 0.8f;
        }

        System.out.printf("Total price: %.2f TL", price);
    }
}
