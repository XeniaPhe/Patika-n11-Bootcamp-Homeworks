import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        var scanner = new Scanner(System.in);
        var classes = new String[] {
            "Math",
            "Turkish",
            "Physics",
            "Chemistry",
            "History",
            "Music",
        };
        
        var gradeSum = 0;
        for (var cls : classes) {
            gradeSum += inputGradeFor(scanner, cls);
        }

        var avgGrade = (float)gradeSum / (float)classes.length;
        if (avgGrade > 60.0f) {
            System.out.println("Passed the class");
        }
        else {
            System.out.println("Failed the class");
        }
    }

    public static int inputGradeFor(Scanner scanner, String className) {
        while (true) {
            System.out.printf("Enter %s grade: ", className);
            var input = scanner.nextLine();

            try {
                var grade = Integer.parseInt(input);

                if (grade < 0 || grade > 100) {
                    continue;
                }

                return grade;
            } catch (Exception e) {
                continue;
            }
        }
    }
}
