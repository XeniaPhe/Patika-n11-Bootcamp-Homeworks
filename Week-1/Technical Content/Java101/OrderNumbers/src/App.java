import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        var numbers = new int[3];
        var variables = new String[] {"a", "b", "c"};

        for (int i = 0; i < 3; ++i) {
            System.out.printf("%s: ", variables[i]);
            numbers[i] = scanner.nextInt();
        }

        int a = numbers[0], b = numbers[1], c = numbers[2];

        if (a <= b && b <= c) {
            System.out.println("a <= b <= c");
        } else if (a <= c && c <= b) {
            System.out.println("a <= c <= b");
        } else if (b <= a && a <= c) {
            System.out.println("b <= a <= c");
        } else if (b <= c && c <= a) {
            System.out.println("b <= c <= a");
        } else if (c <= a && a <= b) {
            System.out.println("c <= a <= b");
        } else if (c <= b && b <= a) {
            System.out.println("c <= b <= a");
        }
    }
}
