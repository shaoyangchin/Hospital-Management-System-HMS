public class MedicalRecord {
    private String diagnosis;
    private String prescription;
    private String patientId;
    private int appointmentId;
    private String notes;
    private String service;
    private int quantity;
    private String prescriptionStatus;

    // Constructor with all the necessary fields
    public MedicalRecord(String diagnosis, String prescription, String patientId, int appointmentId, String notes, String service, int quantity, String prescriptionStatus) {
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.patientId = patientId;
        this.appointmentId = appointmentId;
        this.notes = notes;
        this.service = service;
        this.quantity = quantity;
        this.prescriptionStatus = prescriptionStatus;
    }

    // Getters and Setters
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

    public String getPrescriptionStatus() {
        return prescriptionStatus;
    }

    public void setPrescriptionStatus(String prescriptionStatus) {
        this.prescriptionStatus = prescriptionStatus;
    }

    @Override
    public String toString() {
        return "Appointment ID: " + appointmentId + "\n"
             + "Patient ID: " + patientId + "\n"
             + "Diagnosis: " + diagnosis + "\n"
             + "Prescription: " + prescription + "\n"
             + "Service: " + service + "\n"
             + "Notes: " + notes + "\n"
             + "Quantity: " + quantity + "\n"
             + "Prescription Status: " + prescriptionStatus + "\n";
    }
}
