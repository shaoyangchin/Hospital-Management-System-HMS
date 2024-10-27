public class Patient extends User{
    private int patientID;
    private String name;
    private MedicalRecord medicalRecord;
    private String dateOfBirth;
    private String gender;
    private String bloodType;
    private String contactInformation;
    private Appointment appt;
    
    

    public Patient(int patientID, String name, String userId, String password, String dateOfBirth, String gender, String bloodType, String contactInformation, MedicalRecord medicalRecord) {
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getContact() {
        return contactInformation;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public Appointment getAppt() {
        return appt;
    }

    public void viewMedicalRecord(Patient patient) {
        System.out.println("Viewing medical records for patient ID: " + patient.getPatientID() + ", Name: " + patient.getName() + ", DOB: " + patient.getDateOfBirth() + ", gender: " + patient.getGender() + ", Blood Type: " + patient.getBloodType());
        System.out.println(patient.getMedicalRecord());
    }

    public void updateRecord(Patient patient, String email, String phoneNum) {
        patient.getMedicalRecord().patientUpdateRecord(email, phoneNum);
        System.out.println("Updated medical record for patient ID: " + patient.getPatientID());
    }

    ApptManager manager = new ApptManager();
    public void viewAvailAppts() {
        manager.displayAvailAppointments();
    }

    public void scheduleAppt(Patient patient, int apptId, Doctor doc1) {
        manager.schedulePatient(patient, apptId, doc1);
    }

    public void rescheduleAppt(int apptId, Patient patient) {
        manager.reschedulePatient(apptId, patient);
    }

    public void cancelAppt(int apptId, Patient patient) {
        manager.cancelPatient(apptId, patient);
    }

    public void viewScheduledAppts(Patient patient) {
        manager.viewScheduled(patient);
    }

    public void viewPastApptOutcomes(Patient patient) {
        manager.viewPastOutcomes(patient);
    }
    
}
