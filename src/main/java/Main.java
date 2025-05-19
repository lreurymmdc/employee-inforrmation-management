import features.DisplayProfile;
import features.Login;
import features.Payroll.Payroll;
import users.HR_Admin;
import users.Payroll_Admin;
import users.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Main extends JFrame {

    private JPanel MainPanel;
    private JLabel AppTitle;
    private JPanel jsPanel_Header;
    private JPanel jsPanel_Body;
    private JPanel jsPanel_Footer;
    private JPanel js_Panel_Fields;
    private JTextField jsTxtField_UserID_Login;
    private JTextField jsTxtField_Birthday_Login;
    private JLabel jsLabel_UserID_Login;
    private JLabel jsLabel_Birthday_Login;
    private JButton loginButton;


    public Main() {
        setTitle("MotorPH - Employee Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(375, 667);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setContentPane(MainPanel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {

        new Main();


        Scanner scanner = new Scanner(System.in);
        String userAction = "";
        Login login = new Login();

        login.login();

        if (login.isLoginSuccessful()) {
            HR_Admin hr = new HR_Admin(login);
            Payroll_Admin payroll = new Payroll_Admin(login);
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
                        case "B":
                            payroll.selectPayslipForm();
                            payroll.generatePayslipInformation(login.getUserIDInput());
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

                }
                 else {
                    user.showMenuUser();
                    System.out.println("X. Exit");
                    System.out.print("Select an option: ");
                    userAction = scanner.nextLine();

                    switch (userAction.toUpperCase()) {
                        case "A":
                            DisplayProfile myProfile = new DisplayProfile();
                            myProfile.displayProfile(user);
                            break;
                        case "B":
                            payroll.selectPayslipForm();
                            payroll.generatePayslipInformation(login.getUserIDInput());
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
