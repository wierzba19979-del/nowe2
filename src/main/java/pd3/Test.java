package pd3;

import java.util.List;

public class Test {
    public static void main(String[] args) {

        PaymentProcessor creditCard = new CreditCardProcessor();
        PaymentProcessor paypal = new PayPalProcessor();
        PaymentProcessor crypto = new CryptoProcessor();

        PaymentGateway gateway =
                new PaymentGateway(List.of(creditCard, paypal, crypto));

        gateway.processPayment(100.0);
        gateway.refund(50.00);
        gateway.processPayment(206.0);
        gateway.processPayment(12.0);
        gateway.refund(50.00);

        System.out.println("\nHistoria transakcji:");

        for (Transaction transaction : gateway.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }
}
