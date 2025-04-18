class CarXService implements RideStrategy { 
    public double getPrice(double distance) { 
        return 8 + 0.8 * distance; 
    } 
}  