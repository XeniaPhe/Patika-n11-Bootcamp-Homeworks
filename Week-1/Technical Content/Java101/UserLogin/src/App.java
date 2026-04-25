import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (!username.equals("patika")) {
            System.out.printf("No user found with username %s\n", username);
            return;
        }

        if (password.equals("java123")) {
            System.out.println("Successful login");
            return;
        }

        System.out.print("Enter 'y' or 'Y' to reset password: ");
        String response = scanner.nextLine().toLowerCase();

        if (!response.equals("y")) {
            return;
        }

        while (true) {
            System.out.print("Enter new password: ");
            password = scanner.nextLine();

            if (!password.equals("java123")) {
                System.out.println("Password has been updated");
                break;
            }

            System.out.println("Password couldn't be updated, please enter another password");
        }
    }
}
