package pd12.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor(staticName = "initialize")
@Getter
public class Sale {
    private LocalDate date;
    private String productId;
    private String productName;
    private String category;
    private int quantity;
    private BigDecimal unitPrice;

    public BigDecimal getRevenue() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}