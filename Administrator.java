import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

public class Administrator extends User {
    private String name;
    private String gender;
    private int age;
    // private ArrayList<Staff> stafflist;
    // private Map<String, Medicine> inventory;
    Scanner scanner = new Scanner(System.in);

    public Administrator(String name, String gender, int age, String userId, String password, UserType userType) {
        super(userId, password, userType);
        this.name = name;
        this.gender = gender;
        this.age = age;
        // this.stafflist = stafflist;
        // this.inventory = inventory;
    }

    /*
     * public Map<String, Medicine> getInventory() {
     * return inventory;
     * }
     */

    public void viewStaff(ArrayList<Staff> stafflist) {
        System.out.println("Hospital Staff:");
        for (Staff staff : stafflist) {
            System.out.println(staff);
        }
        while (true) {
            System.out.println("Filter by: \n" + "1. Role: Doctors\n" + "2. Role: Pharmacists\n" +
                    "3. Gender: Male\n" + "4. Gender: Female\n" + "5. Manage Staff");
            int filter = scanner.nextInt();
            switch (filter) {
                case 0:
                    System.out.println("Hospital Staff:");
                    for (Staff staff : stafflist) {
                        System.out.println(staff);
                    }
                    break;
                case 1:
                    System.out.println("Hospital Staff(Doctors):");
                    for (Staff staff : stafflist) {
                        if (staff.getRole().equalsIgnoreCase("Doctor")) {
                            System.out.println(staff);
                        }
                    }
                    break;
                case 2:
                    System.out.println("Hospital Staff(Pharmacists):");
                    for (Staff staff : stafflist) {
                        if (staff.getRole().equalsIgnoreCase("Pharmacist")) {
                            System.out.println(staff);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Hospital Staff(Male):");
                    for (Staff staff : stafflist) {
                        if (staff.getGender().equalsIgnoreCase("Male")) {
                            System.out.println(staff);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Hospital Staff(Female):");
                    for (Staff staff : stafflist) {
                        if (staff.getGender().equalsIgnoreCase("Female")) {
                            System.out.println(staff);
                        }
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void addStaff(ArrayList<Staff> stafflist) {
        scanner.nextLine();
        System.out.println("Enter name of staff to add: ");
        String name = scanner.nextLine();
        System.out.println("Enter ID of staff to add: ");
        String ID = scanner.nextLine();
        System.out.println("Enter role of staff to add: ");
        String role = scanner.nextLine();
        System.out.println("Enter gender of staff to add: ");
        String gender = scanner.nextLine();
        System.out.println("Enter age of staff to add: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        UserType type = null;
        switch (role) {
            case "Administrator":
                type = UserType.ADMINISTRATOR;
            case "Doctor":
                type = UserType.DOCTOR;
            case "Pharmacist":
                type = UserType.PHARMACIST;

        }
        Staff newStaff = new Staff(name, role, gender, age, ID, "password", type);
        stafflist.add(newStaff);
        System.out.println("Staff member " + newStaff.getUserId() + " has been added.");
    }

    public void removeStaff(ArrayList<Staff> stafflist) {
        scanner.nextLine();
        System.out.println("Enter ID of staff to be removed: ");
        String removeID = scanner.nextLine();
        boolean found = false;
        Iterator<Staff> iterator = stafflist.iterator();
        while (iterator.hasNext()) {
            Staff staff = iterator.next();
            if (staff.getUserId().equals(removeID)) {
                iterator.remove();
                System.out.println("Staff ID " + removeID + " has been removed.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Staff ID does not exist.");
        }
        return;
    }

    public void updateStaff(ArrayList<Staff> stafflist) {
        scanner.nextLine();
        System.out.println("Enter ID of staff to be updated");
        String updateID = scanner.nextLine();
        boolean found = false;
        Iterator<Staff> iterator = stafflist.iterator();
        while (iterator.hasNext()) {
            Staff staff = iterator.next();
            if (staff.getUserId().equals(updateID)) {
                System.out.println("Enter name of staff to update: ");
                staff.setName(scanner.nextLine());
                System.out.println("Enter role of staff to update: ");
                staff.setRole(scanner.nextLine());
                System.out.println("Enter gender of staff to update: ");
                staff.setGender(scanner.nextLine());
                System.out.println("Enter age of staff to update: ");
                staff.setAge(scanner.nextInt());
                System.out.println("Staff ID " + staff.getUserId() + " has been updated.");
                break;
            }
        }
        if (!found) {
            System.out.println("Staff ID does not exist.");
        }
        return;
    }

    public void viewInventory(Map<String, Medicine> inventory) {
        System.out.println("Viewing Medication Inventory:");
        for (Medicine medicine : inventory.values()) {
            System.out.println(medicine);
        }
    }

    public void addMedicine(Map<String, Medicine> inventory) {
        System.out.println("Enter name of new medicine: ");
        String medicineName = scanner.nextLine();
        System.out.println("Enter stock of new medicine: ");
        int stock = scanner.nextInt();
        System.out.println("Enter low stock alert level of new medicine: ");
        int threshold = scanner.nextInt();
        System.out.println("Enter expiration date of medicine: ");
        String eDate = scanner.nextLine();
        Medicine medicine = new Medicine(medicineName, "null", stock, threshold, eDate);
        inventory.put(medicineName, medicine);
        System.out.println(medicineName + " has been added to the inventory");
    }

    public void removeMedicine(Map<String, Medicine> inventory) {

        System.out.println("Enter name of medicine to be removed: ");
        String medicineName = scanner.nextLine();
        Medicine med = inventory.get(medicineName);
        if (med != null) {
            inventory.remove(medicineName);
            System.out.println(medicineName + " has been removed.");
        } else {
            System.out.println(medicineName + "does not exist.");
        }
    }

    public void setMedStock(Map<String, Medicine> inventory) {
        System.out.println("Enter name of medicine to be restocked: ");
        String medicineName = scanner.nextLine();
        System.out.println("Enter new stock level: ");
        int stock = scanner.nextInt();
        Medicine med = inventory.get(medicineName);
        if (med != null) {
            med.setQuantity(stock);
            inventory.replace(medicineName, med);
            System.out.println(medicineName + "'s stock has been updated to " + stock);
        } else {
            System.out.println(medicineName + " does not exist");
        }
    }

    public void setMedStockLevel(Map<String, Medicine> inventory) {
        System.out.println("Enter name of medicine to change low stock alert level: ");
        String medicineName = scanner.nextLine();
        System.out.println("Enter new alert level:");
        int stocklevel = scanner.nextInt();
        Medicine med = inventory.get(medicineName);
        if (med != null) {
            med.setThreshold(stocklevel);
            inventory.replace(medicineName, med);
            System.out.println(medicineName + "'s low stock alert level has been updated to " + stocklevel);
        } else {
            System.out.println(medicineName + " does not exist");
        }
    }

    public void approveRequest(Map<String, Medicine> inventory, ArrayList<ReplenishmentRequest> replenishmentRequests) {
        for (ReplenishmentRequest request : replenishmentRequests) {
            Medicine med = inventory.get(request.getMedicineName());
            if (med != null) {
                System.out.println(med.getName() + "does not exist in the inventory.");
                return;
            }
            if (med.isLowStock()) {
                med.setQuantity(request.getRequestedQuantity());
                request.approveRequest();
                System.out.println("Request for " + med.getName() + "has been approved.");
            } else {
                System.out.println("There is sufficient stock for" + med.getName());
            }
        }

    }
}