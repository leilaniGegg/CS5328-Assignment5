package problem7;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete mediator implementation that manages messaging between riders.
 */
public class MessageHub implements Mediator {
    private final List<RiderClass> ridersList;

    public MessageHub() {
        this.ridersList = new ArrayList<>();
    }

    /**
     * Adds a rider to the messaging system
     * @param rider the rider to add
     */
    public void addRider(RiderClass rider) {
        ridersList.add(rider);
    }

    /**
     * Removes a rider from the messaging system
     * @param rider the rider to remove
     */
    public void removeRider(RiderClass rider) {
        ridersList.remove(rider);
    }

    @Override
    public void send(String message, String recipient) {
        for (RiderClass rider : ridersList) {
            if (rider.getName().equals(recipient)) {
                rider.update("Direct Message: " + message);
                return;
            }
        }
        System.out.println("Recipient not found: " + recipient);
    }

    @Override
    public void broadcast(String message) {
        for (RiderClass rider : ridersList) {
            rider.update("Broadcast: " + message);
        }
    }

    /**
     * Gets the current count of riders in the system
     * @return the number of riders
     */
    public int getRiderCount() {
        return ridersList.size();
    }
}