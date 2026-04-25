import java.util.Scanner;

public class App {
    private static ZodiacSign[] zodiacs = new ZodiacSign[] {
        ZodiacSign.Aquarius,
        ZodiacSign.Pisces,
        ZodiacSign.Aries,
        ZodiacSign.Taurus,
        ZodiacSign.Gemini,
        ZodiacSign.Cancer,
        ZodiacSign.Leo,
        ZodiacSign.Virgo,
        ZodiacSign.Libra,
        ZodiacSign.Scorpio,
        ZodiacSign.Sagittarius,
        ZodiacSign.Capricorn
    };

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Month of birth: ");
        int month = scanner.nextInt() - 1;
        System.out.print("Day of birth: ");
        int day = scanner.nextInt();

        ZodiacSign zodiacSign;
        if (day >= zodiacs[month].startDay()) {
            zodiacSign = zodiacs[month];
        } else {
            zodiacSign = zodiacs[(month - 1) % 12];
        }

        System.out.printf("Your zodiac sign is %s", zodiacSign.name());
    }
}
