import javax.print.Doc;
import java.util.ArrayList;
import java.util.Scanner;

public class MainView {

    public static User login(ArrayList<User> userList) {
        System.out.print("  _    _ __  __  _____ \n" +
                " | |  | |  \\/  |/ ____|\n" +
                " | |__| | \\  / | (___  \n" +
                " |  __  | |\\/| |\\___ \\ \n" +
                " | |  | | |  | |____) |\n" +
                " |_|  |_|_|  |_|_____/ \n\n" );
        System.out.println("\n=====================================");
        System.out.println("         Welcome to the          ");
        System.out.println("   Hospital Management System    ");
        System.out.println("=====================================");
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\u001B[34mEnter your hospital ID: \u001B[0m");
        String userId = scanner.nextLine();
        System.out.print("\u001B[34mEnter your password: \u001B[0m");
        String password = scanner.nextLine();

        for (User user : userList) {
            if (user.validateUser(userId, password)) {
                if (password.equals("Password")) {
                    System.out.println("\n\u001B[33mFirst login detected. Please reset your password.\u001B[0m");
                    user.resetPassword();
                }
                System.out.println("\n\u001B[32mLogin successful! Welcome, " + user.getUserId() + ".\u001B[0m\n");
                return user;
            }
        }
        System.out.println("\n\u001B[31mInvalid login credentials. Please try again.\u001B[0m\n");
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HMSDatabase db = new HMSDatabase();
        while (true) {

            while (true){
                System.out.println("\n\u001B[34m 1. Login \u001B[0m");
                System.out.println("\u001B[34m 2. Exit \u001B[0m");
                System.out.println("\u001B[34m Please Select Option:  \u001B[0m");
                String option = scanner.nextLine();
                if (option.equals("1")) {break;}
                else if (option.equals("2")) { return;}
                else{
                    System.out.println("\n\u001B[31mInvalid Option, Please Try Again.\u001B[0m\n");
                }
            }


            User user = login(db.getUsers());
            if (user == null) {
                continue;
            }
            try {
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
                        System.out.println("\n\u001B[31mUser type error. Please contact system administrator.\u001B[0m\n");
                }
            } catch (Exception e) {
                System.out.println("\n\u001B[31mAn error occurred: " + e.getMessage() + "\u001B[0m\n");
            }
        }
    }
}
