package problem11.facade;

import problem11.city.DallasCity;
import problem11.city.NewYorkCity;
import problem11.interfaces.CityInterface;
import problem11.interfaces.FoodDeliveryServiceInterface;
import problem11.interfaces.PaymentServiceInterface;
import problem11.interfaces.RideServiceInterface;
import problem11.model.User;
import problem11.service.PaymentServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Facade pattern implementation that provides a unified interface to multiple city-specific services
 */
public class CarSharingFacade {
    private final Map<String, CityInterface> cities = new HashMap<>();
    private final PaymentServiceInterface paymentService;

    public CarSharingFacade() {
        // Initialize cities
        NewYorkCity nyc = new NewYorkCity();
        DallasCity sf = new DallasCity();

        cities.put(nyc.getName().toLowerCase(), nyc);
        cities.put(sf.getName().toLowerCase(), sf);

        // Initialize central payment service
        paymentService = new PaymentServiceImpl();
    }

    /**
     * Find a driver in the user's city
     */
    public int findDriver(User user, String pickup, String destination) {
        CityInterface userCity = getCityForUser(user);
        if (userCity != null && userCity instanceof RideServiceInterface) {
            System.out.println("CarSharingFacade: Finding driver in " + userCity.getName());
            return ((RideServiceInterface) userCity).requestRide(user, pickup, destination);
        }
        System.out.println("CarSharingFacade: Ride service not available in " + (user != null ? user.getCity() : "unknown city"));
        return -1;
    }

    /**
     * Process payment for a ride
     */
    public boolean pay(int rideId, double amount) {
        System.out.println("CarSharingFacade: Processing payment of $" + amount + " for ride " + rideId);
        return paymentService.processPayment(rideId, amount);
    }

    /**
     * Send direct message to a user
     */
    public void sendMessageToUser(User user, String message) {
        CityInterface userCity = getCityForUser(user);
        if (userCity != null) {
            userCity.sendDirectMessage(user, message);
        } else {
            System.out.println("CarSharingFacade: Cannot send message, city not found for user " + user.getName());
        }
    }

    /**
     * Broadcast message to all users in a city
     */
    public void broadcastToCity(String message, String cityName) {
        CityInterface city = cities.get(cityName.toLowerCase());
        if (city != null) {
            city.broadcast(message);
        } else {
            System.out.println("CarSharingFacade: City " + cityName + " not found");
        }
    }

    /**
     * Order food delivery (city-specific functionality)
     */
    public int orderFoodDelivery(User user, String restaurant, String items) {
        CityInterface userCity = getCityForUser(user);
        if (userCity != null && userCity instanceof FoodDeliveryServiceInterface) {
            System.out.println("CarSharingFacade: Ordering food delivery in " + userCity.getName());
            return ((FoodDeliveryServiceInterface) userCity).orderFood(user, restaurant, items);
        }
        System.out.println("CarSharingFacade: Food delivery service not available in " +
                (user != null ? user.getCity() : "unknown city"));
        return -1;
    }

    /**
     * Track food order (city-specific functionality)
     */
    public boolean trackFoodOrder(User user, int orderId) {
        CityInterface userCity = getCityForUser(user);
        if (userCity != null && userCity instanceof FoodDeliveryServiceInterface) {
            return ((FoodDeliveryServiceInterface) userCity).trackFoodOrder(orderId);
        }
        System.out.println("CarSharingFacade: Food delivery service not available in " +
                (user != null ? user.getCity() : "unknown city"));
        return false;
    }

    /**
     * Calculate fare based on city-specific rates
     */
    public double calculateFare(User user, String distance, String duration) {
        CityInterface userCity = getCityForUser(user);
        if (userCity != null && userCity instanceof RideServiceInterface) {
            return ((RideServiceInterface) userCity).calculateFare(distance, duration);
        }
        System.out.println("CarSharingFacade: Ride service not available in " +
                (user != null ? user.getCity() : "unknown city"));
        return 0.0;
    }

    /**
     * Helper method to get city for a user
     */
    private CityInterface getCityForUser(User user) {
        if (user == null || user.getCity() == null) {
            return null;
        }
        return cities.get(user.getCity().toLowerCase());
    }

    /**
     * Check if a service is available in a city
     */
    public boolean isServiceAvailableInCity(String service, String cityName) {
        CityInterface city = cities.get(cityName.toLowerCase());
        if (city != null) {
            return city.isServiceAvailable(service);
        }
        return false;
    }

    /**
     * Get all available cities
     */
    public String[] getAvailableCities() {
        return cities.keySet().toArray(new String[0]);
    }
}
