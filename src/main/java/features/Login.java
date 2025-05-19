package features;

import csvManager.Reader;
import users.User;

import java.util.Scanner;

public class Login {
    private int userID;
    private String birthday;
    private boolean loginStatus = false;

    private Scanner scanner;
    private Reader read;
    private User user;

    public Login() {
        scanner = new Scanner(System.in);
        read = new Reader("HR");
    }

    public void login() {
        while (!loginStatus) {
            userID = validUserID();

            System.out.print("Enter your date of birth (MM/DD/YYYY): ");
            birthday = scanner.nextLine();

            if (user.getBirthday().equals(birthday)) {
                loginStatus = true;
                String fullName = user.getFullName();
                System.out.println("\nWelcome, " + fullName + "! You have successfully logged in.");
            } else {
                System.out.println("\n We couldn't find a match for the information you entered. Please try again.\n");
            }
        }
    }
    private int validUserID() {
        while(true) {
            System.out.println("Enter userID: ");
            while(!scanner.hasNextInt()) {
                System.out.print("Invalid input. Please enter a numeric User ID: ");
                scanner.next();
            }
            userID = scanner.nextInt();
            scanner.nextLine();

            if (read.isUserIDValid(userID)) {
                this.user = new User(userID);
                return userID;
            }
            System.out.println("User ID not found. Please try again.");
        }
    }
    public boolean validateUserID(int userID) {
        if (read.isUserIDValid(userID)) {
            return true;
        }
        return false;
    }

    public int getUserIDInput() {
        return this.userID;
    }
    public String getBirthdayInput() {
        return this.birthday;
    }
    public boolean isLoginSuccessful() {
        return this.loginStatus;
    }

}
