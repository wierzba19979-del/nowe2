package pd8;

import pd8.model.Product;
import pd8.service.Warehouse;

import java.math.BigDecimal;
import java.util.DoubleSummaryStatistics;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        warehouse.addProduct(new Product(1L, "Rower", "Sprzęt sportowy", new BigDecimal("9999"), 20));
        warehouse.addProduct(new Product(2L, "Rakieta do squasha", "Sprzęt sportowy", new BigDecimal("449.99"), 6));
        warehouse.addProduct(new Product(3L, "Piłka do tennisa", "Sprzęt sportowy", new BigDecimal("20"), 7));
        warehouse.addProduct(new Product(4L, "Kajak", "Sprzęt sportowy", new BigDecimal("5000"), 9));
        warehouse.addProduct(new Product(5L, "Kij golfowy", "Sprzęt sportowy", new BigDecimal("853.49"), 15));
        warehouse.addProduct(new Product(55L, "Chleb", "Spozywcze", new BigDecimal("5.2"), 6));
        warehouse.addProduct(new Product(6L, "Woda", "Spozywcze", new BigDecimal("3.2"), 5));
        warehouse.addProduct(new Product(7L, "Mąka", "Spozywcze", new BigDecimal("2.5"), 8));
        warehouse.addProduct(new Product(8L, "Cukier", "Spozywcze", new BigDecimal("5.0"), 8));
        warehouse.addProduct(new Product(9L, "Czekolada", "Spozywcze", new BigDecimal("9.99"), 4));
        warehouse.addProduct(new Product(10L, "Szafa", "Meble", new BigDecimal("2500"), 20));
        warehouse.addProduct(new Product(11L, "Komoda", "Meble", new BigDecimal("1500"), 6));
        warehouse.addProduct(new Product(12L, "Łóżko", "Meble", new BigDecimal("3000"), 7));
        warehouse.addProduct(new Product(13L, "Biurko", "Meble", new BigDecimal("1200"), 9));
        warehouse.addProduct(new Product(14L, "Krzesło", "Meble", new BigDecimal("300"), 15));

        System.out.println("Number of products: "
                + warehouse.getProductCount());

        System.out.println("Low stock:");
        warehouse.getLowStockReport().forEach(System.out::println);

        System.out.println("Category sorted by price:");
        warehouse.findByCategory("Meble")
                .forEach(System.out::println);

        System.out.println("Category price statistics:");
        Map<String, DoubleSummaryStatistics> stats =
                warehouse.getCategoryStatus();
        stats.forEach((category, s) -> {
            System.out.println("Category: " + category);
            System.out.println("Number of products: " + s.getCount());
            System.out.printf("Lowest price: %.2f%n", s.getMin());
            System.out.printf("Highest price: %.2f%n", s.getMax());
            System.out.printf("Average price: %.2f%n", s.getAverage());
            System.out.printf("Total price: %.2f%n", s.getSum());
            System.out.println();
        });

        System.out.println("Products sorted by price:");
        warehouse.exportSortedByPrice().forEach((price, products) -> {
            System.out.println("Price: " + price);
            products.forEach(System.out::println);
        });

        System.out.println("Update product quantity:");
        warehouse.updateQuantity(1L, 3);
        System.out.println("Updated product:");
        warehouse.findByCategory("Sprzęt sportowy")
                .stream()
                .filter(p -> p.getId().equals(1L))
                .forEach(System.out::println);


        warehouse.addProduct(new Product(11L, "Stół", "Meble", new BigDecimal("700"), 2));

        warehouse.removeProduct(1L);

        System.out.println("Updated low stock report:");
        warehouse.getLowStockReport().forEach(System.out::println);

    }
}
