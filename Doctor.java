import java.util.List;
import java.util.Objects;
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
    public void setName(String name) {}

    public List<TimeSlot> getAvailability() {
        return availability;
    }




    // 1. View Patient Medical Record
    public void viewPatientMedicalRecord(String patientId, HMSDatabase database) {
        Scanner scanner = new Scanner(System.in);
        
        // Validate patientId input format (repeat until valid)
        while (patientId == null || !patientId.matches("P\\d{4}")) {
            System.out.print("Invalid Patient ID format. Please enter a valid Patient ID (PXXXX): ");
            patientId = scanner.nextLine().trim();
        }
    
        int haveAppt = 0; // Tracks if any records are found
        ArrayList<Appointment> appointments = database.getAppointments();
        ArrayList<MedicalRecord> medicalRecords = database.getRecords();
    
        System.out.println("\n--- Medical Records for Patient ID: " + patientId + " ---");
    
        // Print table header
        String header = String.format("| %-15s | %-15s | %-15s | %-15s | %-15s | %-20s |",
                "Appointment ID", "Doctor", "Diagnosis", "Prescription", "Service", "Notes");
        String separator = "=".repeat(header.length());
        System.out.println(separator);
        System.out.println(header);
        System.out.println(separator);
    
        for (MedicalRecord medicalRecord : medicalRecords) {
            if (Objects.equals(medicalRecord.getPatientId(), patientId)) {
                for (Appointment appointment : appointments) {
                    // Match appointment ID and ensure the doctor matches
                    if (Objects.equals(appointment.getAppointmentID(), medicalRecord.getAppointmentId()) &&
                            Objects.equals(appointment.getDoctor().getUserId(), this.getUserId())) {
    
                        // Print the medical record in table format
                        System.out.printf("| %-15d | %-15s | %-15s | %-15s | %-15s | %-20s |\n",
                                medicalRecord.getAppointmentId(),
                                appointment.getDoctor().getName(),
                                medicalRecord.getDiagnosis(),
                                medicalRecord.getPrescription(),
                                medicalRecord.getService(),
                                medicalRecord.getNotes());
                        haveAppt++;
                    }
                }
            }
        }
    
        System.out.println(separator); // Closing line of the table
    
        // Handle the case where no records are found
        if (haveAppt == 0) {
            System.out.println("No completed appointments or medical records found for Patient ID: " + patientId);
        }
    }
    
    



    // 2. Update Patient Medical Record
    public void updatePatientRecord(String patientId, HMSDatabase database) { 
        Scanner scanner = new Scanner(System.in);
        
        // Validate patientId input format (repeat until valid)
        while (patientId == null || !patientId.matches("P\\d{4}")) {
            System.out.print("Invalid Patient ID format. Please enter a valid Patient ID (PXXXX): ");
            patientId = scanner.nextLine().trim();
        }
    
        // Step 1: Choose what to update
        int choice = -1;
        while (choice < 1 || choice > 3) {
            System.out.println("\nWhat do you want to update?");
            System.out.println("1. Diagnosis");
            System.out.println("2. Prescription");
            System.out.println("3. Service");
            System.out.print("Enter choice (1/2/3): ");
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number (1/2/3).");
            }
        }
    
        String updateField;
        switch (choice) {
            case 1 -> updateField = "Diagnosis";
            case 2 -> updateField = "Prescription";
            case 3 -> updateField = "Service";
            default -> throw new IllegalStateException("Unexpected value: " + choice);
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
    
        // Step 3: Display matching records in a table
        System.out.println("\n--- Matching Records for Patient ID: " + patientId + " and Field: " + updateField + " ---");
        String header = String.format("| %-5s | %-15s | %-20s | %-15s |", "No.", "Appointment ID", updateField, "Notes");
        String separator = "=".repeat(header.length());
        System.out.println(separator);
        System.out.println(header);
        System.out.println(separator);
    
        int index = 1;
        for (MedicalRecord record : filteredRecords) {
            String fieldValue = switch (updateField) {
                case "Diagnosis" -> record.getDiagnosis();
                case "Prescription" -> record.getPrescription();
                case "Service" -> record.getService();
                default -> "";
            };
    
            System.out.printf("| %-5d | %-15d | %-20s | %-15s |\n",
                    index, record.getAppointmentId(), fieldValue, record.getNotes());
            index++;
        }
        System.out.println(separator);
    
        // Step 4: Ask which record to update
        int recordChoice = -1;
        while (recordChoice < 1 || recordChoice > filteredRecords.size()) {
            System.out.print("Enter the number of the record you want to update: ");
            try {
                recordChoice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid record number.");
            }
        }
    
        MedicalRecord selectedRecord = filteredRecords.get(recordChoice - 1);
    
        // Step 5: Enter new value
        String newValue = "";
        while (newValue.isEmpty()) {
            System.out.print("Enter new value for " + updateField + ": ");
            newValue = scanner.nextLine().trim();
            if (newValue.isEmpty()) {
                System.out.println("Invalid input. New value cannot be empty.");
            }
        }
    
        // Step 6: Update the record
        switch (updateField) {
            case "Diagnosis" -> selectedRecord.setDiagnosis(newValue);
            case "Prescription" -> selectedRecord.setPrescription(newValue);
            case "Service" -> selectedRecord.setService(newValue);
        }
    
        System.out.println("\n" + updateField + " updated successfully for Appointment ID: " + selectedRecord.getAppointmentId());
    
        // Step 7: Save changes back to the CSV
        //database.saveDatabase();
        //System.out.println("Changes saved to MedicalRecord_List.csv.");
    }
    
    


    

   
}