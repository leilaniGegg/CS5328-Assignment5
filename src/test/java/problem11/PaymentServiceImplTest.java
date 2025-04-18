package problem11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem11.service.PaymentServiceImpl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentServiceImplTest {

    private PaymentServiceImpl paymentService;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setup() {
        paymentService = new PaymentServiceImpl();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testProcessPayment() {
        boolean result = paymentService.processPayment(1001, 25.50);

        assertTrue(result);
        assertTrue(outContent.toString().contains("Processed payment of $25.5"));
    }

    @Test
    public void testVerifyPayment() {
        // First process a payment
        paymentService.processPayment(1001, 25.50);
        outContent.reset();

        // Then verify it
        boolean result = paymentService.verifyPayment(1001);

        assertTrue(result);
        assertTrue(outContent.toString().contains("Payment for ride 1001 verified"));

        // Verify non-existent payment
        outContent.reset();
        assertFalse(paymentService.verifyPayment(9999));
        assertTrue(outContent.toString().contains("No payment found"));
    }

    @Test
    public void testRefundPayment() {
        // First process a payment
        paymentService.processPayment(1001, 25.50);
        outContent.reset();

        // Then refund it
        boolean result = paymentService.refundPayment(1001);

        assertTrue(result);
        assertTrue(outContent.toString().contains("Refunded $25.5"));

        // Try to refund again (should fail)
        outContent.reset();
        assertFalse(paymentService.refundPayment(1001));
        assertTrue(outContent.toString().contains("No payment found"));
    }
}
