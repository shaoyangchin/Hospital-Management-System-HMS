import java.util.ArrayList;
import java.util.List;

public class ApptManager {
    private List<Appointment> appts = new ArrayList<>();

    public ApptManager() {

    }


    public void addAppointment(Appointment appointment) {
        appts.add(appointment);
    }

    public void displayAllAppointments() {
        for (Appointment appointment : appts) {
            System.out.println(appointment);
        }
    }

    public void displayAvailAppointments() {
        for (Appointment appointment : appts) {
            if (appointment.getStatus() == Status.PENDING) {
                System.out.println(appointment);
            }
        }
    }

    public void schedulePatient(Patient patient, int apptId, Doctor doctor) {
        for (Appointment appt : appts) {
            if (appt.getAppointmentID() == apptId) {
                appt.schedule(patient, doctor);
            }
        }
    }

    public void reschedulePatient(int apptId, Patient patient) {
        for (Appointment appt : appts) {
            if (appt.getAppointmentID() == apptId && 
            appt.getStatus() == Status.CONFIRMED &&
            patient.getUserId() == appt.getPatient().getUserId()) {

            }
            else {
                System.out.println("Error");
            }
        }
    }

    public void cancelPatient(int apptId, Patient patient) {
        for (Appointment appt : appts) {
            if (appt.getAppointmentID() == apptId && 
            patient.getUserId() == appt.getPatient().getUserId() &&
            appt.getStatus() == Status.CONFIRMED) {
                
            }
        }
    }

    public void viewScheduled(Patient patient) {
        int haveAppt = 0;
        for (Appointment appt : appts) {
            if (appt.getPatient() != null &&
            patient.getUserId() == appt.getPatient().getUserId() &&
            appt.getStatus() == Status.PENDING) {
                System.out.println(appt);
                haveAppt++;
            }
        }
        if (haveAppt == 0) {
            System.out.println("No scheduled appointments.");
        }
        
    }

    public void viewPastOutcomes(Patient patient) {
        int haveAppt = 0;
        for (Appointment appt : appts) {
            if (appt.getPatient() != null &&
            patient.getUserId() == appt.getPatient().getUserId() &&
            appt.getStatus() == Status.COMPLETED) {
                System.out.println(appt);
                haveAppt++;
            }
        }
        if (haveAppt == 0) {
            System.out.println("No scheduled appointments.");
        }
    }

}

