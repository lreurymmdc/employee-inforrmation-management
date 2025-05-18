package features;

import users.User;

public class DisplayProfile {

    public void displayProfile(User user) {
        System.out.println("EMPLOYEE PROFILE");

        System.out.println("\n=== Personal Information ===\n");
        System.out.println("1.\t Full name: " + user.getFullName());
        System.out.println("2.\t Date of Birth: " + user.getBirthday());
        System.out.println("3.\t Address: " + user.getAddress());
        System.out.println("4.\t Phone No.: +" + user.getPhoneNumber());
        System.out.println("5.\t SSS No.: " + user.getSSSNumber());
        System.out.println("6.\t Pagibig No.: " + user.getPagIbigNumber());
        System.out.println("7.\t Philhealth No.: " + user.getPhilHealthNumber());
        System.out.println("8.\t Tax No.: " + user.getTINNumber());


        System.out.println("\n=== Employment Information ===\n");
        System.out.println("9.\t Position: " + user.getPosition());
        System.out.println("10.\t Status: " + user.getStatus());
        System.out.println("11.\t Supervisor: " + user.getImmediateSupervisor());


        System.out.println("\n=== Earnings & Allowances ===\n");
        System.out.println("12.\t Basic Salary: Php " + user.getBasicSalary());
        System.out.println("13.\t Rice Subsidy: Php " + user.getRiceSubsidy());
        System.out.println("14.\t Phone: Php " + user.getPhoneAllowance());
        System.out.println("15.\t Clothing: Php " + user.getClothingAllowance());
        System.out.println("16.\t Hourly Rate: Php " + user.getHourlyRate());

    }

}
