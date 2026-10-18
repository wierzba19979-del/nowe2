package pd8.service;

import pd8.model.Product;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse {
    private Map<Long, Product> inventory = new HashMap<>();
    private Map<String, Set<Product>> byCategory = new HashMap<>();
    private PriorityQueue<Product> lowStock =
            new PriorityQueue<>(Comparator.comparingInt(Product::getQuantity).thenComparing(Product::getId));
    public static final int LOW_STOCK_LIMIT = 5;


    public void addProduct(Product product) {
        Objects.requireNonNull(product, "product");
        if (inventory.containsKey(product.getId())) {
            throw new IllegalArgumentException("Product with ID " + product.getId() + " already exists");
        }
        inventory.put(product.getId(), product);
        byCategory.computeIfAbsent(
                product.getCategory(), k -> new HashSet<>()
        ).add(product);
        if (product.getQuantity() < LOW_STOCK_LIMIT) {
            lowStock.add(product);
        }
    }


    public void removeProduct(Long productId) {
        Product product = inventory.remove(productId);
        if (product == null) {
            return;
        }
        Set<Product> products = byCategory.get(product.getCategory());
        if (products != null) {
            products.remove(product);
            if (products.isEmpty()) {
                byCategory.remove(product.getCategory());
            }
        }
        lowStock.remove(product);
    }


    public void updateQuantity(Long id, int newQuantity) {
        if (newQuantity < 0) throw new IllegalArgumentException("Quantity cannot be negative");
        Product p = inventory.get(id);
        if (p == null) throw new NoSuchElementException("No product found with ID " + id);
        lowStock.remove(p);
        p.setQuantity(newQuantity);
        if (newQuantity < LOW_STOCK_LIMIT) lowStock.add(p);
    }

    public List<Product> findByCategory(String category) {
        List<Product> result = new ArrayList<>(byCategory.getOrDefault(category, Set.of()));
        result.sort(Comparator.comparing(Product::getPrice).thenComparing(Comparator.naturalOrder()));
        return result;
    }

    public List<Product> getLowStockReport() {
        return inventory.values().stream()
                .filter(p -> p.getQuantity() < LOW_STOCK_LIMIT)
                .sorted(Comparator.comparingInt(Product::getQuantity)
                        .thenComparing(Product::getId))
                .collect(Collectors.toList());
    }


    public Map<String, DoubleSummaryStatistics> getCategoryStatus(){
        return inventory.values().stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        TreeMap::new,
                        Collectors.summarizingDouble(p->p.getPrice().doubleValue())
                ));
    }
    public TreeMap<BigDecimal, List<Product>> exportSortedByPrice() {
        TreeMap<BigDecimal, List<Product>> result = new TreeMap<>();
        for (Product p : inventory.values())
            result.computeIfAbsent(p.getPrice(), k -> new ArrayList<>()).add(p);
        result.values().forEach(Collections::sort);
        return result;
    }

    public int getProductCount() {
        return inventory.size();
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(inventory.get(id));
    }
}
