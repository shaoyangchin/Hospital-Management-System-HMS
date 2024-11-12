import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class PharmacistView {

    public static void pharmacistView(Pharmacist pharmacist, HMSDatabase database) {
        Scanner scanner = new Scanner(System.in);
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        while (true) {
            System.out.println("\n--- Pharmacist Menu ---");
            System.out.println("1. View Appointment Outcome Records");
            System.out.println("2. Update Prescription Status");
            System.out.println("3. View Medication Inventory");
            System.out.println("4. Submit Replenishment Request");
            System.out.println("5. View Replenishment Requests");  // Added new option
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        handleViewAppointments(pharmacist, scanner, database);
                        break;
                    case 2:
                        handlePrescriptionUpdate(pharmacist, scanner, database);
                        break;
                    case 3:
                        pharmacist.viewMedicationInventory(database.getMedicines(), currentDate);
                        break;
                    case 4:
                        handleReplenishmentRequest(pharmacist, scanner, database);
                        break;
                    case 5:
                        pharmacist.viewReplenishmentRequests();
                        break;
                    case 6:
                        System.out.println("Exiting Pharmacist Menu...");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    private static void handleViewAppointments(Pharmacist pharmacist, Scanner scanner, HMSDatabase database) {
        System.out.print("Enter patient ID (or press Enter to view all appointments): ");
        String patientId = scanner.nextLine();
        
        if (patientId.trim().isEmpty()) {
            pharmacist.viewAllCompletedAppointments();
        } else {
            Patient patient = database.getPatientById(patientId);
            if (patient != null) {
                pharmacist.viewAppointmentOutcomes(patient);
            } else {
                System.out.println("Patient not found.");
            }
        }
    }

    private static void handlePrescriptionUpdate(Pharmacist pharmacist, Scanner scanner, HMSDatabase database) {
        try {
            String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            // Show completed appointments first
            pharmacist.viewAllCompletedAppointments();

            System.out.print("\nEnter appointment ID: ");
            int appointmentID = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            // Show current inventory before updating
            pharmacist.viewMedicationInventory(database.getMedicines(), currentDate);

            System.out.print("Enter medicine name: ");
            String medicineName = scanner.nextLine();

            // Get the medicine from database
            Medicine medicine = database.getMedicineByName(medicineName);
            if (medicine == null) {
                System.out.println("Medicine not found in inventory.");
                return;
            }
            
            // Update prescription with stock and expiry checks
            pharmacist.updatePrescriptionStatus(appointmentID, medicine, currentDate);

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid values.");
            scanner.nextLine(); // Clear invalid input
        }
    }

    private static void handleReplenishmentRequest(Pharmacist pharmacist, Scanner scanner, HMSDatabase database) {
        // Show current inventory first
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        pharmacist.viewMedicationInventory(database.getMedicines(), currentDate);

        System.out.print("\nEnter medicine name to submit replenishment request: ");
        String medicineName = scanner.nextLine();

        // Verify medicine exists
        Medicine medicine = database.getMedicineByName(medicineName);
        if (medicine == null) {
            System.out.println("Medicine not found in inventory.");
            return;
        }

        try {
            System.out.print("Enter quantity: ");
            int requestedQuantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (requestedQuantity <= 0) {
                System.out.println("Quantity must be positive.");
                return;
            }

            ReplenishmentRequest request = pharmacist.submitReplenishmentRequest(medicineName, requestedQuantity);
            if (request != null) {
                System.out.println("Replenishment request submitted successfully.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid quantity. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
        }
    }
}