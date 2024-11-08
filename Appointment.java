public class Appointment {
    private int appointmentID;
    private Status status;
    private String outcome;
    private Patient patient;
    private Doctor doctor;
    private String date;
    private String time;

    // Constructor without Patient and Doctor
    public Appointment(int appointmentID, String date, String time) {
        this.appointmentID = appointmentID;
        this.status = Status.PENDING;
        this.date = date;
        this.time = time;
    }

    // Constructor with Patient and Doctor
    public Appointment(int appointmentID, String date, String time, Status status, Patient patient, Doctor doctor) {
        this.appointmentID = appointmentID;
        this.status = status;
        this.date = date;
        this.time = time;
        this.patient = patient;
        this.doctor = doctor;
    }

    // Getter methods
    public int getAppointmentID() {
        return appointmentID;
    }

    public Status getStatus() {
        return status;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    // New setter methods
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Appointment ID: " + appointmentID 
                + ", Patient: " + (patient != null ? (patient.getName() + ", Patient ID: " + patient.getPatientId()) : "No patient assigned") 
                + ", Doctor: " + (doctor != null ? doctor.getName() : "No doctor assigned") 
                + ", Status: " + status 
                + ", Date: " + date 
                + ", Time: " + time;
    }
}
