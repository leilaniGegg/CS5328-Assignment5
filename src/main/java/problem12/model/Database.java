package problem12.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Database {
    private Map<Integer, Ride> rides = new HashMap<>();
    private Map<Integer, Double> paymentHistory = new HashMap<>();
    private AtomicInteger rideIdCounter = new AtomicInteger(1);

    public int findDriver(String location) {
        // In a real implementation, this would search for available drivers near the location
        return 100; // Example driver ID
    }

    public int saveRideRequest(int driverId, String location, String destination) {
        int rideId = rideIdCounter.getAndIncrement();
        Ride ride = new Ride(rideId, driverId, location, destination);
        rides.put(rideId, ride);
        return rideId;
    }

    public Ride getRide(int rideId) {
        return rides.get(rideId);
    }

    public void updateRideStatus(int rideId, RideStatus status) {
        Ride ride = rides.get(rideId);
        if (ride != null) {
            ride.setStatus(status);
        }
    }

    public void savePayment(int rideId, double amount) {
        paymentHistory.put(rideId, amount);
    }

    public Double getPayment(int rideId) {
        return paymentHistory.get(rideId);
    }
}