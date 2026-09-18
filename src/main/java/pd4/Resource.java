package pd4;

public abstract sealed class Resource implements Comparable<Resource> permits Book, Movie{
    private final int id;
    private String name;
    private double basePrice;
    private ResourceType type;

    public Resource(int id, String name, double basePrice, ResourceType type) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.type = type;
    }

    public abstract double calculateRentalCost(int days);

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public ResourceType getType() {
        return type;
    }

    @Override
    public int compareTo(Resource other) {
        return Double.compare(getBasePrice(), other.getBasePrice());
    }
}
