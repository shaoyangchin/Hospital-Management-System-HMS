import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Patient extends User{
    private String patientId;
    private String name;
    private ArrayList<MedicalRecord> medicalRecords;
    private String dateOfBirth;
    private String gender;
    private String bloodType;
    private String contactInformation;
    private Appointment appt;
    
    

    public Patient(String name, String userId, String password, String dateOfBirth, String gender, String bloodType, String contactInformation, ArrayList<MedicalRecord> medicalRecords, UserType userType) {
        super(userId, password, userType);
        this.patientId = userId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.bloodType = bloodType;
        this.contactInformation = contactInformation;
        this.medicalRecords = medicalRecords;
    }

    public String getPatientId() {
        return patientId;
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

    // New method to expose medical records
    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public MedicalRecord getRecordForPatient(Patient patient) {
        for (MedicalRecord record : medicalRecords) {
            if (record.getPatientId().equals(patientId)) {
                return record;
            }
        }
        return null; // Return null if not found
    }
    

    public Appointment getAppt() {
        return appt;
    }

    public void viewMedicalRecord(Patient patient) {
        System.out.println("Viewing medical records for patient ID: " + patient.getPatientId() + ", Name: " + patient.getName() + ", DOB: " + patient.getDateOfBirth() + ", gender: " + patient.getGender() + ", Blood Type: " + patient.getBloodType() + ", Contact: "+patient.getContact());
        System.out.println(getRecordForPatient(patient));
    }

    public void updateRecord(Patient patient, String contact) {
        this.contactInformation = contact;
    }

    ApptManager manager = new ApptManager();
    public void viewAvailAppts() {
        manager.displayAvailAppointments();
    }

    public void scheduleAppt(Patient patient, int apptId, Doctor doc1) {
        manager.schedulePatient(patient, apptId, doc1);
    }

    public void rescheduleAppt(int apptId, Patient patient) {
        //manager.reschedulePatient(apptId, patient);
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
