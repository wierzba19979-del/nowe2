package pd3;

import java.time.LocalDateTime;

public class Transaction {
    private final double amount;
    private final double feePercentage;
    private final String processorName;
    private final PaymentStatus status;
    private final LocalDateTime date;

    public Transaction(double amount, double feePercentage, String processorName, PaymentStatus status) {
        this.amount = amount;
        this.feePercentage = feePercentage;
        this.processorName = processorName;
        this.status = status;
        this.date = LocalDateTime.now();
    }

    public double getAmount() {
        return amount;
    }

    public double getFeePercentage() {
        return feePercentage;
    }

    public String getProcessorName() {
        return processorName;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", feePercentage=" + feePercentage +
                ", processorName='" + processorName + '\'' +
                ", status=" + status +
                ", date=" + date +
                '}';
    }
}
