package pd3;

public class CryptoProcessor implements PaymentProcessor {
    private static final double FEE = 0.01;

    @Override
    public PaymentStatus processPayment(double amount){
        if(amount <= 0 ){
            return PaymentStatus.FAILED;
        }
        System.out.println("Płatność crypto: " + amount + "PLN");
        return PaymentStatus.SUCCESS;
    }

    @Override
    public PaymentStatus refund(double amount){
        if(amount <= 0 ){
            return PaymentStatus.FAILED;
        }
        System.out.println("Zwrot płatności crypto: " + amount + "PLN");
        return PaymentStatus.REFUNDED;
    }


    @Override
    public double getTransactionFee(){
        return FEE;
    }

}
