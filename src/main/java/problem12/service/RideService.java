package problem12.service;

import problem12.model.Database;
import problem12.model.Ride;
import problem12.model.RideStatus;

public class RideService {
    private Database database;

    public RideService(Database database) {
        this.database = database;
    }

    public int findDriver(String location, String destination) {
        int driverId = database.findDriver(location);
        int rideId = database.saveRideRequest(driverId, location, destination);
        return rideId;
    }

    public boolean assignDriver(int rideId) {
        Ride ride = database.getRide(rideId);
        if (ride != null && ride.getStatus() == RideStatus.REQUESTED) {
            database.updateRideStatus(rideId, RideStatus.ACCEPTED);
            return true;
        }
        return false;
    }

    public boolean updateRideStatus(int rideId, RideStatus status) {
        Ride ride = database.getRide(rideId);
        if (ride != null) {
            database.updateRideStatus(rideId, status);
            return true;
        }
        return false;
    }
}
