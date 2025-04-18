package problem12;

import org.junit.jupiter.api.Test;
import problem12.controller.RideController;
import problem12.factory.DriverFactory;
import problem12.factory.UserFactory;
import problem12.ui.DriverUI;
import problem12.ui.UserUI;

import static org.junit.jupiter.api.Assertions.*;

public class RideShareIntegrationTest {

    @Test
    public void testCompleteRideScenario() {
        // Create controller with real services and database
        RideController controller = new RideController();

        // Create UI factories
        UserFactory userFactory = new UserFactory(controller);
        DriverFactory driverFactory = new DriverFactory(controller);

        // Create UIs
        UserUI userUI = (UserUI) userFactory.createUI();
        DriverUI driverUI = (DriverUI) driverFactory.createUI();

        // User requests a ride
        int rideId = userUI.requestRide("Downtown", "Airport");
        assertTrue(rideId > 0);

        // Driver accepts the ride
        boolean accepted = driverUI.acceptRide(rideId);
        assertTrue(accepted);

        // Driver completes the ride
        boolean completed = driverUI.completeRide(rideId);
        assertTrue(completed);

        // User makes a payment
        boolean paymentProcessed = userUI.pay(rideId, 22.00);
        assertTrue(paymentProcessed);
    }

    @Test
    public void testPaymentBeforeCompletionScenario() {
        // Create controller with real services and database
        RideController controller = new RideController();

        // Create UI factories
        UserFactory userFactory = new UserFactory(controller);
        DriverFactory driverFactory = new DriverFactory(controller);

        // Create UIs
        UserUI userUI = (UserUI) userFactory.createUI();
        DriverUI driverUI = (DriverUI) driverFactory.createUI();

        // User requests a ride
        int rideId = userUI.requestRide("Downtown", "Airport");
        assertTrue(rideId > 0);

        // Driver accepts the ride
        boolean accepted = driverUI.acceptRide(rideId);
        assertTrue(accepted);

        // Try to pay before completion
        boolean paymentProcessed = userUI.pay(rideId, 22.00);
        assertFalse(paymentProcessed);
    }
}
