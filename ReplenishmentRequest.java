public class ReplenishmentRequest {
    private String medicineName;
    private int requestedQuantity;
    private String pharmacistId;
    private boolean isApproved;

    public ReplenishmentRequest(String medicineName, int requestedQuantity, String pharmacistId) {
        this.medicineName = medicineName;
        this.requestedQuantity = requestedQuantity;
        this.pharmacistId = pharmacistId;
        this.isApproved = false;
    }

    // Getters
    public String getMedicineName() {
        return medicineName;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public String getPharmacistId() {
        return pharmacistId;
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
                ", pharmacistName='" + pharmacistId + '\'' +
                ", isApproved=" + isApproved +
                '}';
    }
}