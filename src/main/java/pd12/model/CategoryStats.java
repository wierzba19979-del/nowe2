package pd12.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor(staticName = "initialize")
public class CategoryStats {

    private String category;
    private BigDecimal revenue;
    private BigDecimal averagePrice;
}