package problem11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem11.city.DallasCity;
import problem11.model.User;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class DallasCityTest {

    private DallasCity dallas;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setup() {
        dallas = new DallasCity();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testCityNameAndServices() {
        assertEquals("Dallas", dallas.getName());
        assertTrue(dallas.isServiceAvailable("ride"));
        assertTrue(dallas.isServiceAvailable("food"));
        assertFalse(dallas.isServiceAvailable("grocery"));
    }

    @Test
    public void testRequestAndCompleteRide() {
        User user = new User("u2", "Alice", "Dallas");
        int rideId = dallas.requestRide(user, "Golden Gate Park", "Fisherman's Wharf");

        assertTrue(rideId >= 2000); // Check that ID is in expected range
        assertTrue(outContent.toString().contains("Ride requested by Alice"));

        boolean completed = dallas.completeRide(rideId);
        assertTrue(completed);
        assertTrue(outContent.toString().contains("Ride " + rideId + " completed"));
    }

    @Test
    public void testCalculateFare() {
        double fare = dallas.calculateFare("2.8", "12");
        assertEquals(18, fare); // Base(3.50) + Distance(2.8*3.25) + Time(12*0.45)
    }

    @Test
    public void testOrderFood() {
        User user = new User("u2", "Alice", "Dallas");
        int orderId = dallas.orderFood(user, "Golden Gate Grill", "Burger and fries");

        assertTrue(orderId >= 500); // Check that ID is in expected range
        assertTrue(outContent.toString().contains("Food ordered by Alice"));
    }

    @Test
    public void testTrackFoodOrder() {
        User user = new User("u2", "Alice", "Dallas");
        int orderId = dallas.orderFood(user, "Golden Gate Grill", "Burger and fries");

        boolean trackResult = dallas.trackFoodOrder(orderId);
        assertTrue(trackResult);
        assertTrue(outContent.toString().contains("on the way"));

        // Track non-existent order
        assertFalse(dallas.trackFoodOrder(9999));
        assertTrue(outContent.toString().contains("not found"));
    }

    @Test
    public void testCancelFoodOrder() {
        User user = new User("u2", "Alice", "Dallas");
        int orderId = dallas.orderFood(user, "Golden Gate Grill", "Burger and fries");

        boolean cancelResult = dallas.cancelFoodOrder(orderId);
        assertTrue(cancelResult);
        assertTrue(outContent.toString().contains("canceled"));

        // Try to track canceled order
        assertFalse(dallas.trackFoodOrder(orderId));
    }
}
