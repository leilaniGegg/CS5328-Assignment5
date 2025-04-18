class CarBlackService implements RideStrategy { 
    public double getPrice(double distance) { 
        return 15 + 1.2 * distance; 
    } 
} 