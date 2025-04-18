package problem12;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem12.model.Database;
import problem12.model.Ride;
import problem12.model.RideStatus;
import problem12.service.RideService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RideServiceTest {
    private RideService rideService;
    private Database mockDatabase;

    @BeforeEach
    public void setUp() {
        mockDatabase = mock(Database.class);
        rideService = new RideService(mockDatabase);
    }

    @Test
    public void testFindDriver() {
        when(mockDatabase.findDriver("Downtown")).thenReturn(100);
        when(mockDatabase.saveRideRequest(100, "Downtown", "Airport")).thenReturn(1);

        int rideId = rideService.findDriver("Downtown", "Airport");

        assertEquals(1, rideId);
        verify(mockDatabase).findDriver("Downtown");
        verify(mockDatabase).saveRideRequest(100, "Downtown", "Airport");
    }

    @Test
    public void testAssignDriverSuccess() {
        Ride mockRide = mock(Ride.class);
        when(mockRide.getStatus()).thenReturn(RideStatus.REQUESTED);
        when(mockDatabase.getRide(1)).thenReturn(mockRide);

        boolean result = rideService.assignDriver(1);

        assertTrue(result);
        verify(mockDatabase).updateRideStatus(1, RideStatus.ACCEPTED);
    }

    @Test
    public void testAssignDriverFailureNotFound() {
        when(mockDatabase.getRide(1)).thenReturn(null);

        boolean result = rideService.assignDriver(1);

        assertFalse(result);
        verify(mockDatabase, never()).updateRideStatus(anyInt(), any(RideStatus.class));
    }

    @Test
    public void testAssignDriverFailureWrongStatus() {
        Ride mockRide = mock(Ride.class);
        when(mockRide.getStatus()).thenReturn(RideStatus.ACCEPTED);
        when(mockDatabase.getRide(1)).thenReturn(mockRide);

        boolean result = rideService.assignDriver(1);

        assertFalse(result);
        verify(mockDatabase, never()).updateRideStatus(anyInt(), any(RideStatus.class));
    }

    @Test
    public void testUpdateRideStatusSuccess() {
        Ride mockRide = mock(Ride.class);
        when(mockDatabase.getRide(1)).thenReturn(mockRide);

        boolean result = rideService.updateRideStatus(1, RideStatus.COMPLETED);

        assertTrue(result);
        verify(mockDatabase).updateRideStatus(1, RideStatus.COMPLETED);
    }

    @Test
    public void testUpdateRideStatusFailure() {
        when(mockDatabase.getRide(1)).thenReturn(null);

        boolean result = rideService.updateRideStatus(1, RideStatus.COMPLETED);

        assertFalse(result);
        verify(mockDatabase, never()).updateRideStatus(anyInt(), any(RideStatus.class));
    }
}