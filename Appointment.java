public class Appointment {
    private int appointmentID;
    private String patientName;
    private String status;
    private String outcome;

    public Appointment(int appointmentID, String patientName) {
        this.appointmentID = appointmentID;
        this.patientName = patientName;
        this.status = "Pending";
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    @Override
    public String toString() {
        return "Appointment ID: " + appointmentID + ", Patient: " + patientName + ", Status: " + status;
    }
}
