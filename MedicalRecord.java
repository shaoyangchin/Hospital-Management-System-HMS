public class MedicalRecord {
    private String diagnosis;
    private String prescription;

    public void addDiagnosisAndPrescription(String diagnosis, String prescription) {
        this.diagnosis = diagnosis;
        this.prescription = prescription;
    }

    @Override
    public String toString() {
        return "Diagnosis: " + diagnosis + ", Prescription: " + prescription;
    }
}
