package pd4;

import java.util.ArrayList;
import java.util.List;

public class RentalSystem {
    private final List<Rental> rentals;
    private static final double OVERDUE_FEE = 10.0;
    public RentalSystem() {
        this.rentals = new ArrayList<>();
    }

    public static RentalSystem initialize() {
        return new RentalSystem();
    }
    public void addRental(Rental rental){
        rentals.add(rental);
    }
    public double calculateTotalCost() {
        double total = 0;
        for (Rental rental : rentals) {
            double cost = rental.calculateCost();
            if (rental.getStatus() == RentalStatus.OVERDUE) {
                cost += OVERDUE_FEE;
            }
            total += cost;
        }
        return total;
    }
    public int countByStatus(RentalStatus status){
        int counter = 0;
        for (Rental rental : rentals) {
            if(rental.getStatus() == status){
                counter++;
            }
        }
        return counter;
    }
    public List<RentalSummary> getSummaries() {
        List<RentalSummary> summaries = new ArrayList<>();

        for (Rental rental : rentals) {
            double cost = rental.calculateCost();

            if (rental.getStatus() == RentalStatus.OVERDUE) {
                cost += OVERDUE_FEE;
            }
            summaries.add(
                    new RentalSummary(
                            rental.getResource().getName(),
                            rental.getStatus(),
                            cost
                    )
            );
        }
        return summaries;
    }
}
