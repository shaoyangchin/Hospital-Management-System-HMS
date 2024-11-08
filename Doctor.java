import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Doctor extends User{
    private String name;
    private List<Appointment> appointments;
    private String gender;
    private int age;

    public Doctor(String name , String gender, int age, String userId, String password, UserType userType) {
        super(userId, password, userType);
        this.name = name;
        this.appointments = new ArrayList<>();
        this.gender = gender;
        this.age = age;
    }
    
    public String getName() {
        return name;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }


    public static Patient selectPatient(ArrayList<Patient> patients) {
        int num = 0;
        for (Patient patient : patients) {
            num++;
            System.out.printf("%d. %s\n",num , patient.getName());
        }
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Select Option:");
            int choice = scanner.nextInt();
            return patients.get(choice-1);
        }
    }

    


    public MedicalRecord getRecordForPatient(List<Patient> patients, String patientId) {
        for (Patient patient : patients) {
            if (patient.getPatientId().equals(patientId)) {
                // Return the first record found, or you can add further criteria if needed
                if (!patient.getMedicalRecords().isEmpty()) {
                    return patient.getMedicalRecords().get(0); // Retrieves the first record as an example
                }
            }
        }
        System.out.println("Patient with ID " + patientId + " not found or has no records.");
        return null; // Return null if patient or records are not found
    }
    
    

    public void updatePatientRecord(List<Patient> patients, String patientId, String diagnosis, String prescription) {
        for (Patient patient : patients) {
            if (patient.getPatientId().equals(patientId)) {
                // Check if the patient has any existing medical records
                if (!patient.getMedicalRecords().isEmpty()) {
                    // Update the first record as an example (or apply criteria to find a specific record)
                    MedicalRecord recordToUpdate = patient.getMedicalRecords().get(0);
                    recordToUpdate.addDiagnosisAndPrescription(diagnosis, prescription);
                    System.out.println("Updated medical record for patient ID: " + patientId);
                    return;
                } else {
                    System.out.println("No existing medical records for patient ID: " + patientId);
                    return;
                }
            }
        }
        System.out.println("Patient with ID " + patientId + " not found.");
    }
    
    

    public void viewSchedule() {
        System.out.println("Doctor's Schedule:");
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    public void setAvailability(List<TimeSlot> availability) {
        // Logic for setting availability (out of scope for now)
        System.out.println("Availability updated.");
    }

    public void acceptAppointment(int appointmentID) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID() == appointmentID) {
                appointment.setStatus(Status.CONFIRMED);
                System.out.println("Appointment confirmed.");
                return;
            }
        }
        System.out.println("Appointment not found.");
    }

    public void declineAppointment(int appointmentID) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID() == appointmentID) {
                appointment.setStatus(Status.DECLINED);
                System.out.println("Appointment declined.");
                return;
            }
        }
        System.out.println("Appointment not found.");
    }

    public void recordAppointmentOutcome(int appointmentID, String outcome) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID() == appointmentID) {
                appointment.setOutcome(outcome);
                System.out.println("Appointment outcome recorded.");
                return;
            }
        }
        System.out.println("Appointment not found.");
    }
}
