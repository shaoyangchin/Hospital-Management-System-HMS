import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ApptManager {
    //private List<Appointment> appts = new ArrayList<>();

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
        public void schedulePatient(Patient patient, int apptId, ArrayList<Appointment> appts) {
            Boolean doesApptIdExist = false;
            for (Appointment appt : appts) {
                if (appt.getAppointmentID() == apptId && appt.getPatient().getPatientId().equals(patient.getPatientId())
                && appt.getStatus() == Status.PENDING) {
                    patient.setAppointment(appt);
                    appt.setPatient(patient);
                    appt.setStatus(Status.CONFIRMED);
                    doesApptIdExist = true;
                    System.out.println("Appointment scheduled successfully for patient ID " + patient.getPatientId() + " with Doctor " + appt.getDoctor().getName() + " on "+appt.getDate()+" at " + appt.getTime());
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
    public void cancelPatient(int apptId, Patient patient, ArrayList<Appointment> appts) {
        for (Appointment appt : appts) {
            if (appt.getAppointmentID() == apptId && 
                patient.getUserId().equals(appt.getPatient().getUserId()) &&
                appt.getStatus() == Status.CONFIRMED) {
                //appt.setPatient(null);
                appt.setStatus(Status.PENDING);
                System.out.println("Appointment canceled successfully for patient ID " + patient.getPatientId());
                return;
            }
        }
        System.out.println("Error canceling: Appointment with ID " + apptId + " not found or unauthorized access.");
    }

    // Views all confirmed appointments for a specific patient
    public void viewScheduled(Patient patient, ArrayList<Appointment> appts) {
        haveAppt = 0;
        for (Appointment appt : appts) {
            if (appt.getPatient() != null &&
                patient.getUserId().equals(appt.getPatient().getUserId()) &&
                appt.getStatus() == Status.CONFIRMED) {
                
                System.out.println(appt);
                haveAppt++;
            }
        }
        if (haveAppt == 0) {
            System.out.println("No scheduled appointments.");
        }
    }

    // Views all completed appointments and their outcomes for a specific patient
    public void viewPastOutcomes(Patient patient, HMSDatabase database) {
        int haveAppt = 0;
        ArrayList<Appointment> appts = database.getAppointments();
        ArrayList<MedicalRecord> medicalRecords = database.getRecords();

        for (MedicalRecord medicalRecord : medicalRecords) {
            if(Objects.equals(patient.getUserId(), medicalRecord.getPatientId())){
                for (Appointment appt : appts) {
                    if (Objects.equals(appt.getAppointmentID(), medicalRecord.getAppointmentId())) {
                        System.out.println("Appointment ID: "+medicalRecord.getAppointmentId()+", Doctor: "+appt.getDoctor().getName()+", Date: "+appt.getDate()+", Time: "+appt.getTime());
                        System.out.println("Diagnosis:" + (medicalRecord.getDiagnosis()) );
                        System.out.println("Prescription:" + (medicalRecord.getPrescription()) );
                        System.out.println("Notes:" + (medicalRecord.getNotes()) );
                        System.out.println("Service Provided:" + (medicalRecord.getService()) );
                        System.out.println(); //spacing between each entry
                        haveAppt++;
                    }
                }
                
            }

        }
        if (haveAppt == 0) {
            System.out.println("No completed appointments.");
        }
    }

        // Add this new method for updating prescription status
        public void updatePrescriptionStatus(int apptId, String prescriptionStatus, ArrayList<Appointment> appts) {
            // Input validation
            if (prescriptionStatus == null || prescriptionStatus.trim().isEmpty()) {
                System.out.println("Error: Prescription status cannot be empty");
                return;
            }
        
            // Standardize status format
            String status = prescriptionStatus.trim().toUpperCase();
            if (!isValidPrescriptionStatus(status)) {
                System.out.println("Error: Invalid prescription status. Must be PENDING, DISPENSED, or CANCELLED");
                return;
            }
        
            for (Appointment appt : appts) {
                if (appt.getAppointmentID() == apptId) {
                    if (appt.getStatus() != Status.COMPLETED) {
                        System.out.println("Error: Can only update prescription status for completed appointments.");
                        return;
                    }
        
                    String currentOutcome = appt.getOutcome();
                    String newOutcome;
                    
                    if (currentOutcome == null || currentOutcome.trim().isEmpty()) {
                        newOutcome = "Prescription Status: " + status;
                    } else if (currentOutcome.contains("Prescription Status:")) {
                        newOutcome = currentOutcome.replaceAll(
                            "Prescription Status: .*",
                            "Prescription Status: " + status
                        );
                    } else {
                        newOutcome = currentOutcome + "\nPrescription Status: " + status;
                    }
        
                    appt.setOutcome(newOutcome);
                    System.out.println("Prescription status updated successfully for Appointment ID: " + apptId);
                    return;
                }
            }
            System.out.println("Appointment with ID " + apptId + " not found.");
        }
        
        // Helper method to validate prescription status
        private boolean isValidPrescriptionStatus(String status) {
            return status.equals("PENDING") || 
                   status.equals("DISPENSED") || 
                   status.equals("CANCELLED");
        }
    }