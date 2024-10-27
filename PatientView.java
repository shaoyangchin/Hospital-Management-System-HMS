import java.util.ArrayList;
import java.util.Scanner;

public class PatientView {

    public static void patientView(Patient patient, HMSDatabase database) {
        Scanner scanner = new Scanner(System.in);
        ApptManager apptManager = database.getApptManager();

        while (true) {
            System.out.println("\n--- Patient Menu ---");
            System.out.println("1. View Medical Record");
            System.out.println("2. Update Personal Information");
            System.out.println("3. View Available Appointment Slots");
            System.out.println("4. Schedule an Appointment");
            System.out.println("5. Reschedule an Appointment");
            System.out.println("6. Cancel an Appointment");
            System.out.println("7. View scheduled Appointments");
            System.out.println("8. View Past Appointment Outcome Records");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    patient.viewMedicalRecord(patient);
                    break;
                case 2:
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNum = scanner.nextLine();
                    patient.updateRecord(patient, email, phoneNum);
                    break;
                case 3:
                    patient.viewAvailAppts();
                    break;
                case 4:
                    System.out.println("Enter an available appointment ID: ");
                    int apptId = scanner.nextInt();

                    //MIGRATE TO PATIENT CLASS
                    /*public void displayDoctorList() {
                        for (Doctor doc : doctors) {
                            System.out.println("Doctor ID: "+doc.getDoctorID()+", Name: "+doc.getName());
                        }
                    }

                    main.displayDoctorList();*/

                    //TEMP
                    ArrayList<Doctor> doctors= database.getAllDoctors();
                    for (Doctor doc : doctors) {
                        System.out.println("Doctor ID: "+doc.getUserId()+", Name: "+doc.getName());
                    }


                    System.out.println("Enter Doctor ID: ");
                    String doctorId = scanner.nextLine();

                    Doctor doc1 = database.getDoctorById(doctorId);
                    apptManager.schedulePatient(patient, apptId, doc1);
                    break;
                case 5:
                    System.out.println("Enter Appointment ID to reschedule: ");
                    apptId = scanner.nextInt();
                    patient.rescheduleAppt(apptId, patient);
                    break;
                case 6:
                    System.out.println("Enter Appointment ID to cancel: ");
                    apptId = scanner.nextInt();
                    patient.rescheduleAppt(apptId, patient);
                    break;
                case 7:
                    patient.viewScheduledAppts(patient);
                    break;
                case 8:
                    patient.viewPastApptOutcomes(patient);
                    break;
                case 9:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");

            }
        }
    }
}
