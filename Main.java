import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main {
    private ArrayList<User> users;
    private ArrayList<Patient> patients;
    private ArrayList<Doctor> doctors;
    //private ArrayList<Pharmacist> pharmacists;
    //private ArrayList<Administrator> administrators;

    public void displayDoctorList() {
        for (Doctor doc : doctors) {
            System.out.println("Doctor ID: "+doc.getDoctorID()+", Name: "+doc.getName());
        }
    }

    public Doctor findDoctor(int doctorID) {
        for (Doctor doc : doctors) {
            if (doc.getDoctorID() == doctorID) {
                return doc;
            }
        }
        return null;
    }

    public void initData() {
        users = new ArrayList<>();
        patients = new ArrayList<>();
        doctors = new ArrayList<>();


        //staff file

        File staffFile = new File("Staff_List.xlsx");   //creating a new file instance
        XSSFSheet staffSheet = null;
        try {
            FileInputStream fis = new FileInputStream(staffFile);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            staffSheet = workbook.getSheetAt(0);
            staffSheet.iterator();
            /// testing parameters
        } catch (Exception e) {
        }
        for (Row row : staffSheet) {
            if (row.getRowNum() == 0) continue; // Skip header row
            String userId = row.getCell(0).getStringCellValue();
            String name = row.getCell(1).getStringCellValue();
            String role = row.getCell(2).getStringCellValue();
            String gender = row.getCell(3).getStringCellValue();
            int age = (int) row.getCell(4).getNumericCellValue();
            System.out.println(userId);
            User user = null;
            switch (role) {
                case "Doctor":
                    user = new Doctor(1, name, "Cardiologist", gender, age, userId, "password");
                    doctors.add((Doctor) user);
                    users.add(user);
                    break;
                case "Pharmacist":
                    //user = new Pharmacist(userId, password, userId , "password");
                    //pharmacists.add((Pharmacist) user);
                    break;
                case "Administrator":
                    //user = new Administrator(name ,gender, age , userId , "password");
                    //administrators.add((Administrator) user);
                    break;
                default:
                    continue;
            }
        }

        //patient file

        ///temporary var for testing
        MedicalRecord mr1 = new MedicalRecord("cancer", "chemotherapy", "john56@gmail.com", "80913772");

        File patientFile = new File("Patient_List.xlsx");   //creating a new file instance
        XSSFSheet patientSheet = null;
        try {
            FileInputStream fis = new FileInputStream(patientFile);
            XSSFWorkbook patientWorkbook = new XSSFWorkbook(fis);
            patientSheet = patientWorkbook.getSheetAt(0);
            patientSheet.iterator();
            /// testing parameters
        } catch (Exception e) {
        }


        for (Row row : patientSheet) {
            if (row.getRowNum() == 0) continue; // Skip header row
            String userId = row.getCell(0).getStringCellValue();
            String name = row.getCell(1).getStringCellValue();
            String dob = row.getCell(2).getStringCellValue();
            String gender = row.getCell(3).getStringCellValue();
            String bloodType = row.getCell(4).getStringCellValue();
            String contact = row.getCell(5).getStringCellValue();

            Patient patient = new Patient(1, name, userId, "password", dob, gender, bloodType, contact, mr1);
            patients.add(patient);
            users.add(patient);
        }


        //medicine file

        File medicineFile = new File("Medicine_List.xlsx");   //creating a new file instance
        XSSFSheet medicineSheet = null;
        try {
            FileInputStream fis = new FileInputStream(medicineFile);
            XSSFWorkbook medicineWorkbook = new XSSFWorkbook(fis);
            medicineSheet = medicineWorkbook.getSheetAt(0);
            medicineSheet.iterator();
            /// testing parameters
        } catch (Exception e) {
        }


        for (Row row : medicineSheet) {
            if (row.getRowNum() == 0) continue; // Skip header row
            String medicineName = row.getCell(0).getStringCellValue();
            int stockLevel = (int) row.getCell(1).getNumericCellValue();
            int lowStockAlert = (int) row.getCell(2).getNumericCellValue();
            //Medicine medicine = new Medicine(medicineName, stockLevel, lowStockAlert);

            //medicineInventory.add(medicine);
        }


        //Doctor doctor = new Doctor(1, "Dr. Smith", "Cardiologist", 1, "password");
        //Patient patient = new Patient(101, "John Doe", 1, "password");

        //users.add(doctor);
        //users.add(patient);

        //patients.add(patient);
        //doctors.add(doctor);
    }

    public User login(ArrayList<User> userList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your hospital ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        boolean valid = false;

        for (User user : userList) {
            if (user.validateUser(userId, password)) {
                valid = true;
                if (user != null) {
                    System.out.println("Login successful! Welcome.");
                }
                return user;
            }
        }
        System.out.println("Invalid login credentials. Please try again.");
        return null;
    }

    

    public static void main(String[] args) {

        Main main = new Main();

        main.initData();

        User user = null;


        while (user == null) {
            user = main.login(main.users);
        }

        // create sample appointments 
        ApptManager apptManager = new ApptManager();
        Appointment appt1 = new Appointment(1003, "17/10/2024", "1100");
        Appointment appt2 = new Appointment(1004, "17/10/2024", "1200");
        apptManager.addAppointment(appt1);
        apptManager.addAppointment(appt2);


        if (user instanceof Doctor) {
            Scanner scanner = new Scanner(System.in);
            Doctor doctor = (Doctor) user;
            // Sample appointments
            // Appointment appointment1 = new Appointment(1001, "John Doe", 2);
            // Appointment appointment2 = new Appointment(1002, "Jane Doe", 2);
            // doctor.getAppointments().add(appointment1);
            // doctor.getAppointments().add(appointment2);
            
            while (true) {
                System.out.println("\n--- Doctor Menu ---");
                System.out.println("1. View Patient Medical Records");
                System.out.println("2. Update Patient Medical Records");
                System.out.println("3. View Personal Schedule");
                System.out.println("4. Accept Appointment");
                System.out.println("5. Decline Appointment");
                System.out.println("6. Record Appointment Outcome");
                System.out.println("7. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        Patient patient = Doctor.selectPatient(main.patients);
                        doctor.viewPatientRecords(patient);
                        break;
                    case 2:
                        Patient patientRecord = Doctor.selectPatient(main.patients);
                        System.out.print("Enter diagnosis: ");
                        String diagnosis = scanner.nextLine();
                        System.out.print("Enter prescription: ");
                        String prescription = scanner.nextLine();
                        doctor.updatePatientRecord(patientRecord, diagnosis, prescription);
                        break;
                    case 3:
                        doctor.viewSchedule();
                        break;
                    case 4:
                        System.out.print("Enter appointment ID to accept: ");
                        int acceptID = scanner.nextInt();
                        doctor.acceptAppointment(acceptID);
                        break;
                    case 5:
                        System.out.print("Enter appointment ID to decline: ");
                        int declineID = scanner.nextInt();
                        doctor.declineAppointment(declineID);
                        break;
                    case 6:
                        System.out.print("Enter appointment ID to record outcome: ");
                        int outcomeID = scanner.nextInt();
                        scanner.nextLine();  // Consume newline
                        System.out.print("Enter outcome: ");
                        String outcome = scanner.nextLine();
                        doctor.recordAppointmentOutcome(outcomeID, outcome);
                        break;
                    case 7:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }

        else if (user instanceof Patient) {
            Patient patient = (Patient) user;
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\n--- Patient Menu ---");
                System.out.println("1. View Medical Record");
                System.out.println("2. Update Personal Information");
                System.out.println("3. View Available Appointment Slots");
                System.out.println("4. Schedule an Appointment");
                //System.out.println("5. Reschedule an Appointment");
                //System.out.println("6. Cancel an Appointment");
                //System.out.println("7. View scheduled Appointments");
                //System.out.println("8. View Past Appointment Outcome Records");
                System.out.println("9. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        patient.viewMedicalRecord(patient);
                        break;
                    case 2:
                        System.out.print("Enter email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter phone number: ");
                        String phoneNum = scanner.nextLine();
                        patient.updateRecord(patient, email, phoneNum);
                        break;
                    case 3:
                        //patient.viewAvailAppts();
                        apptManager.displayAvail();
                        break;
                    case 4:
                        System.out.println("Enter an available appointment ID: ");
                        int apptId = scanner.nextInt();
                        main.displayDoctorList();
                        System.out.println("Enter Doctor ID: ");
                        int doctorId = scanner.nextInt();

                        Doctor doc1 = main.findDoctor(doctorId);
                        apptManager.schedulePatient(patient, apptId, doc1);
                        break;
                    case 5:
                        patient.rescheduleAppt();
                    case 9:
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid option. Please try again.");

                }
            }
        }

        /* else if ( user instanceof Pharmacist) {

        }
        else if ( user instanceof Administrator) {

        } */



    }
}
