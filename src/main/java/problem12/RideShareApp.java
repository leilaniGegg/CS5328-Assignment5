package problem12;


import problem12.controller.RideController;
import problem12.factory.DriverFactory;
import problem12.factory.UserFactory;
import problem12.ui.DriverUI;
import problem12.ui.UserUI;

public class RideShareApp {
    public static void main(String[] args) {
        // Create controller
        RideController controller = new RideController();

        // Create UI factories
        UserFactory userFactory = new UserFactory(controller);
        DriverFactory driverFactory = new DriverFactory(controller);

        // Create UIs
        UserUI userUI = (UserUI) userFactory.createUI();
        DriverUI driverUI = (DriverUI) driverFactory.createUI();

        // User requests a ride
        int rideId = userUI.requestRide("Downtown", "Airport");
        System.out.println("Ride requested with ID: " + rideId);

        // Driver accepts the ride
        boolean accepted = driverUI.acceptRide(rideId);
        System.out.println("Ride accepted: " + accepted);

        // Driver completes the ride
        boolean completed = driverUI.completeRide(rideId);
        System.out.println("Ride completed: " + completed);

        // User makes a payment
        boolean paymentProcessed = userUI.pay(rideId, 22.00);
        System.out.println("Payment processed: " + paymentProcessed);
    }
}