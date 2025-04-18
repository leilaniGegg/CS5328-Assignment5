package problem7;

/**
 * Demo application to showcase the rider social network functionality
 */
public class RiderNetworkApp {
    public static void main(String[] args) {
        // Create the mediator
        MessageHub hub = new MessageHub();

        // Create riders
        RiderClass sally = new RiderClass("Sally", hub);
        RiderClass bob = new RiderClass("Bob", hub);
        RiderClass charlie = new RiderClass("Charlie", hub);

        System.out.println("\n--- Broadcasting messages ---");
        // Sally posts a message (broadcast)
        sally.postMessage("Hello everyone!");

        System.out.println("\n--- Direct messaging ---");
        // Bob sends a direct message to Charlie
        bob.sendDirectMessage("Hey Charlie, let's meet up!", "Charlie");

        // Charlie sends a direct message to Sally
        charlie.sendDirectMessage("Hey Sally, great ride today!", "Sally");

        System.out.println("\n--- Group activity announcement ---");
        // Bob broadcasts an event
        bob.postMessage("Group ride this Saturday at 9am!");
    }
}