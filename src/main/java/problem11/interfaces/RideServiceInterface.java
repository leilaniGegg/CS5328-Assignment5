package problem11.interfaces;

import problem11.model.User;

public interface RideServiceInterface {
    int requestRide(User user, String pickup, String destination);
    boolean cancelRide(int rideId);
    boolean completeRide(int rideId);
    double calculateFare(String distance, String duration);
}
