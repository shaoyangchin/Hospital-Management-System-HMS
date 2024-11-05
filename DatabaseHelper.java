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
        for (List<String> record : records) {
            if (Objects.equals(record.get(2), "Doctor")){
                users.add(new Doctor(record.get(1), "temp" , record.get(3), Integer.parseInt(record.get(4)),record.get(0), "password" ) );
            }
            else if (Objects.equals(record.get(2), "Pharmacist")){
                Map<String, Medicine> inventory = Collections.emptyMap(); //do we rly need this? should it not be static
                users.add(new Pharmacist(1, record.get(0), record.get(0), "password", inventory, new ApptManager()));
            }
            else{
                //users.add(new Administrator());
            }
        }


        records = readFile("data/Patient_List.csv");
        for (List<String> record : records) {
            MedicalRecord temp = null;
            users.add(new Patient(1, record.get(1),record.get(0), "password",record.get(2),record.get(3),record.get(4),record.get(5),temp ));
        }

        //System.out.println(users);
        return users;
    }

    // Method to initialize patients (dummy data for testing)
    public static ArrayList<Patient> initPatients() {

        ArrayList<Patient> patients = new ArrayList<>();
        List<List<String>> records = readFile("data/Patient_List.csv");
        for (List<String> record : records) {
            MedicalRecord temp = null;
            patients.add(new Patient(1, record.get(1),record.get(0), "password",record.get(2),record.get(3),record.get(4),record.get(5),temp ));
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
