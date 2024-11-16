public class Appointment {
    private int appointmentID;
    private Status status;
    private Patient patient;
    private Doctor doctor;
    private String date;
    private String time;

    // Constructor
    public Appointment(int appointmentID, Status status, Patient patient, Doctor doctor, String date, String time) {
        this.appointmentID = appointmentID;
        this.status = status;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
    }

    // Getters and setters (if required)
    public int getAppointmentID() {
        return appointmentID;
    }

    public Status getStatus() {
        return status;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
