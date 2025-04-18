class Coupon { 
    private String code; 
    private double discount; 
    private String description; 
 
    public Coupon(String code, double discount, String description) { 
        this.code = code; 
        this.discount = discount; 
        this.description = description; 
    } 
 
    public String getCode() { return code; } 
    public double getDiscount() { return discount; } 
    public String getDescription() { return description; } 
 
    @Override 
    public String toString() { 
        return String.format("Coupon: %s (%.0f%% off) - %s",  
               code, discount * 100, description); 
    } 
} 