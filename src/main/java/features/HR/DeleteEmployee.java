package features.HR;

import com.opencsv.CSVWriter;
import csvManager.Reader;
import features.Login;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class DeleteEmployee {

    private final Scanner scanner;
    private final Reader reader;

    public DeleteEmployee() {
        scanner = new Scanner(System.in);
        reader = new Reader("HR");
    }

    public void deleteEmployee(int searchKey, Login login) {
        SearchEmployee search = new SearchEmployee(searchKey, login);

        if (!search.isFound()) {
            System.out.println("Employee not found.");
            return;
        }

        search.displayProfile(search.getEmployee());

        System.out.print("Are you sure you want to terminate this employee? (Y/N): ");
        String confirm = scanner.nextLine();

        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("Termination cancelled.");
            return;
        }

        performDeletion(searchKey);
    }

    private void performDeletion(int employeeIdToRemove) {
        List<String[]> records = reader.getRecords();
        int index = reader.getIndexOfUserID(employeeIdToRemove, records);

        if (index == -1) {
            System.out.println("Employee ID not found. Deletion failed.");
            return;
        }

        records.remove(index); // remove employee row

        try (CSVWriter writer = new CSVWriter(new FileWriter(reader.getFilePath()))) {
            writer.writeAll(records);
            System.out.println("Employee successfully terminated.");
        } catch (IOException e) {
            System.out.println("Error writing to CSV.");
            e.printStackTrace();
        }
    }
}
