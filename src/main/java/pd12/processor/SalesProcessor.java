package pd12.processor;

import pd12.model.CategoryStats;
import pd12.model.ProductRevenue;
import pd12.model.Sale;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class SalesProcessor {
    private final DateTimeFormatter dateFormatter;
    private record ProductKey(String id, String name) {}
    public SalesProcessor(String dateFormat) {
        this.dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
    }
    private Stream<Sale> parseSale(String line) {

        try {

            String[] fields = line.split(",", -1);

            if (fields.length != 6) {
                throw new IllegalArgumentException("Incorrect number of columns");
            }

            LocalDate date = LocalDate.parse(fields[0], dateFormatter);
            String productId = fields[1].trim();
            String productName = fields[2].trim();
            String category = fields[3].trim();
            int quantity = Integer.parseInt(fields[4].trim());
            BigDecimal unitPrice = new BigDecimal(fields[5].trim());

            if (productId.isEmpty() || productName.isEmpty() || category.isEmpty()) {
                throw new IllegalArgumentException("Empty text field");
            }
            if (quantity <= 0 || unitPrice.signum() < 0) {
                throw new IllegalArgumentException("Invalid quantity or price");
            }
            return Stream.of(Sale.initialize(
                    date,
                    productId,
                    productName,
                    category,
                    quantity,
                    unitPrice
            ));
        } catch (Exception e) {
            System.err.println("Skipping invalid CSV row: " + line
                    + " | Reason: " + e.getMessage());
            return Stream.empty();
        }
    }

    public List<Sale> loadSales(String filePath) throws IOException {
        try (Stream<String> lines = Files.lines(Path.of(filePath))) {
            return lines
                    .skip(1)
                    .map(this::parseSale)
                    .flatMap(stream->stream)
                    .collect(Collectors.toList());
        }
    }

    public BigDecimal calculateTotalRevenue(List<Sale> sales) {
        return sales.stream()
                .map(Sale::getRevenue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<ProductRevenue> calculateTopProducts(List<Sale> sales) {
        return sales.stream()
                .collect(Collectors.groupingBy(
                        sale -> new ProductKey(
                                sale.getProductId(),
                                sale.getProductName()
                        ),
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                Sale::getRevenue,
                                BigDecimal::add
                        )
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<ProductKey, BigDecimal>comparingByValue(
                        Comparator.reverseOrder()
                ))
                .limit(3)
                .map(entry -> ProductRevenue.initialize(
                        entry.getKey().id(),
                        entry.getKey().name(),
                        entry.getValue()
                ))
                .toList();
    }

    public List<CategoryStats> calculateCategoryStats(List<Sale> sales) {
        return sales.stream()
                .collect(Collectors.groupingBy(Sale::getCategory))
                .entrySet()
                .stream()
                .map(entry -> {
                    List<Sale> categorySales = entry.getValue();
                    BigDecimal revenue = categorySales.stream()
                            .map(Sale::getRevenue)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    BigDecimal priceSum = categorySales.stream()
                            .map(Sale::getUnitPrice)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    BigDecimal averagePrice = priceSum.divide(
                            BigDecimal.valueOf(categorySales.size()),
                            2,
                            RoundingMode.HALF_UP
                    );
                    return CategoryStats.initialize(
                            entry.getKey(),
                            revenue,
                            averagePrice
                    );
                })
                .sorted(Comparator.comparing(CategoryStats::getRevenue).reversed())
                .toList();
    }

    public Optional<LocalDate> findBestSalesDate(List<Sale> sales) {
        return sales.stream()
                .collect(Collectors.groupingBy(
                        Sale::getDate,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                Sale::getRevenue,
                                BigDecimal::add
                        )
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }
}