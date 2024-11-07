import java.util.ArrayList;
import java.util.Map;

public class Pharmacist extends User {
    private int pharmacistID;
    private String name;
    private Map<String, Medicine> inventory;   // Pharmacist's direct access to medicine inventory
    private ApptManager appointmentManager;    // Reference to ApptManager for managing appointments ****SHOULD BE STATIC
    private ArrayList<ReplenishmentRequest> requests;  // Track submitted replenishment requests

    public Pharmacist(int pharmacistID, String name, String userId, String password, Map<String, Medicine> inventory, ApptManager appointmentManager, UserType userType) {
        super(userId, password, userType);
        this.pharmacistID = pharmacistID;
        this.name = name;
        this.inventory = inventory;
        this.appointmentManager = appointmentManager;
        this.requests = new ArrayList<>();
    }

    // Getters
    public int getPharmacistID() {
        return pharmacistID;
    }

    public String getName() {
        return name;
    }

    public Map<String, Medicine> getInventory() {
        return inventory;
    }

    // View completed appointments (for outcomes) using ApptManager
    public void viewAppointmentOutcomes(Patient patient) {
        System.out.println("Viewing past outcomes for patient: " + patient.getUserId());
        appointmentManager.viewPastOutcomes(patient);
    }

    // View all appointments (could be useful for inventory planning)
    public void viewAllAppointments() {
        System.out.println("Viewing all appointments for pharmacist: " + this.getName());
        appointmentManager.displayAllAppointments();
    }

    // Update prescription status of an appointment via AppointmentManager (if needed)
    public void updatePrescriptionStatus(int apptId, String newStatus) {
        System.out.println("This feature requires ApptManager support.");
    }

    // View medication inventory
    public void viewMedicationInventory() {
        System.out.println("Viewing Medication Inventory:");
        for (Medicine medicine : inventory.values()) {
            System.out.println(medicine);
        }
    }

    // Submit replenishment request based on direct inventory check
    public ReplenishmentRequest submitReplenishmentRequest(String medicineName, int requestedQuantity) {
        Medicine medicine = inventory.get(medicineName);  // Access medicine directly from inventory
        if (medicine != null && medicine.isLowStock()) {
            ReplenishmentRequest request = new ReplenishmentRequest(medicineName, requestedQuantity, this.name);
            requests.add(request);  // Track locally
            System.out.println("Replenishment request submitted for " + medicineName);
            return request;  // To be processed by Administrator
        } else {
            System.out.println("Replenishment not required or medicine not found.");
            return null;
        }
    }

    // View all requests submitted by this pharmacist
    public void viewRequests() {
        System.out.println("Viewing all submitted replenishment requests:");
        for (ReplenishmentRequest request : requests) {
            System.out.println(request);
        }
    }
}