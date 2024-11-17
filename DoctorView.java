import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class DoctorView {
    public static void doctorView(Doctor doctor, HMSDatabase database) {
        Scanner scanner = new Scanner(System.in);
        ApptManager apptManager = database.getApptManager();
        
        while (true) {
            System.out.println("\n======================================");
            System.out.println("         --- Doctor Menu ---          ");
            System.out.println("======================================");
            System.out.printf("| %-2s | %-30s |\n", "1", "View Patient Medical Record");
            System.out.printf("| %-2s | %-30s |\n", "2", "Update Patient Medical Record");
            System.out.printf("| %-2s | %-30s |\n", "3", "View Personal Schedule");
            System.out.printf("| %-2s | %-30s |\n", "4", "Set Availability");
            System.out.printf("| %-2s | %-30s |\n", "5", "Accept Appointment");
            System.out.printf("| %-2s | %-30s |\n", "6", "Decline Appointment");
            System.out.printf("| %-2s | %-30s |\n", "7", "View Upcoming Appointments");
            System.out.printf("| %-2s | %-30s |\n", "8", "Record Appointment Outcome");
            System.out.printf("| %-2s | %-30s |\n", "9", "Exit");
            System.out.printf("| %-2s | %-30s |\n", "10", "Logout");
            System.out.println("======================================");
            System.out.print("Choose an option: ");
            
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input. Please enter a number between 1 and 10.");
                continue; // Restart the menu loop
            }

            switch (choice) {

                // ------------------------ View Patient Medical Record ------------------------
                case 1:
                    System.out.print("Enter Patient ID to view their record: ");
                    String patientIdForView = scanner.nextLine();
                    doctor.viewPatientMedicalRecord(patientIdForView, database);
                    break;

                // ------------------------ Update Patient Record ------------------------    
                case 2: // Update Patient Record
                    System.out.print("Enter Patient ID: ");
                    String patientIdForUpdate = scanner.nextLine();
                    doctor.updatePatientRecord(patientIdForUpdate, database);
                    break;
                
                // ------------------------ View Personal Schedule ------------------------
                case 3:
                    System.out.print("Enter the date to view your schedule (dd-MMM, e.g., 17-Nov): ");
                    String scheduleDate = scanner.nextLine().trim(); // Collect the date once

                    // Validate input date format
                    while (!scheduleDate.matches("\\d{2}-[A-Za-z]{3}")) {
                        System.out.print("Invalid format. Please enter the date in the format (dd-MMM, e.g., 17-Nov): ");
                        scheduleDate = scanner.nextLine().trim();
                    }

                    // Pass the valid scheduleDate directly to the AppointmentManager
                    apptManager.viewPersonalScheduleForDate(doctor, scheduleDate, database);
                    break;
                
                // ------------------------ Set Availability ------------------------
    
                case 4:
                    apptManager.setAvailability(doctor, database);
                    break;
                
                // ------------------------ Accept Appointment ------------------------
                case 5:
                    System.out.println("--- PENDING Appointments for Dr. " + doctor.getName() + " ---");
                    ArrayList<Appointment> pendingAppointments = new ArrayList<>();

                    // Fetch all PENDING appointments for the doctor
                    for (Appointment appt : database.getAppointments()) {
                        if (appt.getDoctor().getUserId().equals(doctor.getUserId()) && appt.getStatus() == Status.PENDING) {
                            pendingAppointments.add(appt);
                        }
                    }

                    if (pendingAppointments.isEmpty()) {
                        System.out.println("No PENDING appointments available.");
                        break; // Exit this case if no appointments are found
                    }

                    // Display the pending appointments in a formatted table
                    String header = String.format("| %-15s | %-15s | %-15s | %-10s | %-10s |",
                                                "Appointment ID", "Patient ID", "Patient Name", "Date", "Time");
                    String separator = "=".repeat(header.length());
                    System.out.println(separator);
                    System.out.println(header);
                    System.out.println(separator);

                    for (Appointment appt : pendingAppointments) {
                        System.out.printf("| %-15d | %-15s | %-15s | %-10s | %-10s |\n",
                                        appt.getAppointmentID(),
                                        appt.getPatient() != null ? appt.getPatient().getUserId() : "N/A",
                                        appt.getPatient() != null ? appt.getPatient().getName() : "N/A",
                                        appt.getDate(),
                                        appt.getTime());
                    }
                    System.out.println(separator);

                    // Prompt the doctor to enter an appointment ID to accept
                    int acceptID;
                    while (true) {
                        System.out.print("Enter appointment ID to accept: ");
                        try {
                            acceptID = Integer.parseInt(scanner.nextLine().trim());
                            break; // Valid input, exit the loop
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid numeric Appointment ID.");
                        }
                    }

                    // Call the method to accept the appointment
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
                        }
                    }

                    if (pendingAppointmentsToDecline.isEmpty()) {
                        System.out.println("No PENDING appointments available.");
                        break; // Exit this case if no appointments are found
                    }

                    // Display the pending appointments in a formatted table
                    String header1 = String.format("| %-15s | %-15s | %-15s | %-10s | %-10s |", 
                                                "Appointment ID", "Patient ID", "Patient Name", "Date", "Time");
                    String separator1 = "=".repeat(header1.length());
                    System.out.println(separator1);
                    System.out.println(header1);
                    System.out.println(separator1);

                    for (Appointment appt : pendingAppointmentsToDecline) {
                        System.out.printf("| %-15d | %-15s | %-15s | %-10s | %-10s |\n",
                                        appt.getAppointmentID(),
                                        appt.getPatient() != null ? appt.getPatient().getUserId() : "N/A",
                                        appt.getPatient() != null ? appt.getPatient().getName() : "N/A",
                                        appt.getDate(),
                                        appt.getTime());
                    }
                    System.out.println(separator1);

                    // Prompt the doctor to enter an appointment ID to decline
                    int declineID;
                    while (true) {
                        System.out.print("Enter appointment ID to decline: ");
                        try {
                            declineID = Integer.parseInt(scanner.nextLine().trim());
                            break; // Valid input, exit the loop
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid numeric Appointment ID.");
                        }
                    }

                    // Call the method to decline the appointment
                    apptManager.declineAppointment(declineID, doctor, database);
                    break;


                    
                // ------------------------ View Upcoming Appointments ------------------------         
                case 7:  
                    apptManager.viewUpcomingAppointments(doctor, database);
                    break;


                // ------------------------ Record Appointment Outcome ------------------------
                case 8: // Record Appointment Outcome
                    System.out.println("\n--- CONFIRMED Appointments for Dr. " + doctor.getName() + " ---");

                    // Fetch all CONFIRMED appointments for the doctor
                    ArrayList<Appointment> confirmedAppointments = new ArrayList<>();
                    for (Appointment appt : database.getAppointments()) {
                        if (appt.getDoctor().getUserId().equals(doctor.getUserId()) && appt.getStatus().toString().equalsIgnoreCase("CONFIRMED")) {
                            confirmedAppointments.add(appt);
                        }
                    }

                    // Check if there are no CONFIRMED appointments
                    if (confirmedAppointments.isEmpty()) {
                        System.out.println("No CONFIRMED appointments available.");
                        break; // Exit this case if no appointments are found
                    }

                    // Display the table of CONFIRMED appointments
                    String header2 = String.format("| %-15s | %-15s | %-20s | %-10s | %-10s |", "Appointment ID", "Patient ID", "Patient Name", "Date", "Time");
                    String separator2 = "=".repeat(header2.length());
                    System.out.println(separator2);
                    System.out.println(header2);
                    System.out.println(separator2);

                    for (Appointment appt : confirmedAppointments) {
                        System.out.printf("| %-15d | %-15s | %-20s | %-10s | %-10s |\n",
                            appt.getAppointmentID(),
                            appt.getPatient() != null ? appt.getPatient().getUserId() : "N/A",
                            appt.getPatient() != null ? appt.getPatient().getName() : "N/A",
                            appt.getDate(),
                            appt.getTime());
                    }
                    System.out.println(separator2);

                    // Prompt the doctor to select an appointment to record the outcome
                    int outcomeID = -1;
                    while (outcomeID <= 0) {
                        System.out.print("Enter appointment ID to record outcome: ");
                        try {
                            outcomeID = Integer.parseInt(scanner.nextLine().trim());
                            if (outcomeID <= 0) {
                                System.out.println("Appointment ID must be a positive integer. Please try again.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid Appointment ID.");
                        }
                    }

                    // Call ApptManager to handle the rest of the outcome recording
                    apptManager.recordAppointmentOutcome(outcomeID, null, null, null, null, 0, doctor, database);
                    break;


                case 9:  // Exit
                    database.saveDatabase();
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    return;
                
                case 10:
                    database.saveDatabase();
                    System.out.println("Logging out...");
                    return; // Logout ends this view

                default:
                    System.out.println("\nInvalid option. Please select a valid choice from the menu.");
            }
            }
        }
}