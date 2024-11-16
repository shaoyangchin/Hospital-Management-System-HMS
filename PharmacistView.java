import java.util.InputMismatchException;
import java.util.Scanner;

public class PharmacistView {
    public static void pharmacistView(Pharmacist pharmacist, HMSDatabase database) {
        Scanner scanner = new Scanner(System.in);

        // Set both the appointment manager and database helper
        DatabaseHelper dbHelper = new DatabaseHelper();
        ApptManager apptManager = database.getApptManager();
        
        // Set them in pharmacist
        pharmacist.setDatabaseHelper(dbHelper);
        pharmacist.setAppointmentManager(apptManager);

        // Also set dbHelper in ApptManager
        apptManager.setDatabaseHelper(dbHelper);

        while (true) {
            System.out.println("\n--- Pharmacist Menu ---");
            System.out.println("1. View Appointment Outcome Records");
            System.out.println("2. Update Prescription Status");
            System.out.println("3. View Medication Inventory");
            System.out.println("4. Submit Replenishment Request");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        // View pending prescriptions
                        pharmacist.viewPendingPrescriptions(database);
                        break;
                    case 2:
                        System.out.println("Enter Appointment ID: ");
                        int appointmentId = scanner.nextInt();
                        pharmacist.updatePrescriptionStatus(appointmentId, database);  // Pass database
                        // Add this to refresh the database after update
                        database = new HMSDatabase();  // Reload database to reflect changes
                        break;
                    case 3:
                        // View all medications in the inventory
                        pharmacist.viewMedicationInventory(database.getMedicines());
                        break;
                    case 4:
                        // Submit replenishment request
                        System.out.print("\nEnter medicine name for replenishment: ");
                        String medicineName = scanner.nextLine();

                        try {
                            System.out.print("Enter quantity to request: ");
                            int requestedQuantity = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            if (requestedQuantity <= 0) {
                                System.out.println("Quantity must be positive.");
                                break;
                            }

                            // Submit replenishment request through Pharmacist
                            ReplenishmentRequest request = pharmacist.submitReplenishmentRequest(medicineName, requestedQuantity);
                            if (request != null) {
                                System.out.println("Replenishment request submitted successfully and saved.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid quantity. Please enter a number.");
                            scanner.nextLine(); // Consume invalid input
                        }
                        break;
                    case 5:
                        System.out.println("Exiting Pharmacist Menu...");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume invalid input
            }
        }
    }
}