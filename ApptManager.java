import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ApptManager {
    //private List<Appointment> appts = new ArrayList<>();
    private DatabaseHelper dbHelper;

    // Constructor
    public ApptManager(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void setDatabaseHelper(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }
    
    public ApptManager() {}

    int haveAppt = 0;

    
    public int getNumOfAppts() {
        return haveAppt;
    }

    // Displays all appointments in the system
    public void displayAllAppointments(ArrayList<Appointment> appts) {
        for (Appointment appointment : appts) {
            System.out.println(appointment);
        }
    }

    
        // Schedules an appointment for a specific patient and doctor by appointment ID
        public void schedulePatient(Patient patient, ArrayList<Appointment> appts) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter an available appointment ID: ");
            int apptId = scanner.nextInt();
            scanner.nextLine();
    
    
            Boolean doesApptIdExist = false;
            for (Appointment appt : appts) {
                if ((appt.getPatient() == null && appt.getAppointmentID() == apptId
                && appt.getStatus() == Status.PENDING)) {
                    patient.setAppointment(appt);
                    appt.setStatus(Status.CONFIRMED);
                    appt.setPatient(patient);
                    doesApptIdExist = true;
                    System.out.println("Appointment scheduled successfully for patient ID " + patient.getPatientId() + " with Doctor " + appt.getDoctor().getName() + " on "+appt.getDate()+" at " + appt.getTime());
                    return;
                } 
    
                else if (appt.getPatient() != null && appt.getAppointmentID() == apptId && appt.getPatient().getPatientId().equals(patient.getPatientId())
                && appt.getStatus() == Status.PENDING) {
                    patient.setAppointment(appt);
                    appt.setStatus(Status.CONFIRMED);
                    doesApptIdExist = true;
                    System.out.println("Appointment scheduled successfully for patient ID " + patient.getPatientId() + " with Doctor " + appt.getDoctor().getName() + " on "+appt.getDate()+" at " + appt.getTime());
                    return;
                }
                
                
                else if (appt.getAppointmentID() == apptId 
                && appt.getPatient() != null
                && appt.getPatient().getPatientId() != patient.getPatientId()) {
                    doesApptIdExist = true;
                    System.out.println("Error, this appointment slot is not available");
                }
            }
            if (!doesApptIdExist) {
                System.out.println("AppointmentId does not exist");
            }
        }
    
        // Reschedules a confirmed appointment to a new date and time for a specific patient
    public void reschedulePatient(Patient patient, ArrayList<Appointment> appts) {
        Scanner scanner = new Scanner(System.in);
        Boolean isApptIdEnteredValid = false;
        int oldApptId = 0;
        while (!isApptIdEnteredValid) {
            try {
                System.out.println("\nEnter Appointment ID to reschedule: ");
                oldApptId = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();  // Clear the buffer
            }

            for (Appointment appt : appts) {
                if (appt.getPatient() != null &&
                    patient.getUserId().equals(appt.getPatient().getUserId()) &&
                    appt.getStatus().equals(Status.CONFIRMED) &&
                    appt.getAppointmentID() == oldApptId) {
                        isApptIdEnteredValid = true;
                }
                else if (appt.getPatient() == null &&
                appt.getStatus().equals(Status.CONFIRMED) &&
                appt.getAppointmentID() == oldApptId) {
                    isApptIdEnteredValid = true;
                }
            }
            
            if (!isApptIdEnteredValid) {
                System.out.println("Error, please choose one of your scheduled appointments.");
            }
        }

        // ApptManager apptM = new ApptManager();
        isApptIdEnteredValid = false;
        int newApptId = 0;
        while (!isApptIdEnteredValid) {
            try {
                // System.out.println("\nAvailable appointments: ");
                // patient.viewAvailAppts(appts, apptM, database);
                System.out.println("\nEnter new Appointment ID: ");
                newApptId = scanner.nextInt();
                scanner.nextLine();
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();  // Clear the buffer
            }

            for (Appointment appt : appts) {
                if (appt.getPatient() != null &&
                    patient.getUserId().equals(appt.getPatient().getUserId()) &&
                    appt.getStatus().equals(Status.PENDING) &&
                    appt.getAppointmentID() != oldApptId &&
                    appt.getAppointmentID() == newApptId) {
                        isApptIdEnteredValid = true;
                }
                else if (appt.getPatient() == null &&
                appt.getStatus().equals(Status.PENDING) &&
                appt.getAppointmentID() != oldApptId &&
                appt.getAppointmentID() == newApptId) {
                    isApptIdEnteredValid = true;
            }
            }
            if (!isApptIdEnteredValid) {
                System.out.println("Error, please choose an available appointment.");
            }
        }
        
        for (Appointment appt : appts) {
            if (appt.getAppointmentID() == oldApptId) {
                if ((appt.getStatus() == Status.PENDING || appt.getStatus() == Status.CONFIRMED) &&
                patient.getUserId().equals(appt.getPatient().getUserId())) {
                    appt.setPatient(null);
                    appt.setStatus(Status.PENDING);
                    for (Appointment appt2 : appts) {
                        if (appt2.getAppointmentID() == newApptId) {
                            if ((appt2.getStatus() == Status.PENDING || appt2.getStatus() == Status.CONFIRMED)) {
                                appt2.setPatient(patient);
                                appt2.setStatus(Status.CONFIRMED);
                                System.out.println("Appointment rescheduled successfully to ");
                                System.out.println(appt2);
                                return;
                            } else {
                                System.out.println("Error, this appointment slot is not available");
                            }
                        }
                    }
                } else {
                    System.out.println("Error");
                    break;
                }
            }
        }
    }
    
    

    // Cancels a confirmed appointment for a specific patient
    public void cancelPatient(int apptId, Patient patient, ArrayList<Appointment> appts) {
        boolean doesApptIdExist = false;
        for (Appointment appt : appts) {
            if (appt.getAppointmentID() == apptId && 
                patient.getUserId().equals(appt.getPatient().getUserId()) &&
                appt.getStatus() == Status.CONFIRMED) {
                appt.setPatient(null);
                appt.setStatus(Status.PENDING);
                System.out.println("Appointment canceled successfully for patient ID " + patient.getPatientId());
                return;
            } else if (appt.getAppointmentID() == apptId 
            && appt.getPatient() != null
            && appt.getPatient().getPatientId() != patient.getPatientId()) {
                doesApptIdExist = true;
                System.out.println("Error, this appointment slot is not available");
            }
        }
        if (!doesApptIdExist) {
            System.out.println("AppointmentId does not exist");
        }
    }

    // Views all confirmed appointments for a specific patient
    public void viewScheduled(Patient patient, ArrayList<Appointment> appts) {
        haveAppt = 0;
        // for (Appointment appt : appts) {
        //     if (appt.getPatient() != null &&
        //         patient.getUserId().equals(appt.getPatient().getUserId()) &&
        //         appt.getStatus() == Status.CONFIRMED) {

        //         System.out.println(appt);
        //         haveAppt++;
        //     }
        // }

        String header = String.format("| %-10s | %-20s | %-10s | %-15s | %-10s | %-10s | %-10s | %-10s |",
                "Appt ID", "Patient", "Patient Id", "Doctor", "Doctor Id", "Status", "Date", "Time");
        String separator = "=".repeat(header.length());
        System.out.println(separator);
        System.out.println(header);
        System.out.println(separator);

        for (Appointment appt : appts) {
            if (appt.getPatient() != null &&
                patient.getUserId().equals(appt.getPatient().getUserId()) &&
                appt.getStatus() == Status.CONFIRMED) {
                //System.out.println(appt);

                // Print the medical record in table format
                    System.out.printf("| %-10s | %-20s | %-10s | %-15s | %-10s | %-10s | %-10s | %-10s |\n",
                    appt.getAppointmentID(),
                    (appt.getPatient() != null ? ( patient.getName())
                        : "No patient assigned"),
                    (appt.getPatient() != null ? ( patient.getPatientId())
                        : "NA"),
                    (appt.getDoctor() != null ? (appt.getDoctor().getName()) : "No doctor assigned"),
                    (appt.getDoctor() != null ? (appt.getDoctor().getUserId()) : "NA"),
                    appt.getStatus(),
                    appt.getDate(),
                    appt.getTime()
                );
                haveAppt++;
            }
        }
        System.out.println(separator); // Closing line of the table

        if (haveAppt == 0) {
            System.out.println("No scheduled appointments.");
        }
    }

    public void viewPastOutcomes(Patient patient, HMSDatabase database) {
        int haveAppt = 0;
        ArrayList<Appointment> appts = database.getAppointments();
        ArrayList<MedicalRecord> medicalRecords = database.getRecords();

        // Print table header
        String header = String.format("| %-15s | %-15s | %-15s | %-15s | %-15s | %-20s |",
                "Appointment ID", "Doctor", "Diagnosis", "Prescription", "Service", "Notes");
        String separator = "=".repeat(header.length());
        System.out.println(separator);
        System.out.println(header);
        System.out.println(separator);

        for (MedicalRecord medicalRecord : medicalRecords) {
            if(Objects.equals(patient.getUserId(), medicalRecord.getPatientId())){
                for (Appointment appt : appts) {
                    if (Objects.equals(appt.getAppointmentID(), medicalRecord.getAppointmentId())) {
                        System.out.printf("| %-15d | %-15s | %-15s | %-15s | %-15s | %-20s |\n",
                                medicalRecord.getAppointmentId(),
                                appt.getDoctor().getName(),
                                medicalRecord.getDiagnosis(),
                                medicalRecord.getPrescription(),
                                medicalRecord.getService(),
                                medicalRecord.getNotes());
                        haveAppt++;
                    }
                }

                
            }

        }
        System.out.println(separator); // Closing line of the table

        if (haveAppt == 0) {
            System.out.println("No completed appointments.");
        }
    }





    // SY DOCTOR METHODS
    // 3. View Personal Schedule
    public void viewPersonalScheduleForDate(Doctor doctor, String date, HMSDatabase database) {
        ArrayList<Appointment> appointments = database.getAppointments();
        ArrayList<Availability> availabilities = database.getAvailabilities();
        ArrayList<String> schedule = new ArrayList<>();
    
        // Define the 30-minute time slots
        String[] timeSlots = {
            "09:00 AM", "09:30 AM", "10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM",
            "12:00 PM", "12:30 PM", "01:00 PM", "01:30 PM", "02:00 PM", "02:30 PM",
            "03:00 PM", "03:30 PM", "04:00 PM", "04:30 PM", "05:00 PM", "05:30 PM",
            "06:00 PM"
        };
    
        // Initialize all slots as "Not Available"
        for (String slot : timeSlots) {
            schedule.add("Free");
        }
    
        // Check availability for the doctor on the given date
        for (Availability availability : availabilities) {
            if (availability.getDoctorId().equals(doctor.getUserId()) && availability.getDate().equalsIgnoreCase(date)) {
                String startTime = availability.getStartTime();
                String endTime = availability.getEndTime();
                boolean withinAvailability = false;
    
                for (int i = 0; i < timeSlots.length; i++) {
                    if (timeSlots[i].equalsIgnoreCase(startTime)) {
                        withinAvailability = true; // Mark the start of availability
                    }
                    if (withinAvailability) {
                        schedule.set(i, "Not Available"); // Mark available slots as "Not Available"
                    }
                    if (timeSlots[i].equalsIgnoreCase(endTime)) {
                        withinAvailability = false; // Mark the end of availability
                    }
                }
            }
        }
    
        // Populate appointments into the schedule
        for (Appointment appt : appointments) {
            if (appt.getDoctor().getUserId().equals(doctor.getUserId()) &&
                appt.getDate().equalsIgnoreCase(date) &&
                appt.getStatus() == Status.CONFIRMED) {
                
                String appointmentTime = appt.getTime();
    
                // Find the matching time slot and populate it
                for (int i = 0; i < timeSlots.length; i++) {
                    if (timeSlots[i].equalsIgnoreCase(appointmentTime)) {
                        schedule.set(i, appt.getPatient().getName() + " (" + appt.getPatient().getUserId() + ")");
                        break;
                    }
                }
            }
        }
    
        // Print the schedule in a table format
        System.out.println("\n--- Personal Schedule for Dr. " + doctor.getName() + " on " + date + " ---");
        String header = String.format("| %-10s | %-20s |", "Time", "Appointment");
        String separator = "=".repeat(header.length());
        System.out.println(separator);
        System.out.println(header);
        System.out.println(separator);
    
        for (int i = 0; i < timeSlots.length; i++) {
            System.out.printf("| %-10s | %-20s |\n", timeSlots[i], schedule.get(i));
        }
        System.out.println(separator);
    }
    
    
    


    // 4. Set Availability
    public void setAvailability(Doctor doctor, HMSDatabase database) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Availability> availabilities = database.getAvailabilities(); // Load from CSV
        String date, startTime, endTime;
    
        // Prompt for date with validation
        while (true) {
            System.out.print("Enter date (dd-MMM, e.g., 17-Nov): ");
            date = scanner.nextLine().trim();
            if (date.matches("\\d{2}-[a-zA-Z]{3}")) { // Example: 17-Nov
                break;
            }
            System.out.println("Invalid date format. Please enter a valid date (dd-MMM, e.g., 17-Nov).");
        }
    
        // Prompt for start time with validation
        while (true) {
            System.out.print("Enter start time (hh:mm AM/PM, e.g., 09:00 AM): ");
            startTime = scanner.nextLine().trim();
            if (startTime.matches("^(0[1-9]|1[0-2]):[0-5][0-9] [APap][Mm]$")) { // Example: 09:00 AM
                break;
            }
            System.out.println("Invalid start time format. Please enter a valid start time (hh:mm AM/PM, e.g., 09:00 AM).");
        }

        // Prompt for end time with validation
        while (true) {
            System.out.print("Enter end time (hh:mm AM/PM, e.g., 12:00 PM): ");
            endTime = scanner.nextLine().trim();
            if (endTime.matches("^(0[1-9]|1[0-2]):[0-5][0-9] [APap][Mm]$")) { // Example: 12:00 PM
                break;
            }
            System.out.println("Invalid end time format. Please enter a valid end time (hh:mm AM/PM, e.g., 12:00 PM).");
        }

        // Ensure end time is after start time
        while (startTime.compareTo(endTime) >= 0) {
            System.out.println("End time must be after start time. Please enter the times again.");
            // Re-prompt for start time
            while (true) {
                System.out.print("Enter start time (hh:mm AM/PM, e.g., 09:00 AM): ");
                startTime = scanner.nextLine().trim();
                if (startTime.matches("^(0[1-9]|1[0-2]):[0-5][0-9] [APap][Mm]$")) { // Example: 09:00 AM
                    break;
                }
                System.out.println("Invalid start time format. Please enter a valid start time (hh:mm AM/PM, e.g., 09:00 AM).");
            }
            // Re-prompt for end time
            while (true) {
                System.out.print("Enter end time (hh:mm AM/PM, e.g., 05:00 PM): ");
                endTime = scanner.nextLine().trim();
                if (endTime.matches("^(0[1-9]|1[0-2]):[0-5][0-9] [APap][Mm]$")) { // Example: 12:00 PM
                    break;
                }
                System.out.println("Invalid end time format. Please enter a valid end time (hh:mm AM/PM, e.g., 05:00 PM).");
            }
        }
    
        // Add new availability
        Availability newAvailability = new Availability(doctor.getUserId(), date, startTime, endTime);
        availabilities.add(newAvailability);
    
        // Save updated availability list back to CSV
        DatabaseHelper.saveAvailabilityList(availabilities);
    
        // Display success message in a formatted table
        System.out.println("\n--- Availability Added ---");
        String header = String.format("| %-15s | %-10s | %-10s | %-10s |", "Doctor ID", "Date", "Start Time", "End Time");
        String separator = "=".repeat(header.length());
        System.out.println(separator);
        System.out.println(header);
        System.out.println(separator);
        System.out.printf("| %-15s | %-10s | %-10s | %-10s |\n", doctor.getUserId(), date, startTime, endTime);
        System.out.println(separator);
    }



    // 5. Accept Appointment
    public void acceptAppointment(int appointmentID, Doctor doctor, HMSDatabase database) {
        ArrayList<Appointment> appts = database.getAppointments(); // Load from CSV
        boolean appointmentFound = false;
    
        for (Appointment appt : appts) {
            if (appt.getAppointmentID() == appointmentID &&
                appt.getDoctor().getUserId().equals(doctor.getUserId()) &&
                appt.getStatus() == Status.PENDING) {
    
                appt.setStatus(Status.CONFIRMED); // Update status to CONFIRMED
                appointmentFound = true;
    
                // Display success message in table format
                String header = String.format("| %-15s | %-15s | %-10s | %-10s | %-10s |",
                                               "Appointment ID", "Patient ID", "Date", "Time", "Status");
                String separator = "=".repeat(header.length());
                System.out.println("\n--- Appointment Accepted ---");
                System.out.println(separator);
                System.out.println(header);
                System.out.println(separator);
                System.out.printf("| %-15d | %-15s | %-10s | %-10s | %-10s |\n",
                                  appt.getAppointmentID(),
                                  appt.getPatient() != null ? appt.getPatient().getUserId() : "N/A",
                                  appt.getDate(),
                                  appt.getTime(),
                                  "CONFIRMED");
                System.out.println(separator);
                break;
            }
        }
    
        if (!appointmentFound) {
            System.out.println("No matching PENDING appointment found for ID: " + appointmentID);
        }
    
        // Save updated appointments back to CSV
        if (appointmentFound) {
            DatabaseHelper.saveDatabase(database);
            System.out.println("Changes saved to Appointment_List.csv.");
        }
    }
    

    
    // 6. Decline Appointment
    public void declineAppointment(int appointmentID, Doctor doctor, HMSDatabase database) {
        ArrayList<Appointment> appts = database.getAppointments(); // Read from CSV
        boolean appointmentFound = false;
    
        for (Appointment appt : appts) {
            if (appt.getAppointmentID() == appointmentID &&
                appt.getDoctor().getUserId().equals(doctor.getUserId()) &&
                appt.getStatus() == Status.PENDING) {
    
                appt.setStatus(Status.DECLINED); // Update status to DECLINED
                appointmentFound = true;
    
                // Display success message in table format
                String header = String.format("| %-15s | %-15s | %-10s | %-10s | %-10s |",
                                               "Appointment ID", "Patient ID", "Date", "Time", "Status");
                String separator = "=".repeat(header.length());
                System.out.println("\n--- Appointment Declined ---");
                System.out.println(separator);
                System.out.println(header);
                System.out.println(separator);
                System.out.printf("| %-15d | %-15s | %-10s | %-10s | %-10s |\n",
                                  appt.getAppointmentID(),
                                  appt.getPatient() != null ? appt.getPatient().getUserId() : "N/A",
                                  appt.getDate(),
                                  appt.getTime(),
                                  "DECLINED");
                System.out.println(separator);
                break;
            }
        }
    
        if (!appointmentFound) {
            System.out.println("No matching PENDING appointment found for ID: " + appointmentID);
        }
    
        // Save updated appointments back to CSV
        if (appointmentFound) {
            DatabaseHelper.saveDatabase(database);
            System.out.println("Changes saved to Appointment_List.csv.");
        }
    }
    


    // 7. View Upcoming Appointments
    public void viewUpcomingAppointments(Doctor doctor, HMSDatabase database) {
        ArrayList<Appointment> appts = database.getAppointments(); // Read from CSV using DatabaseHelper
        String doctorId = doctor.getUserId(); // Get the logged-in doctor's ID
        int haveAppt = 0;

        // Print the header for the table
        System.out.println("\n--- Upcoming Appointments for Dr. " + doctor.getName() + " ---");
        String header = String.format("| %-15s | %-20s | %-15s | %-10s |", "Appointment ID", "Patient Name", "Date", "Time");
        String separator = "=".repeat(header.length());
        System.out.println(separator);
        System.out.println(header);
        System.out.println(separator);

        // Iterate through appointments and filter confirmed ones for the logged-in doctor
        for (Appointment appt : appts) {
            if (appt.getDoctor().getUserId().equals(doctorId) && appt.getStatus() == Status.CONFIRMED) {
                // Print each appointment in a row
                System.out.printf("| %-15d | %-20s | %-15s | %-10s |\n",
                                appt.getAppointmentID(),
                                appt.getPatient() != null ? appt.getPatient().getName() : "N/A",
                                appt.getDate(),
                                appt.getTime());
                haveAppt++;
            }
        }
        System.out.println(separator); // End of the table

        // If no appointments are found, display a message
        if (haveAppt == 0) {
            System.out.println("No upcoming appointments found.");
        }
    }


    // 8. Record Appointment Outcome
    public void recordAppointmentOutcome(int appointmentID, String diagnosis, String prescription, String notes, String service, int quantity, Doctor doctor, HMSDatabase database) {
        ArrayList<Appointment> appts = database.getAppointments();
        ArrayList<MedicalRecord> medicalRecords = database.getRecords();
    
        // Verify the selected appointment
        Appointment selectedAppointment = null;
        for (Appointment appt : appts) {
            if (appt.getAppointmentID() == appointmentID &&
                appt.getDoctor().getUserId().equals(doctor.getUserId()) &&
                appt.getStatus().toString().equalsIgnoreCase("CONFIRMED")) {
                selectedAppointment = appt;
                break;
            }
        }
    
        if (selectedAppointment == null) {
            System.out.println("No matching CONFIRMED appointment found for ID: " + appointmentID);
            return;
        }
    
        // Prompt for outcome details with validations
        System.out.println("\n--- Record Outcome Details ---");
        Scanner scanner = new Scanner(System.in);
    
        while (true) {
            try {
                System.out.print("Enter Diagnosis: ");
                diagnosis = scanner.nextLine().trim();
                if (diagnosis.isEmpty()) throw new IllegalArgumentException("Diagnosis cannot be empty.");
    
                System.out.print("Enter Prescription: ");
                prescription = scanner.nextLine().trim();
                if (prescription.isEmpty()) throw new IllegalArgumentException("Prescription cannot be empty.");
    
                System.out.print("Enter Notes: ");
                notes = scanner.nextLine().trim();
                if (notes.isEmpty()) throw new IllegalArgumentException("Notes cannot be empty.");
    
                System.out.print("Enter Service Provided: ");
                service = scanner.nextLine().trim();
                if (service.isEmpty()) throw new IllegalArgumentException("Service cannot be empty.");
    
                System.out.print("Enter Quantity Provided: ");
                String quantityInput = scanner.nextLine().trim();
                if (quantityInput.isEmpty()) throw new IllegalArgumentException("Quantity cannot be empty.");
                quantity = Integer.parseInt(quantityInput);
                if (quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than 0.");
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for quantity. Please enter a valid integer.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    
        // Update the appointment and add the medical record
        selectedAppointment.setStatus(Status.PENDING_PHARMACIST);
        MedicalRecord newRecord = new MedicalRecord(
            diagnosis,
            prescription,
            selectedAppointment.getPatient().getUserId(),
            appointmentID,
            notes,
            service,
            quantity
        );
        medicalRecords.add(newRecord);
    
        System.out.println("\nAppointment ID " + appointmentID + " marked as PENDING_PHARMACIST.");
        System.out.println("Medical record updated for Patient ID: " + selectedAppointment.getPatient().getUserId());
    
        // Save updated appointments and medical records back to CSV
        DatabaseHelper.saveDatabase(database);
        System.out.println("Changes saved to the database.");
    }
    
    
    
    // END OF DONE

    
    

    
    

    // Cancels a confirmed appointment for a specific patient
    // public void cancelPatient(int apptId, Patient patient, ArrayList<Appointment> appts) {
    //     for (Appointment appt : appts) {
    //         if (appt.getAppointmentID() == apptId && 
    //             patient.getUserId().equals(appt.getPatient().getUserId()) &&
    //             appt.getStatus() == Status.CONFIRMED) {
    //             appt.setPatient(null);
    //             appt.setStatus(Status.PENDING);
    //             System.out.println("Appointment canceled successfully for patient ID " + patient.getPatientId());
    //             return;
    //         }
    //     }
    //     System.out.println("Error canceling: Appointment with ID " + apptId + " not found or unauthorized access.");
    // }

    

    // Views all completed appointments and their outcomes for a specific patient
    // Add this method to ApptManager class
    public void viewPendingPharmacyAppointments(HMSDatabase database) {
        ArrayList<MedicalRecord> medicalRecords = database.getRecords();
        ArrayList<Appointment> appointments = database.getAppointments();
        
        System.out.println("\nViewing all Pending Pharmacy Appointments:");
        System.out.println("----------------------------------------");

        boolean found = false;
        for (Appointment appt : appointments) {
            if (appt.getStatus() == Status.PENDING_PHARMACIST) {
                // Find corresponding medical record
                MedicalRecord matchingRecord = null;
                for (MedicalRecord record : medicalRecords) {
                    if (record.getAppointmentId() == appt.getAppointmentID()) {
                        matchingRecord = record;
                        break;
                    }
                }

                if (matchingRecord != null) {
                    System.out.println("Appointment ID: " + appt.getAppointmentID());
                    System.out.println("Date: " + appt.getDate());
                    System.out.println("Patient: " + appt.getPatient().getName() + " (ID: " + appt.getPatient().getUserId() + ")");
                    System.out.println("Doctor: " + appt.getDoctor().getName());
                    System.out.println("Prescription: " + matchingRecord.getPrescription());
                    System.out.println("Quantity: " + matchingRecord.getQuantity());
                    System.out.println("----------------------------------------");
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No pending pharmacy appointments found.");
        }
    }

    // Add this new method for updating prescription status
    public void dispenseMedicinePharmacist(int appointmentId, HMSDatabase database) {
        if (dbHelper == null) {
            System.out.println("Error: Database helper is not set");
            return;
        }
    
        // Fetch the lists from the database instead of initializing new lists
        ArrayList<MedicalRecord> medicalRecords = database.getRecords();
        ArrayList<Medicine> medicines = database.getMedicines();
        ArrayList<Appointment> appointments = database.getAppointments();
    
        // Find the medical record corresponding to the appointment ID
        MedicalRecord targetRecord = null;
        for (MedicalRecord record : medicalRecords) {
            if (record.getAppointmentId() == appointmentId) {
                targetRecord = record;
                break;
            }
        }
    
        // If no record found, print error and return
        if (targetRecord == null) {
            System.out.println("Error: No medical record found for Appointment ID: " + appointmentId);
            return;
        }
    
        // Get the prescription details
        String medicineName = targetRecord.getPrescription();
        int quantityToDispense = targetRecord.getQuantity();
    
        // Find the corresponding medicine in the inventory
        Medicine targetMedicine = null;
        for (Medicine medicine : medicines) {
            if (medicine.getName().equalsIgnoreCase(medicineName)) {
                targetMedicine = medicine;
                break;
            }
        }
    
        // If no medicine found, print error and return
        if (targetMedicine == null) {
            System.out.println("Error: Medicine " + medicineName + " not found in inventory.");
            return;
        }
    
        // Check if there is enough stock to dispense
        if (targetMedicine.getQuantity() >= quantityToDispense) {
            // Dispense the medicine and update the inventory
            targetMedicine.setQuantity(targetMedicine.getQuantity() - quantityToDispense);
            System.out.println("Successfully dispensed " + quantityToDispense + " of " + medicineName + ". Updated inventory.");
    
            // Update the appointment status to COMPLETED
            for (Appointment appt : appointments) {
                if (appt.getAppointmentID() == appointmentId && appt.getStatus() == Status.PENDING_PHARMACIST) {
                    appt.setStatus(Status.COMPLETED);
                    break;
                }
            }
    
            // Save changes using database
        dbHelper.saveToCsv(appointments, "data/Appointment_List.csv", Arrays.asList("AppointmentID", "Status", "PatientID", "DoctorID", "Date", "Time"), 0);
        dbHelper.saveToCsv(medicines, "data/Medicine_List.csv", Arrays.asList("MedicineName", "Quantity", "Threshold"), 5);
        System.out.println("Appointment and Medicine CSV files updated successfully");

        } else {
            // If not enough stock, print a warning message
            System.out.println("Error: Not enough stock to dispense " + medicineName + ". Required: " + quantityToDispense + ", Available: " + targetMedicine.getQuantity());
        }
    }
    
    
}