import javax.print.Doc;
import java.util.ArrayList;
import java.util.Scanner;

public class MainView {

    public static User login(ArrayList<User> userList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your hospital ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        boolean valid = false;

        for (User user : userList) {
            if (user.validateUser(userId, password)) {
                valid = true;
                if (user != null) {
                    System.out.println("Login successful! Welcome.");
                }
                return user;
            }
        }
        System.out.println("Invalid login credentials. Please try again.");
        return null;
    }

    public static void main(String[] args) {
        HMSDatabase db = new HMSDatabase();
        User user = login(db.getUsers());
        switch (user.getUserType()) {
            case DOCTOR:
                // run DoctorView
                Doctor doctor = (Doctor) user;
                DoctorView.doctorView(doctor, db);
                break;
            case PATIENT:
                // run PatientView
                Patient patient = (Patient) user;
                PatientView.patientView(patient, db);
                break;
            case PHARMACIST:
                // run PharmacistView
                Pharmacist pharmacist = (Pharmacist) user;
                PharmacistView.pharmacistView(pharmacist, db);
                break;

            case ADMINISTRATOR:
                // run AdminView
                Administrator administrator = (Administrator) user;
                AdminView.AdminView(administrator, db);
                break;
            default:
                System.out.println("User Error");
        }

    }
}
