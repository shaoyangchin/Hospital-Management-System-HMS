public class Medicine {
    private int threshold;
    private String name;
    private String dosage;
    private int quantity;

    public Medicine(String name, String dosage, int quantity, int threshold) {
        this.name = name;
        this.dosage = dosage;
        this.quantity = quantity;
        this.threshold = threshold;
    }

    public Medicine() {}

    // Getters
    public String getName() { return name; }
    public String getDosage() { return dosage; }
    public int getQuantity() { return quantity; }
    public int getThreshold() { return threshold; }

    // Setters 
    public void setName(String name) { this.name = name; }
    public void setDosage(String dosage) { this.dosage = dosage; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setThreshold(int threshold) { this.threshold = threshold; }

    public boolean isLowStock() {
        return quantity < threshold;
    }

    public String[] toRowData() {
        return new String[] { name, String.valueOf(quantity), String.valueOf(threshold) };
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "name='" + name + '\'' +
                ", dosage='" + dosage + '\'' +
                ", quantity=" + quantity +
                ", low stock alert level=" + threshold +
                '}';
    }
}