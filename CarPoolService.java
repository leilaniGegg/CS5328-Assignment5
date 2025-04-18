class CarPoolService implements RideStrategy { 
    public double getPrice(double distance) { 
        return 5 + 0.5 * distance; // Base fare + per mile rate 
    } 
} 