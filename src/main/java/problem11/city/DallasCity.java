package problem11.city;

import problem11.interfaces.CityInterface;
import problem11.interfaces.FoodDeliveryServiceInterface;
import problem11.interfaces.RideServiceInterface;
import problem11.model.User;

import java.util.HashMap;
import java.util.Map;

public class DallasCity implements CityInterface, RideServiceInterface, FoodDeliveryServiceInterface {
    private static final String CITY_NAME = "Dallas";
    private final Map<Integer, String> activeRides = new HashMap<>();
    private final Map<Integer, String> foodOrders = new HashMap<>();
    private int rideCounter = 2000;
    private int orderCounter = 500;

    @Override
    public String getName() {
        return CITY_NAME;
    }

    @Override
    public boolean isServiceAvailable(String service) {
        return "ride".equals(service) || "food".equals(service);
    }

    @Override
    public void broadcast(String message) {
        System.out.println("[" + CITY_NAME + " BROADCAST] " + message);
    }

    @Override
    public void sendDirectMessage(User user, String message) {
        if (user != null) {
            System.out.println("[" + CITY_NAME + " DM to " + user.getName() + "] " + message);
        }
    }

    @Override
    public int requestRide(User user, String pickup, String destination) {
        int rideId = rideCounter++;
        activeRides.put(rideId, user.getId());
        System.out.println("[" + CITY_NAME + "] Ride requested by " + user.getName() + " from " + pickup + " to " + destination);
        return rideId;
    }

    @Override
    public boolean cancelRide(int rideId) {
        if (activeRides.containsKey(rideId)) {
            activeRides.remove(rideId);
            System.out.println("[" + CITY_NAME + "] Ride " + rideId + " canceled");
            return true;
        }
        return false;
    }

    @Override
    public boolean completeRide(int rideId) {
        if (activeRides.containsKey(rideId)) {
            activeRides.remove(rideId);
            System.out.println("[" + CITY_NAME + "] Ride " + rideId + " completed");
            return true;
        }
        return false;
    }

    @Override
    public double calculateFare(String distance, String duration) {
        // Simple fare calculation for Dallas (different rates)
        double baseRate = 3.50;
        double perMile = 3.25;
        double perMinute = 0.45;

        try {
            double distanceValue = Double.parseDouble(distance);
            double durationValue = Double.parseDouble(duration);

            return baseRate + (distanceValue * perMile) + (durationValue * perMinute);
        } catch (NumberFormatException e) {
            System.out.println("[" + CITY_NAME + "] Invalid distance or duration format");
            return 0.0;
        }
    }

    @Override
    public int orderFood(User user, String restaurant, String items) {
        int orderId = orderCounter++;
        foodOrders.put(orderId, user.getId());
        System.out.println("[" + CITY_NAME + "] Food ordered by " + user.getName() +
                " from " + restaurant + ": " + items);
        return orderId;
    }

    @Override
    public boolean cancelFoodOrder(int orderId) {
        if (foodOrders.containsKey(orderId)) {
            foodOrders.remove(orderId);
            System.out.println("[" + CITY_NAME + "] Food order " + orderId + " canceled");
            return true;
        }
        return false;
    }

    @Override
    public boolean trackFoodOrder(int orderId) {
        if (foodOrders.containsKey(orderId)) {
            System.out.println("[" + CITY_NAME + "] Food order " + orderId + " is on the way");
            return true;
        }
        System.out.println("[" + CITY_NAME + "] Food order " + orderId + " not found");
        return false;
    }
}
