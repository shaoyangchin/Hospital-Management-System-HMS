public class TimeSlot {
    private String day;
    private String time;

    public TimeSlot(String day, String time) {
        this.day = day;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Day: " + day + ", Time: " + time;
    }
}
