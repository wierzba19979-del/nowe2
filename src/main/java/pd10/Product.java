package pd10;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Product {
    private Long id;
    private String name;
    private Category category;
    private BigDecimal price;
    private int stock;
    private int sold;
    private double rating;

    public Product(Long id, String name, Category category, BigDecimal price, int stock, int sold, double rating) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.sold = sold;
        this.rating = rating;
    }

    public void setRating(double rating) {
        if (rating < 1.0 || rating > 5.0) {
            throw new IllegalArgumentException("Rating must be between 1.0 and 5.0");
        }
        this.rating = rating;
    }
}
