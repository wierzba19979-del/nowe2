package pd3;

public class PayPalProcessor implements PaymentProcessor{
    private static final double FEE = 0.035;

    @Override
    public PaymentStatus processPayment(double amount){
        if(amount <= 0 ){
            return PaymentStatus.FAILED;
        }
        System.out.println("Płatność PayPal: " + amount + "PLN");
        return PaymentStatus.SUCCESS;
    }

    @Override
    public PaymentStatus refund(double amount){
        if(amount <= 0 ){
            return PaymentStatus.FAILED;
        }
        System.out.println("Zwrot płatności PayPal: " + amount + "PLN");
        return PaymentStatus.REFUNDED;
    }


    @Override
    public double getTransactionFee(){
        return FEE;
    }
}
