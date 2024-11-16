import java.io.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DatabaseHelper {
    public static List<String> appListFields = getHeader("data/Appointment_List.csv");
    public static List<String> medRecFields = getHeader("data/MedicalRecord_List.csv");
    public static List<String> medicineFields = getHeader("data/Medicine_List.csv");
    public static List<String> patientFields = getHeader("data/Patient_List.csv");
    public static List<String> replenishmentFields = getHeader("data/Replenishment_List.csv");
    public static List<String> staffFields = getHeader("data/Staff_List.csv");
    public static List<String> availabilityFields = getHeader("data/Availability_List.csv");


    // Method to initialize users (dummy data for testing)
    public static ArrayList<User> initUsers() {
        ArrayList<User> users = new ArrayList<>();
        List<List<String>> records = readFile("data/Staff_List.csv");
        UserType doc = UserType.DOCTOR;
        UserType pharmacist = UserType.PHARMACIST;
        UserType admin = UserType.ADMINISTRATOR;

        for (List<String> record : records) {
            if (Objects.equals(record.get(2), "Doctor")) {
                users.add(new Doctor(record.get(1), record.get(3), Integer.parseInt(record.get(4)),
                        record.get(0), record.get(5), doc));
            } else if (Objects.equals(record.get(2), "Pharmacist")) {
                // Map<String, Medicine> inventory = Collections.emptyMap(); // do we rly need
                // this? should it not be
                // static
                users.add(new Pharmacist(record.get(1), record.get(3), Integer.parseInt(record.get(4)),
                        record.get(0), record.get(5), pharmacist));
            } else if (Objects.equals(record.get(2), "Administrator")) {
                users.add(new Administrator(record.get(1), record.get(3), Integer.parseInt(record.get(4)),
                        record.get(0), record.get(5),
                        admin));
            }
        }

        List<List<String>> medRecords = readFile("data/MedicalRecord_List.csv");
        ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();
        for (List<String> medrecord : medRecords) {
            medicalRecords.add(new MedicalRecord(medrecord.get(0), medrecord.get(1), medrecord.get(2), Integer.parseInt(medrecord.get(3)),medrecord.get(4),medrecord.get(5)));

        }

        records = readFile("data/Patient_List.csv");
        for (List<String> record : records) {
            ArrayList<MedicalRecord> temp = new ArrayList<>();
            for (MedicalRecord mr : medicalRecords) {
                if (Objects.equals(mr.getPatientId(), record.get(0))) {
                    temp.add(mr);
                }
            }
            UserType p = UserType.PATIENT;
            Patient patient1 = new Patient(record.get(1), record.get(0), record.get(6), record.get(2), record.get(3),
                    record.get(4), record.get(5), temp, p);
            users.add(patient1);

        }

        // System.out.println(users);
        return users;
    }

    // Method to initialize patients (dummy data for testing)
    public static ArrayList<Patient> initPatients() {

        ArrayList<Patient> patients = new ArrayList<>();

        List<List<String>> medRecords = readFile("data/MedicalRecord_List.csv");
        ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();
        for (List<String> medrecord : medRecords) {
            medicalRecords.add(new MedicalRecord(medrecord.get(0), medrecord.get(1), medrecord.get(2), Integer.parseInt(medrecord.get(3)),medrecord.get(4),medrecord.get(5)));

        }

        List<List<String>> records = readFile("data/Patient_List.csv");
        for (List<String> record : records) {
            ArrayList<MedicalRecord> temp = new ArrayList<>();
            for (MedicalRecord mr : medicalRecords) {
                if (Objects.equals(mr.getPatientId(), record.get(0))) {
                    temp.add(mr);
                }
            }
            UserType p = UserType.PATIENT;
            patients.add(new Patient(record.get(1), record.get(0), record.get(6), record.get(2), record.get(3),
                    record.get(4), record.get(5), temp, p));
        }

        return patients;
    }

    public static ArrayList<MedicalRecord> initMedicalRecords() {
        List<List<String>> medRecords = readFile("data/MedicalRecord_List.csv");
        ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();
        for (List<String> medrecord : medRecords) {
            medicalRecords.add(new MedicalRecord(medrecord.get(0), medrecord.get(1), medrecord.get(2), Integer.parseInt(medrecord.get(3)),medrecord.get(4),medrecord.get(5)));
        }
        return medicalRecords;
    }

    // Method to initialize doctors (dummy data for testing)
    public static ArrayList<Doctor> initDoctors() {

        ArrayList<Doctor> doctors = new ArrayList<>();
        List<List<String>> records = readFile("data/Staff_List.csv");
        for (List<String> record : records) {
            if (Objects.equals(record.get(2), "Doctor")) {
                doctors.add(new Doctor(record.get(1), record.get(3), Integer.parseInt(record.get(4)),
                        record.get(0), record.get(5), UserType.DOCTOR));
            }
        }
        return doctors;
    }

    // Method to initialize pharmacists (dummy data for testing)
    public static ArrayList<Pharmacist> initPharmacists() {
        ArrayList<Pharmacist> pharmacists = new ArrayList<>();

        List<List<String>> records = readFile("data/Staff_List.csv");
        for (List<String> record : records) {
            if (Objects.equals(record.get(2), "Pharmacist")) {
                // Map<String, Medicine> inventory = Collections.emptyMap(); // do we rly need
                // this? should it not be
                // static
                pharmacists
                        .add(new Pharmacist(record.get(1), record.get(3), Integer.parseInt(record.get(4)),
                                record.get(0), record.get(5), UserType.PHARMACIST));
            }
        }

        return pharmacists;
    }

    // Method to initialize administrators (dummy data for testing)
    public static ArrayList<Administrator> initAdministrators() {
        ArrayList<Administrator> administrators = new ArrayList<>();
        UserType admin = UserType.ADMINISTRATOR;
        List<List<String>> records = readFile("data/Staff_List.csv");
        for (List<String> record : records) {
            if (Objects.equals(record.get(2), "Administrator")) {
                administrators.add(new Administrator(record.get(1), record.get(3), Integer.parseInt(record.get(4)),
                        record.get(0), record.get(5), admin));
            }
        }
        return administrators;
    }

    public static ArrayList<Medicine> initMedicines() {
        ArrayList<Medicine> medicines = new ArrayList<>();
        List<List<String>> records = readFile("data/Medicine_List.csv");
        for (List<String> record : records) {
            medicines.add(new Medicine(
                record.get(0),    // name
                "Standard",       // default dosage
                Integer.parseInt(record.get(1)),  // quantity
                Integer.parseInt(record.get(2))   // threshold
            ));
        }
        return medicines;
    }

    public static Doctor findDoctorById(String doctorId, ArrayList<Doctor> doctors) {
        for (Doctor doctor : doctors) {
            if (doctor.getUserId().equals(doctorId)) {
                return doctor;
            }
        }
        return null; // Return null if no matching doctor is found
    }

    public static Patient findPatientById(String patientId, ArrayList<Patient> patients) {
        for (Patient patient : patients) {
            if (patient.getUserId().equals(patientId)) {
                return patient;
            }
        }
        return null; // Return null if no matching patient is found
    }
    
    

    public static ArrayList<Appointment> initAppointments(ArrayList<Patient> patients, ArrayList<Doctor> doctors) {
        ArrayList<Appointment> appointments = new ArrayList<>();
        List<List<String>> records = readFile("data/Appointment_List.csv");
    
        for (List<String> record : records) {
            // Debug: Print each record
            System.out.println("Loaded Appointment Record: " + record);
    
            Patient patient = findPatientById(record.get(2), patients);
            Doctor doctor = findDoctorById(record.get(3), doctors);
    
            if (doctor != null) {
                appointments.add(new Appointment(
                    Integer.parseInt(record.get(0)), // Appointment ID
                    Status.valueOf(record.get(1).toUpperCase()), // Status
                    patient, // Patient object
                    doctor, // Doctor object
                    record.get(4), // Date
                    record.get(5)  // Time
                ));
            }
        }
    
        return appointments;
    }
    
    

    public static ArrayList<ReplenishmentRequest> initReplenishmentRequests() {
        ArrayList<ReplenishmentRequest> requests = new ArrayList<>();
        List<List<String>> records = readFile("data/Replenishment_List.csv");
        for (List<String> record : records) {
            ReplenishmentRequest request = new ReplenishmentRequest(
                record.get(0),
                Integer.parseInt(record.get(1)),
                record.get(2)
            );
            if (record.size() > 3 && Boolean.parseBoolean(record.get(3))) {
                request.approveRequest();
            }
            requests.add(request);
        }
        return requests;
    }

    public static ArrayList<Staff> initStafflist() {
        ArrayList<Staff> stafflist = new ArrayList<>();
        List<List<String>> records = readFile("data/Staff_List.csv");

        for (List<String> record : records) {
            UserType type = null;
            switch (record.get(2)) {
                case "Administrator":
                    type = UserType.ADMINISTRATOR;
                case "Doctor":
                    type = UserType.DOCTOR;
                case "Pharmacist":
                    type = UserType.PHARMACIST;

            }
            stafflist.add(new Staff(record.get(1), record.get(2), record.get(3), Integer.parseInt(record.get(4)),
                    record.get(0), "password", type));
        }
        return stafflist;
    }

    public static ArrayList<Availability> initAvailabilities() {
        ArrayList<Availability> availabilities = new ArrayList<>();
        List<List<String>> records = readFile("data/Availability_List.csv");
    
        for (List<String> record : records) {
            if (!record.isEmpty() && record.size() >= 4) { // Ensure valid record
                availabilities.add(new Availability(
                    record.get(0), // Doctor ID
                    record.get(1), // Date
                    record.get(2), // Start Time
                    record.get(3)  // End Time
                ));
            }
        }
        return availabilities;
    }
    
    

    public static List<List<String>> readFile(String fileName) {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine().split(",");
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
                // System.out.println(Arrays.toString(records.toArray()));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return records;
    }

    public static void getFields() {

    }

    public static List<String> getHeader(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return Arrays.asList(br.readLine().split(","));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * public static <T> void writeArrayListToCSV(ArrayList<T> list, String
     * filePath) {
     * if (list == null || list.isEmpty()) {
     * System.out.println("The list is empty; no data to write.");
     * return;
     * }
     * 
     * try (FileWriter writer = new FileWriter(filePath)) {
     * // Get all fields of the first object (assuming all objects have the same
     * fields)
     * T firstItem = list.get(0);
     * Field[] fields = firstItem.getClass().getDeclaredFields();
     * 
     * // header
     * for (Field field : fields) {
     * writer.append(field.getName()).append(",");
     * }
     * writer.append("\n");
     * 
     * // rows
     * for (T item : list) {
     * for (Field field : fields) {
     * field.setAccessible(true); // Allow access to private fields
     * Object value = field.get(item); // Get the value of the field
     * writer.append(value != null ? value.toString() : "").append(",");
     * }
     * writer.append("\n");
     * }
     * 
     * System.out.println("CSV file created successfully at " + filePath);
     * } catch (IOException | IllegalAccessException e) {
     * e.printStackTrace();
     * }
     * }
     */

    public static void resetFile(String ref, String destination) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ref));
                FileWriter writer = new FileWriter(destination)) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.write("\n");
            }

            System.out.println("File reset successful. Destination file overwritten with reference file content.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void resetAllFiles() {
        resetFile("data/Patient_List_Reference.csv", "data/Patient_List.csv");
        resetFile("data/Appointment_List_Reference.csv", "data/Appointment_List.csv");
        resetFile("data/Medicine_List_Reference.csv", "data/Medicine_List.csv");
        resetFile("data/Replenishment_List_Reference.csv", "data/Replenishment_List.csv");
        resetFile("data/Staff_List_Reference.csv", "data/Staff_List.csv");
        System.out.println("All Files Reset.");
    }

    public static void updateFiles(HMSDatabase database) {

        System.out.println("All CSV files have been updated.");
    }

    public static <T> void saveToCsv(ArrayList<T> list, String filePath, List<String> fieldNames, int listIndex) {
        if (list == null || list.isEmpty()) {
            System.out.println("The list is empty; no data to write for " + filePath);
            return;
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            // Write CSV header
            for (String fieldName : fieldNames) {
                writer.append(fieldName).append(",");
            }
            writer.append("\n");

            // Write CSV rows
            switch (listIndex) {
                // Appt
                case 0:
                    for (T item : list) {
                        // item = (Appointment)item;
                        writer.append(item != null ? String.valueOf(((Appointment) item).getAppointmentID()) : "")
                                .append(",");
                        writer.append(item != null ? String.valueOf(((Appointment) item).getStatus()) : "").append(",");
                        writer.append(item != null ? String.valueOf(((Appointment) item).getPatient().getUserId()) : "")
                                .append(",");
                        writer.append(item != null ? String.valueOf(((Appointment) item).getDoctor().getUserId()) : "")
                                .append(",");
                        writer.append(item != null ? String.valueOf(((Appointment) item).getDate()) : "").append(",");
                        writer.append(item != null ? String.valueOf(((Appointment) item).getTime()) : "");
                        writer.append("\n");
                    }
                    break;
                // Med Rec
                case 1:
                    for (T item : list) {
                        // item = (Appointment)item;
                        writer.append(item != null ? String.valueOf(((MedicalRecord) item).getDiagnosis()) : "")
                                .append(",");
                        writer.append(item != null ? String.valueOf(((MedicalRecord) item).getPrescription()) : "")
                                .append(",");
                        writer.append(item != null ? String.valueOf(((MedicalRecord) item).getPatientId()) : "").append(",");;
                        writer.append(item != null ? String.valueOf(((MedicalRecord) item).getAppointmentId()) : "").append(",");;
                        writer.append(item != null ? String.valueOf(((MedicalRecord) item).getNotes()) : "").append(",");;
                        writer.append(item != null ? String.valueOf(((MedicalRecord) item).getService()) : "");
                        writer.append("\n");
                    }
                    break;
                // Patient
                case 2:
                    for (T item : list) {
                        // item = (Appointment)item;
                        writer.append(item != null ? String.valueOf(((Patient) item).getUserId()) : "").append(",");
                        writer.append(item != null ? String.valueOf(((Patient) item).getName()) : "").append(",");
                        writer.append(item != null ? String.valueOf(((Patient) item).getDateOfBirth()) : "")
                                .append(",");
                        writer.append(item != null ? String.valueOf(((Patient) item).getGender()) : "").append(",");
                        writer.append(item != null ? String.valueOf(((Patient) item).getBloodType()) : "").append(",");
                        writer.append(item != null ? String.valueOf(((Patient) item).getContact()) : "");
                        writer.append("\n");
                    }
                    break;

                // Replenishment
                case 3:
                    for (T item : list) {
                        // item = (Appointment)item;
                        writer.append(
                                item != null ? String.valueOf(((ReplenishmentRequest) item).getMedicineName()) : "")
                                .append(",");
                        writer.append(
                                item != null ? String.valueOf(((ReplenishmentRequest) item).getRequestedQuantity())
                                        : "")
                                .append(",");
                        writer.append(
                                item != null ? String.valueOf(((ReplenishmentRequest) item).getPharmacistId()) : "");
                        writer.append(
                                item != null ? String.valueOf(((ReplenishmentRequest) item).isApproved()) : "");
                        writer.append("\n");
                    }
                    break;

                // Staff
                case 4:
                    for (T item : list) {
                        // item = (Appointment)item;
                        writer.append(item != null ? String.valueOf(((Staff) item).getUserId()) : "").append(",");
                        writer.append(item != null ? String.valueOf(((Staff) item).getName()) : "").append(",");
                        writer.append(item != null ? String.valueOf(((Staff) item).getRole()) : "").append(",");
                        writer.append(item != null ? String.valueOf(((Staff) item).getGender()) : "").append(",");
                        writer.append(item != null ? String.valueOf(((Staff) item).getAge()) : "");
                        writer.append("\n");
                    }

                    break;
                //
                case 5:
                    break;
                   
                // Availabilities
                case 6:
                    for (T item : list) {
                        writer.append(item != null ? String.valueOf(((Availability) item).getDoctorId()) : "").append(",");
                        writer.append(item != null ? String.valueOf(((Availability) item).getDate()) : "").append(",");
                        writer.append(item != null ? String.valueOf(((Availability) item).getStartTime()) : "").append(",");
                        writer.append(item != null ? String.valueOf(((Availability) item).getEndTime()) : "");
                        writer.append("\n");
                    }
                    break;    

            }

            System.out.println("CSV file updated successfully at " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveDatabase(HMSDatabase database) {
        saveToCsv(database.getAppointments(), "data/Appointment_List.csv", appListFields, 0);
        saveToCsv(database.getRecords(), "data/MedicalRecord_List.csv", medRecFields, 1);
        saveToCsv(database.getPatients(), "data/Patient_List.csv", patientFields, 2);
        saveToCsv(database.getReplenishmentRequests(), "data/Replenishment_List.csv", replenishmentFields, 3);
        saveToCsv(database.getStaff(), "data/Staff_List.csv", staffFields, 4);
        saveToCsv(database.getMedicines(), "data/Medicine_List.csv", medicineFields, 5);
        saveToCsv(database.getAvailabilities(), "data/Availability_List.csv", availabilityFields, 6);
    }

    public static void saveAvailabilityList(ArrayList<Availability> availabilities) {
        try (FileWriter writer = new FileWriter("data/Availability_List.csv")) {
            // Write CSV header
            writer.write("Doctor ID,Date,Start Time,End Time\n");
    
            // Write CSV rows
            for (Availability availability : availabilities) {
                writer.write(availability.getDoctorId() + "," +
                             availability.getDate() + "," +
                             availability.getStartTime() + "," +
                             availability.getEndTime() + "\n");
            }
    
            System.out.println("Availability_List.csv updated successfully.");
        } catch (IOException e) {
            System.out.println("Error saving availability list: " + e.getMessage());
        }
    }
    

    public static void saveReplenishmentRequest(ReplenishmentRequest request) {
        try (FileWriter writer = new FileWriter("data/Replenishment_List.csv", true)) { // Append mode
            // Write header if file is empty
            if (new File("data/Replenishment_List.csv").length() == 0) {
                writer.write("medicineName,requestedQuantity,pharmacistId,isApproved\n");
            }

            // Append new request
            writer.append(request.getMedicineName()).append(",")
                 .append(String.valueOf(request.getRequestedQuantity())).append(",")
                 .append(request.getPharmacistId()).append(",")
                 .append(String.valueOf(request.isApproved())).append("\n");
        } catch (IOException e) {
            System.out.println("Error saving replenishment request: " + e.getMessage());
        }
    }

    public static void updateReplenishmentRequests(ArrayList<ReplenishmentRequest> requests) {
        try (FileWriter writer = new FileWriter("data/Replenishment_List.csv")) {
            // Write header
            writer.write("medicineName,requestedQuantity,pharmacistId,isApproved\n");

            // Write all requests
            for (ReplenishmentRequest req : requests) {
                writer.append(req.getMedicineName()).append(",")
                     .append(String.valueOf(req.getRequestedQuantity())).append(",")
                     .append(req.getPharmacistId()).append(",")
                     .append(String.valueOf(req.isApproved())).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Error updating replenishment requests: " + e.getMessage());
        }
    }

    // Helper method to get Field from class or its superclasses

    public static void main(String[] args) throws IOException {
        resetAllFiles();
        HMSDatabase database = new HMSDatabase();
        ArrayList<Patient> patients = initPatients();
        // writeArrayListToCSV(patients, "test.csv");
        resetFile("data/Patient_List.csv", "test.csv");
        saveDatabase(database);
        // updateFiles(database);
    }
}
