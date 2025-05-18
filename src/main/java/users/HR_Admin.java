package users;


import features.*;


import java.util.ArrayList;
import java.util.Scanner;

public class HR_Admin extends User {

    private static final int[] HR_ADMIN_IDS = {10006, 10007, 10008, 10009};
    private final ArrayList<String> newUserInformation = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public HR_Admin(Login login) {
        super(login);
    }

    public HR_Admin() {}

    public boolean isAdmin() {
        for (int adminId : HR_ADMIN_IDS) {
            if (adminId == employeeId) return true;
        }
        return false;
    }

    public void onboard() {
        AddNewEmployee newUser = new AddNewEmployee();
        newUserInformation.clear();
        newUser.getForm(this);

        System.out.print("Do you want to push the changes? (Y/N): ");
        boolean shouldPush = scanner.nextLine().equalsIgnoreCase("Y");

        if (shouldPush) {
            String data = String.join(",", newUserInformation);
            newUser.appendData(data);
            System.out.println("New employee added.");
        } else {
            System.out.println("New employee not added.");
        }
    }

    public void setInformation(String data) {
        newUserInformation.add(data);
    }

    public void search(int searchKey, Login validator) {
        SearchEmployee search = new SearchEmployee(searchKey, validator);
        displaySearchResult(search);
    }

    public void search(int searchKey, boolean validator) {
        SearchEmployee search = new SearchEmployee(searchKey, validator);
        displaySearchResult(search);
    }

    private void displaySearchResult(SearchEmployee search) {
        if (search.isFound()) {
            search.displayProfile(search.getEmployee());
        } else {
            System.out.println("No employee found.");
        }
    }

    public void edit(int searchKey, Login validator) {
        EditEmployee editor = new EditEmployee();
        editor.editEmployee(searchKey, validator);
    }
    public void terminateEmployee(int employeeIdToRemove, Login login) {
        DeleteEmployee deleteHandler = new DeleteEmployee();
        deleteHandler.deleteEmployee(employeeIdToRemove, login);
    }

}
