package users;

import csvManager.Reader;
import features.Login;
import java.util.List;

public class User {
    protected int employeeId;
    protected String birthday;
    private int searchKey;
    private final Reader database = new Reader();



    private List<String[]> localRecords = database.getRecords();

    public User(int employeeId, String birthday) { //Adding new user
        this.employeeId = employeeId;
        this.birthday = birthday;
    }

    public User (int employeeId) { //Login function
        this.employeeId = employeeId;
        this.birthday = getBirthday();
    }

    public User(Login login) { //Will save userID used during login
        this.employeeId = login.getUserIDInput();
        this.birthday = login.getBirthdayInput();
    }

    public User() {

    }

    public String getFullName() {
        searchKey = database.getIndexOfUserID(employeeId, localRecords);
        return localRecords.get(searchKey)[2] + " " + localRecords.get(searchKey)[1];

    }
    public String getLastName() {
        searchKey = database.getIndexOfUserID(employeeId, localRecords);
        return localRecords.get(searchKey)[1];

    }
    public String getFirstName() {
        searchKey = database.getIndexOfUserID(employeeId, localRecords);
        return localRecords.get(searchKey)[2];

    }
    public String getBirthday() {
        searchKey = database.getIndexOfUserID(employeeId, localRecords);
        return localRecords.get(searchKey)[3];

    }
    public String getAddress() {
        searchKey = database.getIndexOfUserID(employeeId, localRecords);
        return localRecords.get(searchKey)[4];
    }

    public String getPhoneNumber() {
        searchKey = database.getIndexOfUserID(employeeId, localRecords);
        return localRecords.get(searchKey)[5];
    }

    public String getSSSNumber() {
        searchKey = database.getIndexOfUserID(employeeId, localRecords);
        return localRecords.get(searchKey)[6];
    }

    public String getPhilHealthNumber() {
        searchKey = database.getIndexOfUserID(employeeId, localRecords);
        return localRecords.get(searchKey)[7];
    }

    public String getTINNumber() {
        searchKey = database.getIndexOfUserID(employeeId, localRecords);
        return localRecords.get(searchKey)[8];
    }

    public String getPagIbigNumber() {
        searchKey = database.getIndexOfUserID(employeeId, localRecords);
        return localRecords.get(searchKey)[9];
    }

    public String getStatus() {
        searchKey = database.getIndexOfUserID(employeeId, localRecords);
        return localRecords.get(searchKey)[10];
    }

    public String getPosition() {
        searchKey = database.getIndexOfUserID(employeeId, localRecords);
        return localRecords.get(searchKey)[11];
    }

    public String getImmediateSupervisor() {
        searchKey = database.getIndexOfUserID(employeeId, localRecords);
        return localRecords.get(searchKey)[12];
    }

    public int getBasicSalary() {
        searchKey = database.getIndexOfUserID(employeeId, localRecords);
        return Integer.parseInt(localRecords.get(searchKey)[13].replace(",",""));
    }

    public int getRiceSubsidy() {
        searchKey = database.getIndexOfUserID(employeeId, localRecords);
        return Integer.parseInt(localRecords.get(searchKey)[14].replace(",",""));
    }

    public int getPhoneAllowance() {
        searchKey = database.getIndexOfUserID(employeeId, localRecords);
        return Integer.parseInt(localRecords.get(searchKey)[15].replace(",",""));
    }

    public int getClothingAllowance() {
        searchKey = database.getIndexOfUserID(employeeId, localRecords);
        return Integer.parseInt(localRecords.get(searchKey)[16].replace(",",""));
    }

    public double getHourlyRate() {
        searchKey = database.getIndexOfUserID(employeeId, localRecords);
        return Double.parseDouble(localRecords.get(searchKey)[17]);
    }
    public void showMenuUser() {
        System.out.println("\nWhat do you want to do ?\n");
        System.out.println("A. View my profile");
    }
    public void showMenuHR() {
        showMenuUser();
        System.out.println("\n--- HR Tools --- \n");
        System.out.println("D. Add a new employee");
        System.out.println("E. Update an employee data");
        System.out.println("F. Remove an Employee");
        System.out.println("G. Search an Employee");

    }

}
