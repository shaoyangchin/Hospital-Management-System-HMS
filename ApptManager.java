import java.util.ArrayList;
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
    // public void schedulePatient(Patient patient, int apptId, ArrayList<Appointment> appts) {
    //     Boolean doesApptIdExist = false;
    //     for (Appointment appt : appts) {
    //         if (appt.getAppointmentID() == apptId && appt.getPatient() == null
    //         && appt.getStatus() == Status.PENDING) {
    //             patient.setAppointment(appt);
    //             appt.setPatient(patient);
    //             appt.setStatus(Status.CONFIRMED);
    //             doesApptIdExist = true;
    //             System.out.println("Appointment scheduled successfully for patient ID " + patient.getPatientId() + " with Doctor " + appt.getDoctor().getName() + " at " + appt.getTimeSlot());
    
    //     // Schedules an appointment for a specific patient and doctor by appointment ID
    //     public void schedulePatient(Patient patient, int apptId, ArrayList<Appointment> appts) {
    //         Boolean doesApptIdExist = false;
    //         for (Appointment appt : appts) {
    //             if (appt.getAppointmentID() == apptId && appt.getPatient().getPatientId().equals(patient.getPatientId())
    //             && appt.getStatus() == Status.PENDING) {
    //                 patient.setAppointment(appt);
    //                 appt.setPatient(patient);
    //                 appt.setStatus(Status.CONFIRMED);
    //                 doesApptIdExist = true;
    //                 System.out.println("Appointment scheduled successfully for patient ID " + patient.getPatientId() + " with Doctor " + appt.getDoctor().getName() + " on "+appt.getDate()+" at " + appt.getTime());
    //                 return;
    //             } else if (appt.getAppointmentID() == apptId 
    //             && appt.getPatient() != null
    //             && appt.getPatient().getPatientId() != patient.getPatientId()) {
    //                 doesApptIdExist = true;
    //                 System.out.println("Error, this appointment slot is not available");
    //             }
    //         }
    //         if (!doesApptIdExist) {
    //             System.out.println("AppointmentId does not exist");
    //         }
    //     }
    
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
                }
                if (!isApptIdEnteredValid) {
                    System.out.println("Error, please choose one of your scheduled appointments.");
                }
            }

            isApptIdEnteredValid = false;
            int newApptId = 0;
            while (!isApptIdEnteredValid) {
                try {
                    System.out.println("\nAvailable appointments: ");
                    patient.viewAvailAppts(appts);
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
                }
                if (!isApptIdEnteredValid) {
                    System.out.println("Error, please choose an available appointment.");
                }
            }
            
            for (Appointment appt : appts) {
                if (appt.getAppointmentID() == oldApptId) {
                    if ((appt.getStatus() == Status.PENDING || appt.getStatus() == Status.CONFIRMED) &&
                    patient.getUserId() == appt.getPatient().getUserId()) {
                        //appt.setPatient(null);
                        appt.setStatus(Status.PENDING);
                        for (Appointment appt2 : appts) {
                            if (appt2.getAppointmentID() == newApptId) {
                                if ((appt2.getStatus() == Status.PENDING || appt2.getStatus() == Status.CONFIRMED)) {
                                    //appt2.setPatient(patient);
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
    // public void cancelPatient(int apptId, Patient patient, ArrayList<Appointment> appts) {
    //     for (Appointment appt : appts) {
    //         if (appt.getAppointmentID() == apptId && 
    //             patient.getUserId().equals(appt.getPatient().getUserId()) &&
    //             appt.getStatus() == Status.CONFIRMED) {
    //             //appt.setPatient(null);
    //             appt.setStatus(Status.PENDING);
    //             System.out.println("Appointment canceled successfully for patient ID " + patient.getPatientId());
    //             return;
    //         } else if (appt.getAppointmentID() == apptId 
    //         && appt.getPatient() != null
    //         && appt.getPatient().getPatientId() != patient.getPatientId()) {
    //             doesApptIdExist = true;
    //             System.out.println("Error, this appointment slot is not available");
    //         }
    //     }
    //     if (!doesApptIdExist) {
    //         System.out.println("AppointmentId does not exist");
    //     }
    // }






    // SY DOCTOR METHODS
    // 3. View Personal Schedule
    public void viewPersonalScheduleForDate(Doctor doctor, String date, HMSDatabase database) {
        ArrayList<Appointment> appointments = database.getAppointments();
        ArrayList<String> schedule = new ArrayList<>();
    
        // Define the 30-minute time slots
        String[] timeSlots = {
            "09:00 AM", "09:30 AM", "10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM",
            "12:00 PM", "12:30 PM", "01:00 PM", "01:30 PM", "02:00 PM", "02:30 PM",
            "03:00 PM", "03:30 PM", "04:00 PM", "04:30 PM", "05:00 PM", "05:30 PM",
            "06:00 PM"
        };
    
        // Initialize all slots as "Free"
        for (String slot : timeSlots) {
            schedule.add("Free");
        }
    
        // Populate schedule with confirmed appointments for the specified doctor and date
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
    
        // Print the schedule in a formatted table
        String header = String.format("| %-10s | %-20s |", "Time", "Appointment");
        String separator = "=".repeat(header.length());
        System.out.println("\n--- Personal Schedule for Dr. " + doctor.getName() + " on " + date + " ---");
        System.out.println(separator);
        System.out.println(header);
        System.out.println(separator);
    
        for (int i = 0; i < timeSlots.length; i++) {
            System.out.printf("| %-10s | %-20s |%n", timeSlots[i], schedule.get(i));
        }
    
        System.out.println(separator); // Closing line of the table
    }
    
    
    
    
    
    
    


    // 4. Set Availability
    public void setAvailability(Doctor doctor, String date, String startTime, String endTime, HMSDatabase database) {
        ArrayList<Availability> availabilities = database.getAvailabilities(); // Load from CSV
    
        // Add new availability
        Availability newAvailability = new Availability(doctor.getUserId(), date, startTime, endTime);
        availabilities.add(newAvailability);
    
        // Save updated availability list back to CSV
        DatabaseHelper.saveAvailabilityList(availabilities);
        System.out.println("Availability added: " + date + " from " + startTime + " to " + endTime);
    }


    // 5. Accept Appointment
    public void acceptAppointment(int appointmentID, Doctor doctor, HMSDatabase database) {
        ArrayList<Appointment> appts = database.getAppointments(); // Load from CSV
        boolean appointmentFound = false;
    
        for (Appointment appt : appts) {
            // Check if appointment matches the ID, doctor, and is PENDING
            if (appt.getAppointmentID() == appointmentID &&
                appt.getDoctor().getUserId().equals(doctor.getUserId()) &&
                appt.getStatus() == Status.PENDING) {
                
                appt.setStatus(Status.CONFIRMED); // Update status to CONFIRMED
                appointmentFound = true;
                System.out.println("Appointment ID " + appointmentID + " has been accepted.");
                break;
            }
        }
    
        if (!appointmentFound) {
            System.out.println("No matching PENDING appointment found for ID: " + appointmentID);
        }
    
        // Save updated appointments back to CSV
        if (appointmentFound) {
            DatabaseHelper.saveDatabase(database);
        }
    }

    // 6. Decline Appointment
    public void declineAppointment(int appointmentID, Doctor doctor, HMSDatabase database) {
        ArrayList<Appointment> appts = database.getAppointments(); // Read from CSV
        boolean appointmentFound = false;
    
        for (Appointment appt : appts) {
            // Check if the appointment matches the ID, doctor, and is PENDING
            if (appt.getAppointmentID() == appointmentID &&
                appt.getDoctor().getUserId().equals(doctor.getUserId()) &&
                appt.getStatus() == Status.PENDING) {
                
                appt.setStatus(Status.DECLINED); // Update status to DECLINED
                appointmentFound = true;
                System.out.println("Appointment ID " + appointmentID + " has been declined.");
                break;
            }
        }
    
        if (!appointmentFound) {
            System.out.println("No matching PENDING appointment found for ID: " + appointmentID);
        }
    
        // Save updated appointments back to CSV
        if (appointmentFound) {
            DatabaseHelper.saveDatabase(database);
        }
    }

    // 7. View Upcoming Appointments
    public void viewUpcomingAppointments(Doctor doctor, HMSDatabase database) {
        ArrayList<Appointment> appts = database.getAppointments(); // Read from CSV using DatabaseHelper
        String doctorId = doctor.getUserId(); // Get the logged-in doctor's ID
        int haveAppt = 0;
    
        System.out.println("--- Upcoming Appointments for Dr. " + doctor.getName() + " ---");
        for (Appointment appt : appts) {
            // Filter by doctor ID and confirmed status
            if (appt.getDoctor().getUserId().equals(doctorId) && appt.getStatus() == Status.CONFIRMED) {
                System.out.println("Appointment ID: " + appt.getAppointmentID());
                System.out.println("Patient Name: " + (appt.getPatient() != null ? appt.getPatient().getName() : "N/A"));
                System.out.println("Date: " + appt.getDate());
                System.out.println("Time: " + appt.getTime());
                System.out.println("-------------------------------");
                haveAppt++;
            }
        }
        if (haveAppt == 0) {
            System.out.println("No upcoming appointments found.");
        }
    }

    // 8. Record Appointment Outcome
    public void recordAppointmentOutcome(int appointmentID, String diagnosis, String prescription, String notes, String service, int quantity, Doctor doctor, HMSDatabase database) {
        ArrayList<Appointment> appts = database.getAppointments();
        ArrayList<MedicalRecord> medicalRecords = database.getRecords();
        boolean appointmentUpdated = false;
    
        for (Appointment appt : appts) {
            // Match appointment by ID, doctor, and CONFIRMED status
            if (appt.getAppointmentID() == appointmentID &&
                appt.getDoctor().getUserId().equals(doctor.getUserId()) &&
                appt.getStatus().toString().equalsIgnoreCase("CONFIRMED")) {
    
                appt.setStatus(Status.PENDING_PHARMACIST); // Update status to COMPLETED
                appointmentUpdated = true;
    
                // Add a new medical record
                MedicalRecord newRecord = new MedicalRecord(
                    diagnosis,
                    prescription,
                    appt.getPatient().getUserId(),
                    appointmentID,
                    notes,
                    service,
                    quantity
                );
                medicalRecords.add(newRecord);
    
                System.out.println("Appointment ID " + appointmentID + " marked as PENDING_PHARMACIST.");
                System.out.println("Medical record updated for Patient ID: " + appt.getPatient().getUserId());
                break;
            }
        }
    
        if (!appointmentUpdated) {
            System.out.println("No matching CONFIRMED appointment found for ID: " + appointmentID);
            return;
        }
    
        // Save updated appointments and medical records back to CSV
        DatabaseHelper.saveDatabase(database);
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
    

    // Add this new method for updating prescription status
    public void updatePrescriptionStatus(int appointmentId, String newStatus) {
        if (dbHelper == null) {
            System.out.println("Error: Database helper is not set");
            return;
        }
    
        // Validate the new status to ensure it's allowed
        if (!isValidPrescriptionStatus(newStatus)) {
            System.out.println("Error: Invalid prescription status. Must be PENDING, DISPENSED, or CANCELLED.");
            return;
        }
    
        // Load all medical records from the CSV file
        ArrayList<MedicalRecord> medicalRecords = dbHelper.initMedicalRecords();
    
        // Search for the medical record that matches the appointment ID
        for (MedicalRecord record : medicalRecords) {
            if (record.getAppointmentId() == appointmentId) {
                // Update the prescription status
                record.setPrescriptionStatus(newStatus);
                System.out.println("Prescription status updated successfully for Appointment ID: " + appointmentId);
    
                // Save the updated medical records back to CSV using dbHelper instance
                dbHelper.saveToCsv(medicalRecords, "data/MedicalRecord_List.csv", DatabaseHelper.medRecFields, 1);
                return;
            }
        }
    
        // If no medical record is found
        System.out.println("Error: No medical record found for Appointment ID: " + appointmentId);
    }
    
    // Helper method to validate prescription status
    private boolean isValidPrescriptionStatus(String status) {
        return status.equalsIgnoreCase("PENDING") ||
               status.equalsIgnoreCase("DISPENSED") ||
               status.equalsIgnoreCase("CANCELLED");
    }
    
}