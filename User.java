import java.util.Objects;
import java.util.Scanner;

public abstract class User {
    private String userId;
    protected String password;
    //public abstract void displayMenu();


    User(String userId, String password) {
        this.userId = userId;
        this.password = password;
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
        this.userId = userId;
    }
}
