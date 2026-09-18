package pd4;

import java.util.ArrayList;
import java.util.List;

public class RentalSystem {
    private final List<Rental> rentals;

    public RentalSystem() {
        this.rentals = new ArrayList<>();
    }

    public static RentalSystem initialize() {
        return new RentalSystem();
    }
    public void addRental(Rental rental){
        rentals.add(rental);
    }
    public double calculateTotalCost(){
        double total = 0;
        for (Rental rental : rentals){
            total += rental.calculateCost();
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
            summaries.add(rental.getSummary());
        }

        return summaries;
    }
}
