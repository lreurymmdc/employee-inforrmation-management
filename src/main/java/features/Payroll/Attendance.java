package features.Payroll;

import csvManager.Reader;

import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.Duration;
import java.util.List;

public class Attendance {

    private final int employeeID;
    private final LocalDate start, end;

    private int totalLateMinutes = 0;
    private int totalWorkMinutes = 0;
    private int totalOvertimeMinutes = 0;

    private static final int LUNCH_BREAK_MINUTES = 60;

    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private static final LocalTime REQUIRED_LOGIN = LocalTime.of(8, 0);
    private static final LocalTime GRACE_PERIOD_END = LocalTime.of(8, 10);
    private static final LocalTime STANDARD_LOGOUT = LocalTime.of(17, 0);

    private static final Reader reader = new Reader("Payroll");

    public Attendance(int employeeID, String startDate, String endDate) {
        this.employeeID = employeeID;
        this.start = LocalDate.parse(startDate, DATE_FORMAT);
        this.end = LocalDate.parse(endDate, DATE_FORMAT);

        calculateWorkHours();
    }

    private void calculateWorkHours() {
        List<String[]> records = reader.getRecords();

        for (int i = 1; i < records.size(); i++) {
            String[] data = records.get(i);
            if (data.length < 4) continue;

            int recordID;
            try {
                recordID = Integer.parseInt(data[0].trim());
            } catch (NumberFormatException e) {
                continue;
            }

            if (recordID != employeeID) continue;

            LocalDate date;
            try {
                date = LocalDate.parse(data[1].trim(), DATE_FORMAT);
            } catch (Exception e) {
                continue;
            }

            if (date.isBefore(start) || date.isAfter(end)) continue;

            LocalTime loginTime, logoutTime;
            try {
                loginTime = LocalTime.parse(data[2].trim(), TIME_FORMAT);
                logoutTime = LocalTime.parse(data[3].trim(), TIME_FORMAT);
            } catch (Exception e) {
                continue;
            }

            int lateMinutes = loginTime.isAfter(GRACE_PERIOD_END)
                    ? (int) Duration.between(GRACE_PERIOD_END, loginTime).toMinutes()
                    : 0;

            int workMinutes = (int) Duration.between(loginTime, logoutTime).toMinutes() - LUNCH_BREAK_MINUTES;
            workMinutes = Math.max(0, workMinutes - lateMinutes);

            int overtimeMinutes = logoutTime.isAfter(STANDARD_LOGOUT)
                    ? (int) Duration.between(STANDARD_LOGOUT, logoutTime).toMinutes()
                    : 0;

            totalLateMinutes += lateMinutes;
            totalWorkMinutes += workMinutes;
            totalOvertimeMinutes += overtimeMinutes;
        }
    }

    public int getTotalLateMinutes() {
        return totalLateMinutes;
    }

    public int getTotalWorkMinutes() {
        return totalWorkMinutes;
    }

    public int getTotalOvertimeMinutes() {
        return totalOvertimeMinutes;
    }
}
