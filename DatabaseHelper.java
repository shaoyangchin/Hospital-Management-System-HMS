import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DatabaseHelper {

    // Method to initialize users (dummy data for testing)
    public static ArrayList<User> initUsers() {
        ArrayList<User> users = new ArrayList<>();
        List<List<String>> records = readFile("data/Staff_List.csv");
        UserType doc = UserType.DOCTOR;
        UserType pharmacist = UserType.PHARMACIST;

        for (List<String> record : records) {
            if (Objects.equals(record.get(2), "Doctor")){
                users.add(new Doctor(record.get(1), "temp" , record.get(3), Integer.parseInt(record.get(4)),record.get(0), "password" , doc) );
            }
            else if (Objects.equals(record.get(2), "Pharmacist")){
                Map<String, Medicine> inventory = Collections.emptyMap(); //do we rly need this? should it not be static
                users.add(new Pharmacist(1, record.get(0), record.get(0), "password", inventory, new ApptManager(), pharmacist));
            }
            else{
                //users.add(new Administrator());
            }
        }

        List<List<String>> medRecords = readFile("data/MedicalRecord_List.csv");
        ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();
        for (List<String> medrecord : medRecords) {
            medicalRecords.add(new MedicalRecord(medrecord.get(0),medrecord.get(1),medrecord.get(2)));
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
            Patient patient1 = new Patient(record.get(1),record.get(0), "password",record.get(2),record.get(3),record.get(4),record.get(5),temp , p);
            users.add(patient1);
            
        }

        //System.out.println(users);
        return users;
    }

    // Method to initialize patients (dummy data for testing)
    public static ArrayList<Patient> initPatients() {

        ArrayList<Patient> patients = new ArrayList<>();

        List<List<String>> medRecords = readFile("data/MedicalRecord_List.csv");
        ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();
        for (List<String> medrecord : medRecords) {
            medicalRecords.add(new MedicalRecord(medrecord.get(0),medrecord.get(1),medrecord.get(2)));
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
            patients.add(new Patient(record.get(1),record.get(0), "password",record.get(2),record.get(3),record.get(4),record.get(5),temp, p ));
        }

        return patients;
    }

    // Method to initialize doctors (dummy data for testing)
    public static ArrayList<Doctor> initDoctors() {

        ArrayList<Doctor> doctors = new ArrayList<>();
        List<List<String>> records = readFile("data/Staff_List.csv");
        for (List<String> record : records) {
            if (Objects.equals(record.get(2), "Doctor")){
                doctors.add(new Doctor(record.get(1), "temp" , record.get(3), Integer.parseInt(record.get(4)),record.get(0), "password" ) );
            }
        }
        return doctors;
    }

    // Method to initialize pharmacists (dummy data for testing)
    public static ArrayList<Pharmacist> initPharmacists() {
        ArrayList<Pharmacist> pharmacists = new ArrayList<>();

        List<List<String>> records = readFile("data/Staff_List.csv");
        for (List<String> record : records) {
            if (Objects.equals(record.get(2), "Pharmacist")){
                Map<String, Medicine> inventory = Collections.emptyMap(); //do we rly need this? should it not be static
                pharmacists.add(new Pharmacist(1, record.get(0), record.get(0), "password", inventory, new ApptManager()));
            }
        }

        return pharmacists;
    }

    // Method to initialize administrators (dummy data for testing)
    //public static ArrayList<Administrator> initAdministrators() {
        //ArrayList<Administrator> administrators = new ArrayList<>();

        //return administrators;
    //}

    public static Map<String, Medicine> initMedicines() {
        Map<String, Medicine> medicines = new HashMap<>();
        List<List<String>> records = readFile("data/Medicine_List.csv");
        String name;
        for (List<String> record : records) {
            name = record.get(0);
            medicines.put(name,new Medicine(name,"temp", Integer.parseInt(record.get(1)),"temp"));
        }
        return medicines;
    }

    public static ArrayList<Appointment> initAppointments(ArrayList<Patient> patients, ArrayList<Doctor> doctors) {
        ArrayList<Appointment> appointments = new ArrayList<>();

        List<List<String>> records = readFile("data/Appointment_List.csv");
        String name;
        String patientId;
        String doctorId;
        Patient patient = null;
        Doctor doctor = null;
        for (List<String> record : records) {
            patientId = record.get(3);
            for (Patient p : patients){
                if (Objects.equals(p.getUserId(),patientId)){
                    patient = p;
                    break;
                }
            }
            doctorId = record.get(4);
            for (Doctor d : doctors){
                if (Objects.equals(d.getUserId(),doctorId)){
                    doctor = d;
                    break;
                }
            }
            appointments.add(new Appointment(Integer.parseInt(record.get(0)), record.get(5),record.get(6),Status.valueOf(record.get(1)),patient,doctor));
        }

        return appointments;

    }

    public static ArrayList<ReplenishmentRequest> initReplenishmentRequests() {
        ArrayList<ReplenishmentRequest> replenishmentRequests = new ArrayList<>();
        List<List<String>> records = readFile("data/ReplenishmentRequest_List.csv");
        for (List<String> record : records) {
            replenishmentRequests.add(new ReplenishmentRequest(record.get(0),Integer.parseInt(record.get(1)),record.get(2)));
        }
        return replenishmentRequests;
    }

    public static List<List<String>> readFile(String fileName) {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine().split(",");
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
                //System.out.println(Arrays.toString(records.toArray()));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return records;
    }

    //public static void main(String[] args) {
    //    initUsers();
    //}
}
