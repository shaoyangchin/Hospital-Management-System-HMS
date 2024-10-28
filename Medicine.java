import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Medicine {
    private static final int LOW_STOCK_THRESHOLD = 50;
    private String name;
    private String dosage;
    private int quantity;
    private String expirationDate;

    // Standard constructor
    public Medicine(String name, String dosage, int quantity, String expirationDate) {
        this.name = name;
        this.dosage = dosage;
        this.quantity = quantity;
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

    // Check if stock is low
    public boolean isLowStock() {
        return quantity < LOW_STOCK_THRESHOLD;
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
        return new String[]{name, dosage, String.valueOf(quantity), expirationDate};
    }

    // Display Medicine Details
    @Override
    public String toString() {
        return "Medicine{" +
                "name='" + name + '\'' +
                ", dosage='" + dosage + '\'' +
                ", quantity=" + quantity +
                ", expirationDate='" + expirationDate + '\'' +
                '}';
    }
}