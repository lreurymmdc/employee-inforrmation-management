import features.AddNewEmployee;
import features.DisplayProfile;
import features.Login;
import users.HR_Admin;
import users.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        String userAction = "";
        Login login = new Login();

        login.login();

        if (login.isLoginSuccessful()) {
            HR_Admin hr = new HR_Admin(login);
            User user = new User(login);

            while (!userAction.equalsIgnoreCase("x")) {
                if (hr.isAdmin()) {
                    hr.showMenuHR();
                    System.out.println("X. Exit");
                    System.out.print("Select an option: ");
                    userAction = scanner.nextLine();

                    int searchKey;

                    switch (userAction.toUpperCase()) {
                        case "A":
                            DisplayProfile myProfile = new DisplayProfile();
                            myProfile.displayProfile(hr);
                            break;

                        case "D":
                            hr.onboard();
                            break;
                        case "E":
                            System.out.println("Enter an employee ID: ");
                            searchKey = scanner.nextInt();
                            scanner.nextLine();

                            hr.edit(searchKey, login);
                            break;
                        case "F":
                            System.out.println("Enter an employee ID: ");
                            searchKey = scanner.nextInt();
                            scanner.nextLine();

                            hr.terminateEmployee(searchKey, login);
                            break;
                        case "G":
                            System.out.println("Enter an employee ID: ");
                            searchKey = scanner.nextInt();
                            scanner.nextLine();
                            hr.search(searchKey, login);
                            break;
                        case "X":
                            System.out.println("Exiting...");
                            break;

                        default:
                            System.out.println("Invalid input. Please try again!");
                    }

                } else {
                    user.showMenuUser();
                    System.out.println("X. Exit");
                    System.out.print("Select an option: ");
                    userAction = scanner.nextLine();

                    switch (userAction.toUpperCase()) {
                        case "A":
                            DisplayProfile myProfile = new DisplayProfile();
                            myProfile.displayProfile(user);
                            break;

                        case "X":
                            System.out.println("Exiting...");
                            break;

                        default:
                            System.out.println("Invalid input. Please try again!");
                    }
                }
            }

        } else {
            System.out.println("Login failed. Exiting program.");
        }

        scanner.close();

    }
}
