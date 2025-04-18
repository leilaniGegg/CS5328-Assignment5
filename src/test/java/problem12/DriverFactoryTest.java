package problem12;

import org.junit.jupiter.api.Test;
import problem12.controller.RideController;
import problem12.factory.DriverFactory;
import problem12.ui.DriverUI;
import problem12.ui.UI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DriverFactoryTest {

    @Test
    public void testCreateUI() {
        RideController mockController = mock(RideController.class);
        DriverFactory factory = new DriverFactory(mockController);

        UI ui = factory.createUI();

        assertNotNull(ui);
        assertTrue(ui instanceof DriverUI);
    }
}