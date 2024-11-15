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

    public void viewAppointmentOutcomes(Patient patient, ArrayList<Appointment> appts) {
        if (patient == null) {
            System.out.println("Error: Invalid patient");
            return;
        }
        System.out.println("\nViewing prescription orders for patient: " + patient.getName());
        System.out.println("Patient ID: " + patient.getUserId());
        System.out.println("----------------------------------------");
        appointmentManager.viewPastOutcomes(patient, appts);
    }

    public void viewAllCompletedAppointments(ArrayList<Appointment> appts) {
        if (appointmentManager == null) {
            System.out.println("Error: Appointment system not available");
            return;
        }
        System.out.println("\nViewing all completed appointments for prescription management:");
        System.out.println("--------------------------------------------------------");
        appointmentManager.displayAllAppointments(appts);
    }

    public void updatePrescriptionStatus(int appointmentId, Medicine medicine, String currentDate, ArrayList<Appointment> appts) {
        if (appointmentManager == null || medicine == null) {
            System.out.println("Error: Invalid appointment system or medicine");
            return;
        }

        System.out.println("\nPerforming pre-dispensing checks...");
        
        if (medicine.isExpired(currentDate)) {
            System.out.println("\n❌ CANNOT DISPENSE - Medicine has expired");
            System.out.println("Medicine: " + medicine.getName());
            System.out.println("Expiry Date: " + medicine.getExpirationDate());
            
            submitReplenishmentRequest(medicine.getName(), medicine.getThreshold());
            
            appointmentManager.updatePrescriptionStatus(appointmentId, "PENDING", appts);
            System.out.println("Prescription status set to PENDING - Awaiting fresh stock");
            return;
        }

        if (medicine.isLowStock()) {
            System.out.println("\n⚠️ CANNOT DISPENSE - Stock below threshold");
            System.out.println("Current stock: " + medicine.getQuantity());
            System.out.println("Threshold: " + medicine.getThreshold());
            
            int requestQuantity = (int)(medicine.getThreshold() * 1.2) - medicine.getQuantity();
            submitReplenishmentRequest(medicine.getName(), requestQuantity);
            
            appointmentManager.updatePrescriptionStatus(appointmentId, "PENDING", appts);
            System.out.println("Prescription status set to PENDING until stock is replenished");
            return;
        }

        System.out.println("\n✓ Pre-dispensing checks passed:");
        System.out.println("- Medicine is not expired");
        System.out.println("- Sufficient stock available");
        appointmentManager.updatePrescriptionStatus(appointmentId, "DISPENSED", appts);
        System.out.println("Prescription successfully dispensed");
    }

    public void viewMedicationInventory(ArrayList<Medicine> medicines, String currentDate) {
        if (medicines.isEmpty()) {
            System.out.println("No medicines in inventory");
            return;
        }
        
        System.out.println("\nCurrent Medication Inventory Status:");
        System.out.println("==================================");
        
        for (Medicine medicine : medicines) {
            checkMedicineStock(medicine, currentDate);
            System.out.println("==================================");
        }
    }
    
    public void checkMedicineStock(Medicine medicine, String currentDate) {
        if (medicine == null) {
            System.out.println("Error: Invalid medicine");
            return;
        }
        
        System.out.println("\nMedicine Details:");
        System.out.println("Name: " + medicine.getName());
        System.out.println("Current Quantity: " + medicine.getQuantity());
        System.out.println("Stock Threshold: " + medicine.getThreshold());
        System.out.println("Expiry Date: " + medicine.getExpirationDate());
        
        if (medicine.isExpired(currentDate)) {
            System.out.println("❌ WARNING: Medicine has expired!");
        }
        if (medicine.isLowStock()) {
            System.out.println("⚠️ WARNING: Stock is below threshold");
        }
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