public class MedicalRecord {
    private String diagnosis;
    private String prescription;
    private String patientId;
    private int appointmentId;
    private String notes;
    private String service;
    private int quantity;

    // Updated constructor without prescriptionStatus
    public MedicalRecord(String diagnosis, String prescription, String patientId, int appointmentId, String notes, String service, int quantity) {
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.patientId = patientId;
        this.appointmentId = appointmentId;
        this.notes = notes;
        this.service = service;
        this.quantity = quantity;
    }

    // Getters remain the same (except removed prescriptionStatus getter)
    public String getDiagnosis() {
        return diagnosis;
    }

    public String getPrescription() {
        return prescription;
    }

    public String getPatientId() {
        return patientId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public String getNotes() {
        return notes;
    }

    public String getService() {
        return service;
    }

    public int getQuantity() {
        return quantity;
    }

     // Setter for Diagnosis
     public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    // Setter for Prescription
    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    // Setter for Service
    public void setService(String service) {
        this.service = service;
    }

    // Updated toString() without prescriptionStatus
    @Override
    public String toString() {
        return "Appointment ID: " + appointmentId + "\n"
             + "Patient ID: " + patientId + "\n"
             + "Diagnosis: " + diagnosis + "\n"
             + "Prescription: " + prescription + "\n"
             + "Service: " + service + "\n"
             + "Notes: " + notes + "\n"
             + "Quantity: " + quantity + "\n";
    }
}