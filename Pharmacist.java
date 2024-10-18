import java.util.List;       
import java.util.Map;        


public class Pharmacist extends User {
    private int pharmacistID;
    private String name;
    private Map<String, Medicine> inventory;  // Pharmacist manages medicine inventory
    private List<Appointment> appointments;   // List of appointments associated with prescriptions

    public Pharmacist(int pharmacistID, String name, String userId, String password, Map<String, Medicine> inventory, List<Appointment> appointments) {
        super(userId, password);
        this.pharmacistID = pharmacistID;
        this.name = name;
        this.inventory = inventory;
        this.appointments = appointments;
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

    public List<Appointment> getAppointments() {
        return appointments;
    }

    // View appointment outcomes
    public void viewAppointmentOutcomeRecords() {
        System.out.println("Viewing Appointment Outcome Records for pharmacist: " + this.getName());
        for (Appointment appointment : appointments) {
            if ("Completed".equals(appointment.getStatus())) {
                System.out.println("Appointment ID: " + appointment.getAppointmentID());
                System.out.println("Prescriptions: " + appointment.getOutcome());
            }
        }
    }

    // Update prescription status
    public void updatePrescriptionStatus(int appointmentID, String newStatus) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID() == appointmentID) {
                appointment.setStatus(newStatus);
                System.out.println("Updated prescription status for appointment ID: " + appointmentID + " to " + newStatus);
                return;
            }
        }
        System.out.println("Appointment ID " + appointmentID + " not found.");
    }

    // View medication inventory
    public void viewMedicationInventory() {
        System.out.println("Viewing Medication Inventory:");
        for (Medicine medicine : inventory.values()) {
            System.out.println(medicine);
        }
    }

    // Submit replenishment request for low-stock medicines
    public void submitReplenishmentRequest(String medicineName) {
        Medicine medicine = inventory.get(medicineName);
        if (medicine != null) {
            if (medicine.getStock() < medicine.getLowStockAlertLevel()) {
                System.out.println("Submitting replenishment request for " + medicineName);
                // Simulate sending the request to the administrator
            } else {
                System.out.println("Stock level is sufficient for " + medicineName);
            }
        } else {
            System.out.println("Medicine " + medicineName + " not found in inventory.");
        }
    }
}