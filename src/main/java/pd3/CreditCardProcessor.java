package pd3;

public class CreditCardProcessor implements PaymentProcessor {
    private static final double FEE_PERCENTAGE = 3;

    @Override
    public PaymentStatus processPayment(double amount) {
        if (amount <= 0) {
            return PaymentStatus.FAILED;
        }
        System.out.println("Płatność kartą kredytową: " + amount + "PLN");
        return PaymentStatus.SUCCESS;
    }

    @Override
    public PaymentStatus refund(double amount) {
        if (amount <= 0) {
            return PaymentStatus.FAILED;
        }
        System.out.println("Zwrot płatności kartą: " + amount + "PLN");
        return PaymentStatus.REFUNDED;
    }


    @Override
    public double getTransactionFeePercentage() {
        return FEE_PERCENTAGE;
    }
}
