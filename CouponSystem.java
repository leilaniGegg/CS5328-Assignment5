import java.util.ArrayList;

class CouponSystem implements Subject { 
    private ArrayList<Observer> riders = new ArrayList<>(); 
    private Coupon currentCoupon; 
 
    @Override 
    public void subscribe(Observer observer) { 
        if (!riders.contains(observer)) { 
            riders.add(observer); 
            System.out.println(observer.getUserID() + " subscribed to coupons"); 
        } 
    } 
 
    @Override 
    public void unsubscribe(Observer observer) { 
        if (riders.remove(observer)) { 
            System.out.println(observer.getUserID() + " unsubscribed from coupons"); 
        } 
    } 
 
    @Override 
    public void notifyObservers() { 
        if (currentCoupon == null) { 
            System.out.println("No coupon to send"); 
            return; 
        } 
         
        for (Observer rider : riders) { 
            rider.update(currentCoupon); 
        } 
    } 
 
    public void sendCoupon() { 
        // In a real system, this would generate dynamic coupons 
        this.currentCoupon = new Coupon( 
            "SUMMER25",  
            0.25,  
            "Summer special discount" 
        ); 
        System.out.println("\nGenerated new coupon: " + currentCoupon); 
        notifyObservers(); 
    } 
} 