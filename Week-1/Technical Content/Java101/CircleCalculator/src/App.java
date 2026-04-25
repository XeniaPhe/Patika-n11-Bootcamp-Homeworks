import java.util.Scanner;

public class App {
    static final float PI = 3.14159265359f;
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter circle radius: ");
        float radius = scanner.nextFloat();

        if (radius < 0) {
            System.out.println("Circle must have a positive radius");
            System.exit(1);
        }

        System.out.printf("Circle circumference: %.3f\n", 2 * PI * radius);
        System.out.printf("Circle area: %.3f\n", PI * radius * radius);
    }
}
