package pd3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PaymentGateway {
    private final List<PaymentProcessor> processors;
    private final List<Transaction> transactionHistory;

    public PaymentGateway(List<PaymentProcessor> processors) {
        if (processors == null || processors.isEmpty()) {
            throw new IllegalArgumentException("Lista nie może być pusta");
        }
        this.processors = processors;
        this.transactionHistory = new ArrayList<>();
    }

    private PaymentProcessor getBestProcessor() {
        return processors.stream()
                .min(Comparator.comparingDouble(PaymentProcessor::getTransactionFee)).orElseThrow();
    }

    public PaymentStatus processPayment(double amount) {
        validateAmount(amount);
        PaymentProcessor processor = getBestProcessor();
        PaymentStatus status = processor.processPayment(amount);
        transactionHistory.add(new Transaction(amount, processor.getTransactionFee(), processor.getClass().getSimpleName(), status));
        return status;
    }

    public PaymentStatus refund(double amount) {
        validateAmount(amount);
        PaymentProcessor processor = getBestProcessor();
        PaymentStatus status = processor.refund(amount);
        transactionHistory.add(new Transaction(amount, processor.getTransactionFee(), processor.getClass().getSimpleName(), status));
        return status;
    }

    private void validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Kwota musi być większa od 0!");
        }
    }

    public List<Transaction> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }
}
