public class Appointment {
    private int appointmentID;
    private String patientName;
    private String status;
    private String outcome;
    private int patientID;

    public Appointment(int appointmentID, String patientName, int patientID) {
        this.appointmentID = appointmentID;
        this.patientName = patientName;
        this.status = "Pending";
        this.patientID = patientID;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public String getPatientName() {
        return patientName;
    }

    public int getPatientID() {
        return patientID;
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
        return "Appointment ID: " + appointmentID + ", Patient: " + patientName + ", ID: " + patientID + ", Status: " + status;
    }
}
