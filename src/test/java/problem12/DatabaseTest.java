package problem12;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem12.model.Database;
import problem12.model.Ride;
import problem12.model.RideStatus;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {
    private Database database;

    @BeforeEach
    public void setUp() {
        database = new Database();
    }

    @Test
    public void testFindDriver() {
        int driverId = database.findDriver("Downtown");
        assertEquals(100, driverId);
    }

    @Test
    public void testSaveRideRequest() {
        int rideId = database.saveRideRequest(100, "Downtown", "Airport");
        Ride ride = database.getRide(rideId);

        assertNotNull(ride);
        assertEquals(rideId, ride.getRideId());
        assertEquals(100, ride.getDriverId());
        assertEquals("Downtown", ride.getPickupLocation());
        assertEquals("Airport", ride.getDestination());
        assertEquals(RideStatus.REQUESTED, ride.getStatus());
    }

    @Test
    public void testUpdateRideStatus() {
        int rideId = database.saveRideRequest(100, "Downtown", "Airport");
        database.updateRideStatus(rideId, RideStatus.ACCEPTED);

        Ride ride = database.getRide(rideId);
        assertEquals(RideStatus.ACCEPTED, ride.getStatus());
    }

    @Test
    public void testSavePayment() {
        database.savePayment(1, 22.00);
        Double payment = database.getPayment(1);

        assertNotNull(payment);
        assertEquals(22.00, payment);
    }
}
