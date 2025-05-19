package features.Payroll;

import users.Payroll_Admin;
import users.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

public class Payroll {

    private double lateDeductions;
    private double overTimeAdditions;
    private double basicPay;
    private double sssDeductions;
    private double pagIbigDeductions;
    private double philHealthDeductions;
    private double taxDeductions;
    private double totalEarnings;
    private double totalDeductions;
    private double taxableIncome;
    private double netPay;

    private User user;
    private Payroll_Admin admin = new Payroll_Admin();

    private int employeeID;


    private Attendance attendance;

    public Payroll(int employeeID, String startDate, String endDate) {
        this.employeeID = employeeID;
        user = new User(employeeID);
        attendance = new Attendance(employeeID, startDate, endDate);
        payrollRun();
    }


    public Payroll() {

    }

    private static List<String[]> payslips = Arrays.asList(
            new String[]{"Payslip 1", "06/03/2024", "06/09/2024"},
            new String[]{"Payslip 2", "06/10/2024", "06/16/2024"},
            new String[]{"Payslip 3", "06/17/2024", "06/23/2024"},
            new String[]{"Payslip 4", "06/24/2024", "06/30/2024"},
            new String[]{"Payslip 5", "07/01/2024", "07/07/2024"},
            new String[]{"Payslip 6", "07/08/2024", "07/14/2024"},
            new String[]{"Payslip 7", "07/15/2024", "07/21/2024"},
            new String[]{"Payslip 8", "07/22/2024", "07/28/2024"},
            new String[]{"Payslip 9", "07/29/2024", "08/04/2024"},
            new String[]{"Payslip 10", "08/05/2024", "08/11/2024"},
            new String[]{"Payslip 11", "08/12/2024", "08/18/2024"},
            new String[]{"Payslip 12", "08/19/2024", "08/25/2024"},
            new String[]{"Payslip 13", "08/26/2024", "09/01/2024"},
            new String[]{"Payslip 14", "09/02/2024", "09/08/2024"},
            new String[]{"Payslip 15", "09/09/2024", "09/15/2024"},
            new String[]{"Payslip 16", "09/16/2024", "09/22/2024"},
            new String[]{"Payslip 17", "09/23/2024", "09/29/2024"},
            new String[]{"Payslip 18", "09/30/2024", "10/06/2024"},
            new String[]{"Payslip 19", "10/07/2024", "10/13/2024"},
            new String[]{"Payslip 20", "10/14/2024", "10/20/2024"},
            new String[]{"Payslip 21", "10/21/2024", "10/27/2024"},
            new String[]{"Payslip 22", "10/28/2024", "11/03/2024"},
            new String[]{"Payslip 23", "11/04/2024", "11/10/2024"},
            new String[]{"Payslip 24", "11/11/2024", "11/17/2024"},
            new String[]{"Payslip 25", "11/18/2024", "11/24/2024"},
            new String[]{"Payslip 26", "11/25/2024", "12/01/2024"},
            new String[]{"Payslip 27", "12/02/2024", "12/08/2024"},
            new String[]{"Payslip 28", "12/09/2024", "12/15/2024"},
            new String[]{"Payslip 29", "12/16/2024", "12/22/2024"},
            new String[]{"Payslip 30", "12/23/2024", "12/29/2024"},
            new String[]{"Payslip 31", "12/30/2024", "12/31/2024"}
    );

    private void payrollRun() {
        lateDeductionCalc();
        overTimeCalc();
        basicPayCalc();
        sssDeductionCalc();
        pagIbigDeductionCalc();
        philhealthDeductionCalc();
        taxDeductionCalc();
        netPayCalc();
    }

    private double lateDeductionCalc() {
        return lateDeductions = round((attendance.getTotalLateMinutes() / 60.0) * user.getHourlyRate());
    }
    private double overTimeCalc() {
        return overTimeAdditions = round((attendance.getTotalOvertimeMinutes() / 60.0) * user.getHourlyRate());
    }
    private double basicPayCalc() {
        return basicPay = round((attendance.getTotalWorkMinutes() / 60.0) * user.getHourlyRate());
    }
    private double sssDeductionCalc() {
        int[] sssContributionMin = { 3250, 3750, 4250, 4750, 5250, 5750, 6250, 6750, 7250, 7750, 8250, 8750, 9250, 9750,
                10250, 10750, 11250, 11750, 12250, 12750, 13250, 13750, 14250, 14750, 15250, 15750, 16250, 16750, 17250,
                17750, 18250, 18750, 19250, 19750, 20250, 20750, 21250, 21750, 22250, 22750, 23250, 23750, 24250 };
        int[] sssContributionMax = { 3750, 4250, 4750, 5250, 5750, 6250, 6750, 7250, 7750, 8250, 8750, 9250, 9750,
                10250, 10750, 11250, 11750, 12250, 12750, 13250, 13750, 14250, 14750, 15250, 15750, 16250, 16750, 17250,
                17750, 18250, 18750, 19250, 19750, 20250, 20750, 21250, 21750, 22250, 22750, 23250, 23750, 24250,
                24750 };
        double[] sssContributionValue = { 157.50, 180.00, 202.50, 225.00, 247.50, 270.00, 292.50, 315.00, 337.50,
                360.00, 382.50, 405.00, 427.50, 450.00, 472.50, 495.00, 517.50, 540.00, 562.50, 585.00, 607.50, 630.00,
                652.50, 675.00, 697.50, 720.00, 742.50, 765.00, 787.50, 810.00, 832.50, 855.00, 877.50, 900.00, 922.50,
                945.00, 967.50, 990.00, 1012.50, 1035.00, 1057.50, 1080.00, 1102.50 };


        double salary = user.getBasicSalary();

        if (salary < sssContributionMin[0]) {
            return sssDeductions = 135.00;
        } else if (salary > sssContributionMax[sssContributionMax.length - 1]) {
            return sssDeductions = 1125.00;
        } else {
            for (int i = 0; i < sssContributionMin.length; i++) {
                if (salary >= sssContributionMin[i] && salary <= sssContributionMax[i]) {
                    return sssDeductions = sssContributionValue[i];
                }
            }
        }
        return sssDeductions=0.0;
    }
    private double pagIbigDeductionCalc() {
        if (user.getBasicSalary() > 1500)
            return pagIbigDeductions = user.getBasicSalary() * 0.02;
        if (user.getBasicSalary() >= 1000)
            return pagIbigDeductions = user.getBasicSalary() * 0.01;
        return 0.0;
    }
    private double philhealthDeductionCalc() {
        return philHealthDeductions = user.getBasicSalary() * 0.03;
    }
    private double taxDeductionCalc() {
        taxableIncomeCalc();
        if (taxableIncome > 4807 && taxableIncome < 7691) {
            return taxDeductions = (taxableIncome - 4808) * 0.20;
        } else if (taxableIncome > 7691 && taxableIncome < 15384) {
            return taxDeductions = 2500 + (taxableIncome - 7692) * 0.25;
        } else if (taxableIncome > 15384 && taxableIncome < 38461) {
            return taxDeductions = 10833 + (taxableIncome - 15385) * 0.30;
        } else if (taxableIncome > 38461 && taxableIncome < 153845) {
            return taxDeductions = 40833.33 + (taxableIncome - 38462) * 0.32;
        } else if (taxableIncome >= 153846) {
            return taxDeductions = 200833.33 + (taxableIncome - 153846) * 0.35;
        } else {
            return taxDeductions = 0.0;
        }
    }

    private void totalEarningsCalc() {
        totalEarnings = (basicPay + overTimeAdditions);
    }
    private void totalDeductionsCalc() {
        totalDeductions = (lateDeductions + (sssDeductions/4.33) + (pagIbigDeductions/4.33) + (philHealthDeductions/4.33) + taxDeductions);
    }

    private void taxableIncomeCalc() {
        taxableIncome = (basicPay + overTimeAdditions - lateDeductions) - (sssDeductions + pagIbigDeductions + philHealthDeductions);
    }
    private void netPayCalc() {
        totalEarningsCalc();
        totalDeductionsCalc();
        netPay = round(totalEarnings - totalDeductions);
    }

    private double round(double val) {
        return new BigDecimal(val).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public double getLateDeductions() {
        return lateDeductions;
    }

    public double getOverTimeAdditions() {
        return overTimeAdditions;
    }

    public double getBasicPay() {
        return basicPay;
    }

    public double getSssDeductions() {
        return sssDeductions;
    }

    public double getPagIbigDeductions() {
        return pagIbigDeductions;
    }

    public double getPhilHealthDeductions() {
        return philHealthDeductions;
    }

    public double getTaxDeductions() {
        return taxDeductions;
    }

    public double getTotalEarnings() {
        return totalEarnings;
    }

    public double getTotalDeductions() {
        return totalDeductions;
    }

    public double getTaxableIncome() {
        return taxableIncome;
    }

    public double getNetPay() {
        return netPay;
    }
    public List<String[]> getPayslips() {
        return payslips;
    }

}
