package pd12.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor(staticName = "initialize")
@NoArgsConstructor
public class Report {
    private BigDecimal totalRevenue;
    private List<ProductRevenue> topProducts;
    private List<CategoryStats> categoryStats;
    private LocalDate bestSalesDay;
}