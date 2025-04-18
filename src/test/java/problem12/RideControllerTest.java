package problem12;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem12.controller.RideController;
import problem12.model.RideStatus;
import problem12.service.PaymentService;
import problem12.service.RideService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RideControllerTest {
    private RideController controller;
    private RideService mockRideService;
    private PaymentService mockPaymentService;

    @BeforeEach
    public void setUp() {
        mockRideService = mock(RideService.class);
        mockPaymentService = mock(PaymentService.class);
        controller = new RideController(mockRideService, mockPaymentService);
    }

    @Test
    public void testRequestRide() {
        when(mockRideService.findDriver("Downtown", "Airport")).thenReturn(1);

        int rideId = controller.requestRide("Downtown", "Airport");

        assertEquals(1, rideId);
        verify(mockRideService).findDriver("Downtown", "Airport");
    }

    @Test
    public void testAcceptRide() {
        when(mockRideService.assignDriver(1)).thenReturn(true);

        boolean result = controller.acceptRide(1);

        assertTrue(result);
        verify(mockRideService).assignDriver(1);
    }

    @Test
    public void testCompleteRide() {
        when(mockRideService.updateRideStatus(1, RideStatus.COMPLETED)).thenReturn(true);

        boolean result = controller.completeRide(1);

        assertTrue(result);
        verify(mockRideService).updateRideStatus(1, RideStatus.COMPLETED);
    }

    @Test
    public void testProcessPayment() {
        when(mockPaymentService.processPayment(1, 22.00)).thenReturn(true);

        boolean result = controller.processPayment(1, 22.00);

        assertTrue(result);
        verify(mockPaymentService).processPayment(1, 22.00);
    }
}
