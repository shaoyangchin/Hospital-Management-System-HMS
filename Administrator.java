import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

public class Administrator extends User {
    private String name;
    private String gender;
    private int age;
    Scanner scanner = new Scanner(System.in);

    public Administrator(String name, String gender, int age, String userId, String password, UserType userType) {
        super(userId, password, userType);
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public void viewStaff(ArrayList<Staff> stafflist) {
        System.out.println("Hospital Staff:");
        for (Staff staff : stafflist) {
            System.out.println(staff);
        }
        while (true) {
            System.out.println("Filter by: \n" + "1. Role: Doctors\n" + "2. Role: Pharmacists\n" +
                    "3. Gender: Male\n" + "4. Gender: Female\n" + "5. Age: 21-30\n" + "6. Age: 31-40\n"
                    + "7. Age: 41-50\n" + "8. Exit\n");
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
                    System.out.println("Hospital Staff (Age: 21-30):");
                    for (Staff staff : stafflist) {
                        if (staff.getAge() >= 21 && staff.getAge() <= 30) {
                            System.out.println(staff);
                        }
                    }
                    break;
                case 6:
                    System.out.println("Hospital Staff (Age: 31-40):");
                    for (Staff staff : stafflist) {
                        if (staff.getAge() >= 31 && staff.getAge() <= 40) {
                            System.out.println(staff);
                        }
                    }
                    break;
                case 7:
                    System.out.println("Hospital Staff (Age: 41-50):");
                    for (Staff staff : stafflist) {
                        if (staff.getAge() >= 41 && staff.getAge() <= 50) {
                            System.out.println(staff);
                        }
                    }
                    break;
                case 8:
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
                found = true;
                while (true) {
                    System.out.println(
                            "Update: \n" + "1. ID\n" + "2. Name\n" + "3. Role\n"
                                    +
                                    "4. Gender\n" + "5. Age\n" + "6. Exit");
                    int option = scanner.nextInt();
                    switch (option) {
                        case 1:
                            scanner.nextLine();
                            System.out.println("Enter new ID:");
                            staff.setHospitalId(scanner.nextLine());
                            break;
                        case 2:
                            scanner.nextLine();
                            System.out.println("Enter new name:");
                            staff.setName(scanner.nextLine());
                            break;
                        case 3:
                            scanner.nextLine();
                            System.out.println("Enter new role:");
                            staff.setRole(scanner.nextLine());
                            break;
                        case 4:
                            scanner.nextLine();
                            System.out.println("Enter new gender:");
                            staff.setGender(scanner.nextLine());
                            break;
                        case 5:
                            System.out.println("Enter new age:");
                            staff.setAge(scanner.nextInt());
                            break;
                        case 6:
                            return;
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                }
            }
        }
        if (!found) {
            System.out.println("Staff ID does not exist.");
        }
        return;
    }

    public void viewAppts(ArrayList<Appointment> appts) {
        System.out.println("Viewing all current appointments:");
        for (Appointment appointment : appts) {
            System.out.println(appointment);
        }
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
        System.out.println("The current replenishment requests are:");
        for (ReplenishmentRequest request : replenishmentRequests) {
            System.out.println(request);
        }
        System.out.println("Enter the name of the medicine whose request is to be approved/declined: ");
        String medicineName = scanner.nextLine();
        Medicine med = inventory.get(medicineName);
        if (med == null) {
            System.out.println("Medicine " + medicineName + " does not exist.");
            return;
        }
        ReplenishmentRequest request = null;
        for (ReplenishmentRequest req : replenishmentRequests) {
            if (req.getMedicineName().equalsIgnoreCase(medicineName)) {
                request = req;
                break;
            }
        }
        if (request == null) {
            System.out.println("There is no replenishment requested for this medicine.");
            return;
        }
        if (med.isLowStock()) {
            med.setQuantity(request.getRequestedQuantity());
            request.approveRequest();
            System.out.println(
                    "Request for " + med.getName() + " has been approved. The medicine's stock is now "
                            + med.getQuantity());
        } else {
            System.out.println(med.getName() + " has sufficient stock.");
        }
    }
}