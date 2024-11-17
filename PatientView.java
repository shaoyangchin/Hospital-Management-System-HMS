import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PatientView {

    public static void patientView(Patient patient, HMSDatabase database) {
        Scanner scanner = new Scanner(System.in);
        ApptManager apptManager = database.getApptManager();
        ApptManager apptM = new ApptManager();

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

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline
            
                switch (choice) {
                    case 1:
                        patient.viewMedicalRecord(patient, database);
                        break;
                    case 2:
                        System.out.print("Enter email: ");
                        String contact = scanner.nextLine();
                        System.out.print("Enter phone number: ");
                        String phone = scanner.nextLine();

                        patient.updateRecord(patient, contact, phone);

                        System.out.println("Updated medical record for patient ID: ");
                        patient.viewMedicalRecord(patient, database);
                        break;
                    case 3:
                        patient.viewAvailAppts(database.getAppointments(), apptM, database, patient);
                        break;
                    case 4:
                        System.out.println("Enter an available appointment ID: ");
                        int apptId = scanner.nextInt();
                        scanner.nextLine();

                        //MIGRATE TO PATIENT CLASS
                        /*public void displayDoctorList() {
                            for (Doctor doc : doctors) {
                                System.out.println("Doctor ID: "+doc.getDoctorID()+", Name: "+doc.getName());
                            }
                        }

                        main.displayDoctorList();*/

                        //TEMP
                        // ArrayList<Doctor> doctors= database.getAllDoctors();
                        // for (Doctor doc : doctors) {
                        //     System.out.println("Doctor ID: "+doc.getUserId()+", Name: "+doc.getName());
                        // }


                        // System.out.println("Enter Doctor ID: ");
                        // String doctorId = scanner.nextLine();

                        // Doctor doc1 = database.getDoctorById(doctorId);
                        patient.scheduleAppt(patient, apptId, apptM, database.getAppointments());
                        break;
                    case 5:
                        System.out.println("Your scheduled appointments: ");
                        patient.viewScheduledAppts(patient, apptM, database.getAppointments());
                        if (apptM.getNumOfAppts() == 0) {
                            break;
                        }
                    
                    patient.rescheduleAppt(patient, apptM, database.getAppointments());
                        break;
                    case 6:
                        System.out.println("Your scheduled appointments: ");
                        patient.viewScheduledAppts(patient, apptM, database.getAppointments());
                        if (apptM.getNumOfAppts() == 0) {
                            break;
                        }

                        System.out.println("Enter Appointment ID to cancel: ");
                        apptId = scanner.nextInt();
                        scanner.nextLine();

                        patient.cancelAppt(apptId, patient, apptM, database.getAppointments());
                        break;
                    case 7:
                        patient.viewScheduledAppts(patient, apptM, database.getAppointments());
                        break;
                    case 8:
                        patient.viewPastApptOutcomes(patient, apptM, database);
                        break;
                    case 9:
                        database.saveDatabase();
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid option. Please try again.");

                }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();  // Clear the buffer
            }
        }
    }
}
