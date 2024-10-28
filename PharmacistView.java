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
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    pharmacist.viewAllAppointments();
                    break;
                case 2:
                    System.out.print("Enter appointment ID to update prescription status: ");
                    int appointmentID = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter new prescription status: ");
                    String newStatus = scanner.nextLine();
                    pharmacist.updatePrescriptionStatus(appointmentID, newStatus);
                    break;
                case 3:
                    pharmacist.viewMedicationInventory();
                    break;
                case 4:
                    System.out.print("Enter medicine name to submit replenishment request: ");
                    String medicineName = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int requestedQuantity = scanner.nextInt();
                    pharmacist.submitReplenishmentRequest(medicineName, requestedQuantity);
                    break;
                case 5:
                    System.out.println("Exiting Pharmacist Menu...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }




}
