package problem11.interfaces;

public interface PaymentServiceInterface {
    boolean processPayment(int rideId, double amount);
    boolean refundPayment(int rideId);
    boolean verifyPayment(int rideId);
}