package features.HR;

import csvManager.Reader;
import users.HR_Admin;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AddNewEmployee {

    private final Scanner scanner;
    private final Reader reader;

    public AddNewEmployee() {
        scanner = new Scanner(System.in);
        reader = new Reader("HR");
    }

    public void appendData(String data) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(reader.getFilePath(), true))) {
            pw.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addNewUserForm(HR_Admin admin) {

        int newEmployeeID = Integer.parseInt(reader.getLastEmployeeID())+1;

        System.out.println("New Employee ID: " + newEmployeeID);
        admin.setInformation(String.valueOf(newEmployeeID));

        System.out.println("Enter Last Name:");
        admin.setInformation(scanner.nextLine());

        System.out.println("Enter First Name:");
        admin.setInformation(scanner.nextLine());

        System.out.println("Enter Birthday (e.g. DD-MM-YYYY):");
        admin.setInformation(scanner.nextLine());

        System.out.println("Enter Address:");
        admin.setInformation(scanner.nextLine());

        System.out.println("Enter Phone Number:");
        admin.setInformation(scanner.nextLine());

        System.out.println("Enter SSS Number:");
        admin.setInformation(scanner.nextLine());

        System.out.println("Enter PhilHealth Number:");
        admin.setInformation(scanner.nextLine());

        System.out.println("Enter TIN Number:");
        admin.setInformation(scanner.nextLine());

        System.out.println("Enter PAG-IBIG Number:");
        admin.setInformation(scanner.nextLine());

        System.out.println("Enter Status (e.g. Regular/Contractual):");
        admin.setInformation(scanner.nextLine());

        System.out.println("Enter Position:");
        admin.setInformation(scanner.nextLine());

        System.out.println("Enter Supervisor:");
        admin.setInformation(scanner.nextLine());

        System.out.println("Enter Basic Salary:");
        admin.setInformation(scanner.nextLine());

        System.out.println("Enter Rice Subsidy:");
        admin.setInformation(scanner.nextLine());

        System.out.println("Enter Phone Allowance:");
        admin.setInformation(scanner.nextLine());

        System.out.println("Enter Clothing Allowance:");
        admin.setInformation(scanner.nextLine());

        System.out.println("Enter Hourly Rate:");
        admin.setInformation(scanner.nextLine());
    }

    public void getForm(HR_Admin admin) {
        addNewUserForm(admin);
    }
}
