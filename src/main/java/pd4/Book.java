package pd4;

public final class Book extends Resource{
    private String author;

    public Book(int id, String name, double basePrice, ResourceType type, String author) {
        super(id, name, basePrice, type);
        this.author = author;
    }

    @Override
    public double calculateRentalCost(int days) {
        return getBasePrice() * days;
    }

    public String getAuthor() {
        return author;
    }
}
