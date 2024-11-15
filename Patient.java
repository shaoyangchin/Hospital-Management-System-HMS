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
    private String phoneNum = null;
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

    public String getPhoneNum() {
        if (phoneNum == null) {
            return "not available";
        }
        return phoneNum;
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
    
    public void setAppointment(Appointment appt) {
        this.appt = appt;
    }

    public Appointment getAppt() {
        return appt;
    }

    public void viewMedicalRecord(Patient patient) {
        System.out.println("Viewing medical records for patient ID: " + patient.getPatientId() + ", Name: " + patient.getName() + ", DOB: " + patient.getDateOfBirth() + ", gender: " + patient.getGender() + ", Blood Type: " + patient.getBloodType() + ", Email: "+patient.getContact()+", Phone number: "+patient.getPhoneNum());
        System.out.println(getRecordForPatient(patient));
    }

    public void updateRecord(Patient patient, String contact, String phone) {
        this.contactInformation = contact;
        this.phoneNum = phone;
    }

    ApptManager manager = new ApptManager();
    public void viewAvailAppts(ArrayList<Appointment> appts) {
        // Displays all available (PENDING) appointments for all patients
        // for (Appointment appointment : appts) {
        //     if (appointment.getStatus() == Status.PENDING) {
        //         System.out.println(appointment);
        //     }
        // }
        // Displays all available (PENDING) appointments for patient logged in
        for (Appointment appointment : appts) {
            if (appointment.getStatus() == Status.PENDING && appointment.getPatient() == null) {
                System.out.println(appointment);
            }
        }
        
    }

    public void scheduleAppt(Patient patient, int apptId, ApptManager apptM, ArrayList<Appointment> appts) {
        apptM.schedulePatient(patient, apptId, appts);
    }

    public void rescheduleAppt(int oldApptId, int newApptId, Patient patient, ApptManager apptM, ArrayList<Appointment> appts) {
        apptM.reschedulePatient(oldApptId, newApptId, patient, appts);
    }

    public void cancelAppt(int apptId, Patient patient, ApptManager apptM, ArrayList<Appointment> appts) {
        apptM.cancelPatient(apptId, patient, appts);
    }

    public void viewScheduledAppts(Patient patient, ApptManager apptM, ArrayList<Appointment> appts) {
        apptM.viewScheduled(patient, appts);
    }

    public void viewPastApptOutcomes(Patient patient, ApptManager apptM, ArrayList<Appointment> appts) {
        apptM.viewPastOutcomes(patient, appts);
    }

}
