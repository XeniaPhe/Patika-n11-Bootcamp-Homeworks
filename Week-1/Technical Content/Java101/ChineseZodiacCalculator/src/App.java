import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter year of birth: ");
        int year = scanner.nextInt();

        String sign = switch (year % 12) {
            case 0  -> "Monkey";
            case 1  -> "Rooster";
            case 2  -> "Dog";
            case 3  -> "Pig";
            case 4  -> "Rat";
            case 5  -> "Ox";
            case 6  -> "Tiger";
            case 7  -> "Rabbit";
            case 8  -> "Dragon";
            case 9  -> "Snake";
            case 10 -> "Horse";
            case 11 -> "Goat";
            default -> "Invalid";
        };

        System.out.printf("Your Chinese zodiac sign is %s\n", sign);
    }
}
