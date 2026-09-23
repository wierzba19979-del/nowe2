package pd4;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public final class Movie extends Resource{
    private final int duration;
    private static final double EXTRA_FEE_FOR_MOVIE = 10.0;

    @Override
    public double calculateRentalCost(int days) {
        return getBasePrice() * days + EXTRA_FEE_FOR_MOVIE;
    }

}
