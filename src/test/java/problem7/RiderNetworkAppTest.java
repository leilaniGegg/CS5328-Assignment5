package problem7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RiderNetworkAppTest {

    @Test
    public void testRiderNetworkCommunication() {
        // Create the mediator
        MessageHub hub = new MessageHub();

        // Create riders with no initial announcement
        RiderClass sally = new RiderClass("Sally", hub, false);
        RiderClass bob = new RiderClass("Bob", hub, false);
        RiderClass charlie = new RiderClass("Charlie", hub, false);

        // Clear any existing messages
        sally.clearMessages();
        bob.clearMessages();
        charlie.clearMessages();

        // Sally posts a message (broadcast)
        sally.postMessage("Hello everyone!");

        // Verify everyone received the broadcast
        assertEquals(1, sally.getReceivedMessages().size());
        assertEquals(1, bob.getReceivedMessages().size());
        assertEquals(1, charlie.getReceivedMessages().size());

        // Bob sends a direct message to Charlie
        bob.sendDirectMessage("Hey Charlie, let's meet up!", "Charlie");

        // Verify only Charlie received Bob's direct message
        assertEquals(1, sally.getReceivedMessages().size());
        assertEquals(1, bob.getReceivedMessages().size());
        assertEquals(2, charlie.getReceivedMessages().size());

        // Charlie sends a direct message to Sally
        charlie.sendDirectMessage("Hey Sally, great ride today!", "Sally");

        // Verify Sally received Charlie's direct message
        assertEquals(2, sally.getReceivedMessages().size());
        assertEquals(1, bob.getReceivedMessages().size());
        assertEquals(2, charlie.getReceivedMessages().size());

        // Bob broadcasts an event
        bob.postMessage("Group ride this Saturday at 9am!");

        // Verify everyone received the broadcast
        assertEquals(3, sally.getReceivedMessages().size());
        assertEquals(2, bob.getReceivedMessages().size());
        assertEquals(3, charlie.getReceivedMessages().size());
    }

    @Test
    public void testRiderJoiningAndLeaving() {
        MessageHub hub = new MessageHub();

        // Create initial riders with no announcement
        RiderClass alex = new RiderClass("Alex", hub, false);
        RiderClass jamie = new RiderClass("Jamie", hub, false);

        // Clear any existing messages
        alex.clearMessages();
        jamie.clearMessages();

        // Add a new rider with announcement
        RiderClass taylor = new RiderClass("Taylor", hub, true);

        // Check if existing riders received notification
        assertEquals(1, alex.getReceivedMessages().size());
        assertEquals(1, jamie.getReceivedMessages().size());
        assertEquals("Broadcast: Taylor has joined the network.", alex.getReceivedMessages().get(0));

        // Remove a rider
        hub.removeRider(jamie);

        // Clear messages before next test
        alex.clearMessages();
        taylor.clearMessages();

        // Send a message after removal
        alex.postMessage("Testing after removal");

        // Jamie should not receive the message (but we can't verify this directly)
        assertEquals(1, alex.getReceivedMessages().size());
        assertEquals(1, taylor.getReceivedMessages().size());
    }
}