package pd12;

import pd12.config.Config;
import pd12.config.ConfigLoader;
import pd12.exporter.ReportExporter;
import pd12.model.CategoryStats;
import pd12.model.ProductRevenue;
import pd12.model.Report;
import pd12.model.Sale;
import pd12.processor.SalesProcessor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws Exception {
        Config config = ConfigLoader.loadConfig("config.json");

        System.out.println(config.getInputPath());
        System.out.println(config.getOutputPath());
        System.out.println(config.getDateFormat());

        SalesProcessor processor = new SalesProcessor(config.getDateFormat());

        List<Sale> sales = processor.loadSales(config.getInputPath());

        System.out.println("Loaded sales: " + sales.size());

        BigDecimal totalRevenue = processor.calculateTotalRevenue(sales);

        System.out.println("Total revenue: " + totalRevenue);
        List<ProductRevenue> topProducts = processor.calculateTopProducts(sales);

        System.out.println("\nTOP 3 PRODUCTS:");

        topProducts.forEach(
                product -> System.out.println(product.getProductName() + " - " + product.getRevenue())
        );

        List<CategoryStats> categoryStats =
                processor.calculateCategoryStats(sales);

        System.out.println("\nCATEGORY STATISTICS:");
        categoryStats.forEach(category ->
                System.out.println(
                        category.getCategory()
                                + " | Revenue: " + category.getRevenue()
                                + " | Average price: " + category.getAveragePrice()
                )
        );

        Optional<LocalDate> bestSalesDay =
                processor.findBestSalesDate(sales);
        System.out.println("Best sales day: " + bestSalesDay.orElse(null));

        Report report = Report.initialize(
                totalRevenue,
                topProducts,
                categoryStats,
                bestSalesDay.orElse(null)
        );
        ReportExporter exporter = new ReportExporter();
        exporter.exportJson(report, config.getOutputPath());
        System.out.println("JSON report exported successfully!");
        exporter.exportSummaryCsv(categoryStats, config.getOutputPath());
    }
}