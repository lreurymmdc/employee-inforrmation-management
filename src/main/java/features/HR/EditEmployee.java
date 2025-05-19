package features.HR;

import com.opencsv.CSVWriter;
import csvManager.Reader;
import features.Login;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class EditEmployee {

    private final Scanner scanner;
    private final Reader reader;

    public EditEmployee() {
        scanner = new Scanner(System.in);
        reader = new Reader("HR");
    }

    public void editEmployee(int searchKey, Login login) {
        SearchEmployee search = new SearchEmployee(searchKey, login);

        if (!search.isFound()) {
            System.out.println("Employee not found.");
            return;
        }

        displayProfile(search);
        int[] fields = promptFieldsToUpdate();
        String[] newValues = promptNewValues(fields);
        updateCSV(searchKey, fields, newValues);
    }

    private void displayProfile(SearchEmployee search) {
        System.out.println("You're updating employee " + search.getEmployee().getFullName());
        search.displayProfile(search.getEmployee());
    }

    private int[] promptFieldsToUpdate() {
        System.out.print("How many fields do you want to update? ");
        int count = Integer.parseInt(scanner.nextLine());
        int[] fields = new int[count];

        for (int i = 0; i < count; i++) {
            System.out.print("Enter field number to update (1-16): ");
            int field = Integer.parseInt(scanner.nextLine());

            if (field < 1 || field > 16) {
                System.out.println("Invalid field number. Please try again.");
                i--;
            } else {
                fields[i] = field;
            }
        }
        return fields;
    }

    private String[] promptNewValues(int[] fields) {
        String[] values = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            System.out.print("Enter new value for field " + fields[i] + ": ");
            values[i] = scanner.nextLine();
        }
        return values;
    }

    private void updateCSV(int searchKey, int[] fieldsToUpdate, String[] newValues) {
        List<String[]> records = reader.getRecords();
        int index = reader.getIndexOfUserID(searchKey, records);

        if (index == -1) {
            System.out.println("User ID not found. Update failed.");
            return;
        }

        String[] row = records.get(index).clone();
        for (int i = 0; i < fieldsToUpdate.length; i++) {
            int fieldIndex = fieldsToUpdate[i] + 1; // skip ID
            row[fieldIndex] = newValues[i];
        }

        records.set(index, row);

        try (CSVWriter writer = new CSVWriter(new FileWriter(reader.getFilePath()))) {
            writer.writeAll(records);
            System.out.println("Update successful.");
        } catch (IOException e) {
            System.out.println("Error writing to CSV file.");
            e.printStackTrace();
        }
    }


}
