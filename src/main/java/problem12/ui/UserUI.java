package problem12.ui;


import problem12.controller.RideController;

public class UserUI extends UI {

    public UserUI(RideController controller) {
        super(controller);
    }

    public int requestRide(String location, String destination) {
        return controller.requestRide(location, destination);
    }

    public boolean pay(int rideId, double amount) {
        return controller.processPayment(rideId, amount);
    }
}
