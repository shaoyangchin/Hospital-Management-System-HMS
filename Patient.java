public class Patient {
    private int patientID;
    private String name;
    private MedicalRecord medicalRecord;

    public Patient(int patientID, String name) {
        this.patientID = patientID;
        this.name = name;
        this.medicalRecord = new MedicalRecord();
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
}
