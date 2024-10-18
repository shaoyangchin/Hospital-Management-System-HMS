public class Medicine {
    private String medicineName;
    private int stock;
    private int lowStockAlertLevel;

    public Medicine(String medicineName, int stock, int lowStockAlertLevel) {
        this.medicineName = medicineName;
        this.stock = stock;
        this.lowStockAlertLevel = lowStockAlertLevel;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getLowStockAlertLevel() {
        return lowStockAlertLevel;
    }

    public void setLowStockAlertLevel(int lowStockAlertLevel) {
        this.lowStockAlertLevel = lowStockAlertLevel;
    }

    @Override
    public String toString() {
        return "Medicine Name: " + medicineName + ", Stock: " + stock + ", Low Stock Alert Level: " + lowStockAlertLevel;
    }
}