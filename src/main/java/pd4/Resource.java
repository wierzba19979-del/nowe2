package pd4;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public abstract sealed class Resource implements Comparable<Resource> permits Book, Movie{
    private final int id;
    private String name;
    private double basePrice;
    private ResourceType type;

//    public Resource(int id, String name, double basePrice, ResourceType type) {
//        this.id = id;
//        this.name = name;
//        this.basePrice = basePrice;
//        this.type = type;
//    }

    public abstract double calculateRentalCost(int days);

    @Override
    public int compareTo(Resource other) {
        return Double.compare(getBasePrice(), other.getBasePrice());
    }
}
