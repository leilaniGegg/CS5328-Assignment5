class Rider implements Observer { 
    private String userID; 
    private String name; 
    private String email; 
 
    public Rider(String userID, String name, String email) { 
        this.userID = userID; 
        this.name = name; 
        this.email = email; 
    } 
 
    @Override 
    public void update(Coupon coupon) { 
        System.out.printf("Notification sent to %s (%s): %s\n",  
            name, email, coupon); 
    } 
 
    @Override 
    public String getUserID() { 
        return userID; 
    } 
 
    public String getName() { return name; } 
    public String getEmail() { return email; } 
}