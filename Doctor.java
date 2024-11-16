import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Doctor extends User {
    private String name;
    private List<TimeSlot> availability;
    private String gender;
    private int age;

    public Doctor(String name, String gender, int age, String userId, String password, UserType userType) {
        super(userId, password, userType);
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.availability = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<TimeSlot> getAvailability() {
        return availability;
    }




    // Medical Record Management
    public void getRecordForPatient(List<Patient> patients, String patientId, ApptManager apptManager, HMSDatabase hmsDatabase) {
        for (Patient patient : patients) {
            if (patient.getUserId().equals(patientId)) {
                apptManager.viewPastOutcomes(patient, hmsDatabase);
                return;
            }
        }
        System.out.println("No records found for Patient ID: " + patientId);
    }


    public void updatePatientRecord(String patientId, HMSDatabase database) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Choose what to update
        System.out.println("What do you want to update?");
        System.out.println("1. Diagnosis");
        System.out.println("2. Prescription");
        System.out.println("3. Service");
        System.out.print("Enter choice (1/2/3): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String updateField;
        switch (choice) {
            case 1 -> updateField = "Diagnosis";
            case 2 -> updateField = "Prescription";
            case 3 -> updateField = "Service";
            default -> {
                System.out.println("Invalid choice. Exiting...");
                return;
            }
        }

        // Step 2: Fetch relevant records
        ArrayList<MedicalRecord> medicalRecords = database.getRecords();
        ArrayList<MedicalRecord> filteredRecords = new ArrayList<>();

        for (MedicalRecord record : medicalRecords) {
            if (record.getPatientId().equalsIgnoreCase(patientId)) {
                filteredRecords.add(record);
            }
        }

        if (filteredRecords.isEmpty()) {
            System.out.println("No records found for Patient ID: " + patientId);
            return;
        }

        // Step 3: Display matching records
        System.out.println("Matching records for Patient ID: " + patientId + " and field: " + updateField);
        int index = 1;
        for (MedicalRecord record : filteredRecords) {
            String fieldValue = switch (updateField) {
                case "Diagnosis" -> record.getDiagnosis();
                case "Prescription" -> record.getPrescription();
                case "Service" -> record.getService();
                default -> "";
            };
            System.out.println(index + ". Appointment ID: " + record.getAppointmentId() + " | " + updateField + ": " + fieldValue);
            index++;
        }

        // Step 4: Ask which record to update
        System.out.print("Enter the number of the record you want to update: ");
        int recordChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (recordChoice < 1 || recordChoice > filteredRecords.size()) {
            System.out.println("Invalid choice. Exiting...");
            return;
        }

        MedicalRecord selectedRecord = filteredRecords.get(recordChoice - 1);

        // Step 5: Enter new value
        System.out.print("Enter new value for " + updateField + ": ");
        String newValue = scanner.nextLine().trim();

        // Step 6: Update the record
        switch (updateField) {
            case "Diagnosis" -> selectedRecord.setDiagnosis(newValue);
            case "Prescription" -> selectedRecord.setPrescription(newValue);
            case "Service" -> selectedRecord.setService(newValue);
        }

        System.out.println(updateField + " updated successfully for Appointment ID: " + selectedRecord.getAppointmentId());

        // Step 7: Save changes back to the CSV
        database.saveDatabase();
        System.out.println("Changes saved to MedicalRecord_List.csv.");
    }


    

   
}