import java.util.Scanner;
import java.util.ArrayList;
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

    public String getName() {
        return name;
    }

    public String returnGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void viewStaff(ArrayList<Staff> stafflist) {
        System.out.println("                    --- Hospital Staff ---                     ");
        System.out.println("=====================================================================");
        System.out.printf("| %-10s | %-15s | %-15s | %-6s | %-5s |\n", "ID", "Name", "Role", "Gender", "Age");
        System.out.println("=====================================================================");
        for (Staff staff : stafflist) {
            System.out.printf("| %-10s | %-15s | %-15s | %-6s | %-5d |\n",
                    staff.getUserId(),
                    staff.getName(),
                    staff.getRole(),
                    staff.getGender(),
                    staff.getAge());
        }
        System.out.println("====================================================================");
        while (true) {
            System.out.println(
                    "Filter by: \n" + "0. No Filter\n" + "1. Role: Doctors\n" + "2. Role: Pharmacists\n" +
                            "3. Role: Administrators\n" +
                            "4. Gender: Male\n" + "5. Gender: Female\n" + "6. Age: 21-30\n" +
                            "7. Age: 31-40\n"
                            + "8. Age: 41-50\n" + "9. Exit View Staff\n");
            int filter = scanner.nextInt();
            switch (filter) {
                case 0:
                    System.out.println("                       --- Hospital Staff ---                        ");
                    System.out.println("=====================================================================");
                    System.out.printf("| %-10s | %-15s | %-15s | %-6s | %-5s |\n", "ID", "Name", "Role", "Gender",
                            "Age");
                    System.out.println("=====================================================================");
                    boolean foundAll = false;
                    for (Staff staff : stafflist) {
                        System.out.printf("| %-10s | %-15s | %-15s | %-6s | %-5d |\n",
                                staff.getUserId(),
                                staff.getName(),
                                staff.getRole(),
                                staff.getGender(),
                                staff.getAge());
                        foundAll = true;
                    }
                    if (!foundAll) {
                        System.out.println("| No matching staff found.                                         |");
                    }
                    System.out.println("=====================================================================");
                    break;

                case 1:
                    System.out.println("                    --- Hospital Staff (Doctors) ---                     ");
                    System.out.println("=====================================================================");
                    System.out.printf("| %-10s | %-15s | %-15s | %-6s | %-5s |\n", "ID", "Name", "Role", "Gender",
                            "Age");
                    System.out.println("=====================================================================");
                    boolean foundDoctor = false;
                    for (Staff staff : stafflist) {
                        if (staff.getRole().equalsIgnoreCase("Doctor")) {
                            System.out.printf("| %-10s | %-15s | %-15s | %-6s | %-5d |\n",
                                    staff.getUserId(),
                                    staff.getName(),
                                    staff.getRole(),
                                    staff.getGender(),
                                    staff.getAge());
                            foundDoctor = true;
                        }
                    }
                    if (!foundDoctor) {
                        System.out.println("| No matching staff found.                                         |");
                    }
                    System.out.println("=====================================================================");
                    break;

                case 2:
                    System.out.println("                    --- Hospital Staff (Pharmacists) ---                     ");
                    System.out.println("=====================================================================");
                    System.out.printf("| %-10s | %-15s | %-15s | %-6s | %-5s |\n", "ID", "Name", "Role", "Gender",
                            "Age");
                    System.out.println("=====================================================================");
                    boolean foundPharmacist = false;
                    for (Staff staff : stafflist) {
                        if (staff.getRole().equalsIgnoreCase("Pharmacist")) {
                            System.out.printf("| %-10s | %-15s | %-15s | %-6s | %-5d |\n",
                                    staff.getUserId(),
                                    staff.getName(),
                                    staff.getRole(),
                                    staff.getGender(),
                                    staff.getAge());
                            foundPharmacist = true;
                        }
                    }
                    if (!foundPharmacist) {
                        System.out.println("| No matching staff found.                                         |");
                    }
                    System.out.println("=====================================================================");
                    break;

                case 3:
                    System.out.println(
                            "                    --- Hospital Staff (Administrators) ---                     ");
                    System.out.println("=====================================================================");
                    System.out.printf("| %-10s | %-15s | %-15s | %-6s | %-5s |\n", "ID", "Name", "Role", "Gender",
                            "Age");
                    System.out.println("=====================================================================");
                    boolean foundAdmin = false;
                    for (Staff staff : stafflist) {
                        if (staff.getRole().equalsIgnoreCase("Administrator")) {
                            System.out.printf("| %-10s | %-15s | %-15s | %-6s | %-5d |\n",
                                    staff.getUserId(),
                                    staff.getName(),
                                    staff.getRole(),
                                    staff.getGender(),
                                    staff.getAge());
                            foundAdmin = true;
                        }
                    }
                    if (!foundAdmin) {
                        System.out.println("| No matching staff found.                                         |");
                    }
                    System.out.println("=====================================================================");
                    break;

                case 4:
                    System.out.println("                    --- Hospital Staff (Male) ---                     ");
                    System.out.println("=====================================================================");
                    System.out.printf("| %-10s | %-15s | %-15s | %-6s | %-5s |\n", "ID", "Name", "Role", "Gender",
                            "Age");
                    System.out.println("=====================================================================");
                    boolean foundMale = false;
                    for (Staff staff : stafflist) {
                        if (staff.getGender().equalsIgnoreCase("Male")) {
                            System.out.printf("| %-10s | %-15s | %-15s | %-6s | %-5d |\n",
                                    staff.getUserId(),
                                    staff.getName(),
                                    staff.getRole(),
                                    staff.getGender(),
                                    staff.getAge());
                            foundMale = true;
                        }
                    }
                    if (!foundMale) {
                        System.out.println("| No matching staff found.                                         |");
                    }
                    System.out.println("=====================================================================");
                    break;

                case 5:
                    System.out.println("                    --- Hospital Staff (Female) ---                     ");
                    System.out.println("=====================================================================");
                    System.out.printf("| %-10s | %-15s | %-15s | %-6s | %-5s |\n", "ID", "Name", "Role", "Gender",
                            "Age");
                    System.out.println("=====================================================================");
                    boolean foundFemale = false;
                    for (Staff staff : stafflist) {
                        if (staff.getGender().equalsIgnoreCase("Female")) {
                            System.out.printf("| %-10s | %-15s | %-15s | %-6s | %-5d |\n",
                                    staff.getUserId(),
                                    staff.getName(),
                                    staff.getRole(),
                                    staff.getGender(),
                                    staff.getAge());
                            foundFemale = true;
                        }
                    }
                    if (!foundFemale) {
                        System.out.println("| No matching staff found.                                         |");
                    }
                    System.out.println("=====================================================================");
                    break;

                case 6:
                    System.out.println("                    --- Hospital Staff (Age: 21-30) ---                     ");
                    System.out.println("=====================================================================");
                    System.out.printf("| %-10s | %-15s | %-15s | %-6s | %-5s |\n", "ID", "Name", "Role", "Gender",
                            "Age");
                    System.out.println("=====================================================================");
                    boolean foundAge21to30 = false;
                    for (Staff staff : stafflist) {
                        if (staff.getAge() >= 21 && staff.getAge() <= 30) {
                            System.out.printf("| %-10s | %-15s | %-15s | %-6s | %-5d |\n",
                                    staff.getUserId(),
                                    staff.getName(),
                                    staff.getRole(),
                                    staff.getGender(),
                                    staff.getAge());
                            foundAge21to30 = true;
                        }
                    }
                    if (!foundAge21to30) {
                        System.out.println("| No matching staff found.                                         |");
                    }
                    System.out.println("=====================================================================");
                    break;

                case 7:
                    System.out.println("                    --- Hospital Staff (Age: 31-40) ---                     ");
                    System.out.println("=====================================================================");
                    System.out.printf("| %-10s | %-15s | %-15s | %-6s | %-5s |\n", "ID", "Name", "Role", "Gender",
                            "Age");
                    System.out.println("=====================================================================");
                    boolean foundAge31to40 = false;
                    for (Staff staff : stafflist) {
                        if (staff.getAge() >= 31 && staff.getAge() <= 40) {
                            System.out.printf("| %-10s | %-15s | %-15s | %-6s | %-5d |\n",
                                    staff.getUserId(),
                                    staff.getName(),
                                    staff.getRole(),
                                    staff.getGender(),
                                    staff.getAge());
                            foundAge31to40 = true;
                        }
                    }
                    if (!foundAge31to40) {
                        System.out.println("| No matching staff found.                                         |");
                    }
                    System.out.println("=====================================================================");
                    break;

                case 8:
                    System.out.println("                    --- Hospital Staff (Age: 41-50) ---                     ");
                    System.out.println("=====================================================================");
                    System.out.printf("| %-10s | %-15s | %-15s | %-6s | %-5s |\n", "ID", "Name", "Role", "Gender",
                            "Age");
                    System.out.println("=====================================================================");
                    boolean foundAge41to50 = false;
                    for (Staff staff : stafflist) {
                        if (staff.getAge() >= 41 && staff.getAge() <= 50) {
                            System.out.printf("| %-10s | %-15s | %-15s | %-6s | %-5d |\n",
                                    staff.getUserId(),
                                    staff.getName(),
                                    staff.getRole(),
                                    staff.getGender(),
                                    staff.getAge());
                            foundAge41to50 = true;
                        }
                    }
                    if (!foundAge41to50) {
                        System.out.println("| No matching staff found.                                         |");
                    }
                    System.out.println("=====================================================================");
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

        }

    }

    public void addStaff(ArrayList<Staff> stafflist) {
        scanner.nextLine();
        System.out.print("Enter name of staff to add: ");
        String name = scanner.nextLine();
        System.out.print("Enter ID of staff to add: ");
        String ID = scanner.nextLine();
        System.out.print("Enter role of staff to add: ");
        String role = scanner.nextLine();
        System.out.print("Enter gender of staff to add: ");
        String gender = scanner.nextLine();
        System.out.print("Enter age of staff to add: ");
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
        Staff newStaff = new Staff(name, role, gender, age, ID, "Password", type);
        stafflist.add(newStaff);

        System.out.println("Staff member " + newStaff.getUserId() + " has been added.");
    }

    public void removeStaff(ArrayList<Staff> stafflist) {
        scanner.nextLine();
        System.out.print("Enter ID of staff to be removed: ");
        String removeID = scanner.nextLine();
        if (removeID.equalsIgnoreCase(this.getUserId())) {
            System.out.println("Cannot remove current user!");
            return;
        }
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
        System.out.print("Enter ID of staff to be updated: ");
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
                            System.out.print("Enter new ID:");
                            String newID = scanner.nextLine();
                            for (Staff worker : stafflist) {
                                if (newID.equalsIgnoreCase(worker.getUserId())) {
                                    System.out.println("ID already exists.");
                                    break;
                                }
                            }
                            staff.setHospitalId(newID);
                            break;
                        case 2:
                            scanner.nextLine();
                            System.out.print("Enter new name:");
                            String newName = scanner.nextLine();
                            for (Staff worker : stafflist) {
                                if (newName.equalsIgnoreCase(worker.getName())) {
                                    System.out.println("Staff" + newName + "already exists.");
                                    break;
                                }
                            }
                            staff.setName(newName);
                            break;
                        case 3:
                            scanner.nextLine();
                            System.out.print("Enter new role:");
                            staff.setRole(scanner.nextLine());
                            break;
                        case 4:
                            scanner.nextLine();
                            System.out.print("Enter new gender:");
                            staff.setGender(scanner.nextLine());
                            break;
                        case 5:
                            System.out.print("Enter new age:");
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

    public void viewAppts(ArrayList<Appointment> appts, ArrayList<MedicalRecord> records) {
        System.out.println(
                "                                                                   --- All Appointments ---                                                       ");
        System.out.println(
                "=========================================================================================================================================================================");
        System.out.printf("| %-10s | %-20s | %-25s | %-25s | %-25s | %-20s | %-10s | %-10s|\n",
                "Appt ID", "Patient Name", "Patient ID", "Doctor Name", "Doctor ID", "Status", "Date", "Time");
        System.out.println(
                "=========================================================================================================================================================================");

        for (Appointment appointment : appts) {
            System.out.printf("| %-10d | %-20s | %-25s | %-25s | %-25s | %-20s | %-10s | %-10s|\n",
                    appointment.getAppointmentID(),
                    appointment.getPatient().getName(),
                    appointment.getPatient().getPatientId(),
                    appointment.getDoctor().getName(),
                    appointment.getDoctor().getUserId(),
                    appointment.getStatus(),
                    appointment.getDate(),
                    appointment.getTime());
            if (appointment.getStatus() == Status.COMPLETED) {
                for (MedicalRecord record : records) {
                    if (record.getAppointmentId() == appointment.getAppointmentID())
                        System.out.printf("| %-10s | %-20s | %-25s | %-25s | %-25s | %-20s | %-10s | %-10s|\n",
                                "OUTCOME", "Diagnosis: " + record.getDiagnosis(),
                                "Prescription: " + record.getPrescription(), "Service: " + record.getService(),
                                "Notes: " + record.getNotes(), "", "", "");
                }
            }
        }
        System.out.println(
                "=========================================================================================================================================================================");

    }

    public void viewInventory(ArrayList<Medicine> inventory) {
        System.out.println("           --- Medication Inventory ---           ");
        System.out.println("==================================================");
        System.out.printf("| %-20s | %-10s | %-10s |\n", "Medicine Name", "Quantity", "Threshold");
        System.out.println("==================================================");

        for (Medicine medicine : inventory) {
            System.out.printf("| %-20s | %-10d | %-10d |\n",
                    medicine.getName(),
                    medicine.getQuantity(),
                    medicine.getThreshold());
        }
        System.out.println("==================================================");
    }

    public void addMedicine(ArrayList<Medicine> inventory) {
        System.out.println("Enter name of new medicine: ");
        String medicineName = scanner.nextLine();
        System.out.println("Enter stock of new medicine: ");
        int stock = scanner.nextInt();
        System.out.println("Enter low stock alert level of new medicine: ");
        int threshold = scanner.nextInt();
        Medicine medicine = new Medicine(medicineName, "null", stock, threshold);
        inventory.add(medicine);
        System.out.println(medicineName + " has been added to the inventory.");
    }

    public void removeMedicine(ArrayList<Medicine> inventory) {
        scanner.nextLine();
        System.out.print("Enter name of medicine to be removed: ");
        String medicineName = scanner.nextLine();
        Medicine med = null;
        for (Medicine medicine : inventory) {
            if (medicine.getName().equalsIgnoreCase(medicineName)) {
                med = medicine;
            }
        }
        if (med != null) {
            inventory.remove(med);
            System.out.println(medicineName + " has been removed.");
        } else {
            System.out.println(medicineName + "does not exist.");
        }
    }

    public void setMedStock(ArrayList<Medicine> inventory) {
        scanner.nextLine();
        System.out.print("Enter name of medicine to update stock: ");
        String medicineName = scanner.nextLine();
        System.out.print("Enter new stock level: ");
        int stock = scanner.nextInt();
        Medicine med = null;
        for (Medicine medicine : inventory) {
            if (medicine.getName().equalsIgnoreCase(medicineName)) {
                med = medicine;
            }
        }
        if (med != null) {
            med.setQuantity(stock);
            System.out.println(medicineName + "'s stock has been updated to " + stock);
        } else {
            System.out.println(medicineName + " does not exist.");
        }
    }

    public void setMedStockLevel(ArrayList<Medicine> inventory) {
        scanner.nextLine();
        System.out.print("Enter name of medicine to change low stock alert level: ");
        String medicineName = scanner.nextLine();
        System.out.print("Enter new alert level:");
        int stocklevel = scanner.nextInt();
        Medicine med = null;
        for (Medicine medicine : inventory) {
            if (medicine.getName().equalsIgnoreCase(medicineName)) {
                med = medicine;
            }
        }
        if (med != null) {
            med.setThreshold(stocklevel);
            System.out.println(medicineName + "'s low stock alert level has been updated to " + stocklevel);
        } else {
            System.out.println(medicineName + " does not exist.");
        }
    }

    public void approveRequest(ArrayList<Medicine> inventory, ArrayList<ReplenishmentRequest> replenishmentRequests) {
        System.out.println("                     --- Replenishment Requests ---                      ");
        System.out.println("=========================================================================");
        System.out.printf("| %-15s | %-20s | %-15s | %-10s |\n", "Name", "Requested Quantity", "Pharmacist ID",
                "Status");
        System.out.println("=========================================================================");

        for (ReplenishmentRequest request : replenishmentRequests) {
            System.out.printf("| %-15s | %-20d | %-15s | %-10s |\n",
                    request.getMedicineName(),
                    request.getRequestedQuantity(),
                    request.getPharmacistId(),
                    request.getStatus());
        }
        System.out.println("=========================================================================");
        System.out.print("Enter the name of the medicine whose request is to be approved/declined: ");
        String medicineName = scanner.nextLine();
        Medicine med = null;
        for (Medicine medicine : inventory) {
            if (medicine.getName().equalsIgnoreCase(medicineName)) {
                med = medicine;
            }
        }
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
            request.declineRequest();
            System.out.println(med.getName() + " has sufficient stock.");
        }
    }
}