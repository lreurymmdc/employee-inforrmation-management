package csvManager;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Editor {

    protected final Reader csvReader;

    public Editor(Reader reader) {
        this.csvReader = reader;
    }

    protected void updateRowByUserID(int userID, String[] updatedRow) {
        List<String[]> records = csvReader.getRecords();

        int targetIndex = csvReader.getIndexOfUserID(userID, records);
        if (targetIndex == -1) {
            System.out.println("User ID not found. Update failed.");
            return;
        }

        records.set(targetIndex, updatedRow);  // Replace the row

        // Overwrite the CSV file with updated records
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvReader.getFilePath()))) {
            writer.writeAll(records);
            System.out.println("Row updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
