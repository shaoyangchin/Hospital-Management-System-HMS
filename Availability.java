public class Availability {
    private String doctorId;
    private String date;
    private String startTime;
    private String endTime;

    public Availability(String doctorId, String date, String startTime, String endTime) {
        this.doctorId = doctorId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
