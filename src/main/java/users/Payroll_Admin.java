package users;

import features.HR.SearchEmployee;
import features.Login;
import features.Payroll.Attendance;
import features.Payroll.Payroll;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Payroll_Admin extends User {

    private String startDate, endDate;
    private int payslipNumber = -1;

    private int[] PAYROLL_ADMIN_IDS = {10011, 10012, 10013, 10014};
    private Payroll payroll;
    private Attendance attendance;
    public Payroll_Admin(Login login) {
        super(login);

    }

    public Payroll_Admin() {

    }

    public boolean isAdmin() {
        for (int adminId : PAYROLL_ADMIN_IDS) {
            if (adminId == employeeId) return true;
        }
        return false;
    }
    public void generatePayslipInformation(int searchKey) {

        selectPayslip();

        startDate = getStartDate();
        endDate = getEndDate();

        payroll = new Payroll(searchKey, startDate, endDate);
        attendance = new Attendance(searchKey, startDate, endDate);


        User user = new User(searchKey);

        double hourlyRate = user.getHourlyRate();
        int totalWorkHours = attendance.getTotalWorkMinutes() / 60;
        int totalOvertimeHours = attendance.getTotalOvertimeMinutes() / 60;

        double grossIncome = payroll.getBasicPay();
        double overtimePay = payroll.getOverTimeAdditions();
        double lateDeductions = payroll.getLateDeductions();

        double riceSubsidy = user.getRiceSubsidy() / 4.0;
        double phoneAllowance = user.getPhoneAllowance() / 4.0;
        double clothingAllowance = user.getClothingAllowance() / 4.0;

        double sss = payroll.getSssDeductions();
        double philhealth = payroll.getPhilHealthDeductions();
        double pagibig = payroll.getPagIbigDeductions();
        double tax = payroll.getTaxDeductions();

        double totalBenefits = riceSubsidy + phoneAllowance + clothingAllowance;
        double totalEarnings = payroll.getTotalEarnings();
        double totalDeductions = payroll.getTotalDeductions();
        double netPay = payroll.getNetPay();

        System.out.println("----- PAYSLIP -----");
        System.out.println("Employee ID: " + searchKey);
        System.out.println("Pay Period: " + startDate + " to " + endDate);

        System.out.println("\nEARNINGS");
        System.out.printf("Hourly Rate:\t\t%.2f\n", hourlyRate);
        System.out.printf("Total Hours Worked:\t%d\n", totalWorkHours);
        System.out.printf("Overtime Hours:\t\t%d\n", totalOvertimeHours);
        System.out.printf("Basic Pay:\t\t%.2f\n", grossIncome);
        System.out.printf("Overtime Pay:\t\t%.2f\n", overtimePay);
        System.out.printf("Total Earnings:\t\t%.2f\n", totalEarnings);

        System.out.println("\nBENEFITS");
        System.out.printf("Rice Subsidy:\t\t%.2f\n", riceSubsidy);
        System.out.printf("Phone Allowance:\t%.2f\n", phoneAllowance);
        System.out.printf("Clothing Allowance:\t%.2f\n", clothingAllowance);
        System.out.printf("Total Benefits:\t\t%.2f\n", totalBenefits);

        System.out.println("\nDEDUCTIONS");
        System.out.printf("Late Deductions:\t%.2f\n", lateDeductions);
        System.out.printf("SSS:\t\t\t%.2f\n", sss/4.33);
        System.out.printf("PhilHealth:\t\t%.2f\n", philhealth/4.33);
        System.out.printf("Pag-IBIG:\t\t%.2f\n", pagibig/4.33);
        System.out.printf("Tax:\t\t\t%.2f\n", tax);
        System.out.printf("Total Deductions:\t%.2f\n", totalDeductions);

        System.out.println("\nSUMMARY");
        System.out.printf("Total Earnings:\t\t%.2f\n", totalEarnings);
        System.out.printf("Total Benefits:\t\t%.2f\n", totalBenefits);
        System.out.printf("Total Deductions:\t%.2f\n", totalDeductions);
        System.out.printf("Net Pay:\t\t%.2f\n", netPay);
    }

    public void selectPayslipForm() {
        String[] payslipDates = {
                "06/03/2024 - 06/09/2024", "06/10/2024 - 06/16/2024", "06/17/2024 - 06/23/2024",
                "06/24/2024 - 06/30/2024", "07/01/2024 - 07/07/2024", "07/08/2024 - 07/14/2024",
                "07/15/2024 - 07/21/2024", "07/22/2024 - 07/28/2024", "07/29/2024 - 08/04/2024",
                "08/05/2024 - 08/11/2024", "08/12/2024 - 08/18/2024", "08/19/2024 - 08/25/2024",
                "08/26/2024 - 09/01/2024", "09/02/2024 - 09/08/2024", "09/09/2024 - 09/15/2024",
                "09/16/2024 - 09/22/2024", "09/23/2024 - 09/29/2024", "09/30/2024 - 10/06/2024",
                "10/07/2024 - 10/13/2024", "10/14/2024 - 10/20/2024", "10/21/2024 - 10/27/2024",
                "10/28/2024 - 11/03/2024", "11/04/2024 - 11/10/2024", "11/11/2024 - 11/17/2024",
                "11/18/2024 - 11/24/2024", "11/25/2024 - 12/01/2024", "12/02/2024 - 12/08/2024",
                "12/09/2024 - 12/15/2024", "12/16/2024 - 12/22/2024", "12/23/2024 - 12/29/2024",
                "12/30/2024 - 01/05/2025"
        };

        // Display menu
        System.out.println("Enter payslip number you want to view:");
        for (int i = 0; i < payslipDates.length; i++) {
            System.out.printf("%2d. Payslip %d (%s)%n", i + 1, i + 1, payslipDates[i]);
        }
    }


    private void selectPayslip() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Your choice (1–31): ");
            if (scanner.hasNextInt()) {
                payslipNumber = scanner.nextInt();
                scanner.nextLine();
                setPayslipNumber(payslipNumber);
                if (payslipNumber >= 1 && payslipNumber <= 31) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 31.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }


    }

    private void setPayslipNumber(int payslipNumber) {
        this.payslipNumber = payslipNumber;
    }

    public String getStartDate() {
        payroll = new Payroll();
        return payroll.getPayslips().get(payslipNumber-1)[1];
    }
    public String getEndDate() {
        payroll = new Payroll();
        return payroll.getPayslips().get(payslipNumber-1)[2];
    }

}
