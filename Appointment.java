

public class Appointment {
    private int appointmentID;
    //private String patientName;
    //private int patientID;
    private Status status;
    private String outcome;
    private Patient patient;
    private Doctor doctor;
    private String date;
    private String time;

    public Appointment(int appointmentID, String date, String time) {
        this.appointmentID = appointmentID;
        this.status = Status.PENDING;
        this.date = date;
        this.time = time;
        this.patient = null;
        this.doctor = null;
        //appts.add(this);
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public void schedule(Patient patient, Doctor doctor) {
        this.patient = patient;
        this.doctor = doctor;
        //this.status = "Confirmed"; 
        System.out.println("Appointment scheduled successfully.");
    }

    @Override
    public String toString() {
        return "Appointment ID: " + appointmentID + ", Patient: " + (patient != null ? (patient.getName()+", Patient ID: "+patient.getPatientID()) : "No patient assigned") 
        + ", Doctor: " + (doctor != null ? (doctor.getName()) : "No doctor assigned") + ", Status: " + status + ", Date: " + date 
        + ", Time: " + time;
    }
}
