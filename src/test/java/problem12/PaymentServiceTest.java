package problem12;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem12.model.Database;
import problem12.model.RideStatus;
import problem12.service.PaymentService;
import problem12.model.Ride;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PaymentServiceTest {
    private PaymentService paymentService;
    private Database mockDatabase;

    @BeforeEach
    public void setUp() {
        mockDatabase = mock(Database.class);
        paymentService = new PaymentService(mockDatabase);
    }

    @Test
    public void testProcessPaymentSuccess() {
        Ride mockRide = mock(Ride.class);
        when(mockRide.getStatus()).thenReturn(RideStatus.COMPLETED);
        when(mockDatabase.getRide(1)).thenReturn(mockRide);

        boolean result = paymentService.processPayment(1, 22.00);

        assertTrue(result);
        verify(mockDatabase).savePayment(1, 22.00);
    }

    @Test
    public void testProcessPaymentFailureNotFound() {
        when(mockDatabase.getRide(1)).thenReturn(null);

        boolean result = paymentService.processPayment(1, 22.00);

        assertFalse(result);
        verify(mockDatabase, never()).savePayment(anyInt(), anyDouble());
    }

    @Test
    public void testProcessPaymentFailureWrongStatus() {
        Ride mockRide = mock(Ride.class);
        when(mockRide.getStatus()).thenReturn(RideStatus.IN_PROGRESS);
        when(mockDatabase.getRide(1)).thenReturn(mockRide);

        boolean result = paymentService.processPayment(1, 22.00);

        assertFalse(result);
        verify(mockDatabase, never()).savePayment(anyInt(), anyDouble());
    }
}

