package problem12;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem12.controller.RideController;
import problem12.ui.DriverUI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DriverUITest {
    private DriverUI driverUI;
    private RideController mockController;

    @BeforeEach
    public void setUp() {
        mockController = mock(RideController.class);
        driverUI = new DriverUI(mockController);
    }

    @Test
    public void testAcceptRide() {
        when(mockController.acceptRide(1)).thenReturn(true);

        boolean result = driverUI.acceptRide(1);

        assertTrue(result);
        verify(mockController).acceptRide(1);
    }

    @Test
    public void testCompleteRide() {
        when(mockController.completeRide(1)).thenReturn(true);

        boolean result = driverUI.completeRide(1);

        assertTrue(result);
        verify(mockController).completeRide(1);
    }
}
