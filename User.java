import java.util.Objects;
import java.util.Scanner;

public abstract class User {
    private String userId;
    protected String password;
    private AccountType accountType; // Staff or Patient
    private UserType userType; // Patient, Doctor, Pharmacist, Admin
    // public abstract void displayMenu();

    User(String userId, String password, UserType userType) {
        this.userId = userId;
        this.password = password;
        this.userType = userType;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public UserType getUserType() {
        return userType;
    }

    public boolean validateUser(String userId, String password) {
        return Objects.equals(this.userId, userId) && this.password.equals(password);
    }

    public void resetPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("New password: :");
        String newPass = scanner.nextLine();
        this.password = newPass;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public void setHospitalId(String hospitalId) {
        this.userId = hospitalId;
    }
}
