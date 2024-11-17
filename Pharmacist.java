import java.util.ArrayList;
import java.util.Arrays;
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
      public void viewPendingPrescriptions(HMSDatabase database) {
        if (appointmentManager == null) {
            System.out.println("Error: Appointment manager is not set");
            return;
        }
        appointmentManager.viewPendingPharmacyAppointments(database);
    }

    // Update Prescription Status via ApptManager
    public void updatePrescriptionStatus(int appointmentId, HMSDatabase database) {
        if (appointmentManager == null) {
            System.out.println("Error: Appointment manager is not set");
            return;
        }
        appointmentManager.dispenseMedicinePharmacist(appointmentId, database);
    }



    public void viewMedicationInventory(ArrayList<Medicine> medicines) {
        if (medicines.isEmpty()) {
            System.out.println("No medicines in inventory");
            return;
        }
        
        System.out.println("\n=== Current Medication Inventory ===");
        System.out.println("------------------------------------------------");
        System.out.printf("%-20s %-15s %-15s%n", "Medicine Name", "Quantity", "Threshold");
        System.out.println("------------------------------------------------");
        
        for (Medicine medicine : medicines) {
            System.out.printf("%-20s %-15d %-15d", 
                medicine.getName(), 
                medicine.getQuantity(), 
                medicine.getThreshold());
                
            // Check low stock on new line for clearer warning
            if (medicine.getQuantity() <= medicine.getThreshold()) {
                System.out.println("\n Low stock warning!");
            } else {
                System.out.println(); // Just new line if no warning
            }
        }
        System.out.println("------------------------------------------------");
    }
    // Submit Replenishment Request
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
        ArrayList<ReplenishmentRequest> requests = new ArrayList<>();
        requests.add(request);

        // Save the request to the CSV immediately to ensure persistence
        if (dbHelper != null) {
            List<String> fieldNames = Arrays.asList("MedicineName", "RequestedQuantity", "PharmacistId", "IsApproved");
            dbHelper.saveToCsv(requests, "data/Replenishment_List.csv", fieldNames, 3);
        } else {
            System.out.println("Error: Database helper is not set");
        }

        System.out.println("\nReplenishment Request Submitted Successfully");
        System.out.println("Medicine: " + medicineName);
        System.out.println("Quantity Requested: " + requestedQuantity);
        System.out.println("Request Status: Pending administrator approval");
        return request;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name){ this.name = name; }
}