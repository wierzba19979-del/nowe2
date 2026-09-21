package pd4;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
@SuperBuilder
@Getter
public abstract sealed class Resource implements Comparable<Resource> permits Book, Movie{
    private final int id;
    private String name;
    private double basePrice;
    private ResourceType type;

    public abstract double calculateRentalCost(int days);

    @Override
    public int compareTo(Resource other) {
        return Double.compare(getBasePrice(), other.getBasePrice());
    }
}
