package features.HR;

import features.DisplayProfile;
import features.Login;
import users.User;

public class SearchEmployee extends DisplayProfile {

    private User employee;
    private boolean found;

    public SearchEmployee(int searchKey, Login login) {
        if (login.validateUserID(searchKey)) {
            this.employee = new User(searchKey);
            this.found = true;
        } else {
            this.found = false;
        }
    }
    public SearchEmployee(int searchKey, boolean found) {
        if (found) {
            this.employee = new User(searchKey);
            this.found = true;
        } else {
            this.found = false;
        }
    }


    public boolean isFound() {
        return found;
    }

    public User getEmployee() {
        return employee;
    }
}
