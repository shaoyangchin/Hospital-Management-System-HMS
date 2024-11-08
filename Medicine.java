import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Medicine {
    // private static final int LOW_STOCK_THRESHOLD = 50;
    // each medicine has a different threshold, admins can change
    private int threshold;
    private String name;
    private String dosage;
    private int quantity;
    private String expirationDate;

    // Standard constructor
    public Medicine(String name, String dosage, int quantity, int threshold, String expirationDate) {
        this.name = name;
        this.dosage = dosage;
        this.quantity = quantity;
        this.threshold = threshold;
        this.expirationDate = expirationDate;
    }

    // Default constructor for flexibility if needed in database operations
    public Medicine() {
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDosage() {
        return dosage;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public int getThreshold() {
        return threshold;
    }

    // Setters for updates from HMSDatabase
    public void setName(String name) {
        this.name = name;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    // Check if stock is low
    public boolean isLowStock() {
        // return quantity < LOW_STOCK_THRESHOLD;
        return quantity < threshold;
    }

    // Check if medicine is expired based on the current date
    public boolean isExpired(String currentDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate expiration = LocalDate.parse(expirationDate, formatter);
            LocalDate current = LocalDate.parse(currentDate, formatter);
            return current.isAfter(expiration);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format: " + e.getMessage());
            return false;
        }
    }

    // Convert Medicine data to array for easier data handling in Excel operations
    public String[] toRowData() {
        return new String[] { name, dosage, String.valueOf(quantity), expirationDate };
    }

    // Display Medicine Details
    @Override
    public String toString() {
        return "Medicine{" +
                "name='" + name + '\'' +
                ", dosage='" + dosage + '\'' +
                ", quantity=" + quantity +
                ", low stock alert level" + threshold + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                '}';
    }
}