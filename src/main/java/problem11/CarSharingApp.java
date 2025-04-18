package problem11;

import problem11.facade.CarSharingFacade;
import problem11.model.User;

public class CarSharingApp {
    public static void main(String[] args) {
        // Create the facade
        CarSharingFacade facade = new CarSharingFacade();

        // Create users in different cities
        User alice = new User("u1", "Alice", "New York");
        User bob = new User("u2", "Bob", "San Francisco");

        System.out.println("\n--- Testing Core Functionality ---");
        // Request rides in different cities
        int aliceRideId = facade.findDriver(alice, "Times Square", "Central Park");
        int bobRideId = facade.findDriver(bob, "Golden Gate Park", "Fisherman's Wharf");

        // Calculate fares
        double aliceFare = facade.calculateFare(alice, "3.5", "15");
        double bobFare = facade.calculateFare(bob, "2.8", "12");

        System.out.println("Alice's fare: $" + aliceFare);
        System.out.println("Bob's fare: $" + bobFare);

        // Process payments
        facade.pay(aliceRideId, aliceFare);
        facade.pay(bobRideId, bobFare);

        System.out.println("\n--- Testing City-Specific Functionality ---");
        // Test messaging functionality
        facade.sendMessageToUser(alice, "Your ride is confirmed");
        facade.broadcastToCity("Surge pricing in effect due to high demand", "New York");

        System.out.println("\n--- Testing Food Delivery Functionality ---");
        // Test food delivery (only available in San Francisco)
        if (facade.isServiceAvailableInCity("food", "San Francisco")) {
            int bobOrderId = facade.orderFoodDelivery(bob, "Golden Gate Grill", "Burger and fries");
            facade.trackFoodOrder(bob, bobOrderId);
        }

        // Test food delivery in New York (not available)
        if (facade.isServiceAvailableInCity("food", "New York")) {
            facade.orderFoodDelivery(alice, "NYC Deli", "Sandwich");
        } else {
            System.out.println("Food delivery not available in New York yet");
        }
    }
}
