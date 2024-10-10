import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Doctor {
    private int doctorID;
    private String name;
    private String specialization;
    private List<Appointment> appointments;

    public Doctor(int doctorID, String name, String specialization) {
        this.doctorID = doctorID;
        this.name = name;
        this.specialization = specialization;
        this.appointments = new ArrayList<>();
    }

    public int getDoctorID() {
        return doctorID;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void viewPatientRecords(Patient patient) {
        System.out.println("Viewing medical records for patient ID: " + patient.getPatientID());
        System.out.println(patient.getMedicalRecord());
    }

    public void updatePatientRecord(Patient patient, String diagnosis, String prescription) {
        patient.getMedicalRecord().addDiagnosisAndPrescription(diagnosis, prescription);
        System.out.println("Updated medical record for patient ID: " + patient.getPatientID());
    }

    public void viewSchedule() {
        System.out.println("Doctor's Schedule:");
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    public void setAvailability(List<TimeSlot> availability) {
        // Logic for setting availability (out of scope for now)
        System.out.println("Availability updated.");
    }

    public void acceptAppointment(int appointmentID) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID() == appointmentID) {
                appointment.setStatus("Confirmed");
                System.out.println("Appointment confirmed.");
                return;
            }
        }
        System.out.println("Appointment not found.");
    }

    public void declineAppointment(int appointmentID) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID() == appointmentID) {
                appointment.setStatus("Declined");
                System.out.println("Appointment declined.");
                return;
            }
        }
        System.out.println("Appointment not found.");
    }

    public void recordAppointmentOutcome(int appointmentID, String outcome) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID() == appointmentID) {
                appointment.setOutcome(outcome);
                System.out.println("Appointment outcome recorded.");
                return;
            }
        }
        System.out.println("Appointment not found.");
    }
}
