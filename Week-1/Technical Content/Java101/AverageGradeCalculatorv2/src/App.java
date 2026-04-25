import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        var classes = new String[] {
            "Math",
            "Physics",
            "Turkish",
            "Chemistry",
            "Music",
        };
        
        var gradeSum = 0;
        var validGradeCount = 0;
        for (var cls : classes) {
            System.out.printf("%s grade: ", cls);
            var grade = scanner.nextInt();

            if (grade >= 0 && grade <= 100) {
                gradeSum += grade;
                validGradeCount++;
            }
        }

        var avgGrade = (float)gradeSum / (float)validGradeCount;
        if (avgGrade > 55.0f) {
            System.out.println("Passed the class");
        }
        else {
            System.out.println("Failed the class");
        }
    }
}
