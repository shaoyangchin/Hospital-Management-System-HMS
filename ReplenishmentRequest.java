public class ReplenishmentRequest {
    private String medicineName;
    private int requestedQuantity;
    private String pharmacistId;
    // private boolean isApproved;
    private Status status;

    public ReplenishmentRequest(String medicineName, int requestedQuantity, String pharmacistId) {
        this.medicineName = medicineName;
        this.requestedQuantity = requestedQuantity;
        this.pharmacistId = pharmacistId;
        // this.isApproved = false;
        this.status = Status.PENDING;
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

    /*
     * public boolean isApproved() {
     * return isApproved;
     * }
     */

    public Status getStatus() {
        return status;
    }

    // Approve the request
    public void approveRequest() {
        // isApproved = true;
        this.status = Status.COMPLETED;
    }

    public void declineRequest() {
        this.status = Status.DECLINED;
    }

    @Override
    public String toString() {
        return "ReplenishmentRequest{" +
                "medicineName='" + medicineName + '\'' +
                ", requestedQuantity=" + requestedQuantity +
                ", pharmacistName='" + pharmacistId + '\'' +
                // ", isApproved=" + isApproved +
                ", Status=" + status +
                '}';
    }
}