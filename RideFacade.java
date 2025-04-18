import java.util.Map;
import java.util.HashMap;

class RideFacade { 
    private Map<String, RideStrategy> services; 
     
    public RideFacade() { 
        services = new HashMap<>(); 
        services.put("carPOOL", new CarPoolService()); 
        services.put("carX", new CarXService()); 
        services.put("carBlack", new CarBlackService()); 
    } 
     
    public double getPrice(String serviceType, double distance) { 
        if (services.containsKey(serviceType)) { 
            return services.get(serviceType).getPrice(distance); 
        } else { 
            throw new IllegalArgumentException("Invalid service type"); 
        } 
    } 
} 