package pd4;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@AllArgsConstructor
public class Rental {
    private final Resource resource;
    @Setter
    private RentalStatus status;
    private final int days;

    public double calculateCost(){
        return getResource().calculateRentalCost(days);
    }
}
