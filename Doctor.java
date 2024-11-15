import java.util.List;
import java.util.ArrayList;

public class Doctor extends User {
    private String name;
    private List<Appointment> appointments;
    private String gender;
    private int age;

    public Doctor(String name, String gender, int age, String userId, String password, UserType userType) {
        super(userId, password, userType);
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    // Medical Record Management
    public void getRecordForPatient(List<Patient> patients, String patientId, ApptManager apptManager, HMSDatabase hmsDatabase) {
        for (Patient patient : patients) {
            if (patient.getUserId().equals(patientId)) {
                apptManager.viewPastOutcomes(patient, hmsDatabase);
                return;
            }
        }
        System.out.println("No records found for Patient ID: " + patientId);
    }

    public void updatePatientRecord(List<Patient> patients, String patientId, String diagnosis, String prescription, String treatmentPlan, HMSDatabase hmsDatabase) {
        for (Patient patient : patients) {
            if (patient.getUserId().equals(patientId)) {
                if (!patient.getRecord().isEmpty()) {
                    MedicalRecord recordToUpdate = patient.getRecord().get(0);
                    recordToUpdate.addDiagnosisAndPrescription(diagnosis, prescription);
                    recordToUpdate.setService(treatmentPlan);
                    hmsDatabase.saveDatabase();
                    System.out.println("Updated medical record for patient ID: " + patientId);
                    return;
                } else {
                    System.out.println("No existing medical records for patient ID: " + patientId);
                    return;
                }
            }
        }
        System.out.println("Patient with ID " + patientId + " not found.");
    }

    // Appointment Management
    public void viewSchedule() {
        System.out.println("Doctor's Schedule:");
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    public void setAvailability(List<TimeSlot> availability) {
        // Implementation for setting availability
    }

    public void acceptAppointment(int appointmentID) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID() == appointmentID) {
                appointment.setStatus(Status.CONFIRMED);
                System.out.println("Appointment accepted.");
                return;
            }
        }
        System.out.println("Appointment not found.");
    }

    public void declineAppointment(int appointmentID) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID() == appointmentID) {
                appointment.setStatus(Status.DECLINED);
                System.out.println("Appointment declined.");
                return;
            }
        }
        System.out.println("Appointment not found.");
    }

    public void viewUpcomingAppointments() {
        System.out.println("Upcoming Appointments:");
        for (Appointment appointment : appointments) {
            if (appointment.getStatus() == Status.CONFIRMED) {
                System.out.println(appointment);
            }
        }
    }

    // Appointment Outcome Record
    public void recordAppointmentOutcome(int appointmentID, String date, String serviceType, List<Medicine> medications, String notes) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID() == appointmentID) {
                appointment.setOutcome("Date: " + date + "\nService: " + serviceType + "\nMedications: " + medications + "\nNotes: " + notes);
                System.out.println("Appointment outcome recorded.");
                return;
            }
        }
        System.out.println("Appointment not found.");
    }
}