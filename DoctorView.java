import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

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


                // ------------------------ View Personal Schedule ------------------------
                case 3:
                    System.out.print("Enter the date to view your schedule (dd-MMM, e.g., 17-Nov): ");
                    String scheduleDate = scanner.nextLine();
                
                    // View personal schedule for the specified date
                    apptManager.viewPersonalScheduleForDate(doctor, scheduleDate, database);
                    break;
                


                // ------------------------ Set Availability ------------------------
    
                case 4:
                    System.out.print("Enter date (dd-MMM, e.g., 17-Nov): ");
                    String date = scanner.nextLine();
                    System.out.print("Enter start time (hh:mm AM/PM, e.g., 09:00 AM): ");
                    String startTime = scanner.nextLine();
                    System.out.print("Enter end time (hh:mm AM/PM, e.g., 12:00 PM): ");
                    String endTime = scanner.nextLine();
                
                    // Add availability for the logged-in doctor
                    apptManager.setAvailability(doctor, date, startTime, endTime, database);
                    break;


                
                // ------------------------ Accept Appointment ------------------------
                case 5:  
                    System.out.println("--- PENDING Appointments for Dr. " + doctor.getName() + " ---");
                    ArrayList<Appointment> pendingAppointments = new ArrayList<>();
                
                    // Fetch all PENDING appointments for the doctor
                    for (Appointment appt : database.getAppointments()) {
                        if (appt.getDoctor().getUserId().equals(doctor.getUserId()) && appt.getStatus() == Status.PENDING) {
                            pendingAppointments.add(appt);
                            System.out.println("Appointment ID: " + appt.getAppointmentID());
                            System.out.println("Patient ID: " + (appt.getPatient() != null ? appt.getPatient().getUserId() : "N/A"));
                            System.out.println("Patient Name: " + (appt.getPatient() != null ? appt.getPatient().getName() : "N/A"));
                            System.out.println("Date: " + appt.getDate());
                            System.out.println("Time: " + appt.getTime());
                            System.out.println("-------------------------------");
                        }
                    }
                
                    // Check if there are no PENDING appointments
                    if (pendingAppointments.isEmpty()) {
                        System.out.println("No PENDING appointments available.");
                        break; // Exit this case if no appointments are found
                    }
                
                    // Prompt the doctor to enter an appointment ID to accept
                    System.out.print("Enter appointment ID to accept: ");
                    int acceptID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    apptManager.acceptAppointment(acceptID, doctor, database);
                    break;
                
                // ------------------------ Decline Appointment ------------------------
                case 6:
                    System.out.println("--- PENDING Appointments for Dr. " + doctor.getName() + " ---");
                    ArrayList<Appointment> pendingAppointmentsToDecline = new ArrayList<>();
                
                    // Fetch all PENDING appointments for the doctor
                    for (Appointment appt : database.getAppointments()) {
                        if (appt.getDoctor().getUserId().equals(doctor.getUserId()) && appt.getStatus() == Status.PENDING) {
                            pendingAppointmentsToDecline.add(appt);
                            System.out.println("Appointment ID: " + appt.getAppointmentID());
                            System.out.println("Patient ID: " + (appt.getPatient() != null ? appt.getPatient().getUserId() : "N/A"));
                            System.out.println("Patient Name: " + (appt.getPatient() != null ? appt.getPatient().getName() : "N/A"));
                            System.out.println("Date: " + appt.getDate());
                            System.out.println("Time: " + appt.getTime());
                            System.out.println("-------------------------------");
                        }
                    }
                
                    // Check if there are no PENDING appointments
                    if (pendingAppointmentsToDecline.isEmpty()) {
                        System.out.println("No PENDING appointments available.");
                        break; // Exit this case if no appointments are found
                    }
                
                    // Prompt the doctor to enter an appointment ID to decline
                    System.out.print("Enter appointment ID to decline: ");
                    int declineID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    apptManager.declineAppointment(declineID, doctor, database);
                    break;

                    
                // ------------------------ View Upcoming Appointments ------------------------         
                case 7:  
                    apptManager.viewUpcomingAppointments(doctor, database);
                    break;


                // ------------------------ Record Appointment Outcome ------------------------
                case 8:  // Record Appointment Outcome
                    System.out.println("--- CONFIRMED Appointments for Dr. " + doctor.getName() + " ---");
                    ArrayList<Appointment> confirmedAppointments = new ArrayList<>();

                    // Fetch all CONFIRMED appointments for the doctor
                    for (Appointment appt : database.getAppointments()) {
                        if (appt.getDoctor().getUserId().equals(doctor.getUserId()) && appt.getStatus().toString().equalsIgnoreCase("CONFIRMED")) {
                            confirmedAppointments.add(appt);
                            System.out.println("Appointment ID: " + appt.getAppointmentID());
                            System.out.println("Patient ID: " + (appt.getPatient() != null ? appt.getPatient().getUserId() : "N/A"));
                            System.out.println("Patient Name: " + (appt.getPatient() != null ? appt.getPatient().getName() : "N/A"));
                            System.out.println("Date: " + appt.getDate());
                            System.out.println("Time: " + appt.getTime());
                            System.out.println("-------------------------------");
                        }
                    }

                    // Check if there are no CONFIRMED appointments
                    if (confirmedAppointments.isEmpty()) {
                        System.out.println("No CONFIRMED appointments available.");
                        break; // Exit this case if no appointments are found
                    }

                    // Prompt the doctor to select an appointment to record the outcome
                    System.out.print("Enter appointment ID to record outcome: ");
                    int outcomeID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    // Verify the selected appointment
                    Appointment selectedAppointment = null;
                    for (Appointment appt : confirmedAppointments) {
                        if (appt.getAppointmentID() == outcomeID) {
                            selectedAppointment = appt;
                            break;
                        }
                    }

                    if (selectedAppointment == null) {
                        System.out.println("Invalid Appointment ID.");
                        break;
                    }

                    // Record outcome details
                    System.out.print("Enter Diagnosis: ");
                    String diagnosis2 = scanner.nextLine();
                    System.out.print("Enter Prescription: ");
                    String prescription2 = scanner.nextLine();
                    System.out.print("Enter Notes: ");
                    String notes = scanner.nextLine();
                    System.out.print("Enter Service Provided: ");
                    String service = scanner.nextLine();

                    // Call ApptManager to update the appointment and medical record
                    apptManager.recordAppointmentOutcome(outcomeID, diagnosis2, prescription2, notes, service, doctor, database);
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

    private static List<TimeSlot> parseAvailability(String availabilityInput) {
        List<TimeSlot> availability = new ArrayList<>();
        String[] slots = availabilityInput.split(",");
        for (String slot : slots) {
            String[] parts = slot.trim().split(" ");
            if (parts.length == 2) {
                availability.add(new TimeSlot(parts[0], parts[1]));
            }
        }
        return availability;
    }

    private static List<Medicine> parseMedications(String medicationsInput) {
        List<Medicine> medications = new ArrayList<>();
        String[] meds = medicationsInput.split(",");
        for (String med : meds) {
            medications.add(new Medicine(med.trim(), med, 0, 0));
        }
        return medications;
    }
}