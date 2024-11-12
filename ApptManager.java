import java.util.ArrayList;
import java.util.List;

public class ApptManager {
    private List<Appointment> appts = new ArrayList<>();

    public ApptManager() {}

    // Adds a new appointment to the list
    public void addAppointment(Appointment appointment) {
        appts.add(appointment);
    }

    // Displays all appointments in the system
    public void displayAllAppointments() {
        for (Appointment appointment : appts) {
            System.out.println(appointment);
        }
    }

    // Displays all available (PENDING) appointments
    public void displayAvailAppointments() {
        for (Appointment appointment : appts) {
            if (appointment.getStatus() == Status.PENDING) {
                System.out.println(appointment);
            }
        }
    }

    // Schedules an appointment for a specific patient and doctor by appointment ID
    public void schedulePatient(Patient patient, int apptId, Doctor doctor) {
        for (Appointment appt : appts) {
            if (appt.getAppointmentID() == apptId) {
                appt.setPatient(patient);
                appt.setDoctor(doctor);
                appt.setStatus(Status.CONFIRMED);
                System.out.println("Appointment scheduled successfully for patient ID " + patient.getPatientId() + " with Doctor " + doctor.getName());
                return;
            }
        }
        System.out.println("Appointment with ID " + apptId + " not found.");
    }

    // Reschedules a confirmed appointment to a new date and time for a specific patient
    public void reschedulePatient(int apptId, Patient patient, String newDate, String newTime) {
        for (Appointment appt : appts) {
            if (appt.getAppointmentID() == apptId && 
                appt.getStatus() == Status.CONFIRMED &&
                patient.getUserId().equals(appt.getPatient().getUserId())) {
                
                appt.setDate(newDate);
                appt.setTime(newTime);
                System.out.println("Appointment rescheduled to " + newDate + " at " + newTime);
                return;
            }
        }
        System.out.println("Error rescheduling: Appointment with ID " + apptId + " not found or unauthorized access.");
    }

    // Cancels a confirmed appointment for a specific patient
    public void cancelPatient(int apptId, Patient patient) {
        for (Appointment appt : appts) {
            if (appt.getAppointmentID() == apptId && 
                patient.getUserId().equals(appt.getPatient().getUserId()) &&
                appt.getStatus() == Status.CONFIRMED) {
                
                appt.setStatus(Status.CANCELLED);
                System.out.println("Appointment canceled successfully for patient ID " + patient.getPatientId());
                return;
            }
        }
        System.out.println("Error canceling: Appointment with ID " + apptId + " not found or unauthorized access.");
    }

    // Views all confirmed appointments for a specific patient
    public void viewScheduled(Patient patient) {
        int haveAppt = 0;
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
    public void viewPastOutcomes(Patient patient) {
        int haveAppt = 0;
        for (Appointment appt : appts) {
            if (appt.getPatient() != null &&
                patient.getUserId().equals(appt.getPatient().getUserId()) &&
                appt.getStatus() == Status.COMPLETED) {
                
                System.out.println(appt);
                haveAppt++;
            }
        }
        if (haveAppt == 0) {
            System.out.println("No completed appointments.");
        }
    }

        // Add this new method for updating prescription status
        public void updatePrescriptionStatus(int apptId, String prescriptionStatus) {
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