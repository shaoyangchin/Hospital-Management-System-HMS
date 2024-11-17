import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    // Setter methods with validation
    public void setDatabaseHelper(DatabaseHelper dbHelper) {
        if (dbHelper == null) {
            throw new IllegalArgumentException("Database helper cannot be null");
        }
        this.dbHelper = dbHelper;
    }

    public void setAppointmentManager(ApptManager apptManager) {
        if (apptManager == null) {
            throw new IllegalArgumentException("Appointment manager cannot be null");
        }
        this.appointmentManager = apptManager;
    }

    // View pending prescriptions
    public void viewPendingPrescriptions(HMSDatabase database) {
        if (appointmentManager == null) {
            System.out.println("Error: Appointment manager is not set");
            return;
        }
        if (database == null) {
            System.out.println("Error: Database is null");
            return;
        }
        appointmentManager.viewPendingPharmacyAppointments(database);
    }

    // Update prescription status
    public void updatePrescriptionStatus(int appointmentId, HMSDatabase database) {
        if (appointmentManager == null) {
            System.out.println("Error: Appointment manager is not set");
            return;
        }
        if (database == null) {
            System.out.println("Error: Database is null");
            return;
        }
        if (appointmentId <= 0) {
            System.out.println("Error: Invalid appointment ID");
            return;
        }
        appointmentManager.dispenseMedicinePharmacist(appointmentId, database);
    }

    // View medication inventory with null check
    public void viewMedicationInventory(ArrayList<Medicine> medicines) {
        if (medicines == null || medicines.isEmpty()) {
            System.out.println("No medicines in inventory");
            return;
        }
        
        System.out.println("           --- Medication Inventory ---           ");
        System.out.println("==================================================");
        System.out.printf("| %-20s | %-10s | %-10s |\n", "Medicine Name", "Quantity", "Threshold");
        System.out.println("==================================================");
        
        for (Medicine medicine : medicines) {
            if (medicine != null) {
                System.out.printf("| %-20s | %-10d | %-10d |\n", 
                    medicine.getName(), 
                    medicine.getQuantity(), 
                    medicine.getThreshold());
                
                if (medicine.getQuantity() <= medicine.getThreshold()) {
                    System.out.println("(Low Stock Warning)");
                }
            }
        }
        
        System.out.println("==============================================");
    }
    // Submit replenishment request
public ReplenishmentRequest submitReplenishmentRequest(String medicineName, int requestedQuantity) {
    if (medicineName == null || medicineName.trim().isEmpty()) {
        System.out.println("Error: Invalid medicine name");
        return null;
    }
    if (requestedQuantity <= 0) {
        System.out.println("Error: Invalid quantity requested");
        return null;
    }

    ReplenishmentRequest newRequest = new ReplenishmentRequest(medicineName, requestedQuantity, this.getUserId());
    ArrayList<ReplenishmentRequest> existingRequests = new ArrayList<>();

    try {
        // Read existing requests
        BufferedReader reader = new BufferedReader(new FileReader("data/Replenishment_List.csv"));
        String line = reader.readLine(); // Skip header
        
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            ReplenishmentRequest request = new ReplenishmentRequest(
                parts[0],  // medicineName
                Integer.parseInt(parts[1]),  // requestedQuantity
                parts[2]   // pharmacistId
            );
            if (parts.length > 3 && Boolean.parseBoolean(parts[3])) {
                request.approveRequest();
            }
            existingRequests.add(request);
        }
        reader.close();

        // Add the new request
        existingRequests.add(newRequest);

        // Save all requests
        List<String> fieldNames = Arrays.asList("MedicineName", "RequestedQuantity", "PharmacistId", "Status");
        if (dbHelper != null) {
            dbHelper.saveToCsv(existingRequests, "data/Replenishment_List.csv", fieldNames, 3);
        } else {
            System.out.println("Error: Database helper is not set");
            return null;
        }

        System.out.println("\nReplenishment Request Submitted Successfully");
        System.out.println("Medicine: " + medicineName);
        System.out.println("Quantity Requested: " + requestedQuantity);
        System.out.println("Request Status: Pending administrator approval");

    } catch (IOException e) {
        System.out.println("Error reading/writing replenishment requests: " + e.getMessage());
        return null;
    }

    return newRequest;
}

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
    }
}