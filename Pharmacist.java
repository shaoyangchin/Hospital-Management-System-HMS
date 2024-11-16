import java.util.ArrayList;
import java.util.List;

public class Pharmacist extends User {
    private String name;
    private List<ReplenishmentRequest> requests;
    private String gender;
    private int age;
    private ApptManager appointmentManager;

    public Pharmacist(String name, String gender, int age, String userId, String password, UserType userType) {
        super(userId, password, userType);
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.requests = new ArrayList<>();
    }

    // Changed from static to instance method
    public void setAppointmentManager(ApptManager apptManager) {
        this.appointmentManager = apptManager;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public void viewPendingPrescriptions(ArrayList<Appointment> appts) {
        System.out.println("\nPending Prescriptions:");
        System.out.println("---------------------");
        boolean found = false;
        
        for (Appointment appt : appts) {
            if (appt.getPrescribedMedicine() != null && 
                appt.getPrescriptionStatus().equals("PENDING")) {
                System.out.println("Appointment ID: " + appt.getAppointmentID());
                System.out.println("Patient: " + appt.getPatient().getName());
                System.out.println("Prescribed Medicine: " + appt.getPrescribedMedicine());
                System.out.println("Quantity Required: " + appt.getPrescribedQuantity());
                System.out.println("---------------------");
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No pending prescriptions found.");
        }
    }

    public void updatePrescriptionStatus(int appointmentId, Medicine medicine, ArrayList<Appointment> appts, HMSDatabase database) {
        if (appointmentManager == null || medicine == null) {
            System.out.println("Error: Invalid appointment system or medicine");
            return;
        }

        // Find appointment and check prescribed quantity
        Appointment targetAppt = null;
        for (Appointment appt : appts) {
            if (appt.getAppointmentID() == appointmentId) {
                targetAppt = appt;
                break;
            }
        }

        if (targetAppt == null) {
            System.out.println("Appointment not found.");
            return;
        }

        int prescribedQuantity = targetAppt.getPrescribedQuantity();
        System.out.println("\nPerforming pre-dispensing checks...");

        if (medicine.getQuantity() < prescribedQuantity) {
            System.out.println("\n⚠️ CANNOT DISPENSE - Insufficient stock");
            System.out.println("Current stock: " + medicine.getQuantity());
            System.out.println("Required quantity: " + prescribedQuantity);
            
            int requestQuantity = prescribedQuantity + medicine.getThreshold() - medicine.getQuantity();
            submitReplenishmentRequest(medicine.getName(), requestQuantity);
            
            appointmentManager.updatePrescriptionStatus(appointmentId, "PENDING", appts);
            System.out.println("Prescription status remains PENDING until stock is replenished");
            return;
        }

        // Update medicine quantity
        medicine.setQuantity(medicine.getQuantity() - prescribedQuantity);
        
        System.out.println("\n✓ Pre-dispensing checks passed:");
        System.out.println("- Sufficient stock available");
        appointmentManager.updatePrescriptionStatus(appointmentId, "DISPENSED", appts);
        
        // Save changes
        database.saveDatabase();
        
        System.out.println("Prescription successfully dispensed");
        System.out.println("Updated stock level: " + medicine.getQuantity());
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
        
        System.out.println("\nReplenishment Request Submitted Successfully");
        System.out.println("Medicine: " + medicineName);
        System.out.println("Quantity Requested: " + requestedQuantity);
        System.out.println("Request Status: Pending administrator approval");
        return request;
    }

    public void viewReplenishmentRequests() {
        if (requests.isEmpty()) {
            System.out.println("No replenishment requests found");
            return;
        }

        System.out.println("\nReplenishment Requests Status:");
        System.out.println("------------------------------");
        for (ReplenishmentRequest request : requests) {
            System.out.println("Medicine: " + request.getMedicineName());
            System.out.println("Quantity Requested: " + request.getRequestedQuantity());
            System.out.println("Status: " + (request.isApproved() ? "Approved ✓" : "Pending Administrator Approval"));
            System.out.println("------------------------------");
        }
    }
}