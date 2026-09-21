package pd3;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
@RequiredArgsConstructor
@Getter
public class Transaction {
    private final double amount;
    private final double feePercentage;
    private final String processorName;
    private final PaymentStatus status;
    private final LocalDateTime date = LocalDateTime.now();

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
