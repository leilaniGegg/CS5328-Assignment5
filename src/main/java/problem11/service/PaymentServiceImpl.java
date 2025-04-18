package problem11.service;


import problem11.interfaces.PaymentServiceInterface;

import java.util.HashMap;
import java.util.Map;

public class PaymentServiceImpl implements PaymentServiceInterface {
    private final Map<Integer, Double> payments = new HashMap<>();

    @Override
    public boolean processPayment(int rideId, double amount) {
        payments.put(rideId, amount);
        System.out.println("[Payment Service] Processed payment of $" + amount + " for ride " + rideId);
        return true;
    }

    @Override
    public boolean refundPayment(int rideId) {
        if (payments.containsKey(rideId)) {
            double amount = payments.remove(rideId);
            System.out.println("[Payment Service] Refunded $" + amount + " for ride " + rideId);
            return true;
        }
        System.out.println("[Payment Service] No payment found for ride " + rideId);
        return false;
    }

    @Override
    public boolean verifyPayment(int rideId) {
        boolean verified = payments.containsKey(rideId);
        if (verified) {
            System.out.println("[Payment Service] Payment for ride " + rideId + " verified: $" + payments.get(rideId));
        } else {
            System.out.println("[Payment Service] No payment found for ride " + rideId);
        }
        return verified;
    }
}