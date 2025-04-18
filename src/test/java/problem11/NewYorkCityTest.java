package problem11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem11.city.NewYorkCity;
import problem11.model.User;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class NewYorkCityTest {

    private NewYorkCity nyc;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setup() {
        nyc = new NewYorkCity();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testCityNameAndServices() {
        assertEquals("New York", nyc.getName());
        assertTrue(nyc.isServiceAvailable("ride"));
        assertFalse(nyc.isServiceAvailable("food"));
    }

    @Test
    public void testBroadcast() {
        nyc.broadcast("Test message");
        assertTrue(outContent.toString().contains("[New York BROADCAST] Test message"));
    }

    @Test
    public void testSendDirectMessage() {
        User user = new User("u1", "John", "New York");
        nyc.sendDirectMessage(user, "Test DM");
        assertTrue(outContent.toString().contains("[New York DM to John] Test DM"));
    }

    @Test
    public void testRequestRide() {
        User user = new User("u1", "John", "New York");
        int rideId = nyc.requestRide(user, "Times Square", "Central Park");

        assertTrue(rideId >= 1000); // Check that ID is in expected range
        assertTrue(outContent.toString().contains("Ride requested by John"));
    }

    @Test
    public void testCompleteRide() {
        User user = new User("u1", "John", "New York");
        int rideId = nyc.requestRide(user, "Times Square", "Central Park");

        assertTrue(nyc.completeRide(rideId));
        assertTrue(outContent.toString().contains("Ride " + rideId + " completed"));

        // Try to complete the same ride again (should fail)
        assertFalse(nyc.completeRide(rideId));
    }

    @Test
    public void testCalculateFare() {
        double fare = nyc.calculateFare("3.5", "15");
        assertEquals(16.75, fare); // Base(2.75) + Distance(3.5*2.50) + Time(15*0.35)

        // Test with invalid input
        assertEquals(0.0, nyc.calculateFare("invalid", "15"));
    }
}