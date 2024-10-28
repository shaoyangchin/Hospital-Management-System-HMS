public class ReplenishmentRequest {
    private String medicineName;
    private int requestedQuantity;
    private String pharmacistName;
    private boolean isApproved;

    public ReplenishmentRequest(String medicineName, int requestedQuantity, String pharmacistName) {
        this.medicineName = medicineName;
        this.requestedQuantity = requestedQuantity;
        this.pharmacistName = pharmacistName;
        this.isApproved = false;
    }

    // Getters
    public String getMedicineName() {
        return medicineName;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public String getPharmacistName() {
        return pharmacistName;
    }

    public boolean isApproved() {
        return isApproved;
    }

    // Approve the request
    public void approveRequest() {
        isApproved = true;
    }

    @Override
    public String toString() {
        return "ReplenishmentRequest{" +
                "medicineName='" + medicineName + '\'' +
                ", requestedQuantity=" + requestedQuantity +
                ", pharmacistName='" + pharmacistName + '\'' +
                ", isApproved=" + isApproved +
                '}';
    }
}