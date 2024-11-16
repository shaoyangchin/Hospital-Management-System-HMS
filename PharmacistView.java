import java.util.InputMismatchException;
import java.util.Scanner;

public class PharmacistView {
    public static void pharmacistView(Pharmacist pharmacist, HMSDatabase database) {
        Scanner scanner = new Scanner(System.in);
        
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
                        pharmacist.viewPendingPrescriptions();
                        break;
                    case 2:
                        // Handle updating the prescription status
                        handlePrescriptionUpdate(pharmacist, scanner);
                        break;
                    case 3:
                        // View all medications in the inventory
                        pharmacist.viewMedicationInventory(database.getMedicines());
                        break;
                    case 4:
                        // Handle submission of a replenishment request
                        handleReplenishmentRequest(pharmacist, scanner, database);
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

    private static void handlePrescriptionUpdate(Pharmacist pharmacist, Scanner scanner) {
        try {
            // Get appointment ID from the user
            System.out.print("\nEnter appointment ID to update: ");
            int appointmentID = scanner.nextInt();
            scanner.nextLine(); // Consume newline
    
            // Call the updatePrescriptionStatus method in Pharmacist class
            pharmacist.updatePrescriptionStatus(appointmentID);
    
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid appointment ID.");
            scanner.nextLine(); // Consume invalid input
        }
    }

    private static void handleReplenishmentRequest(Pharmacist pharmacist, Scanner scanner, HMSDatabase database) {
        System.out.print("\nEnter medicine name for replenishment: ");
        String medicineName = scanner.nextLine();

        Medicine medicine = database.getMedicineByName(medicineName);
        if (medicine == null) {
            System.out.println("Medicine not found in inventory.");
            return;
        }

        try {
            System.out.print("Enter quantity to request: ");
            int requestedQuantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (requestedQuantity <= 0) {
                System.out.println("Quantity must be positive.");
                return;
            }

            // Submit replenishment request through Pharmacist
            ReplenishmentRequest request = pharmacist.submitReplenishmentRequest(medicineName, requestedQuantity);
            if (request != null) {
                database.saveDatabase();
                System.out.println("Replenishment request submitted successfully and saved.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid quantity. Please enter a number.");
            scanner.nextLine(); // Consume invalid input
        }
    }
}
