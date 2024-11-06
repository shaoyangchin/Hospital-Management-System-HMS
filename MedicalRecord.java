public class MedicalRecord {
    private String diagnosis;
    private String prescription;
    private String email;
    private String phoneNum;
    private String patientId;

    public MedicalRecord(String diagnosis, String prescription, String email, String phoneNum,String patientId) {
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.email = email;
        this.phoneNum = phoneNum;
        this.patientId = patientId;
    }

    public void addDiagnosisAndPrescription(String diagnosis, String prescription) {
        this.diagnosis = diagnosis;
        this.prescription = prescription;
    }

    public void patientUpdateRecord(String email, String phoneNum) {
        this.email = email;
        this.phoneNum = phoneNum;
    }
    public String getPatientId(){ return patientId; }

    @Override
    public String toString() {
        return "Diagnosis: " + diagnosis + ", Prescription: " + prescription + ", Email: " + email + ", Phone Number: " + phoneNum;
    }
}
