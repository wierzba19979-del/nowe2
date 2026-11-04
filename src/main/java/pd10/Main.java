package pd10;

import java.awt.desktop.OpenFilesEvent;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();

        products.add(new Product(1L, "Bike", Category.SPORT, new BigDecimal("9999"), 10, 5, 4.8));
        products.add(new Product(2L, "Squash Racket", Category.SPORT, new BigDecimal("300"), 13, 11, 4.9));
        products.add(new Product(3L, "Tennis ball", Category.SPORT, new BigDecimal("12.99"), 100, 114, 2.7));
        products.add(new Product(4L, "Kayak", Category.SPORT, new BigDecimal("4999.99"), 2, 8, 3.87));
        products.add(new Product(5L, "Golf club", Category.SPORT, new BigDecimal("150"), 15, 20, 4.0));
        products.add(new Product(6L, "Bread", Category.GROCERIES, new BigDecimal("5.29"), 150, 800, 4.7));
        products.add(new Product(7L, "Water", Category.GROCERIES, new BigDecimal("2.99"), 500, 1275, 3.88));
        products.add(new Product(8L, "Flour", Category.GROCERIES, new BigDecimal("4.54"), 475, 864, 4.23));
        products.add(new Product(9L, "Sugar", Category.GROCERIES, new BigDecimal("5.86"), 560, 1502, 4.68));
        products.add(new Product(10L, "Chocolate", Category.GROCERIES, new BigDecimal("9.99"), 650, 845, 4.61));
        products.add(new Product(11L, "Wardrobe", Category.FURNITURE, new BigDecimal("1999"), 6, 6, 4.73));
        products.add(new Product(12L, "Chest of draws", Category.FURNITURE, new BigDecimal("1899.49"), 9, 11, 3.56));
        products.add(new Product(13L, "Bed", Category.FURNITURE, new BigDecimal("3499"), 7, 9, 4.59));
        products.add(new Product(14L, "Desk", Category.FURNITURE, new BigDecimal("899.49"), 6, 10, 3.88));
        products.add(new Product(15L, "Chair", Category.FURNITURE, new BigDecimal("988.35"), 10, 17, 4.88));
        products.add(new Product(16L, "Laptop", Category.ELECTRONIC, new BigDecimal("6999"), 5, 7, 4.19));
        products.add(new Product(17L, "Television", Category.ELECTRONIC, new BigDecimal("4999"), 9, 11, 2.86));
        products.add(new Product(18L, "Phone", Category.ELECTRONIC, new BigDecimal("3548.56"), 15, 14, 3.65));
        products.add(new Product(19L, "Monitor", Category.ELECTRONIC, new BigDecimal("1500"), 20, 21, 4.99));
        products.add(new Product(20L, "Headphones", Category.ELECTRONIC, new BigDecimal("500"), 35, 31, 4.87));
        products.add(new Product(21L, "Pen", Category.SCHOOL, new BigDecimal("2.99"), 5602, 4592, 4.65));
        products.add(new Product(22L, "Pencil", Category.SCHOOL, new BigDecimal("2.65"), 6582, 7850, 4.56));
        products.add(new Product(23L, "Notebook", Category.SCHOOL, new BigDecimal("14.68"), 2687, 2485, 3.56));
        products.add(new Product(24L, "Book", Category.SCHOOL, new BigDecimal("35.48"), 358, 480, 1.87));
        products.add(new Product(25L, "Backpack", Category.SCHOOL, new BigDecimal("345.58"), 98, 150, 3.85));

        System.out.println("\n1. Top 5 products by sales revenue:");
        products.stream().sorted(Comparator.comparing(
                        (Product p) ->
                                p.getPrice().multiply(BigDecimal.valueOf(p.getSold()))).reversed())
                .limit(5)
                .forEach(p -> System.out.println(
                        p.getName() + " - " + p.getPrice().multiply(BigDecimal.valueOf(p.getSold())) + " PLN"));

        System.out.println("\n2. Average rating by category.");
        Map<Category, Double> averageRating = products.stream()
                .collect(
                        Collectors.groupingBy(
                                Product::getCategory,
                                Collectors.averagingDouble(Product::getRating)));

        averageRating.forEach(((category, average) -> System.out.println(category + ": " + average)));

        averageRating.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(entry ->
                System.out.println("Higest average rating: " + entry.getKey() + " " + entry.getValue()));

        System.out.println("\n3. Product with low stock:");
        products.stream()
                .filter(p -> p.getStock() < 10)
                .sorted(Comparator.comparingInt(Product::getStock)).
                forEach(p -> System.out.println(p.getName() + " - Stock:" + p.getStock()));

        System.out.println("\n4. Number of products by categoty");
        Map<Category, Long> productCount = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
        productCount.forEach((category, count) -> System.out.println(category + ": products: " + count));

        System.out.println("\n5. Total revenue and average price by category");
        Map<Category, BigDecimal> revenue = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.reducing(
                        BigDecimal.ZERO,
                        p -> p.getPrice().multiply(BigDecimal.valueOf(p.getSold())),
                        BigDecimal::add)));

        Map<Category, Double> averagePrice = products.stream().collect(Collectors.groupingBy(
                Product::getCategory, Collectors.averagingDouble(p -> p.getPrice().doubleValue())));

        revenue.forEach((category, total) ->
                System.out.println(category + " | Revenue: " + total + " | Average price: " + averagePrice.get(category)));

        System.out.println("\n6. Product rated at least 4.5 and price below 500 PLN:");
        products.stream()
                .filter(p -> p.getRating() >= 4.5 && p.getPrice().compareTo(new BigDecimal("500")) < 0)
                .sorted(Comparator.comparing(Product::getPrice))
                .forEach(p -> System.out.println(
                        p.getName() + " | Price: " + p.getPrice() + "PLN | Rating:" + p.getRating()));

        System.out.print("\n7. All product have a rating greater than 0: ");
        System.out.println(products.stream().map(Product::getRating).allMatch(p -> p > 0));

        System.out.println("\n8. Most expensive product:");
        Optional<Product> mostExpensive = products.stream().max(Comparator.comparing(Product::getPrice));
        mostExpensive.ifPresentOrElse(
                p -> System.out.println(p.getName() + " - " + p.getPrice() + " PLN"),
                () -> System.out.println("No products available"));

        System.out.println("\n9. Premium/Standard partition:");
        Map<Boolean, Long> partition = products.stream()
                .collect(Collectors.partitioningBy(
                        p -> p.getPrice().compareTo(new BigDecimal("1000")) > 0,
                        Collectors.counting()
                ));
        System.out.println("Premium: " + partition.get(true));
        System.out.println("Standard: " + partition.get(false));

        System.out.println("\n10. Category report:");
        products.stream()
                .collect(Collectors.groupingBy(Product::getCategory))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> {
                    Product top = entry.getValue().stream()
                            .max(Comparator.comparing(
                                    (Product p) -> p.getPrice().multiply(BigDecimal.valueOf(p.getSold()))))
                            .orElseThrow();
                    BigDecimal productRevenue = top.getPrice().multiply(BigDecimal.valueOf(top.getSold()));
                    return "Category " + entry.getKey() + ": the top product by sales revenue is "
                            + top.getName() + " " + "with revenue of " + productRevenue + " PLN.";
                })
                .forEach(System.out::println);
    }
}
