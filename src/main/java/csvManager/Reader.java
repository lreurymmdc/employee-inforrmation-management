package csvManager;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    private final String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\EmployeeData.csv";
    private List<String[]> records = new ArrayList<>();

    public List<String[]> readCSV() {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            records = reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return records;
    }
    public boolean isUserIDValid(int userID) {
        readCSV();
        for (String[] record : records) {
            for (String value : record) {
                if (value.equals(String.valueOf(userID))) {
                    return true;
                }
            }
        }
        return false;
    }
    public int getIndexOfUserID(int userID, List<String[]> records) {
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i)[0].equals(String.valueOf(userID))) {
                return i;
            }
        }
        return -1;
    }

    public List<String[]> getRecords() {
        readCSV();
        return records;
    }
    public String getFilePath() {
        return this.filePath;
    }
    public String getLastEmployeeID() {
        readCSV();
        int lastEmployeeID = records.size() - 1;
        return records.get(lastEmployeeID)[0];

    }
}
