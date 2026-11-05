package pd12.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor(staticName = "initialize")
public class ProductRevenue {

    private String productId;
    private String productName;
    private BigDecimal revenue;
}