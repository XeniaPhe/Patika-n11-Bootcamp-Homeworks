import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a year: ");
        int year = scanner.nextInt();

        boolean isLeap = false;
        if (year % 100 == 0) {
            isLeap = year % 400 == 0;
        } else {
            isLeap = year % 4 == 0;
        }

        System.out.printf("The year %d is %sa leap year\n", year, isLeap ? "" : "not ");
    }
}
