package pd4;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public final class Book extends Resource{
    private final String author;

    @Override
    public double calculateRentalCost(int days) {
        return getBasePrice() * days;
    }

}
