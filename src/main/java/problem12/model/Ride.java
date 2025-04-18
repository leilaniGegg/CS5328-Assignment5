package problem12.model;

public class Ride {
    private int rideId;
    private int driverId;
    private String pickupLocation;
    private String destination;
    private RideStatus status;

    public Ride(int rideId, int driverId, String pickupLocation, String destination) {
        this.rideId = rideId;
        this.driverId = driverId;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
        this.status = RideStatus.REQUESTED;
    }

    public int getRideId() {
        return rideId;
    }

    public int getDriverId() {
        return driverId;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public String getDestination() {
        return destination;
    }

    public RideStatus getStatus() {
        return status;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }
}
