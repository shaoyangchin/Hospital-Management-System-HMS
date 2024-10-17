import java.util.ArrayList;
import java.util.List;

public class ApptManager {
    private List<Appointment> appts = new ArrayList<>();

    public ApptManager() {

    }


    public void addAppointment(Appointment appointment) {
        appts.add(appointment);
    }

    public void displayAppointments() {
        for (Appointment appointment : appts) {
            System.out.println(appointment);
        }
    }

    public void displayAvail() {
        for (Appointment appointment : appts) {
            if (appointment.getStatus() == "Pending") {
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

    public void reschedulePatient() {
        for (Appointment appointment : appts) {
            if (appointment.getStatus() == "Confirmed") {
            }
        }
    }
}

