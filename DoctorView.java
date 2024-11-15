import java.util.List;
import java.util.Scanner;

public class DoctorView {
    public static void doctorView(Doctor doctor, HMSDatabase database) {
        Scanner scanner = new Scanner(System.in);
        ApptManager apptManager = database.getApptManager();
        
        while (true) {
            System.out.println("\n--- Doctor Menu ---");
            System.out.println("1. View Patient Medical Record");
            System.out.println("2. Update Patient Medical Record");
            System.out.println("3. View Personal Schedule");
            System.out.println("4. Set Availability");
            System.out.println("5. Accept Appointment");
            System.out.println("6. Decline Appointment");
            System.out.println("7. View Upcoming Appointments");
            System.out.println("8. Record Appointment Outcome");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:  // View a specific Patient Medical Record
                    System.out.print("Enter Patient ID to view their record: ");
                    String patientIdForView = scanner.nextLine();
                    doctor.getRecordForPatient(database.getAllPatients(), patientIdForView, apptManager, database);
                    break;

                case 2:  // Update a specific Patient Medical Record
                    System.out.print("Enter Patient ID to update their record: ");
                    String patientIdForUpdate = scanner.nextLine();
                    System.out.print("Enter diagnosis: ");
                    String diagnosis = scanner.nextLine();
                    System.out.print("Enter prescription: ");
                    String prescription = scanner.nextLine();
                    System.out.print("Enter treatment plan: ");
                    String treatmentPlan = scanner.nextLine();
                    doctor.updatePatientRecord(database.getAllPatients(), patientIdForUpdate, diagnosis, prescription, treatmentPlan, database);
                    break;

                case 3:  // View Personal Schedule
                    doctor.viewSchedule();
                    break;

                case 4:  // Set Availability
                    // Assuming a method to set availability exists
                    System.out.println("Setting availability is not implemented yet.");
                    break;

                case 5:  // Accept Appointment
                    System.out.print("Enter appointment ID to accept: ");
                    int acceptID = scanner.nextInt();
                    doctor.acceptAppointment(acceptID);
                    break;

                case 6:  // Decline Appointment
                    System.out.print("Enter appointment ID to decline: ");
                    int declineID = scanner.nextInt();
                    doctor.declineAppointment(declineID);
                    break;

                case 7:  // View Upcoming Appointments
                    doctor.viewUpcomingAppointments();
                    break;

                case 8:  // Record Appointment Outcome
                    System.out.print("Enter appointment ID to record outcome: ");
                    int outcomeID = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter date of appointment: ");
                    String date = scanner.nextLine();
                    System.out.print("Enter type of service provided: ");
                    String serviceType = scanner.nextLine();
                    System.out.print("Enter prescribed medications (comma-separated): ");
                    String medicationsInput = scanner.nextLine();
                    List<Medicine> medications = parseMedications(medicationsInput);
                    System.out.print("Enter consultation notes: ");
                    String notes = scanner.nextLine();
                    doctor.recordAppointmentOutcome(outcomeID, date, serviceType, medications, notes);
                    break;

                case 9:  // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static List<Medicine> parseMedications(String medicationsInput) {
        // Assuming a method to parse medications from a comma-separated string
        // This method should create a list of Medicine objects with default status as pending
        return null; // Placeholder
    }
}