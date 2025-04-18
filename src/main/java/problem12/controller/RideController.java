package problem12.controller;

import problem12.model.Database;
import problem12.model.RideStatus;
import problem12.service.PaymentService;
import problem12.service.RideService;

public class RideController {
    private RideService rideService;
    private PaymentService paymentService;

    public RideController() {
        Database database = new Database();
        this.rideService = new RideService(database);
        this.paymentService = new PaymentService(database);
    }

    // For testing purposes
    public RideController(RideService rideService, PaymentService paymentService) {
        this.rideService = rideService;
        this.paymentService = paymentService;
    }

    public int requestRide(String location, String destination) {
        return rideService.findDriver(location, destination);
    }

    public boolean acceptRide(int rideId) {
        return rideService.assignDriver(rideId);
    }

    public boolean completeRide(int rideId) {
        return rideService.updateRideStatus(rideId, RideStatus.COMPLETED);
    }

    public boolean processPayment(int rideId, double amount) {
        return paymentService.processPayment(rideId, amount);
    }
}
