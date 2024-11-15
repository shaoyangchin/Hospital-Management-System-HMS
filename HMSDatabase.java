import java.util.ArrayList;
import java.util.Map;

public class HMSDatabase {
    private ArrayList<User> users;
    private ArrayList<Patient> patients;
    private ArrayList<Doctor> doctors;
    private ArrayList<Pharmacist> pharmacists;
    private ArrayList<Administrator> administrators;
    private ArrayList<Medicine> medicineInventory;
    private ArrayList<Appointment> appointments;
    private ApptManager apptManager;
    private ArrayList<ReplenishmentRequest> replenishmentRequests;
    private ArrayList<Staff> stafflist;
    private ArrayList<MedicalRecord> medicalRecords;

    // Constructor to initialize the database
    public HMSDatabase() {
        users = DatabaseHelper.initUsers(); // Initialize users
        patients = DatabaseHelper.initPatients(); // Initialize patients
        doctors = DatabaseHelper.initDoctors(); // Initialize doctors
        pharmacists = DatabaseHelper.initPharmacists(); // Initialize pharmacists
        administrators = DatabaseHelper.initAdministrators(); // Initialize administrators
        medicineInventory = DatabaseHelper.initMedicines(); // Initialize an empty medicine inventory
        appointments = DatabaseHelper.initAppointments(patients, doctors);
        replenishmentRequests = DatabaseHelper.initReplenishmentRequests();
        stafflist = DatabaseHelper.initStafflist();
        medicalRecords = DatabaseHelper.initMedicalRecords();
    }

    // Method to get Appt manager

    public ApptManager getApptManager() {
        return apptManager;
    }

    // Method to get all users
    public ArrayList<User> getUsers() {
        return users;
    }

    // Method to add a new user
    public void addUser(User user) {
        users.add(user);
    }

    // Method to get a user by ID
    public User getUserById(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    // Method to add a new patient
    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    // Method to get a patient by ID
    public Patient getPatientById(String patientId) {
        for (Patient patient : patients) {
            if (patient.getUserId().equals(patientId)) {
                return patient;
            }
        }
        return null;
    }

    // Method to add a new doctor
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    // Method to get a doctor by ID
    public Doctor getDoctorById(String doctorId) {
        for (Doctor doctor : doctors) {
            if (doctor.getUserId().equals(doctorId)) {
                return doctor;
            }
        }
        return null;
    }

    // Method to add a new medicine to the inventory
    public void addMedicine(Medicine medicine) {
        medicineInventory.add(medicine);
    }

    // Method to get a medicine by name
    public Medicine getMedicineByName(String name) {
        for (Medicine medicine : medicineInventory) {
            if (medicine.getName().equals(name)) {
                return medicine;
            }
        }
        return null;
    }

    // Method to check if a medicine exists in the inventory
    public boolean isMedicineAvailable(String name) {
        for (Medicine medicine : medicineInventory) {
            if (medicine.getName().equals(name)) {
                return true;
            }
        }
        return false;

    }

    // Method to remove a medicine from the inventory
    public void removeMedicine(String name) {
        medicineInventory.remove(name);
    }

    public ArrayList<Medicine> getInventory() {
        return medicineInventory;
    }

    public ArrayList<Staff> getStaff() {
        return stafflist;
    }

    // Method to get all patients
    public ArrayList<Patient> getAllPatients() {
        return patients;
    }

    // Method to get all doctors
    public ArrayList<Doctor> getAllDoctors() {
        return doctors;
    }

    // Method to get all pharmacists
    public ArrayList<Pharmacist> getAllPharmacists() {
        return pharmacists;
    }

    // Method to get all administrators
    public ArrayList<Administrator> getAllAdministrators() {
        return administrators;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public ArrayList<Staff> getStaffList() {
        return stafflist;
    }

    public ArrayList<Medicine> getMedicines() {
        return medicineInventory;
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public ArrayList<ReplenishmentRequest> getReplenishmentRequests() {
        return replenishmentRequests;
    }

    public ArrayList<MedicalRecord> getRecords() {
        return medicalRecords;
    }

    public void resetDatabase() {
        DatabaseHelper.resetAllFiles();
    }

    public void saveDatabase() {
        DatabaseHelper.saveDatabase(this);
    }
}
