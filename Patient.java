import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Patient extends User {
    private String patientId;
    private String name;
    private ArrayList<MedicalRecord> medicalRecords;
    private String dateOfBirth;
    private String gender;
    private String bloodType;
    private String contactInformation;
    private String phoneNum = null;
    private Appointment appt;

    public Patient(String name, String userId, String password, String dateOfBirth, String gender, String bloodType,
            String contactInformation, ArrayList<MedicalRecord> medicalRecords, UserType userType) {
        super(userId, password, userType);
        this.patientId = userId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.bloodType = bloodType;
        this.contactInformation = contactInformation;
        this.medicalRecords = medicalRecords;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getContact() {
        return contactInformation;
    }

    public String getPhoneNum() {
        if (phoneNum == null) {
            return "not available";
        }
        return phoneNum;
    }


    // New method to expose medical records
    public List<MedicalRecord> getRecord() {
        return medicalRecords;
    }


    public MedicalRecord getRecordForPatient(Patient patient) {
        for (MedicalRecord record : medicalRecords) {
            if (record.getPatientId().equals(patientId)) {
                return record;
            }
        }
        return null; // Return null if not found
    }

    public void setAppointment(Appointment appt) {
        this.appt = appt;
    }

    public Appointment getAppt() {
        return appt;
    }

    public void viewMedicalRecord(Patient patient, HMSDatabase database) {
        System.out.println("\n--- Medical Record: " + patientId + " ---");
    
        // Print table header
        String header = String.format("| %-10s | %-15s | %-10s | %-5s | %-10s | %-25s | %-10s |",
                "Patient ID", "Name", "DOB", "Gender", "Blood Type", "Email", "Phone Number");
        String separator = "=".repeat(header.length());
        System.out.println(separator);
        System.out.println(header);
        System.out.println(separator);
    
    
        // Print the medical record in table format
        System.out.printf("| %-10s | %-15s | %-10s | %-5s | %-10s | %-25s | %-10s |\n",
        patient.getPatientId(),
        patient.getName(),
        patient.getDateOfBirth(),
        patient.getGender(),
        patient.getBloodType(),
        patient.getContact(),
        patient.getPhoneNum()
        );
        System.out.println(separator); // Closing line of the table



        // System.out.println("Viewing medical records for patient ID: " + patient.getPatientId() + ", Name: "
        //         + patient.getName() + ", DOB: " + patient.getDateOfBirth() + ", gender: " + patient.getGender()
        //         + ", Blood Type: " + patient.getBloodType() + ", Email: " + patient.getContact() + ", Phone number: "
        //         + patient.getPhoneNum());
        // //System.out.println(getRecordForPatient(patient));

        System.out.println("Use View Past Appointment Outcome Records option to view past diagnoses and treatments.");

        
    }

    public void updateRecord(Patient patient, String contact, String phone) {
        this.contactInformation = contact;
        this.phoneNum = phone;
    }

    ApptManager manager = new ApptManager();

    public void viewAvailAppts(ArrayList<Appointment> appts, ApptManager apptM, HMSDatabase database, Patient patient) {
        // Displays all available (PENDING) appointments for all patients
        // for (Appointment appointment : appts) {
        // if (appointment.getStatus() == Status.PENDING) {
        // System.out.println(appointment);
        // }
        // }
        // Displays all available (PENDING) appointments for patient logged in
        

        // Scanner scanner = new Scanner(System.in);
        // System.out.print("Enter doctor ID: ");
        // String doctorId = scanner.nextLine();
        // Doctor doctor = database.getDoctorById(doctorId);


        ArrayList<Availability> availList = database.getAvailabilities();
        ArrayList<Doctor> docList = database.getAllDoctors();
        ArrayList<String> schedule = new ArrayList<>();
    
        // Define the 30-minute time slots
        String[] timeSlots = {
            "09:00 AM", "09:30 AM", "10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM",
            "12:00 PM", "12:30 PM", "01:00 PM", "01:30 PM", "02:00 PM", "02:30 PM",
            "03:00 PM", "03:30 PM", "04:00 PM", "04:30 PM", "05:00 PM", "05:30 PM",
            "06:00 PM"
        };
    
        // Initialize all slots as "Free"
        for (String slot : timeSlots) {
            schedule.add("Free");
        }

        // for (Doctor doc : docList) {
        //     for (Availability availSlot : availList) {
        //         if (availSlot.getDoctorId().equals(doc.getUserId())) {
        
        //             String start = availSlot.getStartTime();
        //             String end = availSlot.getEndTime();
    
        
        //             //Find the matching time slot and populate it
        //             for (int i = 0; i < j; i++) {
        //                 if (timeSlots[i].equalsIgnoreCase(start)) {
        //                     schedule.set(i, appt.getPatient().getName() + " (" + appt.getPatient().getUserId() + ")");
        //                     break;
        //                 }
        //             }
        //         }
        //     }
        // }
        

        // String header = String.format("| %-10s | %-20s |", "Time", "Appointment");
        // String separator = "=".repeat(header.length());
        // System.out.println("\n--- Personal Schedule for Dr. " + doctor.getName());
        // System.out.println(separator);
        // System.out.println(header);
        // System.out.println(separator);
    
        for (Doctor doc : docList) {
            for (int a = 0; a < availList.size(); a++) {
                Availability availSlot = availList.get(a);
                int j = 0;
                int g = 0;
    
                if (availSlot.getDoctorId().equals(doc.getUserId())) {
                    String start = availSlot.getStartTime();
                    String end = availSlot.getEndTime();
    
                    if (a == 0 || !availSlot.getDate().equals(availList.get(a - 1).getDate())) {
                        //System.out.println("date: " + availSlot.getDate());
                    }
    
                    for (int index = 0; index < timeSlots.length; index++) {
                        if (timeSlots[index].equals(end)) {
                            j = index;
                        }
                    }
                    for (int index = 0; index < timeSlots.length; index++) {
                        if (timeSlots[index].equals(start)) {
                            g = index;
                        }
                    }
    
                    // for (int i = g; i < j+1; i++) {
                    //     System.out.printf("| %-10s | %-20s |%n", timeSlots[i], schedule.get(i));
                    // }
    
                    for (int i = g; i < j+1; i++) {
                        int apptListLength = appts.size();
                        Appointment newAppt = new Appointment(apptListLength+1, Status.PENDING, null, doc, availSlot.getDate(), timeSlots[i]);
                        
                        List<Appointment> apptCopy = new ArrayList<>(appts);
                        boolean isDuplicate = false;
    
                        for (Appointment appt : apptCopy) {
                            if (appt.getDoctor() != null && appt.getDate() != null && appt.getTime() != null
                                    && appt.getDoctor().equals(newAppt.getDoctor())
                                    && appt.getDate().equals(newAppt.getDate())
                                    && appt.getTime().equals(newAppt.getTime())) {
                                isDuplicate = true;
                                break;
                            }
                        }
                        if (!isDuplicate) {
                            appts.add(newAppt);
                        }
                        
                    }
                }
                //System.out.println(separator); // Closing line of the table
            }
        }
        
        
    
        // Print table header
        String header = String.format("| %-10s | %-20s | %-10s | %-15s | %-10s | %-10s | %-10s | %-10s |",
                "Appt ID", "Patient", "Patient Id", "Doctor", "Doctor Id", "Status", "Date", "Time");
        String separator = "=".repeat(header.length());
        System.out.println(separator);
        System.out.println(header);
        System.out.println(separator);

        for (Appointment appt : appts) {
            if ((appt.getStatus() == Status.PENDING && appt.getPatient() != null && appt.getPatient().getPatientId().equals(patientId))
            || appt.getPatient() == null) {
                //System.out.println(appt);

                // Print the medical record in table format
                    System.out.printf("| %-10s | %-20s | %-10s | %-15s | %-10s | %-10s | %-10s | %-10s |\n",
                    appt.getAppointmentID(),
                    (appt.getPatient() != null ? ( patient.getName())
                        : "No patient assigned"),
                    (appt.getPatient() != null ? ( patient.getPatientId())
                        : "NA"),
                    (appt.getDoctor() != null ? (appt.getDoctor().getName()) : "No doctor assigned"),
                    (appt.getDoctor() != null ? (appt.getDoctor().getUserId()) : "NA"),
                    appt.getStatus(),
                    appt.getDate(),
                    appt.getTime()
                );
            }
        }
        System.out.println(separator); // Closing line of the table
    }

    public void scheduleAppt(Patient patient, ApptManager apptM, ArrayList<Appointment> appts) {
        apptM.schedulePatient(patient, appts);
    }

    public void rescheduleAppt(Patient patient, ApptManager apptM, ArrayList<Appointment> appts) {
        apptM.reschedulePatient(patient, appts);
    }

    public void cancelAppt(int apptId, Patient patient, ApptManager apptM, ArrayList<Appointment> appts) {
        apptM.cancelPatient(apptId, patient, appts);
    }

    public void viewScheduledAppts(Patient patient, ApptManager apptM, ArrayList<Appointment> appts) {
        apptM.viewScheduled(patient, appts);
    }

    public void viewPastApptOutcomes(Patient patient, ApptManager apptM, HMSDatabase database) {
        apptM.viewPastOutcomes(patient, database);
    }


}
