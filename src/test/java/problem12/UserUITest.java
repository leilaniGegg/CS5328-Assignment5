package problem12;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem12.controller.RideController;
import problem12.ui.UserUI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserUITest {
    private UserUI userUI;
    private RideController mockController;

    @BeforeEach
    public void setUp() {
        mockController = mock(RideController.class);
        userUI = new UserUI(mockController);
    }

    @Test
    public void testRequestRide() {
        when(mockController.requestRide("Downtown", "Airport")).thenReturn(1);

        int rideId = userUI.requestRide("Downtown", "Airport");

        assertEquals(1, rideId);
        verify(mockController).requestRide("Downtown", "Airport");
    }

    @Test
    public void testPay() {
        when(mockController.processPayment(1, 22.00)).thenReturn(true);

        boolean result = userUI.pay(1, 22.00);

        assertTrue(result);
        verify(mockController).processPayment(1, 22.00);
    }
}
