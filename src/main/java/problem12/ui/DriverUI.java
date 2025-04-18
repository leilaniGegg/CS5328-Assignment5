package problem12.ui;


import problem12.controller.RideController;

public class DriverUI extends UI {

    public DriverUI(RideController controller) {
        super(controller);
    }

    public boolean acceptRide(int rideId) {
        return controller.acceptRide(rideId);
    }

    public boolean completeRide(int rideId) {
        return controller.completeRide(rideId);
    }
}
