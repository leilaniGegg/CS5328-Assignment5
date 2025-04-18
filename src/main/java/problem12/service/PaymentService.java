package problem12.service;

import problem12.model.Database;
import problem12.model.Ride;
import problem12.model.RideStatus;

public class PaymentService {
    private Database database;

    public PaymentService(Database database) {
        this.database = database;
    }

    public boolean processPayment(int rideId, double amount) {
        Ride ride = database.getRide(rideId);
        if (ride != null && ride.getStatus() == RideStatus.COMPLETED) {
            database.savePayment(rideId, amount);
            return true;
        }
        return false;
    }
}
