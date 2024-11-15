public class MedicalRecord {
    private String diagnosis;
    private String prescription;
    private String patientId;
    private int appointmentId;
    private String notes;
    private String service;

    public MedicalRecord(String diagnosis, String prescription, String patientId, int appointmentId, String notes, String service) {
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.patientId = patientId;
        this.appointmentId = appointmentId;
        this.notes = notes;
        this.service = service;
    }

    public void addDiagnosisAndPrescription(String diagnosis, String prescription) {
        this.diagnosis = diagnosis;
        this.prescription = prescription;
    }

    // public void patientUpdateRecord(String email, String phoneNum) {
    //     this.email = email;
    //     this.phoneNum = phoneNum;
    // }
    public String getPatientId(){ return patientId; }

    @Override
    public String toString() {
        return "Diagnosis: " + diagnosis + ", Prescription: " + prescription ;
    }

    public String getDiagnosis() {
        return diagnosis;
    }
    public String getPrescription() {return prescription;}

    public int getAppointmentId() { return appointmentId; }

    public String getNotes() { return notes; }

    public String getService() { return service; }

}
