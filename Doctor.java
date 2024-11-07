import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Doctor extends User{
    private String name;
    private String specialization;
    private List<Appointment> appointments;
    private String gender;
    private int age;

    public Doctor(String name, String specialization, String gender, int age, String userId, String password, UserType userType) {
        super(userId, password, userType);
        this.name = name;
        this.specialization = specialization;
        this.appointments = new ArrayList<>();
        this.gender = gender;
        this.age = age;
    }
    
    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select Option:");
        int choice = scanner.nextInt();
        return patients.get(choice-1);
    }


    public void viewPatientRecords(Patient patient) {
        System.out.println("Viewing medical records for patient ID: " + patient.getPatientID());
        System.out.println(patient.getMedicalRecord());
    }

    public void updatePatientRecord(Patient patient, String diagnosis, String prescription) {
        patient.getMedicalRecord().addDiagnosisAndPrescription(diagnosis, prescription);
        System.out.println("Updated medical record for patient ID: " + patient.getPatientID());
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
