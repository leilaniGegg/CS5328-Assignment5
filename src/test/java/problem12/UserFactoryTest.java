package problem12;

import org.junit.jupiter.api.Test;
import problem12.controller.RideController;
import problem12.factory.UserFactory;
import problem12.ui.UI;
import problem12.ui.UserUI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserFactoryTest {

    @Test
    public void testCreateUI() {
        RideController mockController = mock(RideController.class);
        UserFactory factory = new UserFactory(mockController);

        UI ui = factory.createUI();

        assertNotNull(ui);
        assertTrue(ui instanceof UserUI);
    }
}
