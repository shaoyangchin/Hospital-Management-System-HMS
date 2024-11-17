import java.util.Scanner;

public class AdminView {
    public static void AdminView(Administrator admin, HMSDatabase database) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=================================================");
            System.out.println("           --- Administrator Menu ---          ");
            System.out.println("=================================================");
            System.out.printf("| %-2s | %-40s |\n", "1", "View and Manage Hospital Staff");
            System.out.printf("| %-2s | %-40s |\n", "2", "View Appointment Details");
            System.out.printf("| %-2s | %-40s |\n", "3", "View and Manage Medication Inventory");
            System.out.printf("| %-2s | %-40s |\n", "4", "Approve Replenishment Requests");
            System.out.printf("| %-2s | %-40s |\n", "5", "Save Database");
            System.out.printf("| %-2s | %-40s |\n", "6", "Reset Database");
            System.out.printf("| %-2s | %-40s |\n", "7", "Logout");
            System.out.println("=================================================");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    admin.viewStaff(database.getStaff());
                    int manage = 0;
                    do {
                        System.out.println("--- Manage Staff ---\n" + "1. Add Staff\n" + "2. Remove Staff\n"
                                + "3. Update Staff details\n" + "4. Exit");
                        manage = scanner.nextInt();
                        switch (manage) {
                            case 1:
                                admin.addStaff(database.getStaff());
                                break;
                            case 2:
                                admin.removeStaff(database.getStaff());
                                break;
                            case 3:
                                admin.updateStaff(database.getStaff());
                                break;
                            case 4:
                                System.out.println("Exiting manager...");
                                break;
                            default:
                                System.out.println("Invalid option. Please try again.");
                        }
                    } while (manage != 4);
                    break;
                case 2:
                    admin.viewAppts(database.getAppointments(), database.getRecords());
                    break;
                case 3:
                    admin.viewInventory(database.getInventory());
                    int choose = 0;
                    do {
                        System.out.println("--- Manage inventory --- \n" + "1. Add Medicine\n" + "2. Remove Medicine\n"
                                + "3. Update Medicine Stocks\n" + "4. Update Medicine Low Stock Level\n" + "5. Exit\n");
                        choose = scanner.nextInt();
                        switch (choose) {
                            case 1:
                                admin.addMedicine(database.getInventory());
                                break;
                            case 2:
                                admin.removeMedicine(database.getInventory());
                                break;
                            case 3:
                                admin.setMedStock(database.getInventory());
                                break;
                            case 4:
                                admin.setMedStockLevel(database.getInventory());
                                break;
                            case 5:
                                break;
                            default:
                                System.out.println("Invalid option. Please try again.");
                        }
                    } while (choose != 5);
                    break;
                case 4:
                    admin.approveRequest(database.getInventory(), database.getReplenishmentRequests());
                    break;
                case 5:
                    database.saveDatabase();
                    break;
                case 6:
                    database.resetDatabase();
                    break;
                case 7:
                    System.out.println("Exiting Administrator Menu...");
                    database.saveDatabase();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
