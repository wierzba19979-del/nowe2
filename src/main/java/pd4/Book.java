package pd4;

import lombok.Getter;

@Getter
public final class Book extends Resource{
    private final String author;

    public Book(int id, String name, double basePrice, ResourceType type, String author) {
        super(id, name, basePrice, type);
        this.author = author;
    }

    @Override
    public double calculateRentalCost(int days) {
        return getBasePrice() * days;
    }

}
