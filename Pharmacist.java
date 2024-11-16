import java.util.ArrayList;
import java.util.List;

public class Pharmacist extends User {
    private String name;
    private String gender;
    private int age;
    private ApptManager appointmentManager;
    private DatabaseHelper dbHelper;
    private List<ReplenishmentRequest> requests;

    // Constructor
    public Pharmacist(String name, String gender, int age, String userId, String password, UserType userType) {
        super(userId, password, userType);
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.requests = new ArrayList<>();
    }

    // Set DatabaseHelper
    public void setDatabaseHelper(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    // Set AppointmentManager
    public void setAppointmentManager(ApptManager apptManager) {
        this.appointmentManager = apptManager;
    }

    // View Medical Records with PENDING Prescription Status
    public void viewPendingPrescriptions() {
        if (dbHelper == null) {
            System.out.println("Error: Database helper is not set");
            return;
        }

        ArrayList<MedicalRecord> medicalRecords = dbHelper.initMedicalRecords();
        System.out.println("\nViewing all PENDING prescriptions:");
        System.out.println("----------------------------------------");

        boolean found = false;
        for (MedicalRecord record : medicalRecords) {
            if ("PENDING".equalsIgnoreCase(record.getPrescriptionStatus())) {
                System.out.println("Appointment ID: " + record.getAppointmentId());
                System.out.println("Patient ID: " + record.getPatientId());
                System.out.println("Prescription: " + record.getPrescription());
                System.out.println("Service: " + record.getService());
                System.out.println("Notes: " + record.getNotes());
                System.out.println("----------------------------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No PENDING prescriptions found.");
        }
    }

    // Update Prescription Status via ApptManager
    public void updatePrescriptionStatus(int appointmentId) {
        if (appointmentManager == null || dbHelper == null) {
            System.out.println("Error: Appointment manager or Database helper is not set");
            return;
        }
    
        // Load Medical Records and Medicines
        ArrayList<MedicalRecord> medicalRecords = dbHelper.initMedicalRecords();
        ArrayList<Medicine> medicines = dbHelper.initMedicines();
    
        // Find the medical record corresponding to the appointment ID
        MedicalRecord targetRecord = null;
        for (MedicalRecord record : medicalRecords) {
            if (record.getAppointmentId() == appointmentId) {
                targetRecord = record;
                break;
            }
        }
    
        // If no record found, print error and return
        if (targetRecord == null) {
            System.out.println("Error: No medical record found for Appointment ID: " + appointmentId);
            return;
        }
    
        // Get the prescription details
        String medicineName = targetRecord.getPrescription();
        int quantityToDispense = targetRecord.getQuantity();
    
        // Display prescription details
        System.out.println("\nPrescription Details:");
        System.out.println("----------------------");
        System.out.println("Medicine Prescribed: " + medicineName);
        System.out.println("Quantity Required: " + quantityToDispense);
        System.out.println("----------------------");
    
        // Find the corresponding medicine in the inventory
        Medicine targetMedicine = null;
        for (Medicine medicine : medicines) {
            if (medicine.getName().equalsIgnoreCase(medicineName)) {
                targetMedicine = medicine;
                break;
            }
        }
    
        // If no medicine found, print error and return
        if (targetMedicine == null) {
            System.out.println("Error: Medicine " + medicineName + " not found in inventory.");
            return;
        }
    
        // Display available stock
        System.out.println("\nAvailable Stock:");
        System.out.println("----------------------");
        System.out.println("Medicine Name: " + targetMedicine.getName());
        System.out.println("Current Stock: " + targetMedicine.getQuantity());
        System.out.println("----------------------");
    
        // Check if there is enough stock to dispense
        if (targetMedicine.getQuantity() >= quantityToDispense) {
            // Dispense the medicine
            System.out.println("\nDispensing " + quantityToDispense + " units of " + medicineName + "...");
            
            // Update the prescription status to DISPENSED using ApptManager
            appointmentManager.updatePrescriptionStatus(appointmentId, "DISPENSED");
    
            // Deduct the quantity from the medicine inventory
            targetMedicine.setQuantity(targetMedicine.getQuantity() - quantityToDispense);
            System.out.println("Successfully dispensed " + quantityToDispense + " of " + medicineName + ". Updated inventory.");
    
            // Save the updated medicine list to CSV
            dbHelper.saveToCsv(medicines, "data/Medicine_List.csv", DatabaseHelper.medicineFields, 5);
        } else {
            // If not enough stock, print a warning message
            System.out.println("Error: Not enough stock to dispense " + medicineName + ". Required: " + quantityToDispense + ", Available: " + targetMedicine.getQuantity());
        }
    }
    

    
    public void viewMedicationInventory(ArrayList<Medicine> medicines) {
        if (medicines.isEmpty()) {
            System.out.println("No medicines in inventory");
            return;
        }
        
        System.out.println("\nCurrent Medication Inventory:");
        System.out.println("============================");
        System.out.printf("%-20s %-15s %-15s%n", "Medicine Name", "Quantity", "Threshold");
        System.out.println("----------------------------------------------------");
        
        for (Medicine medicine : medicines) {
            System.out.printf("%-20s %-15d %-15d%n", 
                medicine.getName(), 
                medicine.getQuantity(), 
                medicine.getThreshold());

            if (medicine.isLowStock()) {
                System.out.println("⚠️ Low stock warning!");
            }
        }
        System.out.println("============================");
    }

    public ReplenishmentRequest submitReplenishmentRequest(String medicineName, int requestedQuantity) {
        if (medicineName == null || medicineName.trim().isEmpty()) {
            System.out.println("Error: Invalid medicine name");
            return null;
        }
        if (requestedQuantity <= 0) {
            System.out.println("Error: Invalid quantity requested");
            return null;
        }
    
        ReplenishmentRequest request = new ReplenishmentRequest(medicineName, requestedQuantity, this.getUserId());
        requests.add(request);
    
        // Save the request to the CSV immediately to ensure persistence
        if (dbHelper != null) {
            dbHelper.saveReplenishmentRequest(request); // Append request to CSV file for persistence
        } else {
            System.out.println("Error: Database helper is not set");
        }
    
        System.out.println("\nReplenishment Request Submitted Successfully");
        System.out.println("Medicine: " + medicineName);
        System.out.println("Quantity Requested: " + requestedQuantity);
        System.out.println("Request Status: Pending administrator approval");
        return request;
    }
}