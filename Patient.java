public class Patient extends User{
    private int patientID;
    private String name;
    private MedicalRecord medicalRecord;
    private String dateOfBirth;
    private String gender;
    private String bloodType;
    private String contactInformation;

    public Patient(int patientID, String name, String userId, String password, String dateOfBirth, String gender, String bloodType, String contactInformation,MedicalRecord medicalRecord) {
        super(userId, password);
        this.patientID = patientID;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.bloodType = bloodType;
        this.contactInformation = contactInformation;
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
