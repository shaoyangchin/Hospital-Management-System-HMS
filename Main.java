import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Doctor doctor = new Doctor(1, "Dr. Smith", "Cardiologist");
        MedicalRecord mr1 = new MedicalRecord("cancer", "chemotherapy", "john56@gmail.com", "80913772");
        Patient patient = new Patient(101, "John Doe", mr1);
        MedicalRecord mr2 = new MedicalRecord("cancer", "chemotherapy", "jane56@gmail.com", "80913773");
        Patient patient2 = new Patient(102, "Jane Doe", mr2);
        
        // Sample appointments
        Appointment appointment1 = new Appointment(1001, "John Doe", 101);
        Appointment appointment2 = new Appointment(1002, "Jane Doe", 102);
        doctor.getAppointments().add(appointment1);
        doctor.getAppointments().add(appointment2);
        
        while (true) {
            System.out.println("\n--- Doctor Menu ---");
            System.out.println("1. View Patient Medical Records");
            System.out.println("2. Update Patient Medical Records");
            System.out.println("3. View Personal Schedule");
            System.out.println("4. Accept Appointment");
            System.out.println("5. Decline Appointment");
            System.out.println("6. Record Appointment Outcome");
            System.out.println("7. Exit");
            // Each user in the HMS will have a specific menu with 
            // options relevant to their role upon login
            System.out.println("\n--- Patient Menu ---");
            System.out.println("8. View Medical Record");
            System.out.println("9. Update Personal Information");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    doctor.viewPatientRecords(patient);
                    break;
                case 2:
                    System.out.print("Enter diagnosis: ");
                    String diagnosis = scanner.nextLine();
                    System.out.print("Enter prescription: ");
                    String prescription = scanner.nextLine();
                    doctor.updatePatientRecord(patient, diagnosis, prescription);
                    break;
                case 3:
                    doctor.viewSchedule();
                    break;
                case 4:
                    System.out.print("Enter appointment ID to accept: ");
                    int acceptID = scanner.nextInt();
                    doctor.acceptAppointment(acceptID);
                    break;
                case 5:
                    System.out.print("Enter appointment ID to decline: ");
                    int declineID = scanner.nextInt();
                    doctor.declineAppointment(declineID);
                    break;
                case 6:
                    System.out.print("Enter appointment ID to record outcome: ");
                    int outcomeID = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter outcome: ");
                    String outcome = scanner.nextLine();
                    doctor.recordAppointmentOutcome(outcomeID, outcome);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                case 8:
                    patient.viewMedicalRecord(patient2);
                    break;
                case 9:
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNum = scanner.nextLine();
                    patient.updateRecord(patient2, email, phoneNum);
                    break;
                    

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
