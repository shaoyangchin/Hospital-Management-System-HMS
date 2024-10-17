public class Patient {
    private int patientID;
    private String name;
    private MedicalRecord medicalRecord;

    public Patient(int patientID, String name, MedicalRecord medicalRecord) {
        this.patientID = patientID;
        this.name = name;
        this.medicalRecord = medicalRecord;
    }

    public int getPatientID() {
        return patientID;
    }

    public String getName() {
        return name;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void viewMedicalRecord(Patient patient) {
        System.out.println("Viewing medical records for patient ID: " + patient.getPatientID() + ", Name: " + patient.getName());
        System.out.println(patient.getMedicalRecord());
    }

    public void updateRecord(Patient patient, String email, String phoneNum) {
        patient.getMedicalRecord().patientUpdateRecord(email, phoneNum);
        System.out.println("Updated medical record for patient ID: " + patient.getPatientID());
    }
}
