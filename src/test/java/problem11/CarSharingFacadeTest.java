package problem11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem11.facade.CarSharingFacade;
import problem11.model.User;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class CarSharingFacadeTest {

    private CarSharingFacade facade;
    private User nycUser;
    private User dallasUser;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setup() {
        facade = new CarSharingFacade();
        nycUser = new User("u1", "John", "New York");
        dallasUser = new User("u2", "Alice", "Dallas");
        System.setOut(new PrintStream(outContent));
    }

    private boolean containsIgnoreCase(String[] array, String value) {
        for (String item : array) {
            if (item.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void testGetAvailableCities() {
        String[] cities = facade.getAvailableCities();
        assertEquals(2, cities.length);
        assertTrue(containsIgnoreCase(cities, "new york"));
        assertTrue(containsIgnoreCase(cities, "dallas"));
    }

    @Test
    public void testServiceAvailability() {
        assertTrue(facade.isServiceAvailableInCity("ride", "New York"));
        assertFalse(facade.isServiceAvailableInCity("food", "New York"));

        assertTrue(facade.isServiceAvailableInCity("ride", "Dallas"));
        assertTrue(facade.isServiceAvailableInCity("food", "Dallas"));
    }

    @Test
    public void testFindDriver() {
        int rideId = facade.findDriver(nycUser, "Times Square", "Central Park");
        assertTrue(rideId > 0);
        String output = outContent.toString();
        assertTrue(output.contains("Finding driver in New York"));
    }

    @Test
    public void testPayForRide() {
        int rideId = facade.findDriver(dallasUser, "Uptown", "Downtown");
        outContent.reset();
        boolean paymentSuccess = facade.pay(rideId, 20.0);
        String output = outContent.toString();
        assertTrue(paymentSuccess);
        assertTrue(output.contains("Processing payment of $20.0 for ride " + rideId));
    }

    @Test
    public void testFareCalculation() {
        double fare = facade.calculateFare(nycUser, "10", "20");
        assertTrue(fare > 0);
    }

    @Test
    public void testFoodOrderAndTracking() {
        int orderId = facade.orderFoodDelivery(dallasUser, "Taco Place", "3 tacos");
        assertTrue(orderId > 0);
        boolean tracked = facade.trackFoodOrder(dallasUser, orderId);
        assertTrue(tracked);
    }

    @Test
    public void testBroadcastMessage() {
        outContent.reset();
        facade.broadcastToCity("This is a test broadcast", "Dallas");
        String output = outContent.toString();
        assertTrue(output.contains("This is a test broadcast"));
    }

    @Test
    public void testSendMessageToUser() {
        outContent.reset();
        facade.sendMessageToUser(nycUser, "Your ride is on the way!");
        String output = outContent.toString();
        assertTrue(output.contains("Your ride is on the way!"));
    }
}
