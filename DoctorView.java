import java.util.Scanner;

public class DoctorView {

    public static void doctorView(Doctor doctor, HMSDatabase database) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Doctor Menu ---");
            System.out.println("1. View Patient Medical Record");
            System.out.println("2. Update Patient Medical Record");
            System.out.println("3. View Personal Schedule");
            System.out.println("4. Accept Appointment");
            System.out.println("5. Decline Appointment");
            System.out.println("6. Record Appointment Outcome");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:  // View a specific Patient Medical Record
                    System.out.print("Enter Patient ID to view their record: ");
                    String patientIdForView = scanner.nextLine();
                    
                    // Use `getRecordForPatient` to retrieve and display the record
                    MedicalRecord record = doctor.getRecordForPatient(database.getAllPatients(), patientIdForView);
                    if (record != null) {
                        System.out.println("Medical Record for Patient ID " + patientIdForView + ": " + record);
                    }
                    break;

                case 2:  // Update a specific Patient Medical Record
                    System.out.print("Enter Patient ID to update their record: ");
                    String patientIdForUpdate = scanner.nextLine();
                    System.out.print("Enter diagnosis: ");
                    String diagnosis = scanner.nextLine();
                    System.out.print("Enter prescription: ");
                    String prescription = scanner.nextLine();

                    // Use `updatePatientRecord` to update the record
                    doctor.updatePatientRecord(database.getAllPatients(), patientIdForUpdate, diagnosis, prescription);
                    break;

                case 3:  // View Personal Schedule
                    doctor.viewSchedule();
                    break;

                case 4:  // Accept Appointment
                    System.out.print("Enter appointment ID to accept: ");
                    int acceptID = scanner.nextInt();
                    doctor.acceptAppointment(acceptID);
                    break;

                case 5:  // Decline Appointment
                    System.out.print("Enter appointment ID to decline: ");
                    int declineID = scanner.nextInt();
                    doctor.declineAppointment(declineID);
                    break;

                case 6:  // Record Appointment Outcome
                    System.out.print("Enter appointment ID to record outcome: ");
                    int outcomeID = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter outcome: ");
                    String outcome = scanner.nextLine();
                    doctor.recordAppointmentOutcome(outcomeID, outcome);
                    break;

                case 7:  // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
